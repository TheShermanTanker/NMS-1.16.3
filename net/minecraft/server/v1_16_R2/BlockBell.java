/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import io.papermc.paper.event.block.BellRingEvent;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ 
/*     */ public class BlockBell
/*     */   extends BlockTileEntity {
/*   9 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*  10 */   public static final BlockStateEnum<BlockPropertyBellAttach> b = BlockProperties.R;
/*  11 */   public static final BlockStateBoolean c = BlockProperties.w;
/*  12 */   private static final VoxelShape d = Block.a(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
/*  13 */   private static final VoxelShape e = Block.a(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
/*  14 */   private static final VoxelShape f = Block.a(5.0D, 6.0D, 5.0D, 11.0D, 13.0D, 11.0D);
/*  15 */   private static final VoxelShape g = Block.a(4.0D, 4.0D, 4.0D, 12.0D, 6.0D, 12.0D);
/*  16 */   private static final VoxelShape h = VoxelShapes.a(g, f);
/*  17 */   private static final VoxelShape i = VoxelShapes.a(h, Block.a(7.0D, 13.0D, 0.0D, 9.0D, 15.0D, 16.0D));
/*  18 */   private static final VoxelShape j = VoxelShapes.a(h, Block.a(0.0D, 13.0D, 7.0D, 16.0D, 15.0D, 9.0D));
/*  19 */   private static final VoxelShape k = VoxelShapes.a(h, Block.a(0.0D, 13.0D, 7.0D, 13.0D, 15.0D, 9.0D));
/*  20 */   private static final VoxelShape o = VoxelShapes.a(h, Block.a(3.0D, 13.0D, 7.0D, 16.0D, 15.0D, 9.0D));
/*  21 */   private static final VoxelShape p = VoxelShapes.a(h, Block.a(7.0D, 13.0D, 0.0D, 9.0D, 15.0D, 13.0D));
/*  22 */   private static final VoxelShape q = VoxelShapes.a(h, Block.a(7.0D, 13.0D, 3.0D, 9.0D, 15.0D, 16.0D));
/*  23 */   private static final VoxelShape r = VoxelShapes.a(h, Block.a(7.0D, 13.0D, 7.0D, 9.0D, 16.0D, 9.0D));
/*     */   
/*     */   public BlockBell(BlockBase.Info blockbase_info) {
/*  26 */     super(blockbase_info);
/*  27 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH).set(b, BlockPropertyBellAttach.FLOOR).set(c, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  32 */     boolean flag1 = world.isBlockIndirectlyPowered(blockposition);
/*     */     
/*  34 */     if (flag1 != ((Boolean)iblockdata.get(c)).booleanValue()) {
/*  35 */       if (flag1) {
/*  36 */         a(world, blockposition, (EnumDirection)null);
/*     */       }
/*     */       
/*  39 */       world.setTypeAndData(blockposition, iblockdata.set(c, Boolean.valueOf(flag1)), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, IBlockData iblockdata, MovingObjectPositionBlock movingobjectpositionblock, IProjectile iprojectile) {
/*  46 */     Entity entity = iprojectile.getShooter();
/*  47 */     EntityHuman entityhuman = (entity instanceof EntityHuman) ? (EntityHuman)entity : null;
/*     */     
/*  49 */     a(world, iblockdata, movingobjectpositionblock, entityhuman, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  54 */     return a(world, iblockdata, movingobjectpositionblock, entityhuman, true) ? EnumInteractionResult.a(world.isClientSide) : EnumInteractionResult.PASS;
/*     */   }
/*     */   
/*     */   public boolean a(World world, IBlockData iblockdata, MovingObjectPositionBlock movingobjectpositionblock, @Nullable EntityHuman entityhuman, boolean flag) {
/*  58 */     EnumDirection enumdirection = movingobjectpositionblock.getDirection();
/*  59 */     BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
/*  60 */     boolean flag1 = (!flag || a(iblockdata, enumdirection, (movingobjectpositionblock.getPos()).y - blockposition.getY()));
/*     */     
/*  62 */     if (flag1) {
/*  63 */       boolean flag2 = handleBellRing(world, blockposition, enumdirection, entityhuman);
/*     */       
/*  65 */       if (flag2 && entityhuman != null) {
/*  66 */         entityhuman.a(StatisticList.BELL_RING);
/*     */       }
/*     */       
/*  69 */       return true;
/*     */     } 
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(IBlockData iblockdata, EnumDirection enumdirection, double d0) {
/*  76 */     if (enumdirection.n() != EnumDirection.EnumAxis.Y && d0 <= 0.8123999834060669D) {
/*  77 */       EnumDirection enumdirection1 = (EnumDirection)iblockdata.get(a);
/*  78 */       BlockPropertyBellAttach blockpropertybellattach = (BlockPropertyBellAttach)iblockdata.get(b);
/*     */       
/*  80 */       switch (blockpropertybellattach) {
/*     */         case FLOOR:
/*  82 */           return (enumdirection1.n() == enumdirection.n());
/*     */         case SINGLE_WALL:
/*     */         case DOUBLE_WALL:
/*  85 */           return (enumdirection1.n() != enumdirection.n());
/*     */         case CEILING:
/*  87 */           return true;
/*     */       } 
/*  89 */       return false;
/*     */     } 
/*     */     
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, @Nullable EnumDirection enumdirection) {
/*  98 */     return handleBellRing(world, blockposition, enumdirection, (Entity)null);
/*     */   }
/*     */   
/*     */   public boolean handleBellRing(World world, BlockPosition blockposition, @Nullable EnumDirection enumdirection, @Nullable Entity ringer) {
/* 102 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 104 */     if (!world.isClientSide && tileentity instanceof TileEntityBell) {
/* 105 */       if (enumdirection == null) {
/* 106 */         enumdirection = (EnumDirection)world.getType(blockposition).get(a);
/*     */       }
/*     */       
/* 109 */       if (!(new BellRingEvent(world.getWorld().getBlockAt(MCUtil.toLocation(world, blockposition)), (ringer == null) ? null : (Entity)ringer.getBukkitEntity())).callEvent()) return false; 
/* 110 */       ((TileEntityBell)tileentity).a(enumdirection);
/* 111 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_BELL_USE, SoundCategory.BLOCKS, 2.0F, 1.0F);
/* 112 */       return true;
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private VoxelShape h(IBlockData iblockdata) {
/* 119 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(a);
/* 120 */     BlockPropertyBellAttach blockpropertybellattach = (BlockPropertyBellAttach)iblockdata.get(b);
/*     */     
/* 122 */     return (blockpropertybellattach == BlockPropertyBellAttach.FLOOR) ? ((enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH) ? e : d) : ((blockpropertybellattach == BlockPropertyBellAttach.CEILING) ? r : ((blockpropertybellattach == BlockPropertyBellAttach.DOUBLE_WALL) ? ((enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH) ? j : i) : ((enumdirection == EnumDirection.NORTH) ? p : ((enumdirection == EnumDirection.SOUTH) ? q : ((enumdirection == EnumDirection.EAST) ? o : k)))));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 127 */     return h(iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 132 */     return h(iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/* 137 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 143 */     EnumDirection enumdirection = blockactioncontext.getClickedFace();
/* 144 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/* 145 */     World world = blockactioncontext.getWorld();
/* 146 */     EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.n();
/*     */ 
/*     */     
/* 149 */     if (enumdirection_enumaxis == EnumDirection.EnumAxis.Y) {
/* 150 */       IBlockData iblockdata = getBlockData().set(b, (enumdirection == EnumDirection.DOWN) ? BlockPropertyBellAttach.CEILING : BlockPropertyBellAttach.FLOOR).set(a, blockactioncontext.f());
/* 151 */       if (iblockdata.canPlace(blockactioncontext.getWorld(), blockposition)) {
/* 152 */         return iblockdata;
/*     */       }
/*     */     } else {
/* 155 */       boolean flag = ((enumdirection_enumaxis == EnumDirection.EnumAxis.X && world.getType(blockposition.west()).d(world, blockposition.west(), EnumDirection.EAST) && world.getType(blockposition.east()).d(world, blockposition.east(), EnumDirection.WEST)) || (enumdirection_enumaxis == EnumDirection.EnumAxis.Z && world.getType(blockposition.north()).d(world, blockposition.north(), EnumDirection.SOUTH) && world.getType(blockposition.south()).d(world, blockposition.south(), EnumDirection.NORTH)));
/*     */       
/* 157 */       IBlockData iblockdata = getBlockData().set(a, enumdirection.opposite()).set(b, flag ? BlockPropertyBellAttach.DOUBLE_WALL : BlockPropertyBellAttach.SINGLE_WALL);
/* 158 */       if (iblockdata.canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition())) {
/* 159 */         return iblockdata;
/*     */       }
/*     */       
/* 162 */       boolean flag1 = world.getType(blockposition.down()).d(world, blockposition.down(), EnumDirection.UP);
/*     */       
/* 164 */       iblockdata = iblockdata.set(b, flag1 ? BlockPropertyBellAttach.FLOOR : BlockPropertyBellAttach.CEILING);
/* 165 */       if (iblockdata.canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition())) {
/* 166 */         return iblockdata;
/*     */       }
/*     */     } 
/*     */     
/* 170 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 175 */     BlockPropertyBellAttach blockpropertybellattach = (BlockPropertyBellAttach)iblockdata.get(b);
/* 176 */     EnumDirection enumdirection1 = l(iblockdata).opposite();
/*     */     
/* 178 */     if (enumdirection1 == enumdirection && !iblockdata.canPlace(generatoraccess, blockposition) && blockpropertybellattach != BlockPropertyBellAttach.DOUBLE_WALL) {
/* 179 */       return Blocks.AIR.getBlockData();
/*     */     }
/* 181 */     if (enumdirection.n() == ((EnumDirection)iblockdata.get(a)).n()) {
/* 182 */       if (blockpropertybellattach == BlockPropertyBellAttach.DOUBLE_WALL && !iblockdata1.d(generatoraccess, blockposition1, enumdirection)) {
/* 183 */         return iblockdata.set(b, BlockPropertyBellAttach.SINGLE_WALL).set(a, enumdirection.opposite());
/*     */       }
/*     */       
/* 186 */       if (blockpropertybellattach == BlockPropertyBellAttach.SINGLE_WALL && enumdirection1.opposite() == enumdirection && iblockdata1.d(generatoraccess, blockposition1, (EnumDirection)iblockdata.get(a))) {
/* 187 */         return iblockdata.set(b, BlockPropertyBellAttach.DOUBLE_WALL);
/*     */       }
/*     */     } 
/*     */     
/* 191 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 197 */     EnumDirection enumdirection = l(iblockdata).opposite();
/*     */     
/* 199 */     return (enumdirection == EnumDirection.UP) ? Block.a(iworldreader, blockposition.up(), EnumDirection.DOWN) : BlockAttachable.b(iworldreader, blockposition, enumdirection);
/*     */   }
/*     */   
/*     */   private static EnumDirection l(IBlockData iblockdata) {
/* 203 */     switch ((BlockPropertyBellAttach)iblockdata.get(b)) {
/*     */       case FLOOR:
/* 205 */         return EnumDirection.UP;
/*     */       case CEILING:
/* 207 */         return EnumDirection.DOWN;
/*     */     } 
/* 209 */     return ((EnumDirection)iblockdata.get(a)).opposite();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
/* 215 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 220 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a, b, c });
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 226 */     return new TileEntityBell();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 231 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */