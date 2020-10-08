/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Bat;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BatToggleSleepEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancel = false;
/*    */   private final boolean awake;
/*    */   
/*    */   public BatToggleSleepEvent(@NotNull Bat what, boolean awake) {
/* 22 */     super((Entity)what);
/* 23 */     this.awake = awake;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAwake() {
/* 32 */     return this.awake;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 37 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 42 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 48 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 53 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\BatToggleSleepEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */