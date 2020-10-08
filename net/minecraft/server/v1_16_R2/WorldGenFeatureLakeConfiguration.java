/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class WorldGenFeatureLakeConfiguration implements WorldGenFeatureConfiguration {
/*    */   public static final Codec<WorldGenFeatureLakeConfiguration> a;
/*    */   
/*    */   static {
/*  7 */     a = IBlockData.b.fieldOf("state").xmap(WorldGenFeatureLakeConfiguration::new, var0 -> var0.b).codec();
/*    */   }
/*    */   public final IBlockData b;
/*    */   
/*    */   public WorldGenFeatureLakeConfiguration(IBlockData var0) {
/* 12 */     this.b = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureLakeConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */