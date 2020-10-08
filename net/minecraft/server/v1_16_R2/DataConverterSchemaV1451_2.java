/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ public class DataConverterSchemaV1451_2
/*    */   extends DataConverterSchemaNamed
/*    */ {
/*    */   public DataConverterSchemaV1451_2(int var0, Schema var1) {
/* 14 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema var0) {
/* 19 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerBlockEntities(var0);
/*    */     
/* 21 */     var0.register(var1, "minecraft:piston", var1 -> DSL.optionalFields("blockState", DataConverterTypes.BLOCK_STATE.in(var0)));
/*    */ 
/*    */ 
/*    */     
/* 25 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1451_2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */