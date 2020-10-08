/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutResourcePackSend
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private String a;
/*    */   private String b;
/*    */   
/*    */   public PacketPlayOutResourcePackSend() {}
/*    */   
/*    */   public PacketPlayOutResourcePackSend(String var0, String var1) {
/* 19 */     this.a = var0;
/* 20 */     this.b = var1;
/*    */     
/* 22 */     if (var1.length() > 40) {
/* 23 */       throw new IllegalArgumentException("Hash is too long (max 40, was " + var1.length() + ")");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.e(32767);
/* 30 */     this.b = var0.e(40);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 35 */     var0.a(this.a);
/* 36 */     var0.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 41 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutResourcePackSend.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */