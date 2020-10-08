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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayInFlying
/*     */   implements Packet<PacketListenerPlayIn>
/*     */ {
/*     */   protected double x;
/*     */   protected double y;
/*     */   protected double z;
/*     */   protected float yaw;
/*     */   protected float pitch;
/*     */   protected boolean f;
/*     */   protected boolean hasPos;
/*     */   protected boolean hasLook;
/*     */   
/*     */   public static class PacketPlayInPositionLook
/*     */     extends PacketPlayInFlying
/*     */   {
/*     */     public void a(PacketDataSerializer var0) throws IOException {
/*  37 */       this.x = var0.readDouble();
/*  38 */       this.y = var0.readDouble();
/*  39 */       this.z = var0.readDouble();
/*  40 */       this.yaw = var0.readFloat();
/*  41 */       this.pitch = var0.readFloat();
/*  42 */       super.a(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer var0) throws IOException {
/*  47 */       var0.writeDouble(this.x);
/*  48 */       var0.writeDouble(this.y);
/*  49 */       var0.writeDouble(this.z);
/*  50 */       var0.writeFloat(this.yaw);
/*  51 */       var0.writeFloat(this.pitch);
/*  52 */       super.b(var0);
/*     */     }
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
/*     */   public static class PacketPlayInPosition
/*     */     extends PacketPlayInFlying
/*     */   {
/*     */     public void a(PacketDataSerializer var0) throws IOException {
/*  71 */       this.x = var0.readDouble();
/*  72 */       this.y = var0.readDouble();
/*  73 */       this.z = var0.readDouble();
/*  74 */       super.a(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer var0) throws IOException {
/*  79 */       var0.writeDouble(this.x);
/*  80 */       var0.writeDouble(this.y);
/*  81 */       var0.writeDouble(this.z);
/*  82 */       super.b(var0);
/*     */     }
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
/*     */   public static class PacketPlayInLook
/*     */     extends PacketPlayInFlying
/*     */   {
/*     */     public void a(PacketDataSerializer var0) throws IOException {
/* 100 */       this.yaw = var0.readFloat();
/* 101 */       this.pitch = var0.readFloat();
/* 102 */       super.a(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer var0) throws IOException {
/* 107 */       var0.writeFloat(this.yaw);
/* 108 */       var0.writeFloat(this.pitch);
/* 109 */       super.b(var0);
/*     */     }
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
/*     */   public void a(PacketListenerPlayIn var0) {
/* 122 */     var0.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/* 127 */     this.f = (var0.readUnsignedByte() != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/* 132 */     var0.writeByte(this.f ? 1 : 0);
/*     */   }
/*     */   
/*     */   public double a(double var0) {
/* 136 */     return this.hasPos ? this.x : var0;
/*     */   }
/*     */   
/*     */   public double b(double var0) {
/* 140 */     return this.hasPos ? this.y : var0;
/*     */   }
/*     */   
/*     */   public double c(double var0) {
/* 144 */     return this.hasPos ? this.z : var0;
/*     */   }
/*     */   
/*     */   public float a(float var0) {
/* 148 */     return this.hasLook ? this.yaw : var0;
/*     */   }
/*     */   
/*     */   public float b(float var0) {
/* 152 */     return this.hasLook ? this.pitch : var0;
/*     */   }
/*     */   
/*     */   public boolean b() {
/* 156 */     return this.f;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */