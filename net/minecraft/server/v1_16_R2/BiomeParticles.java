/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public class BiomeParticles {
/*    */   static {
/* 11 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Particles.au.fieldOf("options").forGetter(()), (App)Codec.FLOAT.fieldOf("probability").forGetter(())).apply((Applicative)var0, BiomeParticles::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<BiomeParticles> a;
/*    */   private final ParticleParam b;
/*    */   private final float c;
/*    */   
/*    */   public BiomeParticles(ParticleParam var0, float var1) {
/* 20 */     this.b = var0;
/* 21 */     this.c = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */