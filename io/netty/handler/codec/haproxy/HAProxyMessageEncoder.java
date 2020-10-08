/*     */ package io.netty.handler.codec.haproxy;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.channel.ChannelHandler.Sharable;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.handler.codec.MessageToByteEncoder;
/*     */ import io.netty.util.CharsetUtil;
/*     */ import io.netty.util.NetUtil;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Sharable
/*     */ public final class HAProxyMessageEncoder
/*     */   extends MessageToByteEncoder<HAProxyMessage>
/*     */ {
/*     */   private static final int V2_VERSION_BITMASK = 32;
/*     */   static final int UNIX_ADDRESS_BYTES_LENGTH = 108;
/*     */   static final int TOTAL_UNIX_ADDRESS_BYTES_LENGTH = 216;
/*  43 */   public static final HAProxyMessageEncoder INSTANCE = new HAProxyMessageEncoder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void encode(ChannelHandlerContext ctx, HAProxyMessage msg, ByteBuf out) throws Exception {
/*  50 */     switch (msg.protocolVersion()) {
/*     */       case AF_IPv4:
/*  52 */         encodeV1(msg, out);
/*     */         return;
/*     */       case AF_IPv6:
/*  55 */         encodeV2(msg, out);
/*     */         return;
/*     */     } 
/*  58 */     throw new HAProxyProtocolException("Unsupported version: " + msg.protocolVersion());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void encodeV1(HAProxyMessage msg, ByteBuf out) {
/*  63 */     out.writeBytes(HAProxyConstants.TEXT_PREFIX);
/*  64 */     out.writeByte(32);
/*  65 */     out.writeCharSequence(msg.proxiedProtocol().name(), CharsetUtil.US_ASCII);
/*  66 */     out.writeByte(32);
/*  67 */     out.writeCharSequence(msg.sourceAddress(), CharsetUtil.US_ASCII);
/*  68 */     out.writeByte(32);
/*  69 */     out.writeCharSequence(msg.destinationAddress(), CharsetUtil.US_ASCII);
/*  70 */     out.writeByte(32);
/*  71 */     out.writeCharSequence(String.valueOf(msg.sourcePort()), CharsetUtil.US_ASCII);
/*  72 */     out.writeByte(32);
/*  73 */     out.writeCharSequence(String.valueOf(msg.destinationPort()), CharsetUtil.US_ASCII);
/*  74 */     out.writeByte(13);
/*  75 */     out.writeByte(10);
/*     */   } private static void encodeV2(HAProxyMessage msg, ByteBuf out) {
/*     */     byte[] srcAddrBytes, dstAddrBytes;
/*     */     int srcAddrBytesWritten, dstAddrBytesWritten;
/*  79 */     out.writeBytes(HAProxyConstants.BINARY_PREFIX);
/*  80 */     out.writeByte(0x20 | msg.command().byteValue());
/*  81 */     out.writeByte(msg.proxiedProtocol().byteValue());
/*     */     
/*  83 */     switch (msg.proxiedProtocol().addressFamily()) {
/*     */       case AF_IPv4:
/*     */       case AF_IPv6:
/*  86 */         srcAddrBytes = NetUtil.createByteArrayFromIpAddressString(msg.sourceAddress());
/*  87 */         dstAddrBytes = NetUtil.createByteArrayFromIpAddressString(msg.destinationAddress());
/*     */         
/*  89 */         out.writeShort(srcAddrBytes.length + dstAddrBytes.length + 4 + msg.tlvNumBytes());
/*  90 */         out.writeBytes(srcAddrBytes);
/*  91 */         out.writeBytes(dstAddrBytes);
/*  92 */         out.writeShort(msg.sourcePort());
/*  93 */         out.writeShort(msg.destinationPort());
/*  94 */         encodeTlvs(msg.tlvs(), out);
/*     */         return;
/*     */       case AF_UNIX:
/*  97 */         out.writeShort(216 + msg.tlvNumBytes());
/*  98 */         srcAddrBytesWritten = out.writeCharSequence(msg.sourceAddress(), CharsetUtil.US_ASCII);
/*  99 */         out.writeZero(108 - srcAddrBytesWritten);
/* 100 */         dstAddrBytesWritten = out.writeCharSequence(msg.destinationAddress(), CharsetUtil.US_ASCII);
/* 101 */         out.writeZero(108 - dstAddrBytesWritten);
/* 102 */         encodeTlvs(msg.tlvs(), out);
/*     */         return;
/*     */       case AF_UNSPEC:
/* 105 */         out.writeShort(0);
/*     */         return;
/*     */     } 
/* 108 */     throw new HAProxyProtocolException("unexpected addrFamily");
/*     */   }
/*     */ 
/*     */   
/*     */   private static void encodeTlv(HAProxyTLV haProxyTLV, ByteBuf out) {
/* 113 */     if (haProxyTLV instanceof HAProxySSLTLV) {
/* 114 */       HAProxySSLTLV ssltlv = (HAProxySSLTLV)haProxyTLV;
/* 115 */       out.writeByte(haProxyTLV.typeByteValue());
/* 116 */       out.writeShort(ssltlv.contentNumBytes());
/* 117 */       out.writeByte(ssltlv.client());
/* 118 */       out.writeInt(ssltlv.verify());
/* 119 */       encodeTlvs(ssltlv.encapsulatedTLVs(), out);
/*     */     } else {
/* 121 */       out.writeByte(haProxyTLV.typeByteValue());
/* 122 */       ByteBuf value = haProxyTLV.content();
/* 123 */       int readableBytes = value.readableBytes();
/* 124 */       out.writeShort(readableBytes);
/* 125 */       out.writeBytes(value.readSlice(readableBytes));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void encodeTlvs(List<HAProxyTLV> haProxyTLVs, ByteBuf out) {
/* 130 */     for (int i = 0; i < haProxyTLVs.size(); i++)
/* 131 */       encodeTlv(haProxyTLVs.get(i), out); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\haproxy\HAProxyMessageEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */