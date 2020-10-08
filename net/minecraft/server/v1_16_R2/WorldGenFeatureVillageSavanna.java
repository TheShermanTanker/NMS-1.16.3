/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class WorldGenFeatureVillageSavanna
/*     */ {
/*  10 */   public static final WorldGenFeatureDefinedStructurePoolTemplate a = WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/town_centers"), new MinecraftKey("empty"), 
/*     */ 
/*     */         
/*  13 */         (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  14 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/town_centers/savanna_meeting_point_1"), Integer.valueOf(100)), 
/*  15 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/town_centers/savanna_meeting_point_2"), Integer.valueOf(50)), 
/*  16 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/town_centers/savanna_meeting_point_3"), Integer.valueOf(150)), 
/*  17 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/town_centers/savanna_meeting_point_4"), Integer.valueOf(150)), 
/*  18 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/town_centers/savanna_meeting_point_1", ProcessorLists.c), Integer.valueOf(2)), 
/*  19 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/town_centers/savanna_meeting_point_2", ProcessorLists.c), Integer.valueOf(1)), 
/*  20 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/town_centers/savanna_meeting_point_3", ProcessorLists.c), Integer.valueOf(3)), 
/*  21 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/town_centers/savanna_meeting_point_4", ProcessorLists.c), Integer.valueOf(3))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  27 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/streets"), new MinecraftKey("village/savanna/terminators"), 
/*     */ 
/*     */           
/*  30 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  31 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/corner_01", ProcessorLists.k), Integer.valueOf(2)), 
/*  32 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/corner_03", ProcessorLists.k), Integer.valueOf(2)), 
/*  33 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/straight_02", ProcessorLists.k), Integer.valueOf(4)), 
/*  34 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/straight_04", ProcessorLists.k), Integer.valueOf(7)), 
/*  35 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/straight_05", ProcessorLists.k), Integer.valueOf(3)), 
/*  36 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/straight_06", ProcessorLists.k), Integer.valueOf(4)), 
/*  37 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/straight_08", ProcessorLists.k), Integer.valueOf(4)), 
/*  38 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/straight_09", ProcessorLists.k), Integer.valueOf(4)), 
/*  39 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/straight_10", ProcessorLists.k), Integer.valueOf(4)), 
/*  40 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/straight_11", ProcessorLists.k), Integer.valueOf(4)), 
/*  41 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/crossroad_02", ProcessorLists.k), Integer.valueOf(1)), 
/*  42 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/crossroad_03", ProcessorLists.k), Integer.valueOf(2)), (Object[])new Pair[] {
/*  43 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/crossroad_04", ProcessorLists.k), Integer.valueOf(2)), 
/*  44 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/crossroad_05", ProcessorLists.k), Integer.valueOf(2)), 
/*  45 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/crossroad_06", ProcessorLists.k), Integer.valueOf(2)), 
/*  46 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/crossroad_07", ProcessorLists.k), Integer.valueOf(2)), 
/*  47 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/split_01", ProcessorLists.k), Integer.valueOf(2)), 
/*  48 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/split_02", ProcessorLists.k), Integer.valueOf(2)), 
/*  49 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/streets/turn_01", ProcessorLists.k), Integer.valueOf(3))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */     
/*  54 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/zombie/streets"), new MinecraftKey("village/savanna/zombie/terminators"), 
/*     */ 
/*     */           
/*  57 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  58 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/corner_01", ProcessorLists.k), Integer.valueOf(2)), 
/*  59 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/corner_03", ProcessorLists.k), Integer.valueOf(2)), 
/*  60 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/straight_02", ProcessorLists.k), Integer.valueOf(4)), 
/*  61 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/straight_04", ProcessorLists.k), Integer.valueOf(7)), 
/*  62 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/straight_05", ProcessorLists.k), Integer.valueOf(3)), 
/*  63 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/straight_06", ProcessorLists.k), Integer.valueOf(4)), 
/*  64 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/straight_08", ProcessorLists.k), Integer.valueOf(4)), 
/*  65 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/straight_09", ProcessorLists.k), Integer.valueOf(4)), 
/*  66 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/straight_10", ProcessorLists.k), Integer.valueOf(4)), 
/*  67 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/straight_11", ProcessorLists.k), Integer.valueOf(4)), 
/*  68 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/crossroad_02", ProcessorLists.k), Integer.valueOf(1)), 
/*  69 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/crossroad_03", ProcessorLists.k), Integer.valueOf(2)), (Object[])new Pair[] {
/*  70 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/crossroad_04", ProcessorLists.k), Integer.valueOf(2)), 
/*  71 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/crossroad_05", ProcessorLists.k), Integer.valueOf(2)), 
/*  72 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/crossroad_06", ProcessorLists.k), Integer.valueOf(2)), 
/*  73 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/crossroad_07", ProcessorLists.k), Integer.valueOf(2)), 
/*  74 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/split_01", ProcessorLists.k), Integer.valueOf(2)), 
/*  75 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/split_02", ProcessorLists.k), Integer.valueOf(2)), 
/*  76 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/streets/turn_01", ProcessorLists.k), Integer.valueOf(3))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */     
/*  81 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/houses"), new MinecraftKey("village/savanna/terminators"), 
/*     */ 
/*     */           
/*  84 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  85 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_house_1"), Integer.valueOf(2)), 
/*  86 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_house_2"), Integer.valueOf(2)), 
/*  87 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_house_3"), Integer.valueOf(2)), 
/*  88 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_house_4"), Integer.valueOf(2)), 
/*  89 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_house_5"), Integer.valueOf(2)), 
/*  90 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_house_6"), Integer.valueOf(2)), 
/*  91 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_house_7"), Integer.valueOf(2)), 
/*  92 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_house_8"), Integer.valueOf(2)), 
/*  93 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_medium_house_1"), Integer.valueOf(2)), 
/*  94 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_medium_house_2"), Integer.valueOf(2)), 
/*  95 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_butchers_shop_1"), Integer.valueOf(2)), 
/*  96 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_butchers_shop_2"), Integer.valueOf(2)), (Object[])new Pair[] { 
/*  97 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_tool_smith_1"), Integer.valueOf(2)), 
/*  98 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_fletcher_house_1"), Integer.valueOf(2)), 
/*  99 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_shepherd_1"), Integer.valueOf(7)), 
/* 100 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_armorer_1"), Integer.valueOf(1)), 
/* 101 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_fisher_cottage_1"), Integer.valueOf(3)), 
/* 102 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_tannery_1"), Integer.valueOf(2)), 
/* 103 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_cartographer_1"), Integer.valueOf(2)), 
/* 104 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_library_1"), Integer.valueOf(2)), 
/* 105 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_mason_1"), Integer.valueOf(2)), 
/* 106 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_weaponsmith_1"), Integer.valueOf(2)), 
/* 107 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_weaponsmith_2"), Integer.valueOf(2)), 
/* 108 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_temple_1"), Integer.valueOf(2)), 
/* 109 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_temple_2"), Integer.valueOf(3)), 
/* 110 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_large_farm_1", ProcessorLists.n), Integer.valueOf(4)), 
/* 111 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_large_farm_2", ProcessorLists.n), Integer.valueOf(6)), 
/* 112 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_farm", ProcessorLists.n), Integer.valueOf(4)), 
/* 113 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_animal_pen_1"), Integer.valueOf(2)), 
/* 114 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_animal_pen_2"), Integer.valueOf(2)), 
/* 115 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_animal_pen_3"), Integer.valueOf(2)), 
/* 116 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(5)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/zombie/houses"), new MinecraftKey("village/savanna/zombie/terminators"), 
/*     */ 
/*     */           
/* 124 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 125 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_small_house_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 126 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_small_house_2", ProcessorLists.c), Integer.valueOf(2)), 
/* 127 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_small_house_3", ProcessorLists.c), Integer.valueOf(2)), 
/* 128 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_small_house_4", ProcessorLists.c), Integer.valueOf(2)), 
/* 129 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_small_house_5", ProcessorLists.c), Integer.valueOf(2)), 
/* 130 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_small_house_6", ProcessorLists.c), Integer.valueOf(2)), 
/* 131 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_small_house_7", ProcessorLists.c), Integer.valueOf(2)), 
/* 132 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_small_house_8", ProcessorLists.c), Integer.valueOf(2)), 
/* 133 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_medium_house_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 134 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_medium_house_2", ProcessorLists.c), Integer.valueOf(2)), 
/* 135 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_butchers_shop_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 136 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_butchers_shop_2", ProcessorLists.c), Integer.valueOf(2)), (Object[])new Pair[] { 
/* 137 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_tool_smith_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 138 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_fletcher_house_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 139 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_shepherd_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 140 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_armorer_1", ProcessorLists.c), Integer.valueOf(1)), 
/* 141 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_fisher_cottage_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 142 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_tannery_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 143 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_cartographer_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 144 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_library_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 145 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_mason_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 146 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_weaponsmith_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 147 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_weaponsmith_2", ProcessorLists.c), Integer.valueOf(2)), 
/* 148 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_temple_1", ProcessorLists.c), Integer.valueOf(1)), 
/* 149 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_temple_2", ProcessorLists.c), Integer.valueOf(3)), 
/* 150 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_large_farm_1", ProcessorLists.c), Integer.valueOf(4)), 
/* 151 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_large_farm_2", ProcessorLists.c), Integer.valueOf(4)), 
/* 152 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_small_farm", ProcessorLists.c), Integer.valueOf(4)), 
/* 153 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/houses/savanna_animal_pen_1", ProcessorLists.c), Integer.valueOf(2)), 
/* 154 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_animal_pen_2", ProcessorLists.c), Integer.valueOf(2)), 
/* 155 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/houses/savanna_animal_pen_3", ProcessorLists.c), Integer.valueOf(2)), 
/* 156 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(5)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/terminators"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 164 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 165 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_01", ProcessorLists.k), Integer.valueOf(1)), 
/* 166 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_02", ProcessorLists.k), Integer.valueOf(1)), 
/* 167 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_03", ProcessorLists.k), Integer.valueOf(1)), 
/* 168 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_04", ProcessorLists.k), Integer.valueOf(1)), 
/* 169 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/terminators/terminator_05", ProcessorLists.k), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/zombie/terminators"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 177 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 178 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_01", ProcessorLists.k), Integer.valueOf(1)), 
/* 179 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_02", ProcessorLists.k), Integer.valueOf(1)), 
/* 180 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_03", ProcessorLists.k), Integer.valueOf(1)), 
/* 181 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_04", ProcessorLists.k), Integer.valueOf(1)), 
/* 182 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/terminators/terminator_05", ProcessorLists.k), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/trees"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 190 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 191 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.ACACIA), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 199 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 200 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/savanna_lamp_post_01"), Integer.valueOf(4)), 
/* 201 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.ACACIA), Integer.valueOf(4)), 
/* 202 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_HAY), Integer.valueOf(4)), 
/* 203 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_MELON), Integer.valueOf(1)), 
/* 204 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(4))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/zombie/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 212 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 213 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/savanna_lamp_post_01", ProcessorLists.c), Integer.valueOf(4)), 
/* 214 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.ACACIA), Integer.valueOf(4)), 
/* 215 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_HAY), Integer.valueOf(4)), 
/* 216 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_MELON), Integer.valueOf(1)), 
/* 217 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(4))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 225 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 226 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/villagers/nitwit"), Integer.valueOf(1)), 
/* 227 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/villagers/baby"), Integer.valueOf(1)), 
/* 228 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/savanna/zombie/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 236 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 237 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/villagers/nitwit"), Integer.valueOf(1)), 
/* 238 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/savanna/zombie/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */   }
/*     */   
/*     */   public static void a() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureVillageSavanna.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */