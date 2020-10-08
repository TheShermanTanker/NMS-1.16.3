/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class WorldGenDecoratorDungeonConfiguration implements WorldGenFeatureDecoratorConfiguration {
/*    */   public static final Codec<WorldGenDecoratorDungeonConfiguration> a;
/*    */   
/*    */   static {
/*  7 */     a = Codec.INT.fieldOf("chance").xmap(WorldGenDecoratorDungeonConfiguration::new, var0 -> Integer.valueOf(var0.c)).codec();
/*    */   }
/*    */   public final int c;
/*    */   
/*    */   public WorldGenDecoratorDungeonConfiguration(int var0) {
/* 12 */     this.c = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorDungeonConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */