/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public abstract class AttributedCharSequence
/*     */   implements CharSequence
/*     */ {
/*  39 */   static final boolean DISABLE_ALTERNATE_CHARSET = Boolean.getBoolean("org.jline.utils.disableAlternateCharset");
/*     */   
/*     */   public void print(Terminal terminal) {
/*  42 */     terminal.writer().print(toAnsi(terminal));
/*     */   }
/*     */   
/*     */   public void println(Terminal terminal) {
/*  46 */     terminal.writer().println(toAnsi(terminal));
/*     */   }
/*     */   
/*     */   public String toAnsi() {
/*  50 */     return toAnsi(null);
/*     */   }
/*     */   
/*     */   public String toAnsi(Terminal terminal) {
/*  54 */     if (terminal != null && "dumb".equals(terminal.getType())) {
/*  55 */       return toString();
/*     */     }
/*  57 */     int colors = 256;
/*  58 */     boolean force256colors = false;
/*  59 */     String alternateIn = null, alternateOut = null;
/*  60 */     if (terminal != null) {
/*  61 */       Integer max_colors = terminal.getNumericCapability(InfoCmp.Capability.max_colors);
/*  62 */       if (max_colors != null) {
/*  63 */         colors = max_colors.intValue();
/*     */       }
/*     */       
/*  66 */       force256colors = ("windows-256color".equals(terminal.getType()) || "windows-conemu".equals(terminal.getType()));
/*  67 */       if (!DISABLE_ALTERNATE_CHARSET) {
/*  68 */         alternateIn = Curses.tputs(terminal.getStringCapability(InfoCmp.Capability.enter_alt_charset_mode), new Object[0]);
/*  69 */         alternateOut = Curses.tputs(terminal.getStringCapability(InfoCmp.Capability.exit_alt_charset_mode), new Object[0]);
/*     */       } 
/*     */     } 
/*  72 */     return toAnsi(colors, force256colors, alternateIn, alternateOut);
/*     */   }
/*     */   
/*     */   public String toAnsi(int colors, boolean force256colors) {
/*  76 */     return toAnsi(colors, force256colors, null, null);
/*     */   }
/*     */   
/*     */   public String toAnsi(int colors, boolean force256colors, String altIn, String altOut) {
/*  80 */     StringBuilder sb = new StringBuilder();
/*  81 */     int style = 0;
/*  82 */     int foreground = -1;
/*  83 */     int background = -1;
/*  84 */     boolean alt = false;
/*  85 */     for (int i = 0; i < length(); i++) {
/*  86 */       char c = charAt(i);
/*  87 */       if (altIn != null && altOut != null) {
/*  88 */         char pc = c;
/*  89 */         switch (c) { case '┘':
/*  90 */             c = 'j'; break;
/*  91 */           case '┐': c = 'k'; break;
/*  92 */           case '┌': c = 'l'; break;
/*  93 */           case '└': c = 'm'; break;
/*  94 */           case '┼': c = 'n'; break;
/*  95 */           case '─': c = 'q'; break;
/*  96 */           case '├': c = 't'; break;
/*  97 */           case '┤': c = 'u'; break;
/*  98 */           case '┴': c = 'v'; break;
/*  99 */           case '┬': c = 'w'; break;
/* 100 */           case '│': c = 'x'; break; }
/*     */         
/* 102 */         boolean oldalt = alt;
/* 103 */         alt = (c != pc);
/* 104 */         if (oldalt ^ alt) {
/* 105 */           sb.append(alt ? altIn : altOut);
/*     */         }
/*     */       } 
/* 108 */       int s = styleCodeAt(i) & 0xFFFFFBFF;
/* 109 */       if (style != s) {
/* 110 */         int d = (style ^ s) & 0x7FF;
/* 111 */         int fg = ((s & 0x100) != 0) ? ((s & 0xFF0000) >>> 16) : -1;
/* 112 */         int bg = ((s & 0x200) != 0) ? ((s & 0xFF000000) >>> 24) : -1;
/* 113 */         if (s == 0) {
/* 114 */           sb.append("\033[0m");
/* 115 */           foreground = background = -1;
/*     */         } else {
/* 117 */           sb.append("\033[");
/* 118 */           boolean first = true;
/* 119 */           if ((d & 0x4) != 0) {
/* 120 */             first = attr(sb, ((s & 0x4) != 0) ? "3" : "23", first);
/*     */           }
/* 122 */           if ((d & 0x8) != 0) {
/* 123 */             first = attr(sb, ((s & 0x8) != 0) ? "4" : "24", first);
/*     */           }
/* 125 */           if ((d & 0x10) != 0) {
/* 126 */             first = attr(sb, ((s & 0x10) != 0) ? "5" : "25", first);
/*     */           }
/* 128 */           if ((d & 0x20) != 0) {
/* 129 */             first = attr(sb, ((s & 0x20) != 0) ? "7" : "27", first);
/*     */           }
/* 131 */           if ((d & 0x40) != 0) {
/* 132 */             first = attr(sb, ((s & 0x40) != 0) ? "8" : "28", first);
/*     */           }
/* 134 */           if ((d & 0x80) != 0) {
/* 135 */             first = attr(sb, ((s & 0x80) != 0) ? "9" : "29", first);
/*     */           }
/* 137 */           if (foreground != fg) {
/* 138 */             if (fg >= 0) {
/* 139 */               int rounded = Colors.roundColor(fg, colors);
/* 140 */               if (rounded < 8 && !force256colors) {
/* 141 */                 first = attr(sb, "3" + Integer.toString(rounded), first);
/*     */                 
/* 143 */                 d |= s & 0x1;
/* 144 */               } else if (rounded < 16 && !force256colors) {
/* 145 */                 first = attr(sb, "9" + Integer.toString(rounded - 8), first);
/*     */                 
/* 147 */                 d |= s & 0x1;
/*     */               } else {
/* 149 */                 first = attr(sb, "38;5;" + Integer.toString(rounded), first);
/*     */               } 
/*     */             } else {
/* 152 */               first = attr(sb, "39", first);
/*     */             } 
/* 154 */             foreground = fg;
/*     */           } 
/* 156 */           if (background != bg) {
/* 157 */             if (bg >= 0) {
/* 158 */               int rounded = Colors.roundColor(bg, colors);
/* 159 */               if (rounded < 8 && !force256colors) {
/* 160 */                 first = attr(sb, "4" + Integer.toString(rounded), first);
/* 161 */               } else if (rounded < 16 && !force256colors) {
/* 162 */                 first = attr(sb, "10" + Integer.toString(rounded - 8), first);
/*     */               } else {
/* 164 */                 first = attr(sb, "48;5;" + Integer.toString(rounded), first);
/*     */               } 
/*     */             } else {
/* 167 */               first = attr(sb, "49", first);
/*     */             } 
/* 169 */             background = bg;
/*     */           } 
/* 171 */           if ((d & 0x3) != 0) {
/* 172 */             if (((d & 0x1) != 0 && (s & 0x1) == 0) || ((d & 0x2) != 0 && (s & 0x2) == 0))
/*     */             {
/* 174 */               first = attr(sb, "22", first);
/*     */             }
/* 176 */             if ((d & 0x1) != 0 && (s & 0x1) != 0) {
/* 177 */               first = attr(sb, "1", first);
/*     */             }
/* 179 */             if ((d & 0x2) != 0 && (s & 0x2) != 0) {
/* 180 */               first = attr(sb, "2", first);
/*     */             }
/*     */           } 
/* 183 */           sb.append("m");
/*     */         } 
/* 185 */         style = s;
/*     */       } 
/* 187 */       sb.append(c);
/*     */     } 
/* 189 */     if (alt) {
/* 190 */       sb.append(altOut);
/*     */     }
/* 192 */     if (style != 0) {
/* 193 */       sb.append("\033[0m");
/*     */     }
/* 195 */     return sb.toString();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int rgbColor(int col) {
/* 200 */     return Colors.rgbColor(col);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int roundColor(int col, int max) {
/* 205 */     return Colors.roundColor(col, max);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int roundRgbColor(int r, int g, int b, int max) {
/* 210 */     return Colors.roundRgbColor(r, g, b, max);
/*     */   }
/*     */   
/*     */   private static boolean attr(StringBuilder sb, String s, boolean first) {
/* 214 */     if (!first) {
/* 215 */       sb.append(";");
/*     */     }
/* 217 */     sb.append(s);
/* 218 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int styleCodeAt(int index) {
/* 224 */     return styleAt(index).getStyle();
/*     */   }
/*     */   
/*     */   public boolean isHidden(int index) {
/* 228 */     return ((styleCodeAt(index) & 0x400) != 0);
/*     */   }
/*     */   
/*     */   public int runStart(int index) {
/* 232 */     AttributedStyle style = styleAt(index);
/* 233 */     while (index > 0 && styleAt(index - 1).equals(style)) {
/* 234 */       index--;
/*     */     }
/* 236 */     return index;
/*     */   }
/*     */   
/*     */   public int runLimit(int index) {
/* 240 */     AttributedStyle style = styleAt(index);
/* 241 */     while (index < length() - 1 && styleAt(index + 1).equals(style)) {
/* 242 */       index++;
/*     */     }
/* 244 */     return index + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributedString substring(int start, int end) {
/* 251 */     return subSequence(start, end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char charAt(int index) {
/* 260 */     return buffer()[offset() + index];
/*     */   }
/*     */   
/*     */   public int codePointAt(int index) {
/* 264 */     return Character.codePointAt(buffer(), index + offset());
/*     */   }
/*     */   
/*     */   public boolean contains(char c) {
/* 268 */     for (int i = 0; i < length(); i++) {
/* 269 */       if (charAt(i) == c) {
/* 270 */         return true;
/*     */       }
/*     */     } 
/* 273 */     return false;
/*     */   }
/*     */   
/*     */   public int codePointBefore(int index) {
/* 277 */     return Character.codePointBefore(buffer(), index + offset());
/*     */   }
/*     */   
/*     */   public int codePointCount(int index, int length) {
/* 281 */     return Character.codePointCount(buffer(), index + offset(), length);
/*     */   }
/*     */   
/*     */   public int columnLength() {
/* 285 */     int cols = 0;
/* 286 */     int len = length();
/* 287 */     for (int cur = 0; cur < len; ) {
/* 288 */       int cp = codePointAt(cur);
/* 289 */       if (!isHidden(cur))
/* 290 */         cols += WCWidth.wcwidth(cp); 
/* 291 */       cur += Character.charCount(cp);
/*     */     } 
/* 293 */     return cols;
/*     */   }
/*     */   
/*     */   public AttributedString columnSubSequence(int start, int stop) {
/* 297 */     int begin = 0;
/* 298 */     int col = 0;
/* 299 */     while (begin < length()) {
/* 300 */       int cp = codePointAt(begin);
/* 301 */       int w = isHidden(begin) ? 0 : WCWidth.wcwidth(cp);
/* 302 */       if (col + w > start) {
/*     */         break;
/*     */       }
/* 305 */       begin += Character.charCount(cp);
/* 306 */       col += w;
/*     */     } 
/* 308 */     int end = begin;
/* 309 */     while (end < length()) {
/* 310 */       int cp = codePointAt(end);
/* 311 */       if (cp == 10)
/*     */         break; 
/* 313 */       int w = isHidden(end) ? 0 : WCWidth.wcwidth(cp);
/* 314 */       if (col + w > stop) {
/*     */         break;
/*     */       }
/* 317 */       end += Character.charCount(cp);
/* 318 */       col += w;
/*     */     } 
/* 320 */     return subSequence(begin, end);
/*     */   }
/*     */   
/*     */   public List<AttributedString> columnSplitLength(int columns) {
/* 324 */     return columnSplitLength(columns, false, true);
/*     */   }
/*     */   
/*     */   public List<AttributedString> columnSplitLength(int columns, boolean includeNewlines, boolean delayLineWrap) {
/* 328 */     List<AttributedString> strings = new ArrayList<>();
/* 329 */     int cur = 0;
/* 330 */     int beg = cur;
/* 331 */     int col = 0;
/* 332 */     while (cur < length()) {
/* 333 */       int cp = codePointAt(cur);
/* 334 */       int w = isHidden(cur) ? 0 : WCWidth.wcwidth(cp);
/* 335 */       if (cp == 10) {
/* 336 */         strings.add(subSequence(beg, includeNewlines ? (cur + 1) : cur));
/* 337 */         beg = cur + 1;
/* 338 */         col = 0;
/* 339 */       } else if ((col += w) > columns) {
/* 340 */         strings.add(subSequence(beg, cur));
/* 341 */         beg = cur;
/* 342 */         col = w;
/*     */       } 
/* 344 */       cur += Character.charCount(cp);
/*     */     } 
/* 346 */     strings.add(subSequence(beg, cur));
/* 347 */     return strings;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 352 */     return new String(buffer(), offset(), length());
/*     */   }
/*     */   
/*     */   public AttributedString toAttributedString() {
/* 356 */     return substring(0, length());
/*     */   }
/*     */   
/*     */   public abstract AttributedStyle styleAt(int paramInt);
/*     */   
/*     */   public abstract AttributedString subSequence(int paramInt1, int paramInt2);
/*     */   
/*     */   protected abstract char[] buffer();
/*     */   
/*     */   protected abstract int offset();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\AttributedCharSequence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */