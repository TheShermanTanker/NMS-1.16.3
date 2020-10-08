/*     */ package org.bukkit.craftbukkit.v1_16_R2.scheduler;
/*     */ 
/*     */ import co.aikar.timings.MinecraftTimings;
/*     */ import co.aikar.timings.NullTimingHandler;
/*     */ import co.aikar.timings.Timing;
/*     */ import java.util.function.Consumer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ 
/*     */ 
/*     */ public class CraftTask
/*     */   implements BukkitTask, Runnable
/*     */ {
/*  15 */   private volatile CraftTask next = null;
/*     */   
/*     */   public static final int ERROR = 0;
/*     */   
/*     */   public static final int NO_REPEATING = -1;
/*     */   
/*     */   public static final int CANCEL = -2;
/*     */   
/*     */   public static final int PROCESS_FOR_FUTURE = -3;
/*     */   
/*     */   public static final int DONE_FOR_FUTURE = -4;
/*     */   
/*     */   private volatile long period;
/*     */   
/*     */   private long nextRun;
/*     */   
/*     */   public final Runnable rTask;
/*     */   public final Consumer<BukkitTask> cTask;
/*     */   public Timing timings;
/*     */   private final Plugin plugin;
/*     */   private final int id;
/*     */   
/*     */   CraftTask() {
/*  38 */     this(null, null, -1, -1L);
/*     */   }
/*     */   
/*     */   CraftTask(Object task) {
/*  42 */     this(null, task, -1, -1L);
/*     */   }
/*     */   
/*  45 */   public String taskName = null; boolean internal = false;
/*     */   
/*     */   CraftTask(Object task, int id, String taskName) {
/*  48 */     this.rTask = (Runnable)task;
/*  49 */     this.cTask = null;
/*  50 */     this.plugin = CraftScheduler.MINECRAFT;
/*  51 */     this.taskName = taskName;
/*  52 */     this.internal = true;
/*  53 */     this.id = id;
/*  54 */     this.period = -1L;
/*  55 */     this.taskName = taskName;
/*  56 */     this.timings = MinecraftTimings.getInternalTaskName(taskName);
/*     */   }
/*     */ 
/*     */   
/*     */   CraftTask(Plugin plugin, Object task, int id, long period) {
/*  61 */     this.plugin = plugin;
/*  62 */     if (task instanceof Runnable) {
/*  63 */       this.rTask = (Runnable)task;
/*  64 */       this.cTask = null;
/*  65 */     } else if (task instanceof Consumer) {
/*  66 */       this.cTask = (Consumer<BukkitTask>)task;
/*  67 */       this.rTask = null;
/*  68 */     } else if (task == null) {
/*     */       
/*  70 */       this.rTask = null;
/*  71 */       this.cTask = null;
/*     */     } else {
/*  73 */       throw new AssertionError("Illegal task class " + task);
/*     */     } 
/*  75 */     this.id = id;
/*  76 */     this.period = period;
/*  77 */     this.timings = (task != null) ? MinecraftTimings.getPluginTaskTimings(this, period) : NullTimingHandler.NULL;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getTaskId() {
/*  82 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public final Plugin getOwner() {
/*  87 */     return this.plugin;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSync() {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  97 */     Timing ignored = this.timings.startTiming(); 
/*  98 */     try { if (this.rTask != null) {
/*  99 */         this.rTask.run();
/*     */       } else {
/* 101 */         this.cTask.accept(this);
/*     */       } 
/* 103 */       if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null)
/*     */         try { ignored.close(); }
/*     */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */           throw throwable; }
/* 107 */      } long getPeriod() { return this.period; }
/*     */ 
/*     */   
/*     */   void setPeriod(long period) {
/* 111 */     this.period = period;
/*     */   }
/*     */   
/*     */   long getNextRun() {
/* 115 */     return this.nextRun;
/*     */   }
/*     */   
/*     */   void setNextRun(long nextRun) {
/* 119 */     this.nextRun = nextRun;
/*     */   }
/*     */   
/*     */   CraftTask getNext() {
/* 123 */     return this.next;
/*     */   }
/*     */   
/*     */   void setNext(CraftTask next) {
/* 127 */     this.next = next;
/*     */   }
/*     */   
/*     */   public Class<?> getTaskClass() {
/* 131 */     return (this.rTask != null) ? this.rTask.getClass() : ((this.cTask != null) ? this.cTask.getClass() : null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 136 */     return (this.period == -2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancel() {
/* 141 */     Bukkit.getScheduler().cancelTask(this.id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean cancel0() {
/* 150 */     setPeriod(-2L);
/* 151 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scheduler\CraftTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */