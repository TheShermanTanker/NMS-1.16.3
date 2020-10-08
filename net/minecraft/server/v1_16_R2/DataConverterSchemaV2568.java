/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class DataConverterSchemaV2568
/*    */   extends DataConverterSchemaNamed {
/*    */   public DataConverterSchemaV2568(int var0, Schema var1) {
/* 11 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/* 15 */     var0.register(var1, var2, () -> DataConverterSchemaV100.a(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 20 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/* 21 */     a(var0, var1, "minecraft:piglin_brute");
/* 22 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV2568.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */