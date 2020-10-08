/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityHeadRotation
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private byte b;
/*    */   
/*    */   public PacketPlayOutEntityHeadRotation() {}
/*    */   
/*    */   public PacketPlayOutEntityHeadRotation(Entity var0, byte var1) {
/* 19 */     this.a = var0.getId();
/* 20 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 25 */     this.a = var0.i();
/* 26 */     this.b = var0.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 31 */     var0.d(this.a);
/* 32 */     var0.writeByte(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 37 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntityHeadRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */