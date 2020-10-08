/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityEffect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private byte b;
/*    */   private byte c;
/*    */   private int d;
/*    */   private byte e;
/*    */   
/*    */   public PacketPlayOutEntityEffect() {}
/*    */   
/*    */   public PacketPlayOutEntityEffect(int var0, MobEffect var1) {
/* 28 */     this.a = var0;
/* 29 */     this.b = (byte)(MobEffectList.getId(var1.getMobEffect()) & 0xFF);
/* 30 */     this.c = (byte)(var1.getAmplifier() & 0xFF);
/* 31 */     if (var1.getDuration() > 32767) {
/* 32 */       this.d = 32767;
/*    */     } else {
/* 34 */       this.d = var1.getDuration();
/*    */     } 
/* 36 */     this.e = 0;
/*    */     
/* 38 */     if (var1.isAmbient()) {
/* 39 */       this.e = (byte)(this.e | 0x1);
/*    */     }
/* 41 */     if (var1.isShowParticles()) {
/* 42 */       this.e = (byte)(this.e | 0x2);
/*    */     }
/* 44 */     if (var1.isShowIcon()) {
/* 45 */       this.e = (byte)(this.e | 0x4);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 51 */     this.a = var0.i();
/* 52 */     this.b = var0.readByte();
/* 53 */     this.c = var0.readByte();
/* 54 */     this.d = var0.i();
/* 55 */     this.e = var0.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 60 */     var0.d(this.a);
/* 61 */     var0.writeByte(this.b);
/* 62 */     var0.writeByte(this.c);
/* 63 */     var0.d(this.d);
/* 64 */     var0.writeByte(this.e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 73 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntityEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */