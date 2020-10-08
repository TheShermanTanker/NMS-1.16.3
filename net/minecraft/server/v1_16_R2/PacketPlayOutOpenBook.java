/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutOpenBook
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private EnumHand a;
/*    */   
/*    */   public PacketPlayOutOpenBook() {}
/*    */   
/*    */   public PacketPlayOutOpenBook(EnumHand var0) {
/* 16 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 21 */     this.a = var0.<EnumHand>a(EnumHand.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 26 */     var0.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 31 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutOpenBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */