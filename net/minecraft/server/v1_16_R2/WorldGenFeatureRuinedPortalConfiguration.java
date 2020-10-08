/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class WorldGenFeatureRuinedPortalConfiguration implements WorldGenFeatureConfiguration {
/*    */   public static final Codec<WorldGenFeatureRuinedPortalConfiguration> a;
/*    */   
/*    */   static {
/*  7 */     a = WorldGenFeatureRuinedPortal.Type.h.fieldOf("portal_type").xmap(WorldGenFeatureRuinedPortalConfiguration::new, var0 -> var0.b).codec();
/*    */   }
/*    */   public final WorldGenFeatureRuinedPortal.Type b;
/*    */   
/*    */   public WorldGenFeatureRuinedPortalConfiguration(WorldGenFeatureRuinedPortal.Type var0) {
/* 12 */     this.b = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRuinedPortalConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */