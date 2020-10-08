/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerInteractEntityEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   protected Entity clickedEntity;
/*    */   boolean cancelled = false;
/*    */   private EquipmentSlot hand;
/*    */   
/*    */   public PlayerInteractEntityEvent(@NotNull Player who, @NotNull Entity clickedEntity) {
/* 20 */     this(who, clickedEntity, EquipmentSlot.HAND);
/*    */   }
/*    */   
/*    */   public PlayerInteractEntityEvent(@NotNull Player who, @NotNull Entity clickedEntity, @NotNull EquipmentSlot hand) {
/* 24 */     super(who);
/* 25 */     this.clickedEntity = clickedEntity;
/* 26 */     this.hand = hand;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 31 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 36 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getRightClicked() {
/* 46 */     return this.clickedEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EquipmentSlot getHand() {
/* 56 */     return this.hand;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerInteractEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */