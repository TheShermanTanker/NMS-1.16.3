/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.nio.charset.StandardCharsets;
/*    */ 
/*    */ public class StatusChallengeUtils
/*    */ {
/*  7 */   public static final char[] a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*    */ 
/*    */ 
/*    */   
/*    */   public static String a(byte[] var0, int var1, int var2) {
/* 12 */     int var3 = var2 - 1;
/* 13 */     int var4 = (var1 > var3) ? var3 : var1;
/* 14 */     while (0 != var0[var4] && var4 < var3) {
/* 15 */       var4++;
/*    */     }
/*    */     
/* 18 */     return new String(var0, var1, var4 - var1, StandardCharsets.UTF_8);
/*    */   }
/*    */   
/*    */   public static int a(byte[] var0, int var1) {
/* 22 */     return b(var0, var1, var0.length);
/*    */   }
/*    */   
/*    */   public static int b(byte[] var0, int var1, int var2) {
/* 26 */     if (0 > var2 - var1 - 4)
/*    */     {
/*    */       
/* 29 */       return 0;
/*    */     }
/* 31 */     return var0[var1 + 3] << 24 | (var0[var1 + 2] & 0xFF) << 16 | (var0[var1 + 1] & 0xFF) << 8 | var0[var1] & 0xFF;
/*    */   }
/*    */   
/*    */   public static int c(byte[] var0, int var1, int var2) {
/* 35 */     if (0 > var2 - var1 - 4)
/*    */     {
/*    */       
/* 38 */       return 0;
/*    */     }
/* 40 */     return var0[var1] << 24 | (var0[var1 + 1] & 0xFF) << 16 | (var0[var1 + 2] & 0xFF) << 8 | var0[var1 + 3] & 0xFF;
/*    */   }
/*    */   
/*    */   public static String a(byte var0) {
/* 44 */     return "" + a[(var0 & 0xF0) >>> 4] + a[var0 & 0xF];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StatusChallengeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */