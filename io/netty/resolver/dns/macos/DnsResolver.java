/*    */ package io.netty.resolver.dns.macos;
/*    */ 
/*    */ import io.netty.channel.unix.NativeInetAddress;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class DnsResolver
/*    */ {
/*    */   private final String domain;
/*    */   private final InetSocketAddress[] nameservers;
/*    */   private final int port;
/*    */   private final String[] searches;
/*    */   private final String options;
/*    */   private final int timeout;
/*    */   private final int searchOrder;
/*    */   
/*    */   DnsResolver(String domain, byte[][] nameservers, int port, String[] searches, String options, int timeout, int searchOrder) {
/* 37 */     this.domain = domain;
/* 38 */     if (nameservers == null) {
/* 39 */       this.nameservers = new InetSocketAddress[0];
/*    */     } else {
/* 41 */       this.nameservers = new InetSocketAddress[nameservers.length];
/* 42 */       for (int i = 0; i < nameservers.length; i++) {
/* 43 */         byte[] addr = nameservers[i];
/* 44 */         this.nameservers[i] = NativeInetAddress.address(addr, 0, addr.length);
/*    */       } 
/*    */     } 
/* 47 */     this.port = port;
/* 48 */     this.searches = searches;
/* 49 */     this.options = options;
/* 50 */     this.timeout = timeout;
/* 51 */     this.searchOrder = searchOrder;
/*    */   }
/*    */   
/*    */   String domain() {
/* 55 */     return this.domain;
/*    */   }
/*    */   
/*    */   InetSocketAddress[] nameservers() {
/* 59 */     return this.nameservers;
/*    */   }
/*    */   
/*    */   int port() {
/* 63 */     return this.port;
/*    */   }
/*    */   
/*    */   String[] searches() {
/* 67 */     return this.searches;
/*    */   }
/*    */   
/*    */   String options() {
/* 71 */     return this.options;
/*    */   }
/*    */   
/*    */   int timeout() {
/* 75 */     return this.timeout;
/*    */   }
/*    */   
/*    */   int searchOrder() {
/* 79 */     return this.searchOrder;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\macos\DnsResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */