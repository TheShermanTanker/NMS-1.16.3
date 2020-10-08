/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockNetherVinesUtil
/*    */ {
/*    */   public static boolean a(IBlockData var0) {
/* 12 */     return var0.isAir();
/*    */   }
/*    */   
/*    */   public static int a(Random var0) {
/* 16 */     double var1 = 1.0D;
/* 17 */     int var3 = 0;
/* 18 */     while (var0.nextDouble() < var1) {
/* 19 */       var1 *= 0.826D;
/* 20 */       var3++;
/*    */     } 
/* 22 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockNetherVinesUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */