/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface RecipeHolder
/*    */ {
/*    */   void a(@Nullable IRecipe<?> paramIRecipe);
/*    */   
/*    */   @Nullable
/*    */   IRecipe<?> ak_();
/*    */   
/*    */   default void b(EntityHuman var0) {
/* 19 */     IRecipe<?> var1 = ak_();
/* 20 */     if (var1 != null && !var1.isComplex()) {
/* 21 */       var0.discoverRecipes(Collections.singleton(var1));
/* 22 */       a(null);
/*    */     } 
/*    */   }
/*    */   
/*    */   default boolean a(World var0, EntityPlayer var1, IRecipe<?> var2) {
/* 27 */     if (var2.isComplex() || !var0.getGameRules().getBoolean(GameRules.DO_LIMITED_CRAFTING) || var1.getRecipeBook().b(var2)) {
/* 28 */       a(var2);
/* 29 */       return true;
/*    */     } 
/*    */     
/* 32 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */