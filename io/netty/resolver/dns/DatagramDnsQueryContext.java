/*    */ package io.netty.resolver.dns;
/*    */ 
/*    */ import io.netty.channel.AddressedEnvelope;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.handler.codec.dns.DatagramDnsQuery;
/*    */ import io.netty.handler.codec.dns.DnsQuery;
/*    */ import io.netty.handler.codec.dns.DnsQuestion;
/*    */ import io.netty.handler.codec.dns.DnsRecord;
/*    */ import io.netty.handler.codec.dns.DnsResponse;
/*    */ import io.netty.util.concurrent.Promise;
/*    */ import java.net.InetSocketAddress;
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
/*    */ final class DatagramDnsQueryContext
/*    */   extends DnsQueryContext
/*    */ {
/*    */   DatagramDnsQueryContext(DnsNameResolver parent, InetSocketAddress nameServerAddr, DnsQuestion question, DnsRecord[] additionals, Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> promise) {
/* 34 */     super(parent, nameServerAddr, question, additionals, promise);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DnsQuery newQuery(int id) {
/* 39 */     return (DnsQuery)new DatagramDnsQuery(null, nameServerAddr(), id);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Channel channel() {
/* 44 */     return (Channel)(parent()).ch;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String protocol() {
/* 49 */     return "UDP";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\DatagramDnsQueryContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */