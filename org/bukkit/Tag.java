/*     */ package org.bukkit;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public interface Tag<T extends Keyed>
/*     */   extends Keyed
/*     */ {
/*     */   public static final String REGISTRY_BLOCKS = "blocks";
/*  24 */   public static final Tag<Material> WOOL = Bukkit.getTag("blocks", NamespacedKey.minecraft("wool"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  28 */   public static final Tag<Material> PLANKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("planks"), Material.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public static final Tag<Material> STONE_BRICKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("stone_bricks"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  37 */   public static final Tag<Material> WOODEN_BUTTONS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wooden_buttons"), Material.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public static final Tag<Material> BUTTONS = Bukkit.getTag("blocks", NamespacedKey.minecraft("buttons"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  46 */   public static final Tag<Material> CARPETS = Bukkit.getTag("blocks", NamespacedKey.minecraft("carpets"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  50 */   public static final Tag<Material> WOODEN_DOORS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wooden_doors"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final Tag<Material> WOODEN_STAIRS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wooden_stairs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static final Tag<Material> WOODEN_SLABS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wooden_slabs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  62 */   public static final Tag<Material> WOODEN_FENCES = Bukkit.getTag("blocks", NamespacedKey.minecraft("wooden_fences"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final Tag<Material> PRESSURE_PLATES = Bukkit.getTag("blocks", NamespacedKey.minecraft("pressure_plates"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  70 */   public static final Tag<Material> WOODEN_PRESSURE_PLATES = Bukkit.getTag("blocks", NamespacedKey.minecraft("wooden_pressure_plates"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  74 */   public static final Tag<Material> STONE_PRESSURE_PLATES = Bukkit.getTag("blocks", NamespacedKey.minecraft("stone_pressure_plates"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final Tag<Material> WOODEN_TRAPDOORS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wooden_trapdoors"), Material.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final Tag<Material> DOORS = Bukkit.getTag("blocks", NamespacedKey.minecraft("doors"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static final Tag<Material> SAPLINGS = Bukkit.getTag("blocks", NamespacedKey.minecraft("saplings"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  91 */   public static final Tag<Material> LOGS_THAT_BURN = Bukkit.getTag("blocks", NamespacedKey.minecraft("logs_that_burn"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  95 */   public static final Tag<Material> LOGS = Bukkit.getTag("blocks", NamespacedKey.minecraft("logs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*  99 */   public static final Tag<Material> DARK_OAK_LOGS = Bukkit.getTag("blocks", NamespacedKey.minecraft("dark_oak_logs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 103 */   public static final Tag<Material> OAK_LOGS = Bukkit.getTag("blocks", NamespacedKey.minecraft("oak_logs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static final Tag<Material> BIRCH_LOGS = Bukkit.getTag("blocks", NamespacedKey.minecraft("birch_logs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static final Tag<Material> ACACIA_LOGS = Bukkit.getTag("blocks", NamespacedKey.minecraft("acacia_logs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static final Tag<Material> JUNGLE_LOGS = Bukkit.getTag("blocks", NamespacedKey.minecraft("jungle_logs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 119 */   public static final Tag<Material> SPRUCE_LOGS = Bukkit.getTag("blocks", NamespacedKey.minecraft("spruce_logs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 123 */   public static final Tag<Material> CRIMSON_STEMS = Bukkit.getTag("blocks", NamespacedKey.minecraft("crimson_stems"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 127 */   public static final Tag<Material> WARPED_STEMS = Bukkit.getTag("blocks", NamespacedKey.minecraft("warped_stems"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 131 */   public static final Tag<Material> BANNERS = Bukkit.getTag("blocks", NamespacedKey.minecraft("banners"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 135 */   public static final Tag<Material> SAND = Bukkit.getTag("blocks", NamespacedKey.minecraft("sand"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 139 */   public static final Tag<Material> STAIRS = Bukkit.getTag("blocks", NamespacedKey.minecraft("stairs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final Tag<Material> SLABS = Bukkit.getTag("blocks", NamespacedKey.minecraft("slabs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 147 */   public static final Tag<Material> WALLS = Bukkit.getTag("blocks", NamespacedKey.minecraft("walls"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 151 */   public static final Tag<Material> ANVIL = Bukkit.getTag("blocks", NamespacedKey.minecraft("anvil"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 155 */   public static final Tag<Material> RAILS = Bukkit.getTag("blocks", NamespacedKey.minecraft("rails"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 159 */   public static final Tag<Material> LEAVES = Bukkit.getTag("blocks", NamespacedKey.minecraft("leaves"), Material.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public static final Tag<Material> TRAPDOORS = Bukkit.getTag("blocks", NamespacedKey.minecraft("trapdoors"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 168 */   public static final Tag<Material> FLOWER_POTS = Bukkit.getTag("blocks", NamespacedKey.minecraft("flower_pots"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 172 */   public static final Tag<Material> SMALL_FLOWERS = Bukkit.getTag("blocks", NamespacedKey.minecraft("small_flowers"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 176 */   public static final Tag<Material> BEDS = Bukkit.getTag("blocks", NamespacedKey.minecraft("beds"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 180 */   public static final Tag<Material> FENCES = Bukkit.getTag("blocks", NamespacedKey.minecraft("fences"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 184 */   public static final Tag<Material> TALL_FLOWERS = Bukkit.getTag("blocks", NamespacedKey.minecraft("tall_flowers"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 188 */   public static final Tag<Material> FLOWERS = Bukkit.getTag("blocks", NamespacedKey.minecraft("flowers"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 192 */   public static final Tag<Material> PIGLIN_REPELLENTS = Bukkit.getTag("blocks", NamespacedKey.minecraft("piglin_repellents"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 196 */   public static final Tag<Material> GOLD_ORES = Bukkit.getTag("blocks", NamespacedKey.minecraft("gold_ores"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 200 */   public static final Tag<Material> NON_FLAMMABLE_WOOD = Bukkit.getTag("blocks", NamespacedKey.minecraft("non_flammable_wood"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 204 */   public static final Tag<Material> ENDERMAN_HOLDABLE = Bukkit.getTag("blocks", NamespacedKey.minecraft("enderman_holdable"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 208 */   public static final Tag<Material> ICE = Bukkit.getTag("blocks", NamespacedKey.minecraft("ice"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 212 */   public static final Tag<Material> VALID_SPAWN = Bukkit.getTag("blocks", NamespacedKey.minecraft("valid_spawn"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 216 */   public static final Tag<Material> IMPERMEABLE = Bukkit.getTag("blocks", NamespacedKey.minecraft("impermeable"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 220 */   public static final Tag<Material> UNDERWATER_BONEMEALS = Bukkit.getTag("blocks", NamespacedKey.minecraft("underwater_bonemeals"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 224 */   public static final Tag<Material> CORAL_BLOCKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("coral_blocks"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 228 */   public static final Tag<Material> WALL_CORALS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wall_corals"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 232 */   public static final Tag<Material> CORAL_PLANTS = Bukkit.getTag("blocks", NamespacedKey.minecraft("coral_plants"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 236 */   public static final Tag<Material> CORALS = Bukkit.getTag("blocks", NamespacedKey.minecraft("corals"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 240 */   public static final Tag<Material> BAMBOO_PLANTABLE_ON = Bukkit.getTag("blocks", NamespacedKey.minecraft("bamboo_plantable_on"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 244 */   public static final Tag<Material> STANDING_SIGNS = Bukkit.getTag("blocks", NamespacedKey.minecraft("standing_signs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 248 */   public static final Tag<Material> WALL_SIGNS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wall_signs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 252 */   public static final Tag<Material> SIGNS = Bukkit.getTag("blocks", NamespacedKey.minecraft("signs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 256 */   public static final Tag<Material> DRAGON_IMMUNE = Bukkit.getTag("blocks", NamespacedKey.minecraft("dragon_immune"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 260 */   public static final Tag<Material> WITHER_IMMUNE = Bukkit.getTag("blocks", NamespacedKey.minecraft("wither_immune"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 264 */   public static final Tag<Material> WITHER_SUMMON_BASE_BLOCKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wither_summon_base_blocks"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 268 */   public static final Tag<Material> BEEHIVES = Bukkit.getTag("blocks", NamespacedKey.minecraft("beehives"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 272 */   public static final Tag<Material> CROPS = Bukkit.getTag("blocks", NamespacedKey.minecraft("crops"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 276 */   public static final Tag<Material> BEE_GROWABLES = Bukkit.getTag("blocks", NamespacedKey.minecraft("bee_growables"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 280 */   public static final Tag<Material> PORTALS = Bukkit.getTag("blocks", NamespacedKey.minecraft("portals"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 284 */   public static final Tag<Material> FIRE = Bukkit.getTag("blocks", NamespacedKey.minecraft("fire"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 288 */   public static final Tag<Material> NYLIUM = Bukkit.getTag("blocks", NamespacedKey.minecraft("nylium"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 292 */   public static final Tag<Material> WART_BLOCKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("wart_blocks"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 296 */   public static final Tag<Material> BEACON_BASE_BLOCKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("beacon_base_blocks"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 300 */   public static final Tag<Material> SOUL_SPEED_BLOCKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("soul_speed_blocks"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 304 */   public static final Tag<Material> WALL_POST_OVERRIDE = Bukkit.getTag("blocks", NamespacedKey.minecraft("wall_post_override"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 308 */   public static final Tag<Material> CLIMBABLE = Bukkit.getTag("blocks", NamespacedKey.minecraft("climbable"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 312 */   public static final Tag<Material> SHULKER_BOXES = Bukkit.getTag("blocks", NamespacedKey.minecraft("shulker_boxes"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 316 */   public static final Tag<Material> HOGLIN_REPELLENTS = Bukkit.getTag("blocks", NamespacedKey.minecraft("hoglin_repellents"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 320 */   public static final Tag<Material> SOUL_FIRE_BASE_BLOCKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("soul_fire_base_blocks"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 324 */   public static final Tag<Material> STRIDER_WARM_BLOCKS = Bukkit.getTag("blocks", NamespacedKey.minecraft("strider_warm_blocks"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 328 */   public static final Tag<Material> CAMPFIRES = Bukkit.getTag("blocks", NamespacedKey.minecraft("campfires"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 332 */   public static final Tag<Material> GUARDED_BY_PIGLINS = Bukkit.getTag("blocks", NamespacedKey.minecraft("guarded_by_piglins"), Material.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public static final Tag<Material> PREVENT_MOB_SPAWNING_INSIDE = Bukkit.getTag("blocks", NamespacedKey.minecraft("prevent_mob_spawning_inside"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 341 */   public static final Tag<Material> FENCE_GATES = Bukkit.getTag("blocks", NamespacedKey.minecraft("fence_gates"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 345 */   public static final Tag<Material> UNSTABLE_BOTTOM_CENTER = Bukkit.getTag("blocks", NamespacedKey.minecraft("unstable_bottom_center"), Material.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public static final Tag<Material> INFINIBURN_OVERWORLD = Bukkit.getTag("blocks", NamespacedKey.minecraft("infiniburn_overworld"), Material.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public static final Tag<Material> INFINIBURN_NETHER = Bukkit.getTag("blocks", NamespacedKey.minecraft("infiniburn_nether"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 359 */   public static final Tag<Material> INFINIBURN_END = Bukkit.getTag("blocks", NamespacedKey.minecraft("infiniburn_end"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String REGISTRY_ITEMS = "items";
/*     */ 
/*     */ 
/*     */   
/* 367 */   public static final Tag<Material> ITEMS_PIGLIN_LOVED = Bukkit.getTag("items", NamespacedKey.minecraft("piglin_loved"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 371 */   public static final Tag<Material> ITEMS_BANNERS = Bukkit.getTag("items", NamespacedKey.minecraft("banners"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 375 */   public static final Tag<Material> ITEMS_BOATS = Bukkit.getTag("items", NamespacedKey.minecraft("boats"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 379 */   public static final Tag<Material> ITEMS_FISHES = Bukkit.getTag("items", NamespacedKey.minecraft("fishes"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 383 */   public static final Tag<Material> ITEMS_MUSIC_DISCS = Bukkit.getTag("items", NamespacedKey.minecraft("music_discs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 387 */   public static final Tag<Material> ITEMS_CREEPER_DROP_MUSIC_DISCS = Bukkit.getTag("items", NamespacedKey.minecraft("creeper_drop_music_discs"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 391 */   public static final Tag<Material> ITEMS_COALS = Bukkit.getTag("items", NamespacedKey.minecraft("coals"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 395 */   public static final Tag<Material> ITEMS_ARROWS = Bukkit.getTag("items", NamespacedKey.minecraft("arrows"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 399 */   public static final Tag<Material> ITEMS_LECTERN_BOOKS = Bukkit.getTag("items", NamespacedKey.minecraft("lectern_books"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 403 */   public static final Tag<Material> ITEMS_BEACON_PAYMENT_ITEMS = Bukkit.getTag("items", NamespacedKey.minecraft("beacon_payment_items"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 407 */   public static final Tag<Material> ITEMS_STONE_TOOL_MATERIALS = Bukkit.getTag("items", NamespacedKey.minecraft("stone_tool_materials"), Material.class);
/*     */ 
/*     */ 
/*     */   
/* 411 */   public static final Tag<Material> ITEMS_FURNACE_MATERIALS = Bukkit.getTag("items", NamespacedKey.minecraft("furnace_materials"), Material.class);
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String REGISTRY_FLUIDS = "fluids";
/*     */ 
/*     */ 
/*     */   
/* 419 */   public static final Tag<Fluid> FLUIDS_LAVA = Bukkit.getTag("fluids", NamespacedKey.minecraft("lava"), Fluid.class);
/*     */ 
/*     */ 
/*     */   
/* 423 */   public static final Tag<Fluid> FLUIDS_WATER = Bukkit.getTag("fluids", NamespacedKey.minecraft("water"), Fluid.class);
/*     */   
/*     */   boolean isTagged(@NotNull T paramT);
/*     */   
/*     */   @NotNull
/*     */   Set<T> getValues();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Tag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */