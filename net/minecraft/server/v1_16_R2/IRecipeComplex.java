/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftComplexRecipe;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ public abstract class IRecipeComplex implements RecipeCrafting {
/*    */   public IRecipeComplex(MinecraftKey minecraftkey) {
/*  8 */     this.a = minecraftkey;
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey getKey() {
/* 13 */     return this.a;
/*    */   }
/*    */   private final MinecraftKey a;
/*    */   
/*    */   public boolean isComplex() {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getResult() {
/* 23 */     return ItemStack.b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Recipe toBukkitRecipe() {
/* 29 */     return (Recipe)new CraftComplexRecipe(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IRecipeComplex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */