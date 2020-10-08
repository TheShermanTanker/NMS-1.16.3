/*     */ package org.apache.logging.log4j.io;
/*     */ 
/*     */ import java.nio.CharBuffer;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.spi.ExtendedLogger;
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
/*     */ public class CharStreamLogger
/*     */ {
/*     */   private final ExtendedLogger logger;
/*     */   private final Level level;
/*     */   private final Marker marker;
/*  34 */   private final StringBuilder msg = new StringBuilder();
/*     */   private boolean closed = false;
/*     */   
/*     */   public CharStreamLogger(ExtendedLogger logger, Level level, Marker marker) {
/*  38 */     this.logger = logger;
/*  39 */     this.level = (level == null) ? logger.getLevel() : level;
/*  40 */     this.marker = marker;
/*     */   }
/*     */   
/*     */   public void close(String fqcn) {
/*  44 */     synchronized (this.msg) {
/*  45 */       this.closed = true;
/*  46 */       logEnd(fqcn);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void log(String fqcn) {
/*  52 */     this.logger.logIfEnabled(fqcn, this.level, this.marker, this.msg.toString());
/*  53 */     this.msg.setLength(0);
/*     */   }
/*     */   
/*     */   private void logEnd(String fqcn) {
/*  57 */     if (this.msg.length() > 0) {
/*  58 */       log(fqcn);
/*     */     }
/*     */   }
/*     */   
/*     */   public void put(String fqcn, char[] cbuf, int off, int len) {
/*  63 */     put(fqcn, CharBuffer.wrap(cbuf), off, len);
/*     */   }
/*     */   
/*     */   public void put(String fqcn, CharSequence str, int off, int len) {
/*  67 */     if (len >= 0) {
/*  68 */       synchronized (this.msg) {
/*  69 */         if (this.closed) {
/*     */           return;
/*     */         }
/*  72 */         int start = off;
/*  73 */         int end = off + len;
/*  74 */         for (int pos = off; pos < end; pos++) {
/*  75 */           char c = str.charAt(pos);
/*  76 */           switch (c) {
/*     */             case '\n':
/*     */             case '\r':
/*  79 */               this.msg.append(str, start, pos);
/*  80 */               start = pos + 1;
/*  81 */               if (c == '\n') {
/*  82 */                 log(fqcn);
/*     */               }
/*     */               break;
/*     */           } 
/*     */         } 
/*  87 */         this.msg.append(str, start, end);
/*     */       } 
/*     */     } else {
/*  90 */       logEnd(fqcn);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void put(String fqcn, int c) {
/*  95 */     if (c >= 0) {
/*  96 */       synchronized (this.msg) {
/*  97 */         if (this.closed) {
/*     */           return;
/*     */         }
/* 100 */         switch (c) {
/*     */           case 10:
/* 102 */             log(fqcn);
/*     */             break;
/*     */           case 13:
/*     */             break;
/*     */           default:
/* 107 */             this.msg.append((char)c); break;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 111 */       logEnd(fqcn);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\io\CharStreamLogger.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */