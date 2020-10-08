/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeMapClone
/*    */   extends IRecipeComplex
/*    */ {
/*    */   public RecipeMapClone(MinecraftKey var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 16 */     int var2 = 0;
/* 17 */     ItemStack var3 = ItemStack.b;
/*    */     
/* 19 */     for (int var4 = 0; var4 < var0.getSize(); var4++) {
/* 20 */       ItemStack var5 = var0.getItem(var4);
/* 21 */       if (!var5.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 25 */         if (var5.getItem() == Items.FILLED_MAP) {
/* 26 */           if (!var3.isEmpty()) {
/* 27 */             return false;
/*    */           }
/* 29 */           var3 = var5;
/* 30 */         } else if (var5.getItem() == Items.MAP) {
/* 31 */           var2++;
/*    */         } else {
/* 33 */           return false;
/*    */         } 
/*    */       }
/*    */     } 
/* 37 */     return (!var3.isEmpty() && var2 > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 42 */     int var1 = 0;
/* 43 */     ItemStack var2 = ItemStack.b;
/*    */     
/* 45 */     for (int i = 0; i < var0.getSize(); i++) {
/* 46 */       ItemStack var4 = var0.getItem(i);
/* 47 */       if (!var4.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 51 */         if (var4.getItem() == Items.FILLED_MAP) {
/* 52 */           if (!var2.isEmpty()) {
/* 53 */             return ItemStack.b;
/*    */           }
/* 55 */           var2 = var4;
/* 56 */         } else if (var4.getItem() == Items.MAP) {
/* 57 */           var1++;
/*    */         } else {
/* 59 */           return ItemStack.b;
/*    */         } 
/*    */       }
/*    */     } 
/* 63 */     if (var2.isEmpty() || var1 < 1) {
/* 64 */       return ItemStack.b;
/*    */     }
/*    */     
/* 67 */     ItemStack var3 = var2.cloneItemStack();
/* 68 */     var3.setCount(var1 + 1);
/*    */     
/* 70 */     return var3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 80 */     return RecipeSerializer.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeMapClone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */