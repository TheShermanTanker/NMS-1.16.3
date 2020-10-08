/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ public class RayTrace
/*    */ {
/*    */   private final Vec3D a;
/*    */   private final Vec3D b;
/*    */   private final BlockCollisionOption c;
/*    */   private final FluidCollisionOption d;
/*    */   private final VoxelShapeCollision e;
/*    */   
/*    */   public RayTrace(Vec3D vec3d, Vec3D vec3d1, BlockCollisionOption raytrace_blockcollisionoption, FluidCollisionOption raytrace_fluidcollisionoption, Entity entity) {
/* 14 */     this.a = vec3d;
/* 15 */     this.b = vec3d1;
/* 16 */     this.c = raytrace_blockcollisionoption;
/* 17 */     this.d = raytrace_fluidcollisionoption;
/* 18 */     this.e = (entity == null) ? VoxelShapeCollision.a() : VoxelShapeCollision.a(entity);
/*    */   }
/*    */   
/*    */   public Vec3D a() {
/* 22 */     return this.b;
/*    */   }
/*    */   
/*    */   public Vec3D b() {
/* 26 */     return this.a;
/*    */   }
/*    */   
/*    */   public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 30 */     return this.c.get(iblockdata, iblockaccess, blockposition, this.e);
/*    */   }
/*    */   
/*    */   public VoxelShape a(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 34 */     return this.d.a(fluid) ? fluid.d(iblockaccess, blockposition) : VoxelShapes.a();
/*    */   }
/*    */   public static interface c {
/*    */     VoxelShape get(IBlockData param1IBlockData, IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition, VoxelShapeCollision param1VoxelShapeCollision); }
/*    */   
/* 39 */   public enum FluidCollisionOption { NONE((String)(fluid -> false)),
/*    */     
/* 41 */     SOURCE_ONLY((String)Fluid::isSource), ANY((String)Fluid::isSource); static { ANY = new FluidCollisionOption("ANY", 2, fluid -> !fluid.isEmpty()); }
/*    */ 
/*    */ 
/*    */     
/*    */     private final Predicate<Fluid> predicate;
/*    */     
/*    */     FluidCollisionOption(Predicate<Fluid> predicate) {
/* 48 */       this.predicate = predicate;
/*    */     }
/*    */     
/*    */     public boolean a(Fluid fluid) {
/* 52 */       return this.predicate.test(fluid);
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum BlockCollisionOption
/*    */     implements c
/*    */   {
/* 63 */     COLLIDER((String)BlockBase.BlockData::b), OUTLINE((String)BlockBase.BlockData::a), VISUAL((String)BlockBase.BlockData::c);
/*    */     
/*    */     private final RayTrace.c d;
/*    */     
/*    */     BlockCollisionOption(RayTrace.c raytrace_c) {
/* 68 */       this.d = raytrace_c;
/*    */     }
/*    */ 
/*    */     
/*    */     public VoxelShape get(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 73 */       return this.d.get(iblockdata, iblockaccess, blockposition, voxelshapecollision);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RayTrace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */