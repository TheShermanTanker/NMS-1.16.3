/*     */ package io.netty.resolver.dns;
/*     */ 
/*     */ import io.netty.channel.EventLoop;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import io.netty.util.internal.PlatformDependent;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
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
/*     */ public class DefaultAuthoritativeDnsServerCache
/*     */   implements AuthoritativeDnsServerCache
/*     */ {
/*     */   private final int minTtl;
/*     */   private final int maxTtl;
/*     */   private final Comparator<InetSocketAddress> comparator;
/*     */   
/*  37 */   private final Cache<InetSocketAddress> resolveCache = new Cache<InetSocketAddress>()
/*     */     {
/*     */       protected boolean shouldReplaceAll(InetSocketAddress entry) {
/*  40 */         return false;
/*     */       }
/*     */ 
/*     */       
/*     */       protected boolean equals(InetSocketAddress entry, InetSocketAddress otherEntry) {
/*  45 */         if (PlatformDependent.javaVersion() >= 7) {
/*  46 */           return entry.getHostString().equalsIgnoreCase(otherEntry.getHostString());
/*     */         }
/*  48 */         return entry.getHostName().equalsIgnoreCase(otherEntry.getHostName());
/*     */       }
/*     */ 
/*     */       
/*     */       protected void sortEntries(String hostname, List<InetSocketAddress> entries) {
/*  53 */         if (DefaultAuthoritativeDnsServerCache.this.comparator != null) {
/*  54 */           Collections.sort(entries, DefaultAuthoritativeDnsServerCache.this.comparator);
/*     */         }
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultAuthoritativeDnsServerCache() {
/*  63 */     this(0, Cache.MAX_SUPPORTED_TTL_SECS, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultAuthoritativeDnsServerCache(int minTtl, int maxTtl, Comparator<InetSocketAddress> comparator) {
/*  75 */     this.minTtl = Math.min(Cache.MAX_SUPPORTED_TTL_SECS, ObjectUtil.checkPositiveOrZero(minTtl, "minTtl"));
/*  76 */     this.maxTtl = Math.min(Cache.MAX_SUPPORTED_TTL_SECS, ObjectUtil.checkPositive(maxTtl, "maxTtl"));
/*  77 */     if (minTtl > maxTtl) {
/*  78 */       throw new IllegalArgumentException("minTtl: " + minTtl + ", maxTtl: " + maxTtl + " (expected: 0 <= minTtl <= maxTtl)");
/*     */     }
/*     */     
/*  81 */     this.comparator = comparator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DnsServerAddressStream get(String hostname) {
/*  87 */     ObjectUtil.checkNotNull(hostname, "hostname");
/*     */     
/*  89 */     List<? extends InetSocketAddress> addresses = this.resolveCache.get(hostname);
/*  90 */     if (addresses == null || addresses.isEmpty()) {
/*  91 */       return null;
/*     */     }
/*  93 */     return new SequentialDnsServerAddressStream(addresses, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void cache(String hostname, InetSocketAddress address, long originalTtl, EventLoop loop) {
/*  98 */     ObjectUtil.checkNotNull(hostname, "hostname");
/*  99 */     ObjectUtil.checkNotNull(address, "address");
/* 100 */     ObjectUtil.checkNotNull(loop, "loop");
/*     */     
/* 102 */     if (PlatformDependent.javaVersion() >= 7 && address.getHostString() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 108 */     this.resolveCache.cache(hostname, address, Math.max(this.minTtl, (int)Math.min(this.maxTtl, originalTtl)), loop);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 113 */     this.resolveCache.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean clear(String hostname) {
/* 118 */     return this.resolveCache.clear((String)ObjectUtil.checkNotNull(hostname, "hostname"));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 123 */     return "DefaultAuthoritativeDnsServerCache(minTtl=" + this.minTtl + ", maxTtl=" + this.maxTtl + ", cached nameservers=" + this.resolveCache
/* 124 */       .size() + ')';
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\DefaultAuthoritativeDnsServerCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */