/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Vehicle;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.vehicle.VehicleDamageEvent;
/*     */ import org.bukkit.event.vehicle.VehicleDestroyEvent;
/*     */ import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
/*     */ import org.bukkit.event.vehicle.VehicleMoveEvent;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public abstract class EntityMinecartAbstract extends Entity {
/*  24 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.b);
/*  25 */   private static final DataWatcherObject<Integer> c = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.b);
/*  26 */   private static final DataWatcherObject<Float> d = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.c);
/*  27 */   private static final DataWatcherObject<Integer> e = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.b);
/*  28 */   private static final DataWatcherObject<Integer> f = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.b);
/*  29 */   private static final DataWatcherObject<Boolean> g = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.i);
/*  30 */   private static final ImmutableMap<EntityPose, ImmutableList<Integer>> ag = ImmutableMap.of(EntityPose.STANDING, ImmutableList.of(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(-1)), EntityPose.CROUCHING, ImmutableList.of(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(-1)), EntityPose.SWIMMING, ImmutableList.of(Integer.valueOf(0), Integer.valueOf(1))); private boolean ah; private static final Map<BlockPropertyTrackPosition, Pair<BaseBlockPosition, BaseBlockPosition>> ai;
/*     */   static {
/*  32 */     ai = SystemUtils.<Map<BlockPropertyTrackPosition, Pair<BaseBlockPosition, BaseBlockPosition>>>a(Maps.newEnumMap(BlockPropertyTrackPosition.class), enummap -> {
/*     */           BaseBlockPosition baseblockposition = EnumDirection.WEST.p();
/*     */           BaseBlockPosition baseblockposition1 = EnumDirection.EAST.p();
/*     */           BaseBlockPosition baseblockposition2 = EnumDirection.NORTH.p();
/*     */           BaseBlockPosition baseblockposition3 = EnumDirection.SOUTH.p();
/*     */           BaseBlockPosition baseblockposition4 = baseblockposition.down();
/*     */           BaseBlockPosition baseblockposition5 = baseblockposition1.down();
/*     */           BaseBlockPosition baseblockposition6 = baseblockposition2.down();
/*     */           BaseBlockPosition baseblockposition7 = baseblockposition3.down();
/*     */           enummap.put(BlockPropertyTrackPosition.NORTH_SOUTH, Pair.of(baseblockposition2, baseblockposition3));
/*     */           enummap.put(BlockPropertyTrackPosition.EAST_WEST, Pair.of(baseblockposition, baseblockposition1));
/*     */           enummap.put(BlockPropertyTrackPosition.ASCENDING_EAST, Pair.of(baseblockposition4, baseblockposition1));
/*     */           enummap.put(BlockPropertyTrackPosition.ASCENDING_WEST, Pair.of(baseblockposition, baseblockposition5));
/*     */           enummap.put(BlockPropertyTrackPosition.ASCENDING_NORTH, Pair.of(baseblockposition2, baseblockposition7));
/*     */           enummap.put(BlockPropertyTrackPosition.ASCENDING_SOUTH, Pair.of(baseblockposition6, baseblockposition3));
/*     */           enummap.put(BlockPropertyTrackPosition.SOUTH_EAST, Pair.of(baseblockposition3, baseblockposition1));
/*     */           enummap.put(BlockPropertyTrackPosition.SOUTH_WEST, Pair.of(baseblockposition3, baseblockposition));
/*     */           enummap.put(BlockPropertyTrackPosition.NORTH_WEST, Pair.of(baseblockposition2, baseblockposition));
/*     */           enummap.put(BlockPropertyTrackPosition.NORTH_EAST, Pair.of(baseblockposition2, baseblockposition1));
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private int aj;
/*     */   private double ak;
/*     */   private double al;
/*     */   private double am;
/*     */   private double an;
/*     */   private double ao;
/*     */   public boolean slowWhenEmpty = true;
/*  62 */   private double derailedX = 0.5D;
/*  63 */   private double derailedY = 0.5D;
/*  64 */   private double derailedZ = 0.5D;
/*  65 */   private double flyingX = 0.949999988079071D;
/*  66 */   private double flyingY = 0.949999988079071D;
/*  67 */   private double flyingZ = 0.949999988079071D;
/*  68 */   public double maxSpeed = 0.4D;
/*     */ 
/*     */   
/*     */   protected EntityMinecartAbstract(EntityTypes<?> entitytypes, World world) {
/*  72 */     super(entitytypes, world);
/*  73 */     this.i = true;
/*     */   }
/*     */   
/*     */   protected EntityMinecartAbstract(EntityTypes<?> entitytypes, World world, double d0, double d1, double d2) {
/*  77 */     this(entitytypes, world);
/*  78 */     setPosition(d0, d1, d2);
/*  79 */     setMot(Vec3D.ORIGIN);
/*  80 */     this.lastX = d0;
/*  81 */     this.lastY = d1;
/*  82 */     this.lastZ = d2;
/*     */   }
/*     */   
/*     */   public static EntityMinecartAbstract a(World world, double d0, double d1, double d2, EnumMinecartType entityminecartabstract_enumminecarttype) {
/*  86 */     return (entityminecartabstract_enumminecarttype == EnumMinecartType.CHEST) ? new EntityMinecartChest(world, d0, d1, d2) : ((entityminecartabstract_enumminecarttype == EnumMinecartType.FURNACE) ? new EntityMinecartFurnace(world, d0, d1, d2) : ((entityminecartabstract_enumminecarttype == EnumMinecartType.TNT) ? new EntityMinecartTNT(world, d0, d1, d2) : ((entityminecartabstract_enumminecarttype == EnumMinecartType.SPAWNER) ? new EntityMinecartMobSpawner(world, d0, d1, d2) : ((entityminecartabstract_enumminecarttype == EnumMinecartType.HOPPER) ? new EntityMinecartHopper(world, d0, d1, d2) : ((entityminecartabstract_enumminecarttype == EnumMinecartType.COMMAND_BLOCK) ? new EntityMinecartCommandBlock(world, d0, d1, d2) : new EntityMinecartRideable(world, d0, d1, d2))))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  96 */     this.datawatcher.register(b, Integer.valueOf(0));
/*  97 */     this.datawatcher.register(c, Integer.valueOf(1));
/*  98 */     this.datawatcher.register(d, Float.valueOf(0.0F));
/*  99 */     this.datawatcher.register(e, Integer.valueOf(Block.getCombinedId(Blocks.AIR.getBlockData())));
/* 100 */     this.datawatcher.register(f, Integer.valueOf(6));
/* 101 */     this.datawatcher.register(g, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean j(Entity entity) {
/* 106 */     return EntityBoat.a(this, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3D a(EnumDirection.EnumAxis enumdirection_enumaxis, BlockUtil.Rectangle blockutil_rectangle) {
/* 116 */     return EntityLiving.h(super.a(enumdirection_enumaxis, blockutil_rectangle));
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/* 121 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3D b(EntityLiving entityliving) {
/* 126 */     EnumDirection enumdirection = getAdjustedDirection();
/*     */     
/* 128 */     if (enumdirection.n() == EnumDirection.EnumAxis.Y) {
/* 129 */       return super.b(entityliving);
/*     */     }
/* 131 */     int[][] aint = DismountUtil.a(enumdirection);
/* 132 */     BlockPosition blockposition = getChunkCoordinates();
/* 133 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 134 */     ImmutableList<EntityPose> immutablelist = entityliving.ei();
/* 135 */     UnmodifiableIterator unmodifiableiterator = immutablelist.iterator();
/*     */     
/* 137 */     while (unmodifiableiterator.hasNext()) {
/* 138 */       EntityPose entitypose = (EntityPose)unmodifiableiterator.next();
/* 139 */       EntitySize entitysize = entityliving.a(entitypose);
/* 140 */       float f = Math.min(entitysize.width, 1.0F) / 2.0F;
/* 141 */       UnmodifiableIterator unmodifiableiterator1 = ((ImmutableList)ag.get(entitypose)).iterator();
/*     */       
/* 143 */       while (unmodifiableiterator1.hasNext()) {
/* 144 */         int i = ((Integer)unmodifiableiterator1.next()).intValue();
/* 145 */         int[][] aint1 = aint;
/* 146 */         int j = aint.length;
/*     */         
/* 148 */         for (int k = 0; k < j; k++) {
/* 149 */           int[] aint2 = aint1[k];
/*     */           
/* 151 */           blockposition_mutableblockposition.d(blockposition.getX() + aint2[0], blockposition.getY() + i, blockposition.getZ() + aint2[1]);
/* 152 */           double d0 = this.world.a(DismountUtil.a(this.world, blockposition_mutableblockposition), () -> DismountUtil.a(this.world, blockposition_mutableblockposition.down()));
/*     */ 
/*     */ 
/*     */           
/* 156 */           if (DismountUtil.a(d0)) {
/* 157 */             AxisAlignedBB axisalignedbb = new AxisAlignedBB(-f, 0.0D, -f, f, entitysize.height, f);
/* 158 */             Vec3D vec3d = Vec3D.a(blockposition_mutableblockposition, d0);
/*     */             
/* 160 */             if (DismountUtil.a(this.world, entityliving, axisalignedbb.c(vec3d))) {
/* 161 */               entityliving.setPose(entitypose);
/* 162 */               return vec3d;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     double d1 = (getBoundingBox()).maxY;
/*     */     
/* 171 */     blockposition_mutableblockposition.c(blockposition.getX(), d1, blockposition.getZ());
/* 172 */     UnmodifiableIterator unmodifiableiterator2 = immutablelist.iterator();
/*     */     
/* 174 */     while (unmodifiableiterator2.hasNext()) {
/* 175 */       EntityPose entitypose1 = (EntityPose)unmodifiableiterator2.next();
/* 176 */       double d2 = (entityliving.a(entitypose1)).height;
/* 177 */       int l = MathHelper.f(d1 - blockposition_mutableblockposition.getY() + d2);
/* 178 */       double d3 = DismountUtil.a(blockposition_mutableblockposition, l, blockposition1 -> this.world.getType(blockposition1).getCollisionShape(this.world, blockposition1));
/*     */ 
/*     */ 
/*     */       
/* 182 */       if (d1 + d2 <= d3) {
/* 183 */         entityliving.setPose(entitypose1);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 188 */     return super.b(entityliving);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 194 */     if (!this.world.isClientSide && !this.dead) {
/* 195 */       if (isInvulnerable(damagesource)) {
/* 196 */         return false;
/*     */       }
/*     */       
/* 199 */       Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 200 */       CraftEntity craftEntity = (damagesource.getEntity() == null) ? null : damagesource.getEntity().getBukkitEntity();
/*     */       
/* 202 */       VehicleDamageEvent event = new VehicleDamageEvent(vehicle, (Entity)craftEntity, f);
/* 203 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 205 */       if (event.isCancelled()) {
/* 206 */         return false;
/*     */       }
/*     */       
/* 209 */       f = (float)event.getDamage();
/*     */       
/* 211 */       d(-n());
/* 212 */       c(10);
/* 213 */       velocityChanged();
/* 214 */       setDamage(getDamage() + f * 10.0F);
/* 215 */       boolean flag = (damagesource.getEntity() instanceof EntityHuman && ((EntityHuman)damagesource.getEntity()).abilities.canInstantlyBuild);
/*     */       
/* 217 */       if (flag || getDamage() > 40.0F) {
/*     */         
/* 219 */         VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, (Entity)craftEntity);
/* 220 */         this.world.getServer().getPluginManager().callEvent((Event)destroyEvent);
/*     */         
/* 222 */         if (destroyEvent.isCancelled()) {
/* 223 */           setDamage(40.0F);
/* 224 */           return true;
/*     */         } 
/*     */         
/* 227 */         ejectPassengers();
/* 228 */         if (flag && !hasCustomName()) {
/* 229 */           die();
/*     */         } else {
/* 231 */           a(damagesource);
/*     */         } 
/*     */       } 
/*     */       
/* 235 */       return true;
/*     */     } 
/*     */     
/* 238 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getBlockSpeedFactor() {
/* 244 */     IBlockData iblockdata = this.world.getType(getChunkCoordinates());
/*     */     
/* 246 */     return iblockdata.a(TagsBlock.RAILS) ? 1.0F : super.getBlockSpeedFactor();
/*     */   }
/*     */   
/*     */   public void a(DamageSource damagesource) {
/* 250 */     die();
/* 251 */     if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 252 */       ItemStack itemstack = new ItemStack(Items.MINECART);
/*     */       
/* 254 */       if (hasCustomName()) {
/* 255 */         itemstack.a(getCustomName());
/*     */       }
/*     */       
/* 258 */       a(itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 265 */     return !this.dead;
/*     */   }
/*     */   
/*     */   private static Pair<BaseBlockPosition, BaseBlockPosition> a(BlockPropertyTrackPosition blockpropertytrackposition) {
/* 269 */     return ai.get(blockpropertytrackposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection getAdjustedDirection() {
/* 274 */     return this.ah ? getDirection().opposite().g() : getDirection().g();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 280 */     double prevX = locX();
/* 281 */     double prevY = locY();
/* 282 */     double prevZ = locZ();
/* 283 */     float prevYaw = this.yaw;
/* 284 */     float prevPitch = this.pitch;
/*     */ 
/*     */     
/* 287 */     if (getType() > 0) {
/* 288 */       c(getType() - 1);
/*     */     }
/*     */     
/* 291 */     if (getDamage() > 0.0F) {
/* 292 */       setDamage(getDamage() - 1.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     performVoidDamage();
/*     */ 
/*     */ 
/*     */     
/* 306 */     if (this.world.isClientSide) {
/* 307 */       if (this.aj > 0) {
/* 308 */         double d0 = locX() + (this.ak - locX()) / this.aj;
/* 309 */         double d1 = locY() + (this.al - locY()) / this.aj;
/* 310 */         double d2 = locZ() + (this.am - locZ()) / this.aj;
/* 311 */         double d3 = MathHelper.g(this.an - this.yaw);
/*     */         
/* 313 */         this.yaw = (float)(this.yaw + d3 / this.aj);
/* 314 */         this.pitch = (float)(this.pitch + (this.ao - this.pitch) / this.aj);
/* 315 */         this.aj--;
/* 316 */         setPosition(d0, d1, d2);
/* 317 */         setYawPitch(this.yaw, this.pitch);
/*     */       } else {
/* 319 */         ae();
/* 320 */         setYawPitch(this.yaw, this.pitch);
/*     */       } 
/*     */     } else {
/*     */       
/* 324 */       if (!isNoGravity()) {
/* 325 */         setMot(getMot().add(0.0D, -0.04D, 0.0D));
/*     */       }
/*     */       
/* 328 */       int i = MathHelper.floor(locX());
/* 329 */       int j = MathHelper.floor(locY());
/* 330 */       int k = MathHelper.floor(locZ());
/*     */       
/* 332 */       if (this.world.getType(new BlockPosition(i, j - 1, k)).a(TagsBlock.RAILS)) {
/* 333 */         j--;
/*     */       }
/*     */       
/* 336 */       BlockPosition blockposition = new BlockPosition(i, j, k);
/* 337 */       IBlockData iblockdata = this.world.getType(blockposition);
/*     */       
/* 339 */       if (BlockMinecartTrackAbstract.g(iblockdata)) {
/* 340 */         c(blockposition, iblockdata);
/* 341 */         if (iblockdata.a(Blocks.ACTIVATOR_RAIL)) {
/* 342 */           a(i, j, k, ((Boolean)iblockdata.get(BlockPoweredRail.POWERED)).booleanValue());
/*     */         }
/*     */       } else {
/* 345 */         h();
/*     */       } 
/*     */       
/* 348 */       checkBlockCollisions();
/* 349 */       this.pitch = 0.0F;
/* 350 */       double d4 = this.lastX - locX();
/* 351 */       double d5 = this.lastZ - locZ();
/*     */       
/* 353 */       if (d4 * d4 + d5 * d5 > 0.001D) {
/* 354 */         this.yaw = (float)(MathHelper.d(d5, d4) * 180.0D / Math.PI);
/* 355 */         if (this.ah) {
/* 356 */           this.yaw += 180.0F;
/*     */         }
/*     */       } 
/*     */       
/* 360 */       double d6 = MathHelper.g(this.yaw - this.lastYaw);
/*     */       
/* 362 */       if (d6 < -170.0D || d6 >= 170.0D) {
/* 363 */         this.yaw += 180.0F;
/* 364 */         this.ah = !this.ah;
/*     */       } 
/*     */       
/* 367 */       setYawPitch(this.yaw, this.pitch);
/*     */       
/* 369 */       CraftWorld craftWorld = this.world.getWorld();
/* 370 */       Location from = new Location((World)craftWorld, prevX, prevY, prevZ, prevYaw, prevPitch);
/* 371 */       Location to = new Location((World)craftWorld, locX(), locY(), locZ(), this.yaw, this.pitch);
/* 372 */       Vehicle vehicle = (Vehicle)getBukkitEntity();
/*     */       
/* 374 */       this.world.getServer().getPluginManager().callEvent((Event)new VehicleUpdateEvent(vehicle));
/*     */       
/* 376 */       if (!from.equals(to)) {
/* 377 */         this.world.getServer().getPluginManager().callEvent((Event)new VehicleMoveEvent(vehicle, from, to));
/*     */       }
/*     */       
/* 380 */       if (getMinecartType() == EnumMinecartType.RIDEABLE && c(getMot()) > 0.01D) {
/* 381 */         List<Entity> list = this.world.getEntities(this, getBoundingBox().grow(0.20000000298023224D, 0.0D, 0.20000000298023224D), IEntitySelector.a(this));
/*     */         
/* 383 */         if (!list.isEmpty()) {
/* 384 */           for (int l = 0; l < list.size(); l++) {
/* 385 */             Entity entity = list.get(l);
/*     */             
/* 387 */             if (!(entity instanceof EntityHuman) && !(entity instanceof EntityIronGolem) && !(entity instanceof EntityMinecartAbstract) && !isVehicle() && !entity.isPassenger()) {
/*     */               
/* 389 */               VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, (Entity)entity.getBukkitEntity());
/* 390 */               this.world.getServer().getPluginManager().callEvent((Event)collisionEvent);
/*     */               
/* 392 */               if (!collisionEvent.isCancelled())
/*     */               {
/*     */ 
/*     */                 
/* 396 */                 entity.startRiding(this); } 
/*     */               continue;
/*     */             } 
/* 399 */             if (!isSameVehicle(entity)) {
/* 400 */               VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, (Entity)entity.getBukkitEntity());
/* 401 */               this.world.getServer().getPluginManager().callEvent((Event)collisionEvent);
/*     */               
/* 403 */               if (collisionEvent.isCancelled()) {
/*     */                 continue;
/*     */               }
/*     */             } 
/*     */             
/* 408 */             entity.collide(this);
/*     */             continue;
/*     */           } 
/*     */         }
/*     */       } else {
/* 413 */         Iterator<Entity> iterator = this.world.getEntities(this, getBoundingBox().grow(0.20000000298023224D, 0.0D, 0.20000000298023224D)).iterator();
/*     */         
/* 415 */         while (iterator.hasNext()) {
/* 416 */           Entity entity1 = iterator.next();
/*     */           
/* 418 */           if (!w(entity1) && entity1.isCollidable() && entity1 instanceof EntityMinecartAbstract) {
/*     */             
/* 420 */             VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, (Entity)entity1.getBukkitEntity());
/* 421 */             this.world.getServer().getPluginManager().callEvent((Event)collisionEvent);
/*     */             
/* 423 */             if (collisionEvent.isCancelled()) {
/*     */               continue;
/*     */             }
/*     */             
/* 427 */             entity1.collide(this);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 432 */       aJ();
/* 433 */       if (aP()) {
/* 434 */         burnFromLava();
/* 435 */         this.fallDistance *= 0.5F;
/*     */       } 
/*     */       
/* 438 */       this.justCreated = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double getMaxSpeed() {
/* 443 */     return this.maxSpeed;
/*     */   }
/*     */   
/*     */   public void a(int i, int j, int k, boolean flag) {}
/*     */   
/*     */   protected void h() {
/* 449 */     double d0 = getMaxSpeed();
/* 450 */     Vec3D vec3d = getMot();
/*     */     
/* 452 */     setMot(MathHelper.a(vec3d.x, -d0, d0), vec3d.y, MathHelper.a(vec3d.z, -d0, d0));
/* 453 */     if (this.onGround)
/*     */     {
/* 455 */       setMot(new Vec3D((getMot()).x * this.derailedX, (getMot()).y * this.derailedY, (getMot()).z * this.derailedZ));
/*     */     }
/*     */ 
/*     */     
/* 459 */     move(EnumMoveType.SELF, getMot());
/* 460 */     if (!this.onGround)
/*     */     {
/* 462 */       setMot(new Vec3D((getMot()).x * this.flyingX, (getMot()).y * this.flyingY, (getMot()).z * this.flyingZ));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(BlockPosition blockposition, IBlockData iblockdata) {
/*     */     double d15;
/* 469 */     this.fallDistance = 0.0F;
/* 470 */     double d0 = locX();
/* 471 */     double d1 = locY();
/* 472 */     double d2 = locZ();
/* 473 */     Vec3D vec3d = p(d0, d1, d2);
/*     */     
/* 475 */     d1 = blockposition.getY();
/* 476 */     boolean flag = false;
/* 477 */     boolean flag1 = false;
/* 478 */     BlockMinecartTrackAbstract blockminecarttrackabstract = (BlockMinecartTrackAbstract)iblockdata.getBlock();
/*     */     
/* 480 */     if (blockminecarttrackabstract == Blocks.POWERED_RAIL) {
/* 481 */       flag = ((Boolean)iblockdata.get(BlockPoweredRail.POWERED)).booleanValue();
/* 482 */       flag1 = !flag;
/*     */     } 
/*     */     
/* 485 */     double d3 = 0.0078125D;
/* 486 */     Vec3D vec3d1 = getMot();
/* 487 */     BlockPropertyTrackPosition blockpropertytrackposition = (BlockPropertyTrackPosition)iblockdata.get(blockminecarttrackabstract.d());
/*     */     
/* 489 */     switch (blockpropertytrackposition) {
/*     */       case ASCENDING_EAST:
/* 491 */         setMot(vec3d1.add(-0.0078125D, 0.0D, 0.0D));
/* 492 */         d1++;
/*     */         break;
/*     */       case ASCENDING_WEST:
/* 495 */         setMot(vec3d1.add(0.0078125D, 0.0D, 0.0D));
/* 496 */         d1++;
/*     */         break;
/*     */       case ASCENDING_NORTH:
/* 499 */         setMot(vec3d1.add(0.0D, 0.0D, 0.0078125D));
/* 500 */         d1++;
/*     */         break;
/*     */       case ASCENDING_SOUTH:
/* 503 */         setMot(vec3d1.add(0.0D, 0.0D, -0.0078125D));
/* 504 */         d1++;
/*     */         break;
/*     */     } 
/* 507 */     vec3d1 = getMot();
/* 508 */     Pair<BaseBlockPosition, BaseBlockPosition> pair = a(blockpropertytrackposition);
/* 509 */     BaseBlockPosition baseblockposition = (BaseBlockPosition)pair.getFirst();
/* 510 */     BaseBlockPosition baseblockposition1 = (BaseBlockPosition)pair.getSecond();
/* 511 */     double d4 = (baseblockposition1.getX() - baseblockposition.getX());
/* 512 */     double d5 = (baseblockposition1.getZ() - baseblockposition.getZ());
/* 513 */     double d6 = Math.sqrt(d4 * d4 + d5 * d5);
/* 514 */     double d7 = vec3d1.x * d4 + vec3d1.z * d5;
/*     */     
/* 516 */     if (d7 < 0.0D) {
/* 517 */       d4 = -d4;
/* 518 */       d5 = -d5;
/*     */     } 
/*     */     
/* 521 */     double d8 = Math.min(2.0D, Math.sqrt(c(vec3d1)));
/*     */     
/* 523 */     vec3d1 = new Vec3D(d8 * d4 / d6, vec3d1.y, d8 * d5 / d6);
/* 524 */     setMot(vec3d1);
/* 525 */     Entity entity = getPassengers().isEmpty() ? null : getPassengers().get(0);
/*     */     
/* 527 */     if (entity instanceof EntityHuman) {
/* 528 */       Vec3D vec3d2 = entity.getMot();
/* 529 */       double d9 = c(vec3d2);
/* 530 */       double d10 = c(getMot());
/*     */       
/* 532 */       if (d9 > 1.0E-4D && d10 < 0.01D) {
/* 533 */         setMot(getMot().add(vec3d2.x * 0.1D, 0.0D, vec3d2.z * 0.1D));
/* 534 */         flag1 = false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 540 */     if (flag1) {
/* 541 */       double d = Math.sqrt(c(getMot()));
/* 542 */       if (d < 0.03D) {
/* 543 */         setMot(Vec3D.ORIGIN);
/*     */       } else {
/* 545 */         setMot(getMot().d(0.5D, 0.0D, 0.5D));
/*     */       } 
/*     */     } 
/*     */     
/* 549 */     double d11 = blockposition.getX() + 0.5D + baseblockposition.getX() * 0.5D;
/* 550 */     double d12 = blockposition.getZ() + 0.5D + baseblockposition.getZ() * 0.5D;
/* 551 */     double d13 = blockposition.getX() + 0.5D + baseblockposition1.getX() * 0.5D;
/* 552 */     double d14 = blockposition.getZ() + 0.5D + baseblockposition1.getZ() * 0.5D;
/*     */     
/* 554 */     d4 = d13 - d11;
/* 555 */     d5 = d14 - d12;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 560 */     if (d4 == 0.0D) {
/* 561 */       d15 = d2 - blockposition.getZ();
/* 562 */     } else if (d5 == 0.0D) {
/* 563 */       d15 = d0 - blockposition.getX();
/*     */     } else {
/* 565 */       double d9 = d0 - d11;
/* 566 */       double d10 = d2 - d12;
/* 567 */       d15 = (d9 * d4 + d10 * d5) * 2.0D;
/*     */     } 
/*     */     
/* 570 */     d0 = d11 + d4 * d15;
/* 571 */     d2 = d12 + d5 * d15;
/* 572 */     setPosition(d0, d1, d2);
/* 573 */     double d16 = isVehicle() ? 0.75D : 1.0D;
/* 574 */     double d17 = getMaxSpeed();
/* 575 */     vec3d1 = getMot();
/* 576 */     move(EnumMoveType.SELF, new Vec3D(MathHelper.a(d16 * vec3d1.x, -d17, d17), 0.0D, MathHelper.a(d16 * vec3d1.z, -d17, d17)));
/* 577 */     if (baseblockposition.getY() != 0 && MathHelper.floor(locX()) - blockposition.getX() == baseblockposition.getX() && MathHelper.floor(locZ()) - blockposition.getZ() == baseblockposition.getZ()) {
/* 578 */       setPosition(locX(), locY() + baseblockposition.getY(), locZ());
/* 579 */     } else if (baseblockposition1.getY() != 0 && MathHelper.floor(locX()) - blockposition.getX() == baseblockposition1.getX() && MathHelper.floor(locZ()) - blockposition.getZ() == baseblockposition1.getZ()) {
/* 580 */       setPosition(locX(), locY() + baseblockposition1.getY(), locZ());
/*     */     } 
/*     */     
/* 583 */     decelerate();
/* 584 */     Vec3D vec3d3 = p(locX(), locY(), locZ());
/*     */ 
/*     */ 
/*     */     
/* 588 */     if (vec3d3 != null && vec3d != null) {
/* 589 */       double d19 = (vec3d.y - vec3d3.y) * 0.05D;
/*     */       
/* 591 */       Vec3D vec3d4 = getMot();
/* 592 */       double d18 = Math.sqrt(c(vec3d4));
/* 593 */       if (d18 > 0.0D) {
/* 594 */         setMot(vec3d4.d((d18 + d19) / d18, 1.0D, (d18 + d19) / d18));
/*     */       }
/*     */       
/* 597 */       setPosition(locX(), vec3d3.y, locZ());
/*     */     } 
/*     */     
/* 600 */     int i = MathHelper.floor(locX());
/* 601 */     int j = MathHelper.floor(locZ());
/*     */     
/* 603 */     if (i != blockposition.getX() || j != blockposition.getZ()) {
/* 604 */       Vec3D vec3d4 = getMot();
/* 605 */       double d18 = Math.sqrt(c(vec3d4));
/* 606 */       setMot(d18 * (i - blockposition.getX()), vec3d4.y, d18 * (j - blockposition.getZ()));
/*     */     } 
/*     */     
/* 609 */     if (flag) {
/* 610 */       Vec3D vec3d4 = getMot();
/* 611 */       double d18 = Math.sqrt(c(vec3d4));
/* 612 */       if (d18 > 0.01D) {
/* 613 */         double d20 = 0.06D;
/*     */         
/* 615 */         setMot(vec3d4.add(vec3d4.x / d18 * 0.06D, 0.0D, vec3d4.z / d18 * 0.06D));
/*     */       } else {
/* 617 */         Vec3D vec3d5 = getMot();
/* 618 */         double d21 = vec3d5.x;
/* 619 */         double d22 = vec3d5.z;
/*     */         
/* 621 */         if (blockpropertytrackposition == BlockPropertyTrackPosition.EAST_WEST) {
/* 622 */           if (a(blockposition.west())) {
/* 623 */             d21 = 0.02D;
/* 624 */           } else if (a(blockposition.east())) {
/* 625 */             d21 = -0.02D;
/*     */           } 
/*     */         } else {
/* 628 */           if (blockpropertytrackposition != BlockPropertyTrackPosition.NORTH_SOUTH) {
/*     */             return;
/*     */           }
/*     */           
/* 632 */           if (a(blockposition.north())) {
/* 633 */             d22 = 0.02D;
/* 634 */           } else if (a(blockposition.south())) {
/* 635 */             d22 = -0.02D;
/*     */           } 
/*     */         } 
/*     */         
/* 639 */         setMot(d21, vec3d5.y, d22);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(BlockPosition blockposition) {
/* 646 */     return this.world.getType(blockposition).isOccluding(this.world, blockposition);
/*     */   }
/*     */   
/*     */   protected void decelerate() {
/* 650 */     double d0 = (isVehicle() || !this.slowWhenEmpty) ? 0.997D : 0.96D;
/*     */     
/* 652 */     setMot(getMot().d(d0, 0.0D, d0));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Vec3D p(double d0, double d1, double d2) {
/* 657 */     int i = MathHelper.floor(d0);
/* 658 */     int j = MathHelper.floor(d1);
/* 659 */     int k = MathHelper.floor(d2);
/*     */     
/* 661 */     if (this.world.getType(new BlockPosition(i, j - 1, k)).a(TagsBlock.RAILS)) {
/* 662 */       j--;
/*     */     }
/*     */     
/* 665 */     IBlockData iblockdata = this.world.getType(new BlockPosition(i, j, k));
/*     */     
/* 667 */     if (BlockMinecartTrackAbstract.g(iblockdata)) {
/* 668 */       double d12; BlockPropertyTrackPosition blockpropertytrackposition = (BlockPropertyTrackPosition)iblockdata.get(((BlockMinecartTrackAbstract)iblockdata.getBlock()).d());
/* 669 */       Pair<BaseBlockPosition, BaseBlockPosition> pair = a(blockpropertytrackposition);
/* 670 */       BaseBlockPosition baseblockposition = (BaseBlockPosition)pair.getFirst();
/* 671 */       BaseBlockPosition baseblockposition1 = (BaseBlockPosition)pair.getSecond();
/* 672 */       double d3 = i + 0.5D + baseblockposition.getX() * 0.5D;
/* 673 */       double d4 = j + 0.0625D + baseblockposition.getY() * 0.5D;
/* 674 */       double d5 = k + 0.5D + baseblockposition.getZ() * 0.5D;
/* 675 */       double d6 = i + 0.5D + baseblockposition1.getX() * 0.5D;
/* 676 */       double d7 = j + 0.0625D + baseblockposition1.getY() * 0.5D;
/* 677 */       double d8 = k + 0.5D + baseblockposition1.getZ() * 0.5D;
/* 678 */       double d9 = d6 - d3;
/* 679 */       double d10 = (d7 - d4) * 2.0D;
/* 680 */       double d11 = d8 - d5;
/*     */ 
/*     */       
/* 683 */       if (d9 == 0.0D) {
/* 684 */         d12 = d2 - k;
/* 685 */       } else if (d11 == 0.0D) {
/* 686 */         d12 = d0 - i;
/*     */       } else {
/* 688 */         double d13 = d0 - d3;
/* 689 */         double d14 = d2 - d5;
/*     */         
/* 691 */         d12 = (d13 * d9 + d14 * d11) * 2.0D;
/*     */       } 
/*     */       
/* 694 */       d0 = d3 + d9 * d12;
/* 695 */       d1 = d4 + d10 * d12;
/* 696 */       d2 = d5 + d11 * d12;
/* 697 */       if (d10 < 0.0D) {
/* 698 */         d1++;
/* 699 */       } else if (d10 > 0.0D) {
/* 700 */         d1 += 0.5D;
/*     */       } 
/*     */       
/* 703 */       return new Vec3D(d0, d1, d2);
/*     */     } 
/* 705 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/* 711 */     if (nbttagcompound.getBoolean("CustomDisplayTile")) {
/* 712 */       setDisplayBlock(GameProfileSerializer.c(nbttagcompound.getCompound("DisplayState")));
/* 713 */       setDisplayBlockOffset(nbttagcompound.getInt("DisplayOffset"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/* 720 */     if (t()) {
/* 721 */       nbttagcompound.setBoolean("CustomDisplayTile", true);
/* 722 */       nbttagcompound.set("DisplayState", GameProfileSerializer.a(getDisplayBlock()));
/* 723 */       nbttagcompound.setInt("DisplayOffset", getDisplayBlockOffset());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void collide(Entity entity) {
/* 730 */     if (!this.world.isClientSide && 
/* 731 */       !entity.noclip && !this.noclip && 
/* 732 */       !w(entity)) {
/*     */       
/* 734 */       VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent((Vehicle)getBukkitEntity(), (Entity)entity.getBukkitEntity());
/* 735 */       this.world.getServer().getPluginManager().callEvent((Event)collisionEvent);
/*     */       
/* 737 */       if (collisionEvent.isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/* 741 */       double d0 = entity.locX() - locX();
/* 742 */       double d1 = entity.locZ() - locZ();
/* 743 */       double d2 = d0 * d0 + d1 * d1;
/*     */       
/* 745 */       if (d2 >= 9.999999747378752E-5D) {
/* 746 */         d2 = MathHelper.sqrt(d2);
/* 747 */         d0 /= d2;
/* 748 */         d1 /= d2;
/* 749 */         double d3 = 1.0D / d2;
/*     */         
/* 751 */         if (d3 > 1.0D) {
/* 752 */           d3 = 1.0D;
/*     */         }
/*     */         
/* 755 */         d0 *= d3;
/* 756 */         d1 *= d3;
/* 757 */         d0 *= 0.10000000149011612D;
/* 758 */         d1 *= 0.10000000149011612D;
/* 759 */         d0 *= (1.0F - this.I);
/* 760 */         d1 *= (1.0F - this.I);
/* 761 */         d0 *= 0.5D;
/* 762 */         d1 *= 0.5D;
/* 763 */         if (entity instanceof EntityMinecartAbstract) {
/* 764 */           double d4 = entity.locX() - locX();
/* 765 */           double d5 = entity.locZ() - locZ();
/* 766 */           Vec3D vec3d = (new Vec3D(d4, 0.0D, d5)).d();
/* 767 */           Vec3D vec3d1 = (new Vec3D(MathHelper.cos(this.yaw * 0.017453292F), 0.0D, MathHelper.sin(this.yaw * 0.017453292F))).d();
/* 768 */           double d6 = Math.abs(vec3d.b(vec3d1));
/*     */           
/* 770 */           if (d6 < 0.800000011920929D) {
/*     */             return;
/*     */           }
/*     */           
/* 774 */           Vec3D vec3d2 = getMot();
/* 775 */           Vec3D vec3d3 = entity.getMot();
/*     */           
/* 777 */           if (((EntityMinecartAbstract)entity).getMinecartType() == EnumMinecartType.FURNACE && getMinecartType() != EnumMinecartType.FURNACE) {
/* 778 */             setMot(vec3d2.d(0.2D, 1.0D, 0.2D));
/* 779 */             i(vec3d3.x - d0, 0.0D, vec3d3.z - d1);
/* 780 */             entity.setMot(vec3d3.d(0.95D, 1.0D, 0.95D));
/* 781 */           } else if (((EntityMinecartAbstract)entity).getMinecartType() != EnumMinecartType.FURNACE && getMinecartType() == EnumMinecartType.FURNACE) {
/* 782 */             entity.setMot(vec3d3.d(0.2D, 1.0D, 0.2D));
/* 783 */             entity.i(vec3d2.x + d0, 0.0D, vec3d2.z + d1);
/* 784 */             setMot(vec3d2.d(0.95D, 1.0D, 0.95D));
/*     */           } else {
/* 786 */             double d7 = (vec3d3.x + vec3d2.x) / 2.0D;
/* 787 */             double d8 = (vec3d3.z + vec3d2.z) / 2.0D;
/*     */             
/* 789 */             setMot(vec3d2.d(0.2D, 1.0D, 0.2D));
/* 790 */             i(d7 - d0, 0.0D, d8 - d1);
/* 791 */             entity.setMot(vec3d3.d(0.2D, 1.0D, 0.2D));
/* 792 */             entity.i(d7 + d0, 0.0D, d8 + d1);
/*     */           } 
/*     */         } else {
/* 795 */           i(-d0, 0.0D, -d1);
/* 796 */           entity.i(d0 / 4.0D, 0.0D, d1 / 4.0D);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamage(float f) {
/* 806 */     this.datawatcher.set(d, Float.valueOf(f));
/*     */   }
/*     */   
/*     */   public float getDamage() {
/* 810 */     return ((Float)this.datawatcher.<Float>get(d)).floatValue();
/*     */   }
/*     */   
/*     */   public void c(int i) {
/* 814 */     this.datawatcher.set(b, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int getType() {
/* 818 */     return ((Integer)this.datawatcher.<Integer>get(b)).intValue();
/*     */   }
/*     */   
/*     */   public void d(int i) {
/* 822 */     this.datawatcher.set(c, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int n() {
/* 826 */     return ((Integer)this.datawatcher.<Integer>get(c)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getDisplayBlock() {
/* 832 */     return !t() ? q() : Block.getByCombinedId(((Integer)getDataWatcher().<Integer>get(e)).intValue());
/*     */   }
/*     */   
/*     */   public IBlockData q() {
/* 836 */     return Blocks.AIR.getBlockData();
/*     */   }
/*     */   
/*     */   public int getDisplayBlockOffset() {
/* 840 */     return !t() ? s() : ((Integer)getDataWatcher().<Integer>get(f)).intValue();
/*     */   }
/*     */   
/*     */   public int s() {
/* 844 */     return 6;
/*     */   }
/*     */   
/*     */   public void setDisplayBlock(IBlockData iblockdata) {
/* 848 */     getDataWatcher().set(e, Integer.valueOf(Block.getCombinedId(iblockdata)));
/* 849 */     a(true);
/*     */   }
/*     */   
/*     */   public void setDisplayBlockOffset(int i) {
/* 853 */     getDataWatcher().set(f, Integer.valueOf(i));
/* 854 */     a(true);
/*     */   }
/*     */   
/*     */   public boolean t() {
/* 858 */     return ((Boolean)getDataWatcher().<Boolean>get(g)).booleanValue();
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 862 */     getDataWatcher().set(g, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 867 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */   
/*     */   public enum EnumMinecartType
/*     */   {
/* 872 */     RIDEABLE, CHEST, FURNACE, TNT, SPAWNER, HOPPER, COMMAND_BLOCK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getFlyingVelocityMod() {
/* 879 */     return new Vector(this.flyingX, this.flyingY, this.flyingZ);
/*     */   }
/*     */   
/*     */   public void setFlyingVelocityMod(Vector flying) {
/* 883 */     this.flyingX = flying.getX();
/* 884 */     this.flyingY = flying.getY();
/* 885 */     this.flyingZ = flying.getZ();
/*     */   }
/*     */   
/*     */   public Vector getDerailedVelocityMod() {
/* 889 */     return new Vector(this.derailedX, this.derailedY, this.derailedZ);
/*     */   }
/*     */   
/*     */   public void setDerailedVelocityMod(Vector derailed) {
/* 893 */     this.derailedX = derailed.getX();
/* 894 */     this.derailedY = derailed.getY();
/* 895 */     this.derailedZ = derailed.getZ();
/*     */   }
/*     */   
/*     */   public abstract EnumMinecartType getMinecartType();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */