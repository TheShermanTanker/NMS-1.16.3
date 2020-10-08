/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
/*     */ import com.google.common.collect.Lists;
/*     */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.AbstractArrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerPickupArrowEvent;
/*     */ 
/*     */ public abstract class EntityArrow extends IProjectile {
/*  18 */   private static final DataWatcherObject<Byte> f = DataWatcher.a((Class)EntityArrow.class, DataWatcherRegistry.a);
/*  19 */   private static final DataWatcherObject<Byte> g = DataWatcher.a((Class)EntityArrow.class, DataWatcherRegistry.a);
/*     */   
/*     */   @Nullable
/*     */   private IBlockData ag;
/*     */   
/*     */   public boolean inGround;
/*     */   
/*     */   protected int c;
/*     */   public PickupStatus fromPlayer;
/*     */   public int shake;
/*     */   public int despawnCounter;
/*     */   private double damage;
/*     */   public int knockbackStrength;
/*     */   private SoundEffect ak;
/*     */   private IntOpenHashSet al;
/*     */   private List<Entity> am;
/*     */   
/*     */   public void inactiveTick() {
/*  37 */     if (this.inGround)
/*     */     {
/*  39 */       this.despawnCounter++;
/*     */     }
/*  41 */     super.inactiveTick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityArrow(EntityTypes<? extends EntityArrow> entitytypes, World world) {
/*  46 */     super((EntityTypes)entitytypes, world);
/*  47 */     this.fromPlayer = PickupStatus.DISALLOWED;
/*  48 */     this.damage = 2.0D;
/*  49 */     this.ak = i();
/*     */   }
/*     */   
/*     */   protected EntityArrow(EntityTypes<? extends EntityArrow> entitytypes, double d0, double d1, double d2, World world) {
/*  53 */     this(entitytypes, world);
/*  54 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   protected EntityArrow(EntityTypes<? extends EntityArrow> entitytypes, EntityLiving entityliving, World world) {
/*  58 */     this(entitytypes, entityliving.locX(), entityliving.getHeadY() - 0.10000000149011612D, entityliving.locZ(), world);
/*  59 */     setShooter(entityliving);
/*  60 */     if (entityliving instanceof EntityHuman) {
/*  61 */       this.fromPlayer = PickupStatus.ALLOWED;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(SoundEffect soundeffect) {
/*  67 */     this.ak = soundeffect;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  72 */     this.datawatcher.register(f, Byte.valueOf((byte)0));
/*  73 */     this.datawatcher.register(g, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void shoot(double d0, double d1, double d2, float f, float f1) {
/*  78 */     super.shoot(d0, d1, d2, f, f1);
/*  79 */     this.despawnCounter = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  84 */     super.tick();
/*  85 */     boolean flag = t();
/*  86 */     Vec3D vec3d = getMot();
/*     */     
/*  88 */     if (this.lastPitch == 0.0F && this.lastYaw == 0.0F) {
/*  89 */       float f = MathHelper.sqrt(c(vec3d));
/*     */       
/*  91 */       this.yaw = (float)(MathHelper.d(vec3d.x, vec3d.z) * 57.2957763671875D);
/*  92 */       this.pitch = (float)(MathHelper.d(vec3d.y, f) * 57.2957763671875D);
/*  93 */       this.lastYaw = this.yaw;
/*  94 */       this.lastPitch = this.pitch;
/*     */     } 
/*     */     
/*  97 */     BlockPosition blockposition = getChunkCoordinates();
/*  98 */     IBlockData iblockdata = this.world.getType(blockposition);
/*     */ 
/*     */     
/* 101 */     if (!iblockdata.isAir() && !flag) {
/* 102 */       VoxelShape voxelshape = iblockdata.getCollisionShape(this.world, blockposition);
/*     */       
/* 104 */       if (!voxelshape.isEmpty()) {
/* 105 */         Vec3D vec3d1 = getPositionVector();
/* 106 */         Iterator<AxisAlignedBB> iterator = voxelshape.d().iterator();
/*     */         
/* 108 */         while (iterator.hasNext()) {
/* 109 */           AxisAlignedBB axisalignedbb = iterator.next();
/*     */           
/* 111 */           if (axisalignedbb.a(blockposition).d(vec3d1)) {
/* 112 */             this.inGround = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 119 */     if (this.shake > 0) {
/* 120 */       this.shake--;
/*     */     }
/*     */     
/* 123 */     if (isInWaterOrRain()) {
/* 124 */       extinguish();
/*     */     }
/*     */     
/* 127 */     if (this.inGround && !flag) {
/* 128 */       if (this.ag != iblockdata && u()) {
/* 129 */         z();
/* 130 */       } else if (!this.world.isClientSide) {
/* 131 */         h();
/*     */       } 
/*     */       
/* 134 */       this.c++;
/*     */     } else {
/* 136 */       if (this.ticksLived > 200) tickDespawnCounter(); 
/* 137 */       this.c = 0;
/* 138 */       Vec3D vec3d2 = getPositionVector();
/*     */       
/* 140 */       Vec3D vec3d1 = vec3d2.e(vec3d);
/* 141 */       Object object = this.world.rayTrace(new RayTrace(vec3d2, vec3d1, RayTrace.BlockCollisionOption.COLLIDER, RayTrace.FluidCollisionOption.NONE, this));
/*     */       
/* 143 */       if (((MovingObjectPosition)object).getType() != MovingObjectPosition.EnumMovingObjectType.MISS) {
/* 144 */         vec3d1 = ((MovingObjectPosition)object).getPos();
/*     */       }
/*     */       
/* 147 */       while (!this.dead) {
/* 148 */         MovingObjectPositionEntity movingobjectpositionentity = a(vec3d2, vec3d1);
/*     */         
/* 150 */         if (movingobjectpositionentity != null) {
/* 151 */           object = movingobjectpositionentity;
/*     */         }
/*     */         
/* 154 */         if (object != null && ((MovingObjectPosition)object).getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
/* 155 */           Entity entity = ((MovingObjectPositionEntity)object).getEntity();
/* 156 */           Entity entity1 = getShooter();
/*     */           
/* 158 */           if (entity instanceof EntityHuman && entity1 instanceof EntityHuman && !((EntityHuman)entity1).a((EntityHuman)entity)) {
/* 159 */             object = null;
/* 160 */             movingobjectpositionentity = null;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 166 */         if (object instanceof MovingObjectPositionEntity) {
/* 167 */           ProjectileCollideEvent event = CraftEventFactory.callProjectileCollideEvent(this, (MovingObjectPositionEntity)object);
/* 168 */           if (event.isCancelled()) {
/* 169 */             object = null;
/* 170 */             movingobjectpositionentity = null;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 175 */         if (object != null && !flag) {
/* 176 */           a((MovingObjectPosition)object);
/* 177 */           this.impulse = true;
/*     */         } 
/*     */         
/* 180 */         if (movingobjectpositionentity == null || getPierceLevel() <= 0) {
/*     */           break;
/*     */         }
/*     */         
/* 184 */         object = null;
/*     */       } 
/*     */       
/* 187 */       vec3d = getMot();
/* 188 */       double d0 = vec3d.x;
/* 189 */       double d1 = vec3d.y;
/* 190 */       double d2 = vec3d.z;
/*     */       
/* 192 */       if (isCritical()) {
/* 193 */         for (int i = 0; i < 4; i++) {
/* 194 */           this.world.addParticle(Particles.CRIT, locX() + d0 * i / 4.0D, locY() + d1 * i / 4.0D, locZ() + d2 * i / 4.0D, -d0, -d1 + 0.2D, -d2);
/*     */         }
/*     */       }
/*     */       
/* 198 */       double d3 = locX() + d0;
/* 199 */       double d4 = locY() + d1;
/* 200 */       double d5 = locZ() + d2;
/* 201 */       float f1 = MathHelper.sqrt(c(vec3d));
/*     */       
/* 203 */       if (flag) {
/* 204 */         this.yaw = (float)(MathHelper.d(-d0, -d2) * 57.2957763671875D);
/*     */       } else {
/* 206 */         this.yaw = (float)(MathHelper.d(d0, d2) * 57.2957763671875D);
/*     */       } 
/*     */       
/* 209 */       this.pitch = (float)(MathHelper.d(d1, f1) * 57.2957763671875D);
/* 210 */       this.pitch = e(this.lastPitch, this.pitch);
/* 211 */       this.yaw = e(this.lastYaw, this.yaw);
/* 212 */       float f2 = 0.99F;
/* 213 */       float f3 = 0.05F;
/*     */       
/* 215 */       if (isInWater()) {
/* 216 */         for (int j = 0; j < 4; j++) {
/* 217 */           float f4 = 0.25F;
/*     */           
/* 219 */           this.world.addParticle(Particles.BUBBLE, d3 - d0 * 0.25D, d4 - d1 * 0.25D, d5 - d2 * 0.25D, d0, d1, d2);
/*     */         } 
/*     */         
/* 222 */         f2 = s();
/*     */       } 
/*     */       
/* 225 */       setMot(vec3d.a(f2));
/* 226 */       if (!isNoGravity() && !flag) {
/* 227 */         Vec3D vec3d3 = getMot();
/*     */         
/* 229 */         setMot(vec3d3.x, vec3d3.y - 0.05000000074505806D, vec3d3.z);
/*     */       } 
/*     */       
/* 232 */       setPosition(d3, d4, d5);
/* 233 */       checkBlockCollisions();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean u() {
/* 238 */     return (this.inGround && this.world.b((new AxisAlignedBB(getPositionVector(), getPositionVector())).g(0.06D)));
/*     */   }
/*     */   
/*     */   private void z() {
/* 242 */     this.inGround = false;
/* 243 */     Vec3D vec3d = getMot();
/*     */     
/* 245 */     setMot(vec3d.d((this.random.nextFloat() * 0.2F), (this.random.nextFloat() * 0.2F), (this.random.nextFloat() * 0.2F)));
/* 246 */     this.despawnCounter = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType enummovetype, Vec3D vec3d) {
/* 251 */     super.move(enummovetype, vec3d);
/* 252 */     if (enummovetype != EnumMoveType.SELF && u()) {
/* 253 */       z();
/*     */     }
/*     */   }
/*     */   
/*     */   protected final void tickDespawnCounter() {
/* 258 */     h();
/*     */   } protected void h() {
/* 260 */     this.despawnCounter++;
/* 261 */     if (this.despawnCounter >= ((this.fromPlayer == PickupStatus.CREATIVE_ONLY) ? this.world.paperConfig.creativeArrowDespawnRate : ((this.fromPlayer == PickupStatus.DISALLOWED) ? this.world.paperConfig.nonPlayerArrowDespawnRate : ((this instanceof EntityThrownTrident) ? this.world.spigotConfig.tridentDespawnRate : this.world.spigotConfig.arrowDespawnRate)))) {
/* 262 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void A() {
/* 268 */     if (this.am != null) {
/* 269 */       this.am.clear();
/*     */     }
/*     */     
/* 272 */     if (this.al != null) {
/* 273 */       this.al.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/*     */     DamageSource damagesource;
/* 280 */     super.a(movingobjectpositionentity);
/* 281 */     Entity entity = movingobjectpositionentity.getEntity();
/* 282 */     float f = (float)getMot().f();
/* 283 */     int i = MathHelper.f(MathHelper.a(f * this.damage, 0.0D, 2.147483647E9D));
/*     */     
/* 285 */     if (getPierceLevel() > 0) {
/* 286 */       if (this.al == null) {
/* 287 */         this.al = new IntOpenHashSet(5);
/*     */       }
/*     */       
/* 290 */       if (this.am == null) {
/* 291 */         this.am = Lists.newArrayListWithCapacity(5);
/*     */       }
/*     */       
/* 294 */       if (this.al.size() >= getPierceLevel() + 1) {
/* 295 */         die();
/*     */         
/*     */         return;
/*     */       } 
/* 299 */       this.al.add(entity.getId());
/*     */     } 
/*     */     
/* 302 */     if (isCritical()) {
/* 303 */       long j = this.random.nextInt(i / 2 + 2);
/*     */       
/* 305 */       i = (int)Math.min(j + i, 2147483647L);
/*     */     } 
/*     */     
/* 308 */     Entity entity1 = getShooter();
/*     */ 
/*     */     
/* 311 */     if (entity1 == null) {
/* 312 */       damagesource = DamageSource.arrow(this, this);
/*     */     } else {
/* 314 */       damagesource = DamageSource.arrow(this, entity1);
/* 315 */       if (entity1 instanceof EntityLiving) {
/* 316 */         ((EntityLiving)entity1).z(entity);
/*     */       }
/*     */     } 
/*     */     
/* 320 */     boolean flag = (entity.getEntityType() == EntityTypes.ENDERMAN);
/* 321 */     int k = entity.getFireTicks();
/*     */     
/* 323 */     if (isBurning() && !flag) {
/*     */       
/* 325 */       EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), 5);
/* 326 */       Bukkit.getPluginManager().callEvent((Event)combustEvent);
/* 327 */       if (!combustEvent.isCancelled()) {
/* 328 */         entity.setOnFire(combustEvent.getDuration(), false);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 333 */     if (entity.damageEntity(damagesource, i)) {
/* 334 */       if (flag) {
/*     */         return;
/*     */       }
/*     */       
/* 338 */       if (entity instanceof EntityLiving) {
/* 339 */         EntityLiving entityliving = (EntityLiving)entity;
/*     */         
/* 341 */         if (!this.world.isClientSide && getPierceLevel() <= 0) {
/* 342 */           entityliving.setArrowCount(entityliving.getArrowCount() + 1);
/*     */         }
/*     */         
/* 345 */         if (this.knockbackStrength > 0) {
/* 346 */           Vec3D vec3d = getMot().d(1.0D, 0.0D, 1.0D).d().a(this.knockbackStrength * 0.6D);
/*     */           
/* 348 */           if (vec3d.g() > 0.0D) {
/* 349 */             entityliving.i(vec3d.x, 0.1D, vec3d.z);
/*     */           }
/*     */         } 
/*     */         
/* 353 */         if (!this.world.isClientSide && entity1 instanceof EntityLiving) {
/* 354 */           EnchantmentManager.a(entityliving, entity1);
/* 355 */           EnchantmentManager.b((EntityLiving)entity1, entityliving);
/*     */         } 
/*     */         
/* 358 */         a(entityliving);
/* 359 */         if (entity1 != null && entityliving != entity1 && entityliving instanceof EntityHuman && entity1 instanceof EntityPlayer && !isSilent()) {
/* 360 */           ((EntityPlayer)entity1).playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.g, 0.0F));
/*     */         }
/*     */         
/* 363 */         if (!entity.isAlive() && this.am != null) {
/* 364 */           this.am.add(entityliving);
/*     */         }
/*     */         
/* 367 */         if (!this.world.isClientSide && entity1 instanceof EntityPlayer) {
/* 368 */           EntityPlayer entityplayer = (EntityPlayer)entity1;
/*     */           
/* 370 */           if (this.am != null && isShotFromCrossbow()) {
/* 371 */             CriterionTriggers.G.a(entityplayer, this.am);
/* 372 */           } else if (!entity.isAlive() && isShotFromCrossbow()) {
/* 373 */             CriterionTriggers.G.a(entityplayer, Arrays.asList(new Entity[] { entity }));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 378 */       playSound(this.ak, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
/* 379 */       if (getPierceLevel() <= 0) {
/* 380 */         die();
/*     */       }
/*     */     } else {
/* 383 */       entity.setFireTicks(k);
/* 384 */       setMot(getMot().a(-0.1D));
/* 385 */       this.yaw += 180.0F;
/* 386 */       this.lastYaw += 180.0F;
/* 387 */       if (!this.world.isClientSide && getMot().g() < 1.0E-7D) {
/* 388 */         if (this.fromPlayer == PickupStatus.ALLOWED) {
/* 389 */           a(getItemStack(), 0.1F);
/*     */         }
/*     */         
/* 392 */         die();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionBlock movingobjectpositionblock) {
/* 400 */     this.ag = this.world.getType(movingobjectpositionblock.getBlockPosition());
/* 401 */     super.a(movingobjectpositionblock);
/* 402 */     Vec3D vec3d = movingobjectpositionblock.getPos().a(locX(), locY(), locZ());
/*     */     
/* 404 */     setMot(vec3d);
/* 405 */     Vec3D vec3d1 = vec3d.d().a(0.05000000074505806D);
/*     */     
/* 407 */     setPositionRaw(locX() - vec3d1.x, locY() - vec3d1.y, locZ() - vec3d1.z);
/* 408 */     playSound(getSoundHit(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
/* 409 */     this.inGround = true;
/* 410 */     this.shake = 7;
/* 411 */     setCritical(false);
/* 412 */     setPierceLevel((byte)0);
/* 413 */     a(SoundEffects.ENTITY_ARROW_HIT);
/* 414 */     setShotFromCrossbow(false);
/* 415 */     A();
/*     */   }
/*     */   
/*     */   protected SoundEffect i() {
/* 419 */     return SoundEffects.ENTITY_ARROW_HIT;
/*     */   }
/*     */   
/*     */   protected final SoundEffect getSoundHit() {
/* 423 */     return this.ak;
/*     */   }
/*     */   
/*     */   protected void a(EntityLiving entityliving) {}
/*     */   
/*     */   @Nullable
/*     */   protected MovingObjectPositionEntity a(Vec3D vec3d, Vec3D vec3d1) {
/* 430 */     return ProjectileHelper.a(this.world, this, vec3d, vec3d1, getBoundingBox().b(getMot()).g(1.0D), this::a);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(Entity entity) {
/* 435 */     return (super.a(entity) && (this.al == null || !this.al.contains(entity.getId())));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 440 */     super.saveData(nbttagcompound);
/* 441 */     nbttagcompound.setShort("life", (short)this.despawnCounter);
/* 442 */     if (this.ag != null) {
/* 443 */       nbttagcompound.set("inBlockState", GameProfileSerializer.a(this.ag));
/*     */     }
/*     */     
/* 446 */     nbttagcompound.setByte("shake", (byte)this.shake);
/* 447 */     nbttagcompound.setBoolean("inGround", this.inGround);
/* 448 */     nbttagcompound.setByte("pickup", (byte)this.fromPlayer.ordinal());
/* 449 */     nbttagcompound.setDouble("damage", this.damage);
/* 450 */     nbttagcompound.setBoolean("crit", isCritical());
/* 451 */     nbttagcompound.setByte("PierceLevel", getPierceLevel());
/* 452 */     nbttagcompound.setString("SoundEvent", IRegistry.SOUND_EVENT.getKey(this.ak).toString());
/* 453 */     nbttagcompound.setBoolean("ShotFromCrossbow", isShotFromCrossbow());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 458 */     super.loadData(nbttagcompound);
/* 459 */     this.despawnCounter = nbttagcompound.getShort("life");
/* 460 */     if (nbttagcompound.hasKeyOfType("inBlockState", 10)) {
/* 461 */       this.ag = GameProfileSerializer.c(nbttagcompound.getCompound("inBlockState"));
/*     */     }
/*     */     
/* 464 */     this.shake = nbttagcompound.getByte("shake") & 0xFF;
/* 465 */     this.inGround = nbttagcompound.getBoolean("inGround");
/* 466 */     if (nbttagcompound.hasKeyOfType("damage", 99)) {
/* 467 */       this.damage = nbttagcompound.getDouble("damage");
/*     */     }
/*     */     
/* 470 */     if (nbttagcompound.hasKeyOfType("pickup", 99)) {
/* 471 */       this.fromPlayer = PickupStatus.a(nbttagcompound.getByte("pickup"));
/* 472 */     } else if (nbttagcompound.hasKeyOfType("player", 99)) {
/* 473 */       this.fromPlayer = nbttagcompound.getBoolean("player") ? PickupStatus.ALLOWED : PickupStatus.DISALLOWED;
/*     */     } 
/*     */     
/* 476 */     setCritical(nbttagcompound.getBoolean("crit"));
/* 477 */     setPierceLevel(nbttagcompound.getByte("PierceLevel"));
/* 478 */     if (nbttagcompound.hasKeyOfType("SoundEvent", 8)) {
/* 479 */       this.ak = IRegistry.SOUND_EVENT.getOptional(new MinecraftKey(nbttagcompound.getString("SoundEvent"))).orElse(i());
/*     */     }
/*     */     
/* 482 */     setShotFromCrossbow(nbttagcompound.getBoolean("ShotFromCrossbow"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShooter(@Nullable Entity entity) {
/* 487 */     super.setShooter(entity);
/* 488 */     if (entity instanceof EntityHuman) {
/* 489 */       this.fromPlayer = ((EntityHuman)entity).abilities.canInstantlyBuild ? PickupStatus.CREATIVE_ONLY : PickupStatus.ALLOWED;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pickup(EntityHuman entityhuman) {
/* 496 */     if (!this.world.isClientSide && (this.inGround || t()) && this.shake <= 0) {
/*     */       
/* 498 */       ItemStack itemstack = getItemStack();
/* 499 */       if (this.fromPlayer == PickupStatus.ALLOWED && !itemstack.isEmpty() && entityhuman.inventory.canHold(itemstack) > 0) {
/* 500 */         EntityItem item = new EntityItem(this.world, locX(), locY(), locZ(), itemstack);
/* 501 */         PlayerPickupArrowEvent event = new PlayerPickupArrowEvent((Player)entityhuman.getBukkitEntity(), (Item)new CraftItem(this.world.getServer(), this, item), (AbstractArrow)getBukkitEntity());
/*     */         
/* 503 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 505 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/* 508 */         itemstack = item.getItemStack();
/*     */       } 
/* 510 */       boolean flag = (this.fromPlayer == PickupStatus.ALLOWED || (this.fromPlayer == PickupStatus.CREATIVE_ONLY && entityhuman.abilities.canInstantlyBuild) || (t() && getShooter().getUniqueID() == entityhuman.getUniqueID()));
/*     */       
/* 512 */       if (this.fromPlayer == PickupStatus.ALLOWED && !entityhuman.inventory.pickup(itemstack))
/*     */       {
/* 514 */         flag = false;
/*     */       }
/*     */       
/* 517 */       if (flag) {
/* 518 */         entityhuman.receive(this, 1);
/* 519 */         die();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final ItemStack getOriginalItemStack() {
/* 525 */     return getItemStack();
/*     */   }
/*     */   protected abstract ItemStack getItemStack();
/*     */   
/*     */   protected boolean playStepSound() {
/* 530 */     return false;
/*     */   }
/*     */   
/*     */   public void setDamage(double d0) {
/* 534 */     this.damage = d0;
/*     */   }
/*     */   
/*     */   public double getDamage() {
/* 538 */     return this.damage;
/*     */   }
/*     */   
/*     */   public void setKnockbackStrength(int i) {
/* 542 */     this.knockbackStrength = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bK() {
/* 547 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getHeadHeight(EntityPose entitypose, EntitySize entitysize) {
/* 552 */     return 0.13F;
/*     */   }
/*     */   
/*     */   public void setCritical(boolean flag) {
/* 556 */     a(1, flag);
/*     */   }
/*     */   
/*     */   public void setPierceLevel(byte b0) {
/* 560 */     this.datawatcher.set(g, Byte.valueOf(b0));
/*     */   }
/*     */   
/*     */   private void a(int i, boolean flag) {
/* 564 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(f)).byteValue();
/*     */     
/* 566 */     if (flag) {
/* 567 */       this.datawatcher.set(f, Byte.valueOf((byte)(b0 | i)));
/*     */     } else {
/* 569 */       this.datawatcher.set(f, Byte.valueOf((byte)(b0 & (i ^ 0xFFFFFFFF))));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCritical() {
/* 575 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(f)).byteValue();
/*     */     
/* 577 */     return ((b0 & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public boolean isShotFromCrossbow() {
/* 581 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(f)).byteValue();
/*     */     
/* 583 */     return ((b0 & 0x4) != 0);
/*     */   }
/*     */   
/*     */   public byte getPierceLevel() {
/* 587 */     return ((Byte)this.datawatcher.<Byte>get(g)).byteValue();
/*     */   }
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 591 */     int i = EnchantmentManager.a(Enchantments.ARROW_DAMAGE, entityliving);
/* 592 */     int j = EnchantmentManager.a(Enchantments.ARROW_KNOCKBACK, entityliving);
/*     */     
/* 594 */     setDamage((f * 2.0F) + this.random.nextGaussian() * 0.25D + (this.world.getDifficulty().a() * 0.11F));
/* 595 */     if (i > 0) {
/* 596 */       setDamage(getDamage() + i * 0.5D + 0.5D);
/*     */     }
/*     */     
/* 599 */     if (j > 0) {
/* 600 */       setKnockbackStrength(j);
/*     */     }
/*     */     
/* 603 */     if (EnchantmentManager.a(Enchantments.ARROW_FIRE, entityliving) > 0) {
/* 604 */       setOnFire(100);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected float s() {
/* 610 */     return 0.6F;
/*     */   }
/*     */   
/*     */   public void o(boolean flag) {
/* 614 */     this.noclip = flag;
/* 615 */     a(2, flag);
/*     */   }
/*     */   
/*     */   public boolean t() {
/* 619 */     return !this.world.isClientSide ? this.noclip : (((((Byte)this.datawatcher.<Byte>get(f)).byteValue() & 0x2) != 0));
/*     */   }
/*     */   
/*     */   public void setShotFromCrossbow(boolean flag) {
/* 623 */     a(4, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 628 */     Entity entity = getShooter();
/*     */     
/* 630 */     return new PacketPlayOutSpawnEntity(this, (entity == null) ? 0 : entity.getId());
/*     */   }
/*     */   
/*     */   public enum PickupStatus
/*     */   {
/* 635 */     DISALLOWED, ALLOWED, CREATIVE_ONLY;
/*     */ 
/*     */ 
/*     */     
/*     */     public static PickupStatus a(int i) {
/* 640 */       if (i < 0 || i > (values()).length) {
/* 641 */         i = 0;
/*     */       }
/*     */       
/* 644 */       return values()[i];
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */