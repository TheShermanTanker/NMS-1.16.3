/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Firework;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerElytraBoostEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled = false;
/*    */   @NotNull
/*    */   private final ItemStack itemStack;
/*    */   
/*    */   public PlayerElytraBoostEvent(@NotNull Player player, @NotNull ItemStack itemStack, @NotNull Firework firework) {
/* 22 */     super(player);
/* 23 */     this.itemStack = itemStack;
/* 24 */     this.firework = firework;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private Firework firework;
/*    */   private boolean consume = true;
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getItemStack() {
/* 34 */     return this.itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Firework getFirework() {
/* 44 */     return this.firework;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldConsume() {
/* 53 */     return this.consume;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setShouldConsume(boolean consume) {
/* 62 */     this.consume = consume;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 68 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 73 */     return handlers;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 78 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 83 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerElytraBoostEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */