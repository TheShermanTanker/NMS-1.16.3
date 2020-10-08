/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.nio.file.Path;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class js
/*     */   extends jw<Block>
/*     */ {
/*     */   public js(DebugReportGenerator var0) {
/*  14 */     super(var0, IRegistry.BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b() {
/*  19 */     a(TagsBlock.WOOL).a(new Block[] { Blocks.WHITE_WOOL, Blocks.ORANGE_WOOL, Blocks.MAGENTA_WOOL, Blocks.LIGHT_BLUE_WOOL, Blocks.YELLOW_WOOL, Blocks.LIME_WOOL, Blocks.PINK_WOOL, Blocks.GRAY_WOOL, Blocks.LIGHT_GRAY_WOOL, Blocks.CYAN_WOOL, Blocks.PURPLE_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.GREEN_WOOL, Blocks.RED_WOOL, Blocks.BLACK_WOOL });
/*  20 */     a(TagsBlock.PLANKS).a(new Block[] { Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.CRIMSON_PLANKS, Blocks.WARPED_PLANKS });
/*  21 */     a(TagsBlock.STONE_BRICKS).a(new Block[] { Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS });
/*  22 */     a(TagsBlock.WOODEN_BUTTONS).a(new Block[] { Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.ACACIA_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.CRIMSON_BUTTON, Blocks.WARPED_BUTTON });
/*  23 */     a(TagsBlock.BUTTONS).a(TagsBlock.WOODEN_BUTTONS).a(Blocks.STONE_BUTTON).a(Blocks.POLISHED_BLACKSTONE_BUTTON);
/*  24 */     a(TagsBlock.CARPETS).a(new Block[] { Blocks.WHITE_CARPET, Blocks.ORANGE_CARPET, Blocks.MAGENTA_CARPET, Blocks.LIGHT_BLUE_CARPET, Blocks.YELLOW_CARPET, Blocks.LIME_CARPET, Blocks.PINK_CARPET, Blocks.GRAY_CARPET, Blocks.LIGHT_GRAY_CARPET, Blocks.CYAN_CARPET, Blocks.PURPLE_CARPET, Blocks.BLUE_CARPET, Blocks.BROWN_CARPET, Blocks.GREEN_CARPET, Blocks.RED_CARPET, Blocks.BLACK_CARPET });
/*  25 */     a(TagsBlock.WOODEN_DOORS).a(new Block[] { Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.BIRCH_DOOR, Blocks.JUNGLE_DOOR, Blocks.ACACIA_DOOR, Blocks.DARK_OAK_DOOR, Blocks.CRIMSON_DOOR, Blocks.WARPED_DOOR });
/*  26 */     a(TagsBlock.WOODEN_STAIRS).a(new Block[] { Blocks.OAK_STAIRS, Blocks.SPRUCE_STAIRS, Blocks.BIRCH_STAIRS, Blocks.JUNGLE_STAIRS, Blocks.ACACIA_STAIRS, Blocks.DARK_OAK_STAIRS, Blocks.CRIMSON_STAIRS, Blocks.WARPED_STAIRS });
/*  27 */     a(TagsBlock.WOODEN_SLABS).a(new Block[] { Blocks.OAK_SLAB, Blocks.SPRUCE_SLAB, Blocks.BIRCH_SLAB, Blocks.JUNGLE_SLAB, Blocks.ACACIA_SLAB, Blocks.DARK_OAK_SLAB, Blocks.CRIMSON_SLAB, Blocks.WARPED_SLAB });
/*  28 */     a(TagsBlock.WOODEN_FENCES).a(new Block[] { Blocks.OAK_FENCE, Blocks.ACACIA_FENCE, Blocks.DARK_OAK_FENCE, Blocks.SPRUCE_FENCE, Blocks.BIRCH_FENCE, Blocks.JUNGLE_FENCE, Blocks.CRIMSON_FENCE, Blocks.WARPED_FENCE });
/*  29 */     a(TagsBlock.DOORS).a(TagsBlock.WOODEN_DOORS).a(Blocks.IRON_DOOR);
/*  30 */     a(TagsBlock.SAPLINGS).a(new Block[] { Blocks.OAK_SAPLING, Blocks.SPRUCE_SAPLING, Blocks.BIRCH_SAPLING, Blocks.JUNGLE_SAPLING, Blocks.ACACIA_SAPLING, Blocks.DARK_OAK_SAPLING });
/*  31 */     a(TagsBlock.DARK_OAK_LOGS).a(new Block[] { Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD });
/*  32 */     a(TagsBlock.OAK_LOGS).a(new Block[] { Blocks.OAK_LOG, Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD });
/*  33 */     a(TagsBlock.ACACIA_LOGS).a(new Block[] { Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD });
/*  34 */     a(TagsBlock.BIRCH_LOGS).a(new Block[] { Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD });
/*  35 */     a(TagsBlock.JUNGLE_LOGS).a(new Block[] { Blocks.JUNGLE_LOG, Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD });
/*  36 */     a(TagsBlock.SPRUCE_LOGS).a(new Block[] { Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD });
/*  37 */     a(TagsBlock.CRIMSON_STEMS).a(new Block[] { Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE });
/*  38 */     a(TagsBlock.WARPED_STEMS).a(new Block[] { Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE });
/*  39 */     a(TagsBlock.LOGS_THAT_BURN).a(TagsBlock.DARK_OAK_LOGS).a(TagsBlock.OAK_LOGS).a(TagsBlock.ACACIA_LOGS).a(TagsBlock.BIRCH_LOGS).a(TagsBlock.JUNGLE_LOGS).a(TagsBlock.SPRUCE_LOGS);
/*  40 */     a(TagsBlock.LOGS).a(TagsBlock.LOGS_THAT_BURN).a(TagsBlock.CRIMSON_STEMS).a(TagsBlock.WARPED_STEMS);
/*  41 */     a(TagsBlock.ANVIL).a(new Block[] { Blocks.ANVIL, Blocks.CHIPPED_ANVIL, Blocks.DAMAGED_ANVIL });
/*  42 */     a(TagsBlock.SMALL_FLOWERS).a(new Block[] { Blocks.DANDELION, Blocks.POPPY, Blocks.BLUE_ORCHID, Blocks.ALLIUM, Blocks.AZURE_BLUET, Blocks.RED_TULIP, Blocks.ORANGE_TULIP, Blocks.WHITE_TULIP, Blocks.PINK_TULIP, Blocks.OXEYE_DAISY, Blocks.CORNFLOWER, Blocks.LILY_OF_THE_VALLEY, Blocks.WITHER_ROSE });
/*  43 */     a(TagsBlock.ENDERMAN_HOLDABLE).a(TagsBlock.SMALL_FLOWERS).a(new Block[] { Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.SAND, Blocks.RED_SAND, Blocks.GRAVEL, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, Blocks.TNT, Blocks.CACTUS, Blocks.CLAY, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.MELON, Blocks.MYCELIUM, Blocks.CRIMSON_FUNGUS, Blocks.CRIMSON_NYLIUM, Blocks.CRIMSON_ROOTS, Blocks.WARPED_FUNGUS, Blocks.WARPED_NYLIUM, Blocks.WARPED_ROOTS });
/*  44 */     a(TagsBlock.FLOWER_POTS).a(new Block[] { Blocks.FLOWER_POT, Blocks.POTTED_POPPY, Blocks.POTTED_BLUE_ORCHID, Blocks.POTTED_ALLIUM, Blocks.POTTED_AZURE_BLUET, Blocks.POTTED_RED_TULIP, Blocks.POTTED_ORANGE_TULIP, Blocks.POTTED_WHITE_TULIP, Blocks.POTTED_PINK_TULIP, Blocks.POTTED_OXEYE_DAISY, Blocks.POTTED_DANDELION, Blocks.POTTED_OAK_SAPLING, Blocks.POTTED_SPRUCE_SAPLING, Blocks.POTTED_BIRCH_SAPLING, Blocks.POTTED_JUNGLE_SAPLING, Blocks.POTTED_ACACIA_SAPLING, Blocks.POTTED_DARK_OAK_SAPLING, Blocks.POTTED_RED_MUSHROOM, Blocks.POTTED_BROWN_MUSHROOM, Blocks.POTTED_DEAD_BUSH, Blocks.POTTED_FERN, Blocks.POTTED_CACTUS, Blocks.POTTED_CORNFLOWER, Blocks.POTTED_LILY_OF_THE_VALLEY, Blocks.POTTED_WITHER_ROSE, Blocks.POTTED_BAMBOO, Blocks.POTTED_CRIMSON_FUNGUS, Blocks.POTTED_WARPED_FUNGUS, Blocks.POTTED_CRIMSON_ROOTS, Blocks.POTTED_WARPED_ROOTS });
/*  45 */     a(TagsBlock.BANNERS).a(new Block[] { Blocks.WHITE_BANNER, Blocks.ORANGE_BANNER, Blocks.MAGENTA_BANNER, Blocks.LIGHT_BLUE_BANNER, Blocks.YELLOW_BANNER, Blocks.LIME_BANNER, Blocks.PINK_BANNER, Blocks.GRAY_BANNER, Blocks.LIGHT_GRAY_BANNER, Blocks.CYAN_BANNER, Blocks.PURPLE_BANNER, Blocks.BLUE_BANNER, Blocks.BROWN_BANNER, Blocks.GREEN_BANNER, Blocks.RED_BANNER, Blocks.BLACK_BANNER, Blocks.WHITE_WALL_BANNER, Blocks.ORANGE_WALL_BANNER, Blocks.MAGENTA_WALL_BANNER, Blocks.LIGHT_BLUE_WALL_BANNER, Blocks.YELLOW_WALL_BANNER, Blocks.LIME_WALL_BANNER, Blocks.PINK_WALL_BANNER, Blocks.GRAY_WALL_BANNER, Blocks.LIGHT_GRAY_WALL_BANNER, Blocks.CYAN_WALL_BANNER, Blocks.PURPLE_WALL_BANNER, Blocks.BLUE_WALL_BANNER, Blocks.BROWN_WALL_BANNER, Blocks.GREEN_WALL_BANNER, Blocks.RED_WALL_BANNER, Blocks.BLACK_WALL_BANNER });
/*  46 */     a(TagsBlock.WOODEN_PRESSURE_PLATES).a(new Block[] { Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.WARPED_PRESSURE_PLATE });
/*  47 */     a(TagsBlock.STONE_PRESSURE_PLATES).a(new Block[] { Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE });
/*  48 */     a(TagsBlock.PRESSURE_PLATES).a(new Block[] { Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE }).a(TagsBlock.WOODEN_PRESSURE_PLATES).a(TagsBlock.STONE_PRESSURE_PLATES);
/*  49 */     a(TagsBlock.STAIRS).a(TagsBlock.WOODEN_STAIRS).a(new Block[] { Blocks.COBBLESTONE_STAIRS, Blocks.SANDSTONE_STAIRS, Blocks.NETHER_BRICK_STAIRS, Blocks.STONE_BRICK_STAIRS, Blocks.BRICK_STAIRS, Blocks.PURPUR_STAIRS, Blocks.QUARTZ_STAIRS, Blocks.RED_SANDSTONE_STAIRS, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_STAIRS, Blocks.DARK_PRISMARINE_STAIRS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.END_STONE_BRICK_STAIRS, Blocks.STONE_STAIRS, Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SMOOTH_QUARTZ_STAIRS, Blocks.GRANITE_STAIRS, Blocks.ANDESITE_STAIRS, Blocks.RED_NETHER_BRICK_STAIRS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.DIORITE_STAIRS, Blocks.BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_STAIRS });
/*  50 */     a(TagsBlock.SLABS).a(TagsBlock.WOODEN_SLABS).a(new Block[] { Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.BRICK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE_BRICK_SLAB, Blocks.DARK_PRISMARINE_SLAB, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.CUT_SANDSTONE_SLAB, Blocks.CUT_RED_SANDSTONE_SLAB, Blocks.BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB });
/*  51 */     a(TagsBlock.WALLS).a(new Block[] { Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL, Blocks.BRICK_WALL, Blocks.PRISMARINE_WALL, Blocks.RED_SANDSTONE_WALL, Blocks.MOSSY_STONE_BRICK_WALL, Blocks.GRANITE_WALL, Blocks.STONE_BRICK_WALL, Blocks.NETHER_BRICK_WALL, Blocks.ANDESITE_WALL, Blocks.RED_NETHER_BRICK_WALL, Blocks.SANDSTONE_WALL, Blocks.END_STONE_BRICK_WALL, Blocks.DIORITE_WALL, Blocks.BLACKSTONE_WALL, Blocks.POLISHED_BLACKSTONE_BRICK_WALL, Blocks.POLISHED_BLACKSTONE_WALL });
/*  52 */     a(TagsBlock.CORAL_PLANTS).a(new Block[] { Blocks.TUBE_CORAL, Blocks.BRAIN_CORAL, Blocks.BUBBLE_CORAL, Blocks.FIRE_CORAL, Blocks.HORN_CORAL });
/*  53 */     a(TagsBlock.CORALS).a(TagsBlock.CORAL_PLANTS).a(new Block[] { Blocks.TUBE_CORAL_FAN, Blocks.BRAIN_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN, Blocks.FIRE_CORAL_FAN, Blocks.HORN_CORAL_FAN });
/*  54 */     a(TagsBlock.WALL_CORALS).a(new Block[] { Blocks.TUBE_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN });
/*  55 */     a(TagsBlock.SAND).a(new Block[] { Blocks.SAND, Blocks.RED_SAND });
/*  56 */     a(TagsBlock.RAILS).a(new Block[] { Blocks.RAIL, Blocks.POWERED_RAIL, Blocks.DETECTOR_RAIL, Blocks.ACTIVATOR_RAIL });
/*  57 */     a(TagsBlock.CORAL_BLOCKS).a(new Block[] { Blocks.TUBE_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK });
/*  58 */     a(TagsBlock.ICE).a(new Block[] { Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.FROSTED_ICE });
/*  59 */     a(TagsBlock.VALID_SPAWN).a(new Block[] { Blocks.GRASS_BLOCK, Blocks.PODZOL });
/*  60 */     a(TagsBlock.LEAVES).a(new Block[] { Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES });
/*  61 */     a(TagsBlock.IMPERMEABLE).a(new Block[] { Blocks.GLASS, Blocks.WHITE_STAINED_GLASS, Blocks.ORANGE_STAINED_GLASS, Blocks.MAGENTA_STAINED_GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS, Blocks.YELLOW_STAINED_GLASS, Blocks.LIME_STAINED_GLASS, Blocks.PINK_STAINED_GLASS, Blocks.GRAY_STAINED_GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS, Blocks.CYAN_STAINED_GLASS, Blocks.PURPLE_STAINED_GLASS, Blocks.BLUE_STAINED_GLASS, Blocks.BROWN_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS, Blocks.RED_STAINED_GLASS, Blocks.BLACK_STAINED_GLASS });
/*  62 */     a(TagsBlock.WOODEN_TRAPDOORS).a(new Block[] { Blocks.ACACIA_TRAPDOOR, Blocks.BIRCH_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.JUNGLE_TRAPDOOR, Blocks.OAK_TRAPDOOR, Blocks.SPRUCE_TRAPDOOR, Blocks.CRIMSON_TRAPDOOR, Blocks.WARPED_TRAPDOOR });
/*  63 */     a(TagsBlock.TRAPDOORS).a(TagsBlock.WOODEN_TRAPDOORS).a(Blocks.IRON_TRAPDOOR);
/*  64 */     a(TagsBlock.UNDERWATER_BONEMEALS).a(Blocks.SEAGRASS).a(TagsBlock.CORALS).a(TagsBlock.WALL_CORALS);
/*  65 */     a(TagsBlock.BAMBOO_PLANTABLE_ON).a(TagsBlock.SAND).a(new Block[] { Blocks.BAMBOO, Blocks.BAMBOO_SAPLING, Blocks.GRAVEL, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.COARSE_DIRT, Blocks.MYCELIUM });
/*  66 */     a(TagsBlock.STANDING_SIGNS).a(new Block[] { Blocks.OAK_SIGN, Blocks.SPRUCE_SIGN, Blocks.BIRCH_SIGN, Blocks.ACACIA_SIGN, Blocks.JUNGLE_SIGN, Blocks.DARK_OAK_SIGN, Blocks.CRIMSON_SIGN, Blocks.WARPED_SIGN });
/*  67 */     a(TagsBlock.WALL_SIGNS).a(new Block[] { Blocks.OAK_WALL_SIGN, Blocks.SPRUCE_WALL_SIGN, Blocks.BIRCH_WALL_SIGN, Blocks.ACACIA_WALL_SIGN, Blocks.JUNGLE_WALL_SIGN, Blocks.DARK_OAK_WALL_SIGN, Blocks.CRIMSON_WALL_SIGN, Blocks.WARPED_WALL_SIGN });
/*  68 */     a(TagsBlock.SIGNS).a(TagsBlock.STANDING_SIGNS).a(TagsBlock.WALL_SIGNS);
/*  69 */     a(TagsBlock.BEDS).a(new Block[] { Blocks.RED_BED, Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, Blocks.PINK_BED, Blocks.PURPLE_BED, Blocks.WHITE_BED, Blocks.YELLOW_BED });
/*  70 */     a(TagsBlock.FENCES).a(TagsBlock.WOODEN_FENCES).a(Blocks.NETHER_BRICK_FENCE);
/*  71 */     a(TagsBlock.DRAGON_IMMUNE).a(new Block[] { Blocks.BARRIER, Blocks.BEDROCK, Blocks.END_PORTAL, Blocks.END_PORTAL_FRAME, Blocks.END_GATEWAY, Blocks.COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.STRUCTURE_BLOCK, Blocks.JIGSAW, Blocks.MOVING_PISTON, Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN, Blocks.END_STONE, Blocks.IRON_BARS, Blocks.RESPAWN_ANCHOR });
/*  72 */     a(TagsBlock.WITHER_IMMUNE).a(new Block[] { Blocks.BARRIER, Blocks.BEDROCK, Blocks.END_PORTAL, Blocks.END_PORTAL_FRAME, Blocks.END_GATEWAY, Blocks.COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.STRUCTURE_BLOCK, Blocks.JIGSAW, Blocks.MOVING_PISTON });
/*  73 */     a(TagsBlock.WITHER_SUMMON_BASE_BLOCKS).a(new Block[] { Blocks.SOUL_SAND, Blocks.SOUL_SOIL });
/*  74 */     a(TagsBlock.TALL_FLOWERS).a(new Block[] { Blocks.SUNFLOWER, Blocks.LILAC, Blocks.PEONY, Blocks.ROSE_BUSH });
/*  75 */     a(TagsBlock.FLOWERS).a(TagsBlock.SMALL_FLOWERS).a(TagsBlock.TALL_FLOWERS);
/*  76 */     a(TagsBlock.BEEHIVES).a(new Block[] { Blocks.BEE_NEST, Blocks.BEEHIVE });
/*  77 */     a(TagsBlock.CROPS).a(new Block[] { Blocks.BEETROOTS, Blocks.CARROTS, Blocks.POTATOES, Blocks.WHEAT, Blocks.MELON_STEM, Blocks.PUMPKIN_STEM });
/*  78 */     a(TagsBlock.BEE_GROWABLES).a(TagsBlock.CROPS).a(Blocks.SWEET_BERRY_BUSH);
/*  79 */     a(TagsBlock.SHULKER_BOXES).a(new Block[] { Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX });
/*  80 */     a(TagsBlock.PORTALS).a(new Block[] { Blocks.NETHER_PORTAL, Blocks.END_PORTAL, Blocks.END_GATEWAY });
/*  81 */     a(TagsBlock.FIRE).a(new Block[] { Blocks.FIRE, Blocks.SOUL_FIRE });
/*  82 */     a(TagsBlock.NYLIUM).a(new Block[] { Blocks.CRIMSON_NYLIUM, Blocks.WARPED_NYLIUM });
/*  83 */     a(TagsBlock.WART_BLOCKS).a(new Block[] { Blocks.NETHER_WART_BLOCK, Blocks.WARPED_WART_BLOCK });
/*  84 */     a(TagsBlock.BEACON_BASE_BLOCKS).a(new Block[] { Blocks.NETHERITE_BLOCK, Blocks.EMERALD_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK });
/*  85 */     a(TagsBlock.SOUL_SPEED_BLOCKS).a(new Block[] { Blocks.SOUL_SAND, Blocks.SOUL_SOIL });
/*  86 */     a(TagsBlock.WALL_POST_OVERRIDE).a(new Block[] { Blocks.TORCH, Blocks.SOUL_TORCH, Blocks.REDSTONE_TORCH, Blocks.TRIPWIRE }).a(TagsBlock.SIGNS).a(TagsBlock.BANNERS).a(TagsBlock.PRESSURE_PLATES);
/*  87 */     a(TagsBlock.CLIMBABLE).a(new Block[] { Blocks.LADDER, Blocks.VINE, Blocks.SCAFFOLDING, Blocks.WEEPING_VINES, Blocks.WEEPING_VINES_PLANT, Blocks.TWISTING_VINES, Blocks.TWISTING_VINES_PLANT });
/*  88 */     a(TagsBlock.PIGLIN_REPELLENTS).a(Blocks.SOUL_FIRE).a(Blocks.SOUL_TORCH).a(Blocks.SOUL_LANTERN).a(Blocks.SOUL_WALL_TORCH).a(Blocks.SOUL_CAMPFIRE);
/*  89 */     a(TagsBlock.HOGLIN_REPELLENTS).a(Blocks.WARPED_FUNGUS).a(Blocks.POTTED_WARPED_FUNGUS).a(Blocks.NETHER_PORTAL).a(Blocks.RESPAWN_ANCHOR);
/*  90 */     a(TagsBlock.GOLD_ORES).a(new Block[] { Blocks.GOLD_ORE, Blocks.NETHER_GOLD_ORE });
/*  91 */     a(TagsBlock.SOUL_FIRE_BASE_BLOCKS).a(new Block[] { Blocks.SOUL_SAND, Blocks.SOUL_SOIL });
/*  92 */     a(TagsBlock.NON_FLAMMABLE_WOOD).a(new Block[] { Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE, Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_PLANKS, Blocks.WARPED_PLANKS, Blocks.CRIMSON_SLAB, Blocks.WARPED_SLAB, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.WARPED_PRESSURE_PLATE, Blocks.CRIMSON_FENCE, Blocks.WARPED_FENCE, Blocks.CRIMSON_TRAPDOOR, Blocks.WARPED_TRAPDOOR, Blocks.CRIMSON_FENCE_GATE, Blocks.WARPED_FENCE_GATE, Blocks.CRIMSON_STAIRS, Blocks.WARPED_STAIRS, Blocks.CRIMSON_BUTTON, Blocks.WARPED_BUTTON, Blocks.CRIMSON_DOOR, Blocks.WARPED_DOOR, Blocks.CRIMSON_SIGN, Blocks.WARPED_SIGN, Blocks.CRIMSON_WALL_SIGN, Blocks.WARPED_WALL_SIGN });
/*  93 */     a(TagsBlock.STRIDER_WARM_BLOCKS).a(Blocks.LAVA);
/*  94 */     a(TagsBlock.CAMPFIRES).a(new Block[] { Blocks.CAMPFIRE, Blocks.SOUL_CAMPFIRE });
/*  95 */     a(TagsBlock.GUARDED_BY_PIGLINS).a(new Block[] { Blocks.GOLD_BLOCK, Blocks.BARREL, Blocks.CHEST, Blocks.ENDER_CHEST, Blocks.GILDED_BLACKSTONE, Blocks.TRAPPED_CHEST }).a(TagsBlock.SHULKER_BOXES).a(TagsBlock.GOLD_ORES);
/*  96 */     a(TagsBlock.PREVENT_MOB_SPAWNING_INSIDE).a(TagsBlock.RAILS);
/*  97 */     a(TagsBlock.aB).a(new Block[] { Blocks.ACACIA_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.CRIMSON_FENCE_GATE, Blocks.WARPED_FENCE_GATE });
/*  98 */     a(TagsBlock.aC).a(TagsBlock.aB);
/*  99 */     a(TagsBlock.aD).a(Blocks.MYCELIUM).a(Blocks.PODZOL).a(Blocks.CRIMSON_NYLIUM).a(Blocks.WARPED_NYLIUM);
/* 100 */     a(TagsBlock.aE).a(new Block[] { Blocks.NETHERRACK, Blocks.MAGMA_BLOCK });
/* 101 */     a(TagsBlock.aF).a(TagsBlock.aE);
/* 102 */     a(TagsBlock.aG).a(TagsBlock.aE).a(Blocks.BEDROCK);
/* 103 */     a(TagsBlock.aH).a(Blocks.STONE).a(Blocks.GRANITE).a(Blocks.DIORITE).a(Blocks.ANDESITE);
/* 104 */     a(TagsBlock.aI).a(Blocks.NETHERRACK).a(Blocks.BASALT).a(Blocks.BLACKSTONE);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Path a(MinecraftKey var0) {
/* 109 */     return this.b.b().resolve("data/" + var0.getNamespace() + "/tags/blocks/" + var0.getKey() + ".json");
/*     */   }
/*     */ 
/*     */   
/*     */   public String a() {
/* 114 */     return "Block Tags";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\js.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */