/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ import net.minecraft.server.v1_16_R2.IRecipe;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import net.minecraft.server.v1_16_R2.RecipeStonecutting;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ import org.bukkit.inventory.StonecuttingRecipe;
/*    */ 
/*    */ public class CraftStonecuttingRecipe extends StonecuttingRecipe implements CraftRecipe {
/*    */   public CraftStonecuttingRecipe(NamespacedKey key, ItemStack result, RecipeChoice source) {
/* 12 */     super(key, result, source);
/*    */   }
/*    */   
/*    */   public static CraftStonecuttingRecipe fromBukkitRecipe(StonecuttingRecipe recipe) {
/* 16 */     if (recipe instanceof CraftStonecuttingRecipe) {
/* 17 */       return (CraftStonecuttingRecipe)recipe;
/*    */     }
/* 19 */     CraftStonecuttingRecipe ret = new CraftStonecuttingRecipe(recipe.getKey(), recipe.getResult(), recipe.getInputChoice());
/* 20 */     ret.setGroup(recipe.getGroup());
/* 21 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addToCraftingManager() {
/* 26 */     ItemStack result = getResult();
/*    */     
/* 28 */     MinecraftServer.getServer().getCraftingManager().addRecipe((IRecipe)new RecipeStonecutting(CraftNamespacedKey.toMinecraft(getKey()), getGroup(), toNMS(getInputChoice(), true), CraftItemStack.asNMSCopy(result)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftStonecuttingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */