/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeTippedArrow
/*    */   extends IRecipeComplex
/*    */ {
/*    */   public RecipeTippedArrow(MinecraftKey var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 18 */     if (var0.g() != 3 || var0.f() != 3) {
/* 19 */       return false;
/*    */     }
/*    */     
/* 22 */     for (int var2 = 0; var2 < var0.g(); var2++) {
/* 23 */       for (int var3 = 0; var3 < var0.f(); var3++) {
/* 24 */         ItemStack var4 = var0.getItem(var2 + var3 * var0.g());
/*    */         
/* 26 */         if (var4.isEmpty()) {
/* 27 */           return false;
/*    */         }
/*    */         
/* 30 */         Item var5 = var4.getItem();
/* 31 */         if (var2 == 1 && var3 == 1) {
/* 32 */           if (var5 != Items.LINGERING_POTION) {
/* 33 */             return false;
/*    */           }
/* 35 */         } else if (var5 != Items.ARROW) {
/* 36 */           return false;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 46 */     ItemStack var1 = var0.getItem(1 + var0.g());
/* 47 */     if (var1.getItem() != Items.LINGERING_POTION) {
/* 48 */       return ItemStack.b;
/*    */     }
/*    */     
/* 51 */     ItemStack var2 = new ItemStack(Items.TIPPED_ARROW, 8);
/* 52 */     PotionUtil.a(var2, PotionUtil.d(var1));
/* 53 */     PotionUtil.a(var2, PotionUtil.b(var1));
/*    */     
/* 55 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 65 */     return RecipeSerializer.j;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */