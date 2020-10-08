/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ import net.minecraft.server.v1_16_R2.IRecipe;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import net.minecraft.server.v1_16_R2.RecipeSmithing;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ import org.bukkit.inventory.SmithingRecipe;
/*    */ 
/*    */ public class CraftSmithingRecipe extends SmithingRecipe implements CraftRecipe {
/*    */   public CraftSmithingRecipe(NamespacedKey key, ItemStack result, RecipeChoice base, RecipeChoice addition) {
/* 12 */     super(key, result, base, addition);
/*    */   }
/*    */   
/*    */   public static CraftSmithingRecipe fromBukkitRecipe(SmithingRecipe recipe) {
/* 16 */     if (recipe instanceof CraftSmithingRecipe) {
/* 17 */       return (CraftSmithingRecipe)recipe;
/*    */     }
/* 19 */     CraftSmithingRecipe ret = new CraftSmithingRecipe(recipe.getKey(), recipe.getResult(), recipe.getBase(), recipe.getAddition());
/* 20 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addToCraftingManager() {
/* 25 */     ItemStack result = getResult();
/*    */     
/* 27 */     MinecraftServer.getServer().getCraftingManager().addRecipe((IRecipe)new RecipeSmithing(CraftNamespacedKey.toMinecraft(getKey()), toNMS(getBase(), true), toNMS(getAddition(), true), CraftItemStack.asNMSCopy(result)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftSmithingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */