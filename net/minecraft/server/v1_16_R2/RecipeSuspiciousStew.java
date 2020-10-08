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
/*    */ public class RecipeSuspiciousStew
/*    */   extends IRecipeComplex
/*    */ {
/*    */   public RecipeSuspiciousStew(MinecraftKey var0) {
/* 17 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 22 */     boolean var2 = false;
/* 23 */     boolean var3 = false;
/* 24 */     boolean var4 = false;
/* 25 */     boolean var5 = false;
/*    */     
/* 27 */     for (int var6 = 0; var6 < var0.getSize(); var6++) {
/* 28 */       ItemStack var7 = var0.getItem(var6);
/* 29 */       if (!var7.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 33 */         if (var7.getItem() == Blocks.BROWN_MUSHROOM.getItem() && !var4) {
/* 34 */           var4 = true;
/* 35 */         } else if (var7.getItem() == Blocks.RED_MUSHROOM.getItem() && !var3) {
/* 36 */           var3 = true;
/* 37 */         } else if (var7.getItem().a(TagsItem.SMALL_FLOWERS) && !var2) {
/* 38 */           var2 = true;
/* 39 */         } else if (var7.getItem() == Items.BOWL && !var5) {
/* 40 */           var5 = true;
/*    */         } else {
/* 42 */           return false;
/*    */         } 
/*    */       }
/*    */     } 
/* 46 */     return (var2 && var4 && var3 && var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 51 */     ItemStack var1 = ItemStack.b;
/* 52 */     for (int i = 0; i < var0.getSize(); i++) {
/* 53 */       ItemStack var3 = var0.getItem(i);
/* 54 */       if (!var3.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 58 */         if (var3.getItem().a(TagsItem.SMALL_FLOWERS)) {
/* 59 */           var1 = var3;
/*    */           break;
/*    */         } 
/*    */       }
/*    */     } 
/* 64 */     ItemStack var2 = new ItemStack(Items.SUSPICIOUS_STEW, 1);
/* 65 */     if (var1.getItem() instanceof ItemBlock && ((ItemBlock)var1.getItem()).getBlock() instanceof BlockFlowers) {
/* 66 */       BlockFlowers var3 = (BlockFlowers)((ItemBlock)var1.getItem()).getBlock();
/* 67 */       MobEffectList var4 = var3.c();
/* 68 */       ItemSuspiciousStew.a(var2, var4, var3.d());
/*    */     } 
/*    */     
/* 71 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 81 */     return RecipeSerializer.n;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeSuspiciousStew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */