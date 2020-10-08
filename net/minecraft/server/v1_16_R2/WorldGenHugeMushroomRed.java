/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenHugeMushroomRed
/*    */   extends WorldGenMushrooms
/*    */ {
/*    */   public WorldGenHugeMushroomRed(Codec<WorldGenFeatureMushroomConfiguration> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(GeneratorAccess var0, Random var1, BlockPosition var2, int var3, BlockPosition.MutableBlockPosition var4, WorldGenFeatureMushroomConfiguration var5) {
/* 18 */     for (int var6 = var3 - 3; var6 <= var3; var6++) {
/* 19 */       int var7 = (var6 < var3) ? var5.d : (var5.d - 1);
/* 20 */       int var8 = var5.d - 2;
/*    */       
/* 22 */       for (int var9 = -var7; var9 <= var7; var9++) {
/* 23 */         for (int var10 = -var7; var10 <= var7; var10++) {
/* 24 */           boolean var11 = (var9 == -var7);
/* 25 */           boolean var12 = (var9 == var7);
/* 26 */           boolean var13 = (var10 == -var7);
/* 27 */           boolean var14 = (var10 == var7);
/*    */           
/* 29 */           boolean var15 = (var11 || var12);
/* 30 */           boolean var16 = (var13 || var14);
/*    */           
/* 32 */           if (var6 >= var3 || var15 != var16) {
/*    */ 
/*    */ 
/*    */             
/* 36 */             var4.a(var2, var9, var6, var10);
/* 37 */             if (!var0.getType(var4).i(var0, var4)) {
/* 38 */               a(var0, var4, var5.b.a(var1, var2)
/* 39 */                   .set(BlockHugeMushroom.e, Boolean.valueOf((var6 >= var3 - 1)))
/* 40 */                   .set(BlockHugeMushroom.d, Boolean.valueOf((var9 < -var8)))
/* 41 */                   .set(BlockHugeMushroom.b, Boolean.valueOf((var9 > var8)))
/* 42 */                   .set(BlockHugeMushroom.a, Boolean.valueOf((var10 < -var8)))
/* 43 */                   .set(BlockHugeMushroom.c, Boolean.valueOf((var10 > var8))));
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected int a(int var0, int var1, int var2, int var3) {
/* 53 */     int var4 = 0;
/* 54 */     if (var3 < var1 && var3 >= var1 - 3) {
/* 55 */       var4 = var2;
/* 56 */     } else if (var3 == var1) {
/* 57 */       var4 = var2;
/*    */     } 
/* 59 */     return var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenHugeMushroomRed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */