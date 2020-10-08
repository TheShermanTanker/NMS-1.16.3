/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockCoralPlant extends BlockCoralBase {
/*    */   private final Block c;
/*  8 */   protected static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 15.0D, 14.0D);
/*    */   
/*    */   protected BlockCoralPlant(Block block, BlockBase.Info blockbase_info) {
/* 11 */     super(blockbase_info);
/* 12 */     this.c = block;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 17 */     a(iblockdata, world, blockposition);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 22 */     if (!c(iblockdata, worldserver, blockposition)) {
/*    */       
/* 24 */       if (CraftEventFactory.callBlockFadeEvent(worldserver, blockposition, this.c.getBlockData().set(b, Boolean.valueOf(false))).isCancelled()) {
/*    */         return;
/*    */       }
/*    */       
/* 28 */       worldserver.setTypeAndData(blockposition, this.c.getBlockData().set(b, Boolean.valueOf(false)), 2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 35 */     if (enumdirection == EnumDirection.DOWN && !iblockdata.canPlace(generatoraccess, blockposition)) {
/* 36 */       return Blocks.AIR.getBlockData();
/*    */     }
/* 38 */     a(iblockdata, generatoraccess, blockposition);
/* 39 */     if (((Boolean)iblockdata.get(b)).booleanValue()) {
/* 40 */       generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(generatoraccess));
/*    */     }
/*    */     
/* 43 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 49 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCoralPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */