/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoiseGeneratorNormal
/*    */ {
/*    */   private final double a;
/*    */   private final NoiseGeneratorOctaves b;
/*    */   private final NoiseGeneratorOctaves c;
/*    */   
/*    */   public static NoiseGeneratorNormal a(SeededRandom var0, int var1, DoubleList var2) {
/* 31 */     return new NoiseGeneratorNormal(var0, var1, var2);
/*    */   }
/*    */   
/*    */   private NoiseGeneratorNormal(SeededRandom var0, int var1, DoubleList var2) {
/* 35 */     this.b = NoiseGeneratorOctaves.a(var0, var1, var2);
/* 36 */     this.c = NoiseGeneratorOctaves.a(var0, var1, var2);
/*    */     
/* 38 */     int var3 = Integer.MAX_VALUE;
/* 39 */     int var4 = Integer.MIN_VALUE;
/*    */     
/* 41 */     DoubleListIterator var5 = var2.iterator();
/* 42 */     while (var5.hasNext()) {
/* 43 */       int var6 = var5.nextIndex();
/* 44 */       double var7 = var5.nextDouble();
/* 45 */       if (var7 != 0.0D) {
/* 46 */         var3 = Math.min(var3, var6);
/* 47 */         var4 = Math.max(var4, var6);
/*    */       } 
/*    */     } 
/*    */     
/* 51 */     this.a = 0.16666666666666666D / a(var4 - var3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static double a(int var0) {
/* 58 */     return 0.1D * (1.0D + 1.0D / (var0 + 1));
/*    */   }
/*    */   
/*    */   public double a(double var0, double var2, double var4) {
/* 62 */     double var6 = var0 * 1.0181268882175227D;
/* 63 */     double var8 = var2 * 1.0181268882175227D;
/* 64 */     double var10 = var4 * 1.0181268882175227D;
/* 65 */     return (this.b.a(var0, var2, var4) + this.c.a(var6, var8, var10)) * this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NoiseGeneratorNormal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */