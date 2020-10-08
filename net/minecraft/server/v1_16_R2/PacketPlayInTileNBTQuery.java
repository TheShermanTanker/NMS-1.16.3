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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInTileNBTQuery
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private BlockPosition b;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 24 */     this.a = var0.i();
/* 25 */     this.b = var0.e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 30 */     var0.d(this.a);
/* 31 */     var0.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 36 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public int b() {
/* 40 */     return this.a;
/*    */   }
/*    */   
/*    */   public BlockPosition c() {
/* 44 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInTileNBTQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */