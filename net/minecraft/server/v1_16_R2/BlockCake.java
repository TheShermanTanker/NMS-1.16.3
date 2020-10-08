/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*    */ 
/*    */ public class BlockCake extends Block {
/*  5 */   public static final BlockStateInteger BITES = BlockProperties.al;
/*  6 */   protected static final VoxelShape[] b = new VoxelShape[] { Block.a(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.a(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.a(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.a(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.a(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.a(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.a(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D) };
/*    */   
/*    */   protected BlockCake(BlockBase.Info blockbase_info) {
/*  9 */     super(blockbase_info);
/* 10 */     j(((IBlockData)this.blockStateList.getBlockData()).set(BITES, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 15 */     return b[((Integer)iblockdata.get(BITES)).intValue()];
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 20 */     if (world.isClientSide) {
/* 21 */       ItemStack itemstack = entityhuman.b(enumhand);
/*    */       
/* 23 */       if (a(world, blockposition, iblockdata, entityhuman).a()) {
/* 24 */         return EnumInteractionResult.SUCCESS;
/*    */       }
/*    */       
/* 27 */       if (itemstack.isEmpty()) {
/* 28 */         return EnumInteractionResult.CONSUME;
/*    */       }
/*    */     } 
/*    */     
/* 32 */     return a(world, blockposition, iblockdata, entityhuman);
/*    */   }
/*    */   
/*    */   private EnumInteractionResult a(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 36 */     if (!entityhuman.q(false)) {
/* 37 */       return EnumInteractionResult.PASS;
/*    */     }
/* 39 */     entityhuman.a(StatisticList.EAT_CAKE_SLICE);
/*    */ 
/*    */     
/* 42 */     int oldFoodLevel = (entityhuman.getFoodData()).foodLevel;
/*    */     
/* 44 */     FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(entityhuman, 2 + oldFoodLevel);
/*    */     
/* 46 */     if (!event.isCancelled()) {
/* 47 */       entityhuman.getFoodData().eat(event.getFoodLevel() - oldFoodLevel, 0.1F);
/*    */     }
/*    */     
/* 50 */     ((EntityPlayer)entityhuman).getBukkitEntity().sendHealthUpdate();
/*    */     
/* 52 */     int i = ((Integer)iblockdata.get(BITES)).intValue();
/*    */     
/* 54 */     if (i < 6) {
/* 55 */       generatoraccess.setTypeAndData(blockposition, iblockdata.set(BITES, Integer.valueOf(i + 1)), 3);
/*    */     } else {
/* 57 */       generatoraccess.a(blockposition, false);
/*    */     } 
/*    */     
/* 60 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 66 */     return (enumdirection == EnumDirection.DOWN && !iblockdata.canPlace(generatoraccess, blockposition)) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 71 */     return iworldreader.getType(blockposition.down()).getMaterial().isBuildable();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 76 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { BITES });
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 81 */     return (7 - ((Integer)iblockdata.get(BITES)).intValue()) * 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 86 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */