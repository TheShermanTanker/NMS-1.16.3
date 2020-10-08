/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerItemDamageEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final ItemStack item;
/*    */   private int damage;
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public PlayerItemDamageEvent(@NotNull Player player, @NotNull ItemStack what, int damage) {
/* 21 */     super(player);
/* 22 */     this.item = what;
/* 23 */     this.damage = damage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getItem() {
/* 33 */     return this.item;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDamage() {
/* 42 */     return this.damage;
/*    */   }
/*    */   
/*    */   public void setDamage(int damage) {
/* 46 */     this.damage = damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 51 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 56 */     this.cancelled = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerItemDamageEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */