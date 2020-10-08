/*    */ package com.destroystokyo.paper.network;
/*    */ 
/*    */ import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
/*    */ import java.net.InetSocketAddress;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ public final class PaperLegacyStatusClient
/*    */   implements StatusClient {
/*    */   private final InetSocketAddress address;
/*    */   private final int protocolVersion;
/*    */   @Nullable
/*    */   private final InetSocketAddress virtualHost;
/*    */   
/*    */   private PaperLegacyStatusClient(InetSocketAddress address, int protocolVersion, @Nullable InetSocketAddress virtualHost) {
/* 19 */     this.address = address;
/* 20 */     this.protocolVersion = protocolVersion;
/* 21 */     this.virtualHost = virtualHost;
/*    */   }
/*    */ 
/*    */   
/*    */   public InetSocketAddress getAddress() {
/* 26 */     return this.address;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getProtocolVersion() {
/* 31 */     return this.protocolVersion;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public InetSocketAddress getVirtualHost() {
/* 37 */     return this.virtualHost;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLegacy() {
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static PaperServerListPingEvent processRequest(MinecraftServer server, InetSocketAddress address, int protocolVersion, @Nullable InetSocketAddress virtualHost) {
/* 48 */     PaperServerListPingEvent event = new PaperServerListPingEventImpl(server, new PaperLegacyStatusClient(address, protocolVersion, virtualHost), 127, null);
/*    */     
/* 50 */     server.server.getPluginManager().callEvent((Event)event);
/*    */     
/* 52 */     if (event.isCancelled()) {
/* 53 */       return null;
/*    */     }
/*    */     
/* 56 */     return event;
/*    */   }
/*    */   
/*    */   public static String getMotd(PaperServerListPingEvent event) {
/* 60 */     return getFirstLine(event.getMotd());
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getUnformattedMotd(PaperServerListPingEvent event) {
/* 65 */     return getFirstLine(StringUtils.remove(ChatColor.stripColor(event.getMotd()), '§'));
/*    */   }
/*    */   
/*    */   private static String getFirstLine(String s) {
/* 69 */     int pos = s.indexOf('\n');
/* 70 */     return (pos >= 0) ? s.substring(0, pos) : s;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\network\PaperLegacyStatusClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */