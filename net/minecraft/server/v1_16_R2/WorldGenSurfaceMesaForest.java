/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenSurfaceMesaForest
/*    */   extends WorldGenSurfaceMesa
/*    */ {
/* 13 */   private static final IBlockData K = Blocks.WHITE_TERRACOTTA.getBlockData();
/* 14 */   private static final IBlockData L = Blocks.ORANGE_TERRACOTTA.getBlockData();
/* 15 */   private static final IBlockData M = Blocks.TERRACOTTA.getBlockData();
/*    */   
/*    */   public WorldGenSurfaceMesaForest(Codec<WorldGenSurfaceConfigurationBase> var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, int var10, long var11, WorldGenSurfaceConfigurationBase var13) {
/* 23 */     int var14 = var3 & 0xF;
/* 24 */     int var15 = var4 & 0xF;
/*    */     
/* 26 */     IBlockData var16 = K;
/* 27 */     WorldGenSurfaceConfiguration var17 = var2.e().e();
/* 28 */     IBlockData var18 = var17.b();
/* 29 */     IBlockData var19 = var17.a();
/* 30 */     IBlockData var20 = var18;
/*    */     
/* 32 */     int var21 = (int)(var6 / 3.0D + 3.0D + var0.nextDouble() * 0.25D);
/* 33 */     boolean var22 = (Math.cos(var6 / 3.0D * Math.PI) > 0.0D);
/* 34 */     int var23 = -1;
/* 35 */     boolean var24 = false;
/* 36 */     int var25 = 0;
/*    */     
/* 38 */     BlockPosition.MutableBlockPosition var26 = new BlockPosition.MutableBlockPosition();
/*    */     
/* 40 */     for (int var27 = var5; var27 >= 0; var27--) {
/* 41 */       if (var25 < 15) {
/* 42 */         var26.d(var14, var27, var15);
/* 43 */         IBlockData var28 = var1.getType(var26);
/*    */         
/* 45 */         if (var28.isAir()) {
/* 46 */           var23 = -1;
/* 47 */         } else if (var28.a(var8.getBlock())) {
/* 48 */           if (var23 == -1) {
/* 49 */             var24 = false;
/* 50 */             if (var21 <= 0) {
/* 51 */               var16 = Blocks.AIR.getBlockData();
/* 52 */               var20 = var8;
/* 53 */             } else if (var27 >= var10 - 4 && var27 <= var10 + 1) {
/* 54 */               var16 = K;
/* 55 */               var20 = var18;
/*    */             } 
/*    */             
/* 58 */             if (var27 < var10 && (var16 == null || var16.isAir())) {
/* 59 */               var16 = var9;
/*    */             }
/*    */             
/* 62 */             var23 = var21 + Math.max(0, var27 - var10);
/* 63 */             if (var27 >= var10 - 1) {
/* 64 */               if (var27 > 86 + var21 * 2) {
/* 65 */                 if (var22) {
/* 66 */                   var1.setType(var26, Blocks.COARSE_DIRT.getBlockData(), false);
/*    */                 } else {
/* 68 */                   var1.setType(var26, Blocks.GRASS_BLOCK.getBlockData(), false);
/*    */                 } 
/* 70 */               } else if (var27 > var10 + 3 + var21) {
/*    */                 IBlockData var29;
/* 72 */                 if (var27 < 64 || var27 > 127) {
/* 73 */                   var29 = L;
/* 74 */                 } else if (var22) {
/* 75 */                   var29 = M;
/*    */                 } else {
/* 77 */                   var29 = a(var3, var27, var4);
/*    */                 } 
/* 79 */                 var1.setType(var26, var29, false);
/*    */               } else {
/* 81 */                 var1.setType(var26, var19, false);
/* 82 */                 var24 = true;
/*    */               } 
/*    */             } else {
/* 85 */               var1.setType(var26, var20, false);
/* 86 */               if (var20 == K) {
/* 87 */                 var1.setType(var26, L, false);
/*    */               }
/*    */             } 
/* 90 */           } else if (var23 > 0) {
/* 91 */             var23--;
/*    */             
/* 93 */             if (var24) {
/* 94 */               var1.setType(var26, L, false);
/*    */             } else {
/* 96 */               var1.setType(var26, a(var3, var27, var4), false);
/*    */             } 
/*    */           } 
/* 99 */           var25++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceMesaForest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */