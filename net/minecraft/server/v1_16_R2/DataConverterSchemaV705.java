/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.templates.Hook;
/*     */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
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
/*     */ public class DataConverterSchemaV705
/*     */   extends DataConverterSchemaNamed
/*     */ {
/*     */   public DataConverterSchemaV705(int var0, Schema var1) {
/*  27 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  31 */     var0.register(var1, var2, () -> DataConverterSchemaV100.a(var0));
/*     */   }
/*     */   
/*     */   protected static void b(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  35 */     var0.register(var1, var2, () -> DSL.optionalFields("inTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/*  42 */     Map<String, Supplier<TypeTemplate>> var1 = Maps.newHashMap();
/*     */     
/*  44 */     var0.registerSimple(var1, "minecraft:area_effect_cloud");
/*  45 */     a(var0, var1, "minecraft:armor_stand");
/*  46 */     var0.register(var1, "minecraft:arrow", var1 -> DSL.optionalFields("inTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/*  49 */     a(var0, var1, "minecraft:bat");
/*  50 */     a(var0, var1, "minecraft:blaze");
/*  51 */     var0.registerSimple(var1, "minecraft:boat");
/*  52 */     a(var0, var1, "minecraft:cave_spider");
/*  53 */     var0.register(var1, "minecraft:chest_minecart", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */ 
/*     */ 
/*     */     
/*  57 */     a(var0, var1, "minecraft:chicken");
/*  58 */     var0.register(var1, "minecraft:commandblock_minecart", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/*  61 */     a(var0, var1, "minecraft:cow");
/*  62 */     a(var0, var1, "minecraft:creeper");
/*  63 */     var0.register(var1, "minecraft:donkey", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     var0.registerSimple(var1, "minecraft:dragon_fireball");
/*  69 */     b(var0, var1, "minecraft:egg");
/*  70 */     a(var0, var1, "minecraft:elder_guardian");
/*  71 */     var0.registerSimple(var1, "minecraft:ender_crystal");
/*  72 */     a(var0, var1, "minecraft:ender_dragon");
/*  73 */     var0.register(var1, "minecraft:enderman", var1 -> DSL.optionalFields("carried", DataConverterTypes.BLOCK_NAME.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */     
/*  77 */     a(var0, var1, "minecraft:endermite");
/*  78 */     b(var0, var1, "minecraft:ender_pearl");
/*  79 */     var0.registerSimple(var1, "minecraft:eye_of_ender_signal");
/*  80 */     var0.register(var1, "minecraft:falling_block", var1 -> DSL.optionalFields("Block", DataConverterTypes.BLOCK_NAME.in(var0), "TileEntityData", DataConverterTypes.BLOCK_ENTITY.in(var0)));
/*     */ 
/*     */ 
/*     */     
/*  84 */     b(var0, var1, "minecraft:fireball");
/*  85 */     var0.register(var1, "minecraft:fireworks_rocket", var1 -> DSL.optionalFields("FireworksItem", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/*  88 */     var0.register(var1, "minecraft:furnace_minecart", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/*  91 */     a(var0, var1, "minecraft:ghast");
/*  92 */     a(var0, var1, "minecraft:giant");
/*  93 */     a(var0, var1, "minecraft:guardian");
/*  94 */     var0.register(var1, "minecraft:hopper_minecart", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */ 
/*     */ 
/*     */     
/*  98 */     var0.register(var1, "minecraft:horse", var1 -> DSL.optionalFields("ArmorItem", DataConverterTypes.ITEM_STACK.in(var0), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     a(var0, var1, "minecraft:husk");
/* 105 */     var0.register(var1, "minecraft:item", var1 -> DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 108 */     var0.register(var1, "minecraft:item_frame", var1 -> DSL.optionalFields("Item", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/* 111 */     var0.registerSimple(var1, "minecraft:leash_knot");
/* 112 */     a(var0, var1, "minecraft:magma_cube");
/* 113 */     var0.register(var1, "minecraft:minecart", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/* 116 */     a(var0, var1, "minecraft:mooshroom");
/* 117 */     var0.register(var1, "minecraft:mule", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     a(var0, var1, "minecraft:ocelot");
/* 123 */     var0.registerSimple(var1, "minecraft:painting");
/* 124 */     var0.registerSimple(var1, "minecraft:parrot");
/* 125 */     a(var0, var1, "minecraft:pig");
/* 126 */     a(var0, var1, "minecraft:polar_bear");
/* 127 */     var0.register(var1, "minecraft:potion", var1 -> DSL.optionalFields("Potion", DataConverterTypes.ITEM_STACK.in(var0), "inTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 131 */     a(var0, var1, "minecraft:rabbit");
/* 132 */     a(var0, var1, "minecraft:sheep");
/* 133 */     a(var0, var1, "minecraft:shulker");
/* 134 */     var0.registerSimple(var1, "minecraft:shulker_bullet");
/* 135 */     a(var0, var1, "minecraft:silverfish");
/* 136 */     a(var0, var1, "minecraft:skeleton");
/* 137 */     var0.register(var1, "minecraft:skeleton_horse", var1 -> DSL.optionalFields("SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */     
/* 141 */     a(var0, var1, "minecraft:slime");
/* 142 */     b(var0, var1, "minecraft:small_fireball");
/* 143 */     b(var0, var1, "minecraft:snowball");
/* 144 */     a(var0, var1, "minecraft:snowman");
/* 145 */     var0.register(var1, "minecraft:spawner_minecart", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0), DataConverterTypes.UNTAGGED_SPAWNER.in(var0)));
/*     */ 
/*     */ 
/*     */     
/* 149 */     var0.register(var1, "minecraft:spectral_arrow", var1 -> DSL.optionalFields("inTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/* 152 */     a(var0, var1, "minecraft:spider");
/* 153 */     a(var0, var1, "minecraft:squid");
/* 154 */     a(var0, var1, "minecraft:stray");
/* 155 */     var0.registerSimple(var1, "minecraft:tnt");
/* 156 */     var0.register(var1, "minecraft:tnt_minecart", var1 -> DSL.optionalFields("DisplayTile", DataConverterTypes.BLOCK_NAME.in(var0)));
/*     */ 
/*     */     
/* 159 */     var0.register(var1, "minecraft:villager", var1 -> DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "Offers", DSL.optionalFields("Recipes", DSL.list(DSL.optionalFields("buy", DataConverterTypes.ITEM_STACK.in(var0), "buyB", DataConverterTypes.ITEM_STACK.in(var0), "sell", DataConverterTypes.ITEM_STACK.in(var0)))), DataConverterSchemaV100.a(var0)));
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
/* 172 */     a(var0, var1, "minecraft:villager_golem");
/* 173 */     a(var0, var1, "minecraft:witch");
/* 174 */     a(var0, var1, "minecraft:wither");
/* 175 */     a(var0, var1, "minecraft:wither_skeleton");
/* 176 */     b(var0, var1, "minecraft:wither_skull");
/* 177 */     a(var0, var1, "minecraft:wolf");
/* 178 */     b(var0, var1, "minecraft:xp_bottle");
/* 179 */     var0.registerSimple(var1, "minecraft:xp_orb");
/* 180 */     a(var0, var1, "minecraft:zombie");
/* 181 */     var0.register(var1, "minecraft:zombie_horse", var1 -> DSL.optionalFields("SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */     
/* 185 */     a(var0, var1, "minecraft:zombie_pigman");
/* 186 */     a(var0, var1, "minecraft:zombie_villager");
/*     */ 
/*     */     
/* 189 */     var0.registerSimple(var1, "minecraft:evocation_fangs");
/* 190 */     a(var0, var1, "minecraft:evocation_illager");
/* 191 */     var0.registerSimple(var1, "minecraft:illusion_illager");
/* 192 */     var0.register(var1, "minecraft:llama", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), "DecorItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     var0.registerSimple(var1, "minecraft:llama_spit");
/* 199 */     a(var0, var1, "minecraft:vex");
/* 200 */     a(var0, var1, "minecraft:vindication_illager");
/*     */     
/* 202 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 207 */     super.registerTypes(var0, var1, var2);
/* 208 */     var0.registerType(true, DataConverterTypes.ENTITY, () -> DSL.taggedChoiceLazy("id", a(), var0));
/* 209 */     var0.registerType(true, DataConverterTypes.ITEM_STACK, () -> DSL.hook(DSL.optionalFields("id", DataConverterTypes.ITEM_NAME.in(var0), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.ENTITY_TREE.in(var0), "BlockEntityTag", DataConverterTypes.BLOCK_ENTITY.in(var0), "CanDestroy", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)), "CanPlaceOn", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)))), b, Hook.HookFunction.IDENTITY));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   protected static final Hook.HookFunction b = new Hook.HookFunction()
/*     */     {
/*     */       public <T> T apply(DynamicOps<T> var0, T var1) {
/* 223 */         return DataConverterSchemaV99.a(new Dynamic(var0, var1), DataConverterSchemaV704.a, "minecraft:armor_stand");
/*     */       }
/*     */     };
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV705.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */