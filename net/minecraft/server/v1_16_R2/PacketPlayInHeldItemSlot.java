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
/*    */ public class PacketPlayInHeldItemSlot
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int itemInHandIndex;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 20 */     this.itemInHandIndex = var0.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 25 */     var0.writeShort(this.itemInHandIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 30 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public int b() {
/* 34 */     return this.itemInHandIndex;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInHeldItemSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */