/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockPlant extends Block {
/*    */   protected BlockPlant(BlockBase.Info blockbase_info) {
/*  6 */     super(blockbase_info);
/*    */   }
/*    */   
/*    */   protected boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 10 */     return (iblockdata.a(Blocks.GRASS_BLOCK) || iblockdata.a(Blocks.DIRT) || iblockdata.a(Blocks.COARSE_DIRT) || iblockdata.a(Blocks.PODZOL) || iblockdata.a(Blocks.FARMLAND));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 16 */     if (!iblockdata.canPlace(generatoraccess, blockposition) && (
/* 17 */       !(generatoraccess instanceof WorldServer) || !((WorldServer)generatoraccess).hasPhysicsEvent || !CraftEventFactory.callBlockPhysicsEvent(generatoraccess, blockposition).isCancelled())) {
/* 18 */       return Blocks.AIR.getBlockData();
/*    */     }
/*    */     
/* 21 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 27 */     BlockPosition blockposition1 = blockposition.down();
/*    */     
/* 29 */     return c(iworldreader.getType(blockposition1), iworldreader, blockposition1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 34 */     return iblockdata.getFluid().isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 39 */     return (pathmode == PathMode.AIR && !this.at) ? true : super.a(iblockdata, iblockaccess, blockposition, pathmode);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */