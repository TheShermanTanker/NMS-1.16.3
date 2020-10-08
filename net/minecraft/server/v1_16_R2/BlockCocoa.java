/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockCocoa
/*     */   extends BlockFacingHorizontal
/*     */   implements IBlockFragilePlantElement {
/*  10 */   public static final BlockStateInteger AGE = BlockProperties.af;
/*  11 */   protected static final VoxelShape[] b = new VoxelShape[] { Block.a(11.0D, 7.0D, 6.0D, 15.0D, 12.0D, 10.0D), Block.a(9.0D, 5.0D, 5.0D, 15.0D, 12.0D, 11.0D), Block.a(7.0D, 3.0D, 4.0D, 15.0D, 12.0D, 12.0D) };
/*  12 */   protected static final VoxelShape[] c = new VoxelShape[] { Block.a(1.0D, 7.0D, 6.0D, 5.0D, 12.0D, 10.0D), Block.a(1.0D, 5.0D, 5.0D, 7.0D, 12.0D, 11.0D), Block.a(1.0D, 3.0D, 4.0D, 9.0D, 12.0D, 12.0D) };
/*  13 */   protected static final VoxelShape[] d = new VoxelShape[] { Block.a(6.0D, 7.0D, 1.0D, 10.0D, 12.0D, 5.0D), Block.a(5.0D, 5.0D, 1.0D, 11.0D, 12.0D, 7.0D), Block.a(4.0D, 3.0D, 1.0D, 12.0D, 12.0D, 9.0D) };
/*  14 */   protected static final VoxelShape[] e = new VoxelShape[] { Block.a(6.0D, 7.0D, 11.0D, 10.0D, 12.0D, 15.0D), Block.a(5.0D, 5.0D, 9.0D, 11.0D, 12.0D, 15.0D), Block.a(4.0D, 3.0D, 7.0D, 12.0D, 12.0D, 15.0D) };
/*     */   
/*     */   public BlockCocoa(BlockBase.Info blockbase_info) {
/*  17 */     super(blockbase_info);
/*  18 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(AGE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTicking(IBlockData iblockdata) {
/*  23 */     return (((Integer)iblockdata.get(AGE)).intValue() < 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  28 */     if (worldserver.random.nextInt(Math.max(1, (int)(100.0F / worldserver.spigotConfig.cocoaModifier) * 5)) == 0) {
/*  29 */       int i = ((Integer)iblockdata.get(AGE)).intValue();
/*     */       
/*  31 */       if (i < 2) {
/*  32 */         CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, iblockdata.set(AGE, Integer.valueOf(i + 1)), 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  40 */     Block block = iworldreader.getType(blockposition.shift((EnumDirection)iblockdata.get(FACING))).getBlock();
/*     */     
/*  42 */     return block.a(TagsBlock.JUNGLE_LOGS);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  47 */     int i = ((Integer)iblockdata.get(AGE)).intValue();
/*     */     
/*  49 */     switch ((EnumDirection)iblockdata.get(FACING))
/*     */     { case SOUTH:
/*  51 */         return e[i];
/*     */       
/*     */       default:
/*  54 */         return d[i];
/*     */       case WEST:
/*  56 */         return c[i];
/*     */       case EAST:
/*  58 */         break; }  return b[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  65 */     IBlockData iblockdata = getBlockData();
/*  66 */     World world = blockactioncontext.getWorld();
/*  67 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*  68 */     EnumDirection[] aenumdirection = blockactioncontext.e();
/*  69 */     int i = aenumdirection.length;
/*     */     
/*  71 */     for (int j = 0; j < i; j++) {
/*  72 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/*  74 */       if (enumdirection.n().d()) {
/*  75 */         iblockdata = iblockdata.set(FACING, enumdirection);
/*  76 */         if (iblockdata.canPlace(world, blockposition)) {
/*  77 */           return iblockdata;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  87 */     return (enumdirection == iblockdata.get(FACING) && !iblockdata.canPlace(generatoraccess, blockposition)) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/*  92 */     return (((Integer)iblockdata.get(AGE)).intValue() < 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 102 */     CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, iblockdata.set(AGE, Integer.valueOf(((Integer)iblockdata.get(AGE)).intValue() + 1)), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 107 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, AGE });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 112 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCocoa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */