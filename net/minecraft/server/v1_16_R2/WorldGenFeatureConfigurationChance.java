/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureConfigurationChance implements WorldGenCarverConfiguration, WorldGenFeatureConfiguration {
/*    */   static {
/*  8 */     b = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(())).apply((Applicative)var0, WorldGenFeatureConfigurationChance::new));
/*    */   }
/*    */   
/*    */   public static final Codec<WorldGenFeatureConfigurationChance> b;
/*    */   public final float c;
/*    */   
/*    */   public WorldGenFeatureConfigurationChance(float var0) {
/* 15 */     this.c = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureConfigurationChance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */