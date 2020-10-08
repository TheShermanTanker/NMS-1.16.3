/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import co.aikar.timings.Timings;
/*     */ import co.aikar.timings.TimingsManager;
/*     */ import com.destroystokyo.paper.io.chunk.ChunkTaskManager;
/*     */ import com.google.common.base.Strings;
/*     */ import com.google.common.base.Throwables;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ import org.spigotmc.WatchdogThread;
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
/*     */ public class PaperConfig
/*     */ {
/*     */   private static File CONFIG_FILE;
/*     */   private static final String HEADER = "This is the main configuration file for Paper.\nAs you can see, there's tons to configure. Some options may impact gameplay, so use\nwith caution, and make sure you know what each option does before configuring.\n\nIf you need help with the configuration or have any questions related to Paper,\njoin us in our Discord or IRC channel.\n\nDiscord: https://paperdiscord.emc.gs\nIRC: #paper @ irc.spi.gt ( http://irc.spi.gt/iris/?channels=paper )\nWebsite: https://papermc.io/ \nDocs: https://paper.readthedocs.org/ \n";
/*     */   public static YamlConfiguration config;
/*     */   static int version;
/*     */   static Map<String, Command> commands;
/*     */   private static boolean verbose;
/*     */   private static boolean fatalError;
/*     */   private static boolean metricsStarted;
/*     */   
/*     */   public static void init(File configFile) {
/*  57 */     CONFIG_FILE = configFile;
/*  58 */     config = new YamlConfiguration();
/*     */     
/*  60 */     try { config.load(CONFIG_FILE); }
/*  61 */     catch (IOException iOException) {  }
/*  62 */     catch (InvalidConfigurationException ex)
/*  63 */     { Bukkit.getLogger().log(Level.SEVERE, "Could not load paper.yml, please correct your syntax errors", (Throwable)ex);
/*  64 */       throw Throwables.propagate(ex); }
/*     */     
/*  66 */     config.options().header("This is the main configuration file for Paper.\nAs you can see, there's tons to configure. Some options may impact gameplay, so use\nwith caution, and make sure you know what each option does before configuring.\n\nIf you need help with the configuration or have any questions related to Paper,\njoin us in our Discord or IRC channel.\n\nDiscord: https://paperdiscord.emc.gs\nIRC: #paper @ irc.spi.gt ( http://irc.spi.gt/iris/?channels=paper )\nWebsite: https://papermc.io/ \nDocs: https://paper.readthedocs.org/ \n");
/*  67 */     config.options().copyDefaults(true);
/*  68 */     verbose = getBoolean("verbose", false);
/*     */     
/*  70 */     commands = new HashMap<>();
/*  71 */     commands.put("paper", new PaperCommand("paper"));
/*  72 */     commands.put("mspt", new MSPTCommand("mspt"));
/*     */     
/*  74 */     version = getInt("config-version", 20);
/*  75 */     set("config-version", Integer.valueOf(20));
/*  76 */     readConfig(PaperConfig.class, null);
/*     */   }
/*     */   
/*     */   protected static void logError(String s) {
/*  80 */     Bukkit.getLogger().severe(s);
/*     */   }
/*     */   
/*     */   protected static void fatal(String s) {
/*  84 */     fatalError = true;
/*  85 */     throw new RuntimeException("Fatal paper.yml config error: " + s);
/*     */   }
/*     */   
/*     */   protected static void log(String s) {
/*  89 */     if (verbose) {
/*  90 */       Bukkit.getLogger().info(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void registerCommands() {
/*  95 */     for (Map.Entry<String, Command> entry : commands.entrySet()) {
/*  96 */       (MinecraftServer.getServer()).server.getCommandMap().register(entry.getKey(), "Paper", entry.getValue());
/*     */     }
/*     */     
/*  99 */     if (!metricsStarted) {
/* 100 */       Metrics.PaperMetrics.startMetrics();
/* 101 */       metricsStarted = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   static void readConfig(Class<?> clazz, Object instance) {
/* 106 */     for (Method method : clazz.getDeclaredMethods()) {
/* 107 */       if (Modifier.isPrivate(method.getModifiers()) && (
/* 108 */         method.getParameterTypes()).length == 0 && method.getReturnType() == void.class) {
/*     */         try {
/* 110 */           method.setAccessible(true);
/* 111 */           method.invoke(instance, new Object[0]);
/* 112 */         } catch (InvocationTargetException ex) {
/* 113 */           throw Throwables.propagate(ex.getCause());
/* 114 */         } catch (Exception ex) {
/* 115 */           Bukkit.getLogger().log(Level.SEVERE, "Error invoking " + method, ex);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 122 */       config.save(CONFIG_FILE);
/* 123 */     } catch (IOException ex) {
/* 124 */       Bukkit.getLogger().log(Level.SEVERE, "Could not save " + CONFIG_FILE, ex);
/*     */     } 
/*     */   }
/*     */   
/* 128 */   private static final Pattern SPACE = Pattern.compile(" ");
/* 129 */   private static final Pattern NOT_NUMERIC = Pattern.compile("[^-\\d.]"); public static int getSeconds(String str) {
/*     */     double num;
/* 131 */     str = SPACE.matcher(str).replaceAll("");
/* 132 */     char unit = str.charAt(str.length() - 1);
/* 133 */     str = NOT_NUMERIC.matcher(str).replaceAll("");
/*     */     
/*     */     try {
/* 136 */       num = Double.parseDouble(str);
/* 137 */     } catch (Exception e) {
/* 138 */       num = 0.0D;
/*     */     } 
/* 140 */     switch (unit) { case 'd':
/* 141 */         num *= 86400.0D; break;
/* 142 */       case 'h': num *= 3600.0D; break;
/* 143 */       case 'm': num *= 60.0D;
/*     */         break; }
/*     */     
/* 146 */     return (int)num;
/*     */   }
/*     */   public static String timingsServerName;
/*     */   protected static String timeSummary(int seconds) {
/* 150 */     String time = "";
/*     */     
/* 152 */     if (seconds > 86400) {
/* 153 */       time = time + TimeUnit.SECONDS.toDays(seconds) + "d";
/* 154 */       seconds %= 86400;
/*     */     } 
/*     */     
/* 157 */     if (seconds > 3600) {
/* 158 */       time = time + TimeUnit.SECONDS.toHours(seconds) + "h";
/* 159 */       seconds %= 3600;
/*     */     } 
/*     */     
/* 162 */     if (seconds > 0) {
/* 163 */       time = time + TimeUnit.SECONDS.toMinutes(seconds) + "m";
/*     */     }
/* 165 */     return time;
/*     */   }
/*     */   
/*     */   private static void set(String path, Object val) {
/* 169 */     config.set(path, val);
/*     */   }
/*     */   
/*     */   private static boolean getBoolean(String path, boolean def) {
/* 173 */     config.addDefault(path, Boolean.valueOf(def));
/* 174 */     return config.getBoolean(path, config.getBoolean(path));
/*     */   }
/*     */   
/*     */   private static double getDouble(String path, double def) {
/* 178 */     config.addDefault(path, Double.valueOf(def));
/* 179 */     return config.getDouble(path, config.getDouble(path));
/*     */   }
/*     */ 
/*     */   
/*     */   private static float getFloat(String path, float def) {
/* 184 */     return (float)getDouble(path, def);
/*     */   }
/*     */   
/*     */   private static int getInt(String path, int def) {
/* 188 */     config.addDefault(path, Integer.valueOf(def));
/* 189 */     return config.getInt(path, config.getInt(path));
/*     */   }
/*     */   
/*     */   private static <T> List getList(String path, T def) {
/* 193 */     config.addDefault(path, def);
/* 194 */     return config.getList(path, config.getList(path));
/*     */   }
/*     */   
/*     */   private static String getString(String path, String def) {
/* 198 */     config.addDefault(path, def);
/* 199 */     return config.getString(path, config.getString(path));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void timings() {
/* 204 */     boolean timings = getBoolean("timings.enabled", true);
/* 205 */     boolean verboseTimings = getBoolean("timings.verbose", true);
/* 206 */     TimingsManager.privacy = getBoolean("timings.server-name-privacy", false);
/* 207 */     TimingsManager.hiddenConfigs = getList("timings.hidden-config-entries", Lists.newArrayList((Object[])new String[] { "database", "settings.bungeecord-addresses" }));
/* 208 */     int timingHistoryInterval = getInt("timings.history-interval", 300);
/* 209 */     int timingHistoryLength = getInt("timings.history-length", 3600);
/* 210 */     timingsServerName = getString("timings.server-name", "Unknown Server");
/*     */ 
/*     */     
/* 213 */     Timings.setVerboseTimingsEnabled(verboseTimings);
/* 214 */     Timings.setTimingsEnabled(timings);
/* 215 */     Timings.setHistoryInterval(timingHistoryInterval * 20);
/* 216 */     Timings.setHistoryLength(timingHistoryLength * 20);
/*     */     
/* 218 */     log("Timings: " + timings + " - Verbose: " + verboseTimings + " - Interval: " + 
/*     */         
/* 220 */         timeSummary(Timings.getHistoryInterval() / 20) + " - Length: " + 
/* 221 */         timeSummary(Timings.getHistoryLength() / 20) + " - Server Name: " + timingsServerName);
/*     */   }
/*     */   
/*     */   public static boolean loadPermsBeforePlugins = true;
/*     */   
/*     */   private static void loadPermsBeforePlugins() {
/* 227 */     loadPermsBeforePlugins = getBoolean("settings.load-permissions-yml-before-plugins", true);
/*     */   }
/*     */   
/* 230 */   public static int regionFileCacheSize = 256;
/*     */   private static void regionFileCacheSize() {
/* 232 */     regionFileCacheSize = Math.max(getInt("settings.region-file-cache-size", 256), 4);
/*     */   }
/*     */   public static boolean enablePlayerCollisions = true;
/*     */   
/*     */   private static void enablePlayerCollisions() {
/* 237 */     enablePlayerCollisions = getBoolean("settings.enable-player-collisions", true);
/*     */   }
/*     */   public static boolean saveEmptyScoreboardTeams = false;
/*     */   
/*     */   private static void saveEmptyScoreboardTeams() {
/* 242 */     saveEmptyScoreboardTeams = getBoolean("settings.save-empty-scoreboard-teams", false);
/*     */   }
/*     */   public static boolean bungeeOnlineMode = true;
/*     */   
/*     */   private static void bungeeOnlineMode() {
/* 247 */     bungeeOnlineMode = getBoolean("settings.bungee-online-mode", true);
/*     */   }
/*     */   
/*     */   public static boolean isProxyOnlineMode() {
/* 251 */     return (Bukkit.getOnlineMode() || (SpigotConfig.bungee && bungeeOnlineMode) || (velocitySupport && velocityOnlineMode));
/*     */   }
/*     */   
/* 254 */   public static int packetInSpamThreshold = 300;
/*     */   private static void packetInSpamThreshold() {
/* 256 */     if (version < 11) {
/* 257 */       int oldValue = getInt("settings.play-in-use-item-spam-threshold", 300);
/* 258 */       set("settings.incoming-packet-spam-threshold", Integer.valueOf(oldValue));
/*     */     } 
/* 260 */     packetInSpamThreshold = getInt("settings.incoming-packet-spam-threshold", 300);
/*     */   }
/*     */   
/* 263 */   public static String flyingKickPlayerMessage = "Flying is not enabled on this server";
/* 264 */   public static String flyingKickVehicleMessage = "Flying is not enabled on this server";
/*     */   private static void flyingKickMessages() {
/* 266 */     flyingKickPlayerMessage = getString("messages.kick.flying-player", flyingKickPlayerMessage);
/* 267 */     flyingKickVehicleMessage = getString("messages.kick.flying-vehicle", flyingKickVehicleMessage);
/*     */   }
/*     */   public static boolean suggestPlayersWhenNullTabCompletions = true;
/*     */   
/*     */   private static void suggestPlayersWhenNull() {
/* 272 */     suggestPlayersWhenNullTabCompletions = getBoolean("settings.suggest-player-names-when-null-tab-completions", suggestPlayersWhenNullTabCompletions);
/*     */   }
/*     */   
/* 275 */   public static String authenticationServersDownKickMessage = "";
/*     */   private static void authenticationServersDownKickMessage() {
/* 277 */     authenticationServersDownKickMessage = Strings.emptyToNull(getString("messages.kick.authentication-servers-down", authenticationServersDownKickMessage));
/*     */   }
/*     */   
/* 280 */   public static String connectionThrottleKickMessage = "Connection throttled! Please wait before reconnecting.";
/*     */   private static void connectionThrottleKickMessage() {
/* 282 */     connectionThrottleKickMessage = getString("messages.kick.connection-throttle", connectionThrottleKickMessage);
/*     */   }
/*     */   
/* 285 */   public static String noPermissionMessage = "&cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.";
/*     */   private static void noPermissionMessage() {
/* 287 */     noPermissionMessage = ChatColor.translateAlternateColorCodes('&', getString("messages.no-permission", noPermissionMessage));
/*     */   }
/*     */   public static boolean savePlayerData = true;
/*     */   
/*     */   private static void savePlayerData() {
/* 292 */     savePlayerData = getBoolean("settings.save-player-data", savePlayerData);
/* 293 */     if (!savePlayerData) {
/* 294 */       Bukkit.getLogger().log(Level.WARNING, "Player Data Saving is currently disabled. Any changes to your players data, such as inventories, experience points, advancements and the like will not be saved when they log out.");
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean useAlternativeLuckFormula = false;
/*     */   
/*     */   private static void useAlternativeLuckFormula() {
/* 301 */     useAlternativeLuckFormula = getBoolean("settings.use-alternative-luck-formula", false);
/* 302 */     if (useAlternativeLuckFormula) {
/* 303 */       Bukkit.getLogger().log(Level.INFO, "Using Aikar's Alternative Luck Formula to apply Luck attribute to all loot pool calculations. See https://luckformula.emc.gs");
/*     */     }
/*     */   }
/*     */   
/* 307 */   public static int watchdogPrintEarlyWarningEvery = 5000;
/* 308 */   public static int watchdogPrintEarlyWarningDelay = 10000;
/*     */   private static void watchdogEarlyWarning() {
/* 310 */     watchdogPrintEarlyWarningEvery = getInt("settings.watchdog.early-warning-every", 5000);
/* 311 */     watchdogPrintEarlyWarningDelay = getInt("settings.watchdog.early-warning-delay", 10000);
/* 312 */     WatchdogThread.doStart(SpigotConfig.timeoutTime, SpigotConfig.restartOnCrash);
/*     */   }
/*     */   
/* 315 */   public static int tabSpamIncrement = 1;
/* 316 */   public static int tabSpamLimit = 500; public static boolean velocitySupport;
/*     */   private static void tabSpamLimiters() {
/* 318 */     tabSpamIncrement = getInt("settings.spam-limiter.tab-spam-increment", tabSpamIncrement);
/*     */     
/* 320 */     if (version < 14 && 
/* 321 */       tabSpamIncrement == 10) {
/* 322 */       set("settings.spam-limiter.tab-spam-increment", Integer.valueOf(2));
/* 323 */       tabSpamIncrement = 2;
/*     */     } 
/*     */     
/* 326 */     tabSpamLimit = getInt("settings.spam-limiter.tab-spam-limit", tabSpamLimit);
/*     */   }
/*     */   
/*     */   public static boolean velocityOnlineMode;
/*     */   public static byte[] velocitySecretKey;
/*     */   
/*     */   private static void velocitySupport() {
/* 333 */     velocitySupport = getBoolean("settings.velocity-support.enabled", false);
/* 334 */     velocityOnlineMode = getBoolean("settings.velocity-support.online-mode", false);
/* 335 */     String secret = getString("settings.velocity-support.secret", "");
/* 336 */     if (!TimingsManager.hiddenConfigs.contains("settings.velocity-support.secret")) {
/* 337 */       TimingsManager.hiddenConfigs.add("settings.velocity-support.secret");
/*     */     }
/* 339 */     if (velocitySupport && secret.isEmpty()) {
/* 340 */       fatal("Velocity support is enabled, but no secret key was specified. A secret key is required!");
/*     */     } else {
/* 342 */       velocitySecretKey = secret.getBytes(StandardCharsets.UTF_8);
/*     */     } 
/*     */   }
/*     */   
/* 346 */   public static int maxBookPageSize = 2560;
/* 347 */   public static double maxBookTotalSizeMultiplier = 0.98D;
/*     */   private static void maxBookSize() {
/* 349 */     maxBookPageSize = getInt("settings.book-size.page-max", maxBookPageSize);
/* 350 */     maxBookTotalSizeMultiplier = getDouble("settings.book-size.total-multiplier", maxBookTotalSizeMultiplier);
/*     */   }
/*     */   public static boolean useOptimizedTickList = true;
/*     */   
/*     */   private static void useOptimizedTickList() {
/* 355 */     if (config.contains("settings.use-optimized-ticklist"))
/* 356 */       useOptimizedTickList = config.getBoolean("settings.use-optimized-ticklist"); 
/*     */   }
/*     */   
/*     */   public static boolean asyncChunks = false;
/*     */   
/*     */   private static void asyncChunks() {
/*     */     ConfigurationSection section;
/* 363 */     if (version < 15) {
/* 364 */       section = config.createSection("settings.async-chunks");
/* 365 */       section.set("threads", Integer.valueOf(-1));
/*     */     } else {
/* 367 */       section = config.getConfigurationSection("settings.async-chunks");
/* 368 */       if (section == null) {
/* 369 */         section = config.createSection("settings.async-chunks");
/*     */       }
/*     */     } 
/*     */     
/* 373 */     if (section.contains("load-threads")) {
/* 374 */       if (!section.contains("threads")) {
/* 375 */         section.set("threads", section.get("load-threads"));
/*     */       }
/* 377 */       section.set("load-threads", null);
/*     */     } 
/* 379 */     section.set("generation", null);
/* 380 */     section.set("enabled", null);
/* 381 */     section.set("thread-per-world-generation", null);
/*     */     
/* 383 */     int threads = getInt("settings.async-chunks.threads", -1);
/* 384 */     int cpus = Runtime.getRuntime().availableProcessors();
/* 385 */     if (threads <= 0) {
/* 386 */       threads = Math.min(Integer.getInteger("paper.maxChunkThreads", 8).intValue(), Math.max(1, cpus - 1));
/*     */     }
/* 388 */     if (cpus == 1 && !Boolean.getBoolean("Paper.allowAsyncChunksSingleCore")) {
/* 389 */       asyncChunks = false;
/*     */     } else {
/* 391 */       asyncChunks = true;
/*     */     } 
/*     */ 
/*     */     
/* 395 */     String sharedHostThreads = System.getenv("PAPER_ASYNC_CHUNKS_SHARED_HOST_THREADS");
/* 396 */     if (sharedHostThreads != null) {
/*     */       try {
/* 398 */         threads = Math.max(1, Math.min(threads, Integer.parseInt(sharedHostThreads)));
/* 399 */       } catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */     
/* 402 */     if (!asyncChunks) {
/* 403 */       log("Async Chunks: Disabled - Chunks will be managed synchronosuly, and will cause tremendous lag.");
/*     */     } else {
/* 405 */       ChunkTaskManager.initGlobalLoadThreads(threads);
/* 406 */       log("Async Chunks: Enabled - Chunks will be loaded much faster, without lag.");
/*     */     } 
/*     */   }
/*     */   
/* 410 */   public static int midTickChunkTasks = 1000;
/*     */   private static void midTickChunkTasks() {
/* 412 */     midTickChunkTasks = getInt("settings.chunk-tasks-per-tick", midTickChunkTasks);
/*     */   }
/*     */   public static boolean allowBlockPermanentBreakingExploits = false;
/*     */   
/*     */   private static void allowBlockPermanentBreakingExploits() {
/* 417 */     if (config.contains("allow-perm-block-break-exploits")) {
/* 418 */       allowBlockPermanentBreakingExploits = config.getBoolean("allow-perm-block-break-exploits", false);
/* 419 */       config.set("allow-perm-block-break-exploits", null);
/*     */     } 
/*     */     
/* 422 */     config.set("settings.unsupported-settings.allow-permanent-block-break-exploits-readme", "This setting controls if players should be able to break bedrock, end portals and other intended to be permanent blocks.");
/* 423 */     allowBlockPermanentBreakingExploits = getBoolean("settings.unsupported-settings.allow-permanent-block-break-exploits", allowBlockPermanentBreakingExploits);
/*     */   }
/*     */   public static boolean consoleHasAllPermissions = false; public static boolean allowPistonDuplication;
/*     */   public static boolean allowHeadlessPistons;
/*     */   
/*     */   private static void consoleHasAllPermissions() {
/* 429 */     consoleHasAllPermissions = getBoolean("settings.console-has-all-permissions", consoleHasAllPermissions);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void allowPistonDuplication() {
/* 434 */     config.set("settings.unsupported-settings.allow-piston-duplication-readme", "This setting controls if player should be able to use TNT duplication, but this also allows duplicating carpet, rails and potentially other items");
/* 435 */     allowPistonDuplication = getBoolean("settings.unsupported-settings.allow-piston-duplication", config.getBoolean("settings.unsupported-settings.allow-tnt-duplication", false));
/* 436 */     set("settings.unsupported-settings.allow-tnt-duplication", null);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void allowHeadlessPistons() {
/* 441 */     config.set("settings.unsupported-settings.allow-headless-pistons-readme", "This setting controls if players should be able to create headless pistons.");
/* 442 */     allowHeadlessPistons = getBoolean("settings.unsupported-settings.allow-headless-pistons", false);
/*     */   }
/*     */   
/* 445 */   public static int playerAutoSaveRate = -1;
/* 446 */   public static int maxPlayerAutoSavePerTick = 10; public static int maxJoinsPerTick;
/*     */   private static void playerAutoSaveRate() {
/* 448 */     playerAutoSaveRate = getInt("settings.player-auto-save-rate", -1);
/* 449 */     maxPlayerAutoSavePerTick = getInt("settings.max-player-auto-save-per-tick", -1);
/* 450 */     if (maxPlayerAutoSavePerTick == -1)
/*     */     {
/* 452 */       maxPlayerAutoSavePerTick = (playerAutoSaveRate == -1 || playerAutoSaveRate > 100) ? 10 : 20;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void maxJoinsPerTick() {
/* 458 */     maxJoinsPerTick = getInt("settings.max-joins-per-tick", 3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\PaperConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */