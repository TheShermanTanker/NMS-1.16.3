/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class CaveSound {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)SoundEffect.a.fieldOf("sound").forGetter(()), (App)Codec.DOUBLE.fieldOf("tick_chance").forGetter(())).apply((Applicative)var0, CaveSound::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<CaveSound> a;
/*    */   private SoundEffect b;
/*    */   private double c;
/*    */   
/*    */   public CaveSound(SoundEffect var0, double var1) {
/* 17 */     this.b = var0;
/* 18 */     this.c = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CaveSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */