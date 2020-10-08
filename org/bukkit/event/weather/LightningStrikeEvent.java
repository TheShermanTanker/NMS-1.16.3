/*    */ package org.bukkit.event.weather;
/*    */ 
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.LightningStrike;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class LightningStrikeEvent
/*    */   extends WeatherEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean canceled;
/*    */   private final LightningStrike bolt;
/*    */   private final Cause cause;
/*    */   
/*    */   @Deprecated
/*    */   public LightningStrikeEvent(@NotNull World world, @NotNull LightningStrike bolt) {
/* 20 */     this(world, bolt, Cause.UNKNOWN);
/*    */   }
/*    */   
/*    */   public LightningStrikeEvent(@NotNull World world, @NotNull LightningStrike bolt, @NotNull Cause cause) {
/* 24 */     super(world);
/* 25 */     this.bolt = bolt;
/* 26 */     this.cause = cause;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 31 */     return this.canceled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 36 */     this.canceled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LightningStrike getLightning() {
/* 46 */     return this.bolt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Cause getCause() {
/* 56 */     return this.cause;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 62 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 67 */     return handlers;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Cause
/*    */   {
/* 74 */     COMMAND,
/*    */ 
/*    */ 
/*    */     
/* 78 */     TRIDENT,
/*    */ 
/*    */ 
/*    */     
/* 82 */     TRAP,
/*    */ 
/*    */ 
/*    */     
/* 86 */     WEATHER,
/*    */ 
/*    */ 
/*    */     
/* 90 */     UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\weather\LightningStrikeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */