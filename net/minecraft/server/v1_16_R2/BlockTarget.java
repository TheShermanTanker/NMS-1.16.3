/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTarget
/*     */   extends Block
/*     */ {
/*  28 */   private static final BlockStateInteger a = BlockProperties.az;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockTarget(BlockBase.Info var0) {
/*  34 */     super(var0);
/*  35 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World var0, IBlockData var1, MovingObjectPositionBlock var2, IProjectile var3) {
/*  40 */     int var4 = a(var0, var1, var2, var3);
/*     */     
/*  42 */     Entity var5 = var3.getShooter();
/*  43 */     if (var5 instanceof EntityPlayer) {
/*  44 */       EntityPlayer var6 = (EntityPlayer)var5;
/*  45 */       var6.a(StatisticList.TARGET_HIT);
/*  46 */       CriterionTriggers.L.a(var6, var3, var2.getPos(), var4);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int a(GeneratorAccess var0, IBlockData var1, MovingObjectPositionBlock var2, Entity var3) {
/*  51 */     int var4 = a(var2, var2.getPos());
/*  52 */     int var5 = (var3 instanceof EntityArrow) ? 20 : 8;
/*     */     
/*  54 */     if (!var0.getBlockTickList().a(var2.getBlockPosition(), var1.getBlock())) {
/*  55 */       a(var0, var1, var4, var2.getBlockPosition(), var5);
/*     */     }
/*     */     
/*  58 */     return var4;
/*     */   }
/*     */   private static int a(MovingObjectPositionBlock var0, Vec3D var1) {
/*     */     double var9;
/*  62 */     EnumDirection var2 = var0.getDirection();
/*  63 */     double var3 = Math.abs(MathHelper.h(var1.x) - 0.5D);
/*  64 */     double var5 = Math.abs(MathHelper.h(var1.y) - 0.5D);
/*  65 */     double var7 = Math.abs(MathHelper.h(var1.z) - 0.5D);
/*     */ 
/*     */     
/*  68 */     EnumDirection.EnumAxis var11 = var2.n();
/*  69 */     if (var11 == EnumDirection.EnumAxis.Y) {
/*  70 */       var9 = Math.max(var3, var7);
/*  71 */     } else if (var11 == EnumDirection.EnumAxis.Z) {
/*  72 */       var9 = Math.max(var3, var5);
/*     */     } else {
/*  74 */       var9 = Math.max(var5, var7);
/*     */     } 
/*     */     
/*  77 */     return Math.max(1, MathHelper.f(15.0D * MathHelper.a((0.5D - var9) / 0.5D, 0.0D, 1.0D)));
/*     */   }
/*     */   
/*     */   private static void a(GeneratorAccess var0, IBlockData var1, int var2, BlockPosition var3, int var4) {
/*  81 */     var0.setTypeAndData(var3, var1.set(a, Integer.valueOf(var2)), 3);
/*  82 */     var0.getBlockTickList().a(var3, var1.getBlock(), var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/*  87 */     if (((Integer)var0.get(a)).intValue() != 0) {
/*  88 */       var1.setTypeAndData(var2, var0.set(a, Integer.valueOf(0)), 3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/*  94 */     return ((Integer)var0.get(a)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData var0) {
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 104 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 109 */     if (var1.s_() || var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     if (((Integer)var0.get(a)).intValue() > 0 && !var1.getBlockTickList().a(var2, this))
/* 114 */       var1.setTypeAndData(var2, var0.set(a, Integer.valueOf(0)), 18); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */