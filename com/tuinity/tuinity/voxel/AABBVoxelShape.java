/*     */ package com.tuinity.tuinity.voxel;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.AxisAlignedBB;
/*     */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*     */ import net.minecraft.server.v1_16_R2.VoxelShape;
/*     */ import net.minecraft.server.v1_16_R2.VoxelShapes;
/*     */ 
/*     */ public final class AABBVoxelShape extends VoxelShape {
/*     */   public final AxisAlignedBB aabb;
/*     */   private DoubleList cachedListX;
/*     */   
/*     */   public AABBVoxelShape(AxisAlignedBB aabb) {
/*  17 */     super(VoxelShapes.getFullUnoptimisedCube().getShape());
/*  18 */     this.aabb = aabb;
/*     */   }
/*     */   private DoubleList cachedListY; private DoubleList cachedListZ;
/*     */   
/*     */   public boolean isEmpty() {
/*  23 */     return this.aabb.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public double b(EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  28 */     switch (enumdirection_enumaxis.ordinal()) {
/*     */       case 0:
/*  30 */         return this.aabb.minX;
/*     */       case 1:
/*  32 */         return this.aabb.minY;
/*     */       case 2:
/*  34 */         return this.aabb.minZ;
/*     */     } 
/*  36 */     throw new IllegalStateException("Unknown axis requested");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double c(EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  42 */     switch (enumdirection_enumaxis.ordinal()) {
/*     */       case 0:
/*  44 */         return this.aabb.maxX;
/*     */       case 1:
/*  46 */         return this.aabb.maxY;
/*     */       case 2:
/*  48 */         return this.aabb.maxZ;
/*     */     } 
/*  50 */     throw new IllegalStateException("Unknown axis requested");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getBoundingBox() {
/*  56 */     return this.aabb;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected double a(EnumDirection.EnumAxis enumdirection_enumaxis, int i) {
/*  62 */     switch (enumdirection_enumaxis.ordinal() | i << 2) {
/*     */       case 0:
/*  64 */         return this.aabb.minX;
/*     */       case 1:
/*  66 */         return this.aabb.minY;
/*     */       case 2:
/*  68 */         return this.aabb.minZ;
/*     */       case 4:
/*  70 */         return this.aabb.maxX;
/*     */       case 5:
/*  72 */         return this.aabb.maxY;
/*     */       case 6:
/*  74 */         return this.aabb.maxZ;
/*     */     } 
/*  76 */     throw new IllegalStateException("Unknown axis requested");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoubleList a(EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  86 */     switch (enumdirection_enumaxis.ordinal()) {
/*     */       case 0:
/*  88 */         return (this.cachedListX == null) ? (this.cachedListX = (DoubleList)DoubleArrayList.wrap(new double[] { this.aabb.minX, this.aabb.maxX })) : this.cachedListX;
/*     */       case 1:
/*  90 */         return (this.cachedListY == null) ? (this.cachedListY = (DoubleList)DoubleArrayList.wrap(new double[] { this.aabb.minY, this.aabb.maxY })) : this.cachedListY;
/*     */       case 2:
/*  92 */         return (this.cachedListZ == null) ? (this.cachedListZ = (DoubleList)DoubleArrayList.wrap(new double[] { this.aabb.minZ, this.aabb.maxZ })) : this.cachedListZ;
/*     */     } 
/*  94 */     throw new IllegalStateException("Unknown axis requested");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape a(double d0, double d1, double d2) {
/* 100 */     return new AABBVoxelShape(this.aabb.offset(d0, d1, d2));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c() {
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(VoxelShapes.a voxelshapes_a) {
/* 110 */     voxelshapes_a.consume(this.aabb.minX, this.aabb.minY, this.aabb.minZ, this.aabb.maxX, this.aabb.maxY, this.aabb.maxZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AxisAlignedBB> d() {
/* 115 */     List<AxisAlignedBB> ret = new ArrayList<>(1);
/* 116 */     ret.add(this.aabb);
/* 117 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int a(EnumDirection.EnumAxis enumdirection_enumaxis, double d0) {
/* 122 */     switch (enumdirection_enumaxis.ordinal()) {
/*     */       case 0:
/* 124 */         return (d0 < this.aabb.maxX) ? ((d0 < this.aabb.minX) ? -1 : 0) : 1;
/*     */       case 1:
/* 126 */         return (d0 < this.aabb.maxY) ? ((d0 < this.aabb.minY) ? -1 : 0) : 1;
/*     */       case 2:
/* 128 */         return (d0 < this.aabb.maxZ) ? ((d0 < this.aabb.minZ) ? -1 : 0) : 1;
/*     */     } 
/* 130 */     throw new IllegalStateException("Unknown axis requested");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(double d0, double d1, double d2) {
/* 136 */     return this.aabb.contains(d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape a(EnumDirection enumdirection) {
/* 141 */     return super.a(enumdirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public double a(EnumDirection.EnumAxis enumdirection_enumaxis, AxisAlignedBB axisalignedbb, double d0) {
/* 146 */     switch (enumdirection_enumaxis.ordinal()) {
/*     */       case 0:
/* 148 */         return AxisAlignedBB.collideX(this.aabb, axisalignedbb, d0);
/*     */       case 1:
/* 150 */         return AxisAlignedBB.collideY(this.aabb, axisalignedbb, d0);
/*     */       case 2:
/* 152 */         return AxisAlignedBB.collideZ(this.aabb, axisalignedbb, d0);
/*     */     } 
/* 154 */     throw new IllegalStateException("Unknown axis requested");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersects(AxisAlignedBB axisalingedbb) {
/* 160 */     return this.aabb.voxelShapeIntersect(axisalingedbb);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\tuinity\tuinity\voxel\AABBVoxelShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */