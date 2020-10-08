/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutNamedEntitySpawn
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private UUID b;
/*    */   private double c;
/*    */   private double d;
/*    */   private double e;
/*    */   private byte f;
/*    */   private byte g;
/*    */   
/*    */   public PacketPlayOutNamedEntitySpawn() {}
/*    */   
/*    */   public PacketPlayOutNamedEntitySpawn(EntityHuman var0) {
/* 24 */     this.a = var0.getId();
/* 25 */     this.b = var0.getProfile().getId();
/* 26 */     this.c = var0.locX();
/* 27 */     this.d = var0.locY();
/* 28 */     this.e = var0.locZ();
/* 29 */     this.f = (byte)(int)(var0.yaw * 256.0F / 360.0F);
/* 30 */     this.g = (byte)(int)(var0.pitch * 256.0F / 360.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 35 */     this.a = var0.i();
/* 36 */     this.b = var0.k();
/* 37 */     this.c = var0.readDouble();
/* 38 */     this.d = var0.readDouble();
/* 39 */     this.e = var0.readDouble();
/* 40 */     this.f = var0.readByte();
/* 41 */     this.g = var0.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 46 */     var0.d(this.a);
/* 47 */     var0.a(this.b);
/* 48 */     var0.writeDouble(this.c);
/* 49 */     var0.writeDouble(this.d);
/* 50 */     var0.writeDouble(this.e);
/* 51 */     var0.writeByte(this.f);
/* 52 */     var0.writeByte(this.g);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 57 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutNamedEntitySpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */