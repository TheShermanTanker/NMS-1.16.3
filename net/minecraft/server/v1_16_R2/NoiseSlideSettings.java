/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class NoiseSlideSettings {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("target").forGetter(NoiseSlideSettings::a), (App)Codec.intRange(0, 256).fieldOf("size").forGetter(NoiseSlideSettings::b), (App)Codec.INT.fieldOf("offset").forGetter(NoiseSlideSettings::c)).apply((Applicative)var0, NoiseSlideSettings::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<NoiseSlideSettings> a;
/*    */   
/*    */   private final int b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   
/*    */   public NoiseSlideSettings(int var0, int var1, int var2) {
/* 19 */     this.b = var0;
/* 20 */     this.c = var1;
/* 21 */     this.d = var2;
/*    */   }
/*    */   
/*    */   public int a() {
/* 25 */     return this.b;
/*    */   }
/*    */   
/*    */   public int b() {
/* 29 */     return this.c;
/*    */   }
/*    */   
/*    */   public int c() {
/* 33 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NoiseSlideSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */