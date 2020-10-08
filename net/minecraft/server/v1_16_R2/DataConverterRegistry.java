/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import com.mojang.datafixers.DataFixerBuilder;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.UnaryOperator;
/*     */ 
/*     */ public class DataConverterRegistry {
/*  16 */   private static final BiFunction<Integer, Schema, Schema> a = Schema::new;
/*  17 */   private static final BiFunction<Integer, Schema, Schema> b = DataConverterSchemaNamed::new;
/*  18 */   private static final DataFixer c = b();
/*     */   
/*     */   private static DataFixer b() {
/*  21 */     DataFixerBuilder datafixerbuilder = new DataFixerBuilder(SharedConstants.getGameVersion().getWorldVersion());
/*     */     
/*  23 */     a(datafixerbuilder);
/*  24 */     return datafixerbuilder.build(SystemUtils.e());
/*     */   }
/*     */   public static DataFixer getDataFixer() {
/*  27 */     return a();
/*     */   } public static DataFixer a() {
/*  29 */     return c;
/*     */   }
/*     */   
/*     */   private static void a(DataFixerBuilder datafixerbuilder) {
/*  33 */     Schema schema = datafixerbuilder.addSchema(99, DataConverterSchemaV99::new);
/*  34 */     Schema schema1 = datafixerbuilder.addSchema(100, DataConverterSchemaV100::new);
/*     */     
/*  36 */     datafixerbuilder.addFixer(new DataConverterEquipment(schema1, true));
/*  37 */     Schema schema2 = datafixerbuilder.addSchema(101, a);
/*     */     
/*  39 */     datafixerbuilder.addFixer(new DataConverterSignText(schema2, false));
/*  40 */     Schema schema3 = datafixerbuilder.addSchema(102, DataConverterSchemaV102::new);
/*     */     
/*  42 */     datafixerbuilder.addFixer(new DataConverterMaterialId(schema3, true));
/*  43 */     datafixerbuilder.addFixer(new DataConverterPotionId(schema3, false));
/*  44 */     Schema schema4 = datafixerbuilder.addSchema(105, a);
/*     */     
/*  46 */     datafixerbuilder.addFixer(new DataConverterSpawnEgg(schema4, true));
/*  47 */     Schema schema5 = datafixerbuilder.addSchema(106, DataConverterSchemaV106::new);
/*     */     
/*  49 */     datafixerbuilder.addFixer(new DataConverterMobSpawner(schema5, true));
/*  50 */     Schema schema6 = datafixerbuilder.addSchema(107, DataConverterSchemaV107::new);
/*     */     
/*  52 */     datafixerbuilder.addFixer(new DataConverterMinecart(schema6, true));
/*  53 */     Schema schema7 = datafixerbuilder.addSchema(108, a);
/*     */     
/*  55 */     datafixerbuilder.addFixer(new DataConverterUUID(schema7, true));
/*  56 */     Schema schema8 = datafixerbuilder.addSchema(109, a);
/*     */     
/*  58 */     datafixerbuilder.addFixer(new DataConverterHealth(schema8, true));
/*  59 */     Schema schema9 = datafixerbuilder.addSchema(110, a);
/*     */     
/*  61 */     datafixerbuilder.addFixer(new DataConverterSaddle(schema9, true));
/*  62 */     Schema schema10 = datafixerbuilder.addSchema(111, a);
/*     */     
/*  64 */     datafixerbuilder.addFixer(new DataConverterHanging(schema10, true));
/*  65 */     Schema schema11 = datafixerbuilder.addSchema(113, a);
/*     */     
/*  67 */     datafixerbuilder.addFixer(new DataConverterDropChances(schema11, true));
/*  68 */     Schema schema12 = datafixerbuilder.addSchema(135, DataConverterSchemaV135::new);
/*     */     
/*  70 */     datafixerbuilder.addFixer(new DataConverterRiding(schema12, true));
/*  71 */     Schema schema13 = datafixerbuilder.addSchema(143, DataConverterSchemaV143::new);
/*     */     
/*  73 */     datafixerbuilder.addFixer(new DataConverterEntityTippedArrow(schema13, true));
/*  74 */     Schema schema14 = datafixerbuilder.addSchema(147, a);
/*     */     
/*  76 */     datafixerbuilder.addFixer(new DataConverterArmorStand(schema14, true));
/*  77 */     Schema schema15 = datafixerbuilder.addSchema(165, a);
/*     */     
/*  79 */     datafixerbuilder.addFixer(new DataConverterBook(schema15, true));
/*  80 */     Schema schema16 = datafixerbuilder.addSchema(501, DataConverterSchemaV501::new);
/*     */     
/*  82 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema16, "Add 1.10 entities fix", DataConverterTypes.ENTITY));
/*  83 */     Schema schema17 = datafixerbuilder.addSchema(502, a);
/*     */     
/*  85 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema17, "cooked_fished item renamer", s -> Objects.equals(DataConverterSchemaNamed.a(s), "minecraft:cooked_fished") ? "minecraft:cooked_fish" : s));
/*     */ 
/*     */     
/*  88 */     datafixerbuilder.addFixer(new DataConverterZombie(schema17, false));
/*  89 */     Schema schema18 = datafixerbuilder.addSchema(505, a);
/*     */     
/*  91 */     datafixerbuilder.addFixer(new DataConverterVBO(schema18, false));
/*  92 */     Schema schema19 = datafixerbuilder.addSchema(700, DataConverterSchemaV700::new);
/*     */     
/*  94 */     datafixerbuilder.addFixer(new DataConverterGuardian(schema19, true));
/*  95 */     Schema schema20 = datafixerbuilder.addSchema(701, DataConverterSchemaV701::new);
/*     */     
/*  97 */     datafixerbuilder.addFixer(new DataConverterSkeleton(schema20, true));
/*  98 */     Schema schema21 = datafixerbuilder.addSchema(702, DataConverterSchemaV702::new);
/*     */     
/* 100 */     datafixerbuilder.addFixer(new DataConverterZombieType(schema21, true));
/* 101 */     Schema schema22 = datafixerbuilder.addSchema(703, DataConverterSchemaV703::new);
/*     */     
/* 103 */     datafixerbuilder.addFixer(new DataConverterHorse(schema22, true));
/* 104 */     Schema schema23 = datafixerbuilder.addSchema(704, DataConverterSchemaV704::new);
/*     */     
/* 106 */     datafixerbuilder.addFixer(new DataConverterTileEntity(schema23, true));
/* 107 */     Schema schema24 = datafixerbuilder.addSchema(705, DataConverterSchemaV705::new);
/*     */     
/* 109 */     datafixerbuilder.addFixer(new DataConverterEntity(schema24, true));
/* 110 */     Schema schema25 = datafixerbuilder.addSchema(804, b);
/*     */     
/* 112 */     datafixerbuilder.addFixer(new DataConverterBanner(schema25, true));
/* 113 */     Schema schema26 = datafixerbuilder.addSchema(806, b);
/*     */     
/* 115 */     datafixerbuilder.addFixer(new DataConverterPotionWater(schema26, false));
/* 116 */     Schema schema27 = datafixerbuilder.addSchema(808, DataConverterSchemaV808::new);
/*     */     
/* 118 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema27, "added shulker box", DataConverterTypes.BLOCK_ENTITY));
/* 119 */     Schema schema28 = datafixerbuilder.addSchema(808, 1, b);
/*     */     
/* 121 */     datafixerbuilder.addFixer(new DataConverterShulker(schema28, false));
/* 122 */     Schema schema29 = datafixerbuilder.addSchema(813, b);
/*     */     
/* 124 */     datafixerbuilder.addFixer(new DataConverterShulkerBoxItem(schema29, false));
/* 125 */     datafixerbuilder.addFixer(new DataConverterShulkerBoxBlock(schema29, false));
/* 126 */     Schema schema30 = datafixerbuilder.addSchema(816, b);
/*     */     
/* 128 */     datafixerbuilder.addFixer(new DataConverterLang(schema30, false));
/* 129 */     Schema schema31 = datafixerbuilder.addSchema(820, b);
/*     */     
/* 131 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema31, "totem item renamer", a("minecraft:totem", "minecraft:totem_of_undying")));
/* 132 */     Schema schema32 = datafixerbuilder.addSchema(1022, DataConverterSchemaV1022::new);
/*     */     
/* 134 */     datafixerbuilder.addFixer(new DataConverterShoulderEntity(schema32, "added shoulder entities to players", DataConverterTypes.PLAYER));
/* 135 */     Schema schema33 = datafixerbuilder.addSchema(1125, DataConverterSchemaV1125::new);
/*     */     
/* 137 */     datafixerbuilder.addFixer(new DataConverterBedBlock(schema33, true));
/* 138 */     datafixerbuilder.addFixer(new DataConverterBedItem(schema33, false));
/* 139 */     Schema schema34 = datafixerbuilder.addSchema(1344, b);
/*     */     
/* 141 */     datafixerbuilder.addFixer(new DataConverterKeybind(schema34, false));
/* 142 */     Schema schema35 = datafixerbuilder.addSchema(1446, b);
/*     */     
/* 144 */     datafixerbuilder.addFixer(new DataConverterKeybind2(schema35, false));
/* 145 */     Schema schema36 = datafixerbuilder.addSchema(1450, b);
/*     */     
/* 147 */     datafixerbuilder.addFixer(new DataConverterFlattenState(schema36, false));
/* 148 */     Schema schema37 = datafixerbuilder.addSchema(1451, DataConverterSchemaV1451::new);
/*     */     
/* 150 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema37, "AddTrappedChestFix", DataConverterTypes.BLOCK_ENTITY));
/* 151 */     Schema schema38 = datafixerbuilder.addSchema(1451, 1, DataConverterSchemaV1451_1::new);
/*     */     
/* 153 */     datafixerbuilder.addFixer(new ChunkConverterPalette(schema38, true));
/* 154 */     Schema schema39 = datafixerbuilder.addSchema(1451, 2, DataConverterSchemaV1451_2::new);
/*     */     
/* 156 */     datafixerbuilder.addFixer(new DataConverterPiston(schema39, true));
/* 157 */     Schema schema40 = datafixerbuilder.addSchema(1451, 3, DataConverterSchemaV1451_3::new);
/*     */     
/* 159 */     datafixerbuilder.addFixer(new DataConverterEntityBlockState(schema40, true));
/* 160 */     datafixerbuilder.addFixer(new DataConverterMap(schema40, false));
/* 161 */     Schema schema41 = datafixerbuilder.addSchema(1451, 4, DataConverterSchemaV1451_4::new);
/*     */     
/* 163 */     datafixerbuilder.addFixer(new DataConverterBlockName(schema41, true));
/* 164 */     datafixerbuilder.addFixer(new DataConverterFlatten(schema41, false));
/* 165 */     Schema schema42 = datafixerbuilder.addSchema(1451, 5, DataConverterSchemaV1451_5::new);
/*     */     
/* 167 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema42, "RemoveNoteBlockFlowerPotFix", DataConverterTypes.BLOCK_ENTITY));
/* 168 */     datafixerbuilder.addFixer(new DataConverterFlattenSpawnEgg(schema42, false));
/* 169 */     datafixerbuilder.addFixer(new DataConverterWolf(schema42, false));
/* 170 */     datafixerbuilder.addFixer(new DataConverterBannerColour(schema42, false));
/* 171 */     datafixerbuilder.addFixer(new DataConverterWorldGenSettings(schema42, false));
/* 172 */     Schema schema43 = datafixerbuilder.addSchema(1451, 6, DataConverterSchemaV1451_6::new);
/*     */     
/* 174 */     datafixerbuilder.addFixer(new DataConverterStatistic(schema43, true));
/* 175 */     datafixerbuilder.addFixer(new DataConverterJukeBox(schema43, false));
/* 176 */     Schema schema44 = datafixerbuilder.addSchema(1451, 7, DataConverterSchemaV1451_7::new);
/*     */     
/* 178 */     datafixerbuilder.addFixer(new DataConverterVillage(schema44, true));
/* 179 */     Schema schema45 = datafixerbuilder.addSchema(1451, 7, b);
/*     */     
/* 181 */     datafixerbuilder.addFixer(new DataConverterVillagerTrade(schema45, false));
/* 182 */     Schema schema46 = datafixerbuilder.addSchema(1456, b);
/*     */     
/* 184 */     datafixerbuilder.addFixer(new DataConverterItemFrame(schema46, false));
/* 185 */     Schema schema47 = datafixerbuilder.addSchema(1458, b);
/*     */ 
/*     */     
/* 188 */     datafixerbuilder.addFixer(new DataFix(schema47, false)
/*     */         {
/*     */           protected TypeRewriteRule makeRule() {
/* 191 */             return fixTypeEverywhereTyped("Player CustomName", getInputSchema().getType(DataConverterTypes.PLAYER), typed -> typed.update(DSL.remainderFinder(), ()));
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     datafixerbuilder.addFixer(new DataConverterCustomNameEntity(schema47, false));
/* 200 */     datafixerbuilder.addFixer(new DataConverterCustomNameItem(schema47, false));
/* 201 */     datafixerbuilder.addFixer(new DataConverterCustomNameTile(schema47, false));
/* 202 */     Schema schema48 = datafixerbuilder.addSchema(1460, DataConverterSchemaV1460::new);
/*     */     
/* 204 */     datafixerbuilder.addFixer(new DataConverterPainting(schema48, false));
/* 205 */     Schema schema49 = datafixerbuilder.addSchema(1466, DataConverterSchemaV1466::new);
/*     */     
/* 207 */     datafixerbuilder.addFixer(new DataConverterProtoChunk(schema49, true));
/* 208 */     Schema schema50 = datafixerbuilder.addSchema(1470, DataConverterSchemaV1470::new);
/*     */     
/* 210 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema50, "Add 1.13 entities fix", DataConverterTypes.ENTITY));
/* 211 */     Schema schema51 = datafixerbuilder.addSchema(1474, b);
/*     */     
/* 213 */     datafixerbuilder.addFixer(new DataConverterColorlessShulkerEntity(schema51, false));
/* 214 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema51, "Colorless shulker block fixer", s -> Objects.equals(DataConverterSchemaNamed.a(s), "minecraft:purple_shulker_box") ? "minecraft:shulker_box" : s));
/*     */ 
/*     */     
/* 217 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema51, "Colorless shulker item fixer", s -> Objects.equals(DataConverterSchemaNamed.a(s), "minecraft:purple_shulker_box") ? "minecraft:shulker_box" : s));
/*     */ 
/*     */     
/* 220 */     Schema schema52 = datafixerbuilder.addSchema(1475, b);
/*     */     
/* 222 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema52, "Flowing fixer", a((Map<String, String>)ImmutableMap.of("minecraft:flowing_water", "minecraft:water", "minecraft:flowing_lava", "minecraft:lava"))));
/* 223 */     Schema schema53 = datafixerbuilder.addSchema(1480, b);
/*     */     
/* 225 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema53, "Rename coral blocks", a(DataConverterCoral.a)));
/* 226 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema53, "Rename coral items", a(DataConverterCoral.a)));
/* 227 */     Schema schema54 = datafixerbuilder.addSchema(1481, DataConverterSchemaV1481::new);
/*     */     
/* 229 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema54, "Add conduit", DataConverterTypes.BLOCK_ENTITY));
/* 230 */     Schema schema55 = datafixerbuilder.addSchema(1483, DataConverterSchemaV1483::new);
/*     */     
/* 232 */     datafixerbuilder.addFixer(new DataConverterEntityPufferfish(schema55, true));
/* 233 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema55, "Rename pufferfish egg item", a(DataConverterEntityPufferfish.a)));
/* 234 */     Schema schema56 = datafixerbuilder.addSchema(1484, b);
/*     */     
/* 236 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema56, "Rename seagrass items", a((Map<String, String>)ImmutableMap.of("minecraft:sea_grass", "minecraft:seagrass", "minecraft:tall_sea_grass", "minecraft:tall_seagrass"))));
/* 237 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema56, "Rename seagrass blocks", a((Map<String, String>)ImmutableMap.of("minecraft:sea_grass", "minecraft:seagrass", "minecraft:tall_sea_grass", "minecraft:tall_seagrass"))));
/* 238 */     datafixerbuilder.addFixer(new DataConverterHeightmapRenaming(schema56, false));
/* 239 */     Schema schema57 = datafixerbuilder.addSchema(1486, DataConverterSchemaV1486::new);
/*     */     
/* 241 */     datafixerbuilder.addFixer(new DataConverterEntityCodSalmon(schema57, true));
/* 242 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema57, "Rename cod/salmon egg items", a(DataConverterEntityCodSalmon.b)));
/* 243 */     Schema schema58 = datafixerbuilder.addSchema(1487, b);
/*     */     
/* 245 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema58, "Rename prismarine_brick(s)_* blocks", a((Map<String, String>)ImmutableMap.of("minecraft:prismarine_bricks_slab", "minecraft:prismarine_brick_slab", "minecraft:prismarine_bricks_stairs", "minecraft:prismarine_brick_stairs"))));
/* 246 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema58, "Rename prismarine_brick(s)_* items", a((Map<String, String>)ImmutableMap.of("minecraft:prismarine_bricks_slab", "minecraft:prismarine_brick_slab", "minecraft:prismarine_bricks_stairs", "minecraft:prismarine_brick_stairs"))));
/* 247 */     Schema schema59 = datafixerbuilder.addSchema(1488, b);
/*     */     
/* 249 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema59, "Rename kelp/kelptop", a((Map<String, String>)ImmutableMap.of("minecraft:kelp_top", "minecraft:kelp", "minecraft:kelp", "minecraft:kelp_plant"))));
/* 250 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema59, "Rename kelptop", a("minecraft:kelp_top", "minecraft:kelp")));
/* 251 */     datafixerbuilder.addFixer(new DataConverterNamedEntity(schema59, false, "Command block block entity custom name fix", DataConverterTypes.BLOCK_ENTITY, "minecraft:command_block")
/*     */         {
/*     */           protected Typed<?> a(Typed<?> typed) {
/* 254 */             return typed.update(DSL.remainderFinder(), DataConverterCustomNameEntity::a);
/*     */           }
/*     */         });
/* 257 */     datafixerbuilder.addFixer(new DataConverterNamedEntity(schema59, false, "Command block minecart custom name fix", DataConverterTypes.ENTITY, "minecraft:commandblock_minecart")
/*     */         {
/*     */           protected Typed<?> a(Typed<?> typed) {
/* 260 */             return typed.update(DSL.remainderFinder(), DataConverterCustomNameEntity::a);
/*     */           }
/*     */         });
/* 263 */     datafixerbuilder.addFixer(new DataConverterIglooMetadataRemoval(schema59, false));
/* 264 */     Schema schema60 = datafixerbuilder.addSchema(1490, b);
/*     */     
/* 266 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema60, "Rename melon_block", a("minecraft:melon_block", "minecraft:melon")));
/* 267 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema60, "Rename melon_block/melon/speckled_melon", a((Map<String, String>)ImmutableMap.of("minecraft:melon_block", "minecraft:melon", "minecraft:melon", "minecraft:melon_slice", "minecraft:speckled_melon", "minecraft:glistering_melon_slice"))));
/* 268 */     Schema schema61 = datafixerbuilder.addSchema(1492, b);
/*     */     
/* 270 */     datafixerbuilder.addFixer(new DataConverterChunkStructuresTemplateRename(schema61, false));
/* 271 */     Schema schema62 = datafixerbuilder.addSchema(1494, b);
/*     */     
/* 273 */     datafixerbuilder.addFixer(new DataConverterItemStackEnchantment(schema62, false));
/* 274 */     Schema schema63 = datafixerbuilder.addSchema(1496, b);
/*     */     
/* 276 */     datafixerbuilder.addFixer(new DataConverterLeaves(schema63, false));
/* 277 */     Schema schema64 = datafixerbuilder.addSchema(1500, b);
/*     */     
/* 279 */     datafixerbuilder.addFixer(new DataConverterBlockEntityKeepPacked(schema64, false));
/* 280 */     Schema schema65 = datafixerbuilder.addSchema(1501, b);
/*     */     
/* 282 */     datafixerbuilder.addFixer(new DataConverterAdvancement(schema65, false));
/* 283 */     Schema schema66 = datafixerbuilder.addSchema(1502, b);
/*     */     
/* 285 */     datafixerbuilder.addFixer(new DataConverterRecipes(schema66, false));
/* 286 */     Schema schema67 = datafixerbuilder.addSchema(1506, b);
/*     */     
/* 288 */     datafixerbuilder.addFixer(new DataConverterLevelDataGeneratorOptions(schema67, false));
/* 289 */     Schema schema68 = datafixerbuilder.addSchema(1510, DataConverterSchemaV1510::new);
/*     */     
/* 291 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema68, "Block renamening fix", a(DataConverterEntityRename.b)));
/* 292 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema68, "Item renamening fix", a(DataConverterEntityRename.c)));
/* 293 */     datafixerbuilder.addFixer(new DataConverterRecipeRename(schema68, false));
/* 294 */     datafixerbuilder.addFixer(new DataConverterEntityRename(schema68, true));
/* 295 */     datafixerbuilder.addFixer(new DataConverterSwimStats(schema68, false));
/* 296 */     Schema schema69 = datafixerbuilder.addSchema(1514, b);
/*     */     
/* 298 */     datafixerbuilder.addFixer(new DataConverterObjectiveDisplayName(schema69, false));
/* 299 */     datafixerbuilder.addFixer(new DataConverterTeamDisplayName(schema69, false));
/* 300 */     datafixerbuilder.addFixer(new DataConverterObjectiveRenderType(schema69, false));
/* 301 */     Schema schema70 = datafixerbuilder.addSchema(1515, b);
/*     */     
/* 303 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema70, "Rename coral fan blocks", a(DataConverterCoralFan.a)));
/* 304 */     Schema schema71 = datafixerbuilder.addSchema(1624, b);
/*     */     
/* 306 */     datafixerbuilder.addFixer(new DataConverterTrappedChest(schema71, false));
/* 307 */     Schema schema72 = datafixerbuilder.addSchema(1800, DataConverterSchemaV1800::new);
/*     */     
/* 309 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema72, "Added 1.14 mobs fix", DataConverterTypes.ENTITY));
/* 310 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema72, "Rename dye items", a(DataConverterDye.a)));
/* 311 */     Schema schema73 = datafixerbuilder.addSchema(1801, DataConverterSchemaV1801::new);
/*     */     
/* 313 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema73, "Added Illager Beast", DataConverterTypes.ENTITY));
/* 314 */     Schema schema74 = datafixerbuilder.addSchema(1802, b);
/*     */     
/* 316 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema74, "Rename sign blocks & stone slabs", a((Map<String, String>)ImmutableMap.of("minecraft:stone_slab", "minecraft:smooth_stone_slab", "minecraft:sign", "minecraft:oak_sign", "minecraft:wall_sign", "minecraft:oak_wall_sign"))));
/* 317 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema74, "Rename sign item & stone slabs", a((Map<String, String>)ImmutableMap.of("minecraft:stone_slab", "minecraft:smooth_stone_slab", "minecraft:sign", "minecraft:oak_sign"))));
/* 318 */     Schema schema75 = datafixerbuilder.addSchema(1803, b);
/*     */     
/* 320 */     datafixerbuilder.addFixer(new DataConverterItemLoreComponentize(schema75, false));
/* 321 */     Schema schema76 = datafixerbuilder.addSchema(1904, DataConverterSchemaV1904::new);
/*     */     
/* 323 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema76, "Added Cats", DataConverterTypes.ENTITY));
/* 324 */     datafixerbuilder.addFixer(new DataConverterEntityCatSplit(schema76, false));
/* 325 */     Schema schema77 = datafixerbuilder.addSchema(1905, b);
/*     */     
/* 327 */     datafixerbuilder.addFixer(new DataConverterChunkStatus(schema77, false));
/* 328 */     Schema schema78 = datafixerbuilder.addSchema(1906, DataConverterSchemaV1906::new);
/*     */     
/* 330 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema78, "Add POI Blocks", DataConverterTypes.BLOCK_ENTITY));
/* 331 */     Schema schema79 = datafixerbuilder.addSchema(1909, DataConverterSchemaV1909::new);
/*     */     
/* 333 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema79, "Add jigsaw", DataConverterTypes.BLOCK_ENTITY));
/* 334 */     Schema schema80 = datafixerbuilder.addSchema(1911, b);
/*     */     
/* 336 */     datafixerbuilder.addFixer(new DataConverterChunkStatus2(schema80, false));
/* 337 */     Schema schema81 = datafixerbuilder.addSchema(1917, b);
/*     */     
/* 339 */     datafixerbuilder.addFixer(new DataConverterCatType(schema81, false));
/* 340 */     Schema schema82 = datafixerbuilder.addSchema(1918, b);
/*     */     
/* 342 */     datafixerbuilder.addFixer(new DataConverterVillagerProfession(schema82, "minecraft:villager"));
/* 343 */     datafixerbuilder.addFixer(new DataConverterVillagerProfession(schema82, "minecraft:zombie_villager"));
/* 344 */     Schema schema83 = datafixerbuilder.addSchema(1920, DataConverterSchemaV1920::new);
/*     */     
/* 346 */     datafixerbuilder.addFixer(new DataConverterNewVillage(schema83, false));
/* 347 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema83, "Add campfire", DataConverterTypes.BLOCK_ENTITY));
/* 348 */     Schema schema84 = datafixerbuilder.addSchema(1925, b);
/*     */     
/* 350 */     datafixerbuilder.addFixer(new DataConverterMapId(schema84, false));
/* 351 */     Schema schema85 = datafixerbuilder.addSchema(1928, DataConverterSchemaV1928::new);
/*     */     
/* 353 */     datafixerbuilder.addFixer(new DataConverterEntityRavagerRename(schema85, true));
/* 354 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema85, "Rename ravager egg item", a(DataConverterEntityRavagerRename.a)));
/* 355 */     Schema schema86 = datafixerbuilder.addSchema(1929, DataConverterSchemaV1929::new);
/*     */     
/* 357 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema86, "Add Wandering Trader and Trader Llama", DataConverterTypes.ENTITY));
/* 358 */     Schema schema87 = datafixerbuilder.addSchema(1931, DataConverterSchemaV1931::new);
/*     */     
/* 360 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema87, "Added Fox", DataConverterTypes.ENTITY));
/* 361 */     Schema schema88 = datafixerbuilder.addSchema(1936, b);
/*     */     
/* 363 */     datafixerbuilder.addFixer(new DataConverterOptionsAddTextBackground(schema88, false));
/* 364 */     Schema schema89 = datafixerbuilder.addSchema(1946, b);
/*     */     
/* 366 */     datafixerbuilder.addFixer(new DataConverterPOI(schema89, false));
/* 367 */     Schema schema90 = datafixerbuilder.addSchema(1948, b);
/*     */     
/* 369 */     datafixerbuilder.addFixer(new DataConverterOminousBannerRename(schema90, false));
/* 370 */     Schema schema91 = datafixerbuilder.addSchema(1953, b);
/*     */     
/* 372 */     datafixerbuilder.addFixer(new DataConverterOminousBannerBlockEntityRename(schema91, false));
/* 373 */     Schema schema92 = datafixerbuilder.addSchema(1955, b);
/*     */     
/* 375 */     datafixerbuilder.addFixer(new DataConverterVillagerLevelXp(schema92, false));
/* 376 */     datafixerbuilder.addFixer(new DataConverterZombieVillagerLevelXp(schema92, false));
/* 377 */     Schema schema93 = datafixerbuilder.addSchema(1961, b);
/*     */     
/* 379 */     datafixerbuilder.addFixer(new DataConverterChunkLightRemove(schema93, false));
/* 380 */     Schema schema94 = datafixerbuilder.addSchema(1963, b);
/*     */     
/* 382 */     datafixerbuilder.addFixer(new DataConverterRemoveGolemGossip(schema94, false));
/* 383 */     Schema schema95 = datafixerbuilder.addSchema(2100, DataConverterSchemaV2100::new);
/*     */     
/* 385 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema95, "Added Bee and Bee Stinger", DataConverterTypes.ENTITY));
/* 386 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema95, "Add beehive", DataConverterTypes.BLOCK_ENTITY));
/* 387 */     datafixerbuilder.addFixer(new DataConverterRecipeBase(schema95, false, "Rename sugar recipe", a("minecraft:sugar", "sugar_from_sugar_cane")));
/* 388 */     datafixerbuilder.addFixer(new DataConverterAdvancementBase(schema95, false, "Rename sugar recipe advancement", a("minecraft:recipes/misc/sugar", "minecraft:recipes/misc/sugar_from_sugar_cane")));
/* 389 */     Schema schema96 = datafixerbuilder.addSchema(2202, b);
/*     */     
/* 391 */     datafixerbuilder.addFixer(new DataConverterLeavesBiome(schema96, false));
/* 392 */     Schema schema97 = datafixerbuilder.addSchema(2209, b);
/*     */     
/* 394 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema97, "Rename bee_hive item to beehive", a("minecraft:bee_hive", "minecraft:beehive")));
/* 395 */     datafixerbuilder.addFixer(new DataConverterBeehive(schema97));
/* 396 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema97, "Rename bee_hive block to beehive", a("minecraft:bee_hive", "minecraft:beehive")));
/* 397 */     Schema schema98 = datafixerbuilder.addSchema(2211, b);
/*     */     
/* 399 */     datafixerbuilder.addFixer(new DataConverterStructureReference(schema98, false));
/* 400 */     Schema schema99 = datafixerbuilder.addSchema(2218, b);
/*     */     
/* 402 */     datafixerbuilder.addFixer(new DataConverterPOIRebuild(schema99, false));
/* 403 */     Schema schema100 = datafixerbuilder.addSchema(2501, DataConverterSchemaV2501::new);
/*     */     
/* 405 */     datafixerbuilder.addFixer(new DataConverterFurnaceRecipesUsed(schema100, true));
/* 406 */     Schema schema101 = datafixerbuilder.addSchema(2502, DataConverterSchemaV2502::new);
/*     */     
/* 408 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema101, "Added Hoglin", DataConverterTypes.ENTITY));
/* 409 */     Schema schema102 = datafixerbuilder.addSchema(2503, b);
/*     */     
/* 411 */     datafixerbuilder.addFixer(new DataConverterWallProperty(schema102, false));
/* 412 */     datafixerbuilder.addFixer(new DataConverterAdvancementBase(schema102, false, "Composter category change", a("minecraft:recipes/misc/composter", "minecraft:recipes/decorations/composter")));
/* 413 */     Schema schema103 = datafixerbuilder.addSchema(2505, DataConverterSchemaV2505::new);
/*     */     
/* 415 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema103, "Added Piglin", DataConverterTypes.ENTITY));
/* 416 */     datafixerbuilder.addFixer(new DataConverterMemoryExpiry(schema103, "minecraft:villager"));
/* 417 */     Schema schema104 = datafixerbuilder.addSchema(2508, b);
/*     */     
/* 419 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema104, "Renamed fungi items to fungus", a((Map<String, String>)ImmutableMap.of("minecraft:warped_fungi", "minecraft:warped_fungus", "minecraft:crimson_fungi", "minecraft:crimson_fungus"))));
/* 420 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema104, "Renamed fungi blocks to fungus", a((Map<String, String>)ImmutableMap.of("minecraft:warped_fungi", "minecraft:warped_fungus", "minecraft:crimson_fungi", "minecraft:crimson_fungus"))));
/* 421 */     Schema schema105 = datafixerbuilder.addSchema(2509, DataConverterSchemaV2509::new);
/*     */     
/* 423 */     datafixerbuilder.addFixer(new DataConverterEntityZombifiedPiglinRename(schema105));
/* 424 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema105, "Rename zombie pigman egg item", a(DataConverterEntityZombifiedPiglinRename.a)));
/* 425 */     Schema schema106 = datafixerbuilder.addSchema(2511, b);
/*     */     
/* 427 */     datafixerbuilder.addFixer(new DataConverterEntityProjectileOwner(schema106));
/* 428 */     Schema schema107 = datafixerbuilder.addSchema(2514, b);
/*     */     
/* 430 */     datafixerbuilder.addFixer(new DataConverterEntityUUID(schema107));
/* 431 */     datafixerbuilder.addFixer(new DataConverterBlockEntityUUID(schema107));
/* 432 */     datafixerbuilder.addFixer(new DataConverterPlayerUUID(schema107));
/* 433 */     datafixerbuilder.addFixer(new DataConverterMiscUUID(schema107));
/* 434 */     datafixerbuilder.addFixer(new DataConverterSavedDataUUID(schema107));
/* 435 */     datafixerbuilder.addFixer(new DataConverterItemStackUUID(schema107));
/* 436 */     Schema schema108 = datafixerbuilder.addSchema(2516, b);
/*     */     
/* 438 */     datafixerbuilder.addFixer(new DataConverterGossip(schema108, "minecraft:villager"));
/* 439 */     datafixerbuilder.addFixer(new DataConverterGossip(schema108, "minecraft:zombie_villager"));
/* 440 */     Schema schema109 = datafixerbuilder.addSchema(2518, b);
/*     */     
/* 442 */     datafixerbuilder.addFixer(new DataConverterJigsawProperties(schema109, false));
/* 443 */     datafixerbuilder.addFixer(new DataConverterJigsawRotation(schema109, false));
/* 444 */     Schema schema110 = datafixerbuilder.addSchema(2519, DataConverterSchemaV2519::new);
/*     */     
/* 446 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema110, "Added Strider", DataConverterTypes.ENTITY));
/* 447 */     Schema schema111 = datafixerbuilder.addSchema(2522, DataConverterSchemaV2522::new);
/*     */     
/* 449 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema111, "Added Zoglin", DataConverterTypes.ENTITY));
/* 450 */     Schema schema112 = datafixerbuilder.addSchema(2523, b);
/*     */     
/* 452 */     datafixerbuilder.addFixer(new DataConverterAttributes(schema112));
/* 453 */     Schema schema113 = datafixerbuilder.addSchema(2527, b);
/*     */     
/* 455 */     datafixerbuilder.addFixer(new DataConverterBitStorageAlign(schema113));
/* 456 */     Schema schema114 = datafixerbuilder.addSchema(2528, b);
/*     */     
/* 458 */     datafixerbuilder.addFixer(DataConverterItemName.a(schema114, "Rename soul fire torch and soul fire lantern", a((Map<String, String>)ImmutableMap.of("minecraft:soul_fire_torch", "minecraft:soul_torch", "minecraft:soul_fire_lantern", "minecraft:soul_lantern"))));
/* 459 */     datafixerbuilder.addFixer(DataConverterBlockRename.a(schema114, "Rename soul fire torch and soul fire lantern", a((Map<String, String>)ImmutableMap.of("minecraft:soul_fire_torch", "minecraft:soul_torch", "minecraft:soul_fire_wall_torch", "minecraft:soul_wall_torch", "minecraft:soul_fire_lantern", "minecraft:soul_lantern"))));
/* 460 */     Schema schema115 = datafixerbuilder.addSchema(2529, b);
/*     */     
/* 462 */     datafixerbuilder.addFixer(new DataConverterStriderGravity(schema115, false));
/* 463 */     Schema schema116 = datafixerbuilder.addSchema(2531, b);
/*     */     
/* 465 */     datafixerbuilder.addFixer(new DataConverterRedstoneConnections(schema116));
/* 466 */     Schema schema117 = datafixerbuilder.addSchema(2533, b);
/*     */     
/* 468 */     datafixerbuilder.addFixer(new DataConverterVillagerFollowRange(schema117));
/* 469 */     Schema schema118 = datafixerbuilder.addSchema(2535, b);
/*     */     
/* 471 */     datafixerbuilder.addFixer(new DataConverterEntityShulkerRotation(schema118));
/* 472 */     Schema schema119 = datafixerbuilder.addSchema(2550, b);
/*     */     
/* 474 */     datafixerbuilder.addFixer(new DataConverterWorldGenSettingsBuilding(schema119));
/* 475 */     Schema schema120 = datafixerbuilder.addSchema(2551, DataConverterSchemaV2551::new);
/*     */     
/* 477 */     datafixerbuilder.addFixer(new DataConverterShoulderEntity(schema120, "add types to WorldGenData", DataConverterTypes.WORLD_GEN_SETTINGS));
/* 478 */     Schema schema121 = datafixerbuilder.addSchema(2552, b);
/*     */     
/* 480 */     datafixerbuilder.addFixer(new DataConverterBiomeBase(schema121, false, "Nether biome rename", (Map<String, String>)ImmutableMap.of("minecraft:nether", "minecraft:nether_wastes")));
/* 481 */     Schema schema122 = datafixerbuilder.addSchema(2553, b);
/*     */     
/* 483 */     datafixerbuilder.addFixer(new DataConverterBiome(schema122, false));
/* 484 */     Schema schema123 = datafixerbuilder.addSchema(2558, b);
/*     */     
/* 486 */     datafixerbuilder.addFixer(new DataConverterMissingDimension(schema123, false));
/* 487 */     datafixerbuilder.addFixer(new DataConverterSettingRename(schema123, false, "Rename swapHands setting", "key_key.swapHands", "key_key.swapOffhand"));
/* 488 */     Schema schema124 = datafixerbuilder.addSchema(2568, DataConverterSchemaV2568::new);
/*     */     
/* 490 */     datafixerbuilder.addFixer(new DataConverterAddChoices(schema124, "Added Piglin Brute", DataConverterTypes.ENTITY));
/*     */   }
/*     */   
/*     */   private static UnaryOperator<String> a(Map<String, String> map) {
/* 494 */     return s -> (String)map.getOrDefault(s, s);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static UnaryOperator<String> a(String s, String s1) {
/* 500 */     return s2 -> Objects.equals(s2, s) ? s1 : s2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */