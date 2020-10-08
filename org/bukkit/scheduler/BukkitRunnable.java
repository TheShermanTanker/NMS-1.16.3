/*     */ package org.bukkit.scheduler;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BukkitRunnable
/*     */   implements Runnable
/*     */ {
/*     */   private BukkitTask task;
/*     */   
/*     */   public synchronized boolean isCancelled() throws IllegalStateException {
/*  20 */     checkScheduled();
/*  21 */     return this.task.isCancelled();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void cancel() throws IllegalStateException {
/*  30 */     Bukkit.getScheduler().cancelTask(getTaskId());
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
/*     */   @NotNull
/*     */   public synchronized BukkitTask runTask(@NotNull Plugin plugin) throws IllegalArgumentException, IllegalStateException {
/*  44 */     checkNotYetScheduled();
/*  45 */     return setupTask(Bukkit.getScheduler().runTask(plugin, this));
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
/*     */   @NotNull
/*     */   public synchronized BukkitTask runTaskAsynchronously(@NotNull Plugin plugin) throws IllegalArgumentException, IllegalStateException {
/*  62 */     checkNotYetScheduled();
/*  63 */     return setupTask(Bukkit.getScheduler().runTaskAsynchronously(plugin, this));
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
/*     */   @NotNull
/*     */   public synchronized BukkitTask runTaskLater(@NotNull Plugin plugin, long delay) throws IllegalArgumentException, IllegalStateException {
/*  78 */     checkNotYetScheduled();
/*  79 */     return setupTask(Bukkit.getScheduler().runTaskLater(plugin, this, delay));
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
/*     */   
/*     */   @NotNull
/*     */   public synchronized BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, long delay) throws IllegalArgumentException, IllegalStateException {
/*  98 */     checkNotYetScheduled();
/*  99 */     return setupTask(Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, this, delay));
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
/*     */   @NotNull
/*     */   public synchronized BukkitTask runTaskTimer(@NotNull Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException {
/* 116 */     checkNotYetScheduled();
/* 117 */     return setupTask(Bukkit.getScheduler().runTaskTimer(plugin, this, delay, period));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public synchronized BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException {
/* 139 */     checkNotYetScheduled();
/* 140 */     return setupTask(Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, delay, period));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getTaskId() throws IllegalStateException {
/* 150 */     checkScheduled();
/* 151 */     return this.task.getTaskId();
/*     */   }
/*     */   
/*     */   private void checkScheduled() {
/* 155 */     if (this.task == null) {
/* 156 */       throw new IllegalStateException("Not scheduled yet");
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkNotYetScheduled() {
/* 161 */     if (this.task != null) {
/* 162 */       throw new IllegalStateException("Already scheduled as " + this.task.getTaskId());
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private BukkitTask setupTask(@NotNull BukkitTask task) {
/* 168 */     this.task = task;
/* 169 */     return task;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scheduler\BukkitRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */