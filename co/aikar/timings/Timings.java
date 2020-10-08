/*     */ package co.aikar.timings;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.EvictingQueue;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.plugin.Plugin;
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
/*     */ 
/*     */ public final class Timings
/*     */ {
/*  43 */   static final List<CommandSender> requestingReport = Lists.newArrayList();
/*     */   private static final int MAX_HISTORY_FRAMES = 12;
/*  45 */   public static final Timing NULL_HANDLER = new NullTimingHandler();
/*     */   static boolean timingsEnabled = false;
/*     */   static boolean verboseEnabled = false;
/*  48 */   private static int historyInterval = -1;
/*  49 */   private static int historyLength = -1;
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
/*     */   public static Timing of(@NotNull Plugin plugin, @NotNull String name) {
/*  62 */     Timing pluginHandler = null;
/*  63 */     if (plugin != null) {
/*  64 */       pluginHandler = ofSafe(plugin.getName(), "Combined Total", TimingsManager.PLUGIN_GROUP_HANDLER);
/*     */     }
/*  66 */     return of(plugin, name, pluginHandler);
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
/*     */   @NotNull
/*     */   public static Timing of(@NotNull Plugin plugin, @NotNull String name, @Nullable Timing groupHandler) {
/*  83 */     Preconditions.checkNotNull(plugin, "Plugin can not be null");
/*  84 */     return TimingsManager.getHandler(plugin.getName(), name, groupHandler);
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
/*     */   @NotNull
/*     */   public static Timing ofStart(@NotNull Plugin plugin, @NotNull String name) {
/* 100 */     return ofStart(plugin, name, null);
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
/*     */   @NotNull
/*     */   public static Timing ofStart(@NotNull Plugin plugin, @NotNull String name, @Nullable Timing groupHandler) {
/* 117 */     Timing timing = of(plugin, name, groupHandler);
/* 118 */     timing.startTiming();
/* 119 */     return timing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTimingsEnabled() {
/* 128 */     return timingsEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTimingsEnabled(boolean enabled) {
/* 139 */     timingsEnabled = enabled;
/* 140 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isVerboseTimingsEnabled() {
/* 151 */     return verboseEnabled;
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
/*     */   public static void setVerboseTimingsEnabled(boolean enabled) {
/* 163 */     verboseEnabled = enabled;
/* 164 */     TimingsManager.needsRecheckEnabled = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getHistoryInterval() {
/* 175 */     return historyInterval;
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
/*     */   public static void setHistoryInterval(int interval) {
/* 189 */     historyInterval = Math.max(1200, interval);
/*     */     
/* 191 */     if (historyLength != -1) {
/* 192 */       setHistoryLength(historyLength);
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
/*     */   public static int getHistoryLength() {
/* 204 */     return historyLength;
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
/*     */   public static void setHistoryLength(int length) {
/* 220 */     int maxLength = historyInterval * 12;
/*     */ 
/*     */ 
/*     */     
/* 224 */     if (System.getProperty("timings.bypassMax") != null) {
/* 225 */       maxLength = Integer.MAX_VALUE;
/*     */     }
/* 227 */     historyLength = Math.max(Math.min(maxLength, length), historyInterval);
/* 228 */     EvictingQueue<TimingHistory> evictingQueue = TimingsManager.HISTORY;
/* 229 */     int frames = getHistoryLength() / getHistoryInterval();
/* 230 */     if (length > maxLength) {
/* 231 */       Bukkit.getLogger().log(Level.WARNING, "Timings Length too high. Requested " + length + ", max is " + maxLength + ". To get longer history, you must increase your interval. Set Interval to " + Math.ceil((length / 12)) + " to achieve this length.");
/*     */     }
/* 233 */     TimingsManager.HISTORY = EvictingQueue.create(frames);
/* 234 */     TimingsManager.HISTORY.addAll((Collection)evictingQueue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reset() {
/* 241 */     TimingsManager.reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void generateReport(@Nullable CommandSender sender) {
/*     */     ConsoleCommandSender consoleCommandSender;
/* 251 */     if (sender == null) {
/* 252 */       consoleCommandSender = Bukkit.getConsoleSender();
/*     */     }
/* 254 */     requestingReport.add(consoleCommandSender);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void generateReport(@NotNull TimingsReportListener sender) {
/* 263 */     Validate.notNull(sender);
/* 264 */     requestingReport.add(sender);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   static TimingHandler ofSafe(@NotNull String name) {
/* 275 */     return ofSafe(null, name, null);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   static Timing ofSafe(@Nullable Plugin plugin, @NotNull String name) {
/* 280 */     Timing pluginHandler = null;
/* 281 */     if (plugin != null) {
/* 282 */       pluginHandler = ofSafe(plugin.getName(), "Combined Total", TimingsManager.PLUGIN_GROUP_HANDLER);
/*     */     }
/* 284 */     return ofSafe((plugin != null) ? plugin.getName() : "Minecraft - Invalid Plugin", name, pluginHandler);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   static TimingHandler ofSafe(@NotNull String name, @Nullable Timing groupHandler) {
/* 289 */     return ofSafe(null, name, groupHandler);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   static TimingHandler ofSafe(@Nullable String groupName, @NotNull String name, @Nullable Timing groupHandler) {
/* 294 */     return TimingsManager.getHandler(groupName, name, groupHandler);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\Timings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */