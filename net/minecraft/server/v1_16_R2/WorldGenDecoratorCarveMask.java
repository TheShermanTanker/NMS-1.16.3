/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.BitSet;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorCarveMask
/*    */   extends WorldGenDecorator<WorldGenDecoratorCarveMaskConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorCarveMask(Codec<WorldGenDecoratorCarveMaskConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenDecoratorCarveMaskConfiguration var2, BlockPosition var3) {
/* 19 */     ChunkCoordIntPair var4 = new ChunkCoordIntPair(var3);
/* 20 */     BitSet var5 = var0.a(var4, var2.c);
/*    */     
/* 22 */     return IntStream.range(0, var5.length()).filter(var3 -> (var0.get(var3) && var1.nextFloat() < var2.d)).mapToObj(var1 -> {
/*    */           int var2 = var1 & 0xF;
/*    */           int var3 = var1 >> 4 & 0xF;
/*    */           int var4 = var1 >> 8;
/*    */           return new BlockPosition(var0.d() + var2, var4, var0.e() + var3);
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorCarveMask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */