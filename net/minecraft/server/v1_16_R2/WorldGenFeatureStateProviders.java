/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenFeatureStateProviders<P extends WorldGenFeatureStateProvider>
/*    */ {
/*  7 */   public static final WorldGenFeatureStateProviders<WorldGenFeatureStateProviderSimpl> a = a("simple_state_provider", WorldGenFeatureStateProviderSimpl.b);
/*  8 */   public static final WorldGenFeatureStateProviders<WorldGenFeatureStateProviderWeighted> b = a("weighted_state_provider", WorldGenFeatureStateProviderWeighted.b);
/*  9 */   public static final WorldGenFeatureStateProviders<WorldGenFeatureStateProviderPlainFlower> c = a("plain_flower_provider", WorldGenFeatureStateProviderPlainFlower.b);
/* 10 */   public static final WorldGenFeatureStateProviders<WorldGenFeatureStateProviderForestFlower> d = a("forest_flower_provider", WorldGenFeatureStateProviderForestFlower.b);
/* 11 */   public static final WorldGenFeatureStateProviders<WorldGenFeatureStateProviderRotatedBlock> e = a("rotated_block_provider", WorldGenFeatureStateProviderRotatedBlock.b);
/*    */   
/*    */   private static <P extends WorldGenFeatureStateProvider> WorldGenFeatureStateProviders<P> a(String var0, Codec<P> var1) {
/* 14 */     return (WorldGenFeatureStateProviders<P>)IRegistry.<WorldGenFeatureStateProviders<?>>a(IRegistry.BLOCK_STATE_PROVIDER_TYPE, var0, new WorldGenFeatureStateProviders(var1));
/*    */   }
/*    */   
/*    */   private final Codec<P> f;
/*    */   
/*    */   private WorldGenFeatureStateProviders(Codec<P> var0) {
/* 20 */     this.f = var0;
/*    */   }
/*    */   
/*    */   public Codec<P> a() {
/* 24 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureStateProviders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */