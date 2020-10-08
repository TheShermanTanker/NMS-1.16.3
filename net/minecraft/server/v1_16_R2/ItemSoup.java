/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSoup
/*    */   extends Item
/*    */ {
/*    */   public ItemSoup(Item.Info var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, World var1, EntityLiving var2) {
/* 14 */     ItemStack var3 = super.a(var0, var1, var2);
/* 15 */     if (var2 instanceof EntityHuman && ((EntityHuman)var2).abilities.canInstantlyBuild) {
/* 16 */       return var3;
/*    */     }
/* 18 */     return new ItemStack(Items.BOWL);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSoup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */