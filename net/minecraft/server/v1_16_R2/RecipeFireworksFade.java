/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeFireworksFade
/*    */   extends IRecipeComplex
/*    */ {
/* 16 */   private static final RecipeItemStack a = RecipeItemStack.a(new IMaterial[] { Items.FIREWORK_STAR });
/*    */   
/*    */   public RecipeFireworksFade(MinecraftKey var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 24 */     boolean var2 = false;
/* 25 */     boolean var3 = false;
/*    */     
/* 27 */     for (int var4 = 0; var4 < var0.getSize(); var4++) {
/* 28 */       ItemStack var5 = var0.getItem(var4);
/* 29 */       if (!var5.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 33 */         if (var5.getItem() instanceof ItemDye) {
/* 34 */           var2 = true;
/* 35 */         } else if (a.test(var5)) {
/* 36 */           if (var3) {
/* 37 */             return false;
/*    */           }
/* 39 */           var3 = true;
/*    */         } else {
/* 41 */           return false;
/*    */         } 
/*    */       }
/*    */     } 
/* 45 */     return (var3 && var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 50 */     List<Integer> var1 = Lists.newArrayList();
/* 51 */     ItemStack var2 = null;
/*    */     
/* 53 */     for (int var3 = 0; var3 < var0.getSize(); var3++) {
/* 54 */       ItemStack var4 = var0.getItem(var3);
/*    */       
/* 56 */       Item var5 = var4.getItem();
/* 57 */       if (var5 instanceof ItemDye) {
/* 58 */         var1.add(Integer.valueOf(((ItemDye)var5).d().getFireworksColor()));
/* 59 */       } else if (a.test(var4)) {
/* 60 */         var2 = var4.cloneItemStack();
/* 61 */         var2.setCount(1);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 66 */     if (var2 == null || var1.isEmpty()) {
/* 67 */       return ItemStack.b;
/*    */     }
/*    */     
/* 70 */     var2.a("Explosion").b("FadeColors", var1);
/*    */     
/* 72 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 82 */     return RecipeSerializer.i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeFireworksFade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */