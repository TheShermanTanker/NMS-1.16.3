/*     */ package net.minecraft.server.v1_16_R2;
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
/*     */ 
/*     */ public abstract class FluidTypeWater
/*     */   extends FluidTypeFlowing
/*     */ {
/*     */   public FluidType d() {
/*  29 */     return FluidTypes.FLOWING_WATER;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidType e() {
/*  34 */     return FluidTypes.WATER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item a() {
/*  39 */     return Items.WATER_BUCKET;
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
/*     */   protected boolean f() {
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(GeneratorAccess var0, BlockPosition var1, IBlockData var2) {
/*  66 */     TileEntity var3 = var2.getBlock().isTileEntity() ? var0.getTileEntity(var1) : null;
/*  67 */     Block.a(var2, var0, var1, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IWorldReader var0) {
/*  72 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData b(Fluid var0) {
/*  77 */     return Blocks.WATER.getBlockData().set(BlockFluids.LEVEL, Integer.valueOf(e(var0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(FluidType var0) {
/*  82 */     return (var0 == FluidTypes.WATER || var0 == FluidTypes.FLOWING_WATER);
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IWorldReader var0) {
/*  87 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IWorldReader var0) {
/*  92 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Fluid var0, IBlockAccess var1, BlockPosition var2, FluidType var3, EnumDirection var4) {
/*  97 */     return (var4 == EnumDirection.DOWN && !var3.a(TagsFluid.WATER));
/*     */   }
/*     */ 
/*     */   
/*     */   protected float c() {
/* 102 */     return 100.0F;
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends FluidTypeWater {
/*     */     public int d(Fluid var0) {
/* 108 */       return 8;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean c(Fluid var0) {
/* 113 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class a
/*     */     extends FluidTypeWater {
/*     */     protected void a(BlockStateList.a<FluidType, Fluid> var0) {
/* 120 */       super.a(var0);
/* 121 */       var0.a((IBlockState<?>[])new IBlockState[] { LEVEL });
/*     */     }
/*     */ 
/*     */     
/*     */     public int d(Fluid var0) {
/* 126 */       return ((Integer)var0.get(LEVEL)).intValue();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean c(Fluid var0) {
/* 131 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FluidTypeWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */