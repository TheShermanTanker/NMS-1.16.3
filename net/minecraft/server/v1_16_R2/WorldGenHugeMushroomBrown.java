/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenHugeMushroomBrown
/*    */   extends WorldGenMushrooms
/*    */ {
/*    */   public WorldGenHugeMushroomBrown(Codec<WorldGenFeatureMushroomConfiguration> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(GeneratorAccess var0, Random var1, BlockPosition var2, int var3, BlockPosition.MutableBlockPosition var4, WorldGenFeatureMushroomConfiguration var5) {
/* 18 */     int var6 = var5.d;
/* 19 */     for (int var7 = -var6; var7 <= var6; var7++) {
/* 20 */       for (int var8 = -var6; var8 <= var6; var8++) {
/* 21 */         boolean var9 = (var7 == -var6);
/* 22 */         boolean var10 = (var7 == var6);
/* 23 */         boolean var11 = (var8 == -var6);
/* 24 */         boolean var12 = (var8 == var6);
/*    */         
/* 26 */         boolean var13 = (var9 || var10);
/* 27 */         boolean var14 = (var11 || var12);
/* 28 */         if (!var13 || !var14) {
/*    */ 
/*    */ 
/*    */           
/* 32 */           var4.a(var2, var7, var3, var8);
/* 33 */           if (!var0.getType(var4).i(var0, var4)) {
/* 34 */             boolean var15 = (var9 || (var14 && var7 == 1 - var6));
/* 35 */             boolean var16 = (var10 || (var14 && var7 == var6 - 1));
/* 36 */             boolean var17 = (var11 || (var13 && var8 == 1 - var6));
/* 37 */             boolean var18 = (var12 || (var13 && var8 == var6 - 1));
/* 38 */             a(var0, var4, var5.b.a(var1, var2)
/* 39 */                 .set(BlockHugeMushroom.d, Boolean.valueOf(var15))
/* 40 */                 .set(BlockHugeMushroom.b, Boolean.valueOf(var16))
/* 41 */                 .set(BlockHugeMushroom.a, Boolean.valueOf(var17))
/* 42 */                 .set(BlockHugeMushroom.c, Boolean.valueOf(var18)));
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected int a(int var0, int var1, int var2, int var3) {
/* 51 */     return (var3 <= 3) ? 0 : var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenHugeMushroomBrown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */