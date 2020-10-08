/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityInteractEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   protected Block block;
/*    */   private boolean cancelled;
/*    */   
/*    */   public EntityInteractEvent(@NotNull Entity entity, @NotNull Block block) {
/* 18 */     super(entity);
/* 19 */     this.block = block;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 24 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 29 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getBlock() {
/* 39 */     return this.block;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 45 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 50 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityInteractEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */