/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public abstract class DataConverterItemName
/*    */   extends DataFix
/*    */ {
/*    */   private final String a;
/*    */   
/*    */   public DataConverterItemName(Schema var0, String var1) {
/* 19 */     super(var0, false);
/* 20 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 25 */     Type<Pair<String, String>> var0 = DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a());
/* 26 */     if (!Objects.equals(getInputSchema().getType(DataConverterTypes.ITEM_NAME), var0)) {
/* 27 */       throw new IllegalStateException("item name type is not what was expected.");
/*    */     }
/* 29 */     return fixTypeEverywhere(this.a, var0, var0 -> ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static DataFix a(Schema var0, String var1, Function<String, String> var2) {
/* 35 */     return new DataConverterItemName(var0, var1, var2)
/*    */       {
/*    */         protected String a(String var0) {
/* 38 */           return this.a.apply(var0);
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   protected abstract String a(String paramString);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterItemName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */