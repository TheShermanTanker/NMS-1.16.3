/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface IBlockAccess
/*     */ {
/*     */   default Material getMaterialIfLoaded(BlockPosition blockposition) {
/*  18 */     IBlockData type = getTypeIfLoaded(blockposition);
/*  19 */     return (type == null) ? null : type.getMaterial();
/*     */   }
/*     */   
/*     */   default Block getBlockIfLoaded(BlockPosition blockposition) {
/*  23 */     IBlockData type = getTypeIfLoaded(blockposition);
/*  24 */     return (type == null) ? null : type.getBlock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int g(BlockPosition blockposition) {
/*  32 */     return getType(blockposition).f();
/*     */   }
/*     */   
/*     */   default int J() {
/*  36 */     return 15;
/*     */   }
/*     */   
/*     */   default int getBuildHeight() {
/*  40 */     return 256;
/*     */   }
/*     */   
/*     */   default Stream<IBlockData> a(AxisAlignedBB axisalignedbb) {
/*  44 */     return BlockPosition.a(axisalignedbb).map(this::getType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default MovingObjectPositionBlock rayTraceBlock(RayTrace raytrace1, BlockPosition blockposition) {
/*  50 */     IBlockData iblockdata = getTypeIfLoaded(blockposition);
/*  51 */     if (iblockdata == null) {
/*     */       
/*  53 */       Vec3D vec3D = raytrace1.b().d(raytrace1.a());
/*     */       
/*  55 */       return MovingObjectPositionBlock.a(raytrace1.a(), EnumDirection.a(vec3D.x, vec3D.y, vec3D.z), new BlockPosition(raytrace1.a()));
/*     */     } 
/*     */     
/*  58 */     Fluid fluid = getFluid(blockposition);
/*  59 */     Vec3D vec3d = raytrace1.b();
/*  60 */     Vec3D vec3d1 = raytrace1.a();
/*  61 */     VoxelShape voxelshape = raytrace1.a(iblockdata, this, blockposition);
/*  62 */     MovingObjectPositionBlock movingobjectpositionblock = rayTrace(vec3d, vec3d1, blockposition, voxelshape, iblockdata);
/*  63 */     VoxelShape voxelshape1 = raytrace1.a(fluid, this, blockposition);
/*  64 */     MovingObjectPositionBlock movingobjectpositionblock1 = voxelshape1.rayTrace(vec3d, vec3d1, blockposition);
/*  65 */     double d0 = (movingobjectpositionblock == null) ? Double.MAX_VALUE : raytrace1.b().distanceSquared(movingobjectpositionblock.getPos());
/*  66 */     double d1 = (movingobjectpositionblock1 == null) ? Double.MAX_VALUE : raytrace1.b().distanceSquared(movingobjectpositionblock1.getPos());
/*     */     
/*  68 */     return (d0 <= d1) ? movingobjectpositionblock : movingobjectpositionblock1;
/*     */   }
/*     */ 
/*     */   
/*     */   default MovingObjectPositionBlock rayTrace(RayTrace raytrace) {
/*  73 */     return a(raytrace, (raytrace1, blockposition) -> rayTraceBlock(raytrace1, blockposition), raytrace1 -> {
/*     */           Vec3D vec3d = raytrace1.b().d(raytrace1.a());
/*     */           return MovingObjectPositionBlock.a(raytrace1.a(), EnumDirection.a(vec3d.x, vec3d.y, vec3d.z), new BlockPosition(raytrace1.a()));
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default MovingObjectPositionBlock rayTrace(Vec3D vec3d, Vec3D vec3d1, BlockPosition blockposition, VoxelShape voxelshape, IBlockData iblockdata) {
/*  84 */     MovingObjectPositionBlock movingobjectpositionblock = voxelshape.rayTrace(vec3d, vec3d1, blockposition);
/*     */     
/*  86 */     if (movingobjectpositionblock != null) {
/*  87 */       MovingObjectPositionBlock movingobjectpositionblock1 = iblockdata.m(this, blockposition).rayTrace(vec3d, vec3d1, blockposition);
/*     */       
/*  89 */       if (movingobjectpositionblock1 != null && movingobjectpositionblock1.getPos().d(vec3d).g() < movingobjectpositionblock.getPos().d(vec3d).g()) {
/*  90 */         return movingobjectpositionblock.a(movingobjectpositionblock1.getDirection());
/*     */       }
/*     */     } 
/*     */     
/*  94 */     return movingobjectpositionblock;
/*     */   }
/*     */   
/*     */   default double a(VoxelShape voxelshape, Supplier<VoxelShape> supplier) {
/*  98 */     if (!voxelshape.isEmpty()) {
/*  99 */       return voxelshape.c(EnumDirection.EnumAxis.Y);
/*     */     }
/* 101 */     double d0 = ((VoxelShape)supplier.get()).c(EnumDirection.EnumAxis.Y);
/*     */     
/* 103 */     return (d0 >= 1.0D) ? (d0 - 1.0D) : Double.NEGATIVE_INFINITY;
/*     */   }
/*     */ 
/*     */   
/*     */   default double h(BlockPosition blockposition) {
/* 108 */     return a(getType(blockposition).getCollisionShape(this, blockposition), () -> {
/*     */           BlockPosition blockposition1 = blockposition.down();
/*     */           return getType(blockposition1).getCollisionShape(this, blockposition1);
/*     */         });
/*     */   }
/*     */   
/*     */   static <T> T a(RayTrace raytrace, BiFunction<RayTrace, BlockPosition, T> bifunction, Function<RayTrace, T> function) {
/*     */     T object;
/* 116 */     Vec3D vec3d = raytrace.b();
/* 117 */     Vec3D vec3d1 = raytrace.a();
/*     */     
/* 119 */     if (vec3d.equals(vec3d1)) {
/* 120 */       return function.apply(raytrace);
/*     */     }
/* 122 */     double d0 = MathHelper.d(-1.0E-7D, vec3d1.x, vec3d.x);
/* 123 */     double d1 = MathHelper.d(-1.0E-7D, vec3d1.y, vec3d.y);
/* 124 */     double d2 = MathHelper.d(-1.0E-7D, vec3d1.z, vec3d.z);
/* 125 */     double d3 = MathHelper.d(-1.0E-7D, vec3d.x, vec3d1.x);
/* 126 */     double d4 = MathHelper.d(-1.0E-7D, vec3d.y, vec3d1.y);
/* 127 */     double d5 = MathHelper.d(-1.0E-7D, vec3d.z, vec3d1.z);
/* 128 */     int i = MathHelper.floor(d3);
/* 129 */     int j = MathHelper.floor(d4);
/* 130 */     int k = MathHelper.floor(d5);
/* 131 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(i, j, k);
/* 132 */     T t0 = bifunction.apply(raytrace, blockposition_mutableblockposition);
/*     */     
/* 134 */     if (t0 != null) {
/* 135 */       return t0;
/*     */     }
/* 137 */     double d6 = d0 - d3;
/* 138 */     double d7 = d1 - d4;
/* 139 */     double d8 = d2 - d5;
/* 140 */     int l = MathHelper.k(d6);
/* 141 */     int i1 = MathHelper.k(d7);
/* 142 */     int j1 = MathHelper.k(d8);
/* 143 */     double d9 = (l == 0) ? Double.MAX_VALUE : (l / d6);
/* 144 */     double d10 = (i1 == 0) ? Double.MAX_VALUE : (i1 / d7);
/* 145 */     double d11 = (j1 == 0) ? Double.MAX_VALUE : (j1 / d8);
/* 146 */     double d12 = d9 * ((l > 0) ? (1.0D - MathHelper.h(d3)) : MathHelper.h(d3));
/* 147 */     double d13 = d10 * ((i1 > 0) ? (1.0D - MathHelper.h(d4)) : MathHelper.h(d4));
/* 148 */     double d14 = d11 * ((j1 > 0) ? (1.0D - MathHelper.h(d5)) : MathHelper.h(d5));
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 153 */       if (d12 > 1.0D && d13 > 1.0D && d14 > 1.0D) {
/* 154 */         return function.apply(raytrace);
/*     */       }
/*     */       
/* 157 */       if (d12 < d13) {
/* 158 */         if (d12 < d14) {
/* 159 */           i += l;
/* 160 */           d12 += d9;
/*     */         } else {
/* 162 */           k += j1;
/* 163 */           d14 += d11;
/*     */         } 
/* 165 */       } else if (d13 < d14) {
/* 166 */         j += i1;
/* 167 */         d13 += d10;
/*     */       } else {
/* 169 */         k += j1;
/* 170 */         d14 += d11;
/*     */       } 
/*     */       
/* 173 */       object = bifunction.apply(raytrace, blockposition_mutableblockposition.d(i, j, k));
/* 174 */     } while (object == null);
/*     */     
/* 176 */     return object;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   TileEntity getTileEntity(BlockPosition paramBlockPosition);
/*     */   
/*     */   IBlockData getType(BlockPosition paramBlockPosition);
/*     */   
/*     */   IBlockData getTypeIfLoaded(BlockPosition paramBlockPosition);
/*     */   
/*     */   Fluid getFluidIfLoaded(BlockPosition paramBlockPosition);
/*     */   
/*     */   Fluid getFluid(BlockPosition paramBlockPosition);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IBlockAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */