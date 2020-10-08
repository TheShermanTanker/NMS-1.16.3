/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class ParticleType
/*    */   extends Particle<ParticleType>
/*    */   implements ParticleParam {
/* 10 */   private static final ParticleParam.a<ParticleType> a = new ParticleParam.a<ParticleType>()
/*    */     {
/*    */       public ParticleType b(Particle<ParticleType> var0, StringReader var1) throws CommandSyntaxException {
/* 13 */         return (ParticleType)var0;
/*    */       }
/*    */ 
/*    */       
/*    */       public ParticleType b(Particle<ParticleType> var0, PacketDataSerializer var1) {
/* 18 */         return (ParticleType)var0;
/*    */       }
/*    */     };
/*    */   
/* 22 */   private final Codec<ParticleType> b = Codec.unit(this::getParticle);
/*    */   
/*    */   protected ParticleType(boolean var0) {
/* 25 */     super(var0, a);
/*    */   }
/*    */ 
/*    */   
/*    */   public ParticleType getParticle() {
/* 30 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Codec<ParticleType> e() {
/* 35 */     return this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) {}
/*    */ 
/*    */   
/*    */   public String a() {
/* 44 */     return IRegistry.PARTICLE_TYPE.getKey(this).toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ParticleType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */