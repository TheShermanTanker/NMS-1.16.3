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
/*    */ public class PacketPlayInBeacon
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 22 */     this.a = var0.i();
/* 23 */     this.b = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 28 */     var0.d(this.a);
/* 29 */     var0.d(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 34 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public int b() {
/* 38 */     return this.a;
/*    */   }
/*    */   
/*    */   public int c() {
/* 42 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */