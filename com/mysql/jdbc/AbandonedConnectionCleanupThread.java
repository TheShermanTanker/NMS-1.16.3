/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.lang.ref.PhantomReference;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
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
/*     */ public class AbandonedConnectionCleanupThread
/*     */   implements Runnable
/*     */ {
/*     */   private static final Map<ConnectionFinalizerPhantomReference, ConnectionFinalizerPhantomReference> connectionFinalizerPhantomRefs;
/*  43 */   private static final ReferenceQueue<MySQLConnection> referenceQueue = new ReferenceQueue<MySQLConnection>();
/*     */   
/*     */   private static final ExecutorService cleanupThreadExcecutorService;
/*  46 */   static Thread threadRef = null;
/*  47 */   private static Lock threadRefLock = new ReentrantLock();
/*     */   
/*     */   static {
/*  50 */     connectionFinalizerPhantomRefs = new ConcurrentHashMap<ConnectionFinalizerPhantomReference, ConnectionFinalizerPhantomReference>();
/*  51 */     cleanupThreadExcecutorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
/*     */           public Thread newThread(Runnable r) {
/*  53 */             Thread t = new Thread(r, "mysql-cj-abandoned-connection-cleanup");
/*  54 */             t.setDaemon(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  60 */             ClassLoader classLoader = AbandonedConnectionCleanupThread.class.getClassLoader();
/*  61 */             if (classLoader == null)
/*     */             {
/*  63 */               classLoader = ClassLoader.getSystemClassLoader();
/*     */             }
/*     */             
/*  66 */             t.setContextClassLoader(classLoader);
/*  67 */             return AbandonedConnectionCleanupThread.threadRef = t;
/*     */           }
/*     */         });
/*  70 */     cleanupThreadExcecutorService.execute(new AbandonedConnectionCleanupThread());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  79 */         checkThreadContextClassLoader();
/*  80 */         Reference<? extends MySQLConnection> reference = referenceQueue.remove(5000L);
/*  81 */         if (reference != null) {
/*  82 */           finalizeResource((ConnectionFinalizerPhantomReference)reference);
/*     */         }
/*  84 */       } catch (InterruptedException e) {
/*  85 */         threadRefLock.lock();
/*     */         try {
/*  87 */           threadRef = null;
/*     */           
/*     */           Reference<? extends MySQLConnection> reference;
/*     */           
/*  91 */           while ((reference = referenceQueue.poll()) != null) {
/*  92 */             finalizeResource((ConnectionFinalizerPhantomReference)reference);
/*     */           }
/*  94 */           connectionFinalizerPhantomRefs.clear();
/*     */         } finally {
/*  96 */           threadRefLock.unlock();
/*     */         } 
/*     */         return;
/*  99 */       } catch (Exception ex) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkThreadContextClassLoader() {
/*     */     try {
/* 112 */       threadRef.getContextClassLoader().getResource("");
/* 113 */     } catch (Throwable e) {
/*     */       
/* 115 */       uncheckedShutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean consistentClassLoaders() {
/* 125 */     threadRefLock.lock();
/*     */     try {
/* 127 */       if (threadRef == null) {
/* 128 */         return false;
/*     */       }
/* 130 */       ClassLoader callerCtxClassLoader = Thread.currentThread().getContextClassLoader();
/* 131 */       ClassLoader threadCtxClassLoader = threadRef.getContextClassLoader();
/* 132 */       return (callerCtxClassLoader != null && threadCtxClassLoader != null && callerCtxClassLoader == threadCtxClassLoader);
/*     */     } finally {
/* 134 */       threadRefLock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void shutdown(boolean checked) {
/* 145 */     if (checked && !consistentClassLoaders()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 150 */     cleanupThreadExcecutorService.shutdownNow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkedShutdown() {
/* 158 */     shutdown(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void uncheckedShutdown() {
/* 165 */     shutdown(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAlive() {
/* 174 */     threadRefLock.lock();
/*     */     try {
/* 176 */       return (threadRef != null && threadRef.isAlive());
/*     */     } finally {
/* 178 */       threadRefLock.unlock();
/*     */     } 
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
/*     */   protected static void trackConnection(MySQLConnection conn, NetworkResources io) {
/* 191 */     threadRefLock.lock();
/*     */     try {
/* 193 */       if (isAlive()) {
/* 194 */         ConnectionFinalizerPhantomReference reference = new ConnectionFinalizerPhantomReference(conn, io, referenceQueue);
/* 195 */         connectionFinalizerPhantomRefs.put(reference, reference);
/*     */       } 
/*     */     } finally {
/* 198 */       threadRefLock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void finalizeResource(ConnectionFinalizerPhantomReference reference) {
/*     */     try {
/* 210 */       reference.finalizeResources();
/* 211 */       reference.clear();
/*     */     } finally {
/* 213 */       connectionFinalizerPhantomRefs.remove(reference);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static class ConnectionFinalizerPhantomReference
/*     */     extends PhantomReference<MySQLConnection>
/*     */   {
/*     */     private NetworkResources networkResources;
/*     */ 
/*     */     
/*     */     ConnectionFinalizerPhantomReference(MySQLConnection conn, NetworkResources networkResources, ReferenceQueue<? super MySQLConnection> refQueue) {
/* 225 */       super(conn, refQueue);
/* 226 */       this.networkResources = networkResources;
/*     */     }
/*     */     
/*     */     void finalizeResources() {
/* 230 */       if (this.networkResources != null) {
/*     */         try {
/* 232 */           this.networkResources.forceClose();
/*     */         } finally {
/* 234 */           this.networkResources = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static Thread getThread() {
/* 241 */     return threadRef;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\AbandonedConnectionCleanupThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */