/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class PlayerLocaleChangeEvent
/*    */   extends PlayerEvent
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final String oldLocale;
/*    */   private final String newLocale;
/*    */   
/*    */   public PlayerLocaleChangeEvent(Player player, String oldLocale, String newLocale) {
/* 19 */     super(player);
/* 20 */     this.oldLocale = oldLocale;
/* 21 */     this.newLocale = newLocale;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getOldLocale() {
/* 30 */     return this.oldLocale;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNewLocale() {
/* 39 */     return this.newLocale;
/*    */   }
/*    */ 
/*    */   
/*    */   public HandlerList getHandlers() {
/* 44 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 48 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerLocaleChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */