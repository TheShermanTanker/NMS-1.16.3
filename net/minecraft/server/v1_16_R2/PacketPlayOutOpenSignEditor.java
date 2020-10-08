/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutOpenSignEditor
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private BlockPosition a;
/*    */   
/*    */   public PacketPlayOutOpenSignEditor() {}
/*    */   
/*    */   public PacketPlayOutOpenSignEditor(BlockPosition var0) {
/* 16 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 21 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 26 */     this.a = var0.e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 31 */     var0.a(this.a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutOpenSignEditor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */