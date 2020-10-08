/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockReed extends Block {
/*  8 */   public static final BlockStateInteger AGE = BlockProperties.aj;
/*  9 */   protected static final VoxelShape b = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
/*    */   
/*    */   protected BlockReed(BlockBase.Info blockbase_info) {
/* 12 */     super(blockbase_info);
/* 13 */     j(((IBlockData)this.blockStateList.getBlockData()).set(AGE, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 18 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 23 */     if (!iblockdata.canPlace(worldserver, blockposition)) {
/* 24 */       worldserver.b(blockposition, true);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 31 */     if (worldserver.isEmpty(blockposition.up())) {
/*    */       int i;
/*    */       
/* 34 */       for (i = 1; worldserver.getType(blockposition.down(i)).a(this); i++);
/*    */ 
/*    */ 
/*    */       
/* 38 */       if (i < worldserver.paperConfig.reedMaxHeight) {
/* 39 */         int j = ((Integer)iblockdata.get(AGE)).intValue();
/*    */         
/* 41 */         if (j >= (byte)(int)range(3.0F, 100.0F / worldserver.spigotConfig.caneModifier * 15.0F + 0.5F, 15.0F)) {
/* 42 */           CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition.up(), getBlockData());
/* 43 */           worldserver.setTypeAndData(blockposition, iblockdata.set(AGE, Integer.valueOf(0)), 4);
/*    */         } else {
/* 45 */           worldserver.setTypeAndData(blockposition, iblockdata.set(AGE, Integer.valueOf(j + 1)), 4);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 54 */     if (!iblockdata.canPlace(generatoraccess, blockposition)) {
/* 55 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*    */     }
/*    */     
/* 58 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 63 */     IBlockData iblockdata1 = iworldreader.getType(blockposition.down());
/*    */     
/* 65 */     if (iblockdata1.getBlock() == this) {
/* 66 */       return true;
/*    */     }
/* 68 */     if (iblockdata1.a(Blocks.GRASS_BLOCK) || iblockdata1.a(Blocks.DIRT) || iblockdata1.a(Blocks.COARSE_DIRT) || iblockdata1.a(Blocks.PODZOL) || iblockdata1.a(Blocks.SAND) || iblockdata1.a(Blocks.RED_SAND)) {
/* 69 */       BlockPosition blockposition1 = blockposition.down();
/* 70 */       Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*    */       
/* 72 */       while (iterator.hasNext()) {
/* 73 */         EnumDirection enumdirection = iterator.next();
/* 74 */         IBlockData iblockdata2 = iworldreader.getType(blockposition1.shift(enumdirection));
/* 75 */         Fluid fluid = iworldreader.getFluid(blockposition1.shift(enumdirection));
/*    */         
/* 77 */         if (fluid.a(TagsFluid.WATER) || iblockdata2.a(Blocks.FROSTED_ICE)) {
/* 78 */           return true;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 83 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 89 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { AGE });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockReed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */