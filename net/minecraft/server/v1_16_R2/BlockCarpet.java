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
/*    */ public class BlockCarpet
/*    */   extends Block
/*    */ {
/* 14 */   protected static final VoxelShape a = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
/*    */   
/*    */   private final EnumColor color;
/*    */   
/*    */   protected BlockCarpet(EnumColor var0, BlockBase.Info var1) {
/* 19 */     super(var1);
/* 20 */     this.color = var0;
/*    */   }
/*    */   
/*    */   public EnumColor c() {
/* 24 */     return this.color;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 29 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 34 */     if (!var0.canPlace(var3, var4)) {
/* 35 */       return Blocks.AIR.getBlockData();
/*    */     }
/*    */     
/* 38 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 43 */     return !var1.isEmpty(var2.down());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCarpet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */