/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemItemFrame
/*    */   extends ItemHanging
/*    */ {
/*    */   public ItemItemFrame(Item.Info var0) {
/* 11 */     super((EntityTypes)EntityTypes.ITEM_FRAME, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(EntityHuman var0, EnumDirection var1, ItemStack var2, BlockPosition var3) {
/* 16 */     return (!World.isOutsideWorld(var3) && var0.a(var3, var1, var2));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */