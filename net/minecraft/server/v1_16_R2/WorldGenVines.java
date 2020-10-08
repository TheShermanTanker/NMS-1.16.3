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
/*    */ public class WorldGenVines
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/* 17 */   private static final EnumDirection[] a = EnumDirection.values();
/*    */   
/*    */   public WorldGenVines(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 20 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 25 */     BlockPosition.MutableBlockPosition var5 = var3.i();
/* 26 */     for (int var6 = 64; var6 < 256; var6++) {
/* 27 */       var5.g(var3);
/* 28 */       var5.e(var2.nextInt(4) - var2.nextInt(4), 0, var2.nextInt(4) - var2.nextInt(4));
/* 29 */       var5.p(var6);
/*    */       
/* 31 */       if (var0.isEmpty(var5))
/*    */       {
/*    */ 
/*    */         
/* 35 */         for (EnumDirection var10 : a) {
/* 36 */           if (var10 != EnumDirection.DOWN)
/*    */           {
/*    */ 
/*    */             
/* 40 */             if (BlockVine.a(var0, var5, var10)) {
/* 41 */               var0.setTypeAndData(var5, Blocks.VINE.getBlockData().set(BlockVine.getDirection(var10), Boolean.valueOf(true)), 2);
/*    */               break;
/*    */             }  } 
/*    */         }  } 
/*    */     } 
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenVines.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */