/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ 
/*    */ public class DataConverterSwimStats
/*    */   extends DataFix {
/*    */   public DataConverterSwimStats(Schema var0, boolean var1) {
/* 13 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 18 */     Type<?> var0 = getOutputSchema().getType(DataConverterTypes.STATS);
/* 19 */     Type<?> var1 = getInputSchema().getType(DataConverterTypes.STATS);
/* 20 */     OpticFinder<?> var2 = var1.findField("stats");
/* 21 */     OpticFinder<?> var3 = var2.type().findField("minecraft:custom");
/* 22 */     OpticFinder<String> var4 = DataConverterSchemaNamed.a().finder();
/* 23 */     return fixTypeEverywhereTyped("SwimStatsRenameFix", var1, var0, var3 -> var3.updateTyped(var0, ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSwimStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */