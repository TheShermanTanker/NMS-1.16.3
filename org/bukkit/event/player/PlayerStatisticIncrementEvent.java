/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Statistic;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerStatisticIncrementEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  20 */   private static final HandlerList handlers = new HandlerList();
/*     */   protected final Statistic statistic;
/*     */   private final int initialValue;
/*     */   private final int newValue;
/*     */   private boolean isCancelled = false;
/*     */   private final EntityType entityType;
/*     */   private final Material material;
/*     */   
/*     */   public PlayerStatisticIncrementEvent(@NotNull Player player, @NotNull Statistic statistic, int initialValue, int newValue) {
/*  29 */     super(player);
/*  30 */     this.statistic = statistic;
/*  31 */     this.initialValue = initialValue;
/*  32 */     this.newValue = newValue;
/*  33 */     this.entityType = null;
/*  34 */     this.material = null;
/*     */   }
/*     */   
/*     */   public PlayerStatisticIncrementEvent(@NotNull Player player, @NotNull Statistic statistic, int initialValue, int newValue, @NotNull EntityType entityType) {
/*  38 */     super(player);
/*  39 */     this.statistic = statistic;
/*  40 */     this.initialValue = initialValue;
/*  41 */     this.newValue = newValue;
/*  42 */     this.entityType = entityType;
/*  43 */     this.material = null;
/*     */   }
/*     */   
/*     */   public PlayerStatisticIncrementEvent(@NotNull Player player, @NotNull Statistic statistic, int initialValue, int newValue, @NotNull Material material) {
/*  47 */     super(player);
/*  48 */     this.statistic = statistic;
/*  49 */     this.initialValue = initialValue;
/*  50 */     this.newValue = newValue;
/*  51 */     this.entityType = null;
/*  52 */     this.material = material;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Statistic getStatistic() {
/*  62 */     return this.statistic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreviousValue() {
/*  71 */     return this.initialValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNewValue() {
/*  80 */     return this.newValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityType getEntityType() {
/*  91 */     return this.entityType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Material getMaterial() {
/* 102 */     return this.material;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 107 */     return this.isCancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 112 */     this.isCancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 118 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 123 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerStatisticIncrementEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */