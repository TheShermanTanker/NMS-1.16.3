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
/*    */ public class DataConverterSchemaV135
/*    */   extends Schema
/*    */ {
/*    */   public DataConverterSchemaV135(int var0, Schema var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 23 */     super.registerTypes(var0, var1, var2);
/*    */     
/* 25 */     var0.registerType(false, DataConverterTypes.PLAYER, () -> DSL.optionalFields("RootVehicle", DSL.optionalFields("Entity", DataConverterTypes.ENTITY_TREE.in(var0)), "Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "EnderItems", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     var0.registerType(true, DataConverterTypes.ENTITY_TREE, () -> DSL.optionalFields("Passengers", DSL.list(DataConverterTypes.ENTITY_TREE.in(var0)), DataConverterTypes.ENTITY.in(var0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV135.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */