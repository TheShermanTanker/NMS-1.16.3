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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemShield
/*    */   extends Item
/*    */ {
/*    */   public ItemShield(Item.Info var0) {
/* 22 */     super(var0);
/*    */     
/* 24 */     BlockDispenser.a(this, ItemArmor.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public String f(ItemStack var0) {
/* 29 */     if (var0.b("BlockEntityTag") != null) {
/* 30 */       return getName() + '.' + d(var0).c();
/*    */     }
/* 32 */     return super.f(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumAnimation d_(ItemStack var0) {
/* 42 */     return EnumAnimation.BLOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e_(ItemStack var0) {
/* 47 */     return 72000;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 52 */     ItemStack var3 = var1.b(var2);
/* 53 */     var1.c(var2);
/* 54 */     return InteractionResultWrapper.consume(var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack var0, ItemStack var1) {
/* 59 */     return (TagsItem.PLANKS.isTagged(var1.getItem()) || super.a(var0, var1));
/*    */   }
/*    */   
/*    */   public static EnumColor d(ItemStack var0) {
/* 63 */     return EnumColor.fromColorIndex(var0.a("BlockEntityTag").getInt("Base"));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemShield.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */