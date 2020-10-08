/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.stream.Stream;
/*    */ import javax.annotation.Nullable;
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
/*    */ public interface ICombinedAccess
/*    */   extends IEntityAccess, IWorldReader, VirtualLevelWritable
/*    */ {
/*    */   default Stream<VoxelShape> c(@Nullable Entity var0, AxisAlignedBB var1, Predicate<Entity> var2) {
/* 22 */     return super.c(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean a(@Nullable Entity var0, VoxelShape var1) {
/* 27 */     return super.a(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   default BlockPosition getHighestBlockYAt(HeightMap.Type var0, BlockPosition var1) {
/* 32 */     return super.getHighestBlockYAt(var0, var1);
/*    */   }
/*    */   
/*    */   IRegistryCustom r();
/*    */   
/*    */   default Optional<ResourceKey<BiomeBase>> i(BlockPosition var0) {
/* 38 */     return r().<BiomeBase>b(IRegistry.ay).c(getBiome(var0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ICombinedAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */