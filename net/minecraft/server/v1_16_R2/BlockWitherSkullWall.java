/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockWitherSkullWall
/*    */   extends BlockSkullWall
/*    */ {
/*    */   protected BlockWitherSkullWall(BlockBase.Info var0) {
/* 13 */     super(BlockSkull.Type.WITHER_SKELETON, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, @Nullable EntityLiving var3, ItemStack var4) {
/* 18 */     Blocks.WITHER_SKELETON_SKULL.postPlace(var0, var1, var2, var3, var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWitherSkullWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */