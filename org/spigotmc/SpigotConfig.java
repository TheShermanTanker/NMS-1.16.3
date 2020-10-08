/*     */ package org.spigotmc;
/*     */ 
/*     */ import com.google.common.base.Throwables;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import net.minecraft.server.v1_16_R2.AttributeRanged;
/*     */ import net.minecraft.server.v1_16_R2.GenericAttributes;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.core.LoggerContext;
/*     */ import org.apache.logging.log4j.core.config.Configuration;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpigotConfig
/*     */ {
/*     */   private static File CONFIG_FILE;
/*     */   private static final String HEADER = "This is the main configuration file for Spigot.\nAs you can see, there's tons to configure. Some options may impact gameplay, so use\nwith caution, and make sure you know what each option does before configuring.\nFor a reference for any variable inside this file, check out the Spigot wiki at\nhttp://www.spigotmc.org/wiki/spigot-configuration/\n\nIf you need help with the configuration or have any questions related to Spigot,\njoin us at the IRC or drop by our forums and leave a post.\n\nIRC: #spigot @ irc.spi.gt ( http://www.spigotmc.org/pages/irc/ )\nForums: http://www.spigotmc.org/\n";
/*     */   public static YamlConfiguration config;
/*     */   static int version;
/*     */   static Map<String, Command> commands;
/*     */   private static Metrics metrics;
/*     */   public static boolean logCommands;
/*     */   public static int tabComplete;
/*     */   public static boolean sendNamespaced;
/*     */   public static String whitelistMessage;
/*     */   public static String unknownCommandMessage;
/*     */   public static String serverFullMessage;
/*     */   
/*     */   public static void init(File configFile) {
/*  55 */     CONFIG_FILE = configFile;
/*  56 */     config = new YamlConfiguration();
/*     */     
/*     */     try {
/*  59 */       config.load(CONFIG_FILE);
/*  60 */     } catch (IOException iOException) {
/*     */     
/*  62 */     } catch (InvalidConfigurationException ex) {
/*     */       
/*  64 */       Bukkit.getLogger().log(Level.SEVERE, "Could not load spigot.yml, please correct your syntax errors", (Throwable)ex);
/*  65 */       throw Throwables.propagate(ex);
/*     */     } 
/*     */     
/*  68 */     config.options().header("This is the main configuration file for Spigot.\nAs you can see, there's tons to configure. Some options may impact gameplay, so use\nwith caution, and make sure you know what each option does before configuring.\nFor a reference for any variable inside this file, check out the Spigot wiki at\nhttp://www.spigotmc.org/wiki/spigot-configuration/\n\nIf you need help with the configuration or have any questions related to Spigot,\njoin us at the IRC or drop by our forums and leave a post.\n\nIRC: #spigot @ irc.spi.gt ( http://www.spigotmc.org/pages/irc/ )\nForums: http://www.spigotmc.org/\n");
/*  69 */     config.options().copyDefaults(true);
/*     */     
/*  71 */     commands = new HashMap<>();
/*  72 */     commands.put("spigot", new SpigotCommand("spigot"));
/*     */     
/*  74 */     version = getInt("config-version", 12);
/*  75 */     set("config-version", Integer.valueOf(12));
/*  76 */     readConfig(SpigotConfig.class, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerCommands() {
/*  81 */     for (Map.Entry<String, Command> entry : commands.entrySet())
/*     */     {
/*  83 */       (MinecraftServer.getServer()).server.getCommandMap().register(entry.getKey(), "Spigot", entry.getValue());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void readConfig(Class<?> clazz, Object instance) {
/* 103 */     for (Method method : clazz.getDeclaredMethods()) {
/*     */       
/* 105 */       if (Modifier.isPrivate(method.getModifiers()))
/*     */       {
/* 107 */         if ((method.getParameterTypes()).length == 0 && method.getReturnType() == void.class) {
/*     */           
/*     */           try {
/*     */             
/* 111 */             method.setAccessible(true);
/* 112 */             method.invoke(instance, new Object[0]);
/* 113 */           } catch (InvocationTargetException ex) {
/*     */             
/* 115 */             throw Throwables.propagate(ex.getCause());
/* 116 */           } catch (Exception ex) {
/*     */             
/* 118 */             Bukkit.getLogger().log(Level.SEVERE, "Error invoking " + method, ex);
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 126 */       config.save(CONFIG_FILE);
/* 127 */     } catch (IOException ex) {
/*     */       
/* 129 */       Bukkit.getLogger().log(Level.SEVERE, "Could not save " + CONFIG_FILE, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void set(String path, Object val) {
/* 135 */     config.set(path, val);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean getBoolean(String path, boolean def) {
/* 140 */     config.addDefault(path, Boolean.valueOf(def));
/* 141 */     return config.getBoolean(path, config.getBoolean(path));
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getInt(String path, int def) {
/* 146 */     config.addDefault(path, Integer.valueOf(def));
/* 147 */     return config.getInt(path, config.getInt(path));
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> List getList(String path, T def) {
/* 152 */     config.addDefault(path, def);
/* 153 */     return config.getList(path, config.getList(path));
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getString(String path, String def) {
/* 158 */     config.addDefault(path, def);
/* 159 */     return config.getString(path, config.getString(path));
/*     */   }
/*     */ 
/*     */   
/*     */   private static double getDouble(String path, double def) {
/* 164 */     config.addDefault(path, Double.valueOf(def));
/* 165 */     return config.getDouble(path, config.getDouble(path));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void logCommands() {
/* 171 */     logCommands = getBoolean("commands.log", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void tabComplete() {
/* 178 */     if (version < 6) {
/*     */       
/* 180 */       boolean oldValue = getBoolean("commands.tab-complete", true);
/* 181 */       if (oldValue) {
/*     */         
/* 183 */         set("commands.tab-complete", Integer.valueOf(0));
/*     */       } else {
/*     */         
/* 186 */         set("commands.tab-complete", Integer.valueOf(-1));
/*     */       } 
/*     */     } 
/* 189 */     tabComplete = getInt("commands.tab-complete", 0);
/* 190 */     sendNamespaced = getBoolean("commands.send-namespaced", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public static String outdatedClientMessage = "Outdated client! Please use {0}";
/* 197 */   public static String outdatedServerMessage = "Outdated server! I'm still on {0}";
/*     */   
/*     */   private static String transform(String s) {
/* 200 */     return ChatColor.translateAlternateColorCodes('&', s).replaceAll("\\\\n", "\n");
/*     */   }
/*     */   
/*     */   private static void messages() {
/* 204 */     if (version < 8) {
/*     */       
/* 206 */       set("messages.outdated-client", outdatedClientMessage);
/* 207 */       set("messages.outdated-server", outdatedServerMessage);
/*     */     } 
/*     */     
/* 210 */     whitelistMessage = transform(getString("messages.whitelist", "You are not whitelisted on this server!"));
/* 211 */     unknownCommandMessage = transform(getString("messages.unknown-command", "Unknown command. Type \"/help\" for help."));
/* 212 */     serverFullMessage = transform(getString("messages.server-full", "The server is full!"));
/* 213 */     outdatedClientMessage = transform(getString("messages.outdated-client", outdatedClientMessage));
/* 214 */     outdatedServerMessage = transform(getString("messages.outdated-server", outdatedServerMessage));
/*     */   }
/*     */   
/* 217 */   public static int timeoutTime = 60;
/*     */   public static boolean restartOnCrash = true;
/* 219 */   public static String restartScript = "./start.sh";
/*     */   public static String restartMessage;
/*     */   
/*     */   private static void watchdog() {
/* 223 */     timeoutTime = getInt("settings.timeout-time", timeoutTime);
/* 224 */     restartOnCrash = getBoolean("settings.restart-on-crash", restartOnCrash);
/* 225 */     restartScript = getString("settings.restart-script", restartScript);
/* 226 */     restartMessage = transform(getString("messages.restart", "Server is restarting"));
/* 227 */     commands.put("restart", new RestartCommand("restart"));
/*     */   }
/*     */   public static boolean bungee;
/*     */   public static boolean disableStatSaving;
/*     */   
/*     */   private static void bungee() {
/* 233 */     if (version < 4) {
/*     */       
/* 235 */       set("settings.bungeecord", Boolean.valueOf(false));
/* 236 */       System.out.println("Oudated config, disabling BungeeCord support!");
/*     */     } 
/* 238 */     bungee = getBoolean("settings.bungeecord", false);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void nettyThreads() {
/* 243 */     int count = getInt("settings.netty-threads", 4);
/* 244 */     System.setProperty("io.netty.eventLoopThreads", Integer.toString(count));
/* 245 */     Bukkit.getLogger().log(Level.INFO, "Using {0} threads for Netty based IO", Integer.valueOf(count));
/*     */   }
/*     */   public static int playerSample;
/*     */   public static int playerShuffle;
/* 249 */   public static Map<MinecraftKey, Integer> forcedStats = new HashMap<>(); public static List<String> spamExclusions;
/*     */   
/*     */   private static void stats() {
/* 252 */     disableStatSaving = getBoolean("stats.disable-saving", false);
/*     */     
/* 254 */     if (!config.contains("stats.forced-stats")) {
/* 255 */       config.createSection("stats.forced-stats");
/*     */     }
/*     */     
/* 258 */     ConfigurationSection section = config.getConfigurationSection("stats.forced-stats");
/* 259 */     for (String name : section.getKeys(true)) {
/*     */       
/* 261 */       if (section.isInt(name))
/*     */         
/*     */         try {
/*     */           
/* 265 */           MinecraftKey key = new MinecraftKey(name);
/* 266 */           if (IRegistry.CUSTOM_STAT.get(key) == null) {
/*     */             
/* 268 */             Bukkit.getLogger().log(Level.WARNING, "Ignoring non existent stats.forced-stats " + name);
/*     */             continue;
/*     */           } 
/* 271 */           forcedStats.put(key, Integer.valueOf(section.getInt(name)));
/* 272 */         } catch (Exception ex) {
/*     */           
/* 274 */           Bukkit.getLogger().log(Level.WARNING, "Ignoring invalid stats.forced-stats " + name);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   public static boolean silentCommandBlocks; public static Set<String> replaceCommands;
/*     */   public static int userCacheCap;
/*     */   
/*     */   private static void tpsCommand() {
/* 282 */     commands.put("tps", new TicksPerSecondCommand("tps"));
/*     */   }
/*     */   public static boolean saveUserCacheOnStopOnly; public static double movedWronglyThreshold;
/*     */   public static double movedTooQuicklyMultiplier;
/*     */   
/*     */   private static void playerSample() {
/* 288 */     playerSample = Math.max(getInt("settings.sample-count", 12), 0);
/* 289 */     Bukkit.getLogger().log(Level.INFO, "Server Ping Player Sample Count: {0}", Integer.valueOf(playerSample));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void playerShuffle() {
/* 295 */     playerShuffle = getInt("settings.player-shuffle", 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void spamExclusions() {
/* 301 */     spamExclusions = getList("commands.spam-exclusions", Arrays.asList(new String[] { "/skill" }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void silentCommandBlocks() {
/* 310 */     silentCommandBlocks = getBoolean("commands.silent-commandblock-console", false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void replaceCommands() {
/* 316 */     if (config.contains("replace-commands")) {
/*     */       
/* 318 */       set("commands.replace-commands", config.getStringList("replace-commands"));
/* 319 */       config.set("replace-commands", null);
/*     */     } 
/* 321 */     replaceCommands = new HashSet<>(getList("commands.replace-commands", 
/* 322 */           Arrays.asList(new String[] { "setblock", "summon", "testforblock", "tellraw" })));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void userCacheCap() {
/* 328 */     userCacheCap = getInt("settings.user-cache-size", 1000);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void saveUserCacheOnStopOnly() {
/* 334 */     saveUserCacheOnStopOnly = getBoolean("settings.save-user-cache-on-stop-only", false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void movedWronglyThreshold() {
/* 340 */     movedWronglyThreshold = getDouble("settings.moved-wrongly-threshold", 0.0625D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void movedTooQuicklyMultiplier() {
/* 346 */     movedTooQuicklyMultiplier = getDouble("settings.moved-too-quickly-multiplier", 10.0D);
/*     */   }
/*     */   
/* 349 */   public static double maxHealth = 2048.0D;
/* 350 */   public static double movementSpeed = 2048.0D;
/* 351 */   public static double attackDamage = 2048.0D; public static boolean debug; public static boolean disableAdvancementSaving;
/*     */   
/*     */   private static void attributeMaxes() {
/* 354 */     maxHealth = getDouble("settings.attribute.maxHealth.max", maxHealth);
/* 355 */     ((AttributeRanged)GenericAttributes.MAX_HEALTH).maximum = maxHealth;
/* 356 */     movementSpeed = getDouble("settings.attribute.movementSpeed.max", movementSpeed);
/* 357 */     ((AttributeRanged)GenericAttributes.MOVEMENT_SPEED).maximum = movementSpeed;
/* 358 */     attackDamage = getDouble("settings.attribute.attackDamage.max", attackDamage);
/* 359 */     ((AttributeRanged)GenericAttributes.ATTACK_DAMAGE).maximum = attackDamage;
/*     */   }
/*     */   public static List<String> disabledAdvancements;
/*     */   public static boolean logVillagerDeaths;
/*     */   
/*     */   private static void debug() {
/* 365 */     debug = getBoolean("settings.debug", false);
/*     */     
/* 367 */     if (debug && !LogManager.getRootLogger().isTraceEnabled()) {
/*     */ 
/*     */       
/* 370 */       LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
/* 371 */       Configuration conf = ctx.getConfiguration();
/* 372 */       conf.getLoggerConfig("").setLevel(Level.ALL);
/* 373 */       ctx.updateLoggers(conf);
/*     */     } 
/*     */     
/* 376 */     if (LogManager.getRootLogger().isTraceEnabled()) {
/*     */       
/* 378 */       Bukkit.getLogger().info("Debug logging is enabled");
/*     */     } else {
/*     */       
/* 381 */       Bukkit.getLogger().info("Debug logging is disabled");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void disabledAdvancements() {
/* 388 */     disableAdvancementSaving = getBoolean("advancements.disable-saving", false);
/* 389 */     disabledAdvancements = getList("advancements.disabled", Arrays.asList(new String[] { "minecraft:story/disabled" }));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void logVillagerDeaths() {
/* 394 */     logVillagerDeaths = getBoolean("settings.log-villager-deaths", true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\SpigotConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */