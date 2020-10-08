/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftCampfireRecipe;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftRecipe;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ public class RecipeCampfire
/*    */   extends RecipeCooking
/*    */ {
/*    */   public RecipeCampfire(MinecraftKey minecraftkey, String s, RecipeItemStack recipeitemstack, ItemStack itemstack, float f, int i) {
/* 14 */     super(Recipes.CAMPFIRE_COOKING, minecraftkey, s, recipeitemstack, itemstack, f, i);
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 19 */     return RecipeSerializer.s;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Recipe toBukkitRecipe() {
/* 25 */     CraftItemStack result = CraftItemStack.asCraftMirror(this.result);
/*    */     
/* 27 */     CraftCampfireRecipe recipe = new CraftCampfireRecipe(CraftNamespacedKey.fromMinecraft(this.key), (ItemStack)result, CraftRecipe.toBukkit(this.ingredient), this.experience, this.cookingTime);
/* 28 */     recipe.setGroup(this.group);
/*    */     
/* 30 */     return (Recipe)recipe;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeCampfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */