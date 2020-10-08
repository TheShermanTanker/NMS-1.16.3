/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutTransaction
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private short b;
/*    */   private boolean c;
/*    */   
/*    */   public PacketPlayOutTransaction() {}
/*    */   
/*    */   public PacketPlayOutTransaction(int var0, short var1, boolean var2) {
/* 18 */     this.a = var0;
/* 19 */     this.b = var1;
/* 20 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 25 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 30 */     this.a = var0.readUnsignedByte();
/* 31 */     this.b = var0.readShort();
/* 32 */     this.c = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 37 */     var0.writeByte(this.a);
/* 38 */     var0.writeShort(this.b);
/* 39 */     var0.writeBoolean(this.c);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutTransaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */