/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenMineshaftConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(()), (App)WorldGenMineshaft.Type.c.fieldOf("type").forGetter(())).apply((Applicative)var0, WorldGenMineshaftConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenMineshaftConfiguration> a;
/*    */   public final float b;
/*    */   public final WorldGenMineshaft.Type c;
/*    */   
/*    */   public WorldGenMineshaftConfiguration(float var0, WorldGenMineshaft.Type var1) {
/* 17 */     this.b = var0;
/* 18 */     this.c = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMineshaftConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */