/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketStatusOutPong
/*    */   implements Packet<PacketStatusOutListener>
/*    */ {
/*    */   private long a;
/*    */   
/*    */   public PacketStatusOutPong() {}
/*    */   
/*    */   public PacketStatusOutPong(long var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 20 */     this.a = var0.readLong();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 25 */     var0.writeLong(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketStatusOutListener var0) {
/* 30 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketStatusOutPong.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */