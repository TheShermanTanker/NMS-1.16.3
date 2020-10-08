/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenDecoratorNoiseConfiguration implements WorldGenFeatureDecoratorConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("noise_to_count_ratio").forGetter(()), (App)Codec.DOUBLE.fieldOf("noise_factor").forGetter(()), (App)Codec.DOUBLE.fieldOf("noise_offset").orElse(Double.valueOf(0.0D)).forGetter(())).apply((Applicative)var0, WorldGenDecoratorNoiseConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenDecoratorNoiseConfiguration> a;
/*    */   
/*    */   public final int c;
/*    */   public final double d;
/*    */   public final double e;
/*    */   
/*    */   public WorldGenDecoratorNoiseConfiguration(int var0, double var1, double var3) {
/* 19 */     this.c = var0;
/* 20 */     this.d = var1;
/* 21 */     this.e = var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorNoiseConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */