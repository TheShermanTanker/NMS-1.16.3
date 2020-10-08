/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import io.netty.handler.codec.DecoderException;
/*    */ import java.util.List;
/*    */ import java.util.zip.Inflater;
/*    */ 
/*    */ 
/*    */ public class PacketDecompressor
/*    */   extends ByteToMessageDecoder
/*    */ {
/*    */   private final Inflater a;
/*    */   private int b;
/*    */   
/*    */   public PacketDecompressor(int var0) {
/* 19 */     this.b = var0;
/* 20 */     this.a = new Inflater();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext var0, ByteBuf var1, List<Object> var2) throws Exception {
/* 25 */     if (var1.readableBytes() == 0) {
/*    */       return;
/*    */     }
/*    */     
/* 29 */     PacketDataSerializer var3 = new PacketDataSerializer(var1);
/* 30 */     int var4 = var3.i();
/*    */     
/* 32 */     if (var4 == 0)
/* 33 */     { var2.add(var3.readBytes(var3.readableBytes())); }
/* 34 */     else { if (var4 < this.b)
/* 35 */         throw new DecoderException("Badly compressed packet - size of " + var4 + " is below server threshold of " + this.b); 
/* 36 */       if (var4 > 2097152) {
/* 37 */         throw new DecoderException("Badly compressed packet - size of " + var4 + " is larger than protocol maximum of " + 2097152);
/*    */       }
/* 39 */       byte[] var5 = new byte[var3.readableBytes()];
/* 40 */       var3.readBytes(var5);
/* 41 */       this.a.setInput(var5);
/*    */       
/* 43 */       byte[] var6 = new byte[var4];
/* 44 */       this.a.inflate(var6);
/* 45 */       var2.add(Unpooled.wrappedBuffer(var6));
/*    */       
/* 47 */       this.a.reset(); }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(int var0) {
/* 56 */     this.b = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketDecompressor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */