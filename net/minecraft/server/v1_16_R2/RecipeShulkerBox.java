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
/*    */ public class RecipeShulkerBox
/*    */   extends IRecipeComplex
/*    */ {
/*    */   public RecipeShulkerBox(MinecraftKey var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 20 */     int var2 = 0;
/* 21 */     int var3 = 0;
/*    */     
/* 23 */     for (int var4 = 0; var4 < var0.getSize(); var4++) {
/* 24 */       ItemStack var5 = var0.getItem(var4);
/*    */       
/* 26 */       if (!var5.isEmpty()) {
/*    */ 
/*    */ 
/*    */         
/* 30 */         if (Block.asBlock(var5.getItem()) instanceof BlockShulkerBox) {
/* 31 */           var2++;
/* 32 */         } else if (var5.getItem() instanceof ItemDye) {
/* 33 */           var3++;
/*    */         } else {
/* 35 */           return false;
/*    */         } 
/*    */         
/* 38 */         if (var3 > 1 || var2 > 1) {
/* 39 */           return false;
/*    */         }
/*    */       } 
/*    */     } 
/* 43 */     return (var2 == 1 && var3 == 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 48 */     ItemStack var1 = ItemStack.b;
/* 49 */     ItemDye var2 = (ItemDye)Items.WHITE_DYE;
/*    */     
/* 51 */     for (int i = 0; i < var0.getSize(); i++) {
/* 52 */       ItemStack var4 = var0.getItem(i);
/*    */       
/* 54 */       if (!var4.isEmpty()) {
/*    */ 
/*    */ 
/*    */         
/* 58 */         Item var5 = var4.getItem();
/* 59 */         if (Block.asBlock(var5) instanceof BlockShulkerBox) {
/* 60 */           var1 = var4;
/* 61 */         } else if (var5 instanceof ItemDye) {
/* 62 */           var2 = (ItemDye)var5;
/*    */         } 
/*    */       } 
/*    */     } 
/* 66 */     ItemStack var3 = BlockShulkerBox.b(var2.d());
/* 67 */     if (var1.hasTag()) {
/* 68 */       var3.setTag(var1.getTag().clone());
/*    */     }
/*    */     
/* 71 */     return var3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 81 */     return RecipeSerializer.m;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */