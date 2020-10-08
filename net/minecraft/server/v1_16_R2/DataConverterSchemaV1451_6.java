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
/*    */ public class DataConverterSchemaV1451_6
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1451_6(int var0, Schema var1) {
/* 20 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 25 */     super.registerTypes(var0, var1, var2);
/*    */     
/* 27 */     Supplier<TypeTemplate> var3 = () -> DSL.compoundList(DataConverterTypes.ITEM_NAME.in(var0), DSL.constType(DSL.intType()));
/*    */     
/* 29 */     var0.registerType(false, DataConverterTypes.STATS, () -> DSL.optionalFields("stats", DSL.optionalFields("minecraft:mined", DSL.compoundList(DataConverterTypes.BLOCK_NAME.in(var0), DSL.constType(DSL.intType())), "minecraft:crafted", var1.get(), "minecraft:used", var1.get(), "minecraft:broken", var1.get(), "minecraft:picked_up", var1.get(), DSL.optionalFields("minecraft:dropped", var1.get(), "minecraft:killed", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.intType())), "minecraft:killed_by", DSL.compoundList(DataConverterTypes.ENTITY_NAME.in(var0), DSL.constType(DSL.intType())), "minecraft:custom", DSL.compoundList(DSL.constType(a()), DSL.constType(DSL.intType()))))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1451_6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */