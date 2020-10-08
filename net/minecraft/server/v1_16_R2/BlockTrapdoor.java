/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockTrapdoor extends BlockFacingHorizontal implements IBlockWaterlogged {
/*   8 */   public static final BlockStateBoolean OPEN = BlockProperties.u;
/*   9 */   public static final BlockStateEnum<BlockPropertyHalf> HALF = BlockProperties.ab;
/*  10 */   public static final BlockStateBoolean c = BlockProperties.w;
/*  11 */   public static final BlockStateBoolean d = BlockProperties.C;
/*  12 */   protected static final VoxelShape e = Block.a(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
/*  13 */   protected static final VoxelShape f = Block.a(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  14 */   protected static final VoxelShape g = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
/*  15 */   protected static final VoxelShape h = Block.a(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
/*  16 */   protected static final VoxelShape i = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
/*  17 */   protected static final VoxelShape j = Block.a(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*     */   
/*     */   protected BlockTrapdoor(BlockBase.Info blockbase_info) {
/*  20 */     super(blockbase_info);
/*  21 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(OPEN, Boolean.valueOf(false)).set(HALF, BlockPropertyHalf.BOTTOM).set(c, Boolean.valueOf(false)).set(d, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  26 */     if (!((Boolean)iblockdata.get(OPEN)).booleanValue()) {
/*  27 */       return (iblockdata.get(HALF) == BlockPropertyHalf.TOP) ? j : i;
/*     */     }
/*  29 */     switch ((EnumDirection)iblockdata.get(FACING))
/*     */     
/*     */     { default:
/*  32 */         return h;
/*     */       case WATER:
/*  34 */         return g;
/*     */       case AIR:
/*  36 */         return f;
/*     */       case null:
/*  38 */         break; }  return e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/*  45 */     switch (pathmode) {
/*     */       case LAND:
/*  47 */         return ((Boolean)iblockdata.get(OPEN)).booleanValue();
/*     */       case WATER:
/*  49 */         return ((Boolean)iblockdata.get(d)).booleanValue();
/*     */       case AIR:
/*  51 */         return ((Boolean)iblockdata.get(OPEN)).booleanValue();
/*     */     } 
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  59 */     if (this.material == Material.ORE) {
/*  60 */       return EnumInteractionResult.PASS;
/*     */     }
/*  62 */     iblockdata = iblockdata.a(OPEN);
/*  63 */     world.setTypeAndData(blockposition, iblockdata, 2);
/*  64 */     if (((Boolean)iblockdata.get(d)).booleanValue()) {
/*  65 */       world.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(world));
/*     */     }
/*     */     
/*  68 */     a(entityhuman, world, blockposition, ((Boolean)iblockdata.get(OPEN)).booleanValue());
/*  69 */     return EnumInteractionResult.a(world.isClientSide);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(@Nullable EntityHuman entityhuman, World world, BlockPosition blockposition, boolean flag) {
/*  76 */     if (flag) {
/*  77 */       int i = (this.material == Material.ORE) ? 1037 : 1007;
/*  78 */       world.a(entityhuman, i, blockposition, 0);
/*     */     } else {
/*  80 */       int i = (this.material == Material.ORE) ? 1036 : 1013;
/*  81 */       world.a(entityhuman, i, blockposition, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  88 */     if (!world.isClientSide) {
/*  89 */       boolean flag1 = world.isBlockIndirectlyPowered(blockposition);
/*     */       
/*  91 */       if (flag1 != ((Boolean)iblockdata.get(c)).booleanValue()) {
/*     */         
/*  93 */         CraftWorld craftWorld = world.getWorld();
/*  94 */         Block bblock = craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */         
/*  96 */         int power = bblock.getBlockPower();
/*  97 */         int oldPower = ((Boolean)iblockdata.get(OPEN)).booleanValue() ? 15 : 0;
/*     */         
/*  99 */         if ((((oldPower == 0) ? 1 : 0) ^ ((power == 0) ? 1 : 0)) != 0 || block.getBlockData().isPowerSource()) {
/* 100 */           BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bblock, oldPower, power);
/* 101 */           world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/* 102 */           flag1 = (eventRedstone.getNewCurrent() > 0);
/*     */         } 
/*     */         
/* 105 */         if (((Boolean)iblockdata.get(OPEN)).booleanValue() != flag1) {
/* 106 */           iblockdata = iblockdata.set(OPEN, Boolean.valueOf(flag1));
/* 107 */           a((EntityHuman)null, world, blockposition, flag1);
/*     */         } 
/*     */         
/* 110 */         world.setTypeAndData(blockposition, iblockdata.set(c, Boolean.valueOf(flag1)), 2);
/* 111 */         if (((Boolean)iblockdata.get(d)).booleanValue()) {
/* 112 */           world.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(world));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 121 */     IBlockData iblockdata = getBlockData();
/* 122 */     Fluid fluid = blockactioncontext.getWorld().getFluid(blockactioncontext.getClickPosition());
/* 123 */     EnumDirection enumdirection = blockactioncontext.getClickedFace();
/*     */     
/* 125 */     if (!blockactioncontext.c() && enumdirection.n().d()) {
/* 126 */       iblockdata = iblockdata.set(FACING, enumdirection).set(HALF, ((blockactioncontext.getPos()).y - blockactioncontext.getClickPosition().getY() > 0.5D) ? BlockPropertyHalf.TOP : BlockPropertyHalf.BOTTOM);
/*     */     } else {
/* 128 */       iblockdata = iblockdata.set(FACING, blockactioncontext.f().opposite()).set(HALF, (enumdirection == EnumDirection.UP) ? BlockPropertyHalf.BOTTOM : BlockPropertyHalf.TOP);
/*     */     } 
/*     */     
/* 131 */     if (blockactioncontext.getWorld().isBlockIndirectlyPowered(blockactioncontext.getClickPosition())) {
/* 132 */       iblockdata = iblockdata.set(OPEN, Boolean.valueOf(true)).set(c, Boolean.valueOf(true));
/*     */     }
/*     */     
/* 135 */     return iblockdata.set(d, Boolean.valueOf((fluid.getType() == FluidTypes.WATER)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 140 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, OPEN, HALF, c, d });
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData iblockdata) {
/* 145 */     return ((Boolean)iblockdata.get(d)).booleanValue() ? FluidTypes.WATER.a(false) : super.d(iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 150 */     if (((Boolean)iblockdata.get(d)).booleanValue()) {
/* 151 */       generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(generatoraccess));
/*     */     }
/*     */     
/* 154 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTrapdoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */