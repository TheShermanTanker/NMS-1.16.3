/*    */ package com.destroystokyo.paper.event.brigadier;
/*    */ 
/*    */ import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
/*    */ import com.mojang.brigadier.tree.RootCommandNode;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
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
/*    */ public class AsyncPlayerSendCommandsEvent<S extends BukkitBrigadierCommandSource>
/*    */   extends PlayerEvent
/*    */ {
/* 32 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final RootCommandNode<S> node;
/*    */   private final boolean hasFiredAsync;
/*    */   
/*    */   public AsyncPlayerSendCommandsEvent(Player player, RootCommandNode<S> node, boolean hasFiredAsync) {
/* 37 */     super(player, !Bukkit.isPrimaryThread());
/* 38 */     this.node = node;
/* 39 */     this.hasFiredAsync = hasFiredAsync;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RootCommandNode<S> getCommandNode() {
/* 46 */     return this.node;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasFiredAsync() {
/* 53 */     return this.hasFiredAsync;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 58 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 63 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\brigadier\AsyncPlayerSendCommandsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */