/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Random;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.event.player.PlayerHarvestBlockEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class BlockSweetBerryBush
/*     */   extends BlockPlant implements IBlockFragilePlantElement {
/*  14 */   public static final BlockStateInteger a = BlockProperties.ag;
/*  15 */   private static final VoxelShape b = Block.a(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
/*  16 */   private static final VoxelShape c = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
/*     */   
/*     */   public BlockSweetBerryBush(BlockBase.Info blockbase_info) {
/*  19 */     super(blockbase_info);
/*  20 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  25 */     return (((Integer)iblockdata.get(a)).intValue() == 0) ? b : ((((Integer)iblockdata.get(a)).intValue() < 3) ? c : super.b(iblockdata, iblockaccess, blockposition, voxelshapecollision));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTicking(IBlockData iblockdata) {
/*  30 */     return (((Integer)iblockdata.get(a)).intValue() < 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  35 */     int i = ((Integer)iblockdata.get(a)).intValue();
/*     */     
/*  37 */     if (i < 3 && random.nextInt(Math.max(1, (int)(100.0F / worldserver.spigotConfig.sweetBerryModifier) * 5)) == 0 && worldserver.getLightLevel(blockposition.up(), 0) >= 9) {
/*  38 */       CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, iblockdata.set(a, Integer.valueOf(i + 1)), 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/*  45 */     if (entity instanceof EntityLiving && entity.getEntityType() != EntityTypes.FOX && entity.getEntityType() != EntityTypes.BEE) {
/*  46 */       entity.a(iblockdata, new Vec3D(0.800000011920929D, 0.75D, 0.800000011920929D));
/*  47 */       if (!world.isClientSide && ((Integer)iblockdata.get(a)).intValue() > 0 && (entity.D != entity.locX() || entity.F != entity.locZ())) {
/*  48 */         double d0 = Math.abs(entity.locX() - entity.D);
/*  49 */         double d1 = Math.abs(entity.locZ() - entity.F);
/*     */         
/*  51 */         if (d0 >= 0.003000000026077032D || d1 >= 0.003000000026077032D) {
/*  52 */           CraftEventFactory.blockDamage = (Block)CraftBlock.at(world, blockposition);
/*  53 */           entity.damageEntity(DamageSource.SWEET_BERRY_BUSH, 1.0F);
/*  54 */           CraftEventFactory.blockDamage = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  63 */     int i = ((Integer)iblockdata.get(a)).intValue();
/*  64 */     boolean flag = (i == 3);
/*     */     
/*  66 */     if (!flag && entityhuman.b(enumhand).getItem() == Items.BONE_MEAL)
/*  67 */       return EnumInteractionResult.PASS; 
/*  68 */     if (i > 1) {
/*  69 */       int j = 1 + world.random.nextInt(2);
/*     */ 
/*     */       
/*  72 */       PlayerHarvestBlockEvent event = CraftEventFactory.callPlayerHarvestBlockEvent(world, blockposition, entityhuman, Collections.singletonList(new ItemStack(Items.SWEET_BERRIES, j + (flag ? 1 : 0))));
/*  73 */       if (event.isCancelled()) {
/*  74 */         return EnumInteractionResult.SUCCESS;
/*     */       }
/*  76 */       for (ItemStack itemStack : event.getItemsHarvested()) {
/*  77 */         a(world, blockposition, CraftItemStack.asNMSCopy(itemStack));
/*     */       }
/*     */       
/*  80 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
/*  81 */       world.setTypeAndData(blockposition, iblockdata.set(a, Integer.valueOf(1)), 2);
/*  82 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/*  84 */     return super.interact(iblockdata, world, blockposition, entityhuman, enumhand, movingobjectpositionblock);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/*  90 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/*  95 */     return (((Integer)iblockdata.get(a)).intValue() < 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 105 */     int i = Math.min(3, ((Integer)iblockdata.get(a)).intValue() + 1);
/*     */     
/* 107 */     worldserver.setTypeAndData(blockposition, iblockdata.set(a, Integer.valueOf(i)), 2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSweetBerryBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */