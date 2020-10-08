/*    */ package io.netty.handler.codec.dns;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
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
/*    */ @Sharable
/*    */ public final class TcpDnsQueryEncoder
/*    */   extends MessageToByteEncoder<DnsQuery>
/*    */ {
/*    */   private final DnsQueryEncoder encoder;
/*    */   
/*    */   public TcpDnsQueryEncoder() {
/* 34 */     this(DnsRecordEncoder.DEFAULT);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TcpDnsQueryEncoder(DnsRecordEncoder recordEncoder) {
/* 41 */     this.encoder = new DnsQueryEncoder(recordEncoder);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void encode(ChannelHandlerContext ctx, DnsQuery msg, ByteBuf out) throws Exception {
/* 48 */     out.writerIndex(out.writerIndex() + 2);
/* 49 */     this.encoder.encode(msg, out);
/*    */ 
/*    */     
/* 52 */     out.setShort(0, out.readableBytes() - 2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, DnsQuery msg, boolean preferDirect) {
/* 58 */     if (preferDirect) {
/* 59 */       return ctx.alloc().ioBuffer(1024);
/*    */     }
/* 61 */     return ctx.alloc().heapBuffer(1024);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\dns\TcpDnsQueryEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */