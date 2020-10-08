/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutVehicleMove
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private double a;
/*    */   private double b;
/*    */   private double c;
/*    */   private float d;
/*    */   private float e;
/*    */   
/*    */   public PacketPlayOutVehicleMove() {}
/*    */   
/*    */   public PacketPlayOutVehicleMove(Entity var0) {
/* 20 */     this.a = var0.locX();
/* 21 */     this.b = var0.locY();
/* 22 */     this.c = var0.locZ();
/* 23 */     this.d = var0.yaw;
/* 24 */     this.e = var0.pitch;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.readDouble();
/* 30 */     this.b = var0.readDouble();
/* 31 */     this.c = var0.readDouble();
/* 32 */     this.d = var0.readFloat();
/* 33 */     this.e = var0.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 38 */     var0.writeDouble(this.a);
/* 39 */     var0.writeDouble(this.b);
/* 40 */     var0.writeDouble(this.c);
/* 41 */     var0.writeFloat(this.d);
/* 42 */     var0.writeFloat(this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 47 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutVehicleMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */