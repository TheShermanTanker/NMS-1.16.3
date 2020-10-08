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
/*     */ public class BlockBubbleColumn
/*     */   extends Block
/*     */   implements IFluidSource
/*     */ {
/*  28 */   public static final BlockStateBoolean a = BlockProperties.e;
/*     */ 
/*     */   
/*     */   public BlockBubbleColumn(BlockBase.Info var0) {
/*  32 */     super(var0);
/*  33 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData var0, World var1, BlockPosition var2, Entity var3) {
/*  38 */     IBlockData var4 = var1.getType(var2.up());
/*  39 */     if (var4.isAir()) {
/*  40 */       var3.k(((Boolean)var0.get(a)).booleanValue());
/*     */       
/*  42 */       if (!var1.isClientSide) {
/*  43 */         WorldServer var5 = (WorldServer)var1;
/*  44 */         for (int var6 = 0; var6 < 2; var6++) {
/*  45 */           var5.a(Particles.SPLASH, var2.getX() + var1.random.nextDouble(), (var2.getY() + 1), var2.getZ() + var1.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
/*  46 */           var5.a(Particles.BUBBLE, var2.getX() + var1.random.nextDouble(), (var2.getY() + 1), var2.getZ() + var1.random.nextDouble(), 1, 0.0D, 0.01D, 0.0D, 0.2D);
/*     */         } 
/*     */       } 
/*     */     } else {
/*  50 */       var3.l(((Boolean)var0.get(a)).booleanValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/*  56 */     a(var1, var2.up(), a(var1, var2.down()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/*  61 */     a(var1, var2.up(), a(var1, var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/*  66 */     return FluidTypes.WATER.a(false);
/*     */   }
/*     */   
/*     */   public static void a(GeneratorAccess var0, BlockPosition var1, boolean var2) {
/*  70 */     if (a(var0, var1)) {
/*  71 */       var0.setTypeAndData(var1, Blocks.BUBBLE_COLUMN.getBlockData().set(a, Boolean.valueOf(var2)), 2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean a(GeneratorAccess var0, BlockPosition var1) {
/*  76 */     Fluid var2 = var0.getFluid(var1);
/*  77 */     return (var0.getType(var1).a(Blocks.WATER) && var2.e() >= 8 && var2.isSource());
/*     */   }
/*     */   
/*     */   private static boolean a(IBlockAccess var0, BlockPosition var1) {
/*  81 */     IBlockData var2 = var0.getType(var1);
/*     */     
/*  83 */     if (var2.a(Blocks.BUBBLE_COLUMN)) {
/*  84 */       return ((Boolean)var2.get(a)).booleanValue();
/*     */     }
/*     */     
/*  87 */     return !var2.a(Blocks.SOUL_SAND);
/*     */   }
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
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 112 */     if (!var0.canPlace(var3, var4)) {
/* 113 */       return Blocks.WATER.getBlockData();
/*     */     }
/*     */     
/* 116 */     if (var1 == EnumDirection.DOWN) {
/* 117 */       var3.setTypeAndData(var4, Blocks.BUBBLE_COLUMN.getBlockData().set(a, Boolean.valueOf(a(var3, var5))), 2);
/* 118 */     } else if (var1 == EnumDirection.UP && !var2.a(Blocks.BUBBLE_COLUMN) && a(var3, var5)) {
/* 119 */       var3.getBlockTickList().a(var4, this, 5);
/*     */     } 
/*     */     
/* 122 */     var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/* 123 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 128 */     IBlockData var3 = var1.getType(var2.down());
/*     */     
/* 130 */     return (var3.a(Blocks.BUBBLE_COLUMN) || var3.a(Blocks.MAGMA_BLOCK) || var3.a(Blocks.SOUL_SAND));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 135 */     return VoxelShapes.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/* 140 */     return EnumRenderType.INVISIBLE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 145 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidType removeFluid(GeneratorAccess var0, BlockPosition var1, IBlockData var2) {
/* 150 */     var0.setTypeAndData(var1, Blocks.AIR.getBlockData(), 11);
/* 151 */     return FluidTypes.WATER;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBubbleColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */