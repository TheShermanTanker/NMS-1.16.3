/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class BlockTurtleEgg extends Block {
/*  14 */   private static final VoxelShape c = Block.a(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);
/*  15 */   private static final VoxelShape d = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
/*  16 */   public static final BlockStateInteger a = BlockProperties.ap;
/*  17 */   public static final BlockStateInteger b = BlockProperties.ao;
/*     */   
/*     */   public BlockTurtleEgg(BlockBase.Info blockbase_info) {
/*  20 */     super(blockbase_info);
/*  21 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(0)).set(b, Integer.valueOf(1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void stepOn(World world, BlockPosition blockposition, Entity entity) {
/*  26 */     a(world, blockposition, entity, 100);
/*  27 */     super.stepOn(world, blockposition, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
/*  32 */     if (!(entity instanceof EntityZombie)) {
/*  33 */       a(world, blockposition, entity, 3);
/*     */     }
/*     */     
/*  36 */     super.fallOn(world, blockposition, entity, f);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, Entity entity, int i) {
/*  40 */     if (a(world, entity) && 
/*  41 */       !world.isClientSide && world.random.nextInt(i) == 0) {
/*  42 */       IBlockData iblockdata = world.getType(blockposition);
/*     */       
/*  44 */       if (iblockdata.a(Blocks.TURTLE_EGG)) {
/*     */         EntityInteractEvent entityInteractEvent;
/*     */         
/*  47 */         if (entity instanceof EntityHuman) {
/*  48 */           PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*     */         } else {
/*  50 */           entityInteractEvent = new EntityInteractEvent((Entity)entity.getBukkitEntity(), (Block)CraftBlock.at(world, blockposition));
/*  51 */           world.getServer().getPluginManager().callEvent((Event)entityInteractEvent);
/*     */         } 
/*     */         
/*  54 */         if (entityInteractEvent.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/*  58 */         a(world, blockposition, iblockdata);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  66 */     world.playSound((EntityHuman)null, blockposition, SoundEffects.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
/*  67 */     int i = ((Integer)iblockdata.get(b)).intValue();
/*     */     
/*  69 */     if (i <= 1) {
/*  70 */       world.b(blockposition, false);
/*     */     } else {
/*  72 */       world.setTypeAndData(blockposition, iblockdata.set(b, Integer.valueOf(i - 1)), 2);
/*  73 */       world.triggerEffect(2001, blockposition, Block.getCombinedId(iblockdata));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  80 */     if (a(worldserver) && a(worldserver, blockposition)) {
/*  81 */       int i = ((Integer)iblockdata.get(a)).intValue();
/*     */       
/*  83 */       if (i < 2) {
/*     */         
/*  85 */         if (!CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, iblockdata.set(a, Integer.valueOf(i + 1)), 2)) {
/*     */           return;
/*     */         }
/*     */         
/*  89 */         worldserver.playSound((EntityHuman)null, blockposition, SoundEffects.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
/*     */       }
/*     */       else {
/*     */         
/*  93 */         if (CraftEventFactory.callBlockFadeEvent(worldserver, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/*  97 */         worldserver.playSound((EntityHuman)null, blockposition, SoundEffects.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
/*  98 */         worldserver.a(blockposition, false);
/*     */         
/* 100 */         for (int j = 0; j < ((Integer)iblockdata.get(b)).intValue(); j++) {
/* 101 */           worldserver.triggerEffect(2001, blockposition, Block.getCombinedId(iblockdata));
/* 102 */           EntityTurtle entityturtle = EntityTypes.TURTLE.a(worldserver);
/*     */           
/* 104 */           entityturtle.setAgeRaw(-24000);
/* 105 */           entityturtle.setHomePos(blockposition);
/* 106 */           entityturtle.setPositionRotation(blockposition.getX() + 0.3D + j * 0.2D, blockposition.getY(), blockposition.getZ() + 0.3D, 0.0F, 0.0F);
/* 107 */           worldserver.addEntity(entityturtle, CreatureSpawnEvent.SpawnReason.EGG);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 115 */     return b(iblockaccess, blockposition.down());
/*     */   }
/*     */   
/*     */   public static boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 119 */     return iblockaccess.getType(blockposition).a(TagsBlock.SAND);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 124 */     if (a(world, blockposition) && !world.isClientSide) {
/* 125 */       world.triggerEffect(2005, blockposition, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(World world) {
/* 131 */     float f = world.f(1.0F);
/*     */     
/* 133 */     return (f < 0.69D && f > 0.65D) ? true : ((world.random.nextInt(500) == 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 138 */     super.a(world, entityhuman, blockposition, iblockdata, tileentity, itemstack);
/* 139 */     a(world, blockposition, iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
/* 144 */     return (blockactioncontext.getItemStack().getItem() == getItem() && ((Integer)iblockdata.get(b)).intValue() < 4) ? true : super.a(iblockdata, blockactioncontext);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 150 */     IBlockData iblockdata = blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition());
/*     */     
/* 152 */     return iblockdata.a(this) ? iblockdata.set(b, Integer.valueOf(Math.min(4, ((Integer)iblockdata.get(b)).intValue() + 1))) : super.getPlacedState(blockactioncontext);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 157 */     return (((Integer)iblockdata.get(b)).intValue() > 1) ? d : c;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 162 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a, b });
/*     */   }
/*     */   
/*     */   private boolean a(World world, Entity entity) {
/* 166 */     return (!(entity instanceof EntityTurtle) && !(entity instanceof EntityBat)) ? (!(entity instanceof EntityLiving) ? false : ((entity instanceof EntityHuman || world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)))) : false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTurtleEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */