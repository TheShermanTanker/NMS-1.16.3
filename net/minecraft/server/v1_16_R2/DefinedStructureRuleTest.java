/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class DefinedStructureRuleTest
/*    */ {
/* 10 */   public static final Codec<DefinedStructureRuleTest> c = IRegistry.RULE_TEST.dispatch("predicate_type", DefinedStructureRuleTest::a, DefinedStructureRuleTestType::codec);
/*    */   
/*    */   public abstract boolean a(IBlockData paramIBlockData, Random paramRandom);
/*    */   
/*    */   protected abstract DefinedStructureRuleTestType<?> a();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureRuleTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */