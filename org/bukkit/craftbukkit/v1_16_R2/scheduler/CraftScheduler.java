/*     */ package org.bukkit.craftbukkit.v1_16_R2.scheduler;
/*     */ 
/*     */ import co.aikar.timings.MinecraftTimings;
/*     */ import com.destroystokyo.paper.event.server.ServerExceptionEvent;
/*     */ import com.destroystokyo.paper.exception.ServerException;
/*     */ import com.destroystokyo.paper.exception.ServerSchedulerException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.PriorityQueue;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.logging.Level;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.plugin.IllegalPluginAccessException;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ import org.bukkit.scheduler.BukkitWorker;
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
/*     */ public class CraftScheduler
/*     */   implements BukkitScheduler
/*     */ {
/*  50 */   static Plugin MINECRAFT = (Plugin)new MinecraftInternalPlugin();
/*     */ 
/*     */ 
/*     */   
/*  54 */   private final AtomicInteger ids = new AtomicInteger(1);
/*     */ 
/*     */ 
/*     */   
/*  58 */   private volatile CraftTask head = new CraftTask();
/*     */ 
/*     */ 
/*     */   
/*  62 */   private final AtomicReference<CraftTask> tail = new AtomicReference<>(this.head);
/*     */ 
/*     */ 
/*     */   
/*  66 */   final PriorityQueue<CraftTask> pending = new PriorityQueue<>(10, new Comparator<CraftTask>()
/*     */       {
/*     */         public int compare(CraftTask o1, CraftTask o2)
/*     */         {
/*  70 */           int value = Long.compare(o1.getNextRun(), o2.getNextRun());
/*     */ 
/*     */           
/*  73 */           return (value != 0) ? value : Integer.compare(o1.getTaskId(), o2.getTaskId());
/*     */         }
/*     */       });
/*     */ 
/*     */ 
/*     */   
/*  79 */   private final List<CraftTask> temp = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*  83 */   final ConcurrentHashMap<Integer, CraftTask> runners = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */   
/*  87 */   private volatile CraftTask currentTask = null;
/*     */   
/*  89 */   volatile int currentTick = -1;
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
/* 103 */   private static final int RECENT_TICKS = 30;
/*     */   
/*     */   private final CraftScheduler asyncScheduler;
/*     */   
/*     */   private final boolean isAsyncScheduler;
/*     */ 
/*     */   
/*     */   public CraftScheduler() {
/* 111 */     this(false);
/*     */   }
/*     */   
/*     */   public CraftScheduler(boolean isAsync) {
/* 115 */     this.isAsyncScheduler = isAsync;
/* 116 */     if (isAsync) {
/* 117 */       this.asyncScheduler = this;
/*     */     } else {
/* 119 */       this.asyncScheduler = new CraftAsyncScheduler();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int scheduleSyncDelayedTask(Plugin plugin, Runnable task) {
/* 125 */     return scheduleSyncDelayedTask(plugin, task, 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public BukkitTask runTask(Plugin plugin, Runnable runnable) {
/* 130 */     return runTaskLater(plugin, runnable, 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void runTask(Plugin plugin, Consumer<BukkitTask> task) throws IllegalArgumentException {
/* 135 */     runTaskLater(plugin, task, 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task) {
/* 141 */     return scheduleAsyncDelayedTask(plugin, task, 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable runnable) {
/* 146 */     return runTaskLaterAsynchronously(plugin, runnable, 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void runTaskAsynchronously(Plugin plugin, Consumer<BukkitTask> task) throws IllegalArgumentException {
/* 151 */     runTaskLaterAsynchronously(plugin, task, 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public int scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delay) {
/* 156 */     return scheduleSyncRepeatingTask(plugin, task, delay, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public BukkitTask runTaskLater(Plugin plugin, Runnable runnable, long delay) {
/* 161 */     return runTaskTimer(plugin, runnable, delay, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void runTaskLater(Plugin plugin, Consumer<BukkitTask> task, long delay) throws IllegalArgumentException {
/* 166 */     runTaskTimer(plugin, task, delay, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task, long delay) {
/* 172 */     return scheduleAsyncRepeatingTask(plugin, task, delay, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable runnable, long delay) {
/* 177 */     return runTaskTimerAsynchronously(plugin, runnable, delay, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void runTaskLaterAsynchronously(Plugin plugin, Consumer<BukkitTask> task, long delay) throws IllegalArgumentException {
/* 182 */     runTaskTimerAsynchronously(plugin, task, delay, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void runTaskTimerAsynchronously(Plugin plugin, Consumer<BukkitTask> task, long delay, long period) throws IllegalArgumentException {
/* 187 */     runTaskTimerAsynchronously(plugin, task, delay, period);
/*     */   }
/*     */ 
/*     */   
/*     */   public int scheduleSyncRepeatingTask(Plugin plugin, Runnable runnable, long delay, long period) {
/* 192 */     return runTaskTimer(plugin, runnable, delay, period).getTaskId();
/*     */   }
/*     */ 
/*     */   
/*     */   public BukkitTask runTaskTimer(Plugin plugin, Runnable runnable, long delay, long period) {
/* 197 */     return runTaskTimer(plugin, runnable, delay, period);
/*     */   }
/*     */ 
/*     */   
/*     */   public void runTaskTimer(Plugin plugin, Consumer<BukkitTask> task, long delay, long period) throws IllegalArgumentException {
/* 202 */     runTaskTimer(plugin, task, delay, period);
/*     */   }
/*     */   
/*     */   public BukkitTask scheduleInternalTask(Runnable run, int delay, String taskName) {
/* 206 */     CraftTask task = new CraftTask(run, nextId(), "Internal - " + ((taskName != null) ? taskName : "Unknown"));
/* 207 */     task.internal = true;
/* 208 */     return handle(task, delay);
/*     */   }
/*     */   
/*     */   public BukkitTask runTaskTimer(Plugin plugin, Object runnable, long delay, long period) {
/* 212 */     validate(plugin, runnable);
/* 213 */     if (delay < 0L) {
/* 214 */       delay = 0L;
/*     */     }
/* 216 */     if (period == 0L) {
/* 217 */       period = 1L;
/* 218 */     } else if (period < -1L) {
/* 219 */       period = -1L;
/*     */     } 
/* 221 */     return handle(new CraftTask(plugin, runnable, nextId(), period), delay);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int scheduleAsyncRepeatingTask(Plugin plugin, Runnable runnable, long delay, long period) {
/* 227 */     return runTaskTimerAsynchronously(plugin, runnable, delay, period).getTaskId();
/*     */   }
/*     */ 
/*     */   
/*     */   public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable runnable, long delay, long period) {
/* 232 */     return runTaskTimerAsynchronously(plugin, runnable, delay, period);
/*     */   }
/*     */   
/*     */   public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Object runnable, long delay, long period) {
/* 236 */     validate(plugin, runnable);
/* 237 */     if (delay < 0L) {
/* 238 */       delay = 0L;
/*     */     }
/* 240 */     if (period == 0L) {
/* 241 */       period = 1L;
/* 242 */     } else if (period < -1L) {
/* 243 */       period = -1L;
/*     */     } 
/* 245 */     return handle(new CraftAsyncTask(this.asyncScheduler.runners, plugin, runnable, nextId(), period), delay);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> Future<T> callSyncMethod(Plugin plugin, Callable<T> task) {
/* 250 */     validate(plugin, task);
/* 251 */     CraftFuture<T> future = new CraftFuture<>(task, plugin, nextId());
/* 252 */     handle(future, 0L);
/* 253 */     return future;
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancelTask(final int taskId) {
/* 258 */     if (taskId <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 262 */     if (!this.isAsyncScheduler) {
/* 263 */       this.asyncScheduler.cancelTask(taskId);
/*     */     }
/*     */     
/* 266 */     CraftTask task = this.runners.get(Integer.valueOf(taskId));
/* 267 */     if (task != null) {
/* 268 */       task.cancel0();
/*     */     }
/* 270 */     task = new CraftTask(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 274 */             if (!check(CraftScheduler.this.temp))
/* 275 */               check(CraftScheduler.this.pending); 
/*     */           }
/*     */           
/*     */           private boolean check(Iterable<CraftTask> collection) {
/* 279 */             Iterator<CraftTask> tasks = collection.iterator();
/* 280 */             while (tasks.hasNext()) {
/* 281 */               CraftTask task = tasks.next();
/* 282 */               if (task.getTaskId() == taskId) {
/* 283 */                 task.cancel0();
/* 284 */                 tasks.remove();
/* 285 */                 if (task.isSync()) {
/* 286 */                   CraftScheduler.this.runners.remove(Integer.valueOf(taskId));
/*     */                 }
/* 288 */                 return true;
/*     */               } 
/*     */             } 
/* 291 */             return false; }
/*     */         }) {  }
/*     */       ;
/* 294 */     handle(task, 0L);
/* 295 */     for (CraftTask taskPending = this.head.getNext(); taskPending != null; taskPending = taskPending.getNext()) {
/* 296 */       if (taskPending == task) {
/*     */         return;
/*     */       }
/* 299 */       if (taskPending.getTaskId() == taskId) {
/* 300 */         taskPending.cancel0();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancelTasks(final Plugin plugin) {
/* 307 */     Validate.notNull(plugin, "Cannot cancel tasks of null plugin");
/*     */     
/* 309 */     if (!this.isAsyncScheduler) {
/* 310 */       this.asyncScheduler.cancelTasks(plugin);
/*     */     }
/*     */     
/* 313 */     CraftTask task = new CraftTask(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 317 */             check(CraftScheduler.this.pending);
/* 318 */             check(CraftScheduler.this.temp);
/*     */           }
/*     */           void check(Iterable<CraftTask> collection) {
/* 321 */             Iterator<CraftTask> tasks = collection.iterator();
/* 322 */             while (tasks.hasNext()) {
/* 323 */               CraftTask task = tasks.next();
/* 324 */               if (task.getOwner().equals(plugin)) {
/* 325 */                 task.cancel0();
/* 326 */                 tasks.remove();
/* 327 */                 if (task.isSync())
/* 328 */                   CraftScheduler.this.runners.remove(Integer.valueOf(task.getTaskId())); 
/*     */               } 
/*     */             } 
/*     */           }
/*     */         }) {  }
/*     */       ;
/* 334 */     handle(task, 0L);
/* 335 */     for (CraftTask taskPending = this.head.getNext(); taskPending != null && 
/* 336 */       taskPending != task; taskPending = taskPending.getNext()) {
/*     */ 
/*     */       
/* 339 */       if (taskPending.getTaskId() != -1 && taskPending.getOwner().equals(plugin)) {
/* 340 */         taskPending.cancel0();
/*     */       }
/*     */     } 
/* 343 */     for (CraftTask runner : this.runners.values()) {
/* 344 */       if (runner.getOwner().equals(plugin)) {
/* 345 */         runner.cancel0();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCurrentlyRunning(int taskId) {
/* 353 */     if (!this.isAsyncScheduler && 
/* 354 */       this.asyncScheduler.isCurrentlyRunning(taskId)) {
/* 355 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 359 */     CraftTask task = this.runners.get(Integer.valueOf(taskId));
/* 360 */     if (task == null) {
/* 361 */       return false;
/*     */     }
/* 363 */     if (task.isSync()) {
/* 364 */       return (task == this.currentTask);
/*     */     }
/* 366 */     CraftAsyncTask asyncTask = (CraftAsyncTask)task;
/* 367 */     synchronized (asyncTask.getWorkers()) {
/* 368 */       return !asyncTask.getWorkers().isEmpty();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQueued(int taskId) {
/* 374 */     if (taskId <= 0) {
/* 375 */       return false;
/*     */     }
/*     */     
/* 378 */     if (!this.isAsyncScheduler && this.asyncScheduler.isQueued(taskId)) {
/* 379 */       return true;
/*     */     }
/*     */     CraftTask task;
/* 382 */     for (task = this.head.getNext(); task != null; task = task.getNext()) {
/* 383 */       if (task.getTaskId() == taskId) {
/* 384 */         return (task.getPeriod() >= -1L);
/*     */       }
/*     */     } 
/* 387 */     task = this.runners.get(Integer.valueOf(taskId));
/* 388 */     return (task != null && task.getPeriod() >= -1L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BukkitWorker> getActiveWorkers() {
/* 394 */     if (!this.isAsyncScheduler)
/*     */     {
/* 396 */       return this.asyncScheduler.getActiveWorkers();
/*     */     }
/*     */     
/* 399 */     ArrayList<BukkitWorker> workers = new ArrayList<>();
/* 400 */     for (CraftTask taskObj : this.runners.values()) {
/*     */       
/* 402 */       if (taskObj.isSync()) {
/*     */         continue;
/*     */       }
/* 405 */       CraftAsyncTask task = (CraftAsyncTask)taskObj;
/* 406 */       synchronized (task.getWorkers()) {
/*     */         
/* 408 */         workers.addAll(task.getWorkers());
/*     */       } 
/*     */     } 
/* 411 */     return workers;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BukkitTask> getPendingTasks() {
/* 416 */     ArrayList<CraftTask> truePending = new ArrayList<>();
/* 417 */     for (CraftTask task = this.head.getNext(); task != null; task = task.getNext()) {
/* 418 */       if (task.getTaskId() != -1)
/*     */       {
/* 420 */         truePending.add(task);
/*     */       }
/*     */     } 
/*     */     
/* 424 */     ArrayList<BukkitTask> pending = new ArrayList<>();
/* 425 */     for (CraftTask craftTask : this.runners.values()) {
/* 426 */       if (craftTask.getPeriod() >= -1L) {
/* 427 */         pending.add(craftTask);
/*     */       }
/*     */     } 
/*     */     
/* 431 */     for (CraftTask craftTask : truePending) {
/* 432 */       if (craftTask.getPeriod() >= -1L && !pending.contains(craftTask)) {
/* 433 */         pending.add(craftTask);
/*     */       }
/*     */     } 
/*     */     
/* 437 */     if (!this.isAsyncScheduler) {
/* 438 */       pending.addAll(this.asyncScheduler.getPendingTasks());
/*     */     }
/*     */     
/* 441 */     return pending;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mainThreadHeartbeat(int currentTick) {
/* 449 */     if (!this.isAsyncScheduler) {
/* 450 */       this.asyncScheduler.mainThreadHeartbeat(currentTick);
/*     */     }
/*     */     
/* 453 */     this.currentTick = currentTick;
/* 454 */     List<CraftTask> temp = this.temp;
/* 455 */     parsePending();
/* 456 */     while (isReady(currentTick)) {
/* 457 */       CraftTask task = this.pending.remove();
/* 458 */       if (task.getPeriod() < -1L) {
/* 459 */         if (task.isSync()) {
/* 460 */           this.runners.remove(Integer.valueOf(task.getTaskId()), task);
/*     */         }
/* 462 */         parsePending();
/*     */         continue;
/*     */       } 
/* 465 */       if (task.isSync()) {
/* 466 */         this.currentTask = task;
/*     */         try {
/* 468 */           task.run();
/* 469 */         } catch (Throwable throwable) {
/*     */           
/* 471 */           String msg = String.format("Task #%s for %s generated an exception", new Object[] {
/*     */                 
/* 473 */                 Integer.valueOf(task.getTaskId()), task
/* 474 */                 .getOwner().getDescription().getFullName() });
/* 475 */           if (task.getOwner() == MINECRAFT) {
/* 476 */             MinecraftServer.LOGGER.error(msg, throwable);
/*     */           } else {
/* 478 */             task.getOwner().getLogger().log(Level.WARNING, msg, throwable);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 483 */           task.getOwner().getServer().getPluginManager().callEvent((Event)new ServerExceptionEvent((ServerException)new ServerSchedulerException(msg, throwable, task)));
/*     */         }
/*     */         finally {
/*     */           
/* 487 */           this.currentTask = null;
/*     */         } 
/* 489 */         parsePending();
/*     */       } else {
/*     */         
/* 492 */         task.getOwner().getLogger().log(Level.SEVERE, "Unexpected Async Task in the Sync Scheduler. Report this to Paper");
/*     */       } 
/*     */ 
/*     */       
/* 496 */       long period = task.getPeriod();
/* 497 */       if (period > 0L) {
/* 498 */         task.setNextRun(currentTick + period);
/* 499 */         temp.add(task); continue;
/* 500 */       }  if (task.isSync()) {
/* 501 */         this.runners.remove(Integer.valueOf(task.getTaskId()));
/*     */       }
/*     */     } 
/* 504 */     MinecraftTimings.bukkitSchedulerFinishTimer.startTiming();
/* 505 */     this.pending.addAll(temp);
/* 506 */     temp.clear();
/* 507 */     MinecraftTimings.bukkitSchedulerFinishTimer.stopTiming();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addTask(CraftTask task) {
/* 512 */     AtomicReference<CraftTask> tail = this.tail;
/* 513 */     CraftTask tailTask = tail.get();
/* 514 */     while (!tail.compareAndSet(tailTask, task)) {
/* 515 */       tailTask = tail.get();
/*     */     }
/* 517 */     tailTask.setNext(task);
/*     */   }
/*     */ 
/*     */   
/*     */   protected CraftTask handle(CraftTask task, long delay) {
/* 522 */     if (!this.isAsyncScheduler && !task.isSync()) {
/* 523 */       this.asyncScheduler.handle(task, delay);
/* 524 */       return task;
/*     */     } 
/*     */     
/* 527 */     task.setNextRun(this.currentTick + delay);
/* 528 */     addTask(task);
/* 529 */     return task;
/*     */   }
/*     */   
/*     */   private static void validate(Plugin plugin, Object task) {
/* 533 */     Validate.notNull(plugin, "Plugin cannot be null");
/* 534 */     Validate.notNull(task, "Task cannot be null");
/* 535 */     Validate.isTrue((task instanceof Runnable || task instanceof Consumer || task instanceof Callable), "Task must be Runnable, Consumer, or Callable");
/* 536 */     if (!plugin.isEnabled()) {
/* 537 */       throw new IllegalPluginAccessException("Plugin attempted to register task while disabled");
/*     */     }
/*     */   }
/*     */   
/*     */   private int nextId() {
/* 542 */     return this.ids.incrementAndGet();
/*     */   }
/*     */   
/*     */   void parsePending() {
/* 546 */     if (!this.isAsyncScheduler) MinecraftTimings.bukkitSchedulerPendingTimer.startTiming(); 
/* 547 */     CraftTask head = this.head;
/* 548 */     CraftTask task = head.getNext();
/* 549 */     CraftTask lastTask = head;
/* 550 */     for (; task != null; task = (lastTask = task).getNext()) {
/* 551 */       if (task.getTaskId() == -1) {
/* 552 */         task.run();
/* 553 */       } else if (task.getPeriod() >= -1L) {
/* 554 */         this.pending.add(task);
/* 555 */         this.runners.put(Integer.valueOf(task.getTaskId()), task);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 560 */     for (task = head; task != lastTask; task = head) {
/* 561 */       head = task.getNext();
/* 562 */       task.setNext(null);
/*     */     } 
/* 564 */     this.head = lastTask;
/* 565 */     if (!this.isAsyncScheduler) MinecraftTimings.bukkitSchedulerPendingTimer.stopTiming(); 
/*     */   }
/*     */   
/*     */   private boolean isReady(int currentTick) {
/* 569 */     return (!this.pending.isEmpty() && ((CraftTask)this.pending.peek()).getNextRun() <= currentTick);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 575 */     return "";
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
/*     */   @Deprecated
/*     */   public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task, long delay) {
/* 588 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLater(Plugin, long)");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task) {
/* 594 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTask(Plugin)");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int scheduleSyncRepeatingTask(Plugin plugin, BukkitRunnable task, long delay, long period) {
/* 600 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimer(Plugin, long, long)");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BukkitTask runTask(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
/* 606 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTask(Plugin)");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BukkitTask runTaskAsynchronously(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
/* 612 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTaskAsynchronously(Plugin)");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BukkitTask runTaskLater(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
/* 618 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLater(Plugin, long)");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BukkitTask runTaskLaterAsynchronously(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
/* 624 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLaterAsynchronously(Plugin, long)");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BukkitTask runTaskTimer(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
/* 630 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimer(Plugin, long, long)");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BukkitTask runTaskTimerAsynchronously(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
/* 636 */     throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimerAsynchronously(Plugin, long, long)");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scheduler\CraftScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */