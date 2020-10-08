/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public abstract class Particle<T extends ParticleParam> {
/*    */   private final boolean a;
/*    */   private final ParticleParam.a<T> b;
/*    */   
/*    */   protected Particle(boolean var0, ParticleParam.a<T> var1) {
/* 10 */     this.a = var0;
/* 11 */     this.b = var1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ParticleParam.a<T> d() {
/* 19 */     return this.b;
/*    */   }
/*    */   
/*    */   public abstract Codec<T> e();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Particle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */