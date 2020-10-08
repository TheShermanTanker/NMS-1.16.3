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
/*    */ public class DataConverterSchemaV1929
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1929(int var0, Schema var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 20 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/* 21 */     var0.register(var1, "minecraft:wandering_trader", var1 -> DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "Offers", DSL.optionalFields("Recipes", DSL.list(DSL.optionalFields("buy", DataConverterTypes.ITEM_STACK.in(var0), "buyB", DataConverterTypes.ITEM_STACK.in(var0), "sell", DataConverterTypes.ITEM_STACK.in(var0)))), DataConverterSchemaV100.a(var0)));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     var0.register(var1, "minecraft:trader_llama", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), "DecorItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1929.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */