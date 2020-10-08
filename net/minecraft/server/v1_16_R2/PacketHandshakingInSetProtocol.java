/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketHandshakingInSetProtocol
/*    */   implements Packet<PacketHandshakingInListener>
/*    */ {
/*    */   private int a;
/*    */   public String hostname;
/*    */   public int port;
/*    */   private EnumProtocol d;
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 16 */     this.a = packetdataserializer.i();
/* 17 */     this.hostname = packetdataserializer.e(32767);
/* 18 */     this.port = packetdataserializer.readUnsignedShort();
/* 19 */     this.d = EnumProtocol.a(packetdataserializer.i());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 24 */     packetdataserializer.d(this.a);
/* 25 */     packetdataserializer.a(this.hostname);
/* 26 */     packetdataserializer.writeShort(this.port);
/* 27 */     packetdataserializer.d(this.d.a());
/*    */   }
/*    */   
/*    */   public void a(PacketHandshakingInListener packethandshakinginlistener) {
/* 31 */     packethandshakinginlistener.a(this);
/*    */   }
/*    */   
/*    */   public EnumProtocol b() {
/* 35 */     return this.d;
/*    */   }
/*    */   public int getProtocolVersion() {
/* 38 */     return c();
/*    */   } public int c() {
/* 40 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketHandshakingInSetProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */