/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.json.simple.JSONArray;
/*     */ import org.json.simple.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Metrics
/*     */ {
/*     */   public static final int B_STATS_VERSION = 1;
/*     */   private static final String URL = "https://bStats.org/submitData/server-implementation";
/*     */   private static boolean logFailedRequests = false;
/*  40 */   private static Logger logger = Logger.getLogger("bStats");
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */   
/*     */   private final String serverUUID;
/*     */ 
/*     */   
/*  49 */   private final List<CustomChart> charts = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metrics(String name, String serverUUID, boolean logFailedRequests, Logger logger) {
/*  60 */     this.name = name;
/*  61 */     this.serverUUID = serverUUID;
/*  62 */     Metrics.logFailedRequests = logFailedRequests;
/*  63 */     Metrics.logger = logger;
/*     */ 
/*     */     
/*  66 */     startSubmitting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCustomChart(CustomChart chart) {
/*  75 */     if (chart == null) {
/*  76 */       throw new IllegalArgumentException("Chart cannot be null!");
/*     */     }
/*  78 */     this.charts.add(chart);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void startSubmitting() {
/*  85 */     Timer timer = new Timer(true);
/*  86 */     timer.scheduleAtFixedRate(new TimerTask()
/*     */         {
/*     */           public void run() {
/*  89 */             if (MinecraftServer.getServer().hasStopped()) {
/*     */               return;
/*     */             }
/*  92 */             Metrics.this.submitData();
/*     */           }
/*     */         },  300000L, 1800000L);
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
/*     */   private JSONObject getPluginData() {
/* 106 */     JSONObject data = new JSONObject();
/*     */     
/* 108 */     data.put("pluginName", this.name);
/* 109 */     JSONArray customCharts = new JSONArray();
/* 110 */     for (CustomChart customChart : this.charts) {
/*     */       
/* 112 */       JSONObject chart = customChart.getRequestJsonObject();
/* 113 */       if (chart == null) {
/*     */         continue;
/*     */       }
/* 116 */       customCharts.add(chart);
/*     */     } 
/* 118 */     data.put("customCharts", customCharts);
/*     */     
/* 120 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JSONObject getServerData() {
/* 130 */     String osName = System.getProperty("os.name");
/* 131 */     String osArch = System.getProperty("os.arch");
/* 132 */     String osVersion = System.getProperty("os.version");
/* 133 */     int coreCount = Runtime.getRuntime().availableProcessors();
/*     */     
/* 135 */     JSONObject data = new JSONObject();
/*     */     
/* 137 */     data.put("serverUUID", this.serverUUID);
/*     */     
/* 139 */     data.put("osName", osName);
/* 140 */     data.put("osArch", osArch);
/* 141 */     data.put("osVersion", osVersion);
/* 142 */     data.put("coreCount", Integer.valueOf(coreCount));
/*     */     
/* 144 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void submitData() {
/* 151 */     JSONObject data = getServerData();
/*     */     
/* 153 */     JSONArray pluginData = new JSONArray();
/* 154 */     pluginData.add(getPluginData());
/* 155 */     data.put("plugins", pluginData);
/*     */ 
/*     */     
/*     */     try {
/* 159 */       sendData(data);
/* 160 */     } catch (Exception e) {
/*     */       
/* 162 */       if (logFailedRequests) {
/* 163 */         logger.log(Level.WARNING, "Could not submit stats of " + this.name, e);
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
/*     */   private static void sendData(JSONObject data) throws Exception {
/* 175 */     if (data == null) {
/* 176 */       throw new IllegalArgumentException("Data cannot be null!");
/*     */     }
/* 178 */     HttpsURLConnection connection = (HttpsURLConnection)(new URL("https://bStats.org/submitData/server-implementation")).openConnection();
/*     */ 
/*     */     
/* 181 */     byte[] compressedData = compress(data.toString());
/*     */ 
/*     */     
/* 184 */     connection.setRequestMethod("POST");
/* 185 */     connection.addRequestProperty("Accept", "application/json");
/* 186 */     connection.addRequestProperty("Connection", "close");
/* 187 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 188 */     connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
/* 189 */     connection.setRequestProperty("Content-Type", "application/json");
/* 190 */     connection.setRequestProperty("User-Agent", "MC-Server/1");
/*     */ 
/*     */     
/* 193 */     connection.setDoOutput(true);
/* 194 */     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
/* 195 */     outputStream.write(compressedData);
/* 196 */     outputStream.flush();
/* 197 */     outputStream.close();
/*     */     
/* 199 */     connection.getInputStream().close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] compress(String str) throws IOException {
/* 210 */     if (str == null) {
/* 211 */       return null;
/*     */     }
/* 213 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 214 */     GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
/* 215 */     gzip.write(str.getBytes("UTF-8"));
/* 216 */     gzip.close();
/* 217 */     return outputStream.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class CustomChart
/*     */   {
/*     */     final String chartId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     CustomChart(String chartId) {
/* 234 */       if (chartId == null || chartId.isEmpty()) {
/* 235 */         throw new IllegalArgumentException("ChartId cannot be null or empty!");
/*     */       }
/* 237 */       this.chartId = chartId;
/*     */     }
/*     */     
/*     */     private JSONObject getRequestJsonObject() {
/* 241 */       JSONObject chart = new JSONObject();
/* 242 */       chart.put("chartId", this.chartId);
/*     */       try {
/* 244 */         JSONObject data = getChartData();
/* 245 */         if (data == null)
/*     */         {
/* 247 */           return null;
/*     */         }
/* 249 */         chart.put("data", data);
/* 250 */       } catch (Throwable t) {
/* 251 */         if (Metrics.logFailedRequests) {
/* 252 */           Metrics.logger.log(Level.WARNING, "Failed to get data for custom chart with id " + this.chartId, t);
/*     */         }
/* 254 */         return null;
/*     */       } 
/* 256 */       return chart;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract JSONObject getChartData() throws Exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SimplePie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<String> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SimplePie(String chartId, Callable<String> callable) {
/* 277 */       super(chartId);
/* 278 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() throws Exception {
/* 283 */       JSONObject data = new JSONObject();
/* 284 */       String value = this.callable.call();
/* 285 */       if (value == null || value.isEmpty())
/*     */       {
/* 287 */         return null;
/*     */       }
/* 289 */       data.put("value", value);
/* 290 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AdvancedPie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AdvancedPie(String chartId, Callable<Map<String, Integer>> callable) {
/* 308 */       super(chartId);
/* 309 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() throws Exception {
/* 314 */       JSONObject data = new JSONObject();
/* 315 */       JSONObject values = new JSONObject();
/* 316 */       Map<String, Integer> map = this.callable.call();
/* 317 */       if (map == null || map.isEmpty())
/*     */       {
/* 319 */         return null;
/*     */       }
/* 321 */       boolean allSkipped = true;
/* 322 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 323 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/* 326 */         allSkipped = false;
/* 327 */         values.put(entry.getKey(), entry.getValue());
/*     */       } 
/* 329 */       if (allSkipped)
/*     */       {
/* 331 */         return null;
/*     */       }
/* 333 */       data.put("values", values);
/* 334 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DrilldownPie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Map<String, Integer>>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DrilldownPie(String chartId, Callable<Map<String, Map<String, Integer>>> callable) {
/* 352 */       super(chartId);
/* 353 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     public JSONObject getChartData() throws Exception {
/* 358 */       JSONObject data = new JSONObject();
/* 359 */       JSONObject values = new JSONObject();
/* 360 */       Map<String, Map<String, Integer>> map = this.callable.call();
/* 361 */       if (map == null || map.isEmpty())
/*     */       {
/* 363 */         return null;
/*     */       }
/* 365 */       boolean reallyAllSkipped = true;
/* 366 */       for (Map.Entry<String, Map<String, Integer>> entryValues : map.entrySet()) {
/* 367 */         JSONObject value = new JSONObject();
/* 368 */         boolean allSkipped = true;
/* 369 */         for (Map.Entry<String, Integer> valueEntry : (Iterable<Map.Entry<String, Integer>>)((Map)map.get(entryValues.getKey())).entrySet()) {
/* 370 */           value.put(valueEntry.getKey(), valueEntry.getValue());
/* 371 */           allSkipped = false;
/*     */         } 
/* 373 */         if (!allSkipped) {
/* 374 */           reallyAllSkipped = false;
/* 375 */           values.put(entryValues.getKey(), value);
/*     */         } 
/*     */       } 
/* 378 */       if (reallyAllSkipped)
/*     */       {
/* 380 */         return null;
/*     */       }
/* 382 */       data.put("values", values);
/* 383 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SingleLineChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Integer> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SingleLineChart(String chartId, Callable<Integer> callable) {
/* 401 */       super(chartId);
/* 402 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() throws Exception {
/* 407 */       JSONObject data = new JSONObject();
/* 408 */       int value = ((Integer)this.callable.call()).intValue();
/* 409 */       if (value == 0)
/*     */       {
/* 411 */         return null;
/*     */       }
/* 413 */       data.put("value", Integer.valueOf(value));
/* 414 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class MultiLineChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MultiLineChart(String chartId, Callable<Map<String, Integer>> callable) {
/* 433 */       super(chartId);
/* 434 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() throws Exception {
/* 439 */       JSONObject data = new JSONObject();
/* 440 */       JSONObject values = new JSONObject();
/* 441 */       Map<String, Integer> map = this.callable.call();
/* 442 */       if (map == null || map.isEmpty())
/*     */       {
/* 444 */         return null;
/*     */       }
/* 446 */       boolean allSkipped = true;
/* 447 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 448 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/* 451 */         allSkipped = false;
/* 452 */         values.put(entry.getKey(), entry.getValue());
/*     */       } 
/* 454 */       if (allSkipped)
/*     */       {
/* 456 */         return null;
/*     */       }
/* 458 */       data.put("values", values);
/* 459 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SimpleBarChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SimpleBarChart(String chartId, Callable<Map<String, Integer>> callable) {
/* 478 */       super(chartId);
/* 479 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() throws Exception {
/* 484 */       JSONObject data = new JSONObject();
/* 485 */       JSONObject values = new JSONObject();
/* 486 */       Map<String, Integer> map = this.callable.call();
/* 487 */       if (map == null || map.isEmpty())
/*     */       {
/* 489 */         return null;
/*     */       }
/* 491 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 492 */         JSONArray categoryValues = new JSONArray();
/* 493 */         categoryValues.add(entry.getValue());
/* 494 */         values.put(entry.getKey(), categoryValues);
/*     */       } 
/* 496 */       data.put("values", values);
/* 497 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AdvancedBarChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, int[]>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AdvancedBarChart(String chartId, Callable<Map<String, int[]>> callable) {
/* 516 */       super(chartId);
/* 517 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() throws Exception {
/* 522 */       JSONObject data = new JSONObject();
/* 523 */       JSONObject values = new JSONObject();
/* 524 */       Map<String, int[]> map = this.callable.call();
/* 525 */       if (map == null || map.isEmpty())
/*     */       {
/* 527 */         return null;
/*     */       }
/* 529 */       boolean allSkipped = true;
/* 530 */       for (Map.Entry<String, int[]> entry : map.entrySet()) {
/* 531 */         if (((int[])entry.getValue()).length == 0) {
/*     */           continue;
/*     */         }
/* 534 */         allSkipped = false;
/* 535 */         JSONArray categoryValues = new JSONArray();
/* 536 */         for (int categoryValue : (int[])entry.getValue()) {
/* 537 */           categoryValues.add(Integer.valueOf(categoryValue));
/*     */         }
/* 539 */         values.put(entry.getKey(), categoryValues);
/*     */       } 
/* 541 */       if (allSkipped)
/*     */       {
/* 543 */         return null;
/*     */       }
/* 545 */       data.put("values", values);
/* 546 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class PaperMetrics
/*     */   {
/*     */     static void startMetrics() {
/* 554 */       File configFile = new File(new File((File)(MinecraftServer.getServer()).options.valueOf("plugins"), "bStats"), "config.yml");
/* 555 */       YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
/*     */ 
/*     */       
/* 558 */       if (!config.isSet("serverUuid")) {
/*     */ 
/*     */         
/* 561 */         config.addDefault("enabled", Boolean.valueOf(true));
/*     */         
/* 563 */         config.addDefault("serverUuid", UUID.randomUUID().toString());
/*     */         
/* 565 */         config.addDefault("logFailedRequests", Boolean.valueOf(false));
/*     */ 
/*     */         
/* 568 */         config.options().header("bStats collects some data for plugin authors like how many servers are using their plugins.\nTo honor their work, you should not disable it.\nThis has nearly no effect on the server performance!\nCheck out https://bStats.org/ to learn more :)")
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 573 */           .copyDefaults(true);
/*     */         try {
/* 575 */           config.save(configFile);
/* 576 */         } catch (IOException iOException) {}
/*     */       } 
/*     */ 
/*     */       
/* 580 */       String serverUUID = config.getString("serverUuid");
/* 581 */       boolean logFailedRequests = config.getBoolean("logFailedRequests", false);
/*     */       
/* 583 */       if (config.getBoolean("enabled", true)) {
/* 584 */         Metrics metrics = new Metrics("Paper", serverUUID, logFailedRequests, Bukkit.getLogger());
/*     */         
/* 586 */         metrics.addCustomChart(new Metrics.SimplePie("minecraft_version", () -> {
/*     */                 minecraftVersion = Bukkit.getVersion();
/*     */                 
/*     */                 return minecraftVersion.substring(minecraftVersion.indexOf("MC: ") + 4, minecraftVersion.length() - 1);
/*     */               }));
/*     */         
/* 592 */         metrics.addCustomChart(new Metrics.SingleLineChart("players", () -> Integer.valueOf(Bukkit.getOnlinePlayers().size())));
/* 593 */         metrics.addCustomChart(new Metrics.SimplePie("online_mode", () -> (Bukkit.getOnlineMode() || PaperConfig.isProxyOnlineMode()) ? "online" : "offline"));
/* 594 */         metrics.addCustomChart(new Metrics.SimplePie("paper_version", () -> (Metrics.class.getPackage().getImplementationVersion() != null) ? Metrics.class.getPackage().getImplementationVersion() : "unknown"));
/*     */         
/* 596 */         metrics.addCustomChart(new Metrics.DrilldownPie("java_version", () -> {
/*     */                 String release;
/*     */                 Map<String, Map<String, Integer>> map = new HashMap<>();
/*     */                 String javaVersion = System.getProperty("java.version");
/*     */                 Map<String, Integer> entry = new HashMap<>();
/*     */                 entry.put(javaVersion, Integer.valueOf(1));
/*     */                 String majorVersion = javaVersion.split("\\.")[0];
/*     */                 int indexOf = javaVersion.lastIndexOf('.');
/*     */                 if (majorVersion.equals("1")) {
/*     */                   release = "Java " + javaVersion.substring(0, indexOf);
/*     */                 } else {
/*     */                   Matcher versionMatcher = Pattern.compile("\\d+").matcher(majorVersion);
/*     */                   if (versionMatcher.find())
/*     */                     majorVersion = versionMatcher.group(0); 
/*     */                   release = "Java " + majorVersion;
/*     */                 } 
/*     */                 map.put(release, entry);
/*     */                 return map;
/*     */               }));
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\Metrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */