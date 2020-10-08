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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureNoSurfaceOre
/*    */   extends WorldGenerator<WorldGenFeatureOreConfiguration>
/*    */ {
/*    */   WorldGenFeatureNoSurfaceOre(Codec<WorldGenFeatureOreConfiguration> var0) {
/* 21 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureOreConfiguration var4) {
/* 29 */     int var5 = var2.nextInt(var4.c + 1);
/* 30 */     BlockPosition.MutableBlockPosition var6 = new BlockPosition.MutableBlockPosition();
/*    */     
/* 32 */     for (int var7 = 0; var7 < var5; var7++) {
/*    */       
/* 34 */       a(var6, var2, var3, Math.min(var7, 7));
/*    */       
/* 36 */       if (var4.b.a(var0.getType(var6), var2) && !a(var0, var6))
/*    */       {
/* 38 */         var0.setTypeAndData(var6, var4.d, 2);
/*    */       }
/*    */     } 
/* 41 */     return true;
/*    */   }
/*    */   
/*    */   private void a(BlockPosition.MutableBlockPosition var0, Random var1, BlockPosition var2, int var3) {
/* 45 */     int var4 = a(var1, var3);
/* 46 */     int var5 = a(var1, var3);
/* 47 */     int var6 = a(var1, var3);
/* 48 */     var0.a(var2, var4, var5, var6);
/*    */   }
/*    */   
/*    */   private int a(Random var0, int var1) {
/* 52 */     return Math.round((var0.nextFloat() - var0.nextFloat()) * var1);
/*    */   }
/*    */   
/*    */   private boolean a(GeneratorAccess var0, BlockPosition var1) {
/* 56 */     BlockPosition.MutableBlockPosition var2 = new BlockPosition.MutableBlockPosition();
/* 57 */     for (EnumDirection var6 : EnumDirection.values()) {
/* 58 */       var2.a(var1, var6);
/* 59 */       if (var0.getType(var2).isAir()) {
/* 60 */         return true;
/*    */       }
/*    */     } 
/* 63 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureNoSurfaceOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */