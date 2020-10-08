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
/*    */ 
/*    */ public class DataConverterSchemaV2501
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV2501(int var0, Schema var1) {
/* 19 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   private static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/* 23 */     var0.register(var1, var2, () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "RecipesUsed", DSL.compoundList(DataConverterTypes.RECIPE.in(var0), DSL.constType(DSL.intType()))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/* 31 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerBlockEntities(var0);
/* 32 */     a(var0, var1, "minecraft:furnace");
/* 33 */     a(var0, var1, "minecraft:smoker");
/* 34 */     a(var0, var1, "minecraft:blast_furnace");
/* 35 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV2501.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */