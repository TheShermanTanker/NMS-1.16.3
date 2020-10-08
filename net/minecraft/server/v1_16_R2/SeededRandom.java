/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class SeededRandom
/*    */   extends Random {
/*    */   private int a;
/*    */   
/*    */   public SeededRandom() {}
/*    */   
/*    */   public SeededRandom(long var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(int var0) {
/* 20 */     for (int var1 = 0; var1 < var0; var1++) {
/* 21 */       next(1);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected int next(int var0) {
/* 27 */     this.a++;
/* 28 */     return super.next(var0);
/*    */   }
/*    */   
/*    */   public long a(int var0, int var1) {
/* 32 */     long var2 = var0 * 341873128712L + var1 * 132897987541L;
/* 33 */     setSeed(var2);
/* 34 */     return var2;
/*    */   }
/*    */   
/*    */   public long a(long var0, int var2, int var3) {
/* 38 */     setSeed(var0);
/*    */     
/* 40 */     long var4 = nextLong() | 0x1L;
/* 41 */     long var6 = nextLong() | 0x1L;
/* 42 */     long var8 = var2 * var4 + var3 * var6 ^ var0;
/* 43 */     setSeed(var8);
/* 44 */     return var8;
/*    */   }
/*    */   
/*    */   public long b(long var0, int var2, int var3) {
/* 48 */     long var4 = var0 + var2 + (10000 * var3);
/* 49 */     setSeed(var4);
/* 50 */     return var4;
/*    */   }
/*    */   
/*    */   public long c(long var0, int var2, int var3) {
/* 54 */     setSeed(var0);
/* 55 */     long var4 = nextLong();
/* 56 */     long var6 = nextLong();
/* 57 */     long var8 = var2 * var4 ^ var3 * var6 ^ var0;
/* 58 */     setSeed(var8);
/* 59 */     return var8;
/*    */   }
/*    */   
/*    */   public long a(long var0, int var2, int var3, int var4) {
/* 63 */     long var5 = var2 * 341873128712L + var3 * 132897987541L + var0 + var4;
/* 64 */     setSeed(var5);
/* 65 */     return var5;
/*    */   }
/*    */   
/*    */   public static Random a(int var0, int var1, long var2, long var4) {
/* 69 */     return new Random(var2 + (var0 * var0 * 4987142) + (var0 * 5947611) + (var1 * var1) * 4392871L + (var1 * 389711) ^ var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SeededRandom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */