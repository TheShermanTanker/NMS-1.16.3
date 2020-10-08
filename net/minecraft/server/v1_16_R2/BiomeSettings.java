/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BiomeSettings
/*     */ {
/*     */   public static void a(BiomeSettingsGeneration.a var0) {
/*  11 */     var0.a(StructureFeatures.c);
/*  12 */     var0.a(StructureFeatures.k);
/*     */   }
/*     */   
/*     */   public static void b(BiomeSettingsGeneration.a var0) {
/*  16 */     var0.a(StructureFeatures.b);
/*  17 */     var0.a(StructureFeatures.k);
/*     */   }
/*     */   
/*     */   public static void c(BiomeSettingsGeneration.a var0) {
/*  21 */     var0.a(StructureFeatures.b);
/*  22 */     var0.a(StructureFeatures.h);
/*     */   }
/*     */   
/*     */   public static void d(BiomeSettingsGeneration.a var0) {
/*  26 */     var0.a(WorldGenStage.Features.AIR, WorldGenCarvers.a);
/*  27 */     var0.a(WorldGenStage.Features.AIR, WorldGenCarvers.b);
/*     */   }
/*     */   
/*     */   public static void e(BiomeSettingsGeneration.a var0) {
/*  31 */     var0.a(WorldGenStage.Features.AIR, WorldGenCarvers.c);
/*  32 */     var0.a(WorldGenStage.Features.AIR, WorldGenCarvers.b);
/*  33 */     var0.a(WorldGenStage.Features.LIQUID, WorldGenCarvers.d);
/*  34 */     var0.a(WorldGenStage.Features.LIQUID, WorldGenCarvers.e);
/*     */   }
/*     */   
/*     */   public static void f(BiomeSettingsGeneration.a var0) {
/*  38 */     var0.a(WorldGenStage.Decoration.LAKES, BiomeDecoratorGroups.LAKE_WATER);
/*  39 */     var0.a(WorldGenStage.Decoration.LAKES, BiomeDecoratorGroups.LAKE_LAVA);
/*     */   }
/*     */   
/*     */   public static void g(BiomeSettingsGeneration.a var0) {
/*  43 */     var0.a(WorldGenStage.Decoration.LAKES, BiomeDecoratorGroups.LAKE_LAVA);
/*     */   }
/*     */   
/*     */   public static void h(BiomeSettingsGeneration.a var0) {
/*  47 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_STRUCTURES, BiomeDecoratorGroups.MONSTER_ROOM);
/*     */   }
/*     */   
/*     */   public static void i(BiomeSettingsGeneration.a var0) {
/*  51 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_DIRT);
/*  52 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_GRAVEL);
/*  53 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_GRANITE);
/*  54 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_DIORITE);
/*  55 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_ANDESITE);
/*     */   }
/*     */   
/*     */   public static void j(BiomeSettingsGeneration.a var0) {
/*  59 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_COAL);
/*  60 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_IRON);
/*  61 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_GOLD);
/*  62 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_REDSTONE);
/*  63 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_DIAMOND);
/*  64 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_LAPIS);
/*     */   }
/*     */   
/*     */   public static void k(BiomeSettingsGeneration.a var0) {
/*  68 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_GOLD_EXTRA);
/*     */   }
/*     */   
/*     */   public static void l(BiomeSettingsGeneration.a var0) {
/*  72 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.ORE_EMERALD);
/*     */   }
/*     */   
/*     */   public static void m(BiomeSettingsGeneration.a var0) {
/*  76 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_INFESTED);
/*     */   }
/*     */   
/*     */   public static void n(BiomeSettingsGeneration.a var0) {
/*  80 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.DISK_SAND);
/*  81 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.DISK_CLAY);
/*  82 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.DISK_GRAVEL);
/*     */   }
/*     */   
/*     */   public static void o(BiomeSettingsGeneration.a var0) {
/*  86 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeDecoratorGroups.DISK_CLAY);
/*     */   }
/*     */   
/*     */   public static void p(BiomeSettingsGeneration.a var0) {
/*  90 */     var0.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeDecoratorGroups.FOREST_ROCK);
/*     */   }
/*     */   
/*     */   public static void q(BiomeSettingsGeneration.a var0) {
/*  94 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_LARGE_FERN);
/*     */   }
/*     */   
/*     */   public static void r(BiomeSettingsGeneration.a var0) {
/*  98 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_BERRY_DECORATED);
/*     */   }
/*     */   
/*     */   public static void s(BiomeSettingsGeneration.a var0) {
/* 102 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_BERRY_SPARSE);
/*     */   }
/*     */   
/*     */   public static void t(BiomeSettingsGeneration.a var0) {
/* 106 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BAMBOO_LIGHT);
/*     */   }
/*     */   
/*     */   public static void u(BiomeSettingsGeneration.a var0) {
/* 110 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BAMBOO);
/* 111 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BAMBOO_VEGETATION);
/*     */   }
/*     */   
/*     */   public static void v(BiomeSettingsGeneration.a var0) {
/* 115 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TAIGA_VEGETATION);
/*     */   }
/*     */   
/*     */   public static void w(BiomeSettingsGeneration.a var0) {
/* 119 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TREES_WATER);
/*     */   }
/*     */   
/*     */   public static void x(BiomeSettingsGeneration.a var0) {
/* 123 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TREES_BIRCH);
/*     */   }
/*     */   
/*     */   public static void y(BiomeSettingsGeneration.a var0) {
/* 127 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BIRCH_OTHER);
/*     */   }
/*     */   
/*     */   public static void z(BiomeSettingsGeneration.a var0) {
/* 131 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BIRCH_TALL);
/*     */   }
/*     */   
/*     */   public static void A(BiomeSettingsGeneration.a var0) {
/* 135 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TREES_SAVANNA);
/*     */   }
/*     */   
/*     */   public static void B(BiomeSettingsGeneration.a var0) {
/* 139 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TREES_SHATTERED_SAVANNA);
/*     */   }
/*     */   
/*     */   public static void C(BiomeSettingsGeneration.a var0) {
/* 143 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TREES_MOUNTAIN);
/*     */   }
/*     */   
/*     */   public static void D(BiomeSettingsGeneration.a var0) {
/* 147 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TREES_MOUNTAIN_EDGE);
/*     */   }
/*     */   
/*     */   public static void E(BiomeSettingsGeneration.a var0) {
/* 151 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TREES_JUNGLE);
/*     */   }
/*     */   
/*     */   public static void F(BiomeSettingsGeneration.a var0) {
/* 155 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TREES_JUNGLE_EDGE);
/*     */   }
/*     */   
/*     */   public static void G(BiomeSettingsGeneration.a var0) {
/* 159 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.OAK_BADLANDS);
/*     */   }
/*     */   
/*     */   public static void H(BiomeSettingsGeneration.a var0) {
/* 163 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SPRUCE_SNOWY);
/*     */   }
/*     */   
/*     */   public static void I(BiomeSettingsGeneration.a var0) {
/* 167 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_JUNGLE);
/*     */   }
/*     */   
/*     */   public static void J(BiomeSettingsGeneration.a var0) {
/* 171 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_TALL_GRASS);
/*     */   }
/*     */   
/*     */   public static void K(BiomeSettingsGeneration.a var0) {
/* 175 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_NORMAL);
/*     */   }
/*     */   
/*     */   public static void L(BiomeSettingsGeneration.a var0) {
/* 179 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_SAVANNA);
/*     */   }
/*     */   
/*     */   public static void M(BiomeSettingsGeneration.a var0) {
/* 183 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_BADLANDS);
/* 184 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_DEAD_BUSH_BADLANDS);
/*     */   }
/*     */   
/*     */   public static void N(BiomeSettingsGeneration.a var0) {
/* 188 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.FOREST_FLOWER_VEGETATION);
/*     */   }
/*     */   
/*     */   public static void O(BiomeSettingsGeneration.a var0) {
/* 192 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_FOREST);
/*     */   }
/*     */   
/*     */   public static void P(BiomeSettingsGeneration.a var0) {
/* 196 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SWAMP_TREE);
/* 197 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.FLOWER_SWAMP);
/* 198 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_NORMAL);
/* 199 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_DEAD_BUSH);
/* 200 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_WATERLILLY);
/* 201 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BROWN_MUSHROOM_SWAMP);
/* 202 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.RED_MUSHROOM_SWAMP);
/*     */   }
/*     */   
/*     */   public static void Q(BiomeSettingsGeneration.a var0) {
/* 206 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.MUSHROOM_FIELD_VEGETATION);
/* 207 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BROWN_MUSHROOM_TAIGA);
/* 208 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.RED_MUSHROOM_TAIGA);
/*     */   }
/*     */   
/*     */   public static void R(BiomeSettingsGeneration.a var0) {
/* 212 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PLAIN_VEGETATION);
/* 213 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.FLOWER_PLAIN_DECORATED);
/* 214 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_PLAIN);
/*     */   }
/*     */   
/*     */   public static void S(BiomeSettingsGeneration.a var0) {
/* 218 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_DEAD_BUSH_2);
/*     */   }
/*     */   
/*     */   public static void T(BiomeSettingsGeneration.a var0) {
/* 222 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_TAIGA);
/* 223 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_DEAD_BUSH);
/* 224 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BROWN_MUSHROOM_GIANT);
/* 225 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.RED_MUSHROOM_GIANT);
/*     */   }
/*     */   
/*     */   public static void U(BiomeSettingsGeneration.a var0) {
/* 229 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.FLOWER_DEFAULT);
/*     */   }
/*     */   
/*     */   public static void V(BiomeSettingsGeneration.a var0) {
/* 233 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.FLOWER_WARM);
/*     */   }
/*     */   
/*     */   public static void W(BiomeSettingsGeneration.a var0) {
/* 237 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_BADLANDS);
/*     */   }
/*     */   
/*     */   public static void X(BiomeSettingsGeneration.a var0) {
/* 241 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_GRASS_TAIGA_2);
/* 242 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BROWN_MUSHROOM_TAIGA);
/* 243 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.RED_MUSHROOM_TAIGA);
/*     */   }
/*     */   
/*     */   public static void Y(BiomeSettingsGeneration.a var0) {
/* 247 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_TALL_GRASS_2);
/*     */   }
/*     */   
/*     */   public static void Z(BiomeSettingsGeneration.a var0) {
/* 251 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.BROWN_MUSHROOM_NORMAL);
/* 252 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.RED_MUSHROOM_NORMAL);
/*     */   }
/*     */   
/*     */   public static void aa(BiomeSettingsGeneration.a var0) {
/* 256 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_SUGAR_CANE);
/* 257 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_PUMPKIN);
/*     */   }
/*     */   
/*     */   public static void ab(BiomeSettingsGeneration.a var0) {
/* 261 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_SUGAR_CANE_BADLANDS);
/* 262 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_PUMPKIN);
/* 263 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_CACTUS_DECORATED);
/*     */   }
/*     */   
/*     */   public static void ac(BiomeSettingsGeneration.a var0) {
/* 267 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_MELON);
/* 268 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.VINES);
/*     */   }
/*     */   
/*     */   public static void ad(BiomeSettingsGeneration.a var0) {
/* 272 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_SUGAR_CANE_DESERT);
/* 273 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_PUMPKIN);
/* 274 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_CACTUS_DESERT);
/*     */   }
/*     */   
/*     */   public static void ae(BiomeSettingsGeneration.a var0) {
/* 278 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_SUGAR_CANE_SWAMP);
/* 279 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_PUMPKIN);
/*     */   }
/*     */   
/*     */   public static void af(BiomeSettingsGeneration.a var0) {
/* 283 */     var0.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.DESERT_WELL);
/*     */   }
/*     */   
/*     */   public static void ag(BiomeSettingsGeneration.a var0) {
/* 287 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_STRUCTURES, BiomeDecoratorGroups.FOSSIL);
/*     */   }
/*     */   
/*     */   public static void ah(BiomeSettingsGeneration.a var0) {
/* 291 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.KELP_COLD);
/*     */   }
/*     */   
/*     */   public static void ai(BiomeSettingsGeneration.a var0) {
/* 295 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SEAGRASS_SIMPLE);
/*     */   }
/*     */   
/*     */   public static void aj(BiomeSettingsGeneration.a var0) {
/* 299 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.KELP_WARM);
/*     */   }
/*     */   
/*     */   public static void ak(BiomeSettingsGeneration.a var0) {
/* 303 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SPRING_WATER);
/* 304 */     var0.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SPRING_LAVA);
/*     */   }
/*     */   
/*     */   public static void al(BiomeSettingsGeneration.a var0) {
/* 308 */     var0.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeDecoratorGroups.ICEBERG_PACKED);
/* 309 */     var0.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeDecoratorGroups.ICEBERG_BLUE);
/*     */   }
/*     */   
/*     */   public static void am(BiomeSettingsGeneration.a var0) {
/* 313 */     var0.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.BLUE_ICE);
/*     */   }
/*     */   
/*     */   public static void an(BiomeSettingsGeneration.a var0) {
/* 317 */     var0.a(WorldGenStage.Decoration.TOP_LAYER_MODIFICATION, BiomeDecoratorGroups.FREEZE_TOP_LAYER);
/*     */   }
/*     */   
/*     */   public static void ao(BiomeSettingsGeneration.a var0) {
/* 321 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_GRAVEL_NETHER);
/* 322 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_BLACKSTONE);
/*     */     
/* 324 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_GOLD_NETHER);
/* 325 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_QUARTZ_NETHER);
/* 326 */     ap(var0);
/*     */   }
/*     */   
/*     */   public static void ap(BiomeSettingsGeneration.a var0) {
/* 330 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_DEBRIS_LARGE);
/* 331 */     var0.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_DEBRIS_SMALL);
/*     */   }
/*     */   
/*     */   public static void a(BiomeSettingsMobs.a var0) {
/* 335 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.SHEEP, 12, 4, 4));
/* 336 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.PIG, 10, 4, 4));
/* 337 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.CHICKEN, 10, 4, 4));
/* 338 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.COW, 8, 4, 4));
/*     */   }
/*     */   
/*     */   public static void b(BiomeSettingsMobs.a var0) {
/* 342 */     var0.a(EnumCreatureType.AMBIENT, new BiomeSettingsMobs.c(EntityTypes.BAT, 10, 8, 8));
/*     */   }
/*     */   
/*     */   public static void c(BiomeSettingsMobs.a var0) {
/* 346 */     b(var0);
/* 347 */     b(var0, 95, 5, 100);
/*     */   }
/*     */   
/*     */   public static void a(BiomeSettingsMobs.a var0, int var1, int var2, int var3) {
/* 351 */     var0.a(EnumCreatureType.WATER_CREATURE, new BiomeSettingsMobs.c(EntityTypes.SQUID, var1, 1, var2));
/* 352 */     var0.a(EnumCreatureType.WATER_AMBIENT, new BiomeSettingsMobs.c(EntityTypes.COD, var3, 3, 6));
/* 353 */     c(var0);
/* 354 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.DROWNED, 5, 1, 1));
/*     */   }
/*     */   
/*     */   public static void a(BiomeSettingsMobs.a var0, int var1, int var2) {
/* 358 */     var0.a(EnumCreatureType.WATER_CREATURE, new BiomeSettingsMobs.c(EntityTypes.SQUID, var1, var2, 4));
/* 359 */     var0.a(EnumCreatureType.WATER_AMBIENT, new BiomeSettingsMobs.c(EntityTypes.TROPICAL_FISH, 25, 8, 8));
/* 360 */     var0.a(EnumCreatureType.WATER_CREATURE, new BiomeSettingsMobs.c(EntityTypes.DOLPHIN, 2, 1, 2));
/* 361 */     c(var0);
/*     */   }
/*     */   
/*     */   public static void d(BiomeSettingsMobs.a var0) {
/* 365 */     a(var0);
/* 366 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.HORSE, 5, 2, 6));
/* 367 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.DONKEY, 1, 1, 3));
/* 368 */     c(var0);
/*     */   }
/*     */   
/*     */   public static void e(BiomeSettingsMobs.a var0) {
/* 372 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.RABBIT, 10, 2, 3));
/* 373 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.POLAR_BEAR, 1, 1, 2));
/* 374 */     b(var0);
/* 375 */     b(var0, 95, 5, 20);
/* 376 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.STRAY, 80, 4, 4));
/*     */   }
/*     */   
/*     */   public static void f(BiomeSettingsMobs.a var0) {
/* 380 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.RABBIT, 4, 2, 3));
/* 381 */     b(var0);
/* 382 */     b(var0, 19, 1, 100);
/* 383 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.HUSK, 80, 4, 4));
/*     */   }
/*     */   
/*     */   public static void b(BiomeSettingsMobs.a var0, int var1, int var2, int var3) {
/* 387 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.SPIDER, 100, 4, 4));
/* 388 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ZOMBIE, var1, 4, 4));
/* 389 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ZOMBIE_VILLAGER, var2, 1, 1));
/* 390 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.SKELETON, var3, 4, 4));
/* 391 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.CREEPER, 100, 4, 4));
/* 392 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.SLIME, 100, 4, 4));
/* 393 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ENDERMAN, 10, 1, 4));
/* 394 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.WITCH, 5, 1, 1));
/*     */   }
/*     */   
/*     */   public static void g(BiomeSettingsMobs.a var0) {
/* 398 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.MOOSHROOM, 8, 4, 8));
/* 399 */     b(var0);
/*     */   }
/*     */   
/*     */   public static void h(BiomeSettingsMobs.a var0) {
/* 403 */     a(var0);
/* 404 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.CHICKEN, 10, 4, 4));
/* 405 */     c(var0);
/*     */   }
/*     */   
/*     */   public static void i(BiomeSettingsMobs.a var0) {
/* 409 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ENDERMAN, 10, 4, 4));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */