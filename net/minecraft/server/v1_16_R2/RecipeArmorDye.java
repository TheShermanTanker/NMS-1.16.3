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
/*    */ public class RecipeArmorDye
/*    */   extends IRecipeComplex
/*    */ {
/*    */   public RecipeArmorDye(MinecraftKey var0) {
/* 16 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 21 */     ItemStack var2 = ItemStack.b;
/* 22 */     List<ItemStack> var3 = Lists.newArrayList();
/*    */     
/* 24 */     for (int var4 = 0; var4 < var0.getSize(); var4++) {
/* 25 */       ItemStack var5 = var0.getItem(var4);
/* 26 */       if (!var5.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 30 */         if (var5.getItem() instanceof IDyeable) {
/* 31 */           if (!var2.isEmpty()) {
/* 32 */             return false;
/*    */           }
/* 34 */           var2 = var5;
/* 35 */         } else if (var5.getItem() instanceof ItemDye) {
/* 36 */           var3.add(var5);
/*    */         } else {
/* 38 */           return false;
/*    */         } 
/*    */       }
/*    */     } 
/* 42 */     return (!var2.isEmpty() && !var3.isEmpty());
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 47 */     List<ItemDye> var1 = Lists.newArrayList();
/* 48 */     ItemStack var2 = ItemStack.b;
/*    */     
/* 50 */     for (int var3 = 0; var3 < var0.getSize(); var3++) {
/* 51 */       ItemStack var4 = var0.getItem(var3);
/* 52 */       if (!var4.isEmpty()) {
/*    */ 
/*    */ 
/*    */         
/* 56 */         Item var5 = var4.getItem();
/* 57 */         if (var5 instanceof IDyeable) {
/* 58 */           if (!var2.isEmpty()) {
/* 59 */             return ItemStack.b;
/*    */           }
/*    */           
/* 62 */           var2 = var4.cloneItemStack();
/* 63 */         } else if (var5 instanceof ItemDye) {
/* 64 */           var1.add((ItemDye)var5);
/*    */         } else {
/* 66 */           return ItemStack.b;
/*    */         } 
/*    */       } 
/*    */     } 
/* 70 */     if (var2.isEmpty() || var1.isEmpty()) {
/* 71 */       return ItemStack.b;
/*    */     }
/*    */     
/* 74 */     return IDyeable.a(var2, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 84 */     return RecipeSerializer.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeArmorDye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */