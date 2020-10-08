/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public interface GeneratorAccess
/*    */   extends ICombinedAccess, IWorldTime
/*    */ {
/*    */   default long ab() {
/* 10 */     return getWorldData().getDayTime();
/*    */   }
/*    */   
/*    */   TickList<Block> getBlockTickList();
/*    */   
/*    */   TickList<FluidType> getFluidTickList();
/*    */   
/*    */   WorldData getWorldData();
/*    */   
/*    */   DifficultyDamageScaler getDamageScaler(BlockPosition paramBlockPosition);
/*    */   
/*    */   default EnumDifficulty getDifficulty() {
/* 22 */     return getWorldData().getDifficulty();
/*    */   }
/*    */ 
/*    */   
/*    */   IChunkProvider getChunkProvider();
/*    */   
/*    */   default boolean isChunkLoaded(int i, int j) {
/* 29 */     return getChunkProvider().b(i, j);
/*    */   }
/*    */   
/*    */   Random getRandom();
/*    */   
/*    */   default void update(BlockPosition blockposition, Block block) {}
/*    */   
/*    */   void playSound(@Nullable EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, SoundEffect paramSoundEffect, SoundCategory paramSoundCategory, float paramFloat1, float paramFloat2);
/*    */   
/*    */   void addParticle(ParticleParam paramParticleParam, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6);
/*    */   
/*    */   void a(@Nullable EntityHuman paramEntityHuman, int paramInt1, BlockPosition paramBlockPosition, int paramInt2);
/*    */   
/*    */   default int getHeight() {
/* 43 */     return getDimensionManager().getLogicalHeight();
/*    */   }
/*    */   
/*    */   default void triggerEffect(int i, BlockPosition blockposition, int j) {
/* 47 */     a((EntityHuman)null, i, blockposition, j);
/*    */   }
/*    */   
/*    */   WorldServer getMinecraftWorld();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GeneratorAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */