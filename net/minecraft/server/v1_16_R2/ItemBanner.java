/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBanner
/*    */   extends ItemBlockWallable
/*    */ {
/*    */   public ItemBanner(Block var0, Block var1, Item.Info var2) {
/* 23 */     super(var0, var1, var2);
/*    */     
/* 25 */     Validate.isInstanceOf(BlockBannerAbstract.class, var0);
/* 26 */     Validate.isInstanceOf(BlockBannerAbstract.class, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumColor b() {
/* 48 */     return ((BlockBannerAbstract)getBlock()).getColor();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */