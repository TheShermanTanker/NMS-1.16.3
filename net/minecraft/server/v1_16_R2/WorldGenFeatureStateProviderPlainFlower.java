/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureStateProviderPlainFlower
/*    */   extends WorldGenFeatureStateProvider
/*    */ {
/* 13 */   public static final Codec<WorldGenFeatureStateProviderPlainFlower> b = Codec.unit(() -> c);
/*    */   
/* 15 */   public static final WorldGenFeatureStateProviderPlainFlower c = new WorldGenFeatureStateProviderPlainFlower();
/*    */   
/* 17 */   private static final IBlockData[] d = new IBlockData[] { Blocks.ORANGE_TULIP
/* 18 */       .getBlockData(), Blocks.RED_TULIP
/* 19 */       .getBlockData(), Blocks.PINK_TULIP
/* 20 */       .getBlockData(), Blocks.WHITE_TULIP
/* 21 */       .getBlockData() };
/*    */   
/* 23 */   private static final IBlockData[] e = new IBlockData[] { Blocks.POPPY
/* 24 */       .getBlockData(), Blocks.AZURE_BLUET
/* 25 */       .getBlockData(), Blocks.OXEYE_DAISY
/* 26 */       .getBlockData(), Blocks.CORNFLOWER
/* 27 */       .getBlockData() };
/*    */ 
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureStateProviders<?> a() {
/* 32 */     return WorldGenFeatureStateProviders.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(Random var0, BlockPosition var1) {
/* 37 */     double var2 = BiomeBase.f.a(var1.getX() / 200.0D, var1.getZ() / 200.0D, false);
/* 38 */     if (var2 < -0.8D) {
/* 39 */       return SystemUtils.<IBlockData>a(d, var0);
/*    */     }
/*    */     
/* 42 */     if (var0.nextInt(3) > 0) {
/* 43 */       return SystemUtils.<IBlockData>a(e, var0);
/*    */     }
/*    */     
/* 46 */     return Blocks.DANDELION.getBlockData();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureStateProviderPlainFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */