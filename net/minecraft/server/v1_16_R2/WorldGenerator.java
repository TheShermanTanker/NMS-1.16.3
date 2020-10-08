/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Random;
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
/*     */ public abstract class WorldGenerator<FC extends WorldGenFeatureConfiguration>
/*     */ {
/*  41 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> NO_OP = a("no_op", new WorldGenFeatureEmpty(WorldGenFeatureEmptyConfiguration.a));
/*  42 */   public static final WorldGenerator<WorldGenFeatureTreeConfiguration> TREE = a("tree", new WorldGenTrees(WorldGenFeatureTreeConfiguration.a));
/*     */   
/*  44 */   public static final WorldGenFlowers<WorldGenFeatureRandomPatchConfiguration> FLOWER = a("flower", new WorldGenFeatureFlower(WorldGenFeatureRandomPatchConfiguration.a));
/*  45 */   public static final WorldGenFlowers<WorldGenFeatureRandomPatchConfiguration> NO_BONEMEAL_FLOWER = a("no_bonemeal_flower", new WorldGenFeatureFlower(WorldGenFeatureRandomPatchConfiguration.a));
/*  46 */   public static final WorldGenerator<WorldGenFeatureRandomPatchConfiguration> RANDOM_PATCH = a("random_patch", new WorldGenFeatureRandomPatch(WorldGenFeatureRandomPatchConfiguration.a));
/*  47 */   public static final WorldGenerator<WorldGenFeatureBlockPileConfiguration> BLOCK_PILE = a("block_pile", new WorldGenFeatureBlockPile(WorldGenFeatureBlockPileConfiguration.a));
/*  48 */   public static final WorldGenerator<WorldGenFeatureHellFlowingLavaConfiguration> SPRING_FEATURE = a("spring_feature", new WorldGenLiquids(WorldGenFeatureHellFlowingLavaConfiguration.a));
/*     */   
/*  50 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CHORUS_PLANT = a("chorus_plant", new WorldGenFeatureChorusPlant(WorldGenFeatureEmptyConfiguration.a));
/*  51 */   public static final WorldGenerator<WorldGenFeatureReplaceBlockConfiguration> EMERALD_ORE = a("emerald_ore", new WorldGenFeatureReplaceBlock(WorldGenFeatureReplaceBlockConfiguration.a));
/*     */   
/*  53 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> VOID_START_PLATFORM = a("void_start_platform", new WorldGenFeatureEndPlatform(WorldGenFeatureEmptyConfiguration.a));
/*  54 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> DESERT_WELL = a("desert_well", new WorldGenDesertWell(WorldGenFeatureEmptyConfiguration.a));
/*  55 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> FOSSIL = a("fossil", new WorldGenFossils(WorldGenFeatureEmptyConfiguration.a));
/*  56 */   public static final WorldGenerator<WorldGenFeatureMushroomConfiguration> HUGE_RED_MUSHROOM = a("huge_red_mushroom", new WorldGenHugeMushroomRed(WorldGenFeatureMushroomConfiguration.a));
/*  57 */   public static final WorldGenerator<WorldGenFeatureMushroomConfiguration> HUGE_BROWN_MUSHROOM = a("huge_brown_mushroom", new WorldGenHugeMushroomBrown(WorldGenFeatureMushroomConfiguration.a));
/*  58 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> ICE_SPIKE = a("ice_spike", new WorldGenPackedIce2(WorldGenFeatureEmptyConfiguration.a));
/*  59 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> GLOWSTONE_BLOB = a("glowstone_blob", new WorldGenLightStone1(WorldGenFeatureEmptyConfiguration.a));
/*  60 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> FREEZE_TOP_LAYER = a("freeze_top_layer", new WorldGenFeatureIceSnow(WorldGenFeatureEmptyConfiguration.a));
/*  61 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> VINES = a("vines", new WorldGenVines(WorldGenFeatureEmptyConfiguration.a));
/*  62 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> MONSTER_ROOM = a("monster_room", new WorldGenDungeons(WorldGenFeatureEmptyConfiguration.a));
/*  63 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> BLUE_ICE = a("blue_ice", new WorldGenFeatureBlueIce(WorldGenFeatureEmptyConfiguration.a));
/*  64 */   public static final WorldGenerator<WorldGenFeatureLakeConfiguration> ICEBERG = a("iceberg", new WorldGenFeatureIceburg(WorldGenFeatureLakeConfiguration.a));
/*  65 */   public static final WorldGenerator<WorldGenFeatureLakeConfiguration> FOREST_ROCK = a("forest_rock", new WorldGenTaigaStructure(WorldGenFeatureLakeConfiguration.a));
/*  66 */   public static final WorldGenerator<WorldGenFeatureCircleConfiguration> DISK = a("disk", new WorldGenFeatureCircle(WorldGenFeatureCircleConfiguration.a));
/*  67 */   public static final WorldGenerator<WorldGenFeatureCircleConfiguration> ICE_PATCH = a("ice_patch", new WorldGenPackedIce1(WorldGenFeatureCircleConfiguration.a));
/*  68 */   public static final WorldGenerator<WorldGenFeatureLakeConfiguration> LAKE = a("lake", new WorldGenLakes(WorldGenFeatureLakeConfiguration.a));
/*  69 */   public static final WorldGenerator<WorldGenFeatureOreConfiguration> ORE = a("ore", new WorldGenMinable(WorldGenFeatureOreConfiguration.a));
/*  70 */   public static final WorldGenerator<WorldGenFeatureEndSpikeConfiguration> END_SPIKE = a("end_spike", new WorldGenEnder(WorldGenFeatureEndSpikeConfiguration.a));
/*  71 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> END_ISLAND = a("end_island", new WorldGenEndIsland(WorldGenFeatureEmptyConfiguration.a));
/*  72 */   public static final WorldGenerator<WorldGenEndGatewayConfiguration> END_GATEWAY = a("end_gateway", new WorldGenEndGateway(WorldGenEndGatewayConfiguration.a));
/*  73 */   public static final WorldGenFeatureSeaGrass SEAGRASS = a("seagrass", new WorldGenFeatureSeaGrass(WorldGenFeatureConfigurationChance.b));
/*  74 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> KELP = a("kelp", new WorldGenFeatureKelp(WorldGenFeatureEmptyConfiguration.a));
/*  75 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CORAL_TREE = a("coral_tree", new WorldGenFeatureCoralTree(WorldGenFeatureEmptyConfiguration.a));
/*  76 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CORAL_MUSHROOM = a("coral_mushroom", new WorldGenFeatureCoralMushroom(WorldGenFeatureEmptyConfiguration.a));
/*  77 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CORAL_CLAW = a("coral_claw", new WorldGenFeatureCoralClaw(WorldGenFeatureEmptyConfiguration.a));
/*  78 */   public static final WorldGenerator<WorldGenDecoratorFrequencyConfiguration> SEA_PICKLE = a("sea_pickle", new WorldGenFeatureSeaPickel(WorldGenDecoratorFrequencyConfiguration.a));
/*  79 */   public static final WorldGenerator<WorldGenFeatureBlockConfiguration> SIMPLE_BLOCK = a("simple_block", new WorldGenFeatureBlock(WorldGenFeatureBlockConfiguration.a));
/*  80 */   public static final WorldGenerator<WorldGenFeatureConfigurationChance> BAMBOO = a("bamboo", new WorldGenFeatureBamboo(WorldGenFeatureConfigurationChance.b));
/*     */   
/*  82 */   public static final WorldGenerator<WorldGenFeatureHugeFungiConfiguration> HUGE_FUNGUS = a("huge_fungus", new WorldGenFeatureHugeFungi(WorldGenFeatureHugeFungiConfiguration.a));
/*  83 */   public static final WorldGenerator<WorldGenFeatureBlockPileConfiguration> NETHER_FOREST_VEGETATION = a("nether_forest_vegetation", new WorldGenFeatureNetherForestVegetation(WorldGenFeatureBlockPileConfiguration.a));
/*  84 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> WEEPING_VINES = a("weeping_vines", new WorldGenFeatureWeepingVines(WorldGenFeatureEmptyConfiguration.a));
/*  85 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> TWISTING_VINES = a("twisting_vines", new WorldGenFeatureTwistingVines(WorldGenFeatureEmptyConfiguration.a));
/*     */   
/*  87 */   public static final WorldGenerator<WorldGenFeatureBasaltColumnsConfiguration> BASALT_COLUMNS = a("basalt_columns", new WorldGenFeatureBasaltColumns(WorldGenFeatureBasaltColumnsConfiguration.a));
/*  88 */   public static final WorldGenerator<WorldGenFeatureDeltaConfiguration> DELTA_FEATURE = a("delta_feature", new WorldGenFeatureDelta(WorldGenFeatureDeltaConfiguration.a));
/*  89 */   public static final WorldGenerator<WorldGenFeatureRadiusConfiguration> NETHERRACK_REPLACE_BLOBS = a("netherrack_replace_blobs", new WorldGenFeatureNetherrackReplaceBlobs(WorldGenFeatureRadiusConfiguration.a));
/*     */   
/*  91 */   public static final WorldGenerator<WorldGenFeatureFillConfiguration> FILL_LAYER = a("fill_layer", new WorldGenFeatureFill(WorldGenFeatureFillConfiguration.a));
/*  92 */   public static final WorldGenBonusChest BONUS_CHEST = a("bonus_chest", new WorldGenBonusChest(WorldGenFeatureEmptyConfiguration.a));
/*  93 */   public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> BASALT_PILLAR = a("basalt_pillar", new WorldGenFeatureBasaltPillar(WorldGenFeatureEmptyConfiguration.a));
/*  94 */   public static final WorldGenerator<WorldGenFeatureOreConfiguration> NO_SURFACE_ORE = a("no_surface_ore", new WorldGenFeatureNoSurfaceOre(WorldGenFeatureOreConfiguration.a));
/*     */   
/*  96 */   public static final WorldGenerator<WorldGenFeatureRandomChoiceConfiguration> RANDOM_SELECTOR = a("random_selector", new WorldGenFeatureRandomChoice(WorldGenFeatureRandomChoiceConfiguration.a));
/*  97 */   public static final WorldGenerator<WorldGenFeatureRandom2> SIMPLE_RANDOM_SELECTOR = a("simple_random_selector", new WorldGenFeatureRandom2Configuration(WorldGenFeatureRandom2.a));
/*  98 */   public static final WorldGenerator<WorldGenFeatureChoiceConfiguration> RANDOM_BOOLEAN_SELECTOR = a("random_boolean_selector", new WorldGenFeatureChoice(WorldGenFeatureChoiceConfiguration.a));
/*  99 */   public static final WorldGenerator<WorldGenFeatureCompositeConfiguration> DECORATED = a("decorated", new WorldGenFeatureComposite(WorldGenFeatureCompositeConfiguration.a));
/*     */   
/*     */   private static <C extends WorldGenFeatureConfiguration, F extends WorldGenerator<C>> F a(String var0, F var1) {
/* 102 */     return (F)IRegistry.<WorldGenerator>a((IRegistry)IRegistry.FEATURE, var0, (WorldGenerator)var1);
/*     */   }
/*     */   
/*     */   private final Codec<WorldGenFeatureConfigured<FC, WorldGenerator<FC>>> a;
/*     */   
/*     */   public WorldGenerator(Codec<FC> var0) {
/* 108 */     this.a = var0.fieldOf("config").xmap(var0 -> new WorldGenFeatureConfigured<>(this, var0), var0 -> var0.f).codec();
/*     */   }
/*     */   
/*     */   public Codec<WorldGenFeatureConfigured<FC, WorldGenerator<FC>>> a() {
/* 112 */     return this.a;
/*     */   }
/*     */   
/*     */   public WorldGenFeatureConfigured<FC, ?> b(FC var0) {
/* 116 */     return new WorldGenFeatureConfigured<>(this, var0);
/*     */   }
/*     */   
/*     */   protected void a(IWorldWriter var0, BlockPosition var1, IBlockData var2) {
/* 120 */     var0.setTypeAndData(var1, var2, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean a(Block var0) {
/* 126 */     return (var0 == Blocks.STONE || var0 == Blocks.GRANITE || var0 == Blocks.DIORITE || var0 == Blocks.ANDESITE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean b(Block var0) {
/* 133 */     return (var0 == Blocks.DIRT || var0 == Blocks.GRASS_BLOCK || var0 == Blocks.PODZOL || var0 == Blocks.COARSE_DIRT || var0 == Blocks.MYCELIUM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(VirtualLevelReadable var0, BlockPosition var1) {
/* 141 */     return var0.a(var1, var0 -> b(var0.getBlock()));
/*     */   }
/*     */   
/*     */   public static boolean b(VirtualLevelReadable var0, BlockPosition var1) {
/* 145 */     return var0.a(var1, BlockBase.BlockData::isAir);
/*     */   }
/*     */   
/*     */   public abstract boolean generate(GeneratorAccessSeed paramGeneratorAccessSeed, ChunkGenerator paramChunkGenerator, Random paramRandom, BlockPosition paramBlockPosition, FC paramFC);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */