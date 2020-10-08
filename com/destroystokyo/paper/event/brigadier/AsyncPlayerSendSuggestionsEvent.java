/*    */ package com.destroystokyo.paper.event.brigadier;
/*    */ 
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AsyncPlayerSendSuggestionsEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled = false;
/*    */   private Suggestions suggestions;
/*    */   private final String buffer;
/*    */   
/*    */   public AsyncPlayerSendSuggestionsEvent(Player player, Suggestions suggestions, String buffer) {
/* 25 */     super(player, !Bukkit.isPrimaryThread());
/* 26 */     this.suggestions = suggestions;
/* 27 */     this.buffer = buffer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBuffer() {
/* 34 */     return this.buffer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Suggestions getSuggestions() {
/* 41 */     return this.suggestions;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSuggestions(Suggestions suggestions) {
/* 48 */     this.suggestions = suggestions;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 56 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 65 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 70 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 75 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\brigadier\AsyncPlayerSendSuggestionsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */