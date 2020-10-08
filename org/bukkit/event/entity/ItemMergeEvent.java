/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ItemMergeEvent extends EntityEvent implements Cancellable {
/* 10 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final Item target;
/*    */   
/*    */   public ItemMergeEvent(@NotNull Item item, @NotNull Item target) {
/* 15 */     super((Entity)item);
/* 16 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 21 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 26 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Item getEntity() {
/* 32 */     return (Item)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Item getTarget() {
/* 42 */     return this.target;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\ItemMergeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */