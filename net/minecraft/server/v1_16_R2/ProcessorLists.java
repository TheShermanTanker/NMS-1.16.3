/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.List;
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
/*     */ public class ProcessorLists
/*     */ {
/*  24 */   private static final DefinedStructureProcessorPredicates E = new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.BLACKSTONE, 0.01F), DefinedStructureTestTrue.b, Blocks.GILDED_BLACKSTONE.getBlockData());
/*  25 */   private static final DefinedStructureProcessorPredicates F = new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GILDED_BLACKSTONE, 0.5F), DefinedStructureTestTrue.b, Blocks.BLACKSTONE.getBlockData());
/*     */   
/*  27 */   public static final ProcessorList a = a("empty", ImmutableList.of());
/*     */   
/*  29 */   public static final ProcessorList b = a("zombie_plains", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.COBBLESTONE, 0.8F), DefinedStructureTestTrue.b, Blocks.MOSSY_COBBLESTONE
/*  30 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestTag(TagsBlock.DOORS), DefinedStructureTestTrue.b, Blocks.AIR
/*  31 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  32 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.WALL_TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  33 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.COBBLESTONE, 0.07F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  34 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.MOSSY_COBBLESTONE, 0.07F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  35 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHITE_TERRACOTTA, 0.07F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  36 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.OAK_LOG, 0.05F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  37 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.OAK_PLANKS, 0.1F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  38 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.OAK_STAIRS, 0.1F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  39 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.STRIPPED_OAK_LOG, 0.02F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  40 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GLASS_PANE, 0.5F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  41 */               .getBlockData()), (Object[])new DefinedStructureProcessorPredicates[] { new DefinedStructureProcessorPredicates(new DefinedStructureTestBlockState(Blocks.GLASS_PANE
/*  42 */                   .getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true))), DefinedStructureTestTrue.b, Blocks.BROWN_STAINED_GLASS_PANE.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true))), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlockState(Blocks.GLASS_PANE
/*  43 */                   .getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true))), DefinedStructureTestTrue.b, Blocks.BROWN_STAINED_GLASS_PANE.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true))), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.3F), DefinedStructureTestTrue.b, Blocks.CARROTS
/*  44 */                 .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.2F), DefinedStructureTestTrue.b, Blocks.POTATOES
/*  45 */                 .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.1F), DefinedStructureTestTrue.b, Blocks.BEETROOTS
/*  46 */                 .getBlockData()) }))));
/*     */ 
/*     */   
/*  49 */   public static final ProcessorList c = a("zombie_savanna", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestTag(TagsBlock.DOORS), DefinedStructureTestTrue.b, Blocks.AIR
/*  50 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  51 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.WALL_TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  52 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.ACACIA_PLANKS, 0.2F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  53 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.ACACIA_STAIRS, 0.2F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  54 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.ACACIA_LOG, 0.05F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  55 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.ACACIA_WOOD, 0.05F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  56 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.ORANGE_TERRACOTTA, 0.05F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  57 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.YELLOW_TERRACOTTA, 0.05F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  58 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.RED_TERRACOTTA, 0.05F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  59 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GLASS_PANE, 0.5F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  60 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlockState(Blocks.GLASS_PANE
/*  61 */                 .getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true))), DefinedStructureTestTrue.b, Blocks.BROWN_STAINED_GLASS_PANE.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true))), (Object[])new DefinedStructureProcessorPredicates[] { new DefinedStructureProcessorPredicates(new DefinedStructureTestBlockState(Blocks.GLASS_PANE
/*  62 */                   .getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true))), DefinedStructureTestTrue.b, Blocks.BROWN_STAINED_GLASS_PANE.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true))), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.1F), DefinedStructureTestTrue.b, Blocks.MELON_STEM
/*  63 */                 .getBlockData()) }))));
/*     */ 
/*     */   
/*  66 */   public static final ProcessorList d = a("zombie_snowy", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestTag(TagsBlock.DOORS), DefinedStructureTestTrue.b, Blocks.AIR
/*  67 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  68 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.WALL_TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  69 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.LANTERN), DefinedStructureTestTrue.b, Blocks.AIR
/*  70 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.SPRUCE_PLANKS, 0.2F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  71 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.SPRUCE_SLAB, 0.4F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  72 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.STRIPPED_SPRUCE_LOG, 0.05F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  73 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.STRIPPED_SPRUCE_WOOD, 0.05F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  74 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GLASS_PANE, 0.5F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  75 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlockState(Blocks.GLASS_PANE
/*  76 */                 .getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true))), DefinedStructureTestTrue.b, Blocks.BROWN_STAINED_GLASS_PANE.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true))), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlockState(Blocks.GLASS_PANE
/*  77 */                 .getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true))), DefinedStructureTestTrue.b, Blocks.BROWN_STAINED_GLASS_PANE.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true))), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.1F), DefinedStructureTestTrue.b, Blocks.CARROTS
/*  78 */               .getBlockData()), (Object[])new DefinedStructureProcessorPredicates[] { new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.8F), DefinedStructureTestTrue.b, Blocks.POTATOES
/*  79 */                 .getBlockData()) }))));
/*     */ 
/*     */   
/*  82 */   public static final ProcessorList e = a("zombie_taiga", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.COBBLESTONE, 0.8F), DefinedStructureTestTrue.b, Blocks.MOSSY_COBBLESTONE
/*  83 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestTag(TagsBlock.DOORS), DefinedStructureTestTrue.b, Blocks.AIR
/*  84 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  85 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.WALL_TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  86 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.CAMPFIRE), DefinedStructureTestTrue.b, Blocks.CAMPFIRE
/*  87 */               .getBlockData().set(BlockCampfire.b, Boolean.valueOf(false))), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.COBBLESTONE, 0.08F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  88 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.SPRUCE_LOG, 0.08F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  89 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GLASS_PANE, 0.5F), DefinedStructureTestTrue.b, Blocks.COBWEB
/*  90 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlockState(Blocks.GLASS_PANE
/*  91 */                 .getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true))), DefinedStructureTestTrue.b, Blocks.BROWN_STAINED_GLASS_PANE.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true))), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlockState(Blocks.GLASS_PANE
/*  92 */                 .getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true))), DefinedStructureTestTrue.b, Blocks.BROWN_STAINED_GLASS_PANE.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true))), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.3F), DefinedStructureTestTrue.b, Blocks.PUMPKIN_STEM
/*  93 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.2F), DefinedStructureTestTrue.b, Blocks.POTATOES
/*  94 */               .getBlockData()), (Object[])new DefinedStructureProcessorPredicates[0]))));
/*     */ 
/*     */   
/*  97 */   public static final ProcessorList f = a("zombie_desert", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestTag(TagsBlock.DOORS), DefinedStructureTestTrue.b, Blocks.AIR
/*  98 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/*  99 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.WALL_TORCH), DefinedStructureTestTrue.b, Blocks.AIR
/* 100 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.SMOOTH_SANDSTONE, 0.08F), DefinedStructureTestTrue.b, Blocks.COBWEB
/* 101 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.CUT_SANDSTONE, 0.1F), DefinedStructureTestTrue.b, Blocks.COBWEB
/* 102 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.TERRACOTTA, 0.08F), DefinedStructureTestTrue.b, Blocks.COBWEB
/* 103 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.SMOOTH_SANDSTONE_STAIRS, 0.08F), DefinedStructureTestTrue.b, Blocks.COBWEB
/* 104 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.SMOOTH_SANDSTONE_SLAB, 0.08F), DefinedStructureTestTrue.b, Blocks.COBWEB
/* 105 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.2F), DefinedStructureTestTrue.b, Blocks.BEETROOTS
/* 106 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.1F), DefinedStructureTestTrue.b, Blocks.MELON_STEM
/* 107 */               .getBlockData())))));
/*     */ 
/*     */   
/* 110 */   public static final ProcessorList g = a("mossify_10_percent", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.COBBLESTONE, 0.1F), DefinedStructureTestTrue.b, Blocks.MOSSY_COBBLESTONE
/* 111 */               .getBlockData())))));
/*     */ 
/*     */   
/* 114 */   public static final ProcessorList h = a("mossify_20_percent", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.COBBLESTONE, 0.2F), DefinedStructureTestTrue.b, Blocks.MOSSY_COBBLESTONE
/* 115 */               .getBlockData())))));
/*     */ 
/*     */   
/* 118 */   public static final ProcessorList i = a("mossify_70_percent", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.COBBLESTONE, 0.7F), DefinedStructureTestTrue.b, Blocks.MOSSY_COBBLESTONE
/* 119 */               .getBlockData())))));
/*     */ 
/*     */   
/* 122 */   public static final ProcessorList j = a("street_plains", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.GRASS_PATH), new DefinedStructureTestBlock(Blocks.WATER), Blocks.OAK_PLANKS
/* 123 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GRASS_PATH, 0.1F), DefinedStructureTestTrue.b, Blocks.GRASS_BLOCK
/* 124 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.GRASS_BLOCK), new DefinedStructureTestBlock(Blocks.WATER), Blocks.WATER
/* 125 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.DIRT), new DefinedStructureTestBlock(Blocks.WATER), Blocks.WATER
/* 126 */               .getBlockData())))));
/*     */ 
/*     */   
/* 129 */   public static final ProcessorList k = a("street_savanna", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.GRASS_PATH), new DefinedStructureTestBlock(Blocks.WATER), Blocks.ACACIA_PLANKS
/* 130 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GRASS_PATH, 0.2F), DefinedStructureTestTrue.b, Blocks.GRASS_BLOCK
/* 131 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.GRASS_BLOCK), new DefinedStructureTestBlock(Blocks.WATER), Blocks.WATER
/* 132 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.DIRT), new DefinedStructureTestBlock(Blocks.WATER), Blocks.WATER
/* 133 */               .getBlockData())))));
/*     */ 
/*     */   
/* 136 */   public static final ProcessorList l = a("street_snowy_or_taiga", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.GRASS_PATH), new DefinedStructureTestBlock(Blocks.WATER), Blocks.SPRUCE_PLANKS
/* 137 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GRASS_PATH, 0.2F), DefinedStructureTestTrue.b, Blocks.GRASS_BLOCK
/* 138 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.GRASS_BLOCK), new DefinedStructureTestBlock(Blocks.WATER), Blocks.WATER
/* 139 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(Blocks.DIRT), new DefinedStructureTestBlock(Blocks.WATER), Blocks.WATER
/* 140 */               .getBlockData())))));
/*     */ 
/*     */   
/* 143 */   public static final ProcessorList m = a("farm_plains", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.3F), DefinedStructureTestTrue.b, Blocks.CARROTS
/* 144 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.2F), DefinedStructureTestTrue.b, Blocks.POTATOES
/* 145 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.1F), DefinedStructureTestTrue.b, Blocks.BEETROOTS
/* 146 */               .getBlockData())))));
/*     */ 
/*     */   
/* 149 */   public static final ProcessorList n = a("farm_savanna", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.1F), DefinedStructureTestTrue.b, Blocks.MELON_STEM
/* 150 */               .getBlockData())))));
/*     */ 
/*     */   
/* 153 */   public static final ProcessorList o = a("farm_snowy", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.1F), DefinedStructureTestTrue.b, Blocks.CARROTS
/* 154 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.8F), DefinedStructureTestTrue.b, Blocks.POTATOES
/* 155 */               .getBlockData())))));
/*     */ 
/*     */   
/* 158 */   public static final ProcessorList p = a("farm_taiga", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.3F), DefinedStructureTestTrue.b, Blocks.PUMPKIN_STEM
/* 159 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.2F), DefinedStructureTestTrue.b, Blocks.POTATOES
/* 160 */               .getBlockData())))));
/*     */ 
/*     */   
/* 163 */   public static final ProcessorList q = a("farm_desert", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.2F), DefinedStructureTestTrue.b, Blocks.BEETROOTS
/* 164 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.WHEAT, 0.1F), DefinedStructureTestTrue.b, Blocks.MELON_STEM
/* 165 */               .getBlockData())))));
/*     */ 
/*     */   
/* 168 */   public static final ProcessorList r = a("outpost_rot", ImmutableList.of(new DefinedStructureProcessorRotation(0.05F)));
/*     */   
/* 170 */   public static final ProcessorList s = a("bottom_rampart", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.MAGMA_BLOCK, 0.75F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 171 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0.15F), DefinedStructureTestTrue.b, Blocks.POLISHED_BLACKSTONE_BRICKS
/* 172 */               .getBlockData()), F, E))));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public static final ProcessorList t = a("treasure_rooms", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.35F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 178 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.CHISELED_POLISHED_BLACKSTONE, 0.1F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 179 */               .getBlockData()), F, E))));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public static final ProcessorList u = a("housing", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.3F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 185 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.BLACKSTONE, 1.0E-4F), DefinedStructureTestTrue.b, Blocks.AIR
/* 186 */               .getBlockData()), F, E))));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public static final ProcessorList v = a("side_wall_degradation", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.CHISELED_POLISHED_BLACKSTONE, 0.5F), DefinedStructureTestTrue.b, Blocks.AIR
/* 192 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GOLD_BLOCK, 0.1F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 193 */               .getBlockData()), F, E))));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public static final ProcessorList w = a("stable_degradation", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.1F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 199 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.BLACKSTONE, 1.0E-4F), DefinedStructureTestTrue.b, Blocks.AIR
/* 200 */               .getBlockData()), F, E))));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 205 */   public static final ProcessorList x = a("bastion_generic_degradation", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.3F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 206 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.BLACKSTONE, 1.0E-4F), DefinedStructureTestTrue.b, Blocks.AIR
/* 207 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GOLD_BLOCK, 0.3F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 208 */               .getBlockData()), F, E))));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public static final ProcessorList y = a("rampart_degradation", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.4F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 214 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.BLACKSTONE, 0.01F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 215 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 1.0E-4F), DefinedStructureTestTrue.b, Blocks.AIR
/* 216 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.BLACKSTONE, 1.0E-4F), DefinedStructureTestTrue.b, Blocks.AIR
/* 217 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GOLD_BLOCK, 0.3F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 218 */               .getBlockData()), F, E))));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public static final ProcessorList z = a("entrance_replacement", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.CHISELED_POLISHED_BLACKSTONE, 0.5F), DefinedStructureTestTrue.b, Blocks.AIR
/* 224 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GOLD_BLOCK, 0.6F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 225 */               .getBlockData()), F, E))));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public static final ProcessorList A = a("bridge", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.3F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 231 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.BLACKSTONE, 1.0E-4F), DefinedStructureTestTrue.b, Blocks.AIR
/* 232 */               .getBlockData())))));
/*     */ 
/*     */   
/* 235 */   public static final ProcessorList B = a("roof", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.3F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 236 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.15F), DefinedStructureTestTrue.b, Blocks.AIR
/* 237 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.3F), DefinedStructureTestTrue.b, Blocks.BLACKSTONE
/* 238 */               .getBlockData())))));
/*     */ 
/*     */   
/* 241 */   public static final ProcessorList C = a("high_wall", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.01F), DefinedStructureTestTrue.b, Blocks.AIR
/* 242 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.5F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 243 */               .getBlockData()), new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.3F), DefinedStructureTestTrue.b, Blocks.BLACKSTONE
/* 244 */               .getBlockData()), F))));
/*     */ 
/*     */ 
/*     */   
/* 248 */   public static final ProcessorList D = a("high_rampart", ImmutableList.of(new DefinedStructureProcessorRule((List<? extends DefinedStructureProcessorPredicates>)ImmutableList.of(new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(Blocks.GOLD_BLOCK, 0.3F), DefinedStructureTestTrue.b, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
/* 249 */               .getBlockData()), new DefinedStructureProcessorPredicates(DefinedStructureTestTrue.b, DefinedStructureTestTrue.b, new PosRuleTestAxisAlignedLinear(0.0F, 0.05F, 0, 100, EnumDirection.EnumAxis.Y), Blocks.AIR
/* 250 */               .getBlockData()), F))));
/*     */ 
/*     */ 
/*     */   
/*     */   private static ProcessorList a(String var0, ImmutableList<DefinedStructureProcessor> var1) {
/* 255 */     MinecraftKey var2 = new MinecraftKey(var0);
/* 256 */     ProcessorList var3 = new ProcessorList((List<DefinedStructureProcessor>)var1);
/* 257 */     return RegistryGeneration.<ProcessorList, ProcessorList>a(RegistryGeneration.g, var2, var3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ProcessorLists.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */