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
/*    */ public class PacketPlayInTransaction
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private short b;
/*    */   private boolean c;
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 24 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.readByte();
/* 30 */     this.b = var0.readShort();
/* 31 */     this.c = (var0.readByte() != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 36 */     var0.writeByte(this.a);
/* 37 */     var0.writeShort(this.b);
/* 38 */     var0.writeByte(this.c ? 1 : 0);
/*    */   }
/*    */   
/*    */   public int b() {
/* 42 */     return this.a;
/*    */   }
/*    */   
/*    */   public short c() {
/* 46 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInTransaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */