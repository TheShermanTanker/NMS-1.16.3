/*    */ package com.destroystokyo.paper.event.profile;
/*    */ 
/*    */ import com.destroystokyo.paper.profile.PlayerProfile;
/*    */ import com.destroystokyo.paper.profile.ProfileProperty;
/*    */ import java.util.Set;
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
/*    */ public class FillProfileEvent
/*    */   extends Event
/*    */ {
/*    */   @NotNull
/*    */   private final PlayerProfile profile;
/*    */   
/*    */   public FillProfileEvent(@NotNull PlayerProfile profile) {
/* 41 */     super(!Bukkit.isPrimaryThread());
/* 42 */     this.profile = profile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PlayerProfile getPlayerProfile() {
/* 50 */     return this.profile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Set<ProfileProperty> getProperties() {
/* 61 */     return this.profile.getProperties();
/*    */   }
/*    */   
/* 64 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 68 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 73 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\profile\FillProfileEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */