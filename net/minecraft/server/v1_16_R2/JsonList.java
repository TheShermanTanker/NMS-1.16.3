/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.logging.Level;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public abstract class JsonList<K, V extends JsonListEntry<K>> {
/*  28 */   protected static final Logger LOGGER = LogManager.getLogger();
/*  29 */   private static final Gson b = (new GsonBuilder()).setPrettyPrinting().create();
/*     */   
/*     */   private final File c;
/*  32 */   private final Map<String, V> d = Maps.newConcurrentMap(); private final Map<String, V> getBackingMap() { return this.d; }
/*     */    private boolean e = true;
/*  34 */   private static final ParameterizedType f = new ParameterizedType() {
/*     */       public Type[] getActualTypeArguments() {
/*  36 */         return new Type[] { JsonListEntry.class };
/*     */       }
/*     */       
/*     */       public Type getRawType() {
/*  40 */         return List.class;
/*     */       }
/*     */       
/*     */       public Type getOwnerType() {
/*  44 */         return null;
/*     */       }
/*     */     };
/*     */   
/*     */   public JsonList(File file) {
/*  49 */     this.c = file;
/*     */   }
/*     */   
/*     */   public File b() {
/*  53 */     return this.c;
/*     */   }
/*     */   
/*     */   public void add(V v0) {
/*  57 */     this.d.put(a(v0.getKey()), v0);
/*     */     
/*     */     try {
/*  60 */       save();
/*  61 */     } catch (IOException ioexception) {
/*  62 */       LOGGER.warn("Could not save the list after adding a user.", ioexception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public V get(K k0) {
/*  72 */     return getBackingMap().computeIfPresent(getMappingKey(k0), (k, v) -> v.hasExpired() ? null : v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(K k0) {
/*  79 */     this.d.remove(a(k0));
/*     */     
/*     */     try {
/*  82 */       save();
/*  83 */     } catch (IOException ioexception) {
/*  84 */       LOGGER.warn("Could not save the list after removing a user.", ioexception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(JsonListEntry<K> jsonlistentry) {
/*  90 */     remove(jsonlistentry.getKey());
/*     */   }
/*     */   
/*     */   public String[] getEntries() {
/*  94 */     return (String[])this.d.keySet().toArray((Object[])new String[this.d.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<V> getValues() {
/*  99 */     return this.d.values();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 105 */     return getBackingMap().isEmpty();
/*     */   }
/*     */   protected final String getMappingKey(K k0) {
/* 108 */     return a(k0);
/*     */   } protected String a(K k0) {
/* 110 */     return k0.toString();
/*     */   }
/*     */   
/*     */   protected boolean d(K k0) {
/* 114 */     return this.d.containsKey(a(k0));
/*     */   }
/*     */   private void removeStaleEntries() {
/* 117 */     g();
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
/*     */ 
/*     */   
/*     */   private void g() {
/* 138 */     getBackingMap().values().removeIf(JsonListEntry::hasExpired);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<V> d() {
/* 145 */     return this.d.values();
/*     */   }
/*     */   
/*     */   public void save() throws IOException {
/* 149 */     removeStaleEntries();
/* 150 */     JsonArray jsonarray = new JsonArray();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     Objects.requireNonNull(jsonarray); this.d.values().stream().map(jsonlistentry -> { JsonObject jsonobject = new JsonObject(); jsonlistentry.getClass(); Objects.requireNonNull(jsonlistentry); return SystemUtils.<JsonObject>a(jsonobject, jsonlistentry::a); }).forEach(jsonarray::add);
/* 158 */     BufferedWriter bufferedwriter = Files.newWriter(this.c, StandardCharsets.UTF_8);
/* 159 */     Throwable throwable = null;
/*     */     
/*     */     try {
/* 162 */       b.toJson((JsonElement)jsonarray, bufferedwriter);
/* 163 */     } catch (Throwable throwable1) {
/* 164 */       throwable = throwable1;
/* 165 */       throw throwable1;
/*     */     } finally {
/* 167 */       if (bufferedwriter != null) {
/* 168 */         if (throwable != null) {
/*     */           try {
/* 170 */             bufferedwriter.close();
/* 171 */           } catch (Throwable throwable2) {
/* 172 */             throwable.addSuppressed(throwable2);
/*     */           } 
/*     */         } else {
/* 175 */           bufferedwriter.close();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() throws IOException {
/* 184 */     if (this.c.exists()) {
/* 185 */       BufferedReader bufferedreader = Files.newReader(this.c, StandardCharsets.UTF_8);
/* 186 */       Throwable throwable = null;
/*     */       
/*     */       try {
/* 189 */         JsonArray jsonarray = (JsonArray)b.fromJson(bufferedreader, JsonArray.class);
/*     */         
/* 191 */         this.d.clear();
/* 192 */         Iterator<JsonElement> iterator = jsonarray.iterator();
/*     */         
/* 194 */         while (iterator.hasNext()) {
/* 195 */           JsonElement jsonelement = iterator.next();
/* 196 */           JsonObject jsonobject = ChatDeserializer.m(jsonelement, "entry");
/* 197 */           JsonListEntry<K> jsonlistentry = a(jsonobject);
/*     */           
/* 199 */           if (jsonlistentry.getKey() != null) {
/* 200 */             this.d.put(a(jsonlistentry.getKey()), (V)jsonlistentry);
/*     */           }
/*     */         }
/*     */       
/* 204 */       } catch (JsonParseException ex) {
/*     */         
/* 206 */         Bukkit.getLogger().log(Level.WARNING, "Unable to read file " + this.c + ", backing it up to {0}.backup and creating new copy.", (Throwable)ex);
/* 207 */         File backup = new File(this.c + ".backup");
/* 208 */         this.c.renameTo(backup);
/* 209 */         this.c.delete();
/*     */       }
/* 211 */       catch (Throwable throwable1) {
/* 212 */         throwable = throwable1;
/* 213 */         throw throwable1;
/*     */       } finally {
/* 215 */         if (bufferedreader != null)
/* 216 */           if (throwable != null) {
/*     */             try {
/* 218 */               bufferedreader.close();
/* 219 */             } catch (Throwable throwable2) {
/* 220 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/* 223 */             bufferedreader.close();
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract JsonListEntry<K> a(JsonObject paramJsonObject);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\JsonList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */