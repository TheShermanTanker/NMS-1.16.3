/*    */ package com.destroystokyo.paper.network;
/*    */ 
/*    */ import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.CachedServerIcon;
/*    */ 
/*    */ class PaperServerListPingEventImpl
/*    */   extends PaperServerListPingEvent
/*    */ {
/*    */   private final MinecraftServer server;
/*    */   
/*    */   PaperServerListPingEventImpl(MinecraftServer server, StatusClient client, int protocolVersion, @Nullable CachedServerIcon icon) {
/* 16 */     super(client, server.getMotd(), server.getPlayerCount(), server.getMaxPlayers(), server
/* 17 */         .getServerModName() + ' ' + server.getVersion(), protocolVersion, icon);
/* 18 */     this.server = server;
/*    */   }
/*    */ 
/*    */   
/*    */   protected final Object[] getOnlinePlayers() {
/* 23 */     return (this.server.getPlayerList()).players.toArray();
/*    */   }
/*    */ 
/*    */   
/*    */   protected final Player getBukkitPlayer(Object player) {
/* 28 */     return (Player)((EntityPlayer)player).getBukkitEntity();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\network\PaperServerListPingEventImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */