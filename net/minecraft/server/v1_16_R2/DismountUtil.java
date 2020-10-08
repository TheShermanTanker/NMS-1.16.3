/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DismountUtil
/*     */ {
/*     */   public static int[][] a(EnumDirection var0) {
/*  25 */     EnumDirection var1 = var0.g();
/*  26 */     EnumDirection var2 = var1.opposite();
/*  27 */     EnumDirection var3 = var0.opposite();
/*     */     
/*  29 */     return new int[][] { { var1
/*  30 */           .getAdjacentX(), var1.getAdjacentZ() }, { var2
/*  31 */           .getAdjacentX(), var2.getAdjacentZ() }, { var3
/*  32 */           .getAdjacentX() + var1.getAdjacentX(), var3.getAdjacentZ() + var1.getAdjacentZ() }, { var3
/*  33 */           .getAdjacentX() + var2.getAdjacentX(), var3.getAdjacentZ() + var2.getAdjacentZ() }, { var0
/*  34 */           .getAdjacentX() + var1.getAdjacentX(), var0.getAdjacentZ() + var1.getAdjacentZ() }, { var0
/*  35 */           .getAdjacentX() + var2.getAdjacentX(), var0.getAdjacentZ() + var2.getAdjacentZ() }, { var3
/*  36 */           .getAdjacentX(), var3.getAdjacentZ() }, { var0
/*  37 */           .getAdjacentX(), var0.getAdjacentZ() } };
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(double var0) {
/*  42 */     return (!Double.isInfinite(var0) && var0 < 1.0D);
/*     */   }
/*     */   
/*     */   public static boolean a(ICollisionAccess var0, EntityLiving var1, AxisAlignedBB var2) {
/*  46 */     return var0.b(var1, var2).allMatch(VoxelShape::isEmpty);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D a(ICollisionAccess var0, double var1, double var3, double var5, EntityLiving var7, EntityPose var8) {
/*  51 */     if (a(var3)) {
/*  52 */       Vec3D var9 = new Vec3D(var1, var3, var5);
/*  53 */       if (a(var0, var7, var7.f(var8).c(var9))) {
/*  54 */         return var9;
/*     */       }
/*     */     } 
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   public static VoxelShape a(IBlockAccess var0, BlockPosition var1) {
/*  61 */     IBlockData var2 = var0.getType(var1);
/*  62 */     if (var2.a(TagsBlock.CLIMBABLE) || (var2.getBlock() instanceof BlockTrapdoor && ((Boolean)var2.get(BlockTrapdoor.OPEN)).booleanValue())) {
/*  63 */       return VoxelShapes.a();
/*     */     }
/*  65 */     return var2.getCollisionShape(var0, var1);
/*     */   }
/*     */   
/*     */   public static double a(BlockPosition var0, int var1, Function<BlockPosition, VoxelShape> var2) {
/*  69 */     BlockPosition.MutableBlockPosition var3 = var0.i();
/*  70 */     int var4 = 0;
/*  71 */     while (var4 < var1) {
/*  72 */       VoxelShape var5 = var2.apply(var3);
/*  73 */       if (!var5.isEmpty()) {
/*  74 */         return (var0.getY() + var4) + var5.b(EnumDirection.EnumAxis.Y);
/*     */       }
/*  76 */       var4++;
/*  77 */       var3.c(EnumDirection.UP);
/*     */     } 
/*  79 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D a(EntityTypes<?> var0, ICollisionAccess var1, BlockPosition var2, boolean var3) {
/*  84 */     if (var3 && var0.a(var1.getType(var2))) {
/*  85 */       return null;
/*     */     }
/*     */     
/*  88 */     double var4 = var1.a(a(var1, var2), () -> a(var0, var1.down()));
/*  89 */     if (!a(var4)) {
/*  90 */       return null;
/*     */     }
/*     */     
/*  93 */     if (var3 && var4 <= 0.0D && var0.a(var1.getType(var2.down()))) {
/*  94 */       return null;
/*     */     }
/*     */     
/*  97 */     Vec3D var6 = Vec3D.a(var2, var4);
/*  98 */     if (var1.b((Entity)null, var0.l().a(var6)).allMatch(VoxelShape::isEmpty)) {
/*  99 */       return var6;
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DismountUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */