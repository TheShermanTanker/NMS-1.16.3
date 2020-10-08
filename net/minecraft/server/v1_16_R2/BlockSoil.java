/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class BlockSoil extends Block {
/*  13 */   public static final BlockStateInteger MOISTURE = BlockProperties.aw;
/*  14 */   protected static final VoxelShape b = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
/*     */   
/*     */   protected BlockSoil(BlockBase.Info blockbase_info) {
/*  17 */     super(blockbase_info);
/*  18 */     j(((IBlockData)this.blockStateList.getBlockData()).set(MOISTURE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  23 */     if (enumdirection == EnumDirection.UP && !iblockdata.canPlace(generatoraccess, blockposition)) {
/*  24 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*     */     }
/*     */     
/*  27 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  32 */     IBlockData iblockdata1 = iworldreader.getType(blockposition.up());
/*     */     
/*  34 */     return (!iblockdata1.getMaterial().isBuildable() || iblockdata1.getBlock() instanceof BlockFenceGate || iblockdata1.getBlock() instanceof BlockPistonMoving);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  39 */     return !getBlockData().canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition()) ? Blocks.DIRT.getBlockData() : super.getPlacedState(blockactioncontext);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData iblockdata) {
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  49 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  54 */     if (!iblockdata.canPlace(worldserver, blockposition)) {
/*  55 */       fade(iblockdata, worldserver, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  62 */     int i = ((Integer)iblockdata.get(MOISTURE)).intValue();
/*     */     
/*  64 */     if (!a(worldserver, blockposition) && !worldserver.isRainingAt(blockposition.up())) {
/*  65 */       if (i > 0) {
/*  66 */         CraftEventFactory.handleMoistureChangeEvent(worldserver, blockposition, iblockdata.set(MOISTURE, Integer.valueOf(i - 1)), 2);
/*  67 */       } else if (!a(worldserver, blockposition)) {
/*  68 */         fade(iblockdata, worldserver, blockposition);
/*     */       } 
/*  70 */     } else if (i < 7) {
/*  71 */       CraftEventFactory.handleMoistureChangeEvent(worldserver, blockposition, iblockdata.set(MOISTURE, Integer.valueOf(7)), 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
/*  78 */     super.fallOn(world, blockposition, entity, f);
/*  79 */     if (!world.isClientSide && world.random.nextFloat() < f - 0.5F && entity instanceof EntityLiving && (entity instanceof EntityHuman || world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
/*     */       EntityInteractEvent entityInteractEvent;
/*     */       
/*  82 */       if (entity instanceof EntityHuman) {
/*  83 */         PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*     */       } else {
/*  85 */         entityInteractEvent = new EntityInteractEvent((Entity)entity.getBukkitEntity(), world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/*  86 */         world.getServer().getPluginManager().callEvent((Event)entityInteractEvent);
/*     */       } 
/*     */       
/*  89 */       if (entityInteractEvent.isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  93 */       if (CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, Blocks.DIRT.getBlockData()).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  97 */       fade(world.getType(blockposition), world, blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fade(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 105 */     if (CraftEventFactory.callBlockFadeEvent(world, blockposition, Blocks.DIRT.getBlockData()).isCancelled()) {
/*     */       return;
/*     */     }
/*     */     
/* 109 */     world.setTypeUpdate(blockposition, a(iblockdata, Blocks.DIRT.getBlockData(), world, blockposition));
/*     */   }
/*     */   
/*     */   private static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 113 */     Block block = iblockaccess.getType(blockposition.up()).getBlock();
/*     */     
/* 115 */     return (block instanceof BlockCrops || block instanceof BlockStem || block instanceof BlockStemAttached);
/*     */   }
/*     */   private static boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/*     */     BlockPosition blockposition1;
/* 119 */     Iterator<BlockPosition> iterator = BlockPosition.a(blockposition.b(-4, 0, -4), blockposition.b(4, 1, 4)).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 124 */       if (!iterator.hasNext()) {
/* 125 */         return false;
/*     */       }
/*     */       
/* 128 */       blockposition1 = iterator.next();
/* 129 */     } while (!iworldreader.getFluid(blockposition1).a(TagsFluid.WATER));
/*     */     
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 136 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { MOISTURE });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 141 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSoil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */