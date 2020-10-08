/*    */ package org.bukkit.entity.memory;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Keyed;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class MemoryKey<T>
/*    */   implements Keyed
/*    */ {
/*    */   private final NamespacedKey namespacedKey;
/*    */   private final Class<T> tClass;
/*    */   
/*    */   private MemoryKey(NamespacedKey namespacedKey, Class<T> tClass) {
/* 26 */     this.namespacedKey = namespacedKey;
/* 27 */     this.tClass = tClass;
/* 28 */     MEMORY_KEYS.put(namespacedKey, this);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public NamespacedKey getKey() {
/* 34 */     return this.namespacedKey;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Class<T> getMemoryClass() {
/* 44 */     return this.tClass;
/*    */   }
/*    */   
/* 47 */   private static final Map<NamespacedKey, MemoryKey> MEMORY_KEYS = new HashMap<>();
/*    */   
/* 49 */   public static final MemoryKey<Location> HOME = new MemoryKey(NamespacedKey.minecraft("home"), (Class)Location.class);
/* 50 */   public static final MemoryKey<Location> POTENTIAL_JOB_SITE = new MemoryKey(NamespacedKey.minecraft("potential_job_site"), (Class)Location.class);
/* 51 */   public static final MemoryKey<Location> JOB_SITE = new MemoryKey(NamespacedKey.minecraft("job_site"), (Class)Location.class);
/* 52 */   public static final MemoryKey<Location> MEETING_POINT = new MemoryKey(NamespacedKey.minecraft("meeting_point"), (Class)Location.class);
/* 53 */   public static final MemoryKey<Boolean> GOLEM_DETECTED_RECENTLY = new MemoryKey(NamespacedKey.minecraft("golem_detected_recently"), (Class)Boolean.class);
/* 54 */   public static final MemoryKey<Long> LAST_SLEPT = new MemoryKey(NamespacedKey.minecraft("last_slept"), (Class)Long.class);
/* 55 */   public static final MemoryKey<Long> LAST_WOKEN = new MemoryKey(NamespacedKey.minecraft("last_woken"), (Class)Long.class);
/* 56 */   public static final MemoryKey<Long> LAST_WORKED_AT_POI = new MemoryKey(NamespacedKey.minecraft("last_worked_at_poi"), (Class)Long.class);
/* 57 */   public static final MemoryKey<Boolean> UNIVERSAL_ANGER = new MemoryKey(NamespacedKey.minecraft("universal_anger"), (Class)Boolean.class);
/* 58 */   public static final MemoryKey<UUID> ANGRY_AT = new MemoryKey(NamespacedKey.minecraft("angry_at"), (Class)UUID.class);
/* 59 */   public static final MemoryKey<Boolean> ADMIRING_ITEM = new MemoryKey(NamespacedKey.minecraft("admiring_item"), (Class)Boolean.class);
/* 60 */   public static final MemoryKey<Boolean> ADMIRING_DISABLED = new MemoryKey(NamespacedKey.minecraft("admiring_disabled"), (Class)Boolean.class);
/* 61 */   public static final MemoryKey<Boolean> HUNTED_RECENTLY = new MemoryKey(NamespacedKey.minecraft("hunted_recently"), (Class)Boolean.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static MemoryKey getByKey(@NotNull NamespacedKey namespacedKey) {
/* 73 */     return MEMORY_KEYS.get(namespacedKey);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static Set<MemoryKey> values() {
/* 83 */     return new HashSet<>(MEMORY_KEYS.values());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\memory\MemoryKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */