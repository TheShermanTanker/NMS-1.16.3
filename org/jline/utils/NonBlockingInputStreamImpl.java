/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InterruptedIOException;
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
/*     */ public class NonBlockingInputStreamImpl
/*     */   extends NonBlockingInputStream
/*     */ {
/*     */   private InputStream in;
/*  33 */   private int b = -2;
/*     */   
/*     */   private String name;
/*     */   private boolean threadIsReading = false;
/*  37 */   private IOException exception = null;
/*  38 */   private long threadDelay = 60000L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Thread thread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NonBlockingInputStreamImpl(String name, InputStream in) {
/*  50 */     this.in = in;
/*  51 */     this.name = name;
/*     */   }
/*     */   
/*     */   private synchronized void startReadingThreadIfNeeded() {
/*  55 */     if (this.thread == null) {
/*  56 */       this.thread = new Thread(this::run);
/*  57 */       this.thread.setName(this.name + " non blocking reader thread");
/*  58 */       this.thread.setDaemon(true);
/*  59 */       this.thread.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void shutdown() {
/*  69 */     if (this.thread != null) {
/*  70 */       notify();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  80 */     this.in.close();
/*  81 */     shutdown();
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
/*     */   
/*     */   public synchronized int read(long timeout, boolean isPeek) throws IOException {
/*  97 */     if (this.exception != null) {
/*  98 */       assert this.b == -2;
/*  99 */       IOException toBeThrown = this.exception;
/* 100 */       if (!isPeek)
/* 101 */         this.exception = null; 
/* 102 */       throw toBeThrown;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (this.b >= -1) {
/* 111 */       assert this.exception == null;
/*     */     }
/* 113 */     else if (!isPeek && timeout <= 0L && !this.threadIsReading) {
/* 114 */       this.b = this.in.read();
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 120 */       if (!this.threadIsReading) {
/* 121 */         this.threadIsReading = true;
/* 122 */         startReadingThreadIfNeeded();
/* 123 */         notifyAll();
/*     */       } 
/*     */       
/* 126 */       boolean isInfinite = (timeout <= 0L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       while (isInfinite || timeout > 0L) {
/* 133 */         long start = System.currentTimeMillis();
/*     */         
/*     */         try {
/* 136 */           if (Thread.interrupted()) {
/* 137 */             throw new InterruptedException();
/*     */           }
/* 139 */           wait(timeout);
/*     */         }
/* 141 */         catch (InterruptedException e) {
/* 142 */           this.exception = (IOException)(new InterruptedIOException()).initCause(e);
/*     */         } 
/*     */         
/* 145 */         if (this.exception != null) {
/* 146 */           assert this.b == -2;
/*     */           
/* 148 */           IOException toBeThrown = this.exception;
/* 149 */           if (!isPeek)
/* 150 */             this.exception = null; 
/* 151 */           throw toBeThrown;
/*     */         } 
/*     */         
/* 154 */         if (this.b >= -1) {
/* 155 */           assert this.exception == null;
/*     */           
/*     */           break;
/*     */         } 
/* 159 */         if (!isInfinite) {
/* 160 */           timeout -= System.currentTimeMillis() - start;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     int ret = this.b;
/* 172 */     if (!isPeek) {
/* 173 */       this.b = -2;
/*     */     }
/* 175 */     return ret;
/*     */   }
/*     */   
/*     */   private void run() {
/* 179 */     Log.debug(new Object[] { "NonBlockingInputStream start" });
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*     */       int byteRead;
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 189 */         synchronized (this) {
/* 190 */           boolean needToRead = this.threadIsReading;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 196 */             if (!needToRead) {
/* 197 */               wait(this.threadDelay);
/*     */             }
/* 199 */           } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */           
/* 203 */           needToRead = this.threadIsReading;
/* 204 */           if (!needToRead) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 213 */         byteRead = -2;
/* 214 */         IOException failure = null;
/*     */         try {
/* 216 */           byteRead = this.in.read();
/* 217 */         } catch (IOException e) {
/* 218 */           failure = e;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 224 */         synchronized (this) {
/* 225 */           this.exception = failure;
/* 226 */           this.b = byteRead;
/* 227 */           this.threadIsReading = false;
/* 228 */           notify();
/*     */         }
/*     */       
/*     */       }
/* 232 */       while (byteRead >= 0);
/*     */ 
/*     */       
/*     */       return;
/* 236 */     } catch (Throwable t) {
/* 237 */       Log.warn(new Object[] { "Error in NonBlockingInputStream thread", t });
/*     */     } finally {
/* 239 */       Log.debug(new Object[] { "NonBlockingInputStream shutdown" });
/* 240 */       synchronized (this) {
/* 241 */         this.thread = null;
/* 242 */         this.threadIsReading = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\NonBlockingInputStreamImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */