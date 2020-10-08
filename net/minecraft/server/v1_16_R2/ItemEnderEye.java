/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class ItemEnderEye
/*     */   extends Item {
/*     */   public ItemEnderEye(Item.Info item_info) {
/*   6 */     super(item_info);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/*  11 */     World world = itemactioncontext.getWorld();
/*  12 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/*  13 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  15 */     if (iblockdata.a(Blocks.END_PORTAL_FRAME) && !((Boolean)iblockdata.get(BlockEnderPortalFrame.EYE)).booleanValue()) {
/*  16 */       if (world.isClientSide) {
/*  17 */         return EnumInteractionResult.SUCCESS;
/*     */       }
/*  19 */       IBlockData iblockdata1 = iblockdata.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(true));
/*     */       
/*  21 */       Block.a(iblockdata, iblockdata1, world, blockposition);
/*  22 */       world.setTypeAndData(blockposition, iblockdata1, 2);
/*  23 */       world.updateAdjacentComparators(blockposition, Blocks.END_PORTAL_FRAME);
/*  24 */       itemactioncontext.getItemStack().subtract(1);
/*  25 */       world.triggerEffect(1503, blockposition, 0);
/*  26 */       ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = BlockEnderPortalFrame.c().a(world, blockposition);
/*     */       
/*  28 */       if (shapedetector_shapedetectorcollection != null) {
/*  29 */         BlockPosition blockposition1 = shapedetector_shapedetectorcollection.a().b(-3, 0, -3);
/*     */         
/*  31 */         for (int i = 0; i < 3; i++) {
/*  32 */           for (int j = 0; j < 3; j++) {
/*  33 */             world.setTypeAndData(blockposition1.b(i, 0, j), Blocks.END_PORTAL.getBlockData(), 2);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  39 */         int viewDistance = world.getServer().getViewDistance() * 16;
/*  40 */         BlockPosition soundPos = blockposition1.b(1, 0, 1);
/*  41 */         for (EntityPlayer player : (world.getServer().getServer().getPlayerList()).players) {
/*  42 */           double deltaX = soundPos.getX() - player.locX();
/*  43 */           double deltaZ = soundPos.getZ() - player.locZ();
/*  44 */           double distanceSquared = deltaX * deltaX + deltaZ * deltaZ;
/*  45 */           if (world.spigotConfig.endPortalSoundRadius > 0 && distanceSquared > (world.spigotConfig.endPortalSoundRadius * world.spigotConfig.endPortalSoundRadius))
/*  46 */             continue;  if (distanceSquared > (viewDistance * viewDistance)) {
/*  47 */             double deltaLength = Math.sqrt(distanceSquared);
/*  48 */             double relativeX = player.locX() + deltaX / deltaLength * viewDistance;
/*  49 */             double relativeZ = player.locZ() + deltaZ / deltaLength * viewDistance;
/*  50 */             player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1038, new BlockPosition((int)relativeX, soundPos.getY(), (int)relativeZ), 0, true)); continue;
/*     */           } 
/*  52 */           player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1038, soundPos, 0, true));
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  58 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/*     */     
/*  61 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/*  67 */     ItemStack itemstack = entityhuman.b(enumhand);
/*  68 */     MovingObjectPositionBlock movingobjectpositionblock = a(world, entityhuman, RayTrace.FluidCollisionOption.NONE);
/*     */     
/*  70 */     if (movingobjectpositionblock.getType() == MovingObjectPosition.EnumMovingObjectType.BLOCK && world.getType(movingobjectpositionblock.getBlockPosition()).a(Blocks.END_PORTAL_FRAME)) {
/*  71 */       return InteractionResultWrapper.pass(itemstack);
/*     */     }
/*  73 */     entityhuman.c(enumhand);
/*  74 */     if (world instanceof WorldServer) {
/*  75 */       BlockPosition blockposition = ((WorldServer)world).getChunkProvider().getChunkGenerator().findNearestMapFeature((WorldServer)world, StructureGenerator.STRONGHOLD, entityhuman.getChunkCoordinates(), 100, false);
/*     */       
/*  77 */       if (blockposition != null) {
/*  78 */         EntityEnderSignal entityendersignal = new EntityEnderSignal(world, entityhuman.locX(), entityhuman.e(0.5D), entityhuman.locZ());
/*     */         
/*  80 */         entityendersignal.setItem(itemstack);
/*  81 */         entityendersignal.a(blockposition);
/*     */         
/*  83 */         if (!world.addEntity(entityendersignal)) {
/*  84 */           return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */         }
/*     */         
/*  87 */         if (entityhuman instanceof EntityPlayer) {
/*  88 */           CriterionTriggers.m.a((EntityPlayer)entityhuman, blockposition);
/*     */         }
/*     */         
/*  91 */         world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
/*  92 */         world.a((EntityHuman)null, 1003, entityhuman.getChunkCoordinates(), 0);
/*  93 */         if (!entityhuman.abilities.canInstantlyBuild) {
/*  94 */           itemstack.subtract(1);
/*     */         }
/*     */         
/*  97 */         entityhuman.b(StatisticList.ITEM_USED.b(this));
/*  98 */         entityhuman.swingHand(enumhand, true);
/*  99 */         return InteractionResultWrapper.success(itemstack);
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     return InteractionResultWrapper.consume(itemstack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemEnderEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */