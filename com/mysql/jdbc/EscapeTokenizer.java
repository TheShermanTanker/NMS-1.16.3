/*     */ package com.mysql.jdbc;
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
/*     */ public class EscapeTokenizer
/*     */ {
/*     */   private static final char CHR_ESCAPE = '\\';
/*     */   private static final char CHR_SGL_QUOTE = '\'';
/*     */   private static final char CHR_DBL_QUOTE = '"';
/*     */   private static final char CHR_LF = '\n';
/*     */   private static final char CHR_CR = '\r';
/*     */   private static final char CHR_COMMENT = '-';
/*     */   private static final char CHR_BEGIN_TOKEN = '{';
/*     */   private static final char CHR_END_TOKEN = '}';
/*     */   private static final char CHR_VARIABLE = '@';
/*  40 */   private String source = null;
/*  41 */   private int sourceLength = 0;
/*  42 */   private int pos = 0;
/*     */   
/*     */   private boolean emittingEscapeCode = false;
/*     */   private boolean sawVariableUse = false;
/*  46 */   private int bracesLevel = 0;
/*     */   private boolean inQuotes = false;
/*  48 */   private char quoteChar = Character.MIN_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EscapeTokenizer(String source) {
/*  57 */     this.source = source;
/*  58 */     this.sourceLength = source.length();
/*  59 */     this.pos = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean hasMoreTokens() {
/*  68 */     return (this.pos < this.sourceLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String nextToken() {
/*  77 */     StringBuilder tokenBuf = new StringBuilder();
/*  78 */     boolean backslashEscape = false;
/*     */     
/*  80 */     if (this.emittingEscapeCode) {
/*     */       
/*  82 */       tokenBuf.append("{");
/*  83 */       this.emittingEscapeCode = false;
/*     */     }  while (true) {
/*     */       char c;
/*  86 */       if (this.pos < this.sourceLength)
/*  87 */       { c = this.source.charAt(this.pos);
/*     */ 
/*     */         
/*  90 */         if (c == '\\') {
/*  91 */           tokenBuf.append(c);
/*  92 */           backslashEscape = !backslashEscape;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/*  97 */         if ((c == '\'' || c == '"') && !backslashEscape) {
/*  98 */           tokenBuf.append(c);
/*  99 */           if (this.inQuotes) {
/* 100 */             if (c == this.quoteChar)
/*     */             {
/* 102 */               if (this.pos + 1 < this.sourceLength && this.source.charAt(this.pos + 1) == this.quoteChar) {
/* 103 */                 tokenBuf.append(c);
/* 104 */                 this.pos++;
/*     */               } else {
/* 106 */                 this.inQuotes = false;
/*     */               } 
/*     */             }
/*     */           } else {
/* 110 */             this.inQuotes = true;
/* 111 */             this.quoteChar = c;
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 117 */         if (c == '\n' || c == '\r') {
/* 118 */           tokenBuf.append(c);
/* 119 */           backslashEscape = false;
/*     */           
/*     */           continue;
/*     */         } 
/* 123 */         if (!this.inQuotes && !backslashEscape)
/*     */         
/* 125 */         { if (c == '-')
/* 126 */           { tokenBuf.append(c);
/*     */             
/* 128 */             if (this.pos + 1 < this.sourceLength && this.source.charAt(this.pos + 1) == '-')
/*     */             {
/* 130 */               while (++this.pos < this.sourceLength && c != '\n' && c != '\r') {
/* 131 */                 c = this.source.charAt(this.pos);
/* 132 */                 tokenBuf.append(c);
/*     */               } 
/* 134 */               this.pos--;
/*     */             
/*     */             }
/*     */             
/*     */              }
/*     */           
/* 140 */           else if (c == '{')
/* 141 */           { this.bracesLevel++;
/* 142 */             if (this.bracesLevel == 1) {
/* 143 */               this.emittingEscapeCode = true;
/* 144 */               this.pos++;
/* 145 */               return tokenBuf.toString();
/*     */             } 
/* 147 */             tokenBuf.append(c);
/*     */ 
/*     */             
/*     */              }
/*     */           
/* 152 */           else if (c == '}')
/* 153 */           { tokenBuf.append(c);
/* 154 */             this.bracesLevel--;
/* 155 */             if (this.bracesLevel == 0) {
/* 156 */               this.pos++;
/* 157 */               return tokenBuf.toString();
/*     */             }
/*     */              }
/*     */           
/*     */           else
/*     */           
/* 163 */           { if (c == '@') {
/* 164 */               this.sawVariableUse = true;
/*     */             }
/*     */ 
/*     */             
/* 168 */             tokenBuf.append(c);
/* 169 */             backslashEscape = false; }  continue; }  } else { break; }  tokenBuf.append(c); backslashEscape = false;
/*     */       this.pos++;
/*     */     } 
/* 172 */     return tokenBuf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean sawVariableUse() {
/* 182 */     return this.sawVariableUse;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\EscapeTokenizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */