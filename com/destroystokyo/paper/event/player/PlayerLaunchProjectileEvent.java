/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerLaunchProjectileEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   @NotNull
/*    */   private final Projectile projectile;
/*    */   @NotNull
/*    */   private final ItemStack itemStack;
/*    */   
/*    */   public PlayerLaunchProjectileEvent(@NotNull Player shooter, @NotNull ItemStack itemStack, @NotNull Projectile projectile) {
/* 22 */     super(shooter);
/* 23 */     this.itemStack = itemStack;
/* 24 */     this.projectile = projectile;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean consumeItem = true;
/*    */   
/*    */   private boolean cancelled;
/*    */   
/*    */   @NotNull
/*    */   public Projectile getProjectile() {
/* 34 */     return this.projectile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getItemStack() {
/* 44 */     return this.itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldConsume() {
/* 53 */     return this.consumeItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setShouldConsume(boolean consumeItem) {
/* 62 */     this.consumeItem = consumeItem;
/*    */   }
/*    */   
/*    */   public boolean isCancelled() {
/* 66 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 70 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 76 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 81 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerLaunchProjectileEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */