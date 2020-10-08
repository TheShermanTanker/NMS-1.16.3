/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ public class BlockKelp
/*    */   extends BlockGrowingTop
/*    */   implements IFluidContainer
/*    */ {
/* 19 */   protected static final VoxelShape e = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
/*    */ 
/*    */   
/*    */   protected BlockKelp(BlockBase.Info var0) {
/* 23 */     super(var0, EnumDirection.UP, e, true, 0.14D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean h(IBlockData var0) {
/* 28 */     return var0.a(Blocks.WATER);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Block d() {
/* 33 */     return Blocks.KELP_PLANT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(Block var0) {
/* 38 */     return (var0 != Blocks.MAGMA_BLOCK);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockAccess var0, BlockPosition var1, IBlockData var2, FluidType var3) {
/* 43 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Fluid var3) {
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int a(Random var0) {
/* 53 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 59 */     Fluid var1 = var0.getWorld().getFluid(var0.getClickPosition());
/* 60 */     if (var1.a(TagsFluid.WATER) && var1.e() == 8) {
/* 61 */       return super.getPlacedState(var0);
/*    */     }
/* 63 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid d(IBlockData var0) {
/* 68 */     return FluidTypes.WATER.a(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockKelp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */