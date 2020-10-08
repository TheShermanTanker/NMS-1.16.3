/*     */ package org.bukkit.event.block;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CauldronLevelChangeEvent
/*     */   extends BlockEvent implements Cancellable {
/*  13 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private boolean cancelled;
/*     */   private final Entity entity;
/*     */   private final ChangeReason reason;
/*     */   private final int oldLevel;
/*     */   private int newLevel;
/*     */   
/*     */   public CauldronLevelChangeEvent(@NotNull Block block, @Nullable Entity entity, @NotNull ChangeReason reason, int oldLevel, int newLevel) {
/*  22 */     super(block);
/*  23 */     this.entity = entity;
/*  24 */     this.reason = reason;
/*  25 */     this.oldLevel = oldLevel;
/*  26 */     this.newLevel = newLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getEntity() {
/*  36 */     return this.entity;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ChangeReason getReason() {
/*  41 */     return this.reason;
/*     */   }
/*     */   
/*     */   public int getOldLevel() {
/*  45 */     return this.oldLevel;
/*     */   }
/*     */   
/*     */   public int getNewLevel() {
/*  49 */     return this.newLevel;
/*     */   }
/*     */   
/*     */   public void setNewLevel(int newLevel) {
/*  53 */     Preconditions.checkArgument((0 <= newLevel && newLevel <= 3), "Cauldron level out of bounds 0 <= %s <= 3", newLevel);
/*  54 */     this.newLevel = newLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  59 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancelled) {
/*  64 */     this.cancelled = cancelled;
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
/*     */   public enum ChangeReason
/*     */   {
/*  82 */     BUCKET_FILL,
/*     */ 
/*     */ 
/*     */     
/*  86 */     BUCKET_EMPTY,
/*     */ 
/*     */ 
/*     */     
/*  90 */     BOTTLE_FILL,
/*     */ 
/*     */ 
/*     */     
/*  94 */     BOTTLE_EMPTY,
/*     */ 
/*     */ 
/*     */     
/*  98 */     BANNER_WASH,
/*     */ 
/*     */ 
/*     */     
/* 102 */     ARMOR_WASH,
/*     */ 
/*     */ 
/*     */     
/* 106 */     EXTINGUISH,
/*     */ 
/*     */ 
/*     */     
/* 110 */     EVAPORATE,
/*     */ 
/*     */ 
/*     */     
/* 114 */     UNKNOWN;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\CauldronLevelChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */