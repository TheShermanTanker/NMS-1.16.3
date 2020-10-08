/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LinearCongruentialGenerator
/*    */ {
/*    */   public static long a(long var0, long var2) {
/*  8 */     var0 *= var0 * 6364136223846793005L + 1442695040888963407L;
/*  9 */     var0 += var2;
/* 10 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LinearCongruentialGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */