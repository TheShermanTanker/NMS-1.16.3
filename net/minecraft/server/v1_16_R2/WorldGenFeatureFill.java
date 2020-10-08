/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureFill
/*    */   extends WorldGenerator<WorldGenFeatureFillConfiguration>
/*    */ {
/*    */   public WorldGenFeatureFill(Codec<WorldGenFeatureFillConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureFillConfiguration var4) {
/* 19 */     BlockPosition.MutableBlockPosition var5 = new BlockPosition.MutableBlockPosition();
/*    */     
/* 21 */     for (int var6 = 0; var6 < 16; var6++) {
/* 22 */       for (int var7 = 0; var7 < 16; var7++) {
/* 23 */         int var8 = var3.getX() + var6;
/* 24 */         int var9 = var3.getZ() + var7;
/* 25 */         int var10 = var4.b;
/* 26 */         var5.d(var8, var10, var9);
/*    */         
/* 28 */         if (var0.getType(var5).isAir()) {
/* 29 */           var0.setTypeAndData(var5, var4.c, 2);
/*    */         }
/*    */       } 
/*    */     } 
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureFill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */