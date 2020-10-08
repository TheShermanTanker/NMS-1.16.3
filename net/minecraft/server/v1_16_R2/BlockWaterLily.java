/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockWaterLily extends BlockPlant {
/*  5 */   protected static final VoxelShape a = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);
/*    */   
/*    */   protected BlockWaterLily(BlockBase.Info blockbase_info) {
/*  8 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/* 13 */     super.a(iblockdata, world, blockposition, entity);
/* 14 */     if (world instanceof WorldServer && entity instanceof EntityBoat && !CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/* 15 */       world.a(new BlockPosition(blockposition), true, entity);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 22 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 27 */     Fluid fluid = iblockaccess.getFluid(blockposition);
/* 28 */     Fluid fluid1 = iblockaccess.getFluid(blockposition.up());
/*    */     
/* 30 */     return ((fluid.getType() == FluidTypes.WATER || iblockdata.getMaterial() == Material.ICE) && fluid1.getType() == FluidTypes.EMPTY);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWaterLily.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */