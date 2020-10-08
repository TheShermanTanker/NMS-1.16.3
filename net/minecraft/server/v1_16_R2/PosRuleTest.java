/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PosRuleTest
/*    */ {
/* 10 */   public static final Codec<PosRuleTest> c = IRegistry.POS_RULE_TEST.dispatch("predicate_type", PosRuleTest::a, PosRuleTestType::codec);
/*    */   
/*    */   public abstract boolean a(BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2, BlockPosition paramBlockPosition3, Random paramRandom);
/*    */   
/*    */   protected abstract PosRuleTestType<?> a();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PosRuleTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */