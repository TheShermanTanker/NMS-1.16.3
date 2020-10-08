/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.security.PublicKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginOutEncryptionBegin
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private String a;
/*    */   private PublicKey b;
/*    */   private byte[] c;
/*    */   
/*    */   public PacketLoginOutEncryptionBegin() {}
/*    */   
/*    */   public PacketLoginOutEncryptionBegin(String var0, PublicKey var1, byte[] var2) {
/* 19 */     this.a = var0;
/* 20 */     this.b = var1;
/* 21 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 26 */     this.a = var0.e(20);
/* 27 */     this.b = MinecraftEncryption.a(var0.a());
/* 28 */     this.c = var0.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 33 */     var0.a(this.a);
/* 34 */     var0.a(this.b.getEncoded());
/* 35 */     var0.a(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginOutListener var0) {
/* 40 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginOutEncryptionBegin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */