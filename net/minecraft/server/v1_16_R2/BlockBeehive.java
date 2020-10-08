/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class BlockBeehive extends BlockTileEntity {
/*  10 */   private static final EnumDirection[] c = new EnumDirection[] { EnumDirection.WEST, EnumDirection.EAST, EnumDirection.SOUTH };
/*  11 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*  12 */   public static final BlockStateInteger b = BlockProperties.au;
/*     */   
/*     */   public BlockBeehive(BlockBase.Info blockbase_info) {
/*  15 */     super(blockbase_info);
/*  16 */     j(((IBlockData)this.blockStateList.getBlockData()).set(b, Integer.valueOf(0)).set(a, EnumDirection.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/*  21 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/*  26 */     return ((Integer)iblockdata.get(b)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/*  31 */     super.a(world, entityhuman, blockposition, iblockdata, tileentity, itemstack);
/*  32 */     if (!world.isClientSide && tileentity instanceof TileEntityBeehive) {
/*  33 */       TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/*     */       
/*  35 */       if (EnchantmentManager.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) == 0) {
/*  36 */         tileentitybeehive.a(entityhuman, iblockdata, TileEntityBeehive.ReleaseStatus.EMERGENCY);
/*  37 */         world.updateAdjacentComparators(blockposition, this);
/*  38 */         b(world, blockposition);
/*     */       } 
/*     */       
/*  41 */       CriterionTriggers.K.a((EntityPlayer)entityhuman, iblockdata.getBlock(), itemstack, tileentitybeehive.getBeeCount());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(World world, BlockPosition blockposition) {
/*  47 */     List<EntityBee> list = world.a(EntityBee.class, (new AxisAlignedBB(blockposition)).grow(8.0D, 6.0D, 8.0D));
/*     */     
/*  49 */     if (!list.isEmpty()) {
/*  50 */       List<EntityHuman> list1 = world.a(EntityHuman.class, (new AxisAlignedBB(blockposition)).grow(8.0D, 6.0D, 8.0D));
/*  51 */       int i = list1.size();
/*  52 */       Iterator<EntityBee> iterator = list.iterator();
/*     */       
/*  54 */       while (iterator.hasNext()) {
/*  55 */         EntityBee entitybee = iterator.next();
/*     */         
/*  57 */         if (entitybee.getGoalTarget() == null) {
/*  58 */           entitybee.setGoalTarget(list1.get(world.random.nextInt(i)), EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(World world, BlockPosition blockposition) {
/*  66 */     a(world, blockposition, new ItemStack(Items.HONEYCOMB, 3));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  71 */     ItemStack itemstack = entityhuman.b(enumhand);
/*  72 */     int i = ((Integer)iblockdata.get(b)).intValue();
/*  73 */     boolean flag = false;
/*     */     
/*  75 */     if (i >= 5) {
/*  76 */       if (itemstack.getItem() == Items.SHEARS) {
/*  77 */         world.playSound(entityhuman, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.BLOCK_BEEHIVE_SHEAR, SoundCategory.NEUTRAL, 1.0F, 1.0F);
/*  78 */         a(world, blockposition);
/*  79 */         itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(enumhand));
/*     */ 
/*     */         
/*  82 */         flag = true;
/*  83 */       } else if (itemstack.getItem() == Items.GLASS_BOTTLE) {
/*  84 */         itemstack.subtract(1);
/*  85 */         world.playSound(entityhuman, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
/*  86 */         if (itemstack.isEmpty()) {
/*  87 */           entityhuman.a(enumhand, new ItemStack(Items.HONEY_BOTTLE));
/*  88 */         } else if (!entityhuman.inventory.pickup(new ItemStack(Items.HONEY_BOTTLE))) {
/*  89 */           entityhuman.drop(new ItemStack(Items.HONEY_BOTTLE), false);
/*     */         } 
/*     */         
/*  92 */         flag = true;
/*     */       } 
/*     */     }
/*     */     
/*  96 */     if (flag) {
/*  97 */       if (!BlockCampfire.a(world, blockposition)) {
/*  98 */         if (d(world, blockposition)) {
/*  99 */           b(world, blockposition);
/*     */         }
/*     */         
/* 102 */         a(world, iblockdata, blockposition, entityhuman, TileEntityBeehive.ReleaseStatus.EMERGENCY);
/*     */       } else {
/* 104 */         a(world, iblockdata, blockposition);
/*     */       } 
/*     */       
/* 107 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/* 109 */     return super.interact(iblockdata, world, blockposition, entityhuman, enumhand, movingobjectpositionblock);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean d(World world, BlockPosition blockposition) {
/* 114 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 116 */     if (tileentity instanceof TileEntityBeehive) {
/* 117 */       TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/*     */       
/* 119 */       return !tileentitybeehive.isEmpty();
/*     */     } 
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, IBlockData iblockdata, BlockPosition blockposition, @Nullable EntityHuman entityhuman, TileEntityBeehive.ReleaseStatus tileentitybeehive_releasestatus) {
/* 126 */     a(world, iblockdata, blockposition);
/* 127 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 129 */     if (tileentity instanceof TileEntityBeehive) {
/* 130 */       TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/*     */       
/* 132 */       tileentitybeehive.a(entityhuman, iblockdata, tileentitybeehive_releasestatus);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, IBlockData iblockdata, BlockPosition blockposition) {
/* 138 */     world.setTypeAndData(blockposition, iblockdata.set(b, Integer.valueOf(0)), 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 143 */     return getBlockData().set(a, blockactioncontext.f().opposite());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 148 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { b, a });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/* 153 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 159 */     return new TileEntityBeehive();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 164 */     if (!world.isClientSide && entityhuman.isCreative() && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
/* 165 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 167 */       if (tileentity instanceof TileEntityBeehive) {
/* 168 */         TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/* 169 */         ItemStack itemstack = new ItemStack(this);
/* 170 */         int i = ((Integer)iblockdata.get(b)).intValue();
/* 171 */         boolean flag = !tileentitybeehive.isEmpty();
/*     */         
/* 173 */         if (!flag && i == 0) {
/*     */           return;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 179 */         if (flag) {
/* 180 */           NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 181 */           nBTTagCompound.set("Bees", tileentitybeehive.m());
/* 182 */           itemstack.a("BlockEntityTag", nBTTagCompound);
/*     */         } 
/*     */         
/* 185 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 186 */         nbttagcompound.setInt("honey_level", i);
/* 187 */         itemstack.a("BlockStateTag", nbttagcompound);
/* 188 */         EntityItem entityitem = new EntityItem(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), itemstack);
/*     */         
/* 190 */         entityitem.defaultPickupDelay();
/* 191 */         world.addEntity(entityitem);
/*     */       } 
/*     */     } 
/*     */     
/* 195 */     super.a(world, blockposition, iblockdata, entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ItemStack> a(IBlockData iblockdata, LootTableInfo.Builder loottableinfo_builder) {
/* 200 */     Entity entity = loottableinfo_builder.<Entity>b(LootContextParameters.THIS_ENTITY);
/*     */     
/* 202 */     if (entity instanceof EntityTNTPrimed || entity instanceof EntityCreeper || entity instanceof EntityWitherSkull || entity instanceof EntityWither || entity instanceof EntityMinecartTNT) {
/* 203 */       TileEntity tileentity = loottableinfo_builder.<TileEntity>b(LootContextParameters.BLOCK_ENTITY);
/*     */       
/* 205 */       if (tileentity instanceof TileEntityBeehive) {
/* 206 */         TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/*     */         
/* 208 */         tileentitybeehive.a((EntityHuman)null, iblockdata, TileEntityBeehive.ReleaseStatus.EMERGENCY);
/*     */       } 
/*     */     } 
/*     */     
/* 212 */     return super.a(iblockdata, loottableinfo_builder);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 217 */     if (generatoraccess.getType(blockposition1).getBlock() instanceof BlockFire) {
/* 218 */       TileEntity tileentity = generatoraccess.getTileEntity(blockposition);
/*     */       
/* 220 */       if (tileentity instanceof TileEntityBeehive) {
/* 221 */         TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/*     */         
/* 223 */         tileentitybeehive.a((EntityHuman)null, iblockdata, TileEntityBeehive.ReleaseStatus.EMERGENCY);
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */   
/*     */   public static EnumDirection a(Random random) {
/* 231 */     return (EnumDirection)SystemUtils.<Object>a((Object[])c, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBeehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */