/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockObserver
/*     */   extends BlockDirectional
/*     */ {
/*   9 */   public static final BlockStateBoolean b = BlockProperties.w;
/*     */   
/*     */   public BlockObserver(BlockBase.Info blockbase_info) {
/*  12 */     super(blockbase_info);
/*  13 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.SOUTH).set(b, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/*  18 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, b });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/*  23 */     return iblockdata.set(FACING, enumblockrotation.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/*  28 */     return iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  33 */     if (((Boolean)iblockdata.get(b)).booleanValue()) {
/*     */       
/*  35 */       if (CraftEventFactory.callRedstoneChange(worldserver, blockposition, 15, 0).getNewCurrent() != 0) {
/*     */         return;
/*     */       }
/*     */       
/*  39 */       worldserver.setTypeAndData(blockposition, iblockdata.set(b, Boolean.valueOf(false)), 2);
/*     */     } else {
/*     */       
/*  42 */       if (CraftEventFactory.callRedstoneChange(worldserver, blockposition, 0, 15).getNewCurrent() != 15) {
/*     */         return;
/*     */       }
/*     */       
/*  46 */       worldserver.setTypeAndData(blockposition, iblockdata.set(b, Boolean.valueOf(true)), 2);
/*  47 */       worldserver.getBlockTickList().a(blockposition, this, 2);
/*     */     } 
/*     */     
/*  50 */     a(worldserver, blockposition, iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  55 */     if (iblockdata.get(FACING) == enumdirection && !((Boolean)iblockdata.get(b)).booleanValue()) {
/*  56 */       a(generatoraccess, blockposition);
/*     */     }
/*     */     
/*  59 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */   
/*     */   private void a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/*  63 */     if (!generatoraccess.s_() && !generatoraccess.getBlockTickList().a(blockposition, this)) {
/*  64 */       generatoraccess.getBlockTickList().a(blockposition, this, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  70 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*  71 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
/*     */     
/*  73 */     world.a(blockposition1, this, blockposition);
/*  74 */     world.a(blockposition1, this, enumdirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  84 */     return iblockdata.b(iblockaccess, blockposition, enumdirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  89 */     return (((Boolean)iblockdata.get(b)).booleanValue() && iblockdata.get(FACING) == enumdirection) ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  94 */     if (!iblockdata.a(iblockdata1.getBlock()) && 
/*  95 */       !world.s_() && ((Boolean)iblockdata.get(b)).booleanValue() && !world.getBlockTickList().a(blockposition, this)) {
/*  96 */       IBlockData iblockdata2 = iblockdata.set(b, Boolean.valueOf(false));
/*     */       
/*  98 */       world.setTypeAndData(blockposition, iblockdata2, 18);
/*  99 */       a(world, blockposition, iblockdata2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 107 */     if (!iblockdata.a(iblockdata1.getBlock()) && 
/* 108 */       !world.isClientSide && ((Boolean)iblockdata.get(b)).booleanValue() && world.getBlockTickList().a(blockposition, this)) {
/* 109 */       a(world, blockposition, iblockdata.set(b, Boolean.valueOf(false)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 117 */     return getBlockData().set(FACING, blockactioncontext.d().opposite().opposite());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */