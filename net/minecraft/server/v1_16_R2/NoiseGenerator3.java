/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import it.unimi.dsi.fastutil.ints.IntRBTreeSet;
/*    */ import it.unimi.dsi.fastutil.ints.IntSortedSet;
/*    */ import java.util.List;
/*    */ import java.util.stream.IntStream;
/*    */ 
/*    */ public class NoiseGenerator3
/*    */   implements NoiseGenerator
/*    */ {
/*    */   private final NoiseGenerator3Handler[] a;
/*    */   private final double b;
/*    */   private final double c;
/*    */   
/*    */   public NoiseGenerator3(SeededRandom var0, IntStream var1) {
/* 17 */     this(var0, var1.boxed().collect(ImmutableList.toImmutableList()));
/*    */   }
/*    */   
/*    */   public NoiseGenerator3(SeededRandom var0, List<Integer> var1) {
/* 21 */     this(var0, (IntSortedSet)new IntRBTreeSet(var1));
/*    */   }
/*    */   
/*    */   private NoiseGenerator3(SeededRandom var0, IntSortedSet var1) {
/* 25 */     if (var1.isEmpty()) {
/* 26 */       throw new IllegalArgumentException("Need some octaves!");
/*    */     }
/*    */     
/* 29 */     int var2 = -var1.firstInt();
/* 30 */     int var3 = var1.lastInt();
/*    */     
/* 32 */     int var4 = var2 + var3 + 1;
/* 33 */     if (var4 < 1) {
/* 34 */       throw new IllegalArgumentException("Total number of octaves needs to be >= 1");
/*    */     }
/*    */     
/* 37 */     NoiseGenerator3Handler var5 = new NoiseGenerator3Handler(var0);
/* 38 */     int var6 = var3;
/*    */     
/* 40 */     this.a = new NoiseGenerator3Handler[var4];
/* 41 */     if (var6 >= 0 && var6 < var4 && var1.contains(0)) {
/* 42 */       this.a[var6] = var5;
/*    */     }
/*    */     
/* 45 */     for (int var7 = var6 + 1; var7 < var4; var7++) {
/* 46 */       if (var7 >= 0 && var1.contains(var6 - var7)) {
/* 47 */         this.a[var7] = new NoiseGenerator3Handler(var0);
/*    */       } else {
/* 49 */         var0.a(262);
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     if (var3 > 0) {
/*    */       
/* 55 */       long l = (long)(var5.a(var5.b, var5.c, var5.d) * 9.223372036854776E18D);
/* 56 */       SeededRandom var9 = new SeededRandom(l);
/* 57 */       for (int var10 = var6 - 1; var10 >= 0; var10--) {
/* 58 */         if (var10 < var4 && var1.contains(var6 - var10)) {
/* 59 */           this.a[var10] = new NoiseGenerator3Handler(var9);
/*    */         } else {
/* 61 */           var9.a(262);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     this.c = Math.pow(2.0D, var3);
/* 67 */     this.b = 1.0D / (Math.pow(2.0D, var4) - 1.0D);
/*    */   }
/*    */   
/*    */   public double a(double var0, double var2, boolean var4) {
/* 71 */     double var5 = 0.0D;
/* 72 */     double var7 = this.c;
/* 73 */     double var9 = this.b;
/*    */     
/* 75 */     for (NoiseGenerator3Handler var14 : this.a) {
/* 76 */       if (var14 != null) {
/* 77 */         var5 += var14.a(var0 * var7 + (var4 ? var14.b : 0.0D), var2 * var7 + (var4 ? var14.c : 0.0D)) * var9;
/*    */       }
/* 79 */       var7 /= 2.0D;
/* 80 */       var9 *= 2.0D;
/*    */     } 
/*    */     
/* 83 */     return var5;
/*    */   }
/*    */ 
/*    */   
/*    */   public double a(double var0, double var2, double var4, double var6) {
/* 88 */     return a(var0, var2, true) * 0.55D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NoiseGenerator3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */