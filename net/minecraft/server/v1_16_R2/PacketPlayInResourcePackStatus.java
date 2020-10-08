/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInResourcePackStatus
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   public EnumResourcePackStatus status;
/*    */   
/*    */   public PacketPlayInResourcePackStatus() {}
/*    */   
/*    */   public PacketPlayInResourcePackStatus(EnumResourcePackStatus var0) {
/* 15 */     this.status = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 20 */     this.status = var0.<EnumResourcePackStatus>a(EnumResourcePackStatus.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 25 */     var0.a(this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 30 */     var0.a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum EnumResourcePackStatus
/*    */   {
/* 38 */     SUCCESSFULLY_LOADED,
/* 39 */     DECLINED,
/* 40 */     FAILED_DOWNLOAD,
/* 41 */     ACCEPTED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInResourcePackStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */