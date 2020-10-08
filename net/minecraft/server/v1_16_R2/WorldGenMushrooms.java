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
/*    */ public abstract class WorldGenMushrooms
/*    */   extends WorldGenerator<WorldGenFeatureMushroomConfiguration>
/*    */ {
/*    */   public WorldGenMushrooms(Codec<WorldGenFeatureMushroomConfiguration> var0) {
/* 19 */     super(var0);
/*    */   }
/*    */   
/*    */   protected void a(GeneratorAccess var0, Random var1, BlockPosition var2, WorldGenFeatureMushroomConfiguration var3, int var4, BlockPosition.MutableBlockPosition var5) {
/* 23 */     for (int var6 = 0; var6 < var4; var6++) {
/* 24 */       var5.g(var2).c(EnumDirection.UP, var6);
/* 25 */       if (!var0.getType(var5).i(var0, var5)) {
/* 26 */         a(var0, var5, var3.c.a(var1, var2));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   protected int a(Random var0) {
/* 32 */     int var1 = var0.nextInt(3) + 4;
/* 33 */     if (var0.nextInt(12) == 0) {
/* 34 */       var1 *= 2;
/*    */     }
/* 36 */     return var1;
/*    */   }
/*    */   
/*    */   protected boolean a(GeneratorAccess var0, BlockPosition var1, int var2, BlockPosition.MutableBlockPosition var3, WorldGenFeatureMushroomConfiguration var4) {
/* 40 */     int var5 = var1.getY();
/* 41 */     if (var5 < 1 || var5 + var2 + 1 >= 256) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     Block var6 = var0.getType(var1.down()).getBlock();
/* 46 */     if (!b(var6) && !var6.a(TagsBlock.aD)) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     for (int var7 = 0; var7 <= var2; var7++) {
/* 51 */       int var8 = a(-1, -1, var4.d, var7);
/* 52 */       for (int var9 = -var8; var9 <= var8; var9++) {
/* 53 */         for (int var10 = -var8; var10 <= var8; var10++) {
/* 54 */           IBlockData var11 = var0.getType(var3.a(var1, var9, var7, var10));
/* 55 */           if (!var11.isAir() && !var11.a(TagsBlock.LEAVES)) {
/* 56 */             return false;
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureMushroomConfiguration var4) {
/* 66 */     int var5 = a(var2);
/*    */     
/* 68 */     BlockPosition.MutableBlockPosition var6 = new BlockPosition.MutableBlockPosition();
/* 69 */     if (!a(var0, var3, var5, var6, var4)) {
/* 70 */       return false;
/*    */     }
/*    */     
/* 73 */     a(var0, var2, var3, var5, var6, var4);
/* 74 */     a(var0, var2, var3, var4, var5, var6);
/* 75 */     return true;
/*    */   }
/*    */   
/*    */   protected abstract int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */   
/*    */   protected abstract void a(GeneratorAccess paramGeneratorAccess, Random paramRandom, BlockPosition paramBlockPosition, int paramInt, BlockPosition.MutableBlockPosition paramMutableBlockPosition, WorldGenFeatureMushroomConfiguration paramWorldGenFeatureMushroomConfiguration);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMushrooms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */