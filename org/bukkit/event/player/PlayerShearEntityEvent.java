/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerShearEntityEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   private final Entity what;
/*    */   private final ItemStack item;
/*    */   private final EquipmentSlot hand;
/*    */   
/*    */   public PlayerShearEntityEvent(@NotNull Player who, @NotNull Entity what, @NotNull ItemStack item, @NotNull EquipmentSlot hand) {
/* 23 */     super(who);
/* 24 */     this.what = what;
/* 25 */     this.item = item;
/* 26 */     this.hand = hand;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public PlayerShearEntityEvent(@NotNull Player who, @NotNull Entity what) {
/* 31 */     this(who, what, new ItemStack(Material.SHEARS), EquipmentSlot.HAND);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 36 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 41 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getEntity() {
/* 51 */     return this.what;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getItem() {
/* 61 */     return this.item.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EquipmentSlot getHand() {
/* 71 */     return this.hand;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 77 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 82 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerShearEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */