/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockDispenser
/*     */   extends BlockTileEntity {
/*   9 */   public static final BlockStateDirection FACING = BlockDirectional.FACING;
/*  10 */   public static final BlockStateBoolean TRIGGERED = BlockProperties.A; static {
/*  11 */     REGISTRY = (Map<Item, IDispenseBehavior>)SystemUtils.a(new Object2ObjectOpenHashMap(), object2objectopenhashmap -> object2objectopenhashmap.defaultReturnValue(new DispenseBehaviorItem()));
/*     */   }
/*     */   public static final Map<Item, IDispenseBehavior> REGISTRY;
/*     */   public static boolean eventFired = false;
/*     */   
/*     */   public static void a(IMaterial imaterial, IDispenseBehavior idispensebehavior) {
/*  17 */     REGISTRY.put(imaterial.getItem(), idispensebehavior);
/*     */   }
/*     */   
/*     */   protected BlockDispenser(BlockBase.Info blockbase_info) {
/*  21 */     super(blockbase_info);
/*  22 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(TRIGGERED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  27 */     if (world.isClientSide) {
/*  28 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*  30 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  32 */     if (tileentity instanceof TileEntityDispenser) {
/*  33 */       entityhuman.openContainer((TileEntityDispenser)tileentity);
/*  34 */       if (tileentity instanceof TileEntityDropper) {
/*  35 */         entityhuman.a(StatisticList.INSPECT_DROPPER);
/*     */       } else {
/*  37 */         entityhuman.a(StatisticList.INSPECT_DISPENSER);
/*     */       } 
/*     */     } 
/*     */     
/*  41 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispense(WorldServer worldserver, BlockPosition blockposition) {
/*  46 */     SourceBlock sourceblock = new SourceBlock(worldserver, blockposition);
/*  47 */     TileEntityDispenser tileentitydispenser = sourceblock.<TileEntityDispenser>getTileEntity();
/*  48 */     int i = tileentitydispenser.h();
/*     */     
/*  50 */     if (i < 0) {
/*  51 */       worldserver.triggerEffect(1001, blockposition, 0);
/*     */     } else {
/*  53 */       ItemStack itemstack = tileentitydispenser.getItem(i);
/*  54 */       IDispenseBehavior idispensebehavior = a(itemstack);
/*     */       
/*  56 */       if (idispensebehavior != IDispenseBehavior.NONE) {
/*  57 */         eventFired = false;
/*  58 */         tileentitydispenser.setItem(i, idispensebehavior.dispense(sourceblock, itemstack));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected IDispenseBehavior a(ItemStack itemstack) {
/*  65 */     return REGISTRY.get(itemstack.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  70 */     boolean flag1 = (world.isBlockIndirectlyPowered(blockposition) || world.isBlockIndirectlyPowered(blockposition.up()));
/*  71 */     boolean flag2 = ((Boolean)iblockdata.get(TRIGGERED)).booleanValue();
/*     */     
/*  73 */     if (flag1 && !flag2) {
/*  74 */       world.getBlockTickList().a(blockposition, this, 4);
/*  75 */       world.setTypeAndData(blockposition, iblockdata.set(TRIGGERED, Boolean.valueOf(true)), 4);
/*  76 */     } else if (!flag1 && flag2) {
/*  77 */       world.setTypeAndData(blockposition, iblockdata.set(TRIGGERED, Boolean.valueOf(false)), 4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  84 */     dispense(worldserver, blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/*  89 */     return new TileEntityDispenser();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  94 */     return getBlockData().set(FACING, blockactioncontext.d().opposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/*  99 */     if (itemstack.hasName()) {
/* 100 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 102 */       if (tileentity instanceof TileEntityDispenser) {
/* 103 */         ((TileEntityDispenser)tileentity).setCustomName(itemstack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 111 */     if (!iblockdata.a(iblockdata1.getBlock())) {
/* 112 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 114 */       if (tileentity instanceof TileEntityDispenser) {
/* 115 */         InventoryUtils.dropInventory(world, blockposition, (TileEntityDispenser)tileentity);
/* 116 */         world.updateAdjacentComparators(blockposition, this);
/*     */       } 
/*     */       
/* 119 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static IPosition a(ISourceBlock isourceblock) {
/* 124 */     EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(FACING);
/* 125 */     double d0 = isourceblock.getX() + 0.7D * enumdirection.getAdjacentX();
/* 126 */     double d1 = isourceblock.getY() + 0.7D * enumdirection.getAdjacentY();
/* 127 */     double d2 = isourceblock.getZ() + 0.7D * enumdirection.getAdjacentZ();
/*     */     
/* 129 */     return new Position(d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 134 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 139 */     return Container.a(world.getTileEntity(blockposition));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/* 144 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 149 */     return iblockdata.set(FACING, enumblockrotation.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 154 */     return iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 159 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, TRIGGERED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */