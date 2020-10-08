/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class PacketLoginInCustomPayload implements Packet<PacketLoginInListener> {
/*    */   private int a;
/*    */   
/*  7 */   public int getId() { return this.a; } private PacketDataSerializer b; public PacketDataSerializer getBuf() {
/*  8 */     return this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 14 */     this.a = packetdataserializer.i();
/* 15 */     if (packetdataserializer.readBoolean()) {
/* 16 */       int i = packetdataserializer.readableBytes();
/*    */       
/* 18 */       if (i < 0 || i > 1048576) {
/* 19 */         throw new IOException("Payload may not be larger than 1048576 bytes");
/*    */       }
/*    */       
/* 22 */       this.b = new PacketDataSerializer(packetdataserializer.readBytes(i));
/*    */     } else {
/* 24 */       this.b = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 31 */     packetdataserializer.d(this.a);
/* 32 */     if (this.b != null) {
/* 33 */       packetdataserializer.writeBoolean(true);
/* 34 */       packetdataserializer.writeBytes(this.b.copy());
/*    */     } else {
/* 36 */       packetdataserializer.writeBoolean(false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginInListener packetlogininlistener) {
/* 42 */     packetlogininlistener.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginInCustomPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */