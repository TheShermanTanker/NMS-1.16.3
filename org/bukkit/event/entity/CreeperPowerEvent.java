/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LightningStrike;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class CreeperPowerEvent
/*     */   extends EntityEvent
/*     */   implements Cancellable
/*     */ {
/*  16 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean canceled;
/*     */   private final PowerCause cause;
/*     */   private LightningStrike bolt;
/*     */   
/*     */   public CreeperPowerEvent(@NotNull Creeper creeper, @NotNull LightningStrike bolt, @NotNull PowerCause cause) {
/*  22 */     this(creeper, cause);
/*  23 */     this.bolt = bolt;
/*     */   }
/*     */   
/*     */   public CreeperPowerEvent(@NotNull Creeper creeper, @NotNull PowerCause cause) {
/*  27 */     super((Entity)creeper);
/*  28 */     this.cause = cause;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  33 */     return this.canceled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  38 */     this.canceled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Creeper getEntity() {
/*  44 */     return (Creeper)this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LightningStrike getLightning() {
/*  54 */     return this.bolt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PowerCause getCause() {
/*  64 */     return this.cause;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  70 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  75 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum PowerCause
/*     */   {
/*  88 */     LIGHTNING,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     SET_ON,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     SET_OFF;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\CreeperPowerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */