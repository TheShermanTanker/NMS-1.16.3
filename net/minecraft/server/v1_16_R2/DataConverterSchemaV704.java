/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.types.templates.Hook;
/*     */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
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
/*     */ public class DataConverterSchemaV704
/*     */   extends Schema
/*     */ {
/*     */   protected static final Map<String, String> a;
/*     */   
/*     */   public DataConverterSchemaV704(int var0, Schema var1) {
/*  35 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  39 */     var0.register(var1, var2, () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type<?> getChoiceType(DSL.TypeReference var0, String var1) {
/*  46 */     if (Objects.equals(var0.typeName(), DataConverterTypes.BLOCK_ENTITY.typeName())) {
/*  47 */       return super.getChoiceType(var0, DataConverterSchemaNamed.a(var1));
/*     */     }
/*  49 */     return super.getChoiceType(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/*  54 */     Map<String, Supplier<TypeTemplate>> var1 = Maps.newHashMap();
/*     */     
/*  56 */     a(var0, var1, "minecraft:furnace");
/*  57 */     a(var0, var1, "minecraft:chest");
/*  58 */     var0.registerSimple(var1, "minecraft:ender_chest");
/*  59 */     var0.register(var1, "minecraft:jukebox", var1 -> DSL.optionalFields("RecordItem", DataConverterTypes.ITEM_STACK.in(var0)));
/*     */ 
/*     */     
/*  62 */     a(var0, var1, "minecraft:dispenser");
/*  63 */     a(var0, var1, "minecraft:dropper");
/*  64 */     var0.registerSimple(var1, "minecraft:sign");
/*  65 */     var0.register(var1, "minecraft:mob_spawner", var1 -> DataConverterTypes.UNTAGGED_SPAWNER.in(var0));
/*  66 */     var0.registerSimple(var1, "minecraft:noteblock");
/*  67 */     var0.registerSimple(var1, "minecraft:piston");
/*  68 */     a(var0, var1, "minecraft:brewing_stand");
/*  69 */     var0.registerSimple(var1, "minecraft:enchanting_table");
/*  70 */     var0.registerSimple(var1, "minecraft:end_portal");
/*  71 */     var0.registerSimple(var1, "minecraft:beacon");
/*  72 */     var0.registerSimple(var1, "minecraft:skull");
/*  73 */     var0.registerSimple(var1, "minecraft:daylight_detector");
/*  74 */     a(var0, var1, "minecraft:hopper");
/*  75 */     var0.registerSimple(var1, "minecraft:comparator");
/*  76 */     var0.register(var1, "minecraft:flower_pot", var1 -> DSL.optionalFields("Item", DSL.or(DSL.constType(DSL.intType()), DataConverterTypes.ITEM_NAME.in(var0))));
/*     */ 
/*     */     
/*  79 */     var0.registerSimple(var1, "minecraft:banner");
/*  80 */     var0.registerSimple(var1, "minecraft:structure_block");
/*  81 */     var0.registerSimple(var1, "minecraft:end_gateway");
/*  82 */     var0.registerSimple(var1, "minecraft:command_block");
/*     */     
/*  84 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/*  89 */     super.registerTypes(var0, var1, var2);
/*     */     
/*  91 */     var0.registerType(false, DataConverterTypes.BLOCK_ENTITY, () -> DSL.taggedChoiceLazy("id", DataConverterSchemaNamed.a(), var0));
/*     */     
/*  93 */     var0.registerType(true, DataConverterTypes.ITEM_STACK, () -> DSL.hook(DSL.optionalFields("id", DataConverterTypes.ITEM_NAME.in(var0), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.ENTITY_TREE.in(var0), "BlockEntityTag", DataConverterTypes.BLOCK_ENTITY.in(var0), "CanDestroy", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)), "CanPlaceOn", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)))), b, Hook.HookFunction.IDENTITY));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 104 */     a = (Map<String, String>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           var0.put("minecraft:furnace", "minecraft:furnace");
/*     */           var0.put("minecraft:lit_furnace", "minecraft:furnace");
/*     */           var0.put("minecraft:chest", "minecraft:chest");
/*     */           var0.put("minecraft:trapped_chest", "minecraft:chest");
/*     */           var0.put("minecraft:ender_chest", "minecraft:ender_chest");
/*     */           var0.put("minecraft:jukebox", "minecraft:jukebox");
/*     */           var0.put("minecraft:dispenser", "minecraft:dispenser");
/*     */           var0.put("minecraft:dropper", "minecraft:dropper");
/*     */           var0.put("minecraft:sign", "minecraft:sign");
/*     */           var0.put("minecraft:mob_spawner", "minecraft:mob_spawner");
/*     */           var0.put("minecraft:noteblock", "minecraft:noteblock");
/*     */           var0.put("minecraft:brewing_stand", "minecraft:brewing_stand");
/*     */           var0.put("minecraft:enhanting_table", "minecraft:enchanting_table");
/*     */           var0.put("minecraft:command_block", "minecraft:command_block");
/*     */           var0.put("minecraft:beacon", "minecraft:beacon");
/*     */           var0.put("minecraft:skull", "minecraft:skull");
/*     */           var0.put("minecraft:daylight_detector", "minecraft:daylight_detector");
/*     */           var0.put("minecraft:hopper", "minecraft:hopper");
/*     */           var0.put("minecraft:banner", "minecraft:banner");
/*     */           var0.put("minecraft:flower_pot", "minecraft:flower_pot");
/*     */           var0.put("minecraft:repeating_command_block", "minecraft:command_block");
/*     */           var0.put("minecraft:chain_command_block", "minecraft:command_block");
/*     */           var0.put("minecraft:shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:white_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:orange_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:magenta_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:light_blue_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:yellow_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:lime_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:pink_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:gray_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:silver_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:cyan_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:purple_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:blue_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:brown_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:green_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:red_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:black_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:bed", "minecraft:bed");
/*     */           var0.put("minecraft:light_gray_shulker_box", "minecraft:shulker_box");
/*     */           var0.put("minecraft:banner", "minecraft:banner");
/*     */           var0.put("minecraft:white_banner", "minecraft:banner");
/*     */           var0.put("minecraft:orange_banner", "minecraft:banner");
/*     */           var0.put("minecraft:magenta_banner", "minecraft:banner");
/*     */           var0.put("minecraft:light_blue_banner", "minecraft:banner");
/*     */           var0.put("minecraft:yellow_banner", "minecraft:banner");
/*     */           var0.put("minecraft:lime_banner", "minecraft:banner");
/*     */           var0.put("minecraft:pink_banner", "minecraft:banner");
/*     */           var0.put("minecraft:gray_banner", "minecraft:banner");
/*     */           var0.put("minecraft:silver_banner", "minecraft:banner");
/*     */           var0.put("minecraft:cyan_banner", "minecraft:banner");
/*     */           var0.put("minecraft:purple_banner", "minecraft:banner");
/*     */           var0.put("minecraft:blue_banner", "minecraft:banner");
/*     */           var0.put("minecraft:brown_banner", "minecraft:banner");
/*     */           var0.put("minecraft:green_banner", "minecraft:banner");
/*     */           var0.put("minecraft:red_banner", "minecraft:banner");
/*     */           var0.put("minecraft:black_banner", "minecraft:banner");
/*     */           var0.put("minecraft:standing_sign", "minecraft:sign");
/*     */           var0.put("minecraft:wall_sign", "minecraft:sign");
/*     */           var0.put("minecraft:piston_head", "minecraft:piston");
/*     */           var0.put("minecraft:daylight_detector_inverted", "minecraft:daylight_detector");
/*     */           var0.put("minecraft:unpowered_comparator", "minecraft:comparator");
/*     */           var0.put("minecraft:powered_comparator", "minecraft:comparator");
/*     */           var0.put("minecraft:wall_banner", "minecraft:banner");
/*     */           var0.put("minecraft:standing_banner", "minecraft:banner");
/*     */           var0.put("minecraft:structure_block", "minecraft:structure_block");
/*     */           var0.put("minecraft:end_portal", "minecraft:end_portal");
/*     */           var0.put("minecraft:end_gateway", "minecraft:end_gateway");
/*     */           var0.put("minecraft:sign", "minecraft:sign");
/*     */           var0.put("minecraft:shield", "minecraft:banner");
/*     */         });
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
/*     */   
/* 188 */   protected static final Hook.HookFunction b = new Hook.HookFunction()
/*     */     {
/*     */       public <T> T apply(DynamicOps<T> var0, T var1) {
/* 191 */         return DataConverterSchemaV99.a(new Dynamic(var0, var1), DataConverterSchemaV704.a, "ArmorStand");
/*     */       }
/*     */     };
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV704.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */