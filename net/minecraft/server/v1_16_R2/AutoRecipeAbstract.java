/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface AutoRecipeAbstract<T>
/*    */ {
/*    */   default void a(int var0, int var1, int var2, IRecipe<?> var3, Iterator<T> var4, int var5) {
/* 11 */     int var6 = var0;
/* 12 */     int var7 = var1;
/*    */     
/* 14 */     if (var3 instanceof ShapedRecipes) {
/* 15 */       ShapedRecipes shapedRecipes = (ShapedRecipes)var3;
/* 16 */       var6 = shapedRecipes.i();
/* 17 */       var7 = shapedRecipes.j();
/*    */     } 
/*    */     
/* 20 */     int var8 = 0;
/* 21 */     for (int var9 = 0; var9 < var1; var9++) {
/* 22 */       if (var8 == var2) {
/* 23 */         var8++;
/*    */       }
/*    */       
/* 26 */       boolean var10 = (var7 < var1 / 2.0F);
/* 27 */       int var11 = MathHelper.d(var1 / 2.0F - var7 / 2.0F);
/*    */       
/* 29 */       if (var10 && var11 > var9) {
/* 30 */         var8 += var0;
/* 31 */         var9++;
/*    */       } 
/*    */       
/* 34 */       for (int var12 = 0; var12 < var0; var12++) {
/* 35 */         if (!var4.hasNext()) {
/*    */           return;
/*    */         }
/*    */         
/* 39 */         var10 = (var6 < var0 / 2.0F);
/* 40 */         var11 = MathHelper.d(var0 / 2.0F - var6 / 2.0F);
/* 41 */         int var13 = var6;
/* 42 */         boolean var14 = (var12 < var6);
/* 43 */         if (var10) {
/* 44 */           var13 = var11 + var6;
/* 45 */           var14 = (var11 <= var12 && var12 < var11 + var6);
/*    */         } 
/*    */ 
/*    */         
/* 49 */         if (var14) {
/* 50 */           a(var4, var8, var5, var9, var12);
/* 51 */         } else if (var13 == var12) {
/* 52 */           var8 += var0 - var12;
/*    */           
/*    */           break;
/*    */         } 
/* 56 */         var8++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   void a(Iterator<T> paramIterator, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AutoRecipeAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */