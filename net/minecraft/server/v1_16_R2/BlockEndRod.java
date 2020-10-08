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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockEndRod
/*    */   extends BlockDirectional
/*    */ {
/* 22 */   protected static final VoxelShape b = Block.a(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
/* 23 */   protected static final VoxelShape c = Block.a(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
/* 24 */   protected static final VoxelShape d = Block.a(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
/*    */   
/*    */   protected BlockEndRod(BlockBase.Info var0) {
/* 27 */     super(var0);
/* 28 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.UP));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 33 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 38 */     return var0.set(FACING, var1.b((EnumDirection)var0.get(FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 43 */     switch (null.a[((EnumDirection)var0.get(FACING)).n().ordinal()])
/*    */     
/*    */     { default:
/* 46 */         return d;
/*    */       case 2:
/* 48 */         return c;
/*    */       case 3:
/* 50 */         break; }  return b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 57 */     EnumDirection var1 = var0.getClickedFace();
/*    */     
/* 59 */     IBlockData var2 = var0.getWorld().getType(var0.getClickPosition().shift(var1.opposite()));
/* 60 */     if (var2.a(this) && var2.get(FACING) == var1) {
/* 61 */       return getBlockData().set(FACING, var1.opposite());
/*    */     }
/*    */     
/* 64 */     return getBlockData().set(FACING, var1);
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
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 82 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING });
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumPistonReaction getPushReaction(IBlockData var0) {
/* 87 */     return EnumPistonReaction.NORMAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 92 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockEndRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */