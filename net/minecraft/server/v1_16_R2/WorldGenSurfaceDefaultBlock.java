/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenSurfaceDefaultBlock
/*    */   extends WorldGenSurface<WorldGenSurfaceConfigurationBase>
/*    */ {
/*    */   public WorldGenSurfaceDefaultBlock(Codec<WorldGenSurfaceConfigurationBase> var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, int var10, long var11, WorldGenSurfaceConfigurationBase var13) {
/* 20 */     a(var0, var1, var2, var3, var4, var5, var6, var8, var9, var13.a(), var13.b(), var13.c(), var10);
/*    */   }
/*    */   
/*    */   protected void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, IBlockData var10, IBlockData var11, IBlockData var12, int var13) {
/* 24 */     IBlockData var14 = var10;
/* 25 */     IBlockData var15 = var11;
/* 26 */     BlockPosition.MutableBlockPosition var16 = new BlockPosition.MutableBlockPosition();
/*    */     
/* 28 */     int var17 = -1;
/* 29 */     int var18 = (int)(var6 / 3.0D + 3.0D + var0.nextDouble() * 0.25D);
/*    */     
/* 31 */     int var19 = var3 & 0xF;
/* 32 */     int var20 = var4 & 0xF;
/*    */     
/* 34 */     for (int var21 = var5; var21 >= 0; var21--) {
/* 35 */       var16.d(var19, var21, var20);
/* 36 */       IBlockData var22 = var1.getType(var16);
/*    */       
/* 38 */       if (var22.isAir()) {
/* 39 */         var17 = -1;
/*    */ 
/*    */       
/*    */       }
/* 43 */       else if (var22.a(var8.getBlock())) {
/*    */ 
/*    */ 
/*    */         
/* 47 */         if (var17 == -1) {
/* 48 */           if (var18 <= 0) {
/* 49 */             var14 = Blocks.AIR.getBlockData();
/* 50 */             var15 = var8;
/* 51 */           } else if (var21 >= var13 - 4 && var21 <= var13 + 1) {
/* 52 */             var14 = var10;
/* 53 */             var15 = var11;
/*    */           } 
/*    */           
/* 56 */           if (var21 < var13 && (var14 == null || var14.isAir())) {
/* 57 */             if (var2.getAdjustedTemperature(var16.d(var3, var21, var4)) < 0.15F) {
/* 58 */               var14 = Blocks.ICE.getBlockData();
/*    */             } else {
/* 60 */               var14 = var9;
/*    */             } 
/* 62 */             var16.d(var19, var21, var20);
/*    */           } 
/*    */           
/* 65 */           var17 = var18;
/* 66 */           if (var21 >= var13 - 1) {
/* 67 */             var1.setType(var16, var14, false);
/* 68 */           } else if (var21 < var13 - 7 - var18) {
/* 69 */             var14 = Blocks.AIR.getBlockData();
/* 70 */             var15 = var8;
/* 71 */             var1.setType(var16, var12, false);
/*    */           } else {
/* 73 */             var1.setType(var16, var15, false);
/*    */           } 
/* 75 */         } else if (var17 > 0) {
/* 76 */           var17--;
/* 77 */           var1.setType(var16, var15, false);
/*    */ 
/*    */           
/* 80 */           if (var17 == 0 && var15.a(Blocks.SAND) && var18 > 1) {
/* 81 */             var17 = var0.nextInt(4) + Math.max(0, var21 - 63);
/* 82 */             var15 = var15.a(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.getBlockData() : Blocks.SANDSTONE.getBlockData();
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceDefaultBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */