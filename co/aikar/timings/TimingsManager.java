/*     */ package co.aikar.timings;
/*     */ 
/*     */ import co.aikar.util.LoadingMap;
/*     */ import com.google.common.collect.EvictingQueue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.function.Function;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.PluginClassLoader;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public final class TimingsManager
/*     */ {
/*  44 */   static final Map<TimingIdentifier, TimingHandler> TIMING_MAP = LoadingMap.of(new ConcurrentHashMap<>(4096, 0.5F), TimingHandler::new);
/*     */ 
/*     */   
/*  47 */   public static final FullServerTickHandler FULL_SERVER_TICK = new FullServerTickHandler();
/*  48 */   public static final TimingHandler TIMINGS_TICK = Timings.ofSafe("Timings Tick", FULL_SERVER_TICK);
/*  49 */   public static final Timing PLUGIN_GROUP_HANDLER = Timings.ofSafe("Plugins");
/*  50 */   public static List<String> hiddenConfigs = new ArrayList<>();
/*     */   
/*     */   public static boolean privacy = false;
/*  53 */   static final List<TimingHandler> HANDLERS = new ArrayList<>(1024);
/*  54 */   static final List<TimingHistory.MinuteReport> MINUTE_REPORTS = new ArrayList<>(64);
/*     */   
/*  56 */   static EvictingQueue<TimingHistory> HISTORY = EvictingQueue.create(12);
/*  57 */   static long timingStart = 0L;
/*  58 */   static long historyStart = 0L;
/*     */ 
/*     */   
/*     */   static boolean needsFullReset = false;
/*     */ 
/*     */   
/*     */   static boolean needsRecheckEnabled = false;
/*     */ 
/*     */   
/*     */   static void reset() {
/*  68 */     needsFullReset = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void tick() {
/*  76 */     if (Timings.timingsEnabled) {
/*  77 */       boolean violated = FULL_SERVER_TICK.isViolated();
/*     */       
/*  79 */       for (TimingHandler handler : HANDLERS) {
/*  80 */         if (handler.isSpecial()) {
/*     */           continue;
/*     */         }
/*     */         
/*  84 */         handler.processTick(violated);
/*     */       } 
/*     */       
/*  87 */       TimingHistory.playerTicks += Bukkit.getOnlinePlayers().size();
/*  88 */       TimingHistory.timedTicks++;
/*     */     } 
/*     */   }
/*     */   
/*     */   static void stopServer() {
/*  93 */     Timings.timingsEnabled = false;
/*  94 */     recheckEnabled();
/*     */   }
/*     */   static void recheckEnabled() {
/*  97 */     synchronized (TIMING_MAP) {
/*  98 */       for (TimingHandler timings : TIMING_MAP.values()) {
/*  99 */         timings.checkEnabled();
/*     */       }
/*     */     } 
/* 102 */     needsRecheckEnabled = false;
/*     */   }
/*     */   static void resetTimings() {
/* 105 */     if (needsFullReset) {
/*     */ 
/*     */       
/* 108 */       synchronized (TIMING_MAP) {
/* 109 */         for (TimingHandler timings : TIMING_MAP.values()) {
/* 110 */           timings.reset(true);
/*     */         }
/*     */       } 
/* 113 */       Bukkit.getLogger().log(Level.INFO, "Timings Reset");
/* 114 */       HISTORY.clear();
/* 115 */       needsFullReset = false;
/* 116 */       needsRecheckEnabled = false;
/* 117 */       timingStart = System.currentTimeMillis();
/*     */     }
/*     */     else {
/*     */       
/* 121 */       for (TimingHandler timings : HANDLERS) {
/* 122 */         timings.reset(false);
/*     */       }
/*     */     } 
/*     */     
/* 126 */     HANDLERS.clear();
/* 127 */     MINUTE_REPORTS.clear();
/*     */     
/* 129 */     TimingHistory.resetTicks(true);
/* 130 */     historyStart = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   static TimingHandler getHandler(@Nullable String group, @NotNull String name, @Nullable Timing parent) {
/* 135 */     return TIMING_MAP.get(new TimingIdentifier(group, name, parent));
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
/*     */   @NotNull
/*     */   public static Timing getCommandTiming(@Nullable String pluginName, @NotNull Command command) {
/* 150 */     Plugin plugin = null;
/* 151 */     Server server = Bukkit.getServer();
/* 152 */     if (server != null && pluginName != null && 
/* 153 */       !"minecraft".equals(pluginName) && !"bukkit".equals(pluginName) && 
/* 154 */       !"spigot".equalsIgnoreCase(pluginName) && !"paper".equals(pluginName))
/*     */     {
/* 156 */       plugin = server.getPluginManager().getPlugin(pluginName);
/*     */     }
/* 158 */     if (plugin == null)
/*     */     {
/* 160 */       plugin = getPluginByClassloader(command.getClass());
/*     */     }
/* 162 */     if (plugin == null) {
/* 163 */       return Timings.ofSafe("Command: " + pluginName + ":" + command.getTimingName());
/*     */     }
/*     */     
/* 166 */     return Timings.ofSafe(plugin, "Command: " + pluginName + ":" + command.getTimingName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Plugin getPluginByClassloader(@Nullable Class<?> clazz) {
/* 178 */     if (clazz == null) {
/* 179 */       return null;
/*     */     }
/* 181 */     ClassLoader classLoader = clazz.getClassLoader();
/* 182 */     if (classLoader instanceof PluginClassLoader) {
/* 183 */       PluginClassLoader pluginClassLoader = (PluginClassLoader)classLoader;
/* 184 */       return (Plugin)pluginClassLoader.getPlugin();
/*     */     } 
/* 186 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */