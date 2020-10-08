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
/*    */ public class DataConverterSchemaV703
/*    */   extends Schema
/*    */ {
/*    */   public DataConverterSchemaV703(int var0, Schema var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 20 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/*    */     
/* 22 */     var1.remove("EntityHorse");
/* 23 */     var0.register(var1, "Horse", () -> DSL.optionalFields("ArmorItem", DataConverterTypes.ITEM_STACK.in(var0), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     var0.register(var1, "Donkey", () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 33 */     var0.register(var1, "Mule", () -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     var0.register(var1, "ZombieHorse", () -> DSL.optionalFields("SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*    */ 
/*    */ 
/*    */     
/* 42 */     var0.register(var1, "SkeletonHorse", () -> DSL.optionalFields("SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), DataConverterSchemaV100.a(var0)));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV703.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */