/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ public class IntRange
/*    */ {
/*    */   private final int a;
/*    */   private final int b;
/*    */   
/*    */   public IntRange(int var0, int var1) {
/* 22 */     if (var1 < var0) {
/* 23 */       throw new IllegalArgumentException("max must be >= minInclusive! Given minInclusive: " + var0 + ", Given max: " + var1);
/*    */     }
/* 25 */     this.a = var0;
/* 26 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public static IntRange a(int var0, int var1) {
/* 30 */     return new IntRange(var0, var1);
/*    */   }
/*    */   
/*    */   public int a(Random var0) {
/* 34 */     if (this.a == this.b) {
/* 35 */       return this.a;
/*    */     }
/* 37 */     return var0.nextInt(this.b - this.a + 1) + this.a;
/*    */   }
/*    */   
/*    */   public int a() {
/* 41 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 45 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     return "IntRange[" + this.a + "-" + this.b + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IntRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */