/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class EntityRegainHealthEvent
/*     */   extends EntityEvent
/*     */   implements Cancellable
/*     */ {
/*  12 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private boolean cancelled;
/*     */   private double amount;
/*     */   private final RegainReason regainReason;
/*     */   private final boolean isFastRegen;
/*     */   
/*     */   public EntityRegainHealthEvent(@NotNull Entity entity, double amount, @NotNull RegainReason regainReason) {
/*  20 */     this(entity, amount, regainReason, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityRegainHealthEvent(@NotNull Entity entity, double amount, @NotNull RegainReason regainReason, boolean isFastRegen) {
/*  25 */     super(entity);
/*  26 */     this.amount = amount;
/*  27 */     this.regainReason = regainReason;
/*  28 */     this.isFastRegen = isFastRegen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFastRegen() {
/*  38 */     return this.isFastRegen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAmount() {
/*  48 */     return this.amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAmount(double amount) {
/*  57 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  62 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  67 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public RegainReason getRegainReason() {
/*  78 */     return this.regainReason;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  84 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  89 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum RegainReason
/*     */   {
/* 101 */     REGEN,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     SATIATED,
/*     */ 
/*     */ 
/*     */     
/* 110 */     EATING,
/*     */ 
/*     */ 
/*     */     
/* 114 */     ENDER_CRYSTAL,
/*     */ 
/*     */ 
/*     */     
/* 118 */     MAGIC,
/*     */ 
/*     */ 
/*     */     
/* 122 */     MAGIC_REGEN,
/*     */ 
/*     */ 
/*     */     
/* 126 */     WITHER_SPAWN,
/*     */ 
/*     */ 
/*     */     
/* 130 */     WITHER,
/*     */ 
/*     */ 
/*     */     
/* 134 */     CUSTOM;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityRegainHealthEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */