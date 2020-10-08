/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBannerPattern
/*    */   extends Item
/*    */ {
/*    */   private final EnumBannerPatternType a;
/*    */   
/*    */   public ItemBannerPattern(EnumBannerPatternType var0, Item.Info var1) {
/* 17 */     super(var1);
/* 18 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public EnumBannerPatternType b() {
/* 22 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBannerPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */