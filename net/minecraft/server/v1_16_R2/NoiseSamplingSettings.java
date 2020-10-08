/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class NoiseSamplingSettings {
/*  7 */   private static final Codec<Double> b = Codec.doubleRange(0.001D, 1000.0D); public static final Codec<NoiseSamplingSettings> a; private final double c;
/*    */   static {
/*  9 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)b.fieldOf("xz_scale").forGetter(NoiseSamplingSettings::a), (App)b.fieldOf("y_scale").forGetter(NoiseSamplingSettings::b), (App)b.fieldOf("xz_factor").forGetter(NoiseSamplingSettings::c), (App)b.fieldOf("y_factor").forGetter(NoiseSamplingSettings::d)).apply((Applicative)var0, NoiseSamplingSettings::new));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private final double d;
/*    */   
/*    */   private final double e;
/*    */   
/*    */   private final double f;
/*    */ 
/*    */   
/*    */   public NoiseSamplingSettings(double var0, double var2, double var4, double var6) {
/* 22 */     this.c = var0;
/* 23 */     this.d = var2;
/* 24 */     this.e = var4;
/* 25 */     this.f = var6;
/*    */   }
/*    */   
/*    */   public double a() {
/* 29 */     return this.c;
/*    */   }
/*    */   
/*    */   public double b() {
/* 33 */     return this.d;
/*    */   }
/*    */   
/*    */   public double c() {
/* 37 */     return this.e;
/*    */   }
/*    */   
/*    */   public double d() {
/* 41 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NoiseSamplingSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */