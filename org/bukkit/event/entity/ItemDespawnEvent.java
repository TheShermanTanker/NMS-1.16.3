/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemDespawnEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean canceled;
/*    */   private final Location location;
/*    */   
/*    */   public ItemDespawnEvent(@NotNull Item despawnee, @NotNull Location loc) {
/* 22 */     super((Entity)despawnee);
/* 23 */     this.location = loc;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 28 */     return this.canceled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 33 */     this.canceled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Item getEntity() {
/* 39 */     return (Item)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getLocation() {
/* 49 */     return this.location;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 55 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 60 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\ItemDespawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */