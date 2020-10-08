/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInItemName
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private String a;
/*    */   
/*    */   public PacketPlayInItemName() {}
/*    */   
/*    */   public PacketPlayInItemName(String var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 20 */     this.a = var0.e(32767);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 25 */     var0.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 30 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public String b() {
/* 34 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInItemName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */