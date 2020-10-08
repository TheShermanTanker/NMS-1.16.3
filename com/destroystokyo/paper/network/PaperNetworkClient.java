/*    */ package com.destroystokyo.paper.network;
/*    */ 
/*    */ import java.net.InetSocketAddress;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.server.v1_16_R2.NetworkManager;
/*    */ 
/*    */ 
/*    */ public class PaperNetworkClient
/*    */   implements NetworkClient
/*    */ {
/*    */   private final NetworkManager networkManager;
/*    */   
/*    */   PaperNetworkClient(NetworkManager networkManager) {
/* 14 */     this.networkManager = networkManager;
/*    */   }
/*    */ 
/*    */   
/*    */   public InetSocketAddress getAddress() {
/* 19 */     return (InetSocketAddress)this.networkManager.getSocketAddress();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getProtocolVersion() {
/* 24 */     return this.networkManager.protocolVersion;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public InetSocketAddress getVirtualHost() {
/* 30 */     return this.networkManager.virtualHost;
/*    */   }
/*    */   
/*    */   public static InetSocketAddress prepareVirtualHost(String host, int port) {
/* 34 */     int len = host.length();
/*    */ 
/*    */     
/* 37 */     int pos = host.indexOf(false);
/* 38 */     if (pos >= 0) {
/* 39 */       len = pos;
/*    */     }
/*    */ 
/*    */     
/* 43 */     if (len > 0 && host.charAt(len - 1) == '.') {
/* 44 */       len--;
/*    */     }
/*    */     
/* 47 */     return InetSocketAddress.createUnresolved(host.substring(0, len), port);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\network\PaperNetworkClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */