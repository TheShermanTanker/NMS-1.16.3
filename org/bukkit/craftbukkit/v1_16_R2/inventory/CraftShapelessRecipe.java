/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.IRecipe;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import net.minecraft.server.v1_16_R2.NonNullList;
/*    */ import net.minecraft.server.v1_16_R2.RecipeItemStack;
/*    */ import net.minecraft.server.v1_16_R2.ShapelessRecipes;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ import org.bukkit.inventory.ShapelessRecipe;
/*    */ 
/*    */ public class CraftShapelessRecipe extends ShapelessRecipe implements CraftRecipe {
/*    */   private ShapelessRecipes recipe;
/*    */   
/*    */   public CraftShapelessRecipe(NamespacedKey key, ItemStack result) {
/* 19 */     super(key, result);
/*    */   }
/*    */   
/*    */   public CraftShapelessRecipe(ItemStack result, ShapelessRecipes recipe) {
/* 23 */     this(CraftNamespacedKey.fromMinecraft(recipe.getKey()), result);
/* 24 */     this.recipe = recipe;
/*    */   }
/*    */   
/*    */   public static CraftShapelessRecipe fromBukkitRecipe(ShapelessRecipe recipe) {
/* 28 */     if (recipe instanceof CraftShapelessRecipe) {
/* 29 */       return (CraftShapelessRecipe)recipe;
/*    */     }
/* 31 */     CraftShapelessRecipe ret = new CraftShapelessRecipe(recipe.getKey(), recipe.getResult());
/* 32 */     ret.setGroup(recipe.getGroup());
/* 33 */     for (RecipeChoice ingred : recipe.getChoiceList()) {
/* 34 */       ret.addIngredient(ingred);
/*    */     }
/* 36 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addToCraftingManager() {
/* 41 */     List<RecipeChoice> ingred = getChoiceList();
/* 42 */     NonNullList<RecipeItemStack> data = NonNullList.a(ingred.size(), RecipeItemStack.a);
/* 43 */     for (int i = 0; i < ingred.size(); i++) {
/* 44 */       data.set(i, toNMS(ingred.get(i), true));
/*    */     }
/*    */     
/* 47 */     MinecraftServer.getServer().getCraftingManager().addRecipe((IRecipe)new ShapelessRecipes(CraftNamespacedKey.toMinecraft(getKey()), getGroup(), CraftItemStack.asNMSCopy(getResult()), data));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftShapelessRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */