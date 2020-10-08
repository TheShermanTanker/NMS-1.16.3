/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockProperties
/*     */ {
/*  12 */   public static final BlockStateBoolean a = BlockStateBoolean.of("attached");
/*  13 */   public static final BlockStateBoolean b = BlockStateBoolean.of("bottom");
/*  14 */   public static final BlockStateBoolean c = BlockStateBoolean.of("conditional");
/*  15 */   public static final BlockStateBoolean d = BlockStateBoolean.of("disarmed");
/*  16 */   public static final BlockStateBoolean e = BlockStateBoolean.of("drag");
/*  17 */   public static final BlockStateBoolean f = BlockStateBoolean.of("enabled");
/*  18 */   public static final BlockStateBoolean g = BlockStateBoolean.of("extended");
/*  19 */   public static final BlockStateBoolean h = BlockStateBoolean.of("eye");
/*  20 */   public static final BlockStateBoolean i = BlockStateBoolean.of("falling");
/*  21 */   public static final BlockStateBoolean j = BlockStateBoolean.of("hanging");
/*  22 */   public static final BlockStateBoolean k = BlockStateBoolean.of("has_bottle_0");
/*  23 */   public static final BlockStateBoolean l = BlockStateBoolean.of("has_bottle_1");
/*  24 */   public static final BlockStateBoolean m = BlockStateBoolean.of("has_bottle_2");
/*  25 */   public static final BlockStateBoolean n = BlockStateBoolean.of("has_record");
/*  26 */   public static final BlockStateBoolean o = BlockStateBoolean.of("has_book");
/*  27 */   public static final BlockStateBoolean p = BlockStateBoolean.of("inverted");
/*  28 */   public static final BlockStateBoolean q = BlockStateBoolean.of("in_wall");
/*  29 */   public static final BlockStateBoolean r = BlockStateBoolean.of("lit");
/*  30 */   public static final BlockStateBoolean s = BlockStateBoolean.of("locked");
/*  31 */   public static final BlockStateBoolean t = BlockStateBoolean.of("occupied");
/*  32 */   public static final BlockStateBoolean u = BlockStateBoolean.of("open");
/*  33 */   public static final BlockStateBoolean v = BlockStateBoolean.of("persistent");
/*  34 */   public static final BlockStateBoolean w = BlockStateBoolean.of("powered");
/*  35 */   public static final BlockStateBoolean x = BlockStateBoolean.of("short");
/*  36 */   public static final BlockStateBoolean y = BlockStateBoolean.of("signal_fire");
/*  37 */   public static final BlockStateBoolean z = BlockStateBoolean.of("snowy");
/*  38 */   public static final BlockStateBoolean A = BlockStateBoolean.of("triggered");
/*  39 */   public static final BlockStateBoolean B = BlockStateBoolean.of("unstable");
/*  40 */   public static final BlockStateBoolean C = BlockStateBoolean.of("waterlogged");
/*  41 */   public static final BlockStateBoolean D = BlockStateBoolean.of("vine_end");
/*     */   
/*  43 */   public static final BlockStateEnum<EnumDirection.EnumAxis> E = BlockStateEnum.of("axis", EnumDirection.EnumAxis.class, new EnumDirection.EnumAxis[] { EnumDirection.EnumAxis.X, EnumDirection.EnumAxis.Z });
/*  44 */   public static final BlockStateEnum<EnumDirection.EnumAxis> F = BlockStateEnum.of("axis", EnumDirection.EnumAxis.class);
/*     */   
/*  46 */   public static final BlockStateBoolean G = BlockStateBoolean.of("up");
/*  47 */   public static final BlockStateBoolean H = BlockStateBoolean.of("down");
/*  48 */   public static final BlockStateBoolean I = BlockStateBoolean.of("north");
/*  49 */   public static final BlockStateBoolean J = BlockStateBoolean.of("east");
/*  50 */   public static final BlockStateBoolean K = BlockStateBoolean.of("south");
/*  51 */   public static final BlockStateBoolean L = BlockStateBoolean.of("west");
/*     */   public static final BlockStateDirection N;
/*  53 */   public static final BlockStateDirection M = BlockStateDirection.a("facing", new EnumDirection[] { EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST, EnumDirection.UP, EnumDirection.DOWN }); static {
/*  54 */     N = BlockStateDirection.a("facing", var0 -> (var0 != EnumDirection.UP));
/*  55 */   } public static final BlockStateDirection O = BlockStateDirection.a("facing", EnumDirection.EnumDirectionLimit.HORIZONTAL);
/*     */   
/*  57 */   public static final BlockStateEnum<BlockPropertyJigsawOrientation> P = BlockStateEnum.of("orientation", BlockPropertyJigsawOrientation.class);
/*     */   
/*  59 */   public static final BlockStateEnum<BlockPropertyAttachPosition> Q = BlockStateEnum.of("face", BlockPropertyAttachPosition.class);
/*  60 */   public static final BlockStateEnum<BlockPropertyBellAttach> R = BlockStateEnum.of("attachment", BlockPropertyBellAttach.class);
/*     */   
/*  62 */   public static final BlockStateEnum<BlockPropertyWallHeight> S = BlockStateEnum.of("east", BlockPropertyWallHeight.class);
/*  63 */   public static final BlockStateEnum<BlockPropertyWallHeight> T = BlockStateEnum.of("north", BlockPropertyWallHeight.class);
/*  64 */   public static final BlockStateEnum<BlockPropertyWallHeight> U = BlockStateEnum.of("south", BlockPropertyWallHeight.class);
/*  65 */   public static final BlockStateEnum<BlockPropertyWallHeight> V = BlockStateEnum.of("west", BlockPropertyWallHeight.class);
/*     */   
/*  67 */   public static final BlockStateEnum<BlockPropertyRedstoneSide> W = BlockStateEnum.of("east", BlockPropertyRedstoneSide.class);
/*  68 */   public static final BlockStateEnum<BlockPropertyRedstoneSide> X = BlockStateEnum.of("north", BlockPropertyRedstoneSide.class);
/*  69 */   public static final BlockStateEnum<BlockPropertyRedstoneSide> Y = BlockStateEnum.of("south", BlockPropertyRedstoneSide.class);
/*  70 */   public static final BlockStateEnum<BlockPropertyRedstoneSide> Z = BlockStateEnum.of("west", BlockPropertyRedstoneSide.class);
/*     */   
/*  72 */   public static final BlockStateEnum<BlockPropertyDoubleBlockHalf> aa = BlockStateEnum.of("half", BlockPropertyDoubleBlockHalf.class);
/*  73 */   public static final BlockStateEnum<BlockPropertyHalf> ab = BlockStateEnum.of("half", BlockPropertyHalf.class);
/*     */   
/*  75 */   public static final BlockStateEnum<BlockPropertyTrackPosition> ac = BlockStateEnum.of("shape", BlockPropertyTrackPosition.class); static {
/*  76 */     ad = BlockStateEnum.a("shape", BlockPropertyTrackPosition.class, var0 -> 
/*  77 */         (var0 != BlockPropertyTrackPosition.NORTH_EAST && var0 != BlockPropertyTrackPosition.NORTH_WEST && var0 != BlockPropertyTrackPosition.SOUTH_EAST && var0 != BlockPropertyTrackPosition.SOUTH_WEST));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final BlockStateEnum<BlockPropertyTrackPosition> ad;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static final BlockStateInteger ae = BlockStateInteger.of("age", 0, 1);
/*  91 */   public static final BlockStateInteger af = BlockStateInteger.of("age", 0, 2);
/*  92 */   public static final BlockStateInteger ag = BlockStateInteger.of("age", 0, 3);
/*  93 */   public static final BlockStateInteger ah = BlockStateInteger.of("age", 0, 5);
/*  94 */   public static final BlockStateInteger ai = BlockStateInteger.of("age", 0, 7);
/*  95 */   public static final BlockStateInteger aj = BlockStateInteger.of("age", 0, 15);
/*  96 */   public static final BlockStateInteger ak = BlockStateInteger.of("age", 0, 25);
/*     */   
/*  98 */   public static final BlockStateInteger al = BlockStateInteger.of("bites", 0, 6);
/*  99 */   public static final BlockStateInteger am = BlockStateInteger.of("delay", 1, 4);
/*     */   
/* 101 */   public static final BlockStateInteger an = BlockStateInteger.of("distance", 1, 7);
/* 102 */   public static final BlockStateInteger ao = BlockStateInteger.of("eggs", 1, 4);
/* 103 */   public static final BlockStateInteger ap = BlockStateInteger.of("hatch", 0, 2);
/* 104 */   public static final BlockStateInteger aq = BlockStateInteger.of("layers", 1, 8);
/*     */ 
/*     */ 
/*     */   
/* 108 */   public static final BlockStateInteger ar = BlockStateInteger.of("level", 0, 3);
/* 109 */   public static final BlockStateInteger as = BlockStateInteger.of("level", 0, 8);
/* 110 */   public static final BlockStateInteger at = BlockStateInteger.of("level", 1, 8);
/* 111 */   public static final BlockStateInteger au = BlockStateInteger.of("honey_level", 0, 5);
/* 112 */   public static final BlockStateInteger av = BlockStateInteger.of("level", 0, 15);
/* 113 */   public static final BlockStateInteger aw = BlockStateInteger.of("moisture", 0, 7);
/* 114 */   public static final BlockStateInteger ax = BlockStateInteger.of("note", 0, 24);
/* 115 */   public static final BlockStateInteger ay = BlockStateInteger.of("pickles", 1, 4);
/* 116 */   public static final BlockStateInteger az = BlockStateInteger.of("power", 0, 15);
/* 117 */   public static final BlockStateInteger aA = BlockStateInteger.of("stage", 0, 1);
/*     */   
/* 119 */   public static final BlockStateInteger aB = BlockStateInteger.of("distance", 0, 7);
/*     */ 
/*     */   
/* 122 */   public static final BlockStateInteger aC = BlockStateInteger.of("charges", 0, 4);
/*     */ 
/*     */   
/* 125 */   public static final BlockStateInteger aD = BlockStateInteger.of("rotation", 0, 15);
/*     */   
/* 127 */   public static final BlockStateEnum<BlockPropertyBedPart> aE = BlockStateEnum.of("part", BlockPropertyBedPart.class);
/* 128 */   public static final BlockStateEnum<BlockPropertyChestType> aF = BlockStateEnum.of("type", BlockPropertyChestType.class);
/* 129 */   public static final BlockStateEnum<BlockPropertyComparatorMode> aG = BlockStateEnum.of("mode", BlockPropertyComparatorMode.class);
/* 130 */   public static final BlockStateEnum<BlockPropertyDoorHinge> aH = BlockStateEnum.of("hinge", BlockPropertyDoorHinge.class);
/* 131 */   public static final BlockStateEnum<BlockPropertyInstrument> aI = BlockStateEnum.of("instrument", BlockPropertyInstrument.class);
/* 132 */   public static final BlockStateEnum<BlockPropertyPistonType> aJ = BlockStateEnum.of("type", BlockPropertyPistonType.class);
/* 133 */   public static final BlockStateEnum<BlockPropertySlabType> aK = BlockStateEnum.of("type", BlockPropertySlabType.class);
/* 134 */   public static final BlockStateEnum<BlockPropertyStairsShape> aL = BlockStateEnum.of("shape", BlockPropertyStairsShape.class);
/* 135 */   public static final BlockStateEnum<BlockPropertyStructureMode> aM = BlockStateEnum.of("mode", BlockPropertyStructureMode.class);
/* 136 */   public static final BlockStateEnum<BlockPropertyBambooSize> aN = BlockStateEnum.of("leaves", BlockPropertyBambooSize.class);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */