/*     */ package org.jline.utils;
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
/*     */ public class AttributedStyle
/*     */ {
/*     */   public static final int BLACK = 0;
/*     */   public static final int RED = 1;
/*     */   public static final int GREEN = 2;
/*     */   public static final int YELLOW = 3;
/*     */   public static final int BLUE = 4;
/*     */   public static final int MAGENTA = 5;
/*     */   public static final int CYAN = 6;
/*     */   public static final int WHITE = 7;
/*     */   public static final int BRIGHT = 8;
/*     */   static final int F_BOLD = 1;
/*     */   static final int F_FAINT = 2;
/*     */   static final int F_ITALIC = 4;
/*     */   static final int F_UNDERLINE = 8;
/*     */   static final int F_BLINK = 16;
/*     */   static final int F_INVERSE = 32;
/*     */   static final int F_CONCEAL = 64;
/*     */   static final int F_CROSSED_OUT = 128;
/*     */   static final int F_FOREGROUND = 256;
/*     */   static final int F_BACKGROUND = 512;
/*     */   static final int F_HIDDEN = 1024;
/*     */   static final int MASK = 2047;
/*     */   static final int FG_COLOR_EXP = 16;
/*     */   static final int BG_COLOR_EXP = 24;
/*     */   static final int FG_COLOR = 16711680;
/*     */   static final int BG_COLOR = -16777216;
/*  48 */   public static final AttributedStyle DEFAULT = new AttributedStyle();
/*  49 */   public static final AttributedStyle BOLD = DEFAULT.bold();
/*  50 */   public static final AttributedStyle BOLD_OFF = DEFAULT.boldOff();
/*  51 */   public static final AttributedStyle INVERSE = DEFAULT.inverse();
/*  52 */   public static final AttributedStyle INVERSE_OFF = DEFAULT.inverseOff();
/*  53 */   public static final AttributedStyle HIDDEN = DEFAULT.hidden();
/*  54 */   public static final AttributedStyle HIDDEN_OFF = DEFAULT.hiddenOff();
/*     */   
/*     */   final int style;
/*     */   final int mask;
/*     */   
/*     */   public AttributedStyle() {
/*  60 */     this(0, 0);
/*     */   }
/*     */   
/*     */   public AttributedStyle(AttributedStyle s) {
/*  64 */     this(s.style, s.mask);
/*     */   }
/*     */   
/*     */   public AttributedStyle(int style, int mask) {
/*  68 */     this.style = style;
/*  69 */     this.mask = mask & 0x7FF | (((style & 0x100) != 0) ? 16711680 : 0) | (((style & 0x200) != 0) ? -16777216 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributedStyle bold() {
/*  74 */     return new AttributedStyle(this.style | 0x1, this.mask | 0x1);
/*     */   }
/*     */   
/*     */   public AttributedStyle boldOff() {
/*  78 */     return new AttributedStyle(this.style & 0xFFFFFFFE, this.mask | 0x1);
/*     */   }
/*     */   
/*     */   public AttributedStyle boldDefault() {
/*  82 */     return new AttributedStyle(this.style & 0xFFFFFFFE, this.mask & 0xFFFFFFFE);
/*     */   }
/*     */   
/*     */   public AttributedStyle faint() {
/*  86 */     return new AttributedStyle(this.style | 0x2, this.mask | 0x2);
/*     */   }
/*     */   
/*     */   public AttributedStyle faintOff() {
/*  90 */     return new AttributedStyle(this.style & 0xFFFFFFFD, this.mask | 0x2);
/*     */   }
/*     */   
/*     */   public AttributedStyle faintDefault() {
/*  94 */     return new AttributedStyle(this.style & 0xFFFFFFFD, this.mask & 0xFFFFFFFD);
/*     */   }
/*     */   
/*     */   public AttributedStyle italic() {
/*  98 */     return new AttributedStyle(this.style | 0x4, this.mask | 0x4);
/*     */   }
/*     */   
/*     */   public AttributedStyle italicOff() {
/* 102 */     return new AttributedStyle(this.style & 0xFFFFFFFB, this.mask | 0x4);
/*     */   }
/*     */   
/*     */   public AttributedStyle italicDefault() {
/* 106 */     return new AttributedStyle(this.style & 0xFFFFFFFB, this.mask & 0xFFFFFFFB);
/*     */   }
/*     */   
/*     */   public AttributedStyle underline() {
/* 110 */     return new AttributedStyle(this.style | 0x8, this.mask | 0x8);
/*     */   }
/*     */   
/*     */   public AttributedStyle underlineOff() {
/* 114 */     return new AttributedStyle(this.style & 0xFFFFFFF7, this.mask | 0x8);
/*     */   }
/*     */   
/*     */   public AttributedStyle underlineDefault() {
/* 118 */     return new AttributedStyle(this.style & 0xFFFFFFF7, this.mask & 0xFFFFFFF7);
/*     */   }
/*     */   
/*     */   public AttributedStyle blink() {
/* 122 */     return new AttributedStyle(this.style | 0x10, this.mask | 0x10);
/*     */   }
/*     */   
/*     */   public AttributedStyle blinkOff() {
/* 126 */     return new AttributedStyle(this.style & 0xFFFFFFEF, this.mask | 0x10);
/*     */   }
/*     */   
/*     */   public AttributedStyle blinkDefault() {
/* 130 */     return new AttributedStyle(this.style & 0xFFFFFFEF, this.mask & 0xFFFFFFEF);
/*     */   }
/*     */   
/*     */   public AttributedStyle inverse() {
/* 134 */     return new AttributedStyle(this.style | 0x20, this.mask | 0x20);
/*     */   }
/*     */   
/*     */   public AttributedStyle inverseNeg() {
/* 138 */     int s = ((this.style & 0x20) != 0) ? (this.style & 0xFFFFFFDF) : (this.style | 0x20);
/* 139 */     return new AttributedStyle(s, this.mask | 0x20);
/*     */   }
/*     */   
/*     */   public AttributedStyle inverseOff() {
/* 143 */     return new AttributedStyle(this.style & 0xFFFFFFDF, this.mask | 0x20);
/*     */   }
/*     */   
/*     */   public AttributedStyle inverseDefault() {
/* 147 */     return new AttributedStyle(this.style & 0xFFFFFFDF, this.mask & 0xFFFFFFDF);
/*     */   }
/*     */   
/*     */   public AttributedStyle conceal() {
/* 151 */     return new AttributedStyle(this.style | 0x40, this.mask | 0x40);
/*     */   }
/*     */   
/*     */   public AttributedStyle concealOff() {
/* 155 */     return new AttributedStyle(this.style & 0xFFFFFFBF, this.mask | 0x40);
/*     */   }
/*     */   
/*     */   public AttributedStyle concealDefault() {
/* 159 */     return new AttributedStyle(this.style & 0xFFFFFFBF, this.mask & 0xFFFFFFBF);
/*     */   }
/*     */   
/*     */   public AttributedStyle crossedOut() {
/* 163 */     return new AttributedStyle(this.style | 0x80, this.mask | 0x80);
/*     */   }
/*     */   
/*     */   public AttributedStyle crossedOutOff() {
/* 167 */     return new AttributedStyle(this.style & 0xFFFFFF7F, this.mask | 0x80);
/*     */   }
/*     */   
/*     */   public AttributedStyle crossedOutDefault() {
/* 171 */     return new AttributedStyle(this.style & 0xFFFFFF7F, this.mask & 0xFFFFFF7F);
/*     */   }
/*     */   
/*     */   public AttributedStyle foreground(int color) {
/* 175 */     return new AttributedStyle(this.style & 0xFF00FFFF | 0x100 | color << 16 & 0xFF0000, this.mask | 0x100);
/*     */   }
/*     */   
/*     */   public AttributedStyle foregroundOff() {
/* 179 */     return new AttributedStyle(this.style & 0xFF00FFFF & 0xFFFFFEFF, this.mask | 0x100);
/*     */   }
/*     */   
/*     */   public AttributedStyle foregroundDefault() {
/* 183 */     return new AttributedStyle(this.style & 0xFF00FFFF & 0xFFFFFEFF, this.mask & 0xFF00FEFF);
/*     */   }
/*     */   
/*     */   public AttributedStyle background(int color) {
/* 187 */     return new AttributedStyle(this.style & 0xFFFFFF | 0x200 | color << 24 & 0xFF000000, this.mask | 0x200);
/*     */   }
/*     */   
/*     */   public AttributedStyle backgroundOff() {
/* 191 */     return new AttributedStyle(this.style & 0xFFFFFF & 0xFFFFFDFF, this.mask | 0x200);
/*     */   }
/*     */   
/*     */   public AttributedStyle backgroundDefault() {
/* 195 */     return new AttributedStyle(this.style & 0xFFFFFF & 0xFFFFFDFF, this.mask & 0xFFFDFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributedStyle hidden() {
/* 206 */     return new AttributedStyle(this.style | 0x400, this.mask | 0x400);
/*     */   }
/*     */   
/*     */   public AttributedStyle hiddenOff() {
/* 210 */     return new AttributedStyle(this.style & 0xFFFFFBFF, this.mask | 0x400);
/*     */   }
/*     */   
/*     */   public AttributedStyle hiddenDefault() {
/* 214 */     return new AttributedStyle(this.style & 0xFFFFFBFF, this.mask & 0xFFFFFBFF);
/*     */   }
/*     */   
/*     */   public int getStyle() {
/* 218 */     return this.style;
/*     */   }
/*     */   
/*     */   public int getMask() {
/* 222 */     return this.mask;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 227 */     if (this == o) return true; 
/* 228 */     if (o == null || getClass() != o.getClass()) return false; 
/* 229 */     AttributedStyle that = (AttributedStyle)o;
/* 230 */     if (this.style != that.style) return false; 
/* 231 */     return (this.mask == that.mask);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 237 */     int result = this.style;
/* 238 */     result = 31 * result + this.mask;
/* 239 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\AttributedStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */