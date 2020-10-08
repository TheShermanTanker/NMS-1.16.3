/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginOutSetCompression
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private int a;
/*    */   
/*    */   public PacketLoginOutSetCompression() {}
/*    */   
/*    */   public PacketLoginOutSetCompression(int var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */ 
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
/*    */   public void a(PacketLoginOutListener var0) {
/* 30 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginOutSetCompression.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */