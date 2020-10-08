/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.TaggedChoice;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterAddChoices extends DataFix {
/*    */   private final String a;
/*    */   
/*    */   public DataConverterAddChoices(Schema var0, String var1, DSL.TypeReference var2) {
/* 14 */     super(var0, true);
/* 15 */     this.a = var1;
/* 16 */     this.b = var2;
/*    */   }
/*    */   private final DSL.TypeReference b;
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 21 */     TaggedChoice.TaggedChoiceType<?> var0 = getInputSchema().findChoiceType(this.b);
/* 22 */     TaggedChoice.TaggedChoiceType<?> var1 = getOutputSchema().findChoiceType(this.b);
/* 23 */     return a(this.a, var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected final <K> TypeRewriteRule a(String var0, TaggedChoice.TaggedChoiceType<K> var1, TaggedChoice.TaggedChoiceType<?> var2) {
/* 28 */     if (var1.getKeyType() != var2.getKeyType()) {
/* 29 */       throw new IllegalStateException("Could not inject: key type is not the same");
/*    */     }
/* 31 */     TaggedChoice.TaggedChoiceType<K> var3 = (TaggedChoice.TaggedChoiceType)var2;
/* 32 */     return fixTypeEverywhere(var0, (Type)var1, (Type)var3, var1 -> ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterAddChoices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */