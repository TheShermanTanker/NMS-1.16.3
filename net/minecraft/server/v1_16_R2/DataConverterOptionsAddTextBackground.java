/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterOptionsAddTextBackground extends DataFix {
/*    */   public DataConverterOptionsAddTextBackground(Schema var0, boolean var1) {
/* 11 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 16 */     return fixTypeEverywhereTyped("OptionsAddTextBackgroundFix", getInputSchema().getType(DataConverterTypes.OPTIONS), var0 -> var0.update(DSL.remainderFinder(), ()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private double a(String var0) {
/*    */     try {
/* 23 */       double var1 = 0.9D * Double.parseDouble(var0) + 0.1D;
/* 24 */       return var1 / 2.0D;
/* 25 */     } catch (NumberFormatException var1) {
/* 26 */       return 0.5D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterOptionsAddTextBackground.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */