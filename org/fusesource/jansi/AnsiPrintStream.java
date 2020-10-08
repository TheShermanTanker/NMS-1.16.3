/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnsiPrintStream
/*     */   extends FilterPrintStream
/*     */ {
/*     */   public static final String RESET_CODE = "\033[0m";
/*     */   private static final int MAX_ESCAPE_SEQUENCE_LENGTH = 100;
/*     */   private final byte[] buffer;
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
/*     */   private static final int SECOND_ST_CHAR = 92;
/*     */   private static final int SECOND_CHARSET0_CHAR = 40;
/*     */   private static final int SECOND_CHARSET1_CHAR = 41;
/*     */   protected static final int ERASE_SCREEN_TO_END = 0;
/*     */   protected static final int ERASE_SCREEN_TO_BEGINING = 1;
/*     */   
/*     */   public AnsiPrintStream(PrintStream ps) {
/*  46 */     super(ps);
/*     */ 
/*     */ 
/*     */     
/*  50 */     this.buffer = new byte[100];
/*  51 */     this.pos = 0;
/*     */     
/*  53 */     this.options = new ArrayList();
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
/*  66 */     this.state = 0;
/*     */   }
/*     */   protected static final int ERASE_SCREEN = 2; protected static final int ERASE_LINE_TO_END = 0; protected static final int ERASE_LINE_TO_BEGINING = 1; protected static final int ERASE_LINE = 2; protected static final int ATTRIBUTE_INTENSITY_BOLD = 1; protected static final int ATTRIBUTE_INTENSITY_FAINT = 2; protected static final int ATTRIBUTE_ITALIC = 3; protected static final int ATTRIBUTE_UNDERLINE = 4; protected static final int ATTRIBUTE_BLINK_SLOW = 5; protected static final int ATTRIBUTE_BLINK_FAST = 6; protected static final int ATTRIBUTE_NEGATIVE_ON = 7; protected static final int ATTRIBUTE_CONCEAL_ON = 8; protected static final int ATTRIBUTE_UNDERLINE_DOUBLE = 21; protected static final int ATTRIBUTE_INTENSITY_NORMAL = 22; protected static final int ATTRIBUTE_UNDERLINE_OFF = 24; protected static final int ATTRIBUTE_BLINK_OFF = 25; @Deprecated
/*     */   protected static final int ATTRIBUTE_NEGATIVE_Off = 27; protected static final int ATTRIBUTE_NEGATIVE_OFF = 27; protected static final int ATTRIBUTE_CONCEAL_OFF = 28; protected static final int BLACK = 0; protected static final int RED = 1;
/*     */   protected static final int GREEN = 2;
/*     */   protected static final int YELLOW = 3;
/*     */   protected static final int BLUE = 4;
/*     */   protected static final int MAGENTA = 5;
/*     */   protected static final int CYAN = 6;
/*     */   protected static final int WHITE = 7;
/*     */   
/*     */   protected synchronized boolean filter(int data) {
/*  78 */     switch (this.state) {
/*     */       case 0:
/*  80 */         if (data == 27) {
/*  81 */           this.buffer[this.pos++] = (byte)data;
/*  82 */           this.state = 1;
/*  83 */           return false;
/*     */         } 
/*  85 */         return true;
/*     */       
/*     */       case 1:
/*  88 */         this.buffer[this.pos++] = (byte)data;
/*  89 */         if (data == 91) {
/*  90 */           this.state = 2; break;
/*  91 */         }  if (data == 93) {
/*  92 */           this.state = 5; break;
/*  93 */         }  if (data == 40) {
/*  94 */           this.options.add(Integer.valueOf(0));
/*  95 */           this.state = 9; break;
/*  96 */         }  if (data == 41) {
/*  97 */           this.options.add(Integer.valueOf(1));
/*  98 */           this.state = 9; break;
/*     */         } 
/* 100 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 105 */         this.buffer[this.pos++] = (byte)data;
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
/* 119 */         reset(processEscapeCommand(this.options, data));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 126 */         this.buffer[this.pos++] = (byte)data;
/* 127 */         if (48 > data || data > 57) {
/* 128 */           String strValue = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue, Charset.defaultCharset());
/* 129 */           Integer value = new Integer(strValue);
/* 130 */           this.options.add(value);
/* 131 */           if (data == 59) {
/* 132 */             this.state = 2; break;
/*     */           } 
/* 134 */           reset(processEscapeCommand(this.options, data));
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 140 */         this.buffer[this.pos++] = (byte)data;
/* 141 */         if (34 != data) {
/* 142 */           String value = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue, Charset.defaultCharset());
/* 143 */           this.options.add(value);
/* 144 */           if (data == 59) {
/* 145 */             this.state = 2; break;
/*     */           } 
/* 147 */           reset(processEscapeCommand(this.options, data));
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 153 */         this.buffer[this.pos++] = (byte)data;
/* 154 */         if (48 <= data && data <= 57) {
/* 155 */           this.startOfValue = this.pos - 1;
/* 156 */           this.state = 6; break;
/*     */         } 
/* 158 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 163 */         this.buffer[this.pos++] = (byte)data;
/* 164 */         if (59 == data) {
/* 165 */           String strValue = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue, Charset.defaultCharset());
/* 166 */           Integer value = new Integer(strValue);
/* 167 */           this.options.add(value);
/* 168 */           this.startOfValue = this.pos;
/* 169 */           this.state = 7; break;
/* 170 */         }  if (48 <= data && data <= 57) {
/*     */           break;
/*     */         }
/*     */         
/* 174 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 7:
/* 179 */         this.buffer[this.pos++] = (byte)data;
/* 180 */         if (7 == data) {
/* 181 */           String value = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue, Charset.defaultCharset());
/* 182 */           this.options.add(value);
/* 183 */           reset(processOperatingSystemCommand(this.options)); break;
/* 184 */         }  if (27 == data) {
/* 185 */           this.state = 8;
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 8:
/* 192 */         this.buffer[this.pos++] = (byte)data;
/* 193 */         if (92 == data) {
/* 194 */           String value = new String(this.buffer, this.startOfValue, this.pos - 2 - this.startOfValue, Charset.defaultCharset());
/* 195 */           this.options.add(value);
/* 196 */           reset(processOperatingSystemCommand(this.options)); break;
/*     */         } 
/* 198 */         this.state = 7;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 9:
/* 203 */         this.options.add(Character.valueOf((char)data));
/* 204 */         reset(processCharsetSelect(this.options));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 209 */     if (this.pos >= this.buffer.length) {
/* 210 */       reset(false);
/*     */     }
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void reset(boolean skipBuffer) {
/* 220 */     if (!skipBuffer) {
/* 221 */       this.ps.write(this.buffer, 0, this.pos);
/*     */     }
/* 223 */     this.pos = 0;
/* 224 */     this.startOfValue = 0;
/* 225 */     this.options.clear();
/* 226 */     this.state = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getNextOptionInt(Iterator<Object> optionsIterator) throws IOException {
/*     */     while (true) {
/* 236 */       if (!optionsIterator.hasNext())
/* 237 */         throw new IllegalArgumentException(); 
/* 238 */       Object arg = optionsIterator.next();
/* 239 */       if (arg != null) {
/* 240 */         return ((Integer)arg).intValue();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean processEscapeCommand(ArrayList<Object> options, int command) {
/*     */     
/*     */     try { int count;
/*     */       Iterator<Object> optionsIterator;
/* 252 */       switch (command) {
/*     */         case 65:
/* 254 */           processCursorUp(optionInt(options, 0, 1));
/* 255 */           return true;
/*     */         case 66:
/* 257 */           processCursorDown(optionInt(options, 0, 1));
/* 258 */           return true;
/*     */         case 67:
/* 260 */           processCursorRight(optionInt(options, 0, 1));
/* 261 */           return true;
/*     */         case 68:
/* 263 */           processCursorLeft(optionInt(options, 0, 1));
/* 264 */           return true;
/*     */         case 69:
/* 266 */           processCursorDownLine(optionInt(options, 0, 1));
/* 267 */           return true;
/*     */         case 70:
/* 269 */           processCursorUpLine(optionInt(options, 0, 1));
/* 270 */           return true;
/*     */         case 71:
/* 272 */           processCursorToColumn(optionInt(options, 0));
/* 273 */           return true;
/*     */         case 72:
/*     */         case 102:
/* 276 */           processCursorTo(optionInt(options, 0, 1), optionInt(options, 1, 1));
/* 277 */           return true;
/*     */         case 74:
/* 279 */           processEraseScreen(optionInt(options, 0, 0));
/* 280 */           return true;
/*     */         case 75:
/* 282 */           processEraseLine(optionInt(options, 0, 0));
/* 283 */           return true;
/*     */         case 76:
/* 285 */           processInsertLine(optionInt(options, 0, 1));
/* 286 */           return true;
/*     */         case 77:
/* 288 */           processDeleteLine(optionInt(options, 0, 1));
/* 289 */           return true;
/*     */         case 83:
/* 291 */           processScrollUp(optionInt(options, 0, 1));
/* 292 */           return true;
/*     */         case 84:
/* 294 */           processScrollDown(optionInt(options, 0, 1));
/* 295 */           return true;
/*     */         
/*     */         case 109:
/* 298 */           for (Object next : options) {
/* 299 */             if (next != null && next.getClass() != Integer.class) {
/* 300 */               throw new IllegalArgumentException();
/*     */             }
/*     */           } 
/*     */           
/* 304 */           count = 0;
/* 305 */           optionsIterator = options.iterator();
/* 306 */           while (optionsIterator.hasNext()) {
/* 307 */             Object next = optionsIterator.next();
/* 308 */             if (next != null) {
/* 309 */               count++;
/* 310 */               int value = ((Integer)next).intValue();
/* 311 */               if (30 <= value && value <= 37) {
/* 312 */                 processSetForegroundColor(value - 30); continue;
/* 313 */               }  if (40 <= value && value <= 47) {
/* 314 */                 processSetBackgroundColor(value - 40); continue;
/* 315 */               }  if (90 <= value && value <= 97) {
/* 316 */                 processSetForegroundColor(value - 90, true); continue;
/* 317 */               }  if (100 <= value && value <= 107) {
/* 318 */                 processSetBackgroundColor(value - 100, true); continue;
/* 319 */               }  if (value == 38 || value == 48) {
/*     */                 
/* 321 */                 int arg2or5 = getNextOptionInt(optionsIterator);
/* 322 */                 if (arg2or5 == 2) {
/*     */                   
/* 324 */                   int r = getNextOptionInt(optionsIterator);
/* 325 */                   int g = getNextOptionInt(optionsIterator);
/* 326 */                   int b = getNextOptionInt(optionsIterator);
/* 327 */                   if (r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255) {
/* 328 */                     if (value == 38) {
/* 329 */                       processSetForegroundColorExt(r, g, b); continue;
/*     */                     } 
/* 331 */                     processSetBackgroundColorExt(r, g, b); continue;
/*     */                   } 
/* 333 */                   throw new IllegalArgumentException();
/*     */                 } 
/*     */                 
/* 336 */                 if (arg2or5 == 5) {
/*     */                   
/* 338 */                   int paletteIndex = getNextOptionInt(optionsIterator);
/* 339 */                   if (paletteIndex >= 0 && paletteIndex <= 255) {
/* 340 */                     if (value == 38) {
/* 341 */                       processSetForegroundColorExt(paletteIndex); continue;
/*     */                     } 
/* 343 */                     processSetBackgroundColorExt(paletteIndex); continue;
/*     */                   } 
/* 345 */                   throw new IllegalArgumentException();
/*     */                 } 
/*     */ 
/*     */                 
/* 349 */                 throw new IllegalArgumentException();
/*     */               } 
/*     */               
/* 352 */               switch (value) {
/*     */                 case 39:
/* 354 */                   processDefaultTextColor();
/*     */                   continue;
/*     */                 case 49:
/* 357 */                   processDefaultBackgroundColor();
/*     */                   continue;
/*     */                 case 0:
/* 360 */                   processAttributeRest();
/*     */                   continue;
/*     */               } 
/* 363 */               processSetAttribute(value);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 368 */           if (count == 0) {
/* 369 */             processAttributeRest();
/*     */           }
/* 371 */           return true;
/*     */         case 115:
/* 373 */           processSaveCursorPosition();
/* 374 */           return true;
/*     */         case 117:
/* 376 */           processRestoreCursorPosition();
/* 377 */           return true;
/*     */       } 
/*     */       
/* 380 */       if (97 <= command && 122 <= command) {
/* 381 */         processUnknownExtension(options, command);
/* 382 */         return true;
/*     */       } 
/* 384 */       if (65 <= command && 90 <= command) {
/* 385 */         processUnknownExtension(options, command);
/* 386 */         return true;
/*     */       } 
/* 388 */       return false; }
/*     */     
/* 390 */     catch (IllegalArgumentException illegalArgumentException) {  }
/* 391 */     catch (IOException ioe)
/* 392 */     { setError(); }
/*     */     
/* 394 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean processOperatingSystemCommand(ArrayList<Object> options) {
/* 403 */     int command = optionInt(options, 0);
/* 404 */     String label = (String)options.get(1);
/*     */ 
/*     */     
/*     */     try {
/* 408 */       switch (command) {
/*     */         case 0:
/* 410 */           processChangeIconNameAndWindowTitle(label);
/* 411 */           return true;
/*     */         case 1:
/* 413 */           processChangeIconName(label);
/* 414 */           return true;
/*     */         case 2:
/* 416 */           processChangeWindowTitle(label);
/* 417 */           return true;
/*     */       } 
/*     */ 
/*     */       
/* 421 */       processUnknownOperatingSystemCommand(command, label);
/* 422 */       return true;
/*     */     }
/* 424 */     catch (IllegalArgumentException illegalArgumentException) {
/*     */       
/* 426 */       return false;
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
/*     */   
/*     */   protected void processSetForegroundColor(int color) throws IOException {
/* 550 */     processSetForegroundColor(color, false);
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
/*     */   protected void processSetForegroundColor(int color, boolean bright) throws IOException {}
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
/*     */   protected void processSetForegroundColorExt(int r, int g, int b) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColor(int color) throws IOException {
/* 589 */     processSetBackgroundColor(color, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColor(int color, boolean bright) throws IOException {}
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
/*     */   protected void processSetBackgroundColorExt(int r, int g, int b) throws IOException {}
/*     */ 
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
/*     */   
/*     */   protected void processCursorDownLine(int count) throws IOException {
/* 676 */     for (int i = 0; i < count; i++) {
/* 677 */       this.ps.write(10);
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
/* 696 */     for (int i = 0; i < count; i++) {
/* 697 */       this.ps.write(32);
/*     */     }
/*     */   }
/*     */ 
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
/*     */ 
/*     */   
/*     */   protected void processCursorUp(int count) throws IOException {}
/*     */ 
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
/*     */   
/*     */   protected void processChangeIconNameAndWindowTitle(String label) {
/* 730 */     processChangeIconName(label);
/* 731 */     processChangeWindowTitle(label);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processChangeIconName(String label) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processChangeWindowTitle(String label) {}
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
/*     */   private boolean processCharsetSelect(ArrayList<Object> options) {
/* 762 */     int set = optionInt(options, 0);
/* 763 */     char seq = ((Character)options.get(1)).charValue();
/* 764 */     processCharsetSelect(set, seq);
/* 765 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCharsetSelect(int set, char seq) {}
/*     */   
/*     */   private int optionInt(ArrayList<Object> options, int index) {
/* 772 */     if (options.size() <= index)
/* 773 */       throw new IllegalArgumentException(); 
/* 774 */     Object value = options.get(index);
/* 775 */     if (value == null)
/* 776 */       throw new IllegalArgumentException(); 
/* 777 */     if (!value.getClass().equals(Integer.class))
/* 778 */       throw new IllegalArgumentException(); 
/* 779 */     return ((Integer)value).intValue();
/*     */   }
/*     */   
/*     */   private int optionInt(ArrayList<Object> options, int index, int defaultValue) {
/* 783 */     if (options.size() > index) {
/* 784 */       Object value = options.get(index);
/* 785 */       if (value == null) {
/* 786 */         return defaultValue;
/*     */       }
/* 788 */       return ((Integer)value).intValue();
/*     */     } 
/* 790 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 795 */     print("\033[0m");
/* 796 */     flush();
/* 797 */     super.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\AnsiPrintStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */