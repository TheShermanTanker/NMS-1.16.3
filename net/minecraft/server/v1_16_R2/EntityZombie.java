/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoField;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Zombie;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ import org.bukkit.event.entity.EntityTransformEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityZombie
/*     */   extends EntityMonster
/*     */ {
/*  23 */   private static final UUID b = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836"); private final AttributeModifier c;
/*  24 */   private final AttributeModifier babyModifier = this.c = new AttributeModifier(b, "Baby speed boost", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);
/*  25 */   private static final DataWatcherObject<Boolean> d = DataWatcher.a((Class)EntityZombie.class, DataWatcherRegistry.i);
/*  26 */   private static final DataWatcherObject<Integer> bo = DataWatcher.a((Class)EntityZombie.class, DataWatcherRegistry.b);
/*  27 */   public static final DataWatcherObject<Boolean> DROWN_CONVERTING = DataWatcher.a((Class)EntityZombie.class, DataWatcherRegistry.i); static {
/*  28 */     bq = (enumdifficulty -> (enumdifficulty == EnumDifficulty.HARD));
/*     */   }
/*     */   private static final Predicate<EnumDifficulty> bq;
/*     */   private final PathfinderGoalBreakDoor br;
/*     */   private boolean bs;
/*     */   private int bt;
/*     */   public int drownedConversionTime;
/*  35 */   private int lastTick = MinecraftServer.currentTick;
/*     */   private boolean shouldBurnInDay = true;
/*     */   
/*     */   public EntityZombie(EntityTypes<? extends EntityZombie> entitytypes, World world) {
/*  39 */     super((EntityTypes)entitytypes, world);
/*  40 */     this.br = new PathfinderGoalBreakDoor(this, bq);
/*     */   }
/*     */   
/*     */   public EntityZombie(World world) {
/*  44 */     this(EntityTypes.ZOMBIE, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  49 */     if (this.world.paperConfig.zombiesTargetTurtleEggs) this.goalSelector.a(4, new a(this, 1.0D, 3)); 
/*  50 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  51 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  52 */     m();
/*     */   }
/*     */   
/*     */   protected void m() {
/*  56 */     this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
/*  57 */     this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, true, 4, this::eU));
/*  58 */     this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  59 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[] { EntityPigZombie.class }));
/*  60 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  61 */     if (this.world.spigotConfig.zombieAggressiveTowardsVillager) this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillagerAbstract.class, false)); 
/*  62 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*  63 */     this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget<>(this, EntityTurtle.class, 10, true, false, EntityTurtle.bo));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eS() {
/*  67 */     return EntityMonster.eR().a(GenericAttributes.FOLLOW_RANGE, 35.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.23000000417232513D).a(GenericAttributes.ATTACK_DAMAGE, 3.0D).a(GenericAttributes.ARMOR, 2.0D).a(GenericAttributes.SPAWN_REINFORCEMENTS);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  72 */     super.initDatawatcher();
/*  73 */     getDataWatcher().register(d, Boolean.valueOf(false));
/*  74 */     getDataWatcher().register(bo, Integer.valueOf(0));
/*  75 */     getDataWatcher().register(DROWN_CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean isDrownConverting() {
/*  79 */     return ((Boolean)getDataWatcher().<Boolean>get(DROWN_CONVERTING)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean eU() {
/*  83 */     return this.bs;
/*     */   }
/*     */   
/*     */   public void u(boolean flag) {
/*  87 */     if (eK() && PathfinderGoalUtil.a(this)) {
/*  88 */       if (this.bs != flag) {
/*  89 */         this.bs = flag;
/*  90 */         ((Navigation)getNavigation()).a(flag);
/*  91 */         if (flag) {
/*  92 */           this.goalSelector.a(1, this.br);
/*     */         } else {
/*  94 */           this.goalSelector.a(this.br);
/*     */         } 
/*     */       } 
/*  97 */     } else if (this.bs) {
/*  98 */       this.goalSelector.a(this.br);
/*  99 */       this.bs = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean eK() {
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/* 110 */     return ((Boolean)getDataWatcher().<Boolean>get(d)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getExpValue(EntityHuman entityhuman) {
/* 115 */     if (isBaby()) {
/* 116 */       this.f = (int)(this.f * 2.5F);
/*     */     }
/*     */     
/* 119 */     return super.getExpValue(entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaby(boolean flag) {
/* 124 */     getDataWatcher().set(d, Boolean.valueOf(flag));
/* 125 */     if (this.world != null && !this.world.isClientSide) {
/* 126 */       AttributeModifiable attributemodifiable = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*     */       
/* 128 */       attributemodifiable.removeModifier(this.babyModifier);
/* 129 */       if (flag) {
/* 130 */         attributemodifiable.b(this.babyModifier);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 138 */     if (d.equals(datawatcherobject)) {
/* 139 */       updateSize();
/*     */     }
/*     */     
/* 142 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   protected boolean eN() {
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 151 */     if (!this.world.isClientSide && isAlive() && !isNoAI()) {
/* 152 */       if (isDrownConverting()) {
/*     */         
/* 154 */         int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
/* 155 */         this.drownedConversionTime -= elapsedTicks;
/*     */         
/* 157 */         if (this.drownedConversionTime < 0) {
/* 158 */           eP();
/*     */         }
/* 160 */       } else if (eN()) {
/* 161 */         if (a(TagsFluid.WATER)) {
/* 162 */           this.bt++;
/* 163 */           if (this.bt >= 600) {
/* 164 */             startDrownedConversion(300);
/* 165 */             this.lastTick = MinecraftServer.currentTick;
/*     */           } 
/*     */         } else {
/* 168 */           this.bt = -1;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 173 */     super.tick();
/* 174 */     this.lastTick = MinecraftServer.currentTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 179 */     if (isAlive()) {
/* 180 */       boolean flag = (T_() && eG());
/*     */       
/* 182 */       if (flag) {
/* 183 */         ItemStack itemstack = getEquipment(EnumItemSlot.HEAD);
/*     */         
/* 185 */         if (!itemstack.isEmpty()) {
/* 186 */           if (itemstack.e()) {
/* 187 */             itemstack.setDamage(itemstack.getDamage() + this.random.nextInt(2));
/* 188 */             if (itemstack.getDamage() >= itemstack.h()) {
/* 189 */               broadcastItemBreak(EnumItemSlot.HEAD);
/* 190 */               setSlot(EnumItemSlot.HEAD, ItemStack.b);
/*     */             } 
/*     */           } 
/*     */           
/* 194 */           flag = false;
/*     */         } 
/*     */         
/* 197 */         if (flag) {
/* 198 */           setOnFire(8);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 203 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopDrowning() {
/* 208 */     this.drownedConversionTime = -1;
/* 209 */     getDataWatcher().set(DROWN_CONVERTING, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public void startDrownedConversion(int i) {
/* 213 */     this.lastTick = MinecraftServer.currentTick;
/* 214 */     this.drownedConversionTime = i;
/* 215 */     getDataWatcher().set(DROWN_CONVERTING, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   protected void eP() {
/* 219 */     b((EntityTypes)EntityTypes.DROWNED);
/* 220 */     if (!isSilent()) {
/* 221 */       this.world.a((EntityHuman)null, 1040, getChunkCoordinates(), 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(EntityTypes<? extends EntityZombie> entitytypes) {
/* 227 */     EntityZombie entityzombie = a((EntityTypes)entitytypes, true, EntityTransformEvent.TransformReason.DROWNED, CreatureSpawnEvent.SpawnReason.DROWNED);
/*     */     
/* 229 */     if (entityzombie != null) {
/* 230 */       entityzombie.y(entityzombie.world.getDamageScaler(entityzombie.getChunkCoordinates()).d());
/* 231 */       entityzombie.u((entityzombie.eK() && eU()));
/*     */     } else {
/* 233 */       ((Zombie)getBukkitEntity()).setConversionTime(-1);
/*     */     } 
/*     */   }
/*     */   public boolean shouldBurnInDay() {
/* 237 */     return T_();
/*     */   } protected boolean T_() {
/* 239 */     return this.shouldBurnInDay;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShouldBurnInDay(boolean shouldBurnInDay) {
/* 244 */     this.shouldBurnInDay = shouldBurnInDay;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 250 */     if (!super.damageEntity(damagesource, f))
/* 251 */       return false; 
/* 252 */     if (!(this.world instanceof WorldServer)) {
/* 253 */       return false;
/*     */     }
/* 255 */     WorldServer worldserver = (WorldServer)this.world;
/* 256 */     EntityLiving entityliving = getGoalTarget();
/*     */     
/* 258 */     if (entityliving == null && damagesource.getEntity() instanceof EntityLiving) {
/* 259 */       entityliving = (EntityLiving)damagesource.getEntity();
/*     */     }
/*     */     
/* 262 */     if (entityliving != null && this.world.getDifficulty() == EnumDifficulty.HARD && this.random.nextFloat() < b(GenericAttributes.SPAWN_REINFORCEMENTS) && this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
/* 263 */       int i = MathHelper.floor(locX());
/* 264 */       int j = MathHelper.floor(locY());
/* 265 */       int k = MathHelper.floor(locZ());
/* 266 */       EntityZombie entityzombie = new EntityZombie(this.world);
/*     */       
/* 268 */       for (int l = 0; l < 50; l++) {
/* 269 */         int i1 = i + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
/* 270 */         int j1 = j + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
/* 271 */         int k1 = k + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
/* 272 */         BlockPosition blockposition = new BlockPosition(i1, j1, k1);
/* 273 */         EntityTypes<?> entitytypes = entityzombie.getEntityType();
/* 274 */         EntityPositionTypes.Surface entitypositiontypes_surface = EntityPositionTypes.a(entitytypes);
/*     */         
/* 276 */         if (SpawnerCreature.a(entitypositiontypes_surface, this.world, blockposition, entitytypes) && EntityPositionTypes.a(entitytypes, worldserver, EnumMobSpawn.REINFORCEMENT, blockposition, this.world.random)) {
/* 277 */           entityzombie.setPosition(i1, j1, k1);
/* 278 */           if (!this.world.isPlayerNearby(i1, j1, k1, 7.0D) && this.world.j(entityzombie) && this.world.getCubes(entityzombie) && !this.world.containsLiquid(entityzombie.getBoundingBox())) {
/* 279 */             entityzombie.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.REINFORCEMENT_TARGET, true);
/* 280 */             entityzombie.prepare(worldserver, this.world.getDamageScaler(entityzombie.getChunkCoordinates()), EnumMobSpawn.REINFORCEMENT, (GroupDataEntity)null, (NBTTagCompound)null);
/* 281 */             worldserver.addAllEntities(entityzombie, CreatureSpawnEvent.SpawnReason.REINFORCEMENTS);
/* 282 */             getAttributeInstance(GenericAttributes.SPAWN_REINFORCEMENTS).addModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, AttributeModifier.Operation.ADDITION));
/* 283 */             entityzombie.getAttributeInstance(GenericAttributes.SPAWN_REINFORCEMENTS).addModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, AttributeModifier.Operation.ADDITION));
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 296 */     boolean flag = super.attackEntity(entity);
/*     */     
/* 298 */     if (flag) {
/* 299 */       float f = this.world.getDamageScaler(getChunkCoordinates()).b();
/*     */       
/* 301 */       if (getItemInMainHand().isEmpty() && isBurning() && this.random.nextFloat() < f * 0.3F) {
/*     */         
/* 303 */         EntityCombustByEntityEvent event = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), 2 * (int)f);
/* 304 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 306 */         if (!event.isCancelled()) {
/* 307 */           entity.setOnFire(event.getDuration(), false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 313 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 318 */     return SoundEffects.ENTITY_ZOMBIE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 323 */     return SoundEffects.ENTITY_ZOMBIE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 328 */     return SoundEffects.ENTITY_ZOMBIE_DEATH;
/*     */   }
/*     */   
/*     */   protected SoundEffect getSoundStep() {
/* 332 */     return SoundEffects.ENTITY_ZOMBIE_STEP;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 337 */     playSound(getSoundStep(), 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 342 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/* 347 */     super.a(difficultydamagescaler);
/* 348 */     if (this.random.nextFloat() < ((this.world.getDifficulty() == EnumDifficulty.HARD) ? 0.05F : 0.01F)) {
/* 349 */       int i = this.random.nextInt(3);
/*     */       
/* 351 */       if (i == 0) {
/* 352 */         setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
/*     */       } else {
/* 354 */         setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 362 */     super.saveData(nbttagcompound);
/* 363 */     nbttagcompound.setBoolean("IsBaby", isBaby());
/* 364 */     nbttagcompound.setBoolean("CanBreakDoors", eU());
/* 365 */     nbttagcompound.setInt("InWaterTime", isInWater() ? this.bt : -1);
/* 366 */     nbttagcompound.setInt("DrownedConversionTime", isDrownConverting() ? this.drownedConversionTime : -1);
/* 367 */     nbttagcompound.setBoolean("Paper.ShouldBurnInDay", this.shouldBurnInDay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 372 */     super.loadData(nbttagcompound);
/* 373 */     setBaby(nbttagcompound.getBoolean("IsBaby"));
/* 374 */     u(nbttagcompound.getBoolean("CanBreakDoors"));
/* 375 */     this.bt = nbttagcompound.getInt("InWaterTime");
/* 376 */     if (nbttagcompound.hasKeyOfType("DrownedConversionTime", 99) && nbttagcompound.getInt("DrownedConversionTime") > -1) {
/* 377 */       startDrownedConversion(nbttagcompound.getInt("DrownedConversionTime"));
/*     */     }
/*     */     
/* 380 */     if (nbttagcompound.hasKey("Paper.ShouldBurnInDay")) {
/* 381 */       this.shouldBurnInDay = nbttagcompound.getBoolean("Paper.ShouldBurnInDay");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(WorldServer worldserver, EntityLiving entityliving) {
/* 388 */     super.a(worldserver, entityliving);
/*     */     
/* 390 */     if (this.world.paperConfig.zombieVillagerInfectionChance != 0.0D && (this.world.paperConfig.zombieVillagerInfectionChance != -1.0D || worldserver.getDifficulty() == EnumDifficulty.NORMAL || worldserver.getDifficulty() == EnumDifficulty.HARD) && entityliving instanceof EntityVillager) {
/* 391 */       if (this.world.paperConfig.zombieVillagerInfectionChance == -1.0D && worldserver.getDifficulty() != EnumDifficulty.HARD && this.random.nextBoolean()) {
/*     */         return;
/*     */       }
/* 394 */       if (this.world.paperConfig.zombieVillagerInfectionChance != -1.0D && this.random.nextDouble() * 100.0D > this.world.paperConfig.zombieVillagerInfectionChance) {
/*     */         return;
/*     */       }
/*     */       
/* 398 */       EntityVillager entityvillager = (EntityVillager)entityliving;
/*     */       
/* 400 */       EntityZombieVillager entityzombievillager = entityvillager.<EntityZombieVillager>a(EntityTypes.ZOMBIE_VILLAGER, false, EntityTransformEvent.TransformReason.INFECTION, CreatureSpawnEvent.SpawnReason.INFECTION);
/* 401 */       if (entityzombievillager == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 406 */       entityzombievillager.prepare(worldserver, worldserver.getDamageScaler(entityzombievillager.getChunkCoordinates()), EnumMobSpawn.CONVERSION, new GroupDataZombie(false, true), (NBTTagCompound)null);
/* 407 */       entityzombievillager.setVillagerData(entityvillager.getVillagerData());
/* 408 */       entityzombievillager.a((NBTBase)entityvillager.fj().<T>a(DynamicOpsNBT.a).getValue());
/* 409 */       entityzombievillager.setOffers(entityvillager.getOffers().a());
/* 410 */       entityzombievillager.a(entityvillager.getExperience());
/* 411 */       if (!isSilent()) {
/* 412 */         worldserver.a((EntityHuman)null, 1026, getChunkCoordinates(), 0);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 420 */     return isBaby() ? 0.93F : 1.74F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPickup(ItemStack itemstack) {
/* 425 */     return (itemstack.getItem() == Items.EGG && isBaby() && isPassenger()) ? false : super.canPickup(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 431 */     Object object = super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/* 432 */     float f = difficultydamagescaler.d();
/*     */     
/* 434 */     setCanPickupLoot((this.random.nextFloat() < 0.55F * f));
/* 435 */     if (object == null) {
/* 436 */       object = new GroupDataZombie(a(worldaccess.getRandom()), true);
/*     */     }
/*     */     
/* 439 */     if (object instanceof GroupDataZombie) {
/* 440 */       GroupDataZombie entityzombie_groupdatazombie = (GroupDataZombie)object;
/*     */       
/* 442 */       if (entityzombie_groupdatazombie.a) {
/* 443 */         setBaby(true);
/* 444 */         if (entityzombie_groupdatazombie.b) {
/* 445 */           if (worldaccess.getRandom().nextFloat() < 0.05D) {
/* 446 */             List<EntityChicken> list = (List)worldaccess.a((Class)EntityChicken.class, getBoundingBox().grow(5.0D, 3.0D, 5.0D), IEntitySelector.c);
/*     */             
/* 448 */             if (!list.isEmpty()) {
/* 449 */               EntityChicken entitychicken = list.get(0);
/*     */               
/* 451 */               entitychicken.setChickenJockey(true);
/* 452 */               startRiding(entitychicken);
/*     */             } 
/* 454 */           } else if (worldaccess.getRandom().nextFloat() < 0.05D) {
/* 455 */             EntityChicken entitychicken1 = EntityTypes.CHICKEN.a(this.world);
/*     */             
/* 457 */             entitychicken1.setPositionRotation(locX(), locY(), locZ(), this.yaw, 0.0F);
/* 458 */             entitychicken1.prepare(worldaccess, difficultydamagescaler, EnumMobSpawn.JOCKEY, (GroupDataEntity)null, (NBTTagCompound)null);
/* 459 */             entitychicken1.setChickenJockey(true);
/* 460 */             startRiding(entitychicken1);
/* 461 */             worldaccess.addEntity(entitychicken1, CreatureSpawnEvent.SpawnReason.MOUNT);
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 466 */       u((eK() && this.random.nextFloat() < f * 0.1F));
/* 467 */       a(difficultydamagescaler);
/* 468 */       b(difficultydamagescaler);
/*     */     } 
/*     */     
/* 471 */     if (getEquipment(EnumItemSlot.HEAD).isEmpty()) {
/* 472 */       LocalDate localdate = LocalDate.now();
/* 473 */       int i = localdate.get(ChronoField.DAY_OF_MONTH);
/* 474 */       int j = localdate.get(ChronoField.MONTH_OF_YEAR);
/*     */       
/* 476 */       if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
/* 477 */         setSlot(EnumItemSlot.HEAD, new ItemStack((this.random.nextFloat() < 0.1F) ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
/* 478 */         this.dropChanceArmor[EnumItemSlot.HEAD.b()] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 482 */     y(f);
/* 483 */     return (GroupDataEntity)object;
/*     */   }
/*     */   
/*     */   public static boolean a(Random random) {
/* 487 */     return (random.nextFloat() < 0.05F);
/*     */   }
/*     */   
/*     */   protected void y(float f) {
/* 491 */     eV();
/* 492 */     getAttributeInstance(GenericAttributes.KNOCKBACK_RESISTANCE).addModifier(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * 0.05000000074505806D, AttributeModifier.Operation.ADDITION));
/* 493 */     double d0 = this.random.nextDouble() * 1.5D * f;
/*     */     
/* 495 */     if (d0 > 1.0D) {
/* 496 */       getAttributeInstance(GenericAttributes.FOLLOW_RANGE).addModifier(new AttributeModifier("Random zombie-spawn bonus", d0, AttributeModifier.Operation.MULTIPLY_TOTAL));
/*     */     }
/*     */     
/* 499 */     if (this.random.nextFloat() < f * 0.05F) {
/* 500 */       getAttributeInstance(GenericAttributes.SPAWN_REINFORCEMENTS).addModifier(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 0.25D + 0.5D, AttributeModifier.Operation.ADDITION));
/* 501 */       getAttributeInstance(GenericAttributes.MAX_HEALTH).addModifier(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 3.0D + 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL));
/* 502 */       u(eK());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eV() {
/* 508 */     getAttributeInstance(GenericAttributes.SPAWN_REINFORCEMENTS).setValue(this.random.nextDouble() * 0.10000000149011612D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double ba() {
/* 513 */     return isBaby() ? 0.0D : -0.45D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
/* 518 */     super.dropDeathLoot(damagesource, i, flag);
/* 519 */     Entity entity = damagesource.getEntity();
/*     */     
/* 521 */     if (entity instanceof EntityCreeper) {
/* 522 */       EntityCreeper entitycreeper = (EntityCreeper)entity;
/*     */       
/* 524 */       if (entitycreeper.canCauseHeadDrop()) {
/* 525 */         ItemStack itemstack = eM();
/*     */         
/* 527 */         if (!itemstack.isEmpty()) {
/* 528 */           entitycreeper.setCausedHeadDrop();
/* 529 */           a(itemstack);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack eM() {
/* 537 */     return new ItemStack(Items.ZOMBIE_HEAD);
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoalRemoveBlock {
/*     */     a(EntityCreature entitycreature, double d0, int i) {
/* 543 */       super(Blocks.TURTLE_EGG, entitycreature, d0, i);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 548 */       generatoraccess.playSound((EntityHuman)null, blockposition, SoundEffects.ENTITY_ZOMBIE_DESTROY_EGG, SoundCategory.HOSTILE, 0.5F, 0.9F + EntityZombie.this.random.nextFloat() * 0.2F);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(World world, BlockPosition blockposition) {
/* 553 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
/*     */     }
/*     */ 
/*     */     
/*     */     public double h() {
/* 558 */       return 1.14D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GroupDataZombie
/*     */     implements GroupDataEntity {
/*     */     public final boolean a;
/*     */     public final boolean b;
/*     */     
/*     */     public GroupDataZombie(boolean flag, boolean flag1) {
/* 568 */       this.a = flag;
/* 569 */       this.b = flag1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */