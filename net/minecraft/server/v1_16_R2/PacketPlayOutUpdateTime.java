/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutUpdateTime
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private long a;
/*    */   private long b;
/*    */   
/*    */   public PacketPlayOutUpdateTime() {}
/*    */   
/*    */   public PacketPlayOutUpdateTime(long i, long j, boolean flag) {
/* 19 */     this.a = i;
/* 20 */     this.b = j;
/* 21 */     if (!flag) {
/* 22 */       this.b = -this.b;
/* 23 */       if (this.b == 0L) {
/* 24 */         this.b = -1L;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 29 */     this.a %= 192000L;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 35 */     this.a = packetdataserializer.readLong();
/* 36 */     this.b = packetdataserializer.readLong();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 41 */     packetdataserializer.writeLong(this.a);
/* 42 */     packetdataserializer.writeLong(this.b);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 46 */     packetlistenerplayout.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutUpdateTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */