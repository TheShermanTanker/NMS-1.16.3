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
/*    */ public class WorldGenDecoratorNetherFire
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenDecoratorFrequencyConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorNetherFire(Codec<WorldGenDecoratorFrequencyConfiguration> var0) {
/* 16 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenDecoratorFrequencyConfiguration var1, BlockPosition var2) {
/* 23 */     List<BlockPosition> var3 = Lists.newArrayList();
/*    */     
/* 25 */     for (int var4 = 0; var4 < var0.nextInt(var0.nextInt(var1.a().a(var0)) + 1) + 1; var4++) {
/* 26 */       int var5 = var0.nextInt(16) + var2.getX();
/* 27 */       int var6 = var0.nextInt(16) + var2.getZ();
/* 28 */       int var7 = var0.nextInt(120) + 4;
/* 29 */       var3.add(new BlockPosition(var5, var7, var6));
/*    */     } 
/*    */     
/* 32 */     return var3.stream();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorNetherFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */