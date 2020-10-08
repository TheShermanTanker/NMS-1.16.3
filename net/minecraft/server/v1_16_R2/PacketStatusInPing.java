/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketStatusInPing
/*    */   implements Packet<PacketStatusInListener>
/*    */ {
/*    */   private long a;
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
/*    */   public void a(PacketStatusInListener var0) {
/* 30 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public long b() {
/* 34 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketStatusInPing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */