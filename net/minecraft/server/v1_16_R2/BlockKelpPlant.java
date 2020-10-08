/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockKelpPlant
/*    */   extends BlockGrowingStem
/*    */   implements IFluidContainer
/*    */ {
/*    */   protected BlockKelpPlant(BlockBase.Info var0) {
/* 15 */     super(var0, EnumDirection.UP, VoxelShapes.b(), true);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockGrowingTop c() {
/* 20 */     return (BlockGrowingTop)Blocks.KELP;
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid d(IBlockData var0) {
/* 25 */     return FluidTypes.WATER.a(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockAccess var0, BlockPosition var1, IBlockData var2, FluidType var3) {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Fluid var3) {
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockKelpPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */