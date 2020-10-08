/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class IllegalPacketEvent extends PlayerEvent {
/*    */   @Nullable
/*    */   private final String type;
/*    */   @Nullable
/*    */   private final String ex;
/*    */   
/*    */   public IllegalPacketEvent(@NotNull Player player, @Nullable String type, @Nullable String kickMessage, @NotNull Exception e) {
/* 17 */     super(player);
/* 18 */     this.type = type;
/* 19 */     this.kickMessage = kickMessage;
/* 20 */     this.ex = e.getMessage();
/*    */   } @Nullable
/*    */   private String kickMessage; private boolean shouldKick = true;
/*    */   public boolean isShouldKick() {
/* 24 */     return this.shouldKick;
/*    */   }
/*    */   
/*    */   public void setShouldKick(boolean shouldKick) {
/* 28 */     this.shouldKick = shouldKick;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getKickMessage() {
/* 33 */     return this.kickMessage;
/*    */   }
/*    */   
/*    */   public void setKickMessage(@Nullable String kickMessage) {
/* 37 */     this.kickMessage = kickMessage;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getType() {
/* 42 */     return this.type;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getExceptionMessage() {
/* 47 */     return this.ex;
/*    */   }
/*    */   
/* 50 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 54 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 59 */     return handlers;
/*    */   }
/*    */   
/*    */   public static void process(@NotNull Player player, @Nullable String type, @Nullable String kickMessage, @NotNull Exception exception) {
/* 63 */     IllegalPacketEvent event = new IllegalPacketEvent(player, type, kickMessage, exception);
/* 64 */     event.callEvent();
/* 65 */     if (event.shouldKick) {
/* 66 */       player.kickPlayer(kickMessage);
/*    */     }
/* 68 */     Bukkit.getLogger().severe(player.getName() + "/" + type + ": " + exception.getMessage());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\IllegalPacketEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */