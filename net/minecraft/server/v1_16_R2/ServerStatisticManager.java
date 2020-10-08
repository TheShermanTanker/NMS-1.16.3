/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.internal.Streams;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class ServerStatisticManager extends StatisticManager {
/*  28 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final MinecraftServer c;
/*     */   private final File d;
/*  31 */   private final Set<Statistic<?>> e = Sets.newHashSet();
/*  32 */   private int f = -300;
/*     */   
/*     */   public ServerStatisticManager(MinecraftServer minecraftserver, File file) {
/*  35 */     this.c = minecraftserver;
/*  36 */     this.d = file;
/*     */     
/*  38 */     for (Map.Entry<MinecraftKey, Integer> entry : (Iterable<Map.Entry<MinecraftKey, Integer>>)SpigotConfig.forcedStats.entrySet()) {
/*     */       
/*  40 */       Statistic<MinecraftKey> wrapper = StatisticList.CUSTOM.b(entry.getKey());
/*  41 */       this.a.put(wrapper, ((Integer)entry.getValue()).intValue());
/*     */     } 
/*     */     
/*  44 */     if (file.isFile()) {
/*     */       try {
/*  46 */         a(minecraftserver.getDataFixer(), FileUtils.readFileToString(file));
/*  47 */       } catch (IOException ioexception) {
/*  48 */         LOGGER.error("Couldn't read statistics file {}", file, ioexception);
/*  49 */       } catch (JsonParseException jsonparseexception) {
/*  50 */         LOGGER.error("Couldn't parse statistics file {}", file, jsonparseexception);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/*  57 */     if (SpigotConfig.disableStatSaving)
/*     */       return;  try {
/*  59 */       FileUtils.writeStringToFile(this.d, b());
/*  60 */     } catch (IOException ioexception) {
/*  61 */       LOGGER.error("Couldn't save stats", ioexception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatistic(EntityHuman entityhuman, Statistic<?> statistic, int i) {
/*  68 */     if (SpigotConfig.disableStatSaving)
/*  69 */       return;  super.setStatistic(entityhuman, statistic, i);
/*  70 */     this.e.add(statistic);
/*     */   }
/*     */   
/*     */   private Set<Statistic<?>> d() {
/*  74 */     Set<Statistic<?>> set = Sets.newHashSet(this.e);
/*     */     
/*  76 */     this.e.clear();
/*  77 */     return set;
/*     */   }
/*     */   
/*     */   public void a(DataFixer datafixer, String s) {
/*     */     try {
/*  82 */       JsonReader jsonreader = new JsonReader(new StringReader(s));
/*  83 */       Throwable throwable = null;
/*     */       
/*     */       try {
/*  86 */         jsonreader.setLenient(false);
/*  87 */         JsonElement jsonelement = Streams.parse(jsonreader);
/*     */         
/*  89 */         if (!jsonelement.isJsonNull()) {
/*  90 */           NBTTagCompound nbttagcompound = a(jsonelement.getAsJsonObject());
/*     */           
/*  92 */           if (!nbttagcompound.hasKeyOfType("DataVersion", 99)) {
/*  93 */             nbttagcompound.setInt("DataVersion", 1343);
/*     */           }
/*     */           
/*  96 */           nbttagcompound = GameProfileSerializer.a(datafixer, DataFixTypes.STATS, nbttagcompound, nbttagcompound.getInt("DataVersion"));
/*  97 */           if (nbttagcompound.hasKeyOfType("stats", 10)) {
/*  98 */             NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("stats");
/*  99 */             Iterator<String> iterator = nbttagcompound1.getKeys().iterator();
/*     */             
/* 101 */             while (iterator.hasNext()) {
/* 102 */               String s1 = iterator.next();
/*     */               
/* 104 */               if (nbttagcompound1.hasKeyOfType(s1, 10)) {
/* 105 */                 SystemUtils.a(IRegistry.STATS.getOptional(new MinecraftKey(s1)), statisticwrapper -> {
/*     */                       NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompound(s1);
/*     */ 
/*     */                       
/*     */                       Iterator<String> iterator1 = nbttagcompound2.getKeys().iterator();
/*     */ 
/*     */                       
/*     */                       while (iterator1.hasNext()) {
/*     */                         String s2 = iterator1.next();
/*     */ 
/*     */                         
/*     */                         if (nbttagcompound2.hasKeyOfType(s2, 99)) {
/*     */                           SystemUtils.a(a(statisticwrapper, s2), (), ());
/*     */ 
/*     */                           
/*     */                           continue;
/*     */                         } 
/*     */                         
/*     */                         LOGGER.warn("Invalid statistic value in {}: Don't know what {} is for key {}", this.d, nbttagcompound2.get(s2), s2);
/*     */                       } 
/*     */                     }() -> LOGGER.warn("Invalid statistic type in {}: Don't know what {} is", this.d, s1));
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 133 */         LOGGER.error("Unable to parse Stat data from {}", this.d);
/* 134 */       } catch (Throwable throwable1) {
/* 135 */         throwable = throwable1;
/* 136 */         throw throwable1;
/*     */       } finally {
/* 138 */         if (jsonreader != null) {
/* 139 */           if (throwable != null) {
/*     */             try {
/* 141 */               jsonreader.close();
/* 142 */             } catch (Throwable throwable2) {
/* 143 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/* 146 */             jsonreader.close();
/*     */           }
/*     */         
/*     */         }
/*     */       }
/*     */     
/* 152 */     } catch (IOException|JsonParseException jsonparseexception) {
/* 153 */       LOGGER.error("Unable to parse Stat data from {}", this.d, jsonparseexception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private <T> Optional<Statistic<T>> a(StatisticWrapper<T> statisticwrapper, String s) {
/* 158 */     Optional<MinecraftKey> optional = Optional.ofNullable(MinecraftKey.a(s));
/* 159 */     IRegistry<T> iregistry = statisticwrapper.getRegistry();
/*     */     
/* 161 */     iregistry.getClass();
/* 162 */     Objects.requireNonNull(iregistry); Optional<T> optional2 = optional.flatMap(iregistry::getOptional);
/* 163 */     statisticwrapper.getClass();
/* 164 */     Objects.requireNonNull(statisticwrapper); return optional2.map(statisticwrapper::b);
/*     */   }
/*     */   
/*     */   private static NBTTagCompound a(JsonObject jsonobject) {
/* 168 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 169 */     Iterator<Map.Entry<String, JsonElement>> iterator = jsonobject.entrySet().iterator();
/*     */     
/* 171 */     while (iterator.hasNext()) {
/* 172 */       Map.Entry<String, JsonElement> entry = iterator.next();
/* 173 */       JsonElement jsonelement = entry.getValue();
/*     */       
/* 175 */       if (jsonelement.isJsonObject()) {
/* 176 */         nbttagcompound.set(entry.getKey(), a(jsonelement.getAsJsonObject())); continue;
/* 177 */       }  if (jsonelement.isJsonPrimitive()) {
/* 178 */         JsonPrimitive jsonprimitive = jsonelement.getAsJsonPrimitive();
/*     */         
/* 180 */         if (jsonprimitive.isNumber()) {
/* 181 */           nbttagcompound.setInt(entry.getKey(), jsonprimitive.getAsInt());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   protected String b() {
/* 190 */     Map<StatisticWrapper<?>, JsonObject> map = Maps.newHashMap();
/* 191 */     ObjectIterator objectiterator = this.a.object2IntEntrySet().iterator();
/*     */     
/* 193 */     while (objectiterator.hasNext()) {
/* 194 */       Object2IntMap.Entry<Statistic<?>> it_unimi_dsi_fastutil_objects_object2intmap_entry = (Object2IntMap.Entry<Statistic<?>>)objectiterator.next();
/* 195 */       Statistic<?> statistic = (Statistic)it_unimi_dsi_fastutil_objects_object2intmap_entry.getKey();
/*     */       
/* 197 */       ((JsonObject)map.computeIfAbsent(statistic.getWrapper(), statisticwrapper -> new JsonObject()))
/*     */         
/* 199 */         .addProperty(b(statistic).toString(), Integer.valueOf(it_unimi_dsi_fastutil_objects_object2intmap_entry.getIntValue()));
/*     */     } 
/*     */     
/* 202 */     JsonObject jsonobject = new JsonObject();
/* 203 */     Iterator<Map.Entry<StatisticWrapper<?>, JsonObject>> iterator = map.entrySet().iterator();
/*     */     
/* 205 */     while (iterator.hasNext()) {
/* 206 */       Map.Entry<StatisticWrapper<?>, JsonObject> entry = iterator.next();
/*     */       
/* 208 */       jsonobject.add(IRegistry.STATS.getKey(entry.getKey()).toString(), (JsonElement)entry.getValue());
/*     */     } 
/*     */     
/* 211 */     JsonObject jsonobject1 = new JsonObject();
/*     */     
/* 213 */     jsonobject1.add("stats", (JsonElement)jsonobject);
/* 214 */     jsonobject1.addProperty("DataVersion", Integer.valueOf(SharedConstants.getGameVersion().getWorldVersion()));
/* 215 */     return jsonobject1.toString();
/*     */   }
/*     */   
/*     */   private static <T> MinecraftKey b(Statistic<T> statistic) {
/* 219 */     return statistic.getWrapper().getRegistry().getKey(statistic.b());
/*     */   }
/*     */   
/*     */   public void c() {
/* 223 */     this.e.addAll((Collection<? extends Statistic<?>>)this.a.keySet());
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer entityplayer) {
/* 227 */     int i = this.c.ah();
/* 228 */     Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap();
/*     */     
/* 230 */     if (i - this.f > 300) {
/* 231 */       this.f = i;
/* 232 */       Iterator<Statistic<?>> iterator = d().iterator();
/*     */       
/* 234 */       while (iterator.hasNext()) {
/* 235 */         Statistic<?> statistic = iterator.next();
/*     */         
/* 237 */         object2IntOpenHashMap.put(statistic, getStatisticValue(statistic));
/*     */       } 
/*     */     } 
/*     */     
/* 241 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutStatistic((Object2IntMap<Statistic<?>>)object2IntOpenHashMap));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ServerStatisticManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */