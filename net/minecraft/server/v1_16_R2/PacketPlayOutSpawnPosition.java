/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutSpawnPosition
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   public BlockPosition position;
/*    */   private float b;
/*    */   
/*    */   public PacketPlayOutSpawnPosition() {}
/*    */   
/*    */   public PacketPlayOutSpawnPosition(BlockPosition var0, float var1) {
/* 17 */     this.position = var0;
/* 18 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 23 */     this.position = var0.e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 28 */     var0.a(this.position);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 33 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutSpawnPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */