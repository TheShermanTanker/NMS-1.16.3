/*     */ package org.jline.terminal.impl.jansi.win;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.fusesource.jansi.internal.Kernel32;
/*     */ import org.fusesource.jansi.internal.WindowsSupport;
/*     */ import org.jline.utils.AnsiWriter;
/*     */ import org.jline.utils.Colors;
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
/*     */ public final class WindowsAnsiWriter
/*     */   extends AnsiWriter
/*     */ {
/*  38 */   private static final long console = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
/*     */   
/*     */   private static final short FOREGROUND_BLACK = 0;
/*  41 */   private static final short FOREGROUND_YELLOW = (short)(Kernel32.FOREGROUND_RED | Kernel32.FOREGROUND_GREEN);
/*  42 */   private static final short FOREGROUND_MAGENTA = (short)(Kernel32.FOREGROUND_BLUE | Kernel32.FOREGROUND_RED);
/*  43 */   private static final short FOREGROUND_CYAN = (short)(Kernel32.FOREGROUND_BLUE | Kernel32.FOREGROUND_GREEN);
/*  44 */   private static final short FOREGROUND_WHITE = (short)(Kernel32.FOREGROUND_RED | Kernel32.FOREGROUND_GREEN | Kernel32.FOREGROUND_BLUE);
/*     */   
/*     */   private static final short BACKGROUND_BLACK = 0;
/*  47 */   private static final short BACKGROUND_YELLOW = (short)(Kernel32.BACKGROUND_RED | Kernel32.BACKGROUND_GREEN);
/*  48 */   private static final short BACKGROUND_MAGENTA = (short)(Kernel32.BACKGROUND_BLUE | Kernel32.BACKGROUND_RED);
/*  49 */   private static final short BACKGROUND_CYAN = (short)(Kernel32.BACKGROUND_BLUE | Kernel32.BACKGROUND_GREEN);
/*  50 */   private static final short BACKGROUND_WHITE = (short)(Kernel32.BACKGROUND_RED | Kernel32.BACKGROUND_GREEN | Kernel32.BACKGROUND_BLUE);
/*     */   
/*  52 */   private static final short[] ANSI_FOREGROUND_COLOR_MAP = new short[] { 0, Kernel32.FOREGROUND_RED, Kernel32.FOREGROUND_GREEN, FOREGROUND_YELLOW, Kernel32.FOREGROUND_BLUE, FOREGROUND_MAGENTA, FOREGROUND_CYAN, FOREGROUND_WHITE };
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
/*  63 */   private static final short[] ANSI_BACKGROUND_COLOR_MAP = new short[] { 0, Kernel32.BACKGROUND_RED, Kernel32.BACKGROUND_GREEN, BACKGROUND_YELLOW, Kernel32.BACKGROUND_BLUE, BACKGROUND_MAGENTA, BACKGROUND_CYAN, BACKGROUND_WHITE };
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
/*  74 */   private final Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
/*     */   
/*     */   private final short originalColors;
/*     */   private boolean negative;
/*     */   private boolean bold;
/*     */   private boolean underline;
/*  80 */   private short savedX = -1;
/*  81 */   private short savedY = -1;
/*     */   
/*     */   public WindowsAnsiWriter(Writer out) throws IOException {
/*  84 */     super(out);
/*  85 */     getConsoleInfo();
/*  86 */     this.originalColors = this.info.attributes;
/*     */   }
/*     */   
/*     */   private void getConsoleInfo() throws IOException {
/*  90 */     this.out.flush();
/*  91 */     if (Kernel32.GetConsoleScreenBufferInfo(console, this.info) == 0) {
/*  92 */       throw new IOException("Could not get the screen info: " + WindowsSupport.getLastErrorMessage());
/*     */     }
/*  94 */     if (this.negative) {
/*  95 */       this.info.attributes = invertAttributeColors(this.info.attributes);
/*     */     }
/*     */   }
/*     */   
/*     */   private void applyAttribute() throws IOException {
/* 100 */     this.out.flush();
/* 101 */     short attributes = this.info.attributes;
/*     */     
/* 103 */     if (this.bold) {
/* 104 */       attributes = (short)(attributes | Kernel32.FOREGROUND_INTENSITY);
/*     */     }
/*     */     
/* 107 */     if (this.underline) {
/* 108 */       attributes = (short)(attributes | Kernel32.BACKGROUND_INTENSITY);
/*     */     }
/* 110 */     if (this.negative) {
/* 111 */       attributes = invertAttributeColors(attributes);
/*     */     }
/* 113 */     if (Kernel32.SetConsoleTextAttribute(console, attributes) == 0) {
/* 114 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private short invertAttributeColors(short attributes) {
/* 120 */     int fg = 0xF & attributes;
/* 121 */     fg <<= 4;
/* 122 */     int bg = 0xF0 & attributes;
/* 123 */     bg >>= 4;
/* 124 */     attributes = (short)(attributes & 0xFF00 | fg | bg);
/* 125 */     return attributes;
/*     */   }
/*     */   
/*     */   private void applyCursorPosition() throws IOException {
/* 129 */     this.info.cursorPosition.x = (short)Math.max(0, Math.min(this.info.size.x - 1, this.info.cursorPosition.x));
/* 130 */     this.info.cursorPosition.y = (short)Math.max(0, Math.min(this.info.size.y - 1, this.info.cursorPosition.y));
/* 131 */     if (Kernel32.SetConsoleCursorPosition(console, this.info.cursorPosition.copy()) == 0)
/* 132 */       throw new IOException(WindowsSupport.getLastErrorMessage()); 
/*     */   } protected void processEraseScreen(int eraseOption) throws IOException {
/*     */     Kernel32.COORD topLeft;
/*     */     int screenLength;
/*     */     Kernel32.COORD topLeft2;
/*     */     int lengthToCursor, lengthToEnd;
/* 138 */     getConsoleInfo();
/* 139 */     int[] written = new int[1];
/* 140 */     switch (eraseOption) {
/*     */       case 2:
/* 142 */         topLeft = new Kernel32.COORD();
/* 143 */         topLeft.x = 0;
/* 144 */         topLeft.y = this.info.window.top;
/* 145 */         screenLength = this.info.window.height() * this.info.size.x;
/* 146 */         Kernel32.FillConsoleOutputAttribute(console, this.originalColors, screenLength, topLeft, written);
/* 147 */         Kernel32.FillConsoleOutputCharacterW(console, ' ', screenLength, topLeft, written);
/*     */         break;
/*     */       case 1:
/* 150 */         topLeft2 = new Kernel32.COORD();
/* 151 */         topLeft2.x = 0;
/* 152 */         topLeft2.y = this.info.window.top;
/* 153 */         lengthToCursor = (this.info.cursorPosition.y - this.info.window.top) * this.info.size.x + this.info.cursorPosition.x;
/*     */         
/* 155 */         Kernel32.FillConsoleOutputAttribute(console, this.originalColors, lengthToCursor, topLeft2, written);
/* 156 */         Kernel32.FillConsoleOutputCharacterW(console, ' ', lengthToCursor, topLeft2, written);
/*     */         break;
/*     */       case 0:
/* 159 */         lengthToEnd = (this.info.window.bottom - this.info.cursorPosition.y) * this.info.size.x + this.info.size.x - this.info.cursorPosition.x;
/*     */         
/* 161 */         Kernel32.FillConsoleOutputAttribute(console, this.originalColors, lengthToEnd, this.info.cursorPosition.copy(), written);
/* 162 */         Kernel32.FillConsoleOutputCharacterW(console, ' ', lengthToEnd, this.info.cursorPosition.copy(), written);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processEraseLine(int eraseOption) throws IOException {
/*     */     Kernel32.COORD leftColCurrRow, leftColCurrRow2;
/*     */     int lengthToLastCol;
/* 171 */     getConsoleInfo();
/* 172 */     int[] written = new int[1];
/* 173 */     switch (eraseOption) {
/*     */       case 2:
/* 175 */         leftColCurrRow = this.info.cursorPosition.copy();
/* 176 */         leftColCurrRow.x = 0;
/* 177 */         Kernel32.FillConsoleOutputAttribute(console, this.originalColors, this.info.size.x, leftColCurrRow, written);
/* 178 */         Kernel32.FillConsoleOutputCharacterW(console, ' ', this.info.size.x, leftColCurrRow, written);
/*     */         break;
/*     */       case 1:
/* 181 */         leftColCurrRow2 = this.info.cursorPosition.copy();
/* 182 */         leftColCurrRow2.x = 0;
/* 183 */         Kernel32.FillConsoleOutputAttribute(console, this.originalColors, this.info.cursorPosition.x, leftColCurrRow2, written);
/* 184 */         Kernel32.FillConsoleOutputCharacterW(console, ' ', this.info.cursorPosition.x, leftColCurrRow2, written);
/*     */         break;
/*     */       case 0:
/* 187 */         lengthToLastCol = this.info.size.x - this.info.cursorPosition.x;
/* 188 */         Kernel32.FillConsoleOutputAttribute(console, this.originalColors, lengthToLastCol, this.info.cursorPosition.copy(), written);
/* 189 */         Kernel32.FillConsoleOutputCharacterW(console, ' ', lengthToLastCol, this.info.cursorPosition.copy(), written);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorUpLine(int count) throws IOException {
/* 197 */     getConsoleInfo();
/* 198 */     this.info.cursorPosition.x = 0;
/* 199 */     this.info.cursorPosition.y = (short)(this.info.cursorPosition.y - count);
/* 200 */     applyCursorPosition();
/*     */   }
/*     */   
/*     */   protected void processCursorDownLine(int count) throws IOException {
/* 204 */     getConsoleInfo();
/* 205 */     this.info.cursorPosition.x = 0;
/* 206 */     this.info.cursorPosition.y = (short)(this.info.cursorPosition.y + count);
/* 207 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorLeft(int count) throws IOException {
/* 212 */     getConsoleInfo();
/* 213 */     this.info.cursorPosition.x = (short)(this.info.cursorPosition.x - count);
/* 214 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorRight(int count) throws IOException {
/* 219 */     getConsoleInfo();
/* 220 */     this.info.cursorPosition.x = (short)(this.info.cursorPosition.x + count);
/* 221 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorDown(int count) throws IOException {
/* 226 */     getConsoleInfo();
/* 227 */     int nb = Math.max(0, this.info.cursorPosition.y + count - this.info.size.y + 1);
/* 228 */     if (nb != count) {
/* 229 */       this.info.cursorPosition.y = (short)(this.info.cursorPosition.y + count);
/* 230 */       applyCursorPosition();
/*     */     } 
/* 232 */     if (nb > 0) {
/* 233 */       Kernel32.SMALL_RECT scroll = this.info.window.copy();
/* 234 */       scroll.top = 0;
/* 235 */       Kernel32.COORD org = new Kernel32.COORD();
/* 236 */       org.x = 0;
/* 237 */       org.y = (short)-nb;
/* 238 */       Kernel32.CHAR_INFO info = new Kernel32.CHAR_INFO();
/* 239 */       info.unicodeChar = ' ';
/* 240 */       info.attributes = this.originalColors;
/* 241 */       Kernel32.ScrollConsoleScreenBuffer(console, scroll, scroll, org, info);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorUp(int count) throws IOException {
/* 247 */     getConsoleInfo();
/* 248 */     this.info.cursorPosition.y = (short)(this.info.cursorPosition.y - count);
/* 249 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorTo(int row, int col) throws IOException {
/* 254 */     getConsoleInfo();
/* 255 */     this.info.cursorPosition.y = (short)(this.info.window.top + row - 1);
/* 256 */     this.info.cursorPosition.x = (short)(col - 1);
/* 257 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorToColumn(int x) throws IOException {
/* 262 */     getConsoleInfo();
/* 263 */     this.info.cursorPosition.x = (short)(x - 1);
/* 264 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetForegroundColorExt(int paletteIndex) throws IOException {
/* 269 */     int color = Colors.roundColor(paletteIndex, 16);
/* 270 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFFF8 | ANSI_FOREGROUND_COLOR_MAP[color & 0x7]);
/* 271 */     this.info.attributes = (short)(this.info.attributes & (Kernel32.FOREGROUND_INTENSITY ^ 0xFFFFFFFF) | ((color >= 8) ? Kernel32.FOREGROUND_INTENSITY : 0));
/* 272 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColorExt(int paletteIndex) throws IOException {
/* 277 */     int color = Colors.roundColor(paletteIndex, 16);
/* 278 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF8F | ANSI_BACKGROUND_COLOR_MAP[color & 0x7]);
/* 279 */     this.info.attributes = (short)(this.info.attributes & (Kernel32.BACKGROUND_INTENSITY ^ 0xFFFFFFFF) | ((color >= 8) ? Kernel32.BACKGROUND_INTENSITY : 0));
/* 280 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDefaultTextColor() throws IOException {
/* 285 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFFF0 | this.originalColors & 0xF);
/* 286 */     this.info.attributes = (short)(this.info.attributes & (Kernel32.FOREGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 287 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDefaultBackgroundColor() throws IOException {
/* 292 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF0F | this.originalColors & 0xF0);
/* 293 */     this.info.attributes = (short)(this.info.attributes & (Kernel32.BACKGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 294 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processAttributeRest() throws IOException {
/* 299 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF00 | this.originalColors);
/* 300 */     this.negative = false;
/* 301 */     this.bold = false;
/* 302 */     this.underline = false;
/* 303 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetAttribute(int attribute) throws IOException {
/* 308 */     switch (attribute) {
/*     */       case 1:
/* 310 */         this.bold = true;
/* 311 */         applyAttribute();
/*     */         break;
/*     */       case 22:
/* 314 */         this.bold = false;
/* 315 */         applyAttribute();
/*     */         break;
/*     */       
/*     */       case 4:
/* 319 */         this.underline = true;
/* 320 */         applyAttribute();
/*     */         break;
/*     */       case 24:
/* 323 */         this.underline = false;
/* 324 */         applyAttribute();
/*     */         break;
/*     */       
/*     */       case 7:
/* 328 */         this.negative = true;
/* 329 */         applyAttribute();
/*     */         break;
/*     */       case 27:
/* 332 */         this.negative = false;
/* 333 */         applyAttribute();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSaveCursorPosition() throws IOException {
/* 342 */     getConsoleInfo();
/* 343 */     this.savedX = this.info.cursorPosition.x;
/* 344 */     this.savedY = this.info.cursorPosition.y;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processRestoreCursorPosition() throws IOException {
/* 350 */     if (this.savedX != -1 && this.savedY != -1) {
/* 351 */       this.out.flush();
/* 352 */       this.info.cursorPosition.x = this.savedX;
/* 353 */       this.info.cursorPosition.y = this.savedY;
/* 354 */       applyCursorPosition();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processInsertLine(int optionInt) throws IOException {
/* 360 */     getConsoleInfo();
/* 361 */     Kernel32.SMALL_RECT scroll = this.info.window.copy();
/* 362 */     scroll.top = this.info.cursorPosition.y;
/* 363 */     Kernel32.COORD org = new Kernel32.COORD();
/* 364 */     org.x = 0;
/* 365 */     org.y = (short)(this.info.cursorPosition.y + optionInt);
/* 366 */     Kernel32.CHAR_INFO info = new Kernel32.CHAR_INFO();
/* 367 */     info.attributes = this.originalColors;
/* 368 */     info.unicodeChar = ' ';
/* 369 */     if (Kernel32.ScrollConsoleScreenBuffer(console, scroll, scroll, org, info) == 0) {
/* 370 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDeleteLine(int optionInt) throws IOException {
/* 376 */     getConsoleInfo();
/* 377 */     Kernel32.SMALL_RECT scroll = this.info.window.copy();
/* 378 */     scroll.top = this.info.cursorPosition.y;
/* 379 */     Kernel32.COORD org = new Kernel32.COORD();
/* 380 */     org.x = 0;
/* 381 */     org.y = (short)(this.info.cursorPosition.y - optionInt);
/* 382 */     Kernel32.CHAR_INFO info = new Kernel32.CHAR_INFO();
/* 383 */     info.attributes = this.originalColors;
/* 384 */     info.unicodeChar = ' ';
/* 385 */     if (Kernel32.ScrollConsoleScreenBuffer(console, scroll, scroll, org, info) == 0) {
/* 386 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processChangeWindowTitle(String title) {
/* 392 */     Kernel32.SetConsoleTitle(title);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\jansi\win\WindowsAnsiWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */