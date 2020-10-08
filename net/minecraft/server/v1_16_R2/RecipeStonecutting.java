/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftRecipe;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftStonecuttingRecipe;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ public class RecipeStonecutting
/*    */   extends RecipeSingleItem
/*    */ {
/*    */   public RecipeStonecutting(MinecraftKey minecraftkey, String s, RecipeItemStack recipeitemstack, ItemStack itemstack) {
/* 14 */     super(Recipes.STONECUTTING, RecipeSerializer.t, minecraftkey, s, recipeitemstack, itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IInventory iinventory, World world) {
/* 19 */     return this.ingredient.test(iinventory.getItem(0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Recipe toBukkitRecipe() {
/* 25 */     CraftItemStack result = CraftItemStack.asCraftMirror(this.result);
/*    */     
/* 27 */     CraftStonecuttingRecipe recipe = new CraftStonecuttingRecipe(CraftNamespacedKey.fromMinecraft(this.key), (ItemStack)result, CraftRecipe.toBukkit(this.ingredient));
/* 28 */     recipe.setGroup(this.group);
/*    */     
/* 30 */     return (Recipe)recipe;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeStonecutting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */