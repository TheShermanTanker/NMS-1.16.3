/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutWindowItems
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private List<ItemStack> b;
/*    */   
/*    */   public boolean packetTooLarge(NetworkManager manager) {
/* 15 */     for (int i = 0; i < this.b.size(); i++) {
/* 16 */       manager.sendPacket(new PacketPlayOutSetSlot(this.a, i, this.b.get(i)));
/*    */     }
/* 18 */     return true;
/*    */   }
/*    */   
/*    */   public PacketPlayOutWindowItems() {}
/*    */   
/*    */   public PacketPlayOutWindowItems(int i, NonNullList<ItemStack> nonnulllist) {
/* 24 */     this.a = i;
/* 25 */     this.b = NonNullList.a(nonnulllist.size(), ItemStack.b);
/*    */     
/* 27 */     for (int j = 0; j < this.b.size(); j++) {
/* 28 */       this.b.set(j, ((ItemStack)nonnulllist.get(j)).cloneItemStack());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 35 */     this.a = packetdataserializer.readUnsignedByte();
/* 36 */     short short0 = packetdataserializer.readShort();
/*    */     
/* 38 */     this.b = NonNullList.a(short0, ItemStack.b);
/*    */     
/* 40 */     for (int i = 0; i < short0; i++) {
/* 41 */       this.b.set(i, packetdataserializer.n());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 48 */     packetdataserializer.writeByte(this.a);
/* 49 */     packetdataserializer.writeShort(this.b.size());
/* 50 */     Iterator<ItemStack> iterator = this.b.iterator();
/*    */     
/* 52 */     while (iterator.hasNext()) {
/* 53 */       ItemStack itemstack = iterator.next();
/*    */       
/* 55 */       packetdataserializer.a(itemstack);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 61 */     packetlistenerplayout.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutWindowItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */