/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.function.ToIntFunction;
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
/*     */ public class Blocks
/*     */ {
/*     */   private static ToIntFunction<IBlockData> a(int var0) {
/*  39 */     return var1 -> ((Boolean)var1.get(BlockProperties.r)).booleanValue() ? var0 : 0;
/*     */   }
/*     */   
/*     */   private static Boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, EntityTypes<?> var3) {
/*  43 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   private static Boolean b(IBlockData var0, IBlockAccess var1, BlockPosition var2, EntityTypes<?> var3) {
/*  47 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   private static Boolean c(IBlockData var0, IBlockAccess var1, BlockPosition var2, EntityTypes<?> var3) {
/*  51 */     return Boolean.valueOf((var3 == EntityTypes.OCELOT || var3 == EntityTypes.PARROT));
/*     */   }
/*     */   
/*     */   private static BlockBed a(EnumColor var0) {
/*  55 */     return new BlockBed(var0, BlockBase.Info.a(Material.CLOTH, var1 -> (var1.get(BlockBed.PART) == BlockPropertyBedPart.FOOT) ? var0.f() : MaterialMapColor.e).a(SoundEffectType.a).d(0.2F).b());
/*     */   }
/*     */   
/*     */   private static BlockRotatable a(MaterialMapColor var0, MaterialMapColor var1) {
/*  59 */     return new BlockRotatable(BlockBase.Info.a(Material.WOOD, var2 -> (var2.get(BlockRotatable.AXIS) == EnumDirection.EnumAxis.Y) ? var0 : var1).d(2.0F).a(SoundEffectType.a));
/*     */   }
/*     */   
/*     */   private static Block a(MaterialMapColor var0) {
/*  63 */     return new BlockRotatable(BlockBase.Info.a(Material.NETHER_WOOD, var1 -> var0).d(2.0F).a(SoundEffectType.z));
/*     */   }
/*     */   
/*     */   private static boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/*  67 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean b(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   private static BlockStainedGlass b(EnumColor var0) {
/*  75 */     return new BlockStainedGlass(var0, BlockBase.Info.a(Material.SHATTERABLE, var0).d(0.3F).a(SoundEffectType.g).b().a(Blocks::a).a(Blocks::b).b(Blocks::b).c(Blocks::b));
/*     */   }
/*     */   
/*     */   private static BlockLeaves b() {
/*  79 */     return new BlockLeaves(BlockBase.Info.a(Material.LEAVES).d(0.2F).d().a(SoundEffectType.c).b().a(Blocks::c).b(Blocks::b).c(Blocks::b));
/*     */   }
/*     */   
/*     */   private static BlockShulkerBox a(EnumColor var0, BlockBase.Info var1) {
/*  83 */     BlockBase.e var2 = (var0, var1, var2) -> {
/*     */         TileEntity var3 = var1.getTileEntity(var2);
/*     */         if (!(var3 instanceof TileEntityShulkerBox)) {
/*     */           return true;
/*     */         }
/*     */         TileEntityShulkerBox var4 = (TileEntityShulkerBox)var3;
/*     */         return var4.l();
/*     */       };
/*  91 */     return new BlockShulkerBox(var0, var1.d(2.0F).e().b().b(var2).c(var2));
/*     */   }
/*     */   
/*     */   private static BlockPiston a(boolean var0) {
/*  95 */     BlockBase.e var1 = (var0, var1, var2) -> !((Boolean)var0.get(BlockPiston.EXTENDED)).booleanValue();
/*  96 */     return new BlockPiston(var0, BlockBase.Info.a(Material.PISTON).d(1.5F).a(Blocks::b).b(var1).c(var1));
/*     */   }
/*     */   
/*  99 */   public static final Block AIR = a("air", new BlockAir(BlockBase.Info.a(Material.AIR).a().f().g()));
/* 100 */   public static final Block STONE = a("stone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.m).h().a(1.5F, 6.0F)));
/* 101 */   public static final Block GRANITE = a("granite", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.l).h().a(1.5F, 6.0F)));
/* 102 */   public static final Block POLISHED_GRANITE = a("polished_granite", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.l).h().a(1.5F, 6.0F)));
/* 103 */   public static final Block DIORITE = a("diorite", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.p).h().a(1.5F, 6.0F)));
/* 104 */   public static final Block POLISHED_DIORITE = a("polished_diorite", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.p).h().a(1.5F, 6.0F)));
/* 105 */   public static final Block ANDESITE = a("andesite", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.m).h().a(1.5F, 6.0F)));
/* 106 */   public static final Block POLISHED_ANDESITE = a("polished_andesite", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.m).h().a(1.5F, 6.0F)));
/* 107 */   public static final Block GRASS_BLOCK = a("grass_block", new BlockGrass(BlockBase.Info.a(Material.GRASS).d().d(0.6F).a(SoundEffectType.c)));
/* 108 */   public static final Block DIRT = a("dirt", new Block(BlockBase.Info.a(Material.EARTH, MaterialMapColor.l).d(0.5F).a(SoundEffectType.b)));
/* 109 */   public static final Block COARSE_DIRT = a("coarse_dirt", new Block(BlockBase.Info.a(Material.EARTH, MaterialMapColor.l).d(0.5F).a(SoundEffectType.b)));
/* 110 */   public static final Block PODZOL = a("podzol", new BlockDirtSnow(BlockBase.Info.a(Material.EARTH, MaterialMapColor.J).d(0.5F).a(SoundEffectType.b)));
/* 111 */   public static final Block COBBLESTONE = a("cobblestone", new Block(BlockBase.Info.a(Material.STONE).h().a(2.0F, 6.0F)));
/* 112 */   public static final Block OAK_PLANKS = a("oak_planks", new Block(BlockBase.Info.a(Material.WOOD, MaterialMapColor.o).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 113 */   public static final Block SPRUCE_PLANKS = a("spruce_planks", new Block(BlockBase.Info.a(Material.WOOD, MaterialMapColor.J).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 114 */   public static final Block BIRCH_PLANKS = a("birch_planks", new Block(BlockBase.Info.a(Material.WOOD, MaterialMapColor.d).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 115 */   public static final Block JUNGLE_PLANKS = a("jungle_planks", new Block(BlockBase.Info.a(Material.WOOD, MaterialMapColor.l).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 116 */   public static final Block ACACIA_PLANKS = a("acacia_planks", new Block(BlockBase.Info.a(Material.WOOD, MaterialMapColor.q).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 117 */   public static final Block DARK_OAK_PLANKS = a("dark_oak_planks", new Block(BlockBase.Info.a(Material.WOOD, MaterialMapColor.B).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 118 */   public static final Block OAK_SAPLING = a("oak_sapling", new BlockSapling(new WorldGenTreeProviderOak(), BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.c)));
/* 119 */   public static final Block SPRUCE_SAPLING = a("spruce_sapling", new BlockSapling(new WorldGenTreeProviderSpruce(), BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.c)));
/* 120 */   public static final Block BIRCH_SAPLING = a("birch_sapling", new BlockSapling(new WorldGenTreeProviderBirch(), BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.c)));
/* 121 */   public static final Block JUNGLE_SAPLING = a("jungle_sapling", new BlockSapling(new WorldGenMegaTreeProviderJungle(), BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.c)));
/* 122 */   public static final Block ACACIA_SAPLING = a("acacia_sapling", new BlockSapling(new WorldGenTreeProviderAcacia(), BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.c)));
/* 123 */   public static final Block DARK_OAK_SAPLING = a("dark_oak_sapling", new BlockSapling(new WorldGenMegaTreeProviderDarkOak(), BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.c)));
/* 124 */   public static final Block BEDROCK = a("bedrock", new Block(BlockBase.Info.a(Material.STONE).a(-1.0F, 3600000.0F).f().a(Blocks::a)));
/* 125 */   public static final Block WATER = a("water", new BlockFluids(FluidTypes.WATER, BlockBase.Info.a(Material.WATER).a().d(100.0F).f()));
/* 126 */   public static final Block LAVA = a("lava", new BlockFluids(FluidTypes.LAVA, BlockBase.Info.a(Material.LAVA).a().d().d(100.0F).a(var0 -> 15).f()));
/* 127 */   public static final Block SAND = a("sand", new BlockSand(14406560, BlockBase.Info.a(Material.SAND, MaterialMapColor.d).d(0.5F).a(SoundEffectType.i)));
/* 128 */   public static final Block RED_SAND = a("red_sand", new BlockSand(11098145, BlockBase.Info.a(Material.SAND, MaterialMapColor.q).d(0.5F).a(SoundEffectType.i)));
/* 129 */   public static final Block GRAVEL = a("gravel", new BlockGravel(BlockBase.Info.a(Material.SAND, MaterialMapColor.m).d(0.6F).a(SoundEffectType.b)));
/* 130 */   public static final Block GOLD_ORE = a("gold_ore", new BlockOre(BlockBase.Info.a(Material.STONE).h().a(3.0F, 3.0F)));
/* 131 */   public static final Block IRON_ORE = a("iron_ore", new BlockOre(BlockBase.Info.a(Material.STONE).h().a(3.0F, 3.0F)));
/* 132 */   public static final Block COAL_ORE = a("coal_ore", new BlockOre(BlockBase.Info.a(Material.STONE).h().a(3.0F, 3.0F)));
/* 133 */   public static final Block NETHER_GOLD_ORE = a("nether_gold_ore", new BlockOre(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(3.0F, 3.0F).a(SoundEffectType.T)));
/* 134 */   public static final Block OAK_LOG = a("oak_log", a(MaterialMapColor.o, MaterialMapColor.J));
/* 135 */   public static final Block SPRUCE_LOG = a("spruce_log", a(MaterialMapColor.J, MaterialMapColor.B));
/* 136 */   public static final Block BIRCH_LOG = a("birch_log", a(MaterialMapColor.d, MaterialMapColor.p));
/* 137 */   public static final Block JUNGLE_LOG = a("jungle_log", a(MaterialMapColor.l, MaterialMapColor.J));
/* 138 */   public static final Block ACACIA_LOG = a("acacia_log", a(MaterialMapColor.q, MaterialMapColor.m));
/* 139 */   public static final Block DARK_OAK_LOG = a("dark_oak_log", a(MaterialMapColor.B, MaterialMapColor.B));
/* 140 */   public static final Block STRIPPED_SPRUCE_LOG = a("stripped_spruce_log", a(MaterialMapColor.J, MaterialMapColor.J));
/* 141 */   public static final Block STRIPPED_BIRCH_LOG = a("stripped_birch_log", a(MaterialMapColor.d, MaterialMapColor.d));
/* 142 */   public static final Block STRIPPED_JUNGLE_LOG = a("stripped_jungle_log", a(MaterialMapColor.l, MaterialMapColor.l));
/* 143 */   public static final Block STRIPPED_ACACIA_LOG = a("stripped_acacia_log", a(MaterialMapColor.q, MaterialMapColor.q));
/* 144 */   public static final Block STRIPPED_DARK_OAK_LOG = a("stripped_dark_oak_log", a(MaterialMapColor.B, MaterialMapColor.B));
/* 145 */   public static final Block STRIPPED_OAK_LOG = a("stripped_oak_log", a(MaterialMapColor.o, MaterialMapColor.o));
/* 146 */   public static final Block OAK_WOOD = a("oak_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.o).d(2.0F).a(SoundEffectType.a)));
/* 147 */   public static final Block SPRUCE_WOOD = a("spruce_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.J).d(2.0F).a(SoundEffectType.a)));
/* 148 */   public static final Block BIRCH_WOOD = a("birch_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.d).d(2.0F).a(SoundEffectType.a)));
/* 149 */   public static final Block JUNGLE_WOOD = a("jungle_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.l).d(2.0F).a(SoundEffectType.a)));
/* 150 */   public static final Block ACACIA_WOOD = a("acacia_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.w).d(2.0F).a(SoundEffectType.a)));
/* 151 */   public static final Block DARK_OAK_WOOD = a("dark_oak_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.B).d(2.0F).a(SoundEffectType.a)));
/* 152 */   public static final Block STRIPPED_OAK_WOOD = a("stripped_oak_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.o).d(2.0F).a(SoundEffectType.a)));
/* 153 */   public static final Block STRIPPED_SPRUCE_WOOD = a("stripped_spruce_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.J).d(2.0F).a(SoundEffectType.a)));
/* 154 */   public static final Block STRIPPED_BIRCH_WOOD = a("stripped_birch_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.d).d(2.0F).a(SoundEffectType.a)));
/* 155 */   public static final Block STRIPPED_JUNGLE_WOOD = a("stripped_jungle_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.l).d(2.0F).a(SoundEffectType.a)));
/* 156 */   public static final Block STRIPPED_ACACIA_WOOD = a("stripped_acacia_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.q).d(2.0F).a(SoundEffectType.a)));
/* 157 */   public static final Block STRIPPED_DARK_OAK_WOOD = a("stripped_dark_oak_wood", new BlockRotatable(BlockBase.Info.a(Material.WOOD, MaterialMapColor.B).d(2.0F).a(SoundEffectType.a)));
/* 158 */   public static final Block OAK_LEAVES = a("oak_leaves", b());
/* 159 */   public static final Block SPRUCE_LEAVES = a("spruce_leaves", b());
/* 160 */   public static final Block BIRCH_LEAVES = a("birch_leaves", b());
/* 161 */   public static final Block JUNGLE_LEAVES = a("jungle_leaves", b());
/* 162 */   public static final Block ACACIA_LEAVES = a("acacia_leaves", b());
/* 163 */   public static final Block DARK_OAK_LEAVES = a("dark_oak_leaves", b());
/* 164 */   public static final Block SPONGE = a("sponge", new BlockSponge(BlockBase.Info.a(Material.SPONGE).d(0.6F).a(SoundEffectType.c)));
/* 165 */   public static final Block WET_SPONGE = a("wet_sponge", new BlockWetSponge(BlockBase.Info.a(Material.SPONGE).d(0.6F).a(SoundEffectType.c)));
/* 166 */   public static final Block GLASS = a("glass", new BlockGlass(BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b().a(Blocks::a).a(Blocks::b).b(Blocks::b).c(Blocks::b)));
/* 167 */   public static final Block LAPIS_ORE = a("lapis_ore", new BlockOre(BlockBase.Info.a(Material.STONE).h().a(3.0F, 3.0F)));
/* 168 */   public static final Block LAPIS_BLOCK = a("lapis_block", new Block(BlockBase.Info.a(Material.ORE, MaterialMapColor.H).h().a(3.0F, 3.0F)));
/* 169 */   public static final Block DISPENSER = a("dispenser", new BlockDispenser(BlockBase.Info.a(Material.STONE).h().d(3.5F)));
/* 170 */   public static final Block SANDSTONE = a("sandstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().d(0.8F)));
/* 171 */   public static final Block CHISELED_SANDSTONE = a("chiseled_sandstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().d(0.8F)));
/* 172 */   public static final Block CUT_SANDSTONE = a("cut_sandstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().d(0.8F)));
/* 173 */   public static final Block NOTE_BLOCK = a("note_block", new BlockNote(BlockBase.Info.a(Material.WOOD).a(SoundEffectType.a).d(0.8F)));
/* 174 */   public static final Block WHITE_BED = a("white_bed", a(EnumColor.WHITE));
/* 175 */   public static final Block ORANGE_BED = a("orange_bed", a(EnumColor.ORANGE));
/* 176 */   public static final Block MAGENTA_BED = a("magenta_bed", a(EnumColor.MAGENTA));
/* 177 */   public static final Block LIGHT_BLUE_BED = a("light_blue_bed", a(EnumColor.LIGHT_BLUE));
/* 178 */   public static final Block YELLOW_BED = a("yellow_bed", a(EnumColor.YELLOW));
/* 179 */   public static final Block LIME_BED = a("lime_bed", a(EnumColor.LIME));
/* 180 */   public static final Block PINK_BED = a("pink_bed", a(EnumColor.PINK));
/* 181 */   public static final Block GRAY_BED = a("gray_bed", a(EnumColor.GRAY));
/* 182 */   public static final Block LIGHT_GRAY_BED = a("light_gray_bed", a(EnumColor.LIGHT_GRAY));
/* 183 */   public static final Block CYAN_BED = a("cyan_bed", a(EnumColor.CYAN));
/* 184 */   public static final Block PURPLE_BED = a("purple_bed", a(EnumColor.PURPLE));
/* 185 */   public static final Block BLUE_BED = a("blue_bed", a(EnumColor.BLUE));
/* 186 */   public static final Block BROWN_BED = a("brown_bed", a(EnumColor.BROWN));
/* 187 */   public static final Block GREEN_BED = a("green_bed", a(EnumColor.GREEN));
/* 188 */   public static final Block RED_BED = a("red_bed", a(EnumColor.RED));
/* 189 */   public static final Block BLACK_BED = a("black_bed", a(EnumColor.BLACK));
/* 190 */   public static final Block POWERED_RAIL = a("powered_rail", new BlockPoweredRail(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.7F).a(SoundEffectType.f)));
/* 191 */   public static final Block DETECTOR_RAIL = a("detector_rail", new BlockMinecartDetector(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.7F).a(SoundEffectType.f)));
/* 192 */   public static final Block STICKY_PISTON = a("sticky_piston", a(true));
/* 193 */   public static final Block COBWEB = a("cobweb", new BlockWeb(BlockBase.Info.a(Material.WEB).a().h().d(4.0F)));
/* 194 */   public static final Block GRASS = a("grass", new BlockLongGrass(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().c().a(SoundEffectType.c)));
/* 195 */   public static final Block FERN = a("fern", new BlockLongGrass(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().c().a(SoundEffectType.c)));
/* 196 */   public static final Block DEAD_BUSH = a("dead_bush", new BlockDeadBush(BlockBase.Info.a(Material.REPLACEABLE_PLANT, MaterialMapColor.o).a().c().a(SoundEffectType.c)));
/* 197 */   public static final Block SEAGRASS = a("seagrass", new BlockSeaGrass(BlockBase.Info.a(Material.REPLACEABLE_WATER_PLANT).a().c().a(SoundEffectType.o)));
/* 198 */   public static final Block TALL_SEAGRASS = a("tall_seagrass", new BlockTallSeaGrass(BlockBase.Info.a(Material.REPLACEABLE_WATER_PLANT).a().c().a(SoundEffectType.o)));
/* 199 */   public static final Block PISTON = a("piston", a(false));
/* 200 */   public static final Block PISTON_HEAD = a("piston_head", new BlockPistonExtension(BlockBase.Info.a(Material.PISTON).d(1.5F).f()));
/* 201 */   public static final Block WHITE_WOOL = a("white_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.j).d(0.8F).a(SoundEffectType.h)));
/* 202 */   public static final Block ORANGE_WOOL = a("orange_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.q).d(0.8F).a(SoundEffectType.h)));
/* 203 */   public static final Block MAGENTA_WOOL = a("magenta_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.r).d(0.8F).a(SoundEffectType.h)));
/* 204 */   public static final Block LIGHT_BLUE_WOOL = a("light_blue_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.s).d(0.8F).a(SoundEffectType.h)));
/* 205 */   public static final Block YELLOW_WOOL = a("yellow_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.t).d(0.8F).a(SoundEffectType.h)));
/* 206 */   public static final Block LIME_WOOL = a("lime_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.u).d(0.8F).a(SoundEffectType.h)));
/* 207 */   public static final Block PINK_WOOL = a("pink_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.v).d(0.8F).a(SoundEffectType.h)));
/* 208 */   public static final Block GRAY_WOOL = a("gray_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.w).d(0.8F).a(SoundEffectType.h)));
/* 209 */   public static final Block LIGHT_GRAY_WOOL = a("light_gray_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.x).d(0.8F).a(SoundEffectType.h)));
/* 210 */   public static final Block CYAN_WOOL = a("cyan_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.y).d(0.8F).a(SoundEffectType.h)));
/* 211 */   public static final Block PURPLE_WOOL = a("purple_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.z).d(0.8F).a(SoundEffectType.h)));
/* 212 */   public static final Block BLUE_WOOL = a("blue_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.A).d(0.8F).a(SoundEffectType.h)));
/* 213 */   public static final Block BROWN_WOOL = a("brown_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.B).d(0.8F).a(SoundEffectType.h)));
/* 214 */   public static final Block GREEN_WOOL = a("green_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.C).d(0.8F).a(SoundEffectType.h)));
/* 215 */   public static final Block RED_WOOL = a("red_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.D).d(0.8F).a(SoundEffectType.h)));
/* 216 */   public static final Block BLACK_WOOL = a("black_wool", new Block(BlockBase.Info.a(Material.CLOTH, MaterialMapColor.E).d(0.8F).a(SoundEffectType.h)));
/* 217 */   public static final Block MOVING_PISTON = a("moving_piston", new BlockPistonMoving(BlockBase.Info.a(Material.PISTON).d(-1.0F).e().f().b().a(Blocks::b).b(Blocks::b).c(Blocks::b)));
/* 218 */   public static final Block DANDELION = a("dandelion", new BlockFlowers(MobEffects.SATURATION, 7, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 219 */   public static final Block POPPY = a("poppy", new BlockFlowers(MobEffects.NIGHT_VISION, 5, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 220 */   public static final Block BLUE_ORCHID = a("blue_orchid", new BlockFlowers(MobEffects.SATURATION, 7, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 221 */   public static final Block ALLIUM = a("allium", new BlockFlowers(MobEffects.FIRE_RESISTANCE, 4, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 222 */   public static final Block AZURE_BLUET = a("azure_bluet", new BlockFlowers(MobEffects.BLINDNESS, 8, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 223 */   public static final Block RED_TULIP = a("red_tulip", new BlockFlowers(MobEffects.WEAKNESS, 9, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 224 */   public static final Block ORANGE_TULIP = a("orange_tulip", new BlockFlowers(MobEffects.WEAKNESS, 9, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 225 */   public static final Block WHITE_TULIP = a("white_tulip", new BlockFlowers(MobEffects.WEAKNESS, 9, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 226 */   public static final Block PINK_TULIP = a("pink_tulip", new BlockFlowers(MobEffects.WEAKNESS, 9, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 227 */   public static final Block OXEYE_DAISY = a("oxeye_daisy", new BlockFlowers(MobEffects.REGENERATION, 8, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 228 */   public static final Block CORNFLOWER = a("cornflower", new BlockFlowers(MobEffects.JUMP, 6, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 229 */   public static final Block WITHER_ROSE = a("wither_rose", new BlockWitherRose(MobEffects.WITHER, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 230 */   public static final Block LILY_OF_THE_VALLEY = a("lily_of_the_valley", new BlockFlowers(MobEffects.POISON, 12, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.c)));
/* 231 */   public static final Block BROWN_MUSHROOM = a("brown_mushroom", new BlockMushroom(BlockBase.Info.a(Material.PLANT, MaterialMapColor.B).a().d().c().a(SoundEffectType.c).a(var0 -> 1).d(Blocks::a)));
/* 232 */   public static final Block RED_MUSHROOM = a("red_mushroom", new BlockMushroom(BlockBase.Info.a(Material.PLANT, MaterialMapColor.D).a().d().c().a(SoundEffectType.c).d(Blocks::a)));
/* 233 */   public static final Block GOLD_BLOCK = a("gold_block", new Block(BlockBase.Info.a(Material.ORE, MaterialMapColor.F).h().a(3.0F, 6.0F).a(SoundEffectType.f)));
/* 234 */   public static final Block IRON_BLOCK = a("iron_block", new Block(BlockBase.Info.a(Material.ORE, MaterialMapColor.h).h().a(5.0F, 6.0F).a(SoundEffectType.f)));
/* 235 */   public static final Block BRICKS = a("bricks", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.D).h().a(2.0F, 6.0F)));
/* 236 */   public static final Block TNT = a("tnt", new BlockTNT(BlockBase.Info.a(Material.TNT).c().a(SoundEffectType.c)));
/* 237 */   public static final Block BOOKSHELF = a("bookshelf", new Block(BlockBase.Info.a(Material.WOOD).d(1.5F).a(SoundEffectType.a)));
/* 238 */   public static final Block MOSSY_COBBLESTONE = a("mossy_cobblestone", new Block(BlockBase.Info.a(Material.STONE).h().a(2.0F, 6.0F)));
/* 239 */   public static final Block OBSIDIAN = a("obsidian", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.E).h().a(50.0F, 1200.0F)));
/* 240 */   public static final Block TORCH = a("torch", new BlockTorch(BlockBase.Info.a(Material.ORIENTABLE).a().c().a(var0 -> 14).a(SoundEffectType.a), Particles.FLAME));
/* 241 */   public static final Block WALL_TORCH = a("wall_torch", new BlockTorchWall(BlockBase.Info.a(Material.ORIENTABLE).a().c().a(var0 -> 14).a(SoundEffectType.a).a(TORCH), Particles.FLAME));
/* 242 */   public static final Block FIRE = a("fire", new BlockFire(BlockBase.Info.a(Material.FIRE, MaterialMapColor.f).a().c().a(var0 -> 15).a(SoundEffectType.h)));
/* 243 */   public static final Block SOUL_FIRE = a("soul_fire", new BlockSoulFire(BlockBase.Info.a(Material.FIRE, MaterialMapColor.s).a().c().a(var0 -> 10).a(SoundEffectType.h)));
/* 244 */   public static final Block SPAWNER = a("spawner", new BlockMobSpawner(BlockBase.Info.a(Material.STONE).h().d(5.0F).a(SoundEffectType.f).b()));
/* 245 */   public static final Block OAK_STAIRS = a("oak_stairs", new BlockStairs(OAK_PLANKS.getBlockData(), BlockBase.Info.a(OAK_PLANKS)));
/* 246 */   public static final Block CHEST = a("chest", new BlockChest(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a), () -> TileEntityTypes.CHEST));
/* 247 */   public static final Block REDSTONE_WIRE = a("redstone_wire", new BlockRedstoneWire(BlockBase.Info.a(Material.ORIENTABLE).a().c()));
/* 248 */   public static final Block DIAMOND_ORE = a("diamond_ore", new BlockOre(BlockBase.Info.a(Material.STONE).h().a(3.0F, 3.0F)));
/* 249 */   public static final Block DIAMOND_BLOCK = a("diamond_block", new Block(BlockBase.Info.a(Material.ORE, MaterialMapColor.G).h().a(5.0F, 6.0F).a(SoundEffectType.f)));
/* 250 */   public static final Block CRAFTING_TABLE = a("crafting_table", new BlockWorkbench(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a)));
/* 251 */   public static final Block WHEAT = a("wheat", new BlockCrops(BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.u)));
/* 252 */   public static final Block FARMLAND = a("farmland", new BlockSoil(BlockBase.Info.a(Material.EARTH).d().d(0.6F).a(SoundEffectType.b).c(Blocks::a).b(Blocks::a)));
/* 253 */   public static final Block FURNACE = a("furnace", new BlockFurnaceFurace(BlockBase.Info.a(Material.STONE).h().d(3.5F).a(a(13))));
/*     */   
/* 255 */   public static final Block OAK_SIGN = a("oak_sign", new BlockFloorSign(BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a), BlockPropertyWood.a));
/* 256 */   public static final Block SPRUCE_SIGN = a("spruce_sign", new BlockFloorSign(BlockBase.Info.a(Material.WOOD, SPRUCE_LOG.s()).a().d(1.0F).a(SoundEffectType.a), BlockPropertyWood.b));
/* 257 */   public static final Block BIRCH_SIGN = a("birch_sign", new BlockFloorSign(BlockBase.Info.a(Material.WOOD, MaterialMapColor.d).a().d(1.0F).a(SoundEffectType.a), BlockPropertyWood.c));
/* 258 */   public static final Block ACACIA_SIGN = a("acacia_sign", new BlockFloorSign(BlockBase.Info.a(Material.WOOD, MaterialMapColor.q).a().d(1.0F).a(SoundEffectType.a), BlockPropertyWood.d));
/* 259 */   public static final Block JUNGLE_SIGN = a("jungle_sign", new BlockFloorSign(BlockBase.Info.a(Material.WOOD, JUNGLE_LOG.s()).a().d(1.0F).a(SoundEffectType.a), BlockPropertyWood.e));
/* 260 */   public static final Block DARK_OAK_SIGN = a("dark_oak_sign", new BlockFloorSign(BlockBase.Info.a(Material.WOOD, DARK_OAK_LOG.s()).a().d(1.0F).a(SoundEffectType.a), BlockPropertyWood.f));
/* 261 */   public static final Block OAK_DOOR = a("oak_door", new BlockDoor(BlockBase.Info.a(Material.WOOD, OAK_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b()));
/* 262 */   public static final Block LADDER = a("ladder", new BlockLadder(BlockBase.Info.a(Material.ORIENTABLE).d(0.4F).a(SoundEffectType.k).b()));
/* 263 */   public static final Block RAIL = a("rail", new BlockMinecartTrack(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.7F).a(SoundEffectType.f)));
/* 264 */   public static final Block COBBLESTONE_STAIRS = a("cobblestone_stairs", new BlockStairs(COBBLESTONE.getBlockData(), BlockBase.Info.a(COBBLESTONE)));
/* 265 */   public static final Block OAK_WALL_SIGN = a("oak_wall_sign", new BlockWallSign(BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(OAK_SIGN), BlockPropertyWood.a));
/* 266 */   public static final Block SPRUCE_WALL_SIGN = a("spruce_wall_sign", new BlockWallSign(BlockBase.Info.a(Material.WOOD, SPRUCE_LOG.s()).a().d(1.0F).a(SoundEffectType.a).a(SPRUCE_SIGN), BlockPropertyWood.b));
/* 267 */   public static final Block BIRCH_WALL_SIGN = a("birch_wall_sign", new BlockWallSign(BlockBase.Info.a(Material.WOOD, MaterialMapColor.d).a().d(1.0F).a(SoundEffectType.a).a(BIRCH_SIGN), BlockPropertyWood.c));
/* 268 */   public static final Block ACACIA_WALL_SIGN = a("acacia_wall_sign", new BlockWallSign(BlockBase.Info.a(Material.WOOD, MaterialMapColor.q).a().d(1.0F).a(SoundEffectType.a).a(ACACIA_SIGN), BlockPropertyWood.d));
/* 269 */   public static final Block JUNGLE_WALL_SIGN = a("jungle_wall_sign", new BlockWallSign(BlockBase.Info.a(Material.WOOD, JUNGLE_LOG.s()).a().d(1.0F).a(SoundEffectType.a).a(JUNGLE_SIGN), BlockPropertyWood.e));
/* 270 */   public static final Block DARK_OAK_WALL_SIGN = a("dark_oak_wall_sign", new BlockWallSign(BlockBase.Info.a(Material.WOOD, DARK_OAK_LOG.s()).a().d(1.0F).a(SoundEffectType.a).a(DARK_OAK_SIGN), BlockPropertyWood.f));
/* 271 */   public static final Block LEVER = a("lever", new BlockLever(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 272 */   public static final Block STONE_PRESSURE_PLATE = a("stone_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.MOBS, BlockBase.Info.a(Material.STONE).h().a().d(0.5F)));
/* 273 */   public static final Block IRON_DOOR = a("iron_door", new BlockDoor(BlockBase.Info.a(Material.ORE, MaterialMapColor.h).h().d(5.0F).a(SoundEffectType.f).b()));
/* 274 */   public static final Block OAK_PRESSURE_PLATE = a("oak_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.EVERYTHING, BlockBase.Info.a(Material.WOOD, OAK_PLANKS.s()).a().d(0.5F).a(SoundEffectType.a)));
/* 275 */   public static final Block SPRUCE_PRESSURE_PLATE = a("spruce_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.EVERYTHING, BlockBase.Info.a(Material.WOOD, SPRUCE_PLANKS.s()).a().d(0.5F).a(SoundEffectType.a)));
/* 276 */   public static final Block BIRCH_PRESSURE_PLATE = a("birch_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.EVERYTHING, BlockBase.Info.a(Material.WOOD, BIRCH_PLANKS.s()).a().d(0.5F).a(SoundEffectType.a)));
/* 277 */   public static final Block JUNGLE_PRESSURE_PLATE = a("jungle_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.EVERYTHING, BlockBase.Info.a(Material.WOOD, JUNGLE_PLANKS.s()).a().d(0.5F).a(SoundEffectType.a)));
/* 278 */   public static final Block ACACIA_PRESSURE_PLATE = a("acacia_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.EVERYTHING, BlockBase.Info.a(Material.WOOD, ACACIA_PLANKS.s()).a().d(0.5F).a(SoundEffectType.a)));
/* 279 */   public static final Block DARK_OAK_PRESSURE_PLATE = a("dark_oak_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.EVERYTHING, BlockBase.Info.a(Material.WOOD, DARK_OAK_PLANKS.s()).a().d(0.5F).a(SoundEffectType.a)));
/* 280 */   public static final Block REDSTONE_ORE = a("redstone_ore", new BlockRedstoneOre(BlockBase.Info.a(Material.STONE).h().d().a(a(9)).a(3.0F, 3.0F)));
/* 281 */   public static final Block REDSTONE_TORCH = a("redstone_torch", new BlockRedstoneTorch(BlockBase.Info.a(Material.ORIENTABLE).a().c().a(a(7)).a(SoundEffectType.a)));
/* 282 */   public static final Block REDSTONE_WALL_TORCH = a("redstone_wall_torch", new BlockRedstoneTorchWall(BlockBase.Info.a(Material.ORIENTABLE).a().c().a(a(7)).a(SoundEffectType.a).a(REDSTONE_TORCH)));
/* 283 */   public static final Block STONE_BUTTON = a("stone_button", new BlockStoneButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F)));
/* 284 */   public static final Block SNOW = a("snow", new BlockSnow(BlockBase.Info.a(Material.PACKED_ICE).d().d(0.1F).h().a(SoundEffectType.j))); public static final Block ICE; static {
/* 285 */     ICE = a("ice", new BlockIce(BlockBase.Info.a(Material.ICE).a(0.98F).d().d(0.5F).a(SoundEffectType.g).b().a((var0, var1, var2, var3) -> (var3 == EntityTypes.POLAR_BEAR))));
/* 286 */   } public static final Block SNOW_BLOCK = a("snow_block", new Block(BlockBase.Info.a(Material.SNOW_BLOCK).h().d(0.2F).a(SoundEffectType.j)));
/* 287 */   public static final Block CACTUS = a("cactus", new BlockCactus(BlockBase.Info.a(Material.CACTUS).d().d(0.4F).a(SoundEffectType.h)));
/* 288 */   public static final Block CLAY = a("clay", new Block(BlockBase.Info.a(Material.CLAY).d(0.6F).a(SoundEffectType.b)));
/* 289 */   public static final Block SUGAR_CANE = a("sugar_cane", new BlockReed(BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.c)));
/* 290 */   public static final Block JUKEBOX = a("jukebox", new BlockJukeBox(BlockBase.Info.a(Material.WOOD, MaterialMapColor.l).a(2.0F, 6.0F)));
/* 291 */   public static final Block OAK_FENCE = a("oak_fence", new BlockFence(BlockBase.Info.a(Material.WOOD, OAK_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 292 */   public static final Block PUMPKIN = a("pumpkin", new BlockPumpkin(BlockBase.Info.a(Material.PUMPKIN, MaterialMapColor.q).d(1.0F).a(SoundEffectType.a)));
/* 293 */   public static final Block NETHERRACK = a("netherrack", new BlockNetherrack(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().d(0.4F).a(SoundEffectType.K)));
/* 294 */   public static final Block SOUL_SAND = a("soul_sand", new BlockSlowSand(BlockBase.Info.a(Material.SAND, MaterialMapColor.B).d(0.5F).b(0.4F).a(SoundEffectType.G).a(Blocks::b).a(Blocks::a).c(Blocks::a).b(Blocks::a)));
/* 295 */   public static final Block SOUL_SOIL = a("soul_soil", new Block(BlockBase.Info.a(Material.EARTH, MaterialMapColor.B).d(0.5F).a(SoundEffectType.H)));
/* 296 */   public static final Block BASALT = a("basalt", new BlockRotatable(BlockBase.Info.a(Material.STONE, MaterialMapColor.E).h().a(1.25F, 4.2F).a(SoundEffectType.I)));
/* 297 */   public static final Block cP = a("polished_basalt", new BlockRotatable(BlockBase.Info.a(Material.STONE, MaterialMapColor.E).h().a(1.25F, 4.2F).a(SoundEffectType.I)));
/* 298 */   public static final Block SOUL_TORCH = a("soul_torch", new BlockTorch(BlockBase.Info.a(Material.ORIENTABLE).a().c().a(var0 -> 10).a(SoundEffectType.a), Particles.SOUL_FIRE_FLAME));
/* 299 */   public static final Block SOUL_WALL_TORCH = a("soul_wall_torch", new BlockTorchWall(BlockBase.Info.a(Material.ORIENTABLE).a().c().a(var0 -> 10).a(SoundEffectType.a).a(SOUL_TORCH), Particles.SOUL_FIRE_FLAME));
/* 300 */   public static final Block GLOWSTONE = a("glowstone", new Block(BlockBase.Info.a(Material.SHATTERABLE, MaterialMapColor.d).d(0.3F).a(SoundEffectType.g).a(var0 -> 15)));
/* 301 */   public static final Block NETHER_PORTAL = a("nether_portal", new BlockPortal(BlockBase.Info.a(Material.PORTAL).a().d().d(-1.0F).a(SoundEffectType.g).a(var0 -> 11)));
/* 302 */   public static final Block CARVED_PUMPKIN = a("carved_pumpkin", new BlockPumpkinCarved(BlockBase.Info.a(Material.PUMPKIN, MaterialMapColor.q).d(1.0F).a(SoundEffectType.a).a(Blocks::b)));
/* 303 */   public static final Block JACK_O_LANTERN = a("jack_o_lantern", new BlockPumpkinCarved(BlockBase.Info.a(Material.PUMPKIN, MaterialMapColor.q).d(1.0F).a(SoundEffectType.a).a(var0 -> 15).a(Blocks::b)));
/* 304 */   public static final Block CAKE = a("cake", new BlockCake(BlockBase.Info.a(Material.CAKE).d(0.5F).a(SoundEffectType.h)));
/* 305 */   public static final Block REPEATER = a("repeater", new BlockRepeater(BlockBase.Info.a(Material.ORIENTABLE).c().a(SoundEffectType.a)));
/* 306 */   public static final Block WHITE_STAINED_GLASS = a("white_stained_glass", b(EnumColor.WHITE));
/* 307 */   public static final Block ORANGE_STAINED_GLASS = a("orange_stained_glass", b(EnumColor.ORANGE));
/* 308 */   public static final Block MAGENTA_STAINED_GLASS = a("magenta_stained_glass", b(EnumColor.MAGENTA));
/* 309 */   public static final Block LIGHT_BLUE_STAINED_GLASS = a("light_blue_stained_glass", b(EnumColor.LIGHT_BLUE));
/* 310 */   public static final Block YELLOW_STAINED_GLASS = a("yellow_stained_glass", b(EnumColor.YELLOW));
/* 311 */   public static final Block LIME_STAINED_GLASS = a("lime_stained_glass", b(EnumColor.LIME));
/* 312 */   public static final Block PINK_STAINED_GLASS = a("pink_stained_glass", b(EnumColor.PINK));
/* 313 */   public static final Block GRAY_STAINED_GLASS = a("gray_stained_glass", b(EnumColor.GRAY));
/* 314 */   public static final Block LIGHT_GRAY_STAINED_GLASS = a("light_gray_stained_glass", b(EnumColor.LIGHT_GRAY));
/* 315 */   public static final Block CYAN_STAINED_GLASS = a("cyan_stained_glass", b(EnumColor.CYAN));
/* 316 */   public static final Block PURPLE_STAINED_GLASS = a("purple_stained_glass", b(EnumColor.PURPLE));
/* 317 */   public static final Block BLUE_STAINED_GLASS = a("blue_stained_glass", b(EnumColor.BLUE));
/* 318 */   public static final Block BROWN_STAINED_GLASS = a("brown_stained_glass", b(EnumColor.BROWN));
/* 319 */   public static final Block GREEN_STAINED_GLASS = a("green_stained_glass", b(EnumColor.GREEN));
/* 320 */   public static final Block RED_STAINED_GLASS = a("red_stained_glass", b(EnumColor.RED));
/* 321 */   public static final Block BLACK_STAINED_GLASS = a("black_stained_glass", b(EnumColor.BLACK));
/* 322 */   public static final Block OAK_TRAPDOOR = a("oak_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.WOOD, MaterialMapColor.o).d(3.0F).a(SoundEffectType.a).b().a(Blocks::a)));
/* 323 */   public static final Block SPRUCE_TRAPDOOR = a("spruce_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.WOOD, MaterialMapColor.J).d(3.0F).a(SoundEffectType.a).b().a(Blocks::a)));
/* 324 */   public static final Block BIRCH_TRAPDOOR = a("birch_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.WOOD, MaterialMapColor.d).d(3.0F).a(SoundEffectType.a).b().a(Blocks::a)));
/* 325 */   public static final Block JUNGLE_TRAPDOOR = a("jungle_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.WOOD, MaterialMapColor.l).d(3.0F).a(SoundEffectType.a).b().a(Blocks::a)));
/* 326 */   public static final Block ACACIA_TRAPDOOR = a("acacia_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.WOOD, MaterialMapColor.q).d(3.0F).a(SoundEffectType.a).b().a(Blocks::a)));
/* 327 */   public static final Block DARK_OAK_TRAPDOOR = a("dark_oak_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.WOOD, MaterialMapColor.B).d(3.0F).a(SoundEffectType.a).b().a(Blocks::a)));
/* 328 */   public static final Block STONE_BRICKS = a("stone_bricks", new Block(BlockBase.Info.a(Material.STONE).h().a(1.5F, 6.0F)));
/* 329 */   public static final Block MOSSY_STONE_BRICKS = a("mossy_stone_bricks", new Block(BlockBase.Info.a(Material.STONE).h().a(1.5F, 6.0F)));
/* 330 */   public static final Block CRACKED_STONE_BRICKS = a("cracked_stone_bricks", new Block(BlockBase.Info.a(Material.STONE).h().a(1.5F, 6.0F)));
/* 331 */   public static final Block CHISELED_STONE_BRICKS = a("chiseled_stone_bricks", new Block(BlockBase.Info.a(Material.STONE).h().a(1.5F, 6.0F)));
/* 332 */   public static final Block INFESTED_STONE = a("infested_stone", new BlockMonsterEggs(STONE, BlockBase.Info.a(Material.CLAY).a(0.0F, 0.75F)));
/* 333 */   public static final Block INFESTED_COBBLESTONE = a("infested_cobblestone", new BlockMonsterEggs(COBBLESTONE, BlockBase.Info.a(Material.CLAY).a(0.0F, 0.75F)));
/* 334 */   public static final Block INFESTED_STONE_BRICKS = a("infested_stone_bricks", new BlockMonsterEggs(STONE_BRICKS, BlockBase.Info.a(Material.CLAY).a(0.0F, 0.75F)));
/* 335 */   public static final Block INFESTED_MOSSY_STONE_BRICKS = a("infested_mossy_stone_bricks", new BlockMonsterEggs(MOSSY_STONE_BRICKS, BlockBase.Info.a(Material.CLAY).a(0.0F, 0.75F)));
/* 336 */   public static final Block INFESTED_CRACKED_STONE_BRICKS = a("infested_cracked_stone_bricks", new BlockMonsterEggs(CRACKED_STONE_BRICKS, BlockBase.Info.a(Material.CLAY).a(0.0F, 0.75F)));
/* 337 */   public static final Block INFESTED_CHISELED_STONE_BRICKS = a("infested_chiseled_stone_bricks", new BlockMonsterEggs(CHISELED_STONE_BRICKS, BlockBase.Info.a(Material.CLAY).a(0.0F, 0.75F)));
/* 338 */   public static final Block BROWN_MUSHROOM_BLOCK = a("brown_mushroom_block", new BlockHugeMushroom(BlockBase.Info.a(Material.WOOD, MaterialMapColor.l).d(0.2F).a(SoundEffectType.a)));
/* 339 */   public static final Block RED_MUSHROOM_BLOCK = a("red_mushroom_block", new BlockHugeMushroom(BlockBase.Info.a(Material.WOOD, MaterialMapColor.D).d(0.2F).a(SoundEffectType.a)));
/* 340 */   public static final Block MUSHROOM_STEM = a("mushroom_stem", new BlockHugeMushroom(BlockBase.Info.a(Material.WOOD, MaterialMapColor.e).d(0.2F).a(SoundEffectType.a)));
/* 341 */   public static final Block IRON_BARS = a("iron_bars", new BlockIronBars(BlockBase.Info.a(Material.ORE, MaterialMapColor.b).h().a(5.0F, 6.0F).a(SoundEffectType.f).b()));
/* 342 */   public static final Block CHAIN = a("chain", new BlockChain(BlockBase.Info.a(Material.ORE, MaterialMapColor.b).h().a(5.0F, 6.0F).a(SoundEffectType.S).b()));
/* 343 */   public static final Block GLASS_PANE = a("glass_pane", new BlockIronBars(BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 344 */   public static final Block MELON = a("melon", new BlockMelon(BlockBase.Info.a(Material.PUMPKIN, MaterialMapColor.u).d(1.0F).a(SoundEffectType.a)));
/* 345 */   public static final Block ATTACHED_PUMPKIN_STEM = a("attached_pumpkin_stem", new BlockStemAttached((BlockStemmed)PUMPKIN, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.a)));
/* 346 */   public static final Block ATTACHED_MELON_STEM = a("attached_melon_stem", new BlockStemAttached((BlockStemmed)MELON, BlockBase.Info.a(Material.PLANT).a().c().a(SoundEffectType.a)));
/* 347 */   public static final Block PUMPKIN_STEM = a("pumpkin_stem", new BlockStem((BlockStemmed)PUMPKIN, BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.v)));
/* 348 */   public static final Block MELON_STEM = a("melon_stem", new BlockStem((BlockStemmed)MELON, BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.v)));
/* 349 */   public static final Block VINE = a("vine", new BlockVine(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().d().d(0.2F).a(SoundEffectType.w)));
/* 350 */   public static final Block OAK_FENCE_GATE = a("oak_fence_gate", new BlockFenceGate(BlockBase.Info.a(Material.WOOD, OAK_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 351 */   public static final Block BRICK_STAIRS = a("brick_stairs", new BlockStairs(BRICKS.getBlockData(), BlockBase.Info.a(BRICKS)));
/* 352 */   public static final Block STONE_BRICK_STAIRS = a("stone_brick_stairs", new BlockStairs(STONE_BRICKS.getBlockData(), BlockBase.Info.a(STONE_BRICKS)));
/* 353 */   public static final Block MYCELIUM = a("mycelium", new BlockMycel(BlockBase.Info.a(Material.GRASS, MaterialMapColor.z).d().d(0.6F).a(SoundEffectType.c)));
/* 354 */   public static final Block LILY_PAD = a("lily_pad", new BlockWaterLily(BlockBase.Info.a(Material.PLANT).c().a(SoundEffectType.d).b()));
/* 355 */   public static final Block NETHER_BRICKS = a("nether_bricks", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(2.0F, 6.0F).a(SoundEffectType.L)));
/* 356 */   public static final Block NETHER_BRICK_FENCE = a("nether_brick_fence", new BlockFence(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(2.0F, 6.0F).a(SoundEffectType.L)));
/* 357 */   public static final Block NETHER_BRICK_STAIRS = a("nether_brick_stairs", new BlockStairs(NETHER_BRICKS.getBlockData(), BlockBase.Info.a(NETHER_BRICKS)));
/* 358 */   public static final Block NETHER_WART = a("nether_wart", new BlockNetherWart(BlockBase.Info.a(Material.PLANT, MaterialMapColor.D).a().d().a(SoundEffectType.x)));
/* 359 */   public static final Block ENCHANTING_TABLE = a("enchanting_table", new BlockEnchantmentTable(BlockBase.Info.a(Material.STONE, MaterialMapColor.D).h().a(5.0F, 1200.0F)));
/* 360 */   public static final Block BREWING_STAND = a("brewing_stand", new BlockBrewingStand(BlockBase.Info.a(Material.ORE).h().d(0.5F).a(var0 -> 1).b()));
/* 361 */   public static final Block CAULDRON = a("cauldron", new BlockCauldron(BlockBase.Info.a(Material.ORE, MaterialMapColor.m).h().d(2.0F).b()));
/* 362 */   public static final Block END_PORTAL = a("end_portal", new BlockEnderPortal(BlockBase.Info.a(Material.PORTAL, MaterialMapColor.E).a().a(var0 -> 15).a(-1.0F, 3600000.0F).f()));
/* 363 */   public static final Block END_PORTAL_FRAME = a("end_portal_frame", new BlockEnderPortalFrame(BlockBase.Info.a(Material.STONE, MaterialMapColor.C).a(SoundEffectType.g).a(var0 -> 1).a(-1.0F, 3600000.0F).f()));
/* 364 */   public static final Block END_STONE = a("end_stone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().a(3.0F, 9.0F)));
/* 365 */   public static final Block DRAGON_EGG = a("dragon_egg", new BlockDragonEgg(BlockBase.Info.a(Material.DRAGON_EGG, MaterialMapColor.E).a(3.0F, 9.0F).a(var0 -> 1).b()));
/* 366 */   public static final Block REDSTONE_LAMP = a("redstone_lamp", new BlockRedstoneLamp(BlockBase.Info.a(Material.BUILDABLE_GLASS).a(a(15)).d(0.3F).a(SoundEffectType.g).a(Blocks::b)));
/* 367 */   public static final Block COCOA = a("cocoa", new BlockCocoa(BlockBase.Info.a(Material.PLANT).d().a(0.2F, 3.0F).a(SoundEffectType.a).b()));
/* 368 */   public static final Block SANDSTONE_STAIRS = a("sandstone_stairs", new BlockStairs(SANDSTONE.getBlockData(), BlockBase.Info.a(SANDSTONE)));
/* 369 */   public static final Block EMERALD_ORE = a("emerald_ore", new BlockOre(BlockBase.Info.a(Material.STONE).h().a(3.0F, 3.0F)));
/* 370 */   public static final Block ENDER_CHEST = a("ender_chest", new BlockEnderChest(BlockBase.Info.a(Material.STONE).h().a(22.5F, 600.0F).a(var0 -> 7)));
/* 371 */   public static final Block TRIPWIRE_HOOK = a("tripwire_hook", new BlockTripwireHook(BlockBase.Info.a(Material.ORIENTABLE).a()));
/* 372 */   public static final Block TRIPWIRE = a("tripwire", new BlockTripwire((BlockTripwireHook)TRIPWIRE_HOOK, BlockBase.Info.a(Material.ORIENTABLE).a()));
/* 373 */   public static final Block EMERALD_BLOCK = a("emerald_block", new Block(BlockBase.Info.a(Material.ORE, MaterialMapColor.I).h().a(5.0F, 6.0F).a(SoundEffectType.f)));
/* 374 */   public static final Block SPRUCE_STAIRS = a("spruce_stairs", new BlockStairs(SPRUCE_PLANKS.getBlockData(), BlockBase.Info.a(SPRUCE_PLANKS)));
/* 375 */   public static final Block BIRCH_STAIRS = a("birch_stairs", new BlockStairs(BIRCH_PLANKS.getBlockData(), BlockBase.Info.a(BIRCH_PLANKS)));
/* 376 */   public static final Block JUNGLE_STAIRS = a("jungle_stairs", new BlockStairs(JUNGLE_PLANKS.getBlockData(), BlockBase.Info.a(JUNGLE_PLANKS)));
/* 377 */   public static final Block COMMAND_BLOCK = a("command_block", new BlockCommand(BlockBase.Info.a(Material.ORE, MaterialMapColor.B).h().a(-1.0F, 3600000.0F).f()));
/* 378 */   public static final Block BEACON = a("beacon", new BlockBeacon(BlockBase.Info.a(Material.SHATTERABLE, MaterialMapColor.G).d(3.0F).a(var0 -> 15).b().a(Blocks::b)));
/* 379 */   public static final Block COBBLESTONE_WALL = a("cobblestone_wall", new BlockCobbleWall(BlockBase.Info.a(COBBLESTONE)));
/* 380 */   public static final Block MOSSY_COBBLESTONE_WALL = a("mossy_cobblestone_wall", new BlockCobbleWall(BlockBase.Info.a(COBBLESTONE)));
/* 381 */   public static final Block FLOWER_POT = a("flower_pot", new BlockFlowerPot(AIR, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 382 */   public static final Block POTTED_OAK_SAPLING = a("potted_oak_sapling", new BlockFlowerPot(OAK_SAPLING, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 383 */   public static final Block POTTED_SPRUCE_SAPLING = a("potted_spruce_sapling", new BlockFlowerPot(SPRUCE_SAPLING, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 384 */   public static final Block POTTED_BIRCH_SAPLING = a("potted_birch_sapling", new BlockFlowerPot(BIRCH_SAPLING, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 385 */   public static final Block POTTED_JUNGLE_SAPLING = a("potted_jungle_sapling", new BlockFlowerPot(JUNGLE_SAPLING, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 386 */   public static final Block POTTED_ACACIA_SAPLING = a("potted_acacia_sapling", new BlockFlowerPot(ACACIA_SAPLING, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 387 */   public static final Block POTTED_DARK_OAK_SAPLING = a("potted_dark_oak_sapling", new BlockFlowerPot(DARK_OAK_SAPLING, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 388 */   public static final Block POTTED_FERN = a("potted_fern", new BlockFlowerPot(FERN, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 389 */   public static final Block POTTED_DANDELION = a("potted_dandelion", new BlockFlowerPot(DANDELION, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 390 */   public static final Block POTTED_POPPY = a("potted_poppy", new BlockFlowerPot(POPPY, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 391 */   public static final Block POTTED_BLUE_ORCHID = a("potted_blue_orchid", new BlockFlowerPot(BLUE_ORCHID, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 392 */   public static final Block POTTED_ALLIUM = a("potted_allium", new BlockFlowerPot(ALLIUM, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 393 */   public static final Block POTTED_AZURE_BLUET = a("potted_azure_bluet", new BlockFlowerPot(AZURE_BLUET, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 394 */   public static final Block POTTED_RED_TULIP = a("potted_red_tulip", new BlockFlowerPot(RED_TULIP, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 395 */   public static final Block POTTED_ORANGE_TULIP = a("potted_orange_tulip", new BlockFlowerPot(ORANGE_TULIP, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 396 */   public static final Block POTTED_WHITE_TULIP = a("potted_white_tulip", new BlockFlowerPot(WHITE_TULIP, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 397 */   public static final Block POTTED_PINK_TULIP = a("potted_pink_tulip", new BlockFlowerPot(PINK_TULIP, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 398 */   public static final Block POTTED_OXEYE_DAISY = a("potted_oxeye_daisy", new BlockFlowerPot(OXEYE_DAISY, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 399 */   public static final Block POTTED_CORNFLOWER = a("potted_cornflower", new BlockFlowerPot(CORNFLOWER, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 400 */   public static final Block POTTED_LILY_OF_THE_VALLEY = a("potted_lily_of_the_valley", new BlockFlowerPot(LILY_OF_THE_VALLEY, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 401 */   public static final Block POTTED_WITHER_ROSE = a("potted_wither_rose", new BlockFlowerPot(WITHER_ROSE, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 402 */   public static final Block POTTED_RED_MUSHROOM = a("potted_red_mushroom", new BlockFlowerPot(RED_MUSHROOM, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 403 */   public static final Block POTTED_BROWN_MUSHROOM = a("potted_brown_mushroom", new BlockFlowerPot(BROWN_MUSHROOM, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 404 */   public static final Block POTTED_DEAD_BUSH = a("potted_dead_bush", new BlockFlowerPot(DEAD_BUSH, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 405 */   public static final Block POTTED_CACTUS = a("potted_cactus", new BlockFlowerPot(CACTUS, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 406 */   public static final Block CARROTS = a("carrots", new BlockCarrots(BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.u)));
/* 407 */   public static final Block POTATOES = a("potatoes", new BlockPotatoes(BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.u)));
/* 408 */   public static final Block OAK_BUTTON = a("oak_button", new BlockWoodButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 409 */   public static final Block SPRUCE_BUTTON = a("spruce_button", new BlockWoodButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 410 */   public static final Block BIRCH_BUTTON = a("birch_button", new BlockWoodButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 411 */   public static final Block JUNGLE_BUTTON = a("jungle_button", new BlockWoodButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 412 */   public static final Block ACACIA_BUTTON = a("acacia_button", new BlockWoodButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 413 */   public static final Block DARK_OAK_BUTTON = a("dark_oak_button", new BlockWoodButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 414 */   public static final Block SKELETON_SKULL = a("skeleton_skull", new BlockSkull(BlockSkull.Type.SKELETON, BlockBase.Info.a(Material.ORIENTABLE).d(1.0F)));
/* 415 */   public static final Block SKELETON_WALL_SKULL = a("skeleton_wall_skull", new BlockSkullWall(BlockSkull.Type.SKELETON, BlockBase.Info.a(Material.ORIENTABLE).d(1.0F).a(SKELETON_SKULL)));
/* 416 */   public static final Block WITHER_SKELETON_SKULL = a("wither_skeleton_skull", new BlockWitherSkull(BlockBase.Info.a(Material.ORIENTABLE).d(1.0F)));
/* 417 */   public static final Block WITHER_SKELETON_WALL_SKULL = a("wither_skeleton_wall_skull", new BlockWitherSkullWall(BlockBase.Info.a(Material.ORIENTABLE).d(1.0F).a(WITHER_SKELETON_SKULL)));
/* 418 */   public static final Block ZOMBIE_HEAD = a("zombie_head", new BlockSkull(BlockSkull.Type.ZOMBIE, BlockBase.Info.a(Material.ORIENTABLE).d(1.0F)));
/* 419 */   public static final Block ZOMBIE_WALL_HEAD = a("zombie_wall_head", new BlockSkullWall(BlockSkull.Type.ZOMBIE, BlockBase.Info.a(Material.ORIENTABLE).d(1.0F).a(ZOMBIE_HEAD)));
/* 420 */   public static final Block PLAYER_HEAD = a("player_head", new BlockSkullPlayer(BlockBase.Info.a(Material.ORIENTABLE).d(1.0F)));
/* 421 */   public static final Block PLAYER_WALL_HEAD = a("player_wall_head", new BlockSkullPlayerWall(BlockBase.Info.a(Material.ORIENTABLE).d(1.0F).a(PLAYER_HEAD)));
/* 422 */   public static final Block CREEPER_HEAD = a("creeper_head", new BlockSkull(BlockSkull.Type.CREEPER, BlockBase.Info.a(Material.ORIENTABLE).d(1.0F)));
/* 423 */   public static final Block CREEPER_WALL_HEAD = a("creeper_wall_head", new BlockSkullWall(BlockSkull.Type.CREEPER, BlockBase.Info.a(Material.ORIENTABLE).d(1.0F).a(CREEPER_HEAD)));
/* 424 */   public static final Block DRAGON_HEAD = a("dragon_head", new BlockSkull(BlockSkull.Type.DRAGON, BlockBase.Info.a(Material.ORIENTABLE).d(1.0F)));
/* 425 */   public static final Block DRAGON_WALL_HEAD = a("dragon_wall_head", new BlockSkullWall(BlockSkull.Type.DRAGON, BlockBase.Info.a(Material.ORIENTABLE).d(1.0F).a(DRAGON_HEAD)));
/* 426 */   public static final Block ANVIL = a("anvil", new BlockAnvil(BlockBase.Info.a(Material.HEAVY, MaterialMapColor.h).h().a(5.0F, 1200.0F).a(SoundEffectType.l)));
/* 427 */   public static final Block CHIPPED_ANVIL = a("chipped_anvil", new BlockAnvil(BlockBase.Info.a(Material.HEAVY, MaterialMapColor.h).h().a(5.0F, 1200.0F).a(SoundEffectType.l)));
/* 428 */   public static final Block DAMAGED_ANVIL = a("damaged_anvil", new BlockAnvil(BlockBase.Info.a(Material.HEAVY, MaterialMapColor.h).h().a(5.0F, 1200.0F).a(SoundEffectType.l)));
/* 429 */   public static final Block TRAPPED_CHEST = a("trapped_chest", new BlockChestTrapped(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a)));
/* 430 */   public static final Block LIGHT_WEIGHTED_PRESSURE_PLATE = a("light_weighted_pressure_plate", new BlockPressurePlateWeighted(15, BlockBase.Info.a(Material.ORE, MaterialMapColor.F).h().a().d(0.5F).a(SoundEffectType.a)));
/* 431 */   public static final Block HEAVY_WEIGHTED_PRESSURE_PLATE = a("heavy_weighted_pressure_plate", new BlockPressurePlateWeighted(150, BlockBase.Info.a(Material.ORE).h().a().d(0.5F).a(SoundEffectType.a)));
/* 432 */   public static final Block COMPARATOR = a("comparator", new BlockRedstoneComparator(BlockBase.Info.a(Material.ORIENTABLE).c().a(SoundEffectType.a)));
/* 433 */   public static final Block DAYLIGHT_DETECTOR = a("daylight_detector", new BlockDaylightDetector(BlockBase.Info.a(Material.WOOD).d(0.2F).a(SoundEffectType.a)));
/* 434 */   public static final Block REDSTONE_BLOCK = a("redstone_block", new BlockPowered(BlockBase.Info.a(Material.ORE, MaterialMapColor.f).h().a(5.0F, 6.0F).a(SoundEffectType.f).a(Blocks::b)));
/* 435 */   public static final Block NETHER_QUARTZ_ORE = a("nether_quartz_ore", new BlockOre(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(3.0F, 3.0F).a(SoundEffectType.N)));
/* 436 */   public static final Block HOPPER = a("hopper", new BlockHopper(BlockBase.Info.a(Material.ORE, MaterialMapColor.m).h().a(3.0F, 4.8F).a(SoundEffectType.f).b()));
/* 437 */   public static final Block QUARTZ_BLOCK = a("quartz_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.p).h().d(0.8F)));
/* 438 */   public static final Block CHISELED_QUARTZ_BLOCK = a("chiseled_quartz_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.p).h().d(0.8F)));
/* 439 */   public static final Block QUARTZ_PILLAR = a("quartz_pillar", new BlockRotatable(BlockBase.Info.a(Material.STONE, MaterialMapColor.p).h().d(0.8F)));
/* 440 */   public static final Block QUARTZ_STAIRS = a("quartz_stairs", new BlockStairs(QUARTZ_BLOCK.getBlockData(), BlockBase.Info.a(QUARTZ_BLOCK)));
/* 441 */   public static final Block ACTIVATOR_RAIL = a("activator_rail", new BlockPoweredRail(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.7F).a(SoundEffectType.f)));
/* 442 */   public static final Block DROPPER = a("dropper", new BlockDropper(BlockBase.Info.a(Material.STONE).h().d(3.5F)));
/* 443 */   public static final Block WHITE_TERRACOTTA = a("white_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.L).h().a(1.25F, 4.2F)));
/* 444 */   public static final Block ORANGE_TERRACOTTA = a("orange_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.M).h().a(1.25F, 4.2F)));
/* 445 */   public static final Block MAGENTA_TERRACOTTA = a("magenta_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.N).h().a(1.25F, 4.2F)));
/* 446 */   public static final Block LIGHT_BLUE_TERRACOTTA = a("light_blue_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.O).h().a(1.25F, 4.2F)));
/* 447 */   public static final Block YELLOW_TERRACOTTA = a("yellow_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.P).h().a(1.25F, 4.2F)));
/* 448 */   public static final Block LIME_TERRACOTTA = a("lime_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.Q).h().a(1.25F, 4.2F)));
/* 449 */   public static final Block PINK_TERRACOTTA = a("pink_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.R).h().a(1.25F, 4.2F)));
/* 450 */   public static final Block GRAY_TERRACOTTA = a("gray_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.S).h().a(1.25F, 4.2F)));
/* 451 */   public static final Block LIGHT_GRAY_TERRACOTTA = a("light_gray_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.T).h().a(1.25F, 4.2F)));
/* 452 */   public static final Block CYAN_TERRACOTTA = a("cyan_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.U).h().a(1.25F, 4.2F)));
/* 453 */   public static final Block PURPLE_TERRACOTTA = a("purple_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.V).h().a(1.25F, 4.2F)));
/* 454 */   public static final Block BLUE_TERRACOTTA = a("blue_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.W).h().a(1.25F, 4.2F)));
/* 455 */   public static final Block BROWN_TERRACOTTA = a("brown_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.X).h().a(1.25F, 4.2F)));
/* 456 */   public static final Block GREEN_TERRACOTTA = a("green_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.Y).h().a(1.25F, 4.2F)));
/* 457 */   public static final Block RED_TERRACOTTA = a("red_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.Z).h().a(1.25F, 4.2F)));
/* 458 */   public static final Block BLACK_TERRACOTTA = a("black_terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.aa).h().a(1.25F, 4.2F)));
/* 459 */   public static final Block WHITE_STAINED_GLASS_PANE = a("white_stained_glass_pane", new BlockStainedGlassPane(EnumColor.WHITE, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 460 */   public static final Block ORANGE_STAINED_GLASS_PANE = a("orange_stained_glass_pane", new BlockStainedGlassPane(EnumColor.ORANGE, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 461 */   public static final Block MAGENTA_STAINED_GLASS_PANE = a("magenta_stained_glass_pane", new BlockStainedGlassPane(EnumColor.MAGENTA, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 462 */   public static final Block LIGHT_BLUE_STAINED_GLASS_PANE = a("light_blue_stained_glass_pane", new BlockStainedGlassPane(EnumColor.LIGHT_BLUE, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 463 */   public static final Block YELLOW_STAINED_GLASS_PANE = a("yellow_stained_glass_pane", new BlockStainedGlassPane(EnumColor.YELLOW, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 464 */   public static final Block LIME_STAINED_GLASS_PANE = a("lime_stained_glass_pane", new BlockStainedGlassPane(EnumColor.LIME, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 465 */   public static final Block PINK_STAINED_GLASS_PANE = a("pink_stained_glass_pane", new BlockStainedGlassPane(EnumColor.PINK, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 466 */   public static final Block GRAY_STAINED_GLASS_PANE = a("gray_stained_glass_pane", new BlockStainedGlassPane(EnumColor.GRAY, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 467 */   public static final Block LIGHT_GRAY_STAINED_GLASS_PANE = a("light_gray_stained_glass_pane", new BlockStainedGlassPane(EnumColor.LIGHT_GRAY, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 468 */   public static final Block CYAN_STAINED_GLASS_PANE = a("cyan_stained_glass_pane", new BlockStainedGlassPane(EnumColor.CYAN, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 469 */   public static final Block PURPLE_STAINED_GLASS_PANE = a("purple_stained_glass_pane", new BlockStainedGlassPane(EnumColor.PURPLE, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 470 */   public static final Block BLUE_STAINED_GLASS_PANE = a("blue_stained_glass_pane", new BlockStainedGlassPane(EnumColor.BLUE, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 471 */   public static final Block BROWN_STAINED_GLASS_PANE = a("brown_stained_glass_pane", new BlockStainedGlassPane(EnumColor.BROWN, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 472 */   public static final Block GREEN_STAINED_GLASS_PANE = a("green_stained_glass_pane", new BlockStainedGlassPane(EnumColor.GREEN, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 473 */   public static final Block RED_STAINED_GLASS_PANE = a("red_stained_glass_pane", new BlockStainedGlassPane(EnumColor.RED, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 474 */   public static final Block BLACK_STAINED_GLASS_PANE = a("black_stained_glass_pane", new BlockStainedGlassPane(EnumColor.BLACK, BlockBase.Info.a(Material.SHATTERABLE).d(0.3F).a(SoundEffectType.g).b()));
/* 475 */   public static final Block ACACIA_STAIRS = a("acacia_stairs", new BlockStairs(ACACIA_PLANKS.getBlockData(), BlockBase.Info.a(ACACIA_PLANKS)));
/* 476 */   public static final Block DARK_OAK_STAIRS = a("dark_oak_stairs", new BlockStairs(DARK_OAK_PLANKS.getBlockData(), BlockBase.Info.a(DARK_OAK_PLANKS)));
/* 477 */   public static final Block SLIME_BLOCK = a("slime_block", new BlockSlime(BlockBase.Info.a(Material.CLAY, MaterialMapColor.c).a(0.8F).a(SoundEffectType.m).b()));
/* 478 */   public static final Block BARRIER = a("barrier", new BlockBarrier(BlockBase.Info.a(Material.BANNER).a(-1.0F, 3600000.8F).f().b().a(Blocks::a)));
/* 479 */   public static final Block IRON_TRAPDOOR = a("iron_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.ORE).h().d(5.0F).a(SoundEffectType.f).b().a(Blocks::a)));
/* 480 */   public static final Block PRISMARINE = a("prismarine", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.y).h().a(1.5F, 6.0F)));
/* 481 */   public static final Block PRISMARINE_BRICKS = a("prismarine_bricks", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.G).h().a(1.5F, 6.0F)));
/* 482 */   public static final Block DARK_PRISMARINE = a("dark_prismarine", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.G).h().a(1.5F, 6.0F)));
/* 483 */   public static final Block PRISMARINE_STAIRS = a("prismarine_stairs", new BlockStairs(PRISMARINE.getBlockData(), BlockBase.Info.a(PRISMARINE)));
/* 484 */   public static final Block PRISMARINE_BRICK_STAIRS = a("prismarine_brick_stairs", new BlockStairs(PRISMARINE_BRICKS.getBlockData(), BlockBase.Info.a(PRISMARINE_BRICKS)));
/* 485 */   public static final Block DARK_PRISMARINE_STAIRS = a("dark_prismarine_stairs", new BlockStairs(DARK_PRISMARINE.getBlockData(), BlockBase.Info.a(DARK_PRISMARINE)));
/* 486 */   public static final Block PRISMARINE_SLAB = a("prismarine_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.y).h().a(1.5F, 6.0F)));
/* 487 */   public static final Block PRISMARINE_BRICK_SLAB = a("prismarine_brick_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.G).h().a(1.5F, 6.0F)));
/* 488 */   public static final Block DARK_PRISMARINE_SLAB = a("dark_prismarine_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.G).h().a(1.5F, 6.0F)));
/* 489 */   public static final Block SEA_LANTERN = a("sea_lantern", new Block(BlockBase.Info.a(Material.SHATTERABLE, MaterialMapColor.p).d(0.3F).a(SoundEffectType.g).a(var0 -> 15)));
/* 490 */   public static final Block HAY_BLOCK = a("hay_block", new BlockHay(BlockBase.Info.a(Material.GRASS, MaterialMapColor.t).d(0.5F).a(SoundEffectType.c)));
/* 491 */   public static final Block WHITE_CARPET = a("white_carpet", new BlockCarpet(EnumColor.WHITE, BlockBase.Info.a(Material.WOOL, MaterialMapColor.j).d(0.1F).a(SoundEffectType.h)));
/* 492 */   public static final Block ORANGE_CARPET = a("orange_carpet", new BlockCarpet(EnumColor.ORANGE, BlockBase.Info.a(Material.WOOL, MaterialMapColor.q).d(0.1F).a(SoundEffectType.h)));
/* 493 */   public static final Block MAGENTA_CARPET = a("magenta_carpet", new BlockCarpet(EnumColor.MAGENTA, BlockBase.Info.a(Material.WOOL, MaterialMapColor.r).d(0.1F).a(SoundEffectType.h)));
/* 494 */   public static final Block LIGHT_BLUE_CARPET = a("light_blue_carpet", new BlockCarpet(EnumColor.LIGHT_BLUE, BlockBase.Info.a(Material.WOOL, MaterialMapColor.s).d(0.1F).a(SoundEffectType.h)));
/* 495 */   public static final Block YELLOW_CARPET = a("yellow_carpet", new BlockCarpet(EnumColor.YELLOW, BlockBase.Info.a(Material.WOOL, MaterialMapColor.t).d(0.1F).a(SoundEffectType.h)));
/* 496 */   public static final Block LIME_CARPET = a("lime_carpet", new BlockCarpet(EnumColor.LIME, BlockBase.Info.a(Material.WOOL, MaterialMapColor.u).d(0.1F).a(SoundEffectType.h)));
/* 497 */   public static final Block PINK_CARPET = a("pink_carpet", new BlockCarpet(EnumColor.PINK, BlockBase.Info.a(Material.WOOL, MaterialMapColor.v).d(0.1F).a(SoundEffectType.h)));
/* 498 */   public static final Block GRAY_CARPET = a("gray_carpet", new BlockCarpet(EnumColor.GRAY, BlockBase.Info.a(Material.WOOL, MaterialMapColor.w).d(0.1F).a(SoundEffectType.h)));
/* 499 */   public static final Block LIGHT_GRAY_CARPET = a("light_gray_carpet", new BlockCarpet(EnumColor.LIGHT_GRAY, BlockBase.Info.a(Material.WOOL, MaterialMapColor.x).d(0.1F).a(SoundEffectType.h)));
/* 500 */   public static final Block CYAN_CARPET = a("cyan_carpet", new BlockCarpet(EnumColor.CYAN, BlockBase.Info.a(Material.WOOL, MaterialMapColor.y).d(0.1F).a(SoundEffectType.h)));
/* 501 */   public static final Block PURPLE_CARPET = a("purple_carpet", new BlockCarpet(EnumColor.PURPLE, BlockBase.Info.a(Material.WOOL, MaterialMapColor.z).d(0.1F).a(SoundEffectType.h)));
/* 502 */   public static final Block BLUE_CARPET = a("blue_carpet", new BlockCarpet(EnumColor.BLUE, BlockBase.Info.a(Material.WOOL, MaterialMapColor.A).d(0.1F).a(SoundEffectType.h)));
/* 503 */   public static final Block BROWN_CARPET = a("brown_carpet", new BlockCarpet(EnumColor.BROWN, BlockBase.Info.a(Material.WOOL, MaterialMapColor.B).d(0.1F).a(SoundEffectType.h)));
/* 504 */   public static final Block GREEN_CARPET = a("green_carpet", new BlockCarpet(EnumColor.GREEN, BlockBase.Info.a(Material.WOOL, MaterialMapColor.C).d(0.1F).a(SoundEffectType.h)));
/* 505 */   public static final Block RED_CARPET = a("red_carpet", new BlockCarpet(EnumColor.RED, BlockBase.Info.a(Material.WOOL, MaterialMapColor.D).d(0.1F).a(SoundEffectType.h)));
/* 506 */   public static final Block BLACK_CARPET = a("black_carpet", new BlockCarpet(EnumColor.BLACK, BlockBase.Info.a(Material.WOOL, MaterialMapColor.E).d(0.1F).a(SoundEffectType.h)));
/* 507 */   public static final Block TERRACOTTA = a("terracotta", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.q).h().a(1.25F, 4.2F)));
/* 508 */   public static final Block COAL_BLOCK = a("coal_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.E).h().a(5.0F, 6.0F)));
/* 509 */   public static final Block PACKED_ICE = a("packed_ice", new Block(BlockBase.Info.a(Material.SNOW_LAYER).a(0.98F).d(0.5F).a(SoundEffectType.g)));
/* 510 */   public static final Block SUNFLOWER = a("sunflower", new BlockTallPlantFlower(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().c().a(SoundEffectType.c)));
/* 511 */   public static final Block LILAC = a("lilac", new BlockTallPlantFlower(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().c().a(SoundEffectType.c)));
/* 512 */   public static final Block ROSE_BUSH = a("rose_bush", new BlockTallPlantFlower(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().c().a(SoundEffectType.c)));
/* 513 */   public static final Block PEONY = a("peony", new BlockTallPlantFlower(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().c().a(SoundEffectType.c)));
/* 514 */   public static final Block TALL_GRASS = a("tall_grass", new BlockTallPlant(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().c().a(SoundEffectType.c)));
/* 515 */   public static final Block LARGE_FERN = a("large_fern", new BlockTallPlant(BlockBase.Info.a(Material.REPLACEABLE_PLANT).a().c().a(SoundEffectType.c)));
/* 516 */   public static final Block WHITE_BANNER = a("white_banner", new BlockBanner(EnumColor.WHITE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 517 */   public static final Block ORANGE_BANNER = a("orange_banner", new BlockBanner(EnumColor.ORANGE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 518 */   public static final Block MAGENTA_BANNER = a("magenta_banner", new BlockBanner(EnumColor.MAGENTA, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 519 */   public static final Block LIGHT_BLUE_BANNER = a("light_blue_banner", new BlockBanner(EnumColor.LIGHT_BLUE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 520 */   public static final Block YELLOW_BANNER = a("yellow_banner", new BlockBanner(EnumColor.YELLOW, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 521 */   public static final Block LIME_BANNER = a("lime_banner", new BlockBanner(EnumColor.LIME, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 522 */   public static final Block PINK_BANNER = a("pink_banner", new BlockBanner(EnumColor.PINK, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 523 */   public static final Block GRAY_BANNER = a("gray_banner", new BlockBanner(EnumColor.GRAY, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 524 */   public static final Block LIGHT_GRAY_BANNER = a("light_gray_banner", new BlockBanner(EnumColor.LIGHT_GRAY, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 525 */   public static final Block CYAN_BANNER = a("cyan_banner", new BlockBanner(EnumColor.CYAN, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 526 */   public static final Block PURPLE_BANNER = a("purple_banner", new BlockBanner(EnumColor.PURPLE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 527 */   public static final Block BLUE_BANNER = a("blue_banner", new BlockBanner(EnumColor.BLUE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 528 */   public static final Block BROWN_BANNER = a("brown_banner", new BlockBanner(EnumColor.BROWN, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 529 */   public static final Block GREEN_BANNER = a("green_banner", new BlockBanner(EnumColor.GREEN, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 530 */   public static final Block RED_BANNER = a("red_banner", new BlockBanner(EnumColor.RED, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 531 */   public static final Block BLACK_BANNER = a("black_banner", new BlockBanner(EnumColor.BLACK, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a)));
/* 532 */   public static final Block WHITE_WALL_BANNER = a("white_wall_banner", new BlockBannerWall(EnumColor.WHITE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(WHITE_BANNER)));
/* 533 */   public static final Block ORANGE_WALL_BANNER = a("orange_wall_banner", new BlockBannerWall(EnumColor.ORANGE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(ORANGE_BANNER)));
/* 534 */   public static final Block MAGENTA_WALL_BANNER = a("magenta_wall_banner", new BlockBannerWall(EnumColor.MAGENTA, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(MAGENTA_BANNER)));
/* 535 */   public static final Block LIGHT_BLUE_WALL_BANNER = a("light_blue_wall_banner", new BlockBannerWall(EnumColor.LIGHT_BLUE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(LIGHT_BLUE_BANNER)));
/* 536 */   public static final Block YELLOW_WALL_BANNER = a("yellow_wall_banner", new BlockBannerWall(EnumColor.YELLOW, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(YELLOW_BANNER)));
/* 537 */   public static final Block LIME_WALL_BANNER = a("lime_wall_banner", new BlockBannerWall(EnumColor.LIME, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(LIME_BANNER)));
/* 538 */   public static final Block PINK_WALL_BANNER = a("pink_wall_banner", new BlockBannerWall(EnumColor.PINK, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(PINK_BANNER)));
/* 539 */   public static final Block GRAY_WALL_BANNER = a("gray_wall_banner", new BlockBannerWall(EnumColor.GRAY, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(GRAY_BANNER)));
/* 540 */   public static final Block LIGHT_GRAY_WALL_BANNER = a("light_gray_wall_banner", new BlockBannerWall(EnumColor.LIGHT_GRAY, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(LIGHT_GRAY_BANNER)));
/* 541 */   public static final Block CYAN_WALL_BANNER = a("cyan_wall_banner", new BlockBannerWall(EnumColor.CYAN, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(CYAN_BANNER)));
/* 542 */   public static final Block PURPLE_WALL_BANNER = a("purple_wall_banner", new BlockBannerWall(EnumColor.PURPLE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(PURPLE_BANNER)));
/* 543 */   public static final Block BLUE_WALL_BANNER = a("blue_wall_banner", new BlockBannerWall(EnumColor.BLUE, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(BLUE_BANNER)));
/* 544 */   public static final Block BROWN_WALL_BANNER = a("brown_wall_banner", new BlockBannerWall(EnumColor.BROWN, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(BROWN_BANNER)));
/* 545 */   public static final Block GREEN_WALL_BANNER = a("green_wall_banner", new BlockBannerWall(EnumColor.GREEN, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(GREEN_BANNER)));
/* 546 */   public static final Block RED_WALL_BANNER = a("red_wall_banner", new BlockBannerWall(EnumColor.RED, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(RED_BANNER)));
/* 547 */   public static final Block BLACK_WALL_BANNER = a("black_wall_banner", new BlockBannerWall(EnumColor.BLACK, BlockBase.Info.a(Material.WOOD).a().d(1.0F).a(SoundEffectType.a).a(BLACK_BANNER)));
/* 548 */   public static final Block RED_SANDSTONE = a("red_sandstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.q).h().d(0.8F)));
/* 549 */   public static final Block CHISELED_RED_SANDSTONE = a("chiseled_red_sandstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.q).h().d(0.8F)));
/* 550 */   public static final Block CUT_RED_SANDSTONE = a("cut_red_sandstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.q).h().d(0.8F)));
/* 551 */   public static final Block RED_SANDSTONE_STAIRS = a("red_sandstone_stairs", new BlockStairs(RED_SANDSTONE.getBlockData(), BlockBase.Info.a(RED_SANDSTONE)));
/* 552 */   public static final Block OAK_SLAB = a("oak_slab", new BlockStepAbstract(BlockBase.Info.a(Material.WOOD, MaterialMapColor.o).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 553 */   public static final Block SPRUCE_SLAB = a("spruce_slab", new BlockStepAbstract(BlockBase.Info.a(Material.WOOD, MaterialMapColor.J).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 554 */   public static final Block BIRCH_SLAB = a("birch_slab", new BlockStepAbstract(BlockBase.Info.a(Material.WOOD, MaterialMapColor.d).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 555 */   public static final Block JUNGLE_SLAB = a("jungle_slab", new BlockStepAbstract(BlockBase.Info.a(Material.WOOD, MaterialMapColor.l).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 556 */   public static final Block ACACIA_SLAB = a("acacia_slab", new BlockStepAbstract(BlockBase.Info.a(Material.WOOD, MaterialMapColor.q).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 557 */   public static final Block DARK_OAK_SLAB = a("dark_oak_slab", new BlockStepAbstract(BlockBase.Info.a(Material.WOOD, MaterialMapColor.B).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 558 */   public static final Block STONE_SLAB = a("stone_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.m).h().a(2.0F, 6.0F)));
/* 559 */   public static final Block SMOOTH_STONE_SLAB = a("smooth_stone_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.m).h().a(2.0F, 6.0F)));
/* 560 */   public static final Block SANDSTONE_SLAB = a("sandstone_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().a(2.0F, 6.0F)));
/* 561 */   public static final Block CUT_SANDSTONE_SLAB = a("cut_sandstone_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().a(2.0F, 6.0F)));
/* 562 */   public static final Block PETRIFIED_OAK_SLAB = a("petrified_oak_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.o).h().a(2.0F, 6.0F)));
/* 563 */   public static final Block COBBLESTONE_SLAB = a("cobblestone_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.m).h().a(2.0F, 6.0F)));
/* 564 */   public static final Block BRICK_SLAB = a("brick_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.D).h().a(2.0F, 6.0F)));
/* 565 */   public static final Block STONE_BRICK_SLAB = a("stone_brick_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.m).h().a(2.0F, 6.0F)));
/* 566 */   public static final Block NETHER_BRICK_SLAB = a("nether_brick_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(2.0F, 6.0F).a(SoundEffectType.L)));
/* 567 */   public static final Block QUARTZ_SLAB = a("quartz_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.p).h().a(2.0F, 6.0F)));
/* 568 */   public static final Block RED_SANDSTONE_SLAB = a("red_sandstone_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.q).h().a(2.0F, 6.0F)));
/* 569 */   public static final Block CUT_RED_SANDSTONE_SLAB = a("cut_red_sandstone_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.q).h().a(2.0F, 6.0F)));
/* 570 */   public static final Block PURPUR_SLAB = a("purpur_slab", new BlockStepAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.r).h().a(2.0F, 6.0F)));
/* 571 */   public static final Block SMOOTH_STONE = a("smooth_stone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.m).h().a(2.0F, 6.0F)));
/* 572 */   public static final Block SMOOTH_SANDSTONE = a("smooth_sandstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().a(2.0F, 6.0F)));
/* 573 */   public static final Block SMOOTH_QUARTZ = a("smooth_quartz", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.p).h().a(2.0F, 6.0F)));
/* 574 */   public static final Block SMOOTH_RED_SANDSTONE = a("smooth_red_sandstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.q).h().a(2.0F, 6.0F)));
/* 575 */   public static final Block SPRUCE_FENCE_GATE = a("spruce_fence_gate", new BlockFenceGate(BlockBase.Info.a(Material.WOOD, SPRUCE_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 576 */   public static final Block BIRCH_FENCE_GATE = a("birch_fence_gate", new BlockFenceGate(BlockBase.Info.a(Material.WOOD, BIRCH_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 577 */   public static final Block JUNGLE_FENCE_GATE = a("jungle_fence_gate", new BlockFenceGate(BlockBase.Info.a(Material.WOOD, JUNGLE_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 578 */   public static final Block ACACIA_FENCE_GATE = a("acacia_fence_gate", new BlockFenceGate(BlockBase.Info.a(Material.WOOD, ACACIA_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 579 */   public static final Block DARK_OAK_FENCE_GATE = a("dark_oak_fence_gate", new BlockFenceGate(BlockBase.Info.a(Material.WOOD, DARK_OAK_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 580 */   public static final Block SPRUCE_FENCE = a("spruce_fence", new BlockFence(BlockBase.Info.a(Material.WOOD, SPRUCE_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 581 */   public static final Block BIRCH_FENCE = a("birch_fence", new BlockFence(BlockBase.Info.a(Material.WOOD, BIRCH_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 582 */   public static final Block JUNGLE_FENCE = a("jungle_fence", new BlockFence(BlockBase.Info.a(Material.WOOD, JUNGLE_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 583 */   public static final Block ACACIA_FENCE = a("acacia_fence", new BlockFence(BlockBase.Info.a(Material.WOOD, ACACIA_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 584 */   public static final Block DARK_OAK_FENCE = a("dark_oak_fence", new BlockFence(BlockBase.Info.a(Material.WOOD, DARK_OAK_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 585 */   public static final Block SPRUCE_DOOR = a("spruce_door", new BlockDoor(BlockBase.Info.a(Material.WOOD, SPRUCE_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b()));
/* 586 */   public static final Block BIRCH_DOOR = a("birch_door", new BlockDoor(BlockBase.Info.a(Material.WOOD, BIRCH_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b()));
/* 587 */   public static final Block JUNGLE_DOOR = a("jungle_door", new BlockDoor(BlockBase.Info.a(Material.WOOD, JUNGLE_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b()));
/* 588 */   public static final Block ACACIA_DOOR = a("acacia_door", new BlockDoor(BlockBase.Info.a(Material.WOOD, ACACIA_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b()));
/* 589 */   public static final Block DARK_OAK_DOOR = a("dark_oak_door", new BlockDoor(BlockBase.Info.a(Material.WOOD, DARK_OAK_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b()));
/* 590 */   public static final Block END_ROD = a("end_rod", new BlockEndRod(BlockBase.Info.a(Material.ORIENTABLE).c().a(var0 -> 14).a(SoundEffectType.a).b()));
/* 591 */   public static final Block CHORUS_PLANT = a("chorus_plant", new BlockChorusFruit(BlockBase.Info.a(Material.PLANT, MaterialMapColor.z).d(0.4F).a(SoundEffectType.a).b()));
/* 592 */   public static final Block CHORUS_FLOWER = a("chorus_flower", new BlockChorusFlower((BlockChorusFruit)CHORUS_PLANT, BlockBase.Info.a(Material.PLANT, MaterialMapColor.z).d().d(0.4F).a(SoundEffectType.a).b()));
/* 593 */   public static final Block PURPUR_BLOCK = a("purpur_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.r).h().a(1.5F, 6.0F)));
/* 594 */   public static final Block PURPUR_PILLAR = a("purpur_pillar", new BlockRotatable(BlockBase.Info.a(Material.STONE, MaterialMapColor.r).h().a(1.5F, 6.0F)));
/* 595 */   public static final Block PURPUR_STAIRS = a("purpur_stairs", new BlockStairs(PURPUR_BLOCK.getBlockData(), BlockBase.Info.a(PURPUR_BLOCK)));
/* 596 */   public static final Block END_STONE_BRICKS = a("end_stone_bricks", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().a(3.0F, 9.0F)));
/* 597 */   public static final Block BEETROOTS = a("beetroots", new BlockBeetroot(BlockBase.Info.a(Material.PLANT).a().d().c().a(SoundEffectType.u)));
/* 598 */   public static final Block GRASS_PATH = a("grass_path", new BlockGrassPath(BlockBase.Info.a(Material.EARTH).d(0.65F).a(SoundEffectType.c).c(Blocks::a).b(Blocks::a)));
/* 599 */   public static final Block END_GATEWAY = a("end_gateway", new BlockEndGateway(BlockBase.Info.a(Material.PORTAL, MaterialMapColor.E).a().a(var0 -> 15).a(-1.0F, 3600000.0F).f()));
/* 600 */   public static final Block REPEATING_COMMAND_BLOCK = a("repeating_command_block", new BlockCommand(BlockBase.Info.a(Material.ORE, MaterialMapColor.z).h().a(-1.0F, 3600000.0F).f())); public static final Block FROSTED_ICE;
/* 601 */   public static final Block CHAIN_COMMAND_BLOCK = a("chain_command_block", new BlockCommand(BlockBase.Info.a(Material.ORE, MaterialMapColor.C).h().a(-1.0F, 3600000.0F).f())); public static final Block MAGMA_BLOCK; static {
/* 602 */     FROSTED_ICE = a("frosted_ice", new BlockIceFrost(BlockBase.Info.a(Material.ICE).a(0.98F).d().d(0.5F).a(SoundEffectType.g).b().a((var0, var1, var2, var3) -> (var3 == EntityTypes.POLAR_BEAR))));
/* 603 */     MAGMA_BLOCK = a("magma_block", new BlockMagma(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(var0 -> 3).d().d(0.5F).a((var0, var1, var2, var3) -> var3.c()).d(Blocks::a).e(Blocks::a)));
/* 604 */   } public static final Block NETHER_WART_BLOCK = a("nether_wart_block", new Block(BlockBase.Info.a(Material.GRASS, MaterialMapColor.D).d(1.0F).a(SoundEffectType.J)));
/* 605 */   public static final Block RED_NETHER_BRICKS = a("red_nether_bricks", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(2.0F, 6.0F).a(SoundEffectType.L)));
/* 606 */   public static final Block BONE_BLOCK = a("bone_block", new BlockRotatable(BlockBase.Info.a(Material.STONE, MaterialMapColor.d).h().d(2.0F).a(SoundEffectType.O)));
/* 607 */   public static final Block STRUCTURE_VOID = a("structure_void", new BlockStructureVoid(BlockBase.Info.a(Material.STRUCTURE_VOID).a().f()));
/* 608 */   public static final Block OBSERVER = a("observer", new BlockObserver(BlockBase.Info.a(Material.STONE).d(3.0F).h().a(Blocks::b)));
/* 609 */   public static final Block SHULKER_BOX = a("shulker_box", a((EnumColor)null, BlockBase.Info.a(Material.SHULKER_SHELL)));
/* 610 */   public static final Block WHITE_SHULKER_BOX = a("white_shulker_box", a(EnumColor.WHITE, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.j)));
/* 611 */   public static final Block ORANGE_SHULKER_BOX = a("orange_shulker_box", a(EnumColor.ORANGE, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.q)));
/* 612 */   public static final Block MAGENTA_SHULKER_BOX = a("magenta_shulker_box", a(EnumColor.MAGENTA, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.r)));
/* 613 */   public static final Block LIGHT_BLUE_SHULKER_BOX = a("light_blue_shulker_box", a(EnumColor.LIGHT_BLUE, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.s)));
/* 614 */   public static final Block YELLOW_SHULKER_BOX = a("yellow_shulker_box", a(EnumColor.YELLOW, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.t)));
/* 615 */   public static final Block LIME_SHULKER_BOX = a("lime_shulker_box", a(EnumColor.LIME, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.u)));
/* 616 */   public static final Block PINK_SHULKER_BOX = a("pink_shulker_box", a(EnumColor.PINK, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.v)));
/* 617 */   public static final Block GRAY_SHULKER_BOX = a("gray_shulker_box", a(EnumColor.GRAY, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.w)));
/* 618 */   public static final Block LIGHT_GRAY_SHULKER_BOX = a("light_gray_shulker_box", a(EnumColor.LIGHT_GRAY, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.x)));
/* 619 */   public static final Block CYAN_SHULKER_BOX = a("cyan_shulker_box", a(EnumColor.CYAN, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.y)));
/* 620 */   public static final Block PURPLE_SHULKER_BOX = a("purple_shulker_box", a(EnumColor.PURPLE, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.V)));
/* 621 */   public static final Block BLUE_SHULKER_BOX = a("blue_shulker_box", a(EnumColor.BLUE, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.A)));
/* 622 */   public static final Block BROWN_SHULKER_BOX = a("brown_shulker_box", a(EnumColor.BROWN, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.B)));
/* 623 */   public static final Block GREEN_SHULKER_BOX = a("green_shulker_box", a(EnumColor.GREEN, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.C)));
/* 624 */   public static final Block RED_SHULKER_BOX = a("red_shulker_box", a(EnumColor.RED, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.D)));
/* 625 */   public static final Block BLACK_SHULKER_BOX = a("black_shulker_box", a(EnumColor.BLACK, BlockBase.Info.a(Material.SHULKER_SHELL, MaterialMapColor.E)));
/* 626 */   public static final Block WHITE_GLAZED_TERRACOTTA = a("white_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.WHITE).h().d(1.4F)));
/* 627 */   public static final Block ORANGE_GLAZED_TERRACOTTA = a("orange_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.ORANGE).h().d(1.4F)));
/* 628 */   public static final Block MAGENTA_GLAZED_TERRACOTTA = a("magenta_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.MAGENTA).h().d(1.4F)));
/* 629 */   public static final Block LIGHT_BLUE_GLAZED_TERRACOTTA = a("light_blue_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.LIGHT_BLUE).h().d(1.4F)));
/* 630 */   public static final Block YELLOW_GLAZED_TERRACOTTA = a("yellow_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.YELLOW).h().d(1.4F)));
/* 631 */   public static final Block LIME_GLAZED_TERRACOTTA = a("lime_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.LIME).h().d(1.4F)));
/* 632 */   public static final Block PINK_GLAZED_TERRACOTTA = a("pink_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.PINK).h().d(1.4F)));
/* 633 */   public static final Block GRAY_GLAZED_TERRACOTTA = a("gray_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.GRAY).h().d(1.4F)));
/* 634 */   public static final Block LIGHT_GRAY_GLAZED_TERRACOTTA = a("light_gray_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.LIGHT_GRAY).h().d(1.4F)));
/* 635 */   public static final Block CYAN_GLAZED_TERRACOTTA = a("cyan_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.CYAN).h().d(1.4F)));
/* 636 */   public static final Block PURPLE_GLAZED_TERRACOTTA = a("purple_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.PURPLE).h().d(1.4F)));
/* 637 */   public static final Block BLUE_GLAZED_TERRACOTTA = a("blue_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.BLUE).h().d(1.4F)));
/* 638 */   public static final Block BROWN_GLAZED_TERRACOTTA = a("brown_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.BROWN).h().d(1.4F)));
/* 639 */   public static final Block GREEN_GLAZED_TERRACOTTA = a("green_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.GREEN).h().d(1.4F)));
/* 640 */   public static final Block RED_GLAZED_TERRACOTTA = a("red_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.RED).h().d(1.4F)));
/* 641 */   public static final Block BLACK_GLAZED_TERRACOTTA = a("black_glazed_terracotta", new BlockGlazedTerracotta(BlockBase.Info.a(Material.STONE, EnumColor.BLACK).h().d(1.4F)));
/* 642 */   public static final Block WHITE_CONCRETE = a("white_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.WHITE).h().d(1.8F)));
/* 643 */   public static final Block ORANGE_CONCRETE = a("orange_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.ORANGE).h().d(1.8F)));
/* 644 */   public static final Block MAGENTA_CONCRETE = a("magenta_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.MAGENTA).h().d(1.8F)));
/* 645 */   public static final Block LIGHT_BLUE_CONCRETE = a("light_blue_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.LIGHT_BLUE).h().d(1.8F)));
/* 646 */   public static final Block YELLOW_CONCRETE = a("yellow_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.YELLOW).h().d(1.8F)));
/* 647 */   public static final Block LIME_CONCRETE = a("lime_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.LIME).h().d(1.8F)));
/* 648 */   public static final Block PINK_CONCRETE = a("pink_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.PINK).h().d(1.8F)));
/* 649 */   public static final Block GRAY_CONCRETE = a("gray_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.GRAY).h().d(1.8F)));
/* 650 */   public static final Block LIGHT_GRAY_CONCRETE = a("light_gray_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.LIGHT_GRAY).h().d(1.8F)));
/* 651 */   public static final Block CYAN_CONCRETE = a("cyan_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.CYAN).h().d(1.8F)));
/* 652 */   public static final Block PURPLE_CONCRETE = a("purple_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.PURPLE).h().d(1.8F)));
/* 653 */   public static final Block BLUE_CONCRETE = a("blue_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.BLUE).h().d(1.8F)));
/* 654 */   public static final Block BROWN_CONCRETE = a("brown_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.BROWN).h().d(1.8F)));
/* 655 */   public static final Block GREEN_CONCRETE = a("green_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.GREEN).h().d(1.8F)));
/* 656 */   public static final Block RED_CONCRETE = a("red_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.RED).h().d(1.8F)));
/* 657 */   public static final Block BLACK_CONCRETE = a("black_concrete", new Block(BlockBase.Info.a(Material.STONE, EnumColor.BLACK).h().d(1.8F)));
/* 658 */   public static final Block WHITE_CONCRETE_POWDER = a("white_concrete_powder", new BlockConcretePowder(WHITE_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.WHITE).d(0.5F).a(SoundEffectType.i)));
/* 659 */   public static final Block ORANGE_CONCRETE_POWDER = a("orange_concrete_powder", new BlockConcretePowder(ORANGE_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.ORANGE).d(0.5F).a(SoundEffectType.i)));
/* 660 */   public static final Block MAGENTA_CONCRETE_POWDER = a("magenta_concrete_powder", new BlockConcretePowder(MAGENTA_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.MAGENTA).d(0.5F).a(SoundEffectType.i)));
/* 661 */   public static final Block LIGHT_BLUE_CONCRETE_POWDER = a("light_blue_concrete_powder", new BlockConcretePowder(LIGHT_BLUE_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.LIGHT_BLUE).d(0.5F).a(SoundEffectType.i)));
/* 662 */   public static final Block YELLOW_CONCRETE_POWDER = a("yellow_concrete_powder", new BlockConcretePowder(YELLOW_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.YELLOW).d(0.5F).a(SoundEffectType.i)));
/* 663 */   public static final Block LIME_CONCRETE_POWDER = a("lime_concrete_powder", new BlockConcretePowder(LIME_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.LIME).d(0.5F).a(SoundEffectType.i)));
/* 664 */   public static final Block PINK_CONCRETE_POWDER = a("pink_concrete_powder", new BlockConcretePowder(PINK_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.PINK).d(0.5F).a(SoundEffectType.i)));
/* 665 */   public static final Block GRAY_CONCRETE_POWDER = a("gray_concrete_powder", new BlockConcretePowder(GRAY_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.GRAY).d(0.5F).a(SoundEffectType.i)));
/* 666 */   public static final Block LIGHT_GRAY_CONCRETE_POWDER = a("light_gray_concrete_powder", new BlockConcretePowder(LIGHT_GRAY_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.LIGHT_GRAY).d(0.5F).a(SoundEffectType.i)));
/* 667 */   public static final Block CYAN_CONCRETE_POWDER = a("cyan_concrete_powder", new BlockConcretePowder(CYAN_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.CYAN).d(0.5F).a(SoundEffectType.i)));
/* 668 */   public static final Block PURPLE_CONCRETE_POWDER = a("purple_concrete_powder", new BlockConcretePowder(PURPLE_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.PURPLE).d(0.5F).a(SoundEffectType.i)));
/* 669 */   public static final Block BLUE_CONCRETE_POWDER = a("blue_concrete_powder", new BlockConcretePowder(BLUE_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.BLUE).d(0.5F).a(SoundEffectType.i)));
/* 670 */   public static final Block BROWN_CONCRETE_POWDER = a("brown_concrete_powder", new BlockConcretePowder(BROWN_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.BROWN).d(0.5F).a(SoundEffectType.i)));
/* 671 */   public static final Block GREEN_CONCRETE_POWDER = a("green_concrete_powder", new BlockConcretePowder(GREEN_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.GREEN).d(0.5F).a(SoundEffectType.i)));
/* 672 */   public static final Block RED_CONCRETE_POWDER = a("red_concrete_powder", new BlockConcretePowder(RED_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.RED).d(0.5F).a(SoundEffectType.i)));
/* 673 */   public static final Block BLACK_CONCRETE_POWDER = a("black_concrete_powder", new BlockConcretePowder(BLACK_CONCRETE, BlockBase.Info.a(Material.SAND, EnumColor.BLACK).d(0.5F).a(SoundEffectType.i)));
/* 674 */   public static final Block KELP = a("kelp", new BlockKelp(BlockBase.Info.a(Material.WATER_PLANT).a().d().c().a(SoundEffectType.o)));
/* 675 */   public static final Block KELP_PLANT = a("kelp_plant", new BlockKelpPlant(BlockBase.Info.a(Material.WATER_PLANT).a().c().a(SoundEffectType.o)));
/* 676 */   public static final Block DRIED_KELP_BLOCK = a("dried_kelp_block", new Block(BlockBase.Info.a(Material.GRASS, MaterialMapColor.C).a(0.5F, 2.5F).a(SoundEffectType.c)));
/* 677 */   public static final Block TURTLE_EGG = a("turtle_egg", new BlockTurtleEgg(BlockBase.Info.a(Material.DRAGON_EGG, MaterialMapColor.d).d(0.5F).a(SoundEffectType.f).d().b()));
/* 678 */   public static final Block DEAD_TUBE_CORAL_BLOCK = a("dead_tube_coral_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a(1.5F, 6.0F)));
/* 679 */   public static final Block DEAD_BRAIN_CORAL_BLOCK = a("dead_brain_coral_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a(1.5F, 6.0F)));
/* 680 */   public static final Block DEAD_BUBBLE_CORAL_BLOCK = a("dead_bubble_coral_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a(1.5F, 6.0F)));
/* 681 */   public static final Block DEAD_FIRE_CORAL_BLOCK = a("dead_fire_coral_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a(1.5F, 6.0F)));
/* 682 */   public static final Block DEAD_HORN_CORAL_BLOCK = a("dead_horn_coral_block", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a(1.5F, 6.0F)));
/* 683 */   public static final Block TUBE_CORAL_BLOCK = a("tube_coral_block", new BlockCoral(DEAD_TUBE_CORAL_BLOCK, BlockBase.Info.a(Material.STONE, MaterialMapColor.A).h().a(1.5F, 6.0F).a(SoundEffectType.p)));
/* 684 */   public static final Block BRAIN_CORAL_BLOCK = a("brain_coral_block", new BlockCoral(DEAD_BRAIN_CORAL_BLOCK, BlockBase.Info.a(Material.STONE, MaterialMapColor.v).h().a(1.5F, 6.0F).a(SoundEffectType.p)));
/* 685 */   public static final Block BUBBLE_CORAL_BLOCK = a("bubble_coral_block", new BlockCoral(DEAD_BUBBLE_CORAL_BLOCK, BlockBase.Info.a(Material.STONE, MaterialMapColor.z).h().a(1.5F, 6.0F).a(SoundEffectType.p)));
/* 686 */   public static final Block FIRE_CORAL_BLOCK = a("fire_coral_block", new BlockCoral(DEAD_FIRE_CORAL_BLOCK, BlockBase.Info.a(Material.STONE, MaterialMapColor.D).h().a(1.5F, 6.0F).a(SoundEffectType.p)));
/* 687 */   public static final Block HORN_CORAL_BLOCK = a("horn_coral_block", new BlockCoral(DEAD_HORN_CORAL_BLOCK, BlockBase.Info.a(Material.STONE, MaterialMapColor.t).h().a(1.5F, 6.0F).a(SoundEffectType.p)));
/* 688 */   public static final Block DEAD_TUBE_CORAL = a("dead_tube_coral", new BlockCoralDead(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 689 */   public static final Block DEAD_BRAIN_CORAL = a("dead_brain_coral", new BlockCoralDead(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 690 */   public static final Block DEAD_BUBBLE_CORAL = a("dead_bubble_coral", new BlockCoralDead(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 691 */   public static final Block DEAD_FIRE_CORAL = a("dead_fire_coral", new BlockCoralDead(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 692 */   public static final Block DEAD_HORN_CORAL = a("dead_horn_coral", new BlockCoralDead(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 693 */   public static final Block TUBE_CORAL = a("tube_coral", new BlockCoralPlant(DEAD_TUBE_CORAL, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.A).a().c().a(SoundEffectType.o)));
/* 694 */   public static final Block BRAIN_CORAL = a("brain_coral", new BlockCoralPlant(DEAD_BRAIN_CORAL, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.v).a().c().a(SoundEffectType.o)));
/* 695 */   public static final Block BUBBLE_CORAL = a("bubble_coral", new BlockCoralPlant(DEAD_BUBBLE_CORAL, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.z).a().c().a(SoundEffectType.o)));
/* 696 */   public static final Block FIRE_CORAL = a("fire_coral", new BlockCoralPlant(DEAD_FIRE_CORAL, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.D).a().c().a(SoundEffectType.o)));
/* 697 */   public static final Block HORN_CORAL = a("horn_coral", new BlockCoralPlant(DEAD_HORN_CORAL, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.t).a().c().a(SoundEffectType.o)));
/* 698 */   public static final Block DEAD_TUBE_CORAL_FAN = a("dead_tube_coral_fan", new BlockCoralFanAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 699 */   public static final Block DEAD_BRAIN_CORAL_FAN = a("dead_brain_coral_fan", new BlockCoralFanAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 700 */   public static final Block DEAD_BUBBLE_CORAL_FAN = a("dead_bubble_coral_fan", new BlockCoralFanAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 701 */   public static final Block DEAD_FIRE_CORAL_FAN = a("dead_fire_coral_fan", new BlockCoralFanAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 702 */   public static final Block DEAD_HORN_CORAL_FAN = a("dead_horn_coral_fan", new BlockCoralFanAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c()));
/* 703 */   public static final Block TUBE_CORAL_FAN = a("tube_coral_fan", new BlockCoralFan(DEAD_TUBE_CORAL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.A).a().c().a(SoundEffectType.o)));
/* 704 */   public static final Block BRAIN_CORAL_FAN = a("brain_coral_fan", new BlockCoralFan(DEAD_BRAIN_CORAL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.v).a().c().a(SoundEffectType.o)));
/* 705 */   public static final Block BUBBLE_CORAL_FAN = a("bubble_coral_fan", new BlockCoralFan(DEAD_BUBBLE_CORAL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.z).a().c().a(SoundEffectType.o)));
/* 706 */   public static final Block FIRE_CORAL_FAN = a("fire_coral_fan", new BlockCoralFan(DEAD_FIRE_CORAL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.D).a().c().a(SoundEffectType.o)));
/* 707 */   public static final Block HORN_CORAL_FAN = a("horn_coral_fan", new BlockCoralFan(DEAD_HORN_CORAL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.t).a().c().a(SoundEffectType.o)));
/* 708 */   public static final Block DEAD_TUBE_CORAL_WALL_FAN = a("dead_tube_coral_wall_fan", new BlockCoralFanWallAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c().a(DEAD_TUBE_CORAL_FAN)));
/* 709 */   public static final Block DEAD_BRAIN_CORAL_WALL_FAN = a("dead_brain_coral_wall_fan", new BlockCoralFanWallAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c().a(DEAD_BRAIN_CORAL_FAN)));
/* 710 */   public static final Block DEAD_BUBBLE_CORAL_WALL_FAN = a("dead_bubble_coral_wall_fan", new BlockCoralFanWallAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c().a(DEAD_BUBBLE_CORAL_FAN)));
/* 711 */   public static final Block DEAD_FIRE_CORAL_WALL_FAN = a("dead_fire_coral_wall_fan", new BlockCoralFanWallAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c().a(DEAD_FIRE_CORAL_FAN)));
/* 712 */   public static final Block DEAD_HORN_CORAL_WALL_FAN = a("dead_horn_coral_wall_fan", new BlockCoralFanWallAbstract(BlockBase.Info.a(Material.STONE, MaterialMapColor.w).h().a().c().a(DEAD_HORN_CORAL_FAN)));
/* 713 */   public static final Block TUBE_CORAL_WALL_FAN = a("tube_coral_wall_fan", new BlockCoralFanWall(DEAD_TUBE_CORAL_WALL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.A).a().c().a(SoundEffectType.o).a(TUBE_CORAL_FAN)));
/* 714 */   public static final Block BRAIN_CORAL_WALL_FAN = a("brain_coral_wall_fan", new BlockCoralFanWall(DEAD_BRAIN_CORAL_WALL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.v).a().c().a(SoundEffectType.o).a(BRAIN_CORAL_FAN)));
/* 715 */   public static final Block BUBBLE_CORAL_WALL_FAN = a("bubble_coral_wall_fan", new BlockCoralFanWall(DEAD_BUBBLE_CORAL_WALL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.z).a().c().a(SoundEffectType.o).a(BUBBLE_CORAL_FAN)));
/* 716 */   public static final Block FIRE_CORAL_WALL_FAN = a("fire_coral_wall_fan", new BlockCoralFanWall(DEAD_FIRE_CORAL_WALL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.D).a().c().a(SoundEffectType.o).a(FIRE_CORAL_FAN)));
/* 717 */   public static final Block HORN_CORAL_WALL_FAN = a("horn_coral_wall_fan", new BlockCoralFanWall(DEAD_HORN_CORAL_WALL_FAN, BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.t).a().c().a(SoundEffectType.o).a(HORN_CORAL_FAN))); public static final Block SEA_PICKLE; static {
/* 718 */     SEA_PICKLE = a("sea_pickle", new BlockSeaPickle(BlockBase.Info.a(Material.WATER_PLANT, MaterialMapColor.C).a(var0 -> BlockSeaPickle.h(var0) ? 0 : (3 + 3 * ((Integer)var0.get(BlockSeaPickle.a)).intValue())).a(SoundEffectType.m).b()));
/* 719 */   } public static final Block BLUE_ICE = a("blue_ice", new BlockHalfTransparent(BlockBase.Info.a(Material.SNOW_LAYER).d(2.8F).a(0.989F).a(SoundEffectType.g)));
/* 720 */   public static final Block CONDUIT = a("conduit", new BlockConduit(BlockBase.Info.a(Material.SHATTERABLE, MaterialMapColor.G).d(3.0F).a(var0 -> 15).b()));
/* 721 */   public static final Block BAMBOO_SAPLING = a("bamboo_sapling", new BlockBambooSapling(BlockBase.Info.a(Material.BAMBOO_SAPLING).d().c().a().d(1.0F).a(SoundEffectType.r)));
/* 722 */   public static final Block BAMBOO = a("bamboo", new BlockBamboo(BlockBase.Info.a(Material.BAMBOO, MaterialMapColor.i).d().c().d(1.0F).a(SoundEffectType.q).b()));
/* 723 */   public static final Block POTTED_BAMBOO = a("potted_bamboo", new BlockFlowerPot(BAMBOO, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/*     */   
/* 725 */   public static final Block VOID_AIR = a("void_air", new BlockAir(BlockBase.Info.a(Material.AIR).a().f().g()));
/* 726 */   public static final Block CAVE_AIR = a("cave_air", new BlockAir(BlockBase.Info.a(Material.AIR).a().f().g()));
/*     */   
/* 728 */   public static final Block BUBBLE_COLUMN = a("bubble_column", new BlockBubbleColumn(BlockBase.Info.a(Material.BUBBLE_COLUMN).a().f()));
/*     */   
/* 730 */   public static final Block POLISHED_GRANITE_STAIRS = a("polished_granite_stairs", new BlockStairs(POLISHED_GRANITE.getBlockData(), BlockBase.Info.a(POLISHED_GRANITE)));
/* 731 */   public static final Block SMOOTH_RED_SANDSTONE_STAIRS = a("smooth_red_sandstone_stairs", new BlockStairs(SMOOTH_RED_SANDSTONE.getBlockData(), BlockBase.Info.a(SMOOTH_RED_SANDSTONE)));
/* 732 */   public static final Block MOSSY_STONE_BRICK_STAIRS = a("mossy_stone_brick_stairs", new BlockStairs(MOSSY_STONE_BRICKS.getBlockData(), BlockBase.Info.a(MOSSY_STONE_BRICKS)));
/* 733 */   public static final Block POLISHED_DIORITE_STAIRS = a("polished_diorite_stairs", new BlockStairs(POLISHED_DIORITE.getBlockData(), BlockBase.Info.a(POLISHED_DIORITE)));
/* 734 */   public static final Block MOSSY_COBBLESTONE_STAIRS = a("mossy_cobblestone_stairs", new BlockStairs(MOSSY_COBBLESTONE.getBlockData(), BlockBase.Info.a(MOSSY_COBBLESTONE)));
/* 735 */   public static final Block END_STONE_BRICK_STAIRS = a("end_stone_brick_stairs", new BlockStairs(END_STONE_BRICKS.getBlockData(), BlockBase.Info.a(END_STONE_BRICKS)));
/* 736 */   public static final Block STONE_STAIRS = a("stone_stairs", new BlockStairs(STONE.getBlockData(), BlockBase.Info.a(STONE)));
/* 737 */   public static final Block SMOOTH_SANDSTONE_STAIRS = a("smooth_sandstone_stairs", new BlockStairs(SMOOTH_SANDSTONE.getBlockData(), BlockBase.Info.a(SMOOTH_SANDSTONE)));
/* 738 */   public static final Block SMOOTH_QUARTZ_STAIRS = a("smooth_quartz_stairs", new BlockStairs(SMOOTH_QUARTZ.getBlockData(), BlockBase.Info.a(SMOOTH_QUARTZ)));
/* 739 */   public static final Block GRANITE_STAIRS = a("granite_stairs", new BlockStairs(GRANITE.getBlockData(), BlockBase.Info.a(GRANITE)));
/* 740 */   public static final Block ANDESITE_STAIRS = a("andesite_stairs", new BlockStairs(ANDESITE.getBlockData(), BlockBase.Info.a(ANDESITE)));
/* 741 */   public static final Block RED_NETHER_BRICK_STAIRS = a("red_nether_brick_stairs", new BlockStairs(RED_NETHER_BRICKS.getBlockData(), BlockBase.Info.a(RED_NETHER_BRICKS)));
/* 742 */   public static final Block POLISHED_ANDESITE_STAIRS = a("polished_andesite_stairs", new BlockStairs(POLISHED_ANDESITE.getBlockData(), BlockBase.Info.a(POLISHED_ANDESITE)));
/* 743 */   public static final Block DIORITE_STAIRS = a("diorite_stairs", new BlockStairs(DIORITE.getBlockData(), BlockBase.Info.a(DIORITE)));
/*     */   
/* 745 */   public static final Block POLISHED_GRANITE_SLAB = a("polished_granite_slab", new BlockStepAbstract(BlockBase.Info.a(POLISHED_GRANITE)));
/* 746 */   public static final Block SMOOTH_RED_SANDSTONE_SLAB = a("smooth_red_sandstone_slab", new BlockStepAbstract(BlockBase.Info.a(SMOOTH_RED_SANDSTONE)));
/* 747 */   public static final Block MOSSY_STONE_BRICK_SLAB = a("mossy_stone_brick_slab", new BlockStepAbstract(BlockBase.Info.a(MOSSY_STONE_BRICKS)));
/* 748 */   public static final Block POLISHED_DIORITE_SLAB = a("polished_diorite_slab", new BlockStepAbstract(BlockBase.Info.a(POLISHED_DIORITE)));
/* 749 */   public static final Block MOSSY_COBBLESTONE_SLAB = a("mossy_cobblestone_slab", new BlockStepAbstract(BlockBase.Info.a(MOSSY_COBBLESTONE)));
/* 750 */   public static final Block END_STONE_BRICK_SLAB = a("end_stone_brick_slab", new BlockStepAbstract(BlockBase.Info.a(END_STONE_BRICKS)));
/* 751 */   public static final Block SMOOTH_SANDSTONE_SLAB = a("smooth_sandstone_slab", new BlockStepAbstract(BlockBase.Info.a(SMOOTH_SANDSTONE)));
/* 752 */   public static final Block SMOOTH_QUARTZ_SLAB = a("smooth_quartz_slab", new BlockStepAbstract(BlockBase.Info.a(SMOOTH_QUARTZ)));
/* 753 */   public static final Block GRANITE_SLAB = a("granite_slab", new BlockStepAbstract(BlockBase.Info.a(GRANITE)));
/* 754 */   public static final Block ANDESITE_SLAB = a("andesite_slab", new BlockStepAbstract(BlockBase.Info.a(ANDESITE)));
/* 755 */   public static final Block RED_NETHER_BRICK_SLAB = a("red_nether_brick_slab", new BlockStepAbstract(BlockBase.Info.a(RED_NETHER_BRICKS)));
/* 756 */   public static final Block POLISHED_ANDESITE_SLAB = a("polished_andesite_slab", new BlockStepAbstract(BlockBase.Info.a(POLISHED_ANDESITE)));
/* 757 */   public static final Block DIORITE_SLAB = a("diorite_slab", new BlockStepAbstract(BlockBase.Info.a(DIORITE)));
/*     */   
/* 759 */   public static final Block BRICK_WALL = a("brick_wall", new BlockCobbleWall(BlockBase.Info.a(BRICKS)));
/* 760 */   public static final Block PRISMARINE_WALL = a("prismarine_wall", new BlockCobbleWall(BlockBase.Info.a(PRISMARINE)));
/* 761 */   public static final Block RED_SANDSTONE_WALL = a("red_sandstone_wall", new BlockCobbleWall(BlockBase.Info.a(RED_SANDSTONE)));
/* 762 */   public static final Block MOSSY_STONE_BRICK_WALL = a("mossy_stone_brick_wall", new BlockCobbleWall(BlockBase.Info.a(MOSSY_STONE_BRICKS)));
/* 763 */   public static final Block GRANITE_WALL = a("granite_wall", new BlockCobbleWall(BlockBase.Info.a(GRANITE)));
/* 764 */   public static final Block STONE_BRICK_WALL = a("stone_brick_wall", new BlockCobbleWall(BlockBase.Info.a(STONE_BRICKS)));
/* 765 */   public static final Block NETHER_BRICK_WALL = a("nether_brick_wall", new BlockCobbleWall(BlockBase.Info.a(NETHER_BRICKS)));
/* 766 */   public static final Block ANDESITE_WALL = a("andesite_wall", new BlockCobbleWall(BlockBase.Info.a(ANDESITE)));
/* 767 */   public static final Block RED_NETHER_BRICK_WALL = a("red_nether_brick_wall", new BlockCobbleWall(BlockBase.Info.a(RED_NETHER_BRICKS)));
/* 768 */   public static final Block SANDSTONE_WALL = a("sandstone_wall", new BlockCobbleWall(BlockBase.Info.a(SANDSTONE)));
/* 769 */   public static final Block END_STONE_BRICK_WALL = a("end_stone_brick_wall", new BlockCobbleWall(BlockBase.Info.a(END_STONE_BRICKS)));
/* 770 */   public static final Block DIORITE_WALL = a("diorite_wall", new BlockCobbleWall(BlockBase.Info.a(DIORITE)));
/* 771 */   public static final Block SCAFFOLDING = a("scaffolding", new BlockScaffolding(BlockBase.Info.a(Material.ORIENTABLE, MaterialMapColor.d).a().a(SoundEffectType.s).e()));
/*     */   
/* 773 */   public static final Block LOOM = a("loom", new BlockLoom(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a)));
/* 774 */   public static final Block BARREL = a("barrel", new BlockBarrel(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a)));
/* 775 */   public static final Block SMOKER = a("smoker", new BlockSmoker(BlockBase.Info.a(Material.STONE).h().d(3.5F).a(a(13))));
/* 776 */   public static final Block BLAST_FURNACE = a("blast_furnace", new BlockBlastFurnace(BlockBase.Info.a(Material.STONE).h().d(3.5F).a(a(13))));
/* 777 */   public static final Block CARTOGRAPHY_TABLE = a("cartography_table", new BlockCartographyTable(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a)));
/* 778 */   public static final Block FLETCHING_TABLE = a("fletching_table", new BlockFletchingTable(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a)));
/* 779 */   public static final Block GRINDSTONE = a("grindstone", new BlockGrindstone(BlockBase.Info.a(Material.HEAVY, MaterialMapColor.h).h().a(2.0F, 6.0F).a(SoundEffectType.e)));
/* 780 */   public static final Block LECTERN = a("lectern", new BlockLectern(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a)));
/* 781 */   public static final Block SMITHING_TABLE = a("smithing_table", new BlockSmithingTable(BlockBase.Info.a(Material.WOOD).d(2.5F).a(SoundEffectType.a)));
/* 782 */   public static final Block STONECUTTER = a("stonecutter", new BlockStonecutter(BlockBase.Info.a(Material.STONE).h().d(3.5F)));
/* 783 */   public static final Block BELL = a("bell", new BlockBell(BlockBase.Info.a(Material.ORE, MaterialMapColor.F).h().d(5.0F).a(SoundEffectType.l)));
/* 784 */   public static final Block LANTERN = a("lantern", new BlockLantern(BlockBase.Info.a(Material.ORE).h().d(3.5F).a(SoundEffectType.y).a(var0 -> 15).b()));
/* 785 */   public static final Block SOUL_LANTERN = a("soul_lantern", new BlockLantern(BlockBase.Info.a(Material.ORE).h().d(3.5F).a(SoundEffectType.y).a(var0 -> 10).b()));
/* 786 */   public static final Block CAMPFIRE = a("campfire", new BlockCampfire(true, 1, BlockBase.Info.a(Material.WOOD, MaterialMapColor.J).d(2.0F).a(SoundEffectType.a).a(a(15)).b()));
/* 787 */   public static final Block SOUL_CAMPFIRE = a("soul_campfire", new BlockCampfire(false, 2, BlockBase.Info.a(Material.WOOD, MaterialMapColor.J).d(2.0F).a(SoundEffectType.a).a(a(10)).b()));
/* 788 */   public static final Block SWEET_BERRY_BUSH = a("sweet_berry_bush", new BlockSweetBerryBush(BlockBase.Info.a(Material.PLANT).d().a().a(SoundEffectType.t)));
/*     */   
/* 790 */   public static final Block WARPED_STEM = a("warped_stem", a(MaterialMapColor.af));
/* 791 */   public static final Block STRIPPED_WARPED_STEM = a("stripped_warped_stem", a(MaterialMapColor.af));
/* 792 */   public static final Block WARPED_HYPHAE = a("warped_hyphae", new BlockRotatable(BlockBase.Info.a(Material.NETHER_WOOD, MaterialMapColor.ag).d(2.0F).a(SoundEffectType.z)));
/* 793 */   public static final Block STRIPPED_WARPED_HYPHAE = a("stripped_warped_hyphae", new BlockRotatable(BlockBase.Info.a(Material.NETHER_WOOD, MaterialMapColor.ag).d(2.0F).a(SoundEffectType.z)));
/* 794 */   public static final Block WARPED_NYLIUM = a("warped_nylium", new BlockNylium(BlockBase.Info.a(Material.STONE, MaterialMapColor.ae).h().d(0.4F).a(SoundEffectType.A).d()));
/* 795 */   public static final Block WARPED_FUNGUS = a("warped_fungus", new BlockFungi(BlockBase.Info.a(Material.PLANT, MaterialMapColor.y).c().a().a(SoundEffectType.B), () -> BiomeDecoratorGroups.WARPED_FUNGI_PLANTED));
/* 796 */   public static final Block WARPED_WART_BLOCK = a("warped_wart_block", new Block(BlockBase.Info.a(Material.GRASS, MaterialMapColor.ah).d(1.0F).a(SoundEffectType.J)));
/* 797 */   public static final Block WARPED_ROOTS = a("warped_roots", new BlockRoots(BlockBase.Info.a(Material.h, MaterialMapColor.y).a().c().a(SoundEffectType.C)));
/* 798 */   public static final Block NETHER_SPROUTS = a("nether_sprouts", new BlockNetherSprouts(BlockBase.Info.a(Material.h, MaterialMapColor.y).a().c().a(SoundEffectType.M)));
/* 799 */   public static final Block CRIMSON_STEM = a("crimson_stem", a(MaterialMapColor.ac));
/* 800 */   public static final Block STRIPPED_CRIMSON_STEM = a("stripped_crimson_stem", a(MaterialMapColor.ac));
/* 801 */   public static final Block CRIMSON_HYPHAE = a("crimson_hyphae", new BlockRotatable(BlockBase.Info.a(Material.NETHER_WOOD, MaterialMapColor.ad).d(2.0F).a(SoundEffectType.z)));
/* 802 */   public static final Block STRIPPED_CRIMSON_HYPHAE = a("stripped_crimson_hyphae", new BlockRotatable(BlockBase.Info.a(Material.NETHER_WOOD, MaterialMapColor.ad).d(2.0F).a(SoundEffectType.z)));
/* 803 */   public static final Block CRIMSON_NYLIUM = a("crimson_nylium", new BlockNylium(BlockBase.Info.a(Material.STONE, MaterialMapColor.ab).h().d(0.4F).a(SoundEffectType.A).d()));
/* 804 */   public static final Block CRIMSON_FUNGUS = a("crimson_fungus", new BlockFungi(BlockBase.Info.a(Material.PLANT, MaterialMapColor.K).c().a().a(SoundEffectType.B), () -> BiomeDecoratorGroups.CRIMSON_FUNGI_PLANTED));
/* 805 */   public static final Block SHROOMLIGHT = a("shroomlight", new Block(BlockBase.Info.a(Material.GRASS, MaterialMapColor.D).d(1.0F).a(SoundEffectType.D).a(var0 -> 15)));
/* 806 */   public static final Block WEEPING_VINES = a("weeping_vines", new BlockWeepingVines(BlockBase.Info.a(Material.PLANT, MaterialMapColor.K).d().a().c().a(SoundEffectType.E)));
/* 807 */   public static final Block WEEPING_VINES_PLANT = a("weeping_vines_plant", new BlockWeepingVinesPlant(BlockBase.Info.a(Material.PLANT, MaterialMapColor.K).a().c().a(SoundEffectType.E)));
/* 808 */   public static final Block TWISTING_VINES = a("twisting_vines", new BlockTwistingVines(BlockBase.Info.a(Material.PLANT, MaterialMapColor.y).d().a().c().a(SoundEffectType.E)));
/* 809 */   public static final Block TWISTING_VINES_PLANT = a("twisting_vines_plant", new BlockTwistingVinesPlant(BlockBase.Info.a(Material.PLANT, MaterialMapColor.y).a().c().a(SoundEffectType.E)));
/* 810 */   public static final Block CRIMSON_ROOTS = a("crimson_roots", new BlockRoots(BlockBase.Info.a(Material.h, MaterialMapColor.K).a().c().a(SoundEffectType.C)));
/* 811 */   public static final Block CRIMSON_PLANKS = a("crimson_planks", new Block(BlockBase.Info.a(Material.NETHER_WOOD, MaterialMapColor.ac).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 812 */   public static final Block WARPED_PLANKS = a("warped_planks", new Block(BlockBase.Info.a(Material.NETHER_WOOD, MaterialMapColor.af).a(2.0F, 3.0F).a(SoundEffectType.a)));
/*     */   
/* 814 */   public static final Block CRIMSON_SLAB = a("crimson_slab", new BlockStepAbstract(BlockBase.Info.a(Material.NETHER_WOOD, CRIMSON_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 815 */   public static final Block WARPED_SLAB = a("warped_slab", new BlockStepAbstract(BlockBase.Info.a(Material.NETHER_WOOD, WARPED_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 816 */   public static final Block CRIMSON_PRESSURE_PLATE = a("crimson_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.EVERYTHING, BlockBase.Info.a(Material.NETHER_WOOD, CRIMSON_PLANKS.s()).a().d(0.5F).a(SoundEffectType.a)));
/* 817 */   public static final Block WARPED_PRESSURE_PLATE = a("warped_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.EVERYTHING, BlockBase.Info.a(Material.NETHER_WOOD, WARPED_PLANKS.s()).a().d(0.5F).a(SoundEffectType.a)));
/* 818 */   public static final Block CRIMSON_FENCE = a("crimson_fence", new BlockFence(BlockBase.Info.a(Material.NETHER_WOOD, CRIMSON_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 819 */   public static final Block WARPED_FENCE = a("warped_fence", new BlockFence(BlockBase.Info.a(Material.NETHER_WOOD, WARPED_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 820 */   public static final Block CRIMSON_TRAPDOOR = a("crimson_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.NETHER_WOOD, CRIMSON_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b().a(Blocks::a)));
/* 821 */   public static final Block WARPED_TRAPDOOR = a("warped_trapdoor", new BlockTrapdoor(BlockBase.Info.a(Material.NETHER_WOOD, WARPED_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b().a(Blocks::a)));
/* 822 */   public static final Block CRIMSON_FENCE_GATE = a("crimson_fence_gate", new BlockFenceGate(BlockBase.Info.a(Material.NETHER_WOOD, CRIMSON_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 823 */   public static final Block WARPED_FENCE_GATE = a("warped_fence_gate", new BlockFenceGate(BlockBase.Info.a(Material.NETHER_WOOD, WARPED_PLANKS.s()).a(2.0F, 3.0F).a(SoundEffectType.a)));
/* 824 */   public static final Block CRIMSON_STAIRS = a("crimson_stairs", new BlockStairs(CRIMSON_PLANKS.getBlockData(), BlockBase.Info.a(CRIMSON_PLANKS)));
/* 825 */   public static final Block WARPED_STAIRS = a("warped_stairs", new BlockStairs(WARPED_PLANKS.getBlockData(), BlockBase.Info.a(WARPED_PLANKS)));
/* 826 */   public static final Block CRIMSON_BUTTON = a("crimson_button", new BlockWoodButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 827 */   public static final Block WARPED_BUTTON = a("warped_button", new BlockWoodButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F).a(SoundEffectType.a)));
/* 828 */   public static final Block CRIMSON_DOOR = a("crimson_door", new BlockDoor(BlockBase.Info.a(Material.NETHER_WOOD, CRIMSON_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b()));
/* 829 */   public static final Block WARPED_DOOR = a("warped_door", new BlockDoor(BlockBase.Info.a(Material.NETHER_WOOD, WARPED_PLANKS.s()).d(3.0F).a(SoundEffectType.a).b()));
/* 830 */   public static final Block CRIMSON_SIGN = a("crimson_sign", new BlockFloorSign(BlockBase.Info.a(Material.NETHER_WOOD, CRIMSON_PLANKS.s()).a().d(1.0F).a(SoundEffectType.a), BlockPropertyWood.g));
/* 831 */   public static final Block WARPED_SIGN = a("warped_sign", new BlockFloorSign(BlockBase.Info.a(Material.NETHER_WOOD, WARPED_PLANKS.s()).a().d(1.0F).a(SoundEffectType.a), BlockPropertyWood.h));
/* 832 */   public static final Block CRIMSON_WALL_SIGN = a("crimson_wall_sign", new BlockWallSign(BlockBase.Info.a(Material.NETHER_WOOD, CRIMSON_PLANKS.s()).a().d(1.0F).a(SoundEffectType.a).a(CRIMSON_SIGN), BlockPropertyWood.g));
/* 833 */   public static final Block WARPED_WALL_SIGN = a("warped_wall_sign", new BlockWallSign(BlockBase.Info.a(Material.NETHER_WOOD, WARPED_PLANKS.s()).a().d(1.0F).a(SoundEffectType.a).a(WARPED_SIGN), BlockPropertyWood.h));
/*     */   
/* 835 */   public static final Block STRUCTURE_BLOCK = a("structure_block", new BlockStructure(BlockBase.Info.a(Material.ORE, MaterialMapColor.x).h().a(-1.0F, 3600000.0F).f()));
/* 836 */   public static final Block JIGSAW = a("jigsaw", new BlockJigsaw(BlockBase.Info.a(Material.ORE, MaterialMapColor.x).h().a(-1.0F, 3600000.0F).f()));
/* 837 */   public static final Block COMPOSTER = a("composter", new BlockComposter(BlockBase.Info.a(Material.WOOD).d(0.6F).a(SoundEffectType.a)));
/* 838 */   public static final Block TARGET = a("target", new BlockTarget(BlockBase.Info.a(Material.GRASS, MaterialMapColor.p).d(0.5F).a(SoundEffectType.c)));
/*     */   
/* 840 */   public static final Block BEE_NEST = a("bee_nest", new BlockBeehive(BlockBase.Info.a(Material.WOOD, MaterialMapColor.t).d(0.3F).a(SoundEffectType.a)));
/* 841 */   public static final Block BEEHIVE = a("beehive", new BlockBeehive(BlockBase.Info.a(Material.WOOD).d(0.6F).a(SoundEffectType.a)));
/* 842 */   public static final Block HONEY_BLOCK = a("honey_block", new BlockHoney(BlockBase.Info.a(Material.CLAY, MaterialMapColor.q).b(0.4F).c(0.5F).b().a(SoundEffectType.n)));
/* 843 */   public static final Block HONEYCOMB_BLOCK = a("honeycomb_block", new Block(BlockBase.Info.a(Material.CLAY, MaterialMapColor.q).d(0.6F).a(SoundEffectType.p)));
/*     */   
/* 845 */   public static final Block NETHERITE_BLOCK = a("netherite_block", new Block(BlockBase.Info.a(Material.ORE, MaterialMapColor.E).h().a(50.0F, 1200.0F).a(SoundEffectType.P)));
/* 846 */   public static final Block ANCIENT_DEBRIS = a("ancient_debris", new Block(BlockBase.Info.a(Material.ORE, MaterialMapColor.E).h().a(30.0F, 1200.0F).a(SoundEffectType.Q)));
/* 847 */   public static final Block CRYING_OBSIDIAN = a("crying_obsidian", new BlockCryingObsidian(BlockBase.Info.a(Material.STONE, MaterialMapColor.E).h().a(50.0F, 1200.0F).a(var0 -> 10))); public static final Block RESPAWN_ANCHOR; static {
/* 848 */     RESPAWN_ANCHOR = a("respawn_anchor", new BlockRespawnAnchor(BlockBase.Info.a(Material.STONE, MaterialMapColor.E).h().a(50.0F, 1200.0F).a(var0 -> BlockRespawnAnchor.a(var0, 15))));
/*     */   }
/* 850 */   public static final Block POTTED_CRIMSON_FUNGUS = a("potted_crimson_fungus", new BlockFlowerPot(CRIMSON_FUNGUS, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 851 */   public static final Block POTTED_WARPED_FUNGUS = a("potted_warped_fungus", new BlockFlowerPot(WARPED_FUNGUS, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 852 */   public static final Block POTTED_CRIMSON_ROOTS = a("potted_crimson_roots", new BlockFlowerPot(CRIMSON_ROOTS, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/* 853 */   public static final Block POTTED_WARPED_ROOTS = a("potted_warped_roots", new BlockFlowerPot(WARPED_ROOTS, BlockBase.Info.a(Material.ORIENTABLE).c().b()));
/*     */   
/* 855 */   public static final Block LODESTONE = a("lodestone", new Block(BlockBase.Info.a(Material.HEAVY).h().d(3.5F).a(SoundEffectType.R)));
/* 856 */   public static final Block BLACKSTONE = a("blackstone", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.E).h().a(1.5F, 6.0F)));
/* 857 */   public static final Block BLACKSTONE_STAIRS = a("blackstone_stairs", new BlockStairs(BLACKSTONE.getBlockData(), BlockBase.Info.a(BLACKSTONE)));
/* 858 */   public static final Block BLACKSTONE_WALL = a("blackstone_wall", new BlockCobbleWall(BlockBase.Info.a(BLACKSTONE)));
/* 859 */   public static final Block BLACKSTONE_SLAB = a("blackstone_slab", new BlockStepAbstract(BlockBase.Info.a(BLACKSTONE).a(2.0F, 6.0F)));
/* 860 */   public static final Block POLISHED_BLACKSTONE = a("polished_blackstone", new Block(BlockBase.Info.a(BLACKSTONE).a(2.0F, 6.0F)));
/* 861 */   public static final Block POLISHED_BLACKSTONE_BRICKS = a("polished_blackstone_bricks", new Block(BlockBase.Info.a(POLISHED_BLACKSTONE).a(1.5F, 6.0F)));
/* 862 */   public static final Block CRACKED_POLISHED_BLACKSTONE_BRICKS = a("cracked_polished_blackstone_bricks", new Block(BlockBase.Info.a(POLISHED_BLACKSTONE_BRICKS)));
/* 863 */   public static final Block CHISELED_POLISHED_BLACKSTONE = a("chiseled_polished_blackstone", new Block(BlockBase.Info.a(POLISHED_BLACKSTONE).a(1.5F, 6.0F)));
/* 864 */   public static final Block POLISHED_BLACKSTONE_BRICK_SLAB = a("polished_blackstone_brick_slab", new BlockStepAbstract(BlockBase.Info.a(POLISHED_BLACKSTONE_BRICKS).a(2.0F, 6.0F)));
/* 865 */   public static final Block POLISHED_BLACKSTONE_BRICK_STAIRS = a("polished_blackstone_brick_stairs", new BlockStairs(POLISHED_BLACKSTONE_BRICKS.getBlockData(), BlockBase.Info.a(POLISHED_BLACKSTONE_BRICKS)));
/* 866 */   public static final Block POLISHED_BLACKSTONE_BRICK_WALL = a("polished_blackstone_brick_wall", new BlockCobbleWall(BlockBase.Info.a(POLISHED_BLACKSTONE_BRICKS)));
/* 867 */   public static final Block GILDED_BLACKSTONE = a("gilded_blackstone", new Block(BlockBase.Info.a(BLACKSTONE).a(SoundEffectType.U)));
/* 868 */   public static final Block POLISHED_BLACKSTONE_STAIRS = a("polished_blackstone_stairs", new BlockStairs(POLISHED_BLACKSTONE.getBlockData(), BlockBase.Info.a(POLISHED_BLACKSTONE)));
/* 869 */   public static final Block POLISHED_BLACKSTONE_SLAB = a("polished_blackstone_slab", new BlockStepAbstract(BlockBase.Info.a(POLISHED_BLACKSTONE)));
/* 870 */   public static final Block POLISHED_BLACKSTONE_PRESSURE_PLATE = a("polished_blackstone_pressure_plate", new BlockPressurePlateBinary(BlockPressurePlateBinary.EnumMobType.MOBS, BlockBase.Info.a(Material.STONE, MaterialMapColor.E).h().a().d(0.5F)));
/* 871 */   public static final Block POLISHED_BLACKSTONE_BUTTON = a("polished_blackstone_button", new BlockStoneButton(BlockBase.Info.a(Material.ORIENTABLE).a().d(0.5F)));
/* 872 */   public static final Block POLISHED_BLACKSTONE_WALL = a("polished_blackstone_wall", new BlockCobbleWall(BlockBase.Info.a(POLISHED_BLACKSTONE)));
/*     */   
/* 874 */   public static final Block CHISELED_NETHER_BRICKS = a("chiseled_nether_bricks", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(2.0F, 6.0F).a(SoundEffectType.L)));
/* 875 */   public static final Block CRACKED_NETHER_BRICKS = a("cracked_nether_bricks", new Block(BlockBase.Info.a(Material.STONE, MaterialMapColor.K).h().a(2.0F, 6.0F).a(SoundEffectType.L)));
/* 876 */   public static final Block QUARTZ_BRICKS = a("quartz_bricks", new Block(BlockBase.Info.a(QUARTZ_BLOCK)));
/*     */   
/*     */   private static Block a(String var0, Block var1) {
/* 879 */     return IRegistry.<Block>a(IRegistry.BLOCK, var0, var1);
/*     */   }
/*     */   
/*     */   static {
/* 883 */     for (Block var1 : IRegistry.BLOCK) {
/* 884 */       for (UnmodifiableIterator<IBlockData> unmodifiableIterator = var1.getStates().a().iterator(); unmodifiableIterator.hasNext(); ) { IBlockData var3 = unmodifiableIterator.next();
/* 885 */         Block.REGISTRY_ID.b(var3); }
/*     */       
/* 887 */       var1.r();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void a() {
/* 892 */     Block.REGISTRY_ID.forEach(BlockBase.BlockData::a);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */