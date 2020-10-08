/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureOceanRuinConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureOceanRuin.Temperature.c.fieldOf("biome_temp").forGetter(()), (App)Codec.floatRange(0.0F, 1.0F).fieldOf("large_probability").forGetter(()), (App)Codec.floatRange(0.0F, 1.0F).fieldOf("cluster_probability").forGetter(())).apply((Applicative)var0, WorldGenFeatureOceanRuinConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureOceanRuinConfiguration> a;
/*    */   
/*    */   public final WorldGenFeatureOceanRuin.Temperature b;
/*    */   public final float c;
/*    */   public final float d;
/*    */   
/*    */   public WorldGenFeatureOceanRuinConfiguration(WorldGenFeatureOceanRuin.Temperature var0, float var1, float var2) {
/* 19 */     this.b = var0;
/* 20 */     this.c = var1;
/* 21 */     this.d = var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureOceanRuinConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */