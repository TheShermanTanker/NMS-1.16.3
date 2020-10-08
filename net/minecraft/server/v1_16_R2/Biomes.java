/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Biomes
/*    */ {
/*  8 */   public static final ResourceKey<BiomeBase> OCEAN = a("ocean");
/*  9 */   public static final ResourceKey<BiomeBase> PLAINS = a("plains");
/* 10 */   public static final ResourceKey<BiomeBase> DESERT = a("desert");
/* 11 */   public static final ResourceKey<BiomeBase> MOUNTAINS = a("mountains");
/* 12 */   public static final ResourceKey<BiomeBase> FOREST = a("forest");
/* 13 */   public static final ResourceKey<BiomeBase> TAIGA = a("taiga");
/* 14 */   public static final ResourceKey<BiomeBase> SWAMP = a("swamp");
/* 15 */   public static final ResourceKey<BiomeBase> RIVER = a("river");
/* 16 */   public static final ResourceKey<BiomeBase> NETHER_WASTES = a("nether_wastes");
/* 17 */   public static final ResourceKey<BiomeBase> THE_END = a("the_end");
/* 18 */   public static final ResourceKey<BiomeBase> FROZEN_OCEAN = a("frozen_ocean");
/* 19 */   public static final ResourceKey<BiomeBase> FROZEN_RIVER = a("frozen_river");
/* 20 */   public static final ResourceKey<BiomeBase> SNOWY_TUNDRA = a("snowy_tundra");
/* 21 */   public static final ResourceKey<BiomeBase> SNOWY_MOUNTAINS = a("snowy_mountains");
/* 22 */   public static final ResourceKey<BiomeBase> MUSHROOM_FIELDS = a("mushroom_fields");
/* 23 */   public static final ResourceKey<BiomeBase> MUSHROOM_FIELD_SHORE = a("mushroom_field_shore");
/* 24 */   public static final ResourceKey<BiomeBase> BEACH = a("beach");
/* 25 */   public static final ResourceKey<BiomeBase> DESERT_HILLS = a("desert_hills");
/* 26 */   public static final ResourceKey<BiomeBase> WOODED_HILLS = a("wooded_hills");
/* 27 */   public static final ResourceKey<BiomeBase> TAIGA_HILLS = a("taiga_hills");
/* 28 */   public static final ResourceKey<BiomeBase> MOUNTAIN_EDGE = a("mountain_edge");
/* 29 */   public static final ResourceKey<BiomeBase> JUNGLE = a("jungle");
/* 30 */   public static final ResourceKey<BiomeBase> JUNGLE_HILLS = a("jungle_hills");
/* 31 */   public static final ResourceKey<BiomeBase> JUNGLE_EDGE = a("jungle_edge");
/* 32 */   public static final ResourceKey<BiomeBase> DEEP_OCEAN = a("deep_ocean");
/* 33 */   public static final ResourceKey<BiomeBase> STONE_SHORE = a("stone_shore");
/* 34 */   public static final ResourceKey<BiomeBase> SNOWY_BEACH = a("snowy_beach");
/* 35 */   public static final ResourceKey<BiomeBase> BIRCH_FOREST = a("birch_forest");
/* 36 */   public static final ResourceKey<BiomeBase> BIRCH_FOREST_HILLS = a("birch_forest_hills");
/* 37 */   public static final ResourceKey<BiomeBase> DARK_FOREST = a("dark_forest");
/* 38 */   public static final ResourceKey<BiomeBase> SNOWY_TAIGA = a("snowy_taiga");
/* 39 */   public static final ResourceKey<BiomeBase> SNOWY_TAIGA_HILLS = a("snowy_taiga_hills");
/* 40 */   public static final ResourceKey<BiomeBase> GIANT_TREE_TAIGA = a("giant_tree_taiga");
/* 41 */   public static final ResourceKey<BiomeBase> GIANT_TREE_TAIGA_HILLS = a("giant_tree_taiga_hills");
/* 42 */   public static final ResourceKey<BiomeBase> WOODED_MOUNTAINS = a("wooded_mountains");
/* 43 */   public static final ResourceKey<BiomeBase> SAVANNA = a("savanna");
/* 44 */   public static final ResourceKey<BiomeBase> SAVANNA_PLATEAU = a("savanna_plateau");
/* 45 */   public static final ResourceKey<BiomeBase> BADLANDS = a("badlands");
/* 46 */   public static final ResourceKey<BiomeBase> WOODED_BADLANDS_PLATEAU = a("wooded_badlands_plateau");
/* 47 */   public static final ResourceKey<BiomeBase> BADLANDS_PLATEAU = a("badlands_plateau");
/* 48 */   public static final ResourceKey<BiomeBase> SMALL_END_ISLANDS = a("small_end_islands");
/* 49 */   public static final ResourceKey<BiomeBase> END_MIDLANDS = a("end_midlands");
/* 50 */   public static final ResourceKey<BiomeBase> END_HIGHLANDS = a("end_highlands");
/* 51 */   public static final ResourceKey<BiomeBase> END_BARRENS = a("end_barrens");
/* 52 */   public static final ResourceKey<BiomeBase> WARM_OCEAN = a("warm_ocean");
/* 53 */   public static final ResourceKey<BiomeBase> LUKEWARM_OCEAN = a("lukewarm_ocean");
/* 54 */   public static final ResourceKey<BiomeBase> COLD_OCEAN = a("cold_ocean");
/* 55 */   public static final ResourceKey<BiomeBase> DEEP_WARM_OCEAN = a("deep_warm_ocean");
/* 56 */   public static final ResourceKey<BiomeBase> DEEP_LUKEWARM_OCEAN = a("deep_lukewarm_ocean");
/* 57 */   public static final ResourceKey<BiomeBase> DEEP_COLD_OCEAN = a("deep_cold_ocean");
/* 58 */   public static final ResourceKey<BiomeBase> DEEP_FROZEN_OCEAN = a("deep_frozen_ocean");
/*    */   
/* 60 */   public static final ResourceKey<BiomeBase> THE_VOID = a("the_void");
/*    */   
/* 62 */   public static final ResourceKey<BiomeBase> SUNFLOWER_PLAINS = a("sunflower_plains");
/* 63 */   public static final ResourceKey<BiomeBase> DESERT_LAKES = a("desert_lakes");
/* 64 */   public static final ResourceKey<BiomeBase> GRAVELLY_MOUNTAINS = a("gravelly_mountains");
/* 65 */   public static final ResourceKey<BiomeBase> FLOWER_FOREST = a("flower_forest");
/* 66 */   public static final ResourceKey<BiomeBase> TAIGA_MOUNTAINS = a("taiga_mountains");
/* 67 */   public static final ResourceKey<BiomeBase> SWAMP_HILLS = a("swamp_hills");
/* 68 */   public static final ResourceKey<BiomeBase> ICE_SPIKES = a("ice_spikes");
/* 69 */   public static final ResourceKey<BiomeBase> MODIFIED_JUNGLE = a("modified_jungle");
/* 70 */   public static final ResourceKey<BiomeBase> MODIFIED_JUNGLE_EDGE = a("modified_jungle_edge");
/*    */   
/* 72 */   public static final ResourceKey<BiomeBase> TALL_BIRCH_FOREST = a("tall_birch_forest");
/* 73 */   public static final ResourceKey<BiomeBase> TALL_BIRCH_HILLS = a("tall_birch_hills");
/* 74 */   public static final ResourceKey<BiomeBase> DARK_FOREST_HILLS = a("dark_forest_hills");
/* 75 */   public static final ResourceKey<BiomeBase> SNOWY_TAIGA_MOUNTAINS = a("snowy_taiga_mountains");
/* 76 */   public static final ResourceKey<BiomeBase> GIANT_SPRUCE_TAIGA = a("giant_spruce_taiga");
/* 77 */   public static final ResourceKey<BiomeBase> GIANT_SPRUCE_TAIGA_HILLS = a("giant_spruce_taiga_hills");
/* 78 */   public static final ResourceKey<BiomeBase> MODIFIED_GRAVELLY_MOUNTAINS = a("modified_gravelly_mountains");
/* 79 */   public static final ResourceKey<BiomeBase> SHATTERED_SAVANNA = a("shattered_savanna");
/* 80 */   public static final ResourceKey<BiomeBase> SHATTERED_SAVANNA_PLATEAU = a("shattered_savanna_plateau");
/* 81 */   public static final ResourceKey<BiomeBase> ERODED_BADLANDS = a("eroded_badlands");
/* 82 */   public static final ResourceKey<BiomeBase> MODIFIED_WOODED_BADLANDS_PLATEAU = a("modified_wooded_badlands_plateau");
/* 83 */   public static final ResourceKey<BiomeBase> MODIFIED_BADLANDS_PLATEAU = a("modified_badlands_plateau");
/* 84 */   public static final ResourceKey<BiomeBase> BAMBOO_JUNGLE = a("bamboo_jungle");
/* 85 */   public static final ResourceKey<BiomeBase> BAMBOO_JUNGLE_HILLS = a("bamboo_jungle_hills");
/*    */   
/* 87 */   public static final ResourceKey<BiomeBase> SOUL_SAND_VALLEY = a("soul_sand_valley");
/* 88 */   public static final ResourceKey<BiomeBase> CRIMSON_FOREST = a("crimson_forest");
/* 89 */   public static final ResourceKey<BiomeBase> WARPED_FOREST = a("warped_forest");
/* 90 */   public static final ResourceKey<BiomeBase> BASALT_DELTAS = a("basalt_deltas");
/*    */   
/*    */   private static ResourceKey<BiomeBase> a(String var0) {
/* 93 */     return ResourceKey.a(IRegistry.ay, new MinecraftKey(var0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Biomes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */