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
/*    */ 
/*    */ public class DataConverterSchemaV1451_1
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1451_1(int var0, Schema var1) {
/* 20 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 25 */     super.registerTypes(var0, var1, var2);
/*    */     
/* 27 */     var0.registerType(false, DataConverterTypes.CHUNK, () -> DSL.fields("Level", DSL.optionalFields("Entities", DSL.list(DataConverterTypes.ENTITY_TREE.in(var0)), "TileEntities", DSL.list(DataConverterTypes.BLOCK_ENTITY.in(var0)), "TileTicks", DSL.list(DSL.fields("i", DataConverterTypes.BLOCK_NAME.in(var0))), "Sections", DSL.list(DSL.optionalFields("Palette", DSL.list(DataConverterTypes.BLOCK_STATE.in(var0)))))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1451_1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */