/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.block.TNTPrimeEvent;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.entity.Entity;
/*     */ 
/*     */ public class BlockTNT extends Block {
/*   8 */   public static final BlockStateBoolean a = BlockProperties.B;
/*     */   
/*     */   public BlockTNT(BlockBase.Info blockbase_info) {
/*  11 */     super(blockbase_info);
/*  12 */     j(getBlockData().set(a, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  17 */     if (!iblockdata1.a(iblockdata.getBlock()) && 
/*  18 */       world.isBlockIndirectlyPowered(blockposition)) {
/*     */       
/*  20 */       Block tntBlock = MCUtil.toBukkitBlock(world, blockposition);
/*  21 */       if (!(new TNTPrimeEvent(tntBlock, TNTPrimeEvent.PrimeReason.REDSTONE, null)).callEvent()) {
/*     */         return;
/*     */       }
/*  24 */       a(world, blockposition);
/*  25 */       world.a(blockposition, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  33 */     if (world.isBlockIndirectlyPowered(blockposition)) {
/*     */       
/*  35 */       Block tntBlock = MCUtil.toBukkitBlock(world, blockposition);
/*  36 */       if (!(new TNTPrimeEvent(tntBlock, TNTPrimeEvent.PrimeReason.REDSTONE, null)).callEvent()) {
/*     */         return;
/*     */       }
/*  39 */       a(world, blockposition);
/*  40 */       world.a(blockposition, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/*  47 */     if (!world.s_() && !entityhuman.isCreative() && ((Boolean)iblockdata.get(a)).booleanValue()) {
/*  48 */       a(world, blockposition);
/*     */     }
/*     */     
/*  51 */     super.a(world, blockposition, iblockdata, entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void wasExploded(World world, BlockPosition blockposition, Explosion explosion) {
/*  56 */     if (!world.isClientSide) {
/*     */       
/*  58 */       Block tntBlock = MCUtil.toBukkitBlock(world, blockposition);
/*  59 */       CraftEntity craftEntity = (explosion.source != null) ? explosion.source.getBukkitEntity() : null;
/*  60 */       if (!(new TNTPrimeEvent(tntBlock, TNTPrimeEvent.PrimeReason.EXPLOSION, (Entity)craftEntity)).callEvent()) {
/*     */         return;
/*     */       }
/*  63 */       EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D, explosion.getSource());
/*     */       
/*  65 */       entitytntprimed.setFuseTicks((short)(world.random.nextInt(entitytntprimed.getFuseTicks() / 4) + entitytntprimed.getFuseTicks() / 8));
/*  66 */       world.addEntity(entitytntprimed);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void a(World world, BlockPosition blockposition) {
/*  71 */     a(world, blockposition, (EntityLiving)null);
/*     */   }
/*     */   
/*     */   private static void a(World world, BlockPosition blockposition, @Nullable EntityLiving entityliving) {
/*  75 */     if (!world.isClientSide) {
/*  76 */       EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D, entityliving);
/*     */       
/*  78 */       world.addEntity(entitytntprimed);
/*  79 */       world.playSound((EntityHuman)null, entitytntprimed.locX(), entitytntprimed.locY(), entitytntprimed.locZ(), SoundEffects.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  85 */     ItemStack itemstack = entityhuman.b(enumhand);
/*  86 */     Item item = itemstack.getItem();
/*     */     
/*  88 */     if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
/*  89 */       return super.interact(iblockdata, world, blockposition, entityhuman, enumhand, movingobjectpositionblock);
/*     */     }
/*     */     
/*  92 */     Block tntBlock = MCUtil.toBukkitBlock(world, blockposition);
/*  93 */     if (!(new TNTPrimeEvent(tntBlock, TNTPrimeEvent.PrimeReason.ITEM, (Entity)entityhuman.getBukkitEntity())).callEvent()) {
/*  94 */       return EnumInteractionResult.FAIL;
/*     */     }
/*  96 */     a(world, blockposition, entityhuman);
/*  97 */     world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 11);
/*  98 */     if (!entityhuman.isCreative()) {
/*  99 */       if (item == Items.FLINT_AND_STEEL) {
/* 100 */         itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(enumhand));
/*     */       }
/*     */       else {
/*     */         
/* 104 */         itemstack.subtract(1);
/*     */       } 
/*     */     }
/*     */     
/* 108 */     return EnumInteractionResult.a(world.isClientSide);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, IBlockData iblockdata, MovingObjectPositionBlock movingobjectpositionblock, IProjectile iprojectile) {
/* 114 */     if (!world.isClientSide) {
/* 115 */       Entity entity = iprojectile.getShooter();
/*     */       
/* 117 */       if (iprojectile.isBurning()) {
/* 118 */         BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
/*     */         
/* 120 */         if (CraftEventFactory.callEntityChangeBlockEvent(iprojectile, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/*     */           return;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 126 */         Block tntBlock = MCUtil.toBukkitBlock(world, blockposition);
/* 127 */         if (!(new TNTPrimeEvent(tntBlock, TNTPrimeEvent.PrimeReason.PROJECTILE, (Entity)iprojectile.getBukkitEntity())).callEvent()) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 132 */         a(world, blockposition, (entity instanceof EntityLiving) ? (EntityLiving)entity : null);
/* 133 */         world.a(blockposition, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(Explosion explosion) {
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 146 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */