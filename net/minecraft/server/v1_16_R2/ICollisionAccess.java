/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.BiPredicate;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.stream.Stream;
/*    */ import java.util.stream.StreamSupport;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ICollisionAccess
/*    */   extends IBlockAccess
/*    */ {
/*    */   default boolean a(@Nullable Entity entity, VoxelShape voxelshape) {
/* 17 */     return true;
/*    */   }
/*    */   
/*    */   default boolean a(IBlockData iblockdata, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 21 */     VoxelShape voxelshape = iblockdata.b(this, blockposition, voxelshapecollision);
/*    */     
/* 23 */     return (voxelshape.isEmpty() || a((Entity)null, voxelshape.a(blockposition.getX(), blockposition.getY(), blockposition.getZ())));
/*    */   }
/*    */   
/*    */   default boolean j(Entity entity) {
/* 27 */     return a(entity, VoxelShapes.a(entity.getBoundingBox()));
/*    */   }
/*    */   
/*    */   default boolean b(AxisAlignedBB axisalignedbb) {
/* 31 */     return b((Entity)null, axisalignedbb, entity -> true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean getCubes(Entity entity) {
/* 37 */     return b(entity, entity.getBoundingBox(), entity1 -> true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean getCubes(Entity entity, AxisAlignedBB axisalignedbb) {
/* 43 */     return b(entity, axisalignedbb, entity1 -> true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean b(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
/* 50 */     return getCubes(entity, axisalignedbb, predicate);
/*    */   }
/*    */   default boolean getCubes(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
/*    */     
/* 54 */     try { if (entity != null) entity.collisionLoadChunks = true; 
/* 55 */       return d(entity, axisalignedbb, predicate).allMatch(VoxelShape::isEmpty); }
/* 56 */     finally { if (entity != null) entity.collisionLoadChunks = false;
/*    */        }
/*    */   
/*    */   }
/*    */   
/*    */   default Stream<VoxelShape> d(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
/* 62 */     return Stream.concat(b(entity, axisalignedbb), c(entity, axisalignedbb, predicate));
/*    */   }
/*    */   
/*    */   default Stream<VoxelShape> b(@Nullable Entity entity, AxisAlignedBB axisalignedbb) {
/* 66 */     return StreamSupport.stream(new VoxelShapeSpliterator(this, entity, axisalignedbb), false);
/*    */   }
/*    */   
/*    */   default Stream<VoxelShape> b(@Nullable Entity entity, AxisAlignedBB axisalignedbb, BiPredicate<IBlockData, BlockPosition> bipredicate) {
/* 70 */     return StreamSupport.stream(new VoxelShapeSpliterator(this, entity, axisalignedbb, bipredicate), false);
/*    */   }
/*    */   
/*    */   WorldBorder getWorldBorder();
/*    */   
/*    */   @Nullable
/*    */   IBlockAccess c(int paramInt1, int paramInt2);
/*    */   
/*    */   Stream<VoxelShape> c(@Nullable Entity paramEntity, AxisAlignedBB paramAxisAlignedBB, Predicate<Entity> paramPredicate);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ICollisionAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */