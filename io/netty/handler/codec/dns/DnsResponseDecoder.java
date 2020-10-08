/*     */ package io.netty.handler.codec.dns;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.handler.codec.CorruptedFrameException;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import java.net.SocketAddress;
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
/*     */ abstract class DnsResponseDecoder<A extends SocketAddress>
/*     */ {
/*     */   private final DnsRecordDecoder recordDecoder;
/*     */   
/*     */   DnsResponseDecoder(DnsRecordDecoder recordDecoder) {
/*  33 */     this.recordDecoder = (DnsRecordDecoder)ObjectUtil.checkNotNull(recordDecoder, "recordDecoder");
/*     */   }
/*     */   
/*     */   final DnsResponse decode(A sender, A recipient, ByteBuf buffer) throws Exception {
/*  37 */     int id = buffer.readUnsignedShort();
/*     */     
/*  39 */     int flags = buffer.readUnsignedShort();
/*  40 */     if (flags >> 15 == 0) {
/*  41 */       throw new CorruptedFrameException("not a response");
/*     */     }
/*     */     
/*  44 */     DnsResponse response = newResponse(sender, recipient, id, 
/*     */ 
/*     */ 
/*     */         
/*  48 */         DnsOpCode.valueOf((byte)(flags >> 11 & 0xF)), DnsResponseCode.valueOf((byte)(flags & 0xF)));
/*     */     
/*  50 */     response.setRecursionDesired(((flags >> 8 & 0x1) == 1));
/*  51 */     response.setAuthoritativeAnswer(((flags >> 10 & 0x1) == 1));
/*  52 */     response.setTruncated(((flags >> 9 & 0x1) == 1));
/*  53 */     response.setRecursionAvailable(((flags >> 7 & 0x1) == 1));
/*  54 */     response.setZ(flags >> 4 & 0x7);
/*     */     
/*  56 */     boolean success = false;
/*     */     try {
/*  58 */       int questionCount = buffer.readUnsignedShort();
/*  59 */       int answerCount = buffer.readUnsignedShort();
/*  60 */       int authorityRecordCount = buffer.readUnsignedShort();
/*  61 */       int additionalRecordCount = buffer.readUnsignedShort();
/*     */       
/*  63 */       decodeQuestions(response, buffer, questionCount);
/*  64 */       if (!decodeRecords(response, DnsSection.ANSWER, buffer, answerCount)) {
/*  65 */         success = true;
/*  66 */         return response;
/*     */       } 
/*  68 */       if (!decodeRecords(response, DnsSection.AUTHORITY, buffer, authorityRecordCount)) {
/*  69 */         success = true;
/*  70 */         return response;
/*     */       } 
/*     */       
/*  73 */       decodeRecords(response, DnsSection.ADDITIONAL, buffer, additionalRecordCount);
/*  74 */       success = true;
/*  75 */       return response;
/*     */     } finally {
/*  77 */       if (!success) {
/*  78 */         response.release();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract DnsResponse newResponse(A paramA1, A paramA2, int paramInt, DnsOpCode paramDnsOpCode, DnsResponseCode paramDnsResponseCode) throws Exception;
/*     */   
/*     */   private void decodeQuestions(DnsResponse response, ByteBuf buf, int questionCount) throws Exception {
/*  87 */     for (int i = questionCount; i > 0; i--) {
/*  88 */       response.addRecord(DnsSection.QUESTION, this.recordDecoder.decodeQuestion(buf));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean decodeRecords(DnsResponse response, DnsSection section, ByteBuf buf, int count) throws Exception {
/*  94 */     for (int i = count; i > 0; i--) {
/*  95 */       DnsRecord r = this.recordDecoder.decodeRecord(buf);
/*  96 */       if (r == null)
/*     */       {
/*  98 */         return false;
/*     */       }
/*     */       
/* 101 */       response.addRecord(section, r);
/*     */     } 
/* 103 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\dns\DnsResponseDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */