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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenFeatureIceburg
/*     */   extends WorldGenerator<WorldGenFeatureLakeConfiguration>
/*     */ {
/*     */   public WorldGenFeatureIceburg(Codec<WorldGenFeatureLakeConfiguration> var0) {
/*  20 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureLakeConfiguration var4) {
/*  25 */     var3 = new BlockPosition(var3.getX(), var1.getSeaLevel(), var3.getZ());
/*  26 */     boolean var5 = (var2.nextDouble() > 0.7D);
/*  27 */     IBlockData var6 = var4.b;
/*     */ 
/*     */     
/*  30 */     double var7 = var2.nextDouble() * 2.0D * Math.PI;
/*  31 */     int var9 = 11 - var2.nextInt(5);
/*  32 */     int var10 = 3 + var2.nextInt(3);
/*  33 */     boolean var11 = (var2.nextDouble() > 0.7D);
/*     */     
/*  35 */     int var12 = 11;
/*  36 */     int var13 = var11 ? (var2.nextInt(6) + 6) : (var2.nextInt(15) + 3);
/*  37 */     if (!var11 && var2.nextDouble() > 0.9D) {
/*  38 */       var13 += var2.nextInt(19) + 7;
/*     */     }
/*     */     
/*  41 */     int var14 = Math.min(var13 + var2.nextInt(11), 18);
/*  42 */     int var15 = Math.min(var13 + var2.nextInt(7) - var2.nextInt(5), 11);
/*  43 */     int var16 = var11 ? var9 : 11;
/*     */     
/*     */     int i;
/*  46 */     for (i = -var16; i < var16; i++) {
/*  47 */       for (int var18 = -var16; var18 < var16; var18++) {
/*  48 */         for (int var19 = 0; var19 < var13; var19++) {
/*  49 */           int var20 = var11 ? b(var19, var13, var15) : a(var2, var19, var13, var15);
/*  50 */           if (var11 || i < var20)
/*     */           {
/*     */             
/*  53 */             a(var0, var2, var3, var13, i, var19, var18, var20, var16, var11, var10, var7, var5, var6);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  59 */     a(var0, var3, var15, var13, var11, var9);
/*     */ 
/*     */     
/*  62 */     for (i = -var16; i < var16; i++) {
/*  63 */       for (int var18 = -var16; var18 < var16; var18++) {
/*  64 */         for (int var19 = -1; var19 > -var14; var19--) {
/*  65 */           int var20 = var11 ? MathHelper.f(var16 * (1.0F - (float)Math.pow(var19, 2.0D) / var14 * 8.0F)) : var16;
/*  66 */           int var21 = b(var2, -var19, var14, var15);
/*  67 */           if (i < var21)
/*     */           {
/*     */             
/*  70 */             a(var0, var2, var3, var14, i, var19, var18, var21, var20, var11, var10, var7, var5, var6);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     boolean var17 = var11 ? ((var2.nextDouble() > 0.1D)) : ((var2.nextDouble() > 0.7D));
/*  77 */     if (var17) {
/*  78 */       a(var2, var0, var15, var13, var3, var11, var9, var7, var10);
/*     */     }
/*     */     
/*  81 */     return true;
/*     */   }
/*     */   
/*     */   private void a(Random var0, GeneratorAccess var1, int var2, int var3, BlockPosition var4, boolean var5, int var6, double var7, int var9) {
/*  85 */     int var10 = var0.nextBoolean() ? -1 : 1;
/*  86 */     int var11 = var0.nextBoolean() ? -1 : 1;
/*     */     
/*  88 */     int var12 = var0.nextInt(Math.max(var2 / 2 - 2, 1));
/*  89 */     if (var0.nextBoolean()) {
/*  90 */       var12 = var2 / 2 + 1 - var0.nextInt(Math.max(var2 - var2 / 2 - 1, 1));
/*     */     }
/*  92 */     int var13 = var0.nextInt(Math.max(var2 / 2 - 2, 1));
/*  93 */     if (var0.nextBoolean()) {
/*  94 */       var13 = var2 / 2 + 1 - var0.nextInt(Math.max(var2 - var2 / 2 - 1, 1));
/*     */     }
/*     */     
/*  97 */     if (var5) {
/*  98 */       var12 = var13 = var0.nextInt(Math.max(var6 - 5, 1));
/*     */     }
/*     */     
/* 101 */     BlockPosition var14 = new BlockPosition(var10 * var12, 0, var11 * var13);
/* 102 */     double var15 = var5 ? (var7 + 1.5707963267948966D) : (var0.nextDouble() * 2.0D * Math.PI);
/*     */     int var17;
/* 104 */     for (var17 = 0; var17 < var3 - 3; var17++) {
/* 105 */       int var18 = a(var0, var17, var3, var2);
/* 106 */       a(var18, var17, var4, var1, false, var15, var14, var6, var9);
/*     */     } 
/*     */     
/* 109 */     for (var17 = -1; var17 > -var3 + var0.nextInt(5); var17--) {
/* 110 */       int var18 = b(var0, -var17, var3, var2);
/* 111 */       a(var18, var17, var4, var1, true, var15, var14, var6, var9);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(int var0, int var1, BlockPosition var2, GeneratorAccess var3, boolean var4, double var5, BlockPosition var7, int var8, int var9) {
/* 116 */     int var10 = var0 + 1 + var8 / 3;
/* 117 */     int var11 = Math.min(var0 - 3, 3) + var9 / 2 - 1;
/*     */     
/* 119 */     for (int var12 = -var10; var12 < var10; var12++) {
/* 120 */       for (int var13 = -var10; var13 < var10; var13++) {
/* 121 */         double var14 = a(var12, var13, var7, var10, var11, var5);
/* 122 */         if (var14 < 0.0D) {
/* 123 */           BlockPosition var16 = var2.b(var12, var1, var13);
/* 124 */           Block var17 = var3.getType(var16).getBlock();
/* 125 */           if (c(var17) || var17 == Blocks.SNOW_BLOCK) {
/* 126 */             if (var4) {
/* 127 */               a(var3, var16, Blocks.WATER.getBlockData());
/*     */             } else {
/* 129 */               a(var3, var16, Blocks.AIR.getBlockData());
/* 130 */               a(var3, var16);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(GeneratorAccess var0, BlockPosition var1) {
/* 139 */     if (var0.getType(var1.up()).a(Blocks.SNOW)) {
/* 140 */       a(var0, var1.up(), Blocks.AIR.getBlockData());
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(GeneratorAccess var0, Random var1, BlockPosition var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, int var10, double var11, boolean var13, IBlockData var14) {
/* 145 */     double var15 = var9 ? a(var4, var6, BlockPosition.ZERO, var8, a(var5, var3, var10), var11) : a(var4, var6, BlockPosition.ZERO, var7, var1);
/* 146 */     if (var15 < 0.0D) {
/* 147 */       BlockPosition var17 = var2.b(var4, var5, var6);
/* 148 */       double var18 = var9 ? -0.5D : (-6 - var1.nextInt(3));
/* 149 */       if (var15 > var18 && var1.nextDouble() > 0.9D) {
/*     */         return;
/*     */       }
/* 152 */       a(var17, var0, var1, var3 - var5, var3, var9, var13, var14);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(BlockPosition var0, GeneratorAccess var1, Random var2, int var3, int var4, boolean var5, boolean var6, IBlockData var7) {
/* 157 */     IBlockData var8 = var1.getType(var0);
/* 158 */     if (var8.getMaterial() == Material.AIR || var8.a(Blocks.SNOW_BLOCK) || var8.a(Blocks.ICE) || var8.a(Blocks.WATER)) {
/* 159 */       boolean var9 = (!var5 || var2.nextDouble() > 0.05D);
/* 160 */       int var10 = var5 ? 3 : 2;
/* 161 */       if (var6 && !var8.a(Blocks.WATER) && var3 <= var2.nextInt(Math.max(1, var4 / var10)) + var4 * 0.6D && var9) {
/* 162 */         a(var1, var0, Blocks.SNOW_BLOCK.getBlockData());
/*     */       } else {
/* 164 */         a(var1, var0, var7);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int a(int var0, int var1, int var2) {
/* 170 */     int var3 = var2;
/* 171 */     if (var0 > 0 && var1 - var0 <= 3) {
/* 172 */       var3 -= 4 - var1 - var0;
/*     */     }
/*     */     
/* 175 */     return var3;
/*     */   }
/*     */   
/*     */   private double a(int var0, int var1, BlockPosition var2, int var3, Random var4) {
/* 179 */     float var5 = 10.0F * MathHelper.a(var4.nextFloat(), 0.2F, 0.8F) / var3;
/* 180 */     return var5 + Math.pow((var0 - var2.getX()), 2.0D) + Math.pow((var1 - var2.getZ()), 2.0D) - Math.pow(var3, 2.0D);
/*     */   }
/*     */   
/*     */   private double a(int var0, int var1, BlockPosition var2, int var3, int var4, double var5) {
/* 184 */     return Math.pow(((var0 - var2.getX()) * Math.cos(var5) - (var1 - var2.getZ()) * Math.sin(var5)) / var3, 2.0D) + Math.pow(((var0 - var2.getX()) * Math.sin(var5) + (var1 - var2.getZ()) * Math.cos(var5)) / var4, 2.0D) - 1.0D;
/*     */   }
/*     */   
/*     */   private int a(Random var0, int var1, int var2, int var3) {
/* 188 */     float var4 = 3.5F - var0.nextFloat();
/* 189 */     float var5 = (1.0F - (float)Math.pow(var1, 2.0D) / var2 * var4) * var3;
/*     */     
/* 191 */     if (var2 > 15 + var0.nextInt(5)) {
/* 192 */       int var6 = (var1 < 3 + var0.nextInt(6)) ? (var1 / 2) : var1;
/* 193 */       var5 = (1.0F - var6 / var2 * var4 * 0.4F) * var3;
/*     */     } 
/*     */     
/* 196 */     return MathHelper.f(var5 / 2.0F);
/*     */   }
/*     */   
/*     */   private int b(int var0, int var1, int var2) {
/* 200 */     float var3 = 1.0F;
/* 201 */     float var4 = (1.0F - (float)Math.pow(var0, 2.0D) / var1 * 1.0F) * var2;
/* 202 */     return MathHelper.f(var4 / 2.0F);
/*     */   }
/*     */   
/*     */   private int b(Random var0, int var1, int var2, int var3) {
/* 206 */     float var4 = 1.0F + var0.nextFloat() / 2.0F;
/* 207 */     float var5 = (1.0F - var1 / var2 * var4) * var3;
/* 208 */     return MathHelper.f(var5 / 2.0F);
/*     */   }
/*     */   
/*     */   private boolean c(Block var0) {
/* 212 */     return (var0 == Blocks.PACKED_ICE || var0 == Blocks.SNOW_BLOCK || var0 == Blocks.BLUE_ICE);
/*     */   }
/*     */   
/*     */   private boolean a(IBlockAccess var0, BlockPosition var1) {
/* 216 */     return (var0.getType(var1.down()).getMaterial() == Material.AIR);
/*     */   }
/*     */   
/*     */   private void a(GeneratorAccess var0, BlockPosition var1, int var2, int var3, boolean var4, int var5) {
/* 220 */     int var6 = var4 ? var5 : (var2 / 2);
/*     */     
/* 222 */     for (int var7 = -var6; var7 <= var6; var7++) {
/* 223 */       for (int var8 = -var6; var8 <= var6; var8++) {
/* 224 */         for (int var9 = 0; var9 <= var3; var9++) {
/* 225 */           BlockPosition var10 = var1.b(var7, var9, var8);
/* 226 */           Block var11 = var0.getType(var10).getBlock();
/*     */ 
/*     */           
/* 229 */           if (c(var11) || var11 == Blocks.SNOW)
/* 230 */             if (a(var0, var10)) {
/* 231 */               a(var0, var10, Blocks.AIR.getBlockData());
/* 232 */               a(var0, var10.up(), Blocks.AIR.getBlockData());
/*     */ 
/*     */             
/*     */             }
/* 236 */             else if (c(var11)) {
/*     */               
/* 238 */               Block[] var12 = { var0.getType(var10.west()).getBlock(), var0.getType(var10.east()).getBlock(), var0.getType(var10.north()).getBlock(), var0.getType(var10.south()).getBlock() };
/* 239 */               int var13 = 0;
/* 240 */               for (Block var17 : var12) {
/* 241 */                 if (!c(var17)) {
/* 242 */                   var13++;
/*     */                 }
/*     */               } 
/* 245 */               if (var13 >= 3)
/* 246 */                 a(var0, var10, Blocks.AIR.getBlockData()); 
/*     */             }  
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureIceburg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */