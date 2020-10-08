/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureNetherrackReplaceBlobs
/*    */   extends WorldGenerator<WorldGenFeatureRadiusConfiguration>
/*    */ {
/*    */   public WorldGenFeatureNetherrackReplaceBlobs(Codec<WorldGenFeatureRadiusConfiguration> var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureRadiusConfiguration var4) {
/* 23 */     Block var5 = var4.b.getBlock();
/* 24 */     BlockPosition var6 = a(var0, var3.i().a(EnumDirection.EnumAxis.Y, 1, var0.getBuildHeight() - 1), var5);
/* 25 */     if (var6 == null) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     int var7 = var4.b().a(var2);
/*    */     
/* 31 */     boolean var8 = false;
/* 32 */     for (BlockPosition var10 : BlockPosition.a(var6, var7, var7, var7)) {
/* 33 */       if (var10.k(var6) > var7) {
/*    */         break;
/*    */       }
/*    */ 
/*    */       
/* 38 */       IBlockData var11 = var0.getType(var10);
/* 39 */       if (var11.a(var5)) {
/* 40 */         a(var0, var10, var4.c);
/* 41 */         var8 = true;
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     return var8;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private static BlockPosition a(GeneratorAccess var0, BlockPosition.MutableBlockPosition var1, Block var2) {
/* 50 */     while (var1.getY() > 1) {
/* 51 */       IBlockData var3 = var0.getType(var1);
/* 52 */       if (var3.a(var2)) {
/* 53 */         return var1;
/*    */       }
/*    */       
/* 56 */       var1.c(EnumDirection.DOWN);
/*    */     } 
/* 58 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureNetherrackReplaceBlobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */