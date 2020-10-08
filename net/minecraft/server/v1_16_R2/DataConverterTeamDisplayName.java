/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public class DataConverterTeamDisplayName
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterTeamDisplayName(Schema var0, boolean var1) {
/* 20 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 25 */     Type<Pair<String, Dynamic<?>>> var0 = DSL.named(DataConverterTypes.TEAM.typeName(), DSL.remainderType());
/*    */     
/* 27 */     if (!Objects.equals(var0, getInputSchema().getType(DataConverterTypes.TEAM))) {
/* 28 */       throw new IllegalStateException("Team type is not what was expected.");
/*    */     }
/*    */     
/* 31 */     return fixTypeEverywhere("TeamDisplayNameFix", var0, var0 -> ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterTeamDisplayName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */