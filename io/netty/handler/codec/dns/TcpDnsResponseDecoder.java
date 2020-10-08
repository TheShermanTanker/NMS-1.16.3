/*    */ package io.netty.handler.codec.dns;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
/*    */ import java.net.SocketAddress;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TcpDnsResponseDecoder
/*    */   extends LengthFieldBasedFrameDecoder
/*    */ {
/*    */   private final DnsResponseDecoder<SocketAddress> responseDecoder;
/*    */   
/*    */   public TcpDnsResponseDecoder() {
/* 34 */     this(DnsRecordDecoder.DEFAULT, 65536);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TcpDnsResponseDecoder(DnsRecordDecoder recordDecoder, int maxFrameLength) {
/* 43 */     super(maxFrameLength, 0, 2, 0, 2);
/*    */     
/* 45 */     this.responseDecoder = new DnsResponseDecoder<SocketAddress>(recordDecoder)
/*    */       {
/*    */         protected DnsResponse newResponse(SocketAddress sender, SocketAddress recipient, int id, DnsOpCode opCode, DnsResponseCode responseCode)
/*    */         {
/* 49 */           return new DefaultDnsResponse(id, opCode, responseCode);
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
/* 56 */     ByteBuf frame = (ByteBuf)super.decode(ctx, in);
/* 57 */     if (frame == null) {
/* 58 */       return null;
/*    */     }
/*    */     
/*    */     try {
/* 62 */       return this.responseDecoder.decode(ctx.channel().remoteAddress(), ctx.channel().localAddress(), frame.slice());
/*    */     } finally {
/* 64 */       frame.release();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer, int index, int length) {
/* 70 */     return buffer.copy(index, length);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\dns\TcpDnsResponseDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */