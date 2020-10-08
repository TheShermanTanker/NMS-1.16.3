/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ import net.minecraft.server.v1_16_R2.IRecipe;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import net.minecraft.server.v1_16_R2.RecipeBlasting;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.BlastingRecipe;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ 
/*    */ public class CraftBlastingRecipe extends BlastingRecipe implements CraftRecipe {
/*    */   public CraftBlastingRecipe(NamespacedKey key, ItemStack result, RecipeChoice source, float experience, int cookingTime) {
/* 12 */     super(key, result, source, experience, cookingTime);
/*    */   }
/*    */   
/*    */   public static CraftBlastingRecipe fromBukkitRecipe(BlastingRecipe recipe) {
/* 16 */     if (recipe instanceof CraftBlastingRecipe) {
/* 17 */       return (CraftBlastingRecipe)recipe;
/*    */     }
/* 19 */     CraftBlastingRecipe ret = new CraftBlastingRecipe(recipe.getKey(), recipe.getResult(), recipe.getInputChoice(), recipe.getExperience(), recipe.getCookingTime());
/* 20 */     ret.setGroup(recipe.getGroup());
/* 21 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addToCraftingManager() {
/* 26 */     ItemStack result = getResult();
/*    */     
/* 28 */     MinecraftServer.getServer().getCraftingManager().addRecipe((IRecipe)new RecipeBlasting(CraftNamespacedKey.toMinecraft(getKey()), getGroup(), toNMS(getInputChoice(), true), CraftItemStack.asNMSCopy(result), getExperience(), getCookingTime()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftBlastingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */