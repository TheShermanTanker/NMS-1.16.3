/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Queues;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.locks.LockSupport;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public abstract class IAsyncTaskHandler<R extends Runnable>
/*     */   implements Mailbox<R>, Executor {
/*     */   private final String b;
/*  15 */   private static final Logger LOGGER = LogManager.getLogger();
/*  16 */   private final Queue<R> d = Queues.newConcurrentLinkedQueue();
/*     */   private int e;
/*     */   
/*     */   protected IAsyncTaskHandler(String s) {
/*  20 */     this.b = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMainThread() {
/*  28 */     return (Thread.currentThread() == getThread());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isNotMainThread() {
/*  34 */     return !isMainThread();
/*     */   }
/*     */   
/*     */   public int bh() {
/*  38 */     return this.d.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public String bi() {
/*  43 */     return this.b;
/*     */   }
/*     */   
/*     */   private CompletableFuture<Void> executeFuture(Runnable runnable) {
/*  47 */     return CompletableFuture.supplyAsync(() -> { runnable.run(); return null; }this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompletableFuture<Void> f(Runnable runnable) {
/*  54 */     if (isNotMainThread()) {
/*  55 */       return executeFuture(runnable);
/*     */     }
/*  57 */     runnable.run();
/*  58 */     return CompletableFuture.completedFuture(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void executeSync(Runnable runnable) {
/*  63 */     if (!isMainThread()) {
/*  64 */       executeFuture(runnable).join();
/*     */     } else {
/*  66 */       runnable.run();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scheduleOnMain(Runnable r0) {
/*  75 */     addTask(postToMainThread(r0));
/*     */   }
/*     */   
/*     */   public final void addTask(R r0) {
/*  79 */     a(r0);
/*     */   } public void a(R r0) {
/*  81 */     this.d.add(r0);
/*  82 */     LockSupport.unpark(getThread());
/*     */   }
/*     */   
/*     */   public void execute(Runnable runnable) {
/*  86 */     if (isNotMainThread()) {
/*  87 */       a(postToMainThread(runnable));
/*     */     } else {
/*  89 */       runnable.run();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void executeAll() {
/*  95 */     while (executeNext());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean executeNext() {
/* 102 */     Runnable runnable = (Runnable)this.d.peek();
/*     */     
/* 104 */     if (runnable == null)
/* 105 */       return false; 
/* 106 */     if (this.e == 0 && !canExecute((R)runnable)) {
/* 107 */       return false;
/*     */     }
/* 109 */     executeTask(this.d.remove());
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void awaitTasks(BooleanSupplier booleansupplier) {
/* 115 */     this.e++;
/*     */     
/*     */     try {
/* 118 */       while (!booleansupplier.getAsBoolean()) {
/* 119 */         if (!executeNext()) {
/* 120 */           bl();
/*     */         }
/*     */       } 
/*     */     } finally {
/* 124 */       this.e--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void bl() {
/* 130 */     Thread.yield();
/* 131 */     LockSupport.parkNanos("waiting for tasks", 100000L);
/*     */   }
/*     */   
/*     */   protected void executeTask(R r0) {
/*     */     try {
/* 136 */       r0.run();
/* 137 */     } catch (Exception exception) {
/* 138 */       if (exception.getCause() instanceof ThreadDeath) throw exception; 
/* 139 */       LOGGER.fatal("Error executing task on {}", bi(), exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract R postToMainThread(Runnable paramRunnable);
/*     */   
/*     */   protected abstract boolean canExecute(R paramR);
/*     */   
/*     */   protected abstract Thread getThread();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IAsyncTaskHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */