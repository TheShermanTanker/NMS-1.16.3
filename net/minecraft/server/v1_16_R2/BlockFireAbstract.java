/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityCombustByBlockEvent;
/*     */ 
/*     */ public abstract class BlockFireAbstract extends Block {
/*   8 */   protected static final VoxelShape a = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D); private final float b;
/*     */   
/*     */   public BlockFireAbstract(BlockBase.Info blockbase_info, float f) {
/*  11 */     super(blockbase_info);
/*  12 */     this.b = f;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  17 */     return a(blockactioncontext.getWorld(), blockactioncontext.getClickPosition());
/*     */   }
/*     */   
/*     */   public static IBlockData a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  21 */     BlockPosition blockposition1 = blockposition.down();
/*  22 */     IBlockData iblockdata = iblockaccess.getType(blockposition1);
/*     */     
/*  24 */     return BlockSoulFire.c(iblockdata.getBlock()) ? Blocks.SOUL_FIRE.getBlockData() : ((BlockFire)Blocks.FIRE).getPlacedState(iblockaccess, blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  29 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract boolean e(IBlockData paramIBlockData);
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/*  36 */     if (!entity.isFireProof()) {
/*  37 */       entity.setFireTicks(entity.getFireTicks() + 1);
/*  38 */       if (entity.getFireTicks() == 0) {
/*     */         
/*  40 */         EntityCombustByBlockEvent entityCombustByBlockEvent = new EntityCombustByBlockEvent((Block)CraftBlock.at(world, blockposition), (Entity)entity.getBukkitEntity(), 8);
/*  41 */         world.getServer().getPluginManager().callEvent((Event)entityCombustByBlockEvent);
/*     */         
/*  43 */         if (!entityCombustByBlockEvent.isCancelled()) {
/*  44 */           entity.setOnFire(entityCombustByBlockEvent.getDuration(), false);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  49 */       entity.damageEntity(DamageSource.FIRE, this.b);
/*     */     } 
/*     */     
/*  52 */     super.a(iblockdata, world, blockposition, entity);
/*     */   }
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  56 */     onPlace(iblockdata, world, blockposition, iblockdata1, flag, null);
/*     */   }
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag, ItemActionContext itemActionContext) {
/*  60 */     if (!iblockdata1.a(iblockdata.getBlock())) {
/*  61 */       if (a(world)) {
/*  62 */         Optional<BlockPortalShape> optional = BlockPortalShape.a(world, blockposition, EnumDirection.EnumAxis.X);
/*     */         
/*  64 */         if (optional.isPresent()) {
/*  65 */           ((BlockPortalShape)optional.get()).createPortal(itemActionContext);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*  70 */       if (!iblockdata.canPlace(world, blockposition)) {
/*  71 */         fireExtinguished(world, blockposition);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(World world) {
/*  78 */     return (world.getTypeKey() == DimensionManager.OVERWORLD || world.getTypeKey() == DimensionManager.THE_NETHER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/*  83 */     if (!world.s_()) {
/*  84 */       world.a((EntityHuman)null, 1009, blockposition, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  90 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  92 */     return !iblockdata.isAir() ? false : ((a(world, blockposition).canPlace(world, blockposition) || b(world, blockposition, enumdirection)));
/*     */   }
/*     */   
/*     */   private static boolean b(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  96 */     if (!a(world)) {
/*  97 */       return false;
/*     */     }
/*  99 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = blockposition.i();
/* 100 */     boolean flag = false;
/* 101 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 102 */     int i = aenumdirection.length;
/*     */     
/* 104 */     for (int j = 0; j < i; j++) {
/* 105 */       EnumDirection enumdirection1 = aenumdirection[j];
/*     */       
/* 107 */       if (world.getType(blockposition_mutableblockposition.g(blockposition).c(enumdirection1)).a(Blocks.OBSIDIAN)) {
/* 108 */         flag = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 113 */     return (flag && BlockPortalShape.a(world, blockposition, enumdirection.h().n()).isPresent());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireExtinguished(GeneratorAccess world, BlockPosition position) {
/* 119 */     if (!CraftEventFactory.callBlockFadeEvent(world, position, Blocks.AIR.getBlockData()).isCancelled())
/* 120 */       world.a(position, false); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFireAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */