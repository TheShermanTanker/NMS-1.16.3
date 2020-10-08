/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSpectralArrow
/*    */   extends ItemArrow
/*    */ {
/*    */   public ItemSpectralArrow(Item.Info var0) {
/* 10 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityArrow a(World var0, ItemStack var1, EntityLiving var2) {
/* 15 */     return new EntitySpectralArrow(var0, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSpectralArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */