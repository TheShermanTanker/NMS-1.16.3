/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface VoxelShapeCollision
/*    */ {
/*    */   static VoxelShapeCollision a() {
/* 11 */     return VoxelShapeCollisionEntity.a;
/*    */   }
/*    */ 
/*    */   
/*    */   static VoxelShapeCollision a(Entity var0) {
/* 16 */     return new VoxelShapeCollisionEntity(var0);
/*    */   }
/*    */   
/*    */   boolean b();
/*    */   
/*    */   boolean a(VoxelShape paramVoxelShape, BlockPosition paramBlockPosition, boolean paramBoolean);
/*    */   
/*    */   boolean a(Item paramItem);
/*    */   
/*    */   boolean a(Fluid paramFluid, FluidTypeFlowing paramFluidTypeFlowing);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeCollision.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */