/*      */ package org.bukkit;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.function.Consumer;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.bukkit.block.data.Ageable;
/*      */ import org.bukkit.block.data.AnaloguePowerable;
/*      */ import org.bukkit.block.data.Bisected;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.block.data.Directional;
/*      */ import org.bukkit.block.data.Levelled;
/*      */ import org.bukkit.block.data.Lightable;
/*      */ import org.bukkit.block.data.MultipleFacing;
/*      */ import org.bukkit.block.data.Orientable;
/*      */ import org.bukkit.block.data.Powerable;
/*      */ import org.bukkit.block.data.Rail;
/*      */ import org.bukkit.block.data.Rotatable;
/*      */ import org.bukkit.block.data.Snowable;
/*      */ import org.bukkit.block.data.Waterlogged;
/*      */ import org.bukkit.block.data.type.Bed;
/*      */ import org.bukkit.block.data.type.Beehive;
/*      */ import org.bukkit.block.data.type.Campfire;
/*      */ import org.bukkit.block.data.type.Chain;
/*      */ import org.bukkit.block.data.type.Chest;
/*      */ import org.bukkit.block.data.type.CommandBlock;
/*      */ import org.bukkit.block.data.type.CoralWallFan;
/*      */ import org.bukkit.block.data.type.Dispenser;
/*      */ import org.bukkit.block.data.type.Door;
/*      */ import org.bukkit.block.data.type.EnderChest;
/*      */ import org.bukkit.block.data.type.Fence;
/*      */ import org.bukkit.block.data.type.Fire;
/*      */ import org.bukkit.block.data.type.Furnace;
/*      */ import org.bukkit.block.data.type.Gate;
/*      */ import org.bukkit.block.data.type.GlassPane;
/*      */ import org.bukkit.block.data.type.Hopper;
/*      */ import org.bukkit.block.data.type.Jukebox;
/*      */ import org.bukkit.block.data.type.Lantern;
/*      */ import org.bukkit.block.data.type.Leaves;
/*      */ import org.bukkit.block.data.type.Observer;
/*      */ import org.bukkit.block.data.type.Piston;
/*      */ import org.bukkit.block.data.type.RedstoneRail;
/*      */ import org.bukkit.block.data.type.Sapling;
/*      */ import org.bukkit.block.data.type.Scaffolding;
/*      */ import org.bukkit.block.data.type.Sign;
/*      */ import org.bukkit.block.data.type.Slab;
/*      */ import org.bukkit.block.data.type.Stairs;
/*      */ import org.bukkit.block.data.type.Switch;
/*      */ import org.bukkit.block.data.type.TrapDoor;
/*      */ import org.bukkit.block.data.type.TripwireHook;
/*      */ import org.bukkit.block.data.type.TurtleEgg;
/*      */ import org.bukkit.block.data.type.Wall;
/*      */ import org.bukkit.block.data.type.WallSign;
/*      */ import org.bukkit.material.Banner;
/*      */ import org.bukkit.material.Button;
/*      */ import org.bukkit.material.Cauldron;
/*      */ import org.bukkit.material.Chest;
/*      */ import org.bukkit.material.Command;
/*      */ import org.bukkit.material.Comparator;
/*      */ import org.bukkit.material.Crops;
/*      */ import org.bukkit.material.DetectorRail;
/*      */ import org.bukkit.material.Diode;
/*      */ import org.bukkit.material.Dispenser;
/*      */ import org.bukkit.material.Door;
/*      */ import org.bukkit.material.EnderChest;
/*      */ import org.bukkit.material.Furnace;
/*      */ import org.bukkit.material.Gate;
/*      */ import org.bukkit.material.Leaves;
/*      */ import org.bukkit.material.MaterialData;
/*      */ import org.bukkit.material.MonsterEggs;
/*      */ import org.bukkit.material.Mushroom;
/*      */ import org.bukkit.material.Observer;
/*      */ import org.bukkit.material.PistonBaseMaterial;
/*      */ import org.bukkit.material.PoweredRail;
/*      */ import org.bukkit.material.PressurePlate;
/*      */ import org.bukkit.material.Pumpkin;
/*      */ import org.bukkit.material.Rails;
/*      */ import org.bukkit.material.RedstoneTorch;
/*      */ import org.bukkit.material.Sign;
/*      */ import org.bukkit.material.Stairs;
/*      */ import org.bukkit.material.Step;
/*      */ import org.bukkit.material.Torch;
/*      */ import org.bukkit.material.TrapDoor;
/*      */ import org.bukkit.material.Tree;
/*      */ import org.bukkit.material.Vine;
/*      */ import org.bukkit.material.Wood;
/*      */ import org.bukkit.material.Wool;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ public enum Material implements Keyed {
/*   92 */   AIR(9648, 0),
/*   93 */   STONE(22948),
/*   94 */   GRANITE(21091),
/*   95 */   POLISHED_GRANITE(5477),
/*   96 */   DIORITE(24688),
/*   97 */   POLISHED_DIORITE(31615),
/*   98 */   ANDESITE(25975),
/*   99 */   POLISHED_ANDESITE(8335),
/*      */ 
/*      */ 
/*      */   
/*  103 */   GRASS_BLOCK(28346, Snowable.class),
/*  104 */   DIRT(10580),
/*  105 */   COARSE_DIRT(15411),
/*      */ 
/*      */ 
/*      */   
/*  109 */   PODZOL(24068, Snowable.class),
/*  110 */   CRIMSON_NYLIUM(18139),
/*  111 */   WARPED_NYLIUM(26396),
/*  112 */   COBBLESTONE(32147),
/*  113 */   OAK_PLANKS(14905),
/*  114 */   SPRUCE_PLANKS(14593),
/*  115 */   BIRCH_PLANKS(29322),
/*  116 */   JUNGLE_PLANKS(26445),
/*  117 */   ACACIA_PLANKS(31312),
/*  118 */   DARK_OAK_PLANKS(20869),
/*  119 */   CRIMSON_PLANKS(18812),
/*  120 */   WARPED_PLANKS(16045),
/*      */ 
/*      */ 
/*      */   
/*  124 */   OAK_SAPLING(9636, Sapling.class),
/*      */ 
/*      */ 
/*      */   
/*  128 */   SPRUCE_SAPLING(19874, Sapling.class),
/*      */ 
/*      */ 
/*      */   
/*  132 */   BIRCH_SAPLING(31533, Sapling.class),
/*      */ 
/*      */ 
/*      */   
/*  136 */   JUNGLE_SAPLING(17951, Sapling.class),
/*      */ 
/*      */ 
/*      */   
/*  140 */   ACACIA_SAPLING(20806, Sapling.class),
/*      */ 
/*      */ 
/*      */   
/*  144 */   DARK_OAK_SAPLING(14933, Sapling.class),
/*  145 */   BEDROCK(23130),
/*  146 */   SAND(11542),
/*  147 */   RED_SAND(16279),
/*  148 */   GRAVEL(7804),
/*  149 */   GOLD_ORE(32625),
/*  150 */   IRON_ORE(19834),
/*  151 */   COAL_ORE(30965),
/*  152 */   NETHER_GOLD_ORE(4185),
/*      */ 
/*      */ 
/*      */   
/*  156 */   OAK_LOG(26723, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  160 */   SPRUCE_LOG(9726, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  164 */   BIRCH_LOG(26727, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  168 */   JUNGLE_LOG(20721, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  172 */   ACACIA_LOG(8385, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  176 */   DARK_OAK_LOG(14831, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  180 */   CRIMSON_STEM(27920, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  184 */   WARPED_STEM(28920, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  188 */   STRIPPED_OAK_LOG(20523, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  192 */   STRIPPED_SPRUCE_LOG(6140, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  196 */   STRIPPED_BIRCH_LOG(8838, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  200 */   STRIPPED_JUNGLE_LOG(15476, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  204 */   STRIPPED_ACACIA_LOG(18167, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  208 */   STRIPPED_DARK_OAK_LOG(6492, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  212 */   STRIPPED_CRIMSON_STEM(16882, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  216 */   STRIPPED_WARPED_STEM(15627, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  220 */   STRIPPED_OAK_WOOD(31455, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  224 */   STRIPPED_SPRUCE_WOOD(6467, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  228 */   STRIPPED_BIRCH_WOOD(22350, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  232 */   STRIPPED_JUNGLE_WOOD(30315, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  236 */   STRIPPED_ACACIA_WOOD(27193, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  240 */   STRIPPED_DARK_OAK_WOOD(16000, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  244 */   STRIPPED_CRIMSON_HYPHAE(27488, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  248 */   STRIPPED_WARPED_HYPHAE(7422, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  252 */   OAK_WOOD(7378, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  256 */   SPRUCE_WOOD(32328, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  260 */   BIRCH_WOOD(20913, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  264 */   JUNGLE_WOOD(10341, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  268 */   ACACIA_WOOD(9541, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  272 */   DARK_OAK_WOOD(16995, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  276 */   CRIMSON_HYPHAE(6550, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  280 */   WARPED_HYPHAE(18439, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  284 */   OAK_LEAVES(4385, Leaves.class),
/*      */ 
/*      */ 
/*      */   
/*  288 */   SPRUCE_LEAVES(20039, Leaves.class),
/*      */ 
/*      */ 
/*      */   
/*  292 */   BIRCH_LEAVES(12601, Leaves.class),
/*      */ 
/*      */ 
/*      */   
/*  296 */   JUNGLE_LEAVES(5133, Leaves.class),
/*      */ 
/*      */ 
/*      */   
/*  300 */   ACACIA_LEAVES(16606, Leaves.class),
/*      */ 
/*      */ 
/*      */   
/*  304 */   DARK_OAK_LEAVES(22254, Leaves.class),
/*  305 */   SPONGE(15860),
/*  306 */   WET_SPONGE(9043),
/*  307 */   GLASS(6195),
/*  308 */   LAPIS_ORE(22934),
/*  309 */   LAPIS_BLOCK(14485),
/*      */ 
/*      */ 
/*      */   
/*  313 */   DISPENSER(20871, Dispenser.class),
/*  314 */   SANDSTONE(13141),
/*  315 */   CHISELED_SANDSTONE(31763),
/*  316 */   CUT_SANDSTONE(6118),
/*      */ 
/*      */ 
/*      */   
/*  320 */   NOTE_BLOCK(20979, NoteBlock.class),
/*      */ 
/*      */ 
/*      */   
/*  324 */   POWERED_RAIL(11064, RedstoneRail.class),
/*      */ 
/*      */ 
/*      */   
/*  328 */   DETECTOR_RAIL(13475, RedstoneRail.class),
/*      */ 
/*      */ 
/*      */   
/*  332 */   STICKY_PISTON(18127, Piston.class),
/*  333 */   COBWEB(9469),
/*  334 */   GRASS(6155),
/*  335 */   FERN(15794),
/*  336 */   DEAD_BUSH(22888),
/*  337 */   SEAGRASS(23942),
/*      */ 
/*      */ 
/*      */   
/*  341 */   SEA_PICKLE(19562, SeaPickle.class),
/*      */ 
/*      */ 
/*      */   
/*  345 */   PISTON(21130, Piston.class),
/*  346 */   WHITE_WOOL(8624),
/*  347 */   ORANGE_WOOL(23957),
/*  348 */   MAGENTA_WOOL(11853),
/*  349 */   LIGHT_BLUE_WOOL(21073),
/*  350 */   YELLOW_WOOL(29507),
/*  351 */   LIME_WOOL(10443),
/*  352 */   PINK_WOOL(7611),
/*  353 */   GRAY_WOOL(27209),
/*  354 */   LIGHT_GRAY_WOOL(22936),
/*  355 */   CYAN_WOOL(12221),
/*  356 */   PURPLE_WOOL(11922),
/*  357 */   BLUE_WOOL(15738),
/*  358 */   BROWN_WOOL(32638),
/*  359 */   GREEN_WOOL(25085),
/*  360 */   RED_WOOL(11621),
/*  361 */   BLACK_WOOL(16693),
/*  362 */   DANDELION(30558),
/*  363 */   POPPY(12851),
/*  364 */   BLUE_ORCHID(13432),
/*  365 */   ALLIUM(6871),
/*  366 */   AZURE_BLUET(17608),
/*  367 */   RED_TULIP(16781),
/*  368 */   ORANGE_TULIP(26038),
/*  369 */   WHITE_TULIP(31495),
/*  370 */   PINK_TULIP(27319),
/*  371 */   OXEYE_DAISY(11709),
/*  372 */   CORNFLOWER(15405),
/*  373 */   LILY_OF_THE_VALLEY(7185),
/*  374 */   WITHER_ROSE(8619),
/*  375 */   BROWN_MUSHROOM(9665),
/*  376 */   RED_MUSHROOM(19728),
/*  377 */   CRIMSON_FUNGUS(26268),
/*  378 */   WARPED_FUNGUS(19799),
/*  379 */   CRIMSON_ROOTS(14064),
/*  380 */   WARPED_ROOTS(13932),
/*  381 */   NETHER_SPROUTS(10431),
/*      */ 
/*      */ 
/*      */   
/*  385 */   WEEPING_VINES(29267, Ageable.class),
/*      */ 
/*      */ 
/*      */   
/*  389 */   TWISTING_VINES(27283, Ageable.class),
/*      */ 
/*      */ 
/*      */   
/*  393 */   SUGAR_CANE(7726, Ageable.class),
/*      */ 
/*      */ 
/*      */   
/*  397 */   KELP(21916, Ageable.class),
/*      */ 
/*      */ 
/*      */   
/*  401 */   BAMBOO(18728, Bamboo.class),
/*  402 */   GOLD_BLOCK(27392),
/*  403 */   IRON_BLOCK(24754),
/*      */ 
/*      */ 
/*      */   
/*  407 */   OAK_SLAB(12002, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  411 */   SPRUCE_SLAB(28798, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  415 */   BIRCH_SLAB(13807, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  419 */   JUNGLE_SLAB(19117, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  423 */   ACACIA_SLAB(23730, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  427 */   DARK_OAK_SLAB(28852, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  431 */   CRIMSON_SLAB(4691, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  435 */   WARPED_SLAB(27150, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  439 */   STONE_SLAB(19838, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  443 */   SMOOTH_STONE_SLAB(24129, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  447 */   SANDSTONE_SLAB(29830, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  451 */   CUT_SANDSTONE_SLAB(30944, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  455 */   PETRIFIED_OAK_SLAB(18658, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  459 */   COBBLESTONE_SLAB(6340, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  463 */   BRICK_SLAB(26333, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  467 */   STONE_BRICK_SLAB(19676, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  471 */   NETHER_BRICK_SLAB(26586, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  475 */   QUARTZ_SLAB(4423, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  479 */   RED_SANDSTONE_SLAB(17550, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  483 */   CUT_RED_SANDSTONE_SLAB(7220, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  487 */   PURPUR_SLAB(11487, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  491 */   PRISMARINE_SLAB(31323, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  495 */   PRISMARINE_BRICK_SLAB(25624, Slab.class),
/*      */ 
/*      */ 
/*      */   
/*  499 */   DARK_PRISMARINE_SLAB(7577, Slab.class),
/*  500 */   SMOOTH_QUARTZ(14415),
/*  501 */   SMOOTH_RED_SANDSTONE(25180),
/*  502 */   SMOOTH_SANDSTONE(30039),
/*  503 */   SMOOTH_STONE(21910),
/*  504 */   BRICKS(14165),
/*      */ 
/*      */ 
/*      */   
/*  508 */   TNT(7896, TNT.class),
/*  509 */   BOOKSHELF(10069),
/*      */ 
/*      */ 
/*      */   
/*  513 */   MOSSY_COBBLESTONE(21900, MultipleFacing.class),
/*  514 */   OBSIDIAN(32723),
/*  515 */   TORCH(6063),
/*      */ 
/*      */ 
/*      */   
/*  519 */   END_ROD(24832, Directional.class),
/*      */ 
/*      */ 
/*      */   
/*  523 */   CHORUS_PLANT(28243, MultipleFacing.class),
/*      */ 
/*      */ 
/*      */   
/*  527 */   CHORUS_FLOWER(28542, Ageable.class),
/*  528 */   PURPUR_BLOCK(7538),
/*      */ 
/*      */ 
/*      */   
/*  532 */   PURPUR_PILLAR(26718, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  536 */   PURPUR_STAIRS(8921, Stairs.class),
/*  537 */   SPAWNER(7018),
/*      */ 
/*      */ 
/*      */   
/*  541 */   OAK_STAIRS(5449, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  545 */   CHEST(22969, Chest.class),
/*  546 */   DIAMOND_ORE(9292),
/*  547 */   DIAMOND_BLOCK(5944),
/*  548 */   CRAFTING_TABLE(20706),
/*      */ 
/*      */ 
/*      */   
/*  552 */   FARMLAND(31166, Farmland.class),
/*      */ 
/*      */ 
/*      */   
/*  556 */   FURNACE(8133, Furnace.class),
/*      */ 
/*      */ 
/*      */   
/*  560 */   LADDER(23599, Ladder.class),
/*      */ 
/*      */ 
/*      */   
/*  564 */   RAIL(13285, Rail.class),
/*      */ 
/*      */ 
/*      */   
/*  568 */   COBBLESTONE_STAIRS(24715, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  572 */   LEVER(15319, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  576 */   STONE_PRESSURE_PLATE(22591, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  580 */   OAK_PRESSURE_PLATE(20108, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  584 */   SPRUCE_PRESSURE_PLATE(15932, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  588 */   BIRCH_PRESSURE_PLATE(9664, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  592 */   JUNGLE_PRESSURE_PLATE(11376, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  596 */   ACACIA_PRESSURE_PLATE(17586, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  600 */   DARK_OAK_PRESSURE_PLATE(31375, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  604 */   CRIMSON_PRESSURE_PLATE(18316, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  608 */   WARPED_PRESSURE_PLATE(29516, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  612 */   POLISHED_BLACKSTONE_PRESSURE_PLATE(32340, Powerable.class),
/*      */ 
/*      */ 
/*      */   
/*  616 */   REDSTONE_ORE(10887, Lightable.class),
/*      */ 
/*      */ 
/*      */   
/*  620 */   REDSTONE_TORCH(22547, Lightable.class),
/*      */ 
/*      */ 
/*      */   
/*  624 */   SNOW(14146, Snow.class),
/*  625 */   ICE(30428),
/*  626 */   SNOW_BLOCK(19913),
/*      */ 
/*      */ 
/*      */   
/*  630 */   CACTUS(12191, Ageable.class),
/*  631 */   CLAY(27880),
/*      */ 
/*      */ 
/*      */   
/*  635 */   JUKEBOX(19264, Jukebox.class),
/*      */ 
/*      */ 
/*      */   
/*  639 */   OAK_FENCE(6442, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  643 */   SPRUCE_FENCE(25416, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  647 */   BIRCH_FENCE(17347, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  651 */   JUNGLE_FENCE(14358, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  655 */   ACACIA_FENCE(4569, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  659 */   DARK_OAK_FENCE(21767, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  663 */   CRIMSON_FENCE(21075, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  667 */   WARPED_FENCE(18438, Fence.class),
/*  668 */   PUMPKIN(19170),
/*      */ 
/*      */ 
/*      */   
/*  672 */   CARVED_PUMPKIN(25833, Directional.class),
/*  673 */   NETHERRACK(23425),
/*  674 */   SOUL_SAND(16841),
/*  675 */   SOUL_SOIL(31140),
/*      */ 
/*      */ 
/*      */   
/*  679 */   BASALT(28478, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  683 */   POLISHED_BASALT(11659, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/*  687 */   SOUL_TORCH(14292, Lightable.class),
/*  688 */   GLOWSTONE(32713),
/*      */ 
/*      */ 
/*      */   
/*  692 */   JACK_O_LANTERN(13758, Directional.class),
/*      */ 
/*      */ 
/*      */   
/*  696 */   OAK_TRAPDOOR(16927, TrapDoor.class),
/*      */ 
/*      */ 
/*      */   
/*  700 */   SPRUCE_TRAPDOOR(10289, TrapDoor.class),
/*      */ 
/*      */ 
/*      */   
/*  704 */   BIRCH_TRAPDOOR(32585, TrapDoor.class),
/*      */ 
/*      */ 
/*      */   
/*  708 */   JUNGLE_TRAPDOOR(8626, TrapDoor.class),
/*      */ 
/*      */ 
/*      */   
/*  712 */   ACACIA_TRAPDOOR(18343, TrapDoor.class),
/*      */ 
/*      */ 
/*      */   
/*  716 */   DARK_OAK_TRAPDOOR(10355, TrapDoor.class),
/*      */ 
/*      */ 
/*      */   
/*  720 */   CRIMSON_TRAPDOOR(25056, TrapDoor.class),
/*      */ 
/*      */ 
/*      */   
/*  724 */   WARPED_TRAPDOOR(7708, TrapDoor.class),
/*  725 */   INFESTED_STONE(18440),
/*  726 */   INFESTED_COBBLESTONE(4348),
/*  727 */   INFESTED_STONE_BRICKS(19749),
/*  728 */   INFESTED_MOSSY_STONE_BRICKS(9850),
/*  729 */   INFESTED_CRACKED_STONE_BRICKS(7476),
/*  730 */   INFESTED_CHISELED_STONE_BRICKS(4728),
/*  731 */   STONE_BRICKS(6962),
/*  732 */   MOSSY_STONE_BRICKS(16415),
/*  733 */   CRACKED_STONE_BRICKS(27869),
/*  734 */   CHISELED_STONE_BRICKS(9087),
/*      */ 
/*      */ 
/*      */   
/*  738 */   BROWN_MUSHROOM_BLOCK(6291, MultipleFacing.class),
/*      */ 
/*      */ 
/*      */   
/*  742 */   RED_MUSHROOM_BLOCK(20766, MultipleFacing.class),
/*      */ 
/*      */ 
/*      */   
/*  746 */   MUSHROOM_STEM(16543, MultipleFacing.class),
/*      */ 
/*      */ 
/*      */   
/*  750 */   IRON_BARS(9378, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  754 */   CHAIN(28265, Chain.class),
/*      */ 
/*      */ 
/*      */   
/*  758 */   GLASS_PANE(5709, Fence.class),
/*  759 */   MELON(25172),
/*      */ 
/*      */ 
/*      */   
/*  763 */   VINE(14564, MultipleFacing.class),
/*      */ 
/*      */ 
/*      */   
/*  767 */   OAK_FENCE_GATE(16689, Gate.class),
/*      */ 
/*      */ 
/*      */   
/*  771 */   SPRUCE_FENCE_GATE(26423, Gate.class),
/*      */ 
/*      */ 
/*      */   
/*  775 */   BIRCH_FENCE_GATE(6322, Gate.class),
/*      */ 
/*      */ 
/*      */   
/*  779 */   JUNGLE_FENCE_GATE(21360, Gate.class),
/*      */ 
/*      */ 
/*      */   
/*  783 */   ACACIA_FENCE_GATE(14145, Gate.class),
/*      */ 
/*      */ 
/*      */   
/*  787 */   DARK_OAK_FENCE_GATE(10679, Gate.class),
/*      */ 
/*      */ 
/*      */   
/*  791 */   CRIMSON_FENCE_GATE(15602, Gate.class),
/*      */ 
/*      */ 
/*      */   
/*  795 */   WARPED_FENCE_GATE(11115, Gate.class),
/*      */ 
/*      */ 
/*      */   
/*  799 */   BRICK_STAIRS(21534, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  803 */   STONE_BRICK_STAIRS(27032, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  807 */   MYCELIUM(9913, Snowable.class),
/*  808 */   LILY_PAD(19271),
/*  809 */   NETHER_BRICKS(27802),
/*  810 */   CRACKED_NETHER_BRICKS(10888),
/*  811 */   CHISELED_NETHER_BRICKS(21613),
/*      */ 
/*      */ 
/*      */   
/*  815 */   NETHER_BRICK_FENCE(5286, Fence.class),
/*      */ 
/*      */ 
/*      */   
/*  819 */   NETHER_BRICK_STAIRS(12085, Stairs.class),
/*  820 */   ENCHANTING_TABLE(16255),
/*      */ 
/*      */ 
/*      */   
/*  824 */   END_PORTAL_FRAME(15480, EndPortalFrame.class),
/*  825 */   END_STONE(29686),
/*  826 */   END_STONE_BRICKS(20314),
/*  827 */   DRAGON_EGG(29946),
/*      */ 
/*      */ 
/*      */   
/*  831 */   REDSTONE_LAMP(8217, Lightable.class),
/*      */ 
/*      */ 
/*      */   
/*  835 */   SANDSTONE_STAIRS(18474, Stairs.class),
/*  836 */   EMERALD_ORE(16630),
/*      */ 
/*      */ 
/*      */   
/*  840 */   ENDER_CHEST(32349, EnderChest.class),
/*      */ 
/*      */ 
/*      */   
/*  844 */   TRIPWIRE_HOOK(8130, TripwireHook.class),
/*  845 */   EMERALD_BLOCK(9914),
/*      */ 
/*      */ 
/*      */   
/*  849 */   SPRUCE_STAIRS(11192, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  853 */   BIRCH_STAIRS(7657, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  857 */   JUNGLE_STAIRS(20636, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  861 */   CRIMSON_STAIRS(32442, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  865 */   WARPED_STAIRS(17721, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/*  869 */   COMMAND_BLOCK(4355, CommandBlock.class),
/*  870 */   BEACON(6608),
/*      */ 
/*      */ 
/*      */   
/*  874 */   COBBLESTONE_WALL(12616, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  878 */   MOSSY_COBBLESTONE_WALL(11536, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  882 */   BRICK_WALL(18995, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  886 */   PRISMARINE_WALL(18184, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  890 */   RED_SANDSTONE_WALL(4753, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  894 */   MOSSY_STONE_BRICK_WALL(18259, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  898 */   GRANITE_WALL(23279, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  902 */   STONE_BRICK_WALL(29073, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  906 */   NETHER_BRICK_WALL(10398, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  910 */   ANDESITE_WALL(14938, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  914 */   RED_NETHER_BRICK_WALL(4580, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  918 */   SANDSTONE_WALL(18470, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  922 */   END_STONE_BRICK_WALL(27225, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  926 */   DIORITE_WALL(17412, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  930 */   BLACKSTONE_WALL(17327, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  934 */   POLISHED_BLACKSTONE_WALL(15119, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  938 */   POLISHED_BLACKSTONE_BRICK_WALL(9540, Wall.class),
/*      */ 
/*      */ 
/*      */   
/*  942 */   STONE_BUTTON(12279, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  946 */   OAK_BUTTON(13510, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  950 */   SPRUCE_BUTTON(23281, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  954 */   BIRCH_BUTTON(26934, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  958 */   JUNGLE_BUTTON(25317, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  962 */   ACACIA_BUTTON(13993, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  966 */   DARK_OAK_BUTTON(6214, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  970 */   CRIMSON_BUTTON(26799, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  974 */   WARPED_BUTTON(25264, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  978 */   POLISHED_BLACKSTONE_BUTTON(20760, Switch.class),
/*      */ 
/*      */ 
/*      */   
/*  982 */   ANVIL(18718, Directional.class),
/*      */ 
/*      */ 
/*      */   
/*  986 */   CHIPPED_ANVIL(10623, Directional.class),
/*      */ 
/*      */ 
/*      */   
/*  990 */   DAMAGED_ANVIL(10274, Directional.class),
/*      */ 
/*      */ 
/*      */   
/*  994 */   TRAPPED_CHEST(18970, Chest.class),
/*      */ 
/*      */ 
/*      */   
/*  998 */   LIGHT_WEIGHTED_PRESSURE_PLATE(14875, AnaloguePowerable.class),
/*      */ 
/*      */ 
/*      */   
/* 1002 */   HEAVY_WEIGHTED_PRESSURE_PLATE(16970, AnaloguePowerable.class),
/*      */ 
/*      */ 
/*      */   
/* 1006 */   DAYLIGHT_DETECTOR(8864, DaylightDetector.class),
/* 1007 */   REDSTONE_BLOCK(19496),
/* 1008 */   NETHER_QUARTZ_ORE(4807),
/*      */ 
/*      */ 
/*      */   
/* 1012 */   HOPPER(31974, Hopper.class),
/* 1013 */   CHISELED_QUARTZ_BLOCK(30964),
/* 1014 */   QUARTZ_BLOCK(11987),
/* 1015 */   QUARTZ_BRICKS(23358),
/*      */ 
/*      */ 
/*      */   
/* 1019 */   QUARTZ_PILLAR(16452, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/* 1023 */   QUARTZ_STAIRS(24079, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1027 */   ACTIVATOR_RAIL(5834, RedstoneRail.class),
/*      */ 
/*      */ 
/*      */   
/* 1031 */   DROPPER(31273, Dispenser.class),
/* 1032 */   WHITE_TERRACOTTA(20975),
/* 1033 */   ORANGE_TERRACOTTA(18684),
/* 1034 */   MAGENTA_TERRACOTTA(25900),
/* 1035 */   LIGHT_BLUE_TERRACOTTA(31779),
/* 1036 */   YELLOW_TERRACOTTA(32129),
/* 1037 */   LIME_TERRACOTTA(24013),
/* 1038 */   PINK_TERRACOTTA(23727),
/* 1039 */   GRAY_TERRACOTTA(18004),
/* 1040 */   LIGHT_GRAY_TERRACOTTA(26388),
/* 1041 */   CYAN_TERRACOTTA(25940),
/* 1042 */   PURPLE_TERRACOTTA(10387),
/* 1043 */   BLUE_TERRACOTTA(5236),
/* 1044 */   BROWN_TERRACOTTA(23664),
/* 1045 */   GREEN_TERRACOTTA(4105),
/* 1046 */   RED_TERRACOTTA(5086),
/* 1047 */   BLACK_TERRACOTTA(26691),
/* 1048 */   BARRIER(26453),
/*      */ 
/*      */ 
/*      */   
/* 1052 */   IRON_TRAPDOOR(17095, TrapDoor.class),
/*      */ 
/*      */ 
/*      */   
/* 1056 */   HAY_BLOCK(17461, Orientable.class),
/* 1057 */   WHITE_CARPET(15117),
/* 1058 */   ORANGE_CARPET(24752),
/* 1059 */   MAGENTA_CARPET(6180),
/* 1060 */   LIGHT_BLUE_CARPET(21194),
/* 1061 */   YELLOW_CARPET(18149),
/* 1062 */   LIME_CARPET(15443),
/* 1063 */   PINK_CARPET(27381),
/* 1064 */   GRAY_CARPET(26991),
/* 1065 */   LIGHT_GRAY_CARPET(11317),
/* 1066 */   CYAN_CARPET(9742),
/* 1067 */   PURPLE_CARPET(5574),
/* 1068 */   BLUE_CARPET(13292),
/* 1069 */   BROWN_CARPET(23352),
/* 1070 */   GREEN_CARPET(7780),
/* 1071 */   RED_CARPET(5424),
/* 1072 */   BLACK_CARPET(6056),
/* 1073 */   TERRACOTTA(16544),
/* 1074 */   COAL_BLOCK(27968),
/* 1075 */   PACKED_ICE(28993),
/*      */ 
/*      */ 
/*      */   
/* 1079 */   ACACIA_STAIRS(17453, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1083 */   DARK_OAK_STAIRS(22921, Stairs.class),
/* 1084 */   SLIME_BLOCK(31892),
/* 1085 */   GRASS_PATH(8604),
/*      */ 
/*      */ 
/*      */   
/* 1089 */   SUNFLOWER(7408, Bisected.class),
/*      */ 
/*      */ 
/*      */   
/* 1093 */   LILAC(22837, Bisected.class),
/*      */ 
/*      */ 
/*      */   
/* 1097 */   ROSE_BUSH(6080, Bisected.class),
/*      */ 
/*      */ 
/*      */   
/* 1101 */   PEONY(21155, Bisected.class),
/*      */ 
/*      */ 
/*      */   
/* 1105 */   TALL_GRASS(21559, Bisected.class),
/*      */ 
/*      */ 
/*      */   
/* 1109 */   LARGE_FERN(30177, Bisected.class),
/* 1110 */   WHITE_STAINED_GLASS(31190),
/* 1111 */   ORANGE_STAINED_GLASS(25142),
/* 1112 */   MAGENTA_STAINED_GLASS(26814),
/* 1113 */   LIGHT_BLUE_STAINED_GLASS(17162),
/* 1114 */   YELLOW_STAINED_GLASS(12182),
/* 1115 */   LIME_STAINED_GLASS(24266),
/* 1116 */   PINK_STAINED_GLASS(16164),
/* 1117 */   GRAY_STAINED_GLASS(29979),
/* 1118 */   LIGHT_GRAY_STAINED_GLASS(5843),
/* 1119 */   CYAN_STAINED_GLASS(30604),
/* 1120 */   PURPLE_STAINED_GLASS(21845),
/* 1121 */   BLUE_STAINED_GLASS(7107),
/* 1122 */   BROWN_STAINED_GLASS(20945),
/* 1123 */   GREEN_STAINED_GLASS(22503),
/* 1124 */   RED_STAINED_GLASS(9717),
/* 1125 */   BLACK_STAINED_GLASS(13941),
/*      */ 
/*      */ 
/*      */   
/* 1129 */   WHITE_STAINED_GLASS_PANE(10557, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1133 */   ORANGE_STAINED_GLASS_PANE(21089, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1137 */   MAGENTA_STAINED_GLASS_PANE(14082, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1141 */   LIGHT_BLUE_STAINED_GLASS_PANE(18721, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1145 */   YELLOW_STAINED_GLASS_PANE(20298, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1149 */   LIME_STAINED_GLASS_PANE(10610, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1153 */   PINK_STAINED_GLASS_PANE(24637, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1157 */   GRAY_STAINED_GLASS_PANE(25272, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1161 */   LIGHT_GRAY_STAINED_GLASS_PANE(19008, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1165 */   CYAN_STAINED_GLASS_PANE(11784, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1169 */   PURPLE_STAINED_GLASS_PANE(10948, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1173 */   BLUE_STAINED_GLASS_PANE(28484, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1177 */   BROWN_STAINED_GLASS_PANE(17557, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1181 */   GREEN_STAINED_GLASS_PANE(4767, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1185 */   RED_STAINED_GLASS_PANE(8630, GlassPane.class),
/*      */ 
/*      */ 
/*      */   
/* 1189 */   BLACK_STAINED_GLASS_PANE(13201, GlassPane.class),
/* 1190 */   PRISMARINE(7539),
/* 1191 */   PRISMARINE_BRICKS(29118),
/* 1192 */   DARK_PRISMARINE(19940),
/*      */ 
/*      */ 
/*      */   
/* 1196 */   PRISMARINE_STAIRS(19217, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1200 */   PRISMARINE_BRICK_STAIRS(15445, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1204 */   DARK_PRISMARINE_STAIRS(26511, Stairs.class),
/* 1205 */   SEA_LANTERN(20780),
/* 1206 */   RED_SANDSTONE(9092),
/* 1207 */   CHISELED_RED_SANDSTONE(15529),
/* 1208 */   CUT_RED_SANDSTONE(26842),
/*      */ 
/*      */ 
/*      */   
/* 1212 */   RED_SANDSTONE_STAIRS(25466, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1216 */   REPEATING_COMMAND_BLOCK(12405, CommandBlock.class),
/*      */ 
/*      */ 
/*      */   
/* 1220 */   CHAIN_COMMAND_BLOCK(26798, CommandBlock.class),
/* 1221 */   MAGMA_BLOCK(25927),
/* 1222 */   NETHER_WART_BLOCK(15486),
/* 1223 */   WARPED_WART_BLOCK(15463),
/* 1224 */   RED_NETHER_BRICKS(18056),
/*      */ 
/*      */ 
/*      */   
/* 1228 */   BONE_BLOCK(17312, Orientable.class),
/* 1229 */   STRUCTURE_VOID(30806),
/*      */ 
/*      */ 
/*      */   
/* 1233 */   OBSERVER(10726, Observer.class),
/*      */ 
/*      */ 
/*      */   
/* 1237 */   SHULKER_BOX(7776, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1241 */   WHITE_SHULKER_BOX(31750, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1245 */   ORANGE_SHULKER_BOX(21673, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1249 */   MAGENTA_SHULKER_BOX(21566, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1253 */   LIGHT_BLUE_SHULKER_BOX(18226, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1257 */   YELLOW_SHULKER_BOX(28700, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1261 */   LIME_SHULKER_BOX(28360, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1265 */   PINK_SHULKER_BOX(24968, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1269 */   GRAY_SHULKER_BOX(12754, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1273 */   LIGHT_GRAY_SHULKER_BOX(21345, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1277 */   CYAN_SHULKER_BOX(28123, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1281 */   PURPLE_SHULKER_BOX(10373, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1285 */   BLUE_SHULKER_BOX(11476, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1289 */   BROWN_SHULKER_BOX(24230, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1293 */   GREEN_SHULKER_BOX(9377, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1297 */   RED_SHULKER_BOX(32448, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1301 */   BLACK_SHULKER_BOX(24076, 1, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1305 */   WHITE_GLAZED_TERRACOTTA(11326, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1309 */   ORANGE_GLAZED_TERRACOTTA(27451, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1313 */   MAGENTA_GLAZED_TERRACOTTA(8067, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1317 */   LIGHT_BLUE_GLAZED_TERRACOTTA(4336, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1321 */   YELLOW_GLAZED_TERRACOTTA(10914, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1325 */   LIME_GLAZED_TERRACOTTA(13861, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1329 */   PINK_GLAZED_TERRACOTTA(10260, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1333 */   GRAY_GLAZED_TERRACOTTA(6256, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1337 */   LIGHT_GRAY_GLAZED_TERRACOTTA(10707, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1341 */   CYAN_GLAZED_TERRACOTTA(9550, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1345 */   PURPLE_GLAZED_TERRACOTTA(4818, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1349 */   BLUE_GLAZED_TERRACOTTA(23823, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1353 */   BROWN_GLAZED_TERRACOTTA(5655, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1357 */   GREEN_GLAZED_TERRACOTTA(6958, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1361 */   RED_GLAZED_TERRACOTTA(24989, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 1365 */   BLACK_GLAZED_TERRACOTTA(29678, Directional.class),
/* 1366 */   WHITE_CONCRETE(6281),
/* 1367 */   ORANGE_CONCRETE(19914),
/* 1368 */   MAGENTA_CONCRETE(20591),
/* 1369 */   LIGHT_BLUE_CONCRETE(29481),
/* 1370 */   YELLOW_CONCRETE(15722),
/* 1371 */   LIME_CONCRETE(5863),
/* 1372 */   PINK_CONCRETE(5227),
/* 1373 */   GRAY_CONCRETE(13959),
/* 1374 */   LIGHT_GRAY_CONCRETE(14453),
/* 1375 */   CYAN_CONCRETE(26522),
/* 1376 */   PURPLE_CONCRETE(20623),
/* 1377 */   BLUE_CONCRETE(18756),
/* 1378 */   BROWN_CONCRETE(19006),
/* 1379 */   GREEN_CONCRETE(17949),
/* 1380 */   RED_CONCRETE(8032),
/* 1381 */   BLACK_CONCRETE(13338),
/* 1382 */   WHITE_CONCRETE_POWDER(10363),
/* 1383 */   ORANGE_CONCRETE_POWDER(30159),
/* 1384 */   MAGENTA_CONCRETE_POWDER(8272),
/* 1385 */   LIGHT_BLUE_CONCRETE_POWDER(31206),
/* 1386 */   YELLOW_CONCRETE_POWDER(10655),
/* 1387 */   LIME_CONCRETE_POWDER(28859),
/* 1388 */   PINK_CONCRETE_POWDER(6421),
/* 1389 */   GRAY_CONCRETE_POWDER(13031),
/* 1390 */   LIGHT_GRAY_CONCRETE_POWDER(21589),
/* 1391 */   CYAN_CONCRETE_POWDER(15734),
/* 1392 */   PURPLE_CONCRETE_POWDER(26808),
/* 1393 */   BLUE_CONCRETE_POWDER(17773),
/* 1394 */   BROWN_CONCRETE_POWDER(21485),
/* 1395 */   GREEN_CONCRETE_POWDER(6904),
/* 1396 */   RED_CONCRETE_POWDER(13286),
/* 1397 */   BLACK_CONCRETE_POWDER(16150),
/*      */ 
/*      */ 
/*      */   
/* 1401 */   TURTLE_EGG(32101, TurtleEgg.class),
/* 1402 */   DEAD_TUBE_CORAL_BLOCK(28350),
/* 1403 */   DEAD_BRAIN_CORAL_BLOCK(12979),
/* 1404 */   DEAD_BUBBLE_CORAL_BLOCK(28220),
/* 1405 */   DEAD_FIRE_CORAL_BLOCK(5307),
/* 1406 */   DEAD_HORN_CORAL_BLOCK(15103),
/* 1407 */   TUBE_CORAL_BLOCK(23723),
/* 1408 */   BRAIN_CORAL_BLOCK(30618),
/* 1409 */   BUBBLE_CORAL_BLOCK(15437),
/* 1410 */   FIRE_CORAL_BLOCK(12119),
/* 1411 */   HORN_CORAL_BLOCK(19958),
/*      */ 
/*      */ 
/*      */   
/* 1415 */   TUBE_CORAL(23048, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1419 */   BRAIN_CORAL(31316, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1423 */   BUBBLE_CORAL(12464, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1427 */   FIRE_CORAL(29151, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1431 */   HORN_CORAL(19511, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1435 */   DEAD_BRAIN_CORAL(9116, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1439 */   DEAD_BUBBLE_CORAL(30583, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1443 */   DEAD_FIRE_CORAL(8365, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1447 */   DEAD_HORN_CORAL(5755, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1451 */   DEAD_TUBE_CORAL(18028, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1455 */   TUBE_CORAL_FAN(19929, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1459 */   BRAIN_CORAL_FAN(13849, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1463 */   BUBBLE_CORAL_FAN(10795, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1467 */   FIRE_CORAL_FAN(11112, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1471 */   HORN_CORAL_FAN(13610, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1475 */   DEAD_TUBE_CORAL_FAN(17628, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1479 */   DEAD_BRAIN_CORAL_FAN(26150, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1483 */   DEAD_BUBBLE_CORAL_FAN(17322, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1487 */   DEAD_FIRE_CORAL_FAN(27073, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1491 */   DEAD_HORN_CORAL_FAN(11387, Waterlogged.class),
/* 1492 */   BLUE_ICE(22449),
/*      */ 
/*      */ 
/*      */   
/* 1496 */   CONDUIT(5148, Waterlogged.class),
/*      */ 
/*      */ 
/*      */   
/* 1500 */   POLISHED_GRANITE_STAIRS(29588, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1504 */   SMOOTH_RED_SANDSTONE_STAIRS(17561, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1508 */   MOSSY_STONE_BRICK_STAIRS(27578, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1512 */   POLISHED_DIORITE_STAIRS(4625, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1516 */   MOSSY_COBBLESTONE_STAIRS(29210, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1520 */   END_STONE_BRICK_STAIRS(28831, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1524 */   STONE_STAIRS(23784, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1528 */   SMOOTH_SANDSTONE_STAIRS(21183, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1532 */   SMOOTH_QUARTZ_STAIRS(19560, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1536 */   GRANITE_STAIRS(21840, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1540 */   ANDESITE_STAIRS(17747, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1544 */   RED_NETHER_BRICK_STAIRS(26374, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1548 */   POLISHED_ANDESITE_STAIRS(7573, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1552 */   DIORITE_STAIRS(13134, Stairs.class),
/*      */ 
/*      */ 
/*      */   
/* 1556 */   POLISHED_GRANITE_SLAB(4521, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1560 */   SMOOTH_RED_SANDSTONE_SLAB(16304, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1564 */   MOSSY_STONE_BRICK_SLAB(14002, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1568 */   POLISHED_DIORITE_SLAB(18303, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1572 */   MOSSY_COBBLESTONE_SLAB(12139, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1576 */   END_STONE_BRICK_SLAB(23239, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1580 */   SMOOTH_SANDSTONE_SLAB(9030, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1584 */   SMOOTH_QUARTZ_SLAB(26543, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1588 */   GRANITE_SLAB(10901, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1592 */   ANDESITE_SLAB(32124, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1596 */   RED_NETHER_BRICK_SLAB(12462, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1600 */   POLISHED_ANDESITE_SLAB(24573, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1604 */   DIORITE_SLAB(10715, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 1608 */   SCAFFOLDING(15757, Scaffolding.class),
/*      */ 
/*      */ 
/*      */   
/* 1612 */   IRON_DOOR(4788, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1616 */   OAK_DOOR(20341, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1620 */   SPRUCE_DOOR(10642, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1624 */   BIRCH_DOOR(14759, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1628 */   JUNGLE_DOOR(28163, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1632 */   ACACIA_DOOR(23797, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1636 */   DARK_OAK_DOOR(10669, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1640 */   CRIMSON_DOOR(19544, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1644 */   WARPED_DOOR(15062, Door.class),
/*      */ 
/*      */ 
/*      */   
/* 1648 */   REPEATER(28823, Repeater.class),
/*      */ 
/*      */ 
/*      */   
/* 1652 */   COMPARATOR(18911, Comparator.class),
/*      */ 
/*      */ 
/*      */   
/* 1656 */   STRUCTURE_BLOCK(26831, StructureBlock.class),
/*      */ 
/*      */ 
/*      */   
/* 1660 */   JIGSAW(17398, Jigsaw.class),
/* 1661 */   TURTLE_HELMET(30120, 1, 275),
/* 1662 */   SCUTE(11914),
/* 1663 */   FLINT_AND_STEEL(28620, 1, 64),
/* 1664 */   APPLE(7720),
/* 1665 */   BOW(8745, 1, 384),
/* 1666 */   ARROW(31091),
/* 1667 */   COAL(29067),
/* 1668 */   CHARCOAL(5390),
/* 1669 */   DIAMOND(20865),
/* 1670 */   IRON_INGOT(24895),
/* 1671 */   GOLD_INGOT(28927),
/* 1672 */   NETHERITE_INGOT(32457),
/* 1673 */   NETHERITE_SCRAP(29331),
/* 1674 */   WOODEN_SWORD(7175, 1, 59),
/* 1675 */   WOODEN_SHOVEL(28432, 1, 59),
/* 1676 */   WOODEN_PICKAXE(12792, 1, 59),
/* 1677 */   WOODEN_AXE(6292, 1, 59),
/* 1678 */   WOODEN_HOE(16043, 1, 59),
/* 1679 */   STONE_SWORD(25084, 1, 131),
/* 1680 */   STONE_SHOVEL(9520, 1, 131),
/* 1681 */   STONE_PICKAXE(14611, 1, 131),
/* 1682 */   STONE_AXE(6338, 1, 131),
/* 1683 */   STONE_HOE(22855, 1, 131),
/* 1684 */   GOLDEN_SWORD(10505, 1, 32),
/* 1685 */   GOLDEN_SHOVEL(15597, 1, 32),
/* 1686 */   GOLDEN_PICKAXE(25898, 1, 32),
/* 1687 */   GOLDEN_AXE(4878, 1, 32),
/* 1688 */   GOLDEN_HOE(19337, 1, 32),
/* 1689 */   IRON_SWORD(10904, 1, 250),
/* 1690 */   IRON_SHOVEL(30045, 1, 250),
/* 1691 */   IRON_PICKAXE(8842, 1, 250),
/* 1692 */   IRON_AXE(15894, 1, 250),
/* 1693 */   IRON_HOE(11339, 1, 250),
/* 1694 */   DIAMOND_SWORD(27707, 1, 1561),
/* 1695 */   DIAMOND_SHOVEL(25415, 1, 1561),
/* 1696 */   DIAMOND_PICKAXE(24291, 1, 1561),
/* 1697 */   DIAMOND_AXE(27277, 1, 1561),
/* 1698 */   DIAMOND_HOE(24050, 1, 1561),
/* 1699 */   NETHERITE_SWORD(23871, 1, 2031),
/* 1700 */   NETHERITE_SHOVEL(29728, 1, 2031),
/* 1701 */   NETHERITE_PICKAXE(9930, 1, 2031),
/* 1702 */   NETHERITE_AXE(29533, 1, 2031),
/* 1703 */   NETHERITE_HOE(27385, 1, 2031),
/* 1704 */   STICK(9773),
/* 1705 */   BOWL(32661),
/* 1706 */   MUSHROOM_STEW(16336, 1),
/* 1707 */   STRING(12806),
/* 1708 */   FEATHER(30548),
/* 1709 */   GUNPOWDER(29974),
/* 1710 */   WHEAT_SEEDS(28742),
/*      */ 
/*      */ 
/*      */   
/* 1714 */   WHEAT(27709, Ageable.class),
/* 1715 */   BREAD(32049),
/* 1716 */   LEATHER_HELMET(11624, 1, 55),
/* 1717 */   LEATHER_CHESTPLATE(29275, 1, 80),
/* 1718 */   LEATHER_LEGGINGS(28210, 1, 75),
/* 1719 */   LEATHER_BOOTS(15282, 1, 65),
/* 1720 */   CHAINMAIL_HELMET(26114, 1, 165),
/* 1721 */   CHAINMAIL_CHESTPLATE(23602, 1, 240),
/* 1722 */   CHAINMAIL_LEGGINGS(19087, 1, 225),
/* 1723 */   CHAINMAIL_BOOTS(17953, 1, 195),
/* 1724 */   IRON_HELMET(12025, 1, 165),
/* 1725 */   IRON_CHESTPLATE(28112, 1, 240),
/* 1726 */   IRON_LEGGINGS(18951, 1, 225),
/* 1727 */   IRON_BOOTS(8531, 1, 195),
/* 1728 */   DIAMOND_HELMET(10755, 1, 363),
/* 1729 */   DIAMOND_CHESTPLATE(32099, 1, 528),
/* 1730 */   DIAMOND_LEGGINGS(11202, 1, 495),
/* 1731 */   DIAMOND_BOOTS(16522, 1, 429),
/* 1732 */   GOLDEN_HELMET(7945, 1, 77),
/* 1733 */   GOLDEN_CHESTPLATE(4507, 1, 112),
/* 1734 */   GOLDEN_LEGGINGS(21002, 1, 105),
/* 1735 */   GOLDEN_BOOTS(7859, 1, 91),
/* 1736 */   NETHERITE_HELMET(15907, 1, 407),
/* 1737 */   NETHERITE_CHESTPLATE(6106, 1, 592),
/* 1738 */   NETHERITE_LEGGINGS(25605, 1, 555),
/* 1739 */   NETHERITE_BOOTS(8923, 1, 481),
/* 1740 */   FLINT(23596),
/* 1741 */   PORKCHOP(30896),
/* 1742 */   COOKED_PORKCHOP(27231),
/* 1743 */   PAINTING(23945),
/* 1744 */   GOLDEN_APPLE(27732),
/* 1745 */   ENCHANTED_GOLDEN_APPLE(8280),
/*      */ 
/*      */ 
/*      */   
/* 1749 */   OAK_SIGN(8192, 16, Sign.class),
/*      */ 
/*      */ 
/*      */   
/* 1753 */   SPRUCE_SIGN(21502, 16, Sign.class),
/*      */ 
/*      */ 
/*      */   
/* 1757 */   BIRCH_SIGN(11351, 16, Sign.class),
/*      */ 
/*      */ 
/*      */   
/* 1761 */   JUNGLE_SIGN(24717, 16, Sign.class),
/*      */ 
/*      */ 
/*      */   
/* 1765 */   ACACIA_SIGN(29808, 16, Sign.class),
/*      */ 
/*      */ 
/*      */   
/* 1769 */   DARK_OAK_SIGN(15127, 16, Sign.class),
/*      */ 
/*      */ 
/*      */   
/* 1773 */   CRIMSON_SIGN(12162, 16, Sign.class),
/*      */ 
/*      */ 
/*      */   
/* 1777 */   WARPED_SIGN(10407, 16, Sign.class),
/* 1778 */   BUCKET(15215, 16),
/* 1779 */   WATER_BUCKET(8802, 1),
/* 1780 */   LAVA_BUCKET(9228, 1),
/* 1781 */   MINECART(14352, 1),
/* 1782 */   SADDLE(30206, 1),
/* 1783 */   REDSTONE(11233),
/* 1784 */   SNOWBALL(19487, 16),
/* 1785 */   OAK_BOAT(17570, 1),
/* 1786 */   LEATHER(16414),
/* 1787 */   MILK_BUCKET(9680, 1),
/* 1788 */   PUFFERFISH_BUCKET(8861, 1),
/* 1789 */   SALMON_BUCKET(31427, 1),
/* 1790 */   COD_BUCKET(28601, 1),
/* 1791 */   TROPICAL_FISH_BUCKET(29995, 1),
/* 1792 */   BRICK(6820),
/* 1793 */   CLAY_BALL(24603),
/* 1794 */   DRIED_KELP_BLOCK(12966),
/* 1795 */   PAPER(9923),
/* 1796 */   BOOK(23097),
/* 1797 */   SLIME_BALL(5242),
/* 1798 */   CHEST_MINECART(4497, 1),
/* 1799 */   FURNACE_MINECART(14196, 1),
/* 1800 */   EGG(21603, 16),
/* 1801 */   COMPASS(24139),
/* 1802 */   FISHING_ROD(4167, 1, 64),
/* 1803 */   CLOCK(14980),
/* 1804 */   GLOWSTONE_DUST(6665),
/* 1805 */   COD(24691),
/* 1806 */   SALMON(18516),
/* 1807 */   TROPICAL_FISH(24879),
/* 1808 */   PUFFERFISH(8115),
/* 1809 */   COOKED_COD(9681),
/* 1810 */   COOKED_SALMON(5615),
/* 1811 */   INK_SAC(7184),
/* 1812 */   COCOA_BEANS(30186),
/* 1813 */   LAPIS_LAZULI(11075),
/* 1814 */   WHITE_DYE(10758),
/* 1815 */   ORANGE_DYE(13866),
/* 1816 */   MAGENTA_DYE(11788),
/* 1817 */   LIGHT_BLUE_DYE(28738),
/* 1818 */   YELLOW_DYE(5952),
/* 1819 */   LIME_DYE(6147),
/* 1820 */   PINK_DYE(31151),
/* 1821 */   GRAY_DYE(9184),
/* 1822 */   LIGHT_GRAY_DYE(27643),
/* 1823 */   CYAN_DYE(8043),
/* 1824 */   PURPLE_DYE(6347),
/* 1825 */   BLUE_DYE(11588),
/* 1826 */   BROWN_DYE(7648),
/* 1827 */   GREEN_DYE(23215),
/* 1828 */   RED_DYE(5728),
/* 1829 */   BLACK_DYE(6202),
/* 1830 */   BONE_MEAL(32458),
/* 1831 */   BONE(5686),
/* 1832 */   SUGAR(30638),
/*      */ 
/*      */ 
/*      */   
/* 1836 */   CAKE(27048, 1, Cake.class),
/*      */ 
/*      */ 
/*      */   
/* 1840 */   WHITE_BED(8185, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1844 */   ORANGE_BED(11194, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1848 */   MAGENTA_BED(20061, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1852 */   LIGHT_BLUE_BED(20957, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1856 */   YELLOW_BED(30410, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1860 */   LIME_BED(27860, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1864 */   PINK_BED(13795, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1868 */   GRAY_BED(15745, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1872 */   LIGHT_GRAY_BED(5090, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1876 */   CYAN_BED(16746, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1880 */   PURPLE_BED(29755, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1884 */   BLUE_BED(12714, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1888 */   BROWN_BED(26672, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1892 */   GREEN_BED(13797, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1896 */   RED_BED(30910, 1, Bed.class),
/*      */ 
/*      */ 
/*      */   
/* 1900 */   BLACK_BED(20490, 1, Bed.class),
/* 1901 */   COOKIE(27431),
/* 1902 */   FILLED_MAP(23504),
/* 1903 */   SHEARS(27971, 1, 238),
/* 1904 */   MELON_SLICE(5347),
/* 1905 */   DRIED_KELP(21042),
/* 1906 */   PUMPKIN_SEEDS(28985),
/* 1907 */   MELON_SEEDS(18340),
/* 1908 */   BEEF(4803),
/* 1909 */   COOKED_BEEF(21595),
/* 1910 */   CHICKEN(17281),
/* 1911 */   COOKED_CHICKEN(16984),
/* 1912 */   ROTTEN_FLESH(21591),
/* 1913 */   ENDER_PEARL(5259, 16),
/* 1914 */   BLAZE_ROD(8289),
/* 1915 */   GHAST_TEAR(18222),
/* 1916 */   GOLD_NUGGET(28814),
/*      */ 
/*      */ 
/*      */   
/* 1920 */   NETHER_WART(29227, Ageable.class),
/* 1921 */   POTION(24020, 1),
/* 1922 */   GLASS_BOTTLE(6116),
/* 1923 */   SPIDER_EYE(9318),
/* 1924 */   FERMENTED_SPIDER_EYE(19386),
/* 1925 */   BLAZE_POWDER(18941),
/* 1926 */   MAGMA_CREAM(25097),
/*      */ 
/*      */ 
/*      */   
/* 1930 */   BREWING_STAND(14539, BrewingStand.class),
/*      */ 
/*      */ 
/*      */   
/* 1934 */   CAULDRON(26531, Levelled.class),
/* 1935 */   ENDER_EYE(24860),
/* 1936 */   GLISTERING_MELON_SLICE(20158),
/* 1937 */   BAT_SPAWN_EGG(14607),
/* 1938 */   BEE_SPAWN_EGG(22924),
/* 1939 */   BLAZE_SPAWN_EGG(4759),
/* 1940 */   CAT_SPAWN_EGG(29583),
/* 1941 */   CAVE_SPIDER_SPAWN_EGG(23341),
/* 1942 */   CHICKEN_SPAWN_EGG(5462),
/* 1943 */   COD_SPAWN_EGG(27248),
/* 1944 */   COW_SPAWN_EGG(14761),
/* 1945 */   CREEPER_SPAWN_EGG(9653),
/* 1946 */   DOLPHIN_SPAWN_EGG(20787),
/* 1947 */   DONKEY_SPAWN_EGG(14513),
/* 1948 */   DROWNED_SPAWN_EGG(19368),
/* 1949 */   ELDER_GUARDIAN_SPAWN_EGG(11418),
/* 1950 */   ENDERMAN_SPAWN_EGG(29488),
/* 1951 */   ENDERMITE_SPAWN_EGG(16617),
/* 1952 */   EVOKER_SPAWN_EGG(21271),
/* 1953 */   FOX_SPAWN_EGG(22376),
/* 1954 */   GHAST_SPAWN_EGG(9970),
/* 1955 */   GUARDIAN_SPAWN_EGG(20113),
/* 1956 */   HOGLIN_SPAWN_EGG(14088),
/* 1957 */   HORSE_SPAWN_EGG(25981),
/* 1958 */   HUSK_SPAWN_EGG(20178),
/* 1959 */   LLAMA_SPAWN_EGG(23640),
/* 1960 */   MAGMA_CUBE_SPAWN_EGG(26638),
/* 1961 */   MOOSHROOM_SPAWN_EGG(22125),
/* 1962 */   MULE_SPAWN_EGG(11229),
/* 1963 */   OCELOT_SPAWN_EGG(30080),
/* 1964 */   PANDA_SPAWN_EGG(23759),
/* 1965 */   PARROT_SPAWN_EGG(23614),
/* 1966 */   PHANTOM_SPAWN_EGG(24648),
/* 1967 */   PIG_SPAWN_EGG(22584),
/* 1968 */   PIGLIN_SPAWN_EGG(16193),
/* 1969 */   PIGLIN_BRUTE_SPAWN_EGG(30230),
/* 1970 */   PILLAGER_SPAWN_EGG(28659),
/* 1971 */   POLAR_BEAR_SPAWN_EGG(17015),
/* 1972 */   PUFFERFISH_SPAWN_EGG(24570),
/* 1973 */   RABBIT_SPAWN_EGG(26496),
/* 1974 */   RAVAGER_SPAWN_EGG(8726),
/* 1975 */   SALMON_SPAWN_EGG(18739),
/* 1976 */   SHEEP_SPAWN_EGG(24488),
/* 1977 */   SHULKER_SPAWN_EGG(31848),
/* 1978 */   SILVERFISH_SPAWN_EGG(14537),
/* 1979 */   SKELETON_SPAWN_EGG(15261),
/* 1980 */   SKELETON_HORSE_SPAWN_EGG(21356),
/* 1981 */   SLIME_SPAWN_EGG(17196),
/* 1982 */   SPIDER_SPAWN_EGG(14984),
/* 1983 */   SQUID_SPAWN_EGG(10682),
/* 1984 */   STRAY_SPAWN_EGG(30153),
/* 1985 */   STRIDER_SPAWN_EGG(6203),
/* 1986 */   TRADER_LLAMA_SPAWN_EGG(8439),
/* 1987 */   TROPICAL_FISH_SPAWN_EGG(19713),
/* 1988 */   TURTLE_SPAWN_EGG(17324),
/* 1989 */   VEX_SPAWN_EGG(27751),
/* 1990 */   VILLAGER_SPAWN_EGG(30348),
/* 1991 */   VINDICATOR_SPAWN_EGG(25324),
/* 1992 */   WANDERING_TRADER_SPAWN_EGG(17904),
/* 1993 */   WITCH_SPAWN_EGG(11837),
/* 1994 */   WITHER_SKELETON_SPAWN_EGG(10073),
/* 1995 */   WOLF_SPAWN_EGG(21692),
/* 1996 */   ZOGLIN_SPAWN_EGG(7442),
/* 1997 */   ZOMBIE_SPAWN_EGG(5814),
/* 1998 */   ZOMBIE_HORSE_SPAWN_EGG(4275),
/* 1999 */   ZOMBIE_VILLAGER_SPAWN_EGG(10311),
/* 2000 */   ZOMBIFIED_PIGLIN_SPAWN_EGG(6626),
/* 2001 */   EXPERIENCE_BOTTLE(12858),
/* 2002 */   FIRE_CHARGE(4842),
/* 2003 */   WRITABLE_BOOK(13393, 1),
/* 2004 */   WRITTEN_BOOK(24164, 16),
/* 2005 */   EMERALD(5654),
/* 2006 */   ITEM_FRAME(27318),
/* 2007 */   FLOWER_POT(30567),
/* 2008 */   CARROT(22824),
/* 2009 */   POTATO(21088),
/* 2010 */   BAKED_POTATO(14624),
/* 2011 */   POISONOUS_POTATO(32640),
/* 2012 */   MAP(21655),
/* 2013 */   GOLDEN_CARROT(5300),
/*      */ 
/*      */ 
/*      */   
/* 2017 */   SKELETON_SKULL(13270, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2021 */   WITHER_SKELETON_SKULL(31487, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2025 */   PLAYER_HEAD(21174, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2029 */   ZOMBIE_HEAD(9304, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2033 */   CREEPER_HEAD(29146, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2037 */   DRAGON_HEAD(20084, Rotatable.class),
/* 2038 */   CARROT_ON_A_STICK(27809, 1, 25),
/* 2039 */   WARPED_FUNGUS_ON_A_STICK(11706, 1, 100),
/* 2040 */   NETHER_STAR(12469),
/* 2041 */   PUMPKIN_PIE(28725),
/* 2042 */   FIREWORK_ROCKET(23841),
/* 2043 */   FIREWORK_STAR(12190),
/* 2044 */   ENCHANTED_BOOK(11741, 1),
/* 2045 */   NETHER_BRICK(19996),
/* 2046 */   QUARTZ(23608),
/* 2047 */   TNT_MINECART(4277, 1),
/* 2048 */   HOPPER_MINECART(19024, 1),
/* 2049 */   PRISMARINE_SHARD(10993),
/* 2050 */   PRISMARINE_CRYSTALS(31546),
/* 2051 */   RABBIT(23068),
/* 2052 */   COOKED_RABBIT(4454),
/* 2053 */   RABBIT_STEW(10611, 1),
/* 2054 */   RABBIT_FOOT(13864),
/* 2055 */   RABBIT_HIDE(12467),
/* 2056 */   ARMOR_STAND(12852, 16),
/* 2057 */   IRON_HORSE_ARMOR(30108, 1),
/* 2058 */   GOLDEN_HORSE_ARMOR(7996, 1),
/* 2059 */   DIAMOND_HORSE_ARMOR(10321, 1),
/* 2060 */   LEATHER_HORSE_ARMOR(30667, 1),
/* 2061 */   LEAD(29539),
/* 2062 */   NAME_TAG(30731),
/* 2063 */   COMMAND_BLOCK_MINECART(7992, 1),
/* 2064 */   MUTTON(4792),
/* 2065 */   COOKED_MUTTON(31447),
/*      */ 
/*      */ 
/*      */   
/* 2069 */   WHITE_BANNER(17562, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2073 */   ORANGE_BANNER(4839, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2077 */   MAGENTA_BANNER(15591, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2081 */   LIGHT_BLUE_BANNER(18060, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2085 */   YELLOW_BANNER(30382, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2089 */   LIME_BANNER(18887, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2093 */   PINK_BANNER(19439, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2097 */   GRAY_BANNER(12053, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2101 */   LIGHT_GRAY_BANNER(11417, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2105 */   CYAN_BANNER(9839, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2109 */   PURPLE_BANNER(29027, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2113 */   BLUE_BANNER(18481, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2117 */   BROWN_BANNER(11481, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2121 */   GREEN_BANNER(10698, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2125 */   RED_BANNER(26961, 16, Rotatable.class),
/*      */ 
/*      */ 
/*      */   
/* 2129 */   BLACK_BANNER(9365, 16, Rotatable.class),
/* 2130 */   END_CRYSTAL(19090),
/* 2131 */   CHORUS_FRUIT(7652),
/* 2132 */   POPPED_CHORUS_FRUIT(27844),
/* 2133 */   BEETROOT(23305),
/* 2134 */   BEETROOT_SEEDS(21282),
/* 2135 */   BEETROOT_SOUP(16036, 1),
/* 2136 */   DRAGON_BREATH(20154),
/* 2137 */   SPLASH_POTION(30248, 1),
/* 2138 */   SPECTRAL_ARROW(4568),
/* 2139 */   TIPPED_ARROW(25164),
/* 2140 */   LINGERING_POTION(25857, 1),
/* 2141 */   SHIELD(29943, 1, 336),
/* 2142 */   ELYTRA(23829, 1, 432),
/* 2143 */   SPRUCE_BOAT(9606, 1),
/* 2144 */   BIRCH_BOAT(28104, 1),
/* 2145 */   JUNGLE_BOAT(4495, 1),
/* 2146 */   ACACIA_BOAT(27326, 1),
/* 2147 */   DARK_OAK_BOAT(28618, 1),
/* 2148 */   TOTEM_OF_UNDYING(10139, 1),
/* 2149 */   SHULKER_SHELL(27848),
/* 2150 */   IRON_NUGGET(13715),
/* 2151 */   KNOWLEDGE_BOOK(12646, 1),
/* 2152 */   DEBUG_STICK(24562, 1),
/* 2153 */   MUSIC_DISC_13(16359, 1),
/* 2154 */   MUSIC_DISC_CAT(16246, 1),
/* 2155 */   MUSIC_DISC_BLOCKS(26667, 1),
/* 2156 */   MUSIC_DISC_CHIRP(19436, 1),
/* 2157 */   MUSIC_DISC_FAR(13823, 1),
/* 2158 */   MUSIC_DISC_MALL(11517, 1),
/* 2159 */   MUSIC_DISC_MELLOHI(26117, 1),
/* 2160 */   MUSIC_DISC_STAL(14989, 1),
/* 2161 */   MUSIC_DISC_STRAD(16785, 1),
/* 2162 */   MUSIC_DISC_WARD(24026, 1),
/* 2163 */   MUSIC_DISC_11(27426, 1),
/* 2164 */   MUSIC_DISC_WAIT(26499, 1),
/* 2165 */   MUSIC_DISC_PIGSTEP(21323, 1),
/* 2166 */   TRIDENT(7534, 1, 250),
/* 2167 */   PHANTOM_MEMBRANE(18398),
/* 2168 */   NAUTILUS_SHELL(19989),
/* 2169 */   HEART_OF_THE_SEA(11807),
/* 2170 */   CROSSBOW(4340, 1, 326),
/* 2171 */   SUSPICIOUS_STEW(8173, 1),
/*      */ 
/*      */ 
/*      */   
/* 2175 */   LOOM(14276, Directional.class),
/* 2176 */   FLOWER_BANNER_PATTERN(5762, 1),
/* 2177 */   CREEPER_BANNER_PATTERN(15774, 1),
/* 2178 */   SKULL_BANNER_PATTERN(7680, 1),
/* 2179 */   MOJANG_BANNER_PATTERN(11903, 1),
/* 2180 */   GLOBE_BANNER_PATTERN(27753, 1),
/* 2181 */   PIGLIN_BANNER_PATTERN(22028, 1),
/*      */ 
/*      */ 
/*      */   
/* 2185 */   COMPOSTER(31247, Levelled.class),
/*      */ 
/*      */ 
/*      */   
/* 2189 */   BARREL(22396, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2193 */   SMOKER(24781, Furnace.class),
/*      */ 
/*      */ 
/*      */   
/* 2197 */   BLAST_FURNACE(31157, Furnace.class),
/* 2198 */   CARTOGRAPHY_TABLE(28529),
/* 2199 */   FLETCHING_TABLE(30838),
/*      */ 
/*      */ 
/*      */   
/* 2203 */   GRINDSTONE(26260, Grindstone.class),
/*      */ 
/*      */ 
/*      */   
/* 2207 */   LECTERN(23490, Lectern.class),
/* 2208 */   SMITHING_TABLE(9082),
/*      */ 
/*      */ 
/*      */   
/* 2212 */   STONECUTTER(25170, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2216 */   BELL(20000, Bell.class),
/*      */ 
/*      */ 
/*      */   
/* 2220 */   LANTERN(5992, Lantern.class),
/*      */ 
/*      */ 
/*      */   
/* 2224 */   SOUL_LANTERN(27778, Lantern.class),
/* 2225 */   SWEET_BERRIES(19747),
/*      */ 
/*      */ 
/*      */   
/* 2229 */   CAMPFIRE(8488, Campfire.class),
/*      */ 
/*      */ 
/*      */   
/* 2233 */   SOUL_CAMPFIRE(4238, Campfire.class),
/* 2234 */   SHROOMLIGHT(20424),
/* 2235 */   HONEYCOMB(9482),
/*      */ 
/*      */ 
/*      */   
/* 2239 */   BEE_NEST(8825, Beehive.class),
/*      */ 
/*      */ 
/*      */   
/* 2243 */   BEEHIVE(11830, Beehive.class),
/* 2244 */   HONEY_BOTTLE(22927, 16),
/* 2245 */   HONEY_BLOCK(30615),
/* 2246 */   HONEYCOMB_BLOCK(28780),
/* 2247 */   LODESTONE(23127),
/* 2248 */   NETHERITE_BLOCK(6527),
/* 2249 */   ANCIENT_DEBRIS(18198),
/*      */ 
/*      */ 
/*      */   
/* 2253 */   TARGET(22637, AnaloguePowerable.class),
/* 2254 */   CRYING_OBSIDIAN(31545),
/* 2255 */   BLACKSTONE(7354),
/*      */ 
/*      */ 
/*      */   
/* 2259 */   BLACKSTONE_SLAB(11948, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 2263 */   BLACKSTONE_STAIRS(14646, Stairs.class),
/* 2264 */   GILDED_BLACKSTONE(8498),
/* 2265 */   POLISHED_BLACKSTONE(18144),
/*      */ 
/*      */ 
/*      */   
/* 2269 */   POLISHED_BLACKSTONE_SLAB(23430, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 2273 */   POLISHED_BLACKSTONE_STAIRS(8653, Stairs.class),
/* 2274 */   CHISELED_POLISHED_BLACKSTONE(21942),
/* 2275 */   POLISHED_BLACKSTONE_BRICKS(19844),
/*      */ 
/*      */ 
/*      */   
/* 2279 */   POLISHED_BLACKSTONE_BRICK_SLAB(12219, Slab.class),
/*      */ 
/*      */ 
/*      */   
/* 2283 */   POLISHED_BLACKSTONE_BRICK_STAIRS(17983, Stairs.class),
/* 2284 */   CRACKED_POLISHED_BLACKSTONE_BRICKS(16846),
/*      */ 
/*      */ 
/*      */   
/* 2288 */   RESPAWN_ANCHOR(4099, RespawnAnchor.class),
/*      */ 
/*      */ 
/*      */   
/* 2292 */   WATER(24998, Levelled.class),
/*      */ 
/*      */ 
/*      */   
/* 2296 */   LAVA(8415, Levelled.class),
/*      */ 
/*      */ 
/*      */   
/* 2300 */   TALL_SEAGRASS(27189, Bisected.class),
/*      */ 
/*      */ 
/*      */   
/* 2304 */   PISTON_HEAD(30226, PistonHead.class),
/*      */ 
/*      */ 
/*      */   
/* 2308 */   MOVING_PISTON(13831, TechnicalPiston.class),
/*      */ 
/*      */ 
/*      */   
/* 2312 */   WALL_TORCH(25890, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2316 */   FIRE(16396, Fire.class),
/*      */ 
/*      */ 
/*      */   
/* 2320 */   SOUL_FIRE(30163, Fire.class),
/*      */ 
/*      */ 
/*      */   
/* 2324 */   REDSTONE_WIRE(25984, RedstoneWire.class),
/*      */ 
/*      */ 
/*      */   
/* 2328 */   OAK_WALL_SIGN(12984, 16, WallSign.class),
/*      */ 
/*      */ 
/*      */   
/* 2332 */   SPRUCE_WALL_SIGN(7352, 16, WallSign.class),
/*      */ 
/*      */ 
/*      */   
/* 2336 */   BIRCH_WALL_SIGN(9887, 16, WallSign.class),
/*      */ 
/*      */ 
/*      */   
/* 2340 */   ACACIA_WALL_SIGN(20316, 16, WallSign.class),
/*      */ 
/*      */ 
/*      */   
/* 2344 */   JUNGLE_WALL_SIGN(29629, 16, WallSign.class),
/*      */ 
/*      */ 
/*      */   
/* 2348 */   DARK_OAK_WALL_SIGN(9508, 16, WallSign.class),
/*      */ 
/*      */ 
/*      */   
/* 2352 */   REDSTONE_WALL_TORCH(7595, RedstoneWallTorch.class),
/*      */ 
/*      */ 
/*      */   
/* 2356 */   SOUL_WALL_TORCH(27500, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2360 */   NETHER_PORTAL(19469, Orientable.class),
/*      */ 
/*      */ 
/*      */   
/* 2364 */   ATTACHED_PUMPKIN_STEM(12724, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2368 */   ATTACHED_MELON_STEM(30882, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2372 */   PUMPKIN_STEM(19021, Ageable.class),
/*      */ 
/*      */ 
/*      */   
/* 2376 */   MELON_STEM(8247, Ageable.class),
/* 2377 */   END_PORTAL(16782),
/*      */ 
/*      */ 
/*      */   
/* 2381 */   COCOA(29709, Cocoa.class),
/*      */ 
/*      */ 
/*      */   
/* 2385 */   TRIPWIRE(8810, Tripwire.class),
/* 2386 */   POTTED_OAK_SAPLING(11905),
/* 2387 */   POTTED_SPRUCE_SAPLING(29498),
/* 2388 */   POTTED_BIRCH_SAPLING(32484),
/* 2389 */   POTTED_JUNGLE_SAPLING(7525),
/* 2390 */   POTTED_ACACIA_SAPLING(14096),
/* 2391 */   POTTED_DARK_OAK_SAPLING(6486),
/* 2392 */   POTTED_FERN(23315),
/* 2393 */   POTTED_DANDELION(9727),
/* 2394 */   POTTED_POPPY(7457),
/* 2395 */   POTTED_BLUE_ORCHID(6599),
/* 2396 */   POTTED_ALLIUM(13184),
/* 2397 */   POTTED_AZURE_BLUET(8754),
/* 2398 */   POTTED_RED_TULIP(28594),
/* 2399 */   POTTED_ORANGE_TULIP(28807),
/* 2400 */   POTTED_WHITE_TULIP(24330),
/* 2401 */   POTTED_PINK_TULIP(10089),
/* 2402 */   POTTED_OXEYE_DAISY(19707),
/* 2403 */   POTTED_CORNFLOWER(28917),
/* 2404 */   POTTED_LILY_OF_THE_VALLEY(9364),
/* 2405 */   POTTED_WITHER_ROSE(26876),
/* 2406 */   POTTED_RED_MUSHROOM(22881),
/* 2407 */   POTTED_BROWN_MUSHROOM(14481),
/* 2408 */   POTTED_DEAD_BUSH(13020),
/* 2409 */   POTTED_CACTUS(8777),
/*      */ 
/*      */ 
/*      */   
/* 2413 */   CARROTS(17258, Ageable.class),
/*      */ 
/*      */ 
/*      */   
/* 2417 */   POTATOES(10879, Ageable.class),
/*      */ 
/*      */ 
/*      */   
/* 2421 */   SKELETON_WALL_SKULL(31650, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2425 */   WITHER_SKELETON_WALL_SKULL(9326, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2429 */   ZOMBIE_WALL_HEAD(16296, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2433 */   PLAYER_WALL_HEAD(13164, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2437 */   CREEPER_WALL_HEAD(30123, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2441 */   DRAGON_WALL_HEAD(19818, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2445 */   WHITE_WALL_BANNER(15967, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2449 */   ORANGE_WALL_BANNER(9936, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2453 */   MAGENTA_WALL_BANNER(23291, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2457 */   LIGHT_BLUE_WALL_BANNER(12011, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2461 */   YELLOW_WALL_BANNER(32004, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2465 */   LIME_WALL_BANNER(21422, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2469 */   PINK_WALL_BANNER(9421, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2473 */   GRAY_WALL_BANNER(24275, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2477 */   LIGHT_GRAY_WALL_BANNER(31088, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2481 */   CYAN_WALL_BANNER(10889, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2485 */   PURPLE_WALL_BANNER(14298, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2489 */   BLUE_WALL_BANNER(17757, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2493 */   BROWN_WALL_BANNER(14731, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2497 */   GREEN_WALL_BANNER(15046, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2501 */   RED_WALL_BANNER(4378, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2505 */   BLACK_WALL_BANNER(4919, Directional.class),
/*      */ 
/*      */ 
/*      */   
/* 2509 */   BEETROOTS(22075, Ageable.class),
/* 2510 */   END_GATEWAY(26605),
/*      */ 
/*      */ 
/*      */   
/* 2514 */   FROSTED_ICE(21814, Ageable.class),
/* 2515 */   KELP_PLANT(29697),
/*      */ 
/*      */ 
/*      */   
/* 2519 */   DEAD_TUBE_CORAL_WALL_FAN(5128, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2523 */   DEAD_BRAIN_CORAL_WALL_FAN(23718, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2527 */   DEAD_BUBBLE_CORAL_WALL_FAN(18453, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2531 */   DEAD_FIRE_CORAL_WALL_FAN(23375, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2535 */   DEAD_HORN_CORAL_WALL_FAN(27550, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2539 */   TUBE_CORAL_WALL_FAN(25282, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2543 */   BRAIN_CORAL_WALL_FAN(22685, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2547 */   BUBBLE_CORAL_WALL_FAN(20382, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2551 */   FIRE_CORAL_WALL_FAN(20100, CoralWallFan.class),
/*      */ 
/*      */ 
/*      */   
/* 2555 */   HORN_CORAL_WALL_FAN(28883, CoralWallFan.class),
/* 2556 */   BAMBOO_SAPLING(8478),
/* 2557 */   POTTED_BAMBOO(22542),
/* 2558 */   VOID_AIR(13668),
/* 2559 */   CAVE_AIR(17422),
/*      */ 
/*      */ 
/*      */   
/* 2563 */   BUBBLE_COLUMN(31612, BubbleColumn.class),
/*      */ 
/*      */ 
/*      */   
/* 2567 */   SWEET_BERRY_BUSH(11958, Ageable.class),
/* 2568 */   WEEPING_VINES_PLANT(19437),
/* 2569 */   TWISTING_VINES_PLANT(25338),
/*      */ 
/*      */ 
/*      */   
/* 2573 */   CRIMSON_WALL_SIGN(19242, 16, WallSign.class),
/*      */ 
/*      */ 
/*      */   
/* 2577 */   WARPED_WALL_SIGN(13534, 16, WallSign.class),
/* 2578 */   POTTED_CRIMSON_FUNGUS(5548),
/* 2579 */   POTTED_WARPED_FUNGUS(30800),
/* 2580 */   POTTED_CRIMSON_ROOTS(13852),
/* 2581 */   POTTED_WARPED_ROOTS(6403),
/*      */   
/* 2583 */   LEGACY_AIR(0, 0),
/*      */   
/* 2585 */   LEGACY_STONE(1),
/*      */   
/* 2587 */   LEGACY_GRASS(2),
/*      */   
/* 2589 */   LEGACY_DIRT(3),
/*      */   
/* 2591 */   LEGACY_COBBLESTONE(4),
/*      */   
/* 2593 */   LEGACY_WOOD(5, Wood.class),
/*      */   
/* 2595 */   LEGACY_SAPLING(6, Sapling.class),
/*      */   
/* 2597 */   LEGACY_BEDROCK(7),
/*      */   
/* 2599 */   LEGACY_WATER(8, MaterialData.class),
/*      */   
/* 2601 */   LEGACY_STATIONARY_WATER(9, MaterialData.class),
/*      */   
/* 2603 */   LEGACY_LAVA(10, MaterialData.class),
/*      */   
/* 2605 */   LEGACY_STATIONARY_LAVA(11, MaterialData.class),
/*      */   
/* 2607 */   LEGACY_SAND(12),
/*      */   
/* 2609 */   LEGACY_GRAVEL(13),
/*      */   
/* 2611 */   LEGACY_GOLD_ORE(14),
/*      */   
/* 2613 */   LEGACY_IRON_ORE(15),
/*      */   
/* 2615 */   LEGACY_COAL_ORE(16),
/*      */   
/* 2617 */   LEGACY_LOG(17, Tree.class),
/*      */   
/* 2619 */   LEGACY_LEAVES(18, Leaves.class),
/*      */   
/* 2621 */   LEGACY_SPONGE(19),
/*      */   
/* 2623 */   LEGACY_GLASS(20),
/*      */   
/* 2625 */   LEGACY_LAPIS_ORE(21),
/*      */   
/* 2627 */   LEGACY_LAPIS_BLOCK(22),
/*      */   
/* 2629 */   LEGACY_DISPENSER(23, Dispenser.class),
/*      */   
/* 2631 */   LEGACY_SANDSTONE(24, Sandstone.class),
/*      */   
/* 2633 */   LEGACY_NOTE_BLOCK(25),
/*      */   
/* 2635 */   LEGACY_BED_BLOCK(26, Bed.class),
/*      */   
/* 2637 */   LEGACY_POWERED_RAIL(27, PoweredRail.class),
/*      */   
/* 2639 */   LEGACY_DETECTOR_RAIL(28, DetectorRail.class),
/*      */   
/* 2641 */   LEGACY_PISTON_STICKY_BASE(29, PistonBaseMaterial.class),
/*      */   
/* 2643 */   LEGACY_WEB(30),
/*      */   
/* 2645 */   LEGACY_LONG_GRASS(31, LongGrass.class),
/*      */   
/* 2647 */   LEGACY_DEAD_BUSH(32),
/*      */   
/* 2649 */   LEGACY_PISTON_BASE(33, PistonBaseMaterial.class),
/*      */   
/* 2651 */   LEGACY_PISTON_EXTENSION(34, PistonExtensionMaterial.class),
/*      */   
/* 2653 */   LEGACY_WOOL(35, Wool.class),
/*      */   
/* 2655 */   LEGACY_PISTON_MOVING_PIECE(36),
/*      */   
/* 2657 */   LEGACY_YELLOW_FLOWER(37),
/*      */   
/* 2659 */   LEGACY_RED_ROSE(38),
/*      */   
/* 2661 */   LEGACY_BROWN_MUSHROOM(39),
/*      */   
/* 2663 */   LEGACY_RED_MUSHROOM(40),
/*      */   
/* 2665 */   LEGACY_GOLD_BLOCK(41),
/*      */   
/* 2667 */   LEGACY_IRON_BLOCK(42),
/*      */   
/* 2669 */   LEGACY_DOUBLE_STEP(43, Step.class),
/*      */   
/* 2671 */   LEGACY_STEP(44, Step.class),
/*      */   
/* 2673 */   LEGACY_BRICK(45),
/*      */   
/* 2675 */   LEGACY_TNT(46),
/*      */   
/* 2677 */   LEGACY_BOOKSHELF(47),
/*      */   
/* 2679 */   LEGACY_MOSSY_COBBLESTONE(48),
/*      */   
/* 2681 */   LEGACY_OBSIDIAN(49),
/*      */   
/* 2683 */   LEGACY_TORCH(50, Torch.class),
/*      */   
/* 2685 */   LEGACY_FIRE(51),
/*      */   
/* 2687 */   LEGACY_MOB_SPAWNER(52),
/*      */   
/* 2689 */   LEGACY_WOOD_STAIRS(53, Stairs.class),
/*      */   
/* 2691 */   LEGACY_CHEST(54, Chest.class),
/*      */   
/* 2693 */   LEGACY_REDSTONE_WIRE(55, RedstoneWire.class),
/*      */   
/* 2695 */   LEGACY_DIAMOND_ORE(56),
/*      */   
/* 2697 */   LEGACY_DIAMOND_BLOCK(57),
/*      */   
/* 2699 */   LEGACY_WORKBENCH(58),
/*      */   
/* 2701 */   LEGACY_CROPS(59, Crops.class),
/*      */   
/* 2703 */   LEGACY_SOIL(60, MaterialData.class),
/*      */   
/* 2705 */   LEGACY_FURNACE(61, Furnace.class),
/*      */   
/* 2707 */   LEGACY_BURNING_FURNACE(62, Furnace.class),
/*      */   
/* 2709 */   LEGACY_SIGN_POST(63, 64, Sign.class),
/*      */   
/* 2711 */   LEGACY_WOODEN_DOOR(64, Door.class),
/*      */   
/* 2713 */   LEGACY_LADDER(65, Ladder.class),
/*      */   
/* 2715 */   LEGACY_RAILS(66, Rails.class),
/*      */   
/* 2717 */   LEGACY_COBBLESTONE_STAIRS(67, Stairs.class),
/*      */   
/* 2719 */   LEGACY_WALL_SIGN(68, 64, Sign.class),
/*      */   
/* 2721 */   LEGACY_LEVER(69, Lever.class),
/*      */   
/* 2723 */   LEGACY_STONE_PLATE(70, PressurePlate.class),
/*      */   
/* 2725 */   LEGACY_IRON_DOOR_BLOCK(71, Door.class),
/*      */   
/* 2727 */   LEGACY_WOOD_PLATE(72, PressurePlate.class),
/*      */   
/* 2729 */   LEGACY_REDSTONE_ORE(73),
/*      */   
/* 2731 */   LEGACY_GLOWING_REDSTONE_ORE(74),
/*      */   
/* 2733 */   LEGACY_REDSTONE_TORCH_OFF(75, RedstoneTorch.class),
/*      */   
/* 2735 */   LEGACY_REDSTONE_TORCH_ON(76, RedstoneTorch.class),
/*      */   
/* 2737 */   LEGACY_STONE_BUTTON(77, Button.class),
/*      */   
/* 2739 */   LEGACY_SNOW(78),
/*      */   
/* 2741 */   LEGACY_ICE(79),
/*      */   
/* 2743 */   LEGACY_SNOW_BLOCK(80),
/*      */   
/* 2745 */   LEGACY_CACTUS(81, MaterialData.class),
/*      */   
/* 2747 */   LEGACY_CLAY(82),
/*      */   
/* 2749 */   LEGACY_SUGAR_CANE_BLOCK(83, MaterialData.class),
/*      */   
/* 2751 */   LEGACY_JUKEBOX(84),
/*      */   
/* 2753 */   LEGACY_FENCE(85),
/*      */   
/* 2755 */   LEGACY_PUMPKIN(86, Pumpkin.class),
/*      */   
/* 2757 */   LEGACY_NETHERRACK(87),
/*      */   
/* 2759 */   LEGACY_SOUL_SAND(88),
/*      */   
/* 2761 */   LEGACY_GLOWSTONE(89),
/*      */   
/* 2763 */   LEGACY_PORTAL(90),
/*      */   
/* 2765 */   LEGACY_JACK_O_LANTERN(91, Pumpkin.class),
/*      */   
/* 2767 */   LEGACY_CAKE_BLOCK(92, 64, Cake.class),
/*      */   
/* 2769 */   LEGACY_DIODE_BLOCK_OFF(93, Diode.class),
/*      */   
/* 2771 */   LEGACY_DIODE_BLOCK_ON(94, Diode.class),
/*      */   
/* 2773 */   LEGACY_STAINED_GLASS(95),
/*      */   
/* 2775 */   LEGACY_TRAP_DOOR(96, TrapDoor.class),
/*      */   
/* 2777 */   LEGACY_MONSTER_EGGS(97, MonsterEggs.class),
/*      */   
/* 2779 */   LEGACY_SMOOTH_BRICK(98, SmoothBrick.class),
/*      */   
/* 2781 */   LEGACY_HUGE_MUSHROOM_1(99, Mushroom.class),
/*      */   
/* 2783 */   LEGACY_HUGE_MUSHROOM_2(100, Mushroom.class),
/*      */   
/* 2785 */   LEGACY_IRON_FENCE(101),
/*      */   
/* 2787 */   LEGACY_THIN_GLASS(102),
/*      */   
/* 2789 */   LEGACY_MELON_BLOCK(103),
/*      */   
/* 2791 */   LEGACY_PUMPKIN_STEM(104, MaterialData.class),
/*      */   
/* 2793 */   LEGACY_MELON_STEM(105, MaterialData.class),
/*      */   
/* 2795 */   LEGACY_VINE(106, Vine.class),
/*      */   
/* 2797 */   LEGACY_FENCE_GATE(107, Gate.class),
/*      */   
/* 2799 */   LEGACY_BRICK_STAIRS(108, Stairs.class),
/*      */   
/* 2801 */   LEGACY_SMOOTH_STAIRS(109, Stairs.class),
/*      */   
/* 2803 */   LEGACY_MYCEL(110),
/*      */   
/* 2805 */   LEGACY_WATER_LILY(111),
/*      */   
/* 2807 */   LEGACY_NETHER_BRICK(112),
/*      */   
/* 2809 */   LEGACY_NETHER_FENCE(113),
/*      */   
/* 2811 */   LEGACY_NETHER_BRICK_STAIRS(114, Stairs.class),
/*      */   
/* 2813 */   LEGACY_NETHER_WARTS(115, NetherWarts.class),
/*      */   
/* 2815 */   LEGACY_ENCHANTMENT_TABLE(116),
/*      */   
/* 2817 */   LEGACY_BREWING_STAND(117, MaterialData.class),
/*      */   
/* 2819 */   LEGACY_CAULDRON(118, Cauldron.class),
/*      */   
/* 2821 */   LEGACY_ENDER_PORTAL(119),
/*      */   
/* 2823 */   LEGACY_ENDER_PORTAL_FRAME(120),
/*      */   
/* 2825 */   LEGACY_ENDER_STONE(121),
/*      */   
/* 2827 */   LEGACY_DRAGON_EGG(122),
/*      */   
/* 2829 */   LEGACY_REDSTONE_LAMP_OFF(123),
/*      */   
/* 2831 */   LEGACY_REDSTONE_LAMP_ON(124),
/*      */   
/* 2833 */   LEGACY_WOOD_DOUBLE_STEP(125, Wood.class),
/*      */   
/* 2835 */   LEGACY_WOOD_STEP(126, WoodenStep.class),
/*      */   
/* 2837 */   LEGACY_COCOA(127, CocoaPlant.class),
/*      */   
/* 2839 */   LEGACY_SANDSTONE_STAIRS(128, Stairs.class),
/*      */   
/* 2841 */   LEGACY_EMERALD_ORE(129),
/*      */   
/* 2843 */   LEGACY_ENDER_CHEST(130, EnderChest.class),
/*      */   
/* 2845 */   LEGACY_TRIPWIRE_HOOK(131, TripwireHook.class),
/*      */   
/* 2847 */   LEGACY_TRIPWIRE(132, Tripwire.class),
/*      */   
/* 2849 */   LEGACY_EMERALD_BLOCK(133),
/*      */   
/* 2851 */   LEGACY_SPRUCE_WOOD_STAIRS(134, Stairs.class),
/*      */   
/* 2853 */   LEGACY_BIRCH_WOOD_STAIRS(135, Stairs.class),
/*      */   
/* 2855 */   LEGACY_JUNGLE_WOOD_STAIRS(136, Stairs.class),
/*      */   
/* 2857 */   LEGACY_COMMAND(137, Command.class),
/*      */   
/* 2859 */   LEGACY_BEACON(138),
/*      */   
/* 2861 */   LEGACY_COBBLE_WALL(139),
/*      */   
/* 2863 */   LEGACY_FLOWER_POT(140, FlowerPot.class),
/*      */   
/* 2865 */   LEGACY_CARROT(141, Crops.class),
/*      */   
/* 2867 */   LEGACY_POTATO(142, Crops.class),
/*      */   
/* 2869 */   LEGACY_WOOD_BUTTON(143, Button.class),
/*      */   
/* 2871 */   LEGACY_SKULL(144, Skull.class),
/*      */   
/* 2873 */   LEGACY_ANVIL(145),
/*      */   
/* 2875 */   LEGACY_TRAPPED_CHEST(146, Chest.class),
/*      */   
/* 2877 */   LEGACY_GOLD_PLATE(147),
/*      */   
/* 2879 */   LEGACY_IRON_PLATE(148),
/*      */   
/* 2881 */   LEGACY_REDSTONE_COMPARATOR_OFF(149, Comparator.class),
/*      */   
/* 2883 */   LEGACY_REDSTONE_COMPARATOR_ON(150, Comparator.class),
/*      */   
/* 2885 */   LEGACY_DAYLIGHT_DETECTOR(151),
/*      */   
/* 2887 */   LEGACY_REDSTONE_BLOCK(152),
/*      */   
/* 2889 */   LEGACY_QUARTZ_ORE(153),
/*      */   
/* 2891 */   LEGACY_HOPPER(154, Hopper.class),
/*      */   
/* 2893 */   LEGACY_QUARTZ_BLOCK(155),
/*      */   
/* 2895 */   LEGACY_QUARTZ_STAIRS(156, Stairs.class),
/*      */   
/* 2897 */   LEGACY_ACTIVATOR_RAIL(157, PoweredRail.class),
/*      */   
/* 2899 */   LEGACY_DROPPER(158, Dispenser.class),
/*      */   
/* 2901 */   LEGACY_STAINED_CLAY(159),
/*      */   
/* 2903 */   LEGACY_STAINED_GLASS_PANE(160),
/*      */   
/* 2905 */   LEGACY_LEAVES_2(161, Leaves.class),
/*      */   
/* 2907 */   LEGACY_LOG_2(162, Tree.class),
/*      */   
/* 2909 */   LEGACY_ACACIA_STAIRS(163, Stairs.class),
/*      */   
/* 2911 */   LEGACY_DARK_OAK_STAIRS(164, Stairs.class),
/*      */   
/* 2913 */   LEGACY_SLIME_BLOCK(165),
/*      */   
/* 2915 */   LEGACY_BARRIER(166),
/*      */   
/* 2917 */   LEGACY_IRON_TRAPDOOR(167, TrapDoor.class),
/*      */   
/* 2919 */   LEGACY_PRISMARINE(168),
/*      */   
/* 2921 */   LEGACY_SEA_LANTERN(169),
/*      */   
/* 2923 */   LEGACY_HAY_BLOCK(170),
/*      */   
/* 2925 */   LEGACY_CARPET(171),
/*      */   
/* 2927 */   LEGACY_HARD_CLAY(172),
/*      */   
/* 2929 */   LEGACY_COAL_BLOCK(173),
/*      */   
/* 2931 */   LEGACY_PACKED_ICE(174),
/*      */   
/* 2933 */   LEGACY_DOUBLE_PLANT(175),
/*      */   
/* 2935 */   LEGACY_STANDING_BANNER(176, Banner.class),
/*      */   
/* 2937 */   LEGACY_WALL_BANNER(177, Banner.class),
/*      */   
/* 2939 */   LEGACY_DAYLIGHT_DETECTOR_INVERTED(178),
/*      */   
/* 2941 */   LEGACY_RED_SANDSTONE(179),
/*      */   
/* 2943 */   LEGACY_RED_SANDSTONE_STAIRS(180, Stairs.class),
/*      */   
/* 2945 */   LEGACY_DOUBLE_STONE_SLAB2(181),
/*      */   
/* 2947 */   LEGACY_STONE_SLAB2(182),
/*      */   
/* 2949 */   LEGACY_SPRUCE_FENCE_GATE(183, Gate.class),
/*      */   
/* 2951 */   LEGACY_BIRCH_FENCE_GATE(184, Gate.class),
/*      */   
/* 2953 */   LEGACY_JUNGLE_FENCE_GATE(185, Gate.class),
/*      */   
/* 2955 */   LEGACY_DARK_OAK_FENCE_GATE(186, Gate.class),
/*      */   
/* 2957 */   LEGACY_ACACIA_FENCE_GATE(187, Gate.class),
/*      */   
/* 2959 */   LEGACY_SPRUCE_FENCE(188),
/*      */   
/* 2961 */   LEGACY_BIRCH_FENCE(189),
/*      */   
/* 2963 */   LEGACY_JUNGLE_FENCE(190),
/*      */   
/* 2965 */   LEGACY_DARK_OAK_FENCE(191),
/*      */   
/* 2967 */   LEGACY_ACACIA_FENCE(192),
/*      */   
/* 2969 */   LEGACY_SPRUCE_DOOR(193, Door.class),
/*      */   
/* 2971 */   LEGACY_BIRCH_DOOR(194, Door.class),
/*      */   
/* 2973 */   LEGACY_JUNGLE_DOOR(195, Door.class),
/*      */   
/* 2975 */   LEGACY_ACACIA_DOOR(196, Door.class),
/*      */   
/* 2977 */   LEGACY_DARK_OAK_DOOR(197, Door.class),
/*      */   
/* 2979 */   LEGACY_END_ROD(198),
/*      */   
/* 2981 */   LEGACY_CHORUS_PLANT(199),
/*      */   
/* 2983 */   LEGACY_CHORUS_FLOWER(200),
/*      */   
/* 2985 */   LEGACY_PURPUR_BLOCK(201),
/*      */   
/* 2987 */   LEGACY_PURPUR_PILLAR(202),
/*      */   
/* 2989 */   LEGACY_PURPUR_STAIRS(203, Stairs.class),
/*      */   
/* 2991 */   LEGACY_PURPUR_DOUBLE_SLAB(204),
/*      */   
/* 2993 */   LEGACY_PURPUR_SLAB(205),
/*      */   
/* 2995 */   LEGACY_END_BRICKS(206),
/*      */   
/* 2997 */   LEGACY_BEETROOT_BLOCK(207, Crops.class),
/*      */   
/* 2999 */   LEGACY_GRASS_PATH(208),
/*      */   
/* 3001 */   LEGACY_END_GATEWAY(209),
/*      */   
/* 3003 */   LEGACY_COMMAND_REPEATING(210, Command.class),
/*      */   
/* 3005 */   LEGACY_COMMAND_CHAIN(211, Command.class),
/*      */   
/* 3007 */   LEGACY_FROSTED_ICE(212),
/*      */   
/* 3009 */   LEGACY_MAGMA(213),
/*      */   
/* 3011 */   LEGACY_NETHER_WART_BLOCK(214),
/*      */   
/* 3013 */   LEGACY_RED_NETHER_BRICK(215),
/*      */   
/* 3015 */   LEGACY_BONE_BLOCK(216),
/*      */   
/* 3017 */   LEGACY_STRUCTURE_VOID(217),
/*      */   
/* 3019 */   LEGACY_OBSERVER(218, Observer.class),
/*      */   
/* 3021 */   LEGACY_WHITE_SHULKER_BOX(219, 1),
/*      */   
/* 3023 */   LEGACY_ORANGE_SHULKER_BOX(220, 1),
/*      */   
/* 3025 */   LEGACY_MAGENTA_SHULKER_BOX(221, 1),
/*      */   
/* 3027 */   LEGACY_LIGHT_BLUE_SHULKER_BOX(222, 1),
/*      */   
/* 3029 */   LEGACY_YELLOW_SHULKER_BOX(223, 1),
/*      */   
/* 3031 */   LEGACY_LIME_SHULKER_BOX(224, 1),
/*      */   
/* 3033 */   LEGACY_PINK_SHULKER_BOX(225, 1),
/*      */   
/* 3035 */   LEGACY_GRAY_SHULKER_BOX(226, 1),
/*      */   
/* 3037 */   LEGACY_SILVER_SHULKER_BOX(227, 1),
/*      */   
/* 3039 */   LEGACY_CYAN_SHULKER_BOX(228, 1),
/*      */   
/* 3041 */   LEGACY_PURPLE_SHULKER_BOX(229, 1),
/*      */   
/* 3043 */   LEGACY_BLUE_SHULKER_BOX(230, 1),
/*      */   
/* 3045 */   LEGACY_BROWN_SHULKER_BOX(231, 1),
/*      */   
/* 3047 */   LEGACY_GREEN_SHULKER_BOX(232, 1),
/*      */   
/* 3049 */   LEGACY_RED_SHULKER_BOX(233, 1),
/*      */   
/* 3051 */   LEGACY_BLACK_SHULKER_BOX(234, 1),
/*      */   
/* 3053 */   LEGACY_WHITE_GLAZED_TERRACOTTA(235),
/*      */   
/* 3055 */   LEGACY_ORANGE_GLAZED_TERRACOTTA(236),
/*      */   
/* 3057 */   LEGACY_MAGENTA_GLAZED_TERRACOTTA(237),
/*      */   
/* 3059 */   LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA(238),
/*      */   
/* 3061 */   LEGACY_YELLOW_GLAZED_TERRACOTTA(239),
/*      */   
/* 3063 */   LEGACY_LIME_GLAZED_TERRACOTTA(240),
/*      */   
/* 3065 */   LEGACY_PINK_GLAZED_TERRACOTTA(241),
/*      */   
/* 3067 */   LEGACY_GRAY_GLAZED_TERRACOTTA(242),
/*      */   
/* 3069 */   LEGACY_SILVER_GLAZED_TERRACOTTA(243),
/*      */   
/* 3071 */   LEGACY_CYAN_GLAZED_TERRACOTTA(244),
/*      */   
/* 3073 */   LEGACY_PURPLE_GLAZED_TERRACOTTA(245),
/*      */   
/* 3075 */   LEGACY_BLUE_GLAZED_TERRACOTTA(246),
/*      */   
/* 3077 */   LEGACY_BROWN_GLAZED_TERRACOTTA(247),
/*      */   
/* 3079 */   LEGACY_GREEN_GLAZED_TERRACOTTA(248),
/*      */   
/* 3081 */   LEGACY_RED_GLAZED_TERRACOTTA(249),
/*      */   
/* 3083 */   LEGACY_BLACK_GLAZED_TERRACOTTA(250),
/*      */   
/* 3085 */   LEGACY_CONCRETE(251),
/*      */   
/* 3087 */   LEGACY_CONCRETE_POWDER(252),
/*      */   
/* 3089 */   LEGACY_STRUCTURE_BLOCK(255),
/*      */ 
/*      */   
/* 3092 */   LEGACY_IRON_SPADE(256, 1, 250),
/*      */   
/* 3094 */   LEGACY_IRON_PICKAXE(257, 1, 250),
/*      */   
/* 3096 */   LEGACY_IRON_AXE(258, 1, 250),
/*      */   
/* 3098 */   LEGACY_FLINT_AND_STEEL(259, 1, 64),
/*      */   
/* 3100 */   LEGACY_APPLE(260),
/*      */   
/* 3102 */   LEGACY_BOW(261, 1, 384),
/*      */   
/* 3104 */   LEGACY_ARROW(262),
/*      */   
/* 3106 */   LEGACY_COAL(263, Coal.class),
/*      */   
/* 3108 */   LEGACY_DIAMOND(264),
/*      */   
/* 3110 */   LEGACY_IRON_INGOT(265),
/*      */   
/* 3112 */   LEGACY_GOLD_INGOT(266),
/*      */   
/* 3114 */   LEGACY_IRON_SWORD(267, 1, 250),
/*      */   
/* 3116 */   LEGACY_WOOD_SWORD(268, 1, 59),
/*      */   
/* 3118 */   LEGACY_WOOD_SPADE(269, 1, 59),
/*      */   
/* 3120 */   LEGACY_WOOD_PICKAXE(270, 1, 59),
/*      */   
/* 3122 */   LEGACY_WOOD_AXE(271, 1, 59),
/*      */   
/* 3124 */   LEGACY_STONE_SWORD(272, 1, 131),
/*      */   
/* 3126 */   LEGACY_STONE_SPADE(273, 1, 131),
/*      */   
/* 3128 */   LEGACY_STONE_PICKAXE(274, 1, 131),
/*      */   
/* 3130 */   LEGACY_STONE_AXE(275, 1, 131),
/*      */   
/* 3132 */   LEGACY_DIAMOND_SWORD(276, 1, 1561),
/*      */   
/* 3134 */   LEGACY_DIAMOND_SPADE(277, 1, 1561),
/*      */   
/* 3136 */   LEGACY_DIAMOND_PICKAXE(278, 1, 1561),
/*      */   
/* 3138 */   LEGACY_DIAMOND_AXE(279, 1, 1561),
/*      */   
/* 3140 */   LEGACY_STICK(280),
/*      */   
/* 3142 */   LEGACY_BOWL(281),
/*      */   
/* 3144 */   LEGACY_MUSHROOM_SOUP(282, 1),
/*      */   
/* 3146 */   LEGACY_GOLD_SWORD(283, 1, 32),
/*      */   
/* 3148 */   LEGACY_GOLD_SPADE(284, 1, 32),
/*      */   
/* 3150 */   LEGACY_GOLD_PICKAXE(285, 1, 32),
/*      */   
/* 3152 */   LEGACY_GOLD_AXE(286, 1, 32),
/*      */   
/* 3154 */   LEGACY_STRING(287),
/*      */   
/* 3156 */   LEGACY_FEATHER(288),
/*      */   
/* 3158 */   LEGACY_SULPHUR(289),
/*      */   
/* 3160 */   LEGACY_WOOD_HOE(290, 1, 59),
/*      */   
/* 3162 */   LEGACY_STONE_HOE(291, 1, 131),
/*      */   
/* 3164 */   LEGACY_IRON_HOE(292, 1, 250),
/*      */   
/* 3166 */   LEGACY_DIAMOND_HOE(293, 1, 1561),
/*      */   
/* 3168 */   LEGACY_GOLD_HOE(294, 1, 32),
/*      */   
/* 3170 */   LEGACY_SEEDS(295),
/*      */   
/* 3172 */   LEGACY_WHEAT(296),
/*      */   
/* 3174 */   LEGACY_BREAD(297),
/*      */   
/* 3176 */   LEGACY_LEATHER_HELMET(298, 1, 55),
/*      */   
/* 3178 */   LEGACY_LEATHER_CHESTPLATE(299, 1, 80),
/*      */   
/* 3180 */   LEGACY_LEATHER_LEGGINGS(300, 1, 75),
/*      */   
/* 3182 */   LEGACY_LEATHER_BOOTS(301, 1, 65),
/*      */   
/* 3184 */   LEGACY_CHAINMAIL_HELMET(302, 1, 165),
/*      */   
/* 3186 */   LEGACY_CHAINMAIL_CHESTPLATE(303, 1, 240),
/*      */   
/* 3188 */   LEGACY_CHAINMAIL_LEGGINGS(304, 1, 225),
/*      */   
/* 3190 */   LEGACY_CHAINMAIL_BOOTS(305, 1, 195),
/*      */   
/* 3192 */   LEGACY_IRON_HELMET(306, 1, 165),
/*      */   
/* 3194 */   LEGACY_IRON_CHESTPLATE(307, 1, 240),
/*      */   
/* 3196 */   LEGACY_IRON_LEGGINGS(308, 1, 225),
/*      */   
/* 3198 */   LEGACY_IRON_BOOTS(309, 1, 195),
/*      */   
/* 3200 */   LEGACY_DIAMOND_HELMET(310, 1, 363),
/*      */   
/* 3202 */   LEGACY_DIAMOND_CHESTPLATE(311, 1, 528),
/*      */   
/* 3204 */   LEGACY_DIAMOND_LEGGINGS(312, 1, 495),
/*      */   
/* 3206 */   LEGACY_DIAMOND_BOOTS(313, 1, 429),
/*      */   
/* 3208 */   LEGACY_GOLD_HELMET(314, 1, 77),
/*      */   
/* 3210 */   LEGACY_GOLD_CHESTPLATE(315, 1, 112),
/*      */   
/* 3212 */   LEGACY_GOLD_LEGGINGS(316, 1, 105),
/*      */   
/* 3214 */   LEGACY_GOLD_BOOTS(317, 1, 91),
/*      */   
/* 3216 */   LEGACY_FLINT(318),
/*      */   
/* 3218 */   LEGACY_PORK(319),
/*      */   
/* 3220 */   LEGACY_GRILLED_PORK(320),
/*      */   
/* 3222 */   LEGACY_PAINTING(321),
/*      */   
/* 3224 */   LEGACY_GOLDEN_APPLE(322),
/*      */   
/* 3226 */   LEGACY_SIGN(323, 16),
/*      */   
/* 3228 */   LEGACY_WOOD_DOOR(324, 64),
/*      */   
/* 3230 */   LEGACY_BUCKET(325, 16),
/*      */   
/* 3232 */   LEGACY_WATER_BUCKET(326, 1),
/*      */   
/* 3234 */   LEGACY_LAVA_BUCKET(327, 1),
/*      */   
/* 3236 */   LEGACY_MINECART(328, 1),
/*      */   
/* 3238 */   LEGACY_SADDLE(329, 1),
/*      */   
/* 3240 */   LEGACY_IRON_DOOR(330, 64),
/*      */   
/* 3242 */   LEGACY_REDSTONE(331),
/*      */   
/* 3244 */   LEGACY_SNOW_BALL(332, 16),
/*      */   
/* 3246 */   LEGACY_BOAT(333, 1),
/*      */   
/* 3248 */   LEGACY_LEATHER(334),
/*      */   
/* 3250 */   LEGACY_MILK_BUCKET(335, 1),
/*      */   
/* 3252 */   LEGACY_CLAY_BRICK(336),
/*      */   
/* 3254 */   LEGACY_CLAY_BALL(337),
/*      */   
/* 3256 */   LEGACY_SUGAR_CANE(338),
/*      */   
/* 3258 */   LEGACY_PAPER(339),
/*      */   
/* 3260 */   LEGACY_BOOK(340),
/*      */   
/* 3262 */   LEGACY_SLIME_BALL(341),
/*      */   
/* 3264 */   LEGACY_STORAGE_MINECART(342, 1),
/*      */   
/* 3266 */   LEGACY_POWERED_MINECART(343, 1),
/*      */   
/* 3268 */   LEGACY_EGG(344, 16),
/*      */   
/* 3270 */   LEGACY_COMPASS(345),
/*      */   
/* 3272 */   LEGACY_FISHING_ROD(346, 1, 64),
/*      */   
/* 3274 */   LEGACY_WATCH(347),
/*      */   
/* 3276 */   LEGACY_GLOWSTONE_DUST(348),
/*      */   
/* 3278 */   LEGACY_RAW_FISH(349),
/*      */   
/* 3280 */   LEGACY_COOKED_FISH(350),
/*      */   
/* 3282 */   LEGACY_INK_SACK(351, Dye.class),
/*      */   
/* 3284 */   LEGACY_BONE(352),
/*      */   
/* 3286 */   LEGACY_SUGAR(353),
/*      */   
/* 3288 */   LEGACY_CAKE(354, 1),
/*      */   
/* 3290 */   LEGACY_BED(355, 1),
/*      */   
/* 3292 */   LEGACY_DIODE(356),
/*      */   
/* 3294 */   LEGACY_COOKIE(357),
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3299 */   LEGACY_MAP(358, MaterialData.class),
/*      */   
/* 3301 */   LEGACY_SHEARS(359, 1, 238),
/*      */   
/* 3303 */   LEGACY_MELON(360),
/*      */   
/* 3305 */   LEGACY_PUMPKIN_SEEDS(361),
/*      */   
/* 3307 */   LEGACY_MELON_SEEDS(362),
/*      */   
/* 3309 */   LEGACY_RAW_BEEF(363),
/*      */   
/* 3311 */   LEGACY_COOKED_BEEF(364),
/*      */   
/* 3313 */   LEGACY_RAW_CHICKEN(365),
/*      */   
/* 3315 */   LEGACY_COOKED_CHICKEN(366),
/*      */   
/* 3317 */   LEGACY_ROTTEN_FLESH(367),
/*      */   
/* 3319 */   LEGACY_ENDER_PEARL(368, 16),
/*      */   
/* 3321 */   LEGACY_BLAZE_ROD(369),
/*      */   
/* 3323 */   LEGACY_GHAST_TEAR(370),
/*      */   
/* 3325 */   LEGACY_GOLD_NUGGET(371),
/*      */   
/* 3327 */   LEGACY_NETHER_STALK(372),
/*      */   
/* 3329 */   LEGACY_POTION(373, 1, MaterialData.class),
/*      */   
/* 3331 */   LEGACY_GLASS_BOTTLE(374),
/*      */   
/* 3333 */   LEGACY_SPIDER_EYE(375),
/*      */   
/* 3335 */   LEGACY_FERMENTED_SPIDER_EYE(376),
/*      */   
/* 3337 */   LEGACY_BLAZE_POWDER(377),
/*      */   
/* 3339 */   LEGACY_MAGMA_CREAM(378),
/*      */   
/* 3341 */   LEGACY_BREWING_STAND_ITEM(379),
/*      */   
/* 3343 */   LEGACY_CAULDRON_ITEM(380),
/*      */   
/* 3345 */   LEGACY_EYE_OF_ENDER(381),
/*      */   
/* 3347 */   LEGACY_SPECKLED_MELON(382),
/*      */   
/* 3349 */   LEGACY_MONSTER_EGG(383, 64, SpawnEgg.class),
/*      */   
/* 3351 */   LEGACY_EXP_BOTTLE(384, 64),
/*      */   
/* 3353 */   LEGACY_FIREBALL(385, 64),
/*      */   
/* 3355 */   LEGACY_BOOK_AND_QUILL(386, 1),
/*      */   
/* 3357 */   LEGACY_WRITTEN_BOOK(387, 16),
/*      */   
/* 3359 */   LEGACY_EMERALD(388, 64),
/*      */   
/* 3361 */   LEGACY_ITEM_FRAME(389),
/*      */   
/* 3363 */   LEGACY_FLOWER_POT_ITEM(390),
/*      */   
/* 3365 */   LEGACY_CARROT_ITEM(391),
/*      */   
/* 3367 */   LEGACY_POTATO_ITEM(392),
/*      */   
/* 3369 */   LEGACY_BAKED_POTATO(393),
/*      */   
/* 3371 */   LEGACY_POISONOUS_POTATO(394),
/*      */   
/* 3373 */   LEGACY_EMPTY_MAP(395),
/*      */   
/* 3375 */   LEGACY_GOLDEN_CARROT(396),
/*      */   
/* 3377 */   LEGACY_SKULL_ITEM(397),
/*      */   
/* 3379 */   LEGACY_CARROT_STICK(398, 1, 25),
/*      */   
/* 3381 */   LEGACY_NETHER_STAR(399),
/*      */   
/* 3383 */   LEGACY_PUMPKIN_PIE(400),
/*      */   
/* 3385 */   LEGACY_FIREWORK(401),
/*      */   
/* 3387 */   LEGACY_FIREWORK_CHARGE(402),
/*      */   
/* 3389 */   LEGACY_ENCHANTED_BOOK(403, 1),
/*      */   
/* 3391 */   LEGACY_REDSTONE_COMPARATOR(404),
/*      */   
/* 3393 */   LEGACY_NETHER_BRICK_ITEM(405),
/*      */   
/* 3395 */   LEGACY_QUARTZ(406),
/*      */   
/* 3397 */   LEGACY_EXPLOSIVE_MINECART(407, 1),
/*      */   
/* 3399 */   LEGACY_HOPPER_MINECART(408, 1),
/*      */   
/* 3401 */   LEGACY_PRISMARINE_SHARD(409),
/*      */   
/* 3403 */   LEGACY_PRISMARINE_CRYSTALS(410),
/*      */   
/* 3405 */   LEGACY_RABBIT(411),
/*      */   
/* 3407 */   LEGACY_COOKED_RABBIT(412),
/*      */   
/* 3409 */   LEGACY_RABBIT_STEW(413, 1),
/*      */   
/* 3411 */   LEGACY_RABBIT_FOOT(414),
/*      */   
/* 3413 */   LEGACY_RABBIT_HIDE(415),
/*      */   
/* 3415 */   LEGACY_ARMOR_STAND(416, 16),
/*      */   
/* 3417 */   LEGACY_IRON_BARDING(417, 1),
/*      */   
/* 3419 */   LEGACY_GOLD_BARDING(418, 1),
/*      */   
/* 3421 */   LEGACY_DIAMOND_BARDING(419, 1),
/*      */   
/* 3423 */   LEGACY_LEASH(420),
/*      */   
/* 3425 */   LEGACY_NAME_TAG(421),
/*      */   
/* 3427 */   LEGACY_COMMAND_MINECART(422, 1),
/*      */   
/* 3429 */   LEGACY_MUTTON(423),
/*      */   
/* 3431 */   LEGACY_COOKED_MUTTON(424),
/*      */   
/* 3433 */   LEGACY_BANNER(425, 16),
/*      */   
/* 3435 */   LEGACY_END_CRYSTAL(426),
/*      */   
/* 3437 */   LEGACY_SPRUCE_DOOR_ITEM(427),
/*      */   
/* 3439 */   LEGACY_BIRCH_DOOR_ITEM(428),
/*      */   
/* 3441 */   LEGACY_JUNGLE_DOOR_ITEM(429),
/*      */   
/* 3443 */   LEGACY_ACACIA_DOOR_ITEM(430),
/*      */   
/* 3445 */   LEGACY_DARK_OAK_DOOR_ITEM(431),
/*      */   
/* 3447 */   LEGACY_CHORUS_FRUIT(432),
/*      */   
/* 3449 */   LEGACY_CHORUS_FRUIT_POPPED(433),
/*      */   
/* 3451 */   LEGACY_BEETROOT(434),
/*      */   
/* 3453 */   LEGACY_BEETROOT_SEEDS(435),
/*      */   
/* 3455 */   LEGACY_BEETROOT_SOUP(436, 1),
/*      */   
/* 3457 */   LEGACY_DRAGONS_BREATH(437),
/*      */   
/* 3459 */   LEGACY_SPLASH_POTION(438, 1),
/*      */   
/* 3461 */   LEGACY_SPECTRAL_ARROW(439),
/*      */   
/* 3463 */   LEGACY_TIPPED_ARROW(440),
/*      */   
/* 3465 */   LEGACY_LINGERING_POTION(441, 1),
/*      */   
/* 3467 */   LEGACY_SHIELD(442, 1, 336),
/*      */   
/* 3469 */   LEGACY_ELYTRA(443, 1, 431),
/*      */   
/* 3471 */   LEGACY_BOAT_SPRUCE(444, 1),
/*      */   
/* 3473 */   LEGACY_BOAT_BIRCH(445, 1),
/*      */   
/* 3475 */   LEGACY_BOAT_JUNGLE(446, 1),
/*      */   
/* 3477 */   LEGACY_BOAT_ACACIA(447, 1),
/*      */   
/* 3479 */   LEGACY_BOAT_DARK_OAK(448, 1),
/*      */   
/* 3481 */   LEGACY_TOTEM(449, 1),
/*      */   
/* 3483 */   LEGACY_SHULKER_SHELL(450),
/*      */   
/* 3485 */   LEGACY_IRON_NUGGET(452),
/*      */   
/* 3487 */   LEGACY_KNOWLEDGE_BOOK(453, 1),
/*      */   
/* 3489 */   LEGACY_GOLD_RECORD(2256, 1),
/*      */   
/* 3491 */   LEGACY_GREEN_RECORD(2257, 1),
/*      */   
/* 3493 */   LEGACY_RECORD_3(2258, 1),
/*      */   
/* 3495 */   LEGACY_RECORD_4(2259, 1),
/*      */   
/* 3497 */   LEGACY_RECORD_5(2260, 1),
/*      */   
/* 3499 */   LEGACY_RECORD_6(2261, 1),
/*      */   
/* 3501 */   LEGACY_RECORD_7(2262, 1),
/*      */   
/* 3503 */   LEGACY_RECORD_8(2263, 1),
/*      */   
/* 3505 */   LEGACY_RECORD_9(2264, 1),
/*      */   
/* 3507 */   LEGACY_RECORD_10(2265, 1),
/*      */   
/* 3509 */   LEGACY_RECORD_11(2266, 1),
/*      */   
/* 3511 */   LEGACY_RECORD_12(2267, 1); @Deprecated
/*      */   public static final String LEGACY_PREFIX = "LEGACY_"; private final int id;
/*      */   private final Constructor<? extends MaterialData> ctor;
/*      */   private static final Map<String, Material> BY_NAME;
/*      */   private final int maxStack;
/*      */   private final short durability;
/*      */   public final Class<?> data;
/*      */   private final boolean legacy;
/*      */   private final NamespacedKey key;
/*      */   
/* 3521 */   static { BY_NAME = Maps.newHashMap();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4667 */     for (Material material : values())
/* 4668 */       BY_NAME.put(material.name(), material);  }
/*      */   Material(int id, int stack, int durability, Class<?> data) { this.id = id; this.durability = (short)durability; this.maxStack = stack; this.data = data; this.legacy = name().startsWith("LEGACY_"); this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT)); try { if (MaterialData.class.isAssignableFrom(data)) { this.ctor = (Constructor)data.getConstructor(new Class[] { Material.class, byte.class }); } else { this.ctor = null; }  } catch (NoSuchMethodException ex) { throw new AssertionError(ex); } catch (SecurityException ex) { throw new AssertionError(ex); }  }
/*      */   public boolean isEmpty() { switch (this) { case AIR: case CAVE_AIR: case VOID_AIR: return true; }  return false; }
/*      */   @NotNull public String getTranslationKey() { return Bukkit.getUnsafe().getTranslationKey(this); }
/*      */   @Deprecated public int getId() { Validate.isTrue(this.legacy, "Cannot get ID of Modern Material"); return this.id; }
/*      */   @Deprecated public boolean isLegacy() { return this.legacy; }
/*      */   @NotNull public NamespacedKey getKey() { Validate.isTrue(!this.legacy, "Cannot get key of Legacy Material"); return this.key; }
/*      */   public int getMaxStackSize() { return this.maxStack; }
/* 4676 */   public short getMaxDurability() { return this.durability; } @NotNull public BlockData createBlockData() { return Bukkit.createBlockData(this); } @NotNull public BlockData createBlockData(@Nullable Consumer<BlockData> consumer) { return Bukkit.createBlockData(this, consumer); } @NotNull public BlockData createBlockData(@Nullable String data) throws IllegalArgumentException { return Bukkit.createBlockData(this, data); } @NotNull public Class<? extends MaterialData> getData() { Validate.isTrue(this.legacy, "Cannot get data class of Modern Material"); return this.ctor.getDeclaringClass(); } public boolean isRecord() { switch (this) {
/*      */ 
/*      */       
/*      */       case MUSIC_DISC_11:
/*      */       case MUSIC_DISC_13:
/*      */       case MUSIC_DISC_BLOCKS:
/*      */       case MUSIC_DISC_CAT:
/*      */       case MUSIC_DISC_CHIRP:
/*      */       case MUSIC_DISC_FAR:
/*      */       case MUSIC_DISC_MALL:
/*      */       case MUSIC_DISC_MELLOHI:
/*      */       case MUSIC_DISC_PIGSTEP:
/*      */       case MUSIC_DISC_STAL:
/*      */       case MUSIC_DISC_STRAD:
/*      */       case MUSIC_DISC_WAIT:
/*      */       case MUSIC_DISC_WARD:
/* 4692 */         return true;
/*      */     } 
/* 4694 */     return (this.id >= LEGACY_GOLD_RECORD.id && this.id <= LEGACY_RECORD_12.id); }
/*      */   @Deprecated @NotNull public MaterialData getNewData(byte raw) { Validate.isTrue(this.legacy, "Cannot get new data of Modern Material"); try { return this.ctor.newInstance(new Object[] { this, Byte.valueOf(raw) }); } catch (InstantiationException ex) { Throwable t = ex.getCause(); if (t instanceof RuntimeException)
/*      */         throw (RuntimeException)t;  if (t instanceof Error)
/*      */         throw (Error)t;  throw new AssertionError(t); } catch (Throwable t) { throw new AssertionError(t); }  }
/*      */   public boolean isBlock() { switch (this) { case AIR: case CAVE_AIR: case VOID_AIR: case ACACIA_BUTTON: case ACACIA_DOOR: case ACACIA_FENCE: case ACACIA_FENCE_GATE: case ACACIA_LEAVES: case ACACIA_LOG: case ACACIA_PLANKS: case ACACIA_PRESSURE_PLATE: case ACACIA_SAPLING: case ACACIA_SIGN: case ACACIA_SLAB: case ACACIA_STAIRS: case ACACIA_TRAPDOOR: case ACACIA_WALL_SIGN: case ACACIA_WOOD: case ACTIVATOR_RAIL: case ALLIUM: case ANCIENT_DEBRIS: case ANDESITE: case ANDESITE_SLAB: case ANDESITE_STAIRS: case ANDESITE_WALL: case ANVIL: case ATTACHED_MELON_STEM: case ATTACHED_PUMPKIN_STEM: case AZURE_BLUET: case BAMBOO: case BAMBOO_SAPLING: case BARREL: case BARRIER: case BASALT: case BEACON: case BEDROCK: case BEEHIVE: case BEETROOTS: case BEE_NEST: case BELL: case BIRCH_BUTTON: case BIRCH_DOOR: case BIRCH_FENCE: case BIRCH_FENCE_GATE: case BIRCH_LEAVES: case BIRCH_LOG: case BIRCH_PLANKS: case BIRCH_PRESSURE_PLATE: case BIRCH_SAPLING: case BIRCH_SIGN: case BIRCH_SLAB: case BIRCH_STAIRS: case BIRCH_TRAPDOOR: case BIRCH_WALL_SIGN: case BIRCH_WOOD: case BLACKSTONE: case BLACKSTONE_SLAB: case BLACKSTONE_STAIRS: case BLACKSTONE_WALL: case BLACK_BANNER: case BLACK_BED: case BLACK_CARPET: case BLACK_CONCRETE: case BLACK_CONCRETE_POWDER: case BLACK_GLAZED_TERRACOTTA: case BLACK_SHULKER_BOX: case BLACK_STAINED_GLASS: case BLACK_STAINED_GLASS_PANE: case BLACK_TERRACOTTA: case BLACK_WALL_BANNER: case BLACK_WOOL: case BLAST_FURNACE: case BLUE_BANNER: case BLUE_BED: case BLUE_CARPET: case BLUE_CONCRETE: case BLUE_CONCRETE_POWDER: case BLUE_GLAZED_TERRACOTTA: case BLUE_ICE: case BLUE_ORCHID: case BLUE_SHULKER_BOX: case BLUE_STAINED_GLASS: case BLUE_STAINED_GLASS_PANE: case BLUE_TERRACOTTA: case BLUE_WALL_BANNER: case BLUE_WOOL: case BONE_BLOCK: case BOOKSHELF: case BRAIN_CORAL: case BRAIN_CORAL_BLOCK: case BRAIN_CORAL_FAN: case BRAIN_CORAL_WALL_FAN: case BREWING_STAND: case BRICKS: case BRICK_SLAB: case BRICK_STAIRS: case BRICK_WALL: case BROWN_BANNER: case BROWN_BED: case BROWN_CARPET: case BROWN_CONCRETE: case BROWN_CONCRETE_POWDER: case BROWN_GLAZED_TERRACOTTA: case BROWN_MUSHROOM: case BROWN_MUSHROOM_BLOCK: case BROWN_SHULKER_BOX: case BROWN_STAINED_GLASS: case BROWN_STAINED_GLASS_PANE: case BROWN_TERRACOTTA: case BROWN_WALL_BANNER: case BROWN_WOOL: case BUBBLE_COLUMN: case BUBBLE_CORAL: case BUBBLE_CORAL_BLOCK: case BUBBLE_CORAL_FAN: case BUBBLE_CORAL_WALL_FAN: case CACTUS: case CAKE: case CAMPFIRE: case CARROTS: case CARTOGRAPHY_TABLE: case CARVED_PUMPKIN: case CAULDRON: case CHAIN: case CHAIN_COMMAND_BLOCK: case CHEST: case CHIPPED_ANVIL: case CHISELED_NETHER_BRICKS: case CHISELED_POLISHED_BLACKSTONE: case CHISELED_QUARTZ_BLOCK: case CHISELED_RED_SANDSTONE: case CHISELED_SANDSTONE: case CHISELED_STONE_BRICKS: case CHORUS_FLOWER: case CHORUS_PLANT: case CLAY: case COAL_BLOCK: case COAL_ORE: case COARSE_DIRT: case COBBLESTONE: case COBBLESTONE_SLAB: case COBBLESTONE_STAIRS: case COBBLESTONE_WALL: case COBWEB: case COCOA: case COMMAND_BLOCK: case COMPARATOR: case COMPOSTER: case CONDUIT: case CORNFLOWER: case CRACKED_NETHER_BRICKS: case CRACKED_POLISHED_BLACKSTONE_BRICKS: case CRACKED_STONE_BRICKS: case CRAFTING_TABLE: case CREEPER_HEAD: case CREEPER_WALL_HEAD: case CRIMSON_BUTTON: case CRIMSON_DOOR: case CRIMSON_FENCE: case CRIMSON_FENCE_GATE: case CRIMSON_FUNGUS: case CRIMSON_HYPHAE: case CRIMSON_NYLIUM: case CRIMSON_PLANKS: case CRIMSON_PRESSURE_PLATE: case CRIMSON_ROOTS: case CRIMSON_SIGN: case CRIMSON_SLAB: case CRIMSON_STAIRS: case CRIMSON_STEM: case CRIMSON_TRAPDOOR: case CRIMSON_WALL_SIGN: case CRYING_OBSIDIAN: case CUT_RED_SANDSTONE: case CUT_RED_SANDSTONE_SLAB: case CUT_SANDSTONE: case CUT_SANDSTONE_SLAB: case CYAN_BANNER: case CYAN_BED: case CYAN_CARPET: case CYAN_CONCRETE: case CYAN_CONCRETE_POWDER: case CYAN_GLAZED_TERRACOTTA: case CYAN_SHULKER_BOX: case CYAN_STAINED_GLASS: case CYAN_STAINED_GLASS_PANE: case CYAN_TERRACOTTA: case CYAN_WALL_BANNER: case CYAN_WOOL: case DAMAGED_ANVIL: case DANDELION: case DARK_OAK_BUTTON: case DARK_OAK_DOOR: case DARK_OAK_FENCE: case DARK_OAK_FENCE_GATE: case DARK_OAK_LEAVES: case DARK_OAK_LOG: case DARK_OAK_PLANKS: case DARK_OAK_PRESSURE_PLATE: case DARK_OAK_SAPLING: case DARK_OAK_SIGN: case DARK_OAK_SLAB: case DARK_OAK_STAIRS: case DARK_OAK_TRAPDOOR: case DARK_OAK_WALL_SIGN: case DARK_OAK_WOOD: case DARK_PRISMARINE: case DARK_PRISMARINE_SLAB: case DARK_PRISMARINE_STAIRS: case DAYLIGHT_DETECTOR: case DEAD_BRAIN_CORAL: case DEAD_BRAIN_CORAL_BLOCK: case DEAD_BRAIN_CORAL_FAN: case DEAD_BRAIN_CORAL_WALL_FAN: case DEAD_BUBBLE_CORAL: case DEAD_BUBBLE_CORAL_BLOCK: case DEAD_BUBBLE_CORAL_FAN: case DEAD_BUBBLE_CORAL_WALL_FAN: case DEAD_BUSH: case DEAD_FIRE_CORAL: case DEAD_FIRE_CORAL_BLOCK: case DEAD_FIRE_CORAL_FAN: case DEAD_FIRE_CORAL_WALL_FAN: case DEAD_HORN_CORAL: case DEAD_HORN_CORAL_BLOCK: case DEAD_HORN_CORAL_FAN: case DEAD_HORN_CORAL_WALL_FAN: case DEAD_TUBE_CORAL: case DEAD_TUBE_CORAL_BLOCK: case DEAD_TUBE_CORAL_FAN: case DEAD_TUBE_CORAL_WALL_FAN: case DETECTOR_RAIL: case DIAMOND_BLOCK: case DIAMOND_ORE: case DIORITE: case DIORITE_SLAB: case DIORITE_STAIRS: case DIORITE_WALL: case DIRT: case DISPENSER: case DRAGON_EGG: case DRAGON_HEAD: case DRAGON_WALL_HEAD: case DRIED_KELP_BLOCK: case DROPPER: case EMERALD_BLOCK: case EMERALD_ORE: case ENCHANTING_TABLE: case ENDER_CHEST: case END_GATEWAY: case END_PORTAL: case END_PORTAL_FRAME: case END_ROD: case END_STONE: case END_STONE_BRICKS: case END_STONE_BRICK_SLAB: case END_STONE_BRICK_STAIRS: case END_STONE_BRICK_WALL: case FARMLAND: case FERN: case FIRE: case FIRE_CORAL: case FIRE_CORAL_BLOCK: case FIRE_CORAL_FAN: case FIRE_CORAL_WALL_FAN: case FLETCHING_TABLE: case FLOWER_POT: case FROSTED_ICE: case FURNACE: case GILDED_BLACKSTONE: case GLASS: case GLASS_PANE: case GLOWSTONE: case GOLD_BLOCK: case GOLD_ORE: case GRANITE: case GRANITE_SLAB: case GRANITE_STAIRS: case GRANITE_WALL: case GRASS: case GRASS_BLOCK: case GRASS_PATH: case GRAVEL: case GRAY_BANNER: case GRAY_BED: case GRAY_CARPET: case GRAY_CONCRETE: case GRAY_CONCRETE_POWDER: case GRAY_GLAZED_TERRACOTTA: case GRAY_SHULKER_BOX: case GRAY_STAINED_GLASS: case GRAY_STAINED_GLASS_PANE: case GRAY_TERRACOTTA: case GRAY_WALL_BANNER: case GRAY_WOOL: case GREEN_BANNER: case GREEN_BED: case GREEN_CARPET: case GREEN_CONCRETE: case GREEN_CONCRETE_POWDER: case GREEN_GLAZED_TERRACOTTA: case GREEN_SHULKER_BOX: case GREEN_STAINED_GLASS: case GREEN_STAINED_GLASS_PANE: case GREEN_TERRACOTTA: case GREEN_WALL_BANNER: case GREEN_WOOL: case GRINDSTONE: case HAY_BLOCK: case HEAVY_WEIGHTED_PRESSURE_PLATE: case HONEYCOMB_BLOCK: case HONEY_BLOCK: case HOPPER: case HORN_CORAL: case HORN_CORAL_BLOCK: case HORN_CORAL_FAN: case HORN_CORAL_WALL_FAN: case ICE: case INFESTED_CHISELED_STONE_BRICKS: case INFESTED_COBBLESTONE: case INFESTED_CRACKED_STONE_BRICKS: case INFESTED_MOSSY_STONE_BRICKS: case INFESTED_STONE: case INFESTED_STONE_BRICKS: case IRON_BARS: case IRON_BLOCK: case IRON_DOOR: case IRON_ORE: case IRON_TRAPDOOR: case JACK_O_LANTERN: case JIGSAW: case JUKEBOX: case JUNGLE_BUTTON: case JUNGLE_DOOR: case JUNGLE_FENCE: case JUNGLE_FENCE_GATE: case JUNGLE_LEAVES: case JUNGLE_LOG: case JUNGLE_PLANKS: case JUNGLE_PRESSURE_PLATE: case JUNGLE_SAPLING: case JUNGLE_SIGN: case JUNGLE_SLAB: case JUNGLE_STAIRS: case JUNGLE_TRAPDOOR: case JUNGLE_WALL_SIGN: case JUNGLE_WOOD: case KELP: case KELP_PLANT: case LADDER: case LANTERN: case LAPIS_BLOCK: case LAPIS_ORE: case LARGE_FERN: case LAVA: case LECTERN: case LEVER: case LIGHT_BLUE_BANNER: case LIGHT_BLUE_BED: case LIGHT_BLUE_CARPET: case LIGHT_BLUE_CONCRETE: case LIGHT_BLUE_CONCRETE_POWDER: case LIGHT_BLUE_GLAZED_TERRACOTTA: case LIGHT_BLUE_SHULKER_BOX: case LIGHT_BLUE_STAINED_GLASS: case LIGHT_BLUE_STAINED_GLASS_PANE: case LIGHT_BLUE_TERRACOTTA: case LIGHT_BLUE_WALL_BANNER: case LIGHT_BLUE_WOOL: case LIGHT_GRAY_BANNER: case LIGHT_GRAY_BED: case LIGHT_GRAY_CARPET: case LIGHT_GRAY_CONCRETE: case LIGHT_GRAY_CONCRETE_POWDER: case LIGHT_GRAY_GLAZED_TERRACOTTA: case LIGHT_GRAY_SHULKER_BOX: case LIGHT_GRAY_STAINED_GLASS: case LIGHT_GRAY_STAINED_GLASS_PANE: case LIGHT_GRAY_TERRACOTTA: case LIGHT_GRAY_WALL_BANNER: case LIGHT_GRAY_WOOL: case LIGHT_WEIGHTED_PRESSURE_PLATE: case LILAC: case LILY_OF_THE_VALLEY: case LILY_PAD: case LIME_BANNER: case LIME_BED: case LIME_CARPET: case LIME_CONCRETE: case LIME_CONCRETE_POWDER: case LIME_GLAZED_TERRACOTTA: case LIME_SHULKER_BOX: case LIME_STAINED_GLASS: case LIME_STAINED_GLASS_PANE: case LIME_TERRACOTTA: case LIME_WALL_BANNER: case LIME_WOOL: case LODESTONE: case LOOM: case MAGENTA_BANNER: case MAGENTA_BED: case MAGENTA_CARPET: case MAGENTA_CONCRETE: case MAGENTA_CONCRETE_POWDER: case MAGENTA_GLAZED_TERRACOTTA: case MAGENTA_SHULKER_BOX: case MAGENTA_STAINED_GLASS: case MAGENTA_STAINED_GLASS_PANE: case MAGENTA_TERRACOTTA: case MAGENTA_WALL_BANNER: case MAGENTA_WOOL: case MAGMA_BLOCK: case MELON: case MELON_STEM: case MOSSY_COBBLESTONE: case MOSSY_COBBLESTONE_SLAB: case MOSSY_COBBLESTONE_STAIRS: case MOSSY_COBBLESTONE_WALL: case MOSSY_STONE_BRICKS: case MOSSY_STONE_BRICK_SLAB: case MOSSY_STONE_BRICK_STAIRS: case MOSSY_STONE_BRICK_WALL: case MOVING_PISTON: case MUSHROOM_STEM: case MYCELIUM: case NETHERITE_BLOCK: case NETHERRACK: case NETHER_BRICKS: case NETHER_BRICK_FENCE: case NETHER_BRICK_SLAB: case NETHER_BRICK_STAIRS: case NETHER_BRICK_WALL: case NETHER_GOLD_ORE: case NETHER_PORTAL: case NETHER_QUARTZ_ORE: case NETHER_SPROUTS: case NETHER_WART: case NETHER_WART_BLOCK: case NOTE_BLOCK: case OAK_BUTTON: case OAK_DOOR: case OAK_FENCE: case OAK_FENCE_GATE: case OAK_LEAVES: case OAK_LOG: case OAK_PLANKS: case OAK_PRESSURE_PLATE: case OAK_SAPLING: case OAK_SIGN: case OAK_SLAB: case OAK_STAIRS: case OAK_TRAPDOOR: case OAK_WALL_SIGN: case OAK_WOOD: case OBSERVER: case OBSIDIAN: case ORANGE_BANNER: case ORANGE_BED: case ORANGE_CARPET: case ORANGE_CONCRETE: case ORANGE_CONCRETE_POWDER: case ORANGE_GLAZED_TERRACOTTA: case ORANGE_SHULKER_BOX: case ORANGE_STAINED_GLASS: case ORANGE_STAINED_GLASS_PANE: case ORANGE_TERRACOTTA: case ORANGE_TULIP: case ORANGE_WALL_BANNER: case ORANGE_WOOL: case OXEYE_DAISY: case PACKED_ICE: case PEONY: case PETRIFIED_OAK_SLAB: case PINK_BANNER: case PINK_BED: case PINK_CARPET: case PINK_CONCRETE: case PINK_CONCRETE_POWDER: case PINK_GLAZED_TERRACOTTA: case PINK_SHULKER_BOX: case PINK_STAINED_GLASS: case PINK_STAINED_GLASS_PANE: case PINK_TERRACOTTA: case PINK_TULIP: case PINK_WALL_BANNER: case PINK_WOOL: case PISTON: case PISTON_HEAD: case PLAYER_HEAD: case PLAYER_WALL_HEAD: case PODZOL: case POLISHED_ANDESITE: case POLISHED_ANDESITE_SLAB: case POLISHED_ANDESITE_STAIRS: case POLISHED_BASALT: case POLISHED_BLACKSTONE: case POLISHED_BLACKSTONE_BRICKS: case POLISHED_BLACKSTONE_BRICK_SLAB: case POLISHED_BLACKSTONE_BRICK_STAIRS: case POLISHED_BLACKSTONE_BRICK_WALL: case POLISHED_BLACKSTONE_BUTTON: case POLISHED_BLACKSTONE_PRESSURE_PLATE: case POLISHED_BLACKSTONE_SLAB: case POLISHED_BLACKSTONE_STAIRS: case POLISHED_BLACKSTONE_WALL: case POLISHED_DIORITE: case POLISHED_DIORITE_SLAB: case POLISHED_DIORITE_STAIRS: case POLISHED_GRANITE: case POLISHED_GRANITE_SLAB: case POLISHED_GRANITE_STAIRS: case POPPY: case POTATOES: case POTTED_ACACIA_SAPLING: case POTTED_ALLIUM: case POTTED_AZURE_BLUET: case POTTED_BAMBOO: case POTTED_BIRCH_SAPLING: case POTTED_BLUE_ORCHID: case POTTED_BROWN_MUSHROOM: case POTTED_CACTUS: case POTTED_CORNFLOWER: case POTTED_CRIMSON_FUNGUS: case POTTED_CRIMSON_ROOTS: case POTTED_DANDELION: case POTTED_DARK_OAK_SAPLING: case POTTED_DEAD_BUSH: case POTTED_FERN: case POTTED_JUNGLE_SAPLING: case POTTED_LILY_OF_THE_VALLEY: case POTTED_OAK_SAPLING: case POTTED_ORANGE_TULIP: case POTTED_OXEYE_DAISY: case POTTED_PINK_TULIP: case POTTED_POPPY: case POTTED_RED_MUSHROOM: case POTTED_RED_TULIP: case POTTED_SPRUCE_SAPLING: case POTTED_WARPED_FUNGUS: case POTTED_WARPED_ROOTS: case POTTED_WHITE_TULIP: case POTTED_WITHER_ROSE: case POWERED_RAIL: case PRISMARINE: case PRISMARINE_BRICKS: case PRISMARINE_BRICK_SLAB: case PRISMARINE_BRICK_STAIRS: case PRISMARINE_SLAB: case PRISMARINE_STAIRS: case PRISMARINE_WALL: case PUMPKIN: case PUMPKIN_STEM: case PURPLE_BANNER: case PURPLE_BED: case PURPLE_CARPET: case PURPLE_CONCRETE: case PURPLE_CONCRETE_POWDER: case PURPLE_GLAZED_TERRACOTTA: case PURPLE_SHULKER_BOX: case PURPLE_STAINED_GLASS: case PURPLE_STAINED_GLASS_PANE: case PURPLE_TERRACOTTA: case PURPLE_WALL_BANNER: case PURPLE_WOOL: case PURPUR_BLOCK: case PURPUR_PILLAR: case PURPUR_SLAB: case PURPUR_STAIRS: case QUARTZ_BLOCK: case QUARTZ_BRICKS: case QUARTZ_PILLAR: case QUARTZ_SLAB: case QUARTZ_STAIRS: case RAIL: case REDSTONE_BLOCK: case REDSTONE_LAMP: case REDSTONE_ORE: case REDSTONE_TORCH: case REDSTONE_WALL_TORCH: case REDSTONE_WIRE: case RED_BANNER: case RED_BED: case RED_CARPET: case RED_CONCRETE: case RED_CONCRETE_POWDER: case RED_GLAZED_TERRACOTTA: case RED_MUSHROOM: case RED_MUSHROOM_BLOCK: case RED_NETHER_BRICKS: case RED_NETHER_BRICK_SLAB: case RED_NETHER_BRICK_STAIRS: case RED_NETHER_BRICK_WALL: case RED_SAND: case RED_SANDSTONE: case RED_SANDSTONE_SLAB: case RED_SANDSTONE_STAIRS: case RED_SANDSTONE_WALL: case RED_SHULKER_BOX: case RED_STAINED_GLASS: case RED_STAINED_GLASS_PANE: case RED_TERRACOTTA: case RED_TULIP: case RED_WALL_BANNER: case RED_WOOL: case REPEATER: case REPEATING_COMMAND_BLOCK: case RESPAWN_ANCHOR: case ROSE_BUSH: case SAND: case SANDSTONE: case SANDSTONE_SLAB: case SANDSTONE_STAIRS: case SANDSTONE_WALL: case SCAFFOLDING: case SEAGRASS: case SEA_LANTERN: case SEA_PICKLE: case SHROOMLIGHT: case SHULKER_BOX: case SKELETON_SKULL: case SKELETON_WALL_SKULL: case SLIME_BLOCK: case SMITHING_TABLE: case SMOKER: case SMOOTH_QUARTZ: case SMOOTH_QUARTZ_SLAB: case SMOOTH_QUARTZ_STAIRS: case SMOOTH_RED_SANDSTONE: case SMOOTH_RED_SANDSTONE_SLAB: case SMOOTH_RED_SANDSTONE_STAIRS: case SMOOTH_SANDSTONE: case SMOOTH_SANDSTONE_SLAB: case SMOOTH_SANDSTONE_STAIRS: case SMOOTH_STONE: case SMOOTH_STONE_SLAB: case SNOW: case SNOW_BLOCK: case SOUL_CAMPFIRE: case SOUL_FIRE: case SOUL_LANTERN: case SOUL_SAND: case SOUL_SOIL: case SOUL_TORCH: case SOUL_WALL_TORCH: case SPAWNER: case SPONGE: case SPRUCE_BUTTON: case SPRUCE_DOOR: case SPRUCE_FENCE: case SPRUCE_FENCE_GATE: case SPRUCE_LEAVES: case SPRUCE_LOG: case SPRUCE_PLANKS: case SPRUCE_PRESSURE_PLATE: case SPRUCE_SAPLING: case SPRUCE_SIGN: case SPRUCE_SLAB: case SPRUCE_STAIRS: case SPRUCE_TRAPDOOR: case SPRUCE_WALL_SIGN: case SPRUCE_WOOD: case STICKY_PISTON: case STONE: case STONECUTTER: case STONE_BRICKS: case STONE_BRICK_SLAB: case STONE_BRICK_STAIRS: case STONE_BRICK_WALL: case STONE_BUTTON: case STONE_PRESSURE_PLATE: case STONE_SLAB: case STONE_STAIRS: case STRIPPED_ACACIA_LOG: case STRIPPED_ACACIA_WOOD: case STRIPPED_BIRCH_LOG: case STRIPPED_BIRCH_WOOD: case STRIPPED_CRIMSON_HYPHAE: case STRIPPED_CRIMSON_STEM: case STRIPPED_DARK_OAK_LOG: case STRIPPED_DARK_OAK_WOOD: case STRIPPED_JUNGLE_LOG: case STRIPPED_JUNGLE_WOOD: case STRIPPED_OAK_LOG: case STRIPPED_OAK_WOOD: case STRIPPED_SPRUCE_LOG: case STRIPPED_SPRUCE_WOOD: case STRIPPED_WARPED_HYPHAE: case STRIPPED_WARPED_STEM: case STRUCTURE_BLOCK: case STRUCTURE_VOID: case SUGAR_CANE: case SUNFLOWER: case SWEET_BERRY_BUSH: case TALL_GRASS: case TALL_SEAGRASS: case TARGET: case TERRACOTTA: case TNT: case TORCH: case TRAPPED_CHEST: case TRIPWIRE: case TRIPWIRE_HOOK: case TUBE_CORAL: case TUBE_CORAL_BLOCK: case TUBE_CORAL_FAN: case TUBE_CORAL_WALL_FAN: case TURTLE_EGG: case TWISTING_VINES: case TWISTING_VINES_PLANT: case VINE: case WALL_TORCH: case WARPED_BUTTON: case WARPED_DOOR: case WARPED_FENCE: case WARPED_FENCE_GATE: case WARPED_FUNGUS: case WARPED_HYPHAE: case WARPED_NYLIUM: case WARPED_PLANKS: case WARPED_PRESSURE_PLATE: case WARPED_ROOTS: case WARPED_SIGN: case WARPED_SLAB: case WARPED_STAIRS: case WARPED_STEM: case WARPED_TRAPDOOR: case WARPED_WALL_SIGN: case WARPED_WART_BLOCK: case WATER: case WEEPING_VINES: case WEEPING_VINES_PLANT: case WET_SPONGE: case WHEAT: case WHITE_BANNER: case WHITE_BED: case WHITE_CARPET: case WHITE_CONCRETE: case WHITE_CONCRETE_POWDER: case WHITE_GLAZED_TERRACOTTA: case WHITE_SHULKER_BOX: case WHITE_STAINED_GLASS: case WHITE_STAINED_GLASS_PANE: case WHITE_TERRACOTTA: case WHITE_TULIP: case WHITE_WALL_BANNER: case WHITE_WOOL: case WITHER_ROSE: case WITHER_SKELETON_SKULL: case WITHER_SKELETON_WALL_SKULL: case YELLOW_BANNER: case YELLOW_BED: case YELLOW_CARPET: case YELLOW_CONCRETE: case YELLOW_CONCRETE_POWDER: case YELLOW_GLAZED_TERRACOTTA: case YELLOW_SHULKER_BOX: case YELLOW_STAINED_GLASS: case YELLOW_STAINED_GLASS_PANE:
/*      */       case YELLOW_TERRACOTTA:
/*      */       case YELLOW_WALL_BANNER:
/*      */       case YELLOW_WOOL:
/*      */       case ZOMBIE_HEAD:
/*      */       case ZOMBIE_WALL_HEAD:
/* 4704 */         return true; }  return (0 <= this.id && this.id < 256); } public boolean isSolid() { if (!isBlock() || this.id == 0) {
/* 4705 */       return false;
/*      */     }
/* 4707 */     switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case ACACIA_DOOR:
/*      */       case ACACIA_FENCE:
/*      */       case ACACIA_FENCE_GATE:
/*      */       case ACACIA_LEAVES:
/*      */       case ACACIA_LOG:
/*      */       case ACACIA_PLANKS:
/*      */       case ACACIA_PRESSURE_PLATE:
/*      */       case ACACIA_SIGN:
/*      */       case ACACIA_SLAB:
/*      */       case ACACIA_STAIRS:
/*      */       case ACACIA_TRAPDOOR:
/*      */       case ACACIA_WALL_SIGN:
/*      */       case ACACIA_WOOD:
/*      */       case ANCIENT_DEBRIS:
/*      */       case ANDESITE:
/*      */       case ANDESITE_SLAB:
/*      */       case ANDESITE_STAIRS:
/*      */       case ANDESITE_WALL:
/*      */       case ANVIL:
/*      */       case BAMBOO:
/*      */       case BARREL:
/*      */       case BARRIER:
/*      */       case BASALT:
/*      */       case BEACON:
/*      */       case BEDROCK:
/*      */       case BEEHIVE:
/*      */       case BEE_NEST:
/*      */       case BELL:
/*      */       case BIRCH_DOOR:
/*      */       case BIRCH_FENCE:
/*      */       case BIRCH_FENCE_GATE:
/*      */       case BIRCH_LEAVES:
/*      */       case BIRCH_LOG:
/*      */       case BIRCH_PLANKS:
/*      */       case BIRCH_PRESSURE_PLATE:
/*      */       case BIRCH_SIGN:
/*      */       case BIRCH_SLAB:
/*      */       case BIRCH_STAIRS:
/*      */       case BIRCH_TRAPDOOR:
/*      */       case BIRCH_WALL_SIGN:
/*      */       case BIRCH_WOOD:
/*      */       case BLACKSTONE:
/*      */       case BLACKSTONE_SLAB:
/*      */       case BLACKSTONE_STAIRS:
/*      */       case BLACKSTONE_WALL:
/*      */       case BLACK_BANNER:
/*      */       case BLACK_BED:
/*      */       case BLACK_CONCRETE:
/*      */       case BLACK_CONCRETE_POWDER:
/*      */       case BLACK_GLAZED_TERRACOTTA:
/*      */       case BLACK_SHULKER_BOX:
/*      */       case BLACK_STAINED_GLASS:
/*      */       case BLACK_STAINED_GLASS_PANE:
/*      */       case BLACK_TERRACOTTA:
/*      */       case BLACK_WALL_BANNER:
/*      */       case BLACK_WOOL:
/*      */       case BLAST_FURNACE:
/*      */       case BLUE_BANNER:
/*      */       case BLUE_BED:
/*      */       case BLUE_CONCRETE:
/*      */       case BLUE_CONCRETE_POWDER:
/*      */       case BLUE_GLAZED_TERRACOTTA:
/*      */       case BLUE_ICE:
/*      */       case BLUE_SHULKER_BOX:
/*      */       case BLUE_STAINED_GLASS:
/*      */       case BLUE_STAINED_GLASS_PANE:
/*      */       case BLUE_TERRACOTTA:
/*      */       case BLUE_WALL_BANNER:
/*      */       case BLUE_WOOL:
/*      */       case BONE_BLOCK:
/*      */       case BOOKSHELF:
/*      */       case BRAIN_CORAL_BLOCK:
/*      */       case BREWING_STAND:
/*      */       case BRICKS:
/*      */       case BRICK_SLAB:
/*      */       case BRICK_STAIRS:
/*      */       case BRICK_WALL:
/*      */       case BROWN_BANNER:
/*      */       case BROWN_BED:
/*      */       case BROWN_CONCRETE:
/*      */       case BROWN_CONCRETE_POWDER:
/*      */       case BROWN_GLAZED_TERRACOTTA:
/*      */       case BROWN_MUSHROOM_BLOCK:
/*      */       case BROWN_SHULKER_BOX:
/*      */       case BROWN_STAINED_GLASS:
/*      */       case BROWN_STAINED_GLASS_PANE:
/*      */       case BROWN_TERRACOTTA:
/*      */       case BROWN_WALL_BANNER:
/*      */       case BROWN_WOOL:
/*      */       case BUBBLE_CORAL_BLOCK:
/*      */       case CACTUS:
/*      */       case CAKE:
/*      */       case CAMPFIRE:
/*      */       case CARTOGRAPHY_TABLE:
/*      */       case CARVED_PUMPKIN:
/*      */       case CAULDRON:
/*      */       case CHAIN:
/*      */       case CHAIN_COMMAND_BLOCK:
/*      */       case CHEST:
/*      */       case CHIPPED_ANVIL:
/*      */       case CHISELED_NETHER_BRICKS:
/*      */       case CHISELED_POLISHED_BLACKSTONE:
/*      */       case CHISELED_QUARTZ_BLOCK:
/*      */       case CHISELED_RED_SANDSTONE:
/*      */       case CHISELED_SANDSTONE:
/*      */       case CHISELED_STONE_BRICKS:
/*      */       case CLAY:
/*      */       case COAL_BLOCK:
/*      */       case COAL_ORE:
/*      */       case COARSE_DIRT:
/*      */       case COBBLESTONE:
/*      */       case COBBLESTONE_SLAB:
/*      */       case COBBLESTONE_STAIRS:
/*      */       case COBBLESTONE_WALL:
/*      */       case COMMAND_BLOCK:
/*      */       case COMPOSTER:
/*      */       case CONDUIT:
/*      */       case CRACKED_NETHER_BRICKS:
/*      */       case CRACKED_POLISHED_BLACKSTONE_BRICKS:
/*      */       case CRACKED_STONE_BRICKS:
/*      */       case CRAFTING_TABLE:
/*      */       case CRIMSON_DOOR:
/*      */       case CRIMSON_FENCE:
/*      */       case CRIMSON_FENCE_GATE:
/*      */       case CRIMSON_HYPHAE:
/*      */       case CRIMSON_NYLIUM:
/*      */       case CRIMSON_PLANKS:
/*      */       case CRIMSON_PRESSURE_PLATE:
/*      */       case CRIMSON_SIGN:
/*      */       case CRIMSON_SLAB:
/*      */       case CRIMSON_STAIRS:
/*      */       case CRIMSON_STEM:
/*      */       case CRIMSON_TRAPDOOR:
/*      */       case CRIMSON_WALL_SIGN:
/*      */       case CRYING_OBSIDIAN:
/*      */       case CUT_RED_SANDSTONE:
/*      */       case CUT_RED_SANDSTONE_SLAB:
/*      */       case CUT_SANDSTONE:
/*      */       case CUT_SANDSTONE_SLAB:
/*      */       case CYAN_BANNER:
/*      */       case CYAN_BED:
/*      */       case CYAN_CONCRETE:
/*      */       case CYAN_CONCRETE_POWDER:
/*      */       case CYAN_GLAZED_TERRACOTTA:
/*      */       case CYAN_SHULKER_BOX:
/*      */       case CYAN_STAINED_GLASS:
/*      */       case CYAN_STAINED_GLASS_PANE:
/*      */       case CYAN_TERRACOTTA:
/*      */       case CYAN_WALL_BANNER:
/*      */       case CYAN_WOOL:
/*      */       case DAMAGED_ANVIL:
/*      */       case DARK_OAK_DOOR:
/*      */       case DARK_OAK_FENCE:
/*      */       case DARK_OAK_FENCE_GATE:
/*      */       case DARK_OAK_LEAVES:
/*      */       case DARK_OAK_LOG:
/*      */       case DARK_OAK_PLANKS:
/*      */       case DARK_OAK_PRESSURE_PLATE:
/*      */       case DARK_OAK_SIGN:
/*      */       case DARK_OAK_SLAB:
/*      */       case DARK_OAK_STAIRS:
/*      */       case DARK_OAK_TRAPDOOR:
/*      */       case DARK_OAK_WALL_SIGN:
/*      */       case DARK_OAK_WOOD:
/*      */       case DARK_PRISMARINE:
/*      */       case DARK_PRISMARINE_SLAB:
/*      */       case DARK_PRISMARINE_STAIRS:
/*      */       case DAYLIGHT_DETECTOR:
/*      */       case DEAD_BRAIN_CORAL:
/*      */       case DEAD_BRAIN_CORAL_BLOCK:
/*      */       case DEAD_BRAIN_CORAL_FAN:
/*      */       case DEAD_BRAIN_CORAL_WALL_FAN:
/*      */       case DEAD_BUBBLE_CORAL:
/*      */       case DEAD_BUBBLE_CORAL_BLOCK:
/*      */       case DEAD_BUBBLE_CORAL_FAN:
/*      */       case DEAD_BUBBLE_CORAL_WALL_FAN:
/*      */       case DEAD_FIRE_CORAL:
/*      */       case DEAD_FIRE_CORAL_BLOCK:
/*      */       case DEAD_FIRE_CORAL_FAN:
/*      */       case DEAD_FIRE_CORAL_WALL_FAN:
/*      */       case DEAD_HORN_CORAL:
/*      */       case DEAD_HORN_CORAL_BLOCK:
/*      */       case DEAD_HORN_CORAL_FAN:
/*      */       case DEAD_HORN_CORAL_WALL_FAN:
/*      */       case DEAD_TUBE_CORAL:
/*      */       case DEAD_TUBE_CORAL_BLOCK:
/*      */       case DEAD_TUBE_CORAL_FAN:
/*      */       case DEAD_TUBE_CORAL_WALL_FAN:
/*      */       case DIAMOND_BLOCK:
/*      */       case DIAMOND_ORE:
/*      */       case DIORITE:
/*      */       case DIORITE_SLAB:
/*      */       case DIORITE_STAIRS:
/*      */       case DIORITE_WALL:
/*      */       case DIRT:
/*      */       case DISPENSER:
/*      */       case DRAGON_EGG:
/*      */       case DRIED_KELP_BLOCK:
/*      */       case DROPPER:
/*      */       case EMERALD_BLOCK:
/*      */       case EMERALD_ORE:
/*      */       case ENCHANTING_TABLE:
/*      */       case ENDER_CHEST:
/*      */       case END_PORTAL_FRAME:
/*      */       case END_STONE:
/*      */       case END_STONE_BRICKS:
/*      */       case END_STONE_BRICK_SLAB:
/*      */       case END_STONE_BRICK_STAIRS:
/*      */       case END_STONE_BRICK_WALL:
/*      */       case FARMLAND:
/*      */       case FIRE_CORAL_BLOCK:
/*      */       case FLETCHING_TABLE:
/*      */       case FROSTED_ICE:
/*      */       case FURNACE:
/*      */       case GILDED_BLACKSTONE:
/*      */       case GLASS:
/*      */       case GLASS_PANE:
/*      */       case GLOWSTONE:
/*      */       case GOLD_BLOCK:
/*      */       case GOLD_ORE:
/*      */       case GRANITE:
/*      */       case GRANITE_SLAB:
/*      */       case GRANITE_STAIRS:
/*      */       case GRANITE_WALL:
/*      */       case GRASS_BLOCK:
/*      */       case GRASS_PATH:
/*      */       case GRAVEL:
/*      */       case GRAY_BANNER:
/*      */       case GRAY_BED:
/*      */       case GRAY_CONCRETE:
/*      */       case GRAY_CONCRETE_POWDER:
/*      */       case GRAY_GLAZED_TERRACOTTA:
/*      */       case GRAY_SHULKER_BOX:
/*      */       case GRAY_STAINED_GLASS:
/*      */       case GRAY_STAINED_GLASS_PANE:
/*      */       case GRAY_TERRACOTTA:
/*      */       case GRAY_WALL_BANNER:
/*      */       case GRAY_WOOL:
/*      */       case GREEN_BANNER:
/*      */       case GREEN_BED:
/*      */       case GREEN_CONCRETE:
/*      */       case GREEN_CONCRETE_POWDER:
/*      */       case GREEN_GLAZED_TERRACOTTA:
/*      */       case GREEN_SHULKER_BOX:
/*      */       case GREEN_STAINED_GLASS:
/*      */       case GREEN_STAINED_GLASS_PANE:
/*      */       case GREEN_TERRACOTTA:
/*      */       case GREEN_WALL_BANNER:
/*      */       case GREEN_WOOL:
/*      */       case GRINDSTONE:
/*      */       case HAY_BLOCK:
/*      */       case HEAVY_WEIGHTED_PRESSURE_PLATE:
/*      */       case HONEYCOMB_BLOCK:
/*      */       case HONEY_BLOCK:
/*      */       case HOPPER:
/*      */       case HORN_CORAL_BLOCK:
/*      */       case ICE:
/*      */       case INFESTED_CHISELED_STONE_BRICKS:
/*      */       case INFESTED_COBBLESTONE:
/*      */       case INFESTED_CRACKED_STONE_BRICKS:
/*      */       case INFESTED_MOSSY_STONE_BRICKS:
/*      */       case INFESTED_STONE:
/*      */       case INFESTED_STONE_BRICKS:
/*      */       case IRON_BARS:
/*      */       case IRON_BLOCK:
/*      */       case IRON_DOOR:
/*      */       case IRON_ORE:
/*      */       case IRON_TRAPDOOR:
/*      */       case JACK_O_LANTERN:
/*      */       case JIGSAW:
/*      */       case JUKEBOX:
/*      */       case JUNGLE_DOOR:
/*      */       case JUNGLE_FENCE:
/*      */       case JUNGLE_FENCE_GATE:
/*      */       case JUNGLE_LEAVES:
/*      */       case JUNGLE_LOG:
/*      */       case JUNGLE_PLANKS:
/*      */       case JUNGLE_PRESSURE_PLATE:
/*      */       case JUNGLE_SIGN:
/*      */       case JUNGLE_SLAB:
/*      */       case JUNGLE_STAIRS:
/*      */       case JUNGLE_TRAPDOOR:
/*      */       case JUNGLE_WALL_SIGN:
/*      */       case JUNGLE_WOOD:
/*      */       case LANTERN:
/*      */       case LAPIS_BLOCK:
/*      */       case LAPIS_ORE:
/*      */       case LECTERN:
/*      */       case LIGHT_BLUE_BANNER:
/*      */       case LIGHT_BLUE_BED:
/*      */       case LIGHT_BLUE_CONCRETE:
/*      */       case LIGHT_BLUE_CONCRETE_POWDER:
/*      */       case LIGHT_BLUE_GLAZED_TERRACOTTA:
/*      */       case LIGHT_BLUE_SHULKER_BOX:
/*      */       case LIGHT_BLUE_STAINED_GLASS:
/*      */       case LIGHT_BLUE_STAINED_GLASS_PANE:
/*      */       case LIGHT_BLUE_TERRACOTTA:
/*      */       case LIGHT_BLUE_WALL_BANNER:
/*      */       case LIGHT_BLUE_WOOL:
/*      */       case LIGHT_GRAY_BANNER:
/*      */       case LIGHT_GRAY_BED:
/*      */       case LIGHT_GRAY_CONCRETE:
/*      */       case LIGHT_GRAY_CONCRETE_POWDER:
/*      */       case LIGHT_GRAY_GLAZED_TERRACOTTA:
/*      */       case LIGHT_GRAY_SHULKER_BOX:
/*      */       case LIGHT_GRAY_STAINED_GLASS:
/*      */       case LIGHT_GRAY_STAINED_GLASS_PANE:
/*      */       case LIGHT_GRAY_TERRACOTTA:
/*      */       case LIGHT_GRAY_WALL_BANNER:
/*      */       case LIGHT_GRAY_WOOL:
/*      */       case LIGHT_WEIGHTED_PRESSURE_PLATE:
/*      */       case LIME_BANNER:
/*      */       case LIME_BED:
/*      */       case LIME_CONCRETE:
/*      */       case LIME_CONCRETE_POWDER:
/*      */       case LIME_GLAZED_TERRACOTTA:
/*      */       case LIME_SHULKER_BOX:
/*      */       case LIME_STAINED_GLASS:
/*      */       case LIME_STAINED_GLASS_PANE:
/*      */       case LIME_TERRACOTTA:
/*      */       case LIME_WALL_BANNER:
/*      */       case LIME_WOOL:
/*      */       case LODESTONE:
/*      */       case LOOM:
/*      */       case MAGENTA_BANNER:
/*      */       case MAGENTA_BED:
/*      */       case MAGENTA_CONCRETE:
/*      */       case MAGENTA_CONCRETE_POWDER:
/*      */       case MAGENTA_GLAZED_TERRACOTTA:
/*      */       case MAGENTA_SHULKER_BOX:
/*      */       case MAGENTA_STAINED_GLASS:
/*      */       case MAGENTA_STAINED_GLASS_PANE:
/*      */       case MAGENTA_TERRACOTTA:
/*      */       case MAGENTA_WALL_BANNER:
/*      */       case MAGENTA_WOOL:
/*      */       case MAGMA_BLOCK:
/*      */       case MELON:
/*      */       case MOSSY_COBBLESTONE:
/*      */       case MOSSY_COBBLESTONE_SLAB:
/*      */       case MOSSY_COBBLESTONE_STAIRS:
/*      */       case MOSSY_COBBLESTONE_WALL:
/*      */       case MOSSY_STONE_BRICKS:
/*      */       case MOSSY_STONE_BRICK_SLAB:
/*      */       case MOSSY_STONE_BRICK_STAIRS:
/*      */       case MOSSY_STONE_BRICK_WALL:
/*      */       case MOVING_PISTON:
/*      */       case MUSHROOM_STEM:
/*      */       case MYCELIUM:
/*      */       case NETHERITE_BLOCK:
/*      */       case NETHERRACK:
/*      */       case NETHER_BRICKS:
/*      */       case NETHER_BRICK_FENCE:
/*      */       case NETHER_BRICK_SLAB:
/*      */       case NETHER_BRICK_STAIRS:
/*      */       case NETHER_BRICK_WALL:
/*      */       case NETHER_GOLD_ORE:
/*      */       case NETHER_QUARTZ_ORE:
/*      */       case NETHER_WART_BLOCK:
/*      */       case NOTE_BLOCK:
/*      */       case OAK_DOOR:
/*      */       case OAK_FENCE:
/*      */       case OAK_FENCE_GATE:
/*      */       case OAK_LEAVES:
/*      */       case OAK_LOG:
/*      */       case OAK_PLANKS:
/*      */       case OAK_PRESSURE_PLATE:
/*      */       case OAK_SIGN:
/*      */       case OAK_SLAB:
/*      */       case OAK_STAIRS:
/*      */       case OAK_TRAPDOOR:
/*      */       case OAK_WALL_SIGN:
/*      */       case OAK_WOOD:
/*      */       case OBSERVER:
/*      */       case OBSIDIAN:
/*      */       case ORANGE_BANNER:
/*      */       case ORANGE_BED:
/*      */       case ORANGE_CONCRETE:
/*      */       case ORANGE_CONCRETE_POWDER:
/*      */       case ORANGE_GLAZED_TERRACOTTA:
/*      */       case ORANGE_SHULKER_BOX:
/*      */       case ORANGE_STAINED_GLASS:
/*      */       case ORANGE_STAINED_GLASS_PANE:
/*      */       case ORANGE_TERRACOTTA:
/*      */       case ORANGE_WALL_BANNER:
/*      */       case ORANGE_WOOL:
/*      */       case PACKED_ICE:
/*      */       case PETRIFIED_OAK_SLAB:
/*      */       case PINK_BANNER:
/*      */       case PINK_BED:
/*      */       case PINK_CONCRETE:
/*      */       case PINK_CONCRETE_POWDER:
/*      */       case PINK_GLAZED_TERRACOTTA:
/*      */       case PINK_SHULKER_BOX:
/*      */       case PINK_STAINED_GLASS:
/*      */       case PINK_STAINED_GLASS_PANE:
/*      */       case PINK_TERRACOTTA:
/*      */       case PINK_WALL_BANNER:
/*      */       case PINK_WOOL:
/*      */       case PISTON:
/*      */       case PISTON_HEAD:
/*      */       case PODZOL:
/*      */       case POLISHED_ANDESITE:
/*      */       case POLISHED_ANDESITE_SLAB:
/*      */       case POLISHED_ANDESITE_STAIRS:
/*      */       case POLISHED_BASALT:
/*      */       case POLISHED_BLACKSTONE:
/*      */       case POLISHED_BLACKSTONE_BRICKS:
/*      */       case POLISHED_BLACKSTONE_BRICK_SLAB:
/*      */       case POLISHED_BLACKSTONE_BRICK_STAIRS:
/*      */       case POLISHED_BLACKSTONE_BRICK_WALL:
/*      */       case POLISHED_BLACKSTONE_PRESSURE_PLATE:
/*      */       case POLISHED_BLACKSTONE_SLAB:
/*      */       case POLISHED_BLACKSTONE_STAIRS:
/*      */       case POLISHED_BLACKSTONE_WALL:
/*      */       case POLISHED_DIORITE:
/*      */       case POLISHED_DIORITE_SLAB:
/*      */       case POLISHED_DIORITE_STAIRS:
/*      */       case POLISHED_GRANITE:
/*      */       case POLISHED_GRANITE_SLAB:
/*      */       case POLISHED_GRANITE_STAIRS:
/*      */       case PRISMARINE:
/*      */       case PRISMARINE_BRICKS:
/*      */       case PRISMARINE_BRICK_SLAB:
/*      */       case PRISMARINE_BRICK_STAIRS:
/*      */       case PRISMARINE_SLAB:
/*      */       case PRISMARINE_STAIRS:
/*      */       case PRISMARINE_WALL:
/*      */       case PUMPKIN:
/*      */       case PURPLE_BANNER:
/*      */       case PURPLE_BED:
/*      */       case PURPLE_CONCRETE:
/*      */       case PURPLE_CONCRETE_POWDER:
/*      */       case PURPLE_GLAZED_TERRACOTTA:
/*      */       case PURPLE_SHULKER_BOX:
/*      */       case PURPLE_STAINED_GLASS:
/*      */       case PURPLE_STAINED_GLASS_PANE:
/*      */       case PURPLE_TERRACOTTA:
/*      */       case PURPLE_WALL_BANNER:
/*      */       case PURPLE_WOOL:
/*      */       case PURPUR_BLOCK:
/*      */       case PURPUR_PILLAR:
/*      */       case PURPUR_SLAB:
/*      */       case PURPUR_STAIRS:
/*      */       case QUARTZ_BLOCK:
/*      */       case QUARTZ_BRICKS:
/*      */       case QUARTZ_PILLAR:
/*      */       case QUARTZ_SLAB:
/*      */       case QUARTZ_STAIRS:
/*      */       case REDSTONE_BLOCK:
/*      */       case REDSTONE_LAMP:
/*      */       case REDSTONE_ORE:
/*      */       case RED_BANNER:
/*      */       case RED_BED:
/*      */       case RED_CONCRETE:
/*      */       case RED_CONCRETE_POWDER:
/*      */       case RED_GLAZED_TERRACOTTA:
/*      */       case RED_MUSHROOM_BLOCK:
/*      */       case RED_NETHER_BRICKS:
/*      */       case RED_NETHER_BRICK_SLAB:
/*      */       case RED_NETHER_BRICK_STAIRS:
/*      */       case RED_NETHER_BRICK_WALL:
/*      */       case RED_SAND:
/*      */       case RED_SANDSTONE:
/*      */       case RED_SANDSTONE_SLAB:
/*      */       case RED_SANDSTONE_STAIRS:
/*      */       case RED_SANDSTONE_WALL:
/*      */       case RED_SHULKER_BOX:
/*      */       case RED_STAINED_GLASS:
/*      */       case RED_STAINED_GLASS_PANE:
/*      */       case RED_TERRACOTTA:
/*      */       case RED_WALL_BANNER:
/*      */       case RED_WOOL:
/*      */       case REPEATING_COMMAND_BLOCK:
/*      */       case RESPAWN_ANCHOR:
/*      */       case SAND:
/*      */       case SANDSTONE:
/*      */       case SANDSTONE_SLAB:
/*      */       case SANDSTONE_STAIRS:
/*      */       case SANDSTONE_WALL:
/*      */       case SEA_LANTERN:
/*      */       case SHROOMLIGHT:
/*      */       case SHULKER_BOX:
/*      */       case SLIME_BLOCK:
/*      */       case SMITHING_TABLE:
/*      */       case SMOKER:
/*      */       case SMOOTH_QUARTZ:
/*      */       case SMOOTH_QUARTZ_SLAB:
/*      */       case SMOOTH_QUARTZ_STAIRS:
/*      */       case SMOOTH_RED_SANDSTONE:
/*      */       case SMOOTH_RED_SANDSTONE_SLAB:
/*      */       case SMOOTH_RED_SANDSTONE_STAIRS:
/*      */       case SMOOTH_SANDSTONE:
/*      */       case SMOOTH_SANDSTONE_SLAB:
/*      */       case SMOOTH_SANDSTONE_STAIRS:
/*      */       case SMOOTH_STONE:
/*      */       case SMOOTH_STONE_SLAB:
/*      */       case SNOW_BLOCK:
/*      */       case SOUL_CAMPFIRE:
/*      */       case SOUL_LANTERN:
/*      */       case SOUL_SAND:
/*      */       case SOUL_SOIL:
/*      */       case SPAWNER:
/*      */       case SPONGE:
/*      */       case SPRUCE_DOOR:
/*      */       case SPRUCE_FENCE:
/*      */       case SPRUCE_FENCE_GATE:
/*      */       case SPRUCE_LEAVES:
/*      */       case SPRUCE_LOG:
/*      */       case SPRUCE_PLANKS:
/*      */       case SPRUCE_PRESSURE_PLATE:
/*      */       case SPRUCE_SIGN:
/*      */       case SPRUCE_SLAB:
/*      */       case SPRUCE_STAIRS:
/*      */       case SPRUCE_TRAPDOOR:
/*      */       case SPRUCE_WALL_SIGN:
/*      */       case SPRUCE_WOOD:
/*      */       case STICKY_PISTON:
/*      */       case STONE:
/*      */       case STONECUTTER:
/*      */       case STONE_BRICKS:
/*      */       case STONE_BRICK_SLAB:
/*      */       case STONE_BRICK_STAIRS:
/*      */       case STONE_BRICK_WALL:
/*      */       case STONE_PRESSURE_PLATE:
/*      */       case STONE_SLAB:
/*      */       case STONE_STAIRS:
/*      */       case STRIPPED_ACACIA_LOG:
/*      */       case STRIPPED_ACACIA_WOOD:
/*      */       case STRIPPED_BIRCH_LOG:
/*      */       case STRIPPED_BIRCH_WOOD:
/*      */       case STRIPPED_CRIMSON_HYPHAE:
/*      */       case STRIPPED_CRIMSON_STEM:
/*      */       case STRIPPED_DARK_OAK_LOG:
/*      */       case STRIPPED_DARK_OAK_WOOD:
/*      */       case STRIPPED_JUNGLE_LOG:
/*      */       case STRIPPED_JUNGLE_WOOD:
/*      */       case STRIPPED_OAK_LOG:
/*      */       case STRIPPED_OAK_WOOD:
/*      */       case STRIPPED_SPRUCE_LOG:
/*      */       case STRIPPED_SPRUCE_WOOD:
/*      */       case STRIPPED_WARPED_HYPHAE:
/*      */       case STRIPPED_WARPED_STEM:
/*      */       case STRUCTURE_BLOCK:
/*      */       case TARGET:
/*      */       case TERRACOTTA:
/*      */       case TNT:
/*      */       case TRAPPED_CHEST:
/*      */       case TUBE_CORAL_BLOCK:
/*      */       case TURTLE_EGG:
/*      */       case WARPED_DOOR:
/*      */       case WARPED_FENCE:
/*      */       case WARPED_FENCE_GATE:
/*      */       case WARPED_HYPHAE:
/*      */       case WARPED_NYLIUM:
/*      */       case WARPED_PLANKS:
/*      */       case WARPED_PRESSURE_PLATE:
/*      */       case WARPED_SIGN:
/*      */       case WARPED_SLAB:
/*      */       case WARPED_STAIRS:
/*      */       case WARPED_STEM:
/*      */       case WARPED_TRAPDOOR:
/*      */       case WARPED_WALL_SIGN:
/*      */       case WARPED_WART_BLOCK:
/*      */       case WET_SPONGE:
/*      */       case WHITE_BANNER:
/*      */       case WHITE_BED:
/*      */       case WHITE_CONCRETE:
/*      */       case WHITE_CONCRETE_POWDER:
/*      */       case WHITE_GLAZED_TERRACOTTA:
/*      */       case WHITE_SHULKER_BOX:
/*      */       case WHITE_STAINED_GLASS:
/*      */       case WHITE_STAINED_GLASS_PANE:
/*      */       case WHITE_TERRACOTTA:
/*      */       case WHITE_WALL_BANNER:
/*      */       case WHITE_WOOL:
/*      */       case YELLOW_BANNER:
/*      */       case YELLOW_BED:
/*      */       case YELLOW_CONCRETE:
/*      */       case YELLOW_CONCRETE_POWDER:
/*      */       case YELLOW_GLAZED_TERRACOTTA:
/*      */       case YELLOW_SHULKER_BOX:
/*      */       case YELLOW_STAINED_GLASS:
/*      */       case YELLOW_STAINED_GLASS_PANE:
/*      */       case YELLOW_TERRACOTTA:
/*      */       case YELLOW_WALL_BANNER:
/*      */       case YELLOW_WOOL:
/*      */       case LEGACY_STONE:
/*      */       case LEGACY_GRASS:
/*      */       case LEGACY_DIRT:
/*      */       case LEGACY_COBBLESTONE:
/*      */       case LEGACY_WOOD:
/*      */       case LEGACY_BEDROCK:
/*      */       case LEGACY_SAND:
/*      */       case LEGACY_GRAVEL:
/*      */       case LEGACY_GOLD_ORE:
/*      */       case LEGACY_IRON_ORE:
/*      */       case LEGACY_COAL_ORE:
/*      */       case LEGACY_LOG:
/*      */       case LEGACY_LEAVES:
/*      */       case LEGACY_SPONGE:
/*      */       case LEGACY_GLASS:
/*      */       case LEGACY_LAPIS_ORE:
/*      */       case LEGACY_LAPIS_BLOCK:
/*      */       case LEGACY_DISPENSER:
/*      */       case LEGACY_SANDSTONE:
/*      */       case LEGACY_NOTE_BLOCK:
/*      */       case LEGACY_BED_BLOCK:
/*      */       case LEGACY_PISTON_STICKY_BASE:
/*      */       case LEGACY_PISTON_BASE:
/*      */       case LEGACY_PISTON_EXTENSION:
/*      */       case LEGACY_WOOL:
/*      */       case LEGACY_PISTON_MOVING_PIECE:
/*      */       case LEGACY_GOLD_BLOCK:
/*      */       case LEGACY_IRON_BLOCK:
/*      */       case LEGACY_DOUBLE_STEP:
/*      */       case LEGACY_STEP:
/*      */       case LEGACY_BRICK:
/*      */       case LEGACY_TNT:
/*      */       case LEGACY_BOOKSHELF:
/*      */       case LEGACY_MOSSY_COBBLESTONE:
/*      */       case LEGACY_OBSIDIAN:
/*      */       case LEGACY_MOB_SPAWNER:
/*      */       case LEGACY_WOOD_STAIRS:
/*      */       case LEGACY_CHEST:
/*      */       case LEGACY_DIAMOND_ORE:
/*      */       case LEGACY_DIAMOND_BLOCK:
/*      */       case LEGACY_WORKBENCH:
/*      */       case LEGACY_SOIL:
/*      */       case LEGACY_FURNACE:
/*      */       case LEGACY_BURNING_FURNACE:
/*      */       case LEGACY_SIGN_POST:
/*      */       case LEGACY_WOODEN_DOOR:
/*      */       case LEGACY_COBBLESTONE_STAIRS:
/*      */       case LEGACY_WALL_SIGN:
/*      */       case LEGACY_STONE_PLATE:
/*      */       case LEGACY_IRON_DOOR_BLOCK:
/*      */       case LEGACY_WOOD_PLATE:
/*      */       case LEGACY_REDSTONE_ORE:
/*      */       case LEGACY_GLOWING_REDSTONE_ORE:
/*      */       case LEGACY_ICE:
/*      */       case LEGACY_SNOW_BLOCK:
/*      */       case LEGACY_CACTUS:
/*      */       case LEGACY_CLAY:
/*      */       case LEGACY_JUKEBOX:
/*      */       case LEGACY_FENCE:
/*      */       case LEGACY_PUMPKIN:
/*      */       case LEGACY_NETHERRACK:
/*      */       case LEGACY_SOUL_SAND:
/*      */       case LEGACY_GLOWSTONE:
/*      */       case LEGACY_JACK_O_LANTERN:
/*      */       case LEGACY_CAKE_BLOCK:
/*      */       case LEGACY_STAINED_GLASS:
/*      */       case LEGACY_TRAP_DOOR:
/*      */       case LEGACY_MONSTER_EGGS:
/*      */       case LEGACY_SMOOTH_BRICK:
/*      */       case LEGACY_HUGE_MUSHROOM_1:
/*      */       case LEGACY_HUGE_MUSHROOM_2:
/*      */       case LEGACY_IRON_FENCE:
/*      */       case LEGACY_THIN_GLASS:
/*      */       case LEGACY_MELON_BLOCK:
/*      */       case LEGACY_FENCE_GATE:
/*      */       case LEGACY_BRICK_STAIRS:
/*      */       case LEGACY_SMOOTH_STAIRS:
/*      */       case LEGACY_MYCEL:
/*      */       case LEGACY_NETHER_BRICK:
/*      */       case LEGACY_NETHER_FENCE:
/*      */       case LEGACY_NETHER_BRICK_STAIRS:
/*      */       case LEGACY_ENCHANTMENT_TABLE:
/*      */       case LEGACY_BREWING_STAND:
/*      */       case LEGACY_CAULDRON:
/*      */       case LEGACY_ENDER_PORTAL_FRAME:
/*      */       case LEGACY_ENDER_STONE:
/*      */       case LEGACY_DRAGON_EGG:
/*      */       case LEGACY_REDSTONE_LAMP_OFF:
/*      */       case LEGACY_REDSTONE_LAMP_ON:
/*      */       case LEGACY_WOOD_DOUBLE_STEP:
/*      */       case LEGACY_WOOD_STEP:
/*      */       case LEGACY_SANDSTONE_STAIRS:
/*      */       case LEGACY_EMERALD_ORE:
/*      */       case LEGACY_ENDER_CHEST:
/*      */       case LEGACY_EMERALD_BLOCK:
/*      */       case LEGACY_SPRUCE_WOOD_STAIRS:
/*      */       case LEGACY_BIRCH_WOOD_STAIRS:
/*      */       case LEGACY_JUNGLE_WOOD_STAIRS:
/*      */       case LEGACY_COMMAND:
/*      */       case LEGACY_BEACON:
/*      */       case LEGACY_COBBLE_WALL:
/*      */       case LEGACY_ANVIL:
/*      */       case LEGACY_TRAPPED_CHEST:
/*      */       case LEGACY_GOLD_PLATE:
/*      */       case LEGACY_IRON_PLATE:
/*      */       case LEGACY_DAYLIGHT_DETECTOR:
/*      */       case LEGACY_REDSTONE_BLOCK:
/*      */       case LEGACY_QUARTZ_ORE:
/*      */       case LEGACY_HOPPER:
/*      */       case LEGACY_QUARTZ_BLOCK:
/*      */       case LEGACY_QUARTZ_STAIRS:
/*      */       case LEGACY_DROPPER:
/*      */       case LEGACY_STAINED_CLAY:
/*      */       case LEGACY_HAY_BLOCK:
/*      */       case LEGACY_HARD_CLAY:
/*      */       case LEGACY_COAL_BLOCK:
/*      */       case LEGACY_STAINED_GLASS_PANE:
/*      */       case LEGACY_LEAVES_2:
/*      */       case LEGACY_LOG_2:
/*      */       case LEGACY_ACACIA_STAIRS:
/*      */       case LEGACY_DARK_OAK_STAIRS:
/*      */       case LEGACY_PACKED_ICE:
/*      */       case LEGACY_RED_SANDSTONE:
/*      */       case LEGACY_SLIME_BLOCK:
/*      */       case LEGACY_BARRIER:
/*      */       case LEGACY_IRON_TRAPDOOR:
/*      */       case LEGACY_PRISMARINE:
/*      */       case LEGACY_SEA_LANTERN:
/*      */       case LEGACY_DOUBLE_STONE_SLAB2:
/*      */       case LEGACY_RED_SANDSTONE_STAIRS:
/*      */       case LEGACY_STONE_SLAB2:
/*      */       case LEGACY_SPRUCE_FENCE_GATE:
/*      */       case LEGACY_BIRCH_FENCE_GATE:
/*      */       case LEGACY_JUNGLE_FENCE_GATE:
/*      */       case LEGACY_DARK_OAK_FENCE_GATE:
/*      */       case LEGACY_ACACIA_FENCE_GATE:
/*      */       case LEGACY_SPRUCE_FENCE:
/*      */       case LEGACY_BIRCH_FENCE:
/*      */       case LEGACY_JUNGLE_FENCE:
/*      */       case LEGACY_DARK_OAK_FENCE:
/*      */       case LEGACY_ACACIA_FENCE:
/*      */       case LEGACY_STANDING_BANNER:
/*      */       case LEGACY_WALL_BANNER:
/*      */       case LEGACY_DAYLIGHT_DETECTOR_INVERTED:
/*      */       case LEGACY_SPRUCE_DOOR:
/*      */       case LEGACY_BIRCH_DOOR:
/*      */       case LEGACY_JUNGLE_DOOR:
/*      */       case LEGACY_ACACIA_DOOR:
/*      */       case LEGACY_DARK_OAK_DOOR:
/*      */       case LEGACY_PURPUR_BLOCK:
/*      */       case LEGACY_PURPUR_PILLAR:
/*      */       case LEGACY_PURPUR_STAIRS:
/*      */       case LEGACY_PURPUR_DOUBLE_SLAB:
/*      */       case LEGACY_PURPUR_SLAB:
/*      */       case LEGACY_END_BRICKS:
/*      */       case LEGACY_GRASS_PATH:
/*      */       case LEGACY_STRUCTURE_BLOCK:
/*      */       case LEGACY_COMMAND_REPEATING:
/*      */       case LEGACY_COMMAND_CHAIN:
/*      */       case LEGACY_FROSTED_ICE:
/*      */       case LEGACY_MAGMA:
/*      */       case LEGACY_NETHER_WART_BLOCK:
/*      */       case LEGACY_RED_NETHER_BRICK:
/*      */       case LEGACY_BONE_BLOCK:
/*      */       case LEGACY_OBSERVER:
/*      */       case LEGACY_WHITE_SHULKER_BOX:
/*      */       case LEGACY_ORANGE_SHULKER_BOX:
/*      */       case LEGACY_MAGENTA_SHULKER_BOX:
/*      */       case LEGACY_LIGHT_BLUE_SHULKER_BOX:
/*      */       case LEGACY_YELLOW_SHULKER_BOX:
/*      */       case LEGACY_LIME_SHULKER_BOX:
/*      */       case LEGACY_PINK_SHULKER_BOX:
/*      */       case LEGACY_GRAY_SHULKER_BOX:
/*      */       case LEGACY_SILVER_SHULKER_BOX:
/*      */       case LEGACY_CYAN_SHULKER_BOX:
/*      */       case LEGACY_PURPLE_SHULKER_BOX:
/*      */       case LEGACY_BLUE_SHULKER_BOX:
/*      */       case LEGACY_BROWN_SHULKER_BOX:
/*      */       case LEGACY_GREEN_SHULKER_BOX:
/*      */       case LEGACY_RED_SHULKER_BOX:
/*      */       case LEGACY_BLACK_SHULKER_BOX:
/*      */       case LEGACY_WHITE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_ORANGE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_MAGENTA_GLAZED_TERRACOTTA:
/*      */       case LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_YELLOW_GLAZED_TERRACOTTA:
/*      */       case LEGACY_LIME_GLAZED_TERRACOTTA:
/*      */       case LEGACY_PINK_GLAZED_TERRACOTTA:
/*      */       case LEGACY_GRAY_GLAZED_TERRACOTTA:
/*      */       case LEGACY_SILVER_GLAZED_TERRACOTTA:
/*      */       case LEGACY_CYAN_GLAZED_TERRACOTTA:
/*      */       case LEGACY_PURPLE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_BLUE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_BROWN_GLAZED_TERRACOTTA:
/*      */       case LEGACY_GREEN_GLAZED_TERRACOTTA:
/*      */       case LEGACY_RED_GLAZED_TERRACOTTA:
/*      */       case LEGACY_BLACK_GLAZED_TERRACOTTA:
/*      */       case LEGACY_CONCRETE:
/*      */       case LEGACY_CONCRETE_POWDER:
/* 5496 */         return true;
/*      */     } 
/* 5498 */     return false; } public boolean isEdible() { switch (this) { case APPLE: case BAKED_POTATO: case BEEF: case BEETROOT: case BEETROOT_SOUP: case BREAD: case CARROT: case CHICKEN: case CHORUS_FRUIT: case COD: case COOKED_BEEF: case COOKED_CHICKEN: case COOKED_COD: case COOKED_MUTTON: case COOKED_PORKCHOP: case COOKED_RABBIT: case COOKED_SALMON: case COOKIE: case DRIED_KELP: case ENCHANTED_GOLDEN_APPLE: case GOLDEN_APPLE: case GOLDEN_CARROT: case HONEY_BOTTLE: case MELON_SLICE: case MUSHROOM_STEW: case MUTTON: case POISONOUS_POTATO: case PORKCHOP: case POTATO: case PUFFERFISH: case PUMPKIN_PIE: case RABBIT: case RABBIT_STEW: case ROTTEN_FLESH: case SALMON: case SPIDER_EYE: case SUSPICIOUS_STEW: case SWEET_BERRIES: case TROPICAL_FISH: case LEGACY_BREAD: case LEGACY_CARROT_ITEM: case LEGACY_BAKED_POTATO: case LEGACY_POTATO_ITEM: case LEGACY_POISONOUS_POTATO: case LEGACY_GOLDEN_CARROT: case LEGACY_PUMPKIN_PIE: case LEGACY_COOKIE: case LEGACY_MELON: case LEGACY_MUSHROOM_SOUP: case LEGACY_RAW_CHICKEN: case LEGACY_COOKED_CHICKEN: case LEGACY_RAW_BEEF: case LEGACY_COOKED_BEEF: case LEGACY_RAW_FISH: case LEGACY_COOKED_FISH: case LEGACY_PORK: case LEGACY_GRILLED_PORK: case LEGACY_APPLE: case LEGACY_GOLDEN_APPLE: case LEGACY_ROTTEN_FLESH: case LEGACY_SPIDER_EYE: case LEGACY_RABBIT: case LEGACY_COOKED_RABBIT: case LEGACY_RABBIT_STEW: case LEGACY_MUTTON: case LEGACY_COOKED_MUTTON: case LEGACY_BEETROOT:
/*      */       case LEGACY_CHORUS_FRUIT:
/*      */       case LEGACY_BEETROOT_SOUP:
/*      */         return true; }  return false; }
/*      */   @Nullable public static Material getMaterial(@NotNull String name) { return getMaterial(name, false); }
/*      */   @Nullable public static Material getMaterial(@NotNull String name, boolean legacyName) { if (legacyName) { if (!name.startsWith("LEGACY_"))
/*      */         name = "LEGACY_" + name;  Material match = BY_NAME.get(name); return Bukkit.getUnsafe().fromLegacy(match); }  return BY_NAME.get(name); }
/*      */   @Nullable public static Material matchMaterial(@NotNull String name) { return matchMaterial(name, false); }
/*      */   @Nullable public static Material matchMaterial(@NotNull String name, boolean legacyName) { Validate.notNull(name, "Name cannot be null"); String filtered = name; if (filtered.startsWith("minecraft:"))
/*      */       filtered = filtered.substring("minecraft:".length());  filtered = filtered.toUpperCase(Locale.ENGLISH); filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", ""); return getMaterial(filtered, legacyName); }
/* 5508 */   public boolean isAir() { switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case AIR:
/*      */       case CAVE_AIR:
/*      */       case VOID_AIR:
/*      */       case LEGACY_AIR:
/* 5516 */         return true;
/*      */     } 
/* 5518 */     return false; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isTransparent() {
/* 5531 */     if (!isBlock()) {
/* 5532 */       return false;
/*      */     }
/* 5534 */     switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case AIR:
/*      */       case CAVE_AIR:
/*      */       case VOID_AIR:
/*      */       case ACACIA_BUTTON:
/*      */       case ACACIA_SAPLING:
/*      */       case ACTIVATOR_RAIL:
/*      */       case ALLIUM:
/*      */       case ATTACHED_MELON_STEM:
/*      */       case ATTACHED_PUMPKIN_STEM:
/*      */       case AZURE_BLUET:
/*      */       case BARRIER:
/*      */       case BEETROOTS:
/*      */       case BIRCH_BUTTON:
/*      */       case BIRCH_SAPLING:
/*      */       case BLACK_CARPET:
/*      */       case BLUE_CARPET:
/*      */       case BLUE_ORCHID:
/*      */       case BROWN_CARPET:
/*      */       case BROWN_MUSHROOM:
/*      */       case CARROTS:
/*      */       case CHORUS_FLOWER:
/*      */       case CHORUS_PLANT:
/*      */       case COCOA:
/*      */       case COMPARATOR:
/*      */       case CREEPER_HEAD:
/*      */       case CREEPER_WALL_HEAD:
/*      */       case CYAN_CARPET:
/*      */       case DANDELION:
/*      */       case DARK_OAK_BUTTON:
/*      */       case DARK_OAK_SAPLING:
/*      */       case DEAD_BUSH:
/*      */       case DETECTOR_RAIL:
/*      */       case DRAGON_HEAD:
/*      */       case DRAGON_WALL_HEAD:
/*      */       case END_GATEWAY:
/*      */       case END_PORTAL:
/*      */       case END_ROD:
/*      */       case FERN:
/*      */       case FIRE:
/*      */       case FLOWER_POT:
/*      */       case GRASS:
/*      */       case GRAY_CARPET:
/*      */       case GREEN_CARPET:
/*      */       case JUNGLE_BUTTON:
/*      */       case JUNGLE_SAPLING:
/*      */       case LADDER:
/*      */       case LARGE_FERN:
/*      */       case LEVER:
/*      */       case LIGHT_BLUE_CARPET:
/*      */       case LIGHT_GRAY_CARPET:
/*      */       case LILAC:
/*      */       case LILY_PAD:
/*      */       case LIME_CARPET:
/*      */       case MAGENTA_CARPET:
/*      */       case MELON_STEM:
/*      */       case NETHER_PORTAL:
/*      */       case NETHER_WART:
/*      */       case OAK_BUTTON:
/*      */       case OAK_SAPLING:
/*      */       case ORANGE_CARPET:
/*      */       case ORANGE_TULIP:
/*      */       case OXEYE_DAISY:
/*      */       case PEONY:
/*      */       case PINK_CARPET:
/*      */       case PINK_TULIP:
/*      */       case PLAYER_HEAD:
/*      */       case PLAYER_WALL_HEAD:
/*      */       case POPPY:
/*      */       case POTATOES:
/*      */       case POTTED_ACACIA_SAPLING:
/*      */       case POTTED_ALLIUM:
/*      */       case POTTED_AZURE_BLUET:
/*      */       case POTTED_BIRCH_SAPLING:
/*      */       case POTTED_BLUE_ORCHID:
/*      */       case POTTED_BROWN_MUSHROOM:
/*      */       case POTTED_CACTUS:
/*      */       case POTTED_DANDELION:
/*      */       case POTTED_DARK_OAK_SAPLING:
/*      */       case POTTED_DEAD_BUSH:
/*      */       case POTTED_FERN:
/*      */       case POTTED_JUNGLE_SAPLING:
/*      */       case POTTED_OAK_SAPLING:
/*      */       case POTTED_ORANGE_TULIP:
/*      */       case POTTED_OXEYE_DAISY:
/*      */       case POTTED_PINK_TULIP:
/*      */       case POTTED_POPPY:
/*      */       case POTTED_RED_MUSHROOM:
/*      */       case POTTED_RED_TULIP:
/*      */       case POTTED_SPRUCE_SAPLING:
/*      */       case POTTED_WHITE_TULIP:
/*      */       case POWERED_RAIL:
/*      */       case PUMPKIN_STEM:
/*      */       case PURPLE_CARPET:
/*      */       case RAIL:
/*      */       case REDSTONE_TORCH:
/*      */       case REDSTONE_WALL_TORCH:
/*      */       case REDSTONE_WIRE:
/*      */       case RED_CARPET:
/*      */       case RED_MUSHROOM:
/*      */       case RED_TULIP:
/*      */       case REPEATER:
/*      */       case ROSE_BUSH:
/*      */       case SKELETON_SKULL:
/*      */       case SKELETON_WALL_SKULL:
/*      */       case SNOW:
/*      */       case SPRUCE_BUTTON:
/*      */       case SPRUCE_SAPLING:
/*      */       case STONE_BUTTON:
/*      */       case STRUCTURE_VOID:
/*      */       case SUGAR_CANE:
/*      */       case SUNFLOWER:
/*      */       case TALL_GRASS:
/*      */       case TORCH:
/*      */       case TRIPWIRE:
/*      */       case TRIPWIRE_HOOK:
/*      */       case VINE:
/*      */       case WALL_TORCH:
/*      */       case WHEAT:
/*      */       case WHITE_CARPET:
/*      */       case WHITE_TULIP:
/*      */       case WITHER_SKELETON_SKULL:
/*      */       case WITHER_SKELETON_WALL_SKULL:
/*      */       case YELLOW_CARPET:
/*      */       case ZOMBIE_HEAD:
/*      */       case ZOMBIE_WALL_HEAD:
/*      */       case LEGACY_AIR:
/*      */       case LEGACY_SAPLING:
/*      */       case LEGACY_POWERED_RAIL:
/*      */       case LEGACY_DETECTOR_RAIL:
/*      */       case LEGACY_LONG_GRASS:
/*      */       case LEGACY_DEAD_BUSH:
/*      */       case LEGACY_YELLOW_FLOWER:
/*      */       case LEGACY_RED_ROSE:
/*      */       case LEGACY_BROWN_MUSHROOM:
/*      */       case LEGACY_RED_MUSHROOM:
/*      */       case LEGACY_TORCH:
/*      */       case LEGACY_FIRE:
/*      */       case LEGACY_REDSTONE_WIRE:
/*      */       case LEGACY_CROPS:
/*      */       case LEGACY_LADDER:
/*      */       case LEGACY_RAILS:
/*      */       case LEGACY_LEVER:
/*      */       case LEGACY_REDSTONE_TORCH_OFF:
/*      */       case LEGACY_REDSTONE_TORCH_ON:
/*      */       case LEGACY_STONE_BUTTON:
/*      */       case LEGACY_SNOW:
/*      */       case LEGACY_SUGAR_CANE_BLOCK:
/*      */       case LEGACY_PORTAL:
/*      */       case LEGACY_DIODE_BLOCK_OFF:
/*      */       case LEGACY_DIODE_BLOCK_ON:
/*      */       case LEGACY_PUMPKIN_STEM:
/*      */       case LEGACY_MELON_STEM:
/*      */       case LEGACY_VINE:
/*      */       case LEGACY_WATER_LILY:
/*      */       case LEGACY_NETHER_WARTS:
/*      */       case LEGACY_ENDER_PORTAL:
/*      */       case LEGACY_COCOA:
/*      */       case LEGACY_TRIPWIRE_HOOK:
/*      */       case LEGACY_TRIPWIRE:
/*      */       case LEGACY_FLOWER_POT:
/*      */       case LEGACY_CARROT:
/*      */       case LEGACY_POTATO:
/*      */       case LEGACY_WOOD_BUTTON:
/*      */       case LEGACY_SKULL:
/*      */       case LEGACY_REDSTONE_COMPARATOR_OFF:
/*      */       case LEGACY_REDSTONE_COMPARATOR_ON:
/*      */       case LEGACY_ACTIVATOR_RAIL:
/*      */       case LEGACY_CARPET:
/*      */       case LEGACY_DOUBLE_PLANT:
/*      */       case LEGACY_END_ROD:
/*      */       case LEGACY_CHORUS_PLANT:
/*      */       case LEGACY_CHORUS_FLOWER:
/*      */       case LEGACY_BEETROOT_BLOCK:
/*      */       case LEGACY_END_GATEWAY:
/*      */       case LEGACY_STRUCTURE_VOID:
/* 5713 */         return true;
/*      */     } 
/* 5715 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFlammable() {
/* 5725 */     if (!isBlock()) {
/* 5726 */       return false;
/*      */     }
/* 5728 */     switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case ACACIA_DOOR:
/*      */       case ACACIA_FENCE:
/*      */       case ACACIA_FENCE_GATE:
/*      */       case ACACIA_LEAVES:
/*      */       case ACACIA_LOG:
/*      */       case ACACIA_PLANKS:
/*      */       case ACACIA_PRESSURE_PLATE:
/*      */       case ACACIA_SIGN:
/*      */       case ACACIA_SLAB:
/*      */       case ACACIA_STAIRS:
/*      */       case ACACIA_TRAPDOOR:
/*      */       case ACACIA_WALL_SIGN:
/*      */       case ACACIA_WOOD:
/*      */       case BAMBOO:
/*      */       case BAMBOO_SAPLING:
/*      */       case BARREL:
/*      */       case BEEHIVE:
/*      */       case BEE_NEST:
/*      */       case BIRCH_DOOR:
/*      */       case BIRCH_FENCE:
/*      */       case BIRCH_FENCE_GATE:
/*      */       case BIRCH_LEAVES:
/*      */       case BIRCH_LOG:
/*      */       case BIRCH_PLANKS:
/*      */       case BIRCH_PRESSURE_PLATE:
/*      */       case BIRCH_SIGN:
/*      */       case BIRCH_SLAB:
/*      */       case BIRCH_STAIRS:
/*      */       case BIRCH_TRAPDOOR:
/*      */       case BIRCH_WALL_SIGN:
/*      */       case BIRCH_WOOD:
/*      */       case BLACK_BANNER:
/*      */       case BLACK_BED:
/*      */       case BLACK_CARPET:
/*      */       case BLACK_WALL_BANNER:
/*      */       case BLACK_WOOL:
/*      */       case BLUE_BANNER:
/*      */       case BLUE_BED:
/*      */       case BLUE_CARPET:
/*      */       case BLUE_WALL_BANNER:
/*      */       case BLUE_WOOL:
/*      */       case BOOKSHELF:
/*      */       case BROWN_BANNER:
/*      */       case BROWN_BED:
/*      */       case BROWN_CARPET:
/*      */       case BROWN_MUSHROOM_BLOCK:
/*      */       case BROWN_WALL_BANNER:
/*      */       case BROWN_WOOL:
/*      */       case CAMPFIRE:
/*      */       case CARTOGRAPHY_TABLE:
/*      */       case CHEST:
/*      */       case COMPOSTER:
/*      */       case CRAFTING_TABLE:
/*      */       case CYAN_BANNER:
/*      */       case CYAN_BED:
/*      */       case CYAN_CARPET:
/*      */       case CYAN_WALL_BANNER:
/*      */       case CYAN_WOOL:
/*      */       case DARK_OAK_DOOR:
/*      */       case DARK_OAK_FENCE:
/*      */       case DARK_OAK_FENCE_GATE:
/*      */       case DARK_OAK_LEAVES:
/*      */       case DARK_OAK_LOG:
/*      */       case DARK_OAK_PLANKS:
/*      */       case DARK_OAK_PRESSURE_PLATE:
/*      */       case DARK_OAK_SIGN:
/*      */       case DARK_OAK_SLAB:
/*      */       case DARK_OAK_STAIRS:
/*      */       case DARK_OAK_TRAPDOOR:
/*      */       case DARK_OAK_WALL_SIGN:
/*      */       case DARK_OAK_WOOD:
/*      */       case DAYLIGHT_DETECTOR:
/*      */       case DEAD_BUSH:
/*      */       case FERN:
/*      */       case FLETCHING_TABLE:
/*      */       case GRASS:
/*      */       case GRAY_BANNER:
/*      */       case GRAY_BED:
/*      */       case GRAY_CARPET:
/*      */       case GRAY_WALL_BANNER:
/*      */       case GRAY_WOOL:
/*      */       case GREEN_BANNER:
/*      */       case GREEN_BED:
/*      */       case GREEN_CARPET:
/*      */       case GREEN_WALL_BANNER:
/*      */       case GREEN_WOOL:
/*      */       case JUKEBOX:
/*      */       case JUNGLE_DOOR:
/*      */       case JUNGLE_FENCE:
/*      */       case JUNGLE_FENCE_GATE:
/*      */       case JUNGLE_LEAVES:
/*      */       case JUNGLE_LOG:
/*      */       case JUNGLE_PLANKS:
/*      */       case JUNGLE_PRESSURE_PLATE:
/*      */       case JUNGLE_SIGN:
/*      */       case JUNGLE_SLAB:
/*      */       case JUNGLE_STAIRS:
/*      */       case JUNGLE_TRAPDOOR:
/*      */       case JUNGLE_WALL_SIGN:
/*      */       case JUNGLE_WOOD:
/*      */       case LARGE_FERN:
/*      */       case LECTERN:
/*      */       case LIGHT_BLUE_BANNER:
/*      */       case LIGHT_BLUE_BED:
/*      */       case LIGHT_BLUE_CARPET:
/*      */       case LIGHT_BLUE_WALL_BANNER:
/*      */       case LIGHT_BLUE_WOOL:
/*      */       case LIGHT_GRAY_BANNER:
/*      */       case LIGHT_GRAY_BED:
/*      */       case LIGHT_GRAY_CARPET:
/*      */       case LIGHT_GRAY_WALL_BANNER:
/*      */       case LIGHT_GRAY_WOOL:
/*      */       case LILAC:
/*      */       case LIME_BANNER:
/*      */       case LIME_BED:
/*      */       case LIME_CARPET:
/*      */       case LIME_WALL_BANNER:
/*      */       case LIME_WOOL:
/*      */       case LOOM:
/*      */       case MAGENTA_BANNER:
/*      */       case MAGENTA_BED:
/*      */       case MAGENTA_CARPET:
/*      */       case MAGENTA_WALL_BANNER:
/*      */       case MAGENTA_WOOL:
/*      */       case MUSHROOM_STEM:
/*      */       case NOTE_BLOCK:
/*      */       case OAK_DOOR:
/*      */       case OAK_FENCE:
/*      */       case OAK_FENCE_GATE:
/*      */       case OAK_LEAVES:
/*      */       case OAK_LOG:
/*      */       case OAK_PLANKS:
/*      */       case OAK_PRESSURE_PLATE:
/*      */       case OAK_SIGN:
/*      */       case OAK_SLAB:
/*      */       case OAK_STAIRS:
/*      */       case OAK_TRAPDOOR:
/*      */       case OAK_WALL_SIGN:
/*      */       case OAK_WOOD:
/*      */       case ORANGE_BANNER:
/*      */       case ORANGE_BED:
/*      */       case ORANGE_CARPET:
/*      */       case ORANGE_WALL_BANNER:
/*      */       case ORANGE_WOOL:
/*      */       case PEONY:
/*      */       case PINK_BANNER:
/*      */       case PINK_BED:
/*      */       case PINK_CARPET:
/*      */       case PINK_WALL_BANNER:
/*      */       case PINK_WOOL:
/*      */       case PURPLE_BANNER:
/*      */       case PURPLE_BED:
/*      */       case PURPLE_CARPET:
/*      */       case PURPLE_WALL_BANNER:
/*      */       case PURPLE_WOOL:
/*      */       case RED_BANNER:
/*      */       case RED_BED:
/*      */       case RED_CARPET:
/*      */       case RED_MUSHROOM_BLOCK:
/*      */       case RED_WALL_BANNER:
/*      */       case RED_WOOL:
/*      */       case ROSE_BUSH:
/*      */       case SMITHING_TABLE:
/*      */       case SOUL_CAMPFIRE:
/*      */       case SPRUCE_DOOR:
/*      */       case SPRUCE_FENCE:
/*      */       case SPRUCE_FENCE_GATE:
/*      */       case SPRUCE_LEAVES:
/*      */       case SPRUCE_LOG:
/*      */       case SPRUCE_PLANKS:
/*      */       case SPRUCE_PRESSURE_PLATE:
/*      */       case SPRUCE_SIGN:
/*      */       case SPRUCE_SLAB:
/*      */       case SPRUCE_STAIRS:
/*      */       case SPRUCE_TRAPDOOR:
/*      */       case SPRUCE_WALL_SIGN:
/*      */       case SPRUCE_WOOD:
/*      */       case STRIPPED_ACACIA_LOG:
/*      */       case STRIPPED_ACACIA_WOOD:
/*      */       case STRIPPED_BIRCH_LOG:
/*      */       case STRIPPED_BIRCH_WOOD:
/*      */       case STRIPPED_DARK_OAK_LOG:
/*      */       case STRIPPED_DARK_OAK_WOOD:
/*      */       case STRIPPED_JUNGLE_LOG:
/*      */       case STRIPPED_JUNGLE_WOOD:
/*      */       case STRIPPED_OAK_LOG:
/*      */       case STRIPPED_OAK_WOOD:
/*      */       case STRIPPED_SPRUCE_LOG:
/*      */       case STRIPPED_SPRUCE_WOOD:
/*      */       case SUNFLOWER:
/*      */       case TALL_GRASS:
/*      */       case TNT:
/*      */       case TRAPPED_CHEST:
/*      */       case VINE:
/*      */       case WHITE_BANNER:
/*      */       case WHITE_BED:
/*      */       case WHITE_CARPET:
/*      */       case WHITE_WALL_BANNER:
/*      */       case WHITE_WOOL:
/*      */       case YELLOW_BANNER:
/*      */       case YELLOW_BED:
/*      */       case YELLOW_CARPET:
/*      */       case YELLOW_WALL_BANNER:
/*      */       case YELLOW_WOOL:
/*      */       case LEGACY_WOOD:
/*      */       case LEGACY_LOG:
/*      */       case LEGACY_LEAVES:
/*      */       case LEGACY_NOTE_BLOCK:
/*      */       case LEGACY_BED_BLOCK:
/*      */       case LEGACY_WOOL:
/*      */       case LEGACY_TNT:
/*      */       case LEGACY_BOOKSHELF:
/*      */       case LEGACY_WOOD_STAIRS:
/*      */       case LEGACY_CHEST:
/*      */       case LEGACY_WORKBENCH:
/*      */       case LEGACY_SIGN_POST:
/*      */       case LEGACY_WOODEN_DOOR:
/*      */       case LEGACY_WALL_SIGN:
/*      */       case LEGACY_WOOD_PLATE:
/*      */       case LEGACY_JUKEBOX:
/*      */       case LEGACY_FENCE:
/*      */       case LEGACY_TRAP_DOOR:
/*      */       case LEGACY_HUGE_MUSHROOM_1:
/*      */       case LEGACY_HUGE_MUSHROOM_2:
/*      */       case LEGACY_FENCE_GATE:
/*      */       case LEGACY_WOOD_DOUBLE_STEP:
/*      */       case LEGACY_WOOD_STEP:
/*      */       case LEGACY_SPRUCE_WOOD_STAIRS:
/*      */       case LEGACY_BIRCH_WOOD_STAIRS:
/*      */       case LEGACY_JUNGLE_WOOD_STAIRS:
/*      */       case LEGACY_TRAPPED_CHEST:
/*      */       case LEGACY_DAYLIGHT_DETECTOR:
/*      */       case LEGACY_LEAVES_2:
/*      */       case LEGACY_LOG_2:
/*      */       case LEGACY_ACACIA_STAIRS:
/*      */       case LEGACY_DARK_OAK_STAIRS:
/*      */       case LEGACY_SPRUCE_FENCE_GATE:
/*      */       case LEGACY_BIRCH_FENCE_GATE:
/*      */       case LEGACY_JUNGLE_FENCE_GATE:
/*      */       case LEGACY_DARK_OAK_FENCE_GATE:
/*      */       case LEGACY_ACACIA_FENCE_GATE:
/*      */       case LEGACY_SPRUCE_FENCE:
/*      */       case LEGACY_BIRCH_FENCE:
/*      */       case LEGACY_JUNGLE_FENCE:
/*      */       case LEGACY_DARK_OAK_FENCE:
/*      */       case LEGACY_ACACIA_FENCE:
/*      */       case LEGACY_STANDING_BANNER:
/*      */       case LEGACY_WALL_BANNER:
/*      */       case LEGACY_DAYLIGHT_DETECTOR_INVERTED:
/*      */       case LEGACY_SPRUCE_DOOR:
/*      */       case LEGACY_BIRCH_DOOR:
/*      */       case LEGACY_JUNGLE_DOOR:
/*      */       case LEGACY_ACACIA_DOOR:
/*      */       case LEGACY_DARK_OAK_DOOR:
/*      */       case LEGACY_LONG_GRASS:
/*      */       case LEGACY_DEAD_BUSH:
/*      */       case LEGACY_VINE:
/*      */       case LEGACY_CARPET:
/*      */       case LEGACY_DOUBLE_PLANT:
/* 5991 */         return true;
/*      */     } 
/* 5993 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBurnable() {
/* 6003 */     if (!isBlock()) {
/* 6004 */       return false;
/*      */     }
/* 6006 */     switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case ACACIA_FENCE:
/*      */       case ACACIA_FENCE_GATE:
/*      */       case ACACIA_LEAVES:
/*      */       case ACACIA_LOG:
/*      */       case ACACIA_PLANKS:
/*      */       case ACACIA_SLAB:
/*      */       case ACACIA_STAIRS:
/*      */       case ACACIA_WOOD:
/*      */       case ALLIUM:
/*      */       case AZURE_BLUET:
/*      */       case BAMBOO:
/*      */       case BEEHIVE:
/*      */       case BEE_NEST:
/*      */       case BIRCH_FENCE:
/*      */       case BIRCH_FENCE_GATE:
/*      */       case BIRCH_LEAVES:
/*      */       case BIRCH_LOG:
/*      */       case BIRCH_PLANKS:
/*      */       case BIRCH_SLAB:
/*      */       case BIRCH_STAIRS:
/*      */       case BIRCH_WOOD:
/*      */       case BLACK_CARPET:
/*      */       case BLACK_WOOL:
/*      */       case BLUE_CARPET:
/*      */       case BLUE_ORCHID:
/*      */       case BLUE_WOOL:
/*      */       case BOOKSHELF:
/*      */       case BROWN_CARPET:
/*      */       case BROWN_WOOL:
/*      */       case COAL_BLOCK:
/*      */       case COMPOSTER:
/*      */       case CORNFLOWER:
/*      */       case CYAN_CARPET:
/*      */       case CYAN_WOOL:
/*      */       case DANDELION:
/*      */       case DARK_OAK_FENCE:
/*      */       case DARK_OAK_FENCE_GATE:
/*      */       case DARK_OAK_LEAVES:
/*      */       case DARK_OAK_LOG:
/*      */       case DARK_OAK_PLANKS:
/*      */       case DARK_OAK_SLAB:
/*      */       case DARK_OAK_STAIRS:
/*      */       case DARK_OAK_WOOD:
/*      */       case DEAD_BUSH:
/*      */       case DRIED_KELP_BLOCK:
/*      */       case FERN:
/*      */       case GRASS:
/*      */       case GRAY_CARPET:
/*      */       case GRAY_WOOL:
/*      */       case GREEN_CARPET:
/*      */       case GREEN_WOOL:
/*      */       case HAY_BLOCK:
/*      */       case JUNGLE_FENCE:
/*      */       case JUNGLE_FENCE_GATE:
/*      */       case JUNGLE_LEAVES:
/*      */       case JUNGLE_LOG:
/*      */       case JUNGLE_PLANKS:
/*      */       case JUNGLE_SLAB:
/*      */       case JUNGLE_STAIRS:
/*      */       case JUNGLE_WOOD:
/*      */       case LARGE_FERN:
/*      */       case LECTERN:
/*      */       case LIGHT_BLUE_CARPET:
/*      */       case LIGHT_BLUE_WOOL:
/*      */       case LIGHT_GRAY_CARPET:
/*      */       case LIGHT_GRAY_WOOL:
/*      */       case LILAC:
/*      */       case LILY_OF_THE_VALLEY:
/*      */       case LIME_CARPET:
/*      */       case LIME_WOOL:
/*      */       case MAGENTA_CARPET:
/*      */       case MAGENTA_WOOL:
/*      */       case OAK_FENCE:
/*      */       case OAK_FENCE_GATE:
/*      */       case OAK_LEAVES:
/*      */       case OAK_LOG:
/*      */       case OAK_PLANKS:
/*      */       case OAK_SLAB:
/*      */       case OAK_STAIRS:
/*      */       case OAK_WOOD:
/*      */       case ORANGE_CARPET:
/*      */       case ORANGE_TULIP:
/*      */       case ORANGE_WOOL:
/*      */       case OXEYE_DAISY:
/*      */       case PEONY:
/*      */       case PINK_CARPET:
/*      */       case PINK_TULIP:
/*      */       case PINK_WOOL:
/*      */       case POPPY:
/*      */       case PURPLE_CARPET:
/*      */       case PURPLE_WOOL:
/*      */       case RED_CARPET:
/*      */       case RED_TULIP:
/*      */       case RED_WOOL:
/*      */       case ROSE_BUSH:
/*      */       case SCAFFOLDING:
/*      */       case SPRUCE_FENCE:
/*      */       case SPRUCE_FENCE_GATE:
/*      */       case SPRUCE_LEAVES:
/*      */       case SPRUCE_LOG:
/*      */       case SPRUCE_PLANKS:
/*      */       case SPRUCE_SLAB:
/*      */       case SPRUCE_STAIRS:
/*      */       case SPRUCE_WOOD:
/*      */       case STRIPPED_ACACIA_LOG:
/*      */       case STRIPPED_ACACIA_WOOD:
/*      */       case STRIPPED_BIRCH_LOG:
/*      */       case STRIPPED_BIRCH_WOOD:
/*      */       case STRIPPED_DARK_OAK_LOG:
/*      */       case STRIPPED_DARK_OAK_WOOD:
/*      */       case STRIPPED_JUNGLE_LOG:
/*      */       case STRIPPED_JUNGLE_WOOD:
/*      */       case STRIPPED_OAK_LOG:
/*      */       case STRIPPED_OAK_WOOD:
/*      */       case STRIPPED_SPRUCE_LOG:
/*      */       case STRIPPED_SPRUCE_WOOD:
/*      */       case SUNFLOWER:
/*      */       case SWEET_BERRY_BUSH:
/*      */       case TALL_GRASS:
/*      */       case TARGET:
/*      */       case TNT:
/*      */       case VINE:
/*      */       case WHITE_CARPET:
/*      */       case WHITE_TULIP:
/*      */       case WHITE_WOOL:
/*      */       case WITHER_ROSE:
/*      */       case YELLOW_CARPET:
/*      */       case YELLOW_WOOL:
/*      */       case LEGACY_WOOD:
/*      */       case LEGACY_LOG:
/*      */       case LEGACY_LEAVES:
/*      */       case LEGACY_WOOL:
/*      */       case LEGACY_TNT:
/*      */       case LEGACY_BOOKSHELF:
/*      */       case LEGACY_WOOD_STAIRS:
/*      */       case LEGACY_FENCE:
/*      */       case LEGACY_FENCE_GATE:
/*      */       case LEGACY_WOOD_DOUBLE_STEP:
/*      */       case LEGACY_WOOD_STEP:
/*      */       case LEGACY_SPRUCE_WOOD_STAIRS:
/*      */       case LEGACY_BIRCH_WOOD_STAIRS:
/*      */       case LEGACY_JUNGLE_WOOD_STAIRS:
/*      */       case LEGACY_HAY_BLOCK:
/*      */       case LEGACY_COAL_BLOCK:
/*      */       case LEGACY_LEAVES_2:
/*      */       case LEGACY_LOG_2:
/*      */       case LEGACY_ACACIA_STAIRS:
/*      */       case LEGACY_DARK_OAK_STAIRS:
/*      */       case LEGACY_SPRUCE_FENCE_GATE:
/*      */       case LEGACY_BIRCH_FENCE_GATE:
/*      */       case LEGACY_JUNGLE_FENCE_GATE:
/*      */       case LEGACY_DARK_OAK_FENCE_GATE:
/*      */       case LEGACY_ACACIA_FENCE_GATE:
/*      */       case LEGACY_SPRUCE_FENCE:
/*      */       case LEGACY_BIRCH_FENCE:
/*      */       case LEGACY_JUNGLE_FENCE:
/*      */       case LEGACY_DARK_OAK_FENCE:
/*      */       case LEGACY_ACACIA_FENCE:
/*      */       case LEGACY_LONG_GRASS:
/*      */       case LEGACY_DEAD_BUSH:
/*      */       case LEGACY_YELLOW_FLOWER:
/*      */       case LEGACY_RED_ROSE:
/*      */       case LEGACY_VINE:
/*      */       case LEGACY_CARPET:
/*      */       case LEGACY_DOUBLE_PLANT:
/* 6175 */         return true;
/*      */     } 
/* 6177 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFuel() {
/* 6187 */     switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case ACACIA_BUTTON:
/*      */       case ACACIA_DOOR:
/*      */       case ACACIA_FENCE:
/*      */       case ACACIA_FENCE_GATE:
/*      */       case ACACIA_LOG:
/*      */       case ACACIA_PLANKS:
/*      */       case ACACIA_PRESSURE_PLATE:
/*      */       case ACACIA_SAPLING:
/*      */       case ACACIA_SIGN:
/*      */       case ACACIA_SLAB:
/*      */       case ACACIA_STAIRS:
/*      */       case ACACIA_TRAPDOOR:
/*      */       case ACACIA_WOOD:
/*      */       case BAMBOO:
/*      */       case BARREL:
/*      */       case BIRCH_BUTTON:
/*      */       case BIRCH_DOOR:
/*      */       case BIRCH_FENCE:
/*      */       case BIRCH_FENCE_GATE:
/*      */       case BIRCH_LOG:
/*      */       case BIRCH_PLANKS:
/*      */       case BIRCH_PRESSURE_PLATE:
/*      */       case BIRCH_SAPLING:
/*      */       case BIRCH_SIGN:
/*      */       case BIRCH_SLAB:
/*      */       case BIRCH_STAIRS:
/*      */       case BIRCH_TRAPDOOR:
/*      */       case BIRCH_WOOD:
/*      */       case BLACK_BANNER:
/*      */       case BLACK_CARPET:
/*      */       case BLACK_WOOL:
/*      */       case BLUE_BANNER:
/*      */       case BLUE_CARPET:
/*      */       case BLUE_WOOL:
/*      */       case BOOKSHELF:
/*      */       case BROWN_BANNER:
/*      */       case BROWN_CARPET:
/*      */       case BROWN_WOOL:
/*      */       case CARTOGRAPHY_TABLE:
/*      */       case CHEST:
/*      */       case COAL_BLOCK:
/*      */       case COMPOSTER:
/*      */       case CRAFTING_TABLE:
/*      */       case CYAN_BANNER:
/*      */       case CYAN_CARPET:
/*      */       case CYAN_WOOL:
/*      */       case DARK_OAK_BUTTON:
/*      */       case DARK_OAK_DOOR:
/*      */       case DARK_OAK_FENCE:
/*      */       case DARK_OAK_FENCE_GATE:
/*      */       case DARK_OAK_LOG:
/*      */       case DARK_OAK_PLANKS:
/*      */       case DARK_OAK_PRESSURE_PLATE:
/*      */       case DARK_OAK_SAPLING:
/*      */       case DARK_OAK_SIGN:
/*      */       case DARK_OAK_SLAB:
/*      */       case DARK_OAK_STAIRS:
/*      */       case DARK_OAK_TRAPDOOR:
/*      */       case DARK_OAK_WOOD:
/*      */       case DAYLIGHT_DETECTOR:
/*      */       case DEAD_BUSH:
/*      */       case DRIED_KELP_BLOCK:
/*      */       case FLETCHING_TABLE:
/*      */       case GRAY_BANNER:
/*      */       case GRAY_CARPET:
/*      */       case GRAY_WOOL:
/*      */       case GREEN_BANNER:
/*      */       case GREEN_CARPET:
/*      */       case GREEN_WOOL:
/*      */       case JUKEBOX:
/*      */       case JUNGLE_BUTTON:
/*      */       case JUNGLE_DOOR:
/*      */       case JUNGLE_FENCE:
/*      */       case JUNGLE_FENCE_GATE:
/*      */       case JUNGLE_LOG:
/*      */       case JUNGLE_PLANKS:
/*      */       case JUNGLE_PRESSURE_PLATE:
/*      */       case JUNGLE_SAPLING:
/*      */       case JUNGLE_SIGN:
/*      */       case JUNGLE_SLAB:
/*      */       case JUNGLE_STAIRS:
/*      */       case JUNGLE_TRAPDOOR:
/*      */       case JUNGLE_WOOD:
/*      */       case LADDER:
/*      */       case LECTERN:
/*      */       case LIGHT_BLUE_BANNER:
/*      */       case LIGHT_BLUE_CARPET:
/*      */       case LIGHT_BLUE_WOOL:
/*      */       case LIGHT_GRAY_BANNER:
/*      */       case LIGHT_GRAY_CARPET:
/*      */       case LIGHT_GRAY_WOOL:
/*      */       case LIME_BANNER:
/*      */       case LIME_CARPET:
/*      */       case LIME_WOOL:
/*      */       case LOOM:
/*      */       case MAGENTA_BANNER:
/*      */       case MAGENTA_CARPET:
/*      */       case MAGENTA_WOOL:
/*      */       case NOTE_BLOCK:
/*      */       case OAK_BUTTON:
/*      */       case OAK_DOOR:
/*      */       case OAK_FENCE:
/*      */       case OAK_FENCE_GATE:
/*      */       case OAK_LOG:
/*      */       case OAK_PLANKS:
/*      */       case OAK_PRESSURE_PLATE:
/*      */       case OAK_SAPLING:
/*      */       case OAK_SIGN:
/*      */       case OAK_SLAB:
/*      */       case OAK_STAIRS:
/*      */       case OAK_TRAPDOOR:
/*      */       case OAK_WOOD:
/*      */       case ORANGE_BANNER:
/*      */       case ORANGE_CARPET:
/*      */       case ORANGE_WOOL:
/*      */       case PINK_BANNER:
/*      */       case PINK_CARPET:
/*      */       case PINK_WOOL:
/*      */       case PURPLE_BANNER:
/*      */       case PURPLE_CARPET:
/*      */       case PURPLE_WOOL:
/*      */       case RED_BANNER:
/*      */       case RED_CARPET:
/*      */       case RED_WOOL:
/*      */       case SCAFFOLDING:
/*      */       case SMITHING_TABLE:
/*      */       case SPRUCE_BUTTON:
/*      */       case SPRUCE_DOOR:
/*      */       case SPRUCE_FENCE:
/*      */       case SPRUCE_FENCE_GATE:
/*      */       case SPRUCE_LOG:
/*      */       case SPRUCE_PLANKS:
/*      */       case SPRUCE_PRESSURE_PLATE:
/*      */       case SPRUCE_SAPLING:
/*      */       case SPRUCE_SIGN:
/*      */       case SPRUCE_SLAB:
/*      */       case SPRUCE_STAIRS:
/*      */       case SPRUCE_TRAPDOOR:
/*      */       case SPRUCE_WOOD:
/*      */       case STRIPPED_ACACIA_LOG:
/*      */       case STRIPPED_ACACIA_WOOD:
/*      */       case STRIPPED_BIRCH_LOG:
/*      */       case STRIPPED_BIRCH_WOOD:
/*      */       case STRIPPED_DARK_OAK_LOG:
/*      */       case STRIPPED_DARK_OAK_WOOD:
/*      */       case STRIPPED_JUNGLE_LOG:
/*      */       case STRIPPED_JUNGLE_WOOD:
/*      */       case STRIPPED_OAK_LOG:
/*      */       case STRIPPED_OAK_WOOD:
/*      */       case STRIPPED_SPRUCE_LOG:
/*      */       case STRIPPED_SPRUCE_WOOD:
/*      */       case TRAPPED_CHEST:
/*      */       case WHITE_BANNER:
/*      */       case WHITE_CARPET:
/*      */       case WHITE_WOOL:
/*      */       case YELLOW_BANNER:
/*      */       case YELLOW_CARPET:
/*      */       case YELLOW_WOOL:
/*      */       case LEGACY_WOOD:
/*      */       case LEGACY_LOG:
/*      */       case LEGACY_NOTE_BLOCK:
/*      */       case LEGACY_WOOL:
/*      */       case LEGACY_BOOKSHELF:
/*      */       case LEGACY_WOOD_STAIRS:
/*      */       case LEGACY_CHEST:
/*      */       case LEGACY_WORKBENCH:
/*      */       case LEGACY_WOOD_PLATE:
/*      */       case LEGACY_JUKEBOX:
/*      */       case LEGACY_FENCE:
/*      */       case LEGACY_TRAP_DOOR:
/*      */       case LEGACY_FENCE_GATE:
/*      */       case LEGACY_WOOD_STEP:
/*      */       case LEGACY_SPRUCE_WOOD_STAIRS:
/*      */       case LEGACY_BIRCH_WOOD_STAIRS:
/*      */       case LEGACY_JUNGLE_WOOD_STAIRS:
/*      */       case LEGACY_TRAPPED_CHEST:
/*      */       case LEGACY_DAYLIGHT_DETECTOR:
/*      */       case LEGACY_COAL_BLOCK:
/*      */       case LEGACY_LOG_2:
/*      */       case LEGACY_ACACIA_STAIRS:
/*      */       case LEGACY_DARK_OAK_STAIRS:
/*      */       case LEGACY_SPRUCE_FENCE_GATE:
/*      */       case LEGACY_BIRCH_FENCE_GATE:
/*      */       case LEGACY_JUNGLE_FENCE_GATE:
/*      */       case LEGACY_DARK_OAK_FENCE_GATE:
/*      */       case LEGACY_ACACIA_FENCE_GATE:
/*      */       case LEGACY_SPRUCE_FENCE:
/*      */       case LEGACY_BIRCH_FENCE:
/*      */       case LEGACY_JUNGLE_FENCE:
/*      */       case LEGACY_DARK_OAK_FENCE:
/*      */       case LEGACY_ACACIA_FENCE:
/*      */       case LEGACY_SAPLING:
/*      */       case LEGACY_LADDER:
/*      */       case LEGACY_WOOD_BUTTON:
/*      */       case LEGACY_CARPET:
/*      */       case ACACIA_BOAT:
/*      */       case BIRCH_BOAT:
/*      */       case BLAZE_ROD:
/*      */       case BOW:
/*      */       case BOWL:
/*      */       case CHARCOAL:
/*      */       case COAL:
/*      */       case CROSSBOW:
/*      */       case DARK_OAK_BOAT:
/*      */       case FISHING_ROD:
/*      */       case JUNGLE_BOAT:
/*      */       case LAVA_BUCKET:
/*      */       case OAK_BOAT:
/*      */       case SPRUCE_BOAT:
/*      */       case STICK:
/*      */       case WOODEN_AXE:
/*      */       case WOODEN_HOE:
/*      */       case WOODEN_PICKAXE:
/*      */       case WOODEN_SHOVEL:
/*      */       case WOODEN_SWORD:
/*      */       case LEGACY_LAVA_BUCKET:
/*      */       case LEGACY_BLAZE_ROD:
/*      */       case LEGACY_COAL:
/*      */       case LEGACY_BOAT:
/*      */       case LEGACY_BOAT_ACACIA:
/*      */       case LEGACY_BOAT_BIRCH:
/*      */       case LEGACY_BOAT_DARK_OAK:
/*      */       case LEGACY_BOAT_JUNGLE:
/*      */       case LEGACY_BOAT_SPRUCE:
/*      */       case LEGACY_BANNER:
/*      */       case LEGACY_FISHING_ROD:
/*      */       case LEGACY_WOOD_SWORD:
/*      */       case LEGACY_WOOD_PICKAXE:
/*      */       case LEGACY_WOOD_AXE:
/*      */       case LEGACY_WOOD_SPADE:
/*      */       case LEGACY_WOOD_HOE:
/*      */       case LEGACY_BOW:
/*      */       case LEGACY_SIGN:
/*      */       case LEGACY_WOOD_DOOR:
/*      */       case LEGACY_ACACIA_DOOR_ITEM:
/*      */       case LEGACY_BIRCH_DOOR_ITEM:
/*      */       case LEGACY_DARK_OAK_DOOR_ITEM:
/*      */       case LEGACY_JUNGLE_DOOR_ITEM:
/*      */       case LEGACY_SPRUCE_DOOR_ITEM:
/*      */       case LEGACY_STICK:
/*      */       case LEGACY_BOWL:
/* 6432 */         return true;
/*      */     } 
/* 6434 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOccluding() {
/* 6444 */     if (!isBlock()) {
/* 6445 */       return false;
/*      */     }
/* 6447 */     switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case ACACIA_LOG:
/*      */       case ACACIA_PLANKS:
/*      */       case ACACIA_WOOD:
/*      */       case ANCIENT_DEBRIS:
/*      */       case ANDESITE:
/*      */       case BARREL:
/*      */       case BARRIER:
/*      */       case BASALT:
/*      */       case BEDROCK:
/*      */       case BEEHIVE:
/*      */       case BEE_NEST:
/*      */       case BIRCH_LOG:
/*      */       case BIRCH_PLANKS:
/*      */       case BIRCH_WOOD:
/*      */       case BLACKSTONE:
/*      */       case BLACK_CONCRETE:
/*      */       case BLACK_CONCRETE_POWDER:
/*      */       case BLACK_GLAZED_TERRACOTTA:
/*      */       case BLACK_SHULKER_BOX:
/*      */       case BLACK_TERRACOTTA:
/*      */       case BLACK_WOOL:
/*      */       case BLAST_FURNACE:
/*      */       case BLUE_CONCRETE:
/*      */       case BLUE_CONCRETE_POWDER:
/*      */       case BLUE_GLAZED_TERRACOTTA:
/*      */       case BLUE_ICE:
/*      */       case BLUE_SHULKER_BOX:
/*      */       case BLUE_TERRACOTTA:
/*      */       case BLUE_WOOL:
/*      */       case BONE_BLOCK:
/*      */       case BOOKSHELF:
/*      */       case BRAIN_CORAL_BLOCK:
/*      */       case BRICKS:
/*      */       case BROWN_CONCRETE:
/*      */       case BROWN_CONCRETE_POWDER:
/*      */       case BROWN_GLAZED_TERRACOTTA:
/*      */       case BROWN_MUSHROOM_BLOCK:
/*      */       case BROWN_SHULKER_BOX:
/*      */       case BROWN_TERRACOTTA:
/*      */       case BROWN_WOOL:
/*      */       case BUBBLE_CORAL_BLOCK:
/*      */       case CARTOGRAPHY_TABLE:
/*      */       case CARVED_PUMPKIN:
/*      */       case CHAIN_COMMAND_BLOCK:
/*      */       case CHISELED_NETHER_BRICKS:
/*      */       case CHISELED_POLISHED_BLACKSTONE:
/*      */       case CHISELED_QUARTZ_BLOCK:
/*      */       case CHISELED_RED_SANDSTONE:
/*      */       case CHISELED_SANDSTONE:
/*      */       case CHISELED_STONE_BRICKS:
/*      */       case CLAY:
/*      */       case COAL_BLOCK:
/*      */       case COAL_ORE:
/*      */       case COARSE_DIRT:
/*      */       case COBBLESTONE:
/*      */       case COMMAND_BLOCK:
/*      */       case CRACKED_NETHER_BRICKS:
/*      */       case CRACKED_POLISHED_BLACKSTONE_BRICKS:
/*      */       case CRACKED_STONE_BRICKS:
/*      */       case CRAFTING_TABLE:
/*      */       case CRIMSON_HYPHAE:
/*      */       case CRIMSON_NYLIUM:
/*      */       case CRIMSON_PLANKS:
/*      */       case CRIMSON_STEM:
/*      */       case CRYING_OBSIDIAN:
/*      */       case CUT_RED_SANDSTONE:
/*      */       case CUT_SANDSTONE:
/*      */       case CYAN_CONCRETE:
/*      */       case CYAN_CONCRETE_POWDER:
/*      */       case CYAN_GLAZED_TERRACOTTA:
/*      */       case CYAN_SHULKER_BOX:
/*      */       case CYAN_TERRACOTTA:
/*      */       case CYAN_WOOL:
/*      */       case DARK_OAK_LOG:
/*      */       case DARK_OAK_PLANKS:
/*      */       case DARK_OAK_WOOD:
/*      */       case DARK_PRISMARINE:
/*      */       case DEAD_BRAIN_CORAL_BLOCK:
/*      */       case DEAD_BUBBLE_CORAL_BLOCK:
/*      */       case DEAD_FIRE_CORAL_BLOCK:
/*      */       case DEAD_HORN_CORAL_BLOCK:
/*      */       case DEAD_TUBE_CORAL_BLOCK:
/*      */       case DIAMOND_BLOCK:
/*      */       case DIAMOND_ORE:
/*      */       case DIORITE:
/*      */       case DIRT:
/*      */       case DISPENSER:
/*      */       case DRIED_KELP_BLOCK:
/*      */       case DROPPER:
/*      */       case EMERALD_BLOCK:
/*      */       case EMERALD_ORE:
/*      */       case END_STONE:
/*      */       case END_STONE_BRICKS:
/*      */       case FIRE_CORAL_BLOCK:
/*      */       case FLETCHING_TABLE:
/*      */       case FURNACE:
/*      */       case GILDED_BLACKSTONE:
/*      */       case GOLD_BLOCK:
/*      */       case GOLD_ORE:
/*      */       case GRANITE:
/*      */       case GRASS_BLOCK:
/*      */       case GRAVEL:
/*      */       case GRAY_CONCRETE:
/*      */       case GRAY_CONCRETE_POWDER:
/*      */       case GRAY_GLAZED_TERRACOTTA:
/*      */       case GRAY_SHULKER_BOX:
/*      */       case GRAY_TERRACOTTA:
/*      */       case GRAY_WOOL:
/*      */       case GREEN_CONCRETE:
/*      */       case GREEN_CONCRETE_POWDER:
/*      */       case GREEN_GLAZED_TERRACOTTA:
/*      */       case GREEN_SHULKER_BOX:
/*      */       case GREEN_TERRACOTTA:
/*      */       case GREEN_WOOL:
/*      */       case HAY_BLOCK:
/*      */       case HONEYCOMB_BLOCK:
/*      */       case HORN_CORAL_BLOCK:
/*      */       case INFESTED_CHISELED_STONE_BRICKS:
/*      */       case INFESTED_COBBLESTONE:
/*      */       case INFESTED_CRACKED_STONE_BRICKS:
/*      */       case INFESTED_MOSSY_STONE_BRICKS:
/*      */       case INFESTED_STONE:
/*      */       case INFESTED_STONE_BRICKS:
/*      */       case IRON_BLOCK:
/*      */       case IRON_ORE:
/*      */       case JACK_O_LANTERN:
/*      */       case JIGSAW:
/*      */       case JUKEBOX:
/*      */       case JUNGLE_LOG:
/*      */       case JUNGLE_PLANKS:
/*      */       case JUNGLE_WOOD:
/*      */       case LAPIS_BLOCK:
/*      */       case LAPIS_ORE:
/*      */       case LIGHT_BLUE_CONCRETE:
/*      */       case LIGHT_BLUE_CONCRETE_POWDER:
/*      */       case LIGHT_BLUE_GLAZED_TERRACOTTA:
/*      */       case LIGHT_BLUE_SHULKER_BOX:
/*      */       case LIGHT_BLUE_TERRACOTTA:
/*      */       case LIGHT_BLUE_WOOL:
/*      */       case LIGHT_GRAY_CONCRETE:
/*      */       case LIGHT_GRAY_CONCRETE_POWDER:
/*      */       case LIGHT_GRAY_GLAZED_TERRACOTTA:
/*      */       case LIGHT_GRAY_SHULKER_BOX:
/*      */       case LIGHT_GRAY_TERRACOTTA:
/*      */       case LIGHT_GRAY_WOOL:
/*      */       case LIME_CONCRETE:
/*      */       case LIME_CONCRETE_POWDER:
/*      */       case LIME_GLAZED_TERRACOTTA:
/*      */       case LIME_SHULKER_BOX:
/*      */       case LIME_TERRACOTTA:
/*      */       case LIME_WOOL:
/*      */       case LODESTONE:
/*      */       case LOOM:
/*      */       case MAGENTA_CONCRETE:
/*      */       case MAGENTA_CONCRETE_POWDER:
/*      */       case MAGENTA_GLAZED_TERRACOTTA:
/*      */       case MAGENTA_SHULKER_BOX:
/*      */       case MAGENTA_TERRACOTTA:
/*      */       case MAGENTA_WOOL:
/*      */       case MAGMA_BLOCK:
/*      */       case MELON:
/*      */       case MOSSY_COBBLESTONE:
/*      */       case MOSSY_STONE_BRICKS:
/*      */       case MUSHROOM_STEM:
/*      */       case MYCELIUM:
/*      */       case NETHERITE_BLOCK:
/*      */       case NETHERRACK:
/*      */       case NETHER_BRICKS:
/*      */       case NETHER_GOLD_ORE:
/*      */       case NETHER_QUARTZ_ORE:
/*      */       case NETHER_WART_BLOCK:
/*      */       case NOTE_BLOCK:
/*      */       case OAK_LOG:
/*      */       case OAK_PLANKS:
/*      */       case OAK_WOOD:
/*      */       case OBSIDIAN:
/*      */       case ORANGE_CONCRETE:
/*      */       case ORANGE_CONCRETE_POWDER:
/*      */       case ORANGE_GLAZED_TERRACOTTA:
/*      */       case ORANGE_SHULKER_BOX:
/*      */       case ORANGE_TERRACOTTA:
/*      */       case ORANGE_WOOL:
/*      */       case PACKED_ICE:
/*      */       case PINK_CONCRETE:
/*      */       case PINK_CONCRETE_POWDER:
/*      */       case PINK_GLAZED_TERRACOTTA:
/*      */       case PINK_SHULKER_BOX:
/*      */       case PINK_TERRACOTTA:
/*      */       case PINK_WOOL:
/*      */       case PODZOL:
/*      */       case POLISHED_ANDESITE:
/*      */       case POLISHED_BASALT:
/*      */       case POLISHED_BLACKSTONE:
/*      */       case POLISHED_BLACKSTONE_BRICKS:
/*      */       case POLISHED_DIORITE:
/*      */       case POLISHED_GRANITE:
/*      */       case PRISMARINE:
/*      */       case PRISMARINE_BRICKS:
/*      */       case PUMPKIN:
/*      */       case PURPLE_CONCRETE:
/*      */       case PURPLE_CONCRETE_POWDER:
/*      */       case PURPLE_GLAZED_TERRACOTTA:
/*      */       case PURPLE_SHULKER_BOX:
/*      */       case PURPLE_TERRACOTTA:
/*      */       case PURPLE_WOOL:
/*      */       case PURPUR_BLOCK:
/*      */       case PURPUR_PILLAR:
/*      */       case QUARTZ_BLOCK:
/*      */       case QUARTZ_BRICKS:
/*      */       case QUARTZ_PILLAR:
/*      */       case REDSTONE_LAMP:
/*      */       case REDSTONE_ORE:
/*      */       case RED_CONCRETE:
/*      */       case RED_CONCRETE_POWDER:
/*      */       case RED_GLAZED_TERRACOTTA:
/*      */       case RED_MUSHROOM_BLOCK:
/*      */       case RED_NETHER_BRICKS:
/*      */       case RED_SAND:
/*      */       case RED_SANDSTONE:
/*      */       case RED_SHULKER_BOX:
/*      */       case RED_TERRACOTTA:
/*      */       case RED_WOOL:
/*      */       case REPEATING_COMMAND_BLOCK:
/*      */       case RESPAWN_ANCHOR:
/*      */       case SAND:
/*      */       case SANDSTONE:
/*      */       case SHROOMLIGHT:
/*      */       case SHULKER_BOX:
/*      */       case SLIME_BLOCK:
/*      */       case SMITHING_TABLE:
/*      */       case SMOKER:
/*      */       case SMOOTH_QUARTZ:
/*      */       case SMOOTH_RED_SANDSTONE:
/*      */       case SMOOTH_SANDSTONE:
/*      */       case SMOOTH_STONE:
/*      */       case SNOW_BLOCK:
/*      */       case SOUL_SAND:
/*      */       case SOUL_SOIL:
/*      */       case SPAWNER:
/*      */       case SPONGE:
/*      */       case SPRUCE_LOG:
/*      */       case SPRUCE_PLANKS:
/*      */       case SPRUCE_WOOD:
/*      */       case STONE:
/*      */       case STONE_BRICKS:
/*      */       case STRIPPED_ACACIA_LOG:
/*      */       case STRIPPED_ACACIA_WOOD:
/*      */       case STRIPPED_BIRCH_LOG:
/*      */       case STRIPPED_BIRCH_WOOD:
/*      */       case STRIPPED_CRIMSON_HYPHAE:
/*      */       case STRIPPED_CRIMSON_STEM:
/*      */       case STRIPPED_DARK_OAK_LOG:
/*      */       case STRIPPED_DARK_OAK_WOOD:
/*      */       case STRIPPED_JUNGLE_LOG:
/*      */       case STRIPPED_JUNGLE_WOOD:
/*      */       case STRIPPED_OAK_LOG:
/*      */       case STRIPPED_OAK_WOOD:
/*      */       case STRIPPED_SPRUCE_LOG:
/*      */       case STRIPPED_SPRUCE_WOOD:
/*      */       case STRIPPED_WARPED_HYPHAE:
/*      */       case STRIPPED_WARPED_STEM:
/*      */       case STRUCTURE_BLOCK:
/*      */       case TARGET:
/*      */       case TERRACOTTA:
/*      */       case TUBE_CORAL_BLOCK:
/*      */       case WARPED_HYPHAE:
/*      */       case WARPED_NYLIUM:
/*      */       case WARPED_PLANKS:
/*      */       case WARPED_STEM:
/*      */       case WARPED_WART_BLOCK:
/*      */       case WET_SPONGE:
/*      */       case WHITE_CONCRETE:
/*      */       case WHITE_CONCRETE_POWDER:
/*      */       case WHITE_GLAZED_TERRACOTTA:
/*      */       case WHITE_SHULKER_BOX:
/*      */       case WHITE_TERRACOTTA:
/*      */       case WHITE_WOOL:
/*      */       case YELLOW_CONCRETE:
/*      */       case YELLOW_CONCRETE_POWDER:
/*      */       case YELLOW_GLAZED_TERRACOTTA:
/*      */       case YELLOW_SHULKER_BOX:
/*      */       case YELLOW_TERRACOTTA:
/*      */       case YELLOW_WOOL:
/*      */       case LEGACY_STONE:
/*      */       case LEGACY_GRASS:
/*      */       case LEGACY_DIRT:
/*      */       case LEGACY_COBBLESTONE:
/*      */       case LEGACY_WOOD:
/*      */       case LEGACY_BEDROCK:
/*      */       case LEGACY_SAND:
/*      */       case LEGACY_GRAVEL:
/*      */       case LEGACY_GOLD_ORE:
/*      */       case LEGACY_IRON_ORE:
/*      */       case LEGACY_COAL_ORE:
/*      */       case LEGACY_LOG:
/*      */       case LEGACY_SPONGE:
/*      */       case LEGACY_LAPIS_ORE:
/*      */       case LEGACY_LAPIS_BLOCK:
/*      */       case LEGACY_DISPENSER:
/*      */       case LEGACY_SANDSTONE:
/*      */       case LEGACY_NOTE_BLOCK:
/*      */       case LEGACY_WOOL:
/*      */       case LEGACY_GOLD_BLOCK:
/*      */       case LEGACY_IRON_BLOCK:
/*      */       case LEGACY_DOUBLE_STEP:
/*      */       case LEGACY_BRICK:
/*      */       case LEGACY_BOOKSHELF:
/*      */       case LEGACY_MOSSY_COBBLESTONE:
/*      */       case LEGACY_OBSIDIAN:
/*      */       case LEGACY_MOB_SPAWNER:
/*      */       case LEGACY_DIAMOND_ORE:
/*      */       case LEGACY_DIAMOND_BLOCK:
/*      */       case LEGACY_WORKBENCH:
/*      */       case LEGACY_FURNACE:
/*      */       case LEGACY_BURNING_FURNACE:
/*      */       case LEGACY_REDSTONE_ORE:
/*      */       case LEGACY_GLOWING_REDSTONE_ORE:
/*      */       case LEGACY_SNOW_BLOCK:
/*      */       case LEGACY_CLAY:
/*      */       case LEGACY_JUKEBOX:
/*      */       case LEGACY_PUMPKIN:
/*      */       case LEGACY_NETHERRACK:
/*      */       case LEGACY_SOUL_SAND:
/*      */       case LEGACY_JACK_O_LANTERN:
/*      */       case LEGACY_MONSTER_EGGS:
/*      */       case LEGACY_SMOOTH_BRICK:
/*      */       case LEGACY_HUGE_MUSHROOM_1:
/*      */       case LEGACY_HUGE_MUSHROOM_2:
/*      */       case LEGACY_MELON_BLOCK:
/*      */       case LEGACY_MYCEL:
/*      */       case LEGACY_NETHER_BRICK:
/*      */       case LEGACY_ENDER_STONE:
/*      */       case LEGACY_REDSTONE_LAMP_OFF:
/*      */       case LEGACY_REDSTONE_LAMP_ON:
/*      */       case LEGACY_WOOD_DOUBLE_STEP:
/*      */       case LEGACY_EMERALD_ORE:
/*      */       case LEGACY_EMERALD_BLOCK:
/*      */       case LEGACY_COMMAND:
/*      */       case LEGACY_QUARTZ_ORE:
/*      */       case LEGACY_QUARTZ_BLOCK:
/*      */       case LEGACY_DROPPER:
/*      */       case LEGACY_STAINED_CLAY:
/*      */       case LEGACY_HAY_BLOCK:
/*      */       case LEGACY_HARD_CLAY:
/*      */       case LEGACY_COAL_BLOCK:
/*      */       case LEGACY_LOG_2:
/*      */       case LEGACY_PACKED_ICE:
/*      */       case LEGACY_RED_SANDSTONE:
/*      */       case LEGACY_SLIME_BLOCK:
/*      */       case LEGACY_BARRIER:
/*      */       case LEGACY_PRISMARINE:
/*      */       case LEGACY_DOUBLE_STONE_SLAB2:
/*      */       case LEGACY_PURPUR_BLOCK:
/*      */       case LEGACY_PURPUR_PILLAR:
/*      */       case LEGACY_PURPUR_DOUBLE_SLAB:
/*      */       case LEGACY_END_BRICKS:
/*      */       case LEGACY_STRUCTURE_BLOCK:
/*      */       case LEGACY_COMMAND_REPEATING:
/*      */       case LEGACY_COMMAND_CHAIN:
/*      */       case LEGACY_MAGMA:
/*      */       case LEGACY_NETHER_WART_BLOCK:
/*      */       case LEGACY_RED_NETHER_BRICK:
/*      */       case LEGACY_BONE_BLOCK:
/*      */       case LEGACY_WHITE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_ORANGE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_MAGENTA_GLAZED_TERRACOTTA:
/*      */       case LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_YELLOW_GLAZED_TERRACOTTA:
/*      */       case LEGACY_LIME_GLAZED_TERRACOTTA:
/*      */       case LEGACY_PINK_GLAZED_TERRACOTTA:
/*      */       case LEGACY_GRAY_GLAZED_TERRACOTTA:
/*      */       case LEGACY_SILVER_GLAZED_TERRACOTTA:
/*      */       case LEGACY_CYAN_GLAZED_TERRACOTTA:
/*      */       case LEGACY_PURPLE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_BLUE_GLAZED_TERRACOTTA:
/*      */       case LEGACY_BROWN_GLAZED_TERRACOTTA:
/*      */       case LEGACY_GREEN_GLAZED_TERRACOTTA:
/*      */       case LEGACY_RED_GLAZED_TERRACOTTA:
/*      */       case LEGACY_BLACK_GLAZED_TERRACOTTA:
/*      */       case LEGACY_CONCRETE:
/*      */       case LEGACY_CONCRETE_POWDER:
/* 6832 */         return true;
/*      */     } 
/* 6834 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasGravity() {
/* 6842 */     if (!isBlock()) {
/* 6843 */       return false;
/*      */     }
/* 6845 */     switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case ANVIL:
/*      */       case BLACK_CONCRETE_POWDER:
/*      */       case BLUE_CONCRETE_POWDER:
/*      */       case BROWN_CONCRETE_POWDER:
/*      */       case CHIPPED_ANVIL:
/*      */       case CYAN_CONCRETE_POWDER:
/*      */       case DAMAGED_ANVIL:
/*      */       case DRAGON_EGG:
/*      */       case GRAVEL:
/*      */       case GRAY_CONCRETE_POWDER:
/*      */       case GREEN_CONCRETE_POWDER:
/*      */       case LIGHT_BLUE_CONCRETE_POWDER:
/*      */       case LIGHT_GRAY_CONCRETE_POWDER:
/*      */       case LIME_CONCRETE_POWDER:
/*      */       case MAGENTA_CONCRETE_POWDER:
/*      */       case ORANGE_CONCRETE_POWDER:
/*      */       case PINK_CONCRETE_POWDER:
/*      */       case PURPLE_CONCRETE_POWDER:
/*      */       case RED_CONCRETE_POWDER:
/*      */       case RED_SAND:
/*      */       case SAND:
/*      */       case WHITE_CONCRETE_POWDER:
/*      */       case YELLOW_CONCRETE_POWDER:
/*      */       case LEGACY_SAND:
/*      */       case LEGACY_GRAVEL:
/*      */       case LEGACY_ANVIL:
/*      */       case LEGACY_CONCRETE_POWDER:
/* 6876 */         return true;
/*      */     } 
/* 6878 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItem() {
/* 6888 */     switch (this) {
/*      */ 
/*      */ 
/*      */       
/*      */       case CAVE_AIR:
/*      */       case VOID_AIR:
/*      */       case ACACIA_WALL_SIGN:
/*      */       case ATTACHED_MELON_STEM:
/*      */       case ATTACHED_PUMPKIN_STEM:
/*      */       case BAMBOO_SAPLING:
/*      */       case BEETROOTS:
/*      */       case BIRCH_WALL_SIGN:
/*      */       case BLACK_WALL_BANNER:
/*      */       case BLUE_WALL_BANNER:
/*      */       case BRAIN_CORAL_WALL_FAN:
/*      */       case BROWN_WALL_BANNER:
/*      */       case BUBBLE_COLUMN:
/*      */       case BUBBLE_CORAL_WALL_FAN:
/*      */       case CARROTS:
/*      */       case COCOA:
/*      */       case CREEPER_WALL_HEAD:
/*      */       case CRIMSON_WALL_SIGN:
/*      */       case CYAN_WALL_BANNER:
/*      */       case DARK_OAK_WALL_SIGN:
/*      */       case DEAD_BRAIN_CORAL_WALL_FAN:
/*      */       case DEAD_BUBBLE_CORAL_WALL_FAN:
/*      */       case DEAD_FIRE_CORAL_WALL_FAN:
/*      */       case DEAD_HORN_CORAL_WALL_FAN:
/*      */       case DEAD_TUBE_CORAL_WALL_FAN:
/*      */       case DRAGON_WALL_HEAD:
/*      */       case END_GATEWAY:
/*      */       case END_PORTAL:
/*      */       case FIRE:
/*      */       case FIRE_CORAL_WALL_FAN:
/*      */       case FROSTED_ICE:
/*      */       case GRAY_WALL_BANNER:
/*      */       case GREEN_WALL_BANNER:
/*      */       case HORN_CORAL_WALL_FAN:
/*      */       case JUNGLE_WALL_SIGN:
/*      */       case KELP_PLANT:
/*      */       case LAVA:
/*      */       case LIGHT_BLUE_WALL_BANNER:
/*      */       case LIGHT_GRAY_WALL_BANNER:
/*      */       case LIME_WALL_BANNER:
/*      */       case MAGENTA_WALL_BANNER:
/*      */       case MELON_STEM:
/*      */       case MOVING_PISTON:
/*      */       case NETHER_PORTAL:
/*      */       case OAK_WALL_SIGN:
/*      */       case ORANGE_WALL_BANNER:
/*      */       case PINK_WALL_BANNER:
/*      */       case PISTON_HEAD:
/*      */       case PLAYER_WALL_HEAD:
/*      */       case POTATOES:
/*      */       case POTTED_ACACIA_SAPLING:
/*      */       case POTTED_ALLIUM:
/*      */       case POTTED_AZURE_BLUET:
/*      */       case POTTED_BAMBOO:
/*      */       case POTTED_BIRCH_SAPLING:
/*      */       case POTTED_BLUE_ORCHID:
/*      */       case POTTED_BROWN_MUSHROOM:
/*      */       case POTTED_CACTUS:
/*      */       case POTTED_CORNFLOWER:
/*      */       case POTTED_CRIMSON_FUNGUS:
/*      */       case POTTED_CRIMSON_ROOTS:
/*      */       case POTTED_DANDELION:
/*      */       case POTTED_DARK_OAK_SAPLING:
/*      */       case POTTED_DEAD_BUSH:
/*      */       case POTTED_FERN:
/*      */       case POTTED_JUNGLE_SAPLING:
/*      */       case POTTED_LILY_OF_THE_VALLEY:
/*      */       case POTTED_OAK_SAPLING:
/*      */       case POTTED_ORANGE_TULIP:
/*      */       case POTTED_OXEYE_DAISY:
/*      */       case POTTED_PINK_TULIP:
/*      */       case POTTED_POPPY:
/*      */       case POTTED_RED_MUSHROOM:
/*      */       case POTTED_RED_TULIP:
/*      */       case POTTED_SPRUCE_SAPLING:
/*      */       case POTTED_WARPED_FUNGUS:
/*      */       case POTTED_WARPED_ROOTS:
/*      */       case POTTED_WHITE_TULIP:
/*      */       case POTTED_WITHER_ROSE:
/*      */       case PUMPKIN_STEM:
/*      */       case PURPLE_WALL_BANNER:
/*      */       case REDSTONE_WALL_TORCH:
/*      */       case REDSTONE_WIRE:
/*      */       case RED_WALL_BANNER:
/*      */       case SKELETON_WALL_SKULL:
/*      */       case SOUL_FIRE:
/*      */       case SOUL_WALL_TORCH:
/*      */       case SPRUCE_WALL_SIGN:
/*      */       case SWEET_BERRY_BUSH:
/*      */       case TALL_SEAGRASS:
/*      */       case TRIPWIRE:
/*      */       case TUBE_CORAL_WALL_FAN:
/*      */       case TWISTING_VINES_PLANT:
/*      */       case WALL_TORCH:
/*      */       case WARPED_WALL_SIGN:
/*      */       case WATER:
/*      */       case WEEPING_VINES_PLANT:
/*      */       case WHITE_WALL_BANNER:
/*      */       case WITHER_SKELETON_WALL_SKULL:
/*      */       case YELLOW_WALL_BANNER:
/*      */       case ZOMBIE_WALL_HEAD:
/*      */       case LEGACY_BED_BLOCK:
/*      */       case LEGACY_PISTON_EXTENSION:
/*      */       case LEGACY_PISTON_MOVING_PIECE:
/*      */       case LEGACY_DOUBLE_STEP:
/*      */       case LEGACY_BURNING_FURNACE:
/*      */       case LEGACY_SIGN_POST:
/*      */       case LEGACY_WOODEN_DOOR:
/*      */       case LEGACY_WALL_SIGN:
/*      */       case LEGACY_IRON_DOOR_BLOCK:
/*      */       case LEGACY_GLOWING_REDSTONE_ORE:
/*      */       case LEGACY_CAKE_BLOCK:
/*      */       case LEGACY_BREWING_STAND:
/*      */       case LEGACY_CAULDRON:
/*      */       case LEGACY_REDSTONE_LAMP_ON:
/*      */       case LEGACY_WOOD_DOUBLE_STEP:
/*      */       case LEGACY_DOUBLE_STONE_SLAB2:
/*      */       case LEGACY_STANDING_BANNER:
/*      */       case LEGACY_WALL_BANNER:
/*      */       case LEGACY_DAYLIGHT_DETECTOR_INVERTED:
/*      */       case LEGACY_SPRUCE_DOOR:
/*      */       case LEGACY_BIRCH_DOOR:
/*      */       case LEGACY_JUNGLE_DOOR:
/*      */       case LEGACY_ACACIA_DOOR:
/*      */       case LEGACY_DARK_OAK_DOOR:
/*      */       case LEGACY_PURPUR_DOUBLE_SLAB:
/*      */       case LEGACY_FROSTED_ICE:
/*      */       case LEGACY_FIRE:
/*      */       case LEGACY_REDSTONE_WIRE:
/*      */       case LEGACY_CROPS:
/*      */       case LEGACY_REDSTONE_TORCH_OFF:
/*      */       case LEGACY_SUGAR_CANE_BLOCK:
/*      */       case LEGACY_PORTAL:
/*      */       case LEGACY_DIODE_BLOCK_OFF:
/*      */       case LEGACY_DIODE_BLOCK_ON:
/*      */       case LEGACY_PUMPKIN_STEM:
/*      */       case LEGACY_MELON_STEM:
/*      */       case LEGACY_NETHER_WARTS:
/*      */       case LEGACY_ENDER_PORTAL:
/*      */       case LEGACY_COCOA:
/*      */       case LEGACY_TRIPWIRE:
/*      */       case LEGACY_FLOWER_POT:
/*      */       case LEGACY_CARROT:
/*      */       case LEGACY_POTATO:
/*      */       case LEGACY_SKULL:
/*      */       case LEGACY_REDSTONE_COMPARATOR_OFF:
/*      */       case LEGACY_REDSTONE_COMPARATOR_ON:
/*      */       case LEGACY_BEETROOT_BLOCK:
/*      */       case LEGACY_END_GATEWAY:
/*      */       case LEGACY_LAVA:
/*      */       case LEGACY_STATIONARY_LAVA:
/*      */       case LEGACY_STATIONARY_WATER:
/*      */       case LEGACY_WATER:
/* 7045 */         return false;
/*      */     } 
/* 7047 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInteractable() {
/* 7068 */     switch (this) {
/*      */ 
/*      */       
/*      */       case ACACIA_BUTTON:
/*      */       case ACACIA_DOOR:
/*      */       case ACACIA_FENCE:
/*      */       case ACACIA_FENCE_GATE:
/*      */       case ACACIA_SIGN:
/*      */       case ACACIA_STAIRS:
/*      */       case ACACIA_TRAPDOOR:
/*      */       case ACACIA_WALL_SIGN:
/*      */       case ANDESITE_STAIRS:
/*      */       case ANVIL:
/*      */       case BARREL:
/*      */       case BEACON:
/*      */       case BEEHIVE:
/*      */       case BEE_NEST:
/*      */       case BELL:
/*      */       case BIRCH_BUTTON:
/*      */       case BIRCH_DOOR:
/*      */       case BIRCH_FENCE:
/*      */       case BIRCH_FENCE_GATE:
/*      */       case BIRCH_SIGN:
/*      */       case BIRCH_STAIRS:
/*      */       case BIRCH_TRAPDOOR:
/*      */       case BIRCH_WALL_SIGN:
/*      */       case BLACKSTONE_STAIRS:
/*      */       case BLACK_BED:
/*      */       case BLACK_SHULKER_BOX:
/*      */       case BLAST_FURNACE:
/*      */       case BLUE_BED:
/*      */       case BLUE_SHULKER_BOX:
/*      */       case BREWING_STAND:
/*      */       case BRICK_STAIRS:
/*      */       case BROWN_BED:
/*      */       case BROWN_SHULKER_BOX:
/*      */       case CAKE:
/*      */       case CAMPFIRE:
/*      */       case CARTOGRAPHY_TABLE:
/*      */       case CAULDRON:
/*      */       case CHAIN_COMMAND_BLOCK:
/*      */       case CHEST:
/*      */       case CHIPPED_ANVIL:
/*      */       case COBBLESTONE_STAIRS:
/*      */       case COMMAND_BLOCK:
/*      */       case COMPARATOR:
/*      */       case COMPOSTER:
/*      */       case CRAFTING_TABLE:
/*      */       case CRIMSON_BUTTON:
/*      */       case CRIMSON_DOOR:
/*      */       case CRIMSON_FENCE:
/*      */       case CRIMSON_FENCE_GATE:
/*      */       case CRIMSON_SIGN:
/*      */       case CRIMSON_STAIRS:
/*      */       case CRIMSON_TRAPDOOR:
/*      */       case CRIMSON_WALL_SIGN:
/*      */       case CYAN_BED:
/*      */       case CYAN_SHULKER_BOX:
/*      */       case DAMAGED_ANVIL:
/*      */       case DARK_OAK_BUTTON:
/*      */       case DARK_OAK_DOOR:
/*      */       case DARK_OAK_FENCE:
/*      */       case DARK_OAK_FENCE_GATE:
/*      */       case DARK_OAK_SIGN:
/*      */       case DARK_OAK_STAIRS:
/*      */       case DARK_OAK_TRAPDOOR:
/*      */       case DARK_OAK_WALL_SIGN:
/*      */       case DARK_PRISMARINE_STAIRS:
/*      */       case DAYLIGHT_DETECTOR:
/*      */       case DIORITE_STAIRS:
/*      */       case DISPENSER:
/*      */       case DRAGON_EGG:
/*      */       case DROPPER:
/*      */       case ENCHANTING_TABLE:
/*      */       case ENDER_CHEST:
/*      */       case END_STONE_BRICK_STAIRS:
/*      */       case FLETCHING_TABLE:
/*      */       case FLOWER_POT:
/*      */       case FURNACE:
/*      */       case GRANITE_STAIRS:
/*      */       case GRAY_BED:
/*      */       case GRAY_SHULKER_BOX:
/*      */       case GREEN_BED:
/*      */       case GREEN_SHULKER_BOX:
/*      */       case GRINDSTONE:
/*      */       case HOPPER:
/*      */       case IRON_DOOR:
/*      */       case IRON_TRAPDOOR:
/*      */       case JIGSAW:
/*      */       case JUKEBOX:
/*      */       case JUNGLE_BUTTON:
/*      */       case JUNGLE_DOOR:
/*      */       case JUNGLE_FENCE:
/*      */       case JUNGLE_FENCE_GATE:
/*      */       case JUNGLE_SIGN:
/*      */       case JUNGLE_STAIRS:
/*      */       case JUNGLE_TRAPDOOR:
/*      */       case JUNGLE_WALL_SIGN:
/*      */       case LECTERN:
/*      */       case LEVER:
/*      */       case LIGHT_BLUE_BED:
/*      */       case LIGHT_BLUE_SHULKER_BOX:
/*      */       case LIGHT_GRAY_BED:
/*      */       case LIGHT_GRAY_SHULKER_BOX:
/*      */       case LIME_BED:
/*      */       case LIME_SHULKER_BOX:
/*      */       case LOOM:
/*      */       case MAGENTA_BED:
/*      */       case MAGENTA_SHULKER_BOX:
/*      */       case MOSSY_COBBLESTONE_STAIRS:
/*      */       case MOSSY_STONE_BRICK_STAIRS:
/*      */       case MOVING_PISTON:
/*      */       case NETHER_BRICK_FENCE:
/*      */       case NETHER_BRICK_STAIRS:
/*      */       case NOTE_BLOCK:
/*      */       case OAK_BUTTON:
/*      */       case OAK_DOOR:
/*      */       case OAK_FENCE:
/*      */       case OAK_FENCE_GATE:
/*      */       case OAK_SIGN:
/*      */       case OAK_STAIRS:
/*      */       case OAK_TRAPDOOR:
/*      */       case OAK_WALL_SIGN:
/*      */       case ORANGE_BED:
/*      */       case ORANGE_SHULKER_BOX:
/*      */       case PINK_BED:
/*      */       case PINK_SHULKER_BOX:
/*      */       case POLISHED_ANDESITE_STAIRS:
/*      */       case POLISHED_BLACKSTONE_BRICK_STAIRS:
/*      */       case POLISHED_BLACKSTONE_BUTTON:
/*      */       case POLISHED_BLACKSTONE_STAIRS:
/*      */       case POLISHED_DIORITE_STAIRS:
/*      */       case POLISHED_GRANITE_STAIRS:
/*      */       case POTTED_ACACIA_SAPLING:
/*      */       case POTTED_ALLIUM:
/*      */       case POTTED_AZURE_BLUET:
/*      */       case POTTED_BAMBOO:
/*      */       case POTTED_BIRCH_SAPLING:
/*      */       case POTTED_BLUE_ORCHID:
/*      */       case POTTED_BROWN_MUSHROOM:
/*      */       case POTTED_CACTUS:
/*      */       case POTTED_CORNFLOWER:
/*      */       case POTTED_CRIMSON_FUNGUS:
/*      */       case POTTED_CRIMSON_ROOTS:
/*      */       case POTTED_DANDELION:
/*      */       case POTTED_DARK_OAK_SAPLING:
/*      */       case POTTED_DEAD_BUSH:
/*      */       case POTTED_FERN:
/*      */       case POTTED_JUNGLE_SAPLING:
/*      */       case POTTED_LILY_OF_THE_VALLEY:
/*      */       case POTTED_OAK_SAPLING:
/*      */       case POTTED_ORANGE_TULIP:
/*      */       case POTTED_OXEYE_DAISY:
/*      */       case POTTED_PINK_TULIP:
/*      */       case POTTED_POPPY:
/*      */       case POTTED_RED_MUSHROOM:
/*      */       case POTTED_RED_TULIP:
/*      */       case POTTED_SPRUCE_SAPLING:
/*      */       case POTTED_WARPED_FUNGUS:
/*      */       case POTTED_WARPED_ROOTS:
/*      */       case POTTED_WHITE_TULIP:
/*      */       case POTTED_WITHER_ROSE:
/*      */       case PRISMARINE_BRICK_STAIRS:
/*      */       case PRISMARINE_STAIRS:
/*      */       case PUMPKIN:
/*      */       case PURPLE_BED:
/*      */       case PURPLE_SHULKER_BOX:
/*      */       case PURPUR_STAIRS:
/*      */       case QUARTZ_STAIRS:
/*      */       case REDSTONE_ORE:
/*      */       case REDSTONE_WIRE:
/*      */       case RED_BED:
/*      */       case RED_NETHER_BRICK_STAIRS:
/*      */       case RED_SANDSTONE_STAIRS:
/*      */       case RED_SHULKER_BOX:
/*      */       case REPEATER:
/*      */       case REPEATING_COMMAND_BLOCK:
/*      */       case RESPAWN_ANCHOR:
/*      */       case SANDSTONE_STAIRS:
/*      */       case SHULKER_BOX:
/*      */       case SMITHING_TABLE:
/*      */       case SMOKER:
/*      */       case SMOOTH_QUARTZ_STAIRS:
/*      */       case SMOOTH_RED_SANDSTONE_STAIRS:
/*      */       case SMOOTH_SANDSTONE_STAIRS:
/*      */       case SOUL_CAMPFIRE:
/*      */       case SPRUCE_BUTTON:
/*      */       case SPRUCE_DOOR:
/*      */       case SPRUCE_FENCE:
/*      */       case SPRUCE_FENCE_GATE:
/*      */       case SPRUCE_SIGN:
/*      */       case SPRUCE_STAIRS:
/*      */       case SPRUCE_TRAPDOOR:
/*      */       case SPRUCE_WALL_SIGN:
/*      */       case STONECUTTER:
/*      */       case STONE_BRICK_STAIRS:
/*      */       case STONE_BUTTON:
/*      */       case STONE_STAIRS:
/*      */       case STRUCTURE_BLOCK:
/*      */       case SWEET_BERRY_BUSH:
/*      */       case TNT:
/*      */       case TRAPPED_CHEST:
/*      */       case WARPED_BUTTON:
/*      */       case WARPED_DOOR:
/*      */       case WARPED_FENCE:
/*      */       case WARPED_FENCE_GATE:
/*      */       case WARPED_SIGN:
/*      */       case WARPED_STAIRS:
/*      */       case WARPED_TRAPDOOR:
/*      */       case WARPED_WALL_SIGN:
/*      */       case WHITE_BED:
/*      */       case WHITE_SHULKER_BOX:
/*      */       case YELLOW_BED:
/*      */       case YELLOW_SHULKER_BOX:
/* 7282 */         return true;
/*      */     } 
/* 7284 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHardness() {
/* 7298 */     Validate.isTrue(isBlock(), "The Material is not a block!");
/* 7299 */     switch (this) {
/*      */       
/*      */       case BARRIER:
/*      */       case BEDROCK:
/*      */       case CHAIN_COMMAND_BLOCK:
/*      */       case COMMAND_BLOCK:
/*      */       case END_GATEWAY:
/*      */       case END_PORTAL:
/*      */       case END_PORTAL_FRAME:
/*      */       case JIGSAW:
/*      */       case MOVING_PISTON:
/*      */       case NETHER_PORTAL:
/*      */       case REPEATING_COMMAND_BLOCK:
/*      */       case STRUCTURE_BLOCK:
/* 7313 */         return -1.0F;
/*      */       case BLACK_CARPET:
/*      */       case BLUE_CARPET:
/*      */       case BROWN_CARPET:
/*      */       case CYAN_CARPET:
/*      */       case GRAY_CARPET:
/*      */       case GREEN_CARPET:
/*      */       case LIGHT_BLUE_CARPET:
/*      */       case LIGHT_GRAY_CARPET:
/*      */       case LIME_CARPET:
/*      */       case MAGENTA_CARPET:
/*      */       case ORANGE_CARPET:
/*      */       case PINK_CARPET:
/*      */       case PURPLE_CARPET:
/*      */       case RED_CARPET:
/*      */       case SNOW:
/*      */       case WHITE_CARPET:
/*      */       case YELLOW_CARPET:
/* 7331 */         return 0.1F;
/*      */       case ACACIA_LEAVES:
/*      */       case BIRCH_LEAVES:
/*      */       case BLACK_BED:
/*      */       case BLUE_BED:
/*      */       case BROWN_BED:
/*      */       case BROWN_MUSHROOM_BLOCK:
/*      */       case COCOA:
/*      */       case CYAN_BED:
/*      */       case DARK_OAK_LEAVES:
/*      */       case DAYLIGHT_DETECTOR:
/*      */       case GRAY_BED:
/*      */       case GREEN_BED:
/*      */       case JUNGLE_LEAVES:
/*      */       case LIGHT_BLUE_BED:
/*      */       case LIGHT_GRAY_BED:
/*      */       case LIME_BED:
/*      */       case MAGENTA_BED:
/*      */       case MUSHROOM_STEM:
/*      */       case OAK_LEAVES:
/*      */       case ORANGE_BED:
/*      */       case PINK_BED:
/*      */       case PURPLE_BED:
/*      */       case RED_BED:
/*      */       case RED_MUSHROOM_BLOCK:
/*      */       case SNOW_BLOCK:
/*      */       case SPRUCE_LEAVES:
/*      */       case VINE:
/*      */       case WHITE_BED:
/*      */       case YELLOW_BED:
/* 7361 */         return 0.2F;
/*      */       case BEE_NEST:
/*      */       case BLACK_STAINED_GLASS:
/*      */       case BLACK_STAINED_GLASS_PANE:
/*      */       case BLUE_STAINED_GLASS:
/*      */       case BLUE_STAINED_GLASS_PANE:
/*      */       case BROWN_STAINED_GLASS:
/*      */       case BROWN_STAINED_GLASS_PANE:
/*      */       case CYAN_STAINED_GLASS:
/*      */       case CYAN_STAINED_GLASS_PANE:
/*      */       case GLASS:
/*      */       case GLASS_PANE:
/*      */       case GLOWSTONE:
/*      */       case GRAY_STAINED_GLASS:
/*      */       case GRAY_STAINED_GLASS_PANE:
/*      */       case GREEN_STAINED_GLASS:
/*      */       case GREEN_STAINED_GLASS_PANE:
/*      */       case LIGHT_BLUE_STAINED_GLASS:
/*      */       case LIGHT_BLUE_STAINED_GLASS_PANE:
/*      */       case LIGHT_GRAY_STAINED_GLASS:
/*      */       case LIGHT_GRAY_STAINED_GLASS_PANE:
/*      */       case LIME_STAINED_GLASS:
/*      */       case LIME_STAINED_GLASS_PANE:
/*      */       case MAGENTA_STAINED_GLASS:
/*      */       case MAGENTA_STAINED_GLASS_PANE:
/*      */       case ORANGE_STAINED_GLASS:
/*      */       case ORANGE_STAINED_GLASS_PANE:
/*      */       case PINK_STAINED_GLASS:
/*      */       case PINK_STAINED_GLASS_PANE:
/*      */       case PURPLE_STAINED_GLASS:
/*      */       case PURPLE_STAINED_GLASS_PANE:
/*      */       case REDSTONE_LAMP:
/*      */       case RED_STAINED_GLASS:
/*      */       case RED_STAINED_GLASS_PANE:
/*      */       case SEA_LANTERN:
/*      */       case WHITE_STAINED_GLASS:
/*      */       case WHITE_STAINED_GLASS_PANE:
/*      */       case YELLOW_STAINED_GLASS:
/*      */       case YELLOW_STAINED_GLASS_PANE:
/* 7400 */         return 0.3F;
/*      */       case CACTUS:
/*      */       case CHORUS_FLOWER:
/*      */       case CHORUS_PLANT:
/*      */       case CRIMSON_NYLIUM:
/*      */       case LADDER:
/*      */       case NETHERRACK:
/*      */       case WARPED_NYLIUM:
/* 7408 */         return 0.4F;
/*      */       case ACACIA_BUTTON:
/*      */       case ACACIA_PRESSURE_PLATE:
/*      */       case BIRCH_BUTTON:
/*      */       case BIRCH_PRESSURE_PLATE:
/*      */       case BLACK_CONCRETE_POWDER:
/*      */       case BLUE_CONCRETE_POWDER:
/*      */       case BREWING_STAND:
/*      */       case BROWN_CONCRETE_POWDER:
/*      */       case CAKE:
/*      */       case COARSE_DIRT:
/*      */       case CRIMSON_BUTTON:
/*      */       case CRIMSON_PRESSURE_PLATE:
/*      */       case CYAN_CONCRETE_POWDER:
/*      */       case DARK_OAK_BUTTON:
/*      */       case DARK_OAK_PRESSURE_PLATE:
/*      */       case DIRT:
/*      */       case DRIED_KELP_BLOCK:
/*      */       case FROSTED_ICE:
/*      */       case GRAY_CONCRETE_POWDER:
/*      */       case GREEN_CONCRETE_POWDER:
/*      */       case HAY_BLOCK:
/*      */       case HEAVY_WEIGHTED_PRESSURE_PLATE:
/*      */       case ICE:
/*      */       case JUNGLE_BUTTON:
/*      */       case JUNGLE_PRESSURE_PLATE:
/*      */       case LEVER:
/*      */       case LIGHT_BLUE_CONCRETE_POWDER:
/*      */       case LIGHT_GRAY_CONCRETE_POWDER:
/*      */       case LIGHT_WEIGHTED_PRESSURE_PLATE:
/*      */       case LIME_CONCRETE_POWDER:
/*      */       case MAGENTA_CONCRETE_POWDER:
/*      */       case MAGMA_BLOCK:
/*      */       case OAK_BUTTON:
/*      */       case OAK_PRESSURE_PLATE:
/*      */       case ORANGE_CONCRETE_POWDER:
/*      */       case PACKED_ICE:
/*      */       case PINK_CONCRETE_POWDER:
/*      */       case PODZOL:
/*      */       case POLISHED_BLACKSTONE_BUTTON:
/*      */       case POLISHED_BLACKSTONE_PRESSURE_PLATE:
/*      */       case PURPLE_CONCRETE_POWDER:
/*      */       case RED_CONCRETE_POWDER:
/*      */       case RED_SAND:
/*      */       case SAND:
/*      */       case SOUL_SAND:
/*      */       case SOUL_SOIL:
/*      */       case SPRUCE_BUTTON:
/*      */       case SPRUCE_PRESSURE_PLATE:
/*      */       case STONE_BUTTON:
/*      */       case STONE_PRESSURE_PLATE:
/*      */       case TARGET:
/*      */       case TURTLE_EGG:
/*      */       case WARPED_BUTTON:
/*      */       case WARPED_PRESSURE_PLATE:
/*      */       case WHITE_CONCRETE_POWDER:
/*      */       case YELLOW_CONCRETE_POWDER:
/* 7465 */         return 0.5F;
/*      */       case BEEHIVE:
/*      */       case CLAY:
/*      */       case COMPOSTER:
/*      */       case FARMLAND:
/*      */       case GRASS_BLOCK:
/*      */       case GRAVEL:
/*      */       case HONEYCOMB_BLOCK:
/*      */       case MYCELIUM:
/*      */       case SPONGE:
/*      */       case WET_SPONGE:
/* 7476 */         return 0.6F;
/*      */       case GRASS_PATH:
/* 7478 */         return 0.65F;
/*      */       case ACTIVATOR_RAIL:
/*      */       case DETECTOR_RAIL:
/*      */       case POWERED_RAIL:
/*      */       case RAIL:
/* 7483 */         return 0.7F;
/*      */       case BLACK_WOOL:
/*      */       case BLUE_WOOL:
/*      */       case BROWN_WOOL:
/*      */       case CHISELED_QUARTZ_BLOCK:
/*      */       case CHISELED_RED_SANDSTONE:
/*      */       case CHISELED_SANDSTONE:
/*      */       case CUT_RED_SANDSTONE:
/*      */       case CUT_SANDSTONE:
/*      */       case CYAN_WOOL:
/*      */       case GRAY_WOOL:
/*      */       case GREEN_WOOL:
/*      */       case LIGHT_BLUE_WOOL:
/*      */       case LIGHT_GRAY_WOOL:
/*      */       case LIME_WOOL:
/*      */       case MAGENTA_WOOL:
/*      */       case NOTE_BLOCK:
/*      */       case ORANGE_WOOL:
/*      */       case PINK_WOOL:
/*      */       case PURPLE_WOOL:
/*      */       case QUARTZ_BLOCK:
/*      */       case QUARTZ_BRICKS:
/*      */       case QUARTZ_PILLAR:
/*      */       case QUARTZ_STAIRS:
/*      */       case RED_SANDSTONE:
/*      */       case RED_SANDSTONE_STAIRS:
/*      */       case RED_SANDSTONE_WALL:
/*      */       case RED_WOOL:
/*      */       case SANDSTONE:
/*      */       case SANDSTONE_STAIRS:
/*      */       case SANDSTONE_WALL:
/*      */       case WHITE_WOOL:
/*      */       case YELLOW_WOOL:
/* 7516 */         return 0.8F;
/*      */       case ACACIA_SIGN:
/*      */       case ACACIA_WALL_SIGN:
/*      */       case BAMBOO:
/*      */       case BAMBOO_SAPLING:
/*      */       case BIRCH_SIGN:
/*      */       case BIRCH_WALL_SIGN:
/*      */       case BLACK_BANNER:
/*      */       case BLACK_WALL_BANNER:
/*      */       case BLUE_BANNER:
/*      */       case BLUE_WALL_BANNER:
/*      */       case BROWN_BANNER:
/*      */       case BROWN_WALL_BANNER:
/*      */       case CARVED_PUMPKIN:
/*      */       case CREEPER_HEAD:
/*      */       case CREEPER_WALL_HEAD:
/*      */       case CRIMSON_SIGN:
/*      */       case CRIMSON_WALL_SIGN:
/*      */       case CYAN_BANNER:
/*      */       case CYAN_WALL_BANNER:
/*      */       case DARK_OAK_SIGN:
/*      */       case DARK_OAK_WALL_SIGN:
/*      */       case DRAGON_HEAD:
/*      */       case DRAGON_WALL_HEAD:
/*      */       case GRAY_BANNER:
/*      */       case GRAY_WALL_BANNER:
/*      */       case GREEN_BANNER:
/*      */       case GREEN_WALL_BANNER:
/*      */       case JACK_O_LANTERN:
/*      */       case JUNGLE_SIGN:
/*      */       case JUNGLE_WALL_SIGN:
/*      */       case LIGHT_BLUE_BANNER:
/*      */       case LIGHT_BLUE_WALL_BANNER:
/*      */       case LIGHT_GRAY_BANNER:
/*      */       case LIGHT_GRAY_WALL_BANNER:
/*      */       case LIME_BANNER:
/*      */       case LIME_WALL_BANNER:
/*      */       case MAGENTA_BANNER:
/*      */       case MAGENTA_WALL_BANNER:
/*      */       case MELON:
/*      */       case NETHER_WART_BLOCK:
/*      */       case OAK_SIGN:
/*      */       case OAK_WALL_SIGN:
/*      */       case ORANGE_BANNER:
/*      */       case ORANGE_WALL_BANNER:
/*      */       case PINK_BANNER:
/*      */       case PINK_WALL_BANNER:
/*      */       case PLAYER_HEAD:
/*      */       case PLAYER_WALL_HEAD:
/*      */       case PUMPKIN:
/*      */       case PURPLE_BANNER:
/*      */       case PURPLE_WALL_BANNER:
/*      */       case RED_BANNER:
/*      */       case RED_WALL_BANNER:
/*      */       case SHROOMLIGHT:
/*      */       case SKELETON_SKULL:
/*      */       case SKELETON_WALL_SKULL:
/*      */       case SPRUCE_SIGN:
/*      */       case SPRUCE_WALL_SIGN:
/*      */       case WARPED_SIGN:
/*      */       case WARPED_WALL_SIGN:
/*      */       case WARPED_WART_BLOCK:
/*      */       case WHITE_BANNER:
/*      */       case WHITE_WALL_BANNER:
/*      */       case WITHER_SKELETON_SKULL:
/*      */       case WITHER_SKELETON_WALL_SKULL:
/*      */       case YELLOW_BANNER:
/*      */       case YELLOW_WALL_BANNER:
/*      */       case ZOMBIE_HEAD:
/*      */       case ZOMBIE_WALL_HEAD:
/* 7586 */         return 1.0F;
/*      */       case BASALT:
/*      */       case BLACK_TERRACOTTA:
/*      */       case BLUE_TERRACOTTA:
/*      */       case BROWN_TERRACOTTA:
/*      */       case CYAN_TERRACOTTA:
/*      */       case GRAY_TERRACOTTA:
/*      */       case GREEN_TERRACOTTA:
/*      */       case LIGHT_BLUE_TERRACOTTA:
/*      */       case LIGHT_GRAY_TERRACOTTA:
/*      */       case LIME_TERRACOTTA:
/*      */       case MAGENTA_TERRACOTTA:
/*      */       case ORANGE_TERRACOTTA:
/*      */       case PINK_TERRACOTTA:
/*      */       case POLISHED_BASALT:
/*      */       case PURPLE_TERRACOTTA:
/*      */       case RED_TERRACOTTA:
/*      */       case TERRACOTTA:
/*      */       case WHITE_TERRACOTTA:
/*      */       case YELLOW_TERRACOTTA:
/* 7606 */         return 1.25F;
/*      */       case BLACK_GLAZED_TERRACOTTA:
/*      */       case BLUE_GLAZED_TERRACOTTA:
/*      */       case BROWN_GLAZED_TERRACOTTA:
/*      */       case CYAN_GLAZED_TERRACOTTA:
/*      */       case GRAY_GLAZED_TERRACOTTA:
/*      */       case GREEN_GLAZED_TERRACOTTA:
/*      */       case LIGHT_BLUE_GLAZED_TERRACOTTA:
/*      */       case LIGHT_GRAY_GLAZED_TERRACOTTA:
/*      */       case LIME_GLAZED_TERRACOTTA:
/*      */       case MAGENTA_GLAZED_TERRACOTTA:
/*      */       case ORANGE_GLAZED_TERRACOTTA:
/*      */       case PINK_GLAZED_TERRACOTTA:
/*      */       case PURPLE_GLAZED_TERRACOTTA:
/*      */       case RED_GLAZED_TERRACOTTA:
/*      */       case WHITE_GLAZED_TERRACOTTA:
/*      */       case YELLOW_GLAZED_TERRACOTTA:
/* 7623 */         return 1.4F;
/*      */       case ANDESITE:
/*      */       case ANDESITE_SLAB:
/*      */       case ANDESITE_STAIRS:
/*      */       case ANDESITE_WALL:
/*      */       case BLACKSTONE:
/*      */       case BLACKSTONE_STAIRS:
/*      */       case BLACKSTONE_WALL:
/*      */       case BOOKSHELF:
/*      */       case BRAIN_CORAL_BLOCK:
/*      */       case BUBBLE_CORAL_BLOCK:
/*      */       case CHISELED_POLISHED_BLACKSTONE:
/*      */       case CHISELED_STONE_BRICKS:
/*      */       case CRACKED_POLISHED_BLACKSTONE_BRICKS:
/*      */       case CRACKED_STONE_BRICKS:
/*      */       case DARK_PRISMARINE:
/*      */       case DARK_PRISMARINE_SLAB:
/*      */       case DARK_PRISMARINE_STAIRS:
/*      */       case DEAD_BRAIN_CORAL_BLOCK:
/*      */       case DEAD_BUBBLE_CORAL_BLOCK:
/*      */       case DEAD_FIRE_CORAL_BLOCK:
/*      */       case DEAD_HORN_CORAL_BLOCK:
/*      */       case DEAD_TUBE_CORAL_BLOCK:
/*      */       case DIORITE:
/*      */       case DIORITE_SLAB:
/*      */       case DIORITE_STAIRS:
/*      */       case DIORITE_WALL:
/*      */       case FIRE_CORAL_BLOCK:
/*      */       case GILDED_BLACKSTONE:
/*      */       case GRANITE:
/*      */       case GRANITE_SLAB:
/*      */       case GRANITE_STAIRS:
/*      */       case GRANITE_WALL:
/*      */       case HORN_CORAL_BLOCK:
/*      */       case MOSSY_STONE_BRICKS:
/*      */       case MOSSY_STONE_BRICK_SLAB:
/*      */       case MOSSY_STONE_BRICK_STAIRS:
/*      */       case MOSSY_STONE_BRICK_WALL:
/*      */       case PISTON:
/*      */       case PISTON_HEAD:
/*      */       case POLISHED_ANDESITE:
/*      */       case POLISHED_ANDESITE_SLAB:
/*      */       case POLISHED_ANDESITE_STAIRS:
/*      */       case POLISHED_BLACKSTONE_BRICKS:
/*      */       case POLISHED_BLACKSTONE_BRICK_STAIRS:
/*      */       case POLISHED_BLACKSTONE_BRICK_WALL:
/*      */       case POLISHED_DIORITE:
/*      */       case POLISHED_DIORITE_SLAB:
/*      */       case POLISHED_DIORITE_STAIRS:
/*      */       case POLISHED_GRANITE:
/*      */       case POLISHED_GRANITE_SLAB:
/*      */       case POLISHED_GRANITE_STAIRS:
/*      */       case PRISMARINE:
/*      */       case PRISMARINE_BRICKS:
/*      */       case PRISMARINE_BRICK_SLAB:
/*      */       case PRISMARINE_BRICK_STAIRS:
/*      */       case PRISMARINE_SLAB:
/*      */       case PRISMARINE_STAIRS:
/*      */       case PRISMARINE_WALL:
/*      */       case PURPUR_BLOCK:
/*      */       case PURPUR_PILLAR:
/*      */       case PURPUR_STAIRS:
/*      */       case STICKY_PISTON:
/*      */       case STONE:
/*      */       case STONE_BRICKS:
/*      */       case STONE_BRICK_STAIRS:
/*      */       case STONE_BRICK_WALL:
/*      */       case STONE_STAIRS:
/*      */       case TUBE_CORAL_BLOCK:
/* 7692 */         return 1.5F;
/*      */       case BLACK_CONCRETE:
/*      */       case BLUE_CONCRETE:
/*      */       case BROWN_CONCRETE:
/*      */       case CYAN_CONCRETE:
/*      */       case GRAY_CONCRETE:
/*      */       case GREEN_CONCRETE:
/*      */       case LIGHT_BLUE_CONCRETE:
/*      */       case LIGHT_GRAY_CONCRETE:
/*      */       case LIME_CONCRETE:
/*      */       case MAGENTA_CONCRETE:
/*      */       case ORANGE_CONCRETE:
/*      */       case PINK_CONCRETE:
/*      */       case PURPLE_CONCRETE:
/*      */       case RED_CONCRETE:
/*      */       case WHITE_CONCRETE:
/*      */       case YELLOW_CONCRETE:
/* 7709 */         return 1.8F;
/*      */       case ACACIA_FENCE:
/*      */       case ACACIA_FENCE_GATE:
/*      */       case ACACIA_LOG:
/*      */       case ACACIA_PLANKS:
/*      */       case ACACIA_SLAB:
/*      */       case ACACIA_STAIRS:
/*      */       case ACACIA_WOOD:
/*      */       case BIRCH_FENCE:
/*      */       case BIRCH_FENCE_GATE:
/*      */       case BIRCH_LOG:
/*      */       case BIRCH_PLANKS:
/*      */       case BIRCH_SLAB:
/*      */       case BIRCH_STAIRS:
/*      */       case BIRCH_WOOD:
/*      */       case BLACKSTONE_SLAB:
/*      */       case BLACK_SHULKER_BOX:
/*      */       case BLUE_SHULKER_BOX:
/*      */       case BONE_BLOCK:
/*      */       case BRICKS:
/*      */       case BRICK_SLAB:
/*      */       case BRICK_STAIRS:
/*      */       case BRICK_WALL:
/*      */       case BROWN_SHULKER_BOX:
/*      */       case CAMPFIRE:
/*      */       case CAULDRON:
/*      */       case CHISELED_NETHER_BRICKS:
/*      */       case COBBLESTONE:
/*      */       case COBBLESTONE_SLAB:
/*      */       case COBBLESTONE_STAIRS:
/*      */       case COBBLESTONE_WALL:
/*      */       case CRACKED_NETHER_BRICKS:
/*      */       case CRIMSON_FENCE:
/*      */       case CRIMSON_FENCE_GATE:
/*      */       case CRIMSON_HYPHAE:
/*      */       case CRIMSON_PLANKS:
/*      */       case CRIMSON_SLAB:
/*      */       case CRIMSON_STAIRS:
/*      */       case CRIMSON_STEM:
/*      */       case CUT_RED_SANDSTONE_SLAB:
/*      */       case CUT_SANDSTONE_SLAB:
/*      */       case CYAN_SHULKER_BOX:
/*      */       case DARK_OAK_FENCE:
/*      */       case DARK_OAK_FENCE_GATE:
/*      */       case DARK_OAK_LOG:
/*      */       case DARK_OAK_PLANKS:
/*      */       case DARK_OAK_SLAB:
/*      */       case DARK_OAK_STAIRS:
/*      */       case DARK_OAK_WOOD:
/*      */       case GRAY_SHULKER_BOX:
/*      */       case GREEN_SHULKER_BOX:
/*      */       case GRINDSTONE:
/*      */       case JUKEBOX:
/*      */       case JUNGLE_FENCE:
/*      */       case JUNGLE_FENCE_GATE:
/*      */       case JUNGLE_LOG:
/*      */       case JUNGLE_PLANKS:
/*      */       case JUNGLE_SLAB:
/*      */       case JUNGLE_STAIRS:
/*      */       case JUNGLE_WOOD:
/*      */       case LIGHT_BLUE_SHULKER_BOX:
/*      */       case LIGHT_GRAY_SHULKER_BOX:
/*      */       case LIME_SHULKER_BOX:
/*      */       case MAGENTA_SHULKER_BOX:
/*      */       case MOSSY_COBBLESTONE:
/*      */       case MOSSY_COBBLESTONE_SLAB:
/*      */       case MOSSY_COBBLESTONE_STAIRS:
/*      */       case MOSSY_COBBLESTONE_WALL:
/*      */       case NETHER_BRICKS:
/*      */       case NETHER_BRICK_FENCE:
/*      */       case NETHER_BRICK_SLAB:
/*      */       case NETHER_BRICK_STAIRS:
/*      */       case NETHER_BRICK_WALL:
/*      */       case OAK_FENCE:
/*      */       case OAK_FENCE_GATE:
/*      */       case OAK_LOG:
/*      */       case OAK_PLANKS:
/*      */       case OAK_SLAB:
/*      */       case OAK_STAIRS:
/*      */       case OAK_WOOD:
/*      */       case ORANGE_SHULKER_BOX:
/*      */       case PETRIFIED_OAK_SLAB:
/*      */       case PINK_SHULKER_BOX:
/*      */       case POLISHED_BLACKSTONE:
/*      */       case POLISHED_BLACKSTONE_BRICK_SLAB:
/*      */       case POLISHED_BLACKSTONE_SLAB:
/*      */       case POLISHED_BLACKSTONE_STAIRS:
/*      */       case POLISHED_BLACKSTONE_WALL:
/*      */       case PURPLE_SHULKER_BOX:
/*      */       case PURPUR_SLAB:
/*      */       case QUARTZ_SLAB:
/*      */       case RED_NETHER_BRICKS:
/*      */       case RED_NETHER_BRICK_SLAB:
/*      */       case RED_NETHER_BRICK_STAIRS:
/*      */       case RED_NETHER_BRICK_WALL:
/*      */       case RED_SANDSTONE_SLAB:
/*      */       case RED_SHULKER_BOX:
/*      */       case SANDSTONE_SLAB:
/*      */       case SHULKER_BOX:
/*      */       case SMOOTH_QUARTZ:
/*      */       case SMOOTH_QUARTZ_SLAB:
/*      */       case SMOOTH_QUARTZ_STAIRS:
/*      */       case SMOOTH_RED_SANDSTONE:
/*      */       case SMOOTH_RED_SANDSTONE_SLAB:
/*      */       case SMOOTH_RED_SANDSTONE_STAIRS:
/*      */       case SMOOTH_SANDSTONE:
/*      */       case SMOOTH_SANDSTONE_SLAB:
/*      */       case SMOOTH_SANDSTONE_STAIRS:
/*      */       case SMOOTH_STONE:
/*      */       case SMOOTH_STONE_SLAB:
/*      */       case SOUL_CAMPFIRE:
/*      */       case SPRUCE_FENCE:
/*      */       case SPRUCE_FENCE_GATE:
/*      */       case SPRUCE_LOG:
/*      */       case SPRUCE_PLANKS:
/*      */       case SPRUCE_SLAB:
/*      */       case SPRUCE_STAIRS:
/*      */       case SPRUCE_WOOD:
/*      */       case STONE_BRICK_SLAB:
/*      */       case STONE_SLAB:
/*      */       case STRIPPED_ACACIA_LOG:
/*      */       case STRIPPED_ACACIA_WOOD:
/*      */       case STRIPPED_BIRCH_LOG:
/*      */       case STRIPPED_BIRCH_WOOD:
/*      */       case STRIPPED_CRIMSON_HYPHAE:
/*      */       case STRIPPED_CRIMSON_STEM:
/*      */       case STRIPPED_DARK_OAK_LOG:
/*      */       case STRIPPED_DARK_OAK_WOOD:
/*      */       case STRIPPED_JUNGLE_LOG:
/*      */       case STRIPPED_JUNGLE_WOOD:
/*      */       case STRIPPED_OAK_LOG:
/*      */       case STRIPPED_OAK_WOOD:
/*      */       case STRIPPED_SPRUCE_LOG:
/*      */       case STRIPPED_SPRUCE_WOOD:
/*      */       case STRIPPED_WARPED_HYPHAE:
/*      */       case STRIPPED_WARPED_STEM:
/*      */       case WARPED_FENCE:
/*      */       case WARPED_FENCE_GATE:
/*      */       case WARPED_HYPHAE:
/*      */       case WARPED_PLANKS:
/*      */       case WARPED_SLAB:
/*      */       case WARPED_STAIRS:
/*      */       case WARPED_STEM:
/*      */       case WHITE_SHULKER_BOX:
/*      */       case YELLOW_SHULKER_BOX:
/* 7854 */         return 2.0F;
/*      */       case BARREL:
/*      */       case CARTOGRAPHY_TABLE:
/*      */       case CHEST:
/*      */       case CRAFTING_TABLE:
/*      */       case FLETCHING_TABLE:
/*      */       case LECTERN:
/*      */       case LOOM:
/*      */       case SMITHING_TABLE:
/*      */       case TRAPPED_CHEST:
/* 7864 */         return 2.5F;
/*      */       case BLUE_ICE:
/* 7866 */         return 2.8F;
/*      */       case ACACIA_DOOR:
/*      */       case ACACIA_TRAPDOOR:
/*      */       case BEACON:
/*      */       case BIRCH_DOOR:
/*      */       case BIRCH_TRAPDOOR:
/*      */       case COAL_ORE:
/*      */       case CONDUIT:
/*      */       case CRIMSON_DOOR:
/*      */       case CRIMSON_TRAPDOOR:
/*      */       case DARK_OAK_DOOR:
/*      */       case DARK_OAK_TRAPDOOR:
/*      */       case DIAMOND_ORE:
/*      */       case DRAGON_EGG:
/*      */       case EMERALD_ORE:
/*      */       case END_STONE:
/*      */       case END_STONE_BRICKS:
/*      */       case END_STONE_BRICK_SLAB:
/*      */       case END_STONE_BRICK_STAIRS:
/*      */       case END_STONE_BRICK_WALL:
/*      */       case GOLD_BLOCK:
/*      */       case GOLD_ORE:
/*      */       case HOPPER:
/*      */       case IRON_ORE:
/*      */       case JUNGLE_DOOR:
/*      */       case JUNGLE_TRAPDOOR:
/*      */       case LAPIS_BLOCK:
/*      */       case LAPIS_ORE:
/*      */       case NETHER_GOLD_ORE:
/*      */       case NETHER_QUARTZ_ORE:
/*      */       case OAK_DOOR:
/*      */       case OAK_TRAPDOOR:
/*      */       case OBSERVER:
/*      */       case REDSTONE_ORE:
/*      */       case SPRUCE_DOOR:
/*      */       case SPRUCE_TRAPDOOR:
/*      */       case WARPED_DOOR:
/*      */       case WARPED_TRAPDOOR:
/* 7904 */         return 3.0F;
/*      */       case BLAST_FURNACE:
/*      */       case DISPENSER:
/*      */       case DROPPER:
/*      */       case FURNACE:
/*      */       case LANTERN:
/*      */       case LODESTONE:
/*      */       case SMOKER:
/*      */       case SOUL_LANTERN:
/*      */       case STONECUTTER:
/* 7914 */         return 3.5F;
/*      */       case COBWEB:
/* 7916 */         return 4.0F;
/*      */       case ANVIL:
/*      */       case BELL:
/*      */       case CHAIN:
/*      */       case CHIPPED_ANVIL:
/*      */       case COAL_BLOCK:
/*      */       case DAMAGED_ANVIL:
/*      */       case DIAMOND_BLOCK:
/*      */       case EMERALD_BLOCK:
/*      */       case ENCHANTING_TABLE:
/*      */       case IRON_BARS:
/*      */       case IRON_BLOCK:
/*      */       case IRON_DOOR:
/*      */       case IRON_TRAPDOOR:
/*      */       case REDSTONE_BLOCK:
/*      */       case SPAWNER:
/* 7932 */         return 5.0F;
/*      */       case ENDER_CHEST:
/* 7934 */         return 22.5F;
/*      */       case ANCIENT_DEBRIS:
/* 7936 */         return 30.0F;
/*      */       case CRYING_OBSIDIAN:
/*      */       case NETHERITE_BLOCK:
/*      */       case OBSIDIAN:
/*      */       case RESPAWN_ANCHOR:
/* 7941 */         return 50.0F;
/*      */       case LAVA:
/*      */       case WATER:
/* 7944 */         return 100.0F;
/*      */     } 
/* 7946 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBlastResistance() {
/* 7962 */     Validate.isTrue(isBlock(), "The Material is not a block!");
/* 7963 */     switch (this) {
/*      */       
/*      */       case BLACK_CARPET:
/*      */       case BLUE_CARPET:
/*      */       case BROWN_CARPET:
/*      */       case CYAN_CARPET:
/*      */       case GRAY_CARPET:
/*      */       case GREEN_CARPET:
/*      */       case LIGHT_BLUE_CARPET:
/*      */       case LIGHT_GRAY_CARPET:
/*      */       case LIME_CARPET:
/*      */       case MAGENTA_CARPET:
/*      */       case ORANGE_CARPET:
/*      */       case PINK_CARPET:
/*      */       case PURPLE_CARPET:
/*      */       case RED_CARPET:
/*      */       case SNOW:
/*      */       case WHITE_CARPET:
/*      */       case YELLOW_CARPET:
/* 7982 */         return 0.1F;
/*      */       case ACACIA_LEAVES:
/*      */       case BIRCH_LEAVES:
/*      */       case BLACK_BED:
/*      */       case BLUE_BED:
/*      */       case BROWN_BED:
/*      */       case BROWN_MUSHROOM_BLOCK:
/*      */       case CYAN_BED:
/*      */       case DARK_OAK_LEAVES:
/*      */       case DAYLIGHT_DETECTOR:
/*      */       case GRAY_BED:
/*      */       case GREEN_BED:
/*      */       case JUNGLE_LEAVES:
/*      */       case LIGHT_BLUE_BED:
/*      */       case LIGHT_GRAY_BED:
/*      */       case LIME_BED:
/*      */       case MAGENTA_BED:
/*      */       case MUSHROOM_STEM:
/*      */       case OAK_LEAVES:
/*      */       case ORANGE_BED:
/*      */       case PINK_BED:
/*      */       case PURPLE_BED:
/*      */       case RED_BED:
/*      */       case RED_MUSHROOM_BLOCK:
/*      */       case SNOW_BLOCK:
/*      */       case SPRUCE_LEAVES:
/*      */       case VINE:
/*      */       case WHITE_BED:
/*      */       case YELLOW_BED:
/* 8011 */         return 0.2F;
/*      */       case BEE_NEST:
/*      */       case BLACK_STAINED_GLASS:
/*      */       case BLACK_STAINED_GLASS_PANE:
/*      */       case BLUE_STAINED_GLASS:
/*      */       case BLUE_STAINED_GLASS_PANE:
/*      */       case BROWN_STAINED_GLASS:
/*      */       case BROWN_STAINED_GLASS_PANE:
/*      */       case CYAN_STAINED_GLASS:
/*      */       case CYAN_STAINED_GLASS_PANE:
/*      */       case GLASS:
/*      */       case GLASS_PANE:
/*      */       case GLOWSTONE:
/*      */       case GRAY_STAINED_GLASS:
/*      */       case GRAY_STAINED_GLASS_PANE:
/*      */       case GREEN_STAINED_GLASS:
/*      */       case GREEN_STAINED_GLASS_PANE:
/*      */       case LIGHT_BLUE_STAINED_GLASS:
/*      */       case LIGHT_BLUE_STAINED_GLASS_PANE:
/*      */       case LIGHT_GRAY_STAINED_GLASS:
/*      */       case LIGHT_GRAY_STAINED_GLASS_PANE:
/*      */       case LIME_STAINED_GLASS:
/*      */       case LIME_STAINED_GLASS_PANE:
/*      */       case MAGENTA_STAINED_GLASS:
/*      */       case MAGENTA_STAINED_GLASS_PANE:
/*      */       case ORANGE_STAINED_GLASS:
/*      */       case ORANGE_STAINED_GLASS_PANE:
/*      */       case PINK_STAINED_GLASS:
/*      */       case PINK_STAINED_GLASS_PANE:
/*      */       case PURPLE_STAINED_GLASS:
/*      */       case PURPLE_STAINED_GLASS_PANE:
/*      */       case REDSTONE_LAMP:
/*      */       case RED_STAINED_GLASS:
/*      */       case RED_STAINED_GLASS_PANE:
/*      */       case SEA_LANTERN:
/*      */       case WHITE_STAINED_GLASS:
/*      */       case WHITE_STAINED_GLASS_PANE:
/*      */       case YELLOW_STAINED_GLASS:
/*      */       case YELLOW_STAINED_GLASS_PANE:
/* 8050 */         return 0.3F;
/*      */       case CACTUS:
/*      */       case CHORUS_FLOWER:
/*      */       case CHORUS_PLANT:
/*      */       case CRIMSON_NYLIUM:
/*      */       case LADDER:
/*      */       case NETHERRACK:
/*      */       case WARPED_NYLIUM:
/* 8058 */         return 0.4F;
/*      */       case ACACIA_BUTTON:
/*      */       case ACACIA_PRESSURE_PLATE:
/*      */       case BIRCH_BUTTON:
/*      */       case BIRCH_PRESSURE_PLATE:
/*      */       case BLACK_CONCRETE_POWDER:
/*      */       case BLUE_CONCRETE_POWDER:
/*      */       case BREWING_STAND:
/*      */       case BROWN_CONCRETE_POWDER:
/*      */       case CAKE:
/*      */       case COARSE_DIRT:
/*      */       case CRIMSON_BUTTON:
/*      */       case CRIMSON_PRESSURE_PLATE:
/*      */       case CYAN_CONCRETE_POWDER:
/*      */       case DARK_OAK_BUTTON:
/*      */       case DARK_OAK_PRESSURE_PLATE:
/*      */       case DIRT:
/*      */       case FROSTED_ICE:
/*      */       case GRAY_CONCRETE_POWDER:
/*      */       case GREEN_CONCRETE_POWDER:
/*      */       case HAY_BLOCK:
/*      */       case HEAVY_WEIGHTED_PRESSURE_PLATE:
/*      */       case ICE:
/*      */       case JUNGLE_BUTTON:
/*      */       case JUNGLE_PRESSURE_PLATE:
/*      */       case LEVER:
/*      */       case LIGHT_BLUE_CONCRETE_POWDER:
/*      */       case LIGHT_GRAY_CONCRETE_POWDER:
/*      */       case LIGHT_WEIGHTED_PRESSURE_PLATE:
/*      */       case LIME_CONCRETE_POWDER:
/*      */       case MAGENTA_CONCRETE_POWDER:
/*      */       case MAGMA_BLOCK:
/*      */       case OAK_BUTTON:
/*      */       case OAK_PRESSURE_PLATE:
/*      */       case ORANGE_CONCRETE_POWDER:
/*      */       case PACKED_ICE:
/*      */       case PINK_CONCRETE_POWDER:
/*      */       case PODZOL:
/*      */       case POLISHED_BLACKSTONE_BUTTON:
/*      */       case POLISHED_BLACKSTONE_PRESSURE_PLATE:
/*      */       case PURPLE_CONCRETE_POWDER:
/*      */       case RED_CONCRETE_POWDER:
/*      */       case RED_SAND:
/*      */       case SAND:
/*      */       case SOUL_SAND:
/*      */       case SOUL_SOIL:
/*      */       case SPRUCE_BUTTON:
/*      */       case SPRUCE_PRESSURE_PLATE:
/*      */       case STONE_BUTTON:
/*      */       case STONE_PRESSURE_PLATE:
/*      */       case TARGET:
/*      */       case TURTLE_EGG:
/*      */       case WARPED_BUTTON:
/*      */       case WARPED_PRESSURE_PLATE:
/*      */       case WHITE_CONCRETE_POWDER:
/*      */       case YELLOW_CONCRETE_POWDER:
/* 8114 */         return 0.5F;
/*      */       case BEEHIVE:
/*      */       case CLAY:
/*      */       case COMPOSTER:
/*      */       case FARMLAND:
/*      */       case GRASS_BLOCK:
/*      */       case GRAVEL:
/*      */       case HONEYCOMB_BLOCK:
/*      */       case MYCELIUM:
/*      */       case SPONGE:
/*      */       case WET_SPONGE:
/* 8125 */         return 0.6F;
/*      */       case GRASS_PATH:
/* 8127 */         return 0.65F;
/*      */       case ACTIVATOR_RAIL:
/*      */       case DETECTOR_RAIL:
/*      */       case POWERED_RAIL:
/*      */       case RAIL:
/* 8132 */         return 0.7F;
/*      */       case INFESTED_CHISELED_STONE_BRICKS:
/*      */       case INFESTED_COBBLESTONE:
/*      */       case INFESTED_CRACKED_STONE_BRICKS:
/*      */       case INFESTED_MOSSY_STONE_BRICKS:
/*      */       case INFESTED_STONE:
/*      */       case INFESTED_STONE_BRICKS:
/* 8139 */         return 0.75F;
/*      */       case BLACK_WOOL:
/*      */       case BLUE_WOOL:
/*      */       case BROWN_WOOL:
/*      */       case CHISELED_QUARTZ_BLOCK:
/*      */       case CHISELED_RED_SANDSTONE:
/*      */       case CHISELED_SANDSTONE:
/*      */       case CUT_RED_SANDSTONE:
/*      */       case CUT_SANDSTONE:
/*      */       case CYAN_WOOL:
/*      */       case GRAY_WOOL:
/*      */       case GREEN_WOOL:
/*      */       case LIGHT_BLUE_WOOL:
/*      */       case LIGHT_GRAY_WOOL:
/*      */       case LIME_WOOL:
/*      */       case MAGENTA_WOOL:
/*      */       case NOTE_BLOCK:
/*      */       case ORANGE_WOOL:
/*      */       case PINK_WOOL:
/*      */       case PURPLE_WOOL:
/*      */       case QUARTZ_BLOCK:
/*      */       case QUARTZ_BRICKS:
/*      */       case QUARTZ_PILLAR:
/*      */       case QUARTZ_STAIRS:
/*      */       case RED_SANDSTONE:
/*      */       case RED_SANDSTONE_STAIRS:
/*      */       case RED_SANDSTONE_WALL:
/*      */       case RED_WOOL:
/*      */       case SANDSTONE:
/*      */       case SANDSTONE_STAIRS:
/*      */       case SANDSTONE_WALL:
/*      */       case WHITE_WOOL:
/*      */       case YELLOW_WOOL:
/* 8172 */         return 0.8F;
/*      */       case ACACIA_SIGN:
/*      */       case ACACIA_WALL_SIGN:
/*      */       case BAMBOO:
/*      */       case BAMBOO_SAPLING:
/*      */       case BIRCH_SIGN:
/*      */       case BIRCH_WALL_SIGN:
/*      */       case BLACK_BANNER:
/*      */       case BLACK_WALL_BANNER:
/*      */       case BLUE_BANNER:
/*      */       case BLUE_WALL_BANNER:
/*      */       case BROWN_BANNER:
/*      */       case BROWN_WALL_BANNER:
/*      */       case CARVED_PUMPKIN:
/*      */       case CREEPER_HEAD:
/*      */       case CREEPER_WALL_HEAD:
/*      */       case CRIMSON_SIGN:
/*      */       case CRIMSON_WALL_SIGN:
/*      */       case CYAN_BANNER:
/*      */       case CYAN_WALL_BANNER:
/*      */       case DARK_OAK_SIGN:
/*      */       case DARK_OAK_WALL_SIGN:
/*      */       case DRAGON_HEAD:
/*      */       case DRAGON_WALL_HEAD:
/*      */       case GRAY_BANNER:
/*      */       case GRAY_WALL_BANNER:
/*      */       case GREEN_BANNER:
/*      */       case GREEN_WALL_BANNER:
/*      */       case JACK_O_LANTERN:
/*      */       case JUNGLE_SIGN:
/*      */       case JUNGLE_WALL_SIGN:
/*      */       case LIGHT_BLUE_BANNER:
/*      */       case LIGHT_BLUE_WALL_BANNER:
/*      */       case LIGHT_GRAY_BANNER:
/*      */       case LIGHT_GRAY_WALL_BANNER:
/*      */       case LIME_BANNER:
/*      */       case LIME_WALL_BANNER:
/*      */       case MAGENTA_BANNER:
/*      */       case MAGENTA_WALL_BANNER:
/*      */       case MELON:
/*      */       case NETHER_WART_BLOCK:
/*      */       case OAK_SIGN:
/*      */       case OAK_WALL_SIGN:
/*      */       case ORANGE_BANNER:
/*      */       case ORANGE_WALL_BANNER:
/*      */       case PINK_BANNER:
/*      */       case PINK_WALL_BANNER:
/*      */       case PLAYER_HEAD:
/*      */       case PLAYER_WALL_HEAD:
/*      */       case PUMPKIN:
/*      */       case PURPLE_BANNER:
/*      */       case PURPLE_WALL_BANNER:
/*      */       case RED_BANNER:
/*      */       case RED_WALL_BANNER:
/*      */       case SHROOMLIGHT:
/*      */       case SKELETON_SKULL:
/*      */       case SKELETON_WALL_SKULL:
/*      */       case SPRUCE_SIGN:
/*      */       case SPRUCE_WALL_SIGN:
/*      */       case WARPED_SIGN:
/*      */       case WARPED_WALL_SIGN:
/*      */       case WARPED_WART_BLOCK:
/*      */       case WHITE_BANNER:
/*      */       case WHITE_WALL_BANNER:
/*      */       case WITHER_SKELETON_SKULL:
/*      */       case WITHER_SKELETON_WALL_SKULL:
/*      */       case YELLOW_BANNER:
/*      */       case YELLOW_WALL_BANNER:
/*      */       case ZOMBIE_HEAD:
/*      */       case ZOMBIE_WALL_HEAD:
/* 8242 */         return 1.0F;
/*      */       case BLACK_GLAZED_TERRACOTTA:
/*      */       case BLUE_GLAZED_TERRACOTTA:
/*      */       case BROWN_GLAZED_TERRACOTTA:
/*      */       case CYAN_GLAZED_TERRACOTTA:
/*      */       case GRAY_GLAZED_TERRACOTTA:
/*      */       case GREEN_GLAZED_TERRACOTTA:
/*      */       case LIGHT_BLUE_GLAZED_TERRACOTTA:
/*      */       case LIGHT_GRAY_GLAZED_TERRACOTTA:
/*      */       case LIME_GLAZED_TERRACOTTA:
/*      */       case MAGENTA_GLAZED_TERRACOTTA:
/*      */       case ORANGE_GLAZED_TERRACOTTA:
/*      */       case PINK_GLAZED_TERRACOTTA:
/*      */       case PURPLE_GLAZED_TERRACOTTA:
/*      */       case RED_GLAZED_TERRACOTTA:
/*      */       case WHITE_GLAZED_TERRACOTTA:
/*      */       case YELLOW_GLAZED_TERRACOTTA:
/* 8259 */         return 1.4F;
/*      */       case BOOKSHELF:
/*      */       case PISTON:
/*      */       case PISTON_HEAD:
/*      */       case STICKY_PISTON:
/* 8264 */         return 1.5F;
/*      */       case BLACK_CONCRETE:
/*      */       case BLUE_CONCRETE:
/*      */       case BROWN_CONCRETE:
/*      */       case CYAN_CONCRETE:
/*      */       case GRAY_CONCRETE:
/*      */       case GREEN_CONCRETE:
/*      */       case LIGHT_BLUE_CONCRETE:
/*      */       case LIGHT_GRAY_CONCRETE:
/*      */       case LIME_CONCRETE:
/*      */       case MAGENTA_CONCRETE:
/*      */       case ORANGE_CONCRETE:
/*      */       case PINK_CONCRETE:
/*      */       case PURPLE_CONCRETE:
/*      */       case RED_CONCRETE:
/*      */       case WHITE_CONCRETE:
/*      */       case YELLOW_CONCRETE:
/* 8281 */         return 1.8F;
/*      */       case ACACIA_LOG:
/*      */       case ACACIA_WOOD:
/*      */       case BIRCH_LOG:
/*      */       case BIRCH_WOOD:
/*      */       case BLACK_SHULKER_BOX:
/*      */       case BLUE_SHULKER_BOX:
/*      */       case BONE_BLOCK:
/*      */       case BROWN_SHULKER_BOX:
/*      */       case CAMPFIRE:
/*      */       case CAULDRON:
/*      */       case CRIMSON_HYPHAE:
/*      */       case CRIMSON_STEM:
/*      */       case CYAN_SHULKER_BOX:
/*      */       case DARK_OAK_LOG:
/*      */       case DARK_OAK_WOOD:
/*      */       case GRAY_SHULKER_BOX:
/*      */       case GREEN_SHULKER_BOX:
/*      */       case JUNGLE_LOG:
/*      */       case JUNGLE_WOOD:
/*      */       case LIGHT_BLUE_SHULKER_BOX:
/*      */       case LIGHT_GRAY_SHULKER_BOX:
/*      */       case LIME_SHULKER_BOX:
/*      */       case MAGENTA_SHULKER_BOX:
/*      */       case OAK_LOG:
/*      */       case OAK_WOOD:
/*      */       case ORANGE_SHULKER_BOX:
/*      */       case PINK_SHULKER_BOX:
/*      */       case PURPLE_SHULKER_BOX:
/*      */       case RED_SHULKER_BOX:
/*      */       case SHULKER_BOX:
/*      */       case SOUL_CAMPFIRE:
/*      */       case SPRUCE_LOG:
/*      */       case SPRUCE_WOOD:
/*      */       case STRIPPED_ACACIA_LOG:
/*      */       case STRIPPED_ACACIA_WOOD:
/*      */       case STRIPPED_BIRCH_LOG:
/*      */       case STRIPPED_BIRCH_WOOD:
/*      */       case STRIPPED_CRIMSON_HYPHAE:
/*      */       case STRIPPED_CRIMSON_STEM:
/*      */       case STRIPPED_DARK_OAK_LOG:
/*      */       case STRIPPED_DARK_OAK_WOOD:
/*      */       case STRIPPED_JUNGLE_LOG:
/*      */       case STRIPPED_JUNGLE_WOOD:
/*      */       case STRIPPED_OAK_LOG:
/*      */       case STRIPPED_OAK_WOOD:
/*      */       case STRIPPED_SPRUCE_LOG:
/*      */       case STRIPPED_SPRUCE_WOOD:
/*      */       case STRIPPED_WARPED_HYPHAE:
/*      */       case STRIPPED_WARPED_STEM:
/*      */       case WARPED_HYPHAE:
/*      */       case WARPED_STEM:
/*      */       case WHITE_SHULKER_BOX:
/*      */       case YELLOW_SHULKER_BOX:
/* 8335 */         return 2.0F;
/*      */       case BARREL:
/*      */       case CARTOGRAPHY_TABLE:
/*      */       case CHEST:
/*      */       case CRAFTING_TABLE:
/*      */       case DRIED_KELP_BLOCK:
/*      */       case FLETCHING_TABLE:
/*      */       case LECTERN:
/*      */       case LOOM:
/*      */       case SMITHING_TABLE:
/*      */       case TRAPPED_CHEST:
/* 8346 */         return 2.5F;
/*      */       case BLUE_ICE:
/* 8348 */         return 2.8F;
/*      */       case ACACIA_DOOR:
/*      */       case ACACIA_FENCE:
/*      */       case ACACIA_FENCE_GATE:
/*      */       case ACACIA_PLANKS:
/*      */       case ACACIA_SLAB:
/*      */       case ACACIA_STAIRS:
/*      */       case ACACIA_TRAPDOOR:
/*      */       case BEACON:
/*      */       case BIRCH_DOOR:
/*      */       case BIRCH_FENCE:
/*      */       case BIRCH_FENCE_GATE:
/*      */       case BIRCH_PLANKS:
/*      */       case BIRCH_SLAB:
/*      */       case BIRCH_STAIRS:
/*      */       case BIRCH_TRAPDOOR:
/*      */       case COAL_ORE:
/*      */       case COCOA:
/*      */       case CONDUIT:
/*      */       case CRIMSON_DOOR:
/*      */       case CRIMSON_FENCE:
/*      */       case CRIMSON_FENCE_GATE:
/*      */       case CRIMSON_PLANKS:
/*      */       case CRIMSON_SLAB:
/*      */       case CRIMSON_STAIRS:
/*      */       case CRIMSON_TRAPDOOR:
/*      */       case DARK_OAK_DOOR:
/*      */       case DARK_OAK_FENCE:
/*      */       case DARK_OAK_FENCE_GATE:
/*      */       case DARK_OAK_PLANKS:
/*      */       case DARK_OAK_SLAB:
/*      */       case DARK_OAK_STAIRS:
/*      */       case DARK_OAK_TRAPDOOR:
/*      */       case DIAMOND_ORE:
/*      */       case EMERALD_ORE:
/*      */       case GOLD_ORE:
/*      */       case IRON_ORE:
/*      */       case JUNGLE_DOOR:
/*      */       case JUNGLE_FENCE:
/*      */       case JUNGLE_FENCE_GATE:
/*      */       case JUNGLE_PLANKS:
/*      */       case JUNGLE_SLAB:
/*      */       case JUNGLE_STAIRS:
/*      */       case JUNGLE_TRAPDOOR:
/*      */       case LAPIS_BLOCK:
/*      */       case LAPIS_ORE:
/*      */       case NETHER_GOLD_ORE:
/*      */       case NETHER_QUARTZ_ORE:
/*      */       case OAK_DOOR:
/*      */       case OAK_FENCE:
/*      */       case OAK_FENCE_GATE:
/*      */       case OAK_PLANKS:
/*      */       case OAK_SLAB:
/*      */       case OAK_STAIRS:
/*      */       case OAK_TRAPDOOR:
/*      */       case OBSERVER:
/*      */       case REDSTONE_ORE:
/*      */       case SPRUCE_DOOR:
/*      */       case SPRUCE_FENCE:
/*      */       case SPRUCE_FENCE_GATE:
/*      */       case SPRUCE_PLANKS:
/*      */       case SPRUCE_SLAB:
/*      */       case SPRUCE_STAIRS:
/*      */       case SPRUCE_TRAPDOOR:
/*      */       case WARPED_DOOR:
/*      */       case WARPED_FENCE:
/*      */       case WARPED_FENCE_GATE:
/*      */       case WARPED_PLANKS:
/*      */       case WARPED_SLAB:
/*      */       case WARPED_STAIRS:
/*      */       case WARPED_TRAPDOOR:
/* 8419 */         return 3.0F;
/*      */       case BLAST_FURNACE:
/*      */       case DISPENSER:
/*      */       case DROPPER:
/*      */       case FURNACE:
/*      */       case LANTERN:
/*      */       case LODESTONE:
/*      */       case SMOKER:
/*      */       case SOUL_LANTERN:
/*      */       case STONECUTTER:
/* 8429 */         return 3.5F;
/*      */       case COBWEB:
/* 8431 */         return 4.0F;
/*      */       case BASALT:
/*      */       case BLACK_TERRACOTTA:
/*      */       case BLUE_TERRACOTTA:
/*      */       case BROWN_TERRACOTTA:
/*      */       case CYAN_TERRACOTTA:
/*      */       case GRAY_TERRACOTTA:
/*      */       case GREEN_TERRACOTTA:
/*      */       case LIGHT_BLUE_TERRACOTTA:
/*      */       case LIGHT_GRAY_TERRACOTTA:
/*      */       case LIME_TERRACOTTA:
/*      */       case MAGENTA_TERRACOTTA:
/*      */       case ORANGE_TERRACOTTA:
/*      */       case PINK_TERRACOTTA:
/*      */       case POLISHED_BASALT:
/*      */       case PURPLE_TERRACOTTA:
/*      */       case RED_TERRACOTTA:
/*      */       case TERRACOTTA:
/*      */       case WHITE_TERRACOTTA:
/*      */       case YELLOW_TERRACOTTA:
/* 8451 */         return 4.2F;
/*      */       case HOPPER:
/* 8453 */         return 4.8F;
/*      */       case BELL:
/*      */       case IRON_DOOR:
/*      */       case IRON_TRAPDOOR:
/*      */       case SPAWNER:
/* 8458 */         return 5.0F;
/*      */       case ANDESITE:
/*      */       case ANDESITE_SLAB:
/*      */       case ANDESITE_STAIRS:
/*      */       case ANDESITE_WALL:
/*      */       case BLACKSTONE:
/*      */       case BLACKSTONE_SLAB:
/*      */       case BLACKSTONE_STAIRS:
/*      */       case BLACKSTONE_WALL:
/*      */       case BRAIN_CORAL_BLOCK:
/*      */       case BRICKS:
/*      */       case BRICK_SLAB:
/*      */       case BRICK_STAIRS:
/*      */       case BRICK_WALL:
/*      */       case BUBBLE_CORAL_BLOCK:
/*      */       case CHAIN:
/*      */       case CHISELED_NETHER_BRICKS:
/*      */       case CHISELED_POLISHED_BLACKSTONE:
/*      */       case CHISELED_STONE_BRICKS:
/*      */       case COAL_BLOCK:
/*      */       case COBBLESTONE:
/*      */       case COBBLESTONE_SLAB:
/*      */       case COBBLESTONE_STAIRS:
/*      */       case COBBLESTONE_WALL:
/*      */       case CRACKED_NETHER_BRICKS:
/*      */       case CRACKED_POLISHED_BLACKSTONE_BRICKS:
/*      */       case CRACKED_STONE_BRICKS:
/*      */       case CUT_RED_SANDSTONE_SLAB:
/*      */       case CUT_SANDSTONE_SLAB:
/*      */       case DARK_PRISMARINE:
/*      */       case DARK_PRISMARINE_SLAB:
/*      */       case DARK_PRISMARINE_STAIRS:
/*      */       case DEAD_BRAIN_CORAL_BLOCK:
/*      */       case DEAD_BUBBLE_CORAL_BLOCK:
/*      */       case DEAD_FIRE_CORAL_BLOCK:
/*      */       case DEAD_HORN_CORAL_BLOCK:
/*      */       case DEAD_TUBE_CORAL_BLOCK:
/*      */       case DIAMOND_BLOCK:
/*      */       case DIORITE:
/*      */       case DIORITE_SLAB:
/*      */       case DIORITE_STAIRS:
/*      */       case DIORITE_WALL:
/*      */       case EMERALD_BLOCK:
/*      */       case FIRE_CORAL_BLOCK:
/*      */       case GILDED_BLACKSTONE:
/*      */       case GOLD_BLOCK:
/*      */       case GRANITE:
/*      */       case GRANITE_SLAB:
/*      */       case GRANITE_STAIRS:
/*      */       case GRANITE_WALL:
/*      */       case GRINDSTONE:
/*      */       case HORN_CORAL_BLOCK:
/*      */       case IRON_BARS:
/*      */       case IRON_BLOCK:
/*      */       case JUKEBOX:
/*      */       case MOSSY_COBBLESTONE:
/*      */       case MOSSY_COBBLESTONE_SLAB:
/*      */       case MOSSY_COBBLESTONE_STAIRS:
/*      */       case MOSSY_COBBLESTONE_WALL:
/*      */       case MOSSY_STONE_BRICKS:
/*      */       case MOSSY_STONE_BRICK_SLAB:
/*      */       case MOSSY_STONE_BRICK_STAIRS:
/*      */       case MOSSY_STONE_BRICK_WALL:
/*      */       case NETHER_BRICKS:
/*      */       case NETHER_BRICK_FENCE:
/*      */       case NETHER_BRICK_SLAB:
/*      */       case NETHER_BRICK_STAIRS:
/*      */       case NETHER_BRICK_WALL:
/*      */       case PETRIFIED_OAK_SLAB:
/*      */       case POLISHED_ANDESITE:
/*      */       case POLISHED_ANDESITE_SLAB:
/*      */       case POLISHED_ANDESITE_STAIRS:
/*      */       case POLISHED_BLACKSTONE:
/*      */       case POLISHED_BLACKSTONE_BRICKS:
/*      */       case POLISHED_BLACKSTONE_BRICK_SLAB:
/*      */       case POLISHED_BLACKSTONE_BRICK_STAIRS:
/*      */       case POLISHED_BLACKSTONE_BRICK_WALL:
/*      */       case POLISHED_BLACKSTONE_SLAB:
/*      */       case POLISHED_BLACKSTONE_STAIRS:
/*      */       case POLISHED_BLACKSTONE_WALL:
/*      */       case POLISHED_DIORITE:
/*      */       case POLISHED_DIORITE_SLAB:
/*      */       case POLISHED_DIORITE_STAIRS:
/*      */       case POLISHED_GRANITE:
/*      */       case POLISHED_GRANITE_SLAB:
/*      */       case POLISHED_GRANITE_STAIRS:
/*      */       case PRISMARINE:
/*      */       case PRISMARINE_BRICKS:
/*      */       case PRISMARINE_BRICK_SLAB:
/*      */       case PRISMARINE_BRICK_STAIRS:
/*      */       case PRISMARINE_SLAB:
/*      */       case PRISMARINE_STAIRS:
/*      */       case PRISMARINE_WALL:
/*      */       case PURPUR_BLOCK:
/*      */       case PURPUR_PILLAR:
/*      */       case PURPUR_SLAB:
/*      */       case PURPUR_STAIRS:
/*      */       case QUARTZ_SLAB:
/*      */       case REDSTONE_BLOCK:
/*      */       case RED_NETHER_BRICKS:
/*      */       case RED_NETHER_BRICK_SLAB:
/*      */       case RED_NETHER_BRICK_STAIRS:
/*      */       case RED_NETHER_BRICK_WALL:
/*      */       case RED_SANDSTONE_SLAB:
/*      */       case SANDSTONE_SLAB:
/*      */       case SMOOTH_QUARTZ:
/*      */       case SMOOTH_QUARTZ_SLAB:
/*      */       case SMOOTH_QUARTZ_STAIRS:
/*      */       case SMOOTH_RED_SANDSTONE:
/*      */       case SMOOTH_RED_SANDSTONE_SLAB:
/*      */       case SMOOTH_RED_SANDSTONE_STAIRS:
/*      */       case SMOOTH_SANDSTONE:
/*      */       case SMOOTH_SANDSTONE_SLAB:
/*      */       case SMOOTH_SANDSTONE_STAIRS:
/*      */       case SMOOTH_STONE:
/*      */       case SMOOTH_STONE_SLAB:
/*      */       case STONE:
/*      */       case STONE_BRICKS:
/*      */       case STONE_BRICK_SLAB:
/*      */       case STONE_BRICK_STAIRS:
/*      */       case STONE_BRICK_WALL:
/*      */       case STONE_SLAB:
/*      */       case STONE_STAIRS:
/*      */       case TUBE_CORAL_BLOCK:
/* 8582 */         return 6.0F;
/*      */       case DRAGON_EGG:
/*      */       case END_STONE:
/*      */       case END_STONE_BRICKS:
/*      */       case END_STONE_BRICK_SLAB:
/*      */       case END_STONE_BRICK_STAIRS:
/*      */       case END_STONE_BRICK_WALL:
/* 8589 */         return 9.0F;
/*      */       case LAVA:
/*      */       case WATER:
/* 8592 */         return 100.0F;
/*      */       case ENDER_CHEST:
/* 8594 */         return 600.0F;
/*      */       case ANCIENT_DEBRIS:
/*      */       case ANVIL:
/*      */       case CHIPPED_ANVIL:
/*      */       case CRYING_OBSIDIAN:
/*      */       case DAMAGED_ANVIL:
/*      */       case ENCHANTING_TABLE:
/*      */       case NETHERITE_BLOCK:
/*      */       case OBSIDIAN:
/*      */       case RESPAWN_ANCHOR:
/* 8604 */         return 1200.0F;
/*      */       case BEDROCK:
/*      */       case CHAIN_COMMAND_BLOCK:
/*      */       case COMMAND_BLOCK:
/*      */       case END_GATEWAY:
/*      */       case END_PORTAL:
/*      */       case END_PORTAL_FRAME:
/*      */       case JIGSAW:
/*      */       case REPEATING_COMMAND_BLOCK:
/*      */       case STRUCTURE_BLOCK:
/* 8614 */         return 3600000.0F;
/*      */       case BARRIER:
/* 8616 */         return 3600000.8F;
/*      */     } 
/* 8618 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Material getCraftingRemainingItem() {
/* 8633 */     Validate.isTrue(isItem(), "The Material is not an item!");
/* 8634 */     switch (this) {
/*      */       
/*      */       case LAVA_BUCKET:
/*      */       case WATER_BUCKET:
/*      */       case MILK_BUCKET:
/* 8639 */         return BUCKET;
/*      */       case HONEY_BOTTLE:
/*      */       case DRAGON_BREATH:
/* 8642 */         return GLASS_BOTTLE;
/*      */     } 
/* 8644 */     return null;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Material.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */