/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import com.mojang.serialization.MapCodec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectList;
/*     */ import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ 
/*     */ public class RegistryMaterials<T>
/*     */   extends IRegistryWritable<T> {
/*  36 */   protected static final Logger LOGGER = LogManager.getLogger();
/*  37 */   private final ObjectList<T> bf = (ObjectList<T>)new ObjectArrayList(256);
/*  38 */   private final Reference2IntOpenHashMap<T> bg = new Reference2IntOpenHashMap(2048);
/*     */   private final BiMap<MinecraftKey, T> bh;
/*     */   private final BiMap<ResourceKey<T>, T> bi;
/*     */   private final Map<T, Lifecycle> bj;
/*     */   private Lifecycle bk;
/*     */   protected Object[] b;
/*     */   private int bl;
/*     */   
/*     */   public RegistryMaterials(ResourceKey<? extends IRegistry<T>> resourcekey, Lifecycle lifecycle) {
/*  47 */     super(resourcekey, lifecycle);
/*  48 */     this.bg.defaultReturnValue(-1);
/*  49 */     this.bh = (BiMap<MinecraftKey, T>)HashBiMap.create(2048);
/*  50 */     this.bi = (BiMap<ResourceKey<T>, T>)HashBiMap.create(2048);
/*  51 */     this.bj = new IdentityHashMap<>(2048);
/*  52 */     this.bk = lifecycle;
/*     */   }
/*     */   
/*     */   public static <T> MapCodec<a<T>> a(ResourceKey<? extends IRegistry<T>> resourcekey, MapCodec<T> mapcodec) {
/*  56 */     return RecordCodecBuilder.mapCodec(instance -> instance.group((App)MinecraftKey.a.xmap(ResourceKey.b(resourcekey), ResourceKey::a).fieldOf("name").forGetter(()), (App)Codec.INT.fieldOf("id").forGetter(()), (App)mapcodec.forGetter(())).apply((Applicative)instance, a::new));
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
/*     */   public <V extends T> V a(int i, ResourceKey<T> resourcekey, V v0, Lifecycle lifecycle) {
/*  69 */     return a(i, resourcekey, v0, lifecycle, true);
/*     */   }
/*     */   
/*     */   private <V extends T> V a(int i, ResourceKey<T> resourcekey, V v0, Lifecycle lifecycle, boolean flag) {
/*  73 */     Validate.notNull(resourcekey);
/*  74 */     Validate.notNull(v0);
/*  75 */     this.bf.size(Math.max(this.bf.size(), i + 1));
/*  76 */     this.bf.set(i, v0);
/*  77 */     this.bg.put(v0, i);
/*  78 */     this.b = null;
/*  79 */     if (flag && this.bi.containsKey(resourcekey)) {
/*  80 */       LOGGER.debug("Adding duplicate key '{}' to registry", resourcekey);
/*     */     }
/*     */     
/*  83 */     if (this.bh.containsValue(v0)) {
/*  84 */       LOGGER.error("Adding duplicate value '{}' to registry", v0);
/*     */     }
/*     */     
/*  87 */     this.bh.put(resourcekey.a(), v0);
/*  88 */     this.bi.put(resourcekey, v0);
/*  89 */     this.bj.put((T)v0, lifecycle);
/*  90 */     this.bk = this.bk.add(lifecycle);
/*  91 */     if (this.bl <= i) {
/*  92 */       this.bl = i + 1;
/*     */     }
/*     */     
/*  95 */     return v0;
/*     */   }
/*     */ 
/*     */   
/*     */   public <V extends T> V a(ResourceKey<T> resourcekey, V v0, Lifecycle lifecycle) {
/* 100 */     return a(this.bl, resourcekey, v0, lifecycle);
/*     */   }
/*     */   
/*     */   public <V extends T> V a(OptionalInt optionalint, ResourceKey<T> resourcekey, V v0, Lifecycle lifecycle) {
/*     */     int i;
/* 105 */     Validate.notNull(resourcekey);
/* 106 */     Validate.notNull(v0);
/* 107 */     T t0 = (T)this.bi.get(resourcekey);
/*     */ 
/*     */     
/* 110 */     if (t0 == null) {
/* 111 */       i = optionalint.isPresent() ? optionalint.getAsInt() : this.bl;
/*     */     } else {
/* 113 */       i = this.bg.getInt(t0);
/* 114 */       if (optionalint.isPresent() && optionalint.getAsInt() != i) {
/* 115 */         throw new IllegalStateException("ID mismatch");
/*     */       }
/*     */       
/* 118 */       this.bg.removeInt(t0);
/* 119 */       this.bj.remove(t0);
/*     */     } 
/*     */     
/* 122 */     return a(i, resourcekey, v0, lifecycle, false);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MinecraftKey getKey(T t0) {
/* 128 */     return (MinecraftKey)this.bh.inverse().get(t0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<ResourceKey<T>> c(T t0) {
/* 133 */     return Optional.ofNullable((ResourceKey<T>)this.bi.inverse().get(t0));
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(@Nullable T t0) {
/* 138 */     return this.bg.getInt(t0);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public T a(@Nullable ResourceKey<T> resourcekey) {
/* 144 */     return (T)this.bi.get(resourcekey);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public T fromId(int i) {
/* 150 */     return (i >= 0 && i < this.bf.size()) ? (T)this.bf.get(i) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Lifecycle d(T t0) {
/* 155 */     return this.bj.get(t0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Lifecycle b() {
/* 160 */     return this.bk;
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/* 164 */     return (Iterator<T>)Iterators.filter((Iterator)this.bf.iterator(), Objects::nonNull);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public T get(@Nullable MinecraftKey minecraftkey) {
/* 170 */     return (T)this.bh.get(minecraftkey);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MinecraftKey> keySet() {
/* 175 */     return Collections.unmodifiableSet(this.bh.keySet());
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<ResourceKey<T>, T>> d() {
/* 180 */     return Collections.<ResourceKey<T>, T>unmodifiableMap((Map<? extends ResourceKey<T>, ? extends T>)this.bi).entrySet();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public T a(Random random) {
/* 185 */     if (this.b == null) {
/* 186 */       Collection<?> collection = this.bh.values();
/*     */       
/* 188 */       if (collection.isEmpty()) {
/* 189 */         return null;
/*     */       }
/*     */       
/* 192 */       this.b = collection.toArray(new Object[collection.size()]);
/*     */     } 
/*     */     
/* 195 */     return SystemUtils.a((T[])this.b, random);
/*     */   }
/*     */   
/*     */   public static <T> Codec<RegistryMaterials<T>> a(ResourceKey<? extends IRegistry<T>> resourcekey, Lifecycle lifecycle, Codec<T> codec) {
/* 199 */     return a(resourcekey, codec.fieldOf("element")).codec().listOf().xmap(list -> {
/*     */           RegistryMaterials<T> registrymaterials = new RegistryMaterials<>(resourcekey, lifecycle);
/*     */           Iterator<a<T>> iterator = list.iterator();
/*     */           while (iterator.hasNext()) {
/*     */             a<T> registrymaterials_a = iterator.next();
/*     */             registrymaterials.a(registrymaterials_a.b, registrymaterials_a.a, registrymaterials_a.c, lifecycle);
/*     */           } 
/*     */           return registrymaterials;
/*     */         }registrymaterials -> {
/*     */           ImmutableList.Builder<a<T>> builder = ImmutableList.builder();
/*     */           Iterator<T> iterator = registrymaterials.iterator();
/*     */           while (iterator.hasNext()) {
/*     */             T t0 = iterator.next();
/*     */             builder.add(new a<>(registrymaterials.c(t0).get(), registrymaterials.a(t0), t0));
/*     */           } 
/*     */           return (List)builder.build();
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> Codec<RegistryMaterials<T>> b(ResourceKey<? extends IRegistry<T>> resourcekey, Lifecycle lifecycle, Codec<T> codec) {
/* 225 */     return RegistryDataPackCodec.a(resourcekey, lifecycle, codec);
/*     */   }
/*     */   
/*     */   public static <T> Codec<RegistryMaterials<T>> c(ResourceKey<? extends IRegistry<T>> resourcekey, Lifecycle lifecycle, Codec<T> codec) {
/* 229 */     return Codec.unboundedMap(MinecraftKey.a.xmap(ResourceKey.b(resourcekey), ResourceKey::a), codec).xmap(map -> {
/*     */           RegistryMaterials<T> registrymaterials = new RegistryMaterials<>(resourcekey, lifecycle);
/*     */           map.forEach(());
/*     */           return registrymaterials;
/*     */         }registrymaterials -> ImmutableMap.copyOf((Map)registrymaterials.bi));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a<T>
/*     */   {
/*     */     public final ResourceKey<T> a;
/*     */     
/*     */     public final int b;
/*     */     
/*     */     public final T c;
/*     */ 
/*     */     
/*     */     public a(ResourceKey<T> resourcekey, int i, T t0) {
/* 248 */       this.a = resourcekey;
/* 249 */       this.b = i;
/* 250 */       this.c = t0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryMaterials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */