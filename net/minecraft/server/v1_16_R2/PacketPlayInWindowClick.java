/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInWindowClick
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private int slot;
/*    */   private int button;
/*    */   private short d;
/* 15 */   private ItemStack item = ItemStack.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private InventoryClickType shift;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 32 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 37 */     this.a = var0.readByte();
/* 38 */     this.slot = var0.readShort();
/* 39 */     this.button = var0.readByte();
/* 40 */     this.d = var0.readShort();
/* 41 */     this.shift = var0.<InventoryClickType>a(InventoryClickType.class);
/*    */     
/* 43 */     this.item = var0.n();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 48 */     var0.writeByte(this.a);
/* 49 */     var0.writeShort(this.slot);
/* 50 */     var0.writeByte(this.button);
/* 51 */     var0.writeShort(this.d);
/* 52 */     var0.a(this.shift);
/*    */     
/* 54 */     var0.a(this.item);
/*    */   }
/*    */   
/*    */   public int b() {
/* 58 */     return this.a;
/*    */   }
/*    */   
/*    */   public int c() {
/* 62 */     return this.slot;
/*    */   }
/*    */   
/*    */   public int d() {
/* 66 */     return this.button;
/*    */   }
/*    */   
/*    */   public short e() {
/* 70 */     return this.d;
/*    */   }
/*    */   
/*    */   public ItemStack f() {
/* 74 */     return this.item;
/*    */   }
/*    */   
/*    */   public InventoryClickType g() {
/* 78 */     return this.shift;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInWindowClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */