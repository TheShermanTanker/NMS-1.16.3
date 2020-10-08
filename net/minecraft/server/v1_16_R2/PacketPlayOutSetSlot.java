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
/*    */ public class PacketPlayOutSetSlot
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/* 16 */   private ItemStack c = ItemStack.b;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutSetSlot(int var0, int var1, ItemStack var2) {
/* 22 */     this.a = var0;
/* 23 */     this.b = var1;
/* 24 */     this.c = var2.cloneItemStack();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 29 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 34 */     this.a = var0.readByte();
/* 35 */     this.b = var0.readShort();
/* 36 */     this.c = var0.n();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 41 */     var0.writeByte(this.a);
/* 42 */     var0.writeShort(this.b);
/* 43 */     var0.a(this.c);
/*    */   }
/*    */   
/*    */   public PacketPlayOutSetSlot() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutSetSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */