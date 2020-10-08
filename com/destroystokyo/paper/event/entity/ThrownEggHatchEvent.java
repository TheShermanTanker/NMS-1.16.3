/*     */ package com.destroystokyo.paper.event.entity;
/*     */ 
/*     */ import org.bukkit.entity.Egg;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThrownEggHatchEvent
/*     */   extends Event
/*     */ {
/*  15 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final Egg egg;
/*     */   private boolean hatching;
/*     */   private EntityType hatchType;
/*     */   private byte numHatches;
/*     */   
/*     */   public ThrownEggHatchEvent(@NotNull Egg egg, boolean hatching, byte numHatches, @NotNull EntityType hatchingType) {
/*  22 */     this.egg = egg;
/*  23 */     this.hatching = hatching;
/*  24 */     this.numHatches = numHatches;
/*  25 */     this.hatchType = hatchingType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Egg getEgg() {
/*  35 */     return this.egg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHatching() {
/*  45 */     return this.hatching;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHatching(boolean hatching) {
/*  55 */     this.hatching = hatching;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EntityType getHatchingType() {
/*  65 */     return this.hatchType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHatchingType(@NotNull EntityType hatchType) {
/*  74 */     if (!hatchType.isSpawnable()) throw new IllegalArgumentException("Can't spawn that entity type from an egg!"); 
/*  75 */     this.hatchType = hatchType;
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
/*     */ 
/*     */   
/*     */   public byte getNumHatches() {
/*  90 */     return this.numHatches;
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
/*     */   public void setNumHatches(byte numHatches) {
/* 102 */     this.numHatches = numHatches;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 108 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 113 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\ThrownEggHatchEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */