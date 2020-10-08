/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.List;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
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
/*     */ public class BiomeDecoratorGroups
/*     */ {
/*  92 */   public static final WorldGenFeatureConfigured<?, ?> END_SPIKE = a("end_spike", WorldGenerator.END_SPIKE.b(new WorldGenFeatureEndSpikeConfiguration(false, (List<WorldGenEnder.Spike>)ImmutableList.of(), null)));
/*  93 */   public static final WorldGenFeatureConfigured<?, ?> END_GATEWAY = a("end_gateway", WorldGenerator.END_GATEWAY.b(WorldGenEndGatewayConfiguration.a(WorldServer.a, true)).a(WorldGenDecorator.x.b(WorldGenFeatureDecoratorConfiguration.b)));
/*  94 */   public static final WorldGenFeatureConfigured<?, ?> END_GATEWAY_DELAYED = a("end_gateway_delayed", WorldGenerator.END_GATEWAY.b(WorldGenEndGatewayConfiguration.b()));
/*  95 */   public static final WorldGenFeatureConfigured<?, ?> CHORUS_PLANT = a("chorus_plant", WorldGenerator.CHORUS_PLANT.b(WorldGenFeatureConfiguration.k).a(b.l).c(4));
/*  96 */   public static final WorldGenFeatureConfigured<?, ?> END_ISLAND = a("end_island", WorldGenerator.END_ISLAND.b(WorldGenFeatureConfiguration.k));
/*  97 */   public static final WorldGenFeatureConfigured<?, ?> END_ISLAND_DECORATED = a("end_island_decorated", END_ISLAND.a(WorldGenDecorator.A.b(WorldGenFeatureDecoratorConfiguration.b)));
/*     */ 
/*     */   
/* 100 */   public static final WorldGenFeatureConfigured<?, ?> DELTA = a("delta", WorldGenerator.DELTA_FEATURE.b(new WorldGenFeatureDeltaConfiguration(c.ac, c.av, IntSpread.a(3, 4), IntSpread.a(0, 2))).a(WorldGenDecorator.C.b(new WorldGenDecoratorFrequencyConfiguration(40))));
/* 101 */   public static final WorldGenFeatureConfigured<?, ?> SMALL_BASALT_COLUMNS = a("small_basalt_columns", WorldGenerator.BASALT_COLUMNS.b(new WorldGenFeatureBasaltColumnsConfiguration(IntSpread.a(1), IntSpread.a(1, 3))).a(WorldGenDecorator.C.b(new WorldGenDecoratorFrequencyConfiguration(4))));
/* 102 */   public static final WorldGenFeatureConfigured<?, ?> LARGE_BASALT_COLUMNS = a("large_basalt_columns", WorldGenerator.BASALT_COLUMNS.b(new WorldGenFeatureBasaltColumnsConfiguration(IntSpread.a(2, 1), IntSpread.a(5, 5))).a(WorldGenDecorator.C.b(new WorldGenDecoratorFrequencyConfiguration(2))));
/* 103 */   public static final WorldGenFeatureConfigured<?, ?> BASALT_BLOBS = a("basalt_blobs", WorldGenerator.NETHERRACK_REPLACE_BLOBS.b(new WorldGenFeatureRadiusConfiguration(c.N, c.aB, IntSpread.a(3, 4))).d(128).a().b(75));
/* 104 */   public static final WorldGenFeatureConfigured<?, ?> BLACKSTONE_BLOBS = a("blackstone_blobs", WorldGenerator.NETHERRACK_REPLACE_BLOBS.b(new WorldGenFeatureRadiusConfiguration(c.N, c.az, IntSpread.a(3, 4))).d(128).a().b(25));
/* 105 */   public static final WorldGenFeatureConfigured<?, ?> GLOWSTONE_EXTRA = a("glowstone_extra", WorldGenerator.GLOWSTONE_BLOB.b(WorldGenFeatureConfiguration.k).a(WorldGenDecorator.w.b(new WorldGenDecoratorFrequencyConfiguration(10))));
/* 106 */   public static final WorldGenFeatureConfigured<?, ?> GLOWSTONE = a("glowstone", WorldGenerator.GLOWSTONE_BLOB.b(WorldGenFeatureConfiguration.k).d(128).a().b(10));
/*     */   
/* 108 */   public static final WorldGenFeatureConfigured<?, ?> CRIMSON_FOREST_VEGETATION = a("crimson_forest_vegetation", WorldGenerator.NETHER_FOREST_VEGETATION.b(a.k).a(WorldGenDecorator.C.b(new WorldGenDecoratorFrequencyConfiguration(6))));
/* 109 */   public static final WorldGenFeatureConfigured<?, ?> WARPED_FOREST_VEGETATION = a("warped_forest_vegetation", WorldGenerator.NETHER_FOREST_VEGETATION.b(a.l).a(WorldGenDecorator.C.b(new WorldGenDecoratorFrequencyConfiguration(5))));
/* 110 */   public static final WorldGenFeatureConfigured<?, ?> NETHER_SPROUTS = a("nether_sprouts", WorldGenerator.NETHER_FOREST_VEGETATION.b(a.m).a(WorldGenDecorator.C.b(new WorldGenDecoratorFrequencyConfiguration(4))));
/*     */   
/* 112 */   public static final WorldGenFeatureConfigured<?, ?> TWISTING_VINES = a("twisting_vines", WorldGenerator.TWISTING_VINES.b(WorldGenFeatureConfiguration.k).d(128).a().b(10));
/* 113 */   public static final WorldGenFeatureConfigured<?, ?> WEEPING_VINES = a("weeping_vines", WorldGenerator.WEEPING_VINES.b(WorldGenFeatureConfiguration.k).d(128).a().b(10));
/* 114 */   public static final WorldGenFeatureConfigured<?, ?> BASALT_PILLAR = a("basalt_pillar", WorldGenerator.BASALT_PILLAR.b(WorldGenFeatureConfiguration.k).d(128).a().b(10));
/*     */ 
/*     */   
/* 117 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_COLD = a("seagrass_cold", WorldGenerator.SEAGRASS.b(new WorldGenFeatureConfigurationChance(0.3F)).b(32).a(b.n));
/* 118 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_DEEP_COLD = a("seagrass_deep_cold", WorldGenerator.SEAGRASS.b(new WorldGenFeatureConfigurationChance(0.8F)).b(40).a(b.n));
/* 119 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_NORMAL = a("seagrass_normal", WorldGenerator.SEAGRASS.b(new WorldGenFeatureConfigurationChance(0.3F)).b(48).a(b.n));
/* 120 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_RIVER = a("seagrass_river", WorldGenerator.SEAGRASS.b(new WorldGenFeatureConfigurationChance(0.4F)).b(48).a(b.n));
/* 121 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_DEEP = a("seagrass_deep", WorldGenerator.SEAGRASS.b(new WorldGenFeatureConfigurationChance(0.8F)).b(48).a(b.n));
/* 122 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_SWAMP = a("seagrass_swamp", WorldGenerator.SEAGRASS.b(new WorldGenFeatureConfigurationChance(0.6F)).b(64).a(b.n));
/* 123 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_WARM = a("seagrass_warm", WorldGenerator.SEAGRASS.b(new WorldGenFeatureConfigurationChance(0.3F)).b(80).a(b.n));
/* 124 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_DEEP_WARM = a("seagrass_deep_warm", WorldGenerator.SEAGRASS.b(new WorldGenFeatureConfigurationChance(0.8F)).b(80).a(b.n));
/*     */   
/* 126 */   public static final WorldGenFeatureConfigured<?, ?> SEA_PICKLE = a("sea_pickle", WorldGenerator.SEA_PICKLE.b(new WorldGenDecoratorFrequencyConfiguration(20)).a(b.n).a(16));
/* 127 */   public static final WorldGenFeatureConfigured<?, ?> ICE_SPIKE = a("ice_spike", WorldGenerator.ICE_SPIKE.b(WorldGenFeatureConfiguration.k).a(b.l).b(3));
/* 128 */   public static final WorldGenFeatureConfigured<?, ?> ICE_PATCH = a("ice_patch", WorldGenerator.ICE_PATCH.b(new WorldGenFeatureCircleConfiguration(c.B, IntSpread.a(2, 1), 1, (List<IBlockData>)ImmutableList.of(c.ad, c.t, c.c, c.d, c.e, c.f, c.g))).a(b.l).b(2));
/* 129 */   public static final WorldGenFeatureConfigured<?, ?> FOREST_ROCK = a("forest_rock", WorldGenerator.FOREST_ROCK.b(new WorldGenFeatureLakeConfiguration(c.at)).a(b.l).c(2));
/* 130 */   public static final WorldGenFeatureConfigured<?, ?> SEAGRASS_SIMPLE = a("seagrass_simple", WorldGenerator.SIMPLE_BLOCK.b(new WorldGenFeatureBlockConfiguration(c.au, (List<IBlockData>)ImmutableList.of(c.ao), (List<IBlockData>)ImmutableList.of(c.ab), (List<IBlockData>)ImmutableList.of(c.ab))).a(WorldGenDecorator.q.b(new WorldGenDecoratorCarveMaskConfiguration(WorldGenStage.Features.LIQUID, 0.1F))));
/* 131 */   public static final WorldGenFeatureConfigured<?, ?> ICEBERG_PACKED = a("iceberg_packed", WorldGenerator.ICEBERG.b(new WorldGenFeatureLakeConfiguration(c.B)).a(WorldGenDecorator.z.b(WorldGenFeatureEmptyConfiguration2.c)).a(16));
/* 132 */   public static final WorldGenFeatureConfigured<?, ?> ICEBERG_BLUE = a("iceberg_blue", WorldGenerator.ICEBERG.b(new WorldGenFeatureLakeConfiguration(c.C)).a(WorldGenDecorator.z.b(WorldGenFeatureEmptyConfiguration2.c)).a(200));
/*     */   
/* 134 */   public static final WorldGenFeatureConfigured<?, ?> KELP_COLD = a("kelp_cold", WorldGenerator.KELP.b(WorldGenFeatureConfiguration.k).a(b.f).a().a(WorldGenDecorator.e.b(new WorldGenDecoratorNoiseConfiguration(120, 80.0D, 0.0D))));
/* 135 */   public static final WorldGenFeatureConfigured<?, ?> KELP_WARM = a("kelp_warm", WorldGenerator.KELP.b(WorldGenFeatureConfiguration.k).a(b.f).a().a(WorldGenDecorator.e.b(new WorldGenDecoratorNoiseConfiguration(80, 80.0D, 0.0D))));
/* 136 */   public static final WorldGenFeatureConfigured<?, ?> BLUE_ICE = a("blue_ice", WorldGenerator.BLUE_ICE.b(WorldGenFeatureConfiguration.k).a(WorldGenDecorator.l.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(30, 32, 64))).a().c(19));
/*     */ 
/*     */   
/* 139 */   public static final WorldGenFeatureConfigured<?, ?> BAMBOO_LIGHT = a("bamboo_light", WorldGenerator.BAMBOO.b(new WorldGenFeatureConfigurationChance(0.0F)).a(b.m).b(16));
/* 140 */   public static final WorldGenFeatureConfigured<?, ?> BAMBOO = a("bamboo", WorldGenerator.BAMBOO.b(new WorldGenFeatureConfigurationChance(0.2F)).a(b.g).a().a(WorldGenDecorator.e.b(new WorldGenDecoratorNoiseConfiguration(160, 80.0D, 0.3D))));
/* 141 */   public static final WorldGenFeatureConfigured<?, ?> VINES = a("vines", WorldGenerator.VINES.b(WorldGenFeatureConfiguration.k).a().b(50));
/*     */ 
/*     */ 
/*     */   
/* 145 */   public static final WorldGenFeatureConfigured<?, ?> LAKE_WATER = a("lake_water", WorldGenerator.LAKE.b(new WorldGenFeatureLakeConfiguration(c.ab)).a(WorldGenDecorator.v.b(new WorldGenDecoratorDungeonConfiguration(4))));
/* 146 */   public static final WorldGenFeatureConfigured<?, ?> LAKE_LAVA = a("lake_lava", WorldGenerator.LAKE.b(new WorldGenFeatureLakeConfiguration(c.ac)).a(WorldGenDecorator.u.b(new WorldGenDecoratorDungeonConfiguration(80))));
/*     */   
/* 148 */   public static final WorldGenFeatureConfigured<?, ?> DISK_CLAY = a("disk_clay", WorldGenerator.DISK.b(new WorldGenFeatureCircleConfiguration(c.as, IntSpread.a(2, 1), 1, (List<IBlockData>)ImmutableList.of(c.ad, c.as))).a(b.n));
/* 149 */   public static final WorldGenFeatureConfigured<?, ?> DISK_GRAVEL = a("disk_gravel", WorldGenerator.DISK.b(new WorldGenFeatureCircleConfiguration(c.ae, IntSpread.a(2, 3), 2, (List<IBlockData>)ImmutableList.of(c.ad, c.t))).a(b.n));
/* 150 */   public static final WorldGenFeatureConfigured<?, ?> DISK_SAND = a("disk_sand", WorldGenerator.DISK.b(new WorldGenFeatureCircleConfiguration(c.ar, IntSpread.a(2, 4), 2, (List<IBlockData>)ImmutableList.of(c.ad, c.t))).a(b.n).b(3));
/*     */   
/* 152 */   public static final WorldGenFeatureConfigured<?, ?> FREEZE_TOP_LAYER = a("freeze_top_layer", WorldGenerator.FREEZE_TOP_LAYER.b(WorldGenFeatureConfiguration.k));
/*     */ 
/*     */ 
/*     */   
/* 156 */   public static final WorldGenFeatureConfigured<?, ?> BONUS_CHEST = a("bonus_chest", WorldGenerator.BONUS_CHEST.b(WorldGenFeatureConfiguration.k));
/* 157 */   public static final WorldGenFeatureConfigured<?, ?> VOID_START_PLATFORM = a("void_start_platform", WorldGenerator.VOID_START_PLATFORM.b(WorldGenFeatureConfiguration.k));
/* 158 */   public static final WorldGenFeatureConfigured<?, ?> MONSTER_ROOM = a("monster_room", WorldGenerator.MONSTER_ROOM.b(WorldGenFeatureConfiguration.k).d(256).a().b(8));
/* 159 */   public static final WorldGenFeatureConfigured<?, ?> DESERT_WELL = a("desert_well", WorldGenerator.DESERT_WELL.b(WorldGenFeatureConfiguration.k).a(b.l).a(1000));
/* 160 */   public static final WorldGenFeatureConfigured<?, ?> FOSSIL = a("fossil", WorldGenerator.FOSSIL.b(WorldGenFeatureConfiguration.k).a(64));
/*     */ 
/*     */ 
/*     */   
/* 164 */   public static final WorldGenFeatureConfigured<?, ?> SPRING_LAVA_DOUBLE = a("spring_lava_double", WorldGenerator.SPRING_FEATURE.b(a.i).a(WorldGenDecorator.n.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(8, 16, 256))).a().b(40));
/* 165 */   public static final WorldGenFeatureConfigured<?, ?> SPRING_LAVA = a("spring_lava", WorldGenerator.SPRING_FEATURE.b(a.i).a(WorldGenDecorator.n.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(8, 16, 256))).a().b(20));
/* 166 */   public static final WorldGenFeatureConfigured<?, ?> SPRING_DELTA = a("spring_delta", WorldGenerator.SPRING_FEATURE.b(new WorldGenFeatureHellFlowingLavaConfiguration(c.aa, true, 4, 1, (Set<Block>)ImmutableSet.of(Blocks.NETHERRACK, Blocks.SOUL_SAND, Blocks.GRAVEL, Blocks.MAGMA_BLOCK, Blocks.BLACKSTONE))).a(b.j).a().b(16));
/* 167 */   public static final WorldGenFeatureConfigured<?, ?> SPRING_CLOSED = a("spring_closed", WorldGenerator.SPRING_FEATURE.b(a.j).a(b.i).a().b(16));
/* 168 */   public static final WorldGenFeatureConfigured<?, ?> SPRING_CLOSED_DOUBLE = a("spring_closed_double", WorldGenerator.SPRING_FEATURE.b(a.j).a(b.i).a().b(32));
/* 169 */   public static final WorldGenFeatureConfigured<?, ?> SPRING_OPEN = a("spring_open", WorldGenerator.SPRING_FEATURE.b(new WorldGenFeatureHellFlowingLavaConfiguration(c.aa, false, 4, 1, (Set<Block>)ImmutableSet.of(Blocks.NETHERRACK))).a(b.j).a().b(8));
/* 170 */   public static final WorldGenFeatureConfigured<?, ?> SPRING_WATER = a("spring_water", WorldGenerator.SPRING_FEATURE.b(new WorldGenFeatureHellFlowingLavaConfiguration(c.Z, true, 4, 1, (Set<Block>)ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE))).a(WorldGenDecorator.m.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(8, 8, 256))).a().b(50));
/*     */ 
/*     */ 
/*     */   
/* 174 */   public static final WorldGenFeatureConfigured<?, ?> PILE_HAY = a("pile_hay", WorldGenerator.BLOCK_PILE.b(new WorldGenFeatureBlockPileConfiguration(new WorldGenFeatureStateProviderRotatedBlock(Blocks.HAY_BLOCK))));
/* 175 */   public static final WorldGenFeatureConfigured<?, ?> PILE_MELON = a("pile_melon", WorldGenerator.BLOCK_PILE.b(new WorldGenFeatureBlockPileConfiguration(new WorldGenFeatureStateProviderSimpl(c.I))));
/* 176 */   public static final WorldGenFeatureConfigured<?, ?> PILE_SNOW = a("pile_snow", WorldGenerator.BLOCK_PILE.b(new WorldGenFeatureBlockPileConfiguration(new WorldGenFeatureStateProviderSimpl(c.R))));
/* 177 */   public static final WorldGenFeatureConfigured<?, ?> PILE_ICE = a("pile_ice", WorldGenerator.BLOCK_PILE.b(new WorldGenFeatureBlockPileConfiguration((new WorldGenFeatureStateProviderWeighted()).a(c.C, 1).a(c.B, 5))));
/* 178 */   public static final WorldGenFeatureConfigured<?, ?> PILE_PUMPKIN = a("pile_pumpkin", WorldGenerator.BLOCK_PILE.b(new WorldGenFeatureBlockPileConfiguration((new WorldGenFeatureStateProviderWeighted()).a(c.J, 19).a(c.S, 1))));
/*     */ 
/*     */ 
/*     */   
/* 182 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_FIRE = a("patch_fire", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.L), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 186 */         .a(64)
/* 187 */         .a((Set<Block>)ImmutableSet.of(c.N.getBlock()))
/* 188 */         .b()
/* 189 */         .d()).a(b.d));
/* 190 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_SOUL_FIRE = a("patch_soul_fire", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.M), new WorldGenBlockPlacerSimple()))
/*     */ 
/*     */ 
/*     */         
/* 194 */         .a(64)
/* 195 */         .a((Set<Block>)ImmutableSet.of(c.O.getBlock()))
/* 196 */         .b()
/* 197 */         .d()).a(b.d));
/* 198 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_BROWN_MUSHROOM = a("patch_brown_mushroom", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.z), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 202 */         .a(64)
/* 203 */         .b()
/* 204 */         .d()));
/* 205 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_RED_MUSHROOM = a("patch_red_mushroom", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.A), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 209 */         .a(64)
/* 210 */         .b()
/* 211 */         .d()));
/* 212 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_CRIMSON_ROOTS = a("patch_crimson_roots", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.P), new WorldGenBlockPlacerSimple()))
/*     */ 
/*     */ 
/*     */         
/* 216 */         .a(64)
/* 217 */         .b()
/* 218 */         .d()).d(128));
/* 219 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_SUNFLOWER = a("patch_sunflower", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.T), new WorldGenBlockPlacerDoublePlant()))
/*     */ 
/*     */ 
/*     */         
/* 223 */         .a(64)
/* 224 */         .b()
/* 225 */         .d()).a(b.k).a(b.l).b(10));
/* 226 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_PUMPKIN = a("patch_pumpkin", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.J), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 230 */         .a(64)
/* 231 */         .a((Set<Block>)ImmutableSet.of(c.t.getBlock()))
/* 232 */         .b()
/* 233 */         .d()).a(b.m).a(32));
/* 234 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_TAIGA_GRASS = a("patch_taiga_grass", WorldGenerator.RANDOM_PATCH.b(a.b));
/* 235 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_BERRY_BUSH = a("patch_berry_bush", WorldGenerator.RANDOM_PATCH.b(a.f));
/* 236 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_GRASS_PLAIN = a("patch_grass_plain", WorldGenerator.RANDOM_PATCH.b(a.a).a(b.m).a(WorldGenDecorator.d.b(new WorldGenFeatureDecoratorNoiseConfiguration(-0.8D, 5, 10))));
/* 237 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_GRASS_FOREST = a("patch_grass_forest", WorldGenerator.RANDOM_PATCH.b(a.a).a(b.m).b(2));
/* 238 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_GRASS_BADLANDS = a("patch_grass_badlands", WorldGenerator.RANDOM_PATCH.b(a.a).a(b.m));
/* 239 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_GRASS_SAVANNA = a("patch_grass_savanna", WorldGenerator.RANDOM_PATCH.b(a.a).a(b.m).b(20));
/* 240 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_GRASS_NORMAL = a("patch_grass_normal", WorldGenerator.RANDOM_PATCH.b(a.a).a(b.m).b(5));
/* 241 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_GRASS_TAIGA_2 = a("patch_grass_taiga_2", WorldGenerator.RANDOM_PATCH.b(a.b).a(b.m));
/* 242 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_GRASS_TAIGA = a("patch_grass_taiga", WorldGenerator.RANDOM_PATCH.b(a.b).a(b.m).b(7));
/* 243 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_GRASS_JUNGLE = a("patch_grass_jungle", WorldGenerator.RANDOM_PATCH.b(a.c).a(b.m).b(25));
/* 244 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_DEAD_BUSH_2 = a("patch_dead_bush_2", WorldGenerator.RANDOM_PATCH.b(a.e).a(b.m).b(2));
/* 245 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_DEAD_BUSH = a("patch_dead_bush", WorldGenerator.RANDOM_PATCH.b(a.e).a(b.m));
/* 246 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_DEAD_BUSH_BADLANDS = a("patch_dead_bush_badlands", WorldGenerator.RANDOM_PATCH.b(a.e).a(b.m).b(20));
/* 247 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_MELON = a("patch_melon", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.I), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 251 */         .a(64)
/* 252 */         .a((Set<Block>)ImmutableSet.of(c.t.getBlock()))
/* 253 */         .a()
/* 254 */         .b()
/* 255 */         .d()).a(b.m));
/* 256 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_BERRY_SPARSE = a("patch_berry_sparse", PATCH_BERRY_BUSH.a(b.m));
/* 257 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_BERRY_DECORATED = a("patch_berry_decorated", PATCH_BERRY_BUSH.a(b.m).a(12));
/* 258 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_WATERLILLY = a("patch_waterlilly", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.Q), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 262 */         .a(10)
/* 263 */         .d()).a(b.m).b(4));
/* 264 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_TALL_GRASS_2 = a("patch_tall_grass_2", WorldGenerator.RANDOM_PATCH.b(a.g).a(b.k).a(b.e).a().a(WorldGenDecorator.d.b(new WorldGenFeatureDecoratorNoiseConfiguration(-0.8D, 0, 7))));
/* 265 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_TALL_GRASS = a("patch_tall_grass", WorldGenerator.RANDOM_PATCH.b(a.g).a(b.k).a(b.l).b(7));
/* 266 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_LARGE_FERN = a("patch_large_fern", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.u), new WorldGenBlockPlacerDoublePlant()))
/*     */ 
/*     */ 
/*     */         
/* 270 */         .a(64)
/* 271 */         .b()
/* 272 */         .d()).a(b.k).a(b.l).b(7));
/* 273 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_CACTUS = a("patch_cactus", WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.U), new WorldGenBlockPlacerColumn(1, 2)))
/*     */ 
/*     */ 
/*     */         
/* 277 */         .a(10)
/* 278 */         .b()
/* 279 */         .d()));
/* 280 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_CACTUS_DESERT = a("patch_cactus_desert", PATCH_CACTUS.a(b.m).b(10));
/* 281 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_CACTUS_DECORATED = a("patch_cactus_decorated", PATCH_CACTUS.a(b.m).b(5));
/* 282 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_SUGAR_CANE_SWAMP = a("patch_sugar_cane_swamp", WorldGenerator.RANDOM_PATCH.b(a.h).a(b.m).b(20));
/* 283 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_SUGAR_CANE_DESERT = a("patch_sugar_cane_desert", WorldGenerator.RANDOM_PATCH.b(a.h).a(b.m).b(60));
/* 284 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_SUGAR_CANE_BADLANDS = a("patch_sugar_cane_badlands", WorldGenerator.RANDOM_PATCH.b(a.h).a(b.m).b(13));
/* 285 */   public static final WorldGenFeatureConfigured<?, ?> PATCH_SUGAR_CANE = a("patch_sugar_cane", WorldGenerator.RANDOM_PATCH.b(a.h).a(b.m).b(10));
/*     */ 
/*     */ 
/*     */   
/* 289 */   public static final WorldGenFeatureConfigured<?, ?> BROWN_MUSHROOM_NETHER = a("brown_mushroom_nether", PATCH_BROWN_MUSHROOM.d(128).a(2));
/* 290 */   public static final WorldGenFeatureConfigured<?, ?> RED_MUSHROOM_NETHER = a("red_mushroom_nether", PATCH_RED_MUSHROOM.d(128).a(2));
/* 291 */   public static final WorldGenFeatureConfigured<?, ?> BROWN_MUSHROOM_NORMAL = a("brown_mushroom_normal", PATCH_BROWN_MUSHROOM.a(b.m).a(4));
/* 292 */   public static final WorldGenFeatureConfigured<?, ?> RED_MUSHROOM_NORMAL = a("red_mushroom_normal", PATCH_RED_MUSHROOM.a(b.m).a(8));
/* 293 */   public static final WorldGenFeatureConfigured<?, ?> BROWN_MUSHROOM_TAIGA = a("brown_mushroom_taiga", PATCH_BROWN_MUSHROOM.a(4).a(b.l));
/* 294 */   public static final WorldGenFeatureConfigured<?, ?> RED_MUSHROOM_TAIGA = a("red_mushroom_taiga", PATCH_RED_MUSHROOM.a(8).a(b.m));
/* 295 */   public static final WorldGenFeatureConfigured<?, ?> BROWN_MUSHROOM_GIANT = a("brown_mushroom_giant", BROWN_MUSHROOM_TAIGA.b(3));
/* 296 */   public static final WorldGenFeatureConfigured<?, ?> RED_MUSHROOM_GIANT = a("red_mushroom_giant", RED_MUSHROOM_TAIGA.b(3));
/* 297 */   public static final WorldGenFeatureConfigured<?, ?> BROWN_MUSHROOM_SWAMP = a("brown_mushroom_swamp", BROWN_MUSHROOM_TAIGA.b(8));
/* 298 */   public static final WorldGenFeatureConfigured<?, ?> RED_MUSHROOM_SWAMP = a("red_mushroom_swamp", RED_MUSHROOM_TAIGA.b(8));
/*     */ 
/*     */ 
/*     */   
/* 302 */   public static final WorldGenFeatureConfigured<?, ?> ORE_MAGMA = a("ore_magma", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, c.av, 33)).a(WorldGenDecorator.s.b(WorldGenFeatureEmptyConfiguration2.c)).a().b(4));
/* 303 */   public static final WorldGenFeatureConfigured<?, ?> ORE_SOUL_SAND = a("ore_soul_sand", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, c.aw, 12)).d(32).a().b(12));
/* 304 */   public static final WorldGenFeatureConfigured<?, ?> ORE_GOLD_DELTAS = a("ore_gold_deltas", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, c.ax, 10)).a(b.i).a().b(20));
/* 305 */   public static final WorldGenFeatureConfigured<?, ?> ORE_QUARTZ_DELTAS = a("ore_quartz_deltas", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, c.ay, 14)).a(b.i).a().b(32));
/* 306 */   public static final WorldGenFeatureConfigured<?, ?> ORE_GOLD_NETHER = a("ore_gold_nether", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, c.ax, 10)).a(b.i).a().b(10));
/* 307 */   public static final WorldGenFeatureConfigured<?, ?> ORE_QUARTZ_NETHER = a("ore_quartz_nether", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, c.ay, 14)).a(b.i).a().b(16));
/* 308 */   public static final WorldGenFeatureConfigured<?, ?> ORE_GRAVEL_NETHER = a("ore_gravel_nether", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, c.ae, 33)).a(WorldGenDecorator.l.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(5, 0, 37))).a().b(2));
/* 309 */   public static final WorldGenFeatureConfigured<?, ?> ORE_BLACKSTONE = a("ore_blackstone", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, c.az, 33)).a(WorldGenDecorator.l.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(5, 10, 37))).a().b(2));
/* 310 */   public static final WorldGenFeatureConfigured<?, ?> ORE_DIRT = a("ore_dirt", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.ad, 33)).d(256).a().b(10));
/* 311 */   public static final WorldGenFeatureConfigured<?, ?> ORE_GRAVEL = a("ore_gravel", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.ae, 33)).d(256).a().b(8));
/* 312 */   public static final WorldGenFeatureConfigured<?, ?> ORE_GRANITE = a("ore_granite", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.af, 33)).d(80).a().b(10));
/*     */   
/* 314 */   public static final WorldGenFeatureConfigured<?, ?> ORE_DIORITE = a("ore_diorite", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.ag, 33)).d(80).a().b(10));
/* 315 */   public static final WorldGenFeatureConfigured<?, ?> ORE_ANDESITE = a("ore_andesite", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.ah, 33)).d(80).a().b(10));
/* 316 */   public static final WorldGenFeatureConfigured<?, ?> ORE_COAL = a("ore_coal", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.ai, 17)).d(128).a().b(20));
/* 317 */   public static final WorldGenFeatureConfigured<?, ?> ORE_IRON = a("ore_iron", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.aj, 9)).d(64).a().b(20));
/* 318 */   public static final WorldGenFeatureConfigured<?, ?> ORE_GOLD_EXTRA = a("ore_gold_extra", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.ak, 9)).a(WorldGenDecorator.l.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(32, 32, 80))).a().b(20));
/* 319 */   public static final WorldGenFeatureConfigured<?, ?> ORE_GOLD = a("ore_gold", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.ak, 9)).d(32).a().b(2));
/* 320 */   public static final WorldGenFeatureConfigured<?, ?> ORE_REDSTONE = a("ore_redstone", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.al, 8)).d(16).a().b(8));
/* 321 */   public static final WorldGenFeatureConfigured<?, ?> ORE_DIAMOND = a("ore_diamond", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.am, 8)).d(16).a());
/* 322 */   public static final WorldGenFeatureConfigured<?, ?> ORE_LAPIS = a("ore_lapis", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.an, 7)).a(WorldGenDecorator.o.b(new WorldGenDecoratorHeightAverageConfiguration(16, 16))).a());
/* 323 */   public static final WorldGenFeatureConfigured<?, ?> ORE_INFESTED = a("ore_infested", WorldGenerator.ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, c.aq, 9)).d(64).a().b(7));
/* 324 */   public static final WorldGenFeatureConfigured<?, ?> ORE_EMERALD = a("ore_emerald", WorldGenerator.EMERALD_ORE.b(new WorldGenFeatureReplaceBlockConfiguration(c.ao, c.ap)).a(WorldGenDecorator.t.b(WorldGenFeatureDecoratorConfiguration.b)));
/* 325 */   public static final WorldGenFeatureConfigured<?, ?> ORE_DEBRIS_LARGE = a("ore_debris_large", WorldGenerator.NO_SURFACE_ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHER_ORE_REPLACEABLES, c.aA, 3)).a(WorldGenDecorator.o.b(new WorldGenDecoratorHeightAverageConfiguration(16, 8))).a());
/* 326 */   public static final WorldGenFeatureConfigured<?, ?> ORE_DEBRIS_SMALL = a("ore_debris_small", WorldGenerator.NO_SURFACE_ORE.b(new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHER_ORE_REPLACEABLES, c.aA, 2)).a(WorldGenDecorator.l.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(8, 16, 128))).a());
/*     */ 
/*     */ 
/*     */   
/* 330 */   public static final WorldGenFeatureConfigured<?, ?> CRIMSON_FUNGI = a("crimson_fungi", WorldGenerator.HUGE_FUNGUS.b(WorldGenFeatureHugeFungiConfiguration.c).a(WorldGenDecorator.C.b(new WorldGenDecoratorFrequencyConfiguration(8))));
/* 331 */   public static final WorldGenFeatureConfigured<WorldGenFeatureHugeFungiConfiguration, ?> CRIMSON_FUNGI_PLANTED = a("crimson_fungi_planted", WorldGenerator.HUGE_FUNGUS.b(WorldGenFeatureHugeFungiConfiguration.CRIMSON_FUNGUS));
/* 332 */   public static final WorldGenFeatureConfigured<?, ?> WARPED_FUNGI = a("warped_fungi", WorldGenerator.HUGE_FUNGUS.b(WorldGenFeatureHugeFungiConfiguration.e).a(WorldGenDecorator.C.b(new WorldGenDecoratorFrequencyConfiguration(8))));
/* 333 */   public static final WorldGenFeatureConfigured<WorldGenFeatureHugeFungiConfiguration, ?> WARPED_FUNGI_PLANTED = a("warped_fungi_planted", WorldGenerator.HUGE_FUNGUS.b(WorldGenFeatureHugeFungiConfiguration.WARPED_FUNGUS));
/*     */ 
/*     */ 
/*     */   
/* 337 */   public static final WorldGenFeatureConfigured<?, ?> HUGE_BROWN_MUSHROOM = a("huge_brown_mushroom", WorldGenerator.HUGE_BROWN_MUSHROOM.b(new WorldGenFeatureMushroomConfiguration(new WorldGenFeatureStateProviderSimpl(c.X), new WorldGenFeatureStateProviderSimpl(c.Y), 3)));
/* 338 */   public static final WorldGenFeatureConfigured<?, ?> HUGE_RED_MUSHROOM = a("huge_red_mushroom", WorldGenerator.HUGE_RED_MUSHROOM.b(new WorldGenFeatureMushroomConfiguration(new WorldGenFeatureStateProviderSimpl(c.W), new WorldGenFeatureStateProviderSimpl(c.Y), 2)));
/*     */   
/* 340 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> OAK = a("oak", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.h), new WorldGenFeatureStateProviderSimpl(c.i), new WorldGenFoilagePlacerBlob(
/*     */ 
/*     */             
/* 343 */             IntSpread.a(2), IntSpread.a(0), 3), new TrunkPlacerStraight(4, 2, 0), new FeatureSizeTwoLayers(1, 0, 1)))
/*     */ 
/*     */ 
/*     */         
/* 347 */         .a()
/* 348 */         .b()));
/* 349 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> DARK_OAK = a("dark_oak", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.r), new WorldGenFeatureStateProviderSimpl(c.s), new WorldGenFoilagePlacerDarkOak(
/*     */ 
/*     */             
/* 352 */             IntSpread.a(0), IntSpread.a(0)), new TrunkPlacerDarkOak(6, 2, 1), new FeatureSizeThreeLayers(1, 1, 0, 1, 2, 
/*     */             
/* 354 */             OptionalInt.empty())))
/*     */         
/* 356 */         .a(2147483647)
/*     */         
/* 358 */         .a(HeightMap.Type.MOTION_BLOCKING)
/* 359 */         .a()
/* 360 */         .b()));
/* 361 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> BIRCH = a("birch", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.p), new WorldGenFeatureStateProviderSimpl(c.q), new WorldGenFoilagePlacerBlob(
/*     */ 
/*     */             
/* 364 */             IntSpread.a(2), IntSpread.a(0), 3), new TrunkPlacerStraight(5, 2, 0), new FeatureSizeTwoLayers(1, 0, 1)))
/*     */ 
/*     */ 
/*     */         
/* 368 */         .a()
/* 369 */         .b()));
/* 370 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> ACACIA = a("acacia", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.n), new WorldGenFeatureStateProviderSimpl(c.o), new WorldGenFoilagePlacerAcacia(
/*     */ 
/*     */             
/* 373 */             IntSpread.a(2), IntSpread.a(0)), new TrunkPlacerForking(5, 2, 2), new FeatureSizeTwoLayers(1, 0, 2)))
/*     */ 
/*     */ 
/*     */         
/* 377 */         .a()
/* 378 */         .b()));
/* 379 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> SPRUCE = a("spruce", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.l), new WorldGenFeatureStateProviderSimpl(c.m), new WorldGenFoilagePlacerSpruce(
/*     */ 
/*     */             
/* 382 */             IntSpread.a(2, 1), IntSpread.a(0, 2), IntSpread.a(1, 1)), new TrunkPlacerStraight(5, 2, 1), new FeatureSizeTwoLayers(2, 0, 2)))
/*     */ 
/*     */ 
/*     */         
/* 386 */         .a()
/* 387 */         .b()));
/* 388 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> PINE = a("pine", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.l), new WorldGenFeatureStateProviderSimpl(c.m), new WorldGenFoilagePlacerPine(
/*     */ 
/*     */             
/* 391 */             IntSpread.a(1), IntSpread.a(1), IntSpread.a(3, 1)), new TrunkPlacerStraight(6, 4, 0), new FeatureSizeTwoLayers(2, 0, 2)))
/*     */ 
/*     */ 
/*     */         
/* 395 */         .a()
/* 396 */         .b()));
/* 397 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> JUNGLE_TREE = a("jungle_tree", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.j), new WorldGenFeatureStateProviderSimpl(c.k), new WorldGenFoilagePlacerBlob(
/*     */ 
/*     */             
/* 400 */             IntSpread.a(2), IntSpread.a(0), 3), new TrunkPlacerStraight(4, 8, 0), new FeatureSizeTwoLayers(1, 0, 1)))
/*     */ 
/*     */ 
/*     */         
/* 404 */         .a((List<WorldGenFeatureTree>)ImmutableList.of(new WorldGenFeatureTreeCocoa(0.2F), WorldGenFeatureTreeVineTrunk.b, WorldGenFeatureTreeVineLeaves.b))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 409 */         .a()
/* 410 */         .b()));
/* 411 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> FANCY_OAK = a("fancy_oak", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.h), new WorldGenFeatureStateProviderSimpl(c.i), new WorldGenFoilagePlacerFancy(
/*     */ 
/*     */             
/* 414 */             IntSpread.a(2), IntSpread.a(4), 4), new TrunkPlacerFancy(3, 11, 0), new FeatureSizeTwoLayers(0, 0, 0, 
/*     */             
/* 416 */             OptionalInt.of(4))))
/*     */         
/* 418 */         .a()
/* 419 */         .a(HeightMap.Type.MOTION_BLOCKING)
/* 420 */         .b()));
/* 421 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> JUNGLE_TREE_NO_VINE = a("jungle_tree_no_vine", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.j), new WorldGenFeatureStateProviderSimpl(c.k), new WorldGenFoilagePlacerBlob(
/*     */ 
/*     */             
/* 424 */             IntSpread.a(2), IntSpread.a(0), 3), new TrunkPlacerStraight(4, 8, 0), new FeatureSizeTwoLayers(1, 0, 1)))
/*     */ 
/*     */ 
/*     */         
/* 428 */         .a()
/* 429 */         .b()));
/* 430 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> MEGA_JUNGLE_TREE = a("mega_jungle_tree", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.j), new WorldGenFeatureStateProviderSimpl(c.k), new WorldGenFoilagePlacerJungle(
/*     */ 
/*     */             
/* 433 */             IntSpread.a(2), IntSpread.a(0), 2), new TrunkPlacerMegaJungle(10, 2, 19), new FeatureSizeTwoLayers(1, 1, 2)))
/*     */ 
/*     */ 
/*     */         
/* 437 */         .a((List<WorldGenFeatureTree>)ImmutableList.of(WorldGenFeatureTreeVineTrunk.b, WorldGenFeatureTreeVineLeaves.b))
/*     */ 
/*     */ 
/*     */         
/* 441 */         .b()));
/* 442 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> MEGA_SPRUCE = a("mega_spruce", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.l), new WorldGenFeatureStateProviderSimpl(c.m), new WorldGenFoilagePlacerMegaPine(
/*     */ 
/*     */             
/* 445 */             IntSpread.a(0), IntSpread.a(0), IntSpread.a(13, 4)), new TrunkPlacerGiant(13, 2, 14), new FeatureSizeTwoLayers(1, 1, 2)))
/*     */ 
/*     */ 
/*     */         
/* 449 */         .a((List<WorldGenFeatureTree>)ImmutableList.of(new WorldGenFeatureTreeAlterGround(new WorldGenFeatureStateProviderSimpl(c.c))))
/*     */ 
/*     */         
/* 452 */         .b()));
/* 453 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> MEGA_PINE = a("mega_pine", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.l), new WorldGenFeatureStateProviderSimpl(c.m), new WorldGenFoilagePlacerMegaPine(
/*     */ 
/*     */             
/* 456 */             IntSpread.a(0), IntSpread.a(0), IntSpread.a(3, 4)), new TrunkPlacerGiant(13, 2, 14), new FeatureSizeTwoLayers(1, 1, 2)))
/*     */ 
/*     */ 
/*     */         
/* 460 */         .a((List<WorldGenFeatureTree>)ImmutableList.of(new WorldGenFeatureTreeAlterGround(new WorldGenFeatureStateProviderSimpl(c.c))))
/*     */ 
/*     */         
/* 463 */         .b()));
/* 464 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> SUPER_BIRCH_BEES_0002 = a("super_birch_bees_0002", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.p), new WorldGenFeatureStateProviderSimpl(c.q), new WorldGenFoilagePlacerBlob(
/*     */ 
/*     */             
/* 467 */             IntSpread.a(2), IntSpread.a(0), 3), new TrunkPlacerStraight(5, 2, 6), new FeatureSizeTwoLayers(1, 0, 1)))
/*     */ 
/*     */ 
/*     */         
/* 471 */         .a()
/* 472 */         .a((List<WorldGenFeatureTree>)ImmutableList.of(b.a))
/* 473 */         .b()));
/* 474 */   public static final WorldGenFeatureConfigured<?, ?> SWAMP_TREE = a("swamp_tree", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.h), new WorldGenFeatureStateProviderSimpl(c.i), new WorldGenFoilagePlacerBlob(
/*     */ 
/*     */             
/* 477 */             IntSpread.a(3), IntSpread.a(0), 3), new TrunkPlacerStraight(5, 3, 0), new FeatureSizeTwoLayers(1, 0, 1)))
/*     */ 
/*     */ 
/*     */         
/* 481 */         .a(1)
/* 482 */         .a((List<WorldGenFeatureTree>)ImmutableList.of(WorldGenFeatureTreeVineLeaves.b))
/* 483 */         .b()).a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(2, 0.1F, 1))));
/* 484 */   public static final WorldGenFeatureConfigured<?, ?> JUNGLE_BUSH = a("jungle_bush", WorldGenerator.TREE.b((new WorldGenFeatureTreeConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.j), new WorldGenFeatureStateProviderSimpl(c.i), new WorldGenFoilagePlacerBush(
/*     */ 
/*     */             
/* 487 */             IntSpread.a(2), IntSpread.a(1), 2), new TrunkPlacerStraight(1, 0, 0), new FeatureSizeTwoLayers(0, 0, 0)))
/*     */ 
/*     */ 
/*     */         
/* 491 */         .a(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES)
/* 492 */         .b()));
/*     */   
/* 494 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> OAK_BEES_0002 = a("oak_bees_0002", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)OAK.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.a))));
/* 495 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> OAK_BEES_002 = a("oak_bees_002", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)OAK.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.b))));
/* 496 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> OAK_BEES_005 = a("oak_bees_005", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)OAK.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.c))));
/* 497 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> BIRCH_BEES_0002 = a("birch_bees_0002", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)BIRCH.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.a))));
/* 498 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> BIRCH_BEES_002 = a("birch_bees_002", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)BIRCH.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.b))));
/* 499 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> BIRCH_BEES_005 = a("birch_bees_005", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)BIRCH.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.c))));
/* 500 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> FANCY_OAK_BEES_0002 = a("fancy_oak_bees_0002", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)FANCY_OAK.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.a))));
/* 501 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> FANCY_OAK_BEES_002 = a("fancy_oak_bees_002", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)FANCY_OAK.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.b))));
/* 502 */   public static final WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> FANCY_OAK_BEES_005 = a("fancy_oak_bees_005", WorldGenerator.TREE.b(((WorldGenFeatureTreeConfiguration)FANCY_OAK.c()).a((List<WorldGenFeatureTree>)ImmutableList.of(b.c))));
/* 503 */   public static final WorldGenFeatureConfigured<?, ?> OAK_BADLANDS = a("oak_badlands", OAK.a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(5, 0.1F, 1))));
/* 504 */   public static final WorldGenFeatureConfigured<?, ?> SPRUCE_SNOWY = a("spruce_snowy", SPRUCE.a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(0, 0.1F, 1))));
/*     */ 
/*     */ 
/*     */   
/* 508 */   public static final WorldGenFeatureConfigured<?, ?> FLOWER_WARM = a("flower_warm", WorldGenerator.FLOWER.b(a.d).a(b.k).a(b.l).b(4));
/* 509 */   public static final WorldGenFeatureConfigured<?, ?> FLOWER_DEFAULT = a("flower_default", WorldGenerator.FLOWER.b(a.d).a(b.k).a(b.l).b(2));
/* 510 */   public static final WorldGenFeatureConfigured<?, ?> FLOWER_FOREST = a("flower_forest", WorldGenerator.FLOWER.b((new WorldGenFeatureRandomPatchConfiguration.a(WorldGenFeatureStateProviderForestFlower.c, WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 514 */         .a(64)
/* 515 */         .d()).a(b.k).a(b.l).b(100));
/* 516 */   public static final WorldGenFeatureConfigured<?, ?> FLOWER_SWAMP = a("flower_swamp", WorldGenerator.FLOWER.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.E), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 520 */         .a(64)
/* 521 */         .d()).a(b.k).a(b.l));
/* 522 */   public static final WorldGenFeatureConfigured<?, ?> FLOWER_PLAIN = a("flower_plain", WorldGenerator.FLOWER.b((new WorldGenFeatureRandomPatchConfiguration.a(WorldGenFeatureStateProviderPlainFlower.c, WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */         
/* 526 */         .a(64)
/* 527 */         .d()));
/* 528 */   public static final WorldGenFeatureConfigured<?, ?> FLOWER_PLAIN_DECORATED = a("flower_plain_decorated", FLOWER_PLAIN.a(b.k).a(b.e).a().a(WorldGenDecorator.d.b(new WorldGenFeatureDecoratorNoiseConfiguration(-0.8D, 15, 4))));
/*     */ 
/*     */ 
/*     */   
/* 532 */   private static final ImmutableList<Supplier<WorldGenFeatureConfigured<?, ?>>> cJ = ImmutableList.of(() -> WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.w), new WorldGenBlockPlacerDoublePlant())).a(64).b().d()), () -> WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.x), new WorldGenBlockPlacerDoublePlant())).a(64).b().d()), () -> WorldGenerator.RANDOM_PATCH.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.y), new WorldGenBlockPlacerDoublePlant())).a(64).b().d()), () -> WorldGenerator.NO_BONEMEAL_FLOWER.b((new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(c.D), WorldGenBlockPlacerSimple.c)).a(64).d()));
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
/* 561 */   public static final WorldGenFeatureConfigured<?, ?> FOREST_FLOWER_VEGETATION_COMMON = a("forest_flower_vegetation_common", WorldGenerator.SIMPLE_RANDOM_SELECTOR.b(new WorldGenFeatureRandom2((List<Supplier<WorldGenFeatureConfigured<?, ?>>>)cJ)).a(IntSpread.a(-1, 4)).a(b.k).a(b.l).b(5));
/* 562 */   public static final WorldGenFeatureConfigured<?, ?> FOREST_FLOWER_VEGETATION = a("forest_flower_vegetation", WorldGenerator.SIMPLE_RANDOM_SELECTOR.b(new WorldGenFeatureRandom2((List<Supplier<WorldGenFeatureConfigured<?, ?>>>)cJ)).a(IntSpread.a(-3, 4)).a(b.k).a(b.l).b(5));
/*     */   
/* 564 */   public static final WorldGenFeatureConfigured<?, ?> DARK_FOREST_VEGETATION_BROWN = a("dark_forest_vegetation_brown", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 565 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(HUGE_BROWN_MUSHROOM
/* 566 */             .a(0.025F), HUGE_RED_MUSHROOM
/* 567 */             .a(0.05F), DARK_OAK
/* 568 */             .a(0.6666667F), BIRCH
/* 569 */             .a(0.2F), FANCY_OAK
/* 570 */             .a(0.1F)), OAK))
/*     */ 
/*     */       
/* 573 */       .a(WorldGenDecorator.y.b(WorldGenFeatureDecoratorConfiguration.b)));
/*     */   
/* 575 */   public static final WorldGenFeatureConfigured<?, ?> DARK_FOREST_VEGETATION_RED = a("dark_forest_vegetation_red", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 576 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(HUGE_RED_MUSHROOM
/* 577 */             .a(0.025F), HUGE_BROWN_MUSHROOM
/* 578 */             .a(0.05F), DARK_OAK
/* 579 */             .a(0.6666667F), BIRCH
/* 580 */             .a(0.2F), FANCY_OAK
/* 581 */             .a(0.1F)), OAK))
/*     */ 
/*     */       
/* 584 */       .a(WorldGenDecorator.y.b(WorldGenFeatureDecoratorConfiguration.b)));
/*     */   
/* 586 */   public static final WorldGenFeatureConfigured<?, ?> WARM_OCEAN_VEGETATION = a("warm_ocean_vegetation", WorldGenerator.SIMPLE_RANDOM_SELECTOR.b(new WorldGenFeatureRandom2((List<Supplier<WorldGenFeatureConfigured<?, ?>>>)ImmutableList.of(() -> WorldGenerator.CORAL_TREE.b(WorldGenFeatureConfiguration.k), () -> WorldGenerator.CORAL_CLAW.b(WorldGenFeatureConfiguration.k), () -> WorldGenerator.CORAL_MUSHROOM.b(WorldGenFeatureConfiguration.k))))
/*     */ 
/*     */ 
/*     */       
/* 590 */       .a(b.f).a().a(WorldGenDecorator.e.b(new WorldGenDecoratorNoiseConfiguration(20, 400.0D, 0.0D))));
/*     */   
/* 592 */   public static final WorldGenFeatureConfigured<?, ?> FOREST_FLOWER_TREES = a("forest_flower_trees", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 593 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(BIRCH_BEES_002
/* 594 */             .a(0.2F), FANCY_OAK_BEES_002
/* 595 */             .a(0.1F)), OAK_BEES_002))
/*     */       
/* 597 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(6, 0.1F, 1))));
/*     */   
/* 599 */   public static final WorldGenFeatureConfigured<?, ?> TAIGA_VEGETATION = a("taiga_vegetation", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 600 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(PINE.a(0.33333334F)), SPRUCE))
/*     */       
/* 602 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1))));
/*     */   
/* 604 */   public static final WorldGenFeatureConfigured<?, ?> TREES_SHATTERED_SAVANNA = a("trees_shattered_savanna", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 605 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(ACACIA.a(0.8F)), OAK))
/*     */       
/* 607 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(2, 0.1F, 1))));
/*     */   
/* 609 */   public static final WorldGenFeatureConfigured<?, ?> TREES_SAVANNA = a("trees_savanna", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 610 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(ACACIA.a(0.8F)), OAK))
/*     */       
/* 612 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(1, 0.1F, 1))));
/*     */   
/* 614 */   public static final WorldGenFeatureConfigured<?, ?> BIRCH_TALL = a("birch_tall", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 615 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(SUPER_BIRCH_BEES_0002.a(0.5F)), BIRCH_BEES_0002))
/*     */       
/* 617 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1))));
/*     */   
/* 619 */   public static final WorldGenFeatureConfigured<?, ?> TREES_BIRCH = a("trees_birch", BIRCH_BEES_0002
/* 620 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1))));
/*     */   
/* 622 */   public static final WorldGenFeatureConfigured<?, ?> TREES_MOUNTAIN_EDGE = a("trees_mountain_edge", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 623 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(SPRUCE
/* 624 */             .a(0.666F), FANCY_OAK
/* 625 */             .a(0.1F)), OAK))
/*     */ 
/*     */       
/* 628 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(3, 0.1F, 1))));
/*     */   
/* 630 */   public static final WorldGenFeatureConfigured<?, ?> TREES_MOUNTAIN = a("trees_mountain", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 631 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(SPRUCE
/* 632 */             .a(0.666F), FANCY_OAK
/* 633 */             .a(0.1F)), OAK))
/*     */ 
/*     */       
/* 636 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(0, 0.1F, 1))));
/*     */   
/* 638 */   public static final WorldGenFeatureConfigured<?, ?> TREES_WATER = a("trees_water", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 639 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(FANCY_OAK.a(0.1F)), OAK))
/*     */       
/* 641 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(0, 0.1F, 1))));
/*     */   
/* 643 */   public static final WorldGenFeatureConfigured<?, ?> BIRCH_OTHER = a("birch_other", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 644 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(BIRCH_BEES_0002
/* 645 */             .a(0.2F), FANCY_OAK_BEES_0002
/* 646 */             .a(0.1F)), OAK_BEES_0002))
/*     */ 
/*     */       
/* 649 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1))));
/*     */   
/* 651 */   public static final WorldGenFeatureConfigured<?, ?> PLAIN_VEGETATION = a("plain_vegetation", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 652 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(FANCY_OAK_BEES_005.a(0.33333334F)), OAK_BEES_005))
/*     */       
/* 654 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(0, 0.05F, 1))));
/*     */   
/* 656 */   public static final WorldGenFeatureConfigured<?, ?> TREES_JUNGLE_EDGE = a("trees_jungle_edge", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 657 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(FANCY_OAK
/* 658 */             .a(0.1F), JUNGLE_BUSH
/* 659 */             .a(0.5F)), JUNGLE_TREE))
/*     */ 
/*     */       
/* 662 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(2, 0.1F, 1))));
/*     */   
/* 664 */   public static final WorldGenFeatureConfigured<?, ?> TREES_GIANT_SPRUCE = a("trees_giant_spruce", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 665 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(MEGA_SPRUCE
/* 666 */             .a(0.33333334F), PINE
/* 667 */             .a(0.33333334F)), SPRUCE))
/*     */ 
/*     */       
/* 670 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1))));
/*     */   
/* 672 */   public static final WorldGenFeatureConfigured<?, ?> TREES_GIANT = a("trees_giant", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 673 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(MEGA_SPRUCE
/* 674 */             .a(0.025641026F), MEGA_PINE
/* 675 */             .a(0.30769232F), PINE
/* 676 */             .a(0.33333334F)), SPRUCE))
/*     */ 
/*     */       
/* 679 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1))));
/*     */   
/* 681 */   public static final WorldGenFeatureConfigured<?, ?> TREES_JUNGLE = a("trees_jungle", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 682 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(FANCY_OAK
/* 683 */             .a(0.1F), JUNGLE_BUSH
/* 684 */             .a(0.5F), MEGA_JUNGLE_TREE
/* 685 */             .a(0.33333334F)), JUNGLE_TREE))
/*     */ 
/*     */       
/* 688 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(50, 0.1F, 1))));
/*     */   
/* 690 */   public static final WorldGenFeatureConfigured<?, ?> BAMBOO_VEGETATION = a("bamboo_vegetation", WorldGenerator.RANDOM_SELECTOR.b(new WorldGenFeatureRandomChoiceConfiguration(
/* 691 */           (List<WorldGenFeatureRandomChoiceConfigurationWeight>)ImmutableList.of(FANCY_OAK
/* 692 */             .a(0.05F), JUNGLE_BUSH
/* 693 */             .a(0.15F), MEGA_JUNGLE_TREE
/* 694 */             .a(0.7F)), WorldGenerator.RANDOM_PATCH
/*     */           
/* 696 */           .b(a.c)))
/* 697 */       .a(b.l).a(WorldGenDecorator.f.b(new WorldGenDecoratorFrequencyExtraChanceConfiguration(30, 0.1F, 1))));
/*     */   
/* 699 */   public static final WorldGenFeatureConfigured<?, ?> MUSHROOM_FIELD_VEGETATION = a("mushroom_field_vegetation", WorldGenerator.RANDOM_BOOLEAN_SELECTOR.b(new WorldGenFeatureChoiceConfiguration(() -> HUGE_RED_MUSHROOM, () -> HUGE_BROWN_MUSHROOM))
/*     */ 
/*     */       
/* 702 */       .a(b.l));
/*     */   
/*     */   private static <FC extends WorldGenFeatureConfiguration> WorldGenFeatureConfigured<FC, ?> a(String var0, WorldGenFeatureConfigured<FC, ?> var1) {
/* 705 */     return (WorldGenFeatureConfigured<FC, ?>)IRegistry.<WorldGenFeatureConfigured<?, ?>>a(RegistryGeneration.e, var0, var1);
/*     */   }
/*     */   
/*     */   public static final class b {
/* 709 */     public static final WorldGenFeatureTreeBeehive a = new WorldGenFeatureTreeBeehive(0.002F);
/* 710 */     public static final WorldGenFeatureTreeBeehive b = new WorldGenFeatureTreeBeehive(0.02F);
/* 711 */     public static final WorldGenFeatureTreeBeehive c = new WorldGenFeatureTreeBeehive(0.05F);
/* 712 */     public static final WorldGenDecoratorConfigured<WorldGenDecoratorFrequencyConfiguration> d = WorldGenDecorator.r.b(new WorldGenDecoratorFrequencyConfiguration(10));
/*     */     
/* 714 */     public static final WorldGenDecoratorConfigured<WorldGenFeatureEmptyConfiguration2> e = WorldGenDecorator.h.b(WorldGenFeatureDecoratorConfiguration.b);
/* 715 */     public static final WorldGenDecoratorConfigured<WorldGenFeatureEmptyConfiguration2> f = WorldGenDecorator.j.b(WorldGenFeatureDecoratorConfiguration.b);
/* 716 */     public static final WorldGenDecoratorConfigured<WorldGenFeatureEmptyConfiguration2> g = WorldGenDecorator.k.b(WorldGenFeatureDecoratorConfiguration.b);
/* 717 */     public static final WorldGenDecoratorConfigured<WorldGenFeatureEmptyConfiguration2> h = WorldGenDecorator.i.b(WorldGenFeatureDecoratorConfiguration.b);
/*     */     
/* 719 */     public static final WorldGenDecoratorConfigured<WorldGenFeatureChanceDecoratorRangeConfiguration> i = WorldGenDecorator.l.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(10, 20, 128));
/* 720 */     public static final WorldGenDecoratorConfigured<WorldGenFeatureChanceDecoratorRangeConfiguration> j = WorldGenDecorator.l.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(4, 8, 128));
/* 721 */     public static final WorldGenDecoratorConfigured<?> k = WorldGenDecorator.p.b(WorldGenFeatureEmptyConfiguration2.c);
/*     */     
/* 723 */     public static final WorldGenDecoratorConfigured<?> l = e.a();
/* 724 */     public static final WorldGenDecoratorConfigured<?> m = h.a();
/* 725 */     public static final WorldGenDecoratorConfigured<?> n = f.a();
/*     */   }
/*     */   
/*     */   public static final class c {
/* 729 */     protected static final IBlockData a = Blocks.GRASS.getBlockData();
/* 730 */     protected static final IBlockData b = Blocks.FERN.getBlockData();
/* 731 */     protected static final IBlockData c = Blocks.PODZOL.getBlockData();
/* 732 */     protected static final IBlockData d = Blocks.COARSE_DIRT.getBlockData();
/* 733 */     protected static final IBlockData e = Blocks.MYCELIUM.getBlockData();
/* 734 */     protected static final IBlockData f = Blocks.SNOW_BLOCK.getBlockData();
/* 735 */     protected static final IBlockData g = Blocks.ICE.getBlockData();
/* 736 */     protected static final IBlockData h = Blocks.OAK_LOG.getBlockData();
/* 737 */     protected static final IBlockData i = Blocks.OAK_LEAVES.getBlockData();
/* 738 */     protected static final IBlockData j = Blocks.JUNGLE_LOG.getBlockData();
/* 739 */     protected static final IBlockData k = Blocks.JUNGLE_LEAVES.getBlockData();
/* 740 */     protected static final IBlockData l = Blocks.SPRUCE_LOG.getBlockData();
/* 741 */     protected static final IBlockData m = Blocks.SPRUCE_LEAVES.getBlockData();
/* 742 */     protected static final IBlockData n = Blocks.ACACIA_LOG.getBlockData();
/* 743 */     protected static final IBlockData o = Blocks.ACACIA_LEAVES.getBlockData();
/* 744 */     protected static final IBlockData p = Blocks.BIRCH_LOG.getBlockData();
/* 745 */     protected static final IBlockData q = Blocks.BIRCH_LEAVES.getBlockData();
/* 746 */     protected static final IBlockData r = Blocks.DARK_OAK_LOG.getBlockData();
/* 747 */     protected static final IBlockData s = Blocks.DARK_OAK_LEAVES.getBlockData();
/* 748 */     protected static final IBlockData t = Blocks.GRASS_BLOCK.getBlockData();
/* 749 */     protected static final IBlockData u = Blocks.LARGE_FERN.getBlockData();
/* 750 */     protected static final IBlockData v = Blocks.TALL_GRASS.getBlockData();
/* 751 */     protected static final IBlockData w = Blocks.LILAC.getBlockData();
/* 752 */     protected static final IBlockData x = Blocks.ROSE_BUSH.getBlockData();
/* 753 */     protected static final IBlockData y = Blocks.PEONY.getBlockData();
/* 754 */     protected static final IBlockData z = Blocks.BROWN_MUSHROOM.getBlockData();
/* 755 */     protected static final IBlockData A = Blocks.RED_MUSHROOM.getBlockData();
/* 756 */     protected static final IBlockData B = Blocks.PACKED_ICE.getBlockData();
/* 757 */     protected static final IBlockData C = Blocks.BLUE_ICE.getBlockData();
/* 758 */     protected static final IBlockData D = Blocks.LILY_OF_THE_VALLEY.getBlockData();
/* 759 */     protected static final IBlockData E = Blocks.BLUE_ORCHID.getBlockData();
/* 760 */     protected static final IBlockData F = Blocks.POPPY.getBlockData();
/* 761 */     protected static final IBlockData G = Blocks.DANDELION.getBlockData();
/* 762 */     protected static final IBlockData H = Blocks.DEAD_BUSH.getBlockData();
/* 763 */     protected static final IBlockData I = Blocks.MELON.getBlockData();
/* 764 */     protected static final IBlockData J = Blocks.PUMPKIN.getBlockData();
/* 765 */     protected static final IBlockData K = Blocks.SWEET_BERRY_BUSH.getBlockData().set(BlockSweetBerryBush.a, Integer.valueOf(3));
/* 766 */     protected static final IBlockData L = Blocks.FIRE.getBlockData();
/* 767 */     protected static final IBlockData M = Blocks.SOUL_FIRE.getBlockData();
/* 768 */     protected static final IBlockData N = Blocks.NETHERRACK.getBlockData();
/* 769 */     protected static final IBlockData O = Blocks.SOUL_SOIL.getBlockData();
/* 770 */     protected static final IBlockData P = Blocks.CRIMSON_ROOTS.getBlockData();
/* 771 */     protected static final IBlockData Q = Blocks.LILY_PAD.getBlockData();
/* 772 */     protected static final IBlockData R = Blocks.SNOW.getBlockData();
/* 773 */     protected static final IBlockData S = Blocks.JACK_O_LANTERN.getBlockData();
/* 774 */     protected static final IBlockData T = Blocks.SUNFLOWER.getBlockData();
/* 775 */     protected static final IBlockData U = Blocks.CACTUS.getBlockData();
/* 776 */     protected static final IBlockData V = Blocks.SUGAR_CANE.getBlockData();
/* 777 */     protected static final IBlockData W = Blocks.RED_MUSHROOM_BLOCK.getBlockData().set(BlockHugeMushroom.f, Boolean.valueOf(false));
/* 778 */     protected static final IBlockData X = Blocks.BROWN_MUSHROOM_BLOCK.getBlockData().set(BlockHugeMushroom.e, Boolean.valueOf(true)).set(BlockHugeMushroom.f, Boolean.valueOf(false));
/* 779 */     protected static final IBlockData Y = Blocks.MUSHROOM_STEM.getBlockData().set(BlockHugeMushroom.e, Boolean.valueOf(false)).set(BlockHugeMushroom.f, Boolean.valueOf(false));
/*     */     
/* 781 */     protected static final Fluid Z = FluidTypes.WATER.h();
/* 782 */     protected static final Fluid aa = FluidTypes.LAVA.h();
/* 783 */     protected static final IBlockData ab = Blocks.WATER.getBlockData();
/* 784 */     protected static final IBlockData ac = Blocks.LAVA.getBlockData();
/* 785 */     protected static final IBlockData ad = Blocks.DIRT.getBlockData();
/* 786 */     protected static final IBlockData ae = Blocks.GRAVEL.getBlockData();
/* 787 */     protected static final IBlockData af = Blocks.GRANITE.getBlockData();
/* 788 */     protected static final IBlockData ag = Blocks.DIORITE.getBlockData();
/* 789 */     protected static final IBlockData ah = Blocks.ANDESITE.getBlockData();
/* 790 */     protected static final IBlockData ai = Blocks.COAL_ORE.getBlockData();
/* 791 */     protected static final IBlockData aj = Blocks.IRON_ORE.getBlockData();
/* 792 */     protected static final IBlockData ak = Blocks.GOLD_ORE.getBlockData();
/* 793 */     protected static final IBlockData al = Blocks.REDSTONE_ORE.getBlockData();
/* 794 */     protected static final IBlockData am = Blocks.DIAMOND_ORE.getBlockData();
/* 795 */     protected static final IBlockData an = Blocks.LAPIS_ORE.getBlockData();
/* 796 */     protected static final IBlockData ao = Blocks.STONE.getBlockData();
/* 797 */     protected static final IBlockData ap = Blocks.EMERALD_ORE.getBlockData();
/* 798 */     protected static final IBlockData aq = Blocks.INFESTED_STONE.getBlockData();
/* 799 */     protected static final IBlockData ar = Blocks.SAND.getBlockData();
/* 800 */     protected static final IBlockData as = Blocks.CLAY.getBlockData();
/* 801 */     protected static final IBlockData at = Blocks.MOSSY_COBBLESTONE.getBlockData();
/* 802 */     protected static final IBlockData au = Blocks.SEAGRASS.getBlockData();
/* 803 */     protected static final IBlockData av = Blocks.MAGMA_BLOCK.getBlockData();
/* 804 */     protected static final IBlockData aw = Blocks.SOUL_SAND.getBlockData();
/* 805 */     protected static final IBlockData ax = Blocks.NETHER_GOLD_ORE.getBlockData();
/* 806 */     protected static final IBlockData ay = Blocks.NETHER_QUARTZ_ORE.getBlockData();
/* 807 */     protected static final IBlockData az = Blocks.BLACKSTONE.getBlockData();
/* 808 */     protected static final IBlockData aA = Blocks.ANCIENT_DEBRIS.getBlockData();
/* 809 */     protected static final IBlockData aB = Blocks.BASALT.getBlockData();
/* 810 */     protected static final IBlockData aC = Blocks.CRIMSON_FUNGUS.getBlockData();
/* 811 */     protected static final IBlockData aD = Blocks.WARPED_FUNGUS.getBlockData();
/* 812 */     protected static final IBlockData aE = Blocks.WARPED_ROOTS.getBlockData();
/* 813 */     protected static final IBlockData aF = Blocks.NETHER_SPROUTS.getBlockData();
/*     */   }
/*     */   
/*     */   public static final class a {
/* 817 */     public static final WorldGenFeatureRandomPatchConfiguration a = (new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(BiomeDecoratorGroups.c.a), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */       
/* 821 */       .a(32)
/* 822 */       .d();
/* 823 */     public static final WorldGenFeatureRandomPatchConfiguration b = (new WorldGenFeatureRandomPatchConfiguration.a((new WorldGenFeatureStateProviderWeighted())
/* 824 */         .a(BiomeDecoratorGroups.c.a, 1).a(BiomeDecoratorGroups.c.b, 4), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */       
/* 827 */       .a(32)
/* 828 */       .d();
/* 829 */     public static final WorldGenFeatureRandomPatchConfiguration c = (new WorldGenFeatureRandomPatchConfiguration.a((new WorldGenFeatureStateProviderWeighted())
/* 830 */         .a(BiomeDecoratorGroups.c.a, 3).a(BiomeDecoratorGroups.c.b, 1), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */       
/* 833 */       .b((Set<IBlockData>)ImmutableSet.of(BiomeDecoratorGroups.c.c))
/* 834 */       .a(32)
/* 835 */       .d();
/* 836 */     public static final WorldGenFeatureRandomPatchConfiguration d = (new WorldGenFeatureRandomPatchConfiguration.a((new WorldGenFeatureStateProviderWeighted())
/* 837 */         .a(BiomeDecoratorGroups.c.F, 2).a(BiomeDecoratorGroups.c.G, 1), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */       
/* 840 */       .a(64)
/* 841 */       .d();
/* 842 */     public static final WorldGenFeatureRandomPatchConfiguration e = (new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(BiomeDecoratorGroups.c.H), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */       
/* 846 */       .a(4)
/* 847 */       .d();
/* 848 */     public static final WorldGenFeatureRandomPatchConfiguration f = (new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(BiomeDecoratorGroups.c.K), WorldGenBlockPlacerSimple.c))
/*     */ 
/*     */ 
/*     */       
/* 852 */       .a(64)
/* 853 */       .a((Set<Block>)ImmutableSet.of(BiomeDecoratorGroups.c.t.getBlock()))
/* 854 */       .b()
/* 855 */       .d();
/* 856 */     public static final WorldGenFeatureRandomPatchConfiguration g = (new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(BiomeDecoratorGroups.c.v), new WorldGenBlockPlacerDoublePlant()))
/*     */ 
/*     */ 
/*     */       
/* 860 */       .a(64)
/* 861 */       .b()
/* 862 */       .d();
/* 863 */     public static final WorldGenFeatureRandomPatchConfiguration h = (new WorldGenFeatureRandomPatchConfiguration.a(new WorldGenFeatureStateProviderSimpl(BiomeDecoratorGroups.c.V), new WorldGenBlockPlacerColumn(2, 2)))
/*     */ 
/*     */ 
/*     */       
/* 867 */       .a(20)
/* 868 */       .b(4)
/* 869 */       .c(0)
/* 870 */       .d(4)
/* 871 */       .b()
/* 872 */       .c()
/* 873 */       .d();
/* 874 */     public static final WorldGenFeatureHellFlowingLavaConfiguration i = new WorldGenFeatureHellFlowingLavaConfiguration(BiomeDecoratorGroups.c.aa, true, 4, 1, (Set<Block>)ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE));
/* 875 */     public static final WorldGenFeatureHellFlowingLavaConfiguration j = new WorldGenFeatureHellFlowingLavaConfiguration(BiomeDecoratorGroups.c.aa, false, 5, 0, (Set<Block>)ImmutableSet.of(Blocks.NETHERRACK));
/* 876 */     public static final WorldGenFeatureBlockPileConfiguration k = new WorldGenFeatureBlockPileConfiguration((new WorldGenFeatureStateProviderWeighted())
/* 877 */         .a(BiomeDecoratorGroups.c.P, 87)
/* 878 */         .a(BiomeDecoratorGroups.c.aC, 11)
/* 879 */         .a(BiomeDecoratorGroups.c.aD, 1));
/*     */     
/* 881 */     public static final WorldGenFeatureBlockPileConfiguration l = new WorldGenFeatureBlockPileConfiguration((new WorldGenFeatureStateProviderWeighted())
/* 882 */         .a(BiomeDecoratorGroups.c.aE, 85)
/* 883 */         .a(BiomeDecoratorGroups.c.P, 1)
/* 884 */         .a(BiomeDecoratorGroups.c.aD, 13)
/* 885 */         .a(BiomeDecoratorGroups.c.aC, 1));
/*     */     
/* 887 */     public static final WorldGenFeatureBlockPileConfiguration m = new WorldGenFeatureBlockPileConfiguration(new WorldGenFeatureStateProviderSimpl(BiomeDecoratorGroups.c.aF));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeDecoratorGroups.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */