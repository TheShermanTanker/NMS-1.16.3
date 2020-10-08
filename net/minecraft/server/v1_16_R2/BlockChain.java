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
/*    */ public class BlockChain
/*    */   extends BlockRotatable
/*    */   implements IBlockWaterlogged
/*    */ {
/* 21 */   public static final BlockStateBoolean a = BlockProperties.C;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   protected static final VoxelShape b = Block.a(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
/* 27 */   protected static final VoxelShape c = Block.a(6.5D, 6.5D, 0.0D, 9.5D, 9.5D, 16.0D);
/* 28 */   protected static final VoxelShape d = Block.a(0.0D, 6.5D, 6.5D, 16.0D, 9.5D, 9.5D);
/*    */   
/*    */   public BlockChain(BlockBase.Info var0) {
/* 31 */     super(var0);
/* 32 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Boolean.valueOf(false)).set(AXIS, EnumDirection.EnumAxis.Y));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 37 */     switch (null.a[((EnumDirection.EnumAxis)var0.get(AXIS)).ordinal()])
/*    */     
/*    */     { default:
/* 40 */         return d;
/*    */       case 2:
/* 42 */         return c;
/*    */       case 3:
/* 44 */         break; }  return b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 51 */     Fluid var1 = var0.getWorld().getFluid(var0.getClickPosition());
/* 52 */     boolean var2 = (var1.getType() == FluidTypes.WATER);
/* 53 */     return super.getPlacedState(var0).set(a, Boolean.valueOf(var2));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 58 */     if (((Boolean)var0.get(a)).booleanValue()) {
/* 59 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*    */     }
/* 61 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 66 */     var0.a((IBlockState<?>[])new IBlockState[] { a }).a((IBlockState<?>[])new IBlockState[] { AXIS });
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid d(IBlockData var0) {
/* 71 */     if (((Boolean)var0.get(a)).booleanValue()) {
/* 72 */       return FluidTypes.WATER.a(false);
/*    */     }
/* 74 */     return super.d(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 79 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockChain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */