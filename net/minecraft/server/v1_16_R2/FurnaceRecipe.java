/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftFurnaceRecipe;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftRecipe;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FurnaceRecipe
/*    */   extends RecipeCooking
/*    */ {
/*    */   public FurnaceRecipe(MinecraftKey minecraftkey, String s, RecipeItemStack recipeitemstack, ItemStack itemstack, float f, int i) {
/* 17 */     super(Recipes.SMELTING, minecraftkey, s, recipeitemstack, itemstack, f, i);
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 22 */     return RecipeSerializer.p;
/*    */   }
/*    */ 
/*    */   
/*    */   public Recipe toBukkitRecipe() {
/* 27 */     CraftItemStack result = CraftItemStack.asCraftMirror(this.result);
/*    */     
/* 29 */     CraftFurnaceRecipe recipe = new CraftFurnaceRecipe(CraftNamespacedKey.fromMinecraft(this.key), (ItemStack)result, CraftRecipe.toBukkit(this.ingredient), this.experience, this.cookingTime);
/* 30 */     recipe.setGroup(this.group);
/*    */     
/* 32 */     return (Recipe)recipe;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FurnaceRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */