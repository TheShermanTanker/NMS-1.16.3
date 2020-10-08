/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBed
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemBed(Block var0, Item.Info var1) {
/*  9 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(BlockActionContext var0, IBlockData var1) {
/* 14 */     return var0.getWorld().setTypeAndData(var0.getClickPosition(), var1, 26);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */