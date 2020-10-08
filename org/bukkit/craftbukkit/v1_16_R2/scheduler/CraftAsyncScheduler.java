/*     */ package org.bukkit.craftbukkit.v1_16_R2.scheduler;
/*     */ 
/*     */ import com.destroystokyo.paper.ServerSchedulerReportingWrapper;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.SynchronousQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.bukkit.plugin.Plugin;
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
/*     */ public class CraftAsyncScheduler
/*     */   extends CraftScheduler
/*     */ {
/*  41 */   private final ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 2147483647, 30L, TimeUnit.SECONDS, new SynchronousQueue<>(), (new ThreadFactoryBuilder())
/*     */       
/*  43 */       .setNameFormat("Craft Scheduler Thread - %1$d").build());
/*  44 */   private final Executor management = Executors.newSingleThreadExecutor((new ThreadFactoryBuilder())
/*  45 */       .setNameFormat("Craft Async Scheduler Management Thread").build());
/*  46 */   private final List<CraftTask> temp = new ArrayList<>();
/*     */   
/*     */   CraftAsyncScheduler() {
/*  49 */     super(true);
/*  50 */     this.executor.allowCoreThreadTimeOut(true);
/*  51 */     this.executor.prestartAllCoreThreads();
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancelTask(int taskId) {
/*  56 */     this.management.execute(() -> removeTask(taskId));
/*     */   }
/*     */   
/*     */   private synchronized void removeTask(int taskId) {
/*  60 */     parsePending();
/*  61 */     this.pending.removeIf(task -> {
/*     */           if (task.getTaskId() == taskId) {
/*     */             task.cancel0();
/*     */             return true;
/*     */           } 
/*     */           return false;
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void mainThreadHeartbeat(int currentTick) {
/*  72 */     this.currentTick = currentTick;
/*  73 */     this.management.execute(() -> runTasks(currentTick));
/*     */   }
/*     */   
/*     */   private synchronized void runTasks(int currentTick) {
/*  77 */     parsePending();
/*  78 */     while (!this.pending.isEmpty() && ((CraftTask)this.pending.peek()).getNextRun() <= currentTick) {
/*  79 */       CraftTask task = this.pending.remove();
/*  80 */       if (executeTask(task)) {
/*  81 */         long period = task.getPeriod();
/*  82 */         if (period > 0L) {
/*  83 */           task.setNextRun(currentTick + period);
/*  84 */           this.temp.add(task);
/*     */         } 
/*     */       } 
/*  87 */       parsePending();
/*     */     } 
/*  89 */     this.pending.addAll(this.temp);
/*  90 */     this.temp.clear();
/*     */   }
/*     */   
/*     */   private boolean executeTask(CraftTask task) {
/*  94 */     if (isValid(task)) {
/*  95 */       this.runners.put(Integer.valueOf(task.getTaskId()), task);
/*  96 */       this.executor.execute((Runnable)new ServerSchedulerReportingWrapper(task));
/*  97 */       return true;
/*     */     } 
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void cancelTasks(Plugin plugin) {
/* 104 */     parsePending();
/* 105 */     for (Iterator<CraftTask> iterator = this.pending.iterator(); iterator.hasNext(); ) {
/* 106 */       CraftTask task = iterator.next();
/* 107 */       if (task.getTaskId() != -1 && (plugin == null || task.getOwner().equals(plugin))) {
/* 108 */         task.cancel0();
/* 109 */         iterator.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean isValid(CraftTask runningTask) {
/* 120 */     return (runningTask.getPeriod() >= -1L);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scheduler\CraftAsyncScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */