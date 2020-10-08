/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.TaggedChoice;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public abstract class DataConverterEntityRenameAbstract
/*    */   extends DataFix {
/*    */   private final String a;
/*    */   
/*    */   public DataConverterEntityRenameAbstract(String var0, Schema var1, boolean var2) {
/* 19 */     super(var1, var2);
/* 20 */     this.a = var0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 26 */     TaggedChoice.TaggedChoiceType<String> var0 = getInputSchema().findChoiceType(DataConverterTypes.ENTITY);
/* 27 */     TaggedChoice.TaggedChoiceType<String> var1 = getOutputSchema().findChoiceType(DataConverterTypes.ENTITY);
/*    */     
/* 29 */     Type<Pair<String, String>> var2 = DSL.named(DataConverterTypes.ENTITY_NAME.typeName(), DataConverterSchemaNamed.a());
/* 30 */     if (!Objects.equals(getOutputSchema().getType(DataConverterTypes.ENTITY_NAME), var2)) {
/* 31 */       throw new IllegalStateException("Entity name type is not what was expected.");
/*    */     }
/*    */     
/* 34 */     return TypeRewriteRule.seq(
/* 35 */         fixTypeEverywhere(this.a, (Type)var0, (Type)var1, var2 -> ()), 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 47 */         fixTypeEverywhere(this.a + " for entity name", var2, var0 -> ()));
/*    */   }
/*    */   
/*    */   protected abstract String a(String paramString);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityRenameAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */