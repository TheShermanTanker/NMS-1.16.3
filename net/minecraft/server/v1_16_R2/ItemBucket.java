/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.DummyGeneratorAccess;
/*     */ import org.bukkit.event.player.PlayerBucketEmptyEvent;
/*     */ import org.bukkit.event.player.PlayerBucketFillEvent;
/*     */ 
/*     */ 
/*     */ public class ItemBucket
/*     */   extends Item
/*     */ {
/*     */   public final FluidType fluidType;
/*     */   
/*     */   public ItemBucket(FluidType fluidtype, Item.Info item_info) {
/*  17 */     super(item_info);
/*  18 */     this.fluidType = fluidtype;
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/*  23 */     ItemStack itemstack = entityhuman.b(enumhand);
/*  24 */     MovingObjectPositionBlock movingobjectpositionblock = a(world, entityhuman, (this.fluidType == FluidTypes.EMPTY) ? RayTrace.FluidCollisionOption.SOURCE_ONLY : RayTrace.FluidCollisionOption.NONE);
/*     */     
/*  26 */     if (movingobjectpositionblock.getType() == MovingObjectPosition.EnumMovingObjectType.MISS)
/*  27 */       return InteractionResultWrapper.pass(itemstack); 
/*  28 */     if (movingobjectpositionblock.getType() != MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/*  29 */       return InteractionResultWrapper.pass(itemstack);
/*     */     }
/*  31 */     MovingObjectPositionBlock movingobjectpositionblock1 = movingobjectpositionblock;
/*  32 */     BlockPosition blockposition = movingobjectpositionblock1.getBlockPosition();
/*  33 */     EnumDirection enumdirection = movingobjectpositionblock1.getDirection();
/*  34 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */     
/*  36 */     if (world.a(entityhuman, blockposition) && entityhuman.a(blockposition1, enumdirection, itemstack)) {
/*     */ 
/*     */       
/*  39 */       if (this.fluidType == FluidTypes.EMPTY) {
/*  40 */         IBlockData iBlockData = world.getType(blockposition);
/*  41 */         if (iBlockData.getBlock() instanceof IFluidSource) {
/*     */           
/*  43 */           FluidType dummyFluid = ((IFluidSource)iBlockData.getBlock()).removeFluid(DummyGeneratorAccess.INSTANCE, blockposition, iBlockData);
/*  44 */           PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(world, entityhuman, blockposition, blockposition, movingobjectpositionblock.getDirection(), itemstack, dummyFluid.a(), enumhand);
/*     */           
/*  46 */           if (event.isCancelled()) {
/*  47 */             ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutBlockChange(world, blockposition));
/*  48 */             ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*  49 */             return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */           } 
/*     */           
/*  52 */           FluidType fluidtype = ((IFluidSource)iBlockData.getBlock()).removeFluid(world, blockposition, iBlockData);
/*     */           
/*  54 */           if (fluidtype != FluidTypes.EMPTY) {
/*  55 */             entityhuman.b(StatisticList.ITEM_USED.b(this));
/*  56 */             entityhuman.playSound(fluidtype.a(TagsFluid.LAVA) ? SoundEffects.ITEM_BUCKET_FILL_LAVA : SoundEffects.ITEM_BUCKET_FILL, 1.0F, 1.0F);
/*  57 */             ItemStack itemstack1 = ItemLiquidUtil.a(itemstack, entityhuman, CraftItemStack.asNMSCopy(event.getItemStack()));
/*     */             
/*  59 */             if (!world.isClientSide) {
/*  60 */               CriterionTriggers.j.a((EntityPlayer)entityhuman, new ItemStack(fluidtype.a()));
/*     */             }
/*     */             
/*  63 */             return InteractionResultWrapper.a(itemstack1, world.s_());
/*     */           } 
/*     */         } 
/*     */         
/*  67 */         return InteractionResultWrapper.fail(itemstack);
/*     */       } 
/*  69 */       IBlockData iblockdata = world.getType(blockposition);
/*  70 */       BlockPosition blockposition2 = (iblockdata.getBlock() instanceof IFluidContainer && this.fluidType == FluidTypes.WATER) ? blockposition : blockposition1;
/*     */       
/*  72 */       if (a(entityhuman, world, blockposition2, movingobjectpositionblock1, movingobjectpositionblock1.getDirection(), blockposition, itemstack, enumhand)) {
/*  73 */         a(world, itemstack, blockposition2);
/*  74 */         if (entityhuman instanceof EntityPlayer) {
/*  75 */           CriterionTriggers.y.a((EntityPlayer)entityhuman, blockposition2, itemstack);
/*     */         }
/*     */         
/*  78 */         entityhuman.b(StatisticList.ITEM_USED.b(this));
/*  79 */         return InteractionResultWrapper.a(a(itemstack, entityhuman), world.s_());
/*     */       } 
/*  81 */       return InteractionResultWrapper.fail(itemstack);
/*     */     } 
/*     */ 
/*     */     
/*  85 */     return InteractionResultWrapper.fail(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack a(ItemStack itemstack, EntityHuman entityhuman) {
/*  91 */     return !entityhuman.abilities.canInstantlyBuild ? new ItemStack(Items.BUCKET) : itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, ItemStack itemstack, BlockPosition blockposition) {}
/*     */   
/*     */   public boolean a(@Nullable EntityHuman entityhuman, World world, BlockPosition blockposition, @Nullable MovingObjectPositionBlock movingobjectpositionblock) {
/*  98 */     return a(entityhuman, world, blockposition, movingobjectpositionblock, null, null, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, World world, BlockPosition blockposition, @Nullable MovingObjectPositionBlock movingobjectpositionblock, EnumDirection enumdirection, BlockPosition clicked, ItemStack itemstack, EnumHand enumhand) {
/* 104 */     if (!(this.fluidType instanceof FluidTypeFlowing)) {
/* 105 */       return false;
/*     */     }
/* 107 */     IBlockData iblockdata = world.getType(blockposition);
/* 108 */     Block block = iblockdata.getBlock();
/* 109 */     Material material = iblockdata.getMaterial();
/* 110 */     boolean flag = iblockdata.a(this.fluidType);
/* 111 */     boolean flag1 = (iblockdata.isAir() || flag || (block instanceof IFluidContainer && ((IFluidContainer)block).canPlace(world, blockposition, iblockdata, this.fluidType)));
/*     */ 
/*     */     
/* 114 */     if (flag1 && entityhuman != null) {
/* 115 */       PlayerBucketEmptyEvent event = CraftEventFactory.callPlayerBucketEmptyEvent(world, entityhuman, blockposition, clicked, enumdirection, itemstack, enumhand);
/* 116 */       if (event.isCancelled()) {
/* 117 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutBlockChange(world, blockposition));
/* 118 */         ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/* 119 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     if (!flag1)
/* 124 */       return (movingobjectpositionblock != null && a(entityhuman, world, movingobjectpositionblock.getBlockPosition().shift(movingobjectpositionblock.getDirection()), (MovingObjectPositionBlock)null, enumdirection, clicked, itemstack, enumhand)); 
/* 125 */     if (world.getDimensionManager().isNether() && this.fluidType.a(TagsFluid.WATER)) {
/* 126 */       int i = blockposition.getX();
/* 127 */       int j = blockposition.getY();
/* 128 */       int k = blockposition.getZ();
/*     */       
/* 130 */       world.playSound(entityhuman, blockposition, SoundEffects.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
/*     */       
/* 132 */       for (int l = 0; l < 8; l++) {
/* 133 */         world.addParticle(Particles.LARGE_SMOKE, i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */       
/* 136 */       return true;
/* 137 */     }  if (block instanceof IFluidContainer && this.fluidType == FluidTypes.WATER) {
/* 138 */       ((IFluidContainer)block).place(world, blockposition, iblockdata, ((FluidTypeFlowing)this.fluidType).a(false));
/* 139 */       a(entityhuman, world, blockposition);
/* 140 */       return true;
/*     */     } 
/* 142 */     if (!world.isClientSide && flag && !material.isLiquid()) {
/* 143 */       world.b(blockposition, true);
/*     */     }
/*     */     
/* 146 */     if (!world.setTypeAndData(blockposition, this.fluidType.h().getBlockData(), 11) && !iblockdata.getFluid().isSource()) {
/* 147 */       return false;
/*     */     }
/* 149 */     a(entityhuman, world, blockposition);
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(@Nullable EntityHuman entityhuman, GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 157 */     SoundEffect soundeffect = this.fluidType.a(TagsFluid.LAVA) ? SoundEffects.ITEM_BUCKET_EMPTY_LAVA : SoundEffects.ITEM_BUCKET_EMPTY;
/*     */     
/* 159 */     generatoraccess.playSound(entityhuman, blockposition, soundeffect, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */