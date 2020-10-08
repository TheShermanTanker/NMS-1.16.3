/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenBlockPlacerSimple
/*    */   extends WorldGenBlockPlacer
/*    */ {
/* 12 */   public static final Codec<WorldGenBlockPlacerSimple> b = Codec.unit(() -> c);
/* 13 */   public static final WorldGenBlockPlacerSimple c = new WorldGenBlockPlacerSimple();
/*    */ 
/*    */   
/*    */   protected WorldGenBlockPlacers<?> a() {
/* 17 */     return WorldGenBlockPlacers.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Random var3) {
/* 22 */     var0.setTypeAndData(var1, var2, 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenBlockPlacerSimple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */