/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.security.PrivateKey;
/*    */ import javax.crypto.SecretKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginInEncryptionBegin
/*    */   implements Packet<PacketLoginInListener>
/*    */ {
/* 13 */   private byte[] a = new byte[0];
/* 14 */   private byte[] b = new byte[0];
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
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 26 */     this.a = var0.a();
/* 27 */     this.b = var0.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 32 */     var0.a(this.a);
/* 33 */     var0.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginInListener var0) {
/* 38 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public SecretKey a(PrivateKey var0) {
/* 42 */     return MinecraftEncryption.a(var0, this.a);
/*    */   }
/*    */   
/*    */   public byte[] b(PrivateKey var0) {
/* 46 */     if (var0 == null) {
/* 47 */       return this.b;
/*    */     }
/* 49 */     return MinecraftEncryption.b(var0, this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginInEncryptionBegin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */