/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ 
/*     */ public final class VoxelShapeBitSet
/*     */   extends VoxelShapeDiscrete
/*     */ {
/*     */   private final BitSet d;
/*     */   private int e;
/*     */   private int f;
/*     */   private int g;
/*     */   private int h;
/*     */   private int i;
/*     */   private int j;
/*     */   
/*     */   public VoxelShapeBitSet(int var0, int var1, int var2) {
/*  17 */     this(var0, var1, var2, var0, var1, var2, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public VoxelShapeBitSet(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
/*  21 */     super(var0, var1, var2);
/*  22 */     this.d = new BitSet(var0 * var1 * var2);
/*  23 */     this.e = var3;
/*  24 */     this.f = var4;
/*  25 */     this.g = var5;
/*  26 */     this.h = var6;
/*  27 */     this.i = var7;
/*  28 */     this.j = var8;
/*     */   }
/*     */   
/*     */   public VoxelShapeBitSet(VoxelShapeDiscrete var0) {
/*  32 */     super(var0.a, var0.b, var0.c);
/*     */ 
/*     */     
/*  35 */     if (var0 instanceof VoxelShapeBitSet) {
/*  36 */       this.d = (BitSet)((VoxelShapeBitSet)var0).d.clone();
/*     */     } else {
/*  38 */       this.d = new BitSet(this.a * this.b * this.c);
/*  39 */       for (int var1 = 0; var1 < this.a; var1++) {
/*  40 */         for (int var2 = 0; var2 < this.b; var2++) {
/*  41 */           for (int var3 = 0; var3 < this.c; var3++) {
/*  42 */             if (var0.b(var1, var2, var3)) {
/*  43 */               this.d.set(a(var1, var2, var3));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  50 */     this.e = var0.a(EnumDirection.EnumAxis.X);
/*  51 */     this.f = var0.a(EnumDirection.EnumAxis.Y);
/*  52 */     this.g = var0.a(EnumDirection.EnumAxis.Z);
/*     */     
/*  54 */     this.h = var0.b(EnumDirection.EnumAxis.X);
/*  55 */     this.i = var0.b(EnumDirection.EnumAxis.Y);
/*  56 */     this.j = var0.b(EnumDirection.EnumAxis.Z);
/*     */   }
/*     */   
/*     */   protected int a(int var0, int var1, int var2) {
/*  60 */     return (var0 * this.b + var1) * this.c + var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(int var0, int var1, int var2) {
/*  65 */     return this.d.get(a(var0, var1, var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int var0, int var1, int var2, boolean var3, boolean var4) {
/*  70 */     this.d.set(a(var0, var1, var2), var4);
/*  71 */     if (var3 && var4) {
/*  72 */       this.e = Math.min(this.e, var0);
/*  73 */       this.f = Math.min(this.f, var1);
/*  74 */       this.g = Math.min(this.g, var2);
/*     */       
/*  76 */       this.h = Math.max(this.h, var0 + 1);
/*  77 */       this.i = Math.max(this.i, var1 + 1);
/*  78 */       this.j = Math.max(this.j, var2 + 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  84 */     return this.d.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(EnumDirection.EnumAxis var0) {
/*  89 */     return var0.a(this.e, this.f, this.g);
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(EnumDirection.EnumAxis var0) {
/*  94 */     return var0.a(this.h, this.i, this.j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(int var0, int var1, int var2, int var3) {
/* 100 */     if (var2 < 0 || var3 < 0 || var0 < 0) {
/* 101 */       return false;
/*     */     }
/* 103 */     if (var2 >= this.a || var3 >= this.b || var1 > this.c) {
/* 104 */       return false;
/*     */     }
/* 106 */     return (this.d.nextClearBit(a(var2, var3, var0)) >= a(var2, var3, var1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(int var0, int var1, int var2, int var3, boolean var4) {
/* 112 */     this.d.set(a(var2, var3, var0), a(var2, var3, var1), var4);
/*     */   }
/*     */   
/*     */   static VoxelShapeBitSet a(VoxelShapeDiscrete var0, VoxelShapeDiscrete var1, VoxelShapeMerger var2, VoxelShapeMerger var3, VoxelShapeMerger var4, OperatorBoolean var5) {
/* 116 */     VoxelShapeBitSet var6 = new VoxelShapeBitSet(var2.a().size() - 1, var3.a().size() - 1, var4.a().size() - 1);
/* 117 */     int[] var7 = { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     var2.a((var7, var8, var9) -> {
/*     */           boolean[] var10 = { false };
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           boolean var11 = var0.a(());
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           if (var10[0]) {
/*     */             var6[0] = Math.min(var6[0], var9);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             var6[3] = Math.max(var6[3], var9);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/*     */           return var11;
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 153 */     var6.e = var7[0];
/* 154 */     var6.f = var7[1];
/* 155 */     var6.g = var7[2];
/* 156 */     var6.h = var7[3] + 1;
/* 157 */     var6.i = var7[4] + 1;
/* 158 */     var6.j = var7[5] + 1;
/* 159 */     return var6;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeBitSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */