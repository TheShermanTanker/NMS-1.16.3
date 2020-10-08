/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.scheduler.BukkitTask;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerSchedulerException
/*    */   extends ServerPluginException
/*    */ {
/*    */   private final BukkitTask task;
/*    */   
/*    */   public ServerSchedulerException(String message, Throwable cause, BukkitTask task) {
/* 15 */     super(message, cause, task.getOwner());
/* 16 */     this.task = (BukkitTask)Preconditions.checkNotNull(task, "task");
/*    */   }
/*    */   
/*    */   public ServerSchedulerException(Throwable cause, BukkitTask task) {
/* 20 */     super(cause, task.getOwner());
/* 21 */     this.task = (BukkitTask)Preconditions.checkNotNull(task, "task");
/*    */   }
/*    */   
/*    */   protected ServerSchedulerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BukkitTask task) {
/* 25 */     super(message, cause, enableSuppression, writableStackTrace, task.getOwner());
/* 26 */     this.task = (BukkitTask)Preconditions.checkNotNull(task, "task");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BukkitTask getTask() {
/* 35 */     return this.task;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerSchedulerException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */