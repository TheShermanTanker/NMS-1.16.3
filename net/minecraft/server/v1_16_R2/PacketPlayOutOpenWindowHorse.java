/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutOpenWindowHorse
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   
/*    */   public PacketPlayOutOpenWindowHorse() {}
/*    */   
/*    */   public PacketPlayOutOpenWindowHorse(int var0, int var1, int var2) {
/* 17 */     this.a = var0;
/* 18 */     this.b = var1;
/* 19 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 24 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.readUnsignedByte();
/* 30 */     this.b = var0.i();
/* 31 */     this.c = var0.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 36 */     var0.writeByte(this.a);
/* 37 */     var0.d(this.b);
/* 38 */     var0.writeInt(this.c);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutOpenWindowHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */