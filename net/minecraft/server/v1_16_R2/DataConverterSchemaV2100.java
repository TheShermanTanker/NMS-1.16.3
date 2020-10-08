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
/*    */ public class DataConverterSchemaV2100
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV2100(int var0, Schema var1) {
/* 16 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/* 20 */     var0.register(var1, var2, () -> DataConverterSchemaV100.a(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 25 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/* 26 */     a(var0, var1, "minecraft:bee");
/* 27 */     a(var0, var1, "minecraft:bee_stinger");
/* 28 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/* 33 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerBlockEntities(var0);
/*    */     
/* 35 */     var0.register(var1, "minecraft:beehive", () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "Bees", DSL.list(DSL.optionalFields("EntityData", DataConverterTypes.ENTITY_TREE.in(var0)))));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV2100.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */