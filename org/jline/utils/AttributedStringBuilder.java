/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class AttributedStringBuilder
/*     */   extends AttributedCharSequence
/*     */   implements Appendable
/*     */ {
/*     */   private char[] buffer;
/*     */   private int[] style;
/*     */   private int length;
/*  29 */   private TabStops tabs = new TabStops(0);
/*  30 */   private int lastLineLength = 0;
/*  31 */   private AttributedStyle current = AttributedStyle.DEFAULT;
/*     */   
/*     */   public static AttributedString append(CharSequence... strings) {
/*  34 */     AttributedStringBuilder sb = new AttributedStringBuilder();
/*  35 */     for (CharSequence s : strings) {
/*  36 */       sb.append(s);
/*     */     }
/*  38 */     return sb.toAttributedString();
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder() {
/*  42 */     this(64);
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder(int capacity) {
/*  46 */     this.buffer = new char[capacity];
/*  47 */     this.style = new int[capacity];
/*  48 */     this.length = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int length() {
/*  53 */     return this.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public char charAt(int index) {
/*  58 */     return this.buffer[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributedStyle styleAt(int index) {
/*  63 */     return new AttributedStyle(this.style[index], this.style[index]);
/*     */   }
/*     */ 
/*     */   
/*     */   int styleCodeAt(int index) {
/*  68 */     return this.style[index];
/*     */   }
/*     */ 
/*     */   
/*     */   protected char[] buffer() {
/*  73 */     return this.buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int offset() {
/*  78 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributedString subSequence(int start, int end) {
/*  83 */     return new AttributedString(
/*  84 */         Arrays.copyOfRange(this.buffer, start, end), 
/*  85 */         Arrays.copyOfRange(this.style, start, end), 0, end - start);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributedStringBuilder append(CharSequence csq) {
/*  92 */     return append(new AttributedString(csq, this.current));
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributedStringBuilder append(CharSequence csq, int start, int end) {
/*  97 */     return append(csq.subSequence(start, end));
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributedStringBuilder append(char c) {
/* 102 */     return append(Character.toString(c));
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder append(CharSequence csq, AttributedStyle style) {
/* 106 */     return append(new AttributedString(csq, style));
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder style(AttributedStyle style) {
/* 110 */     this.current = style;
/* 111 */     return this;
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder style(Function<AttributedStyle, AttributedStyle> style) {
/* 115 */     this.current = style.apply(this.current);
/* 116 */     return this;
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder styled(Function<AttributedStyle, AttributedStyle> style, CharSequence cs) {
/* 120 */     return styled(style, sb -> sb.append(cs));
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder styled(AttributedStyle style, CharSequence cs) {
/* 124 */     return styled(s -> style, sb -> sb.append(cs));
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder styled(Function<AttributedStyle, AttributedStyle> style, Consumer<AttributedStringBuilder> consumer) {
/* 128 */     AttributedStyle prev = this.current;
/* 129 */     this.current = style.apply(prev);
/* 130 */     consumer.accept(this);
/* 131 */     this.current = prev;
/* 132 */     return this;
/*     */   }
/*     */   
/*     */   public AttributedStyle style() {
/* 136 */     return this.current;
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder append(AttributedString str) {
/* 140 */     return append(str, 0, str.length());
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder append(AttributedString str, int start, int end) {
/* 144 */     return append(str, start, end);
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder append(AttributedCharSequence str) {
/* 148 */     return append(str, 0, str.length());
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder append(AttributedCharSequence str, int start, int end) {
/* 152 */     ensureCapacity(this.length + end - start);
/* 153 */     for (int i = start; i < end; i++) {
/* 154 */       char c = str.charAt(i);
/* 155 */       int s = str.styleCodeAt(i) & (this.current.getMask() ^ 0xFFFFFFFF) | this.current.getStyle();
/* 156 */       if (this.tabs.defined() && c == '\t') {
/* 157 */         insertTab(new AttributedStyle(s, 0));
/*     */       } else {
/* 159 */         ensureCapacity(this.length + 1);
/* 160 */         this.buffer[this.length] = c;
/* 161 */         this.style[this.length] = s;
/* 162 */         if (c == '\n') {
/* 163 */           this.lastLineLength = 0;
/*     */         } else {
/* 165 */           this.lastLineLength++;
/*     */         } 
/* 167 */         this.length++;
/*     */       } 
/*     */     } 
/* 170 */     return this;
/*     */   }
/*     */   
/*     */   protected void ensureCapacity(int nl) {
/* 174 */     if (nl > this.buffer.length) {
/* 175 */       int s = Math.max(this.buffer.length, 1);
/* 176 */       while (s <= nl) {
/* 177 */         s *= 2;
/*     */       }
/* 179 */       this.buffer = Arrays.copyOf(this.buffer, s);
/* 180 */       this.style = Arrays.copyOf(this.style, s);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void appendAnsi(String ansi) {
/* 185 */     ansiAppend(ansi);
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder ansiAppend(String ansi) {
/* 189 */     int ansiStart = 0;
/* 190 */     int ansiState = 0;
/* 191 */     ensureCapacity(this.length + ansi.length());
/* 192 */     for (int i = 0; i < ansi.length(); i++) {
/* 193 */       char c = ansi.charAt(i);
/* 194 */       if (ansiState == 0 && c == '\033') {
/* 195 */         ansiState++;
/* 196 */       } else if (ansiState == 1 && c == '[') {
/* 197 */         ansiState++;
/* 198 */         ansiStart = i + 1;
/* 199 */       } else if (ansiState == 2) {
/* 200 */         if (c == 'm') {
/* 201 */           String[] params = ansi.substring(ansiStart, i).split(";");
/* 202 */           int j = 0;
/* 203 */           while (j < params.length) {
/* 204 */             int ansiParam = params[j].isEmpty() ? 0 : Integer.parseInt(params[j]);
/* 205 */             switch (ansiParam) {
/*     */               case 0:
/* 207 */                 this.current = AttributedStyle.DEFAULT;
/*     */                 break;
/*     */               case 1:
/* 210 */                 this.current = this.current.bold();
/*     */                 break;
/*     */               case 2:
/* 213 */                 this.current = this.current.faint();
/*     */                 break;
/*     */               case 3:
/* 216 */                 this.current = this.current.italic();
/*     */                 break;
/*     */               case 4:
/* 219 */                 this.current = this.current.underline();
/*     */                 break;
/*     */               case 5:
/* 222 */                 this.current = this.current.blink();
/*     */                 break;
/*     */               case 7:
/* 225 */                 this.current = this.current.inverse();
/*     */                 break;
/*     */               case 8:
/* 228 */                 this.current = this.current.conceal();
/*     */                 break;
/*     */               case 9:
/* 231 */                 this.current = this.current.crossedOut();
/*     */                 break;
/*     */               case 22:
/* 234 */                 this.current = this.current.boldOff().faintOff();
/*     */                 break;
/*     */               case 23:
/* 237 */                 this.current = this.current.italicOff();
/*     */                 break;
/*     */               case 24:
/* 240 */                 this.current = this.current.underlineOff();
/*     */                 break;
/*     */               case 25:
/* 243 */                 this.current = this.current.blinkOff();
/*     */                 break;
/*     */               case 27:
/* 246 */                 this.current = this.current.inverseOff();
/*     */                 break;
/*     */               case 28:
/* 249 */                 this.current = this.current.concealOff();
/*     */                 break;
/*     */               case 29:
/* 252 */                 this.current = this.current.crossedOutOff();
/*     */                 break;
/*     */               case 30:
/*     */               case 31:
/*     */               case 32:
/*     */               case 33:
/*     */               case 34:
/*     */               case 35:
/*     */               case 36:
/*     */               case 37:
/* 262 */                 this.current = this.current.foreground(ansiParam - 30);
/*     */                 break;
/*     */               case 39:
/* 265 */                 this.current = this.current.foregroundOff();
/*     */                 break;
/*     */               case 40:
/*     */               case 41:
/*     */               case 42:
/*     */               case 43:
/*     */               case 44:
/*     */               case 45:
/*     */               case 46:
/*     */               case 47:
/* 275 */                 this.current = this.current.background(ansiParam - 40);
/*     */                 break;
/*     */               case 49:
/* 278 */                 this.current = this.current.backgroundOff();
/*     */                 break;
/*     */               case 38:
/*     */               case 48:
/* 282 */                 if (j + 1 < params.length) {
/* 283 */                   int ansiParam2 = Integer.parseInt(params[++j]);
/* 284 */                   if (ansiParam2 == 2) {
/* 285 */                     if (j + 3 < params.length) {
/* 286 */                       int r = Integer.parseInt(params[++j]);
/* 287 */                       int g = Integer.parseInt(params[++j]);
/* 288 */                       int b = Integer.parseInt(params[++j]);
/*     */                       
/* 290 */                       int col = 16 + (r >> 3) * 36 + (g >> 3) * 6 + (b >> 3);
/* 291 */                       if (ansiParam == 38) {
/* 292 */                         this.current = this.current.foreground(col); break;
/*     */                       } 
/* 294 */                       this.current = this.current.background(col);
/*     */                     }  break;
/*     */                   } 
/* 297 */                   if (ansiParam2 == 5 && 
/* 298 */                     j + 1 < params.length) {
/* 299 */                     int col = Integer.parseInt(params[++j]);
/* 300 */                     if (ansiParam == 38) {
/* 301 */                       this.current = this.current.foreground(col); break;
/*     */                     } 
/* 303 */                     this.current = this.current.background(col);
/*     */                   } 
/*     */                 } 
/*     */                 break;
/*     */ 
/*     */               
/*     */               case 90:
/*     */               case 91:
/*     */               case 92:
/*     */               case 93:
/*     */               case 94:
/*     */               case 95:
/*     */               case 96:
/*     */               case 97:
/* 317 */                 this.current = this.current.foreground(ansiParam - 90 + 8);
/*     */                 break;
/*     */               case 100:
/*     */               case 101:
/*     */               case 102:
/*     */               case 103:
/*     */               case 104:
/*     */               case 105:
/*     */               case 106:
/*     */               case 107:
/* 327 */                 this.current = this.current.background(ansiParam - 100 + 8);
/*     */                 break;
/*     */             } 
/* 330 */             j++;
/*     */           } 
/* 332 */           ansiState = 0;
/* 333 */         } else if ((c < '0' || c > '9') && c != ';') {
/*     */           
/* 335 */           ansiState = 0;
/*     */         } 
/* 337 */       } else if (c == '\t' && this.tabs.defined()) {
/* 338 */         insertTab(this.current);
/*     */       } else {
/* 340 */         ensureCapacity(this.length + 1);
/* 341 */         this.buffer[this.length] = c;
/* 342 */         this.style[this.length] = this.current.getStyle();
/* 343 */         if (c == '\n') {
/* 344 */           this.lastLineLength = 0;
/*     */         } else {
/* 346 */           this.lastLineLength++;
/*     */         } 
/* 348 */         this.length++;
/*     */       } 
/*     */     } 
/* 351 */     return this;
/*     */   }
/*     */   
/*     */   protected void insertTab(AttributedStyle s) {
/* 355 */     int nb = this.tabs.spaces(this.lastLineLength);
/* 356 */     ensureCapacity(this.length + nb);
/* 357 */     for (int i = 0; i < nb; i++) {
/* 358 */       this.buffer[this.length] = ' ';
/* 359 */       this.style[this.length] = s.getStyle();
/* 360 */       this.length++;
/*     */     } 
/* 362 */     this.lastLineLength += nb;
/*     */   }
/*     */   
/*     */   public void setLength(int l) {
/* 366 */     this.length = l;
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
/*     */   public AttributedStringBuilder tabs(int tabsize) {
/* 378 */     if (tabsize < 0) {
/* 379 */       throw new IllegalArgumentException("Tab size must be non negative");
/*     */     }
/* 381 */     return tabs(Arrays.asList(new Integer[] { Integer.valueOf(tabsize) }));
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder tabs(List<Integer> tabs) {
/* 385 */     if (this.length > 0) {
/* 386 */       throw new IllegalStateException("Cannot change tab size after appending text");
/*     */     }
/* 388 */     this.tabs = new TabStops(tabs);
/* 389 */     return this;
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder styleMatches(Pattern pattern, AttributedStyle s) {
/* 393 */     Matcher matcher = pattern.matcher(this);
/* 394 */     while (matcher.find()) {
/* 395 */       for (int i = matcher.start(); i < matcher.end(); i++) {
/* 396 */         this.style[i] = this.style[i] & (s.getMask() ^ 0xFFFFFFFF) | s.getStyle();
/*     */       }
/*     */     } 
/* 399 */     return this;
/*     */   }
/*     */   
/*     */   public AttributedStringBuilder styleMatches(Pattern pattern, List<AttributedStyle> styles) {
/* 403 */     Matcher matcher = pattern.matcher(this);
/* 404 */     while (matcher.find()) {
/* 405 */       for (int group = 0; group < matcher.groupCount(); group++) {
/* 406 */         AttributedStyle s = styles.get(group);
/* 407 */         for (int i = matcher.start(group + 1); i < matcher.end(group + 1); i++) {
/* 408 */           this.style[i] = this.style[i] & (s.getMask() ^ 0xFFFFFFFF) | s.getStyle();
/*     */         }
/*     */       } 
/*     */     } 
/* 412 */     return this;
/*     */   }
/*     */   
/*     */   private class TabStops {
/* 416 */     private List<Integer> tabs = new ArrayList<>();
/* 417 */     private int lastStop = 0;
/* 418 */     private int lastSize = 0;
/*     */     
/*     */     public TabStops(int tabs) {
/* 421 */       this.lastSize = tabs;
/*     */     }
/*     */     
/*     */     public TabStops(List<Integer> tabs) {
/* 425 */       this.tabs = tabs;
/* 426 */       int p = 0;
/* 427 */       for (Iterator<Integer> iterator = tabs.iterator(); iterator.hasNext(); ) { int s = ((Integer)iterator.next()).intValue();
/* 428 */         if (s <= p) {
/*     */           continue;
/*     */         }
/* 431 */         this.lastStop = s;
/* 432 */         this.lastSize = s - p;
/* 433 */         p = s; }
/*     */     
/*     */     }
/*     */     
/*     */     boolean defined() {
/* 438 */       return (this.lastSize > 0);
/*     */     }
/*     */     
/*     */     int spaces(int lastLineLength) {
/* 442 */       int out = 0;
/* 443 */       if (lastLineLength >= this.lastStop) {
/* 444 */         out = this.lastSize - (lastLineLength - this.lastStop) % this.lastSize;
/*     */       } else {
/* 446 */         for (Iterator<Integer> iterator = this.tabs.iterator(); iterator.hasNext(); ) { int s = ((Integer)iterator.next()).intValue();
/* 447 */           if (s > lastLineLength) {
/* 448 */             out = s - lastLineLength;
/*     */             break;
/*     */           }  }
/*     */       
/*     */       } 
/* 453 */       return out;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\AttributedStringBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */