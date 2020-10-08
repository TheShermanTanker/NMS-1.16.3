/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.advancement.Advancement;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.boss.KeyedBossBar;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.entity.memory.MemoryKey;
/*     */ import org.bukkit.loot.LootTables;
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
/*     */ public interface Registry<T extends Keyed>
/*     */   extends Iterable<T>
/*     */ {
/*  35 */   public static final Registry<Advancement> ADVANCEMENT = new Registry<Advancement>()
/*     */     {
/*     */       @Nullable
/*     */       public Advancement get(@NotNull NamespacedKey key)
/*     */       {
/*  40 */         return Bukkit.getAdvancement(key);
/*     */       }
/*     */ 
/*     */       
/*     */       @NotNull
/*     */       public Iterator<Advancement> iterator() {
/*  46 */         return Bukkit.advancementIterator();
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final Registry<Art> ART = new SimpleRegistry<>(Art.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public static final Registry<Attribute> ATTRIBUTE = new SimpleRegistry<>(Attribute.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final Registry<Biome> BIOME = new SimpleRegistry<>(Biome.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static final Registry<KeyedBossBar> BOSS_BARS = new Registry<KeyedBossBar>()
/*     */     {
/*     */       @Nullable
/*     */       public KeyedBossBar get(@NotNull NamespacedKey key)
/*     */       {
/*  78 */         return Bukkit.getBossBar(key);
/*     */       }
/*     */ 
/*     */       
/*     */       @NotNull
/*     */       public Iterator<KeyedBossBar> iterator() {
/*  84 */         return Bukkit.getBossBars();
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static final Registry<Enchantment> ENCHANTMENT = new Registry<Enchantment>()
/*     */     {
/*     */       @Nullable
/*     */       public Enchantment get(@NotNull NamespacedKey key)
/*     */       {
/*  97 */         return Enchantment.getByKey(key);
/*     */       }
/*     */ 
/*     */       
/*     */       @NotNull
/*     */       public Iterator<Enchantment> iterator() {
/* 103 */         return Arrays.<Enchantment>asList(Enchantment.values()).iterator();
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public static final Registry<EntityType> ENTITY_TYPE;
/*     */   
/*     */   static {
/* 111 */     ENTITY_TYPE = new SimpleRegistry<>(EntityType.class, entity -> (entity != EntityType.UNKNOWN));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public static final Registry<LootTables> LOOT_TABLES = new SimpleRegistry<>(LootTables.class);
/*     */   
/*     */   public static final Registry<Material> MATERIAL;
/*     */ 
/*     */   
/*     */   static {
/* 123 */     MATERIAL = new SimpleRegistry<>(Material.class, mat -> !mat.isLegacy());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public static final Registry<Statistic> STATISTIC = new SimpleRegistry<>(Statistic.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public static final Registry<Villager.Profession> VILLAGER_PROFESSION = new SimpleRegistry<>(Villager.Profession.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public static final Registry<Villager.Type> VILLAGER_TYPE = new SimpleRegistry<>(Villager.Type.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public static final Registry<MemoryKey> MEMORY_MODULE_TYPE = new Registry<MemoryKey>()
/*     */     {
/*     */       @NotNull
/*     */       public Iterator iterator()
/*     */       {
/* 152 */         return MemoryKey.values().iterator();
/*     */       }
/*     */ 
/*     */       
/*     */       @Nullable
/*     */       public MemoryKey get(@NotNull NamespacedKey key) {
/* 158 */         return MemoryKey.getByKey(key);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public static final Registry<Fluid> FLUID = new SimpleRegistry<>(Fluid.class);
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   T get(@NotNull NamespacedKey paramNamespacedKey);
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class SimpleRegistry<T extends Enum<T> & Keyed>
/*     */     implements Registry<T>
/*     */   {
/*     */     private final Map<NamespacedKey, T> map;
/*     */ 
/*     */     
/*     */     protected SimpleRegistry(@NotNull Class<T> type) {
/* 182 */       this(type, (Predicate<T>)Predicates.alwaysTrue());
/*     */     }
/*     */     
/*     */     protected SimpleRegistry(@NotNull Class<T> type, @NotNull Predicate<T> predicate) {
/* 186 */       ImmutableMap.Builder<NamespacedKey, T> builder = ImmutableMap.builder();
/*     */       
/* 188 */       for (Enum enum_ : (Enum[])type.getEnumConstants()) {
/* 189 */         if (predicate.test((T)enum_)) {
/* 190 */           builder.put(((Keyed)enum_).getKey(), enum_);
/*     */         }
/*     */       } 
/*     */       
/* 194 */       this.map = (Map<NamespacedKey, T>)builder.build();
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public T get(@NotNull NamespacedKey key) {
/* 200 */       return this.map.get(key);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Iterator<T> iterator() {
/* 206 */       return this.map.values().iterator();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Registry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */