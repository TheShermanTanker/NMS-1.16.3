/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ItemBook extends Item {
/*    */   public ItemBook(Item.Info var0) {
/*  5 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean f_(ItemStack var0) {
/* 10 */     return (var0.getCount() == 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int c() {
/* 15 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */