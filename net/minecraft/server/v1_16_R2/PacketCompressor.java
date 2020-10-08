/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import java.util.zip.Deflater;
/*    */ 
/*    */ public class PacketCompressor
/*    */   extends MessageToByteEncoder<ByteBuf> {
/* 10 */   private final byte[] a = new byte[8192];
/*    */   private final Deflater b;
/*    */   private int c;
/*    */   
/*    */   public PacketCompressor(int var0) {
/* 15 */     this.c = var0;
/* 16 */     this.b = new Deflater();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void encode(ChannelHandlerContext var0, ByteBuf var1, ByteBuf var2) throws Exception {
/* 21 */     int var3 = var1.readableBytes();
/* 22 */     PacketDataSerializer var4 = new PacketDataSerializer(var2);
/*    */     
/* 24 */     if (var3 < this.c) {
/* 25 */       var4.d(0);
/* 26 */       var4.writeBytes(var1);
/*    */     } else {
/* 28 */       byte[] var5 = new byte[var3];
/* 29 */       var1.readBytes(var5);
/*    */       
/* 31 */       var4.d(var5.length);
/*    */       
/* 33 */       this.b.setInput(var5, 0, var3);
/* 34 */       this.b.finish();
/* 35 */       while (!this.b.finished()) {
/* 36 */         int var6 = this.b.deflate(this.a);
/* 37 */         var4.writeBytes(this.a, 0, var6);
/*    */       } 
/* 39 */       this.b.reset();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(int var0) {
/* 48 */     this.c = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketCompressor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */