/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenBlockPlacer
/*    */ {
/* 12 */   public static final Codec<WorldGenBlockPlacer> a = IRegistry.BLOCK_PLACER_TYPE.dispatch(WorldGenBlockPlacer::a, WorldGenBlockPlacers::a);
/*    */   
/*    */   public abstract void a(GeneratorAccess paramGeneratorAccess, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom);
/*    */   
/*    */   protected abstract WorldGenBlockPlacers<?> a();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenBlockPlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */