/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenDecoratorHeightAverageConfiguration implements WorldGenFeatureDecoratorConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("baseline").forGetter(()), (App)Codec.INT.fieldOf("spread").forGetter(())).apply((Applicative)var0, WorldGenDecoratorHeightAverageConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenDecoratorHeightAverageConfiguration> a;
/*    */   public final int c;
/*    */   public final int d;
/*    */   
/*    */   public WorldGenDecoratorHeightAverageConfiguration(int var0, int var1) {
/* 17 */     this.c = var0;
/* 18 */     this.d = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorHeightAverageConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */