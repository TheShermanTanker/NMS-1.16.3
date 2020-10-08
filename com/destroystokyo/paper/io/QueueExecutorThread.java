/*     */ package com.destroystokyo.paper.io;
/*     */ 
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.locks.LockSupport;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class QueueExecutorThread<T extends PrioritizedTaskQueue.PrioritizedTask & Runnable>
/*     */   extends Thread
/*     */ {
/*  12 */   private static final Logger LOGGER = MinecraftServer.LOGGER;
/*     */   
/*     */   protected final PrioritizedTaskQueue<T> queue;
/*     */   
/*     */   protected final long spinWaitTime;
/*     */   
/*     */   protected volatile boolean closed;
/*  19 */   protected final AtomicBoolean parked = new AtomicBoolean();
/*     */   
/*  21 */   protected volatile ConcurrentLinkedQueue<Thread> flushQueue = new ConcurrentLinkedQueue<>();
/*     */   protected volatile long flushCycles;
/*     */   
/*     */   public QueueExecutorThread(PrioritizedTaskQueue<T> queue) {
/*  25 */     this(queue, 1000000L);
/*     */   }
/*     */   
/*     */   public QueueExecutorThread(PrioritizedTaskQueue<T> queue, long spinWaitTime) {
/*  29 */     this.queue = queue;
/*  30 */     this.spinWaitTime = spinWaitTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  35 */     long spinWaitTime = this.spinWaitTime;
/*     */     
/*     */     label25: while (true) {
/*  38 */       pollTasks(true);
/*     */ 
/*     */ 
/*     */       
/*  42 */       long start = System.nanoTime();
/*     */ 
/*     */       
/*     */       do {
/*  46 */         Thread.interrupted();
/*  47 */         LockSupport.parkNanos("Spinwaiting on tasks", 1000L);
/*     */         
/*  49 */         if (pollTasks(true)) {
/*     */           continue label25;
/*     */         }
/*     */ 
/*     */         
/*  54 */         if (handleClose()) {
/*     */           return;
/*     */         }
/*     */       }
/*  58 */       while (System.nanoTime() - start < spinWaitTime);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  63 */       if (handleClose()) {
/*     */         return;
/*     */       }
/*     */       
/*  67 */       this.parked.set(true);
/*     */ 
/*     */ 
/*     */       
/*  71 */       if (pollTasks(true)) {
/*  72 */         this.parked.set(false);
/*     */         
/*     */         continue;
/*     */       } 
/*  76 */       if (handleClose()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/*  83 */         Thread.interrupted();
/*  84 */         LockSupport.park("Waiting on tasks");
/*  85 */         if (!this.parked.get())
/*     */           continue label25; 
/*     */       } 
/*     */       break;
/*     */     }  } protected boolean handleClose() {
/*  90 */     if (this.closed) {
/*  91 */       pollTasks(true);
/*  92 */       handleFlushThreads(true);
/*  93 */       return true;
/*     */     } 
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean pollTasks(boolean flushTasks) {
/* 100 */     boolean ret = false;
/*     */     Runnable task;
/* 102 */     while ((task = (Runnable)this.queue.poll()) != null) {
/* 103 */       ret = true;
/*     */       try {
/* 105 */         task.run();
/* 106 */       } catch (Throwable throwable) {
/* 107 */         if (throwable instanceof ThreadDeath) {
/* 108 */           throw (ThreadDeath)throwable;
/*     */         }
/* 110 */         LOGGER.fatal("Exception thrown from prioritized runnable task in thread '" + getName() + "': " + IOUtil.genericToString(task), throwable);
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     if (flushTasks) {
/* 115 */       handleFlushThreads(false);
/*     */     }
/*     */     
/* 118 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handleFlushThreads(boolean shutdown) {
/* 123 */     ConcurrentLinkedQueue<Thread> flushQueue = this.flushQueue;
/*     */     do {
/* 125 */       this.flushCycles++; Thread parking;
/* 126 */       while ((parking = flushQueue.poll()) != null) {
/* 127 */         LockSupport.unpark(parking);
/*     */       }
/* 129 */     } while (pollTasks(false));
/*     */     
/* 131 */     if (shutdown) {
/* 132 */       this.flushQueue = null;
/*     */       
/*     */       Thread thread;
/* 135 */       while ((thread = flushQueue.poll()) != null) {
/* 136 */         LockSupport.unpark(thread);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean notifyTasks() {
/* 146 */     if (this.parked.get() && this.parked.getAndSet(false)) {
/* 147 */       LockSupport.unpark(this);
/* 148 */       return true;
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   protected void queueTask(T task) {
/* 154 */     this.queue.add(task);
/* 155 */     notifyTasks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/* 164 */     Thread currentThread = Thread.currentThread();
/*     */     
/* 166 */     if (currentThread == this)
/*     */     {
/* 168 */       throw new IllegalStateException("Cannot flush the queue executor thread while on the queue executor thread");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 173 */     int successes = 0;
/* 174 */     long lastCycle = -1L;
/*     */     
/*     */     do {
/* 177 */       ConcurrentLinkedQueue<Thread> flushQueue = this.flushQueue;
/* 178 */       if (flushQueue == null) {
/*     */         return;
/*     */       }
/*     */       
/* 182 */       flushQueue.add(currentThread);
/*     */ 
/*     */       
/* 185 */       if (this.flushQueue == null) {
/*     */         return;
/*     */       }
/*     */       
/* 189 */       long currentCycle = this.flushCycles;
/*     */       
/* 191 */       if (currentCycle == lastCycle) {
/* 192 */         Thread.yield();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 197 */         this.parked.set(false);
/* 198 */         LockSupport.unpark(this);
/*     */         
/* 200 */         LockSupport.park("flushing queue executor thread");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 206 */         if (this.queue.hasTasks()) {
/* 207 */           successes = 0;
/*     */         } else {
/* 209 */           successes++;
/*     */         } 
/*     */       } 
/* 212 */     } while (successes != 2);
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
/*     */   
/*     */   public boolean close(boolean wait, boolean killQueue) {
/* 229 */     boolean ret = !killQueue ? false : this.queue.shutdown();
/* 230 */     this.closed = true;
/*     */ 
/*     */     
/* 233 */     this.parked.set(false);
/* 234 */     LockSupport.unpark(this);
/*     */     
/* 236 */     if (wait) {
/* 237 */       flush();
/*     */     }
/* 239 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\QueueExecutorThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */