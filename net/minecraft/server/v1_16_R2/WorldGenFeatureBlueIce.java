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
/*    */ public class WorldGenFeatureBlueIce
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureBlueIce(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 23 */     if (var3.getY() > var0.getSeaLevel() - 1) {
/* 24 */       return false;
/*    */     }
/* 26 */     if (!var0.getType(var3).a(Blocks.WATER) && !var0.getType(var3.down()).a(Blocks.WATER)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     boolean var5 = false;
/* 31 */     for (EnumDirection var9 : EnumDirection.values()) {
/* 32 */       if (var9 != EnumDirection.DOWN)
/*    */       {
/*    */         
/* 35 */         if (var0.getType(var3.shift(var9)).a(Blocks.PACKED_ICE)) {
/* 36 */           var5 = true;
/*    */           break;
/*    */         }  } 
/*    */     } 
/* 40 */     if (!var5) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     var0.setTypeAndData(var3, Blocks.BLUE_ICE.getBlockData(), 2);
/*    */     
/* 46 */     for (int var6 = 0; var6 < 200; var6++) {
/* 47 */       int var7 = var2.nextInt(5) - var2.nextInt(6);
/* 48 */       int var8 = 3;
/* 49 */       if (var7 < 2) {
/* 50 */         var8 += var7 / 2;
/*    */       }
/* 52 */       if (var8 >= 1) {
/*    */ 
/*    */ 
/*    */         
/* 56 */         BlockPosition var9 = var3.b(var2.nextInt(var8) - var2.nextInt(var8), var7, var2.nextInt(var8) - var2.nextInt(var8));
/* 57 */         IBlockData var10 = var0.getType(var9);
/* 58 */         if (var10.getMaterial() == Material.AIR || var10.a(Blocks.WATER) || var10.a(Blocks.PACKED_ICE) || var10.a(Blocks.ICE))
/*    */         {
/*    */ 
/*    */           
/* 62 */           for (EnumDirection var14 : EnumDirection.values()) {
/* 63 */             IBlockData var15 = var0.getType(var9.shift(var14));
/* 64 */             if (var15.a(Blocks.BLUE_ICE)) {
/* 65 */               var0.setTypeAndData(var9, Blocks.BLUE_ICE.getBlockData(), 2);
/*    */               break;
/*    */             } 
/*    */           }  } 
/*    */       } 
/*    */     } 
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBlueIce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */