/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.player.PlayerHandshakeEvent;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ 
/*     */ public class HandshakeListener implements PacketHandshakingInListener {
/*  10 */   private static final Gson gson = new Gson();
/*     */   
/*  12 */   private static final HashMap<InetAddress, Long> throttleTracker = new HashMap<>();
/*  13 */   private static int throttleCounter = 0;
/*     */   
/*  15 */   private static final IChatBaseComponent a = new ChatComponentText("Ignoring status request"); private final MinecraftServer b; private final NetworkManager c;
/*     */   final NetworkManager getNetworkManager() {
/*  17 */     return this.c;
/*     */   }
/*     */   public HandshakeListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
/*  20 */     this.b = minecraftserver;
/*  21 */     this.c = networkmanager;
/*     */   }
/*     */   
/*     */   public void a(PacketHandshakingInSetProtocol packethandshakinginsetprotocol) {
/*     */     boolean proxyLogicEnabled, handledByEvent;
/*  26 */     switch (packethandshakinginsetprotocol.b()) {
/*     */       case LOGIN:
/*  28 */         this.c.setProtocol(EnumProtocol.LOGIN);
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/*  33 */           long currentTime = System.currentTimeMillis();
/*  34 */           long connectionThrottle = this.b.server.getConnectionThrottle();
/*  35 */           InetAddress address = ((InetSocketAddress)this.c.getSocketAddress()).getAddress();
/*     */           
/*  37 */           synchronized (throttleTracker) {
/*  38 */             if (throttleTracker.containsKey(address) && !"127.0.0.1".equals(address.getHostAddress()) && currentTime - ((Long)throttleTracker.get(address)).longValue() < connectionThrottle) {
/*  39 */               throttleTracker.put(address, Long.valueOf(currentTime));
/*  40 */               IChatBaseComponent chatmessage = CraftChatMessage.fromString(PaperConfig.connectionThrottleKickMessage, true)[0];
/*  41 */               this.c.sendPacket(new PacketLoginOutDisconnect(chatmessage));
/*  42 */               this.c.close(chatmessage);
/*     */               
/*     */               return;
/*     */             } 
/*  46 */             throttleTracker.put(address, Long.valueOf(currentTime));
/*  47 */             throttleCounter++;
/*  48 */             if (throttleCounter > 200) {
/*  49 */               throttleCounter = 0;
/*     */ 
/*     */               
/*  52 */               Iterator<Map.Entry<InetAddress, Long>> iter = throttleTracker.entrySet().iterator();
/*  53 */               while (iter.hasNext()) {
/*  54 */                 Map.Entry<InetAddress, Long> entry = iter.next();
/*  55 */                 if (((Long)entry.getValue()).longValue() > connectionThrottle) {
/*  56 */                   iter.remove();
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*  61 */         } catch (Throwable t) {
/*  62 */           LogManager.getLogger().debug("Failed to check connection throttle", t);
/*     */         } 
/*     */ 
/*     */         
/*  66 */         if (packethandshakinginsetprotocol.c() > SharedConstants.getGameVersion().getProtocolVersion()) {
/*  67 */           IChatBaseComponent chatmessage = CraftChatMessage.fromString(MessageFormat.format(SpigotConfig.outdatedServerMessage.replaceAll("'", "''"), new Object[] { SharedConstants.getGameVersion().getName() }), true)[0];
/*  68 */           this.c.sendPacket(new PacketLoginOutDisconnect(chatmessage));
/*  69 */           this.c.close(chatmessage); break;
/*  70 */         }  if (packethandshakinginsetprotocol.c() < SharedConstants.getGameVersion().getProtocolVersion()) {
/*  71 */           IChatBaseComponent chatmessage = CraftChatMessage.fromString(MessageFormat.format(SpigotConfig.outdatedClientMessage.replaceAll("'", "''"), new Object[] { SharedConstants.getGameVersion().getName() }), true)[0];
/*  72 */           this.c.sendPacket(new PacketLoginOutDisconnect(chatmessage));
/*  73 */           this.c.close(chatmessage); break;
/*     */         } 
/*  75 */         this.c.setPacketListener(new LoginListener(this.b, this.c));
/*     */         
/*  77 */         proxyLogicEnabled = SpigotConfig.bungee;
/*  78 */         handledByEvent = false;
/*     */         
/*  80 */         if ((PlayerHandshakeEvent.getHandlerList().getRegisteredListeners()).length != 0) {
/*  81 */           PlayerHandshakeEvent event = new PlayerHandshakeEvent(packethandshakinginsetprotocol.hostname, !proxyLogicEnabled);
/*  82 */           if (event.callEvent()) {
/*     */             
/*  84 */             if (event.isFailed()) {
/*  85 */               IChatBaseComponent chatmessage = CraftChatMessage.fromString(event.getFailMessage(), true)[0];
/*  86 */               getNetworkManager().sendPacket(new PacketLoginOutDisconnect(chatmessage));
/*  87 */               getNetworkManager().close(chatmessage);
/*     */               
/*     */               return;
/*     */             } 
/*  91 */             packethandshakinginsetprotocol.hostname = event.getServerHostname();
/*  92 */             (getNetworkManager()).socketAddress = new InetSocketAddress(event.getSocketAddressHostname(), ((InetSocketAddress)getNetworkManager().getSocketAddress()).getPort());
/*  93 */             (getNetworkManager()).spoofedUUID = event.getUniqueId();
/*  94 */             (getNetworkManager()).spoofedProfile = (Property[])gson.fromJson(event.getPropertiesJson(), Property[].class);
/*  95 */             handledByEvent = true;
/*     */           } 
/*     */         } 
/*     */         
/*  99 */         if (!handledByEvent && proxyLogicEnabled) {
/*     */ 
/*     */ 
/*     */           
/* 103 */           String[] split = packethandshakinginsetprotocol.hostname.split("\000");
/* 104 */           if (split.length == 3 || split.length == 4) {
/* 105 */             packethandshakinginsetprotocol.hostname = split[0];
/* 106 */             this.c.socketAddress = new InetSocketAddress(split[1], ((InetSocketAddress)this.c.getSocketAddress()).getPort());
/* 107 */             this.c.spoofedUUID = UUIDTypeAdapter.fromString(split[2]);
/*     */           } else {
/*     */             
/* 110 */             IChatBaseComponent chatmessage = new ChatMessage("If you wish to use IP forwarding, please enable it in your BungeeCord config as well!");
/* 111 */             this.c.sendPacket(new PacketLoginOutDisconnect(chatmessage));
/* 112 */             this.c.close(chatmessage);
/*     */             return;
/*     */           } 
/* 115 */           if (split.length == 4)
/*     */           {
/* 117 */             this.c.spoofedProfile = (Property[])gson.fromJson(split[3], Property[].class);
/*     */           }
/*     */         } 
/*     */         
/* 121 */         ((LoginListener)this.c.j()).hostname = packethandshakinginsetprotocol.hostname + ":" + packethandshakinginsetprotocol.port;
/*     */         break;
/*     */       
/*     */       case STATUS:
/* 125 */         if (this.b.al()) {
/* 126 */           this.c.setProtocol(EnumProtocol.STATUS);
/* 127 */           this.c.setPacketListener(new PacketStatusListener(this.b, this.c)); break;
/*     */         } 
/* 129 */         this.c.close(a);
/*     */         break;
/*     */       
/*     */       default:
/* 133 */         throw new UnsupportedOperationException("Invalid intention " + packethandshakinginsetprotocol.b());
/*     */     } 
/*     */ 
/*     */     
/* 137 */     (getNetworkManager()).protocolVersion = packethandshakinginsetprotocol.getProtocolVersion();
/* 138 */     (getNetworkManager()).virtualHost = PaperNetworkClient.prepareVirtualHost(packethandshakinginsetprotocol.hostname, packethandshakinginsetprotocol.port);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IChatBaseComponent ichatbasecomponent) {}
/*     */ 
/*     */   
/*     */   public NetworkManager a() {
/* 147 */     return this.c;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\HandshakeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */