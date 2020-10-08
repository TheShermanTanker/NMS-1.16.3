/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutWorldParticles
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private double a;
/*     */   private double b;
/*     */   private double c;
/*     */   private float d;
/*     */   private float e;
/*     */   private float f;
/*     */   private float g;
/*     */   private int h;
/*     */   private boolean i;
/*     */   private ParticleParam j;
/*     */   
/*     */   public PacketPlayOutWorldParticles() {}
/*     */   
/*     */   public <T extends ParticleParam> PacketPlayOutWorldParticles(T var0, boolean var1, double var2, double var4, double var6, float var8, float var9, float var10, float var11, int var12) {
/*  29 */     this.j = (ParticleParam)var0;
/*  30 */     this.i = var1;
/*  31 */     this.a = var2;
/*  32 */     this.b = var4;
/*  33 */     this.c = var6;
/*  34 */     this.d = var8;
/*  35 */     this.e = var9;
/*  36 */     this.f = var10;
/*  37 */     this.g = var11;
/*  38 */     this.h = var12;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/*  43 */     Particle<?> var1 = IRegistry.PARTICLE_TYPE.fromId(var0.readInt());
/*  44 */     if (var1 == null) {
/*  45 */       var1 = Particles.BARRIER;
/*     */     }
/*  47 */     this.i = var0.readBoolean();
/*  48 */     this.a = var0.readDouble();
/*  49 */     this.b = var0.readDouble();
/*  50 */     this.c = var0.readDouble();
/*  51 */     this.d = var0.readFloat();
/*  52 */     this.e = var0.readFloat();
/*  53 */     this.f = var0.readFloat();
/*  54 */     this.g = var0.readFloat();
/*  55 */     this.h = var0.readInt();
/*  56 */     this.j = a(var0, var1);
/*     */   }
/*     */   
/*     */   private <T extends ParticleParam> T a(PacketDataSerializer var0, Particle<T> var1) {
/*  60 */     return var1.d().b(var1, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/*  65 */     var0.writeInt(IRegistry.PARTICLE_TYPE.a(this.j.getParticle()));
/*  66 */     var0.writeBoolean(this.i);
/*  67 */     var0.writeDouble(this.a);
/*  68 */     var0.writeDouble(this.b);
/*  69 */     var0.writeDouble(this.c);
/*  70 */     var0.writeFloat(this.d);
/*  71 */     var0.writeFloat(this.e);
/*  72 */     var0.writeFloat(this.f);
/*  73 */     var0.writeFloat(this.g);
/*  74 */     var0.writeInt(this.h);
/*  75 */     this.j.a(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut var0) {
/* 120 */     var0.a(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutWorldParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */