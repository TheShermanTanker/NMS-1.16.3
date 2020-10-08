/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*     */ import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
/*     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*     */ 
/*     */ public class EntityItem extends Entity {
/*  17 */   private static final DataWatcherObject<ItemStack> ITEM = DataWatcher.a((Class)EntityItem.class, DataWatcherRegistry.g);
/*     */   public int age;
/*     */   public int pickupDelay;
/*     */   private int f;
/*     */   private UUID thrower;
/*     */   private UUID owner;
/*     */   public final float b;
/*  24 */   private int lastTick = MinecraftServer.currentTick - 1;
/*     */   public boolean canMobPickup = true;
/*     */   
/*     */   public EntityItem(EntityTypes<? extends EntityItem> entitytypes, World world) {
/*  28 */     super(entitytypes, world);
/*  29 */     this.f = 5;
/*  30 */     this.b = (float)(Math.random() * Math.PI * 2.0D);
/*     */   }
/*     */   
/*     */   public EntityItem(World world, double d0, double d1, double d2) {
/*  34 */     this(EntityTypes.ITEM, world);
/*  35 */     setPosition(d0, d1, d2);
/*  36 */     this.yaw = this.random.nextFloat() * 360.0F;
/*  37 */     setMot(this.random.nextDouble() * 0.2D - 0.1D, 0.2D, this.random.nextDouble() * 0.2D - 0.1D);
/*     */   }
/*     */   
/*     */   public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
/*  41 */     this(world, d0, d1, d2);
/*  42 */     setItemStack(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  52 */     getDataWatcher().register(ITEM, ItemStack.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  57 */     if (getItemStack().isEmpty()) {
/*  58 */       die();
/*     */     } else {
/*  60 */       super.tick();
/*     */       
/*  62 */       int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
/*  63 */       if (this.pickupDelay != 32767) this.pickupDelay -= elapsedTicks; 
/*  64 */       this.pickupDelay = Math.max(0, this.pickupDelay);
/*  65 */       if (this.age != -32768) this.age += elapsedTicks; 
/*  66 */       this.lastTick = MinecraftServer.currentTick;
/*     */ 
/*     */       
/*  69 */       this.lastX = locX();
/*  70 */       this.lastY = locY();
/*  71 */       this.lastZ = locZ();
/*  72 */       Vec3D vec3d = getMot();
/*  73 */       float f = getHeadHeight() - 0.11111111F;
/*     */       
/*  75 */       if (isInWater() && b(TagsFluid.WATER) > f) {
/*  76 */         u();
/*  77 */       } else if (aP() && b(TagsFluid.LAVA) > f) {
/*  78 */         v();
/*  79 */       } else if (!isNoGravity()) {
/*  80 */         setMot(getMot().add(0.0D, -0.04D, 0.0D));
/*     */       } 
/*     */       
/*  83 */       if (this.world.isClientSide) {
/*  84 */         this.noclip = false;
/*     */       } else {
/*  86 */         this.noclip = !this.world.getCubes(this);
/*  87 */         if (this.noclip) {
/*  88 */           l(locX(), ((getBoundingBox()).minY + (getBoundingBox()).maxY) / 2.0D, locZ());
/*     */         }
/*     */       } 
/*     */       
/*  92 */       if (!this.onGround || c(getMot()) > 9.999999747378752E-6D || this.ticksLived % 4 == 0) {
/*  93 */         move(EnumMoveType.SELF, getMot());
/*  94 */         float f1 = 0.98F;
/*     */         
/*  96 */         if (this.onGround) {
/*  97 */           f1 = this.world.getType(new BlockPosition(locX(), locY() - 1.0D, locZ())).getBlock().getFrictionFactor() * 0.98F;
/*     */         }
/*     */         
/* 100 */         setMot(getMot().d(f1, 0.98D, f1));
/* 101 */         if (this.onGround) {
/* 102 */           Vec3D vec3d1 = getMot();
/*     */           
/* 104 */           if (vec3d1.y < 0.0D) {
/* 105 */             setMot(vec3d1.d(1.0D, -0.5D, 1.0D));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 110 */       boolean flag = (MathHelper.floor(this.lastX) != MathHelper.floor(locX()) || MathHelper.floor(this.lastY) != MathHelper.floor(locY()) || MathHelper.floor(this.lastZ) != MathHelper.floor(locZ()));
/* 111 */       int i = flag ? 2 : 40;
/*     */       
/* 113 */       if (this.ticksLived % i == 0) {
/* 114 */         if (this.world.getFluid(getChunkCoordinates()).a(TagsFluid.LAVA) && !isFireProof()) {
/* 115 */           playSound(SoundEffects.ENTITY_GENERIC_BURN, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
/*     */         }
/*     */         
/* 118 */         if (!this.world.isClientSide && z()) {
/* 119 */           mergeNearby();
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       this.impulse |= aJ();
/* 130 */       if (!this.world.isClientSide) {
/* 131 */         double d0 = getMot().d(vec3d).g();
/*     */         
/* 133 */         if (d0 > 0.01D) {
/* 134 */           this.impulse = true;
/*     */         }
/*     */       } 
/*     */       
/* 138 */       if (!this.world.isClientSide && this.age >= getDespawnRate()) {
/*     */         
/* 140 */         if (CraftEventFactory.callItemDespawnEvent(this).isCancelled()) {
/* 141 */           this.age = 0;
/*     */           
/*     */           return;
/*     */         } 
/* 145 */         die();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inactiveTick() {
/* 155 */     int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
/* 156 */     if (this.pickupDelay != 32767) this.pickupDelay -= elapsedTicks; 
/* 157 */     this.pickupDelay = Math.max(0, this.pickupDelay);
/* 158 */     if (this.age != -32768) this.age += elapsedTicks; 
/* 159 */     this.lastTick = MinecraftServer.currentTick;
/*     */ 
/*     */     
/* 162 */     if (!this.world.isClientSide && this.age >= getDespawnRate()) {
/*     */       
/* 164 */       if (CraftEventFactory.callItemDespawnEvent(this).isCancelled()) {
/* 165 */         this.age = 0;
/*     */         
/*     */         return;
/*     */       } 
/* 169 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void u() {
/* 175 */     Vec3D vec3d = getMot();
/*     */     
/* 177 */     setMot(vec3d.x * 0.9900000095367432D, vec3d.y + ((vec3d.y < 0.05999999865889549D) ? 5.0E-4F : 0.0F), vec3d.z * 0.9900000095367432D);
/*     */   }
/*     */   
/*     */   private void v() {
/* 181 */     Vec3D vec3d = getMot();
/*     */     
/* 183 */     setMot(vec3d.x * 0.949999988079071D, vec3d.y + ((vec3d.y < 0.05999999865889549D) ? 5.0E-4F : 0.0F), vec3d.z * 0.949999988079071D);
/*     */   }
/*     */   
/*     */   private void mergeNearby() {
/* 187 */     if (z()) {
/*     */       
/* 189 */       ItemStack stack = getItemStack();
/* 190 */       if (stack.getCount() >= stack.getMaxStackSize()) {
/*     */         return;
/*     */       }
/* 193 */       double radius = this.world.spigotConfig.itemMerge;
/* 194 */       List<EntityItem> list = this.world.a(EntityItem.class, getBoundingBox().grow(radius, radius, radius), entityitem -> 
/*     */           
/* 196 */           (entityitem != this && entityitem.z()));
/*     */       
/* 198 */       Iterator<EntityItem> iterator = list.iterator();
/*     */       
/* 200 */       while (iterator.hasNext()) {
/* 201 */         EntityItem entityitem = iterator.next();
/*     */         
/* 203 */         if (entityitem.z()) {
/* 204 */           a(entityitem);
/* 205 */           if (this.dead) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean z() {
/* 215 */     ItemStack itemstack = getItemStack();
/*     */     
/* 217 */     return (isAlive() && this.pickupDelay != 32767 && this.age != -32768 && this.age < 6000 && itemstack.getCount() < itemstack.getMaxStackSize());
/*     */   }
/*     */   
/*     */   private void a(EntityItem entityitem) {
/* 221 */     ItemStack itemstack = getItemStack();
/* 222 */     ItemStack itemstack1 = entityitem.getItemStack();
/*     */     
/* 224 */     if (Objects.equals(getOwner(), entityitem.getOwner()) && a(itemstack, itemstack1))
/*     */     {
/* 226 */       a(this, itemstack, entityitem, itemstack1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 235 */     return (itemstack1.getItem() != itemstack.getItem()) ? false : ((itemstack1.getCount() + itemstack.getCount() > itemstack1.getMaxStackSize()) ? false : (((itemstack1.hasTag() ^ itemstack.hasTag()) != 0) ? false : ((!itemstack1.hasTag() || itemstack1.getTag().equals(itemstack.getTag())))));
/*     */   }
/*     */   
/*     */   public static ItemStack a(ItemStack itemstack, ItemStack itemstack1, int i) {
/* 239 */     int j = Math.min(Math.min(itemstack.getMaxStackSize(), i) - itemstack.getCount(), itemstack1.getCount());
/* 240 */     ItemStack itemstack2 = itemstack.cloneItemStack();
/*     */     
/* 242 */     itemstack2.add(j);
/* 243 */     itemstack1.subtract(j);
/* 244 */     return itemstack2;
/*     */   }
/*     */   
/*     */   private static void a(EntityItem entityitem, ItemStack itemstack, ItemStack itemstack1) {
/* 248 */     ItemStack itemstack2 = a(itemstack, itemstack1, 64);
/*     */     
/* 250 */     if (!itemstack2.isEmpty()) entityitem.setItemStack(itemstack2); 
/*     */   }
/*     */   
/*     */   private static void a(EntityItem entityitem, ItemStack itemstack, EntityItem entityitem1, ItemStack itemstack1) {
/* 254 */     if (CraftEventFactory.callItemMergeEvent(entityitem1, entityitem).isCancelled())
/* 255 */       return;  a(entityitem, itemstack, itemstack1);
/* 256 */     entityitem.pickupDelay = Math.max(entityitem.pickupDelay, entityitem1.pickupDelay);
/* 257 */     entityitem.age = Math.min(entityitem.age, entityitem1.age);
/* 258 */     if (itemstack1.isEmpty()) {
/* 259 */       entityitem1.die();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFireProof() {
/* 266 */     return (getItemStack().getItem().u() || super.isFireProof());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 271 */     if (isInvulnerable(damagesource))
/* 272 */       return false; 
/* 273 */     if (!getItemStack().isEmpty() && getItemStack().getItem() == Items.NETHER_STAR && damagesource.isExplosion())
/* 274 */       return false; 
/* 275 */     if (!getItemStack().getItem().a(damagesource)) {
/* 276 */       return false;
/*     */     }
/*     */     
/* 279 */     if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f)) {
/* 280 */       return false;
/*     */     }
/*     */     
/* 283 */     velocityChanged();
/* 284 */     this.f = (int)(this.f - f);
/* 285 */     if (this.f <= 0) {
/* 286 */       die();
/*     */     }
/*     */     
/* 289 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 295 */     nbttagcompound.setShort("Health", (short)this.f);
/* 296 */     nbttagcompound.setShort("Age", (short)this.age);
/* 297 */     nbttagcompound.setShort("PickupDelay", (short)this.pickupDelay);
/* 298 */     if (getThrower() != null) {
/* 299 */       nbttagcompound.a("Thrower", getThrower());
/*     */     }
/*     */     
/* 302 */     if (getOwner() != null) {
/* 303 */       nbttagcompound.a("Owner", getOwner());
/*     */     }
/*     */     
/* 306 */     if (!getItemStack().isEmpty()) {
/* 307 */       nbttagcompound.set("Item", getItemStack().save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 314 */     this.f = nbttagcompound.getShort("Health");
/* 315 */     this.age = nbttagcompound.getShort("Age");
/* 316 */     if (nbttagcompound.hasKey("PickupDelay")) {
/* 317 */       this.pickupDelay = nbttagcompound.getShort("PickupDelay");
/*     */     }
/*     */     
/* 320 */     if (nbttagcompound.b("Owner")) {
/* 321 */       this.owner = nbttagcompound.a("Owner");
/*     */     }
/*     */     
/* 324 */     if (nbttagcompound.b("Thrower")) {
/* 325 */       this.thrower = nbttagcompound.a("Thrower");
/*     */     }
/*     */     
/* 328 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Item");
/*     */     
/* 330 */     setItemStack(ItemStack.a(nbttagcompound1));
/* 331 */     if (getItemStack().isEmpty()) {
/* 332 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pickup(EntityHuman entityhuman) {
/* 339 */     if (!this.world.isClientSide) {
/* 340 */       ItemStack itemstack = getItemStack();
/* 341 */       Item item = itemstack.getItem();
/* 342 */       int i = itemstack.getCount();
/*     */ 
/*     */       
/* 345 */       int canHold = entityhuman.inventory.canHold(itemstack);
/* 346 */       int remaining = i - canHold;
/* 347 */       boolean flyAtPlayer = false;
/*     */ 
/*     */       
/* 350 */       if (this.pickupDelay <= 0) {
/* 351 */         PlayerAttemptPickupItemEvent attemptEvent = new PlayerAttemptPickupItemEvent((Player)entityhuman.getBukkitEntity(), (Item)getBukkitEntity(), remaining);
/* 352 */         this.world.getServer().getPluginManager().callEvent((Event)attemptEvent);
/*     */         
/* 354 */         flyAtPlayer = attemptEvent.getFlyAtPlayer();
/* 355 */         if (attemptEvent.isCancelled()) {
/* 356 */           if (flyAtPlayer) {
/* 357 */             entityhuman.receive(this, i);
/*     */           }
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 365 */       if (this.pickupDelay <= 0 && canHold > 0) {
/* 366 */         itemstack.setCount(canHold);
/*     */         
/* 368 */         PlayerPickupItemEvent playerEvent = new PlayerPickupItemEvent((Player)entityhuman.getBukkitEntity(), (Item)getBukkitEntity(), remaining);
/* 369 */         playerEvent.setCancelled(!entityhuman.canPickUpLoot);
/* 370 */         this.world.getServer().getPluginManager().callEvent((Event)playerEvent);
/* 371 */         flyAtPlayer = playerEvent.getFlyAtPlayer();
/* 372 */         if (playerEvent.isCancelled()) {
/* 373 */           itemstack.setCount(i);
/*     */           
/* 375 */           if (flyAtPlayer) {
/* 376 */             entityhuman.receive(this, i);
/*     */           }
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 383 */         EntityPickupItemEvent entityEvent = new EntityPickupItemEvent((LivingEntity)entityhuman.getBukkitEntity(), (Item)getBukkitEntity(), remaining);
/* 384 */         entityEvent.setCancelled(!entityhuman.canPickUpLoot);
/* 385 */         this.world.getServer().getPluginManager().callEvent((Event)entityEvent);
/* 386 */         if (entityEvent.isCancelled()) {
/* 387 */           itemstack.setCount(i);
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 392 */         ItemStack current = getItemStack();
/* 393 */         if (!itemstack.equals(current)) {
/* 394 */           itemstack = current;
/*     */         } else {
/* 396 */           itemstack.setCount(canHold + remaining);
/*     */         } 
/*     */ 
/*     */         
/* 400 */         this.pickupDelay = 0;
/* 401 */       } else if (this.pickupDelay == 0) {
/*     */         
/* 403 */         this.pickupDelay = -1;
/*     */       } 
/*     */ 
/*     */       
/* 407 */       if (this.pickupDelay == 0 && (this.owner == null || this.owner.equals(entityhuman.getUniqueID())) && entityhuman.inventory.pickup(itemstack)) {
/*     */         
/* 409 */         if (flyAtPlayer) {
/* 410 */           entityhuman.receive(this, i);
/*     */         }
/*     */         
/* 413 */         if (itemstack.isEmpty()) {
/* 414 */           die();
/* 415 */           itemstack.setCount(i);
/*     */         } 
/*     */         
/* 418 */         entityhuman.a(StatisticList.ITEM_PICKED_UP.b(item), i);
/* 419 */         entityhuman.a(this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatBaseComponent getDisplayName() {
/* 427 */     IChatBaseComponent ichatbasecomponent = getCustomName();
/*     */     
/* 429 */     return (ichatbasecomponent != null) ? ichatbasecomponent : new ChatMessage(getItemStack().j());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bK() {
/* 434 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity b(WorldServer worldserver) {
/* 440 */     Entity entity = super.b(worldserver);
/*     */     
/* 442 */     if (!this.world.isClientSide && entity instanceof EntityItem) {
/* 443 */       ((EntityItem)entity).mergeNearby();
/*     */     }
/*     */     
/* 446 */     return entity;
/*     */   }
/*     */   
/*     */   public ItemStack getItemStack() {
/* 450 */     return getDataWatcher().<ItemStack>get(ITEM);
/*     */   }
/*     */   
/*     */   public void setItemStack(ItemStack itemstack) {
/* 454 */     Preconditions.checkArgument(!itemstack.isEmpty(), "Cannot drop air");
/* 455 */     getDataWatcher().set(ITEM, itemstack);
/* 456 */     getDataWatcher().markDirty(ITEM);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 461 */     super.a(datawatcherobject);
/* 462 */     if (ITEM.equals(datawatcherobject)) {
/* 463 */       getItemStack().a(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwner() {
/* 470 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void setOwner(@Nullable UUID uuid) {
/* 474 */     this.owner = uuid;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getThrower() {
/* 479 */     return this.thrower;
/*     */   }
/*     */   
/*     */   public void setThrower(@Nullable UUID uuid) {
/* 483 */     this.thrower = uuid;
/*     */   }
/*     */   
/*     */   public void defaultPickupDelay() {
/* 487 */     this.pickupDelay = 10;
/*     */   }
/*     */   
/*     */   public void n() {
/* 491 */     this.pickupDelay = 0;
/*     */   }
/*     */   
/*     */   public void o() {
/* 495 */     this.pickupDelay = 32767;
/*     */   }
/*     */   
/*     */   public void setPickupDelay(int i) {
/* 499 */     this.pickupDelay = i;
/*     */   }
/*     */   
/*     */   public boolean p() {
/* 503 */     return (this.pickupDelay > 0);
/*     */   }
/*     */   
/*     */   public void r() {
/* 507 */     this.age = -6000;
/*     */   }
/*     */   
/*     */   public void s() {
/* 511 */     o();
/* 512 */     this.age = getDespawnRate() - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDespawnRate() {
/* 517 */     Material material = getItemStack().getBukkitStack().getType();
/* 518 */     return ((Integer)this.world.paperConfig.altItemDespawnRateMap.getOrDefault(material, Integer.valueOf(this.world.spigotConfig.itemDespawnRate))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 524 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */