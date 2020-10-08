/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class VoxelShapeArray
/*     */   extends VoxelShape
/*     */ {
/*     */   private final DoubleList b;
/*     */   private final DoubleList c;
/*     */   private final DoubleList d;
/*  15 */   static final AxisAlignedBB[] EMPTY = new AxisAlignedBB[0];
/*     */   
/*     */   final AxisAlignedBB[] boundingBoxesRepresentation;
/*     */   
/*     */   final double offsetX;
/*     */   final double offsetY;
/*     */   final double offsetZ;
/*     */   
/*     */   protected VoxelShapeArray(VoxelShapeDiscrete voxelshapediscrete, double[] adouble, double[] adouble1, double[] adouble2) {
/*  24 */     this(voxelshapediscrete, (DoubleList)DoubleArrayList.wrap(Arrays.copyOf(adouble, voxelshapediscrete.b() + 1)), (DoubleList)DoubleArrayList.wrap(Arrays.copyOf(adouble1, voxelshapediscrete.c() + 1)), (DoubleList)DoubleArrayList.wrap(Arrays.copyOf(adouble2, voxelshapediscrete.d() + 1)));
/*     */   }
/*     */ 
/*     */   
/*     */   VoxelShapeArray(VoxelShapeDiscrete voxelshapediscrete, DoubleList doublelist, DoubleList doublelist1, DoubleList doublelist2) {
/*  29 */     this(voxelshapediscrete, doublelist, doublelist1, doublelist2, (VoxelShapeArray)null, (AxisAlignedBB[])null, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   VoxelShapeArray(VoxelShapeDiscrete voxelshapediscrete, DoubleList doublelist, DoubleList doublelist1, DoubleList doublelist2, VoxelShapeArray original, AxisAlignedBB[] boundingBoxesRepresentation, double offsetX, double offsetY, double offsetZ) {
/*  33 */     super(voxelshapediscrete);
/*  34 */     int i = voxelshapediscrete.b() + 1;
/*  35 */     int j = voxelshapediscrete.c() + 1;
/*  36 */     int k = voxelshapediscrete.d() + 1;
/*     */     
/*  38 */     if (i == doublelist.size() && j == doublelist1.size() && k == doublelist2.size()) {
/*  39 */       this.b = doublelist;
/*  40 */       this.c = doublelist1;
/*  41 */       this.d = doublelist2;
/*     */     } else {
/*  43 */       throw (IllegalArgumentException)SystemUtils.c(new IllegalArgumentException("Lengths of point arrays must be consistent with the size of the VoxelShape."));
/*     */     } 
/*     */     
/*  46 */     this.boundingBoxesRepresentation = (boundingBoxesRepresentation == null) ? getBoundingBoxesRepresentation().<AxisAlignedBB>toArray(EMPTY) : boundingBoxesRepresentation;
/*  47 */     if (original == null) {
/*  48 */       this.offsetX = offsetX;
/*  49 */       this.offsetY = offsetY;
/*  50 */       this.offsetZ = offsetZ;
/*     */     } else {
/*  52 */       this.offsetX = offsetX + original.offsetX;
/*  53 */       this.offsetY = offsetY + original.offsetY;
/*  54 */       this.offsetZ = offsetZ + original.offsetZ;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoubleList a(EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  61 */     switch (enumdirection_enumaxis) {
/*     */       case X:
/*  63 */         return this.b;
/*     */       case Y:
/*  65 */         return this.c;
/*     */       case Z:
/*  67 */         return this.d;
/*     */     } 
/*  69 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape a(double d0, double d1, double d2) {
/*  76 */     if (this == VoxelShapes.getEmptyShape() || this.boundingBoxesRepresentation.length == 0) {
/*  77 */       return this;
/*     */     }
/*  79 */     return new VoxelShapeArray(this.a, (DoubleList)new DoubleListOffset(a(EnumDirection.EnumAxis.X), d0), (DoubleList)new DoubleListOffset(a(EnumDirection.EnumAxis.Y), d1), (DoubleList)new DoubleListOffset(a(EnumDirection.EnumAxis.Z), d2), this, this.boundingBoxesRepresentation, d0, d1, d2);
/*     */   }
/*     */   
/*     */   public final AxisAlignedBB[] getBoundingBoxesRepresentationRaw() {
/*  83 */     return this.boundingBoxesRepresentation;
/*     */   }
/*     */   
/*     */   public final double getOffsetX() {
/*  87 */     return this.offsetX;
/*     */   }
/*     */   
/*     */   public final double getOffsetY() {
/*  91 */     return this.offsetY;
/*     */   }
/*     */   
/*     */   public final double getOffsetZ() {
/*  95 */     return this.offsetZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean intersects(AxisAlignedBB axisalingedbb) {
/* 100 */     double offX = this.offsetX;
/* 101 */     double offY = this.offsetY;
/* 102 */     double offZ = this.offsetZ;
/*     */     
/* 104 */     for (AxisAlignedBB boundingBox : this.boundingBoxesRepresentation) {
/* 105 */       if (axisalingedbb.voxelShapeIntersect(boundingBox.minX + offX, boundingBox.minY + offY, boundingBox.minZ + offZ, boundingBox.maxX + offX, boundingBox.maxY + offY, boundingBox.maxZ + offZ))
/*     */       {
/* 107 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */