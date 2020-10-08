/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ import net.minecraft.server.v1_16_R2.FurnaceRecipe;
/*    */ import net.minecraft.server.v1_16_R2.IRecipe;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.FurnaceRecipe;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ 
/*    */ public class CraftFurnaceRecipe extends FurnaceRecipe implements CraftRecipe {
/*    */   public CraftFurnaceRecipe(NamespacedKey key, ItemStack result, RecipeChoice source, float experience, int cookingTime) {
/* 12 */     super(key, result, source, experience, cookingTime);
/*    */   }
/*    */   
/*    */   public static CraftFurnaceRecipe fromBukkitRecipe(FurnaceRecipe recipe) {
/* 16 */     if (recipe instanceof CraftFurnaceRecipe) {
/* 17 */       return (CraftFurnaceRecipe)recipe;
/*    */     }
/* 19 */     CraftFurnaceRecipe ret = new CraftFurnaceRecipe(recipe.getKey(), recipe.getResult(), recipe.getInputChoice(), recipe.getExperience(), recipe.getCookingTime());
/* 20 */     ret.setGroup(recipe.getGroup());
/* 21 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addToCraftingManager() {
/* 26 */     ItemStack result = getResult();
/*    */     
/* 28 */     MinecraftServer.getServer().getCraftingManager().addRecipe((IRecipe)new FurnaceRecipe(CraftNamespacedKey.toMinecraft(getKey()), getGroup(), toNMS(getInputChoice(), true), CraftItemStack.asNMSCopy(result), getExperience(), getCookingTime()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftFurnaceRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */