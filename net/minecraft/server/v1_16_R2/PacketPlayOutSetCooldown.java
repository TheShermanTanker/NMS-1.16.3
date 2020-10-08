/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutSetCooldown
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private Item a;
/*    */   private int b;
/*    */   
/*    */   public PacketPlayOutSetCooldown() {}
/*    */   
/*    */   public PacketPlayOutSetCooldown(Item var0, int var1) {
/* 17 */     this.a = var0;
/* 18 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 23 */     this.a = Item.getById(var0.i());
/* 24 */     this.b = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 29 */     var0.d(Item.getId(this.a));
/* 30 */     var0.d(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 35 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutSetCooldown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */