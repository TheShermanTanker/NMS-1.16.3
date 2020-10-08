/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutTags
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private ITagRegistry a;
/*    */   
/*    */   public PacketPlayOutTags() {}
/*    */   
/*    */   public PacketPlayOutTags(ITagRegistry var0) {
/* 16 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 21 */     this.a = ITagRegistry.b(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 26 */     this.a.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 31 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutTags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */