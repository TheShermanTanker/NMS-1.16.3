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
/*    */ public class DataConverterSchemaV1906
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1906(int var0, Schema var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/* 20 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerBlockEntities(var0);
/*    */     
/* 22 */     a(var0, var1, "minecraft:barrel");
/* 23 */     a(var0, var1, "minecraft:smoker");
/* 24 */     a(var0, var1, "minecraft:blast_furnace");
/*    */     
/* 26 */     var0.register(var1, "minecraft:lectern", var1 -> DSL.optionalFields("Book", DataConverterTypes.ITEM_STACK.in(var0)));
/*    */ 
/*    */ 
/*    */     
/* 30 */     var0.registerSimple(var1, "minecraft:bell");
/*    */     
/* 32 */     return var1;
/*    */   }
/*    */   
/*    */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/* 36 */     var0.register(var1, var2, () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1906.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */