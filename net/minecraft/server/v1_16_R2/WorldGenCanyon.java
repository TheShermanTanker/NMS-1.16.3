/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.BitSet;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenCanyon
/*    */   extends WorldGenCarverAbstract<WorldGenFeatureConfigurationChance>
/*    */ {
/* 16 */   private final float[] m = new float[1024];
/*    */   
/*    */   public WorldGenCanyon(Codec<WorldGenFeatureConfigurationChance> var0) {
/* 19 */     super(var0, 256);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Random var0, int var1, int var2, WorldGenFeatureConfigurationChance var3) {
/* 24 */     return (var0.nextFloat() <= var3.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, Random var2, int var3, int var4, int var5, int var6, int var7, BitSet var8, WorldGenFeatureConfigurationChance var9) {
/* 29 */     int var10 = (d() * 2 - 1) * 16;
/*    */     
/* 31 */     double var11 = (var4 * 16 + var2.nextInt(16));
/* 32 */     double var13 = (var2.nextInt(var2.nextInt(40) + 8) + 20);
/* 33 */     double var15 = (var5 * 16 + var2.nextInt(16));
/*    */     
/* 35 */     float var17 = var2.nextFloat() * 6.2831855F;
/* 36 */     float var18 = (var2.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 37 */     double var19 = 3.0D;
/* 38 */     float var21 = (var2.nextFloat() * 2.0F + var2.nextFloat()) * 2.0F;
/* 39 */     int var22 = var10 - var2.nextInt(var10 / 4);
/* 40 */     int var23 = 0;
/* 41 */     a(var0, var1, var2.nextLong(), var3, var6, var7, var11, var13, var15, var21, var17, var18, 0, var22, 3.0D, var8);
/*    */     
/* 43 */     return true;
/*    */   }
/*    */   
/*    */   private void a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, long var2, int var4, int var5, int var6, double var7, double var9, double var11, float var13, float var14, float var15, int var16, int var17, double var18, BitSet var20) {
/* 47 */     Random var21 = new Random(var2);
/*    */     
/* 49 */     float var22 = 1.0F;
/* 50 */     for (int i = 0; i < 256; i++) {
/* 51 */       if (i == 0 || var21.nextInt(3) == 0) {
/* 52 */         var22 = 1.0F + var21.nextFloat() * var21.nextFloat();
/*    */       }
/* 54 */       this.m[i] = var22 * var22;
/*    */     } 
/*    */     
/* 57 */     float var23 = 0.0F;
/* 58 */     float var24 = 0.0F;
/*    */     
/* 60 */     for (int var25 = var16; var25 < var17; var25++) {
/* 61 */       double var26 = 1.5D + (MathHelper.sin(var25 * 3.1415927F / var17) * var13);
/* 62 */       double var28 = var26 * var18;
/*    */       
/* 64 */       var26 *= var21.nextFloat() * 0.25D + 0.75D;
/* 65 */       var28 *= var21.nextFloat() * 0.25D + 0.75D;
/*    */       
/* 67 */       float var30 = MathHelper.cos(var15);
/* 68 */       float var31 = MathHelper.sin(var15);
/* 69 */       var7 += (MathHelper.cos(var14) * var30);
/* 70 */       var9 += var31;
/* 71 */       var11 += (MathHelper.sin(var14) * var30);
/*    */       
/* 73 */       var15 *= 0.7F;
/*    */       
/* 75 */       var15 += var24 * 0.05F;
/* 76 */       var14 += var23 * 0.05F;
/*    */       
/* 78 */       var24 *= 0.8F;
/* 79 */       var23 *= 0.5F;
/* 80 */       var24 += (var21.nextFloat() - var21.nextFloat()) * var21.nextFloat() * 2.0F;
/* 81 */       var23 += (var21.nextFloat() - var21.nextFloat()) * var21.nextFloat() * 4.0F;
/*    */       
/* 83 */       if (var21.nextInt(4) != 0) {
/*    */ 
/*    */ 
/*    */         
/* 87 */         if (!a(var5, var6, var7, var11, var25, var17, var13)) {
/*    */           return;
/*    */         }
/*    */         
/* 91 */         a(var0, var1, var2, var4, var5, var6, var7, var9, var11, var26, var28, var20);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected boolean a(double var0, double var2, double var4, int var6) {
/* 97 */     return ((var0 * var0 + var4 * var4) * this.m[var6 - 1] + var2 * var2 / 6.0D >= 1.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCanyon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */