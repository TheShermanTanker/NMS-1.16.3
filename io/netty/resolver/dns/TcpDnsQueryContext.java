/*    */ package io.netty.resolver.dns;
/*    */ 
/*    */ import io.netty.channel.AddressedEnvelope;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.handler.codec.dns.DefaultDnsQuery;
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
/*    */ final class TcpDnsQueryContext
/*    */   extends DnsQueryContext
/*    */ {
/*    */   private final Channel channel;
/*    */   
/*    */   TcpDnsQueryContext(DnsNameResolver parent, Channel channel, InetSocketAddress nameServerAddr, DnsQuestion question, DnsRecord[] additionals, Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> promise) {
/* 35 */     super(parent, nameServerAddr, question, additionals, promise);
/* 36 */     this.channel = channel;
/*    */   }
/*    */ 
/*    */   
/*    */   protected DnsQuery newQuery(int id) {
/* 41 */     return (DnsQuery)new DefaultDnsQuery(id);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Channel channel() {
/* 46 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String protocol() {
/* 51 */     return "TCP";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\TcpDnsQueryContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */