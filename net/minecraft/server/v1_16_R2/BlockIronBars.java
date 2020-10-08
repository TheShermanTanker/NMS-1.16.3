/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockIronBars
/*    */   extends BlockTall
/*    */ {
/*    */   protected BlockIronBars(BlockBase.Info var0) {
/* 19 */     super(1.0F, 1.0F, 16.0F, 16.0F, 16.0F, var0);
/* 20 */     j(((IBlockData)this.blockStateList.getBlockData()).set(NORTH, Boolean.valueOf(false)).set(EAST, Boolean.valueOf(false)).set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)).set(e, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 25 */     IBlockAccess var1 = var0.getWorld();
/* 26 */     BlockPosition var2 = var0.getClickPosition();
/* 27 */     Fluid var3 = var0.getWorld().getFluid(var0.getClickPosition());
/*    */     
/* 29 */     BlockPosition var4 = var2.north();
/* 30 */     BlockPosition var5 = var2.south();
/* 31 */     BlockPosition var6 = var2.west();
/* 32 */     BlockPosition var7 = var2.east();
/*    */     
/* 34 */     IBlockData var8 = var1.getType(var4);
/* 35 */     IBlockData var9 = var1.getType(var5);
/* 36 */     IBlockData var10 = var1.getType(var6);
/* 37 */     IBlockData var11 = var1.getType(var7);
/*    */     
/* 39 */     return getBlockData()
/* 40 */       .set(NORTH, Boolean.valueOf(a(var8, var8.d(var1, var4, EnumDirection.SOUTH))))
/* 41 */       .set(SOUTH, Boolean.valueOf(a(var9, var9.d(var1, var5, EnumDirection.NORTH))))
/* 42 */       .set(WEST, Boolean.valueOf(a(var10, var10.d(var1, var6, EnumDirection.EAST))))
/* 43 */       .set(EAST, Boolean.valueOf(a(var11, var11.d(var1, var7, EnumDirection.WEST))))
/* 44 */       .set(e, Boolean.valueOf((var3.getType() == FluidTypes.WATER)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 50 */     if (((Boolean)var0.get(e)).booleanValue()) {
/* 51 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*    */     }
/* 53 */     if (var1.n().d()) {
/* 54 */       return var0.set(f.get(var1), Boolean.valueOf(a(var2, var2.d(var3, var5, var1.opposite()))));
/*    */     }
/* 56 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape a(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 61 */     return VoxelShapes.a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final boolean a(IBlockData var0, boolean var1) {
/* 78 */     Block var2 = var0.getBlock();
/* 79 */     return ((!b(var2) && var1) || var2 instanceof BlockIronBars || var2.a(TagsBlock.WALLS));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 84 */     var0.a((IBlockState<?>[])new IBlockState[] { NORTH, EAST, WEST, SOUTH, e });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockIronBars.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */