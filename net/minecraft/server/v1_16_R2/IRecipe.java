/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IRecipe<C extends IInventory>
/*    */ {
/*    */   boolean a(C paramC, World paramWorld);
/*    */   
/*    */   default NonNullList<ItemStack> b(C c0) {
/* 12 */     NonNullList<ItemStack> nonnulllist = NonNullList.a(c0.getSize(), ItemStack.b);
/*    */     
/* 14 */     for (int i = 0; i < nonnulllist.size(); i++) {
/* 15 */       Item item = c0.getItem(i).getItem();
/*    */       
/* 17 */       if (item.p()) {
/* 18 */         nonnulllist.set(i, new ItemStack(item.getCraftingRemainingItem()));
/*    */       }
/*    */     } 
/*    */     
/* 22 */     return nonnulllist;
/*    */   } ItemStack a(C paramC);
/*    */   ItemStack getResult();
/*    */   default NonNullList<RecipeItemStack> a() {
/* 26 */     return NonNullList.a();
/*    */   }
/*    */   
/*    */   default boolean isComplex() {
/* 30 */     return false;
/*    */   }
/*    */   
/*    */   MinecraftKey getKey();
/*    */   
/*    */   RecipeSerializer<?> getRecipeSerializer();
/*    */   
/*    */   Recipes<?> g();
/*    */   
/*    */   Recipe toBukkitRecipe();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */