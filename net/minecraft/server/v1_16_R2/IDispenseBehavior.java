/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.TreeType;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockDispenseEvent;
/*     */ import org.bukkit.event.world.StructureGrowEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public interface IDispenseBehavior {
/*     */   static {
/*  18 */     NONE = ((isourceblock, itemstack) -> itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final IDispenseBehavior NONE;
/*     */   
/*     */   static void c() {
/*  25 */     BlockDispenser.a(Items.ARROW, new DispenseBehaviorProjectile()
/*     */         {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  28 */             EntityTippedArrow entitytippedarrow = new EntityTippedArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */             
/*  30 */             entitytippedarrow.fromPlayer = EntityArrow.PickupStatus.ALLOWED;
/*  31 */             return entitytippedarrow;
/*     */           }
/*     */         });
/*  34 */     BlockDispenser.a(Items.TIPPED_ARROW, new DispenseBehaviorProjectile()
/*     */         {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  37 */             EntityTippedArrow entitytippedarrow = new EntityTippedArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */             
/*  39 */             entitytippedarrow.b(itemstack);
/*  40 */             entitytippedarrow.fromPlayer = EntityArrow.PickupStatus.ALLOWED;
/*  41 */             return entitytippedarrow;
/*     */           }
/*     */         });
/*  44 */     BlockDispenser.a(Items.SPECTRAL_ARROW, new DispenseBehaviorProjectile()
/*     */         {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  47 */             EntitySpectralArrow entityspectralarrow = new EntitySpectralArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */             
/*  49 */             entityspectralarrow.fromPlayer = EntityArrow.PickupStatus.ALLOWED;
/*  50 */             return entityspectralarrow;
/*     */           }
/*     */         });
/*  53 */     BlockDispenser.a(Items.EGG, new DispenseBehaviorProjectile()
/*     */         {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  56 */             return SystemUtils.<IProjectile>a(new EntityEgg(world, iposition.getX(), iposition.getY(), iposition.getZ()), entityegg -> entityegg.setItem(itemstack));
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  61 */     BlockDispenser.a(Items.SNOWBALL, new DispenseBehaviorProjectile()
/*     */         {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  64 */             return SystemUtils.<IProjectile>a(new EntitySnowball(world, iposition.getX(), iposition.getY(), iposition.getZ()), entitysnowball -> entitysnowball.setItem(itemstack));
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  69 */     BlockDispenser.a(Items.EXPERIENCE_BOTTLE, new DispenseBehaviorProjectile()
/*     */         {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  72 */             return SystemUtils.<IProjectile>a(new EntityThrownExpBottle(world, iposition.getX(), iposition.getY(), iposition.getZ()), entitythrownexpbottle -> entitythrownexpbottle.setItem(itemstack));
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           protected float a() {
/*  79 */             return super.a() * 0.5F;
/*     */           }
/*     */ 
/*     */           
/*     */           protected float getPower() {
/*  84 */             return super.getPower() * 1.25F;
/*     */           }
/*     */         });
/*  87 */     BlockDispenser.a(Items.SPLASH_POTION, new IDispenseBehavior()
/*     */         {
/*     */           public ItemStack dispense(ISourceBlock isourceblock, ItemStack itemstack) {
/*  90 */             return (new DispenseBehaviorProjectile()
/*     */               {
/*     */                 protected IProjectile a(World world, IPosition iposition, ItemStack itemstack1) {
/*  93 */                   return SystemUtils.<IProjectile>a(new EntityPotion(world, iposition.getX(), iposition.getY(), iposition.getZ()), entitypotion -> entitypotion.setItem(itemstack1));
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 protected float a() {
/* 100 */                   return super.a() * 0.5F;
/*     */                 }
/*     */ 
/*     */                 
/*     */                 protected float getPower() {
/* 105 */                   return super.getPower() * 1.25F;
/*     */                 }
/* 107 */               }).dispense(isourceblock, itemstack);
/*     */           }
/*     */         });
/* 110 */     BlockDispenser.a(Items.LINGERING_POTION, new IDispenseBehavior()
/*     */         {
/*     */           public ItemStack dispense(ISourceBlock isourceblock, ItemStack itemstack) {
/* 113 */             return (new DispenseBehaviorProjectile()
/*     */               {
/*     */                 protected IProjectile a(World world, IPosition iposition, ItemStack itemstack1) {
/* 116 */                   return SystemUtils.<IProjectile>a(new EntityPotion(world, iposition.getX(), iposition.getY(), iposition.getZ()), entitypotion -> entitypotion.setItem(itemstack1));
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 protected float a() {
/* 123 */                   return super.a() * 0.5F;
/*     */                 }
/*     */ 
/*     */                 
/*     */                 protected float getPower() {
/* 128 */                   return super.getPower() * 1.25F;
/*     */                 }
/* 130 */               }).dispense(isourceblock, itemstack);
/*     */           }
/*     */         });
/* 133 */     DispenseBehaviorItem dispensebehavioritem = new DispenseBehaviorItem()
/*     */       {
/*     */         public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 136 */           EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 137 */           EntityTypes<?> entitytypes = ((ItemMonsterEgg)itemstack.getItem()).a(itemstack.getTag());
/*     */ 
/*     */           
/* 140 */           WorldServer worldserver = isourceblock.getWorld();
/* 141 */           ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 142 */           Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 143 */           CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */           
/* 145 */           BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(0, 0, 0));
/* 146 */           if (!BlockDispenser.eventFired) {
/* 147 */             worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */           }
/*     */           
/* 150 */           if (event.isCancelled()) {
/* 151 */             itemstack.add(1);
/* 152 */             return itemstack;
/*     */           } 
/*     */           
/* 155 */           if (!event.getItem().equals(craftItem)) {
/* 156 */             itemstack.add(1);
/*     */             
/* 158 */             ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 159 */             IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 160 */             if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 161 */               idispensebehavior.dispense(isourceblock, eventStack);
/* 162 */               return itemstack;
/*     */             } 
/*     */           } 
/*     */           
/*     */           try {
/* 167 */             entitytypes.spawnCreature(isourceblock.getWorld(), itemstack, (EntityHuman)null, isourceblock.getBlockPosition().shift(enumdirection), EnumMobSpawn.DISPENSER, (enumdirection != EnumDirection.UP), false);
/*     */           }
/* 169 */           catch (Exception ex) {
/* 170 */             MinecraftServer.LOGGER.warn("An exception occurred dispensing entity at {}[{}]", worldserver.getWorld().getName(), isourceblock.getBlockPosition(), ex);
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 176 */           return itemstack;
/*     */         }
/*     */       };
/* 179 */     Iterator<ItemMonsterEgg> iterator = ItemMonsterEgg.f().iterator();
/*     */     
/* 181 */     while (iterator.hasNext()) {
/* 182 */       ItemMonsterEgg itemmonsteregg = iterator.next();
/*     */       
/* 184 */       BlockDispenser.a(itemmonsteregg, dispensebehavioritem);
/*     */     } 
/*     */     
/* 187 */     BlockDispenser.a(Items.ARMOR_STAND, new DispenseBehaviorItem()
/*     */         {
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 190 */             EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 191 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/* 192 */             WorldServer worldserver = isourceblock.getWorld();
/*     */ 
/*     */             
/* 195 */             ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 196 */             Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 197 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */             
/* 199 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(0, 0, 0));
/* 200 */             if (!BlockDispenser.eventFired) {
/* 201 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 204 */             if (event.isCancelled()) {
/* 205 */               itemstack.add(1);
/* 206 */               return itemstack;
/*     */             } 
/*     */             
/* 209 */             if (!event.getItem().equals(craftItem)) {
/* 210 */               itemstack.add(1);
/*     */               
/* 212 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 213 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 214 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 215 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 216 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 221 */             EntityArmorStand entityarmorstand = new EntityArmorStand(worldserver, blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D);
/*     */             
/* 223 */             EntityTypes.a(worldserver, (EntityHuman)null, entityarmorstand, itemstack.getTag());
/* 224 */             entityarmorstand.yaw = enumdirection.o();
/* 225 */             worldserver.addEntity(entityarmorstand);
/*     */             
/* 227 */             return itemstack;
/*     */           }
/*     */         });
/* 230 */     BlockDispenser.a(Items.SADDLE, new DispenseBehaviorMaybe()
/*     */         {
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 233 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/* 234 */             List<EntityLiving> list = isourceblock.getWorld().a(EntityLiving.class, new AxisAlignedBB(blockposition), entityliving -> {
/*     */                   if (!(entityliving instanceof ISaddleable)) {
/*     */                     return false;
/*     */                   }
/*     */                   
/*     */                   ISaddleable isaddleable = (ISaddleable)entityliving;
/* 240 */                   return (!isaddleable.hasSaddle() && isaddleable.canSaddle());
/*     */                 });
/*     */ 
/*     */             
/* 244 */             if (!list.isEmpty()) {
/* 245 */               ((ISaddleable)list.get(0)).saddle(SoundCategory.BLOCKS);
/* 246 */               itemstack.subtract(1);
/* 247 */               a(true);
/* 248 */               return itemstack;
/*     */             } 
/* 250 */             return super.a(isourceblock, itemstack);
/*     */           }
/*     */         });
/*     */     
/* 254 */     DispenseBehaviorMaybe dispensebehaviormaybe = new DispenseBehaviorMaybe() {
/*     */         protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/*     */           EntityHorseAbstract entityhorseabstract;
/* 257 */           BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/* 258 */           List<EntityHorseAbstract> list = isourceblock.getWorld().a(EntityHorseAbstract.class, new AxisAlignedBB(blockposition), entityhorseabstract -> 
/* 259 */               (entityhorseabstract.isAlive() && entityhorseabstract.fs()));
/*     */           
/* 261 */           Iterator<EntityHorseAbstract> iterator1 = list.iterator();
/*     */ 
/*     */ 
/*     */           
/*     */           do {
/* 266 */             if (!iterator1.hasNext()) {
/* 267 */               return super.a(isourceblock, itemstack);
/*     */             }
/*     */             
/* 270 */             entityhorseabstract = iterator1.next();
/* 271 */           } while (!entityhorseabstract.l(itemstack) || entityhorseabstract.ft() || !entityhorseabstract.isTamed());
/*     */           
/* 273 */           entityhorseabstract.a_(401, itemstack.cloneAndSubtract(1));
/* 274 */           a(true);
/* 275 */           return itemstack;
/*     */         }
/*     */       };
/*     */     
/* 279 */     BlockDispenser.a(Items.LEATHER_HORSE_ARMOR, dispensebehaviormaybe);
/* 280 */     BlockDispenser.a(Items.IRON_HORSE_ARMOR, dispensebehaviormaybe);
/* 281 */     BlockDispenser.a(Items.GOLDEN_HORSE_ARMOR, dispensebehaviormaybe);
/* 282 */     BlockDispenser.a(Items.DIAMOND_HORSE_ARMOR, dispensebehaviormaybe);
/* 283 */     BlockDispenser.a(Items.fM, dispensebehaviormaybe);
/* 284 */     BlockDispenser.a(Items.fN, dispensebehaviormaybe);
/* 285 */     BlockDispenser.a(Items.fV, dispensebehaviormaybe);
/* 286 */     BlockDispenser.a(Items.fX, dispensebehaviormaybe);
/* 287 */     BlockDispenser.a(Items.fY, dispensebehaviormaybe);
/* 288 */     BlockDispenser.a(Items.gb, dispensebehaviormaybe);
/* 289 */     BlockDispenser.a(Items.fT, dispensebehaviormaybe);
/* 290 */     BlockDispenser.a(Items.fZ, dispensebehaviormaybe);
/* 291 */     BlockDispenser.a(Items.fP, dispensebehaviormaybe);
/* 292 */     BlockDispenser.a(Items.fU, dispensebehaviormaybe);
/* 293 */     BlockDispenser.a(Items.fR, dispensebehaviormaybe);
/* 294 */     BlockDispenser.a(Items.fO, dispensebehaviormaybe);
/* 295 */     BlockDispenser.a(Items.fS, dispensebehaviormaybe);
/* 296 */     BlockDispenser.a(Items.fW, dispensebehaviormaybe);
/* 297 */     BlockDispenser.a(Items.ga, dispensebehaviormaybe);
/* 298 */     BlockDispenser.a(Items.fQ, dispensebehaviormaybe);
/* 299 */     BlockDispenser.a(Items.cy, new DispenseBehaviorMaybe() {
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/*     */             EntityHorseChestedAbstract entityhorsechestedabstract;
/* 302 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/* 303 */             List<EntityHorseChestedAbstract> list = isourceblock.getWorld().a(EntityHorseChestedAbstract.class, new AxisAlignedBB(blockposition), entityhorsechestedabstract -> 
/* 304 */                 (entityhorsechestedabstract.isAlive() && !entityhorsechestedabstract.isCarryingChest()));
/*     */             
/* 306 */             Iterator<EntityHorseChestedAbstract> iterator1 = list.iterator();
/*     */ 
/*     */ 
/*     */             
/*     */             do {
/* 311 */               if (!iterator1.hasNext()) {
/* 312 */                 return super.a(isourceblock, itemstack);
/*     */               }
/*     */               
/* 315 */               entityhorsechestedabstract = iterator1.next();
/* 316 */             } while (!entityhorsechestedabstract.isTamed() || !entityhorsechestedabstract.a_(499, itemstack));
/*     */             
/* 318 */             itemstack.subtract(1);
/* 319 */             a(true);
/* 320 */             return itemstack;
/*     */           }
/*     */         });
/* 323 */     BlockDispenser.a(Items.FIREWORK_ROCKET, new DispenseBehaviorItem()
/*     */         {
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 326 */             EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/*     */             
/* 328 */             WorldServer worldserver = isourceblock.getWorld();
/* 329 */             ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 330 */             Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 331 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */             
/* 333 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(enumdirection.getAdjacentX(), enumdirection.getAdjacentY(), enumdirection.getAdjacentZ()));
/* 334 */             if (!BlockDispenser.eventFired) {
/* 335 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 338 */             if (event.isCancelled()) {
/* 339 */               itemstack.add(1);
/* 340 */               return itemstack;
/*     */             } 
/*     */             
/* 343 */             if (!event.getItem().equals(craftItem)) {
/* 344 */               itemstack.add(1);
/*     */               
/* 346 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 347 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 348 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 349 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 350 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 354 */             itemstack1 = CraftItemStack.asNMSCopy(event.getItem());
/* 355 */             EntityFireworks entityfireworks = new EntityFireworks(isourceblock.getWorld(), itemstack1, isourceblock.getX(), isourceblock.getY(), isourceblock.getX(), true);
/*     */             
/* 357 */             IDispenseBehavior.a(isourceblock, entityfireworks, enumdirection);
/* 358 */             entityfireworks.shoot(enumdirection.getAdjacentX(), enumdirection.getAdjacentY(), enumdirection.getAdjacentZ(), 0.5F, 1.0F);
/* 359 */             isourceblock.getWorld().addEntity(entityfireworks);
/*     */ 
/*     */             
/* 362 */             return itemstack;
/*     */           }
/*     */ 
/*     */           
/*     */           protected void a(ISourceBlock isourceblock) {
/* 367 */             isourceblock.getWorld().triggerEffect(1004, isourceblock.getBlockPosition(), 0);
/*     */           }
/*     */         });
/* 370 */     BlockDispenser.a(Items.FIRE_CHARGE, new DispenseBehaviorItem()
/*     */         {
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 373 */             EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 374 */             IPosition iposition = BlockDispenser.a(isourceblock);
/* 375 */             double d0 = iposition.getX() + (enumdirection.getAdjacentX() * 0.3F);
/* 376 */             double d1 = iposition.getY() + (enumdirection.getAdjacentY() * 0.3F);
/* 377 */             double d2 = iposition.getZ() + (enumdirection.getAdjacentZ() * 0.3F);
/* 378 */             WorldServer worldserver = isourceblock.getWorld();
/* 379 */             Random random = worldserver.random;
/* 380 */             double d3 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentX();
/* 381 */             double d4 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentY();
/* 382 */             double d5 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentZ();
/*     */ 
/*     */             
/* 385 */             ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 386 */             Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 387 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */             
/* 389 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(d3, d4, d5));
/* 390 */             if (!BlockDispenser.eventFired) {
/* 391 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 394 */             if (event.isCancelled()) {
/* 395 */               itemstack.add(1);
/* 396 */               return itemstack;
/*     */             } 
/*     */             
/* 399 */             if (!event.getItem().equals(craftItem)) {
/* 400 */               itemstack.add(1);
/*     */               
/* 402 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 403 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 404 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 405 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 406 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 410 */             EntitySmallFireball entitysmallfireball = new EntitySmallFireball(worldserver, d0, d1, d2, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ());
/* 411 */             entitysmallfireball.setItem(itemstack1);
/* 412 */             entitysmallfireball.projectileSource = (ProjectileSource)new CraftBlockProjectileSource(isourceblock.<TileEntityDispenser>getTileEntity());
/*     */             
/* 414 */             worldserver.addEntity(entitysmallfireball);
/*     */ 
/*     */             
/* 417 */             return itemstack;
/*     */           }
/*     */ 
/*     */           
/*     */           protected void a(ISourceBlock isourceblock) {
/* 422 */             isourceblock.getWorld().triggerEffect(1018, isourceblock.getBlockPosition(), 0);
/*     */           }
/*     */         });
/* 425 */     BlockDispenser.a(Items.OAK_BOAT, new DispenseBehaviorBoat(EntityBoat.EnumBoatType.OAK));
/* 426 */     BlockDispenser.a(Items.SPRUCE_BOAT, new DispenseBehaviorBoat(EntityBoat.EnumBoatType.SPRUCE));
/* 427 */     BlockDispenser.a(Items.BIRCH_BOAT, new DispenseBehaviorBoat(EntityBoat.EnumBoatType.BIRCH));
/* 428 */     BlockDispenser.a(Items.JUNGLE_BOAT, new DispenseBehaviorBoat(EntityBoat.EnumBoatType.JUNGLE));
/* 429 */     BlockDispenser.a(Items.DARK_OAK_BOAT, new DispenseBehaviorBoat(EntityBoat.EnumBoatType.DARK_OAK));
/* 430 */     BlockDispenser.a(Items.ACACIA_BOAT, new DispenseBehaviorBoat(EntityBoat.EnumBoatType.ACACIA));
/* 431 */     DispenseBehaviorItem dispensebehavioritem1 = new DispenseBehaviorItem() {
/* 432 */         private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*     */ 
/*     */         
/*     */         public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 436 */           ItemBucket itembucket = (ItemBucket)itemstack.getItem();
/* 437 */           BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/* 438 */           WorldServer worldserver = isourceblock.getWorld();
/*     */ 
/*     */           
/* 441 */           int x = blockposition.getX();
/* 442 */           int y = blockposition.getY();
/* 443 */           int z = blockposition.getZ();
/* 444 */           IBlockData iblockdata = worldserver.getType(blockposition);
/* 445 */           Material material = iblockdata.getMaterial();
/* 446 */           if (worldserver.isEmpty(blockposition) || !material.isBuildable() || material.isReplaceable() || (iblockdata.getBlock() instanceof IFluidContainer && ((IFluidContainer)iblockdata.getBlock()).canPlace(worldserver, blockposition, iblockdata, itembucket.fluidType))) {
/* 447 */             Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 448 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 450 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(x, y, z));
/* 451 */             if (!BlockDispenser.eventFired) {
/* 452 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 455 */             if (event.isCancelled()) {
/* 456 */               return itemstack;
/*     */             }
/*     */             
/* 459 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 461 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 462 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 463 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 464 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 465 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 469 */             itembucket = (ItemBucket)CraftItemStack.asNMSCopy(event.getItem()).getItem();
/*     */           } 
/*     */ 
/*     */           
/* 473 */           if (itembucket.a((EntityHuman)null, worldserver, blockposition, (MovingObjectPositionBlock)null)) {
/* 474 */             itembucket.a(worldserver, itemstack, blockposition);
/*     */             
/* 476 */             Item item = Items.BUCKET;
/* 477 */             itemstack.subtract(1);
/* 478 */             if (itemstack.isEmpty()) {
/* 479 */               itemstack.setItem(Items.BUCKET);
/* 480 */               itemstack.setCount(1);
/* 481 */             } else if (((TileEntityDispenser)isourceblock.<TileEntityDispenser>getTileEntity()).addItem(new ItemStack(item)) < 0) {
/* 482 */               this.b.dispense(isourceblock, new ItemStack(item));
/*     */             } 
/*     */             
/* 485 */             return itemstack;
/*     */           } 
/* 487 */           return this.b.dispense(isourceblock, itemstack);
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 492 */     BlockDispenser.a(Items.LAVA_BUCKET, dispensebehavioritem1);
/* 493 */     BlockDispenser.a(Items.WATER_BUCKET, dispensebehavioritem1);
/* 494 */     BlockDispenser.a(Items.SALMON_BUCKET, dispensebehavioritem1);
/* 495 */     BlockDispenser.a(Items.COD_BUCKET, dispensebehavioritem1);
/* 496 */     BlockDispenser.a(Items.PUFFERFISH_BUCKET, dispensebehavioritem1);
/* 497 */     BlockDispenser.a(Items.TROPICAL_FISH_BUCKET, dispensebehavioritem1);
/* 498 */     BlockDispenser.a(Items.BUCKET, new DispenseBehaviorItem() {
/* 499 */           private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*     */ 
/*     */           
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 503 */             WorldServer worldserver = isourceblock.getWorld();
/* 504 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/* 505 */             IBlockData iblockdata = worldserver.getType(blockposition);
/* 506 */             Block block = iblockdata.getBlock();
/*     */             
/* 508 */             if (block instanceof IFluidSource) {
/* 509 */               FluidType fluidtype = ((IFluidSource)block).removeFluid(DummyGeneratorAccess.INSTANCE, blockposition, iblockdata);
/*     */               
/* 511 */               if (!(fluidtype instanceof FluidTypeFlowing)) {
/* 512 */                 return super.a(isourceblock, itemstack);
/*     */               }
/* 514 */               Item item = fluidtype.a();
/*     */ 
/*     */               
/* 517 */               Block bukkitBlock = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 518 */               CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */               
/* 520 */               BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 521 */               if (!BlockDispenser.eventFired) {
/* 522 */                 worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */               }
/*     */               
/* 525 */               if (event.isCancelled()) {
/* 526 */                 return itemstack;
/*     */               }
/*     */               
/* 529 */               if (!event.getItem().equals(craftItem)) {
/*     */                 
/* 531 */                 ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 532 */                 IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 533 */                 if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 534 */                   idispensebehavior.dispense(isourceblock, eventStack);
/* 535 */                   return itemstack;
/*     */                 } 
/*     */               } 
/*     */               
/* 539 */               fluidtype = ((IFluidSource)block).removeFluid(worldserver, blockposition, iblockdata);
/*     */ 
/*     */               
/* 542 */               itemstack.subtract(1);
/* 543 */               if (itemstack.isEmpty()) {
/* 544 */                 return new ItemStack(item);
/*     */               }
/* 546 */               if (((TileEntityDispenser)isourceblock.<TileEntityDispenser>getTileEntity()).addItem(new ItemStack(item)) < 0) {
/* 547 */                 this.b.dispense(isourceblock, new ItemStack(item));
/*     */               }
/*     */               
/* 550 */               return itemstack;
/*     */             } 
/*     */ 
/*     */             
/* 554 */             return super.a(isourceblock, itemstack);
/*     */           }
/*     */         });
/*     */     
/* 558 */     BlockDispenser.a(Items.FLINT_AND_STEEL, new DispenseBehaviorMaybe()
/*     */         {
/*     */           protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 561 */             WorldServer worldserver = isourceblock.getWorld();
/*     */ 
/*     */             
/* 564 */             Block bukkitBlock = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 565 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 567 */             BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(0, 0, 0));
/* 568 */             if (!BlockDispenser.eventFired) {
/* 569 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 572 */             if (event.isCancelled()) {
/* 573 */               return itemstack;
/*     */             }
/*     */             
/* 576 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 578 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 579 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 580 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 581 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 582 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 587 */             a(true);
/* 588 */             EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 589 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/* 590 */             IBlockData iblockdata = worldserver.getType(blockposition);
/*     */             
/* 592 */             if (BlockFireAbstract.a(worldserver, blockposition, enumdirection)) {
/*     */               
/* 594 */               if (!CraftEventFactory.callBlockIgniteEvent(worldserver, blockposition, isourceblock.getBlockPosition()).isCancelled()) {
/* 595 */                 worldserver.setTypeUpdate(blockposition, BlockFireAbstract.a(worldserver, blockposition));
/*     */               }
/*     */             }
/* 598 */             else if (BlockCampfire.h(iblockdata)) {
/* 599 */               worldserver.setTypeUpdate(blockposition, iblockdata.set(BlockProperties.r, Boolean.valueOf(true)));
/* 600 */             } else if (iblockdata.getBlock() instanceof BlockTNT) {
/* 601 */               BlockTNT.a(worldserver, blockposition);
/* 602 */               worldserver.a(blockposition, false);
/*     */             } else {
/* 604 */               a(false);
/*     */             } 
/*     */             
/* 607 */             if (a() && itemstack.isDamaged(1, worldserver.random, (EntityPlayer)null)) {
/* 608 */               itemstack.setCount(0);
/*     */             }
/*     */             
/* 611 */             return itemstack;
/*     */           }
/*     */         });
/* 614 */     BlockDispenser.a(Items.BONE_MEAL, new DispenseBehaviorMaybe()
/*     */         {
/*     */           protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 617 */             a(true);
/* 618 */             WorldServer worldserver = isourceblock.getWorld();
/* 619 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/*     */             
/* 621 */             Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 622 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 624 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(0, 0, 0));
/* 625 */             if (!BlockDispenser.eventFired) {
/* 626 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 629 */             if (event.isCancelled()) {
/* 630 */               return itemstack;
/*     */             }
/*     */             
/* 633 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 635 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 636 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 637 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 638 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 639 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 643 */             worldserver.captureTreeGeneration = true;
/*     */ 
/*     */             
/* 646 */             if (!ItemBoneMeal.a(itemstack, worldserver, blockposition) && !ItemBoneMeal.a(itemstack, worldserver, blockposition, (EnumDirection)null)) {
/* 647 */               a(false);
/* 648 */             } else if (!worldserver.isClientSide) {
/* 649 */               worldserver.triggerEffect(2005, blockposition, 0);
/*     */             } 
/*     */             
/* 652 */             worldserver.captureTreeGeneration = false;
/* 653 */             if (worldserver.capturedBlockStates.size() > 0) {
/* 654 */               TreeType treeType = BlockSapling.treeType;
/* 655 */               BlockSapling.treeType = null;
/* 656 */               Location location = new Location((World)worldserver.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 657 */               List<BlockState> blocks = new ArrayList<>((Collection)worldserver.capturedBlockStates.values());
/* 658 */               worldserver.capturedBlockStates.clear();
/* 659 */               StructureGrowEvent structureEvent = null;
/* 660 */               if (treeType != null) {
/* 661 */                 structureEvent = new StructureGrowEvent(location, treeType, false, null, blocks);
/* 662 */                 Bukkit.getPluginManager().callEvent((Event)structureEvent);
/*     */               } 
/* 664 */               if (structureEvent == null || !structureEvent.isCancelled()) {
/* 665 */                 for (BlockState blockstate : blocks) {
/* 666 */                   blockstate.update(true);
/*     */                 }
/*     */               }
/*     */             } 
/*     */ 
/*     */             
/* 672 */             return itemstack;
/*     */           }
/*     */         });
/* 675 */     BlockDispenser.a(Blocks.TNT, new DispenseBehaviorItem()
/*     */         {
/*     */           protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 678 */             WorldServer worldserver = isourceblock.getWorld();
/* 679 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/*     */ 
/*     */ 
/*     */             
/* 683 */             ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 684 */             Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 685 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */             
/* 687 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D));
/* 688 */             if (!BlockDispenser.eventFired) {
/* 689 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 692 */             if (event.isCancelled()) {
/* 693 */               itemstack.add(1);
/* 694 */               return itemstack;
/*     */             } 
/*     */             
/* 697 */             if (!event.getItem().equals(craftItem)) {
/* 698 */               itemstack.add(1);
/*     */               
/* 700 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 701 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 702 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 703 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 704 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 708 */             EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldserver, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), (EntityLiving)null);
/*     */ 
/*     */             
/* 711 */             worldserver.addEntity(entitytntprimed);
/* 712 */             worldserver.playSound((EntityHuman)null, entitytntprimed.locX(), entitytntprimed.locY(), entitytntprimed.locZ(), SoundEffects.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */             
/* 714 */             return itemstack;
/*     */           }
/*     */         });
/* 717 */     DispenseBehaviorMaybe dispensebehaviormaybe1 = new DispenseBehaviorMaybe()
/*     */       {
/*     */         protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 720 */           a(ItemArmor.a(isourceblock, itemstack));
/* 721 */           return itemstack;
/*     */         }
/*     */       };
/*     */     
/* 725 */     BlockDispenser.a(Items.CREEPER_HEAD, dispensebehaviormaybe1);
/* 726 */     BlockDispenser.a(Items.ZOMBIE_HEAD, dispensebehaviormaybe1);
/* 727 */     BlockDispenser.a(Items.DRAGON_HEAD, dispensebehaviormaybe1);
/* 728 */     BlockDispenser.a(Items.SKELETON_SKULL, dispensebehaviormaybe1);
/* 729 */     BlockDispenser.a(Items.PLAYER_HEAD, dispensebehaviormaybe1);
/* 730 */     BlockDispenser.a(Items.WITHER_SKELETON_SKULL, new DispenseBehaviorMaybe()
/*     */         {
/*     */           protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 733 */             WorldServer worldserver = isourceblock.getWorld();
/* 734 */             EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 735 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/*     */ 
/*     */             
/* 738 */             Block bukkitBlock = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 739 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 741 */             BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 742 */             if (!BlockDispenser.eventFired) {
/* 743 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 746 */             if (event.isCancelled()) {
/* 747 */               return itemstack;
/*     */             }
/*     */             
/* 750 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 752 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 753 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 754 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 755 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 756 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 761 */             if (worldserver.isEmpty(blockposition) && BlockWitherSkull.b(worldserver, blockposition, itemstack)) {
/* 762 */               worldserver.setTypeAndData(blockposition, Blocks.WITHER_SKELETON_SKULL.getBlockData().set(BlockSkull.a, Integer.valueOf((enumdirection.n() == EnumDirection.EnumAxis.Y) ? 0 : (enumdirection.opposite().get2DRotationValue() * 4))), 3);
/* 763 */               TileEntity tileentity = worldserver.getTileEntity(blockposition);
/*     */               
/* 765 */               if (tileentity instanceof TileEntitySkull) {
/* 766 */                 BlockWitherSkull.a(worldserver, blockposition, (TileEntitySkull)tileentity);
/*     */               }
/*     */               
/* 769 */               itemstack.subtract(1);
/* 770 */               a(true);
/*     */             } else {
/* 772 */               a(ItemArmor.a(isourceblock, itemstack));
/*     */             } 
/*     */             
/* 775 */             return itemstack;
/*     */           }
/*     */         });
/* 778 */     BlockDispenser.a(Blocks.CARVED_PUMPKIN, new DispenseBehaviorMaybe()
/*     */         {
/*     */           protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 781 */             WorldServer worldserver = isourceblock.getWorld();
/* 782 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/* 783 */             BlockPumpkinCarved blockpumpkincarved = (BlockPumpkinCarved)Blocks.CARVED_PUMPKIN;
/*     */ 
/*     */             
/* 786 */             Block bukkitBlock = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 787 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 789 */             BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 790 */             if (!BlockDispenser.eventFired) {
/* 791 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 794 */             if (event.isCancelled()) {
/* 795 */               return itemstack;
/*     */             }
/*     */             
/* 798 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 800 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 801 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 802 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 803 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 804 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 809 */             if (worldserver.isEmpty(blockposition) && blockpumpkincarved.a(worldserver, blockposition)) {
/* 810 */               if (!worldserver.isClientSide) {
/* 811 */                 worldserver.setTypeAndData(blockposition, blockpumpkincarved.getBlockData(), 3);
/*     */               }
/*     */               
/* 814 */               itemstack.subtract(1);
/* 815 */               a(true);
/*     */             } else {
/* 817 */               a(ItemArmor.a(isourceblock, itemstack));
/*     */             } 
/*     */             
/* 820 */             return itemstack;
/*     */           }
/*     */         });
/* 823 */     BlockDispenser.a(Blocks.SHULKER_BOX.getItem(), new DispenseBehaviorShulkerBox());
/* 824 */     EnumColor[] aenumcolor = EnumColor.values();
/* 825 */     int i = aenumcolor.length;
/*     */     
/* 827 */     for (int j = 0; j < i; j++) {
/* 828 */       EnumColor enumcolor = aenumcolor[j];
/*     */       
/* 830 */       BlockDispenser.a(BlockShulkerBox.a(enumcolor).getItem(), new DispenseBehaviorShulkerBox());
/*     */     } 
/*     */     
/* 833 */     BlockDispenser.a(Items.GLASS_BOTTLE.getItem(), new DispenseBehaviorMaybe() {
/* 834 */           private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*     */           
/*     */           private ItemStack a(ISourceBlock isourceblock, ItemStack itemstack, ItemStack itemstack1) {
/* 837 */             itemstack.subtract(1);
/* 838 */             if (itemstack.isEmpty()) {
/* 839 */               return itemstack1.cloneItemStack();
/*     */             }
/* 841 */             if (((TileEntityDispenser)isourceblock.<TileEntityDispenser>getTileEntity()).addItem(itemstack1.cloneItemStack()) < 0) {
/* 842 */               this.b.dispense(isourceblock, itemstack1.cloneItemStack());
/*     */             }
/*     */             
/* 845 */             return itemstack;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 851 */             a(false);
/* 852 */             WorldServer worldserver = isourceblock.getWorld();
/* 853 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/* 854 */             IBlockData iblockdata = worldserver.getType(blockposition);
/*     */ 
/*     */             
/* 857 */             Block bukkitBlock = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 858 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 860 */             BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 861 */             if (!BlockDispenser.eventFired) {
/* 862 */               worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 865 */             if (event.isCancelled()) {
/* 866 */               return itemstack;
/*     */             }
/*     */             
/* 869 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 871 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 872 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 873 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 874 */                 idispensebehavior.dispense(isourceblock, eventStack);
/* 875 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 880 */             if (iblockdata.a(TagsBlock.BEEHIVES, blockbase_blockdata -> blockbase_blockdata.b(BlockBeehive.b)) && ((Integer)iblockdata
/*     */               
/* 882 */               .get(BlockBeehive.b)).intValue() >= 5) {
/* 883 */               ((BlockBeehive)iblockdata.getBlock()).a(worldserver, iblockdata, blockposition, (EntityHuman)null, TileEntityBeehive.ReleaseStatus.BEE_RELEASED);
/* 884 */               a(true);
/* 885 */               return a(isourceblock, itemstack, new ItemStack(Items.HONEY_BOTTLE));
/* 886 */             }  if (worldserver.getFluid(blockposition).a(TagsFluid.WATER)) {
/* 887 */               a(true);
/* 888 */               return a(isourceblock, itemstack, PotionUtil.a(new ItemStack(Items.POTION), Potions.WATER));
/*     */             } 
/* 890 */             return super.a(isourceblock, itemstack);
/*     */           }
/*     */         });
/*     */     
/* 894 */     BlockDispenser.a(Items.dq, new DispenseBehaviorMaybe()
/*     */         {
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 897 */             EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 898 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/* 899 */             WorldServer worldserver = isourceblock.getWorld();
/* 900 */             IBlockData iblockdata = worldserver.getType(blockposition);
/*     */             
/* 902 */             a(true);
/* 903 */             if (iblockdata.a(Blocks.RESPAWN_ANCHOR)) {
/* 904 */               if (((Integer)iblockdata.get(BlockRespawnAnchor.a)).intValue() != 4) {
/* 905 */                 BlockRespawnAnchor.a(worldserver, blockposition, iblockdata);
/* 906 */                 itemstack.subtract(1);
/*     */               } else {
/* 908 */                 a(false);
/*     */               } 
/*     */               
/* 911 */               return itemstack;
/*     */             } 
/* 913 */             return super.a(isourceblock, itemstack);
/*     */           }
/*     */         });
/*     */     
/* 917 */     BlockDispenser.a(Items.SHEARS.getItem(), new DispenseBehaviorShears());
/*     */   }
/*     */   
/*     */   static void a(ISourceBlock isourceblock, Entity entity, EnumDirection enumdirection) {
/* 921 */     entity.setPosition(isourceblock.getX() + enumdirection.getAdjacentX() * (0.5000099999997474D - entity.getWidth() / 2.0D), isourceblock.getY() + enumdirection.getAdjacentY() * (0.5000099999997474D - entity.getHeight() / 2.0D) - entity.getHeight() / 2.0D, isourceblock.getZ() + enumdirection.getAdjacentZ() * (0.5000099999997474D - entity.getWidth() / 2.0D));
/*     */   }
/*     */   
/*     */   ItemStack dispense(ISourceBlock paramISourceBlock, ItemStack paramItemStack);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IDispenseBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */