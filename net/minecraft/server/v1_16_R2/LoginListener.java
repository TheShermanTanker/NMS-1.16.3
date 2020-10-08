/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.destroystokyo.paper.profile.CraftPlayerProfile;
/*     */ import com.destroystokyo.paper.profile.PlayerProfile;
/*     */ import com.destroystokyo.paper.proxy.VelocityProxy;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.math.BigInteger;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.security.PrivateKey;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.logging.Level;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.crypto.SecretKey;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.Waitable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
/*     */ import org.bukkit.event.player.PlayerPreLoginEvent;
/*     */ 
/*     */ public class LoginListener implements PacketLoginInListener {
/*  32 */   private static final AtomicInteger b = new AtomicInteger(0);
/*  33 */   private static final Logger LOGGER = LogManager.getLogger();
/*  34 */   private static final Random random = new Random(); private final MinecraftServer server; public final NetworkManager networkManager; private EnumProtocolState g; private int h;
/*  35 */   private final byte[] e = new byte[4]; private GameProfile i; private final String j; private SecretKey loginKey; private EntityPlayer l;
/*     */   
/*     */   public final EnumProtocolState getLoginState() {
/*  38 */     return this.g;
/*     */   }
/*  40 */   private void setGameProfile(GameProfile profile) { this.i = profile; } public GameProfile getGameProfile() { return this.i; }
/*     */ 
/*     */ 
/*     */   
/*  44 */   public String hostname = "";
/*  45 */   private int velocityLoginMessageId = -1;
/*     */   
/*     */   public LoginListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
/*  48 */     this.g = EnumProtocolState.HELLO;
/*  49 */     this.j = "";
/*  50 */     this.server = minecraftserver;
/*  51 */     this.networkManager = networkmanager;
/*  52 */     random.nextBytes(this.e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  57 */     if (!MinecraftServer.getServer().isRunning()) {
/*  58 */       disconnect(CraftChatMessage.fromString(SpigotConfig.restartMessage)[0]);
/*     */       
/*     */       return;
/*     */     } 
/*  62 */     if (this.g == EnumProtocolState.READY_TO_ACCEPT) {
/*     */       
/*  64 */       if (this.networkManager.isConnected()) {
/*  65 */         c();
/*     */       }
/*     */     }
/*  68 */     else if (this.g == EnumProtocolState.DELAY_ACCEPT) {
/*  69 */       EntityPlayer entityplayer = this.server.getPlayerList().getActivePlayer(this.i.getId());
/*     */       
/*  71 */       if (entityplayer == null) {
/*  72 */         this.g = EnumProtocolState.READY_TO_ACCEPT;
/*  73 */         this.server.getPlayerList().a(this.networkManager, this.l);
/*  74 */         this.l = null;
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     if (this.h++ == 600) {
/*  79 */       disconnect(new ChatMessage("multiplayer.disconnect.slow_login"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void disconnect(String s) {
/*  87 */     disconnect(CraftChatMessage.fromString(s, true)[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NetworkManager a() {
/*  93 */     return this.networkManager;
/*     */   }
/*     */   
/*     */   public void disconnect(IChatBaseComponent ichatbasecomponent) {
/*     */     try {
/*  98 */       LOGGER.info("Disconnecting {}: {}", d(), ichatbasecomponent.getString());
/*  99 */       this.networkManager.sendPacket(new PacketLoginOutDisconnect(ichatbasecomponent));
/* 100 */       this.networkManager.close(ichatbasecomponent);
/* 101 */     } catch (Exception exception) {
/* 102 */       LOGGER.error("Error whilst disconnecting player", exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 108 */   private static final AtomicInteger threadId = new AtomicInteger(0); private static final ExecutorService authenticatorPool; static {
/* 109 */     authenticatorPool = Executors.newCachedThreadPool(r -> new Thread(r, "User Authenticator #" + threadId.incrementAndGet()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initUUID() {
/*     */     UUID uuid;
/* 117 */     if (this.networkManager.spoofedUUID != null) {
/*     */       
/* 119 */       uuid = this.networkManager.spoofedUUID;
/*     */     } else {
/*     */       
/* 122 */       uuid = EntityHuman.getOfflineUUID(this.i.getName());
/*     */     } 
/*     */     
/* 125 */     this.i = new GameProfile(uuid, this.i.getName());
/*     */     
/* 127 */     if (this.networkManager.spoofedProfile != null)
/*     */     {
/* 129 */       for (Property property : this.networkManager.spoofedProfile)
/*     */       {
/* 131 */         this.i.getProperties().put(property.getName(), property);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() {
/* 147 */     EntityPlayer s = this.server.getPlayerList().attemptLogin(this, this.i, this.hostname);
/*     */     
/* 149 */     if (s != null) {
/*     */ 
/*     */ 
/*     */       
/* 153 */       this.g = EnumProtocolState.ACCEPTED;
/* 154 */       if (this.server.aw() >= 0 && !this.networkManager.isLocal()) {
/* 155 */         this.networkManager.sendPacket(new PacketLoginOutSetCompression(this.server.aw()), channelfuture -> this.networkManager.setCompressionLevel(this.server.aw()));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 160 */       this.networkManager.sendPacket(new PacketLoginOutSuccess(this.i));
/* 161 */       EntityPlayer entityplayer = this.server.getPlayerList().getActivePlayer(this.i.getId());
/*     */       
/* 163 */       if (entityplayer != null) {
/* 164 */         this.g = EnumProtocolState.DELAY_ACCEPT;
/* 165 */         this.l = this.server.getPlayerList().processLogin(this.i, s);
/*     */       } else {
/* 167 */         this.server.getPlayerList().a(this.networkManager, this.server.getPlayerList().processLogin(this.i, s));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IChatBaseComponent ichatbasecomponent) {
/* 175 */     LOGGER.info("{} lost connection: {}", d(), ichatbasecomponent.getString());
/*     */   }
/*     */   
/*     */   public String d() {
/* 179 */     return (this.i != null) ? (this.i + " (" + this.networkManager.getSocketAddress() + ")") : String.valueOf(this.networkManager.getSocketAddress());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketLoginInStart packetlogininstart) {
/* 184 */     Validate.validState((this.g == EnumProtocolState.HELLO), "Unexpected hello packet", new Object[0]);
/* 185 */     this.i = packetlogininstart.b();
/* 186 */     if (this.server.getOnlineMode() && !this.networkManager.isLocal()) {
/* 187 */       this.g = EnumProtocolState.KEY;
/* 188 */       this.networkManager.sendPacket(new PacketLoginOutEncryptionBegin("", this.server.getKeyPair().getPublic(), this.e));
/*     */     } else {
/*     */       
/* 191 */       if (PaperConfig.velocitySupport) {
/* 192 */         this.velocityLoginMessageId = ThreadLocalRandom.current().nextInt();
/* 193 */         PacketLoginOutCustomPayload packet = new PacketLoginOutCustomPayload(this.velocityLoginMessageId, VelocityProxy.PLAYER_INFO_CHANNEL, new PacketDataSerializer(Unpooled.EMPTY_BUFFER));
/* 194 */         this.networkManager.sendPacket(packet);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 200 */       authenticatorPool.execute(new Runnable()
/*     */           {
/*     */             public void run() {
/*     */               try {
/* 204 */                 LoginListener.this.initUUID();
/* 205 */                 (new LoginListener.LoginHandler()).fireEvents();
/* 206 */               } catch (Exception ex) {
/* 207 */                 LoginListener.this.disconnect("Failed to verify username!");
/* 208 */                 LoginListener.this.server.server.getLogger().log(Level.WARNING, "Exception verifying " + LoginListener.this.i.getName(), ex);
/*     */               } 
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketLoginInEncryptionBegin packetlogininencryptionbegin) {
/* 220 */     Validate.validState((this.g == EnumProtocolState.KEY), "Unexpected key packet", new Object[0]);
/* 221 */     PrivateKey privatekey = this.server.getKeyPair().getPrivate();
/*     */     
/* 223 */     if (!Arrays.equals(this.e, packetlogininencryptionbegin.b(privatekey))) {
/* 224 */       throw new IllegalStateException("Invalid nonce!");
/*     */     }
/* 226 */     this.loginKey = packetlogininencryptionbegin.a(privatekey);
/* 227 */     this.g = EnumProtocolState.AUTHENTICATING;
/* 228 */     this.networkManager.a(this.loginKey);
/*     */     
/* 230 */     authenticatorPool.execute(new Runnable() {
/*     */           public void run() {
/* 232 */             GameProfile gameprofile = LoginListener.this.i;
/*     */             
/*     */             try {
/* 235 */               String s = (new BigInteger(MinecraftEncryption.a("", LoginListener.this.server.getKeyPair().getPublic(), LoginListener.this.loginKey))).toString(16);
/*     */               
/* 237 */               LoginListener.this.i = LoginListener.this.server.getMinecraftSessionService().hasJoinedServer(new GameProfile((UUID)null, gameprofile.getName()), s, a());
/* 238 */               if (LoginListener.this.i != null) {
/*     */                 
/* 240 */                 if (!LoginListener.this.networkManager.isConnected()) {
/*     */                   return;
/*     */                 }
/*     */                 
/* 244 */                 (new LoginListener.LoginHandler()).fireEvents();
/* 245 */               } else if (LoginListener.this.server.isEmbeddedServer()) {
/* 246 */                 LoginListener.LOGGER.warn("Failed to verify username but will let them in anyway!");
/* 247 */                 LoginListener.this.i = LoginListener.this.a(gameprofile);
/* 248 */                 LoginListener.this.g = LoginListener.EnumProtocolState.READY_TO_ACCEPT;
/*     */               } else {
/* 250 */                 LoginListener.this.disconnect(new ChatMessage("multiplayer.disconnect.unverified_username"));
/* 251 */                 LoginListener.LOGGER.error("Username '{}' tried to join with an invalid session", gameprofile.getName());
/*     */               } 
/* 253 */             } catch (AuthenticationUnavailableException authenticationunavailableexception) {
/* 254 */               if (LoginListener.this.server.isEmbeddedServer()) {
/* 255 */                 LoginListener.LOGGER.warn("Authentication servers are down but will let them in anyway!");
/* 256 */                 LoginListener.this.i = LoginListener.this.a(gameprofile);
/* 257 */                 LoginListener.this.g = LoginListener.EnumProtocolState.READY_TO_ACCEPT;
/*     */               } else {
/*     */                 
/* 260 */                 if (PaperConfig.authenticationServersDownKickMessage != null) {
/* 261 */                   LoginListener.this.disconnect(new ChatComponentText(PaperConfig.authenticationServersDownKickMessage));
/*     */                 } else {
/* 263 */                   LoginListener.this.disconnect(new ChatMessage("multiplayer.disconnect.authservers_down"));
/* 264 */                 }  LoginListener.LOGGER.error("Couldn't verify username because servers are unavailable");
/*     */               }
/*     */             
/* 267 */             } catch (Exception exception) {
/* 268 */               LoginListener.this.disconnect("Failed to verify username!");
/* 269 */               LoginListener.this.server.server.getLogger().log(Level.WARNING, "Exception verifying " + gameprofile.getName(), exception);
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           @Nullable
/*     */           private InetAddress a() {
/* 277 */             SocketAddress socketaddress = LoginListener.this.networkManager.getSocketAddress();
/*     */             
/* 279 */             return (LoginListener.this.server.V() && socketaddress instanceof InetSocketAddress) ? ((InetSocketAddress)socketaddress).getAddress() : null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class LoginHandler
/*     */   {
/*     */     public void fireEvents() throws Exception {
/* 291 */       if (LoginListener.this.velocityLoginMessageId == -1 && PaperConfig.velocitySupport) {
/* 292 */         LoginListener.this.disconnect("This server requires you to connect with Velocity.");
/*     */         
/*     */         return;
/*     */       } 
/* 296 */       String playerName = LoginListener.this.i.getName();
/* 297 */       InetAddress address = ((InetSocketAddress)LoginListener.this.networkManager.getSocketAddress()).getAddress();
/* 298 */       UUID uniqueId = LoginListener.this.i.getId();
/* 299 */       final CraftServer server = LoginListener.this.server.server;
/*     */ 
/*     */       
/* 302 */       PlayerProfile profile = CraftPlayerProfile.asBukkitMirror(LoginListener.this.getGameProfile());
/* 303 */       AsyncPlayerPreLoginEvent asyncEvent = new AsyncPlayerPreLoginEvent(playerName, address, uniqueId, profile);
/* 304 */       server.getPluginManager().callEvent((Event)asyncEvent);
/* 305 */       profile = asyncEvent.getPlayerProfile();
/* 306 */       profile.complete(true);
/* 307 */       LoginListener.this.setGameProfile(CraftPlayerProfile.asAuthlib(profile));
/* 308 */       playerName = LoginListener.this.i.getName();
/* 309 */       uniqueId = LoginListener.this.i.getId();
/*     */ 
/*     */       
/* 312 */       if ((PlayerPreLoginEvent.getHandlerList().getRegisteredListeners()).length != 0) {
/* 313 */         final PlayerPreLoginEvent event = new PlayerPreLoginEvent(playerName, address, uniqueId);
/* 314 */         if (asyncEvent.getResult() != PlayerPreLoginEvent.Result.ALLOWED) {
/* 315 */           event.disallow(asyncEvent.getResult(), asyncEvent.getKickMessage());
/*     */         }
/* 317 */         Waitable<PlayerPreLoginEvent.Result> waitable = new Waitable<PlayerPreLoginEvent.Result>()
/*     */           {
/*     */             protected PlayerPreLoginEvent.Result evaluate() {
/* 320 */               server.getPluginManager().callEvent((Event)event);
/* 321 */               return event.getResult();
/*     */             }
/*     */           };
/* 324 */         LoginListener.this.server.processQueue.add(waitable);
/* 325 */         if (waitable.get() != PlayerPreLoginEvent.Result.ALLOWED) {
/* 326 */           LoginListener.this.disconnect(event.getKickMessage());
/*     */           
/*     */           return;
/*     */         } 
/* 330 */       } else if (asyncEvent.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) {
/* 331 */         LoginListener.this.disconnect(asyncEvent.getKickMessage());
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 336 */       LoginListener.LOGGER.info("UUID of player {} is {}", LoginListener.this.i.getName(), LoginListener.this.i.getId());
/* 337 */       LoginListener.this.g = LoginListener.EnumProtocolState.READY_TO_ACCEPT;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketLoginInCustomPayload packetloginincustompayload) {
/* 344 */     if (PaperConfig.velocitySupport && packetloginincustompayload.getId() == this.velocityLoginMessageId) {
/* 345 */       PacketDataSerializer buf = packetloginincustompayload.getBuf();
/* 346 */       if (buf == null) {
/* 347 */         disconnect("This server requires you to connect with Velocity.");
/*     */         
/*     */         return;
/*     */       } 
/* 351 */       if (!VelocityProxy.checkIntegrity(buf)) {
/* 352 */         disconnect("Unable to verify player details");
/*     */         
/*     */         return;
/*     */       } 
/* 356 */       this.networkManager.socketAddress = new InetSocketAddress(VelocityProxy.readAddress(buf), ((InetSocketAddress)this.networkManager.getSocketAddress()).getPort());
/*     */       
/* 358 */       setGameProfile(VelocityProxy.createProfile(buf));
/*     */ 
/*     */       
/* 361 */       authenticatorPool.execute(() -> {
/*     */             try {
/*     */               (new LoginHandler()).fireEvents();
/* 364 */             } catch (Exception ex) {
/*     */               disconnect("Failed to verify username!");
/*     */               
/*     */               this.server.server.getLogger().log(Level.WARNING, "Exception verifying " + this.i.getName(), ex);
/*     */             } 
/*     */           });
/*     */       return;
/*     */     } 
/* 372 */     disconnect(new ChatMessage("multiplayer.disconnect.unexpected_query_response"));
/*     */   }
/*     */   
/*     */   protected GameProfile a(GameProfile gameprofile) {
/* 376 */     UUID uuid = EntityHuman.getOfflineUUID(gameprofile.getName());
/*     */     
/* 378 */     return new GameProfile(uuid, gameprofile.getName());
/*     */   }
/*     */   
/*     */   enum EnumProtocolState
/*     */   {
/* 383 */     HELLO, KEY, AUTHENTICATING, NEGOTIATING, READY_TO_ACCEPT, DELAY_ACCEPT, ACCEPTED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LoginListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */