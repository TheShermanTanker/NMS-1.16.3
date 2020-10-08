/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterStructureReference extends DataFix {
/*    */   public DataConverterStructureReference(Schema var0, boolean var1) {
/* 12 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 17 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE);
/* 18 */     return fixTypeEverywhereTyped("Structure Reference Fix", var0, var0 -> var0.update(DSL.remainderFinder(), DataConverterStructureReference::a));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static <T> Dynamic<T> a(Dynamic<T> var0) {
/* 24 */     return var0.update("references", var0 -> var0.createInt(((Integer)var0.asNumber().map(Number::intValue).result().filter(()).orElse(Integer.valueOf(1))).intValue()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterStructureReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */