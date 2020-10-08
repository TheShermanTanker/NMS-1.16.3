/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public abstract class BlockDiodeAbstract
/*     */   extends BlockFacingHorizontal
/*     */ {
/*   9 */   protected static final VoxelShape b = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
/*  10 */   public static final BlockStateBoolean c = BlockProperties.w;
/*     */   
/*     */   protected BlockDiodeAbstract(BlockBase.Info blockbase_info) {
/*  13 */     super(blockbase_info);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  18 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  23 */     return c(iworldreader, blockposition.down());
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  28 */     if (!a(worldserver, blockposition, iblockdata)) {
/*  29 */       boolean flag = ((Boolean)iblockdata.get(c)).booleanValue();
/*  30 */       boolean flag1 = a(worldserver, blockposition, iblockdata);
/*     */       
/*  32 */       if (flag && !flag1) {
/*     */         
/*  34 */         if (CraftEventFactory.callRedstoneChange(worldserver, blockposition, 15, 0).getNewCurrent() != 0) {
/*     */           return;
/*     */         }
/*     */         
/*  38 */         worldserver.setTypeAndData(blockposition, iblockdata.set(c, Boolean.valueOf(false)), 2);
/*  39 */       } else if (!flag) {
/*     */         
/*  41 */         if (CraftEventFactory.callRedstoneChange(worldserver, blockposition, 0, 15).getNewCurrent() != 15) {
/*     */           return;
/*     */         }
/*     */         
/*  45 */         worldserver.setTypeAndData(blockposition, iblockdata.set(c, Boolean.valueOf(true)), 2);
/*  46 */         if (!flag1) {
/*  47 */           worldserver.getBlockTickList().a(blockposition, this, g(iblockdata), TickListPriority.VERY_HIGH);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  56 */     return iblockdata.b(iblockaccess, blockposition, enumdirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  61 */     return !((Boolean)iblockdata.get(c)).booleanValue() ? 0 : ((iblockdata.get(FACING) == enumdirection) ? b(iblockaccess, blockposition, iblockdata) : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  66 */     if (iblockdata.canPlace(world, blockposition)) {
/*  67 */       c(world, blockposition, iblockdata);
/*     */     } else {
/*  69 */       TileEntity tileentity = isTileEntity() ? world.getTileEntity(blockposition) : null;
/*     */       
/*  71 */       a(iblockdata, world, blockposition, tileentity);
/*  72 */       world.a(blockposition, false);
/*  73 */       EnumDirection[] aenumdirection = EnumDirection.values();
/*  74 */       int i = aenumdirection.length;
/*     */       
/*  76 */       for (int j = 0; j < i; j++) {
/*  77 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/*  79 */         world.applyPhysics(blockposition.shift(enumdirection), this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  86 */     if (!a(world, blockposition, iblockdata)) {
/*  87 */       boolean flag = ((Boolean)iblockdata.get(c)).booleanValue();
/*  88 */       boolean flag1 = a(world, blockposition, iblockdata);
/*     */       
/*  90 */       if (flag != flag1 && !world.getBlockTickList().b(blockposition, this)) {
/*  91 */         TickListPriority ticklistpriority = TickListPriority.HIGH;
/*     */         
/*  93 */         if (c(world, blockposition, iblockdata)) {
/*  94 */           ticklistpriority = TickListPriority.EXTREMELY_HIGH;
/*  95 */         } else if (flag) {
/*  96 */           ticklistpriority = TickListPriority.VERY_HIGH;
/*     */         } 
/*     */         
/*  99 */         world.getBlockTickList().a(blockposition, this, g(iblockdata), ticklistpriority);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader iworldreader, BlockPosition blockposition, IBlockData iblockdata) {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 110 */     return (b(world, blockposition, iblockdata) > 0);
/*     */   }
/*     */   
/*     */   protected int b(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 114 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/* 115 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 116 */     int i = world.getBlockFacePower(blockposition1, enumdirection);
/*     */     
/* 118 */     if (i >= 15) {
/* 119 */       return i;
/*     */     }
/* 121 */     IBlockData iblockdata1 = world.getType(blockposition1);
/*     */     
/* 123 */     return Math.max(i, iblockdata1.a(Blocks.REDSTONE_WIRE) ? ((Integer)iblockdata1.get(BlockRedstoneWire.POWER)).intValue() : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int b(IWorldReader iworldreader, BlockPosition blockposition, IBlockData iblockdata) {
/* 128 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/* 129 */     EnumDirection enumdirection1 = enumdirection.g();
/* 130 */     EnumDirection enumdirection2 = enumdirection.h();
/*     */     
/* 132 */     return Math.max(b(iworldreader, blockposition.shift(enumdirection1), enumdirection1), b(iworldreader, blockposition.shift(enumdirection2), enumdirection2));
/*     */   }
/*     */   
/*     */   protected int b(IWorldReader iworldreader, BlockPosition blockposition, EnumDirection enumdirection) {
/* 136 */     IBlockData iblockdata = iworldreader.getType(blockposition);
/*     */     
/* 138 */     return h(iblockdata) ? (iblockdata.a(Blocks.REDSTONE_BLOCK) ? 15 : (iblockdata.a(Blocks.REDSTONE_WIRE) ? ((Integer)iblockdata.get(BlockRedstoneWire.POWER)).intValue() : iworldreader.c(blockposition, enumdirection))) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 148 */     return getBlockData().set(FACING, blockactioncontext.f().opposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 153 */     if (a(world, blockposition, iblockdata)) {
/* 154 */       world.getBlockTickList().a(blockposition, this, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 161 */     d(world, blockposition, iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 166 */     if (!flag && !iblockdata.a(iblockdata1.getBlock())) {
/* 167 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/* 168 */       d(world, blockposition, iblockdata);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void d(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 173 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/* 174 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
/*     */     
/* 176 */     world.a(blockposition1, this, blockposition);
/* 177 */     world.a(blockposition1, this, enumdirection);
/*     */   }
/*     */   
/*     */   protected boolean h(IBlockData iblockdata) {
/* 181 */     return iblockdata.isPowerSource();
/*     */   }
/*     */   
/*     */   protected int b(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/* 185 */     return 15;
/*     */   }
/*     */   
/*     */   public static boolean isDiode(IBlockData iblockdata) {
/* 189 */     return iblockdata.getBlock() instanceof BlockDiodeAbstract;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/* 193 */     EnumDirection enumdirection = ((EnumDirection)iblockdata.get(FACING)).opposite();
/* 194 */     IBlockData iblockdata1 = iblockaccess.getType(blockposition.shift(enumdirection));
/*     */     
/* 196 */     return (isDiode(iblockdata1) && iblockdata1.get(FACING) != enumdirection);
/*     */   }
/*     */   
/*     */   protected abstract int g(IBlockData paramIBlockData);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDiodeAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */