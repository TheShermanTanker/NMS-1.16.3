/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenDecoratorFrequencyExtraChanceConfiguration implements WorldGenFeatureDecoratorConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("count").forGetter(()), (App)Codec.FLOAT.fieldOf("extra_chance").forGetter(()), (App)Codec.INT.fieldOf("extra_count").forGetter(())).apply((Applicative)var0, WorldGenDecoratorFrequencyExtraChanceConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenDecoratorFrequencyExtraChanceConfiguration> a;
/*    */   
/*    */   public final int c;
/*    */   public final float d;
/*    */   public final int e;
/*    */   
/*    */   public WorldGenDecoratorFrequencyExtraChanceConfiguration(int var0, float var1, int var2) {
/* 19 */     this.c = var0;
/* 20 */     this.d = var1;
/* 21 */     this.e = var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorFrequencyExtraChanceConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */