/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class PosRuleTestTrue
/*    */   extends PosRuleTest
/*    */ {
/*  9 */   public static final Codec<PosRuleTestTrue> a = Codec.unit(() -> b);
/*    */   
/* 11 */   public static final PosRuleTestTrue b = new PosRuleTestTrue();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(BlockPosition var0, BlockPosition var1, BlockPosition var2, Random var3) {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected PosRuleTestType<?> a() {
/* 23 */     return PosRuleTestType.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PosRuleTestTrue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */