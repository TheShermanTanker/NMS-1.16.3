/*     */ package co.aikar.util;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class MRUMapCache<K, V>
/*     */   extends AbstractMap<K, V>
/*     */ {
/*     */   final Map<K, V> backingMap;
/*     */   Object cacheKey;
/*     */   V cacheValue;
/*     */   
/*     */   public MRUMapCache(@NotNull Map<K, V> backingMap) {
/*  44 */     this.backingMap = backingMap;
/*     */   }
/*     */   public int size() {
/*  47 */     return this.backingMap.size();
/*     */   } public boolean isEmpty() {
/*  49 */     return this.backingMap.isEmpty();
/*     */   }
/*     */   public boolean containsKey(@Nullable Object key) {
/*  52 */     return ((key != null && key.equals(this.cacheKey)) || this.backingMap.containsKey(key));
/*     */   }
/*     */   
/*     */   public boolean containsValue(@Nullable Object value) {
/*  56 */     return ((value != null && value == this.cacheValue) || this.backingMap.containsValue(value));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public V get(@Nullable Object key) {
/*  61 */     if (this.cacheKey != null && this.cacheKey.equals(key)) {
/*  62 */       return this.cacheValue;
/*     */     }
/*  64 */     this.cacheKey = key;
/*  65 */     return this.cacheValue = this.backingMap.get(key);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public V put(@Nullable K key, @Nullable V value) {
/*  70 */     this.cacheKey = key;
/*  71 */     return this.cacheValue = this.backingMap.put(key, value);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public V remove(@Nullable Object key) {
/*  76 */     if (key != null && key.equals(this.cacheKey)) {
/*  77 */       this.cacheKey = null;
/*     */     }
/*  79 */     return this.backingMap.remove(key);
/*     */   }
/*     */   public void putAll(@NotNull Map<? extends K, ? extends V> m) {
/*  82 */     this.backingMap.putAll(m);
/*     */   }
/*     */   public void clear() {
/*  85 */     this.cacheKey = null;
/*  86 */     this.cacheValue = null;
/*  87 */     this.backingMap.clear();
/*     */   }
/*     */   @NotNull
/*     */   public Set<K> keySet() {
/*  91 */     return this.backingMap.keySet();
/*     */   } @NotNull
/*     */   public Collection<V> values() {
/*  94 */     return this.backingMap.values();
/*     */   } @NotNull
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/*  97 */     return this.backingMap.entrySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static <K, V> Map<K, V> of(@NotNull Map<K, V> map) {
/* 109 */     return new MRUMapCache<>(map);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aika\\util\MRUMapCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */