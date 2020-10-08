/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.CauldronLevelChangeEvent;
/*     */ 
/*     */ public class BlockCauldron extends Block {
/*   7 */   public static final BlockStateInteger LEVEL = BlockProperties.ar;
/*   8 */   private static final VoxelShape c = a(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
/*   9 */   protected static final VoxelShape b = VoxelShapes.a(VoxelShapes.b(), VoxelShapes.a(a(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), new VoxelShape[] { a(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), a(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), c }), OperatorBoolean.ONLY_FIRST);
/*     */   
/*     */   public BlockCauldron(BlockBase.Info blockbase_info) {
/*  12 */     super(blockbase_info);
/*  13 */     j(((IBlockData)this.blockStateList.getBlockData()).set(LEVEL, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  18 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape a_(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  23 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/*  28 */     int i = ((Integer)iblockdata.get(LEVEL)).intValue();
/*  29 */     float f = blockposition.getY() + (6.0F + (3 * i)) / 16.0F;
/*     */     
/*  31 */     if (!world.isClientSide && entity.isBurning() && i > 0 && entity.locY() <= f) {
/*     */       
/*  33 */       if (!changeLevel(world, blockposition, iblockdata, i - 1, entity, CauldronLevelChangeEvent.ChangeReason.EXTINGUISH)) {
/*     */         return;
/*     */       }
/*  36 */       entity.extinguish();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  45 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  47 */     if (itemstack.isEmpty()) {
/*  48 */       return EnumInteractionResult.PASS;
/*     */     }
/*  50 */     int i = ((Integer)iblockdata.get(LEVEL)).intValue();
/*  51 */     Item item = itemstack.getItem();
/*     */     
/*  53 */     if (item == Items.WATER_BUCKET) {
/*  54 */       if (i < 3 && !world.isClientSide) {
/*     */         
/*  56 */         if (!changeLevel(world, blockposition, iblockdata, 3, entityhuman, CauldronLevelChangeEvent.ChangeReason.BUCKET_EMPTY)) {
/*  57 */           return EnumInteractionResult.SUCCESS;
/*     */         }
/*  59 */         if (!entityhuman.abilities.canInstantlyBuild) {
/*  60 */           entityhuman.a(enumhand, new ItemStack(Items.BUCKET));
/*     */         }
/*     */         
/*  63 */         entityhuman.a(StatisticList.FILL_CAULDRON);
/*     */ 
/*     */         
/*  66 */         world.playSound((EntityHuman)null, blockposition, SoundEffects.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */       
/*  69 */       return EnumInteractionResult.a(world.isClientSide);
/*  70 */     }  if (item == Items.BUCKET) {
/*  71 */       if (i == 3 && !world.isClientSide) {
/*     */         
/*  73 */         if (!changeLevel(world, blockposition, iblockdata, 0, entityhuman, CauldronLevelChangeEvent.ChangeReason.BUCKET_FILL)) {
/*  74 */           return EnumInteractionResult.SUCCESS;
/*     */         }
/*  76 */         if (!entityhuman.abilities.canInstantlyBuild) {
/*  77 */           itemstack.subtract(1);
/*  78 */           if (itemstack.isEmpty()) {
/*  79 */             entityhuman.a(enumhand, new ItemStack(Items.WATER_BUCKET));
/*  80 */           } else if (!entityhuman.inventory.pickup(new ItemStack(Items.WATER_BUCKET))) {
/*  81 */             entityhuman.drop(new ItemStack(Items.WATER_BUCKET), false);
/*     */           } 
/*     */         } 
/*     */         
/*  85 */         entityhuman.a(StatisticList.USE_CAULDRON);
/*     */ 
/*     */         
/*  88 */         world.playSound((EntityHuman)null, blockposition, SoundEffects.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */       
/*  91 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/*     */ 
/*     */     
/*  95 */     if (item == Items.GLASS_BOTTLE) {
/*  96 */       if (i > 0 && !world.isClientSide) {
/*     */         
/*  98 */         if (!changeLevel(world, blockposition, iblockdata, i - 1, entityhuman, CauldronLevelChangeEvent.ChangeReason.BOTTLE_FILL)) {
/*  99 */           return EnumInteractionResult.SUCCESS;
/*     */         }
/* 101 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 102 */           ItemStack itemstack1 = PotionUtil.a(new ItemStack(Items.POTION), Potions.WATER);
/* 103 */           entityhuman.a(StatisticList.USE_CAULDRON);
/* 104 */           itemstack.subtract(1);
/* 105 */           if (itemstack.isEmpty()) {
/* 106 */             entityhuman.a(enumhand, itemstack1);
/* 107 */           } else if (!entityhuman.inventory.pickup(itemstack1)) {
/* 108 */             entityhuman.drop(itemstack1, false);
/* 109 */           } else if (entityhuman instanceof EntityPlayer) {
/* 110 */             ((EntityPlayer)entityhuman).updateInventory(entityhuman.defaultContainer);
/*     */           } 
/*     */         } 
/*     */         
/* 114 */         world.playSound((EntityHuman)null, blockposition, SoundEffects.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 119 */       return EnumInteractionResult.a(world.isClientSide);
/* 120 */     }  if (item == Items.POTION && PotionUtil.d(itemstack) == Potions.WATER) {
/* 121 */       if (i < 3 && !world.isClientSide) {
/*     */         
/* 123 */         if (!changeLevel(world, blockposition, iblockdata, i + 1, entityhuman, CauldronLevelChangeEvent.ChangeReason.BOTTLE_EMPTY)) {
/* 124 */           return EnumInteractionResult.SUCCESS;
/*     */         }
/* 126 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 127 */           ItemStack itemstack1 = new ItemStack(Items.GLASS_BOTTLE);
/* 128 */           entityhuman.a(StatisticList.USE_CAULDRON);
/* 129 */           entityhuman.a(enumhand, itemstack1);
/* 130 */           if (entityhuman instanceof EntityPlayer) {
/* 131 */             ((EntityPlayer)entityhuman).updateInventory(entityhuman.defaultContainer);
/*     */           }
/*     */         } 
/*     */         
/* 135 */         world.playSound((EntityHuman)null, blockposition, SoundEffects.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 140 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/* 142 */     if (i > 0 && item instanceof IDyeable) {
/* 143 */       IDyeable idyeable = (IDyeable)item;
/*     */       
/* 145 */       if (idyeable.a(itemstack) && !world.isClientSide) {
/*     */         
/* 147 */         if (!changeLevel(world, blockposition, iblockdata, i - 1, entityhuman, CauldronLevelChangeEvent.ChangeReason.ARMOR_WASH)) {
/* 148 */           return EnumInteractionResult.SUCCESS;
/*     */         }
/* 150 */         idyeable.c(itemstack);
/*     */ 
/*     */         
/* 153 */         entityhuman.a(StatisticList.CLEAN_ARMOR);
/* 154 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/*     */     } 
/*     */     
/* 158 */     if (i > 0 && item instanceof ItemBanner) {
/* 159 */       if (TileEntityBanner.b(itemstack) > 0 && !world.isClientSide) {
/*     */         
/* 161 */         if (!changeLevel(world, blockposition, iblockdata, i - 1, entityhuman, CauldronLevelChangeEvent.ChangeReason.BANNER_WASH)) {
/* 162 */           return EnumInteractionResult.SUCCESS;
/*     */         }
/* 164 */         ItemStack itemstack1 = itemstack.cloneItemStack();
/* 165 */         itemstack1.setCount(1);
/* 166 */         TileEntityBanner.c(itemstack1);
/* 167 */         entityhuman.a(StatisticList.CLEAN_BANNER);
/* 168 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 169 */           itemstack.subtract(1);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 174 */         if (itemstack.isEmpty()) {
/* 175 */           entityhuman.a(enumhand, itemstack1);
/* 176 */         } else if (!entityhuman.inventory.pickup(itemstack1)) {
/* 177 */           entityhuman.drop(itemstack1, false);
/* 178 */         } else if (entityhuman instanceof EntityPlayer) {
/* 179 */           ((EntityPlayer)entityhuman).updateInventory(entityhuman.defaultContainer);
/*     */         } 
/*     */       } 
/*     */       
/* 183 */       return EnumInteractionResult.a(world.isClientSide);
/* 184 */     }  if (i > 0 && item instanceof ItemBlock) {
/* 185 */       Block block = ((ItemBlock)item).getBlock();
/*     */       
/* 187 */       if (block instanceof BlockShulkerBox && !world.s_()) {
/* 188 */         ItemStack itemstack2 = new ItemStack(Blocks.SHULKER_BOX, 1);
/*     */         
/* 190 */         if (itemstack.hasTag()) {
/* 191 */           itemstack2.setTag(itemstack.getTag().clone());
/*     */         }
/*     */         
/* 194 */         entityhuman.a(enumhand, itemstack2);
/* 195 */         a(world, blockposition, iblockdata, i - 1);
/* 196 */         entityhuman.a(StatisticList.CLEAN_SHULKER_BOX);
/* 197 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/* 199 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/*     */     
/* 202 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {
/* 211 */     changeLevel(world, blockposition, iblockdata, i, (Entity)null, CauldronLevelChangeEvent.ChangeReason.UNKNOWN);
/*     */   }
/*     */   
/*     */   private boolean changeLevel(World world, BlockPosition blockposition, IBlockData iblockdata, int i, Entity entity, CauldronLevelChangeEvent.ChangeReason reason) {
/* 215 */     int newLevel = Integer.valueOf(MathHelper.clamp(i, 0, 3)).intValue();
/*     */ 
/*     */     
/* 218 */     CauldronLevelChangeEvent event = new CauldronLevelChangeEvent(world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), (entity == null) ? null : (Entity)entity.getBukkitEntity(), reason, ((Integer)iblockdata.get(LEVEL)).intValue(), newLevel);
/*     */     
/* 220 */     world.getServer().getPluginManager().callEvent((Event)event);
/* 221 */     if (event.isCancelled()) {
/* 222 */       return false;
/*     */     }
/* 224 */     world.setTypeAndData(blockposition, iblockdata.set(LEVEL, Integer.valueOf(event.getNewLevel())), 2);
/* 225 */     world.updateAdjacentComparators(blockposition, this);
/* 226 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(World world, BlockPosition blockposition) {
/* 232 */     if (world.random.nextInt(20) == 1) {
/* 233 */       float f = world.getBiome(blockposition).getAdjustedTemperature(blockposition);
/*     */       
/* 235 */       if (f >= 0.15F) {
/* 236 */         IBlockData iblockdata = world.getType(blockposition);
/*     */         
/* 238 */         if (((Integer)iblockdata.get(LEVEL)).intValue() < 3) {
/* 239 */           a(world, blockposition, iblockdata.a(LEVEL), 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 248 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 253 */     return ((Integer)iblockdata.get(LEVEL)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 258 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { LEVEL });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 263 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCauldron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */