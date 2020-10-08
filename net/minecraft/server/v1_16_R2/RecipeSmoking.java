/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftRecipe;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftSmokingRecipe;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ public class RecipeSmoking
/*    */   extends RecipeCooking
/*    */ {
/*    */   public RecipeSmoking(MinecraftKey minecraftkey, String s, RecipeItemStack recipeitemstack, ItemStack itemstack, float f, int i) {
/* 14 */     super(Recipes.SMOKING, minecraftkey, s, recipeitemstack, itemstack, f, i);
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 19 */     return RecipeSerializer.r;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Recipe toBukkitRecipe() {
/* 25 */     CraftItemStack result = CraftItemStack.asCraftMirror(this.result);
/*    */     
/* 27 */     CraftSmokingRecipe recipe = new CraftSmokingRecipe(CraftNamespacedKey.fromMinecraft(this.key), (ItemStack)result, CraftRecipe.toBukkit(this.ingredient), this.experience, this.cookingTime);
/* 28 */     recipe.setGroup(this.group);
/*    */     
/* 30 */     return (Recipe)recipe;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeSmoking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */