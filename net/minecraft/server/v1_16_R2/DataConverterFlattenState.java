/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ 
/*    */ public class DataConverterFlattenState extends DataFix {
/*    */   public DataConverterFlattenState(Schema var0, boolean var1) {
/* 10 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 15 */     return fixTypeEverywhereTyped("BlockStateStructureTemplateFix", getInputSchema().getType(DataConverterTypes.BLOCK_STATE), var0 -> var0.update(DSL.remainderFinder(), DataConverterFlattenData::a));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterFlattenState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */