/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenSurfaceSwamp
/*    */   extends WorldGenSurface<WorldGenSurfaceConfigurationBase>
/*    */ {
/*    */   public WorldGenSurfaceSwamp(Codec<WorldGenSurfaceConfigurationBase> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, int var10, long var11, WorldGenSurfaceConfigurationBase var13) {
/* 18 */     double var14 = BiomeBase.f.a(var3 * 0.25D, var4 * 0.25D, false);
/* 19 */     if (var14 > 0.0D) {
/* 20 */       int var16 = var3 & 0xF;
/* 21 */       int var17 = var4 & 0xF;
/*    */       
/* 23 */       BlockPosition.MutableBlockPosition var18 = new BlockPosition.MutableBlockPosition();
/*    */       
/* 25 */       for (int var19 = var5; var19 >= 0; var19--) {
/* 26 */         var18.d(var16, var19, var17);
/* 27 */         if (!var1.getType(var18).isAir()) {
/* 28 */           if (var19 == 62 && !var1.getType(var18).a(var9.getBlock())) {
/* 29 */             var1.setType(var18, var9, false);
/*    */           }
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 36 */     WorldGenSurface.v.a(var0, var1, var2, var3, var4, var5, var6, var8, var9, var10, var11, var13);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceSwamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */