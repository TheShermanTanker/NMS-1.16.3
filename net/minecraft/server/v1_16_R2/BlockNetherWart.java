/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockNetherWart extends BlockPlant {
/*  7 */   public static final BlockStateInteger AGE = BlockProperties.ag;
/*  8 */   private static final VoxelShape[] b = new VoxelShape[] { Block.a(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D) };
/*    */   
/*    */   protected BlockNetherWart(BlockBase.Info blockbase_info) {
/* 11 */     super(blockbase_info);
/* 12 */     j(((IBlockData)this.blockStateList.getBlockData()).set(AGE, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 17 */     return b[((Integer)iblockdata.get(AGE)).intValue()];
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 22 */     return iblockdata.a(Blocks.SOUL_SAND);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTicking(IBlockData iblockdata) {
/* 27 */     return (((Integer)iblockdata.get(AGE)).intValue() < 3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 32 */     int i = ((Integer)iblockdata.get(AGE)).intValue();
/*    */     
/* 34 */     if (i < 3 && random.nextInt(Math.max(1, (int)(100.0F / worldserver.spigotConfig.wartModifier) * 10)) == 0) {
/* 35 */       iblockdata = iblockdata.set(AGE, Integer.valueOf(i + 1));
/* 36 */       CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, iblockdata, 2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 43 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { AGE });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockNetherWart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */