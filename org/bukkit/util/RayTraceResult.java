/*     */ package org.bukkit.util;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RayTraceResult
/*     */ {
/*     */   private final Vector hitPosition;
/*     */   private final Block hitBlock;
/*     */   private final BlockFace hitBlockFace;
/*     */   private final Entity hitEntity;
/*     */   
/*     */   private RayTraceResult(@NotNull Vector hitPosition, @Nullable Block hitBlock, @Nullable BlockFace hitBlockFace, @Nullable Entity hitEntity) {
/*  27 */     Validate.notNull(hitPosition, "Hit position is null!");
/*  28 */     this.hitPosition = hitPosition.clone();
/*  29 */     this.hitBlock = hitBlock;
/*  30 */     this.hitBlockFace = hitBlockFace;
/*  31 */     this.hitEntity = hitEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RayTraceResult(@NotNull Vector hitPosition) {
/*  40 */     this(hitPosition, null, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RayTraceResult(@NotNull Vector hitPosition, @Nullable BlockFace hitBlockFace) {
/*  50 */     this(hitPosition, null, hitBlockFace, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RayTraceResult(@NotNull Vector hitPosition, @Nullable Block hitBlock, @Nullable BlockFace hitBlockFace) {
/*  61 */     this(hitPosition, hitBlock, hitBlockFace, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RayTraceResult(@NotNull Vector hitPosition, @Nullable Entity hitEntity) {
/*  71 */     this(hitPosition, null, null, hitEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RayTraceResult(@NotNull Vector hitPosition, @Nullable Entity hitEntity, @Nullable BlockFace hitBlockFace) {
/*  82 */     this(hitPosition, null, hitBlockFace, hitEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector getHitPosition() {
/*  92 */     return this.hitPosition.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Block getHitBlock() {
/* 102 */     return this.hitBlock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockFace getHitBlockFace() {
/* 112 */     return this.hitBlockFace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getHitEntity() {
/* 122 */     return this.hitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 127 */     int prime = 31;
/* 128 */     int result = 1;
/* 129 */     result = 31 * result + this.hitPosition.hashCode();
/* 130 */     result = 31 * result + ((this.hitBlock == null) ? 0 : this.hitBlock.hashCode());
/* 131 */     result = 31 * result + ((this.hitBlockFace == null) ? 0 : this.hitBlockFace.hashCode());
/* 132 */     result = 31 * result + ((this.hitEntity == null) ? 0 : this.hitEntity.hashCode());
/* 133 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 138 */     if (this == obj) return true; 
/* 139 */     if (!(obj instanceof RayTraceResult)) return false; 
/* 140 */     RayTraceResult other = (RayTraceResult)obj;
/* 141 */     if (!this.hitPosition.equals(other.hitPosition)) return false; 
/* 142 */     if (!Objects.equals(this.hitBlock, other.hitBlock)) return false; 
/* 143 */     if (!Objects.equals(this.hitBlockFace, other.hitBlockFace)) return false; 
/* 144 */     if (!Objects.equals(this.hitEntity, other.hitEntity)) return false; 
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 150 */     StringBuilder builder = new StringBuilder();
/* 151 */     builder.append("RayTraceResult [hitPosition=");
/* 152 */     builder.append(this.hitPosition);
/* 153 */     builder.append(", hitBlock=");
/* 154 */     builder.append(this.hitBlock);
/* 155 */     builder.append(", hitBlockFace=");
/* 156 */     builder.append(this.hitBlockFace);
/* 157 */     builder.append(", hitEntity=");
/* 158 */     builder.append(this.hitEntity);
/* 159 */     builder.append("]");
/* 160 */     return builder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\RayTraceResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */