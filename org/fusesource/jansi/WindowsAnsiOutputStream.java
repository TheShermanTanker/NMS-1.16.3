/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.fusesource.jansi.internal.Kernel32;
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
/*     */ public final class WindowsAnsiOutputStream
/*     */   extends AnsiOutputStream
/*     */ {
/*  57 */   private static final long stdout_handle = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
/*  58 */   private static final long stderr_handle = Kernel32.GetStdHandle(Kernel32.STD_ERROR_HANDLE);
/*     */   
/*     */   private final long console;
/*     */   private static final short FOREGROUND_BLACK = 0;
/*  62 */   private static final short FOREGROUND_YELLOW = (short)(Kernel32.FOREGROUND_RED | Kernel32.FOREGROUND_GREEN);
/*  63 */   private static final short FOREGROUND_MAGENTA = (short)(Kernel32.FOREGROUND_BLUE | Kernel32.FOREGROUND_RED);
/*  64 */   private static final short FOREGROUND_CYAN = (short)(Kernel32.FOREGROUND_BLUE | Kernel32.FOREGROUND_GREEN);
/*  65 */   private static final short FOREGROUND_WHITE = (short)(Kernel32.FOREGROUND_RED | Kernel32.FOREGROUND_GREEN | Kernel32.FOREGROUND_BLUE);
/*     */   
/*     */   private static final short BACKGROUND_BLACK = 0;
/*  68 */   private static final short BACKGROUND_YELLOW = (short)(Kernel32.BACKGROUND_RED | Kernel32.BACKGROUND_GREEN);
/*  69 */   private static final short BACKGROUND_MAGENTA = (short)(Kernel32.BACKGROUND_BLUE | Kernel32.BACKGROUND_RED);
/*  70 */   private static final short BACKGROUND_CYAN = (short)(Kernel32.BACKGROUND_BLUE | Kernel32.BACKGROUND_GREEN);
/*  71 */   private static final short BACKGROUND_WHITE = (short)(Kernel32.BACKGROUND_RED | Kernel32.BACKGROUND_GREEN | Kernel32.BACKGROUND_BLUE);
/*     */   
/*  73 */   private static final short[] ANSI_FOREGROUND_COLOR_MAP = new short[] { 0, Kernel32.FOREGROUND_RED, Kernel32.FOREGROUND_GREEN, FOREGROUND_YELLOW, Kernel32.FOREGROUND_BLUE, FOREGROUND_MAGENTA, FOREGROUND_CYAN, FOREGROUND_WHITE };
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
/*  84 */   private static final short[] ANSI_BACKGROUND_COLOR_MAP = new short[] { 0, Kernel32.BACKGROUND_RED, Kernel32.BACKGROUND_GREEN, BACKGROUND_YELLOW, Kernel32.BACKGROUND_BLUE, BACKGROUND_MAGENTA, BACKGROUND_CYAN, BACKGROUND_WHITE };
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
/*  95 */   private final Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
/*     */   
/*     */   private final short originalColors;
/*     */   private boolean negative;
/*  99 */   private short savedX = -1;
/* 100 */   private short savedY = -1;
/*     */   
/*     */   public WindowsAnsiOutputStream(OutputStream os, boolean stdout) throws IOException {
/* 103 */     super(os);
/* 104 */     this.console = stdout ? stdout_handle : stderr_handle;
/* 105 */     getConsoleInfo();
/* 106 */     this.originalColors = this.info.attributes;
/*     */   }
/*     */   
/*     */   public WindowsAnsiOutputStream(OutputStream os) throws IOException {
/* 110 */     this(os, true);
/*     */   }
/*     */   
/*     */   private void getConsoleInfo() throws IOException {
/* 114 */     this.out.flush();
/* 115 */     if (Kernel32.GetConsoleScreenBufferInfo(this.console, this.info) == 0) {
/* 116 */       throw new IOException("Could not get the screen info: " + WindowsSupport.getLastErrorMessage());
/*     */     }
/* 118 */     if (this.negative) {
/* 119 */       this.info.attributes = invertAttributeColors(this.info.attributes);
/*     */     }
/*     */   }
/*     */   
/*     */   private void applyAttribute() throws IOException {
/* 124 */     this.out.flush();
/* 125 */     short attributes = this.info.attributes;
/* 126 */     if (this.negative) {
/* 127 */       attributes = invertAttributeColors(attributes);
/*     */     }
/* 129 */     if (Kernel32.SetConsoleTextAttribute(this.console, attributes) == 0) {
/* 130 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private short invertAttributeColors(short attributes) {
/* 136 */     int fg = 0xF & attributes;
/* 137 */     fg <<= 4;
/* 138 */     int bg = 0xF0 & attributes;
/* 139 */     bg >>= 4;
/* 140 */     attributes = (short)(attributes & 0xFF00 | fg | bg);
/* 141 */     return attributes;
/*     */   }
/*     */   
/*     */   private void applyCursorPosition() throws IOException {
/* 145 */     if (Kernel32.SetConsoleCursorPosition(this.console, this.info.cursorPosition.copy()) == 0)
/* 146 */       throw new IOException(WindowsSupport.getLastErrorMessage()); 
/*     */   } protected void processEraseScreen(int eraseOption) throws IOException {
/*     */     Kernel32.COORD topLeft;
/*     */     int screenLength;
/*     */     Kernel32.COORD topLeft2;
/*     */     int lengthToCursor, lengthToEnd;
/* 152 */     getConsoleInfo();
/* 153 */     int[] written = new int[1];
/* 154 */     switch (eraseOption) {
/*     */       case 2:
/* 156 */         topLeft = new Kernel32.COORD();
/* 157 */         topLeft.x = 0;
/* 158 */         topLeft.y = this.info.window.top;
/* 159 */         screenLength = this.info.window.height() * this.info.size.x;
/* 160 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, screenLength, topLeft, written);
/* 161 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', screenLength, topLeft, written);
/*     */         break;
/*     */       case 1:
/* 164 */         topLeft2 = new Kernel32.COORD();
/* 165 */         topLeft2.x = 0;
/* 166 */         topLeft2.y = this.info.window.top;
/* 167 */         lengthToCursor = (this.info.cursorPosition.y - this.info.window.top) * this.info.size.x + this.info.cursorPosition.x;
/*     */         
/* 169 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, lengthToCursor, topLeft2, written);
/* 170 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', lengthToCursor, topLeft2, written);
/*     */         break;
/*     */       case 0:
/* 173 */         lengthToEnd = (this.info.window.bottom - this.info.cursorPosition.y) * this.info.size.x + this.info.size.x - this.info.cursorPosition.x;
/*     */         
/* 175 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, lengthToEnd, this.info.cursorPosition.copy(), written);
/* 176 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', lengthToEnd, this.info.cursorPosition.copy(), written);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processEraseLine(int eraseOption) throws IOException {
/*     */     Kernel32.COORD leftColCurrRow, leftColCurrRow2;
/*     */     int lengthToLastCol;
/* 185 */     getConsoleInfo();
/* 186 */     int[] written = new int[1];
/* 187 */     switch (eraseOption) {
/*     */       case 2:
/* 189 */         leftColCurrRow = this.info.cursorPosition.copy();
/* 190 */         leftColCurrRow.x = 0;
/* 191 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, this.info.size.x, leftColCurrRow, written);
/* 192 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', this.info.size.x, leftColCurrRow, written);
/*     */         break;
/*     */       case 1:
/* 195 */         leftColCurrRow2 = this.info.cursorPosition.copy();
/* 196 */         leftColCurrRow2.x = 0;
/* 197 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, this.info.cursorPosition.x, leftColCurrRow2, written);
/* 198 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', this.info.cursorPosition.x, leftColCurrRow2, written);
/*     */         break;
/*     */       case 0:
/* 201 */         lengthToLastCol = this.info.size.x - this.info.cursorPosition.x;
/* 202 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, lengthToLastCol, this.info.cursorPosition.copy(), written);
/* 203 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', lengthToLastCol, this.info.cursorPosition.copy(), written);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorLeft(int count) throws IOException {
/* 212 */     getConsoleInfo();
/* 213 */     this.info.cursorPosition.x = (short)Math.max(0, this.info.cursorPosition.x - count);
/* 214 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorRight(int count) throws IOException {
/* 219 */     getConsoleInfo();
/* 220 */     this.info.cursorPosition.x = (short)Math.min(this.info.window.width(), this.info.cursorPosition.x + count);
/* 221 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorDown(int count) throws IOException {
/* 226 */     getConsoleInfo();
/* 227 */     this.info.cursorPosition.y = (short)Math.min(Math.max(0, this.info.size.y - 1), this.info.cursorPosition.y + count);
/* 228 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorUp(int count) throws IOException {
/* 233 */     getConsoleInfo();
/* 234 */     this.info.cursorPosition.y = (short)Math.max(this.info.window.top, this.info.cursorPosition.y - count);
/* 235 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorTo(int row, int col) throws IOException {
/* 240 */     getConsoleInfo();
/* 241 */     this.info.cursorPosition.y = (short)Math.max(this.info.window.top, Math.min(this.info.size.y, this.info.window.top + row - 1));
/* 242 */     this.info.cursorPosition.x = (short)Math.max(0, Math.min(this.info.window.width(), col - 1));
/* 243 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorToColumn(int x) throws IOException {
/* 248 */     getConsoleInfo();
/* 249 */     this.info.cursorPosition.x = (short)Math.max(0, Math.min(this.info.window.width(), x - 1));
/* 250 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetForegroundColor(int color, boolean bright) throws IOException {
/* 255 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFFF8 | ANSI_FOREGROUND_COLOR_MAP[color]);
/* 256 */     if (bright) {
/* 257 */       this.info.attributes = (short)(this.info.attributes | Kernel32.FOREGROUND_INTENSITY);
/*     */     }
/* 259 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColor(int color, boolean bright) throws IOException {
/* 264 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF8F | ANSI_BACKGROUND_COLOR_MAP[color]);
/* 265 */     if (bright) {
/* 266 */       this.info.attributes = (short)(this.info.attributes | Kernel32.BACKGROUND_INTENSITY);
/*     */     }
/* 268 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDefaultTextColor() throws IOException {
/* 273 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFFF0 | this.originalColors & 0xF);
/* 274 */     this.info.attributes = (short)(this.info.attributes & (Kernel32.FOREGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 275 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDefaultBackgroundColor() throws IOException {
/* 280 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF0F | this.originalColors & 0xF0);
/* 281 */     this.info.attributes = (short)(this.info.attributes & (Kernel32.BACKGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 282 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processAttributeRest() throws IOException {
/* 287 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF00 | this.originalColors);
/* 288 */     this.negative = false;
/* 289 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetAttribute(int attribute) throws IOException {
/* 294 */     switch (attribute) {
/*     */       case 1:
/* 296 */         this.info.attributes = (short)(this.info.attributes | Kernel32.FOREGROUND_INTENSITY);
/* 297 */         applyAttribute();
/*     */         break;
/*     */       case 22:
/* 300 */         this.info.attributes = (short)(this.info.attributes & (Kernel32.FOREGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 301 */         applyAttribute();
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 307 */         this.info.attributes = (short)(this.info.attributes | Kernel32.BACKGROUND_INTENSITY);
/* 308 */         applyAttribute();
/*     */         break;
/*     */       case 24:
/* 311 */         this.info.attributes = (short)(this.info.attributes & (Kernel32.BACKGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 312 */         applyAttribute();
/*     */         break;
/*     */       
/*     */       case 7:
/* 316 */         this.negative = true;
/* 317 */         applyAttribute();
/*     */         break;
/*     */       case 27:
/* 320 */         this.negative = false;
/* 321 */         applyAttribute();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSaveCursorPosition() throws IOException {
/* 330 */     getConsoleInfo();
/* 331 */     this.savedX = this.info.cursorPosition.x;
/* 332 */     this.savedY = this.info.cursorPosition.y;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processRestoreCursorPosition() throws IOException {
/* 338 */     if (this.savedX != -1 && this.savedY != -1) {
/* 339 */       this.out.flush();
/* 340 */       this.info.cursorPosition.x = this.savedX;
/* 341 */       this.info.cursorPosition.y = this.savedY;
/* 342 */       applyCursorPosition();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processInsertLine(int optionInt) throws IOException {
/* 348 */     getConsoleInfo();
/* 349 */     Kernel32.SMALL_RECT scroll = this.info.window.copy();
/* 350 */     scroll.top = this.info.cursorPosition.y;
/* 351 */     Kernel32.COORD org = new Kernel32.COORD();
/* 352 */     org.x = 0;
/* 353 */     org.y = (short)(this.info.cursorPosition.y + optionInt);
/* 354 */     Kernel32.CHAR_INFO info = new Kernel32.CHAR_INFO();
/* 355 */     info.attributes = this.originalColors;
/* 356 */     info.unicodeChar = ' ';
/* 357 */     if (Kernel32.ScrollConsoleScreenBuffer(this.console, scroll, scroll, org, info) == 0) {
/* 358 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDeleteLine(int optionInt) throws IOException {
/* 364 */     getConsoleInfo();
/* 365 */     Kernel32.SMALL_RECT scroll = this.info.window.copy();
/* 366 */     scroll.top = this.info.cursorPosition.y;
/* 367 */     Kernel32.COORD org = new Kernel32.COORD();
/* 368 */     org.x = 0;
/* 369 */     org.y = (short)(this.info.cursorPosition.y - optionInt);
/* 370 */     Kernel32.CHAR_INFO info = new Kernel32.CHAR_INFO();
/* 371 */     info.attributes = this.originalColors;
/* 372 */     info.unicodeChar = ' ';
/* 373 */     if (Kernel32.ScrollConsoleScreenBuffer(this.console, scroll, scroll, org, info) == 0) {
/* 374 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processChangeWindowTitle(String label) {
/* 380 */     Kernel32.SetConsoleTitle(label);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\WindowsAnsiOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */