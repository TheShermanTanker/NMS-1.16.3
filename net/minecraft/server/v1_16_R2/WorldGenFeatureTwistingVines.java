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
/*     */ 
/*     */ public class WorldGenFeatureTwistingVines
/*     */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*     */ {
/*     */   public WorldGenFeatureTwistingVines(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/*  21 */     super(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/*  27 */     return a(var0, var2, var3, 8, 4, 8);
/*     */   }
/*     */   
/*     */   public static boolean a(GeneratorAccess var0, Random var1, BlockPosition var2, int var3, int var4, int var5) {
/*  31 */     if (a(var0, var2)) {
/*  32 */       return false;
/*     */     }
/*     */     
/*  35 */     b(var0, var1, var2, var3, var4, var5);
/*  36 */     return true;
/*     */   }
/*     */   
/*     */   private static void b(GeneratorAccess var0, Random var1, BlockPosition var2, int var3, int var4, int var5) {
/*  40 */     BlockPosition.MutableBlockPosition var6 = new BlockPosition.MutableBlockPosition();
/*     */     
/*  42 */     for (int var7 = 0; var7 < var3 * var3; var7++) {
/*  43 */       var6.g(var2).e(
/*  44 */           MathHelper.nextInt(var1, -var3, var3), 
/*  45 */           MathHelper.nextInt(var1, -var4, var4), 
/*  46 */           MathHelper.nextInt(var1, -var3, var3));
/*     */ 
/*     */       
/*  49 */       if (a(var0, var6))
/*     */       {
/*     */ 
/*     */         
/*  53 */         if (!a(var0, var6)) {
/*     */ 
/*     */ 
/*     */           
/*  57 */           int var8 = MathHelper.nextInt(var1, 1, var5);
/*  58 */           if (var1.nextInt(6) == 0) {
/*  59 */             var8 *= 2;
/*     */           }
/*  61 */           if (var1.nextInt(5) == 0) {
/*  62 */             var8 = 1;
/*     */           }
/*     */           
/*  65 */           int var9 = 17;
/*  66 */           int var10 = 25;
/*  67 */           a(var0, var1, var6, var8, 17, 25);
/*     */         }  } 
/*     */     } 
/*     */   }
/*     */   private static boolean a(GeneratorAccess var0, BlockPosition.MutableBlockPosition var1) {
/*     */     while (true) {
/*  73 */       var1.e(0, -1, 0);
/*  74 */       if (World.isOutsideWorld(var1)) {
/*  75 */         return false;
/*     */       }
/*  77 */       if (!var0.getType(var1).isAir()) {
/*  78 */         var1.e(0, 1, 0);
/*  79 */         return true;
/*     */       } 
/*     */     } 
/*     */   } public static void a(GeneratorAccess var0, Random var1, BlockPosition.MutableBlockPosition var2, int var3, int var4, int var5) {
/*  83 */     for (int var6 = 1; var6 <= var3; var6++) {
/*  84 */       if (var0.isEmpty(var2)) {
/*  85 */         if (var6 == var3 || !var0.isEmpty(var2.up())) {
/*  86 */           var0.setTypeAndData(var2, Blocks.TWISTING_VINES.getBlockData().set(BlockGrowingTop.d, Integer.valueOf(MathHelper.nextInt(var1, var4, var5))), 2);
/*     */           break;
/*     */         } 
/*  89 */         var0.setTypeAndData(var2, Blocks.TWISTING_VINES_PLANT.getBlockData(), 2);
/*     */       } 
/*     */ 
/*     */       
/*  93 */       var2.c(EnumDirection.UP);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean a(GeneratorAccess var0, BlockPosition var1) {
/*  98 */     if (!var0.isEmpty(var1)) {
/*  99 */       return true;
/*     */     }
/*     */     
/* 102 */     IBlockData var2 = var0.getType(var1.down());
/* 103 */     return (!var2.a(Blocks.NETHERRACK) && !var2.a(Blocks.WARPED_NYLIUM) && !var2.a(Blocks.WARPED_WART_BLOCK));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTwistingVines.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */