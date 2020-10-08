/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class ItemSign
/*    */   extends ItemBlockWallable {
/*    */   public static BlockPosition openSign;
/*    */   
/*    */   public ItemSign(Item.Info item_info, Block block, Block block1) {
/* 10 */     super(block, block1, item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(BlockPosition blockposition, World world, @Nullable EntityHuman entityhuman, ItemStack itemstack, IBlockData iblockdata) {
/* 15 */     boolean flag = super.a(blockposition, world, entityhuman, itemstack, iblockdata);
/*    */     
/* 17 */     if (!world.isClientSide && !flag && entityhuman != null)
/*    */     {
/*    */       
/* 20 */       openSign = blockposition;
/*    */     }
/*    */ 
/*    */     
/* 24 */     return flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */