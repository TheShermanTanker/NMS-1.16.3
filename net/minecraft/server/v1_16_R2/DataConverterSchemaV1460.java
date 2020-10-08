/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.templates.Hook;
/*     */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterSchemaV1460
/*     */   extends DataConverterSchemaNamed
/*     */ {
/*     */   public DataConverterSchemaV1460(int var0, Schema var1) {
/*  50 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  54 */     var0.register(var1, var2, () -> DataConverterSchemaV100.a(var0));
/*     */   }
/*     */   
/*     */   protected static void b(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  58 */     var0.register(var1, var2, () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/*  65 */     Map<String, Supplier<TypeTemplate>> var1 = Maps.newHashMap();
/*     */     
/*  67 */     var0.registerSimple(var1, "minecraft:area_effect_cloud");
/*  68 */     a(var0, var1, "minecraft:armor_stand");
/*  69 */     var0.register(var1, "minecraft:arrow", var1 -> DSL.optionalFields("inBlockState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*     */ 
/*     */     
/*  72 */     a(var0, var1, "minecraft:bat");
/*  73 */     a(var0, var1, "minecraft:blaze");
/*  74 */     var0.registerSimple(var1, "minecraft:boat");
/*  75 */     a(var0, var1, "minecraft:cave_spider");
/*  76 */     var0.register(var1, "minecraft:chest_minecart", var1 -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */ 
/*     */ 
/*     */     
/*  80 */     a(var0, var1, "minecraft:chicken");
/*  81 */     var0.register(var1, "minecraft:commandblock_minecart", var1 -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*     */ 
/*     */     
/*  84 */     a(var0, var1, "minecraft:cow");
/*  85 */     a(var0, var1, "minecraft:creeper");
/*  86 */     var0.register(var1, "minecraft:donkey", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     var0.registerSimple(var1, "minecraft:dragon_fireball");
/*  92 */     var0.registerSimple(var1, "minecraft:egg");
/*  93 */     a(var0, var1, "minecraft:elder_guardian");
/*  94 */     var0.registerSimple(var1, "minecraft:ender_crystal");
/*  95 */     a(var0, var1, "minecraft:ender_dragon");
/*  96 */     var0.register(var1, "minecraft:enderman", var1 -> DSL.optionalFields("carriedBlockState", DataConverterTypes.BLOCK_STATE.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */     
/* 100 */     a(var0, var1, "minecraft:endermite");
/* 101 */     var0.registerSimple(var1, "minecraft:ender_pearl");
/* 102 */     var0.registerSimple(var1, "minecraft:evocation_fangs");
/* 103 */     a(var0, var1, "minecraft:evocation_illager");
/* 104 */     var0.registerSimple(var1, "minecraft:eye_of_ender_signal");
/* 105 */     var0.register(var1, "minecraft:falling_block", var1 -> DSL.optionalFields("BlockState", DataConverterTypes.BLOCK_STATE.in(var0), "TileEntityData", DataConverterTypes.BLOCK_ENTITY.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 109 */     var0.registerSimple(var1, "minecraft:fireball");
/* 110 */     var0.register(var1, "minecraft:fireworks_rocket", var1 -> DSL.optionalFields("FireworksItem", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 113 */     var0.register(var1, "minecraft:furnace_minecart", var1 -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*     */ 
/*     */     
/* 116 */     a(var0, var1, "minecraft:ghast");
/* 117 */     a(var0, var1, "minecraft:giant");
/* 118 */     a(var0, var1, "minecraft:guardian");
/* 119 */     var0.register(var1, "minecraft:hopper_minecart", var1 -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */ 
/*     */ 
/*     */     
/* 123 */     var0.register(var1, "minecraft:horse", var1 -> DSL.optionalFields("ArmorItem", DataConverterTypes.ITEM_STACK.in(var0), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     a(var0, var1, "minecraft:husk");
/* 130 */     var0.registerSimple(var1, "minecraft:illusion_illager");
/* 131 */     var0.register(var1, "minecraft:item", var1 -> DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 134 */     var0.register(var1, "minecraft:item_frame", var1 -> DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 137 */     var0.registerSimple(var1, "minecraft:leash_knot");
/* 138 */     var0.register(var1, "minecraft:llama", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), "DecorItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     var0.registerSimple(var1, "minecraft:llama_spit");
/* 145 */     a(var0, var1, "minecraft:magma_cube");
/* 146 */     var0.register(var1, "minecraft:minecart", var1 -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*     */ 
/*     */     
/* 149 */     a(var0, var1, "minecraft:mooshroom");
/* 150 */     var0.register(var1, "minecraft:mule", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     a(var0, var1, "minecraft:ocelot");
/* 156 */     var0.registerSimple(var1, "minecraft:painting");
/* 157 */     var0.registerSimple(var1, "minecraft:parrot");
/* 158 */     a(var0, var1, "minecraft:pig");
/* 159 */     a(var0, var1, "minecraft:polar_bear");
/* 160 */     var0.register(var1, "minecraft:potion", var1 -> DSL.optionalFields("Potion", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 163 */     a(var0, var1, "minecraft:rabbit");
/* 164 */     a(var0, var1, "minecraft:sheep");
/* 165 */     a(var0, var1, "minecraft:shulker");
/* 166 */     var0.registerSimple(var1, "minecraft:shulker_bullet");
/* 167 */     a(var0, var1, "minecraft:silverfish");
/* 168 */     a(var0, var1, "minecraft:skeleton");
/* 169 */     var0.register(var1, "minecraft:skeleton_horse", var1 -> DSL.optionalFields("SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */     
/* 173 */     a(var0, var1, "minecraft:slime");
/* 174 */     var0.registerSimple(var1, "minecraft:small_fireball");
/* 175 */     var0.registerSimple(var1, "minecraft:snowball");
/* 176 */     a(var0, var1, "minecraft:snowman");
/* 177 */     var0.register(var1, "minecraft:spawner_minecart", var1 -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0), DataConverterTypes.UNTAGGED_SPAWNER.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 181 */     var0.register(var1, "minecraft:spectral_arrow", var1 -> DSL.optionalFields("inBlockState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*     */ 
/*     */     
/* 184 */     a(var0, var1, "minecraft:spider");
/* 185 */     a(var0, var1, "minecraft:squid");
/* 186 */     a(var0, var1, "minecraft:stray");
/* 187 */     var0.registerSimple(var1, "minecraft:tnt");
/* 188 */     var0.register(var1, "minecraft:tnt_minecart", var1 -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*     */ 
/*     */     
/* 191 */     a(var0, var1, "minecraft:vex");
/* 192 */     var0.register(var1, "minecraft:villager", var1 -> DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "Offers", DSL.optionalFields("Recipes", DSL.list(DSL.optionalFields("buy", DataConverterTypes.ITEM_STACK.in(var0), "buyB", DataConverterTypes.ITEM_STACK.in(var0), "sell", DataConverterTypes.ITEM_STACK.in(var0)))), DataConverterSchemaV100.a(var0)));
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
/* 205 */     a(var0, var1, "minecraft:villager_golem");
/* 206 */     a(var0, var1, "minecraft:vindication_illager");
/* 207 */     a(var0, var1, "minecraft:witch");
/* 208 */     a(var0, var1, "minecraft:wither");
/* 209 */     a(var0, var1, "minecraft:wither_skeleton");
/* 210 */     var0.registerSimple(var1, "minecraft:wither_skull");
/* 211 */     a(var0, var1, "minecraft:wolf");
/* 212 */     var0.registerSimple(var1, "minecraft:xp_bottle");
/* 213 */     var0.registerSimple(var1, "minecraft:xp_orb");
/* 214 */     a(var0, var1, "minecraft:zombie");
/* 215 */     var0.register(var1, "minecraft:zombie_horse", var1 -> DSL.optionalFields("SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */     
/* 219 */     a(var0, var1, "minecraft:zombie_pigman");
/* 220 */     a(var0, var1, "minecraft:zombie_villager");
/*     */     
/* 222 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/* 227 */     Map<String, Supplier<TypeTemplate>> var1 = Maps.newHashMap();
/*     */     
/* 229 */     b(var0, var1, "minecraft:furnace");
/* 230 */     b(var0, var1, "minecraft:chest");
/* 231 */     b(var0, var1, "minecraft:trapped_chest");
/* 232 */     var0.registerSimple(var1, "minecraft:ender_chest");
/* 233 */     var0.register(var1, "minecraft:jukebox", var1 -> DSL.optionalFields("RecordItem", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 236 */     b(var0, var1, "minecraft:dispenser");
/* 237 */     b(var0, var1, "minecraft:dropper");
/* 238 */     var0.registerSimple(var1, "minecraft:sign");
/* 239 */     var0.register(var1, "minecraft:mob_spawner", var1 -> DataConverterTypes.UNTAGGED_SPAWNER.in(var0));
/* 240 */     var0.register(var1, "minecraft:piston", var1 -> DSL.optionalFields("blockState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*     */ 
/*     */     
/* 243 */     b(var0, var1, "minecraft:brewing_stand");
/* 244 */     var0.registerSimple(var1, "minecraft:enchanting_table");
/* 245 */     var0.registerSimple(var1, "minecraft:end_portal");
/* 246 */     var0.registerSimple(var1, "minecraft:beacon");
/* 247 */     var0.registerSimple(var1, "minecraft:skull");
/* 248 */     var0.registerSimple(var1, "minecraft:daylight_detector");
/* 249 */     b(var0, var1, "minecraft:hopper");
/* 250 */     var0.registerSimple(var1, "minecraft:comparator");
/* 251 */     var0.registerSimple(var1, "minecraft:banner");
/* 252 */     var0.registerSimple(var1, "minecraft:structure_block");
/* 253 */     var0.registerSimple(var1, "minecraft:end_gateway");
/* 254 */     var0.registerSimple(var1, "minecraft:command_block");
/* 255 */     b(var0, var1, "minecraft:shulker_box");
/* 256 */     var0.registerSimple(var1, "minecraft:bed");
/*     */     
/* 258 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 263 */     var0.registerType(false, DataConverterTypes.LEVEL, DSL::remainder);
/* 264 */     var0.registerType(false, DataConverterTypes.RECIPE, () -> DSL.constType(a()));
/* 265 */     var0.registerType(false, DataConverterTypes.PLAYER, () -> DSL.optionalFields("RootVehicle", DSL.optionalFields("Entity", DataConverterTypes.ENTITY_TREE.in(var0)), "Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "EnderItems", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), DSL.optionalFields("ShoulderEntityLeft", DataConverterTypes.ENTITY_TREE.in(var0), "ShoulderEntityRight", DataConverterTypes.ENTITY_TREE.in(var0), "recipeBook", DSL.optionalFields("recipes", DSL.list(DataConverterTypes.RECIPE.in(var0)), "toBeDisplayed", DSL.list(DataConverterTypes.RECIPE.in(var0))))));
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
/* 282 */     var0.registerType(false, DataConverterTypes.CHUNK, () -> DSL.fields("Level", DSL.optionalFields("Entities", DSL.list(DataConverterTypes.ENTITY_TREE.in(var0)), "TileEntities", DSL.list(DataConverterTypes.BLOCK_ENTITY.in(var0)), "TileTicks", DSL.list(DSL.fields("i", DataConverterTypes.BLOCK_NAME.in(var0))), "Sections", DSL.list(DSL.optionalFields("Palette", DSL.list(DataConverterTypes.BLOCK_STATE.in(var0)))))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 292 */     var0.registerType(true, DataConverterTypes.BLOCK_ENTITY, () -> DSL.taggedChoiceLazy("id", a(), var0));
/* 293 */     var0.registerType(true, DataConverterTypes.ENTITY_TREE, () -> DSL.optionalFields("Passengers", DSL.list(DataConverterTypes.ENTITY_TREE.in(var0)), DataConverterTypes.ENTITY.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 297 */     var0.registerType(true, DataConverterTypes.ENTITY, () -> DSL.taggedChoiceLazy("id", a(), var0));
/* 298 */     var0.registerType(true, DataConverterTypes.ITEM_STACK, () -> DSL.hook(DSL.optionalFields("id", DataConverterTypes.ITEM_NAME.in(var0), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.ENTITY_TREE.in(var0), "BlockEntityTag", DataConverterTypes.BLOCK_ENTITY.in(var0), "CanDestroy", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)), "CanPlaceOn", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)))), DataConverterSchemaV705.b, Hook.HookFunction.IDENTITY));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 307 */     var0.registerType(false, DataConverterTypes.HOTBAR, () -> DSL.compoundList(DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/* 308 */     var0.registerType(false, DataConverterTypes.OPTIONS, DSL::remainder);
/* 309 */     var0.registerType(false, DataConverterTypes.STRUCTURE, () -> DSL.optionalFields("entities", DSL.list(DSL.optionalFields("nbt", DataConverterTypes.ENTITY_TREE.in(var0))), "blocks", DSL.list(DSL.optionalFields("nbt", DataConverterTypes.BLOCK_ENTITY.in(var0))), "palette", DSL.list(DataConverterTypes.BLOCK_STATE.in(var0))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 314 */     var0.registerType(false, DataConverterTypes.BLOCK_NAME, () -> DSL.constType(a()));
/* 315 */     var0.registerType(false, DataConverterTypes.ITEM_NAME, () -> DSL.constType(a()));
/* 316 */     var0.registerType(false, DataConverterTypes.BLOCK_STATE, DSL::remainder);
/* 317 */     Supplier<TypeTemplate> var3 = () -> DSL.compoundList(DataConverterTypes.ITEM_NAME.in(var0), DSL.constType(DSL.intType()));
/*     */     
/* 319 */     var0.registerType(false, DataConverterTypes.STATS, () -> DSL.optionalFields("stats", DSL.optionalFields("minecraft:mined", DSL.compoundList(DataConverterTypes.BLOCK_NAME.in(var0), DSL.constType(DSL.intType())), "minecraft:crafted", var1.get(), "minecraft:used", var1.get(), "minecraft:broken", var1.get(), "minecraft:picked_up", var1.get(), DSL.optionalFields("minecraft:dropped", var1.get(), "minecraft:killed", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.intType())), "minecraft:killed_by", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.intType())), "minecraft:custom", DSL.compoundList(DSL.constType(a()), DSL.constType(DSL.intType()))))));
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
/* 334 */     var0.registerType(false, DataConverterTypes.SAVED_DATA, () -> DSL.optionalFields("data", DSL.optionalFields("Features", DSL.compoundList(DataConverterTypes.STRUCTURE_FEATURE.in(var0)), "Objectives", DSL.list(DataConverterTypes.OBJECTIVE.in(var0)), "Teams", DSL.list(DataConverterTypes.TEAM.in(var0)))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 341 */     var0.registerType(false, DataConverterTypes.STRUCTURE_FEATURE, () -> DSL.optionalFields("Children", DSL.list(DSL.optionalFields("CA", DataConverterTypes.BLOCK_STATE.in(var0), "CB", DataConverterTypes.BLOCK_STATE.in(var0), "CC", DataConverterTypes.BLOCK_STATE.in(var0), "CD", DataConverterTypes.BLOCK_STATE.in(var0)))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 349 */     var0.registerType(false, DataConverterTypes.OBJECTIVE, DSL::remainder);
/* 350 */     var0.registerType(false, DataConverterTypes.TEAM, DSL::remainder);
/* 351 */     var0.registerType(true, DataConverterTypes.UNTAGGED_SPAWNER, () -> DSL.optionalFields("SpawnPotentials", DSL.list(DSL.fields("Entity", DataConverterTypes.ENTITY_TREE.in(var0))), "SpawnData", DataConverterTypes.ENTITY_TREE.in(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 357 */     var0.registerType(false, DataConverterTypes.ADVANCEMENTS, () -> DSL.optionalFields("minecraft:adventure/adventuring_time", DSL.optionalFields("criteria", DSL.compoundList(DataConverterTypes.BIOME.in(var0), DSL.constType(DSL.string()))), "minecraft:adventure/kill_a_mob", DSL.optionalFields("criteria", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.string()))), "minecraft:adventure/kill_all_mobs", DSL.optionalFields("criteria", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.string()))), "minecraft:husbandry/bred_all_animals", DSL.optionalFields("criteria", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.string())))));
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
/* 371 */     var0.registerType(false, DataConverterTypes.BIOME, () -> DSL.constType(a()));
/* 372 */     var0.registerType(false, DataConverterTypes.ENTITY_NAME, () -> DSL.constType(a()));
/* 373 */     var0.registerType(false, DataConverterTypes.POI_CHUNK, DSL::remainder);
/* 374 */     var0.registerType(true, DataConverterTypes.WORLD_GEN_SETTINGS, DSL::remainder);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1460.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */