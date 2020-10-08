/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Collectors;
/*     */ import org.jline.terminal.Terminal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Display
/*     */ {
/*     */   protected final Terminal terminal;
/*     */   protected final boolean fullScreen;
/*  30 */   protected List<AttributedString> oldLines = Collections.emptyList();
/*     */   
/*     */   protected int cursorPos;
/*     */   private int columns;
/*     */   private int columns1;
/*     */   protected int rows;
/*     */   protected boolean reset;
/*     */   protected boolean delayLineWrap;
/*  38 */   protected final Map<InfoCmp.Capability, Integer> cost = new HashMap<>();
/*     */   protected final boolean canScroll;
/*     */   protected final boolean wrapAtEol;
/*     */   protected final boolean delayedWrapAtEol;
/*     */   protected final boolean cursorDownIsNewLine;
/*     */   
/*     */   public Display(Terminal terminal, boolean fullscreen) {
/*  45 */     this.terminal = terminal;
/*  46 */     this.fullScreen = fullscreen;
/*     */     
/*  48 */     this
/*  49 */       .canScroll = (can(InfoCmp.Capability.insert_line, InfoCmp.Capability.parm_insert_line) && can(InfoCmp.Capability.delete_line, InfoCmp.Capability.parm_delete_line));
/*  50 */     this.wrapAtEol = terminal.getBooleanCapability(InfoCmp.Capability.auto_right_margin);
/*  51 */     this
/*  52 */       .delayedWrapAtEol = (this.wrapAtEol && terminal.getBooleanCapability(InfoCmp.Capability.eat_newline_glitch));
/*  53 */     this.cursorDownIsNewLine = "\n".equals(Curses.tputs(terminal.getStringCapability(InfoCmp.Capability.cursor_down), new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean delayLineWrap() {
/*  62 */     return this.delayLineWrap;
/*     */   } public void setDelayLineWrap(boolean v) {
/*  64 */     this.delayLineWrap = v;
/*     */   }
/*     */   public void resize(int rows, int columns) {
/*  67 */     if (this.rows != rows || this.columns != columns) {
/*  68 */       this.rows = rows;
/*  69 */       this.columns = columns;
/*  70 */       this.columns1 = columns + 1;
/*  71 */       this.oldLines = AttributedString.join(AttributedString.EMPTY, this.oldLines).columnSplitLength(columns, true, delayLineWrap());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset() {
/*  76 */     this.oldLines = Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  84 */     if (this.fullScreen) {
/*  85 */       this.reset = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateAnsi(List<String> newLines, int targetCursorPos) {
/*  90 */     update((List<AttributedString>)newLines.stream().map(AttributedString::fromAnsi).collect(Collectors.toList()), targetCursorPos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(List<AttributedString> newLines, int targetCursorPos) {
/*  99 */     update(newLines, targetCursorPos, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(List<AttributedString> newLines, int targetCursorPos, boolean flush) {
/* 109 */     if (this.reset) {
/* 110 */       this.terminal.puts(InfoCmp.Capability.clear_screen, new Object[0]);
/* 111 */       this.oldLines.clear();
/* 112 */       this.cursorPos = 0;
/* 113 */       this.reset = false;
/*     */     } 
/*     */ 
/*     */     
/* 117 */     Integer cols = this.terminal.getNumericCapability(InfoCmp.Capability.max_colors);
/* 118 */     if (cols == null || cols.intValue() < 8)
/*     */     {
/* 120 */       newLines = (List<AttributedString>)newLines.stream().map(s -> new AttributedString(s.toString())).collect(Collectors.toList());
/*     */     }
/*     */ 
/*     */     
/* 124 */     if ((this.fullScreen || newLines.size() >= this.rows) && newLines.size() == this.oldLines.size() && this.canScroll) {
/* 125 */       int nbHeaders = 0;
/* 126 */       int nbFooters = 0;
/*     */       
/* 128 */       int l = newLines.size();
/* 129 */       while (nbHeaders < l && 
/* 130 */         Objects.equals(newLines.get(nbHeaders), this.oldLines.get(nbHeaders))) {
/* 131 */         nbHeaders++;
/*     */       }
/* 133 */       while (nbFooters < l - nbHeaders - 1 && 
/* 134 */         Objects.equals(newLines.get(newLines.size() - nbFooters - 1), this.oldLines.get(this.oldLines.size() - nbFooters - 1))) {
/* 135 */         nbFooters++;
/*     */       }
/* 137 */       List<AttributedString> o1 = newLines.subList(nbHeaders, newLines.size() - nbFooters);
/* 138 */       List<AttributedString> o2 = this.oldLines.subList(nbHeaders, this.oldLines.size() - nbFooters);
/* 139 */       int[] common = longestCommon(o1, o2);
/* 140 */       if (common != null) {
/* 141 */         int s1 = common[0];
/* 142 */         int s2 = common[1];
/* 143 */         int sl = common[2];
/* 144 */         if (sl > 1 && s1 < s2) {
/* 145 */           moveVisualCursorTo((nbHeaders + s1) * this.columns1);
/* 146 */           int nb = s2 - s1;
/* 147 */           deleteLines(nb); int i;
/* 148 */           for (i = 0; i < nb; i++) {
/* 149 */             this.oldLines.remove(nbHeaders + s1);
/*     */           }
/* 151 */           if (nbFooters > 0) {
/* 152 */             moveVisualCursorTo((nbHeaders + s1 + sl) * this.columns1);
/* 153 */             insertLines(nb);
/* 154 */             for (i = 0; i < nb; i++) {
/* 155 */               this.oldLines.add(nbHeaders + s1 + sl, new AttributedString(""));
/*     */             }
/*     */           } 
/* 158 */         } else if (sl > 1 && s1 > s2) {
/* 159 */           int nb = s1 - s2;
/* 160 */           if (nbFooters > 0) {
/* 161 */             moveVisualCursorTo((nbHeaders + s2 + sl) * this.columns1);
/* 162 */             deleteLines(nb);
/* 163 */             for (int j = 0; j < nb; j++) {
/* 164 */               this.oldLines.remove(nbHeaders + s2 + sl);
/*     */             }
/*     */           } 
/* 167 */           moveVisualCursorTo((nbHeaders + s2) * this.columns1);
/* 168 */           insertLines(nb);
/* 169 */           for (int i = 0; i < nb; i++) {
/* 170 */             this.oldLines.add(nbHeaders + s2, new AttributedString(""));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 176 */     int lineIndex = 0;
/* 177 */     int currentPos = 0;
/* 178 */     int numLines = Math.max(this.oldLines.size(), newLines.size());
/* 179 */     boolean wrapNeeded = false;
/* 180 */     while (lineIndex < numLines) {
/*     */       
/* 182 */       AttributedString oldLine = (lineIndex < this.oldLines.size()) ? this.oldLines.get(lineIndex) : AttributedString.NEWLINE;
/*     */ 
/*     */       
/* 185 */       AttributedString newLine = (lineIndex < newLines.size()) ? newLines.get(lineIndex) : AttributedString.NEWLINE;
/*     */       
/* 187 */       currentPos = lineIndex * this.columns1;
/* 188 */       int curCol = currentPos;
/* 189 */       int oldLength = oldLine.length();
/* 190 */       int newLength = newLine.length();
/* 191 */       boolean oldNL = (oldLength > 0 && oldLine.charAt(oldLength - 1) == '\n');
/* 192 */       boolean newNL = (newLength > 0 && newLine.charAt(newLength - 1) == '\n');
/* 193 */       if (oldNL) {
/* 194 */         oldLength--;
/* 195 */         oldLine = oldLine.substring(0, oldLength);
/*     */       } 
/* 197 */       if (newNL) {
/* 198 */         newLength--;
/* 199 */         newLine = newLine.substring(0, newLength);
/*     */       } 
/* 201 */       if (wrapNeeded && lineIndex == (this.cursorPos + 1) / this.columns1 && lineIndex < newLines
/*     */         
/* 203 */         .size()) {
/*     */         
/* 205 */         this.cursorPos++;
/* 206 */         if (newLength == 0 || newLine.isHidden(0)) {
/*     */           
/* 208 */           rawPrint(new AttributedString(" \b"));
/*     */         } else {
/*     */           
/* 211 */           AttributedString firstChar = newLine.columnSubSequence(0, 1);
/*     */           
/* 213 */           rawPrint(firstChar);
/* 214 */           this.cursorPos++;
/* 215 */           int firstLength = firstChar.length();
/* 216 */           newLine = newLine.substring(firstLength, newLength);
/* 217 */           newLength -= firstLength;
/* 218 */           if (oldLength >= firstLength) {
/* 219 */             oldLine = oldLine.substring(firstLength, oldLength);
/* 220 */             oldLength -= firstLength;
/*     */           } 
/* 222 */           currentPos = this.cursorPos;
/*     */         } 
/*     */       } 
/* 225 */       List<DiffHelper.Diff> diffs = DiffHelper.diff(oldLine, newLine);
/* 226 */       boolean ident = true;
/* 227 */       boolean cleared = false;
/* 228 */       for (int i = 0; i < diffs.size(); i++) {
/* 229 */         int oldLen, newLen, nb; DiffHelper.Diff diff = diffs.get(i);
/* 230 */         int width = diff.text.columnLength();
/* 231 */         switch (diff.operation) {
/*     */           case EQUAL:
/* 233 */             if (!ident) {
/* 234 */               this.cursorPos = moveVisualCursorTo(currentPos);
/* 235 */               rawPrint(diff.text);
/* 236 */               this.cursorPos += width;
/* 237 */               currentPos = this.cursorPos; break;
/*     */             } 
/* 239 */             currentPos += width;
/*     */             break;
/*     */           
/*     */           case INSERT:
/* 243 */             if (i <= diffs.size() - 2 && ((DiffHelper.Diff)diffs
/* 244 */               .get(i + 1)).operation == DiffHelper.Operation.EQUAL) {
/* 245 */               this.cursorPos = moveVisualCursorTo(currentPos);
/* 246 */               if (insertChars(width)) {
/* 247 */                 rawPrint(diff.text);
/* 248 */                 this.cursorPos += width;
/* 249 */                 currentPos = this.cursorPos;
/*     */                 break;
/*     */               } 
/* 252 */             } else if (i <= diffs.size() - 2 && ((DiffHelper.Diff)diffs
/* 253 */               .get(i + 1)).operation == DiffHelper.Operation.DELETE && width == ((DiffHelper.Diff)diffs
/* 254 */               .get(i + 1)).text.columnLength()) {
/* 255 */               moveVisualCursorTo(currentPos);
/* 256 */               rawPrint(diff.text);
/* 257 */               this.cursorPos += width;
/* 258 */               currentPos = this.cursorPos;
/* 259 */               i++;
/*     */               break;
/*     */             } 
/* 262 */             moveVisualCursorTo(currentPos);
/* 263 */             rawPrint(diff.text);
/* 264 */             this.cursorPos += width;
/* 265 */             currentPos = this.cursorPos;
/* 266 */             ident = false;
/*     */             break;
/*     */           case DELETE:
/* 269 */             if (cleared) {
/*     */               break;
/*     */             }
/* 272 */             if (currentPos - curCol >= this.columns) {
/*     */               break;
/*     */             }
/* 275 */             if (i <= diffs.size() - 2 && ((DiffHelper.Diff)diffs
/* 276 */               .get(i + 1)).operation == DiffHelper.Operation.EQUAL && 
/* 277 */               currentPos + ((DiffHelper.Diff)diffs.get(i + 1)).text.columnLength() < this.columns) {
/* 278 */               moveVisualCursorTo(currentPos);
/* 279 */               if (deleteChars(width)) {
/*     */                 break;
/*     */               }
/*     */             } 
/*     */             
/* 284 */             oldLen = oldLine.columnLength();
/* 285 */             newLen = newLine.columnLength();
/* 286 */             nb = Math.max(oldLen, newLen) - currentPos - curCol;
/* 287 */             moveVisualCursorTo(currentPos);
/* 288 */             if (!this.terminal.puts(InfoCmp.Capability.clr_eol, new Object[0])) {
/* 289 */               rawPrint(' ', nb);
/* 290 */               this.cursorPos += nb;
/*     */             } 
/* 292 */             cleared = true;
/* 293 */             ident = false;
/*     */             break;
/*     */         } 
/*     */       } 
/* 297 */       lineIndex++;
/* 298 */       boolean newWrap = (!newNL && lineIndex < newLines.size());
/* 299 */       if (targetCursorPos + 1 == lineIndex * this.columns1 && (newWrap || !this.delayLineWrap))
/*     */       {
/* 301 */         targetCursorPos++; } 
/* 302 */       boolean atRight = ((this.cursorPos - curCol) % this.columns1 == this.columns);
/* 303 */       wrapNeeded = false;
/* 304 */       if (this.delayedWrapAtEol) {
/* 305 */         boolean oldWrap = (!oldNL && lineIndex < this.oldLines.size());
/* 306 */         if (newWrap != oldWrap && (!oldWrap || !cleared)) {
/* 307 */           moveVisualCursorTo(lineIndex * this.columns1 - 1, newLines);
/* 308 */           if (newWrap) {
/* 309 */             wrapNeeded = true; continue;
/*     */           } 
/* 311 */           this.terminal.puts(InfoCmp.Capability.clr_eol, new Object[0]);
/*     */         }  continue;
/* 313 */       }  if (atRight) {
/* 314 */         if (this.wrapAtEol) {
/* 315 */           this.terminal.writer().write(" \b");
/* 316 */           this.cursorPos++;
/*     */         } else {
/* 318 */           this.terminal.puts(InfoCmp.Capability.carriage_return, new Object[0]);
/* 319 */           this.cursorPos = curCol;
/*     */         } 
/* 321 */         currentPos = this.cursorPos;
/*     */       } 
/*     */     } 
/* 324 */     int was = this.cursorPos;
/* 325 */     if (this.cursorPos != targetCursorPos) {
/* 326 */       moveVisualCursorTo((targetCursorPos < 0) ? currentPos : targetCursorPos, newLines);
/*     */     }
/* 328 */     this.oldLines = newLines;
/*     */     
/* 330 */     if (flush) {
/* 331 */       this.terminal.flush();
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean deleteLines(int nb) {
/* 336 */     return perform(InfoCmp.Capability.delete_line, InfoCmp.Capability.parm_delete_line, nb);
/*     */   }
/*     */   
/*     */   protected boolean insertLines(int nb) {
/* 340 */     return perform(InfoCmp.Capability.insert_line, InfoCmp.Capability.parm_insert_line, nb);
/*     */   }
/*     */   
/*     */   protected boolean insertChars(int nb) {
/* 344 */     return perform(InfoCmp.Capability.insert_character, InfoCmp.Capability.parm_ich, nb);
/*     */   }
/*     */   
/*     */   protected boolean deleteChars(int nb) {
/* 348 */     return perform(InfoCmp.Capability.delete_character, InfoCmp.Capability.parm_dch, nb);
/*     */   }
/*     */   
/*     */   protected boolean can(InfoCmp.Capability single, InfoCmp.Capability multi) {
/* 352 */     return (this.terminal.getStringCapability(single) != null || this.terminal
/* 353 */       .getStringCapability(multi) != null);
/*     */   }
/*     */   
/*     */   protected boolean perform(InfoCmp.Capability single, InfoCmp.Capability multi, int nb) {
/* 357 */     boolean hasMulti = (this.terminal.getStringCapability(multi) != null);
/* 358 */     boolean hasSingle = (this.terminal.getStringCapability(single) != null);
/* 359 */     if (hasMulti && (!hasSingle || cost(single) * nb > cost(multi))) {
/* 360 */       this.terminal.puts(multi, new Object[] { Integer.valueOf(nb) });
/* 361 */       return true;
/* 362 */     }  if (hasSingle) {
/* 363 */       for (int i = 0; i < nb; i++) {
/* 364 */         this.terminal.puts(single, new Object[0]);
/*     */       }
/* 366 */       return true;
/*     */     } 
/* 368 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private int cost(InfoCmp.Capability cap) {
/* 373 */     return ((Integer)this.cost.computeIfAbsent(cap, this::computeCost)).intValue();
/*     */   }
/*     */   
/*     */   private int computeCost(InfoCmp.Capability cap) {
/* 377 */     String s = Curses.tputs(this.terminal.getStringCapability(cap), new Object[] { Integer.valueOf(0) });
/* 378 */     return (s != null) ? s.length() : Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private static int[] longestCommon(List<AttributedString> l1, List<AttributedString> l2) {
/* 382 */     int start1 = 0;
/* 383 */     int start2 = 0;
/* 384 */     int max = 0;
/* 385 */     for (int i = 0; i < l1.size(); i++) {
/* 386 */       for (int j = 0; j < l2.size(); j++) {
/* 387 */         int x = 0;
/* 388 */         while (Objects.equals(l1.get(i + x), l2.get(j + x))) {
/* 389 */           x++;
/* 390 */           if (i + x >= l1.size() || j + x >= l2.size())
/*     */             break; 
/* 392 */         }  if (x > max) {
/* 393 */           max = x;
/* 394 */           start1 = i;
/* 395 */           start2 = j;
/*     */         } 
/*     */       } 
/*     */     } 
/* 399 */     (new int[3])[0] = start1; (new int[3])[1] = start2; (new int[3])[2] = max; return (max != 0) ? new int[3] : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void moveVisualCursorTo(int targetPos, List<AttributedString> newLines) {
/* 409 */     if (this.cursorPos != targetPos) {
/* 410 */       boolean atRight = (targetPos % this.columns1 == this.columns);
/* 411 */       moveVisualCursorTo(targetPos - (atRight ? 1 : 0));
/* 412 */       if (atRight) {
/*     */ 
/*     */         
/* 415 */         int row = targetPos / this.columns1;
/*     */         
/* 417 */         AttributedString lastChar = (row >= newLines.size()) ? AttributedString.EMPTY : ((AttributedString)newLines.get(row)).columnSubSequence(this.columns - 1, this.columns);
/* 418 */         if (lastChar.length() == 0) {
/* 419 */           rawPrint(32);
/*     */         } else {
/* 421 */           rawPrint(lastChar);
/* 422 */         }  this.cursorPos++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int moveVisualCursorTo(int i1) {
/* 435 */     int i0 = this.cursorPos;
/* 436 */     if (i0 == i1) return i1; 
/* 437 */     int width = this.columns1;
/* 438 */     int l0 = i0 / width;
/* 439 */     int c0 = i0 % width;
/* 440 */     int l1 = i1 / width;
/* 441 */     int c1 = i1 % width;
/* 442 */     if (c0 == this.columns) {
/* 443 */       this.terminal.puts(InfoCmp.Capability.carriage_return, new Object[0]);
/* 444 */       c0 = 0;
/*     */     } 
/* 446 */     if (l0 > l1) {
/* 447 */       perform(InfoCmp.Capability.cursor_up, InfoCmp.Capability.parm_up_cursor, l0 - l1);
/* 448 */     } else if (l0 < l1) {
/*     */       
/* 450 */       if (this.fullScreen) {
/* 451 */         if (!this.terminal.puts(InfoCmp.Capability.parm_down_cursor, new Object[] { Integer.valueOf(l1 - l0) })) {
/* 452 */           for (int i = l0; i < l1; i++) {
/* 453 */             this.terminal.puts(InfoCmp.Capability.cursor_down, new Object[0]);
/*     */           }
/* 455 */           if (this.cursorDownIsNewLine) {
/* 456 */             c0 = 0;
/*     */           }
/*     */         } 
/*     */       } else {
/* 460 */         this.terminal.puts(InfoCmp.Capability.carriage_return, new Object[0]);
/* 461 */         rawPrint('\n', l1 - l0);
/* 462 */         c0 = 0;
/*     */       } 
/*     */     } 
/* 465 */     if (c0 != 0 && c1 == 0) {
/* 466 */       this.terminal.puts(InfoCmp.Capability.carriage_return, new Object[0]);
/* 467 */     } else if (c0 < c1) {
/* 468 */       perform(InfoCmp.Capability.cursor_right, InfoCmp.Capability.parm_right_cursor, c1 - c0);
/* 469 */     } else if (c0 > c1) {
/* 470 */       perform(InfoCmp.Capability.cursor_left, InfoCmp.Capability.parm_left_cursor, c0 - c1);
/*     */     } 
/* 472 */     this.cursorPos = i1;
/* 473 */     return i1;
/*     */   }
/*     */   
/*     */   void rawPrint(char c, int num) {
/* 477 */     for (int i = 0; i < num; i++) {
/* 478 */       rawPrint(c);
/*     */     }
/*     */   }
/*     */   
/*     */   void rawPrint(int c) {
/* 483 */     this.terminal.writer().write(c);
/*     */   }
/*     */   
/*     */   void rawPrint(AttributedString str) {
/* 487 */     str.print(this.terminal);
/*     */   }
/*     */   
/*     */   public int wcwidth(String str) {
/* 491 */     return AttributedString.fromAnsi(str).columnLength();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\Display.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */