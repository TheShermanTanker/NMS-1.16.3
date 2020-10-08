/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class PacketPlayInBlockPlace
/*    */   implements Packet<PacketListenerPlayIn> {
/*    */   private EnumHand a;
/*    */   public long timestamp;
/*    */   
/*    */   public PacketPlayInBlockPlace() {}
/*    */   
/*    */   public PacketPlayInBlockPlace(EnumHand enumhand) {
/* 13 */     this.a = enumhand;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 18 */     this.timestamp = System.currentTimeMillis();
/* 19 */     this.a = packetdataserializer.<EnumHand>a(EnumHand.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 24 */     packetdataserializer.a(this.a);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 28 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   public EnumHand b() {
/* 32 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInBlockPlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */