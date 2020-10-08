/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ 
/*   */ public interface RecipeCrafting
/*   */   extends IRecipe<InventoryCrafting>
/*   */ {
/*   */   default Recipes<?> g() {
/* 8 */     return Recipes.CRAFTING;
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */