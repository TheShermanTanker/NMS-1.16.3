/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotShulkerBox
/*    */   extends Slot
/*    */ {
/*    */   public SlotShulkerBox(IInventory var0, int var1, int var2, int var3) {
/* 10 */     super(var0, var1, var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAllowed(ItemStack var0) {
/* 15 */     return !(Block.asBlock(var0.getItem()) instanceof BlockShulkerBox);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SlotShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */