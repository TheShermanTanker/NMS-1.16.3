/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.nio.file.Path;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import joptsimple.OptionSet;
/*     */ 
/*     */ public class DedicatedServerProperties extends PropertyManager<DedicatedServerProperties> {
/*   9 */   public final boolean debug = getBoolean("debug", false);
/*  10 */   public final boolean onlineMode = getBoolean("online-mode", true);
/*  11 */   public final boolean preventProxyConnections = getBoolean("prevent-proxy-connections", false);
/*  12 */   public final String serverIp = getString("server-ip", "");
/*  13 */   public final boolean spawnAnimals = getBoolean("spawn-animals", true);
/*  14 */   public final boolean spawnNpcs = getBoolean("spawn-npcs", true);
/*  15 */   public final boolean pvp = getBoolean("pvp", true);
/*  16 */   public final boolean allowFlight = getBoolean("allow-flight", false);
/*  17 */   public final String resourcePack = getString("resource-pack", "");
/*  18 */   public final String motd = getString("motd", "A Minecraft Server");
/*  19 */   public final boolean forceGamemode = getBoolean("force-gamemode", false);
/*  20 */   public final boolean enforceWhitelist = getBoolean("enforce-whitelist", false);
/*     */   
/*     */   public final EnumDifficulty difficulty;
/*     */   
/*     */   public final EnumGamemode gamemode;
/*     */   public final String levelName;
/*     */   public final int serverPort;
/*     */   public final int maxBuildHeight;
/*     */   public final Boolean announcePlayerAchievements;
/*     */   public final boolean enableQuery;
/*     */   public final int queryPort;
/*     */   public final boolean enableRcon;
/*     */   public final int rconPort;
/*     */   public final String rconPassword;
/*     */   public final String resourcePackHash;
/*     */   public final String resourcePackSha1;
/*     */   public final boolean hardcore;
/*     */   public final boolean allowNether;
/*     */   public final boolean spawnMonsters;
/*     */   public final boolean snooperEnabled;
/*     */   public final boolean useNativeTransport;
/*     */   public final boolean enableCommandBlock;
/*     */   public final int spawnProtection;
/*     */   public final int opPermissionLevel;
/*     */   public final int functionPermissionLevel;
/*     */   public final long maxTickTime;
/*     */   public final int rateLimit;
/*     */   public final int viewDistance;
/*     */   public final int maxPlayers;
/*     */   public final int networkCompressionThreshold;
/*     */   public final boolean broadcastRconToOps;
/*     */   public final boolean broadcastConsoleToOps;
/*     */   public final int maxWorldSize;
/*     */   public final boolean syncChunkWrites;
/*     */   public final boolean enableJmxMonitoring;
/*     */   public final boolean enableStatus;
/*     */   public final int entityBroadcastRangePercentage;
/*     */   public final PropertyManager<DedicatedServerProperties>.EditableProperty<Integer> playerIdleTimeout;
/*     */   public final PropertyManager<DedicatedServerProperties>.EditableProperty<Boolean> whiteList;
/*     */   public final GeneratorSettings generatorSettings;
/*     */   public final String rconIp;
/*     */   
/*     */   public DedicatedServerProperties(Properties properties, IRegistryCustom iregistrycustom, OptionSet optionset) {
/*  63 */     super(properties, optionset);
/*     */     
/*  65 */     this.difficulty = (EnumDifficulty)a("difficulty", a(EnumDifficulty::getById, EnumDifficulty::a), EnumDifficulty::c, EnumDifficulty.EASY);
/*  66 */     this.gamemode = (EnumGamemode)a("gamemode", a(EnumGamemode::getById, EnumGamemode::a), EnumGamemode::b, EnumGamemode.SURVIVAL);
/*  67 */     this.levelName = getString("level-name", "world");
/*  68 */     this.serverPort = getInt("server-port", 25565);
/*  69 */     this.maxBuildHeight = a("max-build-height", integer -> Integer.valueOf(MathHelper.clamp((integer.intValue() + 8) / 16 * 16, 64, 256)), 256);
/*     */ 
/*     */     
/*  72 */     this.announcePlayerAchievements = b("announce-player-achievements");
/*  73 */     this.enableQuery = getBoolean("enable-query", false);
/*  74 */     this.queryPort = getInt("query.port", 25565);
/*  75 */     this.enableRcon = getBoolean("enable-rcon", false);
/*  76 */     this.rconPort = getInt("rcon.port", 25575);
/*  77 */     this.rconPassword = getString("rcon.password", "");
/*  78 */     this.resourcePackHash = a("resource-pack-hash");
/*  79 */     this.resourcePackSha1 = getString("resource-pack-sha1", "");
/*  80 */     this.hardcore = getBoolean("hardcore", false);
/*  81 */     this.allowNether = getBoolean("allow-nether", true);
/*  82 */     this.spawnMonsters = getBoolean("spawn-monsters", true);
/*  83 */     if (getBoolean("snooper-enabled", true));
/*     */ 
/*     */ 
/*     */     
/*  87 */     this.snooperEnabled = false;
/*  88 */     this.useNativeTransport = getBoolean("use-native-transport", true);
/*  89 */     this.enableCommandBlock = getBoolean("enable-command-block", false);
/*  90 */     this.spawnProtection = getInt("spawn-protection", 16);
/*  91 */     this.opPermissionLevel = getInt("op-permission-level", 4);
/*  92 */     this.functionPermissionLevel = getInt("function-permission-level", 2);
/*  93 */     this.maxTickTime = getLong("max-tick-time", TimeUnit.MINUTES.toMillis(1L));
/*  94 */     this.rateLimit = getInt("rate-limit", 0);
/*  95 */     this.viewDistance = getInt("view-distance", 10);
/*  96 */     this.maxPlayers = getInt("max-players", 20);
/*  97 */     this.networkCompressionThreshold = getInt("network-compression-threshold", 256);
/*  98 */     this.broadcastRconToOps = getBoolean("broadcast-rcon-to-ops", true);
/*  99 */     this.broadcastConsoleToOps = getBoolean("broadcast-console-to-ops", true);
/* 100 */     this.maxWorldSize = a("max-world-size", integer -> Integer.valueOf(MathHelper.clamp(integer.intValue(), 1, 29999984)), 29999984);
/*     */ 
/*     */     
/* 103 */     this.syncChunkWrites = (getBoolean("sync-chunk-writes", true) && Boolean.getBoolean("Paper.enable-sync-chunk-writes"));
/* 104 */     this.enableJmxMonitoring = getBoolean("enable-jmx-monitoring", false);
/* 105 */     this.enableStatus = getBoolean("enable-status", true);
/* 106 */     this.entityBroadcastRangePercentage = a("entity-broadcast-range-percentage", integer -> Integer.valueOf(MathHelper.clamp(integer.intValue(), 10, 1000)), 100);
/*     */ 
/*     */     
/* 109 */     this.playerIdleTimeout = b("player-idle-timeout", 0);
/* 110 */     this.whiteList = b("white-list", false);
/*     */     
/* 112 */     String rconIp = getSettingIfExists("rcon.ip");
/* 113 */     this.rconIp = (rconIp == null) ? this.serverIp : rconIp;
/*     */     
/* 115 */     this.generatorSettings = GeneratorSettings.a(iregistrycustom, properties);
/*     */   }
/*     */ 
/*     */   
/*     */   public static DedicatedServerProperties load(IRegistryCustom iregistrycustom, Path java_nio_file_path, OptionSet optionset) {
/* 120 */     return new DedicatedServerProperties(loadPropertiesFile(java_nio_file_path), iregistrycustom, optionset);
/*     */   }
/*     */ 
/*     */   
/*     */   protected DedicatedServerProperties reload(IRegistryCustom iregistrycustom, Properties properties, OptionSet optionset) {
/* 125 */     return new DedicatedServerProperties(properties, iregistrycustom, optionset);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DedicatedServerProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */