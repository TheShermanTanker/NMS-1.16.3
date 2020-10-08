/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.templates.Hook;
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
/*    */ public class DataConverterSchemaV102
/*    */   extends Schema
/*    */ {
/*    */   public DataConverterSchemaV102(int var0, Schema var1) {
/* 21 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 26 */     super.registerTypes(var0, var1, var2);
/*    */     
/* 28 */     var0.registerType(true, DataConverterTypes.ITEM_STACK, () -> DSL.hook(DSL.optionalFields("id", DataConverterTypes.ITEM_NAME.in(var0), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.ENTITY_TREE.in(var0), "BlockEntityTag", DataConverterTypes.BLOCK_ENTITY.in(var0), "CanDestroy", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)), "CanPlaceOn", DSL.list(DataConverterTypes.BLOCK_NAME.in(var0)))), DataConverterSchemaV99.a, Hook.HookFunction.IDENTITY));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV102.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */