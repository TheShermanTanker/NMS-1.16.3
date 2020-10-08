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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketStatusInStart
/*    */   implements Packet<PacketStatusInListener>
/*    */ {
/*    */   public void a(PacketDataSerializer var0) throws IOException {}
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {}
/*    */   
/*    */   public void a(PacketStatusInListener var0) {
/* 22 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketStatusInStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */