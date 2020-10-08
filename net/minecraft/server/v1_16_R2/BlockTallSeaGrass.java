/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
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
/*    */ 
/*    */ public class BlockTallSeaGrass
/*    */   extends BlockTallPlant
/*    */   implements IFluidContainer
/*    */ {
/* 23 */   public static final BlockStateEnum<BlockPropertyDoubleBlockHalf> b = BlockTallPlant.HALF;
/*    */ 
/*    */   
/* 26 */   protected static final VoxelShape c = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
/*    */   
/*    */   public BlockTallSeaGrass(BlockBase.Info var0) {
/* 29 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 34 */     return c;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 39 */     return (var0.d(var1, var2, EnumDirection.UP) && !var0.a(Blocks.MAGMA_BLOCK));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 50 */     IBlockData var1 = super.getPlacedState(var0);
/*    */     
/* 52 */     if (var1 != null) {
/* 53 */       Fluid var2 = var0.getWorld().getFluid(var0.getClickPosition().up());
/* 54 */       if (var2.a(TagsFluid.WATER) && var2.e() == 8) {
/* 55 */         return var1;
/*    */       }
/*    */     } 
/*    */     
/* 59 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 64 */     if (var0.get(b) == BlockPropertyDoubleBlockHalf.UPPER) {
/* 65 */       IBlockData iBlockData = var1.getType(var2.down());
/* 66 */       return (iBlockData.a(this) && iBlockData.get(b) == BlockPropertyDoubleBlockHalf.LOWER);
/*    */     } 
/*    */     
/* 69 */     Fluid var3 = var1.getFluid(var2);
/* 70 */     return (super.canPlace(var0, var1, var2) && var3.a(TagsFluid.WATER) && var3.e() == 8);
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid d(IBlockData var0) {
/* 75 */     return FluidTypes.WATER.a(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockAccess var0, BlockPosition var1, IBlockData var2, FluidType var3) {
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Fluid var3) {
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTallSeaGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */