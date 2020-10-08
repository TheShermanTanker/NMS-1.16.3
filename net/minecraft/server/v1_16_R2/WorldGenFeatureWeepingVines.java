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
/*     */ public class WorldGenFeatureWeepingVines
/*     */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*     */ {
/*  19 */   private static final EnumDirection[] a = EnumDirection.values();
/*     */   
/*     */   public WorldGenFeatureWeepingVines(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/*  22 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/*  27 */     if (!var0.isEmpty(var3)) {
/*  28 */       return false;
/*     */     }
/*     */     
/*  31 */     IBlockData var5 = var0.getType(var3.up());
/*  32 */     if (!var5.a(Blocks.NETHERRACK) && !var5.a(Blocks.NETHER_WART_BLOCK)) {
/*  33 */       return false;
/*     */     }
/*     */     
/*  36 */     a(var0, var2, var3);
/*  37 */     b(var0, var2, var3);
/*     */     
/*  39 */     return true;
/*     */   }
/*     */   
/*     */   private void a(GeneratorAccess var0, Random var1, BlockPosition var2) {
/*  43 */     var0.setTypeAndData(var2, Blocks.NETHER_WART_BLOCK.getBlockData(), 2);
/*     */     
/*  45 */     BlockPosition.MutableBlockPosition var3 = new BlockPosition.MutableBlockPosition();
/*  46 */     BlockPosition.MutableBlockPosition var4 = new BlockPosition.MutableBlockPosition();
/*     */     
/*  48 */     for (int var5 = 0; var5 < 200; var5++) {
/*  49 */       var3.a(var2, var1.nextInt(6) - var1.nextInt(6), var1.nextInt(2) - var1.nextInt(5), var1.nextInt(6) - var1.nextInt(6));
/*  50 */       if (var0.isEmpty(var3)) {
/*     */ 
/*     */ 
/*     */         
/*  54 */         int var6 = 0;
/*  55 */         for (EnumDirection var10 : a) {
/*  56 */           IBlockData var11 = var0.getType(var4.a(var3, var10));
/*  57 */           if (var11.a(Blocks.NETHERRACK) || var11.a(Blocks.NETHER_WART_BLOCK)) {
/*  58 */             var6++;
/*     */           }
/*     */           
/*  61 */           if (var6 > 1) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */         
/*  66 */         if (var6 == 1)
/*  67 */           var0.setTypeAndData(var3, Blocks.NETHER_WART_BLOCK.getBlockData(), 2); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void b(GeneratorAccess var0, Random var1, BlockPosition var2) {
/*  73 */     BlockPosition.MutableBlockPosition var3 = new BlockPosition.MutableBlockPosition();
/*     */     
/*  75 */     for (int var4 = 0; var4 < 100; var4++) {
/*  76 */       var3.a(var2, var1.nextInt(8) - var1.nextInt(8), var1.nextInt(2) - var1.nextInt(7), var1.nextInt(8) - var1.nextInt(8));
/*  77 */       if (var0.isEmpty(var3)) {
/*     */ 
/*     */ 
/*     */         
/*  81 */         IBlockData var5 = var0.getType(var3.up());
/*  82 */         if (var5.a(Blocks.NETHERRACK) || var5.a(Blocks.NETHER_WART_BLOCK)) {
/*     */ 
/*     */ 
/*     */           
/*  86 */           int var6 = MathHelper.nextInt(var1, 1, 8);
/*  87 */           if (var1.nextInt(6) == 0) {
/*  88 */             var6 *= 2;
/*     */           }
/*  90 */           if (var1.nextInt(5) == 0) {
/*  91 */             var6 = 1;
/*     */           }
/*     */           
/*  94 */           int var7 = 17;
/*  95 */           int var8 = 25;
/*  96 */           a(var0, var1, var3, var6, 17, 25);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } public static void a(GeneratorAccess var0, Random var1, BlockPosition.MutableBlockPosition var2, int var3, int var4, int var5) {
/* 101 */     for (int var6 = 0; var6 <= var3; var6++) {
/* 102 */       if (var0.isEmpty(var2)) {
/* 103 */         if (var6 == var3 || !var0.isEmpty(var2.down())) {
/* 104 */           var0.setTypeAndData(var2, Blocks.WEEPING_VINES.getBlockData().set(BlockGrowingTop.d, Integer.valueOf(MathHelper.nextInt(var1, var4, var5))), 2);
/*     */           break;
/*     */         } 
/* 107 */         var0.setTypeAndData(var2, Blocks.WEEPING_VINES_PLANT.getBlockData(), 2);
/*     */       } 
/*     */ 
/*     */       
/* 111 */       var2.c(EnumDirection.DOWN);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureWeepingVines.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */