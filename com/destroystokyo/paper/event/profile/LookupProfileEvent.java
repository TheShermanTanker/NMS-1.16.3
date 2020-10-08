/*    */ package com.destroystokyo.paper.event.profile;
/*    */ 
/*    */ import com.destroystokyo.paper.profile.PlayerProfile;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LookupProfileEvent
/*    */   extends Event
/*    */ {
/* 19 */   private static final HandlerList handlers = new HandlerList();
/*    */   @NotNull
/*    */   private final PlayerProfile profile;
/*    */   
/*    */   public LookupProfileEvent(@NotNull PlayerProfile profile) {
/* 24 */     super(!Bukkit.isPrimaryThread());
/* 25 */     this.profile = profile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PlayerProfile getPlayerProfile() {
/* 33 */     return this.profile;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 39 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 44 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\profile\LookupProfileEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */