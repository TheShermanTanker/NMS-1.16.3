/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutBlockBreakAnimation
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private BlockPosition b;
/*    */   private int c;
/*    */   
/*    */   public PacketPlayOutBlockBreakAnimation() {}
/*    */   
/*    */   public PacketPlayOutBlockBreakAnimation(int var0, BlockPosition var1, int var2) {
/* 18 */     this.a = var0;
/* 19 */     this.b = var1;
/* 20 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 25 */     this.a = var0.i();
/* 26 */     this.b = var0.e();
/* 27 */     this.c = var0.readUnsignedByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 32 */     var0.d(this.a);
/* 33 */     var0.a(this.b);
/* 34 */     var0.writeByte(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 39 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutBlockBreakAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */