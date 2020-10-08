/*    */ package io.netty.handler.codec.dns;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.util.internal.ObjectUtil;
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
/*    */ final class DnsQueryEncoder
/*    */ {
/*    */   private final DnsRecordEncoder recordEncoder;
/*    */   
/*    */   DnsQueryEncoder(DnsRecordEncoder recordEncoder) {
/* 30 */     this.recordEncoder = (DnsRecordEncoder)ObjectUtil.checkNotNull(recordEncoder, "recordEncoder");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void encode(DnsQuery query, ByteBuf out) throws Exception {
/* 37 */     encodeHeader(query, out);
/* 38 */     encodeQuestions(query, out);
/* 39 */     encodeRecords(query, DnsSection.ADDITIONAL, out);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void encodeHeader(DnsQuery query, ByteBuf buf) {
/* 49 */     buf.writeShort(query.id());
/* 50 */     int flags = 0;
/* 51 */     flags |= (query.opCode().byteValue() & 0xFF) << 14;
/* 52 */     if (query.isRecursionDesired()) {
/* 53 */       flags |= 0x100;
/*    */     }
/* 55 */     buf.writeShort(flags);
/* 56 */     buf.writeShort(query.count(DnsSection.QUESTION));
/* 57 */     buf.writeShort(0);
/* 58 */     buf.writeShort(0);
/* 59 */     buf.writeShort(query.count(DnsSection.ADDITIONAL));
/*    */   }
/*    */   
/*    */   private void encodeQuestions(DnsQuery query, ByteBuf buf) throws Exception {
/* 63 */     int count = query.count(DnsSection.QUESTION);
/* 64 */     for (int i = 0; i < count; i++) {
/* 65 */       this.recordEncoder.encodeQuestion(query.<DnsQuestion>recordAt(DnsSection.QUESTION, i), buf);
/*    */     }
/*    */   }
/*    */   
/*    */   private void encodeRecords(DnsQuery query, DnsSection section, ByteBuf buf) throws Exception {
/* 70 */     int count = query.count(section);
/* 71 */     for (int i = 0; i < count; i++)
/* 72 */       this.recordEncoder.encodeRecord(query.recordAt(section, i), buf); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\dns\DnsQueryEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */