/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenSurfaceMesaBryce
/*     */   extends WorldGenSurfaceMesa
/*     */ {
/*  14 */   private static final IBlockData K = Blocks.WHITE_TERRACOTTA.getBlockData();
/*  15 */   private static final IBlockData L = Blocks.ORANGE_TERRACOTTA.getBlockData();
/*  16 */   private static final IBlockData M = Blocks.TERRACOTTA.getBlockData();
/*     */   
/*     */   public WorldGenSurfaceMesaBryce(Codec<WorldGenSurfaceConfigurationBase> var0) {
/*  19 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, int var10, long var11, WorldGenSurfaceConfigurationBase var13) {
/*  24 */     double var14 = 0.0D;
/*     */     
/*  26 */     double var16 = Math.min(Math.abs(var6), this.c.a(var3 * 0.25D, var4 * 0.25D, false) * 15.0D);
/*  27 */     if (var16 > 0.0D) {
/*  28 */       double d1 = 0.001953125D;
/*  29 */       double d2 = Math.abs(this.d.a(var3 * 0.001953125D, var4 * 0.001953125D, false));
/*  30 */       var14 = var16 * var16 * 2.5D;
/*  31 */       double d3 = Math.ceil(d2 * 50.0D) + 14.0D;
/*  32 */       if (var14 > d3) {
/*  33 */         var14 = d3;
/*     */       }
/*  35 */       var14 += 64.0D;
/*     */     } 
/*     */     
/*  38 */     int var18 = var3 & 0xF;
/*  39 */     int var19 = var4 & 0xF;
/*     */     
/*  41 */     IBlockData var20 = K;
/*  42 */     WorldGenSurfaceConfiguration var21 = var2.e().e();
/*  43 */     IBlockData var22 = var21.b();
/*  44 */     IBlockData var23 = var21.a();
/*  45 */     IBlockData var24 = var22;
/*     */     
/*  47 */     int var25 = (int)(var6 / 3.0D + 3.0D + var0.nextDouble() * 0.25D);
/*  48 */     boolean var26 = (Math.cos(var6 / 3.0D * Math.PI) > 0.0D);
/*  49 */     int var27 = -1;
/*  50 */     boolean var28 = false;
/*     */     
/*  52 */     BlockPosition.MutableBlockPosition var29 = new BlockPosition.MutableBlockPosition();
/*     */     
/*  54 */     for (int var30 = Math.max(var5, (int)var14 + 1); var30 >= 0; var30--) {
/*  55 */       var29.d(var18, var30, var19);
/*  56 */       if (var1.getType(var29).isAir() && var30 < (int)var14) {
/*  57 */         var1.setType(var29, var8, false);
/*     */       }
/*     */       
/*  60 */       IBlockData var31 = var1.getType(var29);
/*     */       
/*  62 */       if (var31.isAir()) {
/*  63 */         var27 = -1;
/*  64 */       } else if (var31.a(var8.getBlock())) {
/*  65 */         if (var27 == -1) {
/*  66 */           var28 = false;
/*  67 */           if (var25 <= 0) {
/*  68 */             var20 = Blocks.AIR.getBlockData();
/*  69 */             var24 = var8;
/*  70 */           } else if (var30 >= var10 - 4 && var30 <= var10 + 1) {
/*  71 */             var20 = K;
/*  72 */             var24 = var22;
/*     */           } 
/*     */           
/*  75 */           if (var30 < var10 && (var20 == null || var20.isAir())) {
/*  76 */             var20 = var9;
/*     */           }
/*     */           
/*  79 */           var27 = var25 + Math.max(0, var30 - var10);
/*  80 */           if (var30 >= var10 - 1) {
/*  81 */             if (var30 > var10 + 3 + var25) {
/*     */               IBlockData var32;
/*  83 */               if (var30 < 64 || var30 > 127) {
/*  84 */                 var32 = L;
/*  85 */               } else if (var26) {
/*  86 */                 var32 = M;
/*     */               } else {
/*  88 */                 var32 = a(var3, var30, var4);
/*     */               } 
/*  90 */               var1.setType(var29, var32, false);
/*     */             } else {
/*  92 */               var1.setType(var29, var23, false);
/*  93 */               var28 = true;
/*     */             } 
/*     */           } else {
/*  96 */             var1.setType(var29, var24, false);
/*  97 */             Block var32 = var24.getBlock();
/*  98 */             if (var32 == Blocks.WHITE_TERRACOTTA || var32 == Blocks.ORANGE_TERRACOTTA || var32 == Blocks.MAGENTA_TERRACOTTA || var32 == Blocks.LIGHT_BLUE_TERRACOTTA || var32 == Blocks.YELLOW_TERRACOTTA || var32 == Blocks.LIME_TERRACOTTA || var32 == Blocks.PINK_TERRACOTTA || var32 == Blocks.GRAY_TERRACOTTA || var32 == Blocks.LIGHT_GRAY_TERRACOTTA || var32 == Blocks.CYAN_TERRACOTTA || var32 == Blocks.PURPLE_TERRACOTTA || var32 == Blocks.BLUE_TERRACOTTA || var32 == Blocks.BROWN_TERRACOTTA || var32 == Blocks.GREEN_TERRACOTTA || var32 == Blocks.RED_TERRACOTTA || var32 == Blocks.BLACK_TERRACOTTA)
/*     */             {
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
/* 115 */               var1.setType(var29, L, false);
/*     */             }
/*     */           } 
/* 118 */         } else if (var27 > 0) {
/* 119 */           var27--;
/*     */           
/* 121 */           if (var28) {
/* 122 */             var1.setType(var29, L, false);
/*     */           } else {
/* 124 */             var1.setType(var29, a(var3, var30, var4), false);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceMesaBryce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */