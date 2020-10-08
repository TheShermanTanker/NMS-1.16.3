/*    */ package com.destroystokyo.paper.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.block.BlockEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class BeaconEffectEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private PotionEffect effect;
/*    */   private Player player;
/*    */   private boolean primary;
/*    */   
/*    */   public BeaconEffectEvent(@NotNull Block block, @NotNull PotionEffect effect, @NotNull Player player, boolean primary) {
/* 22 */     super(block);
/* 23 */     this.effect = effect;
/* 24 */     this.player = player;
/* 25 */     this.primary = primary;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 30 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 35 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PotionEffect getEffect() {
/* 45 */     return this.effect;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEffect(@NotNull PotionEffect effect) {
/* 54 */     this.effect = effect;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 64 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isPrimary() {
/* 73 */     return this.primary;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 79 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 84 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\block\BeaconEffectEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */