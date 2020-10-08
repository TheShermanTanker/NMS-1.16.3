/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ 
/*    */ 
/*    */ @Sharable
/*    */ public class PacketPrepender
/*    */   extends MessageToByteEncoder<ByteBuf>
/*    */ {
/*    */   protected void encode(ChannelHandlerContext var0, ByteBuf var1, ByteBuf var2) throws Exception {
/* 14 */     int var3 = var1.readableBytes();
/* 15 */     int var4 = PacketDataSerializer.a(var3);
/*    */     
/* 17 */     if (var4 > 3) {
/* 18 */       throw new IllegalArgumentException("unable to fit " + var3 + " into " + '\003');
/*    */     }
/*    */     
/* 21 */     PacketDataSerializer var5 = new PacketDataSerializer(var2);
/*    */     
/* 23 */     var5.ensureWritable(var4 + var3);
/*    */     
/* 25 */     var5.d(var3);
/* 26 */     var5.writeBytes(var1, var1.readerIndex(), var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPrepender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */