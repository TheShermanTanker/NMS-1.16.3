/*    */ package com.destroystokyo.paper;
/*    */ 
/*    */ import com.destroystokyo.paper.event.server.ServerExceptionEvent;
/*    */ import com.destroystokyo.paper.exception.ServerException;
/*    */ import com.destroystokyo.paper.exception.ServerSchedulerException;
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.scheduler.CraftTask;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.scheduler.BukkitTask;
/*    */ 
/*    */ public class ServerSchedulerReportingWrapper
/*    */   implements Runnable {
/*    */   private final CraftTask internalTask;
/*    */   
/*    */   public ServerSchedulerReportingWrapper(CraftTask internalTask) {
/* 16 */     this.internalTask = (CraftTask)Preconditions.checkNotNull(internalTask, "internalTask");
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     try {
/* 22 */       this.internalTask.run();
/* 23 */     } catch (RuntimeException e) {
/* 24 */       this.internalTask.getOwner().getServer().getPluginManager().callEvent((Event)new ServerExceptionEvent((ServerException)new ServerSchedulerException(e, (BukkitTask)this.internalTask)));
/*    */ 
/*    */       
/* 27 */       throw e;
/* 28 */     } catch (Throwable t) {
/* 29 */       this.internalTask.getOwner().getServer().getPluginManager().callEvent((Event)new ServerExceptionEvent((ServerException)new ServerSchedulerException(t, (BukkitTask)this.internalTask)));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CraftTask getInternalTask() {
/* 36 */     return this.internalTask;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\ServerSchedulerReportingWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */