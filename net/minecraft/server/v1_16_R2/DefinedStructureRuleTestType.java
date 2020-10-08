/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public interface DefinedStructureRuleTestType<P extends DefinedStructureRuleTest>
/*    */ {
/*  7 */   public static final DefinedStructureRuleTestType<DefinedStructureTestTrue> a = a("always_true", DefinedStructureTestTrue.a);
/*  8 */   public static final DefinedStructureRuleTestType<DefinedStructureTestBlock> b = a("block_match", DefinedStructureTestBlock.a);
/*  9 */   public static final DefinedStructureRuleTestType<DefinedStructureTestBlockState> c = a("blockstate_match", DefinedStructureTestBlockState.a);
/* 10 */   public static final DefinedStructureRuleTestType<DefinedStructureTestTag> d = a("tag_match", DefinedStructureTestTag.a);
/* 11 */   public static final DefinedStructureRuleTestType<DefinedStructureTestRandomBlock> e = a("random_block_match", DefinedStructureTestRandomBlock.a);
/* 12 */   public static final DefinedStructureRuleTestType<DefinedStructureTestRandomBlockState> f = a("random_blockstate_match", DefinedStructureTestRandomBlockState.a);
/*    */ 
/*    */   
/*    */   Codec<P> codec();
/*    */   
/*    */   static <P extends DefinedStructureRuleTest> DefinedStructureRuleTestType<P> a(String var0, Codec<P> var1) {
/* 18 */     return (DefinedStructureRuleTestType<P>)IRegistry.<DefinedStructureRuleTestType<?>>a(IRegistry.RULE_TEST, var0, () -> var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureRuleTestType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */