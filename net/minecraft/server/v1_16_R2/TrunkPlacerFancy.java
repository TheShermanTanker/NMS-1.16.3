/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TrunkPlacerFancy
/*     */   extends TrunkPlacer
/*     */ {
/*     */   public static final Codec<TrunkPlacerFancy> a;
/*     */   
/*     */   static {
/*  22 */     a = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, TrunkPlacerFancy::new));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TrunkPlacerFancy(int var0, int var1, int var2) {
/*  30 */     super(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TrunkPlacers<?> a() {
/*  35 */     return TrunkPlacers.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WorldGenFoilagePlacer.b> a(VirtualLevelWritable var0, Random var1, int var2, BlockPosition var3, Set<BlockPosition> var4, StructureBoundingBox var5, WorldGenFeatureTreeConfiguration var6) {
/*  40 */     int var7 = 5;
/*  41 */     int var8 = var2 + 2;
/*  42 */     int var9 = MathHelper.floor(var8 * 0.618D);
/*     */     
/*  44 */     if (!var6.e) {
/*  45 */       a(var0, var3.down());
/*     */     }
/*     */     
/*  48 */     double var10 = 1.0D;
/*  49 */     int var12 = Math.min(1, MathHelper.floor(1.382D + Math.pow(1.0D * var8 / 13.0D, 2.0D)));
/*     */     
/*  51 */     int var13 = var3.getY() + var9;
/*  52 */     int var14 = var8 - 5;
/*     */     
/*  54 */     List<a> var15 = Lists.newArrayList();
/*  55 */     var15.add(new a(var3.up(var14), var13));
/*     */     
/*  57 */     for (; var14 >= 0; var14--) {
/*  58 */       float f = b(var8, var14);
/*  59 */       if (f >= 0.0F)
/*     */       {
/*     */ 
/*     */         
/*  63 */         for (int var17 = 0; var17 < var12; var17++) {
/*  64 */           double var18 = 1.0D;
/*  65 */           double var20 = 1.0D * f * (var1.nextFloat() + 0.328D);
/*  66 */           double var22 = (var1.nextFloat() * 2.0F) * Math.PI;
/*     */           
/*  68 */           double var24 = var20 * Math.sin(var22) + 0.5D;
/*  69 */           double var26 = var20 * Math.cos(var22) + 0.5D;
/*     */           
/*  71 */           BlockPosition var28 = var3.a(var24, (var14 - 1), var26);
/*  72 */           BlockPosition var29 = var28.up(5);
/*     */ 
/*     */           
/*  75 */           if (a(var0, var1, var28, var29, false, var4, var5, var6)) {
/*     */             
/*  77 */             int var30 = var3.getX() - var28.getX();
/*  78 */             int var31 = var3.getZ() - var28.getZ();
/*     */             
/*  80 */             double var32 = var28.getY() - Math.sqrt((var30 * var30 + var31 * var31)) * 0.381D;
/*  81 */             int var34 = (var32 > var13) ? var13 : (int)var32;
/*  82 */             BlockPosition var35 = new BlockPosition(var3.getX(), var34, var3.getZ());
/*     */ 
/*     */             
/*  85 */             if (a(var0, var1, var35, var28, false, var4, var5, var6))
/*     */             {
/*  87 */               var15.add(new a(var28, var35.getY())); } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*  92 */     a(var0, var1, var3, var3.up(var9), true, var4, var5, var6);
/*  93 */     a(var0, var1, var8, var3, var15, var4, var5, var6);
/*     */     
/*  95 */     List<WorldGenFoilagePlacer.b> var16 = Lists.newArrayList();
/*  96 */     for (a var18 : var15) {
/*  97 */       if (a(var8, var18.a() - var3.getY())) {
/*  98 */         var16.add(a.a(var18));
/*     */       }
/*     */     } 
/* 101 */     return var16;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(VirtualLevelWritable var0, Random var1, BlockPosition var2, BlockPosition var3, boolean var4, Set<BlockPosition> var5, StructureBoundingBox var6, WorldGenFeatureTreeConfiguration var7) {
/* 106 */     if (!var4 && Objects.equals(var2, var3)) {
/* 107 */       return true;
/*     */     }
/*     */     
/* 110 */     BlockPosition var8 = var3.b(-var2.getX(), -var2.getY(), -var2.getZ());
/*     */     
/* 112 */     int var9 = a(var8);
/*     */     
/* 114 */     float var10 = var8.getX() / var9;
/* 115 */     float var11 = var8.getY() / var9;
/* 116 */     float var12 = var8.getZ() / var9;
/*     */     
/* 118 */     for (int var13 = 0; var13 <= var9; var13++) {
/* 119 */       BlockPosition var14 = var2.a((0.5F + var13 * var10), (0.5F + var13 * var11), (0.5F + var13 * var12));
/* 120 */       if (var4) {
/* 121 */         a(var0, var14, var7.b.a(var1, var14).set(BlockRotatable.AXIS, a(var2, var14)), var6);
/* 122 */         var5.add(var14.immutableCopy());
/*     */       
/*     */       }
/* 125 */       else if (!WorldGenTrees.c(var0, var14)) {
/* 126 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   private int a(BlockPosition var0) {
/* 134 */     int var1 = MathHelper.a(var0.getX());
/* 135 */     int var2 = MathHelper.a(var0.getY());
/* 136 */     int var3 = MathHelper.a(var0.getZ());
/*     */     
/* 138 */     return Math.max(var1, Math.max(var2, var3));
/*     */   }
/*     */   
/*     */   private EnumDirection.EnumAxis a(BlockPosition var0, BlockPosition var1) {
/* 142 */     EnumDirection.EnumAxis var2 = EnumDirection.EnumAxis.Y;
/* 143 */     int var3 = Math.abs(var1.getX() - var0.getX());
/* 144 */     int var4 = Math.abs(var1.getZ() - var0.getZ());
/* 145 */     int var5 = Math.max(var3, var4);
/*     */     
/* 147 */     if (var5 > 0) {
/* 148 */       if (var3 == var5) {
/* 149 */         var2 = EnumDirection.EnumAxis.X;
/*     */       } else {
/* 151 */         var2 = EnumDirection.EnumAxis.Z;
/*     */       } 
/*     */     }
/* 154 */     return var2;
/*     */   }
/*     */   
/*     */   private boolean a(int var0, int var1) {
/* 158 */     return (var1 >= var0 * 0.2D);
/*     */   }
/*     */   
/*     */   private void a(VirtualLevelWritable var0, Random var1, int var2, BlockPosition var3, List<a> var4, Set<BlockPosition> var5, StructureBoundingBox var6, WorldGenFeatureTreeConfiguration var7) {
/* 162 */     for (a var9 : var4) {
/* 163 */       int var10 = var9.a();
/* 164 */       BlockPosition var11 = new BlockPosition(var3.getX(), var10, var3.getZ());
/*     */       
/* 166 */       if (!var11.equals(a.a(var9).a()) && a(var2, var10 - var3.getY())) {
/* 167 */         a(var0, var1, var11, a.a(var9).a(), true, var5, var6, var7);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float b(int var0, int var1) {
/* 174 */     if (var1 < var0 * 0.3F) {
/* 175 */       return -1.0F;
/*     */     }
/*     */     
/* 178 */     float var2 = var0 / 2.0F;
/* 179 */     float var3 = var2 - var1;
/*     */     
/* 181 */     float var4 = MathHelper.c(var2 * var2 - var3 * var3);
/* 182 */     if (var3 == 0.0F) {
/* 183 */       var4 = var2;
/* 184 */     } else if (Math.abs(var3) >= var2) {
/* 185 */       return 0.0F;
/*     */     } 
/*     */     
/* 188 */     return var4 * 0.5F;
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final WorldGenFoilagePlacer.b a;
/*     */     private final int b;
/*     */     
/*     */     public a(BlockPosition var0, int var1) {
/* 196 */       this.a = new WorldGenFoilagePlacer.b(var0, 0, false);
/* 197 */       this.b = var1;
/*     */     }
/*     */     
/*     */     public int a() {
/* 201 */       return this.b;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TrunkPlacerFancy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */