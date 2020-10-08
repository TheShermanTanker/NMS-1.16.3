/*    */ package com.destroystokyo.paper.event.profile;
/*    */ 
/*    */ import com.destroystokyo.paper.profile.PlayerProfile;
/*    */ import com.destroystokyo.paper.profile.ProfileProperty;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ public class PreFillProfileEvent
/*    */   extends Event
/*    */ {
/*    */   @NotNull
/*    */   private final PlayerProfile profile;
/*    */   
/*    */   public PreFillProfileEvent(@NotNull PlayerProfile profile) {
/* 43 */     super(!Bukkit.isPrimaryThread());
/* 44 */     this.profile = profile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PlayerProfile getPlayerProfile() {
/* 52 */     return this.profile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setProperties(@NotNull Collection<ProfileProperty> properties) {
/* 63 */     this.profile.setProperties(properties);
/*    */   }
/*    */   
/* 66 */   private static final HandlerList handlers = new HandlerList();
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\profile\PreFillProfileEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */