/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.ShortBufferException;
/*    */ 
/*    */ public class PacketEncryptionHandler
/*    */ {
/*    */   private final Cipher a;
/* 11 */   private byte[] b = new byte[0];
/* 12 */   private byte[] c = new byte[0];
/*    */   
/*    */   protected PacketEncryptionHandler(Cipher var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */   
/*    */   private byte[] a(ByteBuf var0) {
/* 19 */     int var1 = var0.readableBytes();
/* 20 */     if (this.b.length < var1) {
/* 21 */       this.b = new byte[var1];
/*    */     }
/* 23 */     var0.readBytes(this.b, 0, var1);
/* 24 */     return this.b;
/*    */   }
/*    */   
/*    */   protected ByteBuf a(ChannelHandlerContext var0, ByteBuf var1) throws ShortBufferException {
/* 28 */     int var2 = var1.readableBytes();
/* 29 */     byte[] var3 = a(var1);
/*    */     
/* 31 */     ByteBuf var4 = var0.alloc().heapBuffer(this.a.getOutputSize(var2));
/* 32 */     var4.writerIndex(this.a.update(var3, 0, var2, var4.array(), var4.arrayOffset()));
/*    */     
/* 34 */     return var4;
/*    */   }
/*    */   
/*    */   protected void a(ByteBuf var0, ByteBuf var1) throws ShortBufferException {
/* 38 */     int var2 = var0.readableBytes();
/* 39 */     byte[] var3 = a(var0);
/*    */     
/* 41 */     int var4 = this.a.getOutputSize(var2);
/* 42 */     if (this.c.length < var4) {
/* 43 */       this.c = new byte[var4];
/*    */     }
/* 45 */     var1.writeBytes(this.c, 0, this.a.update(var3, 0, var2, this.c));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketEncryptionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */