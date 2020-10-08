/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
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
/*    */ 
/*    */ public class WorldGenFeatureDelta
/*    */   extends WorldGenerator<WorldGenFeatureDeltaConfiguration>
/*    */ {
/* 18 */   private static final ImmutableList<Block> a = ImmutableList.of(Blocks.BEDROCK, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_WART, Blocks.CHEST, Blocks.SPAWNER);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   private static final EnumDirection[] ab = EnumDirection.values();
/*    */ 
/*    */   
/*    */   public WorldGenFeatureDelta(Codec<WorldGenFeatureDeltaConfiguration> var0) {
/* 29 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureDeltaConfiguration var4) {
/* 34 */     boolean var5 = false;
/* 35 */     boolean var6 = (var2.nextDouble() < 0.9D);
/* 36 */     int var7 = var6 ? var4.e().a(var2) : 0;
/* 37 */     int var8 = var6 ? var4.e().a(var2) : 0;
/* 38 */     boolean var9 = (var6 && var7 != 0 && var8 != 0);
/*    */     
/* 40 */     int var10 = var4.d().a(var2);
/* 41 */     int var11 = var4.d().a(var2);
/* 42 */     int var12 = Math.max(var10, var11);
/* 43 */     for (BlockPosition var14 : BlockPosition.a(var3, var10, 0, var11)) {
/* 44 */       if (var14.k(var3) > var12) {
/*    */         break;
/*    */       }
/*    */       
/* 48 */       if (a(var0, var14, var4)) {
/* 49 */         if (var9) {
/* 50 */           var5 = true;
/* 51 */           a(var0, var14, var4.c());
/*    */         } 
/*    */         
/* 54 */         BlockPosition var15 = var14.b(var7, 0, var8);
/* 55 */         if (a(var0, var15, var4)) {
/* 56 */           var5 = true;
/* 57 */           a(var0, var15, var4.b());
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 62 */     return var5;
/*    */   }
/*    */   
/*    */   private static boolean a(GeneratorAccess var0, BlockPosition var1, WorldGenFeatureDeltaConfiguration var2) {
/* 66 */     IBlockData var3 = var0.getType(var1);
/* 67 */     if (var3.a(var2.b().getBlock())) {
/* 68 */       return false;
/*    */     }
/*    */     
/* 71 */     if (a.contains(var3.getBlock())) {
/* 72 */       return false;
/*    */     }
/*    */     
/* 75 */     for (EnumDirection var7 : ab) {
/* 76 */       boolean var8 = var0.getType(var1.shift(var7)).isAir();
/* 77 */       if ((var8 && var7 != EnumDirection.UP) || (!var8 && var7 == EnumDirection.UP)) {
/* 78 */         return false;
/*    */       }
/*    */     } 
/* 81 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDelta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */