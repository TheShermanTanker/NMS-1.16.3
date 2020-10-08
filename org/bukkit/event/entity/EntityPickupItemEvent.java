/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityPickupItemEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Item item;
/*    */   private boolean cancel = false;
/*    */   private final int remaining;
/*    */   
/*    */   public EntityPickupItemEvent(@NotNull LivingEntity entity, @NotNull Item item, int remaining) {
/* 19 */     super((Entity)entity);
/* 20 */     this.item = item;
/* 21 */     this.remaining = remaining;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getEntity() {
/* 27 */     return (LivingEntity)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Item getItem() {
/* 37 */     return this.item;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRemaining() {
/* 46 */     return this.remaining;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 51 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 56 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 62 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 67 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityPickupItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */