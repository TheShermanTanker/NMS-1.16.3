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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSeaGrass
/*    */   extends BlockPlant
/*    */   implements IBlockFragilePlantElement, IFluidContainer
/*    */ {
/* 24 */   protected static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
/*    */   
/*    */   protected BlockSeaGrass(BlockBase.Info var0) {
/* 27 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 32 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 37 */     return (var0.d(var1, var2, EnumDirection.UP) && !var0.a(Blocks.MAGMA_BLOCK));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 43 */     Fluid var1 = var0.getWorld().getFluid(var0.getClickPosition());
/*    */     
/* 45 */     if (var1.a(TagsFluid.WATER) && var1.e() == 8) {
/* 46 */       return super.getPlacedState(var0);
/*    */     }
/* 48 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 53 */     IBlockData var6 = super.updateState(var0, var1, var2, var3, var4, var5);
/* 54 */     if (!var6.isAir()) {
/* 55 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*    */     }
/* 57 */     return var6;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess var0, BlockPosition var1, IBlockData var2, boolean var3) {
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid d(IBlockData var0) {
/* 72 */     return FluidTypes.WATER.a(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 77 */     IBlockData var4 = Blocks.TALL_SEAGRASS.getBlockData();
/* 78 */     IBlockData var5 = var4.set(BlockTallSeaGrass.b, BlockPropertyDoubleBlockHalf.UPPER);
/* 79 */     BlockPosition var6 = var2.up();
/* 80 */     if (var0.getType(var6).a(Blocks.WATER)) {
/* 81 */       var0.setTypeAndData(var2, var4, 2);
/* 82 */       var0.setTypeAndData(var6, var5, 2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockAccess var0, BlockPosition var1, IBlockData var2, FluidType var3) {
/* 88 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Fluid var3) {
/* 93 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSeaGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */