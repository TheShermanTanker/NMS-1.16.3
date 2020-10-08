/*    */ package io.netty.util.internal;
/*    */ 
/*    */ import io.netty.util.concurrent.EventExecutor;
/*    */ import io.netty.util.concurrent.FastThreadLocal;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ThreadExecutorMap
/*    */ {
/* 29 */   private static final FastThreadLocal<EventExecutor> mappings = new FastThreadLocal();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static EventExecutor currentExecutor() {
/* 37 */     return (EventExecutor)mappings.get();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void setCurrentEventExecutor(EventExecutor executor) {
/* 44 */     mappings.set(executor);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Executor apply(final Executor executor, final EventExecutor eventExecutor) {
/* 52 */     ObjectUtil.checkNotNull(executor, "executor");
/* 53 */     ObjectUtil.checkNotNull(eventExecutor, "eventExecutor");
/* 54 */     return new Executor()
/*    */       {
/*    */         public void execute(Runnable command) {
/* 57 */           executor.execute(ThreadExecutorMap.apply(command, eventExecutor));
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Runnable apply(final Runnable command, final EventExecutor eventExecutor) {
/* 67 */     ObjectUtil.checkNotNull(command, "command");
/* 68 */     ObjectUtil.checkNotNull(eventExecutor, "eventExecutor");
/* 69 */     return new Runnable()
/*    */       {
/*    */         public void run() {
/* 72 */           ThreadExecutorMap.setCurrentEventExecutor(eventExecutor);
/*    */           try {
/* 74 */             command.run();
/*    */           } finally {
/* 76 */             ThreadExecutorMap.setCurrentEventExecutor(null);
/*    */           } 
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ThreadFactory apply(final ThreadFactory threadFactory, final EventExecutor eventExecutor) {
/* 87 */     ObjectUtil.checkNotNull(threadFactory, "command");
/* 88 */     ObjectUtil.checkNotNull(eventExecutor, "eventExecutor");
/* 89 */     return new ThreadFactory()
/*    */       {
/*    */         public Thread newThread(Runnable r) {
/* 92 */           return threadFactory.newThread(ThreadExecutorMap.apply(r, eventExecutor));
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\internal\ThreadExecutorMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */