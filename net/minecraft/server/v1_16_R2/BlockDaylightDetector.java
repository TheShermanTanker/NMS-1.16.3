/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockDaylightDetector extends BlockTileEntity {
/*  5 */   public static final BlockStateInteger POWER = BlockProperties.az;
/*  6 */   public static final BlockStateBoolean b = BlockProperties.p;
/*  7 */   protected static final VoxelShape c = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
/*    */   
/*    */   public BlockDaylightDetector(BlockBase.Info blockbase_info) {
/* 10 */     super(blockbase_info);
/* 11 */     j(((IBlockData)this.blockStateList.getBlockData()).set(POWER, Integer.valueOf(0)).set(b, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 16 */     return c;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c_(IBlockData iblockdata) {
/* 21 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 26 */     return ((Integer)iblockdata.get(POWER)).intValue();
/*    */   }
/*    */   
/*    */   public static void d(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 30 */     if (world.getDimensionManager().hasSkyLight()) {
/* 31 */       int i = world.getBrightness(EnumSkyBlock.SKY, blockposition) - world.c();
/* 32 */       float f = world.a(1.0F);
/* 33 */       boolean flag = ((Boolean)iblockdata.get(b)).booleanValue();
/*    */       
/* 35 */       if (flag) {
/* 36 */         i = 15 - i;
/* 37 */       } else if (i > 0) {
/* 38 */         float f1 = (f < 3.1415927F) ? 0.0F : 6.2831855F;
/*    */         
/* 40 */         f += (f1 - f) * 0.2F;
/* 41 */         i = Math.round(i * MathHelper.cos(f));
/*    */       } 
/*    */       
/* 44 */       i = MathHelper.clamp(i, 0, 15);
/* 45 */       if (((Integer)iblockdata.get(POWER)).intValue() != i) {
/* 46 */         i = CraftEventFactory.callRedstoneChange(world, blockposition, ((Integer)iblockdata.get(POWER)).intValue(), i).getNewCurrent();
/* 47 */         world.setTypeAndData(blockposition, iblockdata.set(POWER, Integer.valueOf(i)), 3);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 55 */     if (entityhuman.eJ()) {
/* 56 */       if (world.isClientSide) {
/* 57 */         return EnumInteractionResult.SUCCESS;
/*    */       }
/* 59 */       IBlockData iblockdata1 = iblockdata.a(b);
/*    */       
/* 61 */       world.setTypeAndData(blockposition, iblockdata1, 4);
/* 62 */       d(iblockdata1, world, blockposition);
/* 63 */       return EnumInteractionResult.CONSUME;
/*    */     } 
/*    */     
/* 66 */     return super.interact(iblockdata, world, blockposition, entityhuman, enumhand, movingobjectpositionblock);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData iblockdata) {
/* 72 */     return EnumRenderType.MODEL;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPowerSource(IBlockData iblockdata) {
/* 77 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 82 */     return new TileEntityLightDetector();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 87 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { POWER, b });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */