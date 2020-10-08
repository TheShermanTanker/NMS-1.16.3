/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemArrow
/*    */   extends Item
/*    */ {
/*    */   public ItemArrow(Item.Info var0) {
/* 10 */     super(var0);
/*    */   }
/*    */   
/*    */   public EntityArrow a(World var0, ItemStack var1, EntityLiving var2) {
/* 14 */     EntityTippedArrow var3 = new EntityTippedArrow(var0, var2);
/* 15 */     var3.b(var1);
/* 16 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */