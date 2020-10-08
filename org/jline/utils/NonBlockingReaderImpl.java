/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.Reader;
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
/*     */ public class NonBlockingReaderImpl
/*     */   extends NonBlockingReader
/*     */ {
/*     */   public static final int READ_EXPIRED = -2;
/*     */   private Reader in;
/*  37 */   private int ch = -2;
/*     */   
/*     */   private String name;
/*     */   private boolean threadIsReading = false;
/*  41 */   private IOException exception = null;
/*  42 */   private long threadDelay = 60000L;
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
/*     */   public NonBlockingReaderImpl(String name, Reader in) {
/*  54 */     this.in = in;
/*  55 */     this.name = name;
/*     */   }
/*     */   
/*     */   private synchronized void startReadingThreadIfNeeded() {
/*  59 */     if (this.thread == null) {
/*  60 */       this.thread = new Thread(this::run);
/*  61 */       this.thread.setName(this.name + " non blocking reader thread");
/*  62 */       this.thread.setDaemon(true);
/*  63 */       this.thread.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void shutdown() {
/*  73 */     if (this.thread != null) {
/*  74 */       notify();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  84 */     this.in.close();
/*  85 */     shutdown();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean ready() throws IOException {
/*  90 */     return (this.ch >= 0 || this.in.ready());
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
/*     */   protected synchronized int read(long timeout, boolean isPeek) throws IOException {
/* 104 */     if (this.exception != null) {
/* 105 */       assert this.ch == -2;
/* 106 */       IOException toBeThrown = this.exception;
/* 107 */       if (!isPeek)
/* 108 */         this.exception = null; 
/* 109 */       throw toBeThrown;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (this.ch >= -1) {
/* 118 */       assert this.exception == null;
/*     */     }
/* 120 */     else if (!isPeek && timeout <= 0L && !this.threadIsReading) {
/* 121 */       this.ch = this.in.read();
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 127 */       if (!this.threadIsReading) {
/* 128 */         this.threadIsReading = true;
/* 129 */         startReadingThreadIfNeeded();
/* 130 */         notifyAll();
/*     */       } 
/*     */       
/* 133 */       boolean isInfinite = (timeout <= 0L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 139 */       while (isInfinite || timeout > 0L) {
/* 140 */         long start = System.currentTimeMillis();
/*     */         
/*     */         try {
/* 143 */           if (Thread.interrupted()) {
/* 144 */             throw new InterruptedException();
/*     */           }
/* 146 */           wait(timeout);
/*     */         }
/* 148 */         catch (InterruptedException e) {
/* 149 */           this.exception = (IOException)(new InterruptedIOException()).initCause(e);
/*     */         } 
/*     */         
/* 152 */         if (this.exception != null) {
/* 153 */           assert this.ch == -2;
/*     */           
/* 155 */           IOException toBeThrown = this.exception;
/* 156 */           if (!isPeek)
/* 157 */             this.exception = null; 
/* 158 */           throw toBeThrown;
/*     */         } 
/*     */         
/* 161 */         if (this.ch >= -1) {
/* 162 */           assert this.exception == null;
/*     */           
/*     */           break;
/*     */         } 
/* 166 */         if (!isInfinite) {
/* 167 */           timeout -= System.currentTimeMillis() - start;
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
/* 178 */     int ret = this.ch;
/* 179 */     if (!isPeek) {
/* 180 */       this.ch = -2;
/*     */     }
/* 182 */     return ret;
/*     */   }
/*     */   
/*     */   private void run() {
/* 186 */     Log.debug(new Object[] { "NonBlockingReader start" });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*     */       while (true) {
/* 196 */         synchronized (this) {
/* 197 */           boolean needToRead = this.threadIsReading;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 203 */             if (!needToRead) {
/* 204 */               wait(this.threadDelay);
/*     */             }
/* 206 */           } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */           
/* 210 */           needToRead = this.threadIsReading;
/* 211 */           if (!needToRead) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 220 */         int charRead = -2;
/* 221 */         IOException failure = null;
/*     */         try {
/* 223 */           charRead = this.in.read();
/*     */ 
/*     */         
/*     */         }
/* 227 */         catch (IOException e) {
/* 228 */           failure = e;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 235 */         synchronized (this) {
/* 236 */           this.exception = failure;
/* 237 */           this.ch = charRead;
/* 238 */           this.threadIsReading = false;
/* 239 */           notify();
/*     */         } 
/*     */       } 
/* 242 */     } catch (Throwable t) {
/* 243 */       Log.warn(new Object[] { "Error in NonBlockingReader thread", t });
/*     */     } finally {
/* 245 */       Log.debug(new Object[] { "NonBlockingReader shutdown" });
/* 246 */       synchronized (this) {
/* 247 */         this.thread = null;
/* 248 */         this.threadIsReading = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void clear() throws IOException {
/* 254 */     while (ready())
/* 255 */       read(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\NonBlockingReaderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */