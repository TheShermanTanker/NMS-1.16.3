/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Collector;
/*    */ import java.util.stream.Collectors;
/*    */ import java.util.stream.IntStream;
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
/*    */ public class WorldGenBonusChest
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenBonusChest(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 25 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 30 */     ChunkCoordIntPair var5 = new ChunkCoordIntPair(var3);
/* 31 */     List<Integer> var6 = IntStream.rangeClosed(var5.d(), var5.f()).boxed().collect((Collector)Collectors.toList());
/* 32 */     Collections.shuffle(var6, var2);
/* 33 */     List<Integer> var7 = IntStream.rangeClosed(var5.e(), var5.g()).boxed().collect((Collector)Collectors.toList());
/* 34 */     Collections.shuffle(var7, var2);
/* 35 */     BlockPosition.MutableBlockPosition var8 = new BlockPosition.MutableBlockPosition();
/*    */     
/* 37 */     for (Integer var10 : var6) {
/* 38 */       for (Integer var12 : var7) {
/* 39 */         var8.d(var10.intValue(), 0, var12.intValue());
/* 40 */         BlockPosition var13 = var0.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, var8);
/*    */         
/* 42 */         if (var0.isEmpty(var13) || var0.getType(var13).getCollisionShape(var0, var13).isEmpty()) {
/* 43 */           var0.setTypeAndData(var13, Blocks.CHEST.getBlockData(), 2);
/*    */           
/* 45 */           TileEntityLootable.a(var0, var2, var13, LootTables.b);
/*    */           
/* 47 */           IBlockData var14 = Blocks.TORCH.getBlockData();
/*    */           
/* 49 */           for (EnumDirection var16 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 50 */             BlockPosition var17 = var13.shift(var16);
/* 51 */             if (var14.canPlace(var0, var17)) {
/* 52 */               var0.setTypeAndData(var17, var14, 2);
/*    */             }
/*    */           } 
/* 55 */           return true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenBonusChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */