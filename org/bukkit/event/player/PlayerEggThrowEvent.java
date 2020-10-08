/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.bukkit.entity.Egg;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class PlayerEggThrowEvent
/*     */   extends PlayerEvent
/*     */ {
/*  13 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final Egg egg;
/*     */   private boolean hatching;
/*     */   private EntityType hatchType;
/*     */   private byte numHatches;
/*     */   
/*     */   public PlayerEggThrowEvent(@NotNull Player player, @NotNull Egg egg, boolean hatching, byte numHatches, @NotNull EntityType hatchingType) {
/*  20 */     super(player);
/*  21 */     this.egg = egg;
/*  22 */     this.hatching = hatching;
/*  23 */     this.numHatches = numHatches;
/*  24 */     this.hatchType = hatchingType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Egg getEgg() {
/*  34 */     return this.egg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHatching() {
/*  44 */     return this.hatching;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHatching(boolean hatching) {
/*  54 */     this.hatching = hatching;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EntityType getHatchingType() {
/*  64 */     return this.hatchType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHatchingType(@NotNull EntityType hatchType) {
/*  73 */     if (!hatchType.isSpawnable()) throw new IllegalArgumentException("Can't spawn that entity type from an egg!"); 
/*  74 */     this.hatchType = hatchType;
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
/*  89 */     return this.numHatches;
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
/* 101 */     this.numHatches = numHatches;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 107 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 112 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerEggThrowEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */