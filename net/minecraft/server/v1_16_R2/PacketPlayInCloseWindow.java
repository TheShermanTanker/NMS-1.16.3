/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class PacketPlayInCloseWindow
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public PacketPlayInCloseWindow() {}
/*    */   
/*    */   public PacketPlayInCloseWindow(int id) {
/* 13 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 18 */     packetlistenerplayin.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 23 */     this.id = packetdataserializer.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 28 */     packetdataserializer.writeByte(this.id);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInCloseWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */