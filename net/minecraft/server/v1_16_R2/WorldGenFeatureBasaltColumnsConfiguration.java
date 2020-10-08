/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureBasaltColumnsConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IntSpread.a(0, 2, 1).fieldOf("reach").forGetter(()), (App)IntSpread.a(1, 5, 5).fieldOf("height").forGetter(())).apply((Applicative)var0, WorldGenFeatureBasaltColumnsConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureBasaltColumnsConfiguration> a;
/*    */   private final IntSpread b;
/*    */   private final IntSpread c;
/*    */   
/*    */   public WorldGenFeatureBasaltColumnsConfiguration(IntSpread var0, IntSpread var1) {
/* 17 */     this.b = var0;
/* 18 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public IntSpread am_() {
/* 22 */     return this.b;
/*    */   }
/*    */   
/*    */   public IntSpread b() {
/* 26 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBasaltColumnsConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */