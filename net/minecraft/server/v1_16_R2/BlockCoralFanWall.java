/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockCoralFanWall extends BlockCoralFanWallAbstract {
/*    */   private final Block c;
/*    */   
/*    */   protected BlockCoralFanWall(Block block, BlockBase.Info blockbase_info) {
/* 10 */     super(blockbase_info);
/* 11 */     this.c = block;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 16 */     a(iblockdata, world, blockposition);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 21 */     if (!c(iblockdata, worldserver, blockposition)) {
/*    */       
/* 23 */       if (CraftEventFactory.callBlockFadeEvent(worldserver, blockposition, this.c.getBlockData().set(b, Boolean.valueOf(false)).set(a, iblockdata.get(a))).isCancelled()) {
/*    */         return;
/*    */       }
/*    */       
/* 27 */       worldserver.setTypeAndData(blockposition, this.c.getBlockData().set(b, Boolean.valueOf(false)).set(a, iblockdata.get(a)), 2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 34 */     if (enumdirection.opposite() == iblockdata.get(a) && !iblockdata.canPlace(generatoraccess, blockposition)) {
/* 35 */       return Blocks.AIR.getBlockData();
/*    */     }
/* 37 */     if (((Boolean)iblockdata.get(b)).booleanValue()) {
/* 38 */       generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(generatoraccess));
/*    */     }
/*    */     
/* 41 */     a(iblockdata, generatoraccess, blockposition);
/* 42 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCoralFanWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */