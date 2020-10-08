/*     */ package com.destroystokyo.paper.network;
/*     */ 
/*     */ import com.destroystokyo.paper.profile.CraftPlayerProfile;
/*     */ import com.destroystokyo.paper.profile.PlayerProfile;
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.common.base.Strings;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.NetworkManager;
/*     */ import net.minecraft.server.v1_16_R2.Packet;
/*     */ import net.minecraft.server.v1_16_R2.PacketStatusOutServerInfo;
/*     */ import net.minecraft.server.v1_16_R2.ServerPing;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.util.CachedServerIcon;
/*     */ 
/*     */ public final class StandardPaperServerListPingEventImpl extends PaperServerListPingEventImpl {
/*  21 */   private static final GameProfile[] EMPTY_PROFILES = new GameProfile[0];
/*  22 */   private static final UUID FAKE_UUID = new UUID(0L, 0L);
/*     */   
/*     */   private GameProfile[] originalSample;
/*     */   
/*     */   private StandardPaperServerListPingEventImpl(MinecraftServer server, NetworkManager networkManager, ServerPing ping) {
/*  27 */     super(server, new PaperStatusClient(networkManager), (ping.getServerData() != null) ? ping.getServerData().getProtocolVersion() : -1, (CachedServerIcon)server.server.getServerIcon());
/*  28 */     this.originalSample = (ping.getPlayers() == null) ? null : ping.getPlayers().getSample();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public List<PlayerProfile> getPlayerSample() {
/*  34 */     List<PlayerProfile> sample = super.getPlayerSample();
/*     */     
/*  36 */     if (this.originalSample != null) {
/*  37 */       for (GameProfile profile : this.originalSample) {
/*  38 */         sample.add(CraftPlayerProfile.asBukkitCopy(profile));
/*     */       }
/*  40 */       this.originalSample = null;
/*     */     } 
/*     */     
/*  43 */     return sample;
/*     */   }
/*     */   
/*     */   private GameProfile[] getPlayerSampleHandle() {
/*  47 */     if (this.originalSample != null) {
/*  48 */       return this.originalSample;
/*     */     }
/*     */     
/*  51 */     List<PlayerProfile> entries = super.getPlayerSample();
/*  52 */     if (entries.isEmpty()) {
/*  53 */       return EMPTY_PROFILES;
/*     */     }
/*     */     
/*  56 */     GameProfile[] profiles = new GameProfile[entries.size()];
/*  57 */     for (int i = 0; i < profiles.length; i++) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  66 */       PlayerProfile profile = entries.get(i);
/*  67 */       if (profile.getId() != null && profile.getName() != null) {
/*  68 */         profiles[i] = CraftPlayerProfile.asAuthlib(profile);
/*     */       } else {
/*  70 */         profiles[i] = new GameProfile((UUID)MoreObjects.firstNonNull(profile.getId(), FAKE_UUID), Strings.nullToEmpty(profile.getName()));
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     return profiles;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processRequest(MinecraftServer server, NetworkManager networkManager) {
/*  79 */     StandardPaperServerListPingEventImpl event = new StandardPaperServerListPingEventImpl(server, networkManager, server.getServerPing());
/*  80 */     server.server.getPluginManager().callEvent((Event)event);
/*     */ 
/*     */     
/*  83 */     if (event.isCancelled()) {
/*  84 */       networkManager.close(null);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  89 */     ServerPing ping = new ServerPing();
/*     */ 
/*     */     
/*  92 */     ping.setMOTD(CraftChatMessage.fromString(event.getMotd(), true)[0]);
/*     */ 
/*     */     
/*  95 */     if (!event.shouldHidePlayers()) {
/*  96 */       ping.setPlayerSample(new ServerPing.ServerPingPlayerSample(event.getMaxPlayers(), event.getNumPlayers()));
/*  97 */       ping.getPlayers().setSample(event.getPlayerSampleHandle());
/*     */     } 
/*     */ 
/*     */     
/* 101 */     ping.setServerInfo(new ServerPing.ServerData(event.getVersion(), event.getProtocolVersion()));
/*     */ 
/*     */     
/* 104 */     if (event.getServerIcon() != null) {
/* 105 */       ping.setFavicon(event.getServerIcon().getData());
/*     */     }
/*     */ 
/*     */     
/* 109 */     networkManager.sendPacket((Packet)new PacketStatusOutServerInfo(ping));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\network\StandardPaperServerListPingEventImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */