/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenFeatureDecoratorNoiseConfiguration implements WorldGenFeatureDecoratorConfiguration {
/*    */   static {
/*  7 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.DOUBLE.fieldOf("noise_level").forGetter(()), (App)Codec.INT.fieldOf("below_noise").forGetter(()), (App)Codec.INT.fieldOf("above_noise").forGetter(())).apply((Applicative)var0, WorldGenFeatureDecoratorNoiseConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureDecoratorNoiseConfiguration> a;
/*    */   
/*    */   public final double c;
/*    */   public final int d;
/*    */   public final int e;
/*    */   
/*    */   public WorldGenFeatureDecoratorNoiseConfiguration(double var0, int var2, int var3) {
/* 18 */     this.c = var0;
/* 19 */     this.d = var2;
/* 20 */     this.e = var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDecoratorNoiseConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */