/*    */ package io.netty.resolver.dns;
/*    */ 
/*    */ import io.netty.channel.EventLoop;
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
/*    */ public final class NoopDnsCnameCache
/*    */   implements DnsCnameCache
/*    */ {
/* 22 */   public static final NoopDnsCnameCache INSTANCE = new NoopDnsCnameCache();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String get(String hostname) {
/* 28 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cache(String hostname, String cname, long originalTtl, EventLoop loop) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void clear() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean clear(String hostname) {
/* 43 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\NoopDnsCnameCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */