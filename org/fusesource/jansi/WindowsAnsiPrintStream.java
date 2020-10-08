/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
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
/*     */ 
/*     */ 
/*     */ public final class WindowsAnsiPrintStream
/*     */   extends AnsiPrintStream
/*     */ {
/*  59 */   private static final long stdout_handle = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
/*  60 */   private static final long stderr_handle = Kernel32.GetStdHandle(Kernel32.STD_ERROR_HANDLE);
/*     */   
/*     */   private final long console;
/*     */   private static final short FOREGROUND_BLACK = 0;
/*  64 */   private static final short FOREGROUND_YELLOW = (short)(Kernel32.FOREGROUND_RED | Kernel32.FOREGROUND_GREEN);
/*  65 */   private static final short FOREGROUND_MAGENTA = (short)(Kernel32.FOREGROUND_BLUE | Kernel32.FOREGROUND_RED);
/*  66 */   private static final short FOREGROUND_CYAN = (short)(Kernel32.FOREGROUND_BLUE | Kernel32.FOREGROUND_GREEN);
/*  67 */   private static final short FOREGROUND_WHITE = (short)(Kernel32.FOREGROUND_RED | Kernel32.FOREGROUND_GREEN | Kernel32.FOREGROUND_BLUE);
/*     */   
/*     */   private static final short BACKGROUND_BLACK = 0;
/*  70 */   private static final short BACKGROUND_YELLOW = (short)(Kernel32.BACKGROUND_RED | Kernel32.BACKGROUND_GREEN);
/*  71 */   private static final short BACKGROUND_MAGENTA = (short)(Kernel32.BACKGROUND_BLUE | Kernel32.BACKGROUND_RED);
/*  72 */   private static final short BACKGROUND_CYAN = (short)(Kernel32.BACKGROUND_BLUE | Kernel32.BACKGROUND_GREEN);
/*  73 */   private static final short BACKGROUND_WHITE = (short)(Kernel32.BACKGROUND_RED | Kernel32.BACKGROUND_GREEN | Kernel32.BACKGROUND_BLUE);
/*     */   
/*  75 */   private static final short[] ANSI_FOREGROUND_COLOR_MAP = new short[] { 0, Kernel32.FOREGROUND_RED, Kernel32.FOREGROUND_GREEN, FOREGROUND_YELLOW, Kernel32.FOREGROUND_BLUE, FOREGROUND_MAGENTA, FOREGROUND_CYAN, FOREGROUND_WHITE };
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
/*  86 */   private static final short[] ANSI_BACKGROUND_COLOR_MAP = new short[] { 0, Kernel32.BACKGROUND_RED, Kernel32.BACKGROUND_GREEN, BACKGROUND_YELLOW, Kernel32.BACKGROUND_BLUE, BACKGROUND_MAGENTA, BACKGROUND_CYAN, BACKGROUND_WHITE };
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
/*  97 */   private final Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
/*     */   
/*     */   private final short originalColors;
/*     */   private boolean negative;
/* 101 */   private short savedX = -1;
/* 102 */   private short savedY = -1;
/*     */   
/*     */   public WindowsAnsiPrintStream(PrintStream ps, boolean stdout) throws IOException {
/* 105 */     super(ps);
/* 106 */     this.console = stdout ? stdout_handle : stderr_handle;
/* 107 */     getConsoleInfo();
/* 108 */     this.originalColors = this.info.attributes;
/*     */   }
/*     */   
/*     */   public WindowsAnsiPrintStream(PrintStream ps) throws IOException {
/* 112 */     this(ps, true);
/*     */   }
/*     */   
/*     */   private void getConsoleInfo() throws IOException {
/* 116 */     this.ps.flush();
/* 117 */     if (Kernel32.GetConsoleScreenBufferInfo(this.console, this.info) == 0) {
/* 118 */       throw new IOException("Could not get the screen info: " + WindowsSupport.getLastErrorMessage());
/*     */     }
/* 120 */     if (this.negative) {
/* 121 */       this.info.attributes = invertAttributeColors(this.info.attributes);
/*     */     }
/*     */   }
/*     */   
/*     */   private void applyAttribute() throws IOException {
/* 126 */     this.ps.flush();
/* 127 */     short attributes = this.info.attributes;
/* 128 */     if (this.negative) {
/* 129 */       attributes = invertAttributeColors(attributes);
/*     */     }
/* 131 */     if (Kernel32.SetConsoleTextAttribute(this.console, attributes) == 0) {
/* 132 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private short invertAttributeColors(short attributes) {
/* 138 */     int fg = 0xF & attributes;
/* 139 */     fg <<= 4;
/* 140 */     int bg = 0xF0 & attributes;
/* 141 */     bg >>= 4;
/* 142 */     attributes = (short)(attributes & 0xFF00 | fg | bg);
/* 143 */     return attributes;
/*     */   }
/*     */   
/*     */   private void applyCursorPosition() throws IOException {
/* 147 */     if (Kernel32.SetConsoleCursorPosition(this.console, this.info.cursorPosition.copy()) == 0)
/* 148 */       throw new IOException(WindowsSupport.getLastErrorMessage()); 
/*     */   } protected void processEraseScreen(int eraseOption) throws IOException {
/*     */     Kernel32.COORD topLeft;
/*     */     int screenLength;
/*     */     Kernel32.COORD topLeft2;
/*     */     int lengthToCursor, lengthToEnd;
/* 154 */     getConsoleInfo();
/* 155 */     int[] written = new int[1];
/* 156 */     switch (eraseOption) {
/*     */       case 2:
/* 158 */         topLeft = new Kernel32.COORD();
/* 159 */         topLeft.x = 0;
/* 160 */         topLeft.y = this.info.window.top;
/* 161 */         screenLength = this.info.window.height() * this.info.size.x;
/* 162 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, screenLength, topLeft, written);
/* 163 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', screenLength, topLeft, written);
/*     */         break;
/*     */       case 1:
/* 166 */         topLeft2 = new Kernel32.COORD();
/* 167 */         topLeft2.x = 0;
/* 168 */         topLeft2.y = this.info.window.top;
/* 169 */         lengthToCursor = (this.info.cursorPosition.y - this.info.window.top) * this.info.size.x + this.info.cursorPosition.x;
/*     */         
/* 171 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, lengthToCursor, topLeft2, written);
/* 172 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', lengthToCursor, topLeft2, written);
/*     */         break;
/*     */       case 0:
/* 175 */         lengthToEnd = (this.info.window.bottom - this.info.cursorPosition.y) * this.info.size.x + this.info.size.x - this.info.cursorPosition.x;
/*     */         
/* 177 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, lengthToEnd, this.info.cursorPosition.copy(), written);
/* 178 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', lengthToEnd, this.info.cursorPosition.copy(), written);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processEraseLine(int eraseOption) throws IOException {
/*     */     Kernel32.COORD leftColCurrRow, leftColCurrRow2;
/*     */     int lengthToLastCol;
/* 187 */     getConsoleInfo();
/* 188 */     int[] written = new int[1];
/* 189 */     switch (eraseOption) {
/*     */       case 2:
/* 191 */         leftColCurrRow = this.info.cursorPosition.copy();
/* 192 */         leftColCurrRow.x = 0;
/* 193 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, this.info.size.x, leftColCurrRow, written);
/* 194 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', this.info.size.x, leftColCurrRow, written);
/*     */         break;
/*     */       case 1:
/* 197 */         leftColCurrRow2 = this.info.cursorPosition.copy();
/* 198 */         leftColCurrRow2.x = 0;
/* 199 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, this.info.cursorPosition.x, leftColCurrRow2, written);
/* 200 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', this.info.cursorPosition.x, leftColCurrRow2, written);
/*     */         break;
/*     */       case 0:
/* 203 */         lengthToLastCol = this.info.size.x - this.info.cursorPosition.x;
/* 204 */         Kernel32.FillConsoleOutputAttribute(this.console, this.originalColors, lengthToLastCol, this.info.cursorPosition.copy(), written);
/* 205 */         Kernel32.FillConsoleOutputCharacterW(this.console, ' ', lengthToLastCol, this.info.cursorPosition.copy(), written);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processCursorLeft(int count) throws IOException {
/* 214 */     getConsoleInfo();
/* 215 */     this.info.cursorPosition.x = (short)Math.max(0, this.info.cursorPosition.x - count);
/* 216 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorRight(int count) throws IOException {
/* 221 */     getConsoleInfo();
/* 222 */     this.info.cursorPosition.x = (short)Math.min(this.info.window.width(), this.info.cursorPosition.x + count);
/* 223 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorDown(int count) throws IOException {
/* 228 */     getConsoleInfo();
/* 229 */     this.info.cursorPosition.y = (short)Math.min(Math.max(0, this.info.size.y - 1), this.info.cursorPosition.y + count);
/* 230 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorUp(int count) throws IOException {
/* 235 */     getConsoleInfo();
/* 236 */     this.info.cursorPosition.y = (short)Math.max(this.info.window.top, this.info.cursorPosition.y - count);
/* 237 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorTo(int row, int col) throws IOException {
/* 242 */     getConsoleInfo();
/* 243 */     this.info.cursorPosition.y = (short)Math.max(this.info.window.top, Math.min(this.info.size.y, this.info.window.top + row - 1));
/* 244 */     this.info.cursorPosition.x = (short)Math.max(0, Math.min(this.info.window.width(), col - 1));
/* 245 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processCursorToColumn(int x) throws IOException {
/* 250 */     getConsoleInfo();
/* 251 */     this.info.cursorPosition.x = (short)Math.max(0, Math.min(this.info.window.width(), x - 1));
/* 252 */     applyCursorPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetForegroundColor(int color, boolean bright) throws IOException {
/* 257 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFFF8 | ANSI_FOREGROUND_COLOR_MAP[color]);
/* 258 */     if (bright) {
/* 259 */       this.info.attributes = (short)(this.info.attributes | Kernel32.FOREGROUND_INTENSITY);
/*     */     }
/* 261 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColor(int color, boolean bright) throws IOException {
/* 266 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF8F | ANSI_BACKGROUND_COLOR_MAP[color]);
/* 267 */     if (bright) {
/* 268 */       this.info.attributes = (short)(this.info.attributes | Kernel32.BACKGROUND_INTENSITY);
/*     */     }
/* 270 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDefaultTextColor() throws IOException {
/* 275 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFFF0 | this.originalColors & 0xF);
/* 276 */     this.info.attributes = (short)(this.info.attributes & (Kernel32.FOREGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 277 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDefaultBackgroundColor() throws IOException {
/* 282 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF0F | this.originalColors & 0xF0);
/* 283 */     this.info.attributes = (short)(this.info.attributes & (Kernel32.BACKGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 284 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processAttributeRest() throws IOException {
/* 289 */     this.info.attributes = (short)(this.info.attributes & 0xFFFFFF00 | this.originalColors);
/* 290 */     this.negative = false;
/* 291 */     applyAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetAttribute(int attribute) throws IOException {
/* 296 */     switch (attribute) {
/*     */       case 1:
/* 298 */         this.info.attributes = (short)(this.info.attributes | Kernel32.FOREGROUND_INTENSITY);
/* 299 */         applyAttribute();
/*     */         break;
/*     */       case 22:
/* 302 */         this.info.attributes = (short)(this.info.attributes & (Kernel32.FOREGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 303 */         applyAttribute();
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 309 */         this.info.attributes = (short)(this.info.attributes | Kernel32.BACKGROUND_INTENSITY);
/* 310 */         applyAttribute();
/*     */         break;
/*     */       case 24:
/* 313 */         this.info.attributes = (short)(this.info.attributes & (Kernel32.BACKGROUND_INTENSITY ^ 0xFFFFFFFF));
/* 314 */         applyAttribute();
/*     */         break;
/*     */       
/*     */       case 7:
/* 318 */         this.negative = true;
/* 319 */         applyAttribute();
/*     */         break;
/*     */       case 27:
/* 322 */         this.negative = false;
/* 323 */         applyAttribute();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processSaveCursorPosition() throws IOException {
/* 332 */     getConsoleInfo();
/* 333 */     this.savedX = this.info.cursorPosition.x;
/* 334 */     this.savedY = this.info.cursorPosition.y;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processRestoreCursorPosition() throws IOException {
/* 340 */     if (this.savedX != -1 && this.savedY != -1) {
/* 341 */       this.ps.flush();
/* 342 */       this.info.cursorPosition.x = this.savedX;
/* 343 */       this.info.cursorPosition.y = this.savedY;
/* 344 */       applyCursorPosition();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processInsertLine(int optionInt) throws IOException {
/* 350 */     getConsoleInfo();
/* 351 */     Kernel32.SMALL_RECT scroll = this.info.window.copy();
/* 352 */     scroll.top = this.info.cursorPosition.y;
/* 353 */     Kernel32.COORD org = new Kernel32.COORD();
/* 354 */     org.x = 0;
/* 355 */     org.y = (short)(this.info.cursorPosition.y + optionInt);
/* 356 */     Kernel32.CHAR_INFO info = new Kernel32.CHAR_INFO();
/* 357 */     info.attributes = this.originalColors;
/* 358 */     info.unicodeChar = ' ';
/* 359 */     if (Kernel32.ScrollConsoleScreenBuffer(this.console, scroll, scroll, org, info) == 0) {
/* 360 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processDeleteLine(int optionInt) throws IOException {
/* 366 */     getConsoleInfo();
/* 367 */     Kernel32.SMALL_RECT scroll = this.info.window.copy();
/* 368 */     scroll.top = this.info.cursorPosition.y;
/* 369 */     Kernel32.COORD org = new Kernel32.COORD();
/* 370 */     org.x = 0;
/* 371 */     org.y = (short)(this.info.cursorPosition.y - optionInt);
/* 372 */     Kernel32.CHAR_INFO info = new Kernel32.CHAR_INFO();
/* 373 */     info.attributes = this.originalColors;
/* 374 */     info.unicodeChar = ' ';
/* 375 */     if (Kernel32.ScrollConsoleScreenBuffer(this.console, scroll, scroll, org, info) == 0) {
/* 376 */       throw new IOException(WindowsSupport.getLastErrorMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processChangeWindowTitle(String label) {
/* 382 */     Kernel32.SetConsoleTitle(label);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\WindowsAnsiPrintStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */