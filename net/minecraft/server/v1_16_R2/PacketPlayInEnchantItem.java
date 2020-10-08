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
/*    */ public class PacketPlayInEnchantItem
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 22 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 27 */     this.a = var0.readByte();
/* 28 */     this.b = var0.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 33 */     var0.writeByte(this.a);
/* 34 */     var0.writeByte(this.b);
/*    */   }
/*    */   
/*    */   public int b() {
/* 38 */     return this.a;
/*    */   }
/*    */   
/*    */   public int c() {
/* 42 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInEnchantItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */