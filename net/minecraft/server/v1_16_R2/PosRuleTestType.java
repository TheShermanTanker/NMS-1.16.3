/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public interface PosRuleTestType<P extends PosRuleTest>
/*    */ {
/*  7 */   public static final PosRuleTestType<PosRuleTestTrue> a = a("always_true", PosRuleTestTrue.a);
/*  8 */   public static final PosRuleTestType<PosRuleTestLinear> b = a("linear_pos", PosRuleTestLinear.a);
/*  9 */   public static final PosRuleTestType<PosRuleTestAxisAlignedLinear> c = a("axis_aligned_linear_pos", PosRuleTestAxisAlignedLinear.a);
/*    */ 
/*    */   
/*    */   Codec<P> codec();
/*    */   
/*    */   static <P extends PosRuleTest> PosRuleTestType<P> a(String var0, Codec<P> var1) {
/* 15 */     return (PosRuleTestType<P>)IRegistry.<PosRuleTestType<?>>a(IRegistry.POS_RULE_TEST, var0, () -> var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PosRuleTestType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */