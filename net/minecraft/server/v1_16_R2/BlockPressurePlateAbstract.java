/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public abstract class BlockPressurePlateAbstract extends Block {
/*   9 */   protected static final VoxelShape a = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 0.5D, 15.0D);
/*  10 */   protected static final VoxelShape b = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D);
/*  11 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.25D, 0.875D);
/*     */   
/*     */   protected BlockPressurePlateAbstract(BlockBase.Info blockbase_info) {
/*  14 */     super(blockbase_info);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  19 */     return (getPower(iblockdata) > 0) ? a : b;
/*     */   }
/*     */   
/*     */   protected int c() {
/*  23 */     return 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ai_() {
/*  28 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  33 */     return (enumdirection == EnumDirection.DOWN && !iblockdata.canPlace(generatoraccess, blockposition)) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  38 */     BlockPosition blockposition1 = blockposition.down();
/*     */     
/*  40 */     return (c(iworldreader, blockposition1) || a(iworldreader, blockposition1, EnumDirection.UP));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  45 */     int i = getPower(iblockdata);
/*     */     
/*  47 */     if (i > 0) {
/*  48 */       a(worldserver, blockposition, iblockdata, i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/*  55 */     if (!world.isClientSide) {
/*  56 */       int i = getPower(iblockdata);
/*     */       
/*  58 */       if (i == 0) {
/*  59 */         a(world, blockposition, iblockdata, i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {
/*  66 */     int j = b(world, blockposition);
/*  67 */     boolean flag = (i > 0);
/*  68 */     boolean flag1 = (j > 0);
/*     */ 
/*     */     
/*  71 */     CraftWorld craftWorld = world.getWorld();
/*  72 */     PluginManager manager = world.getServer().getPluginManager();
/*     */     
/*  74 */     if (flag != flag1) {
/*  75 */       BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), i, j);
/*  76 */       manager.callEvent((Event)eventRedstone);
/*     */       
/*  78 */       flag1 = (eventRedstone.getNewCurrent() > 0);
/*  79 */       j = eventRedstone.getNewCurrent();
/*     */     } 
/*     */ 
/*     */     
/*  83 */     if (i != j) {
/*  84 */       IBlockData iblockdata1 = a(iblockdata, j);
/*     */       
/*  86 */       world.setTypeAndData(blockposition, iblockdata1, 2);
/*  87 */       a(world, blockposition);
/*  88 */       world.b(blockposition, iblockdata, iblockdata1);
/*     */     } 
/*     */     
/*  91 */     if (!flag1 && flag) {
/*  92 */       b(world, blockposition);
/*  93 */     } else if (flag1 && !flag) {
/*  94 */       a(world, blockposition);
/*     */     } 
/*     */     
/*  97 */     if (flag1) {
/*  98 */       world.getBlockTickList().a(new BlockPosition(blockposition), this, c());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void a(GeneratorAccess paramGeneratorAccess, BlockPosition paramBlockPosition);
/*     */ 
/*     */   
/*     */   protected abstract void b(GeneratorAccess paramGeneratorAccess, BlockPosition paramBlockPosition);
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 109 */     if (!flag && !iblockdata.a(iblockdata1.getBlock())) {
/* 110 */       if (getPower(iblockdata) > 0) {
/* 111 */         a(world, blockposition);
/*     */       }
/*     */       
/* 114 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(World world, BlockPosition blockposition) {
/* 119 */     world.applyPhysics(blockposition, this);
/* 120 */     world.applyPhysics(blockposition.down(), this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 125 */     return getPower(iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 130 */     return (enumdirection == EnumDirection.UP) ? getPower(iblockdata) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
/* 140 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */   
/*     */   protected abstract int b(World paramWorld, BlockPosition paramBlockPosition);
/*     */   
/*     */   protected abstract int getPower(IBlockData paramIBlockData);
/*     */   
/*     */   protected abstract IBlockData a(IBlockData paramIBlockData, int paramInt);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPressurePlateAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */