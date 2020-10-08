/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Locale;
/*    */ 
/*    */ public class ParticleParamRedstone
/*    */   implements ParticleParam {
/* 14 */   public static final ParticleParamRedstone a = new ParticleParamRedstone(1.0F, 0.0F, 0.0F, 1.0F);
/*    */   static {
/* 16 */     b = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.FLOAT.fieldOf("r").forGetter(()), (App)Codec.FLOAT.fieldOf("g").forGetter(()), (App)Codec.FLOAT.fieldOf("b").forGetter(()), (App)Codec.FLOAT.fieldOf("scale").forGetter(())).apply((Applicative)var0, ParticleParamRedstone::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<ParticleParamRedstone> b;
/*    */ 
/*    */   
/* 23 */   public static final ParticleParam.a<ParticleParamRedstone> c = new ParticleParam.a<ParticleParamRedstone>()
/*    */     {
/*    */       public ParticleParamRedstone b(Particle<ParticleParamRedstone> var0, StringReader var1) throws CommandSyntaxException
/*    */       {
/* 27 */         var1.expect(' ');
/* 28 */         float var2 = (float)var1.readDouble();
/* 29 */         var1.expect(' ');
/* 30 */         float var3 = (float)var1.readDouble();
/* 31 */         var1.expect(' ');
/* 32 */         float var4 = (float)var1.readDouble();
/* 33 */         var1.expect(' ');
/* 34 */         float var5 = (float)var1.readDouble();
/* 35 */         return new ParticleParamRedstone(var2, var3, var4, var5);
/*    */       }
/*    */ 
/*    */       
/*    */       public ParticleParamRedstone b(Particle<ParticleParamRedstone> var0, PacketDataSerializer var1) {
/* 40 */         return new ParticleParamRedstone(var1.readFloat(), var1.readFloat(), var1.readFloat(), var1.readFloat());
/*    */       }
/*    */     };
/*    */   
/*    */   private final float d;
/*    */   private final float e;
/*    */   private final float f;
/*    */   private final float g;
/*    */   
/*    */   public ParticleParamRedstone(float var0, float var1, float var2, float var3) {
/* 50 */     this.d = var0;
/* 51 */     this.e = var1;
/* 52 */     this.f = var2;
/*    */     
/* 54 */     this.g = MathHelper.a(var3, 0.01F, 4.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) {
/* 59 */     var0.writeFloat(this.d);
/* 60 */     var0.writeFloat(this.e);
/* 61 */     var0.writeFloat(this.f);
/* 62 */     var0.writeFloat(this.g);
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 67 */     return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", new Object[] { IRegistry.PARTICLE_TYPE.getKey(getParticle()), Float.valueOf(this.d), Float.valueOf(this.e), Float.valueOf(this.f), Float.valueOf(this.g) });
/*    */   }
/*    */ 
/*    */   
/*    */   public Particle<ParticleParamRedstone> getParticle() {
/* 72 */     return Particles.DUST;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ParticleParamRedstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */