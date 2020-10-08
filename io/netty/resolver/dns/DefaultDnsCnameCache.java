/*    */ package io.netty.resolver.dns;
/*    */ 
/*    */ import io.netty.channel.EventLoop;
/*    */ import io.netty.util.AsciiString;
/*    */ import io.netty.util.internal.ObjectUtil;
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
/*    */ public final class DefaultDnsCnameCache
/*    */   implements DnsCnameCache
/*    */ {
/*    */   private final int minTtl;
/*    */   private final int maxTtl;
/*    */   
/* 32 */   private final Cache<String> cache = new Cache<String>()
/*    */     {
/*    */       protected boolean shouldReplaceAll(String entry)
/*    */       {
/* 36 */         return true;
/*    */       }
/*    */ 
/*    */       
/*    */       protected boolean equals(String entry, String otherEntry) {
/* 41 */         return AsciiString.contentEqualsIgnoreCase(entry, otherEntry);
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DefaultDnsCnameCache() {
/* 49 */     this(0, Cache.MAX_SUPPORTED_TTL_SECS);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DefaultDnsCnameCache(int minTtl, int maxTtl) {
/* 59 */     this.minTtl = Math.min(Cache.MAX_SUPPORTED_TTL_SECS, ObjectUtil.checkPositiveOrZero(minTtl, "minTtl"));
/* 60 */     this.maxTtl = Math.min(Cache.MAX_SUPPORTED_TTL_SECS, ObjectUtil.checkPositive(maxTtl, "maxTtl"));
/* 61 */     if (minTtl > maxTtl) {
/* 62 */       throw new IllegalArgumentException("minTtl: " + minTtl + ", maxTtl: " + maxTtl + " (expected: 0 <= minTtl <= maxTtl)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String get(String hostname) {
/* 70 */     List<? extends String> cached = this.cache.get((String)ObjectUtil.checkNotNull(hostname, "hostname"));
/* 71 */     if (cached == null || cached.isEmpty()) {
/* 72 */       return null;
/*    */     }
/*    */     
/* 75 */     return cached.get(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void cache(String hostname, String cname, long originalTtl, EventLoop loop) {
/* 80 */     ObjectUtil.checkNotNull(hostname, "hostname");
/* 81 */     ObjectUtil.checkNotNull(cname, "cname");
/* 82 */     ObjectUtil.checkNotNull(loop, "loop");
/* 83 */     this.cache.cache(hostname, cname, Math.max(this.minTtl, (int)Math.min(this.maxTtl, originalTtl)), loop);
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 88 */     this.cache.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean clear(String hostname) {
/* 93 */     return this.cache.clear((String)ObjectUtil.checkNotNull(hostname, "hostname"));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\DefaultDnsCnameCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */