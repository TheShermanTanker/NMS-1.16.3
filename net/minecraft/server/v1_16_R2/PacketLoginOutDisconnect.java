/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginOutDisconnect
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private IChatBaseComponent a;
/*    */   
/*    */   public PacketLoginOutDisconnect() {}
/*    */   
/*    */   public PacketLoginOutDisconnect(IChatBaseComponent var0) {
/* 16 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 21 */     this.a = IChatBaseComponent.ChatSerializer.b(var0.e(262144));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 26 */     var0.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginOutListener var0) {
/* 31 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginOutDisconnect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */