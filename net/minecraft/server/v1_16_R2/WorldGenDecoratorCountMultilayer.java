/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorCountMultilayer
/*    */   extends WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorCountMultilayer(Codec<WorldGenDecoratorFrequencyConfiguration> var0) {
/* 20 */     super(var0);
/*    */   }
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenDecoratorFrequencyConfiguration var2, BlockPosition var3) {
/*    */     boolean var5;
/* 25 */     List<BlockPosition> var4 = Lists.newArrayList();
/*    */     
/* 27 */     int var6 = 0;
/*    */     do {
/* 29 */       var5 = false;
/* 30 */       for (int var7 = 0; var7 < var2.a().a(var1); var7++) {
/* 31 */         int var8 = var1.nextInt(16) + var3.getX();
/* 32 */         int var9 = var1.nextInt(16) + var3.getZ();
/* 33 */         int var10 = var0.a(HeightMap.Type.MOTION_BLOCKING, var8, var9);
/* 34 */         int var11 = a(var0, var8, var10, var9, var6);
/* 35 */         if (var11 != Integer.MAX_VALUE) {
/* 36 */           var4.add(new BlockPosition(var8, var11, var9));
/* 37 */           var5 = true;
/*    */         } 
/*    */       } 
/* 40 */       var6++;
/* 41 */     } while (var5);
/*    */     
/* 43 */     return var4.stream();
/*    */   }
/*    */ 
/*    */   
/*    */   private static int a(WorldGenDecoratorContext var0, int var1, int var2, int var3, int var4) {
/* 48 */     BlockPosition.MutableBlockPosition var5 = new BlockPosition.MutableBlockPosition(var1, var2, var3);
/*    */     
/* 50 */     int var6 = 0;
/* 51 */     IBlockData var7 = var0.a(var5);
/* 52 */     for (int var8 = var2; var8 >= 1; var8--) {
/* 53 */       var5.p(var8 - 1);
/* 54 */       IBlockData var9 = var0.a(var5);
/* 55 */       if (!a(var9) && a(var7) && !var9.a(Blocks.BEDROCK)) {
/* 56 */         if (var6 == var4) {
/* 57 */           return var5.getY() + 1;
/*    */         }
/* 59 */         var6++;
/*    */       } 
/* 61 */       var7 = var9;
/*    */     } 
/* 63 */     return Integer.MAX_VALUE;
/*    */   }
/*    */   
/*    */   private static boolean a(IBlockData var0) {
/* 67 */     return (var0.isAir() || var0.a(Blocks.WATER) || var0.a(Blocks.LAVA));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorCountMultilayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */