/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.BitSet;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenCavesHell
/*    */   extends WorldGenCaves
/*    */ {
/*    */   public WorldGenCavesHell(Codec<WorldGenFeatureConfigurationChance> var0) {
/* 21 */     super(var0, 128);
/* 22 */     this.j = (Set<Block>)ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, (Object[])new Block[] { Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.NETHERRACK, Blocks.SOUL_SAND, Blocks.SOUL_SOIL, Blocks.CRIMSON_NYLIUM, Blocks.WARPED_NYLIUM, Blocks.NETHER_WART_BLOCK, Blocks.WARPED_WART_BLOCK, Blocks.BASALT, Blocks.BLACKSTONE });
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     this.k = (Set<FluidType>)ImmutableSet.of(FluidTypes.LAVA, FluidTypes.WATER);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int a() {
/* 50 */     return 10;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float a(Random var0) {
/* 55 */     return (var0.nextFloat() * 2.0F + var0.nextFloat()) * 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected double b() {
/* 60 */     return 5.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int b(Random var0) {
/* 65 */     return var0.nextInt(this.l);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, BitSet var2, Random var3, BlockPosition.MutableBlockPosition var4, BlockPosition.MutableBlockPosition var5, BlockPosition.MutableBlockPosition var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, MutableBoolean var15) {
/* 70 */     int var16 = var12 | var14 << 4 | var13 << 8;
/* 71 */     if (var2.get(var16)) {
/* 72 */       return false;
/*    */     }
/* 74 */     var2.set(var16);
/*    */     
/* 76 */     var4.d(var10, var13, var11);
/*    */     
/* 78 */     if (a(var0.getType(var4))) {
/*    */       IBlockData var17;
/* 80 */       if (var13 <= 31) {
/* 81 */         var17 = i.getBlockData();
/*    */       } else {
/* 83 */         var17 = g;
/*    */       } 
/* 85 */       var0.setType(var4, var17, false);
/* 86 */       return true;
/*    */     } 
/* 88 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCavesHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */