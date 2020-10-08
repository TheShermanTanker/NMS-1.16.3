/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class WorldGenFeatureVillagePlain
/*     */ {
/*  10 */   public static final WorldGenFeatureDefinedStructurePoolTemplate a = WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/town_centers"), new MinecraftKey("empty"), 
/*     */ 
/*     */         
/*  13 */         (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  14 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/town_centers/plains_fountain_01", ProcessorLists.h), Integer.valueOf(50)), 
/*  15 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/town_centers/plains_meeting_point_1", ProcessorLists.h), Integer.valueOf(50)), 
/*  16 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/town_centers/plains_meeting_point_2"), Integer.valueOf(50)), 
/*  17 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/town_centers/plains_meeting_point_3", ProcessorLists.i), Integer.valueOf(50)), 
/*  18 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/town_centers/plains_fountain_01", ProcessorLists.b), Integer.valueOf(1)), 
/*  19 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/town_centers/plains_meeting_point_1", ProcessorLists.b), Integer.valueOf(1)), 
/*  20 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/town_centers/plains_meeting_point_2", ProcessorLists.b), Integer.valueOf(1)), 
/*  21 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/town_centers/plains_meeting_point_3", ProcessorLists.b), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  27 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/streets"), new MinecraftKey("village/plains/terminators"), 
/*     */ 
/*     */           
/*  30 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  31 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/corner_01", ProcessorLists.j), Integer.valueOf(2)), 
/*  32 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/corner_02", ProcessorLists.j), Integer.valueOf(2)), 
/*  33 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/corner_03", ProcessorLists.j), Integer.valueOf(2)), 
/*  34 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/straight_01", ProcessorLists.j), Integer.valueOf(4)), 
/*  35 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/straight_02", ProcessorLists.j), Integer.valueOf(4)), 
/*  36 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/straight_03", ProcessorLists.j), Integer.valueOf(7)), 
/*  37 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/straight_04", ProcessorLists.j), Integer.valueOf(7)), 
/*  38 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/straight_05", ProcessorLists.j), Integer.valueOf(3)), 
/*  39 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/straight_06", ProcessorLists.j), Integer.valueOf(4)), 
/*  40 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/crossroad_01", ProcessorLists.j), Integer.valueOf(2)), 
/*  41 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/crossroad_02", ProcessorLists.j), Integer.valueOf(1)), 
/*  42 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/crossroad_03", ProcessorLists.j), Integer.valueOf(2)), (Object[])new Pair[] {
/*  43 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/crossroad_04", ProcessorLists.j), Integer.valueOf(2)), 
/*  44 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/crossroad_05", ProcessorLists.j), Integer.valueOf(2)), 
/*  45 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/crossroad_06", ProcessorLists.j), Integer.valueOf(2)), 
/*  46 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/streets/turn_01", ProcessorLists.j), Integer.valueOf(3))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */     
/*  51 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/zombie/streets"), new MinecraftKey("village/plains/terminators"), 
/*     */ 
/*     */           
/*  54 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  55 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/corner_01", ProcessorLists.j), Integer.valueOf(2)), 
/*  56 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/corner_02", ProcessorLists.j), Integer.valueOf(2)), 
/*  57 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/corner_03", ProcessorLists.j), Integer.valueOf(2)), 
/*  58 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/straight_01", ProcessorLists.j), Integer.valueOf(4)), 
/*  59 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/straight_02", ProcessorLists.j), Integer.valueOf(4)), 
/*  60 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/straight_03", ProcessorLists.j), Integer.valueOf(7)), 
/*  61 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/straight_04", ProcessorLists.j), Integer.valueOf(7)), 
/*  62 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/straight_05", ProcessorLists.j), Integer.valueOf(3)), 
/*  63 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/straight_06", ProcessorLists.j), Integer.valueOf(4)), 
/*  64 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/crossroad_01", ProcessorLists.j), Integer.valueOf(2)), 
/*  65 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/crossroad_02", ProcessorLists.j), Integer.valueOf(1)), 
/*  66 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/crossroad_03", ProcessorLists.j), Integer.valueOf(2)), (Object[])new Pair[] {
/*  67 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/crossroad_04", ProcessorLists.j), Integer.valueOf(2)), 
/*  68 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/crossroad_05", ProcessorLists.j), Integer.valueOf(2)), 
/*  69 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/crossroad_06", ProcessorLists.j), Integer.valueOf(2)), 
/*  70 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/streets/turn_01", ProcessorLists.j), Integer.valueOf(3))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */     
/*  75 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/houses"), new MinecraftKey("village/plains/terminators"), 
/*     */ 
/*     */           
/*  78 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  79 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  80 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_house_2", ProcessorLists.g), Integer.valueOf(2)), 
/*  81 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_house_3", ProcessorLists.g), Integer.valueOf(2)), 
/*  82 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_house_4", ProcessorLists.g), Integer.valueOf(2)), 
/*  83 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_house_5", ProcessorLists.g), Integer.valueOf(2)), 
/*  84 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_house_6", ProcessorLists.g), Integer.valueOf(1)), 
/*  85 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_house_7", ProcessorLists.g), Integer.valueOf(2)), 
/*  86 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_house_8", ProcessorLists.g), Integer.valueOf(3)), 
/*  87 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_medium_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  88 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_medium_house_2", ProcessorLists.g), Integer.valueOf(2)), 
/*  89 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_big_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  90 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_butcher_shop_1", ProcessorLists.g), Integer.valueOf(2)), (Object[])new Pair[] { 
/*  91 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_butcher_shop_2", ProcessorLists.g), Integer.valueOf(2)), 
/*  92 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_tool_smith_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  93 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_fletcher_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  94 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_shepherds_house_1"), Integer.valueOf(2)), 
/*  95 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_armorer_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  96 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_fisher_cottage_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  97 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_tannery_1", ProcessorLists.g), Integer.valueOf(2)), 
/*  98 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_cartographer_1", ProcessorLists.g), Integer.valueOf(1)), 
/*  99 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_library_1", ProcessorLists.g), Integer.valueOf(5)), 
/* 100 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_library_2", ProcessorLists.g), Integer.valueOf(1)), 
/* 101 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_masons_house_1", ProcessorLists.g), Integer.valueOf(2)), 
/* 102 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_weaponsmith_1", ProcessorLists.g), Integer.valueOf(2)), 
/* 103 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_temple_3", ProcessorLists.g), Integer.valueOf(2)), 
/* 104 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_temple_4", ProcessorLists.g), Integer.valueOf(2)), 
/* 105 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_stable_1", ProcessorLists.g), Integer.valueOf(2)), 
/* 106 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_stable_2"), Integer.valueOf(2)), 
/* 107 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_large_farm_1", ProcessorLists.m), Integer.valueOf(4)), 
/* 108 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_farm_1", ProcessorLists.m), Integer.valueOf(4)), 
/* 109 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_animal_pen_1"), Integer.valueOf(1)), 
/* 110 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_animal_pen_2"), Integer.valueOf(1)), 
/* 111 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_animal_pen_3"), Integer.valueOf(5)), 
/* 112 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_accessory_1"), Integer.valueOf(1)), 
/* 113 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_meeting_point_4", ProcessorLists.i), Integer.valueOf(3)), 
/* 114 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_meeting_point_5"), Integer.valueOf(1)), 
/* 115 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(10)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/zombie/houses"), new MinecraftKey("village/plains/terminators"), 
/*     */ 
/*     */           
/* 123 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 124 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_small_house_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 125 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_small_house_2", ProcessorLists.b), Integer.valueOf(2)), 
/* 126 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_small_house_3", ProcessorLists.b), Integer.valueOf(2)), 
/* 127 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_small_house_4", ProcessorLists.b), Integer.valueOf(2)), 
/* 128 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_small_house_5", ProcessorLists.b), Integer.valueOf(2)), 
/* 129 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_small_house_6", ProcessorLists.b), Integer.valueOf(1)), 
/* 130 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_small_house_7", ProcessorLists.b), Integer.valueOf(2)), 
/* 131 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_small_house_8", ProcessorLists.b), Integer.valueOf(2)), 
/* 132 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_medium_house_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 133 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_medium_house_2", ProcessorLists.b), Integer.valueOf(2)), 
/* 134 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_big_house_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 135 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_butcher_shop_1", ProcessorLists.b), Integer.valueOf(2)), (Object[])new Pair[] { 
/* 136 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_butcher_shop_2", ProcessorLists.b), Integer.valueOf(2)), 
/* 137 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_tool_smith_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 138 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_fletcher_house_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 139 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_shepherds_house_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 140 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_armorer_house_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 141 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_fisher_cottage_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 142 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_tannery_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 143 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_cartographer_1", ProcessorLists.b), Integer.valueOf(1)), 
/* 144 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_library_1", ProcessorLists.b), Integer.valueOf(3)), 
/* 145 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_library_2", ProcessorLists.b), Integer.valueOf(1)), 
/* 146 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_masons_house_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 147 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_weaponsmith_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 148 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_temple_3", ProcessorLists.b), Integer.valueOf(2)), 
/* 149 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_temple_4", ProcessorLists.b), Integer.valueOf(2)), 
/* 150 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_stable_1", ProcessorLists.b), Integer.valueOf(2)), 
/* 151 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_stable_2", ProcessorLists.b), Integer.valueOf(2)), 
/* 152 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_large_farm_1", ProcessorLists.b), Integer.valueOf(4)), 
/* 153 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_small_farm_1", ProcessorLists.b), Integer.valueOf(4)), 
/* 154 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_animal_pen_1", ProcessorLists.b), Integer.valueOf(1)), 
/* 155 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/houses/plains_animal_pen_2", ProcessorLists.b), Integer.valueOf(1)), 
/* 156 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_animal_pen_3", ProcessorLists.b), Integer.valueOf(5)), 
/* 157 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_meeting_point_4", ProcessorLists.b), Integer.valueOf(3)), 
/* 158 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/houses/plains_meeting_point_5", ProcessorLists.b), Integer.valueOf(1)), 
/* 159 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(10)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/terminators"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 167 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 168 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_01", ProcessorLists.j), Integer.valueOf(1)), 
/* 169 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_02", ProcessorLists.j), Integer.valueOf(1)), 
/* 170 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_03", ProcessorLists.j), Integer.valueOf(1)), 
/* 171 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/terminators/terminator_04", ProcessorLists.j), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/trees"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 179 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 180 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.OAK), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 188 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 189 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/plains_lamp_1"), Integer.valueOf(2)), 
/* 190 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.OAK), Integer.valueOf(1)), 
/* 191 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.FLOWER_PLAIN), Integer.valueOf(1)), 
/* 192 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_HAY), Integer.valueOf(1)), 
/* 193 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(2))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/zombie/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 201 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 202 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/plains_lamp_1", ProcessorLists.b), Integer.valueOf(1)), 
/* 203 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.OAK), Integer.valueOf(1)), 
/* 204 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.FLOWER_PLAIN), Integer.valueOf(1)), 
/* 205 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_HAY), Integer.valueOf(1)), 
/* 206 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(2))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 214 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 215 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/villagers/nitwit"), Integer.valueOf(1)), 
/* 216 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/villagers/baby"), Integer.valueOf(1)), 
/* 217 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/plains/zombie/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 225 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 226 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/villagers/nitwit"), Integer.valueOf(1)), 
/* 227 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/plains/zombie/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/common/animals"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 237 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 238 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cows_1"), Integer.valueOf(7)), 
/* 239 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/pigs_1"), Integer.valueOf(7)), 
/* 240 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/horses_1"), Integer.valueOf(1)), 
/* 241 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/horses_2"), Integer.valueOf(1)), 
/* 242 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/horses_3"), Integer.valueOf(1)), 
/* 243 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/horses_4"), Integer.valueOf(1)), 
/* 244 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/horses_5"), Integer.valueOf(1)), 
/* 245 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/sheep_1"), Integer.valueOf(1)), 
/* 246 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/sheep_2"), Integer.valueOf(1)), 
/* 247 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(5))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 252 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/common/sheep"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 255 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 256 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/sheep_1"), Integer.valueOf(1)), 
/* 257 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/sheep_2"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 262 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/common/cats"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 265 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 266 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_black"), Integer.valueOf(1)), 
/* 267 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_british"), Integer.valueOf(1)), 
/* 268 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_calico"), Integer.valueOf(1)), 
/* 269 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_persian"), Integer.valueOf(1)), 
/* 270 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_ragdoll"), Integer.valueOf(1)), 
/* 271 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_red"), Integer.valueOf(1)), 
/* 272 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_siamese"), Integer.valueOf(1)), 
/* 273 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_tabby"), Integer.valueOf(1)), 
/* 274 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_white"), Integer.valueOf(1)), 
/* 275 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cat_jellie"), Integer.valueOf(1)), 
/* 276 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(3))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 281 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/common/butcher_animals"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 284 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 285 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/cows_1"), Integer.valueOf(3)), 
/* 286 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/pigs_1"), Integer.valueOf(3)), 
/* 287 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/sheep_1"), Integer.valueOf(1)), 
/* 288 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/animals/sheep_2"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/common/iron_golem"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 296 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 297 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/iron_golem"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/common/well_bottoms"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 305 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 306 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/common/well_bottom"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */   }
/*     */   
/*     */   public static void a() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureVillagePlain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */