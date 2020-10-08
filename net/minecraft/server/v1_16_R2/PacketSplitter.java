/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import io.netty.handler.codec.CorruptedFrameException;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PacketSplitter extends ByteToMessageDecoder {
/* 12 */   private final byte[] lenBuf = new byte[3];
/*    */ 
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf, List<Object> list) throws Exception {
/* 17 */     if (!channelhandlercontext.channel().isActive()) {
/* 18 */       bytebuf.skipBytes(bytebuf.readableBytes());
/*    */       
/*    */       return;
/*    */     } 
/* 22 */     bytebuf.markReaderIndex();
/*    */     
/* 24 */     byte[] abyte = this.lenBuf;
/* 25 */     Arrays.fill(abyte, (byte)0);
/*    */ 
/*    */     
/* 28 */     for (int i = 0; i < abyte.length; i++) {
/* 29 */       if (!bytebuf.isReadable()) {
/* 30 */         bytebuf.resetReaderIndex();
/*    */         
/*    */         return;
/*    */       } 
/* 34 */       abyte[i] = bytebuf.readByte();
/* 35 */       if (abyte[i] >= 0) {
/* 36 */         PacketDataSerializer packetdataserializer = new PacketDataSerializer(Unpooled.wrappedBuffer(abyte));
/*    */         
/*    */         try {
/* 39 */           int j = packetdataserializer.i();
/*    */           
/* 41 */           if (bytebuf.readableBytes() >= j) {
/* 42 */             list.add(bytebuf.readBytes(j));
/*    */             
/*    */             return;
/*    */           } 
/* 46 */           bytebuf.resetReaderIndex();
/*    */         } finally {
/* 48 */           packetdataserializer.release();
/*    */         } 
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     throw new CorruptedFrameException("length wider than 21-bit");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketSplitter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */