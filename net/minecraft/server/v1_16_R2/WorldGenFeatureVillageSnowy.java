/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class WorldGenFeatureVillageSnowy
/*     */ {
/*  10 */   public static final WorldGenFeatureDefinedStructurePoolTemplate a = WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/town_centers"), new MinecraftKey("empty"), 
/*     */ 
/*     */         
/*  13 */         (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  14 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/town_centers/snowy_meeting_point_1"), Integer.valueOf(100)), 
/*  15 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/town_centers/snowy_meeting_point_2"), Integer.valueOf(50)), 
/*  16 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/town_centers/snowy_meeting_point_3"), Integer.valueOf(150)), 
/*  17 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/town_centers/snowy_meeting_point_1"), Integer.valueOf(2)), 
/*  18 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/town_centers/snowy_meeting_point_2"), Integer.valueOf(1)), 
/*  19 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/town_centers/snowy_meeting_point_3"), Integer.valueOf(3))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  25 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/streets"), new MinecraftKey("village/snowy/terminators"), 
/*     */ 
/*     */           
/*  28 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  29 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/corner_01", ProcessorLists.l), Integer.valueOf(2)), 
/*  30 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/corner_02", ProcessorLists.l), Integer.valueOf(2)), 
/*  31 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/corner_03", ProcessorLists.l), Integer.valueOf(2)), 
/*  32 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/square_01", ProcessorLists.l), Integer.valueOf(2)), 
/*  33 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/straight_01", ProcessorLists.l), Integer.valueOf(4)), 
/*  34 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/straight_02", ProcessorLists.l), Integer.valueOf(4)), 
/*  35 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/straight_03", ProcessorLists.l), Integer.valueOf(4)), 
/*  36 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/straight_04", ProcessorLists.l), Integer.valueOf(7)), 
/*  37 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/straight_06", ProcessorLists.l), Integer.valueOf(4)), 
/*  38 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/straight_08", ProcessorLists.l), Integer.valueOf(4)), 
/*  39 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/crossroad_02", ProcessorLists.l), Integer.valueOf(1)), 
/*  40 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/crossroad_03", ProcessorLists.l), Integer.valueOf(2)), (Object[])new Pair[] {
/*  41 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/crossroad_04", ProcessorLists.l), Integer.valueOf(2)), 
/*  42 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/crossroad_05", ProcessorLists.l), Integer.valueOf(2)), 
/*  43 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/crossroad_06", ProcessorLists.l), Integer.valueOf(2)), 
/*  44 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/streets/turn_01", ProcessorLists.l), Integer.valueOf(3))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */     
/*  49 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/zombie/streets"), new MinecraftKey("village/snowy/terminators"), 
/*     */ 
/*     */           
/*  52 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  53 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/corner_01", ProcessorLists.l), Integer.valueOf(2)), 
/*  54 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/corner_02", ProcessorLists.l), Integer.valueOf(2)), 
/*  55 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/corner_03", ProcessorLists.l), Integer.valueOf(2)), 
/*  56 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/square_01", ProcessorLists.l), Integer.valueOf(2)), 
/*  57 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/straight_01", ProcessorLists.l), Integer.valueOf(4)), 
/*  58 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/straight_02", ProcessorLists.l), Integer.valueOf(4)), 
/*  59 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/straight_03", ProcessorLists.l), Integer.valueOf(4)), 
/*  60 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/straight_04", ProcessorLists.l), Integer.valueOf(7)), 
/*  61 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/straight_06", ProcessorLists.l), Integer.valueOf(4)), 
/*  62 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/straight_08", ProcessorLists.l), Integer.valueOf(4)), 
/*  63 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/crossroad_02", ProcessorLists.l), Integer.valueOf(1)), 
/*  64 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/crossroad_03", ProcessorLists.l), Integer.valueOf(2)), (Object[])new Pair[] {
/*  65 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/crossroad_04", ProcessorLists.l), Integer.valueOf(2)), 
/*  66 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/crossroad_05", ProcessorLists.l), Integer.valueOf(2)), 
/*  67 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/crossroad_06", ProcessorLists.l), Integer.valueOf(2)), 
/*  68 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/streets/turn_01", ProcessorLists.l), Integer.valueOf(3))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */     
/*  73 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/houses"), new MinecraftKey("village/snowy/terminators"), 
/*     */ 
/*     */           
/*  76 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  77 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_small_house_1"), Integer.valueOf(2)), 
/*  78 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_small_house_2"), Integer.valueOf(2)), 
/*  79 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_small_house_3"), Integer.valueOf(2)), 
/*  80 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_small_house_4"), Integer.valueOf(3)), 
/*  81 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_small_house_5"), Integer.valueOf(2)), 
/*  82 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_small_house_6"), Integer.valueOf(2)), 
/*  83 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_small_house_7"), Integer.valueOf(2)), 
/*  84 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_small_house_8"), Integer.valueOf(2)), 
/*  85 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_medium_house_1"), Integer.valueOf(2)), 
/*  86 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_medium_house_2"), Integer.valueOf(2)), 
/*  87 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_medium_house_3"), Integer.valueOf(2)), 
/*  88 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_butchers_shop_1"), Integer.valueOf(2)), (Object[])new Pair[] { 
/*  89 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_butchers_shop_2"), Integer.valueOf(2)), 
/*  90 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_tool_smith_1"), Integer.valueOf(2)), 
/*  91 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_fletcher_house_1"), Integer.valueOf(2)), 
/*  92 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_shepherds_house_1"), Integer.valueOf(3)), 
/*  93 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_armorer_house_1"), Integer.valueOf(1)), 
/*  94 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_armorer_house_2"), Integer.valueOf(1)), 
/*  95 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_fisher_cottage"), Integer.valueOf(2)), 
/*  96 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_tannery_1"), Integer.valueOf(2)), 
/*  97 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_cartographer_house_1"), Integer.valueOf(2)), 
/*  98 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_library_1"), Integer.valueOf(2)), 
/*  99 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_masons_house_1"), Integer.valueOf(2)), 
/* 100 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_masons_house_2"), Integer.valueOf(2)), 
/* 101 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_weapon_smith_1"), Integer.valueOf(2)), 
/* 102 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_temple_1"), Integer.valueOf(2)), 
/* 103 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_farm_1", ProcessorLists.o), Integer.valueOf(3)), 
/* 104 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_farm_2", ProcessorLists.o), Integer.valueOf(3)), 
/* 105 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_animal_pen_1"), Integer.valueOf(2)), 
/* 106 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_animal_pen_2"), Integer.valueOf(2)), 
/* 107 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(6)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/zombie/houses"), new MinecraftKey("village/snowy/terminators"), 
/*     */ 
/*     */           
/* 115 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 116 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_small_house_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 117 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_small_house_2", ProcessorLists.d), Integer.valueOf(2)), 
/* 118 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_small_house_3", ProcessorLists.d), Integer.valueOf(2)), 
/* 119 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_small_house_4", ProcessorLists.d), Integer.valueOf(2)), 
/* 120 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_small_house_5", ProcessorLists.d), Integer.valueOf(2)), 
/* 121 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_small_house_6", ProcessorLists.d), Integer.valueOf(2)), 
/* 122 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_small_house_7", ProcessorLists.d), Integer.valueOf(2)), 
/* 123 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_small_house_8", ProcessorLists.d), Integer.valueOf(2)), 
/* 124 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_medium_house_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 125 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_medium_house_2", ProcessorLists.d), Integer.valueOf(2)), 
/* 126 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/houses/snowy_medium_house_3", ProcessorLists.d), Integer.valueOf(1)), 
/* 127 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_butchers_shop_1", ProcessorLists.d), Integer.valueOf(2)), (Object[])new Pair[] { 
/* 128 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_butchers_shop_2", ProcessorLists.d), Integer.valueOf(2)), 
/* 129 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_tool_smith_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 130 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_fletcher_house_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 131 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_shepherds_house_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 132 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_armorer_house_1", ProcessorLists.d), Integer.valueOf(1)), 
/* 133 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_armorer_house_2", ProcessorLists.d), Integer.valueOf(1)), 
/* 134 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_fisher_cottage", ProcessorLists.d), Integer.valueOf(2)), 
/* 135 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_tannery_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 136 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_cartographer_house_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 137 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_library_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 138 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_masons_house_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 139 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_masons_house_2", ProcessorLists.d), Integer.valueOf(2)), 
/* 140 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_weapon_smith_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 141 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_temple_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 142 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_farm_1", ProcessorLists.d), Integer.valueOf(3)), 
/* 143 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_farm_2", ProcessorLists.d), Integer.valueOf(3)), 
/* 144 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_animal_pen_1", ProcessorLists.d), Integer.valueOf(2)), 
/* 145 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/houses/snowy_animal_pen_2", ProcessorLists.d), Integer.valueOf(2)), 
/* 146 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(6)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/terminators"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 154 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 155 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_01", ProcessorLists.l), Integer.valueOf(1)), 
/* 156 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_02", ProcessorLists.l), Integer.valueOf(1)), 
/* 157 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_03", ProcessorLists.l), Integer.valueOf(1)), 
/* 158 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_04", ProcessorLists.l), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/trees"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 166 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 167 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.SPRUCE), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 175 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 176 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/snowy_lamp_post_01"), Integer.valueOf(4)), 
/* 177 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/snowy_lamp_post_02"), Integer.valueOf(4)), 
/* 178 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/snowy_lamp_post_03"), Integer.valueOf(1)), 
/* 179 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.SPRUCE), Integer.valueOf(4)), 
/* 180 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_SNOW), Integer.valueOf(4)), 
/* 181 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_ICE), Integer.valueOf(1)), 
/* 182 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(9))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/zombie/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 190 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 191 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/snowy_lamp_post_01", ProcessorLists.d), Integer.valueOf(1)), 
/* 192 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/snowy_lamp_post_02", ProcessorLists.d), Integer.valueOf(1)), 
/* 193 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/snowy_lamp_post_03", ProcessorLists.d), Integer.valueOf(1)), 
/* 194 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.SPRUCE), Integer.valueOf(4)), 
/* 195 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_SNOW), Integer.valueOf(4)), 
/* 196 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_ICE), Integer.valueOf(4)), 
/* 197 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(7))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 205 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 206 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/villagers/nitwit"), Integer.valueOf(1)), 
/* 207 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/villagers/baby"), Integer.valueOf(1)), 
/* 208 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/snowy/zombie/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 216 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 217 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/villagers/nitwit"), Integer.valueOf(1)), 
/* 218 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/snowy/zombie/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */   }
/*     */   
/*     */   public static void a() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureVillageSnowy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */