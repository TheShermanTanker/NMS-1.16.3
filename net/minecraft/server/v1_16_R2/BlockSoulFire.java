/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSoulFire
/*    */   extends BlockFireAbstract
/*    */ {
/*    */   public BlockSoulFire(BlockBase.Info var0) {
/* 12 */     super(var0, 2.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 17 */     if (canPlace(var0, var3, var4)) {
/* 18 */       return getBlockData();
/*    */     }
/*    */     
/* 21 */     return Blocks.AIR.getBlockData();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 26 */     return c(var1.getType(var2.down()).getBlock());
/*    */   }
/*    */   
/*    */   public static boolean c(Block var0) {
/* 30 */     return var0.a(TagsBlock.SOUL_FIRE_BASE_BLOCKS);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean e(IBlockData var0) {
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSoulFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */