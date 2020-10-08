/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ProjectileLaunchEvent
/*    */   extends EntitySpawnEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private boolean cancelled;
/*    */   
/*    */   public ProjectileLaunchEvent(@NotNull Entity what) {
/* 15 */     super(what);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 20 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 25 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Projectile getEntity() {
/* 31 */     return (Projectile)this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\ProjectileLaunchEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */