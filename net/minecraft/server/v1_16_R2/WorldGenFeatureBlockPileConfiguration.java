/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class WorldGenFeatureBlockPileConfiguration implements WorldGenFeatureConfiguration {
/*    */   public static final Codec<WorldGenFeatureBlockPileConfiguration> a;
/*    */   
/*    */   static {
/*  7 */     a = WorldGenFeatureStateProvider.a.fieldOf("state_provider").xmap(WorldGenFeatureBlockPileConfiguration::new, var0 -> var0.b).codec();
/*    */   }
/*    */   public final WorldGenFeatureStateProvider b;
/*    */   
/*    */   public WorldGenFeatureBlockPileConfiguration(WorldGenFeatureStateProvider var0) {
/* 12 */     this.b = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBlockPileConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */