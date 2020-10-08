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
/*    */ public class ItemTippedArrow
/*    */   extends ItemArrow
/*    */ {
/*    */   public ItemTippedArrow(Item.Info var0) {
/* 16 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack createItemStack() {
/* 21 */     return PotionUtil.a(super.createItemStack(), Potions.POISON);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(CreativeModeTab var0, NonNullList<ItemStack> var1) {
/* 26 */     if (a(var0)) {
/* 27 */       for (PotionRegistry var3 : IRegistry.POTION) {
/* 28 */         if (!var3.a().isEmpty()) {
/* 29 */           var1.add(PotionUtil.a(new ItemStack(this), var3));
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String f(ItemStack var0) {
/* 42 */     return PotionUtil.d(var0).b(getName() + ".effect.");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */