/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInUseItem
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private MovingObjectPositionBlock a;
/*    */   private EnumHand b;
/*    */   public long timestamp;
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 15 */     this.timestamp = System.currentTimeMillis();
/* 16 */     this.b = packetdataserializer.<EnumHand>a(EnumHand.class);
/* 17 */     this.a = packetdataserializer.r();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 22 */     packetdataserializer.a(this.b);
/* 23 */     packetdataserializer.a(this.a);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 27 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   public EnumHand b() {
/* 31 */     return this.b;
/*    */   }
/*    */   
/*    */   public MovingObjectPositionBlock c() {
/* 35 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInUseItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */