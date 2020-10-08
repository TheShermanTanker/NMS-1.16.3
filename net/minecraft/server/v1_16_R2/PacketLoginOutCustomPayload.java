/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class PacketLoginOutCustomPayload
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private int a;
/*    */   private MinecraftKey b;
/*    */   private PacketDataSerializer c;
/*    */   
/*    */   public PacketLoginOutCustomPayload() {}
/*    */   
/*    */   public PacketLoginOutCustomPayload(int id, MinecraftKey channel, PacketDataSerializer buf) {
/* 15 */     this.a = id;
/* 16 */     this.b = channel;
/* 17 */     this.c = buf;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 23 */     this.a = packetdataserializer.i();
/* 24 */     this.b = packetdataserializer.p();
/* 25 */     int i = packetdataserializer.readableBytes();
/*    */     
/* 27 */     if (i >= 0 && i <= 1048576) {
/* 28 */       this.c = new PacketDataSerializer(packetdataserializer.readBytes(i));
/*    */     } else {
/* 30 */       throw new IOException("Payload may not be larger than 1048576 bytes");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 36 */     packetdataserializer.d(this.a);
/* 37 */     packetdataserializer.a(this.b);
/* 38 */     packetdataserializer.writeBytes(this.c.copy());
/*    */   }
/*    */   
/*    */   public void a(PacketLoginOutListener packetloginoutlistener) {
/* 42 */     packetloginoutlistener.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginOutCustomPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */