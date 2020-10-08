/*     */ package com.google.common.collect;
/*     */ 
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import com.google.common.primitives.Booleans;
/*     */ import com.google.common.primitives.Ints;
/*     */ import com.google.common.primitives.Longs;
/*     */ import java.util.Comparator;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @GwtCompatible
/*     */ public abstract class ComparisonChain
/*     */ {
/*     */   private ComparisonChain() {}
/*     */   
/*     */   public static ComparisonChain start() {
/*  70 */     return ACTIVE;
/*     */   }
/*     */   
/*  73 */   private static final ComparisonChain ACTIVE = new ComparisonChain()
/*     */     {
/*     */       
/*     */       public ComparisonChain compare(Comparable<Comparable> left, Comparable right)
/*     */       {
/*  78 */         return classify(left.compareTo(right));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public <T> ComparisonChain compare(@Nullable T left, @Nullable T right, Comparator<T> comparator) {
/*  84 */         return classify(comparator.compare(left, right));
/*     */       }
/*     */ 
/*     */       
/*     */       public ComparisonChain compare(int left, int right) {
/*  89 */         return classify(Ints.compare(left, right));
/*     */       }
/*     */ 
/*     */       
/*     */       public ComparisonChain compare(long left, long right) {
/*  94 */         return classify(Longs.compare(left, right));
/*     */       }
/*     */ 
/*     */       
/*     */       public ComparisonChain compare(float left, float right) {
/*  99 */         return classify(Float.compare(left, right));
/*     */       }
/*     */ 
/*     */       
/*     */       public ComparisonChain compare(double left, double right) {
/* 104 */         return classify(Double.compare(left, right));
/*     */       }
/*     */ 
/*     */       
/*     */       public ComparisonChain compareTrueFirst(boolean left, boolean right) {
/* 109 */         return classify(Booleans.compare(right, left));
/*     */       }
/*     */ 
/*     */       
/*     */       public ComparisonChain compareFalseFirst(boolean left, boolean right) {
/* 114 */         return classify(Booleans.compare(left, right));
/*     */       }
/*     */       
/*     */       ComparisonChain classify(int result) {
/* 118 */         return (result < 0) ? ComparisonChain.LESS : ((result > 0) ? ComparisonChain.GREATER : ComparisonChain.ACTIVE);
/*     */       }
/*     */ 
/*     */       
/*     */       public int result() {
/* 123 */         return 0;
/*     */       }
/*     */     };
/*     */   
/* 127 */   private static final ComparisonChain LESS = new InactiveComparisonChain(-1);
/*     */   
/* 129 */   private static final ComparisonChain GREATER = new InactiveComparisonChain(1);
/*     */   
/*     */   private static final class InactiveComparisonChain extends ComparisonChain {
/*     */     final int result;
/*     */     
/*     */     InactiveComparisonChain(int result) {
/* 135 */       this.result = result;
/*     */     }
/*     */ 
/*     */     
/*     */     public ComparisonChain compare(@Nullable Comparable left, @Nullable Comparable right) {
/* 140 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> ComparisonChain compare(@Nullable T left, @Nullable T right, @Nullable Comparator<T> comparator) {
/* 146 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ComparisonChain compare(int left, int right) {
/* 151 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ComparisonChain compare(long left, long right) {
/* 156 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ComparisonChain compare(float left, float right) {
/* 161 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ComparisonChain compare(double left, double right) {
/* 166 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ComparisonChain compareTrueFirst(boolean left, boolean right) {
/* 171 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ComparisonChain compareFalseFirst(boolean left, boolean right) {
/* 176 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public int result() {
/* 181 */       return this.result;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public final ComparisonChain compare(Boolean left, Boolean right) {
/* 237 */     return compareFalseFirst(left.booleanValue(), right.booleanValue());
/*     */   }
/*     */   
/*     */   public abstract ComparisonChain compare(Comparable<?> paramComparable1, Comparable<?> paramComparable2);
/*     */   
/*     */   public abstract <T> ComparisonChain compare(@Nullable T paramT1, @Nullable T paramT2, Comparator<T> paramComparator);
/*     */   
/*     */   public abstract ComparisonChain compare(int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract ComparisonChain compare(long paramLong1, long paramLong2);
/*     */   
/*     */   public abstract ComparisonChain compare(float paramFloat1, float paramFloat2);
/*     */   
/*     */   public abstract ComparisonChain compare(double paramDouble1, double paramDouble2);
/*     */   
/*     */   public abstract ComparisonChain compareTrueFirst(boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   public abstract ComparisonChain compareFalseFirst(boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   public abstract int result();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\google\common\collect\ComparisonChain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */