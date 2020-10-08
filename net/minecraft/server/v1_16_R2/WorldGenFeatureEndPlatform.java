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
/*    */ 
/*    */ public class WorldGenFeatureEndPlatform
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/* 15 */   private static final BlockPosition a = new BlockPosition(8, 3, 8);
/* 16 */   private static final ChunkCoordIntPair ab = new ChunkCoordIntPair(a);
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenFeatureEndPlatform(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 21 */     super(var0);
/*    */   }
/*    */   
/*    */   private static int a(int var0, int var1, int var2, int var3) {
/* 25 */     return Math.max(Math.abs(var0 - var2), Math.abs(var1 - var3));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 30 */     ChunkCoordIntPair var5 = new ChunkCoordIntPair(var3);
/* 31 */     if (a(var5.x, var5.z, ab.x, ab.z) > 1) {
/* 32 */       return true;
/*    */     }
/*    */     
/* 35 */     BlockPosition.MutableBlockPosition var6 = new BlockPosition.MutableBlockPosition();
/* 36 */     for (int var7 = var5.e(); var7 <= var5.g(); var7++) {
/* 37 */       for (int var8 = var5.d(); var8 <= var5.f(); var8++) {
/* 38 */         if (a(a.getX(), a.getZ(), var8, var7) <= 16) {
/*    */ 
/*    */           
/* 41 */           var6.d(var8, a.getY(), var7);
/* 42 */           if (var6.equals(a)) {
/* 43 */             var0.setTypeAndData(var6, Blocks.COBBLESTONE.getBlockData(), 2);
/*    */           } else {
/* 45 */             var0.setTypeAndData(var6, Blocks.STONE.getBlockData(), 2);
/*    */           } 
/*    */         } 
/*    */       } 
/* 49 */     }  return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureEndPlatform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */