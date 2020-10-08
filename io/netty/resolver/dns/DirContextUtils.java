/*    */ package io.netty.resolver.dns;
/*    */ 
/*    */ import io.netty.util.internal.SocketUtils;
/*    */ import io.netty.util.internal.logging.InternalLogger;
/*    */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import java.util.Hashtable;
/*    */ import java.util.List;
/*    */ import javax.naming.NamingException;
/*    */ import javax.naming.directory.DirContext;
/*    */ import javax.naming.directory.InitialDirContext;
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
/*    */ final class DirContextUtils
/*    */ {
/* 34 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(DirContextUtils.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static void addNameServers(List<InetSocketAddress> defaultNameServers, int defaultPort) {
/* 44 */     Hashtable<String, String> env = new Hashtable<String, String>();
/* 45 */     env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
/* 46 */     env.put("java.naming.provider.url", "dns://");
/*    */     
/*    */     try {
/* 49 */       DirContext ctx = new InitialDirContext(env);
/* 50 */       String dnsUrls = (String)ctx.getEnvironment().get("java.naming.provider.url");
/*    */       
/* 52 */       if (dnsUrls != null && !dnsUrls.isEmpty()) {
/* 53 */         String[] servers = dnsUrls.split(" ");
/* 54 */         for (String server : servers) {
/*    */           try {
/* 56 */             URI uri = new URI(server);
/* 57 */             String host = (new URI(server)).getHost();
/*    */             
/* 59 */             if (host == null || host.isEmpty()) {
/* 60 */               logger.debug("Skipping a nameserver URI as host portion could not be extracted: {}", server);
/*    */             
/*    */             }
/*    */             else {
/*    */               
/* 65 */               int port = uri.getPort();
/* 66 */               defaultNameServers.add(SocketUtils.socketAddress(uri.getHost(), (port == -1) ? defaultPort : port));
/*    */             } 
/* 68 */           } catch (URISyntaxException e) {
/* 69 */             logger.debug("Skipping a malformed nameserver URI: {}", server, e);
/*    */           } 
/*    */         } 
/*    */       } 
/* 73 */     } catch (NamingException namingException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\DirContextUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */