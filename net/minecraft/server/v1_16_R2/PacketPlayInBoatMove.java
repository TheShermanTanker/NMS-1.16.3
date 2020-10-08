/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInBoatMove
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private boolean a;
/*    */   private boolean b;
/*    */   
/*    */   public PacketPlayInBoatMove() {}
/*    */   
/*    */   public PacketPlayInBoatMove(boolean var0, boolean var1) {
/* 16 */     this.a = var0;
/* 17 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 22 */     this.a = var0.readBoolean();
/* 23 */     this.b = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 28 */     var0.writeBoolean(this.a);
/* 29 */     var0.writeBoolean(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 34 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 38 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 42 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInBoatMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */