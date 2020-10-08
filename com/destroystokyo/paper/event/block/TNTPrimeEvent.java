/*     */ package com.destroystokyo.paper.event.block;
/*     */ 
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.block.BlockEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TNTPrimeEvent
/*     */   extends BlockEvent
/*     */   implements Cancellable
/*     */ {
/*  20 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private boolean cancelled;
/*     */ 
/*     */   
/*     */   public TNTPrimeEvent(@NotNull Block theBlock, @NotNull PrimeReason reason, @Nullable Entity primerEntity) {
/*  26 */     super(theBlock);
/*  27 */     this.reason = reason;
/*  28 */     this.primerEntity = primerEntity;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private PrimeReason reason;
/*     */   @Nullable
/*     */   private Entity primerEntity;
/*     */   
/*     */   @NotNull
/*     */   public PrimeReason getReason() {
/*  38 */     return this.reason;
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
/*     */   
/*     */   @Nullable
/*     */   public Entity getPrimerEntity() {
/*  52 */     return this.primerEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  62 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  72 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public HandlerList getHandlers() {
/*  78 */     return handlers;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static HandlerList getHandlerList() {
/*  83 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum PrimeReason
/*     */   {
/*  90 */     EXPLOSION,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     FIRE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     ITEM,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     PROJECTILE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     REDSTONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\block\TNTPrimeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */