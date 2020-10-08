/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockScaffolding extends Block implements IBlockWaterlogged {
/*     */   private static final VoxelShape d;
/*     */   private static final VoxelShape e;
/*  10 */   private static final VoxelShape f = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
/*  11 */   private static final VoxelShape g = VoxelShapes.b().a(0.0D, -1.0D, 0.0D);
/*  12 */   public static final BlockStateInteger a = BlockProperties.aB;
/*  13 */   public static final BlockStateBoolean b = BlockProperties.C;
/*  14 */   public static final BlockStateBoolean c = BlockProperties.b;
/*     */   
/*     */   protected BlockScaffolding(BlockBase.Info blockbase_info) {
/*  17 */     super(blockbase_info);
/*  18 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(7)).set(b, Boolean.valueOf(false)).set(c, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/*  23 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a, b, c });
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  28 */     return !voxelshapecollision.a(iblockdata.getBlock().getItem()) ? (((Boolean)iblockdata.get(c)).booleanValue() ? e : d) : VoxelShapes.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape a_(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  33 */     return VoxelShapes.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
/*  38 */     return (blockactioncontext.getItemStack().getItem() == getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  43 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*  44 */     World world = blockactioncontext.getWorld();
/*  45 */     int i = a(world, blockposition);
/*     */     
/*  47 */     return getBlockData().set(b, Boolean.valueOf((world.getFluid(blockposition).getType() == FluidTypes.WATER))).set(a, Integer.valueOf(i)).set(c, Boolean.valueOf(a(world, blockposition, i)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  52 */     if (!world.isClientSide) {
/*  53 */       world.getBlockTickList().a(blockposition, this, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  60 */     if (((Boolean)iblockdata.get(b)).booleanValue()) {
/*  61 */       generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(generatoraccess));
/*     */     }
/*     */     
/*  64 */     if (!generatoraccess.s_()) {
/*  65 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*     */     }
/*     */     
/*  68 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  73 */     int i = a(worldserver, blockposition);
/*  74 */     IBlockData iblockdata1 = iblockdata.set(a, Integer.valueOf(i)).set(c, Boolean.valueOf(a(worldserver, blockposition, i)));
/*     */     
/*  76 */     if (((Integer)iblockdata1.get(a)).intValue() == 7 && !CraftEventFactory.callBlockFadeEvent(worldserver, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/*  77 */       if (((Integer)iblockdata.get(a)).intValue() == 7) {
/*  78 */         worldserver.addEntity(new EntityFallingBlock(worldserver, blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D, iblockdata1.set(b, Boolean.valueOf(false))));
/*     */       } else {
/*  80 */         worldserver.b(blockposition, true);
/*     */       } 
/*  82 */     } else if (iblockdata != iblockdata1) {
/*  83 */       worldserver.setTypeAndData(blockposition, iblockdata1, 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  90 */     return (a(iworldreader, blockposition) < 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  95 */     return (voxelshapecollision.a(VoxelShapes.b(), blockposition, true) && !voxelshapecollision.b()) ? d : ((((Integer)iblockdata.get(a)).intValue() != 0 && ((Boolean)iblockdata.get(c)).booleanValue() && voxelshapecollision.a(g, blockposition, true)) ? f : VoxelShapes.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData iblockdata) {
/* 100 */     return ((Boolean)iblockdata.get(b)).booleanValue() ? FluidTypes.WATER.a(false) : super.d(iblockdata);
/*     */   }
/*     */   
/*     */   private boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, int i) {
/* 104 */     return (i > 0 && !iblockaccess.getType(blockposition.down()).a(this));
/*     */   }
/*     */   
/*     */   public static int a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 108 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = blockposition.i().c(EnumDirection.DOWN);
/* 109 */     IBlockData iblockdata = iblockaccess.getType(blockposition_mutableblockposition);
/* 110 */     int i = 7;
/*     */     
/* 112 */     if (iblockdata.a(Blocks.SCAFFOLDING)) {
/* 113 */       i = ((Integer)iblockdata.get(a)).intValue();
/* 114 */     } else if (iblockdata.d(iblockaccess, blockposition_mutableblockposition, EnumDirection.UP)) {
/* 115 */       return 0;
/*     */     } 
/*     */     
/* 118 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 120 */     while (iterator.hasNext()) {
/* 121 */       EnumDirection enumdirection = iterator.next();
/* 122 */       IBlockData iblockdata1 = iblockaccess.getType(blockposition_mutableblockposition.a(blockposition, enumdirection));
/*     */       
/* 124 */       if (iblockdata1.a(Blocks.SCAFFOLDING)) {
/* 125 */         i = Math.min(i, ((Integer)iblockdata1.get(a)).intValue() + 1);
/* 126 */         if (i == 1) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 132 */     return i;
/*     */   }
/*     */   
/*     */   static {
/* 136 */     VoxelShape voxelshape = Block.a(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/* 137 */     VoxelShape voxelshape1 = Block.a(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D);
/* 138 */     VoxelShape voxelshape2 = Block.a(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
/* 139 */     VoxelShape voxelshape3 = Block.a(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D);
/* 140 */     VoxelShape voxelshape4 = Block.a(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
/*     */     
/* 142 */     d = VoxelShapes.a(voxelshape, new VoxelShape[] { voxelshape1, voxelshape2, voxelshape3, voxelshape4 });
/* 143 */     VoxelShape voxelshape5 = Block.a(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D);
/* 144 */     VoxelShape voxelshape6 = Block.a(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
/* 145 */     VoxelShape voxelshape7 = Block.a(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D);
/* 146 */     VoxelShape voxelshape8 = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D);
/*     */     
/* 148 */     e = VoxelShapes.a(f, new VoxelShape[] { d, voxelshape6, voxelshape5, voxelshape8, voxelshape7 });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockScaffolding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */