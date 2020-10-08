/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterObjectiveRenderType
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterObjectiveRenderType(Schema var0, boolean var1) {
/* 19 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   private static IScoreboardCriteria.EnumScoreboardHealthDisplay a(String var0) {
/* 23 */     return var0.equals("health") ? IScoreboardCriteria.EnumScoreboardHealthDisplay.HEARTS : IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER;
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 28 */     Type<Pair<String, Dynamic<?>>> var0 = DSL.named(DataConverterTypes.OBJECTIVE.typeName(), DSL.remainderType());
/*    */     
/* 30 */     if (!Objects.equals(var0, getInputSchema().getType(DataConverterTypes.OBJECTIVE))) {
/* 31 */       throw new IllegalStateException("Objective type is not what was expected.");
/*    */     }
/*    */     
/* 34 */     return fixTypeEverywhere("ObjectiveRenderTypeFix", var0, var0 -> ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterObjectiveRenderType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */