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
/*    */ public class PacketPlayInEntityNBTQuery
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 23 */     this.a = var0.i();
/* 24 */     this.b = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 29 */     var0.d(this.a);
/* 30 */     var0.d(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 35 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public int b() {
/* 39 */     return this.a;
/*    */   }
/*    */   
/*    */   public int c() {
/* 43 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInEntityNBTQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */