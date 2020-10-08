/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CombatMath
/*    */ {
/*    */   public static float a(float var0, float var1, float var2) {
/* 13 */     float var3 = 2.0F + var2 / 4.0F;
/* 14 */     float var4 = MathHelper.a(var1 - var0 / var3, var1 * 0.2F, 20.0F);
/* 15 */     return var0 * (1.0F - var4 / 25.0F);
/*    */   }
/*    */   
/*    */   public static float a(float var0, float var1) {
/* 19 */     float var2 = MathHelper.a(var1, 0.0F, 20.0F);
/* 20 */     return var0 * (1.0F - var2 / 25.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CombatMath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */