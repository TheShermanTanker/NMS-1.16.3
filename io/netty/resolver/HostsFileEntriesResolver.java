/*    */ package io.netty.resolver;
/*    */ 
/*    */ import java.net.InetAddress;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface HostsFileEntriesResolver
/*    */ {
/* 31 */   public static final HostsFileEntriesResolver DEFAULT = new DefaultHostsFileEntriesResolver();
/*    */   
/*    */   InetAddress address(String paramString, ResolvedAddressTypes paramResolvedAddressTypes);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\HostsFileEntriesResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */