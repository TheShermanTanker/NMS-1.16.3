/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenDecoratorCarveMaskConfiguration implements WorldGenFeatureDecoratorConfiguration {
/*    */   static {
/*  9 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenStage.Features.c.fieldOf("step").forGetter(()), (App)Codec.FLOAT.fieldOf("probability").forGetter(())).apply((Applicative)var0, WorldGenDecoratorCarveMaskConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenDecoratorCarveMaskConfiguration> a;
/*    */   protected final WorldGenStage.Features c;
/*    */   protected final float d;
/*    */   
/*    */   public WorldGenDecoratorCarveMaskConfiguration(WorldGenStage.Features var0, float var1) {
/* 18 */     this.c = var0;
/* 19 */     this.d = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorCarveMaskConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */