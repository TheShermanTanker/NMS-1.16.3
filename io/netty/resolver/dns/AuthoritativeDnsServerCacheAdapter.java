/*    */ package io.netty.resolver.dns;
/*    */ 
/*    */ import io.netty.channel.EventLoop;
/*    */ import io.netty.handler.codec.dns.DnsRecord;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import java.net.InetAddress;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ final class AuthoritativeDnsServerCacheAdapter
/*    */   implements AuthoritativeDnsServerCache
/*    */ {
/* 34 */   private static final DnsRecord[] EMPTY = new DnsRecord[0];
/*    */   private final DnsCache cache;
/*    */   
/*    */   AuthoritativeDnsServerCacheAdapter(DnsCache cache) {
/* 38 */     this.cache = (DnsCache)ObjectUtil.checkNotNull(cache, "cache");
/*    */   }
/*    */ 
/*    */   
/*    */   public DnsServerAddressStream get(String hostname) {
/* 43 */     List<? extends DnsCacheEntry> entries = this.cache.get(hostname, EMPTY);
/* 44 */     if (entries == null || entries.isEmpty()) {
/* 45 */       return null;
/*    */     }
/* 47 */     if (((DnsCacheEntry)entries.get(0)).cause() != null) {
/* 48 */       return null;
/*    */     }
/*    */     
/* 51 */     List<InetSocketAddress> addresses = new ArrayList<InetSocketAddress>(entries.size());
/*    */     
/* 53 */     int i = 0;
/*    */     while (true) {
/* 55 */       InetAddress addr = ((DnsCacheEntry)entries.get(i)).address();
/* 56 */       addresses.add(new InetSocketAddress(addr, 53));
/* 57 */       if (++i >= entries.size()) {
/* 58 */         return new SequentialDnsServerAddressStream(addresses, 0);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void cache(String hostname, InetSocketAddress address, long originalTtl, EventLoop loop) {
/* 64 */     if (!address.isUnresolved()) {
/* 65 */       this.cache.cache(hostname, EMPTY, address.getAddress(), originalTtl, loop);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 71 */     this.cache.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean clear(String hostname) {
/* 76 */     return this.cache.clear(hostname);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\AuthoritativeDnsServerCacheAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */