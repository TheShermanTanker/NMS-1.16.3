/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterAdvancementBase extends DataFix {
/*    */   private final String a;
/*    */   
/*    */   public DataConverterAdvancementBase(Schema var0, boolean var1, String var2, Function<String, String> var3) {
/* 15 */     super(var0, var1);
/* 16 */     this.a = var2;
/* 17 */     this.b = var3;
/*    */   }
/*    */   private final Function<String, String> b;
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 22 */     return fixTypeEverywhereTyped(this.a, getInputSchema().getType(DataConverterTypes.ADVANCEMENTS), var0 -> var0.update(DSL.remainderFinder(), ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterAdvancementBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */