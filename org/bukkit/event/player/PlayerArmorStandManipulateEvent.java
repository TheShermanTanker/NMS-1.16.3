/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerArmorStandManipulateEvent
/*    */   extends PlayerInteractEntityEvent
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final ItemStack playerItem;
/*    */   private final ItemStack armorStandItem;
/*    */   private final EquipmentSlot slot;
/*    */   
/*    */   public PlayerArmorStandManipulateEvent(@NotNull Player who, @NotNull ArmorStand clickedEntity, @NotNull ItemStack playerItem, @NotNull ItemStack armorStandItem, @NotNull EquipmentSlot slot) {
/* 22 */     super(who, (Entity)clickedEntity);
/* 23 */     this.playerItem = playerItem;
/* 24 */     this.armorStandItem = armorStandItem;
/* 25 */     this.slot = slot;
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
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getPlayerItem() {
/* 39 */     return this.playerItem;
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
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getArmorStandItem() {
/* 53 */     return this.armorStandItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EquipmentSlot getSlot() {
/* 63 */     return this.slot;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArmorStand getRightClicked() {
/* 69 */     return (ArmorStand)this.clickedEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 75 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 80 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerArmorStandManipulateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */