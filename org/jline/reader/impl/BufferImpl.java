/*     */ package org.jline.reader.impl;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import org.jline.reader.Buffer;
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
/*     */ public class BufferImpl
/*     */   implements Buffer
/*     */ {
/*  24 */   private int cursor = 0;
/*  25 */   private int cursorCol = -1;
/*     */   private int[] buffer;
/*     */   private int g0;
/*     */   private int g1;
/*     */   
/*     */   public BufferImpl() {
/*  31 */     this(64);
/*     */   }
/*     */   
/*     */   public BufferImpl(int size) {
/*  35 */     this.buffer = new int[size];
/*  36 */     this.g0 = 0;
/*  37 */     this.g1 = this.buffer.length;
/*     */   }
/*     */   
/*     */   private BufferImpl(BufferImpl buffer) {
/*  41 */     this.cursor = buffer.cursor;
/*  42 */     this.cursorCol = buffer.cursorCol;
/*  43 */     this.buffer = (int[])buffer.buffer.clone();
/*  44 */     this.g0 = buffer.g0;
/*  45 */     this.g1 = buffer.g1;
/*     */   }
/*     */   
/*     */   public BufferImpl copy() {
/*  49 */     return new BufferImpl(this);
/*     */   }
/*     */   
/*     */   public int cursor() {
/*  53 */     return this.cursor;
/*     */   }
/*     */   
/*     */   public int length() {
/*  57 */     return this.buffer.length - this.g1 - this.g0;
/*     */   }
/*     */   
/*     */   public boolean currChar(int ch) {
/*  61 */     if (this.cursor == length()) {
/*  62 */       return false;
/*     */     }
/*  64 */     this.buffer[adjust(this.cursor)] = ch;
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int currChar() {
/*  70 */     if (this.cursor == length()) {
/*  71 */       return 0;
/*     */     }
/*  73 */     return atChar(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public int prevChar() {
/*  78 */     if (this.cursor <= 0) {
/*  79 */       return 0;
/*     */     }
/*  81 */     return atChar(this.cursor - 1);
/*     */   }
/*     */   
/*     */   public int nextChar() {
/*  85 */     if (this.cursor >= length() - 1) {
/*  86 */       return 0;
/*     */     }
/*  88 */     return atChar(this.cursor + 1);
/*     */   }
/*     */   
/*     */   public int atChar(int i) {
/*  92 */     if (i < 0 || i >= length()) {
/*  93 */       return 0;
/*     */     }
/*  95 */     return this.buffer[adjust(i)];
/*     */   }
/*     */   
/*     */   private int adjust(int i) {
/*  99 */     return (i >= this.g0) ? (i + this.g1 - this.g0) : i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int c) {
/* 109 */     write(new int[] { c });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int c, boolean overTyping) {
/* 120 */     if (overTyping) {
/* 121 */       delete(1);
/*     */     }
/* 123 */     write(new int[] { c });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(CharSequence str) {
/* 130 */     Objects.requireNonNull(str);
/* 131 */     write(str.codePoints().toArray());
/*     */   }
/*     */   
/*     */   public void write(CharSequence str, boolean overTyping) {
/* 135 */     Objects.requireNonNull(str);
/* 136 */     int[] ucps = str.codePoints().toArray();
/* 137 */     if (overTyping) {
/* 138 */       delete(ucps.length);
/*     */     }
/* 140 */     write(ucps);
/*     */   }
/*     */   
/*     */   private void write(int[] ucps) {
/* 144 */     moveGapToCursor();
/* 145 */     int len = length() + ucps.length;
/* 146 */     int sz = this.buffer.length;
/* 147 */     if (sz < len) {
/* 148 */       while (sz < len) {
/* 149 */         sz *= 2;
/*     */       }
/* 151 */       int[] nb = new int[sz];
/* 152 */       System.arraycopy(this.buffer, 0, nb, 0, this.g0);
/* 153 */       System.arraycopy(this.buffer, this.g1, nb, this.g1 + sz - this.buffer.length, this.buffer.length - this.g1);
/* 154 */       this.g1 += sz - this.buffer.length;
/* 155 */       this.buffer = nb;
/*     */     } 
/* 157 */     System.arraycopy(ucps, 0, this.buffer, this.cursor, ucps.length);
/* 158 */     this.g0 += ucps.length;
/* 159 */     this.cursor += ucps.length;
/* 160 */     this.cursorCol = -1;
/*     */   }
/*     */   
/*     */   public boolean clear() {
/* 164 */     if (length() == 0) {
/* 165 */       return false;
/*     */     }
/* 167 */     this.g0 = 0;
/* 168 */     this.g1 = this.buffer.length;
/* 169 */     this.cursor = 0;
/* 170 */     this.cursorCol = -1;
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public String substring(int start) {
/* 175 */     return substring(start, length());
/*     */   }
/*     */   
/*     */   public String substring(int start, int end) {
/* 179 */     if (start >= end || start < 0 || end > length()) {
/* 180 */       return "";
/*     */     }
/* 182 */     if (end <= this.g0)
/* 183 */       return new String(this.buffer, start, end - start); 
/* 184 */     if (start > this.g0) {
/* 185 */       return new String(this.buffer, this.g1 - this.g0 + start, end - start);
/*     */     }
/* 187 */     int[] b = (int[])this.buffer.clone();
/* 188 */     System.arraycopy(b, this.g1, b, this.g0, b.length - this.g1);
/* 189 */     return new String(b, start, end - start);
/*     */   }
/*     */ 
/*     */   
/*     */   public String upToCursor() {
/* 194 */     return substring(0, this.cursor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cursor(int position) {
/* 201 */     if (position == this.cursor) {
/* 202 */       return true;
/*     */     }
/* 204 */     return (move(position - this.cursor) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int move(int num) {
/* 214 */     int where = num;
/*     */     
/* 216 */     if (this.cursor == 0 && where <= 0) {
/* 217 */       return 0;
/*     */     }
/*     */     
/* 220 */     if (this.cursor == length() && where >= 0) {
/* 221 */       return 0;
/*     */     }
/*     */     
/* 224 */     if (this.cursor + where < 0) {
/* 225 */       where = -this.cursor;
/*     */     }
/* 227 */     else if (this.cursor + where > length()) {
/* 228 */       where = length() - this.cursor;
/*     */     } 
/*     */     
/* 231 */     this.cursor += where;
/* 232 */     this.cursorCol = -1;
/*     */     
/* 234 */     return where;
/*     */   }
/*     */   
/*     */   public boolean up() {
/* 238 */     int col = getCursorCol();
/* 239 */     int pnl = this.cursor - 1;
/* 240 */     while (pnl >= 0 && atChar(pnl) != 10) {
/* 241 */       pnl--;
/*     */     }
/* 243 */     if (pnl < 0) {
/* 244 */       return false;
/*     */     }
/* 246 */     int ppnl = pnl - 1;
/* 247 */     while (ppnl >= 0 && atChar(ppnl) != 10) {
/* 248 */       ppnl--;
/*     */     }
/* 250 */     this.cursor = Math.min(ppnl + col + 1, pnl);
/* 251 */     return true;
/*     */   }
/*     */   
/*     */   public boolean down() {
/* 255 */     int col = getCursorCol();
/* 256 */     int nnl = this.cursor;
/* 257 */     while (nnl < length() && atChar(nnl) != 10) {
/* 258 */       nnl++;
/*     */     }
/* 260 */     if (nnl >= length()) {
/* 261 */       return false;
/*     */     }
/* 263 */     int nnnl = nnl + 1;
/* 264 */     while (nnnl < length() && atChar(nnnl) != 10) {
/* 265 */       nnnl++;
/*     */     }
/* 267 */     this.cursor = Math.min(nnl + col + 1, nnnl);
/* 268 */     return true;
/*     */   }
/*     */   
/*     */   public boolean moveXY(int dx, int dy) {
/* 272 */     int col = 0;
/* 273 */     while (prevChar() != 10 && move(-1) == -1) {
/* 274 */       col++;
/*     */     }
/* 276 */     this.cursorCol = 0;
/* 277 */     while (dy < 0) {
/* 278 */       up();
/* 279 */       dy++;
/*     */     } 
/* 281 */     while (dy > 0) {
/* 282 */       down();
/* 283 */       dy--;
/*     */     } 
/* 285 */     col = Math.max(col + dx, 0);
/* 286 */     for (int i = 0; i < col && 
/* 287 */       move(1) == 1 && currChar() != 10; i++);
/*     */ 
/*     */ 
/*     */     
/* 291 */     this.cursorCol = col;
/* 292 */     return true;
/*     */   }
/*     */   
/*     */   private int getCursorCol() {
/* 296 */     if (this.cursorCol < 0) {
/* 297 */       this.cursorCol = 0;
/* 298 */       int pnl = this.cursor - 1;
/* 299 */       while (pnl >= 0 && atChar(pnl) != 10) {
/* 300 */         pnl--;
/*     */       }
/* 302 */       this.cursorCol = this.cursor - pnl - 1;
/*     */     } 
/* 304 */     return this.cursorCol;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int backspace(int num) {
/* 313 */     int count = Math.max(Math.min(this.cursor, num), 0);
/* 314 */     moveGapToCursor();
/* 315 */     this.cursor -= count;
/* 316 */     this.g0 -= count;
/* 317 */     this.cursorCol = -1;
/* 318 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean backspace() {
/* 327 */     return (backspace(1) == 1);
/*     */   }
/*     */   
/*     */   public int delete(int num) {
/* 331 */     int count = Math.max(Math.min(length() - this.cursor, num), 0);
/* 332 */     moveGapToCursor();
/* 333 */     this.g1 += count;
/* 334 */     this.cursorCol = -1;
/* 335 */     return count;
/*     */   }
/*     */   
/*     */   public boolean delete() {
/* 339 */     return (delete(1) == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 344 */     return substring(0, length());
/*     */   }
/*     */   
/*     */   public void copyFrom(Buffer buf) {
/* 348 */     if (!(buf instanceof BufferImpl)) {
/* 349 */       throw new IllegalStateException();
/*     */     }
/* 351 */     BufferImpl that = (BufferImpl)buf;
/* 352 */     this.g0 = that.g0;
/* 353 */     this.g1 = that.g1;
/* 354 */     this.buffer = (int[])that.buffer.clone();
/* 355 */     this.cursor = that.cursor;
/* 356 */     this.cursorCol = that.cursorCol;
/*     */   }
/*     */   
/*     */   private void moveGapToCursor() {
/* 360 */     if (this.cursor < this.g0) {
/* 361 */       int l = this.g0 - this.cursor;
/* 362 */       System.arraycopy(this.buffer, this.cursor, this.buffer, this.g1 - l, l);
/* 363 */       this.g0 -= l;
/* 364 */       this.g1 -= l;
/* 365 */     } else if (this.cursor > this.g0) {
/* 366 */       int l = this.cursor - this.g0;
/* 367 */       System.arraycopy(this.buffer, this.g1, this.buffer, this.g0, l);
/* 368 */       this.g0 += l;
/* 369 */       this.g1 += l;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\BufferImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */