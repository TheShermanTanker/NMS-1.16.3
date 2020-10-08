/*     */ package org.bukkit.event.block;
/*     */ 
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockIgniteEvent
/*     */   extends BlockEvent
/*     */   implements Cancellable
/*     */ {
/*  18 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final IgniteCause cause;
/*     */   private final Entity ignitingEntity;
/*     */   private final Block ignitingBlock;
/*     */   private boolean cancel;
/*     */   
/*     */   public BlockIgniteEvent(@NotNull Block theBlock, @NotNull IgniteCause cause, @Nullable Entity ignitingEntity) {
/*  25 */     this(theBlock, cause, ignitingEntity, null);
/*     */   }
/*     */   
/*     */   public BlockIgniteEvent(@NotNull Block theBlock, @NotNull IgniteCause cause, @NotNull Block ignitingBlock) {
/*  29 */     this(theBlock, cause, null, ignitingBlock);
/*     */   }
/*     */   
/*     */   public BlockIgniteEvent(@NotNull Block theBlock, @NotNull IgniteCause cause, @Nullable Entity ignitingEntity, @Nullable Block ignitingBlock) {
/*  33 */     super(theBlock);
/*  34 */     this.cause = cause;
/*  35 */     this.ignitingEntity = ignitingEntity;
/*  36 */     this.ignitingBlock = ignitingBlock;
/*  37 */     this.cancel = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  42 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  47 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public IgniteCause getCause() {
/*  57 */     return this.cause;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Player getPlayer() {
/*  67 */     if (this.ignitingEntity instanceof Player) {
/*  68 */       return (Player)this.ignitingEntity;
/*     */     }
/*     */     
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getIgnitingEntity() {
/*  81 */     return this.ignitingEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Block getIgnitingBlock() {
/*  91 */     return this.ignitingBlock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum IgniteCause
/*     */   {
/* 102 */     LAVA,
/*     */ 
/*     */ 
/*     */     
/* 106 */     FLINT_AND_STEEL,
/*     */ 
/*     */ 
/*     */     
/* 110 */     SPREAD,
/*     */ 
/*     */ 
/*     */     
/* 114 */     LIGHTNING,
/*     */ 
/*     */ 
/*     */     
/* 118 */     FIREBALL,
/*     */ 
/*     */ 
/*     */     
/* 122 */     ENDER_CRYSTAL,
/*     */ 
/*     */ 
/*     */     
/* 126 */     EXPLOSION,
/*     */ 
/*     */ 
/*     */     
/* 130 */     ARROW;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 136 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 141 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockIgniteEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */