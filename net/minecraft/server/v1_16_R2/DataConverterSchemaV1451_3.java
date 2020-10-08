/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterSchemaV1451_3
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1451_3(int var0, Schema var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 23 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/*    */ 
/*    */     
/* 26 */     var0.registerSimple(var1, "minecraft:egg");
/* 27 */     var0.registerSimple(var1, "minecraft:ender_pearl");
/* 28 */     var0.registerSimple(var1, "minecraft:fireball");
/* 29 */     var0.register(var1, "minecraft:potion", var1 -> DSL.optionalFields("Potion", DataConverterTypes.ITEM_STACK.in(var0)));
/*    */ 
/*    */     
/* 32 */     var0.registerSimple(var1, "minecraft:small_fireball");
/* 33 */     var0.registerSimple(var1, "minecraft:snowball");
/* 34 */     var0.registerSimple(var1, "minecraft:wither_skull");
/* 35 */     var0.registerSimple(var1, "minecraft:xp_bottle");
/*    */     
/* 37 */     var0.register(var1, "minecraft:arrow", () -> DSL.optionalFields("inBlockState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*    */ 
/*    */     
/* 40 */     var0.register(var1, "minecraft:enderman", () -> DSL.optionalFields("carriedBlockState", DataConverterTypes.BLOCK_STATE.in(var0), DataConverterSchemaV100.a(var0)));
/*    */ 
/*    */ 
/*    */     
/* 44 */     var0.register(var1, "minecraft:falling_block", () -> DSL.optionalFields("BlockState", DataConverterTypes.BLOCK_STATE.in(var0), "TileEntityData", DataConverterTypes.BLOCK_ENTITY.in(var0)));
/*    */ 
/*    */ 
/*    */     
/* 48 */     var0.register(var1, "minecraft:spectral_arrow", () -> DSL.optionalFields("inBlockState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*    */ 
/*    */     
/* 51 */     var0.register(var1, "minecraft:chest_minecart", () -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*    */ 
/*    */ 
/*    */     
/* 55 */     var0.register(var1, "minecraft:commandblock_minecart", () -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*    */ 
/*    */     
/* 58 */     var0.register(var1, "minecraft:furnace_minecart", () -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*    */ 
/*    */     
/* 61 */     var0.register(var1, "minecraft:hopper_minecart", () -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0), "Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*    */ 
/*    */ 
/*    */     
/* 65 */     var0.register(var1, "minecraft:minecart", () -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*    */ 
/*    */     
/* 68 */     var0.register(var1, "minecraft:spawner_minecart", () -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0), DataConverterTypes.UNTAGGED_SPAWNER.in(var0)));
/*    */ 
/*    */ 
/*    */     
/* 72 */     var0.register(var1, "minecraft:tnt_minecart", () -> DSL.optionalFields("DisplayState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*    */ 
/*    */ 
/*    */     
/* 76 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1451_3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */