/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerUseUnknownEntityEvent
/*    */   extends PlayerEvent {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final int entityId;
/*    */ 
/*    */   
/*    */   public PlayerUseUnknownEntityEvent(@NotNull Player who, int entityId, boolean attack, @NotNull EquipmentSlot hand) {
/* 17 */     super(who);
/* 18 */     this.entityId = entityId;
/* 19 */     this.attack = attack;
/* 20 */     this.hand = hand;
/*    */   } private final boolean attack; @NotNull
/*    */   private final EquipmentSlot hand;
/*    */   public int getEntityId() {
/* 24 */     return this.entityId;
/*    */   }
/*    */   
/*    */   public boolean isAttack() {
/* 28 */     return this.attack;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public EquipmentSlot getHand() {
/* 33 */     return this.hand;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 39 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 44 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerUseUnknownEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */