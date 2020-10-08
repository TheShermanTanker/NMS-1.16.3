/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.advancement.Advancement;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerAdvancementCriterionGrantEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   private final Advancement advancement;
/*    */   
/*    */   public PlayerAdvancementCriterionGrantEvent(@NotNull Player who, @NotNull Advancement advancement, @NotNull String criterion) {
/* 20 */     super(who);
/* 21 */     this.advancement = advancement;
/* 22 */     this.criterion = criterion;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private final String criterion;
/*    */   private boolean cancel = false;
/*    */   
/*    */   @NotNull
/*    */   public Advancement getAdvancement() {
/* 32 */     return this.advancement;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getCriterion() {
/* 42 */     return this.criterion;
/*    */   }
/*    */   
/*    */   public boolean isCancelled() {
/* 46 */     return this.cancel;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 50 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 56 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 61 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerAdvancementCriterionGrantEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */