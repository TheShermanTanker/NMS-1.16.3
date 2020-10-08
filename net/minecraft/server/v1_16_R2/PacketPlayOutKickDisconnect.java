/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutKickDisconnect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private IChatBaseComponent a;
/*    */   
/*    */   public PacketPlayOutKickDisconnect() {}
/*    */   
/*    */   public PacketPlayOutKickDisconnect(IChatBaseComponent var0) {
/* 16 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 21 */     this.a = var0.h();
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutKickDisconnect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */