/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShapeDetector
/*     */ {
/*     */   private final Predicate<ShapeDetectorBlock>[][][] a;
/*     */   private final int b;
/*     */   private final int c;
/*     */   private final int d;
/*     */   
/*     */   public ShapeDetector(Predicate<ShapeDetectorBlock>[][][] var0) {
/*  22 */     this.a = var0;
/*     */     
/*  24 */     this.b = var0.length;
/*     */     
/*  26 */     if (this.b > 0) {
/*  27 */       this.c = (var0[0]).length;
/*     */       
/*  29 */       if (this.c > 0) {
/*  30 */         this.d = (var0[0][0]).length;
/*     */       } else {
/*  32 */         this.d = 0;
/*     */       } 
/*     */     } else {
/*  35 */       this.c = 0;
/*  36 */       this.d = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int a() {
/*  41 */     return this.b;
/*     */   }
/*     */   
/*     */   public int b() {
/*  45 */     return this.c;
/*     */   }
/*     */   
/*     */   public int c() {
/*  49 */     return this.d;
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
/*     */   @Nullable
/*     */   private ShapeDetectorCollection a(BlockPosition var0, EnumDirection var1, EnumDirection var2, LoadingCache<BlockPosition, ShapeDetectorBlock> var3) {
/*  64 */     for (int var4 = 0; var4 < this.d; var4++) {
/*  65 */       for (int var5 = 0; var5 < this.c; var5++) {
/*  66 */         for (int var6 = 0; var6 < this.b; var6++) {
/*  67 */           if (!this.a[var6][var5][var4].test(var3.getUnchecked(a(var0, var1, var2, var4, var5, var6)))) {
/*  68 */             return null;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     return new ShapeDetectorCollection(var0, var1, var2, var3, this.d, this.c, this.b);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ShapeDetectorCollection a(IWorldReader var0, BlockPosition var1) {
/*  79 */     LoadingCache<BlockPosition, ShapeDetectorBlock> var2 = a(var0, false);
/*     */     
/*  81 */     int var3 = Math.max(Math.max(this.d, this.c), this.b);
/*     */     
/*  83 */     for (BlockPosition var5 : BlockPosition.a(var1, var1.b(var3 - 1, var3 - 1, var3 - 1))) {
/*  84 */       for (EnumDirection var9 : EnumDirection.values()) {
/*  85 */         for (EnumDirection var13 : EnumDirection.values()) {
/*  86 */           if (var13 != var9 && var13 != var9.opposite()) {
/*     */ 
/*     */ 
/*     */             
/*  90 */             ShapeDetectorCollection var14 = a(var5, var9, var13, var2);
/*  91 */             if (var14 != null) {
/*  92 */               return var14;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  98 */     return null;
/*     */   }
/*     */   
/*     */   public static LoadingCache<BlockPosition, ShapeDetectorBlock> a(IWorldReader var0, boolean var1) {
/* 102 */     return CacheBuilder.newBuilder().build(new BlockLoader(var0, var1));
/*     */   }
/*     */   
/*     */   protected static BlockPosition a(BlockPosition var0, EnumDirection var1, EnumDirection var2, int var3, int var4, int var5) {
/* 106 */     if (var1 == var2 || var1 == var2.opposite()) {
/* 107 */       throw new IllegalArgumentException("Invalid forwards & up combination");
/*     */     }
/*     */     
/* 110 */     BaseBlockPosition var6 = new BaseBlockPosition(var1.getAdjacentX(), var1.getAdjacentY(), var1.getAdjacentZ());
/* 111 */     BaseBlockPosition var7 = new BaseBlockPosition(var2.getAdjacentX(), var2.getAdjacentY(), var2.getAdjacentZ());
/* 112 */     BaseBlockPosition var8 = var6.d(var7);
/*     */     
/* 114 */     return var0.b(var7
/* 115 */         .getX() * -var4 + var8.getX() * var3 + var6.getX() * var5, var7
/* 116 */         .getY() * -var4 + var8.getY() * var3 + var6.getY() * var5, var7
/* 117 */         .getZ() * -var4 + var8.getZ() * var3 + var6.getZ() * var5);
/*     */   }
/*     */   
/*     */   static class BlockLoader
/*     */     extends CacheLoader<BlockPosition, ShapeDetectorBlock> {
/*     */     private final IWorldReader a;
/*     */     private final boolean b;
/*     */     
/*     */     public BlockLoader(IWorldReader var0, boolean var1) {
/* 126 */       this.a = var0;
/* 127 */       this.b = var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public ShapeDetectorBlock load(BlockPosition var0) throws Exception {
/* 132 */       return new ShapeDetectorBlock(this.a, var0, this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ShapeDetectorCollection {
/*     */     private final BlockPosition a;
/*     */     private final EnumDirection b;
/*     */     private final EnumDirection c;
/*     */     private final LoadingCache<BlockPosition, ShapeDetectorBlock> d;
/*     */     private final int e;
/*     */     private final int f;
/*     */     private final int g;
/*     */     
/*     */     public ShapeDetectorCollection(BlockPosition var0, EnumDirection var1, EnumDirection var2, LoadingCache<BlockPosition, ShapeDetectorBlock> var3, int var4, int var5, int var6) {
/* 146 */       this.a = var0;
/* 147 */       this.b = var1;
/* 148 */       this.c = var2;
/* 149 */       this.d = var3;
/* 150 */       this.e = var4;
/* 151 */       this.f = var5;
/* 152 */       this.g = var6;
/*     */     }
/*     */     
/*     */     public BlockPosition a() {
/* 156 */       return this.a;
/*     */     }
/*     */     
/*     */     public EnumDirection getFacing() {
/* 160 */       return this.b;
/*     */     }
/*     */     
/*     */     public EnumDirection c() {
/* 164 */       return this.c;
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
/*     */     public ShapeDetectorBlock a(int var0, int var1, int var2) {
/* 180 */       return (ShapeDetectorBlock)this.d.getUnchecked(ShapeDetector.a(this.a, getFacing(), c(), var0, var1, var2));
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 185 */       return MoreObjects.toStringHelper(this)
/* 186 */         .add("up", this.c)
/* 187 */         .add("forwards", this.b)
/* 188 */         .add("frontTopLeft", this.a)
/* 189 */         .toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ShapeDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */