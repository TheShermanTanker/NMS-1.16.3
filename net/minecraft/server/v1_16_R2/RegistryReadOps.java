/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Supplier;
/*     */ import com.google.common.base.Suppliers;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Decoder;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.Encoder;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Collection;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegistryReadOps<T>
/*     */   extends DynamicOpsWrapper<T>
/*     */ {
/*  42 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final b c;
/*     */   
/*     */   private final IRegistryCustom.Dimension d;
/*     */   private final Map<ResourceKey<? extends IRegistry<?>>, a<?>> e;
/*     */   private final RegistryReadOps<JsonElement> f;
/*     */   
/*     */   public static <T> RegistryReadOps<T> a(DynamicOps<T> var0, IResourceManager var1, IRegistryCustom.Dimension var2) {
/*  51 */     return a(var0, b.a(var1), var2);
/*     */   }
/*     */   
/*     */   public static <T> RegistryReadOps<T> a(DynamicOps<T> var0, b var1, IRegistryCustom.Dimension var2) {
/*  55 */     RegistryReadOps<T> var3 = new RegistryReadOps<>(var0, var1, var2, Maps.newIdentityHashMap());
/*     */     
/*  57 */     IRegistryCustom.a(var2, var3);
/*  58 */     return var3;
/*     */   }
/*     */   
/*     */   static final class a<E> {
/*  62 */     private final Map<ResourceKey<E>, DataResult<Supplier<E>>> a = Maps.newIdentityHashMap();
/*     */     
/*     */     private a() {} }
/*     */   
/*     */   private RegistryReadOps(DynamicOps<T> var0, b var1, IRegistryCustom.Dimension var2, IdentityHashMap<ResourceKey<? extends IRegistry<?>>, a<?>> var3) {
/*  67 */     super(var0);
/*  68 */     this.c = var1;
/*  69 */     this.d = var2;
/*  70 */     this.e = var3;
/*  71 */     this.f = (var0 == JsonOps.INSTANCE) ? (RegistryReadOps)this : new RegistryReadOps((DynamicOps<T>)JsonOps.INSTANCE, var1, var2, var3);
/*     */   }
/*     */   
/*     */   protected <E> DataResult<Pair<Supplier<E>, T>> a(T var0, ResourceKey<? extends IRegistry<E>> var1, Codec<E> var2, boolean var3) {
/*  75 */     Optional<IRegistryWritable<E>> var4 = this.d.a(var1);
/*  76 */     if (!var4.isPresent()) {
/*  77 */       return DataResult.error("Unknown registry: " + var1);
/*     */     }
/*     */     
/*  80 */     IRegistryWritable<E> var5 = var4.get();
/*     */     
/*  82 */     DataResult<Pair<MinecraftKey, T>> var6 = MinecraftKey.a.decode(this.a, var0);
/*  83 */     if (!var6.result().isPresent()) {
/*  84 */       if (!var3) {
/*  85 */         return DataResult.error("Inline definitions not allowed here");
/*     */       }
/*  87 */       return var2.decode(this, var0).map(var0 -> var0.mapFirst(()));
/*     */     } 
/*     */     
/*  90 */     Pair<MinecraftKey, T> var7 = var6.result().get();
/*  91 */     MinecraftKey var8 = (MinecraftKey)var7.getFirst();
/*  92 */     return a(var1, var5, var2, var8).map(var1 -> Pair.of(var1, var0.getSecond()));
/*     */   }
/*     */ 
/*     */   
/*     */   public <E> DataResult<RegistryMaterials<E>> a(RegistryMaterials<E> var0, ResourceKey<? extends IRegistry<E>> var1, Codec<E> var2) {
/*  97 */     Collection<MinecraftKey> var3 = this.c.a(var1);
/*     */     
/*  99 */     DataResult<RegistryMaterials<E>> var4 = DataResult.success(var0, Lifecycle.stable());
/*     */     
/* 101 */     String var5 = var1.a().getKey() + "/";
/* 102 */     for (MinecraftKey var7 : var3) {
/* 103 */       String var8 = var7.getKey();
/* 104 */       if (!var8.endsWith(".json")) {
/* 105 */         LOGGER.warn("Skipping resource {} since it is not a json file", var7);
/*     */         continue;
/*     */       } 
/* 108 */       if (!var8.startsWith(var5)) {
/* 109 */         LOGGER.warn("Skipping resource {} since it does not have a registry name prefix", var7);
/*     */         continue;
/*     */       } 
/* 112 */       String var9 = var8.substring(var5.length(), var8.length() - ".json".length());
/* 113 */       MinecraftKey var10 = new MinecraftKey(var7.getNamespace(), var9);
/*     */       
/* 115 */       var4 = var4.flatMap(var3 -> a(var0, var3, var1, var2).map(()));
/*     */     } 
/*     */     
/* 118 */     return var4.setPartial(var0);
/*     */   }
/*     */   private <E> DataResult<Supplier<E>> a(ResourceKey<? extends IRegistry<E>> var0, IRegistryWritable<E> var1, Codec<E> var2, MinecraftKey var3) {
/*     */     DataResult<Supplier<E>> var10;
/* 122 */     ResourceKey<E> var4 = ResourceKey.a(var0, var3);
/*     */     
/* 124 */     a<E> var5 = b(var0);
/* 125 */     DataResult<Supplier<E>> var6 = (DataResult<Supplier<E>>)a.a(var5).get(var4);
/* 126 */     if (var6 != null)
/*     */     {
/* 128 */       return var6;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     Supplier supplier = Suppliers.memoize(() -> {
/*     */           E var2 = var0.a(var1);
/*     */           if (var2 == null) {
/*     */             throw new RuntimeException("Error during recursive registry parsing, element resolved too early: " + var1);
/*     */           }
/*     */           return (Supplier)var2;
/*     */         });
/* 141 */     a.a(var5).put(var4, DataResult.success(supplier));
/*     */ 
/*     */     
/* 144 */     DataResult<Pair<E, OptionalInt>> var8 = this.c.a(this.f, var0, var4, (Decoder<E>)var2);
/*     */     
/* 146 */     Optional<Pair<E, OptionalInt>> var9 = var8.result();
/*     */     
/* 148 */     if (var9.isPresent()) {
/*     */       
/* 150 */       Pair<E, OptionalInt> pair = var9.get();
/* 151 */       var1.a((OptionalInt)pair.getSecond(), var4, pair.getFirst(), var8.lifecycle());
/*     */     } 
/*     */ 
/*     */     
/* 155 */     if (!var9.isPresent() && var1.a(var4) != null) {
/*     */       
/* 157 */       var10 = DataResult.success(() -> var0.a(var1), Lifecycle.stable());
/*     */     } else {
/*     */       
/* 160 */       var10 = var8.map(var2 -> ());
/*     */     } 
/*     */ 
/*     */     
/* 164 */     a.a(var5).put(var4, var10);
/*     */     
/* 166 */     return var10;
/*     */   }
/*     */ 
/*     */   
/*     */   private <E> a<E> b(ResourceKey<? extends IRegistry<E>> var0) {
/* 171 */     return (a<E>)this.e.computeIfAbsent(var0, var0 -> new a());
/*     */   }
/*     */ 
/*     */   
/*     */   protected <E> DataResult<IRegistry<E>> a(ResourceKey<? extends IRegistry<E>> var0) {
/* 176 */     return this.d.<E>a(var0)
/* 177 */       .map(var0 -> DataResult.success(var0, var0.b()))
/* 178 */       .orElseGet(() -> DataResult.error("Unknown registry: " + var0));
/*     */   }
/*     */   
/*     */   public static interface b {
/*     */     Collection<MinecraftKey> a(ResourceKey<? extends IRegistry<?>> param1ResourceKey);
/*     */     
/*     */     <E> DataResult<Pair<E, OptionalInt>> a(DynamicOps<JsonElement> param1DynamicOps, ResourceKey<? extends IRegistry<E>> param1ResourceKey, ResourceKey<E> param1ResourceKey1, Decoder<E> param1Decoder);
/*     */     
/*     */     static b a(IResourceManager var0) {
/* 187 */       return new b(var0)
/*     */         {
/*     */           public Collection<MinecraftKey> a(ResourceKey<? extends IRegistry<?>> var0) {
/* 190 */             return this.a.a(var0.a().getKey(), var0 -> var0.endsWith(".json"));
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public <E> DataResult<Pair<E, OptionalInt>> a(DynamicOps<JsonElement> var0, ResourceKey<? extends IRegistry<E>> var1, ResourceKey<E> var2, Decoder<E> var3) {
/* 196 */             MinecraftKey var4 = var2.a();
/* 197 */             MinecraftKey var5 = new MinecraftKey(var4.getNamespace(), var1.a().getKey() + "/" + var4.getKey() + ".json");
/*     */             
/* 199 */             try(IResource var6 = this.a.a(var5); 
/* 200 */                 Reader var8 = new InputStreamReader(var6.b(), StandardCharsets.UTF_8)) {
/*     */               
/* 202 */               JsonParser var10 = new JsonParser();
/* 203 */               JsonElement var11 = var10.parse(var8);
/* 204 */               return var3.parse(var0, var11).map(var0 -> Pair.of(var0, OptionalInt.empty()));
/* 205 */             } catch (IOException|com.google.gson.JsonIOException|com.google.gson.JsonSyntaxException var6) {
/* 206 */               return DataResult.error("Failed to parse " + var5 + " file: " + var6.getMessage());
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/*     */           public String toString() {
/* 212 */             return "ResourceAccess[" + this.a + "]";
/*     */           }
/*     */         };
/*     */     }
/*     */     
/*     */     public static final class a implements b {
/* 218 */       private final Map<ResourceKey<?>, JsonElement> a = Maps.newIdentityHashMap();
/* 219 */       private final Object2IntMap<ResourceKey<?>> b = (Object2IntMap<ResourceKey<?>>)new Object2IntOpenCustomHashMap(SystemUtils.k());
/* 220 */       private final Map<ResourceKey<?>, Lifecycle> c = Maps.newIdentityHashMap();
/*     */       
/*     */       public <E> void a(IRegistryCustom.Dimension var0, ResourceKey<E> var1, Encoder<E> var2, int var3, E var4, Lifecycle var5) {
/* 223 */         DataResult<JsonElement> var6 = var2.encodeStart(RegistryWriteOps.a((DynamicOps<?>)JsonOps.INSTANCE, var0), var4);
/* 224 */         Optional<DataResult.PartialResult<JsonElement>> var7 = var6.error();
/* 225 */         if (var7.isPresent()) {
/* 226 */           RegistryReadOps.a().error("Error adding element: {}", ((DataResult.PartialResult)var7.get()).message());
/*     */           return;
/*     */         } 
/* 229 */         this.a.put(var1, var6.result().get());
/* 230 */         this.b.put(var1, var3);
/* 231 */         this.c.put(var1, var5);
/*     */       }
/*     */ 
/*     */       
/*     */       public Collection<MinecraftKey> a(ResourceKey<? extends IRegistry<?>> var0) {
/* 236 */         return (Collection<MinecraftKey>)this.a.keySet().stream().filter(var1 -> var1.a(var0)).map(var1 -> new MinecraftKey(var1.a().getNamespace(), var0.a().getKey() + "/" + var1.a().getKey() + ".json")).collect(Collectors.toList());
/*     */       }
/*     */ 
/*     */       
/*     */       public <E> DataResult<Pair<E, OptionalInt>> a(DynamicOps<JsonElement> var0, ResourceKey<? extends IRegistry<E>> var1, ResourceKey<E> var2, Decoder<E> var3) {
/* 241 */         JsonElement var4 = this.a.get(var2);
/* 242 */         if (var4 == null) {
/* 243 */           return DataResult.error("Unknown element: " + var2);
/*     */         }
/* 245 */         return var3.parse(var0, var4).setLifecycle(this.c.get(var2)).map(var1 -> Pair.of(var1, OptionalInt.of(this.b.getInt(var0))));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryReadOps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */