/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInTabComplete
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private String b;
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 14 */     this.a = packetdataserializer.i();
/* 15 */     this.b = packetdataserializer.e(2048);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 20 */     packetdataserializer.d(this.a);
/* 21 */     packetdataserializer.a(this.b, 32500);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 25 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   public int b() {
/* 29 */     return this.a;
/*    */   }
/*    */   
/*    */   public String c() {
/* 33 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */