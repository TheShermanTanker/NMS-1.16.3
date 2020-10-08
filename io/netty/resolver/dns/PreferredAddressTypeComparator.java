/*    */ package io.netty.resolver.dns;
/*    */ 
/*    */ import io.netty.channel.socket.InternetProtocolFamily;
/*    */ import java.net.Inet4Address;
/*    */ import java.net.Inet6Address;
/*    */ import java.net.InetAddress;
/*    */ import java.util.Comparator;
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
/*    */ final class PreferredAddressTypeComparator
/*    */   implements Comparator<InetAddress>
/*    */ {
/* 27 */   private static final PreferredAddressTypeComparator IPv4 = new PreferredAddressTypeComparator((Class)Inet4Address.class);
/* 28 */   private static final PreferredAddressTypeComparator IPv6 = new PreferredAddressTypeComparator((Class)Inet6Address.class);
/*    */   
/*    */   static PreferredAddressTypeComparator comparator(InternetProtocolFamily family) {
/* 31 */     switch (family) {
/*    */       case IPv4:
/* 33 */         return IPv4;
/*    */       case IPv6:
/* 35 */         return IPv6;
/*    */     } 
/* 37 */     throw new IllegalArgumentException();
/*    */   }
/*    */ 
/*    */   
/*    */   private final Class<? extends InetAddress> preferredAddressType;
/*    */   
/*    */   private PreferredAddressTypeComparator(Class<? extends InetAddress> preferredAddressType) {
/* 44 */     this.preferredAddressType = preferredAddressType;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compare(InetAddress o1, InetAddress o2) {
/* 49 */     if (o1.getClass() == o2.getClass()) {
/* 50 */       return 0;
/*    */     }
/* 52 */     return this.preferredAddressType.isAssignableFrom(o1.getClass()) ? -1 : 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\PreferredAddressTypeComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */