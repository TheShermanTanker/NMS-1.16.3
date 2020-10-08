/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*     */ import it.unimi.dsi.fastutil.ints.IntBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntRBTreeSet;
/*     */ import it.unimi.dsi.fastutil.ints.IntSortedSet;
/*     */ import java.util.List;
/*     */ import java.util.stream.IntStream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NoiseGeneratorOctaves
/*     */   implements NoiseGenerator
/*     */ {
/*     */   private final NoiseGeneratorPerlin[] a;
/*     */   private final DoubleList b;
/*     */   private final double c;
/*     */   private final double d;
/*     */   
/*     */   public NoiseGeneratorOctaves(SeededRandom var0, IntStream var1) {
/*  27 */     this(var0, var1.boxed().collect(ImmutableList.toImmutableList()));
/*     */   }
/*     */   
/*     */   public NoiseGeneratorOctaves(SeededRandom var0, List<Integer> var1) {
/*  31 */     this(var0, (IntSortedSet)new IntRBTreeSet(var1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NoiseGeneratorOctaves a(SeededRandom var0, int var1, DoubleList var2) {
/*  39 */     return new NoiseGeneratorOctaves(var0, Pair.of(Integer.valueOf(var1), var2));
/*     */   }
/*     */   
/*     */   private static Pair<Integer, DoubleList> a(IntSortedSet var0) {
/*  43 */     if (var0.isEmpty()) {
/*  44 */       throw new IllegalArgumentException("Need some octaves!");
/*     */     }
/*     */     
/*  47 */     int var1 = -var0.firstInt();
/*  48 */     int var2 = var0.lastInt();
/*     */     
/*  50 */     int var3 = var1 + var2 + 1;
/*  51 */     if (var3 < 1) {
/*  52 */       throw new IllegalArgumentException("Total number of octaves needs to be >= 1");
/*     */     }
/*     */     
/*  55 */     DoubleArrayList doubleArrayList = new DoubleArrayList(new double[var3]);
/*  56 */     IntBidirectionalIterator var5 = var0.iterator();
/*  57 */     while (var5.hasNext()) {
/*  58 */       int var6 = var5.nextInt();
/*  59 */       doubleArrayList.set(var6 + var1, 1.0D);
/*     */     } 
/*     */     
/*  62 */     return Pair.of(Integer.valueOf(-var1), doubleArrayList);
/*     */   }
/*     */   
/*     */   private NoiseGeneratorOctaves(SeededRandom var0, IntSortedSet var1) {
/*  66 */     this(var0, a(var1));
/*     */   }
/*     */   
/*     */   private NoiseGeneratorOctaves(SeededRandom var0, Pair<Integer, DoubleList> var1) {
/*  70 */     int var2 = ((Integer)var1.getFirst()).intValue();
/*  71 */     this.b = (DoubleList)var1.getSecond();
/*  72 */     NoiseGeneratorPerlin var3 = new NoiseGeneratorPerlin(var0);
/*  73 */     int var4 = this.b.size();
/*  74 */     int var5 = -var2;
/*     */     
/*  76 */     this.a = new NoiseGeneratorPerlin[var4];
/*  77 */     if (var5 >= 0 && var5 < var4) {
/*  78 */       double d = this.b.getDouble(var5);
/*  79 */       if (d != 0.0D) {
/*  80 */         this.a[var5] = var3;
/*     */       }
/*     */     } 
/*     */     
/*  84 */     for (int var6 = var5 - 1; var6 >= 0; var6--) {
/*  85 */       if (var6 < var4) {
/*  86 */         double var7 = this.b.getDouble(var6);
/*  87 */         if (var7 != 0.0D) {
/*  88 */           this.a[var6] = new NoiseGeneratorPerlin(var0);
/*     */         } else {
/*  90 */           var0.a(262);
/*     */         } 
/*     */       } else {
/*  93 */         var0.a(262);
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     if (var5 < var4 - 1) {
/*     */       
/*  99 */       long l = (long)(var3.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D) * 9.223372036854776E18D);
/* 100 */       SeededRandom var8 = new SeededRandom(l);
/* 101 */       for (int var9 = var5 + 1; var9 < var4; var9++) {
/* 102 */         if (var9 >= 0) {
/* 103 */           double var10 = this.b.getDouble(var9);
/* 104 */           if (var10 != 0.0D) {
/* 105 */             this.a[var9] = new NoiseGeneratorPerlin(var8);
/*     */           } else {
/* 107 */             var8.a(262);
/*     */           } 
/*     */         } else {
/* 110 */           var8.a(262);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     this.d = Math.pow(2.0D, -var5);
/* 116 */     this.c = Math.pow(2.0D, (var4 - 1)) / (Math.pow(2.0D, var4) - 1.0D);
/*     */   }
/*     */   
/*     */   public double a(double var0, double var2, double var4) {
/* 120 */     return a(var0, var2, var4, 0.0D, 0.0D, false);
/*     */   }
/*     */   
/*     */   public double a(double var0, double var2, double var4, double var6, double var8, boolean var10) {
/* 124 */     double var11 = 0.0D;
/* 125 */     double var13 = this.d;
/* 126 */     double var15 = this.c;
/*     */     
/* 128 */     for (int var17 = 0; var17 < this.a.length; var17++) {
/* 129 */       NoiseGeneratorPerlin var18 = this.a[var17];
/* 130 */       if (var18 != null) {
/* 131 */         var11 += this.b.getDouble(var17) * var18.a(a(var0 * var13), var10 ? -var18.b : a(var2 * var13), a(var4 * var13), var6 * var13, var8 * var13) * var15;
/*     */       }
/* 133 */       var13 *= 2.0D;
/* 134 */       var15 /= 2.0D;
/*     */     } 
/*     */     
/* 137 */     return var11;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NoiseGeneratorPerlin a(int var0) {
/* 142 */     return this.a[this.a.length - 1 - var0];
/*     */   }
/*     */   
/*     */   public static double a(double var0) {
/* 146 */     return var0 - MathHelper.d(var0 / 3.3554432E7D + 0.5D) * 3.3554432E7D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double a(double var0, double var2, double var4, double var6) {
/* 151 */     return a(var0, var2, 0.0D, var4, var6, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NoiseGeneratorOctaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */