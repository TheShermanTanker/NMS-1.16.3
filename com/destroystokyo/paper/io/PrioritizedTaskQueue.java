/*     */ package com.destroystokyo.paper.io;
/*     */ 
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicReference;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrioritizedTaskQueue<T extends PrioritizedTaskQueue.PrioritizedTask>
/*     */ {
/*     */   public static final int COMPLETING_PRIORITY = -1;
/*     */   public static final int HIGHEST_PRIORITY = 0;
/*     */   public static final int HIGHER_PRIORITY = 1;
/*     */   public static final int HIGH_PRIORITY = 2;
/*     */   public static final int NORMAL_PRIORITY = 3;
/*     */   public static final int LOW_PRIORITY = 4;
/*     */   public static final int LOWEST_PRIORITY = 5;
/*     */   private static final int TOTAL_PRIORITIES = 6;
/*  51 */   final ConcurrentLinkedQueue<T>[] queues = (ConcurrentLinkedQueue<T>[])new ConcurrentLinkedQueue[6];
/*     */   
/*  53 */   private final AtomicBoolean shutdown = new AtomicBoolean();
/*     */   
/*     */   public PrioritizedTaskQueue() {
/*  56 */     for (int i = 0; i < 6; i++) {
/*  57 */       this.queues[i] = new ConcurrentLinkedQueue<>();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean validPriority(int priority) {
/*  65 */     return (priority >= 0 && priority < 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(T task) throws IllegalStateException {
/*  75 */     int priority = task.getPriority();
/*  76 */     if (priority != -1) {
/*  77 */       task.setQueue(this);
/*  78 */       this.queues[priority].add(task);
/*     */     } 
/*  80 */     if (this.shutdown.get())
/*     */     {
/*  82 */       throw new IllegalStateException("Queue has shutdown, refusing to execute task " + IOUtil.genericToString(task));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T poll() {
/*  91 */     for (int i = 0; i < 6; i++) {
/*  92 */       ConcurrentLinkedQueue<T> queue = this.queues[i];
/*     */       PrioritizedTask prioritizedTask;
/*  94 */       while ((prioritizedTask = (PrioritizedTask)queue.poll()) != null) {
/*  95 */         int prevPriority = prioritizedTask.tryComplete(i);
/*  96 */         if (prevPriority != -1 && prevPriority <= i)
/*     */         {
/*  98 */           return (T)prioritizedTask;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTasks() {
/* 114 */     for (int i = 0; i < 6; i++) {
/* 115 */       ConcurrentLinkedQueue<T> queue = this.queues[i];
/*     */       
/* 117 */       if (queue.peek() != null) {
/* 118 */         return true;
/*     */       }
/*     */     } 
/* 121 */     return false;
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
/*     */   public boolean shutdown() {
/* 136 */     return this.shutdown.getAndSet(false);
/*     */   }
/*     */   
/*     */   public static abstract class PrioritizedTask
/*     */   {
/* 141 */     protected final AtomicReference<PrioritizedTaskQueue> queue = new AtomicReference<>();
/*     */     
/*     */     protected final AtomicInteger priority;
/*     */     
/*     */     protected PrioritizedTask() {
/* 146 */       this(3);
/*     */     }
/*     */     
/*     */     protected PrioritizedTask(int priority) {
/* 150 */       if (!PrioritizedTaskQueue.validPriority(priority)) {
/* 151 */         throw new IllegalArgumentException("Invalid priority " + priority);
/*     */       }
/* 153 */       this.priority = new AtomicInteger(priority);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final int getPriority() {
/* 161 */       return this.priority.get();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isScheduled() {
/* 168 */       return (this.queue.get() != null);
/*     */     }
/*     */     
/*     */     final int tryComplete(int minPriority) {
/* 172 */       int curr = getPriorityVolatile(); while (true) {
/* 173 */         if (curr == -1) {
/* 174 */           return -1;
/*     */         }
/* 176 */         if (curr > minPriority)
/*     */         {
/* 178 */           return curr;
/*     */         }
/*     */         
/* 181 */         if (curr == (curr = compareAndExchangePriorityVolatile(curr, -1))) {
/* 182 */           return curr;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean cancel() {
/* 193 */       return (exchangePriorityVolatile(-1) != -1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean raisePriority(int priority) {
/* 202 */       if (!PrioritizedTaskQueue.validPriority(priority)) {
/* 203 */         throw new IllegalArgumentException("Invalid priority");
/*     */       }
/*     */       
/* 206 */       int curr = getPriorityVolatile(); while (true) {
/* 207 */         if (curr == -1) {
/* 208 */           return false;
/*     */         }
/* 210 */         if (priority >= curr) {
/* 211 */           return true;
/*     */         }
/*     */         
/* 214 */         if (curr == (curr = compareAndExchangePriorityVolatile(curr, priority))) {
/* 215 */           PrioritizedTaskQueue queue = this.queue.get();
/* 216 */           if (queue != null)
/*     */           {
/* 218 */             queue.queues[priority].add(this);
/*     */           }
/* 220 */           return true;
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
/*     */     public boolean updatePriority(int priority) {
/* 232 */       if (!PrioritizedTaskQueue.validPriority(priority)) {
/* 233 */         throw new IllegalArgumentException("Invalid priority");
/*     */       }
/*     */       
/* 236 */       int curr = getPriorityVolatile(); while (true) {
/* 237 */         if (curr == -1) {
/* 238 */           return false;
/*     */         }
/* 240 */         if (curr == priority) {
/* 241 */           return true;
/*     */         }
/*     */         
/* 244 */         if (curr == (curr = compareAndExchangePriorityVolatile(curr, priority))) {
/* 245 */           PrioritizedTaskQueue queue = this.queue.get();
/* 246 */           if (queue != null)
/*     */           {
/* 248 */             queue.queues[priority].add(this);
/*     */           }
/* 250 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     void setQueue(PrioritizedTaskQueue queue) {
/* 257 */       this.queue.set(queue);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected final int getPriorityVolatile() {
/* 263 */       return this.priority.get();
/*     */     }
/*     */     
/*     */     protected final int compareAndExchangePriorityVolatile(int expect, int update) {
/* 267 */       if (this.priority.compareAndSet(expect, update)) {
/* 268 */         return expect;
/*     */       }
/* 270 */       return this.priority.get();
/*     */     }
/*     */     
/*     */     protected final int exchangePriorityVolatile(int value) {
/* 274 */       return this.priority.getAndSet(value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\PrioritizedTaskQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */