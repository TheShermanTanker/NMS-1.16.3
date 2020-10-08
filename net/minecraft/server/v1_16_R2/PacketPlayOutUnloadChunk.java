/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutUnloadChunk
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public PacketPlayOutUnloadChunk() {}
/*    */   
/*    */   public PacketPlayOutUnloadChunk(int var0, int var1) {
/* 16 */     this.a = var0;
/* 17 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 22 */     this.a = var0.readInt();
/* 23 */     this.b = var0.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 28 */     var0.writeInt(this.a);
/* 29 */     var0.writeInt(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 34 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutUnloadChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */