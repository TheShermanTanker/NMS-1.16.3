/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBisected
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemBisected(Block var0, Item.Info var1) {
/* 10 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(BlockActionContext var0, IBlockData var1) {
/* 15 */     var0.getWorld().setTypeAndData(var0.getClickPosition().up(), Blocks.AIR.getBlockData(), 27);
/* 16 */     return super.a(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBisected.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */