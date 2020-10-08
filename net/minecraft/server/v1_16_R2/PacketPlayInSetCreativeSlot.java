/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInSetCreativeSlot
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int slot;
/* 11 */   private ItemStack b = ItemStack.b;
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
/*    */   public void a(PacketListenerPlayIn var0) {
/* 23 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 28 */     this.slot = var0.readShort();
/* 29 */     this.b = var0.n();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 34 */     var0.writeShort(this.slot);
/* 35 */     var0.a(this.b);
/*    */   }
/*    */   
/*    */   public int b() {
/* 39 */     return this.slot;
/*    */   }
/*    */   
/*    */   public ItemStack getItemStack() {
/* 43 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInSetCreativeSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */