/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TagsBlock
/*     */ {
/*   9 */   protected static final TagUtil<Block> a = TagStatic.a(new MinecraftKey("block"), ITagRegistry::getBlockTags);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  14 */   public static final Tag.e<Block> WOOL = a("wool");
/*  15 */   public static final Tag.e<Block> PLANKS = a("planks");
/*  16 */   public static final Tag.e<Block> STONE_BRICKS = a("stone_bricks");
/*  17 */   public static final Tag.e<Block> WOODEN_BUTTONS = a("wooden_buttons");
/*  18 */   public static final Tag.e<Block> BUTTONS = a("buttons");
/*  19 */   public static final Tag.e<Block> CARPETS = a("carpets");
/*  20 */   public static final Tag.e<Block> WOODEN_DOORS = a("wooden_doors");
/*  21 */   public static final Tag.e<Block> WOODEN_STAIRS = a("wooden_stairs");
/*  22 */   public static final Tag.e<Block> WOODEN_SLABS = a("wooden_slabs");
/*  23 */   public static final Tag.e<Block> WOODEN_FENCES = a("wooden_fences");
/*  24 */   public static final Tag.e<Block> PRESSURE_PLATES = a("pressure_plates");
/*  25 */   public static final Tag.e<Block> WOODEN_PRESSURE_PLATES = a("wooden_pressure_plates");
/*  26 */   public static final Tag.e<Block> STONE_PRESSURE_PLATES = a("stone_pressure_plates");
/*  27 */   public static final Tag.e<Block> WOODEN_TRAPDOORS = a("wooden_trapdoors");
/*  28 */   public static final Tag.e<Block> DOORS = a("doors");
/*  29 */   public static final Tag.e<Block> SAPLINGS = a("saplings");
/*  30 */   public static final Tag.e<Block> LOGS_THAT_BURN = a("logs_that_burn");
/*  31 */   public static final Tag.e<Block> LOGS = a("logs");
/*  32 */   public static final Tag.e<Block> DARK_OAK_LOGS = a("dark_oak_logs");
/*  33 */   public static final Tag.e<Block> OAK_LOGS = a("oak_logs");
/*  34 */   public static final Tag.e<Block> BIRCH_LOGS = a("birch_logs");
/*  35 */   public static final Tag.e<Block> ACACIA_LOGS = a("acacia_logs");
/*  36 */   public static final Tag.e<Block> JUNGLE_LOGS = a("jungle_logs");
/*  37 */   public static final Tag.e<Block> SPRUCE_LOGS = a("spruce_logs");
/*  38 */   public static final Tag.e<Block> CRIMSON_STEMS = a("crimson_stems");
/*  39 */   public static final Tag.e<Block> WARPED_STEMS = a("warped_stems");
/*  40 */   public static final Tag.e<Block> BANNERS = a("banners");
/*  41 */   public static final Tag.e<Block> SAND = a("sand");
/*  42 */   public static final Tag.e<Block> STAIRS = a("stairs");
/*  43 */   public static final Tag.e<Block> SLABS = a("slabs");
/*  44 */   public static final Tag.e<Block> WALLS = a("walls");
/*  45 */   public static final Tag.e<Block> ANVIL = a("anvil");
/*  46 */   public static final Tag.e<Block> RAILS = a("rails");
/*  47 */   public static final Tag.e<Block> LEAVES = a("leaves");
/*  48 */   public static final Tag.e<Block> TRAPDOORS = a("trapdoors");
/*  49 */   public static final Tag.e<Block> SMALL_FLOWERS = a("small_flowers");
/*  50 */   public static final Tag.e<Block> BEDS = a("beds");
/*  51 */   public static final Tag.e<Block> FENCES = a("fences");
/*  52 */   public static final Tag.e<Block> TALL_FLOWERS = a("tall_flowers");
/*  53 */   public static final Tag.e<Block> FLOWERS = a("flowers");
/*  54 */   public static final Tag.e<Block> PIGLIN_REPELLENTS = a("piglin_repellents");
/*  55 */   public static final Tag.e<Block> GOLD_ORES = a("gold_ores");
/*  56 */   public static final Tag.e<Block> NON_FLAMMABLE_WOOD = a("non_flammable_wood");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public static final Tag.e<Block> FLOWER_POTS = a("flower_pots");
/*  62 */   public static final Tag.e<Block> ENDERMAN_HOLDABLE = a("enderman_holdable");
/*  63 */   public static final Tag.e<Block> ICE = a("ice");
/*  64 */   public static final Tag.e<Block> VALID_SPAWN = a("valid_spawn");
/*  65 */   public static final Tag.e<Block> IMPERMEABLE = a("impermeable");
/*  66 */   public static final Tag.e<Block> UNDERWATER_BONEMEALS = a("underwater_bonemeals");
/*  67 */   public static final Tag.e<Block> CORAL_BLOCKS = a("coral_blocks");
/*  68 */   public static final Tag.e<Block> WALL_CORALS = a("wall_corals");
/*  69 */   public static final Tag.e<Block> CORAL_PLANTS = a("coral_plants");
/*  70 */   public static final Tag.e<Block> CORALS = a("corals");
/*  71 */   public static final Tag.e<Block> BAMBOO_PLANTABLE_ON = a("bamboo_plantable_on");
/*  72 */   public static final Tag.e<Block> STANDING_SIGNS = a("standing_signs");
/*  73 */   public static final Tag.e<Block> WALL_SIGNS = a("wall_signs");
/*  74 */   public static final Tag.e<Block> SIGNS = a("signs");
/*  75 */   public static final Tag.e<Block> DRAGON_IMMUNE = a("dragon_immune");
/*  76 */   public static final Tag.e<Block> WITHER_IMMUNE = a("wither_immune");
/*  77 */   public static final Tag.e<Block> WITHER_SUMMON_BASE_BLOCKS = a("wither_summon_base_blocks");
/*  78 */   public static final Tag.e<Block> BEEHIVES = a("beehives");
/*  79 */   public static final Tag.e<Block> CROPS = a("crops");
/*  80 */   public static final Tag.e<Block> BEE_GROWABLES = a("bee_growables");
/*  81 */   public static final Tag.e<Block> PORTALS = a("portals");
/*  82 */   public static final Tag.e<Block> FIRE = a("fire");
/*  83 */   public static final Tag.e<Block> NYLIUM = a("nylium");
/*  84 */   public static final Tag.e<Block> WART_BLOCKS = a("wart_blocks");
/*  85 */   public static final Tag.e<Block> BEACON_BASE_BLOCKS = a("beacon_base_blocks");
/*  86 */   public static final Tag.e<Block> SOUL_SPEED_BLOCKS = a("soul_speed_blocks");
/*  87 */   public static final Tag.e<Block> WALL_POST_OVERRIDE = a("wall_post_override");
/*  88 */   public static final Tag.e<Block> CLIMBABLE = a("climbable");
/*  89 */   public static final Tag.e<Block> SHULKER_BOXES = a("shulker_boxes");
/*  90 */   public static final Tag.e<Block> HOGLIN_REPELLENTS = a("hoglin_repellents");
/*  91 */   public static final Tag.e<Block> SOUL_FIRE_BASE_BLOCKS = a("soul_fire_base_blocks");
/*  92 */   public static final Tag.e<Block> STRIDER_WARM_BLOCKS = a("strider_warm_blocks");
/*  93 */   public static final Tag.e<Block> CAMPFIRES = a("campfires");
/*  94 */   public static final Tag.e<Block> GUARDED_BY_PIGLINS = a("guarded_by_piglins");
/*  95 */   public static final Tag.e<Block> PREVENT_MOB_SPAWNING_INSIDE = a("prevent_mob_spawning_inside");
/*  96 */   public static final Tag.e<Block> aB = a("fence_gates");
/*  97 */   public static final Tag.e<Block> aC = a("unstable_bottom_center");
/*  98 */   public static final Tag.e<Block> aD = a("mushroom_grow_block");
/*     */   
/* 100 */   public static final Tag.e<Block> aE = a("infiniburn_overworld");
/* 101 */   public static final Tag.e<Block> aF = a("infiniburn_nether");
/* 102 */   public static final Tag.e<Block> aG = a("infiniburn_end");
/*     */   
/* 104 */   public static final Tag.e<Block> aH = a("base_stone_overworld");
/* 105 */   public static final Tag.e<Block> aI = a("base_stone_nether");
/*     */   
/*     */   private static Tag.e<Block> a(String var0) {
/* 108 */     return a.a(var0);
/*     */   }
/*     */   
/*     */   public static Tags<Block> a() {
/* 112 */     return a.b();
/*     */   }
/*     */   
/*     */   public static List<? extends Tag.e<Block>> b() {
/* 116 */     return a.c();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagsBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */