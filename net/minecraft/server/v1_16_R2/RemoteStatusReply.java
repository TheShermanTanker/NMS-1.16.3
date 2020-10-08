/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class RemoteStatusReply {
/*    */   private final ByteArrayOutputStream a;
/*    */   private final DataOutputStream b;
/*    */   
/*    */   public RemoteStatusReply(int var0) {
/* 12 */     this.a = new ByteArrayOutputStream(var0);
/* 13 */     this.b = new DataOutputStream(this.a);
/*    */   }
/*    */   
/*    */   public void a(byte[] var0) throws IOException {
/* 17 */     this.b.write(var0, 0, var0.length);
/*    */   }
/*    */   
/*    */   public void a(String var0) throws IOException {
/* 21 */     this.b.writeBytes(var0);
/* 22 */     this.b.write(0);
/*    */   }
/*    */   
/*    */   public void a(int var0) throws IOException {
/* 26 */     this.b.write(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(short var0) throws IOException {
/* 31 */     this.b.writeShort(Short.reverseBytes(var0));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] a() {
/* 43 */     return this.a.toByteArray();
/*    */   }
/*    */   
/*    */   public void b() {
/* 47 */     this.a.reset();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RemoteStatusReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */