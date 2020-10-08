/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ public class DataConverterSchemaV1470
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1470(int var0, Schema var1) {
/* 14 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/* 18 */     var0.register(var1, var2, () -> DataConverterSchemaV100.a(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 23 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/*    */ 
/*    */     
/* 26 */     a(var0, var1, "minecraft:turtle");
/* 27 */     a(var0, var1, "minecraft:cod_mob");
/* 28 */     a(var0, var1, "minecraft:tropical_fish");
/* 29 */     a(var0, var1, "minecraft:salmon_mob");
/* 30 */     a(var0, var1, "minecraft:puffer_fish");
/* 31 */     a(var0, var1, "minecraft:phantom");
/* 32 */     a(var0, var1, "minecraft:dolphin");
/* 33 */     a(var0, var1, "minecraft:drowned");
/*    */     
/* 35 */     var0.register(var1, "minecraft:trident", var1 -> DSL.optionalFields("inBlockState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*    */ 
/*    */ 
/*    */     
/* 39 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1470.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */