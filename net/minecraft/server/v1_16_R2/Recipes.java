/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Recipes<T extends IRecipe<?>>
/*    */ {
/* 11 */   public static final Recipes<RecipeCrafting> CRAFTING = a("crafting");
/* 12 */   public static final Recipes<FurnaceRecipe> SMELTING = a("smelting");
/* 13 */   public static final Recipes<RecipeBlasting> BLASTING = a("blasting");
/* 14 */   public static final Recipes<RecipeSmoking> SMOKING = a("smoking");
/* 15 */   public static final Recipes<RecipeCampfire> CAMPFIRE_COOKING = a("campfire_cooking");
/* 16 */   public static final Recipes<RecipeStonecutting> STONECUTTING = a("stonecutting");
/* 17 */   public static final Recipes<RecipeSmithing> SMITHING = a("smithing");
/*    */   
/*    */   static <T extends IRecipe<?>> Recipes<T> a(String var0) {
/* 20 */     return IRegistry.<Recipes<?>, Recipes<T>>a(IRegistry.RECIPE_TYPE, new MinecraftKey(var0), new Recipes<T>(var0)
/*    */         {
/*    */           public String toString() {
/* 23 */             return this.h;
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   default <C extends IInventory> Optional<T> a(IRecipe<C> var0, World var1, C var2) {
/* 30 */     return var0.a(var2, var1) ? Optional.<T>of((T)var0) : Optional.<T>empty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Recipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */