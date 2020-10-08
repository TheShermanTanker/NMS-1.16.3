/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenBlockPlacerDoublePlant
/*    */   extends WorldGenBlockPlacer
/*    */ {
/* 13 */   public static final Codec<WorldGenBlockPlacerDoublePlant> b = Codec.unit(() -> c);
/*    */   
/* 15 */   public static final WorldGenBlockPlacerDoublePlant c = new WorldGenBlockPlacerDoublePlant();
/*    */ 
/*    */   
/*    */   protected WorldGenBlockPlacers<?> a() {
/* 19 */     return WorldGenBlockPlacers.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Random var3) {
/* 24 */     ((BlockTallPlant)var2.getBlock()).a(var0, var1, 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenBlockPlacerDoublePlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */