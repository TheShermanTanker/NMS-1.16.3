/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.types.templates.Tag;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.datafixers.util.Unit;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterEntityBlockState
/*     */   extends DataFix
/*     */ {
/*     */   private static final Map<String, Integer> a;
/*     */   
/*     */   public DataConverterEntityBlockState(Schema var0, boolean var1) {
/*  34 */     super(var0, var1);
/*     */   }
/*     */   static {
/*  37 */     a = (Map<String, Integer>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           var0.put("minecraft:air", Integer.valueOf(0));
/*     */           var0.put("minecraft:stone", Integer.valueOf(1));
/*     */           var0.put("minecraft:grass", Integer.valueOf(2));
/*     */           var0.put("minecraft:dirt", Integer.valueOf(3));
/*     */           var0.put("minecraft:cobblestone", Integer.valueOf(4));
/*     */           var0.put("minecraft:planks", Integer.valueOf(5));
/*     */           var0.put("minecraft:sapling", Integer.valueOf(6));
/*     */           var0.put("minecraft:bedrock", Integer.valueOf(7));
/*     */           var0.put("minecraft:flowing_water", Integer.valueOf(8));
/*     */           var0.put("minecraft:water", Integer.valueOf(9));
/*     */           var0.put("minecraft:flowing_lava", Integer.valueOf(10));
/*     */           var0.put("minecraft:lava", Integer.valueOf(11));
/*     */           var0.put("minecraft:sand", Integer.valueOf(12));
/*     */           var0.put("minecraft:gravel", Integer.valueOf(13));
/*     */           var0.put("minecraft:gold_ore", Integer.valueOf(14));
/*     */           var0.put("minecraft:iron_ore", Integer.valueOf(15));
/*     */           var0.put("minecraft:coal_ore", Integer.valueOf(16));
/*     */           var0.put("minecraft:log", Integer.valueOf(17));
/*     */           var0.put("minecraft:leaves", Integer.valueOf(18));
/*     */           var0.put("minecraft:sponge", Integer.valueOf(19));
/*     */           var0.put("minecraft:glass", Integer.valueOf(20));
/*     */           var0.put("minecraft:lapis_ore", Integer.valueOf(21));
/*     */           var0.put("minecraft:lapis_block", Integer.valueOf(22));
/*     */           var0.put("minecraft:dispenser", Integer.valueOf(23));
/*     */           var0.put("minecraft:sandstone", Integer.valueOf(24));
/*     */           var0.put("minecraft:noteblock", Integer.valueOf(25));
/*     */           var0.put("minecraft:bed", Integer.valueOf(26));
/*     */           var0.put("minecraft:golden_rail", Integer.valueOf(27));
/*     */           var0.put("minecraft:detector_rail", Integer.valueOf(28));
/*     */           var0.put("minecraft:sticky_piston", Integer.valueOf(29));
/*     */           var0.put("minecraft:web", Integer.valueOf(30));
/*     */           var0.put("minecraft:tallgrass", Integer.valueOf(31));
/*     */           var0.put("minecraft:deadbush", Integer.valueOf(32));
/*     */           var0.put("minecraft:piston", Integer.valueOf(33));
/*     */           var0.put("minecraft:piston_head", Integer.valueOf(34));
/*     */           var0.put("minecraft:wool", Integer.valueOf(35));
/*     */           var0.put("minecraft:piston_extension", Integer.valueOf(36));
/*     */           var0.put("minecraft:yellow_flower", Integer.valueOf(37));
/*     */           var0.put("minecraft:red_flower", Integer.valueOf(38));
/*     */           var0.put("minecraft:brown_mushroom", Integer.valueOf(39));
/*     */           var0.put("minecraft:red_mushroom", Integer.valueOf(40));
/*     */           var0.put("minecraft:gold_block", Integer.valueOf(41));
/*     */           var0.put("minecraft:iron_block", Integer.valueOf(42));
/*     */           var0.put("minecraft:double_stone_slab", Integer.valueOf(43));
/*     */           var0.put("minecraft:stone_slab", Integer.valueOf(44));
/*     */           var0.put("minecraft:brick_block", Integer.valueOf(45));
/*     */           var0.put("minecraft:tnt", Integer.valueOf(46));
/*     */           var0.put("minecraft:bookshelf", Integer.valueOf(47));
/*     */           var0.put("minecraft:mossy_cobblestone", Integer.valueOf(48));
/*     */           var0.put("minecraft:obsidian", Integer.valueOf(49));
/*     */           var0.put("minecraft:torch", Integer.valueOf(50));
/*     */           var0.put("minecraft:fire", Integer.valueOf(51));
/*     */           var0.put("minecraft:mob_spawner", Integer.valueOf(52));
/*     */           var0.put("minecraft:oak_stairs", Integer.valueOf(53));
/*     */           var0.put("minecraft:chest", Integer.valueOf(54));
/*     */           var0.put("minecraft:redstone_wire", Integer.valueOf(55));
/*     */           var0.put("minecraft:diamond_ore", Integer.valueOf(56));
/*     */           var0.put("minecraft:diamond_block", Integer.valueOf(57));
/*     */           var0.put("minecraft:crafting_table", Integer.valueOf(58));
/*     */           var0.put("minecraft:wheat", Integer.valueOf(59));
/*     */           var0.put("minecraft:farmland", Integer.valueOf(60));
/*     */           var0.put("minecraft:furnace", Integer.valueOf(61));
/*     */           var0.put("minecraft:lit_furnace", Integer.valueOf(62));
/*     */           var0.put("minecraft:standing_sign", Integer.valueOf(63));
/*     */           var0.put("minecraft:wooden_door", Integer.valueOf(64));
/*     */           var0.put("minecraft:ladder", Integer.valueOf(65));
/*     */           var0.put("minecraft:rail", Integer.valueOf(66));
/*     */           var0.put("minecraft:stone_stairs", Integer.valueOf(67));
/*     */           var0.put("minecraft:wall_sign", Integer.valueOf(68));
/*     */           var0.put("minecraft:lever", Integer.valueOf(69));
/*     */           var0.put("minecraft:stone_pressure_plate", Integer.valueOf(70));
/*     */           var0.put("minecraft:iron_door", Integer.valueOf(71));
/*     */           var0.put("minecraft:wooden_pressure_plate", Integer.valueOf(72));
/*     */           var0.put("minecraft:redstone_ore", Integer.valueOf(73));
/*     */           var0.put("minecraft:lit_redstone_ore", Integer.valueOf(74));
/*     */           var0.put("minecraft:unlit_redstone_torch", Integer.valueOf(75));
/*     */           var0.put("minecraft:redstone_torch", Integer.valueOf(76));
/*     */           var0.put("minecraft:stone_button", Integer.valueOf(77));
/*     */           var0.put("minecraft:snow_layer", Integer.valueOf(78));
/*     */           var0.put("minecraft:ice", Integer.valueOf(79));
/*     */           var0.put("minecraft:snow", Integer.valueOf(80));
/*     */           var0.put("minecraft:cactus", Integer.valueOf(81));
/*     */           var0.put("minecraft:clay", Integer.valueOf(82));
/*     */           var0.put("minecraft:reeds", Integer.valueOf(83));
/*     */           var0.put("minecraft:jukebox", Integer.valueOf(84));
/*     */           var0.put("minecraft:fence", Integer.valueOf(85));
/*     */           var0.put("minecraft:pumpkin", Integer.valueOf(86));
/*     */           var0.put("minecraft:netherrack", Integer.valueOf(87));
/*     */           var0.put("minecraft:soul_sand", Integer.valueOf(88));
/*     */           var0.put("minecraft:glowstone", Integer.valueOf(89));
/*     */           var0.put("minecraft:portal", Integer.valueOf(90));
/*     */           var0.put("minecraft:lit_pumpkin", Integer.valueOf(91));
/*     */           var0.put("minecraft:cake", Integer.valueOf(92));
/*     */           var0.put("minecraft:unpowered_repeater", Integer.valueOf(93));
/*     */           var0.put("minecraft:powered_repeater", Integer.valueOf(94));
/*     */           var0.put("minecraft:stained_glass", Integer.valueOf(95));
/*     */           var0.put("minecraft:trapdoor", Integer.valueOf(96));
/*     */           var0.put("minecraft:monster_egg", Integer.valueOf(97));
/*     */           var0.put("minecraft:stonebrick", Integer.valueOf(98));
/*     */           var0.put("minecraft:brown_mushroom_block", Integer.valueOf(99));
/*     */           var0.put("minecraft:red_mushroom_block", Integer.valueOf(100));
/*     */           var0.put("minecraft:iron_bars", Integer.valueOf(101));
/*     */           var0.put("minecraft:glass_pane", Integer.valueOf(102));
/*     */           var0.put("minecraft:melon_block", Integer.valueOf(103));
/*     */           var0.put("minecraft:pumpkin_stem", Integer.valueOf(104));
/*     */           var0.put("minecraft:melon_stem", Integer.valueOf(105));
/*     */           var0.put("minecraft:vine", Integer.valueOf(106));
/*     */           var0.put("minecraft:fence_gate", Integer.valueOf(107));
/*     */           var0.put("minecraft:brick_stairs", Integer.valueOf(108));
/*     */           var0.put("minecraft:stone_brick_stairs", Integer.valueOf(109));
/*     */           var0.put("minecraft:mycelium", Integer.valueOf(110));
/*     */           var0.put("minecraft:waterlily", Integer.valueOf(111));
/*     */           var0.put("minecraft:nether_brick", Integer.valueOf(112));
/*     */           var0.put("minecraft:nether_brick_fence", Integer.valueOf(113));
/*     */           var0.put("minecraft:nether_brick_stairs", Integer.valueOf(114));
/*     */           var0.put("minecraft:nether_wart", Integer.valueOf(115));
/*     */           var0.put("minecraft:enchanting_table", Integer.valueOf(116));
/*     */           var0.put("minecraft:brewing_stand", Integer.valueOf(117));
/*     */           var0.put("minecraft:cauldron", Integer.valueOf(118));
/*     */           var0.put("minecraft:end_portal", Integer.valueOf(119));
/*     */           var0.put("minecraft:end_portal_frame", Integer.valueOf(120));
/*     */           var0.put("minecraft:end_stone", Integer.valueOf(121));
/*     */           var0.put("minecraft:dragon_egg", Integer.valueOf(122));
/*     */           var0.put("minecraft:redstone_lamp", Integer.valueOf(123));
/*     */           var0.put("minecraft:lit_redstone_lamp", Integer.valueOf(124));
/*     */           var0.put("minecraft:double_wooden_slab", Integer.valueOf(125));
/*     */           var0.put("minecraft:wooden_slab", Integer.valueOf(126));
/*     */           var0.put("minecraft:cocoa", Integer.valueOf(127));
/*     */           var0.put("minecraft:sandstone_stairs", Integer.valueOf(128));
/*     */           var0.put("minecraft:emerald_ore", Integer.valueOf(129));
/*     */           var0.put("minecraft:ender_chest", Integer.valueOf(130));
/*     */           var0.put("minecraft:tripwire_hook", Integer.valueOf(131));
/*     */           var0.put("minecraft:tripwire", Integer.valueOf(132));
/*     */           var0.put("minecraft:emerald_block", Integer.valueOf(133));
/*     */           var0.put("minecraft:spruce_stairs", Integer.valueOf(134));
/*     */           var0.put("minecraft:birch_stairs", Integer.valueOf(135));
/*     */           var0.put("minecraft:jungle_stairs", Integer.valueOf(136));
/*     */           var0.put("minecraft:command_block", Integer.valueOf(137));
/*     */           var0.put("minecraft:beacon", Integer.valueOf(138));
/*     */           var0.put("minecraft:cobblestone_wall", Integer.valueOf(139));
/*     */           var0.put("minecraft:flower_pot", Integer.valueOf(140));
/*     */           var0.put("minecraft:carrots", Integer.valueOf(141));
/*     */           var0.put("minecraft:potatoes", Integer.valueOf(142));
/*     */           var0.put("minecraft:wooden_button", Integer.valueOf(143));
/*     */           var0.put("minecraft:skull", Integer.valueOf(144));
/*     */           var0.put("minecraft:anvil", Integer.valueOf(145));
/*     */           var0.put("minecraft:trapped_chest", Integer.valueOf(146));
/*     */           var0.put("minecraft:light_weighted_pressure_plate", Integer.valueOf(147));
/*     */           var0.put("minecraft:heavy_weighted_pressure_plate", Integer.valueOf(148));
/*     */           var0.put("minecraft:unpowered_comparator", Integer.valueOf(149));
/*     */           var0.put("minecraft:powered_comparator", Integer.valueOf(150));
/*     */           var0.put("minecraft:daylight_detector", Integer.valueOf(151));
/*     */           var0.put("minecraft:redstone_block", Integer.valueOf(152));
/*     */           var0.put("minecraft:quartz_ore", Integer.valueOf(153));
/*     */           var0.put("minecraft:hopper", Integer.valueOf(154));
/*     */           var0.put("minecraft:quartz_block", Integer.valueOf(155));
/*     */           var0.put("minecraft:quartz_stairs", Integer.valueOf(156));
/*     */           var0.put("minecraft:activator_rail", Integer.valueOf(157));
/*     */           var0.put("minecraft:dropper", Integer.valueOf(158));
/*     */           var0.put("minecraft:stained_hardened_clay", Integer.valueOf(159));
/*     */           var0.put("minecraft:stained_glass_pane", Integer.valueOf(160));
/*     */           var0.put("minecraft:leaves2", Integer.valueOf(161));
/*     */           var0.put("minecraft:log2", Integer.valueOf(162));
/*     */           var0.put("minecraft:acacia_stairs", Integer.valueOf(163));
/*     */           var0.put("minecraft:dark_oak_stairs", Integer.valueOf(164));
/*     */           var0.put("minecraft:slime", Integer.valueOf(165));
/*     */           var0.put("minecraft:barrier", Integer.valueOf(166));
/*     */           var0.put("minecraft:iron_trapdoor", Integer.valueOf(167));
/*     */           var0.put("minecraft:prismarine", Integer.valueOf(168));
/*     */           var0.put("minecraft:sea_lantern", Integer.valueOf(169));
/*     */           var0.put("minecraft:hay_block", Integer.valueOf(170));
/*     */           var0.put("minecraft:carpet", Integer.valueOf(171));
/*     */           var0.put("minecraft:hardened_clay", Integer.valueOf(172));
/*     */           var0.put("minecraft:coal_block", Integer.valueOf(173));
/*     */           var0.put("minecraft:packed_ice", Integer.valueOf(174));
/*     */           var0.put("minecraft:double_plant", Integer.valueOf(175));
/*     */           var0.put("minecraft:standing_banner", Integer.valueOf(176));
/*     */           var0.put("minecraft:wall_banner", Integer.valueOf(177));
/*     */           var0.put("minecraft:daylight_detector_inverted", Integer.valueOf(178));
/*     */           var0.put("minecraft:red_sandstone", Integer.valueOf(179));
/*     */           var0.put("minecraft:red_sandstone_stairs", Integer.valueOf(180));
/*     */           var0.put("minecraft:double_stone_slab2", Integer.valueOf(181));
/*     */           var0.put("minecraft:stone_slab2", Integer.valueOf(182));
/*     */           var0.put("minecraft:spruce_fence_gate", Integer.valueOf(183));
/*     */           var0.put("minecraft:birch_fence_gate", Integer.valueOf(184));
/*     */           var0.put("minecraft:jungle_fence_gate", Integer.valueOf(185));
/*     */           var0.put("minecraft:dark_oak_fence_gate", Integer.valueOf(186));
/*     */           var0.put("minecraft:acacia_fence_gate", Integer.valueOf(187));
/*     */           var0.put("minecraft:spruce_fence", Integer.valueOf(188));
/*     */           var0.put("minecraft:birch_fence", Integer.valueOf(189));
/*     */           var0.put("minecraft:jungle_fence", Integer.valueOf(190));
/*     */           var0.put("minecraft:dark_oak_fence", Integer.valueOf(191));
/*     */           var0.put("minecraft:acacia_fence", Integer.valueOf(192));
/*     */           var0.put("minecraft:spruce_door", Integer.valueOf(193));
/*     */           var0.put("minecraft:birch_door", Integer.valueOf(194));
/*     */           var0.put("minecraft:jungle_door", Integer.valueOf(195));
/*     */           var0.put("minecraft:acacia_door", Integer.valueOf(196));
/*     */           var0.put("minecraft:dark_oak_door", Integer.valueOf(197));
/*     */           var0.put("minecraft:end_rod", Integer.valueOf(198));
/*     */           var0.put("minecraft:chorus_plant", Integer.valueOf(199));
/*     */           var0.put("minecraft:chorus_flower", Integer.valueOf(200));
/*     */           var0.put("minecraft:purpur_block", Integer.valueOf(201));
/*     */           var0.put("minecraft:purpur_pillar", Integer.valueOf(202));
/*     */           var0.put("minecraft:purpur_stairs", Integer.valueOf(203));
/*     */           var0.put("minecraft:purpur_double_slab", Integer.valueOf(204));
/*     */           var0.put("minecraft:purpur_slab", Integer.valueOf(205));
/*     */           var0.put("minecraft:end_bricks", Integer.valueOf(206));
/*     */           var0.put("minecraft:beetroots", Integer.valueOf(207));
/*     */           var0.put("minecraft:grass_path", Integer.valueOf(208));
/*     */           var0.put("minecraft:end_gateway", Integer.valueOf(209));
/*     */           var0.put("minecraft:repeating_command_block", Integer.valueOf(210));
/*     */           var0.put("minecraft:chain_command_block", Integer.valueOf(211));
/*     */           var0.put("minecraft:frosted_ice", Integer.valueOf(212));
/*     */           var0.put("minecraft:magma", Integer.valueOf(213));
/*     */           var0.put("minecraft:nether_wart_block", Integer.valueOf(214));
/*     */           var0.put("minecraft:red_nether_brick", Integer.valueOf(215));
/*     */           var0.put("minecraft:bone_block", Integer.valueOf(216));
/*     */           var0.put("minecraft:structure_void", Integer.valueOf(217));
/*     */           var0.put("minecraft:observer", Integer.valueOf(218));
/*     */           var0.put("minecraft:white_shulker_box", Integer.valueOf(219));
/*     */           var0.put("minecraft:orange_shulker_box", Integer.valueOf(220));
/*     */           var0.put("minecraft:magenta_shulker_box", Integer.valueOf(221));
/*     */           var0.put("minecraft:light_blue_shulker_box", Integer.valueOf(222));
/*     */           var0.put("minecraft:yellow_shulker_box", Integer.valueOf(223));
/*     */           var0.put("minecraft:lime_shulker_box", Integer.valueOf(224));
/*     */           var0.put("minecraft:pink_shulker_box", Integer.valueOf(225));
/*     */           var0.put("minecraft:gray_shulker_box", Integer.valueOf(226));
/*     */           var0.put("minecraft:silver_shulker_box", Integer.valueOf(227));
/*     */           var0.put("minecraft:cyan_shulker_box", Integer.valueOf(228));
/*     */           var0.put("minecraft:purple_shulker_box", Integer.valueOf(229));
/*     */           var0.put("minecraft:blue_shulker_box", Integer.valueOf(230));
/*     */           var0.put("minecraft:brown_shulker_box", Integer.valueOf(231));
/*     */           var0.put("minecraft:green_shulker_box", Integer.valueOf(232));
/*     */           var0.put("minecraft:red_shulker_box", Integer.valueOf(233));
/*     */           var0.put("minecraft:black_shulker_box", Integer.valueOf(234));
/*     */           var0.put("minecraft:white_glazed_terracotta", Integer.valueOf(235));
/*     */           var0.put("minecraft:orange_glazed_terracotta", Integer.valueOf(236));
/*     */           var0.put("minecraft:magenta_glazed_terracotta", Integer.valueOf(237));
/*     */           var0.put("minecraft:light_blue_glazed_terracotta", Integer.valueOf(238));
/*     */           var0.put("minecraft:yellow_glazed_terracotta", Integer.valueOf(239));
/*     */           var0.put("minecraft:lime_glazed_terracotta", Integer.valueOf(240));
/*     */           var0.put("minecraft:pink_glazed_terracotta", Integer.valueOf(241));
/*     */           var0.put("minecraft:gray_glazed_terracotta", Integer.valueOf(242));
/*     */           var0.put("minecraft:silver_glazed_terracotta", Integer.valueOf(243));
/*     */           var0.put("minecraft:cyan_glazed_terracotta", Integer.valueOf(244));
/*     */           var0.put("minecraft:purple_glazed_terracotta", Integer.valueOf(245));
/*     */           var0.put("minecraft:blue_glazed_terracotta", Integer.valueOf(246));
/*     */           var0.put("minecraft:brown_glazed_terracotta", Integer.valueOf(247));
/*     */           var0.put("minecraft:green_glazed_terracotta", Integer.valueOf(248));
/*     */           var0.put("minecraft:red_glazed_terracotta", Integer.valueOf(249));
/*     */           var0.put("minecraft:black_glazed_terracotta", Integer.valueOf(250));
/*     */           var0.put("minecraft:concrete", Integer.valueOf(251));
/*     */           var0.put("minecraft:concrete_powder", Integer.valueOf(252));
/*     */           var0.put("minecraft:structure_block", Integer.valueOf(255));
/*     */         });
/*     */   }
/*     */   public static int a(String var0) {
/* 295 */     Integer var1 = a.get(var0);
/* 296 */     return (var1 == null) ? 0 : var1.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeRewriteRule makeRule() {
/* 301 */     Schema var0 = getInputSchema();
/* 302 */     Schema var1 = getOutputSchema();
/*     */     
/* 304 */     Function<Typed<?>, Typed<?>> var2 = var0 -> a(var0, "DisplayTile", "DisplayData", "DisplayState");
/* 305 */     Function<Typed<?>, Typed<?>> var3 = var0 -> a(var0, "inTile", "inData", "inBlockState");
/*     */     
/* 307 */     Type<Pair<Either<Pair<String, Either<Integer, String>>, Unit>, Dynamic<?>>> var4 = DSL.and(
/* 308 */         DSL.optional((Type)DSL.field("inTile", DSL.named(DataConverterTypes.BLOCK_NAME.typeName(), DSL.or(DSL.intType(), DataConverterSchemaNamed.a())))), 
/* 309 */         DSL.remainderType());
/*     */ 
/*     */     
/* 312 */     Function<Typed<?>, Typed<?>> var5 = var1 -> var1.update(var0.finder(), DSL.remainderType(), Pair::getSecond);
/*     */     
/* 314 */     return fixTypeEverywhereTyped("EntityBlockStateFix", var0.getType(DataConverterTypes.ENTITY), var1.getType(DataConverterTypes.ENTITY), var3 -> {
/*     */           var3 = a(var3, "minecraft:falling_block", this::a);
/*     */           var3 = a(var3, "minecraft:enderman", ());
/*     */           var3 = a(var3, "minecraft:arrow", var0);
/*     */           var3 = a(var3, "minecraft:spectral_arrow", var0);
/*     */           var3 = a(var3, "minecraft:egg", var1);
/*     */           var3 = a(var3, "minecraft:ender_pearl", var1);
/*     */           var3 = a(var3, "minecraft:fireball", var1);
/*     */           var3 = a(var3, "minecraft:potion", var1);
/*     */           var3 = a(var3, "minecraft:small_fireball", var1);
/*     */           var3 = a(var3, "minecraft:snowball", var1);
/*     */           var3 = a(var3, "minecraft:wither_skull", var1);
/*     */           var3 = a(var3, "minecraft:xp_bottle", var1);
/*     */           var3 = a(var3, "minecraft:commandblock_minecart", var2);
/*     */           var3 = a(var3, "minecraft:minecart", var2);
/*     */           var3 = a(var3, "minecraft:chest_minecart", var2);
/*     */           var3 = a(var3, "minecraft:furnace_minecart", var2);
/*     */           var3 = a(var3, "minecraft:tnt_minecart", var2);
/*     */           var3 = a(var3, "minecraft:hopper_minecart", var2);
/*     */           return a(var3, "minecraft:spawner_minecart", var2);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private Typed<?> a(Typed<?> var0) {
/* 339 */     Type<Either<Pair<String, Either<Integer, String>>, Unit>> var1 = DSL.optional((Type)DSL.field("Block", DSL.named(DataConverterTypes.BLOCK_NAME.typeName(), DSL.or(DSL.intType(), DataConverterSchemaNamed.a()))));
/* 340 */     Type<Either<Pair<String, Dynamic<?>>, Unit>> var2 = DSL.optional((Type)DSL.field("BlockState", DSL.named(DataConverterTypes.BLOCK_STATE.typeName(), DSL.remainderType())));
/*     */     
/* 342 */     Dynamic<?> var3 = (Dynamic)var0.get(DSL.remainderFinder());
/*     */     
/* 344 */     return var0.update(var1.finder(), var2, var1 -> {
/*     */           int var2 = ((Integer)var1.map((), ())).intValue();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           int var3 = var0.get("Data").asInt(0) & 0xF;
/*     */ 
/*     */ 
/*     */           
/*     */           return Either.left(Pair.of(DataConverterTypes.BLOCK_STATE.typeName(), DataConverterFlattenData.b(var2 << 4 | var3)));
/* 355 */         }).set(DSL.remainderFinder(), var3.remove("Data").remove("TileID").remove("Tile"));
/*     */   }
/*     */   
/*     */   private Typed<?> a(Typed<?> var0, String var1, String var2, String var3) {
/* 359 */     Tag.TagType tagType1 = DSL.field(var1, DSL.named(DataConverterTypes.BLOCK_NAME.typeName(), DSL.or(DSL.intType(), DataConverterSchemaNamed.a())));
/* 360 */     Tag.TagType tagType2 = DSL.field(var3, DSL.named(DataConverterTypes.BLOCK_STATE.typeName(), DSL.remainderType()));
/*     */     
/* 362 */     Dynamic<?> var6 = (Dynamic)var0.getOrCreate(DSL.remainderFinder());
/*     */     
/* 364 */     return var0.update(tagType1.finder(), (Type)tagType2, var2 -> {
/*     */           int var3 = ((Integer)((Either)var2.getSecond()).map((), DataConverterEntityBlockState::a)).intValue();
/*     */           
/*     */           int var4 = var0.get(var1).asInt(0) & 0xF;
/*     */           return Pair.of(DataConverterTypes.BLOCK_STATE.typeName(), DataConverterFlattenData.b(var3 << 4 | var4));
/* 369 */         }).set(DSL.remainderFinder(), var6.remove(var2));
/*     */   }
/*     */   
/*     */   private Typed<?> a(Typed<?> var0, String var1, Function<Typed<?>, Typed<?>> var2) {
/* 373 */     Type<?> var3 = getInputSchema().getChoiceType(DataConverterTypes.ENTITY, var1);
/* 374 */     Type<?> var4 = getOutputSchema().getChoiceType(DataConverterTypes.ENTITY, var1);
/* 375 */     return var0.updateTyped(DSL.namedChoice(var1, var3), var4, var2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */