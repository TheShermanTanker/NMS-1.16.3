/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerRecipeBookClickEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancel = false;
/*    */ 
/*    */   
/*    */   public PlayerRecipeBookClickEvent(@NotNull Player player, @NotNull NamespacedKey recipe, boolean makeAll) {
/* 20 */     super(player);
/* 21 */     this.recipe = recipe;
/* 22 */     this.makeAll = makeAll;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private NamespacedKey recipe;
/*    */   private boolean makeAll;
/*    */   
/*    */   @NotNull
/*    */   public NamespacedKey getRecipe() {
/* 32 */     return this.recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRecipe(@NotNull NamespacedKey recipe) {
/* 41 */     this.recipe = recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isMakeAll() {
/* 51 */     return this.makeAll;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMakeAll(boolean makeAll) {
/* 61 */     this.makeAll = makeAll;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 66 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 71 */     this.cancel = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerRecipeBookClickEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */