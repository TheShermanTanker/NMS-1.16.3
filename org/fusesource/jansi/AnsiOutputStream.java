/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ public class AnsiOutputStream
/*     */   extends FilterOutputStream
/*     */ {
/*  44 */   public static final byte[] RESET_CODE = "\033[0m".getBytes(); private static final int MAX_ESCAPE_SEQUENCE_LENGTH = 100; private final byte[] buffer; private int pos; private int startOfValue; private final ArrayList<Object> options; private static final int LOOKING_FOR_FIRST_ESC_CHAR = 0; private static final int LOOKING_FOR_SECOND_ESC_CHAR = 1; private static final int LOOKING_FOR_NEXT_ARG = 2; private static final int LOOKING_FOR_STR_ARG_END = 3; private static final int LOOKING_FOR_INT_ARG_END = 4; private static final int LOOKING_FOR_OSC_COMMAND = 5; private static final int LOOKING_FOR_OSC_COMMAND_END = 6;
/*     */   private static final int LOOKING_FOR_OSC_PARAM = 7;
/*     */   @Deprecated
/*  47 */   public static final byte[] REST_CODE = RESET_CODE; private static final int LOOKING_FOR_ST = 8; private static final int LOOKING_FOR_CHARSET = 9; int state; private static final int FIRST_ESC_CHAR = 27; private static final int SECOND_ESC_CHAR = 91; private static final int SECOND_OSC_CHAR = 93;
/*     */   
/*     */   public AnsiOutputStream(OutputStream os) {
/*  50 */     super(os);
/*     */ 
/*     */ 
/*     */     
/*  54 */     this.buffer = new byte[100];
/*  55 */     this.pos = 0;
/*     */     
/*  57 */     this.options = new ArrayList();
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
/*  70 */     this.state = 0;
/*     */   }
/*     */   private static final int BEL = 7; private static final int SECOND_ST_CHAR = 92; private static final int SECOND_CHARSET0_CHAR = 40; private static final int SECOND_CHARSET1_CHAR = 41; protected static final int ERASE_SCREEN_TO_END = 0; protected static final int ERASE_SCREEN_TO_BEGINING = 1; protected static final int ERASE_SCREEN = 2; protected static final int ERASE_LINE_TO_END = 0; protected static final int ERASE_LINE_TO_BEGINING = 1; protected static final int ERASE_LINE = 2;
/*     */   protected static final int ATTRIBUTE_INTENSITY_BOLD = 1;
/*     */   protected static final int ATTRIBUTE_INTENSITY_FAINT = 2;
/*     */   protected static final int ATTRIBUTE_ITALIC = 3;
/*     */   protected static final int ATTRIBUTE_UNDERLINE = 4;
/*     */   protected static final int ATTRIBUTE_BLINK_SLOW = 5;
/*     */   protected static final int ATTRIBUTE_BLINK_FAST = 6;
/*     */   protected static final int ATTRIBUTE_NEGATIVE_ON = 7;
/*     */   protected static final int ATTRIBUTE_CONCEAL_ON = 8;
/*     */   protected static final int ATTRIBUTE_UNDERLINE_DOUBLE = 21;
/*     */   protected static final int ATTRIBUTE_INTENSITY_NORMAL = 22;
/*     */   
/*     */   public synchronized void write(int data) throws IOException {
/*  85 */     switch (this.state) {
/*     */       case 0:
/*  87 */         if (data == 27) {
/*  88 */           this.buffer[this.pos++] = (byte)data;
/*  89 */           this.state = 1; break;
/*     */         } 
/*  91 */         this.out.write(data);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/*  96 */         this.buffer[this.pos++] = (byte)data;
/*  97 */         if (data == 91) {
/*  98 */           this.state = 2; break;
/*  99 */         }  if (data == 93) {
/* 100 */           this.state = 5; break;
/* 101 */         }  if (data == 40) {
/* 102 */           this.options.add(Integer.valueOf(0));
/* 103 */           this.state = 9; break;
/* 104 */         }  if (data == 41) {
/* 105 */           this.options.add(Integer.valueOf(1));
/* 106 */           this.state = 9; break;
/*     */         } 
/* 108 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 113 */         this.buffer[this.pos++] = (byte)data;
/* 114 */         if (34 == data) {
/* 115 */           this.startOfValue = this.pos - 1;
/* 116 */           this.state = 3; break;
/* 117 */         }  if (48 <= data && data <= 57) {
/* 118 */           this.startOfValue = this.pos - 1;
/* 119 */           this.state = 4; break;
/* 120 */         }  if (59 == data) {
/* 121 */           this.options.add(null); break;
/* 122 */         }  if (63 == data) {
/* 123 */           this.options.add(Character.valueOf('?')); break;
/* 124 */         }  if (61 == data) {
/* 125 */           this.options.add(Character.valueOf('=')); break;
/*     */         } 
/* 127 */         reset(processEscapeCommand(this.options, data));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 134 */         this.buffer[this.pos++] = (byte)data;
/* 135 */         if (48 > data || data > 57) {
/* 136 */           String strValue = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue, Charset.defaultCharset());
/* 137 */           Integer value = new Integer(strValue);
/* 138 */           this.options.add(value);
/* 139 */           if (data == 59) {
/* 140 */             this.state = 2; break;
/*     */           } 
/* 142 */           reset(processEscapeCommand(this.options, data));
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 148 */         this.buffer[this.pos++] = (byte)data;
/* 149 */         if (34 != data) {
/* 150 */           String value = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue, Charset.defaultCharset());
/* 151 */           this.options.add(value);
/* 152 */           if (data == 59) {
/* 153 */             this.state = 2; break;
/*     */           } 
/* 155 */           reset(processEscapeCommand(this.options, data));
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 161 */         this.buffer[this.pos++] = (byte)data;
/* 162 */         if (48 <= data && data <= 57) {
/* 163 */           this.startOfValue = this.pos - 1;
/* 164 */           this.state = 6; break;
/*     */         } 
/* 166 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 171 */         this.buffer[this.pos++] = (byte)data;
/* 172 */         if (59 == data) {
/* 173 */           String strValue = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue, Charset.defaultCharset());
/* 174 */           Integer value = new Integer(strValue);
/* 175 */           this.options.add(value);
/* 176 */           this.startOfValue = this.pos;
/* 177 */           this.state = 7; break;
/* 178 */         }  if (48 <= data && data <= 57) {
/*     */           break;
/*     */         }
/*     */         
/* 182 */         reset(false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 7:
/* 187 */         this.buffer[this.pos++] = (byte)data;
/* 188 */         if (7 == data) {
/* 189 */           String value = new String(this.buffer, this.startOfValue, this.pos - 1 - this.startOfValue, Charset.defaultCharset());
/* 190 */           this.options.add(value);
/* 191 */           reset(processOperatingSystemCommand(this.options)); break;
/* 192 */         }  if (27 == data) {
/* 193 */           this.state = 8;
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 8:
/* 200 */         this.buffer[this.pos++] = (byte)data;
/* 201 */         if (92 == data) {
/* 202 */           String value = new String(this.buffer, this.startOfValue, this.pos - 2 - this.startOfValue, Charset.defaultCharset());
/* 203 */           this.options.add(value);
/* 204 */           reset(processOperatingSystemCommand(this.options)); break;
/*     */         } 
/* 206 */         this.state = 7;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 9:
/* 211 */         this.options.add(Character.valueOf((char)data));
/* 212 */         reset(processCharsetSelect(this.options));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 217 */     if (this.pos >= this.buffer.length)
/* 218 */       reset(false); 
/*     */   }
/*     */   protected static final int ATTRIBUTE_UNDERLINE_OFF = 24; protected static final int ATTRIBUTE_BLINK_OFF = 25; @Deprecated
/*     */   protected static final int ATTRIBUTE_NEGATIVE_Off = 27; protected static final int ATTRIBUTE_NEGATIVE_OFF = 27; protected static final int ATTRIBUTE_CONCEAL_OFF = 28; protected static final int BLACK = 0; protected static final int RED = 1; protected static final int GREEN = 2; protected static final int YELLOW = 3;
/*     */   protected static final int BLUE = 4;
/*     */   protected static final int MAGENTA = 5;
/*     */   protected static final int CYAN = 6;
/*     */   protected static final int WHITE = 7;
/*     */   
/*     */   private void reset(boolean skipBuffer) throws IOException {
/* 228 */     if (!skipBuffer) {
/* 229 */       this.out.write(this.buffer, 0, this.pos);
/*     */     }
/* 231 */     this.pos = 0;
/* 232 */     this.startOfValue = 0;
/* 233 */     this.options.clear();
/* 234 */     this.state = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getNextOptionInt(Iterator<Object> optionsIterator) throws IOException {
/*     */     while (true) {
/* 244 */       if (!optionsIterator.hasNext())
/* 245 */         throw new IllegalArgumentException(); 
/* 246 */       Object arg = optionsIterator.next();
/* 247 */       if (arg != null) {
/* 248 */         return ((Integer)arg).intValue();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean processEscapeCommand(ArrayList<Object> options, int command) throws IOException {
/*     */     try {
/*     */       int count;
/*     */       Iterator<Object> optionsIterator;
/* 260 */       switch (command) {
/*     */         case 65:
/* 262 */           processCursorUp(optionInt(options, 0, 1));
/* 263 */           return true;
/*     */         case 66:
/* 265 */           processCursorDown(optionInt(options, 0, 1));
/* 266 */           return true;
/*     */         case 67:
/* 268 */           processCursorRight(optionInt(options, 0, 1));
/* 269 */           return true;
/*     */         case 68:
/* 271 */           processCursorLeft(optionInt(options, 0, 1));
/* 272 */           return true;
/*     */         case 69:
/* 274 */           processCursorDownLine(optionInt(options, 0, 1));
/* 275 */           return true;
/*     */         case 70:
/* 277 */           processCursorUpLine(optionInt(options, 0, 1));
/* 278 */           return true;
/*     */         case 71:
/* 280 */           processCursorToColumn(optionInt(options, 0));
/* 281 */           return true;
/*     */         case 72:
/*     */         case 102:
/* 284 */           processCursorTo(optionInt(options, 0, 1), optionInt(options, 1, 1));
/* 285 */           return true;
/*     */         case 74:
/* 287 */           processEraseScreen(optionInt(options, 0, 0));
/* 288 */           return true;
/*     */         case 75:
/* 290 */           processEraseLine(optionInt(options, 0, 0));
/* 291 */           return true;
/*     */         case 76:
/* 293 */           processInsertLine(optionInt(options, 0, 1));
/* 294 */           return true;
/*     */         case 77:
/* 296 */           processDeleteLine(optionInt(options, 0, 1));
/* 297 */           return true;
/*     */         case 83:
/* 299 */           processScrollUp(optionInt(options, 0, 1));
/* 300 */           return true;
/*     */         case 84:
/* 302 */           processScrollDown(optionInt(options, 0, 1));
/* 303 */           return true;
/*     */         
/*     */         case 109:
/* 306 */           for (Object next : options) {
/* 307 */             if (next != null && next.getClass() != Integer.class) {
/* 308 */               throw new IllegalArgumentException();
/*     */             }
/*     */           } 
/*     */           
/* 312 */           count = 0;
/* 313 */           optionsIterator = options.iterator();
/* 314 */           while (optionsIterator.hasNext()) {
/* 315 */             Object next = optionsIterator.next();
/* 316 */             if (next != null) {
/* 317 */               count++;
/* 318 */               int value = ((Integer)next).intValue();
/* 319 */               if (30 <= value && value <= 37) {
/* 320 */                 processSetForegroundColor(value - 30); continue;
/* 321 */               }  if (40 <= value && value <= 47) {
/* 322 */                 processSetBackgroundColor(value - 40); continue;
/* 323 */               }  if (90 <= value && value <= 97) {
/* 324 */                 processSetForegroundColor(value - 90, true); continue;
/* 325 */               }  if (100 <= value && value <= 107) {
/* 326 */                 processSetBackgroundColor(value - 100, true); continue;
/* 327 */               }  if (value == 38 || value == 48) {
/*     */                 
/* 329 */                 int arg2or5 = getNextOptionInt(optionsIterator);
/* 330 */                 if (arg2or5 == 2) {
/*     */                   
/* 332 */                   int r = getNextOptionInt(optionsIterator);
/* 333 */                   int g = getNextOptionInt(optionsIterator);
/* 334 */                   int b = getNextOptionInt(optionsIterator);
/* 335 */                   if (r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255) {
/* 336 */                     if (value == 38) {
/* 337 */                       processSetForegroundColorExt(r, g, b); continue;
/*     */                     } 
/* 339 */                     processSetBackgroundColorExt(r, g, b); continue;
/*     */                   } 
/* 341 */                   throw new IllegalArgumentException();
/*     */                 } 
/*     */                 
/* 344 */                 if (arg2or5 == 5) {
/*     */                   
/* 346 */                   int paletteIndex = getNextOptionInt(optionsIterator);
/* 347 */                   if (paletteIndex >= 0 && paletteIndex <= 255) {
/* 348 */                     if (value == 38) {
/* 349 */                       processSetForegroundColorExt(paletteIndex); continue;
/*     */                     } 
/* 351 */                     processSetBackgroundColorExt(paletteIndex); continue;
/*     */                   } 
/* 353 */                   throw new IllegalArgumentException();
/*     */                 } 
/*     */ 
/*     */                 
/* 357 */                 throw new IllegalArgumentException();
/*     */               } 
/*     */               
/* 360 */               switch (value) {
/*     */                 case 39:
/* 362 */                   processDefaultTextColor();
/*     */                   continue;
/*     */                 case 49:
/* 365 */                   processDefaultBackgroundColor();
/*     */                   continue;
/*     */                 case 0:
/* 368 */                   processAttributeRest();
/*     */                   continue;
/*     */               } 
/* 371 */               processSetAttribute(value);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 376 */           if (count == 0) {
/* 377 */             processAttributeRest();
/*     */           }
/* 379 */           return true;
/*     */         case 115:
/* 381 */           processSaveCursorPosition();
/* 382 */           return true;
/*     */         case 117:
/* 384 */           processRestoreCursorPosition();
/* 385 */           return true;
/*     */       } 
/*     */       
/* 388 */       if (97 <= command && 122 <= command) {
/* 389 */         processUnknownExtension(options, command);
/* 390 */         return true;
/*     */       } 
/* 392 */       if (65 <= command && 90 <= command) {
/* 393 */         processUnknownExtension(options, command);
/* 394 */         return true;
/*     */       } 
/* 396 */       return false;
/*     */     }
/* 398 */     catch (IllegalArgumentException illegalArgumentException) {
/*     */       
/* 400 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean processOperatingSystemCommand(ArrayList<Object> options) throws IOException {
/* 409 */     int command = optionInt(options, 0);
/* 410 */     String label = (String)options.get(1);
/*     */ 
/*     */     
/*     */     try {
/* 414 */       switch (command) {
/*     */         case 0:
/* 416 */           processChangeIconNameAndWindowTitle(label);
/* 417 */           return true;
/*     */         case 1:
/* 419 */           processChangeIconName(label);
/* 420 */           return true;
/*     */         case 2:
/* 422 */           processChangeWindowTitle(label);
/* 423 */           return true;
/*     */       } 
/*     */ 
/*     */       
/* 427 */       processUnknownOperatingSystemCommand(command, label);
/* 428 */       return true;
/*     */     }
/* 430 */     catch (IllegalArgumentException illegalArgumentException) {
/*     */       
/* 432 */       return false;
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
/* 556 */     processSetForegroundColor(color, false);
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
/* 595 */     processSetBackgroundColor(color, false);
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
/* 682 */     for (int i = 0; i < count; i++) {
/* 683 */       this.out.write(10);
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
/* 702 */     for (int i = 0; i < count; i++) {
/* 703 */       this.out.write(32);
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
/* 731 */     processChangeIconName(label);
/* 732 */     processChangeWindowTitle(label);
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
/* 763 */     int set = optionInt(options, 0);
/* 764 */     char seq = ((Character)options.get(1)).charValue();
/* 765 */     processCharsetSelect(set, seq);
/* 766 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCharsetSelect(int set, char seq) {}
/*     */   
/*     */   private int optionInt(ArrayList<Object> options, int index) {
/* 773 */     if (options.size() <= index)
/* 774 */       throw new IllegalArgumentException(); 
/* 775 */     Object value = options.get(index);
/* 776 */     if (value == null)
/* 777 */       throw new IllegalArgumentException(); 
/* 778 */     if (!value.getClass().equals(Integer.class))
/* 779 */       throw new IllegalArgumentException(); 
/* 780 */     return ((Integer)value).intValue();
/*     */   }
/*     */   
/*     */   private int optionInt(ArrayList<Object> options, int index, int defaultValue) {
/* 784 */     if (options.size() > index) {
/* 785 */       Object value = options.get(index);
/* 786 */       if (value == null) {
/* 787 */         return defaultValue;
/*     */       }
/* 789 */       return ((Integer)value).intValue();
/*     */     } 
/* 791 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 796 */     write(RESET_CODE);
/* 797 */     flush();
/* 798 */     super.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\AnsiOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */