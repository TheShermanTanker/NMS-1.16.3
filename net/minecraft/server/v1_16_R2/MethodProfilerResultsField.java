/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public final class MethodProfilerResultsField implements Comparable<MethodProfilerResultsField> {
/*    */   public final double a;
/*    */   public final double b;
/*    */   public final long c;
/*    */   public final String d;
/*    */   
/*    */   public MethodProfilerResultsField(String var0, double var1, double var3, long var5) {
/* 10 */     this.d = var0;
/* 11 */     this.a = var1;
/* 12 */     this.b = var3;
/* 13 */     this.c = var5;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(MethodProfilerResultsField var0) {
/* 18 */     if (var0.a < this.a) {
/* 19 */       return -1;
/*    */     }
/* 21 */     if (var0.a > this.a) {
/* 22 */       return 1;
/*    */     }
/* 24 */     return var0.d.compareTo(this.d);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MethodProfilerResultsField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */