/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ 
/*     */ public class EntityDolphin extends EntityWaterAnimal {
/*  13 */   private static final DataWatcherObject<BlockPosition> c = DataWatcher.a((Class)EntityDolphin.class, DataWatcherRegistry.l);
/*  14 */   private static final DataWatcherObject<Boolean> d = DataWatcher.a((Class)EntityDolphin.class, DataWatcherRegistry.i);
/*  15 */   private static final DataWatcherObject<Integer> bo = DataWatcher.a((Class)EntityDolphin.class, DataWatcherRegistry.b);
/*  16 */   private static final PathfinderTargetCondition bp = (new PathfinderTargetCondition()).a(10.0D).b().a().c(); static {
/*  17 */     b = (entityitem -> 
/*  18 */       (!entityitem.p() && entityitem.isAlive() && entityitem.isInWater()));
/*     */   }
/*     */   public static final Predicate<EntityItem> b;
/*     */   public EntityDolphin(EntityTypes<? extends EntityDolphin> entitytypes, World world) {
/*  22 */     super((EntityTypes)entitytypes, world);
/*  23 */     this.moveController = new a(this);
/*  24 */     this.lookController = new ControllerLookDolphin(this, 10);
/*  25 */     setCanPickupLoot(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  31 */     setAirTicks(bG());
/*  32 */     this.pitch = 0.0F;
/*  33 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cL() {
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(int i) {}
/*     */   
/*     */   public void setTreasurePos(BlockPosition blockposition) {
/*  45 */     this.datawatcher.set(c, blockposition);
/*     */   }
/*     */   
/*     */   public BlockPosition getTreasurePos() {
/*  49 */     return this.datawatcher.<BlockPosition>get(c);
/*     */   }
/*     */   
/*     */   public boolean gotFish() {
/*  53 */     return ((Boolean)this.datawatcher.<Boolean>get(d)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setGotFish(boolean flag) {
/*  57 */     this.datawatcher.set(d, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public int getMoistness() {
/*  61 */     return ((Integer)this.datawatcher.<Integer>get(bo)).intValue();
/*     */   }
/*     */   
/*     */   public void setMoistness(int i) {
/*  65 */     this.datawatcher.set(bo, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  70 */     super.initDatawatcher();
/*  71 */     this.datawatcher.register(c, BlockPosition.ZERO);
/*  72 */     this.datawatcher.register(d, Boolean.valueOf(false));
/*  73 */     this.datawatcher.register(bo, Integer.valueOf(2400));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  78 */     super.saveData(nbttagcompound);
/*  79 */     nbttagcompound.setInt("TreasurePosX", getTreasurePos().getX());
/*  80 */     nbttagcompound.setInt("TreasurePosY", getTreasurePos().getY());
/*  81 */     nbttagcompound.setInt("TreasurePosZ", getTreasurePos().getZ());
/*  82 */     nbttagcompound.setBoolean("GotFish", gotFish());
/*  83 */     nbttagcompound.setInt("Moistness", getMoistness());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  88 */     int i = nbttagcompound.getInt("TreasurePosX");
/*  89 */     int j = nbttagcompound.getInt("TreasurePosY");
/*  90 */     int k = nbttagcompound.getInt("TreasurePosZ");
/*     */     
/*  92 */     setTreasurePos(new BlockPosition(i, j, k));
/*  93 */     super.loadData(nbttagcompound);
/*  94 */     setGotFish(nbttagcompound.getBoolean("GotFish"));
/*  95 */     setMoistness(nbttagcompound.getInt("Moistness"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/* 100 */     this.goalSelector.a(0, new PathfinderGoalBreath(this));
/* 101 */     this.goalSelector.a(0, new PathfinderGoalWater(this));
/* 102 */     this.goalSelector.a(1, new b(this));
/* 103 */     this.goalSelector.a(2, new c(this, 4.0D));
/* 104 */     this.goalSelector.a(4, new PathfinderGoalRandomSwim(this, 1.0D, 10));
/* 105 */     this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
/* 106 */     this.goalSelector.a(5, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/* 107 */     this.goalSelector.a(5, new PathfinderGoalWaterJump(this, 10));
/* 108 */     this.goalSelector.a(6, new PathfinderGoalMeleeAttack(this, 1.2000000476837158D, true));
/* 109 */     this.goalSelector.a(8, new d());
/* 110 */     this.goalSelector.a(8, new PathfinderGoalFollowBoat(this));
/* 111 */     this.goalSelector.a(9, new PathfinderGoalAvoidTarget<>(this, EntityGuardian.class, 8.0F, 1.0D, 1.0D));
/* 112 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[] { EntityGuardian.class })).a(new Class[0]));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eM() {
/* 116 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 10.0D).a(GenericAttributes.MOVEMENT_SPEED, 1.2000000476837158D).a(GenericAttributes.ATTACK_DAMAGE, 3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World world) {
/* 121 */     return new NavigationGuardian(this, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 126 */     boolean flag = entity.damageEntity(DamageSource.mobAttack(this), (int)b(GenericAttributes.ATTACK_DAMAGE));
/*     */     
/* 128 */     if (flag) {
/* 129 */       a(this, entity);
/* 130 */       playSound(SoundEffects.ENTITY_DOLPHIN_ATTACK, 1.0F, 1.0F);
/*     */     } 
/*     */     
/* 133 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int bG() {
/* 138 */     return 4800;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int m(int i) {
/* 143 */     return bG();
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 148 */     return 0.3F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int O() {
/* 153 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int eo() {
/* 158 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean n(Entity entity) {
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 168 */     EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/*     */     
/* 170 */     return !getEquipment(enumitemslot).isEmpty() ? false : ((enumitemslot == EnumItemSlot.MAINHAND && super.e(itemstack)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(EntityItem entityitem) {
/* 175 */     if (getEquipment(EnumItemSlot.MAINHAND).isEmpty()) {
/* 176 */       ItemStack itemstack = entityitem.getItemStack();
/*     */       
/* 178 */       if (canPickup(itemstack)) {
/*     */         
/* 180 */         if (CraftEventFactory.callEntityPickupItemEvent(this, entityitem, 0, false).isCancelled()) {
/*     */           return;
/*     */         }
/* 183 */         itemstack = entityitem.getItemStack();
/*     */         
/* 185 */         a(entityitem);
/* 186 */         setSlot(EnumItemSlot.MAINHAND, itemstack);
/* 187 */         this.dropChanceHand[EnumItemSlot.MAINHAND.b()] = 2.0F;
/* 188 */         receive(entityitem, itemstack.getCount());
/* 189 */         entityitem.die();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 197 */     super.tick();
/* 198 */     if (isNoAI()) {
/* 199 */       setAirTicks(bG());
/*     */     } else {
/* 201 */       if (aF()) {
/* 202 */         setMoistness(2400);
/*     */       } else {
/* 204 */         setMoistness(getMoistness() - 1);
/* 205 */         if (getMoistness() <= 0) {
/* 206 */           damageEntity(DamageSource.DRYOUT, 1.0F);
/*     */         }
/*     */         
/* 209 */         if (this.onGround) {
/* 210 */           setMot(getMot().add(((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
/* 211 */           this.yaw = this.random.nextFloat() * 360.0F;
/* 212 */           this.onGround = false;
/* 213 */           this.impulse = true;
/*     */         } 
/*     */       } 
/*     */       
/* 217 */       if (this.world.isClientSide && isInWater() && getMot().g() > 0.03D) {
/* 218 */         Vec3D vec3d = f(0.0F);
/* 219 */         float f = MathHelper.cos(this.yaw * 0.017453292F) * 0.3F;
/* 220 */         float f1 = MathHelper.sin(this.yaw * 0.017453292F) * 0.3F;
/* 221 */         float f2 = 1.2F - this.random.nextFloat() * 0.7F;
/*     */         
/* 223 */         for (int i = 0; i < 2; i++) {
/* 224 */           this.world.addParticle(Particles.DOLPHIN, locX() - vec3d.x * f2 + f, locY() - vec3d.y, locZ() - vec3d.z * f2 + f1, 0.0D, 0.0D, 0.0D);
/* 225 */           this.world.addParticle(Particles.DOLPHIN, locX() - vec3d.x * f2 - f, locY() - vec3d.y, locZ() - vec3d.z * f2 - f1, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 234 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 236 */     if (!itemstack.isEmpty() && itemstack.getItem().a(TagsItem.FISHES)) {
/* 237 */       if (!this.world.isClientSide) {
/* 238 */         playSound(SoundEffects.ENTITY_DOLPHIN_EAT, 1.0F, 1.0F);
/*     */       }
/*     */       
/* 241 */       setGotFish(true);
/* 242 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 243 */         itemstack.subtract(1);
/*     */       }
/*     */       
/* 246 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/* 248 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(EntityTypes<EntityDolphin> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 253 */     if (blockposition.getY() > 45 && blockposition.getY() < generatoraccess.getSeaLevel()) {
/* 254 */       Optional<ResourceKey<BiomeBase>> optional = generatoraccess.i(blockposition);
/*     */       
/* 256 */       return ((!Objects.equals(optional, Optional.of(Biomes.OCEAN)) || !Objects.equals(optional, Optional.of(Biomes.DEEP_OCEAN))) && generatoraccess.getFluid(blockposition).a(TagsFluid.WATER));
/*     */     } 
/* 258 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 264 */     return SoundEffects.ENTITY_DOLPHIN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundDeath() {
/* 270 */     return SoundEffects.ENTITY_DOLPHIN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAmbient() {
/* 276 */     return isInWater() ? SoundEffects.ENTITY_DOLPHIN_AMBIENT_WATER : SoundEffects.ENTITY_DOLPHIN_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSplash() {
/* 281 */     return SoundEffects.ENTITY_DOLPHIN_SPLASH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSwim() {
/* 286 */     return SoundEffects.ENTITY_DOLPHIN_SWIM;
/*     */   }
/*     */   
/*     */   protected boolean eN() {
/* 290 */     BlockPosition blockposition = getNavigation().h();
/*     */     
/* 292 */     return (blockposition != null) ? blockposition.a(getPositionVector(), 12.0D) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(Vec3D vec3d) {
/* 297 */     if (doAITick() && isInWater()) {
/* 298 */       a(dM(), vec3d);
/* 299 */       move(EnumMoveType.SELF, getMot());
/* 300 */       setMot(getMot().a(0.9D));
/* 301 */       if (getGoalTarget() == null) {
/* 302 */         setMot(getMot().add(0.0D, -0.005D, 0.0D));
/*     */       }
/*     */     } else {
/* 305 */       super.g(vec3d);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 312 */     return true;
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends PathfinderGoal {
/*     */     private final EntityDolphin a;
/*     */     private boolean b;
/*     */     
/*     */     b(EntityDolphin entitydolphin) {
/* 321 */       this.a = entitydolphin;
/* 322 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean C_() {
/* 327 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 332 */       return (this.a.gotFish() && this.a.getAirTicks() >= 100 && this.a.world.getWorld().canGenerateStructures());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 337 */       BlockPosition blockposition = this.a.getTreasurePos(); return (
/*     */         
/* 339 */         !(new BlockPosition(blockposition.getX(), this.a.locY(), blockposition.getZ())).a(this.a.getPositionVector(), 4.0D) && !this.b && this.a.getAirTicks() >= 100);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 344 */       if (this.a.world instanceof WorldServer) {
/* 345 */         WorldServer worldserver = (WorldServer)this.a.world;
/*     */         
/* 347 */         this.b = false;
/* 348 */         this.a.getNavigation().o();
/* 349 */         BlockPosition blockposition = this.a.getChunkCoordinates();
/* 350 */         StructureGenerator<?> structuregenerator = (worldserver.random.nextFloat() >= 0.5D) ? StructureGenerator.OCEAN_RUIN : StructureGenerator.SHIPWRECK;
/* 351 */         BlockPosition blockposition1 = worldserver.a(structuregenerator, blockposition, 50, false);
/*     */         
/* 353 */         if (blockposition1 == null) {
/* 354 */           StructureGenerator<?> structuregenerator1 = structuregenerator.equals(StructureGenerator.OCEAN_RUIN) ? StructureGenerator.SHIPWRECK : StructureGenerator.OCEAN_RUIN;
/* 355 */           BlockPosition blockposition2 = worldserver.a(structuregenerator1, blockposition, 50, false);
/*     */           
/* 357 */           if (blockposition2 == null) {
/* 358 */             this.b = true;
/*     */             
/*     */             return;
/*     */           } 
/* 362 */           this.a.setTreasurePos(blockposition2);
/*     */         } else {
/* 364 */           this.a.setTreasurePos(blockposition1);
/*     */         } 
/*     */         
/* 367 */         worldserver.broadcastEntityEffect(this.a, (byte)38);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 373 */       BlockPosition blockposition = this.a.getTreasurePos();
/*     */       
/* 375 */       if ((new BlockPosition(blockposition.getX(), this.a.locY(), blockposition.getZ())).a(this.a.getPositionVector(), 4.0D) || this.b) {
/* 376 */         this.a.setGotFish(false);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 383 */       World world = this.a.world;
/*     */       
/* 385 */       if (this.a.eN() || this.a.getNavigation().m()) {
/* 386 */         Vec3D vec3d = Vec3D.a(this.a.getTreasurePos());
/* 387 */         Vec3D vec3d1 = RandomPositionGenerator.a(this.a, 16, 1, vec3d, 0.39269909262657166D);
/*     */         
/* 389 */         if (vec3d1 == null) {
/* 390 */           vec3d1 = RandomPositionGenerator.b(this.a, 8, 4, vec3d);
/*     */         }
/*     */         
/* 393 */         if (vec3d1 != null) {
/* 394 */           BlockPosition blockposition = new BlockPosition(vec3d1);
/*     */           
/* 396 */           if (!world.getFluid(blockposition).a(TagsFluid.WATER) || !world.getType(blockposition).a(world, blockposition, PathMode.WATER)) {
/* 397 */             vec3d1 = RandomPositionGenerator.b(this.a, 8, 5, vec3d);
/*     */           }
/*     */         } 
/*     */         
/* 401 */         if (vec3d1 == null) {
/* 402 */           this.b = true;
/*     */           
/*     */           return;
/*     */         } 
/* 406 */         this.a.getControllerLook().a(vec3d1.x, vec3d1.y, vec3d1.z, (this.a.eo() + 20), this.a.O());
/* 407 */         this.a.getNavigation().a(vec3d1.x, vec3d1.y, vec3d1.z, 1.3D);
/* 408 */         if (world.random.nextInt(80) == 0) {
/* 409 */           world.broadcastEntityEffect(this.a, (byte)38);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityDolphin a;
/*     */     private final double b;
/*     */     private EntityHuman c;
/*     */     
/*     */     c(EntityDolphin entitydolphin, double d0) {
/* 423 */       this.a = entitydolphin;
/* 424 */       this.b = d0;
/* 425 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 430 */       this.c = this.a.world.a(EntityDolphin.bp, this.a);
/* 431 */       return (this.c == null) ? false : ((this.c.isSwimming() && this.a.getGoalTarget() != this.c));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 436 */       return (this.c != null && this.c.isSwimming() && this.a.h(this.c) < 256.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 441 */       this.c.addEffect(new MobEffect(MobEffects.DOLPHINS_GRACE, 100), EntityPotionEffectEvent.Cause.DOLPHIN);
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 446 */       this.c = null;
/* 447 */       this.a.getNavigation().o();
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 452 */       this.a.getControllerLook().a(this.c, (this.a.eo() + 20), this.a.O());
/* 453 */       if (this.a.h(this.c) < 6.25D) {
/* 454 */         this.a.getNavigation().o();
/*     */       } else {
/* 456 */         this.a.getNavigation().a(this.c, this.b);
/*     */       } 
/*     */       
/* 459 */       if (this.c.isSwimming() && this.c.world.random.nextInt(6) == 0) {
/* 460 */         this.c.addEffect(new MobEffect(MobEffects.DOLPHINS_GRACE, 100), EntityPotionEffectEvent.Cause.DOLPHIN);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class d
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private int b;
/*     */     
/*     */     private d() {}
/*     */     
/*     */     public boolean a() {
/* 474 */       if (this.b > EntityDolphin.this.ticksLived) {
/* 475 */         return false;
/*     */       }
/* 477 */       List<EntityItem> list = EntityDolphin.this.world.a(EntityItem.class, EntityDolphin.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityDolphin.b);
/*     */       
/* 479 */       return (!list.isEmpty() || !EntityDolphin.this.getEquipment(EnumItemSlot.MAINHAND).isEmpty());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void c() {
/* 485 */       List<EntityItem> list = EntityDolphin.this.world.a(EntityItem.class, EntityDolphin.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityDolphin.b);
/*     */       
/* 487 */       if (!list.isEmpty()) {
/* 488 */         EntityDolphin.this.getNavigation().a(list.get(0), 1.2000000476837158D);
/* 489 */         EntityDolphin.this.playSound(SoundEffects.ENTITY_DOLPHIN_PLAY, 1.0F, 1.0F);
/*     */       } 
/*     */       
/* 492 */       this.b = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 497 */       ItemStack itemstack = EntityDolphin.this.getEquipment(EnumItemSlot.MAINHAND);
/*     */       
/* 499 */       if (!itemstack.isEmpty()) {
/* 500 */         a(itemstack);
/* 501 */         EntityDolphin.this.setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/* 502 */         this.b = EntityDolphin.this.ticksLived + EntityDolphin.this.random.nextInt(100);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 509 */       List<EntityItem> list = EntityDolphin.this.world.a(EntityItem.class, EntityDolphin.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityDolphin.b);
/* 510 */       ItemStack itemstack = EntityDolphin.this.getEquipment(EnumItemSlot.MAINHAND);
/*     */       
/* 512 */       if (!itemstack.isEmpty()) {
/* 513 */         a(itemstack);
/* 514 */         EntityDolphin.this.setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/* 515 */       } else if (!list.isEmpty()) {
/* 516 */         EntityDolphin.this.getNavigation().a(list.get(0), 1.2000000476837158D);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void a(ItemStack itemstack) {
/* 522 */       if (!itemstack.isEmpty()) {
/* 523 */         double d0 = EntityDolphin.this.getHeadY() - 0.30000001192092896D;
/* 524 */         EntityItem entityitem = new EntityItem(EntityDolphin.this.world, EntityDolphin.this.locX(), d0, EntityDolphin.this.locZ(), itemstack);
/*     */         
/* 526 */         entityitem.setPickupDelay(40);
/* 527 */         entityitem.setThrower(EntityDolphin.this.getUniqueID());
/* 528 */         float f = 0.3F;
/* 529 */         float f1 = EntityDolphin.this.random.nextFloat() * 6.2831855F;
/* 530 */         float f2 = 0.02F * EntityDolphin.this.random.nextFloat();
/*     */         
/* 532 */         entityitem.setMot((0.3F * -MathHelper.sin(EntityDolphin.this.yaw * 0.017453292F) * MathHelper.cos(EntityDolphin.this.pitch * 0.017453292F) + MathHelper.cos(f1) * f2), (0.3F * MathHelper.sin(EntityDolphin.this.pitch * 0.017453292F) * 1.5F), (0.3F * MathHelper.cos(EntityDolphin.this.yaw * 0.017453292F) * MathHelper.cos(EntityDolphin.this.pitch * 0.017453292F) + MathHelper.sin(f1) * f2));
/* 533 */         EntityDolphin.this.world.addEntity(entityitem);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends ControllerMove {
/*     */     private final EntityDolphin i;
/*     */     
/*     */     public a(EntityDolphin entitydolphin) {
/* 543 */       super(entitydolphin);
/* 544 */       this.i = entitydolphin;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 549 */       if (this.i.isInWater()) {
/* 550 */         this.i.setMot(this.i.getMot().add(0.0D, 0.005D, 0.0D));
/*     */       }
/*     */       
/* 553 */       if (this.h == ControllerMove.Operation.MOVE_TO && !this.i.getNavigation().m()) {
/* 554 */         double d0 = this.b - this.i.locX();
/* 555 */         double d1 = this.c - this.i.locY();
/* 556 */         double d2 = this.d - this.i.locZ();
/* 557 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */         
/* 559 */         if (d3 < 2.500000277905201E-7D) {
/* 560 */           this.a.t(0.0F);
/*     */         } else {
/* 562 */           float f = (float)(MathHelper.d(d2, d0) * 57.2957763671875D) - 90.0F;
/*     */           
/* 564 */           this.i.yaw = a(this.i.yaw, f, 10.0F);
/* 565 */           this.i.aA = this.i.yaw;
/* 566 */           this.i.aC = this.i.yaw;
/* 567 */           float f1 = (float)(this.e * this.i.b(GenericAttributes.MOVEMENT_SPEED));
/*     */           
/* 569 */           if (this.i.isInWater()) {
/* 570 */             this.i.q(f1 * 0.02F);
/* 571 */             float f2 = -((float)(MathHelper.d(d1, MathHelper.sqrt(d0 * d0 + d2 * d2)) * 57.2957763671875D));
/*     */             
/* 573 */             f2 = MathHelper.a(MathHelper.g(f2), -85.0F, 85.0F);
/* 574 */             this.i.pitch = a(this.i.pitch, f2, 5.0F);
/* 575 */             float f3 = MathHelper.cos(this.i.pitch * 0.017453292F);
/* 576 */             float f4 = MathHelper.sin(this.i.pitch * 0.017453292F);
/*     */             
/* 578 */             this.i.aT = f3 * f1;
/* 579 */             this.i.aS = -f4 * f1;
/*     */           } else {
/* 581 */             this.i.q(f1 * 0.1F);
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 586 */         this.i.q(0.0F);
/* 587 */         this.i.v(0.0F);
/* 588 */         this.i.u(0.0F);
/* 589 */         this.i.t(0.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */