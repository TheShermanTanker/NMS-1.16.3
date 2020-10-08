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
/*    */ public class DataConverterSchemaV106
/*    */   extends Schema
/*    */ {
/*    */   public DataConverterSchemaV106(int var0, Schema var1) {
/* 17 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 22 */     super.registerTypes(var0, var1, var2);
/*    */     
/* 24 */     var0.registerType(true, DataConverterTypes.UNTAGGED_SPAWNER, () -> DSL.optionalFields("SpawnPotentials", DSL.list(DSL.fields("Entity", DataConverterTypes.ENTITY_TREE.in(var0))), "SpawnData", DataConverterTypes.ENTITY_TREE.in(var0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV106.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */