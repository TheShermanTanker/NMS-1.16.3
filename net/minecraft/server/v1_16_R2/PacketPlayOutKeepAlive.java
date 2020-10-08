/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutKeepAlive
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private long a;
/*    */   
/*    */   public PacketPlayOutKeepAlive() {}
/*    */   
/*    */   public PacketPlayOutKeepAlive(long var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 20 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 25 */     this.a = var0.readLong();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 30 */     var0.writeLong(this.a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutKeepAlive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */