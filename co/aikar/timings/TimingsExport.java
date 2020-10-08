/*     */ package co.aikar.timings;
/*     */ 
/*     */ import co.aikar.util.JSONUtil;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.management.GarbageCollectorMXBean;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.OperatingSystemMXBean;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.InetAddress;
/*     */ import java.net.URL;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.ResourcePackLoader;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.json.simple.JSONObject;
/*     */ import org.json.simple.JSONValue;
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
/*     */ public class TimingsExport
/*     */   extends Thread
/*     */ {
/*     */   private final TimingsReportListener listeners;
/*     */   private final Map out;
/*     */   private final TimingHistory[] history;
/*  69 */   private static long lastReport = 0L;
/*     */   
/*     */   private TimingsExport(TimingsReportListener listeners, Map out, TimingHistory[] history) {
/*  72 */     super("Timings paste thread");
/*  73 */     this.listeners = listeners;
/*  74 */     this.out = out;
/*  75 */     this.history = history;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reportTimings() {
/*     */     Map groupData;
/*  82 */     if (Timings.requestingReport.isEmpty()) {
/*     */       return;
/*     */     }
/*  85 */     TimingsReportListener listeners = new TimingsReportListener(Timings.requestingReport);
/*  86 */     listeners.addConsoleIfNeeded();
/*     */     
/*  88 */     Timings.requestingReport.clear();
/*  89 */     long now = System.currentTimeMillis();
/*  90 */     long lastReportDiff = now - lastReport;
/*  91 */     if (lastReportDiff < 60000L) {
/*  92 */       listeners.sendMessage(ChatColor.RED + "Please wait at least 1 minute in between Timings reports. (" + (int)((60000L - lastReportDiff) / 1000L) + " seconds)");
/*  93 */       listeners.done();
/*     */       return;
/*     */     } 
/*  96 */     long lastStartDiff = now - TimingsManager.timingStart;
/*  97 */     if (lastStartDiff < 180000L) {
/*  98 */       listeners.sendMessage(ChatColor.RED + "Please wait at least 3 minutes before generating a Timings report. Unlike Timings v1, v2 benefits from longer timings and is not as useful with short timings. (" + (int)((180000L - lastStartDiff) / 1000L) + " seconds)");
/*  99 */       listeners.done();
/*     */       return;
/*     */     } 
/* 102 */     listeners.sendMessage(ChatColor.GREEN + "Preparing Timings Report...");
/* 103 */     lastReport = now;
/* 104 */     Map<String, Map> parent = JSONUtil.createObject(new JSONUtil.JSONPair[] {
/*     */           
/* 106 */           JSONUtil.pair("version", Bukkit.getVersion()), 
/* 107 */           JSONUtil.pair("maxplayers", Integer.valueOf(Bukkit.getMaxPlayers())), 
/* 108 */           JSONUtil.pair("start", Long.valueOf(TimingsManager.timingStart / 1000L)), 
/* 109 */           JSONUtil.pair("end", Long.valueOf(System.currentTimeMillis() / 1000L)), 
/* 110 */           JSONUtil.pair("online-mode", Boolean.valueOf(Bukkit.getServer().getOnlineMode())), 
/* 111 */           JSONUtil.pair("sampletime", Long.valueOf((System.currentTimeMillis() - TimingsManager.timingStart) / 1000L)), 
/* 112 */           JSONUtil.pair("datapacks", JSONUtil.toArrayMapper(MinecraftServer.getServer().getResourcePackRepository().e(), pack -> ChatColor.stripColor(CraftChatMessage.fromComponent(pack.a(true)))))
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (!TimingsManager.privacy) {
/* 118 */       JSONUtil.appendObjectData(parent, new JSONUtil.JSONPair[] {
/* 119 */             JSONUtil.pair("server", Bukkit.getUnsafe().getTimingsServerName()), 
/* 120 */             JSONUtil.pair("motd", Bukkit.getServer().getMotd()), 
/* 121 */             JSONUtil.pair("icon", Bukkit.getServer().getServerIcon().getData())
/*     */           });
/*     */     }
/*     */     
/* 125 */     Runtime runtime = Runtime.getRuntime();
/* 126 */     RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
/*     */     
/* 128 */     OperatingSystemMXBean osInfo = ManagementFactory.getOperatingSystemMXBean();
/*     */     
/* 130 */     parent.put("system", JSONUtil.createObject(new JSONUtil.JSONPair[] { 
/* 131 */             JSONUtil.pair("timingcost", Long.valueOf(getCost())), 
/* 132 */             JSONUtil.pair("loadavg", Double.valueOf(osInfo.getSystemLoadAverage())), 
/* 133 */             JSONUtil.pair("name", System.getProperty("os.name")), 
/* 134 */             JSONUtil.pair("version", System.getProperty("os.version")), 
/* 135 */             JSONUtil.pair("jvmversion", System.getProperty("java.version")), 
/* 136 */             JSONUtil.pair("arch", System.getProperty("os.arch")), 
/* 137 */             JSONUtil.pair("maxmem", Long.valueOf(runtime.maxMemory())), 
/* 138 */             JSONUtil.pair("memory", JSONUtil.createObject(new JSONUtil.JSONPair[] {
/* 139 */                   JSONUtil.pair("heap", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().toString()), 
/* 140 */                   JSONUtil.pair("nonheap", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().toString()), 
/* 141 */                   JSONUtil.pair("finalizing", Integer.valueOf(ManagementFactory.getMemoryMXBean().getObjectPendingFinalizationCount()))
/*     */                 
/* 143 */                 })), JSONUtil.pair("cpu", Integer.valueOf(runtime.availableProcessors())), 
/* 144 */             JSONUtil.pair("runtime", Long.valueOf(runtimeBean.getUptime())), 
/* 145 */             JSONUtil.pair("flags", StringUtils.join(runtimeBean.getInputArguments(), " ")), 
/* 146 */             JSONUtil.pair("gc", JSONUtil.toObjectMapper(ManagementFactory.getGarbageCollectorMXBeans(), input -> JSONUtil.pair(input.getName(), JSONUtil.toArray(new Object[] { Long.valueOf(input.getCollectionCount()), Long.valueOf(input.getCollectionTime()) })))) }));
/*     */ 
/*     */ 
/*     */     
/* 150 */     parent.put("worlds", JSONUtil.toObjectMapper(MinecraftServer.getServer().getWorlds(), world -> world.getWorld().getName().equals("worldeditregentempworld") ? null : JSONUtil.pair(world.getWorld().getName(), JSONUtil.createObject(new JSONUtil.JSONPair[] { JSONUtil.pair("gamerules", JSONUtil.toObjectMapper((Object[])world.getWorld().getGameRules(), ())), JSONUtil.pair("ticking-distance", Integer.valueOf((world.getChunkProvider()).playerChunkMap.getEffectiveViewDistance())), JSONUtil.pair("notick-viewdistance", Integer.valueOf((world.getChunkProvider()).playerChunkMap.getEffectiveNoTickViewDistance())) }))));
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
/* 161 */     Set<Material> tileEntityTypeSet = Sets.newHashSet();
/* 162 */     Set<EntityType> entityTypeSet = Sets.newHashSet();
/*     */     
/* 164 */     int size = TimingsManager.HISTORY.size();
/* 165 */     TimingHistory[] history = new TimingHistory[size + 1];
/* 166 */     int i = 0;
/* 167 */     for (TimingHistory timingHistory : TimingsManager.HISTORY) {
/* 168 */       tileEntityTypeSet.addAll(timingHistory.tileEntityTypeSet);
/* 169 */       entityTypeSet.addAll(timingHistory.entityTypeSet);
/* 170 */       history[i++] = timingHistory;
/*     */     } 
/*     */     
/* 173 */     history[i] = new TimingHistory();
/* 174 */     tileEntityTypeSet.addAll((history[i]).tileEntityTypeSet);
/* 175 */     entityTypeSet.addAll((history[i]).entityTypeSet);
/*     */ 
/*     */     
/* 178 */     Map<Integer, List> handlers = JSONUtil.createObject(new JSONUtil.JSONPair[0]);
/*     */     
/* 180 */     synchronized (TimingIdentifier.GROUP_MAP) {
/* 181 */       for (TimingIdentifier.TimingGroup group : TimingIdentifier.GROUP_MAP.values()) {
/* 182 */         synchronized (group.handlers) {
/* 183 */           for (TimingHandler id : group.handlers) {
/*     */             
/* 185 */             if (!id.isTimed() && !id.isSpecial()) {
/*     */               continue;
/*     */             }
/*     */             
/* 189 */             String name = id.identifier.name;
/* 190 */             if (name.startsWith("##")) {
/* 191 */               name = name.substring(3);
/*     */             }
/* 193 */             handlers.put(Integer.valueOf(id.id), JSONUtil.toArray(new Object[] {
/* 194 */                     Integer.valueOf(group.id), name
/*     */                   }));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 201 */       groupData = JSONUtil.toObjectMapper(TimingIdentifier.GROUP_MAP
/* 202 */           .values(), group -> JSONUtil.pair(group.id, group.name));
/*     */     } 
/*     */     
/* 205 */     parent.put("idmap", JSONUtil.createObject(new JSONUtil.JSONPair[] {
/* 206 */             JSONUtil.pair("groups", groupData), 
/* 207 */             JSONUtil.pair("handlers", handlers), 
/* 208 */             JSONUtil.pair("worlds", JSONUtil.toObjectMapper(TimingHistory.worldMap.entrySet(), input -> JSONUtil.pair(((Integer)input.getValue()).intValue(), input.getKey()))), 
/* 209 */             JSONUtil.pair("tileentity", 
/* 210 */               JSONUtil.toObjectMapper(tileEntityTypeSet, input -> JSONUtil.pair(input.ordinal(), input.name()))), 
/* 211 */             JSONUtil.pair("entity", 
/* 212 */               JSONUtil.toObjectMapper(entityTypeSet, input -> JSONUtil.pair(input.ordinal(), input.name())))
/*     */           }));
/*     */ 
/*     */ 
/*     */     
/* 217 */     parent.put("plugins", JSONUtil.toObjectMapper((Object[])Bukkit.getPluginManager().getPlugins(), plugin -> JSONUtil.pair(plugin.getName(), JSONUtil.createObject(new JSONUtil.JSONPair[] { JSONUtil.pair("version", plugin.getDescription().getVersion()), JSONUtil.pair("description", String.valueOf(plugin.getDescription().getDescription()).trim()), JSONUtil.pair("website", plugin.getDescription().getWebsite()), JSONUtil.pair("authors", StringUtils.join(plugin.getDescription().getAuthors(), ", ")) }))));
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
/* 229 */     parent.put("config", JSONUtil.createObject(new JSONUtil.JSONPair[] {
/* 230 */             JSONUtil.pair("spigot", mapAsJSON((ConfigurationSection)Bukkit.spigot().getSpigotConfig(), null)), 
/* 231 */             JSONUtil.pair("bukkit", mapAsJSON((ConfigurationSection)Bukkit.spigot().getBukkitConfig(), null)), 
/* 232 */             JSONUtil.pair("paper", mapAsJSON((ConfigurationSection)Bukkit.spigot().getPaperConfig(), null)), 
/* 233 */             JSONUtil.pair("tuinity", mapAsJSON((ConfigurationSection)Bukkit.spigot().getTuinityConfig(), null))
/*     */           }));
/*     */     
/* 236 */     (new TimingsExport(listeners, parent, history)).start();
/*     */   }
/*     */ 
/*     */   
/*     */   static long getCost() {
/* 241 */     int passes = 100;
/* 242 */     TimingHandler SAMPLER1 = Timings.ofSafe("Timings Sampler 1");
/* 243 */     TimingHandler SAMPLER2 = Timings.ofSafe("Timings Sampler 2");
/* 244 */     TimingHandler SAMPLER3 = Timings.ofSafe("Timings Sampler 3");
/* 245 */     TimingHandler SAMPLER4 = Timings.ofSafe("Timings Sampler 4");
/* 246 */     TimingHandler SAMPLER5 = Timings.ofSafe("Timings Sampler 5");
/* 247 */     TimingHandler SAMPLER6 = Timings.ofSafe("Timings Sampler 6");
/*     */     
/* 249 */     long start = System.nanoTime();
/* 250 */     for (int i = 0; i < passes; i++) {
/* 251 */       SAMPLER1.startTiming();
/* 252 */       SAMPLER2.startTiming();
/* 253 */       SAMPLER3.startTiming();
/* 254 */       SAMPLER3.stopTiming();
/* 255 */       SAMPLER4.startTiming();
/* 256 */       SAMPLER5.startTiming();
/* 257 */       SAMPLER6.startTiming();
/* 258 */       SAMPLER6.stopTiming();
/* 259 */       SAMPLER5.stopTiming();
/* 260 */       SAMPLER4.stopTiming();
/* 261 */       SAMPLER2.stopTiming();
/* 262 */       SAMPLER1.stopTiming();
/*     */     } 
/* 264 */     long timingsCost = (System.nanoTime() - start) / passes / 6L;
/* 265 */     SAMPLER1.reset(true);
/* 266 */     SAMPLER2.reset(true);
/* 267 */     SAMPLER3.reset(true);
/* 268 */     SAMPLER4.reset(true);
/* 269 */     SAMPLER5.reset(true);
/* 270 */     SAMPLER6.reset(true);
/* 271 */     return timingsCost;
/*     */   }
/*     */ 
/*     */   
/*     */   private static JSONObject mapAsJSON(ConfigurationSection config, String parentKey) {
/* 276 */     JSONObject object = new JSONObject();
/* 277 */     for (String key : config.getKeys(false)) {
/* 278 */       String fullKey = (parentKey != null) ? (parentKey + "." + key) : key;
/* 279 */       if (fullKey.equals("database") || fullKey.equals("settings.bungeecord-addresses") || TimingsManager.hiddenConfigs.contains(fullKey) || key.startsWith("seed-") || key.equals("worldeditregentempworld")) {
/*     */         continue;
/*     */       }
/* 282 */       Object val = config.get(key);
/*     */       
/* 284 */       object.put(key, valAsJSON(val, fullKey));
/*     */     } 
/* 286 */     return object;
/*     */   }
/*     */   
/*     */   private static Object valAsJSON(Object val, String parentKey) {
/* 290 */     if (!(val instanceof org.bukkit.configuration.MemorySection)) {
/* 291 */       if (val instanceof List) {
/* 292 */         Iterable<Object> v = (Iterable<Object>)val;
/* 293 */         return JSONUtil.toArrayMapper(v, input -> valAsJSON(input, parentKey));
/*     */       } 
/* 295 */       return String.valueOf(val);
/*     */     } 
/*     */     
/* 298 */     return mapAsJSON((ConfigurationSection)val, parentKey);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 304 */     this.out.put("data", JSONUtil.toArrayMapper((Object[])this.history, TimingHistory::export));
/*     */ 
/*     */     
/* 307 */     String response = null;
/* 308 */     String timingsURL = null;
/*     */     try {
/* 310 */       HttpURLConnection con = (HttpURLConnection)(new URL("http://timings.aikar.co/post")).openConnection();
/* 311 */       con.setDoOutput(true);
/* 312 */       String hostName = "BrokenHost";
/*     */       try {
/* 314 */         hostName = InetAddress.getLocalHost().getHostName();
/* 315 */       } catch (Exception exception) {}
/* 316 */       con.setRequestProperty("User-Agent", "Paper/" + Bukkit.getUnsafe().getTimingsServerName() + "/" + hostName);
/* 317 */       con.setRequestMethod("POST");
/* 318 */       con.setInstanceFollowRedirects(false);
/*     */       
/* 320 */       OutputStream request = new GZIPOutputStream(con.getOutputStream())
/*     */         {
/*     */         
/*     */         };
/* 324 */       request.write(JSONValue.toJSONString(this.out).getBytes("UTF-8"));
/* 325 */       request.close();
/*     */       
/* 327 */       response = getResponse(con);
/*     */       
/* 329 */       if (con.getResponseCode() != 302) {
/* 330 */         this.listeners.sendMessage(ChatColor.RED + "Upload Error: " + con
/* 331 */             .getResponseCode() + ": " + con.getResponseMessage());
/* 332 */         this.listeners.sendMessage(ChatColor.RED + "Check your logs for more information");
/* 333 */         if (response != null) {
/* 334 */           Bukkit.getLogger().log(Level.SEVERE, response);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/* 339 */       timingsURL = con.getHeaderField("Location");
/* 340 */       this.listeners.sendMessage(ChatColor.GREEN + "View Timings Report: " + timingsURL);
/*     */       
/* 342 */       if (response != null && !response.isEmpty()) {
/* 343 */         Bukkit.getLogger().log(Level.INFO, "Timing Response: " + response);
/*     */       }
/* 345 */     } catch (IOException ex) {
/* 346 */       this.listeners.sendMessage(ChatColor.RED + "Error uploading timings, check your logs for more information");
/* 347 */       if (response != null) {
/* 348 */         Bukkit.getLogger().log(Level.SEVERE, response);
/*     */       }
/* 350 */       Bukkit.getLogger().log(Level.SEVERE, "Could not paste timings", ex);
/*     */     } finally {
/* 352 */       this.listeners.done(timingsURL);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getResponse(HttpURLConnection con) throws IOException {
/* 357 */     InputStream is = null;
/*     */     try {
/* 359 */       is = con.getInputStream();
/* 360 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*     */       
/* 362 */       byte[] b = new byte[1024];
/*     */       int bytesRead;
/* 364 */       while ((bytesRead = is.read(b)) != -1) {
/* 365 */         bos.write(b, 0, bytesRead);
/*     */       }
/* 367 */       return bos.toString();
/*     */     }
/* 369 */     catch (IOException ex) {
/* 370 */       this.listeners.sendMessage(ChatColor.RED + "Error uploading timings, check your logs for more information");
/* 371 */       Bukkit.getLogger().log(Level.WARNING, con.getResponseMessage(), ex);
/* 372 */       return null;
/*     */     } finally {
/* 374 */       if (is != null)
/* 375 */         is.close(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingsExport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */