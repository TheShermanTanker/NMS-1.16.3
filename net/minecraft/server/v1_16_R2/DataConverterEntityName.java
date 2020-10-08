/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.TaggedChoice;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public abstract class DataConverterEntityName
/*    */   extends DataFix {
/*    */   public DataConverterEntityName(String var0, Schema var1, boolean var2) {
/* 16 */     super(var1, var2);
/* 17 */     this.a = var0;
/*    */   }
/*    */   
/*    */   protected final String a;
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 23 */     TaggedChoice.TaggedChoiceType<String> var0 = getInputSchema().findChoiceType(DataConverterTypes.ENTITY);
/* 24 */     TaggedChoice.TaggedChoiceType<String> var1 = getOutputSchema().findChoiceType(DataConverterTypes.ENTITY);
/*    */     
/* 26 */     return fixTypeEverywhere(this.a, (Type)var0, (Type)var1, var2 -> ());
/*    */   }
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
/*    */ 
/*    */ 
/*    */   
/*    */   private <A> Typed<A> a(Object var0, DynamicOps<?> var1, Type<A> var2) {
/* 43 */     return new Typed(var2, var1, var0);
/*    */   }
/*    */   
/*    */   protected abstract Pair<String, Typed<?>> a(String paramString, Typed<?> paramTyped);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */