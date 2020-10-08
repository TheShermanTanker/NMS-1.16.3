/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Locale;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterLang extends DataFix {
/*    */   public DataConverterLang(Schema var0, boolean var1) {
/* 13 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 18 */     return fixTypeEverywhereTyped("OptionsLowerCaseLanguageFix", getInputSchema().getType(DataConverterTypes.OPTIONS), var0 -> var0.update(DSL.remainderFinder(), ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterLang.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */