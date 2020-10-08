/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class WeightedRandom
/*    */ {
/*    */   public static int a(List<? extends WeightedRandomChoice> var0) {
/* 10 */     int var1 = 0;
/* 11 */     for (int var2 = 0, var3 = var0.size(); var2 < var3; var2++) {
/* 12 */       WeightedRandomChoice var4 = var0.get(var2);
/* 13 */       var1 += var4.a;
/*    */     } 
/* 15 */     return var1;
/*    */   }
/*    */   
/*    */   public static <T extends WeightedRandomChoice> T a(Random var0, List<T> var1, int var2) {
/* 19 */     if (var2 <= 0) {
/* 20 */       throw (IllegalArgumentException)SystemUtils.c(new IllegalArgumentException());
/*    */     }
/*    */     
/* 23 */     int var3 = var0.nextInt(var2);
/* 24 */     return a(var1, var3);
/*    */   }
/*    */   
/*    */   public static <T extends WeightedRandomChoice> T a(List<T> var0, int var1) {
/* 28 */     for (int var2 = 0, var3 = var0.size(); var2 < var3; var2++) {
/* 29 */       WeightedRandomChoice weightedRandomChoice = (WeightedRandomChoice)var0.get(var2);
/* 30 */       var1 -= weightedRandomChoice.a;
/* 31 */       if (var1 < 0) {
/* 32 */         return (T)weightedRandomChoice;
/*    */       }
/*    */     } 
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   public static <T extends WeightedRandomChoice> T a(Random var0, List<T> var1) {
/* 39 */     return a(var0, var1, a(var1));
/*    */   }
/*    */   
/*    */   public static class WeightedRandomChoice {
/*    */     protected final int a;
/*    */     
/*    */     public WeightedRandomChoice(int var0) {
/* 46 */       this.a = var0;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WeightedRandom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */