/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.mojang.authlib.Agent;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.GameProfileRepository;
/*     */ import com.mojang.authlib.ProfileLookupCallback;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class UserCache {
/*  43 */   private static final Logger LOGGER = LogManager.getLogger(); private static boolean b;
/*     */   private final Map<String, UserCacheEntry> c;
/*  45 */   private final Map<String, UserCacheEntry> nameCache = this.c = Maps.newConcurrentMap();
/*  46 */   private final Map<UUID, UserCacheEntry> d = Maps.newConcurrentMap();
/*     */   private final GameProfileRepository e;
/*  48 */   private final Gson f = (new GsonBuilder()).create();
/*     */   private final File g;
/*  50 */   private final AtomicLong h = new AtomicLong();
/*     */ 
/*     */   
/*  53 */   protected final ReentrantLock stateLock = new ReentrantLock();
/*  54 */   protected final ReentrantLock lookupLock = new ReentrantLock();
/*     */ 
/*     */   
/*     */   public UserCache(GameProfileRepository gameprofilerepository, File file) {
/*  58 */     this.e = gameprofilerepository;
/*  59 */     this.g = file;
/*  60 */     Lists.reverse(a()).forEach(this::a);
/*     */   }
/*     */   private void a(UserCacheEntry usercache_usercacheentry) {
/*     */     try {
/*  64 */       this.stateLock.lock();
/*  65 */       GameProfile gameprofile = usercache_usercacheentry.a();
/*     */       
/*  67 */       usercache_usercacheentry.a(d());
/*  68 */       String s = gameprofile.getName();
/*     */       
/*  70 */       if (s != null) {
/*  71 */         this.c.put(s.toLowerCase(Locale.ROOT), usercache_usercacheentry);
/*     */       }
/*     */       
/*  74 */       UUID uuid = gameprofile.getId();
/*     */       
/*  76 */       if (uuid != null)
/*  77 */         this.d.put(uuid, usercache_usercacheentry); 
/*     */     } finally {
/*  79 */       this.stateLock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static GameProfile a(GameProfileRepository gameprofilerepository, String s) {
/*  85 */     final AtomicReference<GameProfile> atomicreference = new AtomicReference<>();
/*  86 */     ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */         public void onProfileLookupSucceeded(GameProfile gameprofile) {
/*  88 */           atomicreference.set(gameprofile);
/*     */         }
/*     */         
/*     */         public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/*  92 */           atomicreference.set(null);
/*     */         }
/*     */       };
/*     */     
/*  96 */     gameprofilerepository.findProfilesByNames(new String[] { s }, Agent.MINECRAFT, profilelookupcallback);
/*  97 */     GameProfile gameprofile = atomicreference.get();
/*     */     
/*  99 */     if (!c() && gameprofile == null && !StringUtils.isBlank(s)) {
/* 100 */       UUID uuid = EntityHuman.a(new GameProfile((UUID)null, s));
/*     */       
/* 102 */       gameprofile = new GameProfile(uuid, s);
/*     */     } 
/*     */     
/* 105 */     return gameprofile;
/*     */   }
/*     */   
/*     */   public static void a(boolean flag) {
/* 109 */     b = flag;
/*     */   }
/*     */   
/*     */   private static boolean c() {
/* 113 */     return b;
/*     */   }
/*     */   public void saveProfile(GameProfile gameprofile) {
/* 116 */     a(gameprofile);
/*     */   } public void a(GameProfile gameprofile) {
/* 118 */     Calendar calendar = Calendar.getInstance();
/*     */     
/* 120 */     calendar.setTime(new Date());
/* 121 */     calendar.add(2, 1);
/* 122 */     Date date = calendar.getTime();
/* 123 */     UserCacheEntry usercache_usercacheentry = new UserCacheEntry(gameprofile, date);
/*     */     
/* 125 */     a(usercache_usercacheentry);
/* 126 */     if (!SpigotConfig.saveUserCacheOnStopOnly) b(true); 
/*     */   }
/*     */   
/*     */   private long d() {
/* 130 */     return this.h.incrementAndGet();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GameProfile getProfile(String s) {
/* 135 */     String s1 = s.toLowerCase(Locale.ROOT);
/* 136 */     boolean stateLocked = true; try { GameProfile gameprofile; this.stateLock.lock();
/* 137 */       UserCacheEntry usercache_usercacheentry = this.c.get(s1);
/* 138 */       boolean flag = false;
/*     */       
/* 140 */       if (usercache_usercacheentry != null && (new Date()).getTime() >= usercache_usercacheentry.b.getTime()) {
/* 141 */         this.d.remove(usercache_usercacheentry.a().getId());
/* 142 */         this.c.remove(usercache_usercacheentry.a().getName().toLowerCase(Locale.ROOT));
/* 143 */         flag = true;
/* 144 */         usercache_usercacheentry = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 149 */       if (usercache_usercacheentry != null) {
/* 150 */         stateLocked = false; this.stateLock.unlock();
/* 151 */         usercache_usercacheentry.a(d());
/* 152 */         gameprofile = usercache_usercacheentry.a();
/*     */       } else {
/* 154 */         stateLocked = false; this.stateLock.unlock(); 
/* 155 */         try { this.lookupLock.lock();
/* 156 */           gameprofile = a(this.e, s); }
/* 157 */         finally { this.lookupLock.unlock(); }
/* 158 */          if (gameprofile != null) {
/* 159 */           a(gameprofile);
/* 160 */           flag = false;
/*     */         } 
/*     */       } 
/*     */       
/* 164 */       if (flag && !SpigotConfig.saveUserCacheOnStopOnly) {
/* 165 */         b(true);
/*     */       }
/*     */       
/* 168 */       return gameprofile; }
/* 169 */     finally { if (stateLocked) this.stateLock.unlock();  }
/*     */   
/*     */   }
/*     */   @Nullable
/*     */   public GameProfile getProfileIfCached(String name) {
/* 174 */     UserCacheEntry entry = this.nameCache.get(name.toLowerCase(Locale.ROOT));
/* 175 */     return (entry == null) ? null : entry.getProfile();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GameProfile getProfile(UUID uuid) {
/* 181 */     UserCacheEntry usercache_usercacheentry = this.d.get(uuid);
/*     */     
/* 183 */     if (usercache_usercacheentry == null) {
/* 184 */       return null;
/*     */     }
/* 186 */     usercache_usercacheentry.a(d());
/* 187 */     return usercache_usercacheentry.a();
/*     */   }
/*     */ 
/*     */   
/*     */   private static DateFormat e() {
/* 192 */     return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*     */   }
/*     */   
/*     */   public List<UserCacheEntry> a() {
/* 196 */     ArrayList<UserCacheEntry> arraylist = Lists.newArrayList();
/*     */     try {
/*     */       ArrayList<UserCacheEntry> arraylist1;
/* 199 */       BufferedReader bufferedreader = Files.newReader(this.g, StandardCharsets.UTF_8);
/* 200 */       Throwable throwable = null;
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 205 */         JsonArray jsonarray = (JsonArray)this.f.fromJson(bufferedreader, JsonArray.class);
/*     */         
/* 207 */         if (jsonarray != null) {
/* 208 */           DateFormat dateformat = e();
/*     */           
/* 210 */           jsonarray.forEach(jsonelement -> {
/*     */                 UserCacheEntry usercache_usercacheentry = a(jsonelement, dateformat);
/*     */                 
/*     */                 if (usercache_usercacheentry != null) {
/*     */                   arraylist.add(usercache_usercacheentry);
/*     */                 }
/*     */               });
/*     */           
/* 218 */           return arraylist;
/*     */         } 
/*     */         
/* 221 */         arraylist1 = arraylist;
/* 222 */       } catch (Throwable throwable1) {
/* 223 */         throwable = throwable1;
/* 224 */         throw throwable1;
/*     */       } finally {
/* 226 */         if (bufferedreader != null) {
/* 227 */           if (throwable != null) {
/*     */             try {
/* 229 */               bufferedreader.close();
/* 230 */             } catch (Throwable throwable2) {
/* 231 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/* 234 */             bufferedreader.close();
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 240 */       return arraylist1;
/* 241 */     } catch (FileNotFoundException fileNotFoundException) {
/*     */ 
/*     */     
/* 244 */     } catch (JsonSyntaxException ex) {
/* 245 */       JsonList.LOGGER.warn("Usercache.json is corrupted or has bad formatting. Deleting it to prevent further issues.");
/* 246 */       this.g.delete();
/*     */     }
/* 248 */     catch (JsonParseException|IOException ioexception) {
/* 249 */       LOGGER.warn("Failed to load profile cache {}", this.g, ioexception);
/*     */     } 
/*     */     
/* 252 */     return arraylist;
/*     */   }
/*     */   
/*     */   public void b(boolean asyncSave) {
/* 256 */     JsonArray jsonarray = new JsonArray();
/* 257 */     DateFormat dateformat = e();
/*     */     
/* 259 */     a(SpigotConfig.userCacheCap).forEach(usercache_usercacheentry -> jsonarray.add(a(usercache_usercacheentry, dateformat)));
/*     */ 
/*     */     
/* 262 */     String s = this.f.toJson((JsonElement)jsonarray);
/* 263 */     Runnable save = () -> {
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
/*     */         try (BufferedWriter bufferedwriter = Files.newWriter(this.g, StandardCharsets.UTF_8)) {
/*     */           
/*     */           bufferedwriter.write(s);
/* 288 */         } catch (IOException iOException) {}
/*     */       };
/*     */ 
/*     */ 
/*     */     
/* 293 */     if (asyncSave) {
/* 294 */       MCUtil.scheduleAsyncTask(save);
/*     */     } else {
/* 296 */       save.run();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Stream<UserCacheEntry> a(int i) {
/*     */     
/* 303 */     try { this.stateLock.lock();
/* 304 */       return ImmutableList.copyOf(this.d.values()).stream().sorted(Comparator.comparing(UserCacheEntry::c).reversed()).limit(i); }
/* 305 */     finally { this.stateLock.unlock(); }
/*     */   
/*     */   }
/*     */   private static JsonElement a(UserCacheEntry usercache_usercacheentry, DateFormat dateformat) {
/* 309 */     JsonObject jsonobject = new JsonObject();
/*     */     
/* 311 */     jsonobject.addProperty("name", usercache_usercacheentry.a().getName());
/* 312 */     UUID uuid = usercache_usercacheentry.a().getId();
/*     */     
/* 314 */     jsonobject.addProperty("uuid", (uuid == null) ? "" : uuid.toString());
/* 315 */     jsonobject.addProperty("expiresOn", dateformat.format(usercache_usercacheentry.b()));
/* 316 */     return (JsonElement)jsonobject;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static UserCacheEntry a(JsonElement jsonelement, DateFormat dateformat) {
/* 321 */     if (jsonelement.isJsonObject()) {
/* 322 */       JsonObject jsonobject = jsonelement.getAsJsonObject();
/* 323 */       JsonElement jsonelement1 = jsonobject.get("name");
/* 324 */       JsonElement jsonelement2 = jsonobject.get("uuid");
/* 325 */       JsonElement jsonelement3 = jsonobject.get("expiresOn");
/*     */       
/* 327 */       if (jsonelement1 != null && jsonelement2 != null) {
/* 328 */         String s = jsonelement2.getAsString();
/* 329 */         String s1 = jsonelement1.getAsString();
/* 330 */         Date date = null;
/*     */         
/* 332 */         if (jsonelement3 != null) {
/*     */           try {
/* 334 */             date = dateformat.parse(jsonelement3.getAsString());
/* 335 */           } catch (ParseException parseException) {}
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 340 */         if (s1 != null && s != null && date != null) {
/*     */           UUID uuid;
/*     */           
/*     */           try {
/* 344 */             uuid = UUID.fromString(s);
/* 345 */           } catch (Throwable throwable) {
/* 346 */             return null;
/*     */           } 
/*     */           
/* 349 */           return new UserCacheEntry(new GameProfile(uuid, s1), date);
/*     */         } 
/* 351 */         return null;
/*     */       } 
/*     */       
/* 354 */       return null;
/*     */     } 
/*     */     
/* 357 */     return null;
/*     */   }
/*     */   static class UserCacheEntry { private final GameProfile a; private final Date b;
/*     */     private volatile long c;
/*     */     
/*     */     public GameProfile getProfile() {
/* 363 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     private UserCacheEntry(GameProfile gameprofile, Date date) {
/* 368 */       this.a = gameprofile;
/* 369 */       this.b = date;
/*     */     }
/*     */     
/*     */     public GameProfile a() {
/* 373 */       return this.a;
/*     */     }
/*     */     
/*     */     public Date b() {
/* 377 */       return this.b;
/*     */     }
/*     */     
/*     */     public void a(long i) {
/* 381 */       this.c = i;
/*     */     }
/*     */     
/*     */     public long c() {
/* 385 */       return this.c;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\UserCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */