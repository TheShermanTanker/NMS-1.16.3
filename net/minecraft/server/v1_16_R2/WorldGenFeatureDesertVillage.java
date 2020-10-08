/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class WorldGenFeatureDesertVillage
/*     */ {
/*  10 */   public static final WorldGenFeatureDefinedStructurePoolTemplate a = WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/town_centers"), new MinecraftKey("empty"), 
/*     */ 
/*     */         
/*  13 */         (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  14 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/town_centers/desert_meeting_point_1"), Integer.valueOf(98)), 
/*  15 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/town_centers/desert_meeting_point_2"), Integer.valueOf(98)), 
/*  16 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/town_centers/desert_meeting_point_3"), Integer.valueOf(49)), 
/*  17 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/town_centers/desert_meeting_point_1", ProcessorLists.f), Integer.valueOf(2)), 
/*  18 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/town_centers/desert_meeting_point_2", ProcessorLists.f), Integer.valueOf(2)), 
/*  19 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/town_centers/desert_meeting_point_3", ProcessorLists.f), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  25 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/streets"), new MinecraftKey("village/desert/terminators"), 
/*     */ 
/*     */           
/*  28 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  29 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/corner_01"), Integer.valueOf(3)), 
/*  30 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/corner_02"), Integer.valueOf(3)), 
/*  31 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/straight_01"), Integer.valueOf(4)), 
/*  32 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/straight_02"), Integer.valueOf(4)), 
/*  33 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/straight_03"), Integer.valueOf(3)), 
/*  34 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/crossroad_01"), Integer.valueOf(3)), 
/*  35 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/crossroad_02"), Integer.valueOf(3)), 
/*  36 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/crossroad_03"), Integer.valueOf(3)), 
/*  37 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/square_01"), Integer.valueOf(3)), 
/*  38 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/square_02"), Integer.valueOf(3)), 
/*  39 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/streets/turn_01"), Integer.valueOf(3))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/zombie/streets"), new MinecraftKey("village/desert/zombie/terminators"), 
/*     */ 
/*     */           
/*  47 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  48 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/corner_01"), Integer.valueOf(3)), 
/*  49 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/corner_02"), Integer.valueOf(3)), 
/*  50 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/straight_01"), Integer.valueOf(4)), 
/*  51 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/straight_02"), Integer.valueOf(4)), 
/*  52 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/straight_03"), Integer.valueOf(3)), 
/*  53 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/crossroad_01"), Integer.valueOf(3)), 
/*  54 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/crossroad_02"), Integer.valueOf(3)), 
/*  55 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/crossroad_03"), Integer.valueOf(3)), 
/*  56 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/square_01"), Integer.valueOf(3)), 
/*  57 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/square_02"), Integer.valueOf(3)), 
/*  58 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/streets/turn_01"), Integer.valueOf(3))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/houses"), new MinecraftKey("village/desert/terminators"), 
/*     */ 
/*     */           
/*  66 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  67 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_small_house_1"), Integer.valueOf(2)), 
/*  68 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_small_house_2"), Integer.valueOf(2)), 
/*  69 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_small_house_3"), Integer.valueOf(2)), 
/*  70 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_small_house_4"), Integer.valueOf(2)), 
/*  71 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_small_house_5"), Integer.valueOf(2)), 
/*  72 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_small_house_6"), Integer.valueOf(1)), 
/*  73 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_small_house_7"), Integer.valueOf(2)), 
/*  74 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_small_house_8"), Integer.valueOf(2)), 
/*  75 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_medium_house_1"), Integer.valueOf(2)), 
/*  76 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_medium_house_2"), Integer.valueOf(2)), 
/*  77 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_butcher_shop_1"), Integer.valueOf(2)), 
/*  78 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_tool_smith_1"), Integer.valueOf(2)), (Object[])new Pair[] { 
/*  79 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_fletcher_house_1"), Integer.valueOf(2)), 
/*  80 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_shepherd_house_1"), Integer.valueOf(2)), 
/*  81 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_armorer_1"), Integer.valueOf(1)), 
/*  82 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_fisher_1"), Integer.valueOf(2)), 
/*  83 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_tannery_1"), Integer.valueOf(2)), 
/*  84 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_cartographer_house_1"), Integer.valueOf(2)), 
/*  85 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_library_1"), Integer.valueOf(2)), 
/*  86 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_mason_1"), Integer.valueOf(2)), 
/*  87 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_weaponsmith_1"), Integer.valueOf(2)), 
/*  88 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_temple_1"), Integer.valueOf(2)), 
/*  89 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_temple_2"), Integer.valueOf(2)), 
/*  90 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_large_farm_1", ProcessorLists.q), Integer.valueOf(11)), 
/*  91 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_farm_1", ProcessorLists.q), Integer.valueOf(4)), 
/*  92 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_farm_2", ProcessorLists.q), Integer.valueOf(4)), 
/*  93 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_animal_pen_1"), Integer.valueOf(2)), 
/*  94 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_animal_pen_2"), Integer.valueOf(2)), 
/*  95 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(5)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/zombie/houses"), new MinecraftKey("village/desert/zombie/terminators"), 
/*     */ 
/*     */           
/* 103 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 104 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_small_house_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 105 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_small_house_2", ProcessorLists.f), Integer.valueOf(2)), 
/* 106 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_small_house_3", ProcessorLists.f), Integer.valueOf(2)), 
/* 107 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_small_house_4", ProcessorLists.f), Integer.valueOf(2)), 
/* 108 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_small_house_5", ProcessorLists.f), Integer.valueOf(2)), 
/* 109 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_small_house_6", ProcessorLists.f), Integer.valueOf(1)), 
/* 110 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_small_house_7", ProcessorLists.f), Integer.valueOf(2)), 
/* 111 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_small_house_8", ProcessorLists.f), Integer.valueOf(2)), 
/* 112 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_medium_house_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 113 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/houses/desert_medium_house_2", ProcessorLists.f), Integer.valueOf(2)), 
/* 114 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_butcher_shop_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 115 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_tool_smith_1", ProcessorLists.f), Integer.valueOf(2)), (Object[])new Pair[] { 
/* 116 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_fletcher_house_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 117 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_shepherd_house_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 118 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_armorer_1", ProcessorLists.f), Integer.valueOf(1)), 
/* 119 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_fisher_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 120 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_tannery_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 121 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_cartographer_house_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 122 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_library_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 123 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_mason_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 124 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_weaponsmith_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 125 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_temple_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 126 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_temple_2", ProcessorLists.f), Integer.valueOf(2)), 
/* 127 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_large_farm_1", ProcessorLists.f), Integer.valueOf(7)), 
/* 128 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_farm_1", ProcessorLists.f), Integer.valueOf(4)), 
/* 129 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_farm_2", ProcessorLists.f), Integer.valueOf(4)), 
/* 130 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_animal_pen_1", ProcessorLists.f), Integer.valueOf(2)), 
/* 131 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/houses/desert_animal_pen_2", ProcessorLists.f), Integer.valueOf(2)), 
/* 132 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(5)) }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/terminators"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 140 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 141 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/terminators/terminator_01"), Integer.valueOf(1)), 
/* 142 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/terminators/terminator_02"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/zombie/terminators"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 150 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 151 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/terminators/terminator_01"), Integer.valueOf(1)), 
/* 152 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/terminators/terminator_02"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 160 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 161 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/desert_lamp_1"), Integer.valueOf(10)), 
/* 162 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PATCH_CACTUS), Integer.valueOf(4)), 
/* 163 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_HAY), Integer.valueOf(4)), 
/* 164 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/zombie/decor"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 172 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 173 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/desert_lamp_1", ProcessorLists.f), Integer.valueOf(10)), 
/* 174 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PATCH_CACTUS), Integer.valueOf(4)), 
/* 175 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a(BiomeDecoratorGroups.PILE_HAY), Integer.valueOf(4)), 
/* 176 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 184 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 185 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/villagers/nitwit"), Integer.valueOf(1)), 
/* 186 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/villagers/baby"), Integer.valueOf(1)), 
/* 187 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("village/desert/zombie/villagers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 195 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 196 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/villagers/nitwit"), Integer.valueOf(1)), 
/* 197 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("village/desert/zombie/villagers/unemployed"), Integer.valueOf(10))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */   }
/*     */   
/*     */   public static void a() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDesertVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */