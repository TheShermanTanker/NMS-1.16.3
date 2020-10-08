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
/*    */ public class WorldGenLiquids
/*    */   extends WorldGenerator<WorldGenFeatureHellFlowingLavaConfiguration>
/*    */ {
/*    */   public WorldGenLiquids(Codec<WorldGenFeatureHellFlowingLavaConfiguration> var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureHellFlowingLavaConfiguration var4) {
/* 20 */     if (!var4.f.contains(var0.getType(var3.up()).getBlock())) {
/* 21 */       return false;
/*    */     }
/* 23 */     if (var4.c && !var4.f.contains(var0.getType(var3.down()).getBlock())) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     IBlockData var5 = var0.getType(var3);
/* 28 */     if (!var5.isAir() && !var4.f.contains(var5.getBlock())) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     int var6 = 0;
/*    */     
/* 34 */     int var7 = 0;
/* 35 */     if (var4.f.contains(var0.getType(var3.west()).getBlock())) {
/* 36 */       var7++;
/*    */     }
/* 38 */     if (var4.f.contains(var0.getType(var3.east()).getBlock())) {
/* 39 */       var7++;
/*    */     }
/* 41 */     if (var4.f.contains(var0.getType(var3.north()).getBlock())) {
/* 42 */       var7++;
/*    */     }
/* 44 */     if (var4.f.contains(var0.getType(var3.south()).getBlock())) {
/* 45 */       var7++;
/*    */     }
/* 47 */     if (var4.f.contains(var0.getType(var3.down()).getBlock())) {
/* 48 */       var7++;
/*    */     }
/*    */     
/* 51 */     int var8 = 0;
/* 52 */     if (var0.isEmpty(var3.west())) {
/* 53 */       var8++;
/*    */     }
/* 55 */     if (var0.isEmpty(var3.east())) {
/* 56 */       var8++;
/*    */     }
/* 58 */     if (var0.isEmpty(var3.north())) {
/* 59 */       var8++;
/*    */     }
/* 61 */     if (var0.isEmpty(var3.south())) {
/* 62 */       var8++;
/*    */     }
/* 64 */     if (var0.isEmpty(var3.down())) {
/* 65 */       var8++;
/*    */     }
/*    */     
/* 68 */     if (var7 == var4.d && var8 == var4.e) {
/* 69 */       var0.setTypeAndData(var3, var4.b.getBlockData(), 2);
/* 70 */       var0.getFluidTickList().a(var3, var4.b.getType(), 0);
/* 71 */       var6++;
/*    */     } 
/*    */     
/* 74 */     return (var6 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenLiquids.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */