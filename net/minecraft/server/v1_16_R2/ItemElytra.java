/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemElytra
/*    */   extends Item
/*    */   implements ItemWearable
/*    */ {
/*    */   public ItemElytra(Item.Info var0) {
/* 13 */     super(var0);
/*    */     
/* 15 */     BlockDispenser.a(this, ItemArmor.a);
/*    */   }
/*    */   
/*    */   public static boolean d(ItemStack var0) {
/* 19 */     return (var0.getDamage() < var0.h() - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack var0, ItemStack var1) {
/* 24 */     return (var1.getItem() == Items.PHANTOM_MEMBRANE);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 29 */     ItemStack var3 = var1.b(var2);
/* 30 */     EnumItemSlot var4 = EntityInsentient.j(var3);
/* 31 */     ItemStack var5 = var1.getEquipment(var4);
/*    */     
/* 33 */     if (var5.isEmpty()) {
/* 34 */       var1.setSlot(var4, var3.cloneItemStack());
/* 35 */       var3.setCount(0);
/* 36 */       return InteractionResultWrapper.a(var3, var0.s_());
/*    */     } 
/*    */     
/* 39 */     return InteractionResultWrapper.fail(var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemElytra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */