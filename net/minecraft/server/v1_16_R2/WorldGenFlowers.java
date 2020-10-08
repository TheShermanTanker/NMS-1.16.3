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
/*    */ 
/*    */ public abstract class WorldGenFlowers<U extends WorldGenFeatureConfiguration>
/*    */   extends WorldGenerator<U>
/*    */ {
/*    */   public WorldGenFlowers(Codec<U> var0) {
/* 17 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, U var4) {
/* 22 */     IBlockData var5 = b(var2, var3, var4);
/* 23 */     int var6 = 0;
/* 24 */     for (int var7 = 0; var7 < a(var4); var7++) {
/* 25 */       BlockPosition var8 = a(var2, var3, var4);
/*    */       
/* 27 */       if (var0.isEmpty(var8) && var8.getY() < 255 && var5.canPlace(var0, var8) && a(var0, var8, var4)) {
/* 28 */         var0.setTypeAndData(var8, var5, 2);
/* 29 */         var6++;
/*    */       } 
/*    */     } 
/*    */     
/* 33 */     return (var6 > 0);
/*    */   }
/*    */   
/*    */   public abstract boolean a(GeneratorAccess paramGeneratorAccess, BlockPosition paramBlockPosition, U paramU);
/*    */   
/*    */   public abstract int a(U paramU);
/*    */   
/*    */   public abstract BlockPosition a(Random paramRandom, BlockPosition paramBlockPosition, U paramU);
/*    */   
/*    */   public abstract IBlockData b(Random paramRandom, BlockPosition paramBlockPosition, U paramU);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFlowers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */