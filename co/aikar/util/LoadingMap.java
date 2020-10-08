/*     */ package co.aikar.util;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
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
/*     */ public class LoadingMap<K, V>
/*     */   extends AbstractMap<K, V>
/*     */ {
/*     */   private final Map<K, V> backingMap;
/*     */   private final Function<K, V> loader;
/*     */   
/*     */   public LoadingMap(@NotNull Map<K, V> backingMap, @NotNull Function<K, V> loader) {
/*  63 */     this.backingMap = backingMap;
/*  64 */     this.loader = loader;
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> of(@NotNull Map<K, V> backingMap, @NotNull Function<K, V> loader) {
/*  78 */     return new LoadingMap<>(backingMap, loader);
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> newAutoMap(@NotNull Map<K, V> backingMap, @Nullable Class<? extends K> keyClass, @NotNull Class<? extends V> valueClass) {
/*  99 */     return new LoadingMap<>(backingMap, new AutoInstantiatingLoader<>(keyClass, valueClass));
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> newAutoMap(@NotNull Map<K, V> backingMap, @NotNull Class<? extends V> valueClass) {
/* 118 */     return newAutoMap(backingMap, null, valueClass);
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
/*     */   public static <K, V> Map<K, V> newHashAutoMap(@Nullable Class<? extends K> keyClass, @NotNull Class<? extends V> valueClass) {
/* 134 */     return newAutoMap(new HashMap<>(), keyClass, valueClass);
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
/*     */   public static <K, V> Map<K, V> newHashAutoMap(@NotNull Class<? extends V> valueClass) {
/* 149 */     return newHashAutoMap(null, valueClass);
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> newHashAutoMap(@Nullable Class<? extends K> keyClass, @NotNull Class<? extends V> valueClass, int initialCapacity, float loadFactor) {
/* 167 */     return newAutoMap(new HashMap<>(initialCapacity, loadFactor), keyClass, valueClass);
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
/*     */   public static <K, V> Map<K, V> newHashAutoMap(@NotNull Class<? extends V> valueClass, int initialCapacity, float loadFactor) {
/* 184 */     return newHashAutoMap(null, valueClass, initialCapacity, loadFactor);
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> newHashMap(@NotNull Function<K, V> loader) {
/* 197 */     return new LoadingMap<>(new HashMap<>(), loader);
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> newHashMap(@NotNull Function<K, V> loader, int initialCapacity) {
/* 211 */     return new LoadingMap<>(new HashMap<>(initialCapacity), loader);
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> newHashMap(@NotNull Function<K, V> loader, int initialCapacity, float loadFactor) {
/* 225 */     return new LoadingMap<>(new HashMap<>(initialCapacity, loadFactor), loader);
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> newIdentityHashMap(@NotNull Function<K, V> loader) {
/* 238 */     return new LoadingMap<>(new IdentityHashMap<>(), loader);
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
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> newIdentityHashMap(@NotNull Function<K, V> loader, int initialCapacity) {
/* 252 */     return new LoadingMap<>(new IdentityHashMap<>(initialCapacity), loader);
/*     */   }
/*     */   
/*     */   public int size() {
/* 256 */     return this.backingMap.size();
/*     */   }
/*     */   public boolean isEmpty() {
/* 259 */     return this.backingMap.isEmpty();
/*     */   }
/*     */   public boolean containsKey(@Nullable Object key) {
/* 262 */     return this.backingMap.containsKey(key);
/*     */   }
/*     */   public boolean containsValue(@Nullable Object value) {
/* 265 */     return this.backingMap.containsValue(value);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public V get(@Nullable Object key) {
/* 270 */     V v = this.backingMap.get(key);
/* 271 */     if (v != null) {
/* 272 */       return v;
/*     */     }
/* 274 */     return this.backingMap.computeIfAbsent((K)key, this.loader);
/*     */   }
/*     */   @Nullable
/*     */   public V put(@Nullable K key, @Nullable V value) {
/* 278 */     return this.backingMap.put(key, value);
/*     */   }
/*     */   @Nullable
/*     */   public V remove(@Nullable Object key) {
/* 282 */     return this.backingMap.remove(key);
/*     */   } public void putAll(@NotNull Map<? extends K, ? extends V> m) {
/* 284 */     this.backingMap.putAll(m);
/*     */   }
/*     */   public void clear() {
/* 287 */     this.backingMap.clear();
/*     */   }
/*     */   @NotNull
/*     */   public Set<K> keySet() {
/* 291 */     return this.backingMap.keySet();
/*     */   }
/*     */   @NotNull
/*     */   public Collection<V> values() {
/* 295 */     return this.backingMap.values();
/*     */   }
/*     */   public boolean equals(@Nullable Object o) {
/* 298 */     return this.backingMap.equals(o);
/*     */   }
/*     */   public int hashCode() {
/* 301 */     return this.backingMap.hashCode();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 306 */     return this.backingMap.entrySet();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public LoadingMap<K, V> clone() {
/* 311 */     return new LoadingMap(this.backingMap, this.loader);
/*     */   }
/*     */   
/*     */   private static class AutoInstantiatingLoader<K, V> implements Function<K, V> {
/*     */     final Constructor<? extends V> constructor;
/*     */     private final Class<? extends V> valueClass;
/*     */     
/*     */     AutoInstantiatingLoader(@Nullable Class<? extends K> keyClass, @NotNull Class<? extends V> valueClass) {
/*     */       try {
/* 320 */         this.valueClass = valueClass;
/* 321 */         if (keyClass != null) {
/* 322 */           this.constructor = valueClass.getConstructor(new Class[] { keyClass });
/*     */         } else {
/* 324 */           this.constructor = null;
/*     */         } 
/* 326 */       } catch (NoSuchMethodException e) {
/* 327 */         throw new IllegalStateException(valueClass
/* 328 */             .getName() + " does not have a constructor for " + ((keyClass != null) ? keyClass.getName() : null));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public V apply(@Nullable K input) {
/*     */       try {
/* 336 */         return (this.constructor != null) ? this.constructor.newInstance(new Object[] { input }) : this.valueClass.newInstance();
/* 337 */       } catch (Exception e) {
/* 338 */         throw new ExceptionInInitializerError(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 344 */       return super.hashCode();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object object) {
/* 349 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class Feeder<T>
/*     */     implements Function<T, T>
/*     */   {
/*     */     @Nullable
/*     */     public T apply(@Nullable Object input) {
/* 362 */       return apply();
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public abstract T apply();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aika\\util\LoadingMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */