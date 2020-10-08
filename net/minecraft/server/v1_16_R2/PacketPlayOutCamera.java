/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutCamera
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   public int a;
/*    */   
/*    */   public PacketPlayOutCamera() {}
/*    */   
/*    */   public PacketPlayOutCamera(Entity var0) {
/* 18 */     this.a = var0.getId();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 23 */     this.a = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 28 */     var0.d(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 33 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutCamera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */