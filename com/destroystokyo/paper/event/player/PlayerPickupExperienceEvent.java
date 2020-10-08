/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.entity.ExperienceOrb;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerPickupExperienceEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/*    */   @NotNull
/*    */   private final ExperienceOrb experienceOrb;
/*    */   
/*    */   @NotNull
/*    */   public ExperienceOrb getExperienceOrb() {
/*    */     return this.experienceOrb;
/*    */   }
/*    */   
/*    */   public PlayerPickupExperienceEvent(@NotNull Player player, @NotNull ExperienceOrb experienceOrb) {
/* 41 */     super(player);
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
/*    */ 
/*    */ 
/*    */     
/* 65 */     this.cancelled = false;
/*    */     this.experienceOrb = experienceOrb;
/*    */   }
/*    */   public boolean isCancelled() {
/* 69 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 78 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/*    */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/*    */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerPickupExperienceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */