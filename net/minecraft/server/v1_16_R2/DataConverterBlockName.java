/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterBlockName
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterBlockName(Schema var0, boolean var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 23 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.BLOCK_NAME);
/* 24 */     Type<?> var1 = getOutputSchema().getType(DataConverterTypes.BLOCK_NAME);
/*    */     
/* 26 */     Type<Pair<String, Either<Integer, String>>> var2 = DSL.named(DataConverterTypes.BLOCK_NAME.typeName(), DSL.or(DSL.intType(), DataConverterSchemaNamed.a()));
/* 27 */     Type<Pair<String, String>> var3 = DSL.named(DataConverterTypes.BLOCK_NAME.typeName(), DataConverterSchemaNamed.a());
/*    */     
/* 29 */     if (!Objects.equals(var0, var2) || !Objects.equals(var1, var3)) {
/* 30 */       throw new IllegalStateException("Expected and actual types don't match.");
/*    */     }
/* 32 */     return fixTypeEverywhere("BlockNameFlatteningFix", var2, var3, var0 -> ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBlockName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */