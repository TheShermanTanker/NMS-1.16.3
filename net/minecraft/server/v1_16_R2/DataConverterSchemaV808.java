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
/*    */ public class DataConverterSchemaV808
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV808(int var0, Schema var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/* 19 */     var0.register(var1, var2, () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/* 26 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerBlockEntities(var0);
/*    */     
/* 28 */     a(var0, var1, "minecraft:shulker_box");
/*    */     
/* 30 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV808.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */