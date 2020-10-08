/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BiomeRegistry
/*     */ {
/*  11 */   private static final Int2ObjectMap<ResourceKey<BiomeBase>> c = (Int2ObjectMap<ResourceKey<BiomeBase>>)new Int2ObjectArrayMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  17 */     a(0, Biomes.OCEAN, BiomesSettingsDefault.c(false));
/*  18 */   } public static final BiomeBase a = a(1, Biomes.PLAINS, BiomesSettingsDefault.a(false)); static {
/*  19 */     a(2, Biomes.DESERT, BiomesSettingsDefault.a(0.125F, 0.05F, true, true, true));
/*  20 */     a(3, Biomes.MOUNTAINS, BiomesSettingsDefault.a(1.0F, 0.5F, WorldGenSurfaceComposites.m, false));
/*  21 */     a(4, Biomes.FOREST, BiomesSettingsDefault.c(0.1F, 0.2F));
/*  22 */     a(5, Biomes.TAIGA, BiomesSettingsDefault.a(0.2F, 0.2F, false, false, true, false));
/*  23 */     a(6, Biomes.SWAMP, BiomesSettingsDefault.d(-0.2F, 0.1F, false));
/*  24 */     a(7, Biomes.RIVER, BiomesSettingsDefault.a(-0.5F, 0.0F, 0.5F, 4159204, false));
/*  25 */     a(8, Biomes.NETHER_WASTES, BiomesSettingsDefault.s());
/*  26 */     a(9, Biomes.THE_END, BiomesSettingsDefault.i());
/*  27 */     a(10, Biomes.FROZEN_OCEAN, BiomesSettingsDefault.e(false));
/*  28 */     a(11, Biomes.FROZEN_RIVER, BiomesSettingsDefault.a(-0.5F, 0.0F, 0.0F, 3750089, true));
/*  29 */     a(12, Biomes.SNOWY_TUNDRA, BiomesSettingsDefault.a(0.125F, 0.05F, false, false));
/*  30 */     a(13, Biomes.SNOWY_MOUNTAINS, BiomesSettingsDefault.a(0.45F, 0.3F, false, true));
/*  31 */     a(14, Biomes.MUSHROOM_FIELDS, BiomesSettingsDefault.a(0.2F, 0.3F));
/*  32 */     a(15, Biomes.MUSHROOM_FIELD_SHORE, BiomesSettingsDefault.a(0.0F, 0.025F));
/*  33 */     a(16, Biomes.BEACH, BiomesSettingsDefault.a(0.0F, 0.025F, 0.8F, 0.4F, 4159204, false, false));
/*  34 */     a(17, Biomes.DESERT_HILLS, BiomesSettingsDefault.a(0.45F, 0.3F, false, true, false));
/*  35 */     a(18, Biomes.WOODED_HILLS, BiomesSettingsDefault.c(0.45F, 0.3F));
/*  36 */     a(19, Biomes.TAIGA_HILLS, BiomesSettingsDefault.a(0.45F, 0.3F, false, false, false, false));
/*  37 */     a(20, Biomes.MOUNTAIN_EDGE, BiomesSettingsDefault.a(0.8F, 0.3F, WorldGenSurfaceComposites.j, true));
/*  38 */     a(21, Biomes.JUNGLE, BiomesSettingsDefault.a());
/*  39 */     a(22, Biomes.JUNGLE_HILLS, BiomesSettingsDefault.e());
/*  40 */     a(23, Biomes.JUNGLE_EDGE, BiomesSettingsDefault.b());
/*  41 */     a(24, Biomes.DEEP_OCEAN, BiomesSettingsDefault.c(true));
/*  42 */     a(25, Biomes.STONE_SHORE, BiomesSettingsDefault.a(0.1F, 0.8F, 0.2F, 0.3F, 4159204, false, true));
/*  43 */     a(26, Biomes.SNOWY_BEACH, BiomesSettingsDefault.a(0.0F, 0.025F, 0.05F, 0.3F, 4020182, true, false));
/*  44 */     a(27, Biomes.BIRCH_FOREST, BiomesSettingsDefault.a(0.1F, 0.2F, false));
/*  45 */     a(28, Biomes.BIRCH_FOREST_HILLS, BiomesSettingsDefault.a(0.45F, 0.3F, false));
/*  46 */     a(29, Biomes.DARK_FOREST, BiomesSettingsDefault.c(0.1F, 0.2F, false));
/*  47 */     a(30, Biomes.SNOWY_TAIGA, BiomesSettingsDefault.a(0.2F, 0.2F, true, false, false, true));
/*  48 */     a(31, Biomes.SNOWY_TAIGA_HILLS, BiomesSettingsDefault.a(0.45F, 0.3F, true, false, false, false));
/*  49 */     a(32, Biomes.GIANT_TREE_TAIGA, BiomesSettingsDefault.a(0.2F, 0.2F, 0.3F, false));
/*  50 */     a(33, Biomes.GIANT_TREE_TAIGA_HILLS, BiomesSettingsDefault.a(0.45F, 0.3F, 0.3F, false));
/*  51 */     a(34, Biomes.WOODED_MOUNTAINS, BiomesSettingsDefault.a(1.0F, 0.5F, WorldGenSurfaceComposites.j, true));
/*  52 */     a(35, Biomes.SAVANNA, BiomesSettingsDefault.a(0.125F, 0.05F, 1.2F, false, false));
/*  53 */     a(36, Biomes.SAVANNA_PLATEAU, BiomesSettingsDefault.m());
/*  54 */     a(37, Biomes.BADLANDS, BiomesSettingsDefault.b(0.1F, 0.2F, false));
/*  55 */     a(38, Biomes.WOODED_BADLANDS_PLATEAU, BiomesSettingsDefault.b(1.5F, 0.025F));
/*  56 */     a(39, Biomes.BADLANDS_PLATEAU, BiomesSettingsDefault.b(1.5F, 0.025F, true));
/*  57 */     a(40, Biomes.SMALL_END_ISLANDS, BiomesSettingsDefault.l());
/*  58 */     a(41, Biomes.END_MIDLANDS, BiomesSettingsDefault.j());
/*  59 */     a(42, Biomes.END_HIGHLANDS, BiomesSettingsDefault.k());
/*  60 */     a(43, Biomes.END_BARRENS, BiomesSettingsDefault.h());
/*  61 */     a(44, Biomes.WARM_OCEAN, BiomesSettingsDefault.o());
/*  62 */     a(45, Biomes.LUKEWARM_OCEAN, BiomesSettingsDefault.d(false));
/*  63 */     a(46, Biomes.COLD_OCEAN, BiomesSettingsDefault.b(false));
/*  64 */     a(47, Biomes.DEEP_WARM_OCEAN, BiomesSettingsDefault.p());
/*  65 */     a(48, Biomes.DEEP_LUKEWARM_OCEAN, BiomesSettingsDefault.d(true));
/*  66 */     a(49, Biomes.DEEP_COLD_OCEAN, BiomesSettingsDefault.b(true));
/*  67 */     a(50, Biomes.DEEP_FROZEN_OCEAN, BiomesSettingsDefault.e(true));
/*     */   }
/*  69 */   public static final BiomeBase b = a(127, Biomes.THE_VOID, BiomesSettingsDefault.r());
/*     */   static {
/*  71 */     a(129, Biomes.SUNFLOWER_PLAINS, BiomesSettingsDefault.a(true));
/*  72 */     a(130, Biomes.DESERT_LAKES, BiomesSettingsDefault.a(0.225F, 0.25F, false, false, false));
/*  73 */     a(131, Biomes.GRAVELLY_MOUNTAINS, BiomesSettingsDefault.a(1.0F, 0.5F, WorldGenSurfaceComposites.k, false));
/*  74 */     a(132, Biomes.FLOWER_FOREST, BiomesSettingsDefault.q());
/*  75 */     a(133, Biomes.TAIGA_MOUNTAINS, BiomesSettingsDefault.a(0.3F, 0.4F, false, true, false, false));
/*  76 */     a(134, Biomes.SWAMP_HILLS, BiomesSettingsDefault.d(-0.1F, 0.3F, true));
/*  77 */     a(140, Biomes.ICE_SPIKES, BiomesSettingsDefault.a(0.425F, 0.45000002F, true, false));
/*  78 */     a(149, Biomes.MODIFIED_JUNGLE, BiomesSettingsDefault.d());
/*  79 */     a(151, Biomes.MODIFIED_JUNGLE_EDGE, BiomesSettingsDefault.c());
/*     */     
/*  81 */     a(155, Biomes.TALL_BIRCH_FOREST, BiomesSettingsDefault.a(0.2F, 0.4F, true));
/*  82 */     a(156, Biomes.TALL_BIRCH_HILLS, BiomesSettingsDefault.a(0.55F, 0.5F, true));
/*  83 */     a(157, Biomes.DARK_FOREST_HILLS, BiomesSettingsDefault.c(0.2F, 0.4F, true));
/*  84 */     a(158, Biomes.SNOWY_TAIGA_MOUNTAINS, BiomesSettingsDefault.a(0.3F, 0.4F, true, true, false, false));
/*  85 */     a(160, Biomes.GIANT_SPRUCE_TAIGA, BiomesSettingsDefault.a(0.2F, 0.2F, 0.25F, true));
/*  86 */     a(161, Biomes.GIANT_SPRUCE_TAIGA_HILLS, BiomesSettingsDefault.a(0.2F, 0.2F, 0.25F, true));
/*  87 */     a(162, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, BiomesSettingsDefault.a(1.0F, 0.5F, WorldGenSurfaceComposites.k, false));
/*  88 */     a(163, Biomes.SHATTERED_SAVANNA, BiomesSettingsDefault.a(0.3625F, 1.225F, 1.1F, true, true));
/*  89 */     a(164, Biomes.SHATTERED_SAVANNA_PLATEAU, BiomesSettingsDefault.a(1.05F, 1.2125001F, 1.0F, true, true));
/*  90 */     a(165, Biomes.ERODED_BADLANDS, BiomesSettingsDefault.n());
/*  91 */     a(166, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, BiomesSettingsDefault.b(0.45F, 0.3F));
/*  92 */     a(167, Biomes.MODIFIED_BADLANDS_PLATEAU, BiomesSettingsDefault.b(0.45F, 0.3F, true));
/*  93 */     a(168, Biomes.BAMBOO_JUNGLE, BiomesSettingsDefault.f());
/*  94 */     a(169, Biomes.BAMBOO_JUNGLE_HILLS, BiomesSettingsDefault.g());
/*     */     
/*  96 */     a(170, Biomes.SOUL_SAND_VALLEY, BiomesSettingsDefault.t());
/*  97 */     a(171, Biomes.CRIMSON_FOREST, BiomesSettingsDefault.v());
/*  98 */     a(172, Biomes.WARPED_FOREST, BiomesSettingsDefault.w());
/*  99 */     a(173, Biomes.BASALT_DELTAS, BiomesSettingsDefault.u());
/*     */   }
/*     */   
/*     */   private static BiomeBase a(int var0, ResourceKey<BiomeBase> var1, BiomeBase var2) {
/* 103 */     c.put(var0, var1);
/* 104 */     return RegistryGeneration.<BiomeBase, BiomeBase>a(RegistryGeneration.WORLDGEN_BIOME, var0, var1, var2);
/*     */   }
/*     */   
/*     */   public static ResourceKey<BiomeBase> a(int var0) {
/* 108 */     return (ResourceKey<BiomeBase>)c.get(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */