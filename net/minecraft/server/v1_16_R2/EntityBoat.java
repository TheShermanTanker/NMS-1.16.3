/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Vehicle;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.vehicle.VehicleDamageEvent;
/*     */ import org.bukkit.event.vehicle.VehicleDestroyEvent;
/*     */ import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
/*     */ import org.bukkit.event.vehicle.VehicleMoveEvent;
/*     */ 
/*     */ public class EntityBoat extends Entity {
/*  18 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.b);
/*  19 */   private static final DataWatcherObject<Integer> c = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.b);
/*  20 */   private static final DataWatcherObject<Float> d = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.c);
/*  21 */   private static final DataWatcherObject<Integer> e = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.b);
/*  22 */   private static final DataWatcherObject<Boolean> f = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.i);
/*  23 */   private static final DataWatcherObject<Boolean> g = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.i);
/*  24 */   private static final DataWatcherObject<Integer> ag = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.b);
/*     */   
/*     */   private final float[] ah;
/*     */   
/*     */   private float ai;
/*     */   
/*     */   private float aj;
/*     */   private float ak;
/*     */   private int al;
/*     */   private double am;
/*     */   private double an;
/*     */   private double ao;
/*     */   private double ap;
/*     */   private double aq;
/*     */   private boolean ar;
/*     */   private boolean as;
/*     */   private boolean at;
/*     */   private boolean au;
/*     */   private double av;
/*     */   private float aw;
/*     */   private EnumStatus ax;
/*     */   private EnumStatus ay;
/*     */   private double az;
/*     */   private boolean aA;
/*     */   private boolean aB;
/*     */   private float aC;
/*     */   private float aD;
/*     */   private float aE;
/*  52 */   public double maxSpeed = 0.4D;
/*  53 */   public double occupiedDeceleration = 0.2D;
/*  54 */   public double unoccupiedDeceleration = -1.0D;
/*     */   public boolean landBoats = false;
/*     */   private Location lastLocation;
/*     */   
/*     */   public EntityBoat(EntityTypes<? extends EntityBoat> entitytypes, World world) {
/*  59 */     super(entitytypes, world);
/*  60 */     this.ah = new float[2];
/*  61 */     this.i = true;
/*     */   }
/*     */   
/*     */   public EntityBoat(World world, double d0, double d1, double d2) {
/*  65 */     this(EntityTypes.BOAT, world);
/*  66 */     setPosition(d0, d1, d2);
/*  67 */     setMot(Vec3D.ORIGIN);
/*  68 */     this.lastX = d0;
/*  69 */     this.lastY = d1;
/*  70 */     this.lastZ = d2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getHeadHeight(EntityPose entitypose, EntitySize entitysize) {
/*  75 */     return entitysize.height;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  85 */     this.datawatcher.register(b, Integer.valueOf(0));
/*  86 */     this.datawatcher.register(c, Integer.valueOf(1));
/*  87 */     this.datawatcher.register(d, Float.valueOf(0.0F));
/*  88 */     this.datawatcher.register(e, Integer.valueOf(EnumBoatType.OAK.ordinal()));
/*  89 */     this.datawatcher.register(f, Boolean.valueOf(false));
/*  90 */     this.datawatcher.register(g, Boolean.valueOf(false));
/*  91 */     this.datawatcher.register(ag, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean j(Entity entity) {
/*  96 */     return a(this, entity);
/*     */   }
/*     */   
/*     */   public static boolean a(Entity entity, Entity entity1) {
/* 100 */     return ((entity1.aY() || entity1.isCollidable()) && !entity.isSameVehicle(entity1));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean aY() {
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3D a(EnumDirection.EnumAxis enumdirection_enumaxis, BlockUtil.Rectangle blockutil_rectangle) {
/* 115 */     return EntityLiving.h(super.a(enumdirection_enumaxis, blockutil_rectangle));
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/* 120 */     return -0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 125 */     if (isInvulnerable(damagesource))
/* 126 */       return false; 
/* 127 */     if (!this.world.isClientSide && !this.dead) {
/*     */       
/* 129 */       Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 130 */       CraftEntity craftEntity = (damagesource.getEntity() == null) ? null : damagesource.getEntity().getBukkitEntity();
/*     */       
/* 132 */       VehicleDamageEvent event = new VehicleDamageEvent(vehicle, (Entity)craftEntity, f);
/* 133 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 135 */       if (event.isCancelled()) {
/* 136 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 141 */       c(-o());
/* 142 */       b(10);
/* 143 */       setDamage(getDamage() + f * 10.0F);
/* 144 */       velocityChanged();
/* 145 */       boolean flag = (damagesource.getEntity() instanceof EntityHuman && ((EntityHuman)damagesource.getEntity()).abilities.canInstantlyBuild);
/*     */       
/* 147 */       if (flag || getDamage() > 40.0F) {
/*     */         
/* 149 */         VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, (Entity)craftEntity);
/* 150 */         this.world.getServer().getPluginManager().callEvent((Event)destroyEvent);
/*     */         
/* 152 */         if (destroyEvent.isCancelled()) {
/* 153 */           setDamage(40.0F);
/* 154 */           return true;
/*     */         } 
/*     */         
/* 157 */         if (!flag && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 158 */           a(g());
/*     */         }
/*     */         
/* 161 */         die();
/*     */       } 
/*     */       
/* 164 */       return true;
/*     */     } 
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void k(boolean flag) {
/* 172 */     if (!this.world.isClientSide) {
/* 173 */       this.aA = true;
/* 174 */       this.aB = flag;
/* 175 */       if (z() == 0) {
/* 176 */         d(60);
/*     */       }
/*     */     } 
/*     */     
/* 180 */     this.world.addParticle(Particles.SPLASH, locX() + this.random.nextFloat(), locY() + 0.7D, locZ() + this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
/* 181 */     if (this.random.nextInt(20) == 0) {
/* 182 */       this.world.a(locX(), locY(), locZ(), getSoundSplash(), getSoundCategory(), 1.0F, 0.8F + 0.4F * this.random.nextFloat(), false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void collide(Entity entity) {
/* 189 */     if (entity instanceof EntityBoat) {
/* 190 */       if ((entity.getBoundingBox()).minY < (getBoundingBox()).maxY) {
/*     */         
/* 192 */         if (!isSameVehicle(entity)) {
/* 193 */           VehicleEntityCollisionEvent event = new VehicleEntityCollisionEvent((Vehicle)getBukkitEntity(), (Entity)entity.getBukkitEntity());
/* 194 */           this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */           
/* 196 */           if (event.isCancelled()) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */         
/* 201 */         super.collide(entity);
/*     */       } 
/* 203 */     } else if ((entity.getBoundingBox()).minY <= (getBoundingBox()).minY) {
/*     */       
/* 205 */       if (!isSameVehicle(entity)) {
/* 206 */         VehicleEntityCollisionEvent event = new VehicleEntityCollisionEvent((Vehicle)getBukkitEntity(), (Entity)entity.getBukkitEntity());
/* 207 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 209 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 214 */       super.collide(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item g() {
/* 220 */     switch (getType())
/*     */     
/*     */     { default:
/* 223 */         return Items.OAK_BOAT;
/*     */       case UNDER_WATER:
/* 225 */         return Items.SPRUCE_BOAT;
/*     */       case UNDER_FLOWING_WATER:
/* 227 */         return Items.BIRCH_BOAT;
/*     */       case ON_LAND:
/* 229 */         return Items.JUNGLE_BOAT;
/*     */       case IN_AIR:
/* 231 */         return Items.ACACIA_BOAT;
/*     */       case null:
/* 233 */         break; }  return Items.DARK_OAK_BOAT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 239 */     return !this.dead;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection getAdjustedDirection() {
/* 244 */     return getDirection().g();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 250 */     this.ay = this.ax;
/* 251 */     this.ax = s();
/* 252 */     if (this.ax != EnumStatus.UNDER_WATER && this.ax != EnumStatus.UNDER_FLOWING_WATER) {
/* 253 */       this.aj = 0.0F;
/*     */     } else {
/* 255 */       this.aj++;
/*     */     } 
/*     */     
/* 258 */     if (!this.world.isClientSide && this.aj >= 60.0F) {
/* 259 */       ejectPassengers();
/*     */     }
/*     */     
/* 262 */     if (n() > 0) {
/* 263 */       b(n() - 1);
/*     */     }
/*     */     
/* 266 */     if (getDamage() > 0.0F) {
/* 267 */       setDamage(getDamage() - 1.0F);
/*     */     }
/*     */     
/* 270 */     super.tick();
/* 271 */     r();
/* 272 */     if (cr()) {
/* 273 */       if (getPassengers().isEmpty() || !(getPassengers().get(0) instanceof EntityHuman)) {
/* 274 */         a(false, false);
/*     */       }
/*     */       
/* 277 */       v();
/* 278 */       if (this.world.isClientSide) {
/* 279 */         x();
/* 280 */         this.world.a(new PacketPlayInBoatMove(a(0), a(1)));
/*     */       } 
/*     */       
/* 283 */       move(EnumMoveType.SELF, getMot());
/*     */     } else {
/* 285 */       setMot(Vec3D.ORIGIN);
/*     */     } 
/*     */ 
/*     */     
/* 289 */     CraftServer craftServer = this.world.getServer();
/* 290 */     CraftWorld craftWorld = this.world.getWorld();
/*     */     
/* 292 */     Location to = new Location((World)craftWorld, locX(), locY(), locZ(), this.yaw, this.pitch);
/* 293 */     Vehicle vehicle = (Vehicle)getBukkitEntity();
/*     */     
/* 295 */     craftServer.getPluginManager().callEvent((Event)new VehicleUpdateEvent(vehicle));
/*     */     
/* 297 */     if (this.lastLocation != null && !this.lastLocation.equals(to)) {
/* 298 */       VehicleMoveEvent event = new VehicleMoveEvent(vehicle, this.lastLocation, to);
/* 299 */       craftServer.getPluginManager().callEvent((Event)event);
/*     */     } 
/* 301 */     this.lastLocation = vehicle.getLocation();
/*     */ 
/*     */     
/* 304 */     q();
/*     */     
/* 306 */     for (int i = 0; i <= 1; i++) {
/* 307 */       if (a(i)) {
/* 308 */         if (!isSilent() && (this.ah[i] % 6.2831855F) <= 0.7853981852531433D && (this.ah[i] + 0.39269909262657166D) % 6.2831854820251465D >= 0.7853981852531433D) {
/* 309 */           SoundEffect soundeffect = h();
/*     */           
/* 311 */           if (soundeffect != null) {
/* 312 */             Vec3D vec3d = f(1.0F);
/* 313 */             double d0 = (i == 1) ? -vec3d.z : vec3d.z;
/* 314 */             double d1 = (i == 1) ? vec3d.x : -vec3d.x;
/*     */             
/* 316 */             this.world.playSound((EntityHuman)null, locX() + d0, locY(), locZ() + d1, soundeffect, getSoundCategory(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
/*     */           } 
/*     */         } 
/*     */         
/* 320 */         this.ah[i] = (float)(this.ah[i] + 0.39269909262657166D);
/*     */       } else {
/* 322 */         this.ah[i] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 326 */     checkBlockCollisions();
/* 327 */     List<Entity> list = this.world.getEntities(this, getBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), IEntitySelector.a(this));
/*     */     
/* 329 */     if (!list.isEmpty()) {
/* 330 */       boolean flag = (!this.world.isClientSide && !(getRidingPassenger() instanceof EntityHuman));
/*     */       
/* 332 */       for (int j = 0; j < list.size(); j++) {
/* 333 */         Entity entity = list.get(j);
/*     */         
/* 335 */         if (!entity.w(this)) {
/* 336 */           if (flag && getPassengers().size() < 2 && !entity.isPassenger() && entity.getWidth() < getWidth() && entity instanceof EntityLiving && !(entity instanceof EntityWaterAnimal) && !(entity instanceof EntityHuman)) {
/* 337 */             entity.startRiding(this);
/*     */           } else {
/* 339 */             collide(entity);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void q() {
/* 350 */     if (this.world.isClientSide) {
/* 351 */       int i = z();
/* 352 */       if (i > 0) {
/* 353 */         this.aC += 0.05F;
/*     */       } else {
/* 355 */         this.aC -= 0.1F;
/*     */       } 
/*     */       
/* 358 */       this.aC = MathHelper.a(this.aC, 0.0F, 1.0F);
/* 359 */       this.aE = this.aD;
/* 360 */       this.aD = 10.0F * (float)Math.sin((0.5F * (float)this.world.getTime())) * this.aC;
/*     */     } else {
/* 362 */       if (!this.aA) {
/* 363 */         d(0);
/*     */       }
/*     */       
/* 366 */       int i = z();
/* 367 */       if (i > 0) {
/* 368 */         i--;
/* 369 */         d(i);
/* 370 */         int j = 60 - i - 1;
/*     */         
/* 372 */         if (j > 0 && i == 0) {
/* 373 */           d(0);
/* 374 */           Vec3D vec3d = getMot();
/*     */           
/* 376 */           if (this.aB) {
/* 377 */             setMot(vec3d.add(0.0D, -0.7D, 0.0D));
/* 378 */             ejectPassengers();
/*     */           } else {
/* 380 */             setMot(vec3d.x, a((Class)EntityHuman.class) ? 2.7D : 0.6D, vec3d.z);
/*     */           } 
/*     */         } 
/*     */         
/* 384 */         this.aA = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect h() {
/* 392 */     switch (s()) {
/*     */       case IN_WATER:
/*     */       case UNDER_WATER:
/*     */       case UNDER_FLOWING_WATER:
/* 396 */         return SoundEffects.ENTITY_BOAT_PADDLE_WATER;
/*     */       case ON_LAND:
/* 398 */         return SoundEffects.ENTITY_BOAT_PADDLE_LAND;
/*     */     } 
/*     */     
/* 401 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void r() {
/* 406 */     if (cr()) {
/* 407 */       this.al = 0;
/* 408 */       c(locX(), locY(), locZ());
/*     */     } 
/*     */     
/* 411 */     if (this.al > 0) {
/* 412 */       double d0 = locX() + (this.am - locX()) / this.al;
/* 413 */       double d1 = locY() + (this.an - locY()) / this.al;
/* 414 */       double d2 = locZ() + (this.ao - locZ()) / this.al;
/* 415 */       double d3 = MathHelper.g(this.ap - this.yaw);
/*     */       
/* 417 */       this.yaw = (float)(this.yaw + d3 / this.al);
/* 418 */       this.pitch = (float)(this.pitch + (this.aq - this.pitch) / this.al);
/* 419 */       this.al--;
/* 420 */       setPosition(d0, d1, d2);
/* 421 */       setYawPitch(this.yaw, this.pitch);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(boolean flag, boolean flag1) {
/* 426 */     this.datawatcher.set(f, Boolean.valueOf(flag));
/* 427 */     this.datawatcher.set(g, Boolean.valueOf(flag1));
/*     */   }
/*     */   
/*     */   private EnumStatus s() {
/* 431 */     EnumStatus entityboat_enumstatus = u();
/*     */     
/* 433 */     if (entityboat_enumstatus != null) {
/* 434 */       this.av = (getBoundingBox()).maxY;
/* 435 */       return entityboat_enumstatus;
/* 436 */     }  if (t()) {
/* 437 */       return EnumStatus.IN_WATER;
/*     */     }
/* 439 */     float f = k();
/*     */     
/* 441 */     if (f > 0.0F) {
/* 442 */       this.aw = f;
/* 443 */       return EnumStatus.ON_LAND;
/*     */     } 
/* 445 */     return EnumStatus.IN_AIR;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float i() {
/* 451 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 452 */     int i = MathHelper.floor(axisalignedbb.minX);
/* 453 */     int j = MathHelper.f(axisalignedbb.maxX);
/* 454 */     int k = MathHelper.floor(axisalignedbb.maxY);
/* 455 */     int l = MathHelper.f(axisalignedbb.maxY - this.az);
/* 456 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/* 457 */     int j1 = MathHelper.f(axisalignedbb.maxZ);
/* 458 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 459 */     int k1 = k;
/*     */     
/* 461 */     label27: while (k1 < l) {
/* 462 */       float f = 0.0F;
/* 463 */       int l1 = i;
/*     */ 
/*     */ 
/*     */       
/* 467 */       label25: while (l1 < j) {
/* 468 */         int i2 = i1;
/*     */         
/*     */         while (true) {
/* 471 */           if (i2 >= j1) {
/* 472 */             l1++;
/*     */             
/*     */             continue label25;
/*     */           } 
/* 476 */           blockposition_mutableblockposition.d(l1, k1, i2);
/* 477 */           Fluid fluid = this.world.getFluid(blockposition_mutableblockposition);
/*     */           
/* 479 */           if (fluid.a(TagsFluid.WATER)) {
/* 480 */             f = Math.max(f, fluid.getHeight(this.world, blockposition_mutableblockposition));
/*     */           }
/*     */           
/* 483 */           if (f >= 1.0F) {
/*     */             break;
/*     */           }
/*     */           
/* 487 */           i2++;
/*     */         }  continue label27;
/* 489 */       }  if (f < 1.0F) {
/* 490 */         return blockposition_mutableblockposition.getY() + f;
/*     */       }
/*     */       
/* 493 */       k1++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 498 */     return (l + 1);
/*     */   }
/*     */   
/*     */   public float k() {
/* 502 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 503 */     AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY - 0.001D, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
/* 504 */     int i = MathHelper.floor(axisalignedbb1.minX) - 1;
/* 505 */     int j = MathHelper.f(axisalignedbb1.maxX) + 1;
/* 506 */     int k = MathHelper.floor(axisalignedbb1.minY) - 1;
/* 507 */     int l = MathHelper.f(axisalignedbb1.maxY) + 1;
/* 508 */     int i1 = MathHelper.floor(axisalignedbb1.minZ) - 1;
/* 509 */     int j1 = MathHelper.f(axisalignedbb1.maxZ) + 1;
/* 510 */     VoxelShape voxelshape = VoxelShapes.a(axisalignedbb1);
/* 511 */     float f = 0.0F;
/* 512 */     int k1 = 0;
/* 513 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/* 515 */     for (int l1 = i; l1 < j; l1++) {
/* 516 */       for (int i2 = i1; i2 < j1; i2++) {
/* 517 */         int j2 = ((l1 != i && l1 != j - 1) ? 0 : 1) + ((i2 != i1 && i2 != j1 - 1) ? 0 : 1);
/*     */         
/* 519 */         if (j2 != 2) {
/* 520 */           for (int k2 = k; k2 < l; k2++) {
/* 521 */             if (j2 <= 0 || (k2 != k && k2 != l - 1)) {
/* 522 */               blockposition_mutableblockposition.d(l1, k2, i2);
/* 523 */               IBlockData iblockdata = this.world.getType(blockposition_mutableblockposition);
/*     */               
/* 525 */               if (!(iblockdata.getBlock() instanceof BlockWaterLily) && VoxelShapes.c(iblockdata.getCollisionShape(this.world, blockposition_mutableblockposition).a(l1, k2, i2), voxelshape, OperatorBoolean.AND)) {
/* 526 */                 f += iblockdata.getBlock().getFrictionFactor();
/* 527 */                 k1++;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 535 */     return f / k1;
/*     */   }
/*     */   private boolean t() {
/*     */     int m;
/* 539 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 540 */     int i = MathHelper.floor(axisalignedbb.minX);
/* 541 */     int j = MathHelper.f(axisalignedbb.maxX);
/* 542 */     int k = MathHelper.floor(axisalignedbb.minY);
/* 543 */     int l = MathHelper.f(axisalignedbb.minY + 0.001D);
/* 544 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/* 545 */     int j1 = MathHelper.f(axisalignedbb.maxZ);
/* 546 */     boolean flag = false;
/*     */     
/* 548 */     this.av = Double.MIN_VALUE;
/* 549 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/* 551 */     for (int k1 = i; k1 < j; k1++) {
/* 552 */       for (int l1 = k; l1 < l; l1++) {
/* 553 */         for (int i2 = i1; i2 < j1; i2++) {
/* 554 */           blockposition_mutableblockposition.d(k1, l1, i2);
/* 555 */           Fluid fluid = this.world.getFluid(blockposition_mutableblockposition);
/*     */           
/* 557 */           if (fluid.a(TagsFluid.WATER)) {
/* 558 */             float f = l1 + fluid.getHeight(this.world, blockposition_mutableblockposition);
/*     */             
/* 560 */             this.av = Math.max(f, this.av);
/* 561 */             m = flag | ((axisalignedbb.minY < f) ? 1 : 0);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 567 */     return m;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EnumStatus u() {
/* 572 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 573 */     double d0 = axisalignedbb.maxY + 0.001D;
/* 574 */     int i = MathHelper.floor(axisalignedbb.minX);
/* 575 */     int j = MathHelper.f(axisalignedbb.maxX);
/* 576 */     int k = MathHelper.floor(axisalignedbb.maxY);
/* 577 */     int l = MathHelper.f(d0);
/* 578 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/* 579 */     int j1 = MathHelper.f(axisalignedbb.maxZ);
/* 580 */     boolean flag = false;
/* 581 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/* 583 */     for (int k1 = i; k1 < j; k1++) {
/* 584 */       for (int l1 = k; l1 < l; l1++) {
/* 585 */         for (int i2 = i1; i2 < j1; i2++) {
/* 586 */           blockposition_mutableblockposition.d(k1, l1, i2);
/* 587 */           Fluid fluid = this.world.getFluid(blockposition_mutableblockposition);
/*     */           
/* 589 */           if (fluid.a(TagsFluid.WATER) && d0 < (blockposition_mutableblockposition.getY() + fluid.getHeight(this.world, blockposition_mutableblockposition))) {
/* 590 */             if (!fluid.isSource()) {
/* 591 */               return EnumStatus.UNDER_FLOWING_WATER;
/*     */             }
/*     */             
/* 594 */             flag = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 600 */     return flag ? EnumStatus.UNDER_WATER : null;
/*     */   }
/*     */   
/*     */   private void v() {
/* 604 */     double d0 = -0.03999999910593033D;
/* 605 */     double d1 = isNoGravity() ? 0.0D : -0.03999999910593033D;
/* 606 */     double d2 = 0.0D;
/*     */     
/* 608 */     this.ai = 0.05F;
/* 609 */     if (this.ay == EnumStatus.IN_AIR && this.ax != EnumStatus.IN_AIR && this.ax != EnumStatus.ON_LAND) {
/* 610 */       this.av = e(1.0D);
/* 611 */       setPosition(locX(), (i() - getHeight()) + 0.101D, locZ());
/* 612 */       setMot(getMot().d(1.0D, 0.0D, 1.0D));
/* 613 */       this.az = 0.0D;
/* 614 */       this.ax = EnumStatus.IN_WATER;
/*     */     } else {
/* 616 */       if (this.ax == EnumStatus.IN_WATER) {
/* 617 */         d2 = (this.av - locY()) / getHeight();
/* 618 */         this.ai = 0.9F;
/* 619 */       } else if (this.ax == EnumStatus.UNDER_FLOWING_WATER) {
/* 620 */         d1 = -7.0E-4D;
/* 621 */         this.ai = 0.9F;
/* 622 */       } else if (this.ax == EnumStatus.UNDER_WATER) {
/* 623 */         d2 = 0.009999999776482582D;
/* 624 */         this.ai = 0.45F;
/* 625 */       } else if (this.ax == EnumStatus.IN_AIR) {
/* 626 */         this.ai = 0.9F;
/* 627 */       } else if (this.ax == EnumStatus.ON_LAND) {
/* 628 */         this.ai = this.aw;
/* 629 */         if (getRidingPassenger() instanceof EntityHuman) {
/* 630 */           this.aw /= 2.0F;
/*     */         }
/*     */       } 
/*     */       
/* 634 */       Vec3D vec3d = getMot();
/*     */       
/* 636 */       setMot(vec3d.x * this.ai, vec3d.y + d1, vec3d.z * this.ai);
/* 637 */       this.ak *= this.ai;
/* 638 */       if (d2 > 0.0D) {
/* 639 */         Vec3D vec3d1 = getMot();
/*     */         
/* 641 */         setMot(vec3d1.x, (vec3d1.y + d2 * 0.06153846016296973D) * 0.75D, vec3d1.z);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void x() {
/* 648 */     if (isVehicle()) {
/* 649 */       float f = 0.0F;
/*     */       
/* 651 */       if (this.ar) {
/* 652 */         this.ak--;
/*     */       }
/*     */       
/* 655 */       if (this.as) {
/* 656 */         this.ak++;
/*     */       }
/*     */       
/* 659 */       if (this.as != this.ar && !this.at && !this.au) {
/* 660 */         f += 0.005F;
/*     */       }
/*     */       
/* 663 */       this.yaw += this.ak;
/* 664 */       if (this.at) {
/* 665 */         f += 0.04F;
/*     */       }
/*     */       
/* 668 */       if (this.au) {
/* 669 */         f -= 0.005F;
/*     */       }
/*     */       
/* 672 */       setMot(getMot().add((MathHelper.sin(-this.yaw * 0.017453292F) * f), 0.0D, (MathHelper.cos(this.yaw * 0.017453292F) * f)));
/* 673 */       a(((this.as && !this.ar) || this.at), ((this.ar && !this.as) || this.at));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void k(Entity entity) {
/* 679 */     if (w(entity)) {
/* 680 */       float f = 0.0F;
/* 681 */       float f1 = (float)((this.dead ? 0.009999999776482582D : bb()) + entity.ba());
/*     */       
/* 683 */       if (getPassengers().size() > 1) {
/* 684 */         int i = getPassengers().indexOf(entity);
/*     */         
/* 686 */         if (i == 0) {
/* 687 */           f = 0.2F;
/*     */         } else {
/* 689 */           f = -0.6F;
/*     */         } 
/*     */         
/* 692 */         if (entity instanceof EntityAnimal) {
/* 693 */           f = (float)(f + 0.2D);
/*     */         }
/*     */       } 
/*     */       
/* 697 */       Vec3D vec3d = (new Vec3D(f, 0.0D, 0.0D)).b(-this.yaw * 0.017453292F - 1.5707964F);
/*     */       
/* 699 */       entity.setPosition(locX() + vec3d.x, locY() + f1, locZ() + vec3d.z);
/* 700 */       entity.yaw += this.ak;
/* 701 */       entity.setHeadRotation(entity.getHeadRotation() + this.ak);
/* 702 */       a(entity);
/* 703 */       if (entity instanceof EntityAnimal && getPassengers().size() > 1) {
/* 704 */         int j = (entity.getId() % 2 == 0) ? 90 : 270;
/*     */         
/* 706 */         entity.n(((EntityAnimal)entity).aA + j);
/* 707 */         entity.setHeadRotation(entity.getHeadRotation() + j);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3D b(EntityLiving entityliving) {
/* 715 */     Vec3D vec3d = a((getWidth() * MathHelper.a), entityliving.getWidth(), this.yaw);
/* 716 */     double d0 = locX() + vec3d.x;
/* 717 */     double d1 = locZ() + vec3d.z;
/* 718 */     BlockPosition blockposition = new BlockPosition(d0, (getBoundingBox()).maxY, d1);
/* 719 */     BlockPosition blockposition1 = blockposition.down();
/*     */     
/* 721 */     if (!this.world.A(blockposition1)) {
/* 722 */       double d2 = blockposition.getY() + this.world.h(blockposition);
/* 723 */       double d3 = blockposition.getY() + this.world.h(blockposition1);
/* 724 */       UnmodifiableIterator unmodifiableiterator = entityliving.ei().iterator();
/*     */       
/* 726 */       while (unmodifiableiterator.hasNext()) {
/* 727 */         EntityPose entitypose = (EntityPose)unmodifiableiterator.next();
/* 728 */         Vec3D vec3d1 = DismountUtil.a(this.world, d0, d2, d1, entityliving, entitypose);
/*     */         
/* 730 */         if (vec3d1 != null) {
/* 731 */           entityliving.setPose(entitypose);
/* 732 */           return vec3d1;
/*     */         } 
/*     */         
/* 735 */         Vec3D vec3d2 = DismountUtil.a(this.world, d0, d3, d1, entityliving, entitypose);
/*     */         
/* 737 */         if (vec3d2 != null) {
/* 738 */           entityliving.setPose(entitypose);
/* 739 */           return vec3d2;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 744 */     return super.b(entityliving);
/*     */   }
/*     */   
/*     */   protected void a(Entity entity) {
/* 748 */     entity.n(this.yaw);
/* 749 */     float f = MathHelper.g(entity.yaw - this.yaw);
/* 750 */     float f1 = MathHelper.a(f, -105.0F, 105.0F);
/*     */     
/* 752 */     entity.lastYaw += f1 - f;
/* 753 */     entity.yaw += f1 - f;
/* 754 */     entity.setHeadRotation(entity.yaw);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/* 759 */     nbttagcompound.setString("Type", getType().a());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/* 764 */     if (nbttagcompound.hasKeyOfType("Type", 8)) {
/* 765 */       setType(EnumBoatType.a(nbttagcompound.getString("Type")));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, EnumHand enumhand) {
/* 772 */     return entityhuman.ep() ? EnumInteractionResult.PASS : ((this.aj < 60.0F) ? (!this.world.isClientSide ? (entityhuman.startRiding(this) ? EnumInteractionResult.CONSUME : EnumInteractionResult.PASS) : EnumInteractionResult.SUCCESS) : EnumInteractionResult.PASS);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {
/* 777 */     this.az = (getMot()).y;
/* 778 */     if (!isPassenger()) {
/* 779 */       if (flag) {
/* 780 */         if (this.fallDistance > 3.0F) {
/* 781 */           if (this.ax != EnumStatus.ON_LAND) {
/* 782 */             this.fallDistance = 0.0F;
/*     */             
/*     */             return;
/*     */           } 
/* 786 */           b(this.fallDistance, 1.0F);
/* 787 */           if (!this.world.isClientSide && !this.dead) {
/*     */             
/* 789 */             Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 790 */             VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, null);
/* 791 */             this.world.getServer().getPluginManager().callEvent((Event)destroyEvent);
/* 792 */             if (!destroyEvent.isCancelled()) {
/* 793 */               die();
/* 794 */               if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/*     */                 int i;
/*     */                 
/* 797 */                 for (i = 0; i < 3; i++) {
/* 798 */                   a(getType().b());
/*     */                 }
/*     */                 
/* 801 */                 for (i = 0; i < 2; i++) {
/* 802 */                   a(Items.STICK);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 809 */         this.fallDistance = 0.0F;
/* 810 */       } else if (!this.world.getFluid(getChunkCoordinates().down()).a(TagsFluid.WATER) && d0 < 0.0D) {
/* 811 */         this.fallDistance = (float)(this.fallDistance - d0);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(int i) {
/* 818 */     return (((Boolean)this.datawatcher.<Boolean>get((i == 0) ? f : g)).booleanValue() && getRidingPassenger() != null);
/*     */   }
/*     */   
/*     */   public void setDamage(float f) {
/* 822 */     this.datawatcher.set(d, Float.valueOf(f));
/*     */   }
/*     */   
/*     */   public float getDamage() {
/* 826 */     return ((Float)this.datawatcher.<Float>get(d)).floatValue();
/*     */   }
/*     */   
/*     */   public void b(int i) {
/* 830 */     this.datawatcher.set(b, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int n() {
/* 834 */     return ((Integer)this.datawatcher.<Integer>get(b)).intValue();
/*     */   }
/*     */   
/*     */   private void d(int i) {
/* 838 */     this.datawatcher.set(ag, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   private int z() {
/* 842 */     return ((Integer)this.datawatcher.<Integer>get(ag)).intValue();
/*     */   }
/*     */   
/*     */   public void c(int i) {
/* 846 */     this.datawatcher.set(c, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int o() {
/* 850 */     return ((Integer)this.datawatcher.<Integer>get(c)).intValue();
/*     */   }
/*     */   
/*     */   public void setType(EnumBoatType entityboat_enumboattype) {
/* 854 */     this.datawatcher.set(e, Integer.valueOf(entityboat_enumboattype.ordinal()));
/*     */   }
/*     */   
/*     */   public EnumBoatType getType() {
/* 858 */     return EnumBoatType.a(((Integer)this.datawatcher.<Integer>get(e)).intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean q(Entity entity) {
/* 863 */     return (getPassengers().size() < 2 && !a(TagsFluid.WATER));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getRidingPassenger() {
/* 869 */     List<Entity> list = getPassengers();
/*     */     
/* 871 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 876 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean aH() {
/* 881 */     return (this.ax == EnumStatus.UNDER_WATER || this.ax == EnumStatus.UNDER_FLOWING_WATER);
/*     */   }
/*     */   
/*     */   public enum EnumBoatType
/*     */   {
/* 886 */     OAK((String)Blocks.OAK_PLANKS, "oak"), SPRUCE((String)Blocks.SPRUCE_PLANKS, "spruce"), BIRCH((String)Blocks.BIRCH_PLANKS, "birch"), JUNGLE((String)Blocks.JUNGLE_PLANKS, "jungle"), ACACIA((String)Blocks.ACACIA_PLANKS, "acacia"), DARK_OAK((String)Blocks.DARK_OAK_PLANKS, "dark_oak");
/*     */     
/*     */     private final Block h;
/*     */     private final String g;
/*     */     
/*     */     EnumBoatType(Block block, String s) {
/* 892 */       this.g = s;
/* 893 */       this.h = block;
/*     */     }
/*     */     
/*     */     public String a() {
/* 897 */       return this.g;
/*     */     }
/*     */     
/*     */     public Block b() {
/* 901 */       return this.h;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 905 */       return this.g;
/*     */     }
/*     */     
/*     */     public static EnumBoatType a(int i) {
/* 909 */       EnumBoatType[] aentityboat_enumboattype = values();
/*     */       
/* 911 */       if (i < 0 || i >= aentityboat_enumboattype.length) {
/* 912 */         i = 0;
/*     */       }
/*     */       
/* 915 */       return aentityboat_enumboattype[i];
/*     */     }
/*     */     
/*     */     public static EnumBoatType a(String s) {
/* 919 */       EnumBoatType[] aentityboat_enumboattype = values();
/*     */       
/* 921 */       for (int i = 0; i < aentityboat_enumboattype.length; i++) {
/* 922 */         if (aentityboat_enumboattype[i].a().equals(s)) {
/* 923 */           return aentityboat_enumboattype[i];
/*     */         }
/*     */       } 
/*     */       
/* 927 */       return aentityboat_enumboattype[0];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumStatus
/*     */   {
/* 933 */     IN_WATER, UNDER_WATER, UNDER_FLOWING_WATER, ON_LAND, IN_AIR;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */