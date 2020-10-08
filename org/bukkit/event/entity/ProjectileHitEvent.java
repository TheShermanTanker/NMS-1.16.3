/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class ProjectileHitEvent
/*    */   extends EntityEvent
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Entity hitEntity;
/*    */   private final Block hitBlock;
/*    */   private final BlockFace hitFace;
/*    */   
/*    */   public ProjectileHitEvent(@NotNull Projectile projectile) {
/* 21 */     this(projectile, null, null);
/*    */   }
/*    */   
/*    */   public ProjectileHitEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity) {
/* 25 */     this(projectile, hitEntity, null);
/*    */   }
/*    */   
/*    */   public ProjectileHitEvent(@NotNull Projectile projectile, @Nullable Block hitBlock) {
/* 29 */     this(projectile, null, hitBlock);
/*    */   }
/*    */   
/*    */   public ProjectileHitEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity, @Nullable Block hitBlock) {
/* 33 */     this(projectile, hitEntity, hitBlock, null);
/*    */   }
/*    */   
/*    */   public ProjectileHitEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity, @Nullable Block hitBlock, @Nullable BlockFace hitFace) {
/* 37 */     super((Entity)projectile);
/* 38 */     this.hitEntity = hitEntity;
/* 39 */     this.hitBlock = hitBlock;
/* 40 */     this.hitFace = hitFace;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Projectile getEntity() {
/* 46 */     return (Projectile)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Block getHitBlock() {
/* 56 */     return this.hitBlock;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockFace getHitBlockFace() {
/* 67 */     return this.hitFace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getHitEntity() {
/* 77 */     return this.hitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 83 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 88 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\ProjectileHitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */