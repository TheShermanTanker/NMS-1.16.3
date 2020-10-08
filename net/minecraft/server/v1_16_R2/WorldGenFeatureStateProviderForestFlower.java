/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureStateProviderForestFlower
/*    */   extends WorldGenFeatureStateProvider
/*    */ {
/* 13 */   public static final Codec<WorldGenFeatureStateProviderForestFlower> b = Codec.unit(() -> c);
/*    */   
/* 15 */   private static final IBlockData[] d = new IBlockData[] { Blocks.DANDELION
/* 16 */       .getBlockData(), Blocks.POPPY
/* 17 */       .getBlockData(), Blocks.ALLIUM
/* 18 */       .getBlockData(), Blocks.AZURE_BLUET
/* 19 */       .getBlockData(), Blocks.RED_TULIP
/* 20 */       .getBlockData(), Blocks.ORANGE_TULIP
/* 21 */       .getBlockData(), Blocks.WHITE_TULIP
/* 22 */       .getBlockData(), Blocks.PINK_TULIP
/* 23 */       .getBlockData(), Blocks.OXEYE_DAISY
/* 24 */       .getBlockData(), Blocks.CORNFLOWER
/* 25 */       .getBlockData(), Blocks.LILY_OF_THE_VALLEY
/* 26 */       .getBlockData() };
/*    */ 
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureStateProviders<?> a() {
/* 31 */     return WorldGenFeatureStateProviders.d;
/*    */   }
/*    */   
/* 34 */   public static final WorldGenFeatureStateProviderForestFlower c = new WorldGenFeatureStateProviderForestFlower();
/*    */ 
/*    */   
/*    */   public IBlockData a(Random var0, BlockPosition var1) {
/* 38 */     double var2 = MathHelper.a((1.0D + BiomeBase.f.a(var1.getX() / 48.0D, var1.getZ() / 48.0D, false)) / 2.0D, 0.0D, 0.9999D);
/* 39 */     return d[(int)(var2 * d.length)];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureStateProviderForestFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */