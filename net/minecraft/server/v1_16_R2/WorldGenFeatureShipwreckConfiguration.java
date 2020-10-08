/*    */ package net.minecraft.server.v1_16_R2;
/*    */ public class WorldGenFeatureShipwreckConfiguration implements WorldGenFeatureConfiguration {
/*    */   public static final Codec<WorldGenFeatureShipwreckConfiguration> a;
/*    */   
/*    */   static {
/*  6 */     a = Codec.BOOL.fieldOf("is_beached").orElse(Boolean.valueOf(false)).xmap(WorldGenFeatureShipwreckConfiguration::new, var0 -> Boolean.valueOf(var0.b)).codec();
/*    */   }
/*    */   public final boolean b;
/*    */   
/*    */   public WorldGenFeatureShipwreckConfiguration(boolean var0) {
/* 11 */     this.b = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureShipwreckConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */