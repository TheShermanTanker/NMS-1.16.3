/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenSurfaceExtremeHillMutated
/*    */   extends WorldGenSurface<WorldGenSurfaceConfigurationBase>
/*    */ {
/*    */   public WorldGenSurfaceExtremeHillMutated(Codec<WorldGenSurfaceConfigurationBase> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, int var10, long var11, WorldGenSurfaceConfigurationBase var13) {
/* 17 */     if (var6 < -1.0D || var6 > 2.0D) {
/* 18 */       WorldGenSurface.v.a(var0, var1, var2, var3, var4, var5, var6, var8, var9, var10, var11, WorldGenSurface.g);
/* 19 */     } else if (var6 > 1.0D) {
/* 20 */       WorldGenSurface.v.a(var0, var1, var2, var3, var4, var5, var6, var8, var9, var10, var11, WorldGenSurface.i);
/*    */     } else {
/* 22 */       WorldGenSurface.v.a(var0, var1, var2, var3, var4, var5, var6, var8, var9, var10, var11, WorldGenSurface.h);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceExtremeHillMutated.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */