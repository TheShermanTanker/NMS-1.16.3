/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class BlockRedstoneOre extends Block {
/*  12 */   public static final BlockStateBoolean a = BlockRedstoneTorch.LIT;
/*     */   
/*     */   public BlockRedstoneOre(BlockBase.Info blockbase_info) {
/*  15 */     super(blockbase_info);
/*  16 */     j(getBlockData().set(a, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void attack(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {
/*  21 */     interact(iblockdata, world, blockposition, entityhuman);
/*  22 */     super.attack(iblockdata, world, blockposition, entityhuman);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stepOn(World world, BlockPosition blockposition, Entity entity) {
/*  30 */     if (entity instanceof EntityHuman) {
/*  31 */       PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*  32 */       if (!event.isCancelled()) {
/*  33 */         interact(world.getType(blockposition), world, blockposition, entity);
/*  34 */         super.stepOn(world, blockposition, entity);
/*     */       } 
/*     */     } else {
/*  37 */       EntityInteractEvent event = new EntityInteractEvent((Entity)entity.getBukkitEntity(), world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/*  38 */       world.getServer().getPluginManager().callEvent((Event)event);
/*  39 */       if (!event.isCancelled()) {
/*  40 */         interact(world.getType(blockposition), world, blockposition, entity);
/*  41 */         super.stepOn(world, blockposition, entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  49 */     if (world.isClientSide) {
/*  50 */       playEffect(world, blockposition);
/*     */     } else {
/*  52 */       interact(iblockdata, world, blockposition, entityhuman);
/*     */     } 
/*     */     
/*  55 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  57 */     return (itemstack.getItem() instanceof ItemBlock && (new BlockActionContext(entityhuman, enumhand, itemstack, movingobjectpositionblock)).b()) ? EnumInteractionResult.PASS : EnumInteractionResult.SUCCESS;
/*     */   }
/*     */   
/*     */   private static void interact(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/*  61 */     playEffect(world, blockposition);
/*  62 */     if (!((Boolean)iblockdata.get(a)).booleanValue()) {
/*     */       
/*  64 */       if (CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, iblockdata.set(a, Boolean.valueOf(true))).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  68 */       world.setTypeAndData(blockposition, iblockdata.set(a, Boolean.valueOf(true)), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTicking(IBlockData iblockdata) {
/*  75 */     return ((Boolean)iblockdata.get(a)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  80 */     if (((Boolean)iblockdata.get(a)).booleanValue()) {
/*     */       
/*  82 */       if (CraftEventFactory.callBlockFadeEvent(worldserver, blockposition, iblockdata.set(a, Boolean.valueOf(false))).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  86 */       worldserver.setTypeAndData(blockposition, iblockdata.set(a, Boolean.valueOf(false)), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropNaturally(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/*  93 */     super.dropNaturally(iblockdata, worldserver, blockposition, itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpDrop(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/* 106 */     if (EnchantmentManager.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) == 0) {
/* 107 */       int i = 1 + worldserver.random.nextInt(5);
/*     */       
/* 109 */       return i;
/*     */     } 
/* 111 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void playEffect(World world, BlockPosition blockposition) {
/* 116 */     double d0 = 0.5625D;
/* 117 */     Random random = world.random;
/* 118 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 119 */     int i = aenumdirection.length;
/*     */     
/* 121 */     for (int j = 0; j < i; j++) {
/* 122 */       EnumDirection enumdirection = aenumdirection[j];
/* 123 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */       
/* 125 */       if (!world.getType(blockposition1).i(world, blockposition1)) {
/* 126 */         EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.n();
/* 127 */         double d1 = (enumdirection_enumaxis == EnumDirection.EnumAxis.X) ? (0.5D + 0.5625D * enumdirection.getAdjacentX()) : random.nextFloat();
/* 128 */         double d2 = (enumdirection_enumaxis == EnumDirection.EnumAxis.Y) ? (0.5D + 0.5625D * enumdirection.getAdjacentY()) : random.nextFloat();
/* 129 */         double d3 = (enumdirection_enumaxis == EnumDirection.EnumAxis.Z) ? (0.5D + 0.5625D * enumdirection.getAdjacentZ()) : random.nextFloat();
/*     */         
/* 131 */         world.addParticle(ParticleParamRedstone.a, blockposition.getX() + d1, blockposition.getY() + d2, blockposition.getZ() + d3, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 139 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRedstoneOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */