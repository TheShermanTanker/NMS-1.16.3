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
/*    */ 
/*    */ public class PacketPlayInUpdateSign
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private String[] b;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 24 */     this.a = var0.e();
/* 25 */     this.b = new String[4];
/* 26 */     for (int var1 = 0; var1 < 4; var1++) {
/* 27 */       this.b[var1] = var0.e(384);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 33 */     var0.a(this.a);
/* 34 */     for (int var1 = 0; var1 < 4; var1++) {
/* 35 */       var0.a(this.b[var1]);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 41 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public BlockPosition b() {
/* 45 */     return this.a;
/*    */   }
/*    */   
/*    */   public String[] c() {
/* 49 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInUpdateSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */