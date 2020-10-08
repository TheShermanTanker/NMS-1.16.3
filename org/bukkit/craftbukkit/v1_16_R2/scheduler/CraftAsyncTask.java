/*     */ package org.bukkit.craftbukkit.v1_16_R2.scheduler;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitWorker;
/*     */ 
/*     */ class CraftAsyncTask
/*     */   extends CraftTask {
/*  12 */   private final LinkedList<BukkitWorker> workers = new LinkedList<>();
/*     */   private final Map<Integer, CraftTask> runners;
/*     */   
/*     */   CraftAsyncTask(Map<Integer, CraftTask> runners, Plugin plugin, Object task, int id, long delay) {
/*  16 */     super(plugin, task, id, delay);
/*  17 */     this.runners = runners;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSync() {
/*  22 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  27 */     final Thread thread = Thread.currentThread();
/*     */     
/*  29 */     String nameBefore = thread.getName();
/*  30 */     thread.setName(nameBefore + " - " + getOwner().getName());
/*     */     try {
/*  32 */       synchronized (this.workers) {
/*  33 */         if (getPeriod() == -2L) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  38 */         this.workers.add(new BukkitWorker()
/*     */             {
/*     */               public Thread getThread()
/*     */               {
/*  42 */                 return thread;
/*     */               }
/*     */ 
/*     */               
/*     */               public int getTaskId() {
/*  47 */                 return CraftAsyncTask.this.getTaskId();
/*     */               }
/*     */ 
/*     */               
/*     */               public Plugin getOwner() {
/*  52 */                 return CraftAsyncTask.this.getOwner();
/*     */               }
/*     */             });
/*     */       } 
/*  56 */       Throwable thrown = null;
/*     */       try {
/*  58 */         super.run();
/*  59 */       } catch (Throwable t) {
/*  60 */         thrown = t;
/*  61 */         getOwner().getLogger().log(Level.WARNING, 
/*     */             
/*  63 */             String.format("Plugin %s generated an exception while executing task %s", new Object[] {
/*     */                 
/*  65 */                 getOwner().getDescription().getFullName(), 
/*  66 */                 Integer.valueOf(getTaskId())
/*     */               }), thrown);
/*     */       } finally {
/*     */         
/*  70 */         synchronized (this.workers) {
/*     */           try {
/*  72 */             Iterator<BukkitWorker> workers = this.workers.iterator();
/*  73 */             boolean removed = false;
/*  74 */             while (workers.hasNext()) {
/*  75 */               if (((BukkitWorker)workers.next()).getThread() == thread) {
/*  76 */                 workers.remove();
/*  77 */                 removed = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/*  81 */             if (!removed) {
/*  82 */               throw new IllegalStateException(
/*  83 */                   String.format("Unable to remove worker %s on task %s for %s", new Object[] {
/*     */                       
/*  85 */                       thread.getName(), 
/*  86 */                       Integer.valueOf(getTaskId()), 
/*  87 */                       getOwner().getDescription().getFullName()
/*     */                     }), thrown);
/*     */             }
/*     */           } finally {
/*  91 */             if (getPeriod() < 0L && this.workers.isEmpty())
/*     */             {
/*     */               
/*  94 */               this.runners.remove(Integer.valueOf(getTaskId())); } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } finally {
/*  99 */       thread.setName(nameBefore);
/*     */     } 
/*     */   }
/*     */   LinkedList<BukkitWorker> getWorkers() {
/* 103 */     return this.workers;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean cancel0() {
/* 108 */     synchronized (this.workers) {
/*     */       
/* 110 */       setPeriod(-2L);
/* 111 */       if (this.workers.isEmpty()) {
/* 112 */         this.runners.remove(Integer.valueOf(getTaskId()));
/*     */       }
/*     */     } 
/* 115 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scheduler\CraftAsyncTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */