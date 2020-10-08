/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockRedstoneComparator
/*     */   extends BlockDiodeAbstract
/*     */   implements ITileEntity {
/*  11 */   public static final BlockStateEnum<BlockPropertyComparatorMode> MODE = BlockProperties.aG;
/*     */   
/*     */   public BlockRedstoneComparator(BlockBase.Info blockbase_info) {
/*  14 */     super(blockbase_info);
/*  15 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(c, Boolean.valueOf(false)).set(MODE, BlockPropertyComparatorMode.COMPARE));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int g(IBlockData iblockdata) {
/*  20 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int b(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/*  25 */     TileEntity tileentity = iblockaccess.getTileEntity(blockposition);
/*     */     
/*  27 */     return (tileentity instanceof TileEntityComparator) ? ((TileEntityComparator)tileentity).d() : 0;
/*     */   }
/*     */   
/*     */   private int e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  31 */     return (iblockdata.get(MODE) == BlockPropertyComparatorMode.SUBTRACT) ? Math.max(b(world, blockposition, iblockdata) - b(world, blockposition, iblockdata), 0) : b(world, blockposition, iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  36 */     int i = b(world, blockposition, iblockdata);
/*     */     
/*  38 */     if (i == 0) {
/*  39 */       return false;
/*     */     }
/*  41 */     int j = b(world, blockposition, iblockdata);
/*     */     
/*  43 */     return (i > j) ? true : ((i == j && iblockdata.get(MODE) == BlockPropertyComparatorMode.COMPARE));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int b(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  49 */     int i = super.b(world, blockposition, iblockdata);
/*  50 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*  51 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*  52 */     IBlockData iblockdata1 = world.getType(blockposition1);
/*     */     
/*  54 */     if (iblockdata1.isComplexRedstone()) {
/*  55 */       i = iblockdata1.a(world, blockposition1);
/*  56 */     } else if (i < 15 && iblockdata1.isOccluding(world, blockposition1)) {
/*  57 */       blockposition1 = blockposition1.shift(enumdirection);
/*  58 */       iblockdata1 = world.getType(blockposition1);
/*  59 */       EntityItemFrame entityitemframe = a(world, enumdirection, blockposition1);
/*  60 */       int j = Math.max((entityitemframe == null) ? Integer.MIN_VALUE : entityitemframe.q(), iblockdata1.isComplexRedstone() ? iblockdata1.a(world, blockposition1) : Integer.MIN_VALUE);
/*     */       
/*  62 */       if (j != Integer.MIN_VALUE) {
/*  63 */         i = j;
/*     */       }
/*     */     } 
/*     */     
/*  67 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private EntityItemFrame a(World world, EnumDirection enumdirection, BlockPosition blockposition) {
/*  73 */     List<EntityItemFrame> list = world.a(EntityItemFrame.class, new AxisAlignedBB(blockposition.getX(), blockposition.getY(), blockposition.getZ(), (blockposition.getX() + 1), (blockposition.getY() + 1), (blockposition.getZ() + 1)), entityitemframe -> 
/*  74 */         (entityitemframe != null && entityitemframe.getDirection() == enumdirection));
/*     */ 
/*     */     
/*  77 */     return (list.size() == 1) ? list.get(0) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  82 */     if (!entityhuman.abilities.mayBuild) {
/*  83 */       return EnumInteractionResult.PASS;
/*     */     }
/*  85 */     iblockdata = iblockdata.a(MODE);
/*  86 */     float f = (iblockdata.get(MODE) == BlockPropertyComparatorMode.SUBTRACT) ? 0.55F : 0.5F;
/*     */     
/*  88 */     world.playSound(entityhuman, blockposition, SoundEffects.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 0.3F, f);
/*  89 */     world.setTypeAndData(blockposition, iblockdata, 2);
/*  90 */     f(world, blockposition, iblockdata);
/*  91 */     return EnumInteractionResult.a(world.isClientSide);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  97 */     if (!world.getBlockTickList().b(blockposition, this)) {
/*  98 */       int i = e(world, blockposition, iblockdata);
/*  99 */       TileEntity tileentity = world.getTileEntity(blockposition);
/* 100 */       int j = (tileentity instanceof TileEntityComparator) ? ((TileEntityComparator)tileentity).d() : 0;
/*     */       
/* 102 */       if (i != j || ((Boolean)iblockdata.get(c)).booleanValue() != a(world, blockposition, iblockdata)) {
/* 103 */         TickListPriority ticklistpriority = c(world, blockposition, iblockdata) ? TickListPriority.HIGH : TickListPriority.NORMAL;
/*     */         
/* 105 */         world.getBlockTickList().a(blockposition, this, 2, ticklistpriority);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 112 */     int i = e(world, blockposition, iblockdata);
/* 113 */     TileEntity tileentity = world.getTileEntity(blockposition);
/* 114 */     int j = 0;
/*     */     
/* 116 */     if (tileentity instanceof TileEntityComparator) {
/* 117 */       TileEntityComparator tileentitycomparator = (TileEntityComparator)tileentity;
/*     */       
/* 119 */       j = tileentitycomparator.d();
/* 120 */       tileentitycomparator.a(i);
/*     */     } 
/*     */     
/* 123 */     if (j != i || iblockdata.get(MODE) == BlockPropertyComparatorMode.COMPARE) {
/* 124 */       boolean flag = a(world, blockposition, iblockdata);
/* 125 */       boolean flag1 = ((Boolean)iblockdata.get(c)).booleanValue();
/*     */       
/* 127 */       if (flag1 && !flag) {
/*     */         
/* 129 */         if (CraftEventFactory.callRedstoneChange(world, blockposition, 15, 0).getNewCurrent() != 0) {
/*     */           return;
/*     */         }
/*     */         
/* 133 */         world.setTypeAndData(blockposition, iblockdata.set(c, Boolean.valueOf(false)), 2);
/* 134 */       } else if (!flag1 && flag) {
/*     */         
/* 136 */         if (CraftEventFactory.callRedstoneChange(world, blockposition, 0, 15).getNewCurrent() != 15) {
/*     */           return;
/*     */         }
/*     */         
/* 140 */         world.setTypeAndData(blockposition, iblockdata.set(c, Boolean.valueOf(true)), 2);
/*     */       } 
/*     */       
/* 143 */       d(world, blockposition, iblockdata);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 150 */     f(worldserver, blockposition, iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, int i, int j) {
/* 155 */     super.a(iblockdata, world, blockposition, i, j);
/* 156 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 158 */     return (tileentity != null && tileentity.setProperty(i, j));
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 163 */     return new TileEntityComparator();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 168 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, MODE, c });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRedstoneComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */