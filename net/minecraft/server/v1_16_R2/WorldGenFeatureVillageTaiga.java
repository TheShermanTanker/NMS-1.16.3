/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class WorldGenFeatureVillageTaiga
/*     */ {
/*  10 */   public static final WorldGenFeatureDefinedStructurePoolTemplate a = WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/town_centers"), new MinecraftKey("empty"), 
/*     */ 
/*     */         
/*  13 */         (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  14 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/town_centers/taiga_meeting_point_1", ProcessorLists.g), Integer.valueOf(49)), 
/*  15 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/town_centers/taiga_meeting_point_2", ProcessorLists.g), Integer.valueOf(49)), 
/*  16 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/town_centers/taiga_meeting_point_1", ProcessorLists.e), Integer.valueOf(1)), 
/*  17 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/town_centers/taiga_meeting_point_2", ProcessorLists.e), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  23 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/streets"), new MinecraftKey("village/taiga/terminators"), 
/*     */ 
/*     */           
/*  26 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  27 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/corner_01", ProcessorLists.l), Integer.valueOf(2)), 
/*  28 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/corner_02", ProcessorLists.l), Integer.valueOf(2)), 
/*  29 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/corner_03", ProcessorLists.l), Integer.valueOf(2)), 
/*  30 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/straight_01", ProcessorLists.l), Integer.valueOf(4)), 
/*  31 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/straight_02", ProcessorLists.l), Integer.valueOf(4)), 
/*  32 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/straight_03", ProcessorLists.l), Integer.valueOf(4)), 
/*  33 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/straight_04", ProcessorLists.l), Integer.valueOf(7)), 
/*  34 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/straight_05", ProcessorLists.l), Integer.valueOf(7)), 
/*  35 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/straight_06", ProcessorLists.l), Integer.valueOf(4)), 
/*  36 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/crossroad_01", ProcessorLists.l), Integer.valueOf(1)), 
/*  37 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/crossroad_02", ProcessorLists.l), Integer.valueOf(1)), 
/*  38 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/crossroad_03", ProcessorLists.l), Integer.valueOf(2)), (Object[])new Pair[] {
/*  39 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/crossroad_04", ProcessorLists.l), Integer.valueOf(2)), 
/*  40 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/crossroad_05", ProcessorLists.l), Integer.valueOf(2)), 
/*  41 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/crossroad_06", ProcessorLists.l), Integer.valueOf(2)), 
/*  42 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/streets/turn_01", ProcessorLists.l), Integer.valueOf(3))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */     
/*  47 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/zombie/streets"), new MinecraftKey("village/taiga/terminators"), 
/*     */ 
/*     */           
/*  50 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  51 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/corner_01", ProcessorLists.l), Integer.valueOf(2)), 
/*  52 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/corner_02", ProcessorLists.l), Integer.valueOf(2)), 
/*  53 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/corner_03", ProcessorLists.l), Integer.valueOf(2)), 
/*  54 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/straight_01", ProcessorLists.l), Integer.valueOf(4)), 
/*  55 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/straight_02", ProcessorLists.l), Integer.valueOf(4)), 
/*  56 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/straight_03", ProcessorLists.l), Integer.valueOf(4)), 
/*  57 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/straight_04", ProcessorLists.l), Integer.valueOf(7)), 
/*  58 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/straight_05", ProcessorLists.l), Integer.valueOf(7)), 
/*  59 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/straight_06", ProcessorLists.l), Integer.valueOf(4)), 
/*  60 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/crossroad_01", ProcessorLists.l), Integer.valueOf(1)), 
/*  61 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/crossroad_02", ProcessorLists.l), Integer.valueOf(1)), 
/*  62 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/crossroad_03", ProcessorLists.l), Integer.valueOf(2)), (Object[])new Pair[] {
/*  63 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/crossroad_04", ProcessorLists.l), Integer.valueOf(2)), 
/*  64 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/crossroad_05", ProcessorLists.l), Integer.valueOf(2)), 
/*  65 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/crossroad_06", ProcessorLists.l), Integer.valueOf(2)), 
/*  66 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/streets/turn_01", ProcessorLists.l), Integer.valueOf(3))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */     
/*  71 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/houses"), new MinecraftKey("village/taiga/terminators"), 
/*     */ 
/*     */           
/*  74 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  75 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_small_house_1", ProcessorLists.g), Integer.valueOf(4)), 
/*  76 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_small_house_2", ProcessorLists.g), Integer.valueOf(4)), 
/*  77 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_small_house_3", ProcessorLists.g), Integer.valueOf(4)), 
/*  78 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_small_house_4", ProcessorLists.g), Integer.valueOf(4)), 
/*  79 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_small_house_5", ProcessorLists.g), Integer.valueOf(4)), 
/*  80 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_medium_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  81 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_medium_house_2", ProcessorLists.g), Integer.valueOf(2)), 
/*  82 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_medium_house_3", ProcessorLists.g), Integer.valueOf(2)), 
/*  83 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_medium_house_4", ProcessorLists.g), Integer.valueOf(2)), 
/*  84 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_butcher_shop_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  85 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_tool_smith_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  86 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_fletcher_house_1", ProcessorLists.g), Integer.valueOf(2)), (Object[])new Pair[] { 
/*  87 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_shepherds_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  88 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_armorer_house_1", ProcessorLists.g), Integer.valueOf(1)), 
/*  89 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_armorer_2", ProcessorLists.g), Integer.valueOf(1)), 
/*  90 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_fisher_cottage_1", ProcessorLists.g), Integer.valueOf(3)), 
/*  91 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_tannery_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  92 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_cartographer_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  93 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_library_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  94 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_masons_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  95 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_weaponsmith_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  96 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_weaponsmith_2", ProcessorLists.g), Integer.valueOf(2)), 
/*  97 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_temple_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  98 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_large_farm_1", ProcessorLists.p), Integer.valueOf(6)), 
/*  99 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_large_farm_2", ProcessorLists.p), Integer.valueOf(6)), 
/* 100 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_small_farm_1", ProcessorLists.g), Integer.valueOf(1)), 
/* 101 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_animal_pen_1", ProcessorLists.g), Integer.valueOf(2)), 
/* 102 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(6)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/zombie/houses"), new MinecraftKey("village/taiga/terminators"), 
/*     */ 
/*     */           
/* 110 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 111 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_small_house_1", ProcessorLists.e), Integer.valueOf(4)), 
/* 112 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_small_house_2", ProcessorLists.e), Integer.valueOf(4)), 
/* 113 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_small_house_3", ProcessorLists.e), Integer.valueOf(4)), 
/* 114 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_small_house_4", ProcessorLists.e), Integer.valueOf(4)), 
/* 115 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_small_house_5", ProcessorLists.e), Integer.valueOf(4)), 
/* 116 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_medium_house_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 117 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_medium_house_2", ProcessorLists.e), Integer.valueOf(2)), 
/* 118 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_medium_house_3", ProcessorLists.e), Integer.valueOf(2)), 
/* 119 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_medium_house_4", ProcessorLists.e), Integer.valueOf(2)), 
/* 120 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_butcher_shop_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 121 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_tool_smith_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 122 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_fletcher_house_1", ProcessorLists.e), Integer.valueOf(2)), (Object[])new Pair[] { 
/* 123 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_shepherds_house_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 124 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_armorer_house_1", ProcessorLists.e), Integer.valueOf(1)), 
/* 125 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_fisher_cottage_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 126 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_tannery_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 127 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_cartographer_house_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 128 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_library_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 129 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_masons_house_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 130 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_weaponsmith_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 131 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_weaponsmith_2", ProcessorLists.e), Integer.valueOf(2)), 
/* 132 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_temple_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 133 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_large_farm_1", ProcessorLists.e), Integer.valueOf(6)), 
/* 134 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/houses/taiga_large_farm_2", ProcessorLists.e), Integer.valueOf(6)), 
/* 135 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_small_farm_1", ProcessorLists.e), Integer.valueOf(1)), 
/* 136 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/houses/taiga_animal_pen_1", ProcessorLists.e), Integer.valueOf(2)), 
/* 137 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(6)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/terminators"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 145 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 146 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_01", ProcessorLists.l), Integer.valueOf(1)), 
/* 147 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_02", ProcessorLists.l), Integer.valueOf(1)), 
/* 148 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_03", ProcessorLists.l), Integer.valueOf(1)), 
/* 149 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_04", ProcessorLists.l), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 157 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 158 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_lamp_post_1"), Integer.valueOf(10)), 
/* 159 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_1"), Integer.valueOf(4)), 
/* 160 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_2"), Integer.valueOf(1)), 
/* 161 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_3"), Integer.valueOf(1)), 
/* 162 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_4"), Integer.valueOf(1)), 
/* 163 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_5"), Integer.valueOf(2)), 
/* 164 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_6"), Integer.valueOf(1)), 
/* 165 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.SPRUCE), Integer.valueOf(4)), 
/* 166 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PINE), Integer.valueOf(4)), 
/* 167 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_PUMPKIN), Integer.valueOf(2)), 
/* 168 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PATCH_TAIGA_GRASS), Integer.valueOf(4)), 
/* 169 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PATCH_BERRY_BUSH), Integer.valueOf(1)), (Object[])new Pair[] {
/* 170 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(4))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */     
/* 175 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/zombie/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 178 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 179 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_1"), Integer.valueOf(4)), 
/* 180 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_2"), Integer.valueOf(1)), 
/* 181 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_3"), Integer.valueOf(1)), 
/* 182 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/taiga_decoration_4"), Integer.valueOf(1)), 
/* 183 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.SPRUCE), Integer.valueOf(4)), 
/* 184 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PINE), Integer.valueOf(4)), 
/* 185 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_PUMPKIN), Integer.valueOf(2)), 
/* 186 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PATCH_TAIGA_GRASS), Integer.valueOf(4)), 
/* 187 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PATCH_BERRY_BUSH), Integer.valueOf(1)), 
/* 188 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(4))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 196 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 197 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/villagers/nitwit"), Integer.valueOf(1)), 
/* 198 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/villagers/baby"), Integer.valueOf(1)), 
/* 199 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/taiga/zombie/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 207 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 208 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/villagers/nitwit"), Integer.valueOf(1)), 
/* 209 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/taiga/zombie/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */   }
/*     */   
/*     */   public static void a() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureVillageTaiga.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */