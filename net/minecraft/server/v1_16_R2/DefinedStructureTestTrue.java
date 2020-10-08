/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class DefinedStructureTestTrue
/*    */   extends DefinedStructureRuleTest
/*    */ {
/*  9 */   public static final Codec<DefinedStructureTestTrue> a = Codec.unit(() -> b);
/*    */   
/* 11 */   public static final DefinedStructureTestTrue b = new DefinedStructureTestTrue();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, Random var1) {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureRuleTestType<?> a() {
/* 23 */     return DefinedStructureRuleTestType.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureTestTrue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */