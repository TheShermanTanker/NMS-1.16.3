/*     */ package io.netty.handler.codec.dns;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.ByteBufUtil;
/*     */ import io.netty.handler.codec.CorruptedFrameException;
/*     */ import io.netty.util.CharsetUtil;
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
/*     */ final class DnsCodecUtil
/*     */ {
/*     */   static void encodeDomainName(String name, ByteBuf buf) {
/*  33 */     if (".".equals(name)) {
/*     */       
/*  35 */       buf.writeByte(0);
/*     */       
/*     */       return;
/*     */     } 
/*  39 */     String[] labels = name.split("\\.");
/*  40 */     for (String label : labels) {
/*  41 */       int labelLen = label.length();
/*  42 */       if (labelLen == 0) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/*  47 */       buf.writeByte(labelLen);
/*  48 */       ByteBufUtil.writeAscii(buf, label);
/*     */     } 
/*     */     
/*  51 */     buf.writeByte(0);
/*     */   }
/*     */   
/*     */   static String decodeDomainName(ByteBuf in) {
/*  55 */     int position = -1;
/*  56 */     int checked = 0;
/*  57 */     int end = in.writerIndex();
/*  58 */     int readable = in.readableBytes();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     if (readable == 0) {
/*  68 */       return ".";
/*     */     }
/*     */     
/*  71 */     StringBuilder name = new StringBuilder(readable << 1);
/*  72 */     while (in.isReadable()) {
/*  73 */       int len = in.readUnsignedByte();
/*  74 */       boolean pointer = ((len & 0xC0) == 192);
/*  75 */       if (pointer) {
/*  76 */         if (position == -1) {
/*  77 */           position = in.readerIndex() + 1;
/*     */         }
/*     */         
/*  80 */         if (!in.isReadable()) {
/*  81 */           throw new CorruptedFrameException("truncated pointer in a name");
/*     */         }
/*     */         
/*  84 */         int next = (len & 0x3F) << 8 | in.readUnsignedByte();
/*  85 */         if (next >= end) {
/*  86 */           throw new CorruptedFrameException("name has an out-of-range pointer");
/*     */         }
/*  88 */         in.readerIndex(next);
/*     */ 
/*     */         
/*  91 */         checked += 2;
/*  92 */         if (checked >= end)
/*  93 */           throw new CorruptedFrameException("name contains a loop.");  continue;
/*     */       } 
/*  95 */       if (len != 0) {
/*  96 */         if (!in.isReadable(len)) {
/*  97 */           throw new CorruptedFrameException("truncated label in a name");
/*     */         }
/*  99 */         name.append(in.toString(in.readerIndex(), len, CharsetUtil.UTF_8)).append('.');
/* 100 */         in.skipBytes(len);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (position != -1) {
/* 107 */       in.readerIndex(position);
/*     */     }
/*     */     
/* 110 */     if (name.length() == 0) {
/* 111 */       return ".";
/*     */     }
/*     */     
/* 114 */     if (name.charAt(name.length() - 1) != '.') {
/* 115 */       name.append('.');
/*     */     }
/*     */     
/* 118 */     return name.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static ByteBuf decompressDomainName(ByteBuf compression) {
/* 127 */     String domainName = decodeDomainName(compression);
/* 128 */     ByteBuf result = compression.alloc().buffer(domainName.length() << 1);
/* 129 */     encodeDomainName(domainName, result);
/* 130 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\dns\DnsCodecUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */