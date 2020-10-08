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
/*    */ public class DataConverterSchemaV1928
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1928(int var0, Schema var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   protected static TypeTemplate a(Schema var0) {
/* 19 */     return DSL.optionalFields("ArmorItems", 
/* 20 */         DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "HandItems", 
/* 21 */         DSL.list(DataConverterTypes.ITEM_STACK.in(var0)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/* 26 */     var0.register(var1, var2, () -> a(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 31 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/*    */     
/* 33 */     var1.remove("minecraft:illager_beast");
/* 34 */     a(var0, var1, "minecraft:ravager");
/*    */     
/* 36 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1928.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */