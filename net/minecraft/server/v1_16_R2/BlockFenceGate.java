/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockFenceGate extends BlockFacingHorizontal {
/*   5 */   public static final BlockStateBoolean OPEN = BlockProperties.u;
/*   6 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*   7 */   public static final BlockStateBoolean IN_WALL = BlockProperties.q;
/*   8 */   protected static final VoxelShape d = Block.a(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
/*   9 */   protected static final VoxelShape e = Block.a(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
/*  10 */   protected static final VoxelShape f = Block.a(0.0D, 0.0D, 6.0D, 16.0D, 13.0D, 10.0D);
/*  11 */   protected static final VoxelShape g = Block.a(6.0D, 0.0D, 0.0D, 10.0D, 13.0D, 16.0D);
/*  12 */   protected static final VoxelShape h = Block.a(0.0D, 0.0D, 6.0D, 16.0D, 24.0D, 10.0D);
/*  13 */   protected static final VoxelShape i = Block.a(6.0D, 0.0D, 0.0D, 10.0D, 24.0D, 16.0D);
/*  14 */   protected static final VoxelShape j = VoxelShapes.a(Block.a(0.0D, 5.0D, 7.0D, 2.0D, 16.0D, 9.0D), Block.a(14.0D, 5.0D, 7.0D, 16.0D, 16.0D, 9.0D));
/*  15 */   protected static final VoxelShape k = VoxelShapes.a(Block.a(7.0D, 5.0D, 0.0D, 9.0D, 16.0D, 2.0D), Block.a(7.0D, 5.0D, 14.0D, 9.0D, 16.0D, 16.0D));
/*  16 */   protected static final VoxelShape o = VoxelShapes.a(Block.a(0.0D, 2.0D, 7.0D, 2.0D, 13.0D, 9.0D), Block.a(14.0D, 2.0D, 7.0D, 16.0D, 13.0D, 9.0D));
/*  17 */   protected static final VoxelShape p = VoxelShapes.a(Block.a(7.0D, 2.0D, 0.0D, 9.0D, 13.0D, 2.0D), Block.a(7.0D, 2.0D, 14.0D, 9.0D, 13.0D, 16.0D));
/*     */   
/*     */   public BlockFenceGate(BlockBase.Info blockbase_info) {
/*  20 */     super(blockbase_info);
/*  21 */     j(((IBlockData)this.blockStateList.getBlockData()).set(OPEN, Boolean.valueOf(false)).set(POWERED, Boolean.valueOf(false)).set(IN_WALL, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  26 */     return ((Boolean)iblockdata.get(IN_WALL)).booleanValue() ? ((((EnumDirection)iblockdata.get(FACING)).n() == EnumDirection.EnumAxis.X) ? g : f) : ((((EnumDirection)iblockdata.get(FACING)).n() == EnumDirection.EnumAxis.X) ? e : d);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  31 */     EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.n();
/*     */     
/*  33 */     if (((EnumDirection)iblockdata.get(FACING)).g().n() != enumdirection_enumaxis) {
/*  34 */       return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */     }
/*  36 */     boolean flag = (h(iblockdata1) || h(generatoraccess.getType(blockposition.shift(enumdirection.opposite()))));
/*     */     
/*  38 */     return iblockdata.set(IN_WALL, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  44 */     return ((Boolean)iblockdata.get(OPEN)).booleanValue() ? VoxelShapes.a() : ((((EnumDirection)iblockdata.get(FACING)).n() == EnumDirection.EnumAxis.Z) ? h : i);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape d(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  49 */     return ((Boolean)iblockdata.get(IN_WALL)).booleanValue() ? ((((EnumDirection)iblockdata.get(FACING)).n() == EnumDirection.EnumAxis.X) ? p : o) : ((((EnumDirection)iblockdata.get(FACING)).n() == EnumDirection.EnumAxis.X) ? k : j);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/*  54 */     switch (pathmode) {
/*     */       case LAND:
/*  56 */         return ((Boolean)iblockdata.get(OPEN)).booleanValue();
/*     */       case WATER:
/*  58 */         return false;
/*     */       case AIR:
/*  60 */         return ((Boolean)iblockdata.get(OPEN)).booleanValue();
/*     */     } 
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  68 */     World world = blockactioncontext.getWorld();
/*  69 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*  70 */     boolean flag = world.isBlockIndirectlyPowered(blockposition);
/*  71 */     EnumDirection enumdirection = blockactioncontext.f();
/*  72 */     EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.n();
/*  73 */     boolean flag1 = ((enumdirection_enumaxis == EnumDirection.EnumAxis.Z && (h(world.getType(blockposition.west())) || h(world.getType(blockposition.east())))) || (enumdirection_enumaxis == EnumDirection.EnumAxis.X && (h(world.getType(blockposition.north())) || h(world.getType(blockposition.south())))));
/*     */     
/*  75 */     return getBlockData().set(FACING, enumdirection).set(OPEN, Boolean.valueOf(flag)).set(POWERED, Boolean.valueOf(flag)).set(IN_WALL, Boolean.valueOf(flag1));
/*     */   }
/*     */   
/*     */   private boolean h(IBlockData iblockdata) {
/*  79 */     return iblockdata.getBlock().a(TagsBlock.WALLS);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  84 */     if (((Boolean)iblockdata.get(OPEN)).booleanValue()) {
/*  85 */       iblockdata = iblockdata.set(OPEN, Boolean.valueOf(false));
/*  86 */       world.setTypeAndData(blockposition, iblockdata, 10);
/*     */     } else {
/*  88 */       EnumDirection enumdirection = entityhuman.getDirection();
/*     */       
/*  90 */       if (iblockdata.get(FACING) == enumdirection.opposite()) {
/*  91 */         iblockdata = iblockdata.set(FACING, enumdirection);
/*     */       }
/*     */       
/*  94 */       iblockdata = iblockdata.set(OPEN, Boolean.valueOf(true));
/*  95 */       world.setTypeAndData(blockposition, iblockdata, 10);
/*     */     } 
/*     */     
/*  98 */     world.a(entityhuman, ((Boolean)iblockdata.get(OPEN)).booleanValue() ? 1008 : 1014, blockposition, 0);
/*  99 */     return EnumInteractionResult.a(world.isClientSide);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 104 */     if (!world.isClientSide) {
/* 105 */       boolean flag1 = world.isBlockIndirectlyPowered(blockposition);
/*     */       
/* 107 */       boolean oldPowered = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/* 108 */       if (oldPowered != flag1) {
/* 109 */         int newPower = flag1 ? 15 : 0;
/* 110 */         int oldPower = oldPowered ? 15 : 0;
/* 111 */         CraftBlock craftBlock = CraftBlock.at(world, blockposition);
/* 112 */         BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent((Block)craftBlock, oldPower, newPower);
/* 113 */         world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/* 114 */         flag1 = (eventRedstone.getNewCurrent() > 0);
/*     */       } 
/*     */ 
/*     */       
/* 118 */       if (((Boolean)iblockdata.get(POWERED)).booleanValue() != flag1) {
/* 119 */         world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(flag1)).set(OPEN, Boolean.valueOf(flag1)), 2);
/* 120 */         if (((Boolean)iblockdata.get(OPEN)).booleanValue() != flag1) {
/* 121 */           world.a((EntityHuman)null, flag1 ? 1008 : 1014, blockposition, 0);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 130 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, OPEN, POWERED, IN_WALL });
/*     */   }
/*     */   
/*     */   public static boolean a(IBlockData iblockdata, EnumDirection enumdirection) {
/* 134 */     return (((EnumDirection)iblockdata.get(FACING)).n() == enumdirection.g().n());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */