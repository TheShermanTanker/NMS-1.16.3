/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*     */ import java.util.function.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockUtil
/*     */ {
/*     */   public static class IntBounds
/*     */   {
/*     */     public final int a;
/*     */     public final int b;
/*     */     
/*     */     public IntBounds(int var0, int var1) {
/*  18 */       this.a = var0;
/*  19 */       this.b = var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  24 */       return "IntBounds{min=" + this.a + ", max=" + this.b + '}';
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Rectangle
/*     */   {
/*     */     public final BlockPosition origin;
/*     */     
/*     */     public final int side1;
/*     */     public final int side2;
/*     */     
/*     */     public Rectangle(BlockPosition var0, int var1, int var2) {
/*  37 */       this.origin = var0;
/*  38 */       this.side1 = var1;
/*  39 */       this.side2 = var2;
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
/*     */   public static Rectangle a(BlockPosition var0, EnumDirection.EnumAxis var1, int var2, EnumDirection.EnumAxis var3, int var4, Predicate<BlockPosition> var5) {
/*  57 */     BlockPosition.MutableBlockPosition var6 = var0.i();
/*     */     
/*  59 */     EnumDirection var7 = EnumDirection.a(EnumDirection.EnumAxisDirection.NEGATIVE, var1);
/*  60 */     EnumDirection var8 = var7.opposite();
/*     */     
/*  62 */     EnumDirection var9 = EnumDirection.a(EnumDirection.EnumAxisDirection.NEGATIVE, var3);
/*  63 */     EnumDirection var10 = var9.opposite();
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
/*  79 */     int var11 = a(var5, var6.g(var0), var7, var2);
/*  80 */     int var12 = a(var5, var6.g(var0), var8, var2);
/*     */     
/*  82 */     int var13 = var11;
/*  83 */     IntBounds[] var14 = new IntBounds[var13 + 1 + var12];
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
/*  99 */     var14[var13] = new IntBounds(
/* 100 */         a(var5, var6.g(var0), var9, var4), 
/* 101 */         a(var5, var6.g(var0), var10, var4));
/*     */ 
/*     */     
/* 104 */     int var15 = (var14[var13]).a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int var16;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     for (var16 = 1; var16 <= var11; var16++) {
/* 125 */       IntBounds intBounds = var14[var13 - var16 - 1];
/* 126 */       var14[var13 - var16] = new IntBounds(
/* 127 */           a(var5, var6.g(var0).c(var7, var16), var9, intBounds.a), 
/* 128 */           a(var5, var6.g(var0).c(var7, var16), var10, intBounds.b));
/*     */     } 
/*     */ 
/*     */     
/* 132 */     for (var16 = 1; var16 <= var12; var16++) {
/* 133 */       IntBounds intBounds = var14[var13 + var16 - 1];
/* 134 */       var14[var13 + var16] = new IntBounds(
/* 135 */           a(var5, var6.g(var0).c(var8, var16), var9, intBounds.a), 
/* 136 */           a(var5, var6.g(var0).c(var8, var16), var10, intBounds.b));
/*     */     } 
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
/* 154 */     var16 = 0;
/* 155 */     int var17 = 0;
/* 156 */     int var18 = 0;
/* 157 */     int var19 = 0;
/*     */     
/* 159 */     int[] var20 = new int[var14.length];
/*     */     
/* 161 */     for (int var21 = var15; var21 >= 0; var21--) {
/* 162 */       for (int i = 0; i < var14.length; i++) {
/* 163 */         IntBounds intBounds = var14[i];
/* 164 */         int j = var15 - intBounds.a;
/* 165 */         int k = var15 + intBounds.b;
/*     */         
/* 167 */         var20[i] = (var21 >= j && var21 <= k) ? (k + 1 - var21) : 0;
/*     */       } 
/*     */       
/* 170 */       Pair<IntBounds, Integer> var22 = a(var20);
/* 171 */       IntBounds var23 = (IntBounds)var22.getFirst();
/* 172 */       int var24 = 1 + var23.b - var23.a;
/* 173 */       int var25 = ((Integer)var22.getSecond()).intValue();
/*     */       
/* 175 */       if (var24 * var25 > var18 * var19) {
/* 176 */         var16 = var23.a;
/* 177 */         var17 = var21;
/* 178 */         var18 = var24;
/* 179 */         var19 = var25;
/*     */       } 
/*     */     } 
/*     */     
/* 183 */     return new Rectangle(var0
/* 184 */         .a(var1, var16 - var13).a(var3, var17 - var15), var18, var19);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(Predicate<BlockPosition> var0, BlockPosition.MutableBlockPosition var1, EnumDirection var2, int var3) {
/* 191 */     int var4 = 0;
/* 192 */     while (var4 < var3 && var0.test(var1.c(var2))) {
/* 193 */       var4++;
/*     */     }
/* 195 */     return var4;
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static Pair<IntBounds, Integer> a(int[] var0) {
/* 200 */     int var1 = 0;
/* 201 */     int var2 = 0;
/* 202 */     int var3 = 0;
/*     */     
/* 204 */     IntArrayList intArrayList = new IntArrayList();
/* 205 */     intArrayList.push(0);
/* 206 */     for (int var5 = 1; var5 <= var0.length; var5++) {
/* 207 */       int var6 = (var5 == var0.length) ? 0 : var0[var5];
/* 208 */       while (!intArrayList.isEmpty()) {
/* 209 */         int var7 = var0[intArrayList.topInt()];
/* 210 */         if (var6 >= var7) {
/* 211 */           intArrayList.push(var5);
/*     */           
/*     */           break;
/*     */         } 
/* 215 */         intArrayList.popInt();
/* 216 */         int var8 = intArrayList.isEmpty() ? 0 : (intArrayList.topInt() + 1);
/*     */         
/* 218 */         if (var7 * (var5 - var8) > var3 * (var2 - var1)) {
/* 219 */           var2 = var5;
/* 220 */           var1 = var8;
/* 221 */           var3 = var7;
/*     */         } 
/*     */       } 
/*     */       
/* 225 */       if (intArrayList.isEmpty()) {
/* 226 */         intArrayList.push(var5);
/*     */       }
/*     */     } 
/*     */     
/* 230 */     return new Pair(new IntBounds(var1, var2 - 1), Integer.valueOf(var3));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */