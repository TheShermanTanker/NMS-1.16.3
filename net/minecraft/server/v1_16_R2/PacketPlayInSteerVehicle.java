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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInSteerVehicle
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private float a;
/*    */   private float b;
/*    */   private boolean c;
/*    */   private boolean d;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.readFloat();
/* 30 */     this.b = var0.readFloat();
/*    */     
/* 32 */     byte var1 = var0.readByte();
/* 33 */     this.c = ((var1 & 0x1) > 0);
/* 34 */     this.d = ((var1 & 0x2) > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 39 */     var0.writeFloat(this.a);
/* 40 */     var0.writeFloat(this.b);
/*    */     
/* 42 */     byte var1 = 0;
/* 43 */     if (this.c) {
/* 44 */       var1 = (byte)(var1 | 0x1);
/*    */     }
/* 46 */     if (this.d) {
/* 47 */       var1 = (byte)(var1 | 0x2);
/*    */     }
/* 49 */     var0.writeByte(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 54 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public float b() {
/* 58 */     return this.a;
/*    */   }
/*    */   
/*    */   public float c() {
/* 62 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 66 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 70 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInSteerVehicle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */