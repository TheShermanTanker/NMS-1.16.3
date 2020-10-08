/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.FilterWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
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
/*     */ public class AnsiWriter
/*     */   extends FilterWriter
/*     */ {
/*     */   private static final int MAX_ESCAPE_SEQUENCE_LENGTH = 100;
/*     */   private final char[] buffer;
/*     */   private int pos;
/*     */   private int startOfValue;
/*     */   private final ArrayList<Object> options;
/*     */   private static final int LOOKING_FOR_FIRST_ESC_CHAR = 0;
/*     */   private static final int LOOKING_FOR_SECOND_ESC_CHAR = 1;
/*     */   private static final int LOOKING_FOR_NEXT_ARG = 2;
/*     */   private static final int LOOKING_FOR_STR_ARG_END = 3;
/*     */   private static final int LOOKING_FOR_INT_ARG_END = 4;
/*     */   private static final int LOOKING_FOR_OSC_COMMAND = 5;
/*     */   private static final int LOOKING_FOR_OSC_COMMAND_END = 6;
/*     */   private static final int LOOKING_FOR_OSC_PARAM = 7;
/*     */   private static final int LOOKING_FOR_ST = 8;
/*     */   private static final int LOOKING_FOR_CHARSET = 9;
/*     */   int state;
/*     */   private static final int FIRST_ESC_CHAR = 27;
/*     */   private static final int SECOND_ESC_CHAR = 91;
/*     */   private static final int SECOND_OSC_CHAR = 93;
/*     */   private static final int BEL = 7;
/*  42 */   private static final char[] RESET_CODE = "\033[0m".toCharArray(); private static final int SECOND_ST_CHAR = 92; private static final int SECOND_CHARSET0_CHAR = 40; private static final int SECOND_CHARSET1_CHAR = 41; protected static final int ERASE_SCREEN_TO_END = 0; protected static final int ERASE_SCREEN_TO_BEGINING = 1; protected static final int ERASE_SCREEN = 2;
/*     */   
/*     */   public AnsiWriter(Writer out) {
/*  45 */     super(out);
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.buffer = new char[100];
/*  50 */     this.pos = 0;
/*     */     
/*  52 */     this.options = new ArrayList();
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
/*  65 */     this.state = 0;
/*     */   }
/*     */   
/*     */   protected static final int ERASE_LINE_TO_END = 0;
/*     */   protected static final int ERASE_LINE_TO_BEGINING = 1;
/*     */   protected static final int ERASE_LINE = 2;
/*     */   protected static final int ATTRIBUTE_INTENSITY_BOLD = 1;
/*     */   protected static final int ATTRIBUTE_INTENSITY_FAINT = 2;
/*     */   protected static final int ATTRIBUTE_ITALIC = 3;
/*     */   
/*     */   public synchronized void write(int data) throws IOException {
/*     */     boolean skip;
/*  77 */     switch (this.state) {
/*     */       case 0:
/*  79 */         if (data == 27) {
/*  80 */           this.buffer[this.pos++] = (char)data;
/*  81 */           this.state = 1; break;
/*     */         } 
/*  83 */         this.out.write(data);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/*  88 */         this.buffer[this.pos++] = (char)data;
/*  89 */         if (data == 91) {
/*  90 */           this.state = 2; break;
/*  91 */         }  if (data == 93) {
/*  92 */           this.state = 5; break;
/*  93 */         }  if (data == 40) {
/*  94 */           this.options.add(Integer.valueOf(48));
/*  95 */           this.state = 9; break;
/*  96 */         }  if (data == 41) {
/*  97 */           this.options.add(Integer.valueOf(49));
/*  98 */           this.state = 9; break;
/*     */         } 
/* 100 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 105 */         this.buffer[this.pos++] = (char)data;
/* 106 */         if (34 == data) {
/* 107 */           this.startOfValue = this.pos - 1;
/* 108 */           this.state = 3; break;
/* 109 */         }  if (48 <= data && data <= 57) {
/* 110 */           this.startOfValue = this.pos - 1;
/* 111 */           this.state = 4; break;
/* 112 */         }  if (59 == data) {
/* 113 */           this.options.add(null); break;
/* 114 */         }  if (63 == data) {
/* 115 */           this.options.add(Character.valueOf('?')); break;
/* 116 */         }  if (61 == data) {
/* 117 */           this.options.add(Character.valueOf('=')); break;
/*     */         } 
/* 119 */         skip = true;
/*     */         try {
/* 121 */           skip = processEscapeCommand(this.options, data);
/*     */         } finally {
/* 123 */           reset(skip);
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 131 */         this.buffer[this.pos++] = (char)data;
/* 132 */         if (48 > data || data > 57) {
/* 133 */           String strValue = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue);
/* 134 */           Integer value = Integer.valueOf(strValue);
/* 135 */           this.options.add(value);
/* 136 */           if (data == 59) {
/* 137 */             this.state = 2; break;
/*     */           } 
/* 139 */           boolean bool = true;
/*     */           try {
/* 141 */             bool = processEscapeCommand(this.options, data);
/*     */           } finally {
/* 143 */             reset(bool);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 150 */         this.buffer[this.pos++] = (char)data;
/* 151 */         if (34 != data) {
/* 152 */           String value = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue);
/* 153 */           this.options.add(value);
/* 154 */           if (data == 59) {
/* 155 */             this.state = 2; break;
/*     */           } 
/* 157 */           reset(processEscapeCommand(this.options, data));
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 163 */         this.buffer[this.pos++] = (char)data;
/* 164 */         if (48 <= data && data <= 57) {
/* 165 */           this.startOfValue = this.pos - 1;
/* 166 */           this.state = 6; break;
/*     */         } 
/* 168 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 173 */         this.buffer[this.pos++] = (char)data;
/* 174 */         if (59 == data) {
/* 175 */           String strValue = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue);
/* 176 */           Integer value = Integer.valueOf(strValue);
/* 177 */           this.options.add(value);
/* 178 */           this.startOfValue = this.pos;
/* 179 */           this.state = 7; break;
/* 180 */         }  if (48 <= data && data <= 57) {
/*     */           break;
/*     */         }
/*     */         
/* 184 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 7:
/* 189 */         this.buffer[this.pos++] = (char)data;
/* 190 */         if (7 == data) {
/* 191 */           String value = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue);
/* 192 */           this.options.add(value);
/* 193 */           boolean bool = true;
/*     */           try {
/* 195 */             bool = processOperatingSystemCommand(this.options);
/*     */           } finally {
/* 197 */             reset(bool);
/*     */           }  break;
/* 199 */         }  if (27 == data) {
/* 200 */           this.state = 8;
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 8:
/* 207 */         this.buffer[this.pos++] = (char)data;
/* 208 */         if (92 == data) {
/* 209 */           String value = new String(this.buffer, this.startOfValue, this.pos - 2 - this.startOfValue);
/* 210 */           this.options.add(value);
/* 211 */           boolean bool = true;
/*     */           try {
/* 213 */             bool = processOperatingSystemCommand(this.options);
/*     */           } finally {
/* 215 */             reset(bool);
/*     */           }  break;
/*     */         } 
/* 218 */         this.state = 7;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 9:
/* 223 */         this.options.add(Character.valueOf((char)data));
/* 224 */         reset(processCharsetSelect(this.options));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 229 */     if (this.pos >= this.buffer.length)
/* 230 */       reset(false); 
/*     */   }
/*     */   protected static final int ATTRIBUTE_UNDERLINE = 4; protected static final int ATTRIBUTE_BLINK_SLOW = 5; protected static final int ATTRIBUTE_BLINK_FAST = 6; protected static final int ATTRIBUTE_NEGATIVE_ON = 7; protected static final int ATTRIBUTE_CONCEAL_ON = 8; protected static final int ATTRIBUTE_UNDERLINE_DOUBLE = 21; protected static final int ATTRIBUTE_INTENSITY_NORMAL = 22; protected static final int ATTRIBUTE_UNDERLINE_OFF = 24; protected static final int ATTRIBUTE_BLINK_OFF = 25;
/*     */   @Deprecated
/*     */   protected static final int ATTRIBUTE_NEGATIVE_Off = 27;
/*     */   protected static final int ATTRIBUTE_NEGATIVE_OFF = 27;
/*     */   protected static final int ATTRIBUTE_CONCEAL_OFF = 28;
/*     */   protected static final int BLACK = 0;
/*     */   
/*     */   private void reset(boolean skipBuffer) throws IOException {
/* 240 */     if (!skipBuffer) {
/* 241 */       this.out.write(this.buffer, 0, this.pos);
/*     */     }
/* 243 */     this.pos = 0;
/* 244 */     this.startOfValue = 0;
/* 245 */     this.options.clear();
/* 246 */     this.state = 0;
/*     */   }
/*     */   protected static final int RED = 1; protected static final int GREEN = 2; protected static final int YELLOW = 3;
/*     */   protected static final int BLUE = 4;
/*     */   protected static final int MAGENTA = 5;
/*     */   protected static final int CYAN = 6;
/*     */   protected static final int WHITE = 7;
/*     */   
/*     */   private int getNextOptionInt(Iterator<Object> optionsIterator) throws IOException {
/*     */     while (true) {
/* 256 */       if (!optionsIterator.hasNext())
/* 257 */         throw new IllegalArgumentException(); 
/* 258 */       Object arg = optionsIterator.next();
/* 259 */       if (arg != null) {
/* 260 */         return ((Integer)arg).intValue();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean processEscapeCommand(ArrayList<Object> options, int command) throws IOException {
/*     */     try {
/*     */       int count;
/*     */       Iterator<Object> optionsIterator;
/* 273 */       switch (command) {
/*     */         case 65:
/* 275 */           processCursorUp(optionInt(options, 0, 1));
/* 276 */           return true;
/*     */         case 66:
/* 278 */           processCursorDown(optionInt(options, 0, 1));
/* 279 */           return true;
/*     */         case 67:
/* 281 */           processCursorRight(optionInt(options, 0, 1));
/* 282 */           return true;
/*     */         case 68:
/* 284 */           processCursorLeft(optionInt(options, 0, 1));
/* 285 */           return true;
/*     */         case 69:
/* 287 */           processCursorDownLine(optionInt(options, 0, 1));
/* 288 */           return true;
/*     */         case 70:
/* 290 */           processCursorUpLine(optionInt(options, 0, 1));
/* 291 */           return true;
/*     */         case 71:
/* 293 */           processCursorToColumn(optionInt(options, 0));
/* 294 */           return true;
/*     */         case 72:
/*     */         case 102:
/* 297 */           processCursorTo(optionInt(options, 0, 1), optionInt(options, 1, 1));
/* 298 */           return true;
/*     */         case 74:
/* 300 */           processEraseScreen(optionInt(options, 0, 0));
/* 301 */           return true;
/*     */         case 75:
/* 303 */           processEraseLine(optionInt(options, 0, 0));
/* 304 */           return true;
/*     */         case 76:
/* 306 */           processInsertLine(optionInt(options, 0, 1));
/* 307 */           return true;
/*     */         case 77:
/* 309 */           processDeleteLine(optionInt(options, 0, 1));
/* 310 */           return true;
/*     */         case 83:
/* 312 */           processScrollUp(optionInt(options, 0, 1));
/* 313 */           return true;
/*     */         case 84:
/* 315 */           processScrollDown(optionInt(options, 0, 1));
/* 316 */           return true;
/*     */         
/*     */         case 109:
/* 319 */           for (Object next : options) {
/* 320 */             if (next != null && next.getClass() != Integer.class) {
/* 321 */               throw new IllegalArgumentException();
/*     */             }
/*     */           } 
/*     */           
/* 325 */           count = 0;
/* 326 */           optionsIterator = options.iterator();
/* 327 */           while (optionsIterator.hasNext()) {
/* 328 */             Object next = optionsIterator.next();
/* 329 */             if (next != null) {
/* 330 */               count++;
/* 331 */               int value = ((Integer)next).intValue();
/* 332 */               if (30 <= value && value <= 37) {
/* 333 */                 processSetForegroundColor(value - 30); continue;
/* 334 */               }  if (40 <= value && value <= 47) {
/* 335 */                 processSetBackgroundColor(value - 40); continue;
/* 336 */               }  if (90 <= value && value <= 97) {
/* 337 */                 processSetForegroundColor(value - 90, true); continue;
/* 338 */               }  if (100 <= value && value <= 107) {
/* 339 */                 processSetBackgroundColor(value - 100, true); continue;
/* 340 */               }  if (value == 38 || value == 48) {
/*     */                 
/* 342 */                 int arg2or5 = getNextOptionInt(optionsIterator);
/* 343 */                 if (arg2or5 == 2) {
/*     */                   
/* 345 */                   int r = getNextOptionInt(optionsIterator);
/* 346 */                   int g = getNextOptionInt(optionsIterator);
/* 347 */                   int b = getNextOptionInt(optionsIterator);
/* 348 */                   if (r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255) {
/* 349 */                     if (value == 38) {
/* 350 */                       processSetForegroundColorExt(r, g, b); continue;
/*     */                     } 
/* 352 */                     processSetBackgroundColorExt(r, g, b); continue;
/*     */                   } 
/* 354 */                   throw new IllegalArgumentException();
/*     */                 } 
/*     */                 
/* 357 */                 if (arg2or5 == 5) {
/*     */                   
/* 359 */                   int paletteIndex = getNextOptionInt(optionsIterator);
/* 360 */                   if (paletteIndex >= 0 && paletteIndex <= 255) {
/* 361 */                     if (value == 38) {
/* 362 */                       processSetForegroundColorExt(paletteIndex); continue;
/*     */                     } 
/* 364 */                     processSetBackgroundColorExt(paletteIndex); continue;
/*     */                   } 
/* 366 */                   throw new IllegalArgumentException();
/*     */                 } 
/*     */ 
/*     */                 
/* 370 */                 throw new IllegalArgumentException();
/*     */               } 
/*     */               
/* 373 */               switch (value) {
/*     */                 case 39:
/* 375 */                   processDefaultTextColor();
/*     */                   continue;
/*     */                 case 49:
/* 378 */                   processDefaultBackgroundColor();
/*     */                   continue;
/*     */                 case 0:
/* 381 */                   processAttributeRest();
/*     */                   continue;
/*     */               } 
/* 384 */               processSetAttribute(value);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 389 */           if (count == 0) {
/* 390 */             processAttributeRest();
/*     */           }
/* 392 */           return true;
/*     */         case 115:
/* 394 */           processSaveCursorPosition();
/* 395 */           return true;
/*     */         case 117:
/* 397 */           processRestoreCursorPosition();
/* 398 */           return true;
/*     */       } 
/*     */       
/* 401 */       if (97 <= command && 122 <= command) {
/* 402 */         processUnknownExtension(options, command);
/* 403 */         return true;
/*     */       } 
/* 405 */       if (65 <= command && 90 <= command) {
/* 406 */         processUnknownExtension(options, command);
/* 407 */         return true;
/*     */       } 
/* 409 */       return false;
/*     */     }
/* 411 */     catch (IllegalArgumentException illegalArgumentException) {
/*     */       
/* 413 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean processOperatingSystemCommand(ArrayList<Object> options) throws IOException {
/* 422 */     int command = optionInt(options, 0);
/* 423 */     String label = (String)options.get(1);
/*     */ 
/*     */     
/*     */     try {
/* 427 */       switch (command) {
/*     */         case 0:
/* 429 */           processChangeIconNameAndWindowTitle(label);
/* 430 */           return true;
/*     */         case 1:
/* 432 */           processChangeIconName(label);
/* 433 */           return true;
/*     */         case 2:
/* 435 */           processChangeWindowTitle(label);
/* 436 */           return true;
/*     */       } 
/*     */ 
/*     */       
/* 440 */       processUnknownOperatingSystemCommand(command, label);
/* 441 */       return true;
/*     */     }
/* 443 */     catch (IllegalArgumentException illegalArgumentException) {
/*     */       
/* 445 */       return false;
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processRestoreCursorPosition() throws IOException {}
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
/*     */   protected void processSaveCursorPosition() throws IOException {}
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
/*     */   protected void processInsertLine(int optionInt) throws IOException {}
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
/*     */   protected void processDeleteLine(int optionInt) throws IOException {}
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
/*     */   protected void processScrollDown(int optionInt) throws IOException {}
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
/*     */   protected void processScrollUp(int optionInt) throws IOException {}
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
/*     */   protected void processEraseScreen(int eraseOption) throws IOException {}
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
/*     */   protected void processEraseLine(int eraseOption) throws IOException {}
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
/*     */   protected void processSetAttribute(int attribute) throws IOException {}
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
/*     */   protected void processSetForegroundColor(int color) throws IOException {
/* 567 */     processSetForegroundColor(color, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetForegroundColor(int color, boolean bright) throws IOException {
/* 578 */     processSetForegroundColorExt(bright ? (color + 8) : color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetForegroundColorExt(int paletteIndex) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetForegroundColorExt(int r, int g, int b) throws IOException {
/* 599 */     processSetForegroundColorExt(Colors.roundRgbColor(r, g, b, 16));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColor(int color) throws IOException {
/* 608 */     processSetBackgroundColor(color, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColor(int color, boolean bright) throws IOException {
/* 619 */     processSetBackgroundColorExt(bright ? (color + 8) : color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColorExt(int paletteIndex) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColorExt(int r, int g, int b) throws IOException {
/* 640 */     processSetBackgroundColorExt(Colors.roundRgbColor(r, g, b, 16));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processDefaultTextColor() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processDefaultBackgroundColor() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processAttributeRest() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorTo(int row, int col) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorToColumn(int x) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorUpLine(int count) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorDownLine(int count) throws IOException {
/* 697 */     for (int i = 0; i < count; i++) {
/* 698 */       this.out.write(10);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorLeft(int count) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorRight(int count) throws IOException {
/* 717 */     for (int i = 0; i < count; i++) {
/* 718 */       this.out.write(32);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorDown(int count) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorUp(int count) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processUnknownExtension(ArrayList<Object> options, int command) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processChangeIconNameAndWindowTitle(String label) {
/* 746 */     processChangeIconName(label);
/* 747 */     processChangeWindowTitle(label);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processChangeIconName(String name) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processChangeWindowTitle(String title) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processUnknownOperatingSystemCommand(int command, String param) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean processCharsetSelect(ArrayList<Object> options) throws IOException {
/* 778 */     int set = optionInt(options, 0);
/* 779 */     char seq = ((Character)options.get(1)).charValue();
/* 780 */     processCharsetSelect(set, seq);
/* 781 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCharsetSelect(int set, char seq) {}
/*     */   
/*     */   private int optionInt(ArrayList<Object> options, int index) {
/* 788 */     if (options.size() <= index)
/* 789 */       throw new IllegalArgumentException(); 
/* 790 */     Object value = options.get(index);
/* 791 */     if (value == null)
/* 792 */       throw new IllegalArgumentException(); 
/* 793 */     if (!value.getClass().equals(Integer.class))
/* 794 */       throw new IllegalArgumentException(); 
/* 795 */     return ((Integer)value).intValue();
/*     */   }
/*     */   
/*     */   private int optionInt(ArrayList<Object> options, int index, int defaultValue) {
/* 799 */     if (options.size() > index) {
/* 800 */       Object value = options.get(index);
/* 801 */       if (value == null) {
/* 802 */         return defaultValue;
/*     */       }
/* 804 */       return ((Integer)value).intValue();
/*     */     } 
/* 806 */     return defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(char[] cbuf, int off, int len) throws IOException {
/* 812 */     for (int i = 0; i < len; i++) {
/* 813 */       write(cbuf[off + i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(String str, int off, int len) throws IOException {
/* 820 */     for (int i = 0; i < len; i++) {
/* 821 */       write(str.charAt(off + i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 827 */     write(RESET_CODE);
/* 828 */     flush();
/* 829 */     super.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\AnsiWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */