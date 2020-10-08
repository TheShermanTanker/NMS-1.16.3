/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class DataConverterSchemaV1486
/*    */   extends DataConverterSchemaNamed {
/*    */   public DataConverterSchemaV1486(int var0, Schema var1) {
/* 11 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 16 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/*    */     
/* 18 */     var1.put("minecraft:cod", var1.remove("minecraft:cod_mob"));
/* 19 */     var1.put("minecraft:salmon", var1.remove("minecraft:salmon_mob"));
/*    */     
/* 21 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1486.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */