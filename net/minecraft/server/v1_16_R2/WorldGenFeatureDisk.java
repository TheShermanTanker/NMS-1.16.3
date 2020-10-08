/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureDisk
/*    */   extends WorldGenerator<WorldGenFeatureCircleConfiguration>
/*    */ {
/*    */   public WorldGenFeatureDisk(Codec<WorldGenFeatureCircleConfiguration> var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureCircleConfiguration var4) {
/* 20 */     boolean var5 = false;
/*    */     
/* 22 */     int var6 = var4.c.a(var2);
/* 23 */     for (int var7 = var3.getX() - var6; var7 <= var3.getX() + var6; var7++) {
/* 24 */       for (int var8 = var3.getZ() - var6; var8 <= var3.getZ() + var6; var8++) {
/* 25 */         int var9 = var7 - var3.getX();
/* 26 */         int var10 = var8 - var3.getZ();
/* 27 */         if (var9 * var9 + var10 * var10 <= var6 * var6)
/*    */         {
/*    */           
/* 30 */           for (int var11 = var3.getY() - var4.d; var11 <= var3.getY() + var4.d; var11++) {
/* 31 */             BlockPosition var12 = new BlockPosition(var7, var11, var8);
/* 32 */             Block var13 = var0.getType(var12).getBlock();
/*    */             
/* 34 */             for (IBlockData var15 : var4.e) {
/* 35 */               if (var15.a(var13)) {
/* 36 */                 var0.setTypeAndData(var12, var4.b, 2);
/* 37 */                 var5 = true;
/*    */                 break;
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/* 45 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDisk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */