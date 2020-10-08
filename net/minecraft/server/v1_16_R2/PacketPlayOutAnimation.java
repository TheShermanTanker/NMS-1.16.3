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
/*    */ public class PacketPlayOutAnimation
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public PacketPlayOutAnimation() {}
/*    */   
/*    */   public PacketPlayOutAnimation(Entity var0, int var1) {
/* 25 */     this.a = var0.getId();
/* 26 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 31 */     this.a = var0.i();
/* 32 */     this.b = var0.readUnsignedByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 37 */     var0.d(this.a);
/* 38 */     var0.writeByte(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 43 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */