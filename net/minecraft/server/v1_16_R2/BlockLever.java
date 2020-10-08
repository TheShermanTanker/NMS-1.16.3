/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockLever extends BlockAttachable {
/*   7 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*   8 */   protected static final VoxelShape b = Block.a(5.0D, 4.0D, 10.0D, 11.0D, 12.0D, 16.0D);
/*   9 */   protected static final VoxelShape c = Block.a(5.0D, 4.0D, 0.0D, 11.0D, 12.0D, 6.0D);
/*  10 */   protected static final VoxelShape d = Block.a(10.0D, 4.0D, 5.0D, 16.0D, 12.0D, 11.0D);
/*  11 */   protected static final VoxelShape e = Block.a(0.0D, 4.0D, 5.0D, 6.0D, 12.0D, 11.0D);
/*  12 */   protected static final VoxelShape f = Block.a(5.0D, 0.0D, 4.0D, 11.0D, 6.0D, 12.0D);
/*  13 */   protected static final VoxelShape g = Block.a(4.0D, 0.0D, 5.0D, 12.0D, 6.0D, 11.0D);
/*  14 */   protected static final VoxelShape h = Block.a(5.0D, 10.0D, 4.0D, 11.0D, 16.0D, 12.0D);
/*  15 */   protected static final VoxelShape i = Block.a(4.0D, 10.0D, 5.0D, 12.0D, 16.0D, 11.0D);
/*     */   
/*     */   protected BlockLever(BlockBase.Info blockbase_info) {
/*  18 */     super(blockbase_info);
/*  19 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(POWERED, Boolean.valueOf(false)).set(FACE, BlockPropertyAttachPosition.WALL));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  24 */     switch ((BlockPropertyAttachPosition)iblockdata.get(FACE)) {
/*     */       case FLOOR:
/*  26 */         switch (((EnumDirection)iblockdata.get(FACING)).n()) {
/*     */           case FLOOR:
/*  28 */             return g;
/*     */         } 
/*     */         
/*  31 */         return f;
/*     */       
/*     */       case WALL:
/*  34 */         switch ((EnumDirection)iblockdata.get(FACING)) {
/*     */           case FLOOR:
/*  36 */             return e;
/*     */           case WALL:
/*  38 */             return d;
/*     */           case CEILING:
/*  40 */             return c;
/*     */         } 
/*     */         
/*  43 */         return b;
/*     */     } 
/*     */ 
/*     */     
/*  47 */     switch (((EnumDirection)iblockdata.get(FACING)).n()) {
/*     */       case FLOOR:
/*  49 */         return i;
/*     */     } 
/*     */     
/*  52 */     return h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  61 */     if (world.isClientSide) {
/*  62 */       IBlockData iBlockData = iblockdata.a(POWERED);
/*  63 */       if (((Boolean)iBlockData.get(POWERED)).booleanValue()) {
/*  64 */         a(iBlockData, world, blockposition, 1.0F);
/*     */       }
/*     */       
/*  67 */       return EnumInteractionResult.SUCCESS;
/*     */     } 
/*     */     
/*  70 */     boolean powered = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/*  71 */     Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  72 */     int old = powered ? 15 : 0;
/*  73 */     int current = !powered ? 15 : 0;
/*     */     
/*  75 */     BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
/*  76 */     world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */     
/*  78 */     if (((eventRedstone.getNewCurrent() > 0) ? true : false) != (!powered ? true : false)) {
/*  79 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */ 
/*     */     
/*  83 */     IBlockData iblockdata1 = d(iblockdata, world, blockposition);
/*  84 */     float f = ((Boolean)iblockdata1.get(POWERED)).booleanValue() ? 0.6F : 0.5F;
/*     */     
/*  86 */     world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
/*  87 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData d(IBlockData iblockdata, World world, BlockPosition blockposition) {
/*  92 */     iblockdata = iblockdata.a(POWERED);
/*  93 */     world.setTypeAndData(blockposition, iblockdata, 3);
/*  94 */     e(iblockdata, world, blockposition);
/*  95 */     return iblockdata;
/*     */   }
/*     */   
/*     */   private static void a(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, float f) {
/*  99 */     EnumDirection enumdirection = ((EnumDirection)iblockdata.get(FACING)).opposite();
/* 100 */     EnumDirection enumdirection1 = h(iblockdata).opposite();
/* 101 */     double d0 = blockposition.getX() + 0.5D + 0.1D * enumdirection.getAdjacentX() + 0.2D * enumdirection1.getAdjacentX();
/* 102 */     double d1 = blockposition.getY() + 0.5D + 0.1D * enumdirection.getAdjacentY() + 0.2D * enumdirection1.getAdjacentY();
/* 103 */     double d2 = blockposition.getZ() + 0.5D + 0.1D * enumdirection.getAdjacentZ() + 0.2D * enumdirection1.getAdjacentZ();
/*     */     
/* 105 */     generatoraccess.addParticle(new ParticleParamRedstone(1.0F, 0.0F, 0.0F, f), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 110 */     if (!flag && !iblockdata.a(iblockdata1.getBlock())) {
/* 111 */       if (((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/* 112 */         e(iblockdata, world, blockposition);
/*     */       }
/*     */       
/* 115 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 121 */     return ((Boolean)iblockdata.get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 126 */     return (((Boolean)iblockdata.get(POWERED)).booleanValue() && h(iblockdata) == enumdirection) ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 131 */     return true;
/*     */   }
/*     */   
/*     */   private void e(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 135 */     world.applyPhysics(blockposition, this);
/* 136 */     world.applyPhysics(blockposition.shift(h(iblockdata).opposite()), this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 141 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACE, FACING, POWERED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockLever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */