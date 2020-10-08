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
/*    */ public class DataConverterSchemaV1451_7
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1451_7(int var0, Schema var1) {
/* 16 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 21 */     super.registerTypes(var0, var1, var2);
/*    */     
/* 23 */     var0.registerType(false, DataConverterTypes.STRUCTURE_FEATURE, () -> DSL.optionalFields("Children", DSL.list(DSL.optionalFields("CA", DataConverterTypes.BLOCK_STATE.in(var0), "CB", DataConverterTypes.BLOCK_STATE.in(var0), "CC", DataConverterTypes.BLOCK_STATE.in(var0), "CD", DataConverterTypes.BLOCK_STATE.in(var0)))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1451_7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */