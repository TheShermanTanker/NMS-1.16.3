/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public abstract class DataConverterBlockRename extends DataFix {
/*    */   private final String a;
/*    */   
/*    */   public DataConverterBlockRename(Schema var0, String var1) {
/* 20 */     super(var0, false);
/* 21 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 26 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.BLOCK_NAME);
/* 27 */     Type<Pair<String, String>> var1 = DSL.named(DataConverterTypes.BLOCK_NAME.typeName(), DataConverterSchemaNamed.a());
/* 28 */     if (!Objects.equals(var0, var1)) {
/* 29 */       throw new IllegalStateException("block type is not what was expected.");
/*    */     }
/*    */     
/* 32 */     TypeRewriteRule var2 = fixTypeEverywhere(this.a + " for block", var1, var0 -> ());
/*    */     
/* 34 */     TypeRewriteRule var3 = fixTypeEverywhereTyped(this.a + " for block_state", getInputSchema().getType(DataConverterTypes.BLOCK_STATE), var0 -> var0.update(DSL.remainderFinder(), ()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     return TypeRewriteRule.seq(var2, var3);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static DataFix a(Schema var0, String var1, Function<String, String> var2) {
/* 48 */     return new DataConverterBlockRename(var0, var1, var2)
/*    */       {
/*    */         protected String a(String var0) {
/* 51 */           return this.a.apply(var0);
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   protected abstract String a(String paramString);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBlockRename.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */