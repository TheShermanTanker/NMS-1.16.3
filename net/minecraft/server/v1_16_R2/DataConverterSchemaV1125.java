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
/*    */ public class DataConverterSchemaV1125
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1125(int var0, Schema var1) {
/* 19 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/* 24 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerBlockEntities(var0);
/*    */     
/* 26 */     var0.registerSimple(var1, "minecraft:bed");
/*    */     
/* 28 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 33 */     super.registerTypes(var0, var1, var2);
/* 34 */     var0.registerType(false, DataConverterTypes.ADVANCEMENTS, () -> DSL.optionalFields("minecraft:adventure/adventuring_time", DSL.optionalFields("criteria", DSL.compoundList(DataConverterTypes.BIOME.in(var0), DSL.constType(DSL.string()))), "minecraft:adventure/kill_a_mob", DSL.optionalFields("criteria", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.string()))), "minecraft:adventure/kill_all_mobs", DSL.optionalFields("criteria", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.string()))), "minecraft:husbandry/bred_all_animals", DSL.optionalFields("criteria", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.string())))));
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
/* 48 */     var0.registerType(false, DataConverterTypes.BIOME, () -> DSL.constType(a()));
/* 49 */     var0.registerType(false, DataConverterTypes.ENTITY_NAME, () -> DSL.constType(a()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1125.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */