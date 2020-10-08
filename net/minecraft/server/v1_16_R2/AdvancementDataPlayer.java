/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.internal.Streams;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.StringReader;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerAdvancementDoneEvent;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class AdvancementDataPlayer {
/*  38 */   private static final Logger LOGGER = LogManager.getLogger();
/*  39 */   private static final Gson b = (new GsonBuilder()).registerTypeAdapter(AdvancementProgress.class, new AdvancementProgress.a()).registerTypeAdapter(MinecraftKey.class, new MinecraftKey.a()).setPrettyPrinting().create();
/*  40 */   private static final TypeToken<Map<MinecraftKey, AdvancementProgress>> c = new TypeToken<Map<MinecraftKey, AdvancementProgress>>() {  }
/*     */   ;
/*     */   private final DataFixer d;
/*     */   private final PlayerList e;
/*     */   private final File f;
/*  45 */   public final Map<Advancement, AdvancementProgress> data = Maps.newLinkedHashMap();
/*  46 */   private final Set<Advancement> h = Sets.newLinkedHashSet();
/*  47 */   private final Set<Advancement> i = Sets.newLinkedHashSet();
/*  48 */   private final Set<Advancement> j = Sets.newLinkedHashSet();
/*     */   
/*     */   private EntityPlayer player;
/*     */   
/*     */   @Nullable
/*     */   private Advancement l;
/*     */   private boolean m = true;
/*  55 */   final Map<CriterionTriggerAbstract, Set<CriterionTrigger.a>> criterionData = Maps.newIdentityHashMap();
/*     */ 
/*     */   
/*     */   public AdvancementDataPlayer(DataFixer datafixer, PlayerList playerlist, AdvancementDataWorld advancementdataworld, File file, EntityPlayer entityplayer) {
/*  59 */     this.d = datafixer;
/*  60 */     this.e = playerlist;
/*  61 */     this.f = file;
/*  62 */     this.player = entityplayer;
/*  63 */     d(advancementdataworld);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer entityplayer) {
/*  67 */     this.player = entityplayer;
/*     */   }
/*     */   
/*     */   public void a() {
/*  71 */     Iterator<? extends CriterionTrigger<?>> iterator = CriterionTriggers.a().iterator();
/*     */     
/*  73 */     while (iterator.hasNext()) {
/*  74 */       CriterionTrigger<?> criteriontrigger = iterator.next();
/*     */       
/*  76 */       criteriontrigger.a(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataWorld advancementdataworld) {
/*  82 */     a();
/*  83 */     this.data.clear();
/*  84 */     this.h.clear();
/*  85 */     this.i.clear();
/*  86 */     this.j.clear();
/*  87 */     this.m = true;
/*  88 */     this.l = null;
/*  89 */     d(advancementdataworld);
/*     */   }
/*     */   
/*     */   private void b(AdvancementDataWorld advancementdataworld) {
/*  93 */     Iterator<Advancement> iterator = advancementdataworld.getAdvancements().iterator();
/*     */     
/*  95 */     while (iterator.hasNext()) {
/*  96 */       Advancement advancement = iterator.next();
/*     */       
/*  98 */       c(advancement);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c() {
/* 104 */     List<Advancement> list = Lists.newArrayList();
/* 105 */     Iterator<Map.Entry<Advancement, AdvancementProgress>> iterator = this.data.entrySet().iterator();
/*     */     
/* 107 */     while (iterator.hasNext()) {
/* 108 */       Map.Entry<Advancement, AdvancementProgress> entry = iterator.next();
/*     */       
/* 110 */       if (((AdvancementProgress)entry.getValue()).isDone()) {
/* 111 */         list.add(entry.getKey());
/* 112 */         this.j.add(entry.getKey());
/*     */       } 
/*     */     } 
/*     */     
/* 116 */     iterator = (Iterator)list.iterator();
/*     */     
/* 118 */     while (iterator.hasNext()) {
/* 119 */       Advancement advancement = (Advancement)iterator.next();
/*     */       
/* 121 */       e(advancement);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(AdvancementDataWorld advancementdataworld) {
/* 127 */     Iterator<Advancement> iterator = advancementdataworld.getAdvancements().iterator();
/*     */     
/* 129 */     while (iterator.hasNext()) {
/* 130 */       Advancement advancement = iterator.next();
/*     */       
/* 132 */       if (advancement.getCriteria().isEmpty()) {
/* 133 */         grantCriteria(advancement, "");
/* 134 */         advancement.d().a(this.player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void d(AdvancementDataWorld advancementdataworld) {
/* 141 */     if (this.f.isFile()) {
/*     */       try {
/* 143 */         JsonReader jsonreader = new JsonReader(new StringReader(Files.toString(this.f, StandardCharsets.UTF_8)));
/* 144 */         Throwable throwable = null;
/*     */         
/*     */         try {
/* 147 */           jsonreader.setLenient(false);
/* 148 */           Dynamic<JsonElement> dynamic = new Dynamic((DynamicOps)JsonOps.INSTANCE, Streams.parse(jsonreader));
/*     */           
/* 150 */           if (!dynamic.get("DataVersion").asNumber().result().isPresent()) {
/* 151 */             dynamic = dynamic.set("DataVersion", dynamic.createInt(1343));
/*     */           }
/*     */           
/* 154 */           dynamic = this.d.update(DataFixTypes.ADVANCEMENTS.a(), dynamic, dynamic.get("DataVersion").asInt(0), SharedConstants.getGameVersion().getWorldVersion());
/* 155 */           dynamic = dynamic.remove("DataVersion");
/* 156 */           Map<MinecraftKey, AdvancementProgress> map = (Map<MinecraftKey, AdvancementProgress>)b.getAdapter(c).fromJsonTree((JsonElement)dynamic.getValue());
/*     */           
/* 158 */           if (map == null) {
/* 159 */             throw new JsonParseException("Found null for advancements");
/*     */           }
/*     */           
/* 162 */           Stream<Map.Entry<MinecraftKey, AdvancementProgress>> stream = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue));
/* 163 */           Iterator<Map.Entry<MinecraftKey, AdvancementProgress>> iterator = ((List)stream.collect((Collector)Collectors.toList())).iterator();
/*     */           
/* 165 */           while (iterator.hasNext()) {
/* 166 */             Map.Entry<MinecraftKey, AdvancementProgress> entry = iterator.next();
/* 167 */             Advancement advancement = advancementdataworld.a(entry.getKey());
/*     */             
/* 169 */             if (advancement == null) {
/*     */               
/* 171 */               if (((MinecraftKey)entry.getKey()).getNamespace().equals("minecraft")) {
/* 172 */                 LOGGER.warn("Ignored advancement '{}' in progress file {} - it doesn't exist anymore?", entry.getKey(), this.f);
/*     */               }
/*     */               continue;
/*     */             } 
/* 176 */             a(advancement, entry.getValue());
/*     */           }
/*     */         
/* 179 */         } catch (Throwable throwable1) {
/* 180 */           throwable = throwable1;
/* 181 */           throw throwable1;
/*     */         } finally {
/* 183 */           if (jsonreader != null) {
/* 184 */             if (throwable != null) {
/*     */               try {
/* 186 */                 jsonreader.close();
/* 187 */               } catch (Throwable throwable2) {
/* 188 */                 throwable.addSuppressed(throwable2);
/*     */               } 
/*     */             } else {
/* 191 */               jsonreader.close();
/*     */             }
/*     */           
/*     */           }
/*     */         } 
/* 196 */       } catch (JsonParseException jsonparseexception) {
/* 197 */         LOGGER.error("Couldn't parse player advancements in {}", this.f, jsonparseexception);
/* 198 */       } catch (IOException ioexception) {
/* 199 */         LOGGER.error("Couldn't access player advancements in {}", this.f, ioexception);
/*     */       } 
/*     */     }
/*     */     
/* 203 */     c(advancementdataworld);
/* 204 */     c();
/* 205 */     b(advancementdataworld);
/*     */   }
/*     */   
/*     */   public void b() {
/* 209 */     if (SpigotConfig.disableAdvancementSaving)
/* 210 */       return;  Map<MinecraftKey, AdvancementProgress> map = Maps.newHashMap();
/* 211 */     Iterator<Map.Entry<Advancement, AdvancementProgress>> iterator = this.data.entrySet().iterator();
/*     */     
/* 213 */     while (iterator.hasNext()) {
/* 214 */       Map.Entry<Advancement, AdvancementProgress> entry = iterator.next();
/* 215 */       AdvancementProgress advancementprogress = entry.getValue();
/*     */       
/* 217 */       if (advancementprogress.b()) {
/* 218 */         map.put(((Advancement)entry.getKey()).getName(), advancementprogress);
/*     */       }
/*     */     } 
/*     */     
/* 222 */     if (this.f.getParentFile() != null) {
/* 223 */       this.f.getParentFile().mkdirs();
/*     */     }
/*     */     
/* 226 */     JsonElement jsonelement = b.toJsonTree(map);
/*     */     
/* 228 */     jsonelement.getAsJsonObject().addProperty("DataVersion", Integer.valueOf(SharedConstants.getGameVersion().getWorldVersion()));
/*     */     
/*     */     try {
/* 231 */       FileOutputStream fileoutputstream = new FileOutputStream(this.f);
/* 232 */       Throwable throwable = null;
/*     */       
/*     */       try {
/* 235 */         OutputStreamWriter outputstreamwriter = new OutputStreamWriter(fileoutputstream, Charsets.UTF_8.newEncoder());
/* 236 */         Throwable throwable1 = null;
/*     */         
/*     */         try {
/* 239 */           b.toJson(jsonelement, outputstreamwriter);
/* 240 */         } catch (Throwable throwable2) {
/* 241 */           throwable1 = throwable2;
/* 242 */           throw throwable2;
/*     */         } finally {
/* 244 */           if (outputstreamwriter != null) {
/* 245 */             if (throwable1 != null) {
/*     */               try {
/* 247 */                 outputstreamwriter.close();
/* 248 */               } catch (Throwable throwable3) {
/* 249 */                 throwable1.addSuppressed(throwable3);
/*     */               } 
/*     */             } else {
/* 252 */               outputstreamwriter.close();
/*     */             }
/*     */           
/*     */           }
/*     */         } 
/* 257 */       } catch (Throwable throwable4) {
/* 258 */         throwable = throwable4;
/* 259 */         throw throwable4;
/*     */       } finally {
/* 261 */         if (fileoutputstream != null) {
/* 262 */           if (throwable != null) {
/*     */             try {
/* 264 */               fileoutputstream.close();
/* 265 */             } catch (Throwable throwable5) {
/* 266 */               throwable.addSuppressed(throwable5);
/*     */             } 
/*     */           } else {
/* 269 */             fileoutputstream.close();
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/* 274 */     } catch (IOException ioexception) {
/* 275 */       LOGGER.error("Couldn't save player advancements to {}", this.f, ioexception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean grantCriteria(Advancement advancement, String s) {
/* 281 */     boolean flag = false;
/* 282 */     AdvancementProgress advancementprogress = getProgress(advancement);
/* 283 */     boolean flag1 = advancementprogress.isDone();
/*     */     
/* 285 */     if (advancementprogress.a(s)) {
/*     */       
/* 287 */       if (!(new PlayerAdvancementCriterionGrantEvent((Player)this.player.getBukkitEntity(), advancement.bukkit, s)).callEvent()) {
/* 288 */         advancementprogress.b(s);
/* 289 */         return false;
/*     */       } 
/*     */       
/* 292 */       d(advancement);
/* 293 */       this.j.add(advancement);
/* 294 */       flag = true;
/* 295 */       if (!flag1 && advancementprogress.isDone()) {
/* 296 */         this.player.world.getServer().getPluginManager().callEvent((Event)new PlayerAdvancementDoneEvent((Player)this.player.getBukkitEntity(), advancement.bukkit));
/* 297 */         advancement.d().a(this.player);
/* 298 */         if (advancement.c() != null && advancement.c().i() && this.player.world.getGameRules().getBoolean(GameRules.ANNOUNCE_ADVANCEMENTS)) {
/* 299 */           this.e.sendMessage(new ChatMessage("chat.type.advancement." + advancement.c().e().a(), new Object[] { this.player.getScoreboardDisplayName(), advancement.j() }), ChatMessageType.SYSTEM, SystemUtils.b);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 304 */     if (advancementprogress.isDone()) {
/* 305 */       e(advancement);
/*     */     }
/*     */     
/* 308 */     return flag;
/*     */   }
/*     */   
/*     */   public boolean revokeCritera(Advancement advancement, String s) {
/* 312 */     boolean flag = false;
/* 313 */     AdvancementProgress advancementprogress = getProgress(advancement);
/*     */     
/* 315 */     if (advancementprogress.b(s)) {
/* 316 */       c(advancement);
/* 317 */       this.j.add(advancement);
/* 318 */       flag = true;
/*     */     } 
/*     */     
/* 321 */     if (!advancementprogress.b()) {
/* 322 */       e(advancement);
/*     */     }
/*     */     
/* 325 */     return flag;
/*     */   }
/*     */   
/*     */   private void c(Advancement advancement) {
/* 329 */     AdvancementProgress advancementprogress = getProgress(advancement);
/*     */     
/* 331 */     if (!advancementprogress.isDone()) {
/* 332 */       Iterator<Map.Entry<String, Criterion>> iterator = advancement.getCriteria().entrySet().iterator();
/*     */       
/* 334 */       while (iterator.hasNext()) {
/* 335 */         Map.Entry<String, Criterion> entry = iterator.next();
/* 336 */         CriterionProgress criterionprogress = advancementprogress.getCriterionProgress(entry.getKey());
/*     */         
/* 338 */         if (criterionprogress != null && !criterionprogress.a()) {
/* 339 */           CriterionInstance criterioninstance = ((Criterion)entry.getValue()).a();
/*     */           
/* 341 */           if (criterioninstance != null) {
/* 342 */             CriterionTrigger<CriterionInstance> criteriontrigger = CriterionTriggers.a(criterioninstance.a());
/*     */             
/* 344 */             if (criteriontrigger != null) {
/* 345 */               criteriontrigger.a(this, new CriterionTrigger.a<>(criterioninstance, advancement, entry.getKey()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void d(Advancement advancement) {
/* 355 */     AdvancementProgress advancementprogress = getProgress(advancement);
/* 356 */     Iterator<Map.Entry<String, Criterion>> iterator = advancement.getCriteria().entrySet().iterator();
/*     */     
/* 358 */     while (iterator.hasNext()) {
/* 359 */       Map.Entry<String, Criterion> entry = iterator.next();
/* 360 */       CriterionProgress criterionprogress = advancementprogress.getCriterionProgress(entry.getKey());
/*     */       
/* 362 */       if (criterionprogress != null && (criterionprogress.a() || advancementprogress.isDone())) {
/* 363 */         CriterionInstance criterioninstance = ((Criterion)entry.getValue()).a();
/*     */         
/* 365 */         if (criterioninstance != null) {
/* 366 */           CriterionTrigger<CriterionInstance> criteriontrigger = CriterionTriggers.a(criterioninstance.a());
/*     */           
/* 368 */           if (criteriontrigger != null) {
/* 369 */             criteriontrigger.b(this, new CriterionTrigger.a<>(criterioninstance, advancement, entry.getKey()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityPlayer entityplayer) {
/* 378 */     if (this.m || !this.i.isEmpty() || !this.j.isEmpty()) {
/* 379 */       Map<MinecraftKey, AdvancementProgress> map = Maps.newHashMap();
/* 380 */       Set<Advancement> set = Sets.newLinkedHashSet();
/* 381 */       Set<MinecraftKey> set1 = Sets.newLinkedHashSet();
/* 382 */       Iterator<Advancement> iterator = this.j.iterator();
/*     */ 
/*     */ 
/*     */       
/* 386 */       while (iterator.hasNext()) {
/* 387 */         Advancement advancement = iterator.next();
/* 388 */         if (this.h.contains(advancement)) {
/* 389 */           map.put(advancement.getName(), this.data.get(advancement));
/*     */         }
/*     */       } 
/*     */       
/* 393 */       iterator = this.i.iterator();
/*     */       
/* 395 */       while (iterator.hasNext()) {
/* 396 */         Advancement advancement = iterator.next();
/* 397 */         if (this.h.contains(advancement)) {
/* 398 */           set.add(advancement); continue;
/*     */         } 
/* 400 */         set1.add(advancement.getName());
/*     */       } 
/*     */ 
/*     */       
/* 404 */       if (this.m || !map.isEmpty() || !set.isEmpty() || !set1.isEmpty()) {
/* 405 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutAdvancements(this.m, set, set1, map));
/* 406 */         this.i.clear();
/* 407 */         this.j.clear();
/*     */       } 
/*     */     } 
/*     */     
/* 411 */     this.m = false;
/*     */   }
/*     */   
/*     */   public void a(@Nullable Advancement advancement) {
/* 415 */     Advancement advancement1 = this.l;
/*     */     
/* 417 */     if (advancement != null && advancement.b() == null && advancement.c() != null) {
/* 418 */       this.l = advancement;
/*     */     } else {
/* 420 */       this.l = null;
/*     */     } 
/*     */     
/* 423 */     if (advancement1 != this.l) {
/* 424 */       this.player.playerConnection.sendPacket(new PacketPlayOutSelectAdvancementTab((this.l == null) ? null : this.l.getName()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AdvancementProgress getProgress(Advancement advancement) {
/* 430 */     AdvancementProgress advancementprogress = this.data.get(advancement);
/*     */     
/* 432 */     if (advancementprogress == null) {
/* 433 */       advancementprogress = new AdvancementProgress();
/* 434 */       a(advancement, advancementprogress);
/*     */     } 
/*     */     
/* 437 */     return advancementprogress;
/*     */   }
/*     */   
/*     */   private void a(Advancement advancement, AdvancementProgress advancementprogress) {
/* 441 */     advancementprogress.a(advancement.getCriteria(), advancement.i());
/* 442 */     this.data.put(advancement, advancementprogress);
/*     */   }
/*     */ 
/*     */   
/*     */   private void e(Advancement advancement) {
/* 447 */     e(advancement, IterationEntryPoint.ROOT);
/*     */   }
/*     */   
/* 450 */   private enum IterationEntryPoint { ROOT,
/* 451 */     ITERATOR,
/* 452 */     PARENT_OF_ITERATOR; }
/*     */ 
/*     */   
/*     */   private void e(Advancement advancement, IterationEntryPoint entryPoint) {
/* 456 */     boolean flag = f(advancement);
/* 457 */     boolean flag1 = this.h.contains(advancement);
/*     */     
/* 459 */     if (flag && !flag1) {
/* 460 */       this.h.add(advancement);
/* 461 */       this.i.add(advancement);
/* 462 */       if (this.data.containsKey(advancement)) {
/* 463 */         this.j.add(advancement);
/*     */       }
/* 465 */     } else if (!flag && flag1) {
/* 466 */       this.h.remove(advancement);
/* 467 */       this.i.add(advancement);
/*     */     } 
/*     */     
/* 470 */     if (flag != flag1 && advancement.b() != null)
/*     */     {
/*     */       
/* 473 */       e(advancement.b(), (entryPoint == IterationEntryPoint.ITERATOR) ? IterationEntryPoint.PARENT_OF_ITERATOR : IterationEntryPoint.ROOT);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 478 */     if (entryPoint == IterationEntryPoint.PARENT_OF_ITERATOR) {
/*     */       return;
/*     */     }
/*     */     
/* 482 */     Iterator<Advancement> iterator = advancement.e().iterator();
/*     */     
/* 484 */     while (iterator.hasNext()) {
/* 485 */       Advancement advancement1 = iterator.next();
/*     */       
/* 487 */       e(advancement1, IterationEntryPoint.ITERATOR);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean f(Advancement advancement) {
/* 493 */     for (int i = 0; advancement != null && i <= 2; i++) {
/* 494 */       if (i == 0 && g(advancement)) {
/* 495 */         return true;
/*     */       }
/*     */       
/* 498 */       if (advancement.c() == null) {
/* 499 */         return false;
/*     */       }
/*     */       
/* 502 */       AdvancementProgress advancementprogress = getProgress(advancement);
/*     */       
/* 504 */       if (advancementprogress.isDone()) {
/* 505 */         return true;
/*     */       }
/*     */       
/* 508 */       if (advancement.c().j()) {
/* 509 */         return false;
/*     */       }
/*     */       
/* 512 */       advancement = advancement.b();
/*     */     } 
/*     */     
/* 515 */     return false;
/*     */   }
/*     */   private boolean g(Advancement advancement) {
/*     */     Advancement advancement1;
/* 519 */     AdvancementProgress advancementprogress = getProgress(advancement);
/*     */     
/* 521 */     if (advancementprogress.isDone()) {
/* 522 */       return true;
/*     */     }
/* 524 */     Iterator<Advancement> iterator = advancement.e().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 529 */       if (!iterator.hasNext()) {
/* 530 */         return false;
/*     */       }
/*     */       
/* 533 */       advancement1 = iterator.next();
/* 534 */     } while (!g(advancement1));
/*     */     
/* 536 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AdvancementDataPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */