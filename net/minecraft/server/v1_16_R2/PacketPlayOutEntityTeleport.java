/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityTeleport
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private double b;
/*    */   private double c;
/*    */   private double d;
/*    */   private byte e;
/*    */   private byte f;
/*    */   private boolean g;
/*    */   
/*    */   public PacketPlayOutEntityTeleport() {}
/*    */   
/*    */   public PacketPlayOutEntityTeleport(Entity var0) {
/* 22 */     this.a = var0.getId();
/* 23 */     this.b = var0.locX();
/* 24 */     this.c = var0.locY();
/* 25 */     this.d = var0.locZ();
/* 26 */     this.e = (byte)(int)(var0.yaw * 256.0F / 360.0F);
/* 27 */     this.f = (byte)(int)(var0.pitch * 256.0F / 360.0F);
/* 28 */     this.g = var0.isOnGround();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 33 */     this.a = var0.i();
/* 34 */     this.b = var0.readDouble();
/* 35 */     this.c = var0.readDouble();
/* 36 */     this.d = var0.readDouble();
/* 37 */     this.e = var0.readByte();
/* 38 */     this.f = var0.readByte();
/* 39 */     this.g = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 44 */     var0.d(this.a);
/* 45 */     var0.writeDouble(this.b);
/* 46 */     var0.writeDouble(this.c);
/* 47 */     var0.writeDouble(this.d);
/* 48 */     var0.writeByte(this.e);
/* 49 */     var0.writeByte(this.f);
/* 50 */     var0.writeBoolean(this.g);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 55 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntityTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */