/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IRecipe;
/*    */ import net.minecraft.server.v1_16_R2.IRecipeComplex;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.inventory.ComplexRecipe;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftComplexRecipe implements CraftRecipe, ComplexRecipe {
/*    */   private final IRecipeComplex recipe;
/*    */   
/*    */   public CraftComplexRecipe(IRecipeComplex recipe) {
/* 15 */     this.recipe = recipe;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getResult() {
/* 20 */     return CraftItemStack.asCraftMirror(this.recipe.getResult());
/*    */   }
/*    */ 
/*    */   
/*    */   public NamespacedKey getKey() {
/* 25 */     return CraftNamespacedKey.fromMinecraft(this.recipe.getKey());
/*    */   }
/*    */ 
/*    */   
/*    */   public void addToCraftingManager() {
/* 30 */     MinecraftServer.getServer().getCraftingManager().addRecipe((IRecipe)this.recipe);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftComplexRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */