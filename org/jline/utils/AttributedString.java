/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.security.InvalidParameterException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributedString
/*     */   extends AttributedCharSequence
/*     */ {
/*     */   final char[] buffer;
/*     */   final int[] style;
/*     */   final int start;
/*     */   final int end;
/*  31 */   public static final AttributedString EMPTY = new AttributedString("");
/*  32 */   public static final AttributedString NEWLINE = new AttributedString("\n");
/*     */   
/*     */   public AttributedString(CharSequence str) {
/*  35 */     this(str, 0, str.length(), (AttributedStyle)null);
/*     */   }
/*     */   
/*     */   public AttributedString(CharSequence str, int start, int end) {
/*  39 */     this(str, start, end, (AttributedStyle)null);
/*     */   }
/*     */   
/*     */   public AttributedString(CharSequence str, AttributedStyle s) {
/*  43 */     this(str, 0, str.length(), s);
/*     */   }
/*     */   
/*     */   public AttributedString(CharSequence str, int start, int end, AttributedStyle s) {
/*  47 */     if (end < start) {
/*  48 */       throw new InvalidParameterException();
/*     */     }
/*  50 */     if (str instanceof AttributedString) {
/*  51 */       AttributedString as = (AttributedString)str;
/*  52 */       this.buffer = as.buffer;
/*  53 */       if (s != null) {
/*  54 */         this.style = (int[])as.style.clone();
/*  55 */         for (int i = 0; i < this.style.length; i++) {
/*  56 */           this.style[i] = this.style[i] & (s.getMask() ^ 0xFFFFFFFF) | s.getStyle();
/*     */         }
/*     */       } else {
/*  59 */         this.style = as.style;
/*     */       } 
/*  61 */       as.start += start;
/*  62 */       this.end = as.start + end;
/*  63 */     } else if (str instanceof AttributedStringBuilder) {
/*  64 */       AttributedStringBuilder asb = (AttributedStringBuilder)str;
/*  65 */       AttributedString as = asb.subSequence(start, end);
/*  66 */       this.buffer = as.buffer;
/*  67 */       this.style = as.style;
/*  68 */       if (s != null) {
/*  69 */         for (int i = 0; i < this.style.length; i++) {
/*  70 */           this.style[i] = this.style[i] & (s.getMask() ^ 0xFFFFFFFF) | s.getStyle();
/*     */         }
/*     */       }
/*  73 */       this.start = as.start;
/*  74 */       this.end = as.end;
/*     */     } else {
/*  76 */       int l = end - start;
/*  77 */       this.buffer = new char[l];
/*  78 */       for (int i = 0; i < l; i++) {
/*  79 */         this.buffer[i] = str.charAt(start + i);
/*     */       }
/*  81 */       this.style = new int[l];
/*  82 */       if (s != null) {
/*  83 */         Arrays.fill(this.style, s.getStyle());
/*     */       }
/*  85 */       this.start = 0;
/*  86 */       this.end = l;
/*     */     } 
/*     */   }
/*     */   
/*     */   AttributedString(char[] buffer, int[] style, int start, int end) {
/*  91 */     this.buffer = buffer;
/*  92 */     this.style = style;
/*  93 */     this.start = start;
/*  94 */     this.end = end;
/*     */   }
/*     */   
/*     */   public static AttributedString fromAnsi(String ansi) {
/*  98 */     return fromAnsi(ansi, 0);
/*     */   }
/*     */   
/*     */   public static AttributedString fromAnsi(String ansi, int tabs) {
/* 102 */     return fromAnsi(ansi, Arrays.asList(new Integer[] { Integer.valueOf(tabs) }));
/*     */   }
/*     */   
/*     */   public static AttributedString fromAnsi(String ansi, List<Integer> tabs) {
/* 106 */     if (ansi == null) {
/* 107 */       return null;
/*     */     }
/* 109 */     return (new AttributedStringBuilder(ansi.length()))
/* 110 */       .tabs(tabs)
/* 111 */       .ansiAppend(ansi)
/* 112 */       .toAttributedString();
/*     */   }
/*     */   
/*     */   public static String stripAnsi(String ansi) {
/* 116 */     if (ansi == null) {
/* 117 */       return null;
/*     */     }
/* 119 */     return (new AttributedStringBuilder(ansi.length()))
/* 120 */       .ansiAppend(ansi)
/* 121 */       .toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected char[] buffer() {
/* 126 */     return this.buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int offset() {
/* 131 */     return this.start;
/*     */   }
/*     */ 
/*     */   
/*     */   public int length() {
/* 136 */     return this.end - this.start;
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributedStyle styleAt(int index) {
/* 141 */     return new AttributedStyle(this.style[this.start + index], this.style[this.start + index]);
/*     */   }
/*     */ 
/*     */   
/*     */   int styleCodeAt(int index) {
/* 146 */     return this.style[this.start + index];
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributedString subSequence(int start, int end) {
/* 151 */     return new AttributedString(this, start, end);
/*     */   }
/*     */   
/*     */   public AttributedString styleMatches(Pattern pattern, AttributedStyle style) {
/* 155 */     Matcher matcher = pattern.matcher(this);
/* 156 */     boolean result = matcher.find();
/* 157 */     if (result) {
/* 158 */       int[] newstyle = (int[])this.style.clone();
/*     */       while (true) {
/* 160 */         for (int i = matcher.start(); i < matcher.end(); i++) {
/* 161 */           newstyle[this.start + i] = newstyle[this.start + i] & (style.getMask() ^ 0xFFFFFFFF) | style.getStyle();
/*     */         }
/* 163 */         result = matcher.find();
/* 164 */         if (!result)
/* 165 */           return new AttributedString(this.buffer, newstyle, this.start, this.end); 
/*     */       } 
/* 167 */     }  return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 172 */     if (this == o) return true; 
/* 173 */     if (o == null || getClass() != o.getClass()) return false; 
/* 174 */     AttributedString that = (AttributedString)o;
/* 175 */     return (this.end - this.start == that.end - that.start && 
/* 176 */       arrEq(this.buffer, that.buffer, this.start, that.start, this.end - this.start) && 
/* 177 */       arrEq(this.style, that.style, this.start, that.start, this.end - this.start));
/*     */   }
/*     */   
/*     */   private boolean arrEq(char[] a1, char[] a2, int s1, int s2, int l) {
/* 181 */     for (int i = 0; i < l; i++) {
/* 182 */       if (a1[s1 + i] != a2[s2 + i]) {
/* 183 */         return false;
/*     */       }
/*     */     } 
/* 186 */     return true;
/*     */   }
/*     */   private boolean arrEq(int[] a1, int[] a2, int s1, int s2, int l) {
/* 189 */     for (int i = 0; i < l; i++) {
/* 190 */       if (a1[s1 + i] != a2[s2 + i]) {
/* 191 */         return false;
/*     */       }
/*     */     } 
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 199 */     int result = Arrays.hashCode(this.buffer);
/* 200 */     result = 31 * result + Arrays.hashCode(this.style);
/* 201 */     result = 31 * result + this.start;
/* 202 */     result = 31 * result + this.end;
/* 203 */     return result;
/*     */   }
/*     */   
/*     */   public static AttributedString join(AttributedString delimiter, AttributedString... elements) {
/* 207 */     Objects.requireNonNull(delimiter);
/* 208 */     Objects.requireNonNull(elements);
/* 209 */     return join(delimiter, Arrays.asList(elements));
/*     */   }
/*     */   
/*     */   public static AttributedString join(AttributedString delimiter, Iterable<AttributedString> elements) {
/* 213 */     Objects.requireNonNull(elements);
/* 214 */     AttributedStringBuilder sb = new AttributedStringBuilder();
/* 215 */     int i = 0;
/* 216 */     for (AttributedString str : elements) {
/* 217 */       if (i++ > 0 && delimiter != null) {
/* 218 */         sb.append(delimiter);
/*     */       }
/* 220 */       sb.append(str);
/*     */     } 
/* 222 */     return sb.toAttributedString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\AttributedString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */