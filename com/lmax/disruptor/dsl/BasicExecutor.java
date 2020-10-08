/*    */ package com.lmax.disruptor.dsl;
/*    */ 
/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.lang.management.ThreadInfo;
/*    */ import java.lang.management.ThreadMXBean;
/*    */ import java.util.Queue;
/*    */ import java.util.concurrent.ConcurrentLinkedQueue;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ 
/*    */ public class BasicExecutor
/*    */   implements Executor {
/*    */   private final ThreadFactory factory;
/* 14 */   private final Queue<Thread> threads = new ConcurrentLinkedQueue<>();
/*    */ 
/*    */   
/*    */   public BasicExecutor(ThreadFactory factory) {
/* 18 */     this.factory = factory;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(Runnable command) {
/* 24 */     Thread thread = this.factory.newThread(command);
/* 25 */     if (null == thread)
/*    */     {
/* 27 */       throw new RuntimeException("Failed to create thread to run: " + command);
/*    */     }
/*    */     
/* 30 */     thread.start();
/*    */     
/* 32 */     this.threads.add(thread);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     return "BasicExecutor{threads=" + 
/* 39 */       dumpThreadInfo() + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private String dumpThreadInfo() {
/* 45 */     StringBuilder sb = new StringBuilder();
/*    */     
/* 47 */     ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
/*    */     
/* 49 */     for (Thread t : this.threads) {
/*    */       
/* 51 */       ThreadInfo threadInfo = threadMXBean.getThreadInfo(t.getId());
/* 52 */       sb.append("{");
/* 53 */       sb.append("name=").append(t.getName()).append(",");
/* 54 */       sb.append("id=").append(t.getId()).append(",");
/* 55 */       sb.append("state=").append(threadInfo.getThreadState()).append(",");
/* 56 */       sb.append("lockInfo=").append(threadInfo.getLockInfo());
/* 57 */       sb.append("}");
/*    */     } 
/*    */     
/* 60 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\dsl\BasicExecutor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */