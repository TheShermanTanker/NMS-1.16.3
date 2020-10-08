/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public abstract class BlockMinecartTrackAbstract
/*     */   extends Block {
/*   5 */   protected static final VoxelShape a = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
/*   6 */   protected static final VoxelShape b = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
/*     */   private final boolean c;
/*     */   
/*     */   public static boolean a(World world, BlockPosition blockposition) {
/*  10 */     return g(world.getType(blockposition));
/*     */   }
/*     */   
/*     */   public static boolean g(IBlockData iblockdata) {
/*  14 */     return (iblockdata.a(TagsBlock.RAILS) && iblockdata.getBlock() instanceof BlockMinecartTrackAbstract);
/*     */   }
/*     */   
/*     */   protected BlockMinecartTrackAbstract(boolean flag, BlockBase.Info blockbase_info) {
/*  18 */     super(blockbase_info);
/*  19 */     this.c = flag;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  23 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  28 */     BlockPropertyTrackPosition blockpropertytrackposition = iblockdata.a(this) ? (BlockPropertyTrackPosition)iblockdata.get(d()) : null;
/*     */     
/*  30 */     return (blockpropertytrackposition != null && blockpropertytrackposition.c()) ? b : a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  35 */     return c(iworldreader, blockposition.down());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  40 */     if (!iblockdata1.a(iblockdata.getBlock())) {
/*  41 */       a(iblockdata, world, blockposition, flag);
/*     */     }
/*     */   }
/*     */   
/*     */   protected IBlockData a(IBlockData iblockdata, World world, BlockPosition blockposition, boolean flag) {
/*  46 */     iblockdata = a(world, blockposition, iblockdata, true);
/*  47 */     if (this.c) {
/*  48 */       iblockdata.doPhysics(world, blockposition, this, blockposition, flag);
/*  49 */       iblockdata = world.getType(blockposition);
/*     */     } 
/*     */     
/*  52 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  57 */     if (!world.isClientSide && world.getType(blockposition).a(this)) {
/*  58 */       BlockPropertyTrackPosition blockpropertytrackposition = (BlockPropertyTrackPosition)iblockdata.get(d());
/*     */       
/*  60 */       if (a(blockposition, world, blockpropertytrackposition)) {
/*  61 */         c(iblockdata, world, blockposition);
/*  62 */         world.a(blockposition, flag);
/*     */       } else {
/*  64 */         a(iblockdata, world, blockposition, block);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(BlockPosition blockposition, World world, BlockPropertyTrackPosition blockpropertytrackposition) {
/*  71 */     if (!c(world, blockposition.down())) {
/*  72 */       return true;
/*     */     }
/*  74 */     switch (blockpropertytrackposition) {
/*     */       case ASCENDING_EAST:
/*  76 */         return !c(world, blockposition.east());
/*     */       case ASCENDING_WEST:
/*  78 */         return !c(world, blockposition.west());
/*     */       case ASCENDING_NORTH:
/*  80 */         return !c(world, blockposition.north());
/*     */       case ASCENDING_SOUTH:
/*  82 */         return !c(world, blockposition.south());
/*     */     } 
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block) {}
/*     */ 
/*     */   
/*     */   protected IBlockData a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/*  92 */     if (world.isClientSide) {
/*  93 */       return iblockdata;
/*     */     }
/*  95 */     BlockPropertyTrackPosition blockpropertytrackposition = (BlockPropertyTrackPosition)iblockdata.get(d());
/*     */     
/*  97 */     return (new MinecartTrackLogic(world, blockposition, iblockdata)).a(world.isBlockIndirectlyPowered(blockposition), flag, blockpropertytrackposition).c();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
/* 103 */     return EnumPistonReaction.NORMAL;
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 108 */     if (!flag) {
/* 109 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/* 110 */       if (((BlockPropertyTrackPosition)iblockdata.get(d())).c()) {
/* 111 */         world.applyPhysics(blockposition.up(), this);
/*     */       }
/*     */       
/* 114 */       if (this.c) {
/* 115 */         world.applyPhysics(blockposition, this);
/* 116 */         world.applyPhysics(blockposition.down(), this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 124 */     IBlockData iblockdata = getBlockData();
/* 125 */     EnumDirection enumdirection = blockactioncontext.f();
/* 126 */     boolean flag = (enumdirection == EnumDirection.EAST || enumdirection == EnumDirection.WEST);
/*     */     
/* 128 */     return iblockdata.set(d(), flag ? BlockPropertyTrackPosition.EAST_WEST : BlockPropertyTrackPosition.NORTH_SOUTH);
/*     */   }
/*     */   
/*     */   public abstract IBlockState<BlockPropertyTrackPosition> d();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockMinecartTrackAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */