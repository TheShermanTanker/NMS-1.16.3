/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.v1_16_R2.IRecipe;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import net.minecraft.server.v1_16_R2.NonNullList;
/*    */ import net.minecraft.server.v1_16_R2.RecipeItemStack;
/*    */ import net.minecraft.server.v1_16_R2.ShapedRecipes;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ import org.bukkit.inventory.ShapedRecipe;
/*    */ 
/*    */ public class CraftShapedRecipe
/*    */   extends ShapedRecipe implements CraftRecipe {
/*    */   public CraftShapedRecipe(NamespacedKey key, ItemStack result) {
/* 19 */     super(key, result);
/*    */   }
/*    */   private ShapedRecipes recipe;
/*    */   public CraftShapedRecipe(ItemStack result, ShapedRecipes recipe) {
/* 23 */     this(CraftNamespacedKey.fromMinecraft(recipe.getKey()), result);
/* 24 */     this.recipe = recipe;
/*    */   }
/*    */   
/*    */   public static CraftShapedRecipe fromBukkitRecipe(ShapedRecipe recipe) {
/* 28 */     if (recipe instanceof CraftShapedRecipe) {
/* 29 */       return (CraftShapedRecipe)recipe;
/*    */     }
/* 31 */     CraftShapedRecipe ret = new CraftShapedRecipe(recipe.getKey(), recipe.getResult());
/* 32 */     ret.setGroup(recipe.getGroup());
/* 33 */     String[] shape = recipe.getShape();
/* 34 */     ret.shape(shape);
/* 35 */     Map<Character, RecipeChoice> ingredientMap = recipe.getChoiceMap();
/* 36 */     for (Iterator<Character> iterator = ingredientMap.keySet().iterator(); iterator.hasNext(); ) { char c = ((Character)iterator.next()).charValue();
/* 37 */       RecipeChoice stack = ingredientMap.get(Character.valueOf(c));
/* 38 */       if (stack != null) {
/* 39 */         ret.setIngredient(c, stack);
/*    */       } }
/*    */     
/* 42 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addToCraftingManager() {
/* 47 */     String[] shape = getShape();
/* 48 */     Map<Character, RecipeChoice> ingred = getChoiceMap();
/* 49 */     int width = shape[0].length();
/* 50 */     NonNullList<RecipeItemStack> data = NonNullList.a(shape.length * width, RecipeItemStack.a);
/*    */     
/* 52 */     for (int i = 0; i < shape.length; i++) {
/* 53 */       String row = shape[i];
/* 54 */       for (int j = 0; j < row.length(); j++) {
/* 55 */         data.set(i * width + j, toNMS(ingred.get(Character.valueOf(row.charAt(j))), false));
/*    */       }
/*    */     } 
/*    */     
/* 59 */     MinecraftServer.getServer().getCraftingManager().addRecipe((IRecipe)new ShapedRecipes(CraftNamespacedKey.toMinecraft(getKey()), getGroup(), width, shape.length, data, CraftItemStack.asNMSCopy(getResult())));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftShapedRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */