/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockSnow extends Block {
/*   8 */   public static final BlockStateInteger LAYERS = BlockProperties.aq;
/*   9 */   protected static final VoxelShape[] b = new VoxelShape[] { VoxelShapes.a(), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };
/*     */   
/*     */   protected BlockSnow(BlockBase.Info blockbase_info) {
/*  12 */     super(blockbase_info);
/*  13 */     j(((IBlockData)this.blockStateList.getBlockData()).set(LAYERS, Integer.valueOf(1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/*  18 */     switch (pathmode) {
/*     */       case LAND:
/*  20 */         return (((Integer)iblockdata.get(LAYERS)).intValue() < 5);
/*     */       case WATER:
/*  22 */         return false;
/*     */       case AIR:
/*  24 */         return false;
/*     */     } 
/*  26 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  32 */     return b[((Integer)iblockdata.get(LAYERS)).intValue()];
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  37 */     return b[((Integer)iblockdata.get(LAYERS)).intValue() - 1];
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape e(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  42 */     return b[((Integer)iblockdata.get(LAYERS)).intValue()];
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  47 */     return b[((Integer)iblockdata.get(LAYERS)).intValue()];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData iblockdata) {
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  57 */     IBlockData iblockdata1 = iworldreader.getType(blockposition.down());
/*     */     
/*  59 */     return (!iblockdata1.a(Blocks.ICE) && !iblockdata1.a(Blocks.PACKED_ICE) && !iblockdata1.a(Blocks.BARRIER)) ? ((!iblockdata1.a(Blocks.HONEY_BLOCK) && !iblockdata1.a(Blocks.SOUL_SAND)) ? ((Block.a(iblockdata1.getCollisionShape(iworldreader, blockposition.down()), EnumDirection.UP) || (iblockdata1.getBlock() == this && ((Integer)iblockdata1.get(LAYERS)).intValue() == 8))) : true) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  64 */     return !iblockdata.canPlace(generatoraccess, blockposition) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  69 */     if (worldserver.getBrightness(EnumSkyBlock.BLOCK, blockposition) > 11) {
/*     */       
/*  71 */       if (CraftEventFactory.callBlockFadeEvent(worldserver, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  75 */       c(iblockdata, worldserver, blockposition);
/*  76 */       worldserver.a(blockposition, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
/*  83 */     int i = ((Integer)iblockdata.get(LAYERS)).intValue();
/*     */     
/*  85 */     return (blockactioncontext.getItemStack().getItem() == getItem() && i < 8) ? (blockactioncontext.c() ? ((blockactioncontext.getClickedFace() == EnumDirection.UP)) : true) : ((i == 1));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  91 */     IBlockData iblockdata = blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition());
/*     */     
/*  93 */     if (iblockdata.a(this)) {
/*  94 */       int i = ((Integer)iblockdata.get(LAYERS)).intValue();
/*     */       
/*  96 */       return iblockdata.set(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
/*     */     } 
/*  98 */     return super.getPlacedState(blockactioncontext);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 104 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { LAYERS });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */