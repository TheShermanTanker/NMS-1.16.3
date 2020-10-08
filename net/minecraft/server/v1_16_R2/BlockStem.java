/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockStem
/*    */   extends BlockPlant
/*    */   implements IBlockFragilePlantElement {
/*  9 */   public static final BlockStateInteger AGE = BlockProperties.ai;
/* 10 */   protected static final VoxelShape[] b = new VoxelShape[] { Block.a(7.0D, 0.0D, 7.0D, 9.0D, 2.0D, 9.0D), Block.a(7.0D, 0.0D, 7.0D, 9.0D, 4.0D, 9.0D), Block.a(7.0D, 0.0D, 7.0D, 9.0D, 6.0D, 9.0D), Block.a(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D), Block.a(7.0D, 0.0D, 7.0D, 9.0D, 10.0D, 9.0D), Block.a(7.0D, 0.0D, 7.0D, 9.0D, 12.0D, 9.0D), Block.a(7.0D, 0.0D, 7.0D, 9.0D, 14.0D, 9.0D), Block.a(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D) };
/*    */   private final BlockStemmed blockFruit;
/*    */   
/*    */   protected BlockStem(BlockStemmed blockstemmed, BlockBase.Info blockbase_info) {
/* 14 */     super(blockbase_info);
/* 15 */     this.blockFruit = blockstemmed;
/* 16 */     j(((IBlockData)this.blockStateList.getBlockData()).set(AGE, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 21 */     return b[((Integer)iblockdata.get(AGE)).intValue()];
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 26 */     return iblockdata.a(Blocks.FARMLAND);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 31 */     if (worldserver.getLightLevel(blockposition, 0) >= 9) {
/* 32 */       float f = BlockCrops.a(this, worldserver, blockposition);
/*    */       
/* 34 */       if (random.nextInt((int)(100.0F / ((this == Blocks.PUMPKIN_STEM) ? worldserver.spigotConfig.pumpkinModifier : worldserver.spigotConfig.melonModifier) * 25.0F / f) + 1) == 0) {
/* 35 */         int i = ((Integer)iblockdata.get(AGE)).intValue();
/*    */         
/* 37 */         if (i < 7) {
/* 38 */           iblockdata = iblockdata.set(AGE, Integer.valueOf(i + 1));
/* 39 */           CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, iblockdata, 2);
/*    */         } else {
/* 41 */           EnumDirection enumdirection = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);
/* 42 */           BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 43 */           IBlockData iblockdata1 = worldserver.getType(blockposition1.down());
/*    */           
/* 45 */           if (worldserver.getType(blockposition1).isAir() && (iblockdata1.a(Blocks.FARMLAND) || iblockdata1.a(Blocks.DIRT) || iblockdata1.a(Blocks.COARSE_DIRT) || iblockdata1.a(Blocks.PODZOL) || iblockdata1.a(Blocks.GRASS_BLOCK))) {
/*    */             
/* 47 */             if (!CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition1, this.blockFruit.getBlockData())) {
/*    */               return;
/*    */             }
/*    */             
/* 51 */             worldserver.setTypeUpdate(blockposition, this.blockFruit.d().getBlockData().set(BlockFacingHorizontal.FACING, enumdirection));
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 61 */     return (((Integer)iblockdata.get(AGE)).intValue() != 7);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 66 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 71 */     int i = Math.min(7, ((Integer)iblockdata.get(AGE)).intValue() + MathHelper.nextInt(worldserver.random, 2, 5));
/* 72 */     IBlockData iblockdata1 = iblockdata.set(AGE, Integer.valueOf(i));
/*    */     
/* 74 */     CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, iblockdata1, 2);
/* 75 */     if (i == 7) {
/* 76 */       iblockdata1.b(worldserver, blockposition, worldserver.random);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 83 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { AGE });
/*    */   }
/*    */   
/*    */   public BlockStemmed d() {
/* 87 */     return this.blockFruit;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */