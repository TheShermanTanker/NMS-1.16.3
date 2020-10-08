/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.math.DoubleMath;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public abstract class VoxelShape {
/*     */   protected final VoxelShapeDiscrete a;
/*     */   
/*     */   public final VoxelShapeDiscrete getShape() {
/*  11 */     return this.a;
/*     */   }
/*     */   @Nullable
/*     */   private VoxelShape[] b;
/*     */   protected VoxelShape(VoxelShapeDiscrete voxelshapediscrete) {
/*  16 */     this.a = voxelshapediscrete;
/*     */   }
/*     */   
/*     */   public double b(EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  20 */     int i = this.a.a(enumdirection_enumaxis);
/*     */     
/*  22 */     return (i >= this.a.c(enumdirection_enumaxis)) ? Double.POSITIVE_INFINITY : a(enumdirection_enumaxis, i);
/*     */   }
/*     */   
/*     */   public double c(EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  26 */     int i = this.a.b(enumdirection_enumaxis);
/*     */     
/*  28 */     return (i <= 0) ? Double.NEGATIVE_INFINITY : a(enumdirection_enumaxis, i);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB getBoundingBox() {
/*  32 */     if (isEmpty()) {
/*  33 */       throw (UnsupportedOperationException)SystemUtils.c(new UnsupportedOperationException("No bounds for empty shape."));
/*     */     }
/*  35 */     return new AxisAlignedBB(b(EnumDirection.EnumAxis.X), b(EnumDirection.EnumAxis.Y), b(EnumDirection.EnumAxis.Z), c(EnumDirection.EnumAxis.X), c(EnumDirection.EnumAxis.Y), c(EnumDirection.EnumAxis.Z));
/*     */   }
/*     */ 
/*     */   
/*     */   protected double a(EnumDirection.EnumAxis enumdirection_enumaxis, int i) {
/*  40 */     return a(enumdirection_enumaxis).getDouble(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  46 */     return this.a.a();
/*     */   }
/*     */   public final VoxelShape offset(double x, double y, double z) {
/*  49 */     return a(x, y, z);
/*     */   } public VoxelShape a(double d0, double d1, double d2) {
/*  51 */     return isEmpty() ? VoxelShapes.a() : new VoxelShapeArray(this.a, (DoubleList)new DoubleListOffset(a(EnumDirection.EnumAxis.X), d0), (DoubleList)new DoubleListOffset(a(EnumDirection.EnumAxis.Y), d1), (DoubleList)new DoubleListOffset(a(EnumDirection.EnumAxis.Z), d2));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean intersects(AxisAlignedBB axisalingedbb) {
/*  56 */     return VoxelShapes.applyOperation(this, (VoxelShape)new AABBVoxelShape(axisalingedbb), OperatorBoolean.AND);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c() {
/*  61 */     VoxelShape[] avoxelshape = { VoxelShapes.a() };
/*     */     
/*  63 */     b((d0, d1, d2, d3, d4, d5) -> avoxelshape[0] = VoxelShapes.b(avoxelshape[0], VoxelShapes.create(d0, d1, d2, d3, d4, d5), OperatorBoolean.OR));
/*     */ 
/*     */     
/*  66 */     return avoxelshape[0];
/*     */   }
/*     */   
/*     */   public void b(VoxelShapes.a voxelshapes_a) {
/*  70 */     DoubleList doublelist = a(EnumDirection.EnumAxis.X);
/*  71 */     DoubleList doublelist1 = a(EnumDirection.EnumAxis.Y);
/*  72 */     DoubleList doublelist2 = a(EnumDirection.EnumAxis.Z);
/*     */     
/*  74 */     this.a.b((i, j, k, l, i1, j1) -> voxelshapes_a.consume(doublelist.getDouble(i), doublelist1.getDouble(j), doublelist2.getDouble(k), doublelist.getDouble(l), doublelist1.getDouble(i1), doublelist2.getDouble(j1)), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public final List<AxisAlignedBB> getBoundingBoxesRepresentation() {
/*  79 */     return d();
/*     */   } public List<AxisAlignedBB> d() {
/*  81 */     List<AxisAlignedBB> list = Lists.newArrayList();
/*     */     
/*  83 */     b((d0, d1, d2, d3, d4, d5) -> list.add(new AxisAlignedBB(d0, d1, d2, d3, d4, d5)));
/*     */ 
/*     */     
/*  86 */     return list;
/*     */   }
/*     */   
/*     */   protected int a(EnumDirection.EnumAxis enumdirection_enumaxis, double d0) {
/*  90 */     return MathHelper.a(0, this.a.c(enumdirection_enumaxis) + 1, i -> (i < 0) ? false : ((i > this.a.c(enumdirection_enumaxis)) ? true : ((d0 < a(enumdirection_enumaxis, i))))) - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(double d0, double d1, double d2) {
/*  96 */     return this.a.c(a(EnumDirection.EnumAxis.X, d0), a(EnumDirection.EnumAxis.Y, d1), a(EnumDirection.EnumAxis.Z, d2));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MovingObjectPositionBlock rayTrace(Vec3D vec3d, Vec3D vec3d1, BlockPosition blockposition) {
/* 101 */     if (isEmpty()) {
/* 102 */       return null;
/*     */     }
/* 104 */     Vec3D vec3d2 = vec3d1.d(vec3d);
/*     */     
/* 106 */     if (vec3d2.g() < 1.0E-7D) {
/* 107 */       return null;
/*     */     }
/* 109 */     Vec3D vec3d3 = vec3d.e(vec3d2.a(0.001D));
/*     */     
/* 111 */     return b(vec3d3.x - blockposition.getX(), vec3d3.y - blockposition.getY(), vec3d3.z - blockposition.getZ()) ? new MovingObjectPositionBlock(vec3d3, EnumDirection.a(vec3d2.x, vec3d2.y, vec3d2.z).opposite(), blockposition, true) : AxisAlignedBB.a(d(), vec3d, vec3d1, blockposition);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape a(EnumDirection enumdirection) {
/* 117 */     if (!isEmpty() && this != VoxelShapes.b()) {
/*     */ 
/*     */       
/* 120 */       if (this.b != null) {
/* 121 */         VoxelShape voxelShape = this.b[enumdirection.ordinal()];
/* 122 */         if (voxelShape != null) {
/* 123 */           return voxelShape;
/*     */         }
/*     */       } else {
/* 126 */         this.b = new VoxelShape[6];
/*     */       } 
/*     */       
/* 129 */       VoxelShape voxelshape = b(enumdirection);
/* 130 */       this.b[enumdirection.ordinal()] = voxelshape;
/* 131 */       return voxelshape;
/*     */     } 
/* 133 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private VoxelShape b(EnumDirection enumdirection) {
/* 138 */     EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.n();
/* 139 */     EnumDirection.EnumAxisDirection enumdirection_enumaxisdirection = enumdirection.e();
/* 140 */     DoubleList doublelist = a(enumdirection_enumaxis);
/*     */     
/* 142 */     if (doublelist.size() == 2 && DoubleMath.fuzzyEquals(doublelist.getDouble(0), 0.0D, 1.0E-7D) && DoubleMath.fuzzyEquals(doublelist.getDouble(1), 1.0D, 1.0E-7D)) {
/* 143 */       return this;
/*     */     }
/* 145 */     int i = a(enumdirection_enumaxis, (enumdirection_enumaxisdirection == EnumDirection.EnumAxisDirection.POSITIVE) ? 0.9999999D : 1.0E-7D);
/*     */     
/* 147 */     return new VoxelShapeSlice(this, enumdirection_enumaxis, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public double a(EnumDirection.EnumAxis enumdirection_enumaxis, AxisAlignedBB axisalignedbb, double d0) {
/* 152 */     return a(EnumAxisCycle.a(enumdirection_enumaxis, EnumDirection.EnumAxis.X), axisalignedbb, d0);
/*     */   }
/*     */   
/*     */   protected double a(EnumAxisCycle enumaxiscycle, AxisAlignedBB axisalignedbb, double d0) {
/* 156 */     if (isEmpty())
/* 157 */       return d0; 
/* 158 */     if (Math.abs(d0) < 1.0E-7D) {
/* 159 */       return 0.0D;
/*     */     }
/* 161 */     EnumAxisCycle enumaxiscycle1 = enumaxiscycle.a();
/* 162 */     EnumDirection.EnumAxis enumdirection_enumaxis = enumaxiscycle1.a(EnumDirection.EnumAxis.X);
/* 163 */     EnumDirection.EnumAxis enumdirection_enumaxis1 = enumaxiscycle1.a(EnumDirection.EnumAxis.Y);
/* 164 */     EnumDirection.EnumAxis enumdirection_enumaxis2 = enumaxiscycle1.a(EnumDirection.EnumAxis.Z);
/* 165 */     double d1 = axisalignedbb.b(enumdirection_enumaxis);
/* 166 */     double d2 = axisalignedbb.a(enumdirection_enumaxis);
/* 167 */     int i = a(enumdirection_enumaxis, d2 + 1.0E-7D);
/* 168 */     int j = a(enumdirection_enumaxis, d1 - 1.0E-7D);
/* 169 */     int k = Math.max(0, a(enumdirection_enumaxis1, axisalignedbb.a(enumdirection_enumaxis1) + 1.0E-7D));
/* 170 */     int l = Math.min(this.a.c(enumdirection_enumaxis1), a(enumdirection_enumaxis1, axisalignedbb.b(enumdirection_enumaxis1) - 1.0E-7D) + 1);
/* 171 */     int i1 = Math.max(0, a(enumdirection_enumaxis2, axisalignedbb.a(enumdirection_enumaxis2) + 1.0E-7D));
/* 172 */     int j1 = Math.min(this.a.c(enumdirection_enumaxis2), a(enumdirection_enumaxis2, axisalignedbb.b(enumdirection_enumaxis2) - 1.0E-7D) + 1);
/* 173 */     int k1 = this.a.c(enumdirection_enumaxis);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     if (d0 > 0.0D) {
/* 180 */       for (int l1 = j + 1; l1 < k1; l1++) {
/* 181 */         for (int i2 = k; i2 < l; i2++) {
/* 182 */           for (int j2 = i1; j2 < j1; j2++) {
/* 183 */             if (this.a.a(enumaxiscycle1, l1, i2, j2)) {
/* 184 */               double d3 = a(enumdirection_enumaxis, l1) - d1;
/* 185 */               if (d3 >= -1.0E-7D) {
/* 186 */                 d0 = Math.min(d0, d3);
/*     */               }
/*     */               
/* 189 */               return d0;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 194 */     } else if (d0 < 0.0D) {
/* 195 */       for (int l1 = i - 1; l1 >= 0; l1--) {
/* 196 */         for (int i2 = k; i2 < l; i2++) {
/* 197 */           for (int j2 = i1; j2 < j1; j2++) {
/* 198 */             if (this.a.a(enumaxiscycle1, l1, i2, j2)) {
/* 199 */               double d3 = a(enumdirection_enumaxis, l1 + 1) - d2;
/* 200 */               if (d3 <= 1.0E-7D) {
/* 201 */                 d0 = Math.max(d0, d3);
/*     */               }
/*     */               
/* 204 */               return d0;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 211 */     return d0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     return isEmpty() ? "EMPTY" : ("VoxelShape[" + getBoundingBox() + "]");
/*     */   }
/*     */   
/*     */   protected abstract DoubleList a(EnumDirection.EnumAxis paramEnumAxis);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */