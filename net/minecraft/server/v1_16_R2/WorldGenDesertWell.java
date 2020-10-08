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
/*    */ public class WorldGenDesertWell
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/* 17 */   private static final BlockStatePredicate a = BlockStatePredicate.a(Blocks.SAND);
/*    */   
/* 19 */   private final IBlockData ab = Blocks.SANDSTONE_SLAB.getBlockData();
/* 20 */   private final IBlockData ac = Blocks.SANDSTONE.getBlockData();
/* 21 */   private final IBlockData ad = Blocks.WATER.getBlockData();
/*    */   
/*    */   public WorldGenDesertWell(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 24 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 29 */     var3 = var3.up();
/*    */     
/* 31 */     while (var0.isEmpty(var3) && var3.getY() > 2) {
/* 32 */       var3 = var3.down();
/*    */     }
/*    */     
/* 35 */     if (!a.test(var0.getType(var3))) {
/* 36 */       return false;
/*    */     }
/*    */     
/*    */     int var5;
/*    */     
/* 41 */     for (var5 = -2; var5 <= 2; var5++) {
/* 42 */       for (int var6 = -2; var6 <= 2; var6++) {
/* 43 */         if (var0.isEmpty(var3.b(var5, -1, var6)) && var0.isEmpty(var3.b(var5, -2, var6))) {
/* 44 */           return false;
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 50 */     for (var5 = -1; var5 <= 0; var5++) {
/* 51 */       for (int var6 = -2; var6 <= 2; var6++) {
/* 52 */         for (int var7 = -2; var7 <= 2; var7++) {
/* 53 */           var0.setTypeAndData(var3.b(var6, var5, var7), this.ac, 2);
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 59 */     var0.setTypeAndData(var3, this.ad, 2);
/* 60 */     for (EnumDirection var6 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 61 */       var0.setTypeAndData(var3.shift(var6), this.ad, 2);
/*    */     }
/*    */ 
/*    */     
/* 65 */     for (var5 = -2; var5 <= 2; var5++) {
/* 66 */       for (int var6 = -2; var6 <= 2; var6++) {
/* 67 */         if (var5 == -2 || var5 == 2 || var6 == -2 || var6 == 2) {
/* 68 */           var0.setTypeAndData(var3.b(var5, 1, var6), this.ac, 2);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 73 */     var0.setTypeAndData(var3.b(2, 1, 0), this.ab, 2);
/* 74 */     var0.setTypeAndData(var3.b(-2, 1, 0), this.ab, 2);
/* 75 */     var0.setTypeAndData(var3.b(0, 1, 2), this.ab, 2);
/* 76 */     var0.setTypeAndData(var3.b(0, 1, -2), this.ab, 2);
/*    */ 
/*    */     
/* 79 */     for (var5 = -1; var5 <= 1; var5++) {
/* 80 */       for (int var6 = -1; var6 <= 1; var6++) {
/* 81 */         if (var5 == 0 && var6 == 0) {
/* 82 */           var0.setTypeAndData(var3.b(var5, 4, var6), this.ac, 2);
/*    */         } else {
/* 84 */           var0.setTypeAndData(var3.b(var5, 4, var6), this.ab, 2);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 90 */     for (var5 = 1; var5 <= 3; var5++) {
/* 91 */       var0.setTypeAndData(var3.b(-1, var5, -1), this.ac, 2);
/* 92 */       var0.setTypeAndData(var3.b(-1, var5, 1), this.ac, 2);
/* 93 */       var0.setTypeAndData(var3.b(1, var5, -1), this.ac, 2);
/* 94 */       var0.setTypeAndData(var3.b(1, var5, 1), this.ac, 2);
/*    */     } 
/*    */     
/* 97 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDesertWell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */