/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumBlockRotation
/*     */ {
/*  14 */   NONE(PointGroupO.IDENTITY),
/*  15 */   CLOCKWISE_90(PointGroupO.ROT_90_Y_NEG),
/*  16 */   CLOCKWISE_180(PointGroupO.ROT_180_FACE_XZ),
/*  17 */   COUNTERCLOCKWISE_90(PointGroupO.ROT_90_Y_POS);
/*     */   
/*     */   private final PointGroupO e;
/*     */ 
/*     */   
/*     */   EnumBlockRotation(PointGroupO var2) {
/*  23 */     this.e = var2;
/*     */   }
/*     */   
/*     */   public EnumBlockRotation a(EnumBlockRotation var0) {
/*  27 */     switch (null.a[var0.ordinal()]) {
/*     */       case 3:
/*  29 */         switch (null.a[ordinal()]) {
/*     */           case 1:
/*  31 */             return CLOCKWISE_180;
/*     */           case 2:
/*  33 */             return COUNTERCLOCKWISE_90;
/*     */           case 3:
/*  35 */             return NONE;
/*     */           case 4:
/*  37 */             return CLOCKWISE_90;
/*     */         } 
/*     */       case 4:
/*  40 */         switch (null.a[ordinal()]) {
/*     */           case 1:
/*  42 */             return COUNTERCLOCKWISE_90;
/*     */           case 2:
/*  44 */             return NONE;
/*     */           case 3:
/*  46 */             return CLOCKWISE_90;
/*     */           case 4:
/*  48 */             return CLOCKWISE_180;
/*     */         } 
/*     */       case 2:
/*  51 */         switch (null.a[ordinal()]) {
/*     */           case 1:
/*  53 */             return CLOCKWISE_90;
/*     */           case 2:
/*  55 */             return CLOCKWISE_180;
/*     */           case 3:
/*  57 */             return COUNTERCLOCKWISE_90;
/*     */           case 4:
/*  59 */             return NONE;
/*     */         }  break;
/*     */     } 
/*  62 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public PointGroupO a() {
/*  67 */     return this.e;
/*     */   }
/*     */   
/*     */   public EnumDirection a(EnumDirection var0) {
/*  71 */     if (var0.n() == EnumDirection.EnumAxis.Y) {
/*  72 */       return var0;
/*     */     }
/*  74 */     switch (null.a[ordinal()]) {
/*     */       case 3:
/*  76 */         return var0.opposite();
/*     */       case 4:
/*  78 */         return var0.h();
/*     */       case 2:
/*  80 */         return var0.g();
/*     */     } 
/*  82 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(int var0, int var1) {
/*  87 */     switch (null.a[ordinal()]) {
/*     */       case 3:
/*  89 */         return (var0 + var1 / 2) % var1;
/*     */       case 4:
/*  91 */         return (var0 + var1 * 3 / 4) % var1;
/*     */       case 2:
/*  93 */         return (var0 + var1 / 4) % var1;
/*     */     } 
/*  95 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumBlockRotation a(Random var0) {
/* 100 */     return SystemUtils.<EnumBlockRotation>a(values(), var0);
/*     */   }
/*     */   
/*     */   public static List<EnumBlockRotation> b(Random var0) {
/* 104 */     List<EnumBlockRotation> var1 = Lists.newArrayList((Object[])values());
/* 105 */     Collections.shuffle(var1, var0);
/* 106 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumBlockRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */