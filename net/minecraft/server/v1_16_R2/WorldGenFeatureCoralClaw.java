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
/*    */ 
/*    */ public class WorldGenFeatureCoralClaw
/*    */   extends WorldGenFeatureCoral
/*    */ {
/*    */   public WorldGenFeatureCoralClaw(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(GeneratorAccess var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 23 */     if (!b(var0, var1, var2, var3)) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     EnumDirection var4 = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var1);
/* 28 */     int var5 = var1.nextInt(2) + 2;
/*    */     
/* 30 */     List<EnumDirection> var6 = Lists.newArrayList((Object[])new EnumDirection[] { var4, var4.g(), var4.h() });
/* 31 */     Collections.shuffle(var6, var1);
/* 32 */     List<EnumDirection> var7 = var6.subList(0, var5);
/*    */     
/* 34 */     for (EnumDirection var9 : var7) {
/* 35 */       int var12; EnumDirection var13; BlockPosition.MutableBlockPosition var10 = var2.i();
/* 36 */       int var11 = var1.nextInt(2) + 1;
/*    */ 
/*    */ 
/*    */       
/* 40 */       var10.c(var9);
/* 41 */       if (var9 == var4) {
/* 42 */         var13 = var4;
/* 43 */         var12 = var1.nextInt(3) + 2;
/*    */       } else {
/* 45 */         var10.c(EnumDirection.UP);
/*    */ 
/*    */         
/* 48 */         EnumDirection[] arrayOfEnumDirection = { var9, EnumDirection.UP };
/* 49 */         var13 = SystemUtils.<EnumDirection>a(arrayOfEnumDirection, var1);
/* 50 */         var12 = var1.nextInt(3) + 3;
/*    */       } 
/*    */       int var14;
/* 53 */       for (var14 = 0; var14 < var11 && 
/* 54 */         b(var0, var1, var10, var3); var14++)
/*    */       {
/*    */         
/* 57 */         var10.c(var13);
/*    */       }
/* 59 */       var10.c(var13.opposite());
/* 60 */       var10.c(EnumDirection.UP);
/*    */       
/* 62 */       for (var14 = 0; var14 < var12; var14++) {
/* 63 */         var10.c(var4);
/* 64 */         if (!b(var0, var1, var10, var3)) {
/*    */           break;
/*    */         }
/*    */         
/* 68 */         if (var1.nextFloat() < 0.25F) {
/* 69 */           var10.c(EnumDirection.UP);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureCoralClaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */