/*     */ package org.bukkit.block;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public enum Biome
/*     */   implements Keyed
/*     */ {
/*  12 */   OCEAN,
/*  13 */   PLAINS,
/*  14 */   DESERT,
/*  15 */   MOUNTAINS,
/*  16 */   FOREST,
/*  17 */   TAIGA,
/*  18 */   SWAMP,
/*  19 */   RIVER,
/*  20 */   NETHER_WASTES,
/*  21 */   THE_END,
/*  22 */   FROZEN_OCEAN,
/*  23 */   FROZEN_RIVER,
/*  24 */   SNOWY_TUNDRA,
/*  25 */   SNOWY_MOUNTAINS,
/*  26 */   MUSHROOM_FIELDS,
/*  27 */   MUSHROOM_FIELD_SHORE,
/*  28 */   BEACH,
/*  29 */   DESERT_HILLS,
/*  30 */   WOODED_HILLS,
/*  31 */   TAIGA_HILLS,
/*  32 */   MOUNTAIN_EDGE,
/*  33 */   JUNGLE,
/*  34 */   JUNGLE_HILLS,
/*  35 */   JUNGLE_EDGE,
/*  36 */   DEEP_OCEAN,
/*  37 */   STONE_SHORE,
/*  38 */   SNOWY_BEACH,
/*  39 */   BIRCH_FOREST,
/*  40 */   BIRCH_FOREST_HILLS,
/*  41 */   DARK_FOREST,
/*  42 */   SNOWY_TAIGA,
/*  43 */   SNOWY_TAIGA_HILLS,
/*  44 */   GIANT_TREE_TAIGA,
/*  45 */   GIANT_TREE_TAIGA_HILLS,
/*  46 */   WOODED_MOUNTAINS,
/*  47 */   SAVANNA,
/*  48 */   SAVANNA_PLATEAU,
/*  49 */   BADLANDS,
/*  50 */   WOODED_BADLANDS_PLATEAU,
/*  51 */   BADLANDS_PLATEAU,
/*  52 */   SMALL_END_ISLANDS,
/*  53 */   END_MIDLANDS,
/*  54 */   END_HIGHLANDS,
/*  55 */   END_BARRENS,
/*  56 */   WARM_OCEAN,
/*  57 */   LUKEWARM_OCEAN,
/*  58 */   COLD_OCEAN,
/*  59 */   DEEP_WARM_OCEAN,
/*  60 */   DEEP_LUKEWARM_OCEAN,
/*  61 */   DEEP_COLD_OCEAN,
/*  62 */   DEEP_FROZEN_OCEAN,
/*  63 */   THE_VOID,
/*  64 */   SUNFLOWER_PLAINS,
/*  65 */   DESERT_LAKES,
/*  66 */   GRAVELLY_MOUNTAINS,
/*  67 */   FLOWER_FOREST,
/*  68 */   TAIGA_MOUNTAINS,
/*  69 */   SWAMP_HILLS,
/*  70 */   ICE_SPIKES,
/*  71 */   MODIFIED_JUNGLE,
/*  72 */   MODIFIED_JUNGLE_EDGE,
/*  73 */   TALL_BIRCH_FOREST,
/*  74 */   TALL_BIRCH_HILLS,
/*  75 */   DARK_FOREST_HILLS,
/*  76 */   SNOWY_TAIGA_MOUNTAINS,
/*  77 */   GIANT_SPRUCE_TAIGA,
/*  78 */   GIANT_SPRUCE_TAIGA_HILLS,
/*  79 */   MODIFIED_GRAVELLY_MOUNTAINS,
/*  80 */   SHATTERED_SAVANNA,
/*  81 */   SHATTERED_SAVANNA_PLATEAU,
/*  82 */   ERODED_BADLANDS,
/*  83 */   MODIFIED_WOODED_BADLANDS_PLATEAU,
/*  84 */   MODIFIED_BADLANDS_PLATEAU,
/*  85 */   BAMBOO_JUNGLE,
/*  86 */   BAMBOO_JUNGLE_HILLS,
/*  87 */   SOUL_SAND_VALLEY,
/*  88 */   CRIMSON_FOREST,
/*  89 */   WARPED_FOREST,
/*  90 */   BASALT_DELTAS;
/*     */   
/*     */   private final NamespacedKey key;
/*     */   
/*     */   Biome() {
/*  95 */     this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 101 */     return this.key;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Biome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */