/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BlockGrassPath
/*    */   extends Block {
/*  7 */   protected static final VoxelShape a = BlockSoil.b;
/*    */   
/*    */   protected BlockGrassPath(BlockBase.Info blockbase_info) {
/* 10 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c_(IBlockData iblockdata) {
/* 15 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 20 */     return !getBlockData().canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition()) ? Block.a(getBlockData(), Blocks.DIRT.getBlockData(), blockactioncontext.getWorld(), blockactioncontext.getClickPosition()) : super.getPlacedState(blockactioncontext);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 25 */     if (enumdirection == EnumDirection.UP && !iblockdata.canPlace(generatoraccess, blockposition)) {
/* 26 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*    */     }
/*    */     
/* 29 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 35 */     if (iblockdata.canPlace(worldserver, blockposition)) {
/*    */       return;
/*    */     }
/*    */     
/* 39 */     BlockSoil.fade(iblockdata, worldserver, blockposition);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 44 */     IBlockData iblockdata1 = iworldreader.getType(blockposition.up());
/*    */     
/* 46 */     return (!iblockdata1.getMaterial().isBuildable() || iblockdata1.getBlock() instanceof BlockFenceGate);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 51 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockGrassPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */