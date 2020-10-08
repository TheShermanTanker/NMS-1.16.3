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
/*    */ public class PacketPlayInPickItem
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 20 */     this.a = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 25 */     var0.d(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 30 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public int b() {
/* 34 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInPickItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */