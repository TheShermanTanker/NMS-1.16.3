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
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutPlayerListHeaderFooter
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   public IChatBaseComponent header;
/*    */   public IChatBaseComponent footer;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 23 */     this.header = var0.h();
/* 24 */     this.footer = var0.h();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 29 */     var0.a(this.header);
/* 30 */     var0.a(this.footer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 35 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutPlayerListHeaderFooter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */