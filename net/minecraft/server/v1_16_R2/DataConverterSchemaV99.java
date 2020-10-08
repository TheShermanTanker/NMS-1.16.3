/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.templates.Hook;
/*     */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Supplier;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class DataConverterSchemaV99
/*     */   extends Schema
/*     */ {
/*  51 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public DataConverterSchemaV99(int var0, Schema var1) {
/*  54 */     super(var0, var1);
/*     */   }
/*     */   private static final Map<String, String> c;
/*     */   protected static TypeTemplate a(Schema var0) {
/*  58 */     return DSL.optionalFields("Equipment", 
/*  59 */         DSL.list(DataConverterTypes.ITEM_STACK.in(var0)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  64 */     var0.register(var1, var2, () -> a(var0));
/*     */   }
/*     */   
/*     */   protected static void b(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  68 */     var0.register(var1, var2, () -> DSL.optionalFields("inTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void c(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  75 */     var0.register(var1, var2, () -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void d(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  81 */     var0.register(var1, var2, () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/*  88 */     Map<String, Supplier<TypeTemplate>> var1 = Maps.newHashMap();
/*     */     
/*  90 */     var0.register(var1, "Item", var1 -> DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/*  93 */     var0.registerSimple(var1, "XPOrb");
/*  94 */     b(var0, var1, "ThrownEgg");
/*  95 */     var0.registerSimple(var1, "LeashKnot");
/*  96 */     var0.registerSimple(var1, "Painting");
/*  97 */     var0.register(var1, "Arrow", var1 -> DSL.optionalFields("inTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/* 100 */     var0.register(var1, "TippedArrow", var1 -> DSL.optionalFields("inTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/* 103 */     var0.register(var1, "SpectralArrow", var1 -> DSL.optionalFields("inTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/* 106 */     b(var0, var1, "Snowball");
/* 107 */     b(var0, var1, "Fireball");
/* 108 */     b(var0, var1, "SmallFireball");
/* 109 */     b(var0, var1, "ThrownEnderpearl");
/* 110 */     var0.registerSimple(var1, "EyeOfEnderSignal");
/* 111 */     var0.register(var1, "ThrownPotion", var1 -> DSL.optionalFields("inTile", DataConverterTypes.BLOCK_NAME.in(var0), "Potion", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 115 */     b(var0, var1, "ThrownExpBottle");
/* 116 */     var0.register(var1, "ItemFrame", var1 -> DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 119 */     b(var0, var1, "WitherSkull");
/* 120 */     var0.registerSimple(var1, "PrimedTnt");
/* 121 */     var0.register(var1, "FallingSand", var1 -> DSL.optionalFields("Block", DataConverterTypes.BLOCK_NAME.in(var0), "TileEntityData", DataConverterTypes.BLOCK_ENTITY.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 125 */     var0.register(var1, "FireworksRocketEntity", var1 -> DSL.optionalFields("FireworksItem", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 128 */     var0.registerSimple(var1, "Boat");
/*     */ 
/*     */     
/* 131 */     var0.register(var1, "Minecart", () -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */ 
/*     */ 
/*     */     
/* 135 */     c(var0, var1, "MinecartRideable");
/* 136 */     var0.register(var1, "MinecartChest", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */ 
/*     */ 
/*     */     
/* 140 */     c(var0, var1, "MinecartFurnace");
/* 141 */     c(var0, var1, "MinecartTNT");
/* 142 */     var0.register(var1, "MinecartSpawner", () -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0), DataConverterTypes.UNTAGGED_SPAWNER.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 146 */     var0.register(var1, "MinecartHopper", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */ 
/*     */ 
/*     */     
/* 150 */     c(var0, var1, "MinecartCommandBlock");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     a(var0, var1, "ArmorStand");
/* 156 */     a(var0, var1, "Creeper");
/* 157 */     a(var0, var1, "Skeleton");
/* 158 */     a(var0, var1, "Spider");
/* 159 */     a(var0, var1, "Giant");
/* 160 */     a(var0, var1, "Zombie");
/* 161 */     a(var0, var1, "Slime");
/* 162 */     a(var0, var1, "Ghast");
/* 163 */     a(var0, var1, "PigZombie");
/* 164 */     var0.register(var1, "Enderman", var1 -> DSL.optionalFields("carried", DataConverterTypes.BLOCK_NAME.in(var0), a(var0)));
/*     */ 
/*     */ 
/*     */     
/* 168 */     a(var0, var1, "CaveSpider");
/* 169 */     a(var0, var1, "Silverfish");
/* 170 */     a(var0, var1, "Blaze");
/* 171 */     a(var0, var1, "LavaSlime");
/* 172 */     a(var0, var1, "EnderDragon");
/* 173 */     a(var0, var1, "WitherBoss");
/* 174 */     a(var0, var1, "Bat");
/* 175 */     a(var0, var1, "Witch");
/* 176 */     a(var0, var1, "Endermite");
/* 177 */     a(var0, var1, "Guardian");
/* 178 */     a(var0, var1, "Pig");
/* 179 */     a(var0, var1, "Sheep");
/* 180 */     a(var0, var1, "Cow");
/* 181 */     a(var0, var1, "Chicken");
/* 182 */     a(var0, var1, "Squid");
/* 183 */     a(var0, var1, "Wolf");
/* 184 */     a(var0, var1, "MushroomCow");
/* 185 */     a(var0, var1, "SnowMan");
/* 186 */     a(var0, var1, "Ozelot");
/* 187 */     a(var0, var1, "VillagerGolem");
/* 188 */     var0.register(var1, "EntityHorse", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "ArmorItem", DataConverterTypes.ITEM_STACK.in(var0), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     a(var0, var1, "Rabbit");
/* 195 */     var0.register(var1, "Villager", var1 -> DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "Offers", DSL.optionalFields("Recipes", DSL.list(DSL.optionalFields("buy", DataConverterTypes.ITEM_STACK.in(var0), "buyB", DataConverterTypes.ITEM_STACK.in(var0), "sell", DataConverterTypes.ITEM_STACK.in(var0)))), a(var0)));
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
/* 208 */     var0.registerSimple(var1, "EnderCrystal");
/*     */ 
/*     */ 
/*     */     
/* 212 */     var0.registerSimple(var1, "AreaEffectCloud");
/* 213 */     var0.registerSimple(var1, "ShulkerBullet");
/* 214 */     a(var0, var1, "Shulker");
/*     */     
/* 216 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/* 221 */     Map<String, Supplier<TypeTemplate>> var1 = Maps.newHashMap();
/*     */     
/* 223 */     d(var0, var1, "Furnace");
/* 224 */     d(var0, var1, "Chest");
/* 225 */     var0.registerSimple(var1, "EnderChest");
/* 226 */     var0.register(var1, "RecordPlayer", var1 -> DSL.optionalFields("RecordItem", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 229 */     d(var0, var1, "Trap");
/* 230 */     d(var0, var1, "Dropper");
/* 231 */     var0.registerSimple(var1, "Sign");
/* 232 */     var0.register(var1, "MobSpawner", var1 -> DataConverterTypes.UNTAGGED_SPAWNER.in(var0));
/* 233 */     var0.registerSimple(var1, "Music");
/* 234 */     var0.registerSimple(var1, "Piston");
/* 235 */     d(var0, var1, "Cauldron");
/* 236 */     var0.registerSimple(var1, "EnchantTable");
/* 237 */     var0.registerSimple(var1, "Airportal");
/* 238 */     var0.registerSimple(var1, "Control");
/* 239 */     var0.registerSimple(var1, "Beacon");
/* 240 */     var0.registerSimple(var1, "Skull");
/* 241 */     var0.registerSimple(var1, "DLDetector");
/* 242 */     d(var0, var1, "Hopper");
/* 243 */     var0.registerSimple(var1, "Comparator");
/* 244 */     var0.register(var1, "FlowerPot", var1 -> DSL.optionalFields("Item", DSL.or(DSL.constType(DSL.intType()), DataConverterTypes.ITEM_NAME.in(var0))));
/*     */ 
/*     */     
/* 247 */     var0.registerSimple(var1, "Banner");
/*     */ 
/*     */ 
/*     */     
/* 251 */     var0.registerSimple(var1, "Structure");
/* 252 */     var0.registerSimple(var1, "EndGateway");
/* 253 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 258 */     var0.registerType(false, DataConverterTypes.LEVEL, DSL::remainder);
/* 259 */     var0.registerType(false, DataConverterTypes.PLAYER, () -> DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "EnderItems", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */ 
/*     */ 
/*     */     
/* 263 */     var0.registerType(false, DataConverterTypes.CHUNK, () -> DSL.fields("Level", DSL.optionalFields("Entities", DSL.list(DataConverterTypes.ENTITY_TREE.in(var0)), "TileEntities", DSL.list(DataConverterTypes.BLOCK_ENTITY.in(var0)), "TileTicks", DSL.list(DSL.fields("i", DataConverterTypes.BLOCK_NAME.in(var0))))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 270 */     var0.registerType(true, DataConverterTypes.BLOCK_ENTITY, () -> DSL.taggedChoiceLazy("id", DSL.string(), var0));
/* 271 */     var0.registerType(true, DataConverterTypes.ENTITY_TREE, () -> DSL.optionalFields("Riding", DataConverterTypes.ENTITY_TREE.in(var0), DataConverterTypes.ENTITY.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 275 */     var0.registerType(false, DataConverterTypes.ENTITY_NAME, () -> DSL.constType(DataConverterSchemaNamed.a()));
/* 276 */     var0.registerType(true, DataConverterTypes.ENTITY, () -> DSL.taggedChoiceLazy("id", DSL.string(), var0));
/* 277 */     var0.registerType(true, DataConverterTypes.ITEM_STACK, () -> DSL.hook(DSL.optionalFields("id", DSL.or(DSL.constType(DSL.intType()), DataConverterTypes.ITEM_NAME.in(var0)), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.ENTITY_TREE.in(var0), "BlockEntityTag", DataConverterTypes.BLOCK_ENTITY.in(var0), "CanDestroy", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)), "CanPlaceOn", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)))), a, Hook.HookFunction.IDENTITY));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     var0.registerType(false, DataConverterTypes.OPTIONS, DSL::remainder);
/* 287 */     var0.registerType(false, DataConverterTypes.BLOCK_NAME, () -> DSL.or(DSL.constType(DSL.intType()), DSL.constType(DataConverterSchemaNamed.a())));
/* 288 */     var0.registerType(false, DataConverterTypes.ITEM_NAME, () -> DSL.constType(DataConverterSchemaNamed.a()));
/* 289 */     var0.registerType(false, DataConverterTypes.STATS, DSL::remainder);
/* 290 */     var0.registerType(false, DataConverterTypes.SAVED_DATA, () -> DSL.optionalFields("data", DSL.optionalFields("Features", DSL.compoundList(DataConverterTypes.STRUCTURE_FEATURE.in(var0)), "Objectives", DSL.list(DataConverterTypes.OBJECTIVE.in(var0)), "Teams", DSL.list(DataConverterTypes.TEAM.in(var0)))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 297 */     var0.registerType(false, DataConverterTypes.STRUCTURE_FEATURE, DSL::remainder);
/* 298 */     var0.registerType(false, DataConverterTypes.OBJECTIVE, DSL::remainder);
/* 299 */     var0.registerType(false, DataConverterTypes.TEAM, DSL::remainder);
/*     */     
/* 301 */     var0.registerType(true, DataConverterTypes.UNTAGGED_SPAWNER, DSL::remainder);
/* 302 */     var0.registerType(false, DataConverterTypes.POI_CHUNK, DSL::remainder);
/* 303 */     var0.registerType(true, DataConverterTypes.WORLD_GEN_SETTINGS, DSL::remainder);
/*     */   }
/*     */   static {
/* 306 */     c = (Map<String, String>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           var0.put("minecraft:furnace", "Furnace");
/*     */           var0.put("minecraft:lit_furnace", "Furnace");
/*     */           var0.put("minecraft:chest", "Chest");
/*     */           var0.put("minecraft:trapped_chest", "Chest");
/*     */           var0.put("minecraft:ender_chest", "EnderChest");
/*     */           var0.put("minecraft:jukebox", "RecordPlayer");
/*     */           var0.put("minecraft:dispenser", "Trap");
/*     */           var0.put("minecraft:dropper", "Dropper");
/*     */           var0.put("minecraft:sign", "Sign");
/*     */           var0.put("minecraft:mob_spawner", "MobSpawner");
/*     */           var0.put("minecraft:noteblock", "Music");
/*     */           var0.put("minecraft:brewing_stand", "Cauldron");
/*     */           var0.put("minecraft:enhanting_table", "EnchantTable");
/*     */           var0.put("minecraft:command_block", "CommandBlock");
/*     */           var0.put("minecraft:beacon", "Beacon");
/*     */           var0.put("minecraft:skull", "Skull");
/*     */           var0.put("minecraft:daylight_detector", "DLDetector");
/*     */           var0.put("minecraft:hopper", "Hopper");
/*     */           var0.put("minecraft:banner", "Banner");
/*     */           var0.put("minecraft:flower_pot", "FlowerPot");
/*     */           var0.put("minecraft:repeating_command_block", "CommandBlock");
/*     */           var0.put("minecraft:chain_command_block", "CommandBlock");
/*     */           var0.put("minecraft:standing_sign", "Sign");
/*     */           var0.put("minecraft:wall_sign", "Sign");
/*     */           var0.put("minecraft:piston_head", "Piston");
/*     */           var0.put("minecraft:daylight_detector_inverted", "DLDetector");
/*     */           var0.put("minecraft:unpowered_comparator", "Comparator");
/*     */           var0.put("minecraft:powered_comparator", "Comparator");
/*     */           var0.put("minecraft:wall_banner", "Banner");
/*     */           var0.put("minecraft:standing_banner", "Banner");
/*     */           var0.put("minecraft:structure_block", "Structure");
/*     */           var0.put("minecraft:end_portal", "Airportal");
/*     */           var0.put("minecraft:end_gateway", "EndGateway");
/*     */           var0.put("minecraft:shield", "Banner");
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   protected static final Hook.HookFunction a = new Hook.HookFunction()
/*     */     {
/*     */       public <T> T apply(DynamicOps<T> var0, T var1) {
/* 351 */         return DataConverterSchemaV99.a(new Dynamic(var0, var1), DataConverterSchemaV99.a(), "ArmorStand");
/*     */       }
/*     */     };
/*     */   
/*     */   protected static <T> T a(Dynamic<T> var0, Map<String, String> var1, String var2) {
/* 356 */     return (T)var0.update("tag", var3 -> var3.update("BlockEntityTag", ()).update("EntityTag", ()))
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
/* 372 */       .getValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV99.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */