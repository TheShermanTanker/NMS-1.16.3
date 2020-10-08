/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureCoralTree
/*    */   extends WorldGenFeatureCoral
/*    */ {
/*    */   public WorldGenFeatureCoralTree(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 17 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(GeneratorAccess var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 22 */     BlockPosition.MutableBlockPosition var4 = var2.i();
/*    */     
/* 24 */     int var5 = var1.nextInt(3) + 1;
/* 25 */     for (int i = 0; i < var5; i++) {
/* 26 */       if (!b(var0, var1, var4, var3)) {
/* 27 */         return true;
/*    */       }
/* 29 */       var4.c(EnumDirection.UP);
/*    */     } 
/* 31 */     BlockPosition var6 = var4.immutableCopy();
/*    */     
/* 33 */     int var7 = var1.nextInt(3) + 2;
/* 34 */     List<EnumDirection> var8 = Lists.newArrayList(EnumDirection.EnumDirectionLimit.HORIZONTAL);
/* 35 */     Collections.shuffle(var8, var1);
/* 36 */     List<EnumDirection> var9 = var8.subList(0, var7);
/*    */     
/* 38 */     for (EnumDirection var11 : var9) {
/* 39 */       var4.g(var6);
/* 40 */       var4.c(var11);
/*    */       
/* 42 */       int var12 = var1.nextInt(5) + 2;
/* 43 */       int var13 = 0;
/* 44 */       for (int var14 = 0; var14 < var12 && 
/* 45 */         b(var0, var1, var4, var3); var14++) {
/*    */ 
/*    */         
/* 48 */         var13++;
/* 49 */         var4.c(EnumDirection.UP);
/*    */         
/* 51 */         if (var14 == 0 || (var13 >= 2 && var1.nextFloat() < 0.25F)) {
/* 52 */           var4.c(var11);
/* 53 */           var13 = 0;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureCoralTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */