/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenFeatureStateProvider
/*    */ {
/* 11 */   public static final Codec<WorldGenFeatureStateProvider> a = IRegistry.BLOCK_STATE_PROVIDER_TYPE.dispatch(WorldGenFeatureStateProvider::a, WorldGenFeatureStateProviders::a);
/*    */   
/*    */   protected abstract WorldGenFeatureStateProviders<?> a();
/*    */   
/*    */   public abstract IBlockData a(Random paramRandom, BlockPosition paramBlockPosition);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureStateProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */