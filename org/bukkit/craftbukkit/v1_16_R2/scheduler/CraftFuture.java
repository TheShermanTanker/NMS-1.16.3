/*     */ package org.bukkit.craftbukkit.v1_16_R2.scheduler;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.CancellationException;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ class CraftFuture<T>
/*     */   extends CraftTask implements Future<T> {
/*     */   private final Callable<T> callable;
/*     */   private T value;
/*  15 */   private Exception exception = null;
/*     */   
/*     */   CraftFuture(Callable<T> callable, Plugin plugin, int id) {
/*  18 */     super(plugin, null, id, -1L);
/*  19 */     this.callable = callable;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean cancel(boolean mayInterruptIfRunning) {
/*  24 */     if (getPeriod() != -1L) {
/*  25 */       return false;
/*     */     }
/*  27 */     setPeriod(-2L);
/*  28 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDone() {
/*  33 */     long period = getPeriod();
/*  34 */     return (period != -1L && period != -3L);
/*     */   }
/*     */ 
/*     */   
/*     */   public T get() throws CancellationException, InterruptedException, ExecutionException {
/*     */     try {
/*  40 */       return get(0L, TimeUnit.MILLISECONDS);
/*  41 */     } catch (TimeoutException e) {
/*  42 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
/*  48 */     timeout = unit.toMillis(timeout);
/*  49 */     long period = getPeriod();
/*  50 */     long timestamp = (timeout > 0L) ? System.currentTimeMillis() : 0L;
/*     */     
/*  52 */     while (period == -1L || period == -3L) {
/*  53 */       wait(timeout);
/*  54 */       period = getPeriod();
/*  55 */       if (period == -1L || period == -3L) {
/*  56 */         if (timeout == 0L) {
/*     */           continue;
/*     */         }
/*  59 */         timeout += timestamp - (timestamp = System.currentTimeMillis());
/*  60 */         if (timeout > 0L) {
/*     */           continue;
/*     */         }
/*  63 */         throw new TimeoutException();
/*     */       } 
/*     */     } 
/*  66 */     if (period == -2L) {
/*  67 */       throw new CancellationException();
/*     */     }
/*  69 */     if (period == -4L) {
/*  70 */       if (this.exception == null) {
/*  71 */         return this.value;
/*     */       }
/*  73 */       throw new ExecutionException(this.exception);
/*     */     } 
/*  75 */     throw new IllegalStateException("Expected -1 to -4, got " + period);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  81 */     synchronized (this) {
/*  82 */       if (getPeriod() == -2L) {
/*     */         return;
/*     */       }
/*  85 */       setPeriod(-3L);
/*     */     } 
/*     */     try {
/*  88 */       this.value = this.callable.call();
/*  89 */     } catch (Exception e) {
/*  90 */       this.exception = e;
/*     */     } finally {
/*  92 */       synchronized (this) {
/*  93 */         setPeriod(-4L);
/*  94 */         notifyAll();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   synchronized boolean cancel0() {
/* 101 */     if (getPeriod() != -1L) {
/* 102 */       return false;
/*     */     }
/* 104 */     setPeriod(-2L);
/* 105 */     notifyAll();
/* 106 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scheduler\CraftFuture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */