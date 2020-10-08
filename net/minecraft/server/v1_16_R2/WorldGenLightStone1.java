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
/*    */ public class WorldGenLightStone1
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenLightStone1(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 17 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 22 */     if (!var0.isEmpty(var3)) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     IBlockData var5 = var0.getType(var3.up());
/* 27 */     if (!var5.a(Blocks.NETHERRACK) && !var5.a(Blocks.BASALT) && !var5.a(Blocks.BLACKSTONE)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     var0.setTypeAndData(var3, Blocks.GLOWSTONE.getBlockData(), 2);
/*    */     
/* 33 */     for (int var6 = 0; var6 < 1500; var6++) {
/* 34 */       BlockPosition var7 = var3.b(var2.nextInt(8) - var2.nextInt(8), -var2.nextInt(12), var2.nextInt(8) - var2.nextInt(8));
/* 35 */       if (var0.getType(var7).isAir()) {
/*    */ 
/*    */ 
/*    */         
/* 39 */         int var8 = 0;
/* 40 */         for (EnumDirection var12 : EnumDirection.values()) {
/* 41 */           if (var0.getType(var7.shift(var12)).a(Blocks.GLOWSTONE)) {
/* 42 */             var8++;
/*    */           }
/*    */           
/* 45 */           if (var8 > 1) {
/*    */             break;
/*    */           }
/*    */         } 
/*    */         
/* 50 */         if (var8 == 1) {
/* 51 */           var0.setTypeAndData(var7, Blocks.GLOWSTONE.getBlockData(), 2);
/*    */         }
/*    */       } 
/*    */     } 
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenLightStone1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */