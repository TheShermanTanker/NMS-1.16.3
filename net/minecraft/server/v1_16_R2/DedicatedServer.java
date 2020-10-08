/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import co.aikar.timings.MinecraftTimings;
/*     */ import co.aikar.timings.Timings;
/*     */ import co.aikar.timings.TimingsReportListener;
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.destroystokyo.paper.console.PaperConsole;
/*     */ import com.google.common.base.Strings;
/*     */ import com.mojang.authlib.GameProfileRepository;
/*     */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import java.util.logging.Handler;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.regex.Pattern;
/*     */ import joptsimple.OptionSet;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.io.IoBuilder;
/*     */ import org.bukkit.command.BufferedCommandSender;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.Main;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.Waitable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.server.RemoteServerCommandEvent;
/*     */ import org.bukkit.event.server.ServerCommandEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginLoadOrder;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class DedicatedServer extends MinecraftServer implements IMinecraftServer {
/*  37 */   private static final Logger LOGGER = LogManager.getLogger();
/*  38 */   private static final Pattern k = Pattern.compile("^[a-fA-F0-9]{40}$");
/*  39 */   private final Queue<ServerCommand> serverCommandQueue = new ConcurrentLinkedQueue<>();
/*     */   
/*     */   private RemoteStatusListener remoteStatusListener;
/*     */   public final RemoteControlCommandListener remoteControlCommandListener;
/*     */   private RemoteControlListener remoteControlListener;
/*     */   public DedicatedServerSettings propertyManager;
/*     */   @Nullable
/*     */   private ServerGUI q;
/*     */   
/*     */   public DedicatedServer(OptionSet options, DataPackConfiguration datapackconfiguration, Thread thread, IRegistryCustom.Dimension iregistrycustom_dimension, Convertable.ConversionSession convertable_conversionsession, ResourcePackRepository resourcepackrepository, DataPackResources datapackresources, SaveData savedata, DedicatedServerSettings dedicatedserversettings, DataFixer datafixer, MinecraftSessionService minecraftsessionservice, GameProfileRepository gameprofilerepository, UserCache usercache, WorldLoadListenerFactory worldloadlistenerfactory) {
/*  49 */     super(options, datapackconfiguration, thread, iregistrycustom_dimension, convertable_conversionsession, savedata, resourcepackrepository, Proxy.NO_PROXY, datafixer, datapackresources, minecraftsessionservice, gameprofilerepository, usercache, worldloadlistenerfactory);
/*     */     
/*  51 */     this.propertyManager = dedicatedserversettings;
/*  52 */     this.remoteControlCommandListener = new RemoteControlCommandListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean init() throws IOException {
/*  57 */     Thread thread = new Thread("Server console handler")
/*     */       {
/*     */         public void run() {
/*  60 */           if (!Main.useConsole) {
/*     */             return;
/*     */           }
/*     */           
/*  64 */           (new PaperConsole(DedicatedServer.this)).start();
/*     */         }
/*     */       };
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
/* 111 */     Logger global = Logger.getLogger("");
/* 112 */     global.setUseParentHandlers(false);
/* 113 */     for (Handler handler : global.getHandlers()) {
/* 114 */       global.removeHandler(handler);
/*     */     }
/* 116 */     global.addHandler((Handler)new ForwardLogHandler());
/*     */ 
/*     */     
/* 119 */     Logger logger = LogManager.getRootLogger();
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
/*     */     
/* 132 */     System.setOut(IoBuilder.forLogger(logger).setLevel(Level.INFO).buildPrintStream());
/* 133 */     System.setErr(IoBuilder.forLogger(logger).setLevel(Level.WARN).buildPrintStream());
/*     */ 
/*     */     
/* 136 */     thread.setDaemon(true);
/* 137 */     thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
/* 138 */     thread.start();
/* 139 */     LOGGER.info("Starting minecraft server version " + SharedConstants.getGameVersion().getName());
/* 140 */     if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
/* 141 */       LOGGER.warn("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
/*     */     }
/*     */     
/* 144 */     LOGGER.info("Loading properties");
/* 145 */     DedicatedServerProperties dedicatedserverproperties = this.propertyManager.getProperties();
/*     */     
/* 147 */     if (isEmbeddedServer()) {
/* 148 */       a_("127.0.0.1");
/*     */     } else {
/* 150 */       setOnlineMode(dedicatedserverproperties.onlineMode);
/* 151 */       e(dedicatedserverproperties.preventProxyConnections);
/* 152 */       a_(dedicatedserverproperties.serverIp);
/*     */     } 
/*     */     
/* 155 */     a(new DedicatedPlayerList(this, this.customRegistry, this.worldNBTStorage));
/* 156 */     SpigotConfig.init((File)this.options.valueOf("spigot-settings"));
/* 157 */     SpigotConfig.registerCommands();
/*     */ 
/*     */     
/*     */     try {
/* 161 */       PaperConfig.init((File)this.options.valueOf("paper-settings"));
/* 162 */     } catch (Exception e) {
/* 163 */       LOGGER.error("Unable to load server configuration", e);
/* 164 */       return false;
/*     */     } 
/* 166 */     PaperConfig.registerCommands();
/* 167 */     VersionHistoryManager.INSTANCE.getClass();
/*     */     
/* 169 */     TuinityConfig.init((File)this.options.valueOf("tuinity-settings"));
/*     */     
/* 171 */     setPVP(dedicatedserverproperties.pvp);
/* 172 */     setAllowFlight(dedicatedserverproperties.allowFlight);
/* 173 */     setResourcePack(dedicatedserverproperties.resourcePack, aZ());
/* 174 */     setMotd(dedicatedserverproperties.motd);
/* 175 */     setForceGamemode(dedicatedserverproperties.forceGamemode);
/* 176 */     super.setIdleTimeout(((Integer)dedicatedserverproperties.playerIdleTimeout.get()).intValue());
/* 177 */     i(dedicatedserverproperties.enforceWhitelist);
/*     */     
/* 179 */     LOGGER.info("Default game type: {}", dedicatedserverproperties.gamemode);
/* 180 */     InetAddress inetaddress = null;
/*     */     
/* 182 */     if (!getServerIp().isEmpty()) {
/* 183 */       inetaddress = InetAddress.getByName(getServerIp());
/*     */     }
/*     */     
/* 186 */     if (getPort() < 0) {
/* 187 */       setPort(dedicatedserverproperties.serverPort);
/*     */     }
/*     */     
/* 190 */     LOGGER.info("Generating keypair");
/* 191 */     a(MinecraftEncryption.b());
/* 192 */     LOGGER.info("Starting Minecraft server on {}:{}", getServerIp().isEmpty() ? "*" : getServerIp(), Integer.valueOf(getPort()));
/*     */     
/*     */     try {
/* 195 */       getServerConnection().a(inetaddress, getPort());
/* 196 */     } catch (IOException ioexception) {
/* 197 */       LOGGER.warn("**** FAILED TO BIND TO PORT!");
/* 198 */       LOGGER.warn("The exception was: {}", ioexception.toString());
/* 199 */       LOGGER.warn("Perhaps a server is already running on that port?");
/* 200 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 205 */     this.server.loadPlugins();
/* 206 */     this.server.enablePlugins(PluginLoadOrder.STARTUP);
/*     */ 
/*     */     
/* 209 */     if (!getOnlineMode()) {
/* 210 */       LOGGER.warn("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
/* 211 */       LOGGER.warn("The server will make no attempt to authenticate usernames. Beware.");
/*     */       
/* 213 */       if (SpigotConfig.bungee) {
/* 214 */         LOGGER.warn("Whilst this makes it possible to use BungeeCord, unless access to your server is properly restricted, it also opens up the ability for hackers to connect with any username they choose.");
/* 215 */         LOGGER.warn("Please see http://www.spigotmc.org/wiki/firewall-guide/ for further information.");
/*     */       } else {
/* 217 */         LOGGER.warn("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
/*     */       } 
/*     */       
/* 220 */       LOGGER.warn("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
/*     */     } 
/*     */     
/* 223 */     if (convertNames()) {
/* 224 */       getUserCache().b(false);
/*     */     }
/*     */     
/* 227 */     if (!NameReferencingFileConverter.e(this)) {
/* 228 */       return false;
/*     */     }
/*     */     
/* 231 */     long i = SystemUtils.getMonotonicNanos();
/*     */     
/* 233 */     c(dedicatedserverproperties.maxBuildHeight);
/* 234 */     TileEntitySkull.a(getUserCache());
/* 235 */     TileEntitySkull.a(getMinecraftSessionService());
/* 236 */     UserCache.a(getOnlineMode());
/* 237 */     LOGGER.info("Preparing level \"{}\"", getWorld());
/* 238 */     loadWorld(this.convertable.getLevelName());
/* 239 */     long j = SystemUtils.getMonotonicNanos() - i;
/* 240 */     String s = String.format(Locale.ROOT, "%.3fs", new Object[] { Double.valueOf(j / 1.0E9D) });
/*     */ 
/*     */     
/* 243 */     if (dedicatedserverproperties.announcePlayerAchievements != null) {
/* 244 */       ((GameRules.GameRuleBoolean)getGameRules().<GameRules.GameRuleBoolean>get(GameRules.ANNOUNCE_ADVANCEMENTS)).a(dedicatedserverproperties.announcePlayerAchievements.booleanValue(), this);
/*     */     }
/*     */     
/* 247 */     if (dedicatedserverproperties.enableQuery) {
/* 248 */       LOGGER.info("Starting GS4 status listener");
/* 249 */       this.remoteStatusListener = RemoteStatusListener.a(this);
/*     */     } 
/*     */     
/* 252 */     if (dedicatedserverproperties.enableRcon) {
/* 253 */       LOGGER.info("Starting remote control listener");
/* 254 */       this.remoteControlListener = RemoteControlListener.a(this);
/* 255 */       this.remoteConsole = (RemoteConsoleCommandSender)new CraftRemoteConsoleCommandSender(this.remoteControlCommandListener);
/*     */     } 
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
/* 267 */     Items.AIR.a(CreativeModeTab.g, NonNullList.a());
/* 268 */     if (dedicatedserverproperties.enableJmxMonitoring) {
/* 269 */       MinecraftServerBeans.a(this);
/*     */     }
/*     */     
/* 272 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSpawnAnimals() {
/* 278 */     return ((getDedicatedServerProperties()).spawnAnimals && super.getSpawnAnimals());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getSpawnMonsters() {
/* 283 */     return ((this.propertyManager.getProperties()).spawnMonsters && super.getSpawnMonsters());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getSpawnNPCs() {
/* 288 */     return ((this.propertyManager.getProperties()).spawnNpcs && super.getSpawnNPCs());
/*     */   }
/*     */   public String aZ() {
/*     */     String s;
/* 292 */     DedicatedServerProperties dedicatedserverproperties = this.propertyManager.getProperties();
/*     */ 
/*     */     
/* 295 */     if (!dedicatedserverproperties.resourcePackSha1.isEmpty()) {
/* 296 */       s = dedicatedserverproperties.resourcePackSha1;
/* 297 */       if (!Strings.isNullOrEmpty(dedicatedserverproperties.resourcePackHash)) {
/* 298 */         LOGGER.warn("resource-pack-hash is deprecated and found along side resource-pack-sha1. resource-pack-hash will be ignored.");
/*     */       }
/* 300 */     } else if (!Strings.isNullOrEmpty(dedicatedserverproperties.resourcePackHash)) {
/* 301 */       LOGGER.warn("resource-pack-hash is deprecated. Please use resource-pack-sha1 instead.");
/* 302 */       s = dedicatedserverproperties.resourcePackHash;
/*     */     } else {
/* 304 */       s = "";
/*     */     } 
/*     */     
/* 307 */     if (!s.isEmpty() && !k.matcher(s).matches()) {
/* 308 */       LOGGER.warn("Invalid sha1 for ressource-pack-sha1");
/*     */     }
/*     */     
/* 311 */     if (!dedicatedserverproperties.resourcePack.isEmpty() && s.isEmpty()) {
/* 312 */       LOGGER.warn("You specified a resource pack without providing a sha1 hash. Pack will be updated on the client only if you change the name of the pack.");
/*     */     }
/*     */     
/* 315 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public DedicatedServerProperties getDedicatedServerProperties() {
/* 320 */     return this.propertyManager.getProperties();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateWorldSettings() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHardcore() {
/* 330 */     return (getDedicatedServerProperties()).hardcore;
/*     */   }
/*     */ 
/*     */   
/*     */   public CrashReport b(CrashReport crashreport) {
/* 335 */     crashreport = super.b(crashreport);
/* 336 */     crashreport.g().a("Is Modded", () -> (String)getModded().orElse("Unknown (can't tell)"));
/*     */ 
/*     */     
/* 339 */     crashreport.g().a("Type", () -> "Dedicated Server (map_server.txt)");
/*     */ 
/*     */     
/* 342 */     return crashreport;
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<String> getModded() {
/* 347 */     String s = getServerModName();
/*     */     
/* 349 */     return !"vanilla".equals(s) ? Optional.<String>of("Definitely; Server brand changed to '" + s + "'") : Optional.<String>empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void exit() {
/* 354 */     if (this.q != null) {
/* 355 */       this.q.b();
/*     */     }
/*     */     
/* 358 */     if (this.remoteControlListener != null);
/*     */ 
/*     */ 
/*     */     
/* 362 */     if (this.remoteStatusListener != null);
/*     */ 
/*     */ 
/*     */     
/* 366 */     this.hasFullyShutdown = true;
/* 367 */     System.exit(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(BooleanSupplier booleansupplier) {
/* 372 */     super.b(booleansupplier);
/* 373 */     handleCommandQueue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAllowNether() {
/* 378 */     return (getDedicatedServerProperties()).allowNether;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
/* 383 */     mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(getPlayerList().getHasWhitelist()));
/* 384 */     mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf((getPlayerList().getWhitelisted()).length));
/* 385 */     super.a(mojangstatisticsgenerator);
/*     */   }
/*     */   
/*     */   public void issueCommand(String s, CommandListenerWrapper commandlistenerwrapper) {
/* 389 */     this.serverCommandQueue.add(new ServerCommand(s, commandlistenerwrapper));
/*     */   }
/*     */   
/*     */   public void handleCommandQueue() {
/* 393 */     MinecraftTimings.serverCommandTimer.startTiming();
/*     */     
/*     */     ServerCommand servercommand;
/* 396 */     while ((servercommand = this.serverCommandQueue.poll()) != null) {
/*     */ 
/*     */ 
/*     */       
/* 400 */       ServerCommandEvent event = new ServerCommandEvent((CommandSender)this.console, servercommand.command);
/* 401 */       this.server.getPluginManager().callEvent((Event)event);
/* 402 */       if (event.isCancelled())
/* 403 */         continue;  servercommand = new ServerCommand(event.getCommand(), servercommand.source);
/*     */ 
/*     */       
/* 406 */       this.server.dispatchServerCommand((CommandSender)this.console, servercommand);
/*     */     } 
/*     */ 
/*     */     
/* 410 */     MinecraftTimings.serverCommandTimer.stopTiming();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean j() {
/* 415 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int k() {
/* 420 */     return (getDedicatedServerProperties()).rateLimit;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean l() {
/* 425 */     return (getDedicatedServerProperties()).useNativeTransport;
/*     */   }
/*     */ 
/*     */   
/*     */   public DedicatedPlayerList getPlayerList() {
/* 430 */     return (DedicatedPlayerList)super.getPlayerList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean n() {
/* 435 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String h_() {
/* 440 */     return getServerIp();
/*     */   }
/*     */ 
/*     */   
/*     */   public int p() {
/* 445 */     return getPort();
/*     */   }
/*     */ 
/*     */   
/*     */   public String i_() {
/* 450 */     return getMotd();
/*     */   }
/*     */   
/*     */   public void bc() {
/* 454 */     if (this.q == null) {
/* 455 */       this.q = ServerGUI.a(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ag() {
/* 462 */     return (this.q != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EnumGamemode enumgamemode, boolean flag, int i) {
/* 467 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getEnableCommandBlock() {
/* 472 */     return (getDedicatedServerProperties()).enableCommandBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpawnProtection() {
/* 477 */     return (getDedicatedServerProperties()).spawnProtection;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(WorldServer worldserver, BlockPosition blockposition, EntityHuman entityhuman) {
/* 482 */     if (worldserver.getDimensionKey() != World.OVERWORLD)
/* 483 */       return false; 
/* 484 */     if (getPlayerList().getOPs().isEmpty())
/* 485 */       return false; 
/* 486 */     if (getPlayerList().isOp(entityhuman.getProfile()))
/* 487 */       return false; 
/* 488 */     if (getSpawnProtection() <= 0) {
/* 489 */       return false;
/*     */     }
/* 491 */     BlockPosition blockposition1 = worldserver.getSpawn();
/* 492 */     int i = MathHelper.a(blockposition.getX() - blockposition1.getX());
/* 493 */     int j = MathHelper.a(blockposition.getZ() - blockposition1.getZ());
/* 494 */     int k = Math.max(i, j);
/*     */     
/* 496 */     return (k <= getSpawnProtection());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean al() {
/* 502 */     return (getDedicatedServerProperties()).enableStatus;
/*     */   }
/*     */ 
/*     */   
/*     */   public int g() {
/* 507 */     return (getDedicatedServerProperties()).opPermissionLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public int h() {
/* 512 */     return (getDedicatedServerProperties()).functionPermissionLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdleTimeout(int i) {
/* 517 */     super.setIdleTimeout(i);
/* 518 */     this.propertyManager.setProperty(dedicatedserverproperties -> (DedicatedServerProperties)dedicatedserverproperties.playerIdleTimeout.set(getCustomRegistry(), Integer.valueOf(i)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean i() {
/* 525 */     return (getDedicatedServerProperties()).broadcastRconToOps;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldBroadcastCommands() {
/* 530 */     return (getDedicatedServerProperties()).broadcastConsoleToOps;
/*     */   }
/*     */ 
/*     */   
/*     */   public int at() {
/* 535 */     return (getDedicatedServerProperties()).maxWorldSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public int aw() {
/* 540 */     return (getDedicatedServerProperties()).networkCompressionThreshold;
/*     */   }
/*     */   
/*     */   protected boolean convertNames() {
/* 544 */     boolean flag = false;
/*     */     
/*     */     int i;
/*     */     
/* 548 */     for (i = 0; !flag && i <= 2; i++) {
/* 549 */       if (i > 0) {
/* 550 */         LOGGER.warn("Encountered a problem while converting the user banlist, retrying in a few seconds");
/* 551 */         bn();
/*     */       } 
/*     */       
/* 554 */       flag = NameReferencingFileConverter.a(this);
/*     */     } 
/*     */     
/* 557 */     boolean flag1 = false;
/*     */     
/* 559 */     for (i = 0; !flag1 && i <= 2; i++) {
/* 560 */       if (i > 0) {
/* 561 */         LOGGER.warn("Encountered a problem while converting the ip banlist, retrying in a few seconds");
/* 562 */         bn();
/*     */       } 
/*     */       
/* 565 */       flag1 = NameReferencingFileConverter.b(this);
/*     */     } 
/*     */     
/* 568 */     boolean flag2 = false;
/*     */     
/* 570 */     for (i = 0; !flag2 && i <= 2; i++) {
/* 571 */       if (i > 0) {
/* 572 */         LOGGER.warn("Encountered a problem while converting the op list, retrying in a few seconds");
/* 573 */         bn();
/*     */       } 
/*     */       
/* 576 */       flag2 = NameReferencingFileConverter.c(this);
/*     */     } 
/*     */     
/* 579 */     boolean flag3 = false;
/*     */     
/* 581 */     for (i = 0; !flag3 && i <= 2; i++) {
/* 582 */       if (i > 0) {
/* 583 */         LOGGER.warn("Encountered a problem while converting the whitelist, retrying in a few seconds");
/* 584 */         bn();
/*     */       } 
/*     */       
/* 587 */       flag3 = NameReferencingFileConverter.d(this);
/*     */     } 
/*     */     
/* 590 */     boolean flag4 = false;
/*     */     
/* 592 */     for (i = 0; !flag4 && i <= 2; i++) {
/* 593 */       if (i > 0) {
/* 594 */         LOGGER.warn("Encountered a problem while converting the player save files, retrying in a few seconds");
/* 595 */         bn();
/*     */       } 
/*     */       
/* 598 */       flag4 = NameReferencingFileConverter.a(this);
/*     */     } 
/*     */     
/* 601 */     return (flag || flag1 || flag2 || flag3 || flag4);
/*     */   }
/*     */   
/*     */   private void bn() {
/*     */     try {
/* 606 */       Thread.sleep(5000L);
/* 607 */     } catch (InterruptedException interruptedException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaxTickTime() {
/* 613 */     return (getDedicatedServerProperties()).maxTickTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPlugins() {
/* 619 */     StringBuilder result = new StringBuilder();
/* 620 */     Plugin[] plugins = this.server.getPluginManager().getPlugins();
/*     */     
/* 622 */     result.append(this.server.getName());
/* 623 */     result.append(" on Bukkit ");
/* 624 */     result.append(this.server.getBukkitVersion());
/*     */     
/* 626 */     if (plugins.length > 0 && this.server.getQueryPlugins()) {
/* 627 */       result.append(": ");
/*     */       
/* 629 */       for (int i = 0; i < plugins.length; i++) {
/* 630 */         if (i > 0) {
/* 631 */           result.append("; ");
/*     */         }
/*     */         
/* 634 */         result.append(plugins[i].getDescription().getName());
/* 635 */         result.append(" ");
/* 636 */         result.append(plugins[i].getDescription().getVersion().replaceAll(";", ","));
/*     */       } 
/*     */     } 
/*     */     
/* 640 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String executeRemoteCommand(String s) {
/* 646 */     Waitable[] waitableArray = new Waitable[1];
/* 647 */     this.remoteControlCommandListener.clearMessages();
/* 648 */     executeSync(() -> {
/*     */           RemoteServerCommandEvent event = new RemoteServerCommandEvent((CommandSender)this.remoteConsole, s);
/*     */           
/*     */           this.server.getPluginManager().callEvent((Event)event);
/*     */           if (event.isCancelled()) {
/*     */             return;
/*     */           }
/*     */           if (s.toLowerCase().startsWith("timings") && s.toLowerCase().matches("timings (report|paste|get|merged|seperate)")) {
/*     */             final BufferedCommandSender sender = new BufferedCommandSender();
/*     */             Waitable<String> waitable = new Waitable<String>()
/*     */               {
/*     */                 protected String evaluate()
/*     */                 {
/* 661 */                   return sender.getBuffer();
/*     */                 }
/*     */               };
/*     */             
/*     */             waitableArray[0] = waitable;
/*     */             
/*     */             Timings.generateReport(new TimingsReportListener((CommandSender)sender, (Runnable)waitable));
/*     */           } else {
/*     */             ServerCommand serverCommand = new ServerCommand(event.getCommand(), this.remoteControlCommandListener.getWrapper());
/*     */             
/*     */             this.server.dispatchServerCommand((CommandSender)this.remoteConsole, serverCommand);
/*     */           } 
/*     */         });
/* 674 */     if (waitableArray[0] != null) {
/*     */       
/* 676 */       Waitable<String> waitable = waitableArray[0];
/*     */       try {
/* 678 */         return (String)waitable.get();
/* 679 */       } catch (ExecutionException e) {
/* 680 */         throw new RuntimeException("Exception processing rcon command " + s, e.getCause());
/* 681 */       } catch (InterruptedException e) {
/* 682 */         Thread.currentThread().interrupt();
/* 683 */         throw new RuntimeException("Interrupted processing rcon command " + s, e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 688 */     return this.remoteControlCommandListener.getMessages();
/*     */   }
/*     */   
/*     */   public void setHasWhitelist(boolean flag) {
/* 692 */     this.propertyManager.setProperty(dedicatedserverproperties -> (DedicatedServerProperties)dedicatedserverproperties.whiteList.set(getCustomRegistry(), Boolean.valueOf(flag)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 699 */     super.stop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(GameProfile gameprofile) {
/* 705 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(int i) {
/* 710 */     return (getDedicatedServerProperties()).entityBroadcastRangePercentage * i / 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWorld() {
/* 715 */     return this.convertable.getLevelName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSyncChunkWrites() {
/* 720 */     return (this.propertyManager.getProperties()).syncChunkWrites;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDebugging() {
/* 725 */     return (getDedicatedServerProperties()).debug;
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandSender getBukkitSender(CommandListenerWrapper wrapper) {
/* 730 */     return (CommandSender)this.console;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DedicatedServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */