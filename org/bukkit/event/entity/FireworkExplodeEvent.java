/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Firework;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class FireworkExplodeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   
/*    */   public FireworkExplodeEvent(@NotNull Firework what) {
/* 17 */     super((Entity)what);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 22 */     return this.cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 34 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Firework getEntity() {
/* 40 */     return (Firework)super.getEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 46 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 51 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\FireworkExplodeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */