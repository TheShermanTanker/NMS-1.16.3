/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import com.google.common.collect.Maps;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import java.util.logging.Level;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftMob;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*      */ import org.bukkit.event.entity.EntityTargetEvent;
/*      */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*      */ import org.bukkit.event.entity.EntityTransformEvent;
/*      */ import org.bukkit.event.entity.EntityUnleashEvent;
/*      */ 
/*      */ public abstract class EntityInsentient extends EntityLiving {
/*   27 */   private static final DataWatcherObject<Byte> b = DataWatcher.a((Class)EntityInsentient.class, DataWatcherRegistry.a);
/*      */   
/*      */   public int e;
/*      */   protected int f;
/*      */   protected ControllerLook lookController;
/*      */   protected ControllerMove moveController;
/*      */   protected ControllerJump bi;
/*      */   private final EntityAIBodyControl c;
/*      */   protected NavigationAbstract navigation;
/*      */   public PathfinderGoalSelector goalSelector;
/*      */   @Nullable
/*      */   public PathfinderGoalFloat goalFloat;
/*      */   public PathfinderGoalSelector targetSelector;
/*      */   private EntityLiving goalTarget;
/*      */   private final EntitySenses bo;
/*      */   private final NonNullList<ItemStack> bp;
/*      */   public final float[] dropChanceHand;
/*      */   private final NonNullList<ItemStack> bq;
/*      */   public final float[] dropChanceArmor;
/*      */   public boolean persistent;
/*      */   private final Map<PathType, Float> bt;
/*      */   public MinecraftKey lootTableKey;
/*      */   public long lootTableSeed;
/*      */   @Nullable
/*      */   public Entity leashHolder;
/*      */   private int bx;
/*      */   @Nullable
/*      */   private NBTTagCompound by;
/*      */   private BlockPosition bz;
/*      */   private float bA;
/*      */   public boolean aware = true;
/*      */   
/*      */   protected EntityInsentient(EntityTypes<? extends EntityInsentient> entitytypes, World world) {
/*   60 */     super((EntityTypes)entitytypes, world);
/*   61 */     this.bp = NonNullList.a(2, ItemStack.b);
/*   62 */     this.dropChanceHand = new float[2];
/*   63 */     this.bq = NonNullList.a(4, ItemStack.b);
/*   64 */     this.dropChanceArmor = new float[4];
/*   65 */     this.bt = Maps.newEnumMap(PathType.class);
/*   66 */     this.bz = BlockPosition.ZERO;
/*   67 */     this.bA = -1.0F;
/*   68 */     this.goalSelector = new PathfinderGoalSelector(world.getMethodProfilerSupplier());
/*   69 */     this.targetSelector = new PathfinderGoalSelector(world.getMethodProfilerSupplier());
/*   70 */     this.lookController = new ControllerLook(this);
/*   71 */     this.moveController = new ControllerMove(this);
/*   72 */     this.bi = new ControllerJump(this);
/*   73 */     this.c = r();
/*   74 */     this.navigation = b(world);
/*   75 */     this.bo = new EntitySenses(this);
/*   76 */     Arrays.fill(this.dropChanceArmor, 0.085F);
/*   77 */     Arrays.fill(this.dropChanceHand, 0.085F);
/*   78 */     if (world != null && !world.isClientSide) {
/*   79 */       initPathfinder();
/*      */     }
/*      */ 
/*      */     
/*   83 */     this.persistent = !isTypeNotPersistent(0.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initPathfinder() {}
/*      */   
/*      */   public static AttributeProvider.Builder p() {
/*   90 */     return EntityLiving.cK().a(GenericAttributes.FOLLOW_RANGE, 16.0D).a(GenericAttributes.ATTACK_KNOCKBACK);
/*      */   }
/*      */   
/*      */   protected NavigationAbstract b(World world) {
/*   94 */     return new Navigation(this, world);
/*      */   }
/*      */   
/*      */   protected boolean q() {
/*   98 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public float a(PathType pathtype) {
/*      */     EntityInsentient entityinsentient;
/*  104 */     if (getVehicle() instanceof EntityInsentient && ((EntityInsentient)getVehicle()).q()) {
/*  105 */       entityinsentient = (EntityInsentient)getVehicle();
/*      */     } else {
/*  107 */       entityinsentient = this;
/*      */     } 
/*      */     
/*  110 */     Float ofloat = entityinsentient.bt.get(pathtype);
/*      */     
/*  112 */     return (ofloat == null) ? pathtype.a() : ofloat.floatValue();
/*      */   }
/*      */   
/*      */   public void a(PathType pathtype, float f) {
/*  116 */     this.bt.put(pathtype, Float.valueOf(f));
/*      */   }
/*      */   
/*      */   public boolean b(PathType pathtype) {
/*  120 */     return (pathtype != PathType.DANGER_FIRE && pathtype != PathType.DANGER_CACTUS && pathtype != PathType.DANGER_OTHER && pathtype != PathType.WALKABLE_DOOR);
/*      */   }
/*      */   
/*      */   protected EntityAIBodyControl r() {
/*  124 */     return new EntityAIBodyControl(this);
/*      */   }
/*      */   
/*      */   public ControllerLook getControllerLook() {
/*  128 */     return this.lookController;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void inactiveTick() {
/*  134 */     super.inactiveTick();
/*  135 */     this.goalSelector.inactiveTick();
/*  136 */     if (this.targetSelector.inactiveTick()) {
/*  137 */       this.targetSelector.doTick();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public ControllerMove getControllerMove() {
/*  143 */     if (isPassenger() && getVehicle() instanceof EntityInsentient) {
/*  144 */       EntityInsentient entityinsentient = (EntityInsentient)getVehicle();
/*      */       
/*  146 */       return entityinsentient.getControllerMove();
/*      */     } 
/*  148 */     return this.moveController;
/*      */   }
/*      */ 
/*      */   
/*      */   public ControllerJump getControllerJump() {
/*  153 */     return this.bi;
/*      */   }
/*      */   
/*      */   public NavigationAbstract getNavigation() {
/*  157 */     if (isPassenger() && getVehicle() instanceof EntityInsentient) {
/*  158 */       EntityInsentient entityinsentient = (EntityInsentient)getVehicle();
/*      */       
/*  160 */       return entityinsentient.getNavigation();
/*      */     } 
/*  162 */     return this.navigation;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntitySenses getEntitySenses() {
/*  167 */     return this.bo;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityLiving getGoalTarget() {
/*  172 */     return this.goalTarget;
/*      */   }
/*      */   public CraftMob getBukkitMob() {
/*  175 */     return (CraftMob)getBukkitEntity();
/*      */   }
/*      */   public void setGoalTarget(@Nullable EntityLiving entityliving) {
/*  178 */     setGoalTarget(entityliving, EntityTargetEvent.TargetReason.UNKNOWN, true);
/*      */   }
/*      */   
/*      */   public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
/*  182 */     if (getGoalTarget() == entityliving) return false; 
/*  183 */     if (fireEvent) {
/*  184 */       if (reason == EntityTargetEvent.TargetReason.UNKNOWN && getGoalTarget() != null && entityliving == null) {
/*  185 */         reason = getGoalTarget().isAlive() ? EntityTargetEvent.TargetReason.FORGOT_TARGET : EntityTargetEvent.TargetReason.TARGET_DIED;
/*      */       }
/*  187 */       if (reason == EntityTargetEvent.TargetReason.UNKNOWN) {
/*  188 */         this.world.getServer().getLogger().log(Level.WARNING, "Unknown target reason, please report on the issue tracker", new Exception());
/*      */       }
/*  190 */       CraftLivingEntity ctarget = null;
/*  191 */       if (entityliving != null) {
/*  192 */         ctarget = (CraftLivingEntity)entityliving.getBukkitEntity();
/*      */       }
/*  194 */       EntityTargetLivingEntityEvent event = new EntityTargetLivingEntityEvent((Entity)getBukkitEntity(), (LivingEntity)ctarget, reason);
/*  195 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*  196 */       if (event.isCancelled()) {
/*  197 */         return false;
/*      */       }
/*      */       
/*  200 */       if (event.getTarget() != null) {
/*  201 */         entityliving = ((CraftLivingEntity)event.getTarget()).getHandle();
/*      */       } else {
/*  203 */         entityliving = null;
/*      */       } 
/*      */     } 
/*  206 */     this.goalTarget = entityliving;
/*  207 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean a(EntityTypes<?> entitytypes) {
/*  213 */     return (entitytypes != EntityTypes.GHAST);
/*      */   }
/*      */   
/*      */   public boolean a(ItemProjectileWeapon itemprojectileweapon) {
/*  217 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void blockEaten() {}
/*      */   
/*      */   protected void initDatawatcher() {
/*  224 */     super.initDatawatcher();
/*  225 */     this.datawatcher.register(b, Byte.valueOf((byte)0));
/*      */   }
/*      */   
/*      */   public int D() {
/*  229 */     return 80;
/*      */   }
/*      */   
/*      */   public void F() {
/*  233 */     SoundEffect soundeffect = getSoundAmbient();
/*      */     
/*  235 */     if (soundeffect != null) {
/*  236 */       playSound(soundeffect, getSoundVolume(), dG());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void entityBaseTick() {
/*  243 */     super.entityBaseTick();
/*  244 */     this.world.getMethodProfiler().enter("mobBaseTick");
/*  245 */     if (isAlive() && this.random.nextInt(1000) < this.e++) {
/*  246 */       m();
/*  247 */       F();
/*      */     } 
/*      */     
/*  250 */     this.world.getMethodProfiler().exit();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void c(DamageSource damagesource) {
/*  255 */     m();
/*  256 */     super.c(damagesource);
/*      */   }
/*      */   
/*      */   private void m() {
/*  260 */     this.e = -D();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getExpValue(EntityHuman entityhuman) {
/*  265 */     if (this.f > 0) {
/*  266 */       int i = this.f;
/*      */       
/*      */       int j;
/*      */       
/*  270 */       for (j = 0; j < this.bq.size(); j++) {
/*  271 */         if (!((ItemStack)this.bq.get(j)).isEmpty() && this.dropChanceArmor[j] <= 1.0F) {
/*  272 */           i += 1 + this.random.nextInt(3);
/*      */         }
/*      */       } 
/*      */       
/*  276 */       for (j = 0; j < this.bp.size(); j++) {
/*  277 */         if (!((ItemStack)this.bp.get(j)).isEmpty() && this.dropChanceHand[j] <= 1.0F) {
/*  278 */           i += 1 + this.random.nextInt(3);
/*      */         }
/*      */       } 
/*      */       
/*  282 */       return i;
/*      */     } 
/*  284 */     return this.f;
/*      */   }
/*      */ 
/*      */   
/*      */   public void doSpawnEffect() {
/*  289 */     if (this.world.isClientSide) {
/*  290 */       for (int i = 0; i < 20; i++) {
/*  291 */         double d0 = this.random.nextGaussian() * 0.02D;
/*  292 */         double d1 = this.random.nextGaussian() * 0.02D;
/*  293 */         double d2 = this.random.nextGaussian() * 0.02D;
/*  294 */         double d3 = 10.0D;
/*      */         
/*  296 */         this.world.addParticle(Particles.POOF, c(1.0D) - d0 * 10.0D, cE() - d1 * 10.0D, g(1.0D) - d2 * 10.0D, d0, d1, d2);
/*      */       } 
/*      */     } else {
/*  299 */       this.world.broadcastEntityEffect(this, (byte)20);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {
/*  306 */     super.tick();
/*  307 */     if (!this.world.isClientSide) {
/*  308 */       eA();
/*  309 */       if (this.ticksLived % 5 == 0) {
/*  310 */         H();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void H() {
/*  317 */     boolean flag = !(getRidingPassenger() instanceof EntityInsentient);
/*  318 */     boolean flag1 = !(getVehicle() instanceof EntityBoat);
/*      */     
/*  320 */     this.goalSelector.a(PathfinderGoal.Type.MOVE, flag);
/*  321 */     this.goalSelector.a(PathfinderGoal.Type.JUMP, (flag && flag1));
/*  322 */     this.goalSelector.a(PathfinderGoal.Type.LOOK, flag);
/*      */   }
/*      */ 
/*      */   
/*      */   protected float f(float f, float f1) {
/*  327 */     this.c.a();
/*  328 */     return f1;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect getSoundAmbient() {
/*  333 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void saveData(NBTTagCompound nbttagcompound) {
/*  338 */     super.saveData(nbttagcompound);
/*  339 */     nbttagcompound.setBoolean("CanPickUpLoot", canPickupLoot());
/*  340 */     nbttagcompound.setBoolean("PersistenceRequired", this.persistent);
/*  341 */     NBTTagList nbttaglist = new NBTTagList();
/*      */ 
/*      */ 
/*      */     
/*  345 */     for (Iterator<ItemStack> iterator = this.bq.iterator(); iterator.hasNext(); nbttaglist.add(nbttagcompound1)) {
/*  346 */       ItemStack itemstack = iterator.next();
/*      */       
/*  348 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  349 */       if (!itemstack.isEmpty()) {
/*  350 */         itemstack.save(nbttagcompound1);
/*      */       }
/*      */     } 
/*      */     
/*  354 */     nbttagcompound.set("ArmorItems", nbttaglist);
/*  355 */     NBTTagList nbttaglist1 = new NBTTagList();
/*      */ 
/*      */ 
/*      */     
/*  359 */     for (Iterator<ItemStack> iterator1 = this.bp.iterator(); iterator1.hasNext(); nbttaglist1.add(nbttagcompound2)) {
/*  360 */       ItemStack itemstack1 = iterator1.next();
/*      */       
/*  362 */       NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*  363 */       if (!itemstack1.isEmpty()) {
/*  364 */         itemstack1.save(nbttagcompound2);
/*      */       }
/*      */     } 
/*      */     
/*  368 */     nbttagcompound.set("HandItems", nbttaglist1);
/*  369 */     NBTTagList nbttaglist2 = new NBTTagList();
/*  370 */     float[] afloat = this.dropChanceArmor;
/*  371 */     int i = afloat.length;
/*      */     
/*      */     int j;
/*      */     
/*  375 */     for (j = 0; j < i; j++) {
/*  376 */       float f = afloat[j];
/*      */       
/*  378 */       nbttaglist2.add(NBTTagFloat.a(f));
/*      */     } 
/*      */     
/*  381 */     nbttagcompound.set("ArmorDropChances", nbttaglist2);
/*  382 */     NBTTagList nbttaglist3 = new NBTTagList();
/*  383 */     float[] afloat1 = this.dropChanceHand;
/*      */     
/*  385 */     j = afloat1.length;
/*      */     
/*  387 */     for (int k = 0; k < j; k++) {
/*  388 */       float f1 = afloat1[k];
/*      */       
/*  390 */       nbttaglist3.add(NBTTagFloat.a(f1));
/*      */     } 
/*      */     
/*  393 */     nbttagcompound.set("HandDropChances", nbttaglist3);
/*  394 */     if (this.leashHolder != null) {
/*  395 */       NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*  396 */       if (this.leashHolder instanceof EntityLiving) {
/*  397 */         UUID uuid = this.leashHolder.getUniqueID();
/*      */         
/*  399 */         nbttagcompound2.a("UUID", uuid);
/*  400 */       } else if (this.leashHolder instanceof EntityHanging) {
/*  401 */         BlockPosition blockposition = ((EntityHanging)this.leashHolder).getBlockPosition();
/*      */         
/*  403 */         nbttagcompound2.setInt("X", blockposition.getX());
/*  404 */         nbttagcompound2.setInt("Y", blockposition.getY());
/*  405 */         nbttagcompound2.setInt("Z", blockposition.getZ());
/*      */       } 
/*      */       
/*  408 */       nbttagcompound.set("Leash", nbttagcompound2);
/*  409 */     } else if (this.by != null) {
/*  410 */       nbttagcompound.set("Leash", this.by.clone());
/*      */     } 
/*      */     
/*  413 */     nbttagcompound.setBoolean("LeftHanded", isLeftHanded());
/*  414 */     if (this.lootTableKey != null) {
/*  415 */       nbttagcompound.setString("DeathLootTable", this.lootTableKey.toString());
/*  416 */       if (this.lootTableSeed != 0L) {
/*  417 */         nbttagcompound.setLong("DeathLootTableSeed", this.lootTableSeed);
/*      */       }
/*      */     } 
/*      */     
/*  421 */     if (isNoAI()) {
/*  422 */       nbttagcompound.setBoolean("NoAI", isNoAI());
/*      */     }
/*      */     
/*  425 */     nbttagcompound.setBoolean("Bukkit.Aware", this.aware);
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadData(NBTTagCompound nbttagcompound) {
/*  430 */     super.loadData(nbttagcompound);
/*      */ 
/*      */     
/*  433 */     if (nbttagcompound.hasKeyOfType("CanPickUpLoot", 1)) {
/*  434 */       boolean bool = nbttagcompound.getBoolean("CanPickUpLoot");
/*  435 */       if (isLevelAtLeast(nbttagcompound, 1) || bool) {
/*  436 */         setCanPickupLoot(bool);
/*      */       }
/*      */     } 
/*      */     
/*  440 */     boolean data = nbttagcompound.getBoolean("PersistenceRequired");
/*  441 */     if (isLevelAtLeast(nbttagcompound, 1) || data) {
/*  442 */       this.persistent = data;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  448 */     if (nbttagcompound.hasKeyOfType("ArmorItems", 9)) {
/*  449 */       NBTTagList nbttaglist = nbttagcompound.getList("ArmorItems", 10);
/*      */       
/*  451 */       for (int i = 0; i < this.bq.size(); i++) {
/*  452 */         this.bq.set(i, ItemStack.a(nbttaglist.getCompound(i)));
/*      */       }
/*      */     } 
/*      */     
/*  456 */     if (nbttagcompound.hasKeyOfType("HandItems", 9)) {
/*  457 */       NBTTagList nbttaglist = nbttagcompound.getList("HandItems", 10);
/*      */       
/*  459 */       for (int i = 0; i < this.bp.size(); i++) {
/*  460 */         this.bp.set(i, ItemStack.a(nbttaglist.getCompound(i)));
/*      */       }
/*      */     } 
/*      */     
/*  464 */     if (nbttagcompound.hasKeyOfType("ArmorDropChances", 9)) {
/*  465 */       NBTTagList nbttaglist = nbttagcompound.getList("ArmorDropChances", 5);
/*      */       
/*  467 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  468 */         this.dropChanceArmor[i] = nbttaglist.i(i);
/*      */       }
/*      */     } 
/*      */     
/*  472 */     if (nbttagcompound.hasKeyOfType("HandDropChances", 9)) {
/*  473 */       NBTTagList nbttaglist = nbttagcompound.getList("HandDropChances", 5);
/*      */       
/*  475 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  476 */         this.dropChanceHand[i] = nbttaglist.i(i);
/*      */       }
/*      */     } 
/*      */     
/*  480 */     if (nbttagcompound.hasKeyOfType("Leash", 10)) {
/*  481 */       this.by = nbttagcompound.getCompound("Leash");
/*      */     }
/*      */     
/*  484 */     setLeftHanded(nbttagcompound.getBoolean("LeftHanded"));
/*  485 */     if (nbttagcompound.hasKeyOfType("DeathLootTable", 8)) {
/*  486 */       this.lootTableKey = new MinecraftKey(nbttagcompound.getString("DeathLootTable"));
/*  487 */       this.lootTableSeed = nbttagcompound.getLong("DeathLootTableSeed");
/*      */     } 
/*      */     
/*  490 */     setNoAI(nbttagcompound.getBoolean("NoAI"));
/*      */     
/*  492 */     if (nbttagcompound.hasKey("Bukkit.Aware")) {
/*  493 */       this.aware = nbttagcompound.getBoolean("Bukkit.Aware");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void a(DamageSource damagesource, boolean flag) {
/*  500 */     super.a(damagesource, flag);
/*  501 */     this.lootTableKey = null;
/*      */   }
/*      */   
/*      */   public MinecraftKey getLootTable() {
/*  505 */     return getDefaultLootTable();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected LootTableInfo.Builder a(boolean flag, DamageSource damagesource) {
/*  511 */     return super.a(flag, damagesource).a(this.lootTableSeed, this.random);
/*      */   }
/*      */ 
/*      */   
/*      */   public final MinecraftKey do_() {
/*  516 */     return (this.lootTableKey == null) ? getDefaultLootTable() : this.lootTableKey;
/*      */   }
/*      */   
/*      */   protected MinecraftKey getDefaultLootTable() {
/*  520 */     return super.do_();
/*      */   }
/*      */   
/*      */   public void t(float f) {
/*  524 */     this.aT = f;
/*      */   }
/*      */   
/*      */   public void u(float f) {
/*  528 */     this.aS = f;
/*      */   }
/*      */   
/*      */   public void v(float f) {
/*  532 */     this.aR = f;
/*      */   }
/*      */ 
/*      */   
/*      */   public void q(float f) {
/*  537 */     super.q(f);
/*  538 */     t(f);
/*      */   }
/*      */ 
/*      */   
/*      */   public void movementTick() {
/*  543 */     super.movementTick();
/*  544 */     this.world.getMethodProfiler().enter("looting");
/*  545 */     if (!this.world.isClientSide && canPickupLoot() && isAlive() && !this.killed && this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
/*  546 */       List<EntityItem> list = this.world.a(EntityItem.class, getBoundingBox().grow(1.0D, 0.0D, 1.0D));
/*  547 */       Iterator<EntityItem> iterator = list.iterator();
/*      */       
/*  549 */       while (iterator.hasNext()) {
/*  550 */         EntityItem entityitem = iterator.next();
/*      */         
/*  552 */         if (!entityitem.dead && !entityitem.getItemStack().isEmpty() && !entityitem.p() && i(entityitem.getItemStack())) {
/*      */           
/*  554 */           if (!entityitem.canMobPickup) {
/*      */             continue;
/*      */           }
/*      */           
/*  558 */           b(entityitem);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  563 */     this.world.getMethodProfiler().exit();
/*      */   }
/*      */   
/*      */   protected void b(EntityItem entityitem) {
/*  567 */     ItemStack itemstack = entityitem.getItemStack();
/*      */     
/*  569 */     if (g(itemstack, entityitem)) {
/*  570 */       a(entityitem);
/*  571 */       receive(entityitem, itemstack.getCount());
/*  572 */       entityitem.die();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean g(ItemStack itemstack) {
/*  579 */     return g(itemstack, (EntityItem)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean g(ItemStack itemstack, EntityItem entityitem) {
/*  584 */     EnumItemSlot enumitemslot = j(itemstack);
/*  585 */     ItemStack itemstack1 = getEquipment(enumitemslot);
/*  586 */     boolean flag = a(itemstack, itemstack1);
/*      */ 
/*      */     
/*  589 */     boolean canPickup = (flag && canPickup(itemstack));
/*  590 */     if (entityitem != null) {
/*  591 */       canPickup = !CraftEventFactory.callEntityPickupItemEvent(this, entityitem, 0, !canPickup).isCancelled();
/*      */     }
/*  593 */     if (canPickup) {
/*      */       
/*  595 */       double d0 = e(enumitemslot);
/*      */       
/*  597 */       if (!itemstack1.isEmpty() && Math.max(this.random.nextFloat() - 0.1F, 0.0F) < d0) {
/*  598 */         this.forceDrops = true;
/*  599 */         a(itemstack1);
/*  600 */         this.forceDrops = false;
/*      */       } 
/*      */       
/*  603 */       b(enumitemslot, itemstack);
/*  604 */       b(itemstack);
/*  605 */       return true;
/*      */     } 
/*  607 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(EnumItemSlot enumitemslot, ItemStack itemstack) {
/*  612 */     setSlot(enumitemslot, itemstack);
/*  613 */     d(enumitemslot);
/*  614 */     this.persistent = true;
/*      */   }
/*      */   
/*      */   public void d(EnumItemSlot enumitemslot) {
/*  618 */     switch (enumitemslot.a()) {
/*      */       case HEAD:
/*  620 */         this.dropChanceHand[enumitemslot.b()] = 2.0F;
/*      */         break;
/*      */       case CHEST:
/*  623 */         this.dropChanceArmor[enumitemslot.b()] = 2.0F;
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean a(ItemStack itemstack, ItemStack itemstack1) {
/*  629 */     if (itemstack1.isEmpty())
/*  630 */       return true; 
/*  631 */     if (itemstack.getItem() instanceof ItemSword) {
/*  632 */       if (!(itemstack1.getItem() instanceof ItemSword)) {
/*  633 */         return true;
/*      */       }
/*  635 */       ItemSword itemsword = (ItemSword)itemstack.getItem();
/*  636 */       ItemSword itemsword1 = (ItemSword)itemstack1.getItem();
/*      */       
/*  638 */       return (itemsword.f() != itemsword1.f()) ? ((itemsword.f() > itemsword1.f())) : b(itemstack, itemstack1);
/*      */     } 
/*  640 */     if (itemstack.getItem() instanceof ItemBow && itemstack1.getItem() instanceof ItemBow)
/*  641 */       return b(itemstack, itemstack1); 
/*  642 */     if (itemstack.getItem() instanceof ItemCrossbow && itemstack1.getItem() instanceof ItemCrossbow)
/*  643 */       return b(itemstack, itemstack1); 
/*  644 */     if (itemstack.getItem() instanceof ItemArmor) {
/*  645 */       if (EnchantmentManager.d(itemstack1))
/*  646 */         return false; 
/*  647 */       if (!(itemstack1.getItem() instanceof ItemArmor)) {
/*  648 */         return true;
/*      */       }
/*  650 */       ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
/*  651 */       ItemArmor itemarmor1 = (ItemArmor)itemstack1.getItem();
/*      */       
/*  653 */       return (itemarmor.e() != itemarmor1.e()) ? ((itemarmor.e() > itemarmor1.e())) : ((itemarmor.f() != itemarmor1.f()) ? ((itemarmor.f() > itemarmor1.f())) : b(itemstack, itemstack1));
/*      */     } 
/*      */     
/*  656 */     if (itemstack.getItem() instanceof ItemTool) {
/*  657 */       if (itemstack1.getItem() instanceof ItemBlock) {
/*  658 */         return true;
/*      */       }
/*      */       
/*  661 */       if (itemstack1.getItem() instanceof ItemTool) {
/*  662 */         ItemTool itemtool = (ItemTool)itemstack.getItem();
/*  663 */         ItemTool itemtool1 = (ItemTool)itemstack1.getItem();
/*      */         
/*  665 */         if (itemtool.d() != itemtool1.d()) {
/*  666 */           return (itemtool.d() > itemtool1.d());
/*      */         }
/*      */         
/*  669 */         return b(itemstack, itemstack1);
/*      */       } 
/*      */     } 
/*      */     
/*  673 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean b(ItemStack itemstack, ItemStack itemstack1) {
/*  678 */     return (itemstack.getDamage() >= itemstack1.getDamage() && (!itemstack.hasTag() || itemstack1.hasTag())) ? ((itemstack.hasTag() && itemstack1.hasTag()) ? (
/*      */       
/*  680 */       (itemstack.getTag().getKeys().stream().anyMatch(s -> !s.equals("Damage")) && !itemstack1.getTag().getKeys().stream().anyMatch(s -> !s.equals("Damage")))) : false) : true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canPickup(ItemStack itemstack) {
/*  686 */     return true;
/*      */   }
/*      */   
/*      */   public boolean i(ItemStack itemstack) {
/*  690 */     return canPickup(itemstack);
/*      */   }
/*      */   
/*      */   public boolean isTypeNotPersistent(double d0) {
/*  694 */     return true;
/*      */   }
/*      */   
/*      */   public boolean isSpecialPersistence() {
/*  698 */     return isPassenger();
/*      */   }
/*      */   
/*      */   protected boolean L() {
/*  702 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void checkDespawn() {
/*  707 */     if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL && L()) {
/*  708 */       die();
/*  709 */     } else if (!isPersistent() && !isSpecialPersistence()) {
/*  710 */       EntityHuman entityhuman = this.world.findNearbyPlayer(this, -1.0D, IEntitySelector.affectsSpawning);
/*      */       
/*  712 */       if (entityhuman != null) {
/*  713 */         double d0 = entityhuman.h(this);
/*  714 */         int i = getEntityType().e().f();
/*  715 */         int j = i * i;
/*      */         
/*  717 */         if (d0 > this.world.paperConfig.hardDespawnDistance) {
/*  718 */           die();
/*      */         }
/*      */         
/*  721 */         int k = getEntityType().e().g();
/*  722 */         int l = k * k;
/*      */         
/*  724 */         if (this.ticksFarFromPlayer > 600 && this.random.nextInt(800) == 0 && d0 > this.world.paperConfig.softDespawnDistance) {
/*  725 */           die();
/*  726 */         } else if (d0 < l) {
/*  727 */           this.ticksFarFromPlayer = 0;
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  732 */       this.ticksFarFromPlayer = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void doTick() {
/*  738 */     this.ticksFarFromPlayer++;
/*  739 */     if (!this.aware) {
/*  740 */       if (this.goalFloat != null) {
/*  741 */         if (this.goalFloat.validConditions()) this.goalFloat.update(); 
/*  742 */         getControllerJump().jumpIfSet();
/*      */       } 
/*  744 */       if ((this instanceof EntityBlaze || this instanceof EntityEnderman) && isInWaterOrRainOrBubble()) {
/*  745 */         damageEntity(DamageSource.DROWN, 1.0F);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  750 */     this.world.getMethodProfiler().enter("sensing");
/*  751 */     this.bo.a();
/*  752 */     this.world.getMethodProfiler().exit();
/*  753 */     this.world.getMethodProfiler().enter("targetSelector");
/*  754 */     this.targetSelector.doTick();
/*  755 */     this.world.getMethodProfiler().exit();
/*  756 */     this.world.getMethodProfiler().enter("goalSelector");
/*  757 */     this.goalSelector.doTick();
/*  758 */     this.world.getMethodProfiler().exit();
/*  759 */     this.world.getMethodProfiler().enter("navigation");
/*  760 */     this.navigation.c();
/*  761 */     this.world.getMethodProfiler().exit();
/*  762 */     this.world.getMethodProfiler().enter("mob tick");
/*  763 */     mobTick();
/*  764 */     this.world.getMethodProfiler().exit();
/*  765 */     this.world.getMethodProfiler().enter("controls");
/*  766 */     this.world.getMethodProfiler().enter("move");
/*  767 */     this.moveController.a();
/*  768 */     this.world.getMethodProfiler().exitEnter("look");
/*  769 */     this.lookController.a();
/*  770 */     this.world.getMethodProfiler().exitEnter("jump");
/*  771 */     this.bi.b();
/*  772 */     this.world.getMethodProfiler().exit();
/*  773 */     this.world.getMethodProfiler().exit();
/*  774 */     M();
/*      */   }
/*      */   
/*      */   protected void M() {
/*  778 */     PacketDebug.a(this.world, this, this.goalSelector);
/*      */   }
/*      */   
/*      */   protected void mobTick() {}
/*      */   
/*      */   public int O() {
/*  784 */     return 40;
/*      */   }
/*      */   
/*      */   public int eo() {
/*  788 */     return 75;
/*      */   }
/*      */   
/*      */   public int ep() {
/*  792 */     return 10;
/*      */   }
/*      */   
/*      */   public void a(Entity entity, float f, float f1) {
/*  796 */     double d2, d0 = entity.locX() - locX();
/*  797 */     double d1 = entity.locZ() - locZ();
/*      */ 
/*      */     
/*  800 */     if (entity instanceof EntityLiving) {
/*  801 */       EntityLiving entityliving = (EntityLiving)entity;
/*      */       
/*  803 */       d2 = entityliving.getHeadY() - getHeadY();
/*      */     } else {
/*  805 */       d2 = ((entity.getBoundingBox()).minY + (entity.getBoundingBox()).maxY) / 2.0D - getHeadY();
/*      */     } 
/*      */     
/*  808 */     double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1);
/*  809 */     float f2 = (float)(MathHelper.d(d1, d0) * 57.2957763671875D) - 90.0F;
/*  810 */     float f3 = (float)-(MathHelper.d(d2, d3) * 57.2957763671875D);
/*      */     
/*  812 */     this.pitch = a(this.pitch, f3, f1);
/*  813 */     this.yaw = a(this.yaw, f2, f);
/*      */   }
/*      */   
/*      */   private float a(float f, float f1, float f2) {
/*  817 */     float f3 = MathHelper.g(f1 - f);
/*      */     
/*  819 */     if (f3 > f2) {
/*  820 */       f3 = f2;
/*      */     }
/*      */     
/*  823 */     if (f3 < -f2) {
/*  824 */       f3 = -f2;
/*      */     }
/*      */     
/*  827 */     return f + f3;
/*      */   }
/*      */   
/*      */   public static boolean a(EntityTypes<? extends EntityInsentient> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  831 */     BlockPosition blockposition1 = blockposition.down();
/*      */     
/*  833 */     return (enummobspawn == EnumMobSpawn.SPAWNER || generatoraccess.getType(blockposition1).a(generatoraccess, blockposition1, entitytypes));
/*      */   }
/*      */   
/*      */   public boolean a(GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn) {
/*  837 */     return true;
/*      */   }
/*      */   
/*      */   public boolean a(IWorldReader iworldreader) {
/*  841 */     return (!iworldreader.containsLiquid(getBoundingBox()) && iworldreader.j(this));
/*      */   }
/*      */   
/*      */   public int getMaxSpawnGroup() {
/*  845 */     return 4;
/*      */   }
/*      */   
/*      */   public boolean c(int i) {
/*  849 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int bO() {
/*  854 */     if (getGoalTarget() == null) {
/*  855 */       return 3;
/*      */     }
/*  857 */     int i = (int)(getHealth() - getMaxHealth() * 0.33F);
/*      */     
/*  859 */     i -= (3 - this.world.getDifficulty().a()) * 4;
/*  860 */     if (i < 0) {
/*  861 */       i = 0;
/*      */     }
/*      */     
/*  864 */     return i + 3;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterable<ItemStack> bm() {
/*  870 */     return this.bp;
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterable<ItemStack> getArmorItems() {
/*  875 */     return this.bq;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getEquipment(EnumItemSlot enumitemslot) {
/*  880 */     switch (enumitemslot.a()) {
/*      */       case HEAD:
/*  882 */         return this.bp.get(enumitemslot.b());
/*      */       case CHEST:
/*  884 */         return this.bq.get(enumitemslot.b());
/*      */     } 
/*  886 */     return ItemStack.b;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSlot(EnumItemSlot enumitemslot, ItemStack itemstack) {
/*  892 */     switch (enumitemslot.a()) {
/*      */       case HEAD:
/*  894 */         this.bp.set(enumitemslot.b(), itemstack);
/*      */         break;
/*      */       case CHEST:
/*  897 */         this.bq.set(enumitemslot.b(), itemstack);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
/*  904 */     super.dropDeathLoot(damagesource, i, flag);
/*  905 */     EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/*  906 */     int j = aenumitemslot.length;
/*      */     
/*  908 */     for (int k = 0; k < j; k++) {
/*  909 */       EnumItemSlot enumitemslot = aenumitemslot[k];
/*  910 */       ItemStack itemstack = getEquipment(enumitemslot);
/*  911 */       float f = e(enumitemslot);
/*  912 */       boolean flag1 = (f > 1.0F);
/*      */       
/*  914 */       if (!itemstack.isEmpty() && !EnchantmentManager.shouldNotDrop(itemstack) && (flag || flag1) && Math.max(this.random.nextFloat() - i * 0.01F, 0.0F) < f) {
/*  915 */         if (!flag1 && itemstack.e()) {
/*  916 */           itemstack.setDamage(itemstack.h() - this.random.nextInt(1 + this.random.nextInt(Math.max(itemstack.h() - 3, 1))));
/*      */         }
/*      */         
/*  919 */         a(itemstack);
/*  920 */         setSlot(enumitemslot, ItemStack.b);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float e(EnumItemSlot enumitemslot) {
/*  929 */     switch (enumitemslot.a())
/*      */     { case HEAD:
/*  931 */         f = this.dropChanceHand[enumitemslot.b()];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  940 */         return f;case CHEST: f = this.dropChanceArmor[enumitemslot.b()]; return f; }  float f = 0.0F; return f;
/*      */   }
/*      */   
/*      */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/*  944 */     if (this.random.nextFloat() < 0.15F * difficultydamagescaler.d()) {
/*  945 */       int i = this.random.nextInt(2);
/*  946 */       float f = (this.world.getDifficulty() == EnumDifficulty.HARD) ? 0.1F : 0.25F;
/*      */       
/*  948 */       if (this.random.nextFloat() < 0.095F) {
/*  949 */         i++;
/*      */       }
/*      */       
/*  952 */       if (this.random.nextFloat() < 0.095F) {
/*  953 */         i++;
/*      */       }
/*      */       
/*  956 */       if (this.random.nextFloat() < 0.095F) {
/*  957 */         i++;
/*      */       }
/*      */       
/*  960 */       boolean flag = true;
/*  961 */       EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/*  962 */       int j = aenumitemslot.length;
/*      */       
/*  964 */       for (int k = 0; k < j; k++) {
/*  965 */         EnumItemSlot enumitemslot = aenumitemslot[k];
/*      */         
/*  967 */         if (enumitemslot.a() == EnumItemSlot.Function.ARMOR) {
/*  968 */           ItemStack itemstack = getEquipment(enumitemslot);
/*      */           
/*  970 */           if (!flag && this.random.nextFloat() < f) {
/*      */             break;
/*      */           }
/*      */           
/*  974 */           flag = false;
/*  975 */           if (itemstack.isEmpty()) {
/*  976 */             Item item = a(enumitemslot, i);
/*      */             
/*  978 */             if (item != null) {
/*  979 */               setSlot(enumitemslot, new ItemStack(item));
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static EnumItemSlot j(ItemStack itemstack) {
/*  989 */     Item item = itemstack.getItem();
/*      */     
/*  991 */     return (item != Blocks.CARVED_PUMPKIN.getItem() && (!(item instanceof ItemBlock) || !(((ItemBlock)item).getBlock() instanceof BlockSkullAbstract))) ? ((item instanceof ItemArmor) ? ((ItemArmor)item).b() : ((item == Items.ELYTRA) ? EnumItemSlot.CHEST : ((item == Items.SHIELD) ? EnumItemSlot.OFFHAND : EnumItemSlot.MAINHAND))) : EnumItemSlot.HEAD;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public static Item a(EnumItemSlot enumitemslot, int i) {
/*  996 */     switch (enumitemslot) {
/*      */       case HEAD:
/*  998 */         if (i == 0)
/*  999 */           return Items.LEATHER_HELMET; 
/* 1000 */         if (i == 1)
/* 1001 */           return Items.GOLDEN_HELMET; 
/* 1002 */         if (i == 2)
/* 1003 */           return Items.CHAINMAIL_HELMET; 
/* 1004 */         if (i == 3)
/* 1005 */           return Items.IRON_HELMET; 
/* 1006 */         if (i == 4) {
/* 1007 */           return Items.DIAMOND_HELMET;
/*      */         }
/*      */       case CHEST:
/* 1010 */         if (i == 0)
/* 1011 */           return Items.LEATHER_CHESTPLATE; 
/* 1012 */         if (i == 1)
/* 1013 */           return Items.GOLDEN_CHESTPLATE; 
/* 1014 */         if (i == 2)
/* 1015 */           return Items.CHAINMAIL_CHESTPLATE; 
/* 1016 */         if (i == 3)
/* 1017 */           return Items.IRON_CHESTPLATE; 
/* 1018 */         if (i == 4) {
/* 1019 */           return Items.DIAMOND_CHESTPLATE;
/*      */         }
/*      */       case LEGS:
/* 1022 */         if (i == 0)
/* 1023 */           return Items.LEATHER_LEGGINGS; 
/* 1024 */         if (i == 1)
/* 1025 */           return Items.GOLDEN_LEGGINGS; 
/* 1026 */         if (i == 2)
/* 1027 */           return Items.CHAINMAIL_LEGGINGS; 
/* 1028 */         if (i == 3)
/* 1029 */           return Items.IRON_LEGGINGS; 
/* 1030 */         if (i == 4) {
/* 1031 */           return Items.DIAMOND_LEGGINGS;
/*      */         }
/*      */       case FEET:
/* 1034 */         if (i == 0)
/* 1035 */           return Items.LEATHER_BOOTS; 
/* 1036 */         if (i == 1)
/* 1037 */           return Items.GOLDEN_BOOTS; 
/* 1038 */         if (i == 2)
/* 1039 */           return Items.CHAINMAIL_BOOTS; 
/* 1040 */         if (i == 3)
/* 1041 */           return Items.IRON_BOOTS; 
/* 1042 */         if (i == 4)
/* 1043 */           return Items.DIAMOND_BOOTS; 
/*      */         break;
/*      */     } 
/* 1046 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(DifficultyDamageScaler difficultydamagescaler) {
/* 1051 */     float f = difficultydamagescaler.d();
/*      */     
/* 1053 */     w(f);
/* 1054 */     EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/* 1055 */     int i = aenumitemslot.length;
/*      */     
/* 1057 */     for (int j = 0; j < i; j++) {
/* 1058 */       EnumItemSlot enumitemslot = aenumitemslot[j];
/*      */       
/* 1060 */       if (enumitemslot.a() == EnumItemSlot.Function.ARMOR) {
/* 1061 */         a(f, enumitemslot);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void w(float f) {
/* 1068 */     if (!getItemInMainHand().isEmpty() && this.random.nextFloat() < 0.25F * f) {
/* 1069 */       setSlot(EnumItemSlot.MAINHAND, EnchantmentManager.a(this.random, getItemInMainHand(), (int)(5.0F + f * this.random.nextInt(18)), false));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(float f, EnumItemSlot enumitemslot) {
/* 1075 */     ItemStack itemstack = getEquipment(enumitemslot);
/*      */     
/* 1077 */     if (!itemstack.isEmpty() && this.random.nextFloat() < 0.5F * f) {
/* 1078 */       setSlot(enumitemslot, EnchantmentManager.a(this.random, itemstack, (int)(5.0F + f * this.random.nextInt(18)), false));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 1085 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).addModifier(new AttributeModifier("Random spawn bonus", this.random.nextGaussian() * 0.05D, AttributeModifier.Operation.MULTIPLY_BASE));
/* 1086 */     if (this.random.nextFloat() < 0.05F) {
/* 1087 */       setLeftHanded(true);
/*      */     } else {
/* 1089 */       setLeftHanded(false);
/*      */     } 
/*      */     
/* 1092 */     return groupdataentity;
/*      */   }
/*      */   
/*      */   public boolean er() {
/* 1096 */     return false;
/*      */   }
/*      */   
/*      */   public void setPersistent() {
/* 1100 */     this.persistent = true;
/*      */   }
/*      */   
/*      */   public void a(EnumItemSlot enumitemslot, float f) {
/* 1104 */     switch (enumitemslot.a()) {
/*      */       case HEAD:
/* 1106 */         this.dropChanceHand[enumitemslot.b()] = f;
/*      */         break;
/*      */       case CHEST:
/* 1109 */         this.dropChanceArmor[enumitemslot.b()] = f;
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean canPickupLoot() {
/* 1115 */     return this.canPickUpLoot;
/*      */   }
/*      */   
/*      */   public void setCanPickupLoot(boolean flag) {
/* 1119 */     this.canPickUpLoot = flag;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean e(ItemStack itemstack) {
/* 1124 */     EnumItemSlot enumitemslot = j(itemstack);
/*      */     
/* 1126 */     return (getEquipment(enumitemslot).isEmpty() && canPickupLoot());
/*      */   }
/*      */   
/*      */   public boolean isPersistent() {
/* 1130 */     return this.persistent;
/*      */   }
/*      */ 
/*      */   
/*      */   public final EnumInteractionResult a(EntityHuman entityhuman, EnumHand enumhand) {
/* 1135 */     if (!isAlive())
/* 1136 */       return EnumInteractionResult.PASS; 
/* 1137 */     if (getLeashHolder() == entityhuman) {
/*      */       
/* 1139 */       if (CraftEventFactory.callPlayerUnleashEntityEvent(this, entityhuman).isCancelled()) {
/* 1140 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutAttachEntity(this, getLeashHolder()));
/* 1141 */         return EnumInteractionResult.PASS;
/*      */       } 
/*      */       
/* 1144 */       unleash(true, !entityhuman.abilities.canInstantlyBuild);
/* 1145 */       return EnumInteractionResult.a(this.world.isClientSide);
/*      */     } 
/* 1147 */     EnumInteractionResult enuminteractionresult = c(entityhuman, enumhand);
/*      */     
/* 1149 */     if (enuminteractionresult.a()) {
/* 1150 */       return enuminteractionresult;
/*      */     }
/* 1152 */     enuminteractionresult = b(entityhuman, enumhand);
/* 1153 */     return enuminteractionresult.a() ? enuminteractionresult : super.a(entityhuman, enumhand);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private EnumInteractionResult c(EntityHuman entityhuman, EnumHand enumhand) {
/* 1159 */     ItemStack itemstack = entityhuman.b(enumhand);
/*      */     
/* 1161 */     if (itemstack.getItem() == Items.LEAD && a(entityhuman)) {
/*      */       
/* 1163 */       if (CraftEventFactory.callPlayerLeashEntityEvent(this, entityhuman, entityhuman).isCancelled()) {
/* 1164 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutAttachEntity(this, getLeashHolder()));
/* 1165 */         return EnumInteractionResult.PASS;
/*      */       } 
/*      */       
/* 1168 */       setLeashHolder(entityhuman, true);
/* 1169 */       itemstack.subtract(1);
/* 1170 */       return EnumInteractionResult.a(this.world.isClientSide);
/*      */     } 
/* 1172 */     if (itemstack.getItem() == Items.NAME_TAG) {
/* 1173 */       EnumInteractionResult enuminteractionresult = itemstack.a(entityhuman, this, enumhand);
/*      */       
/* 1175 */       if (enuminteractionresult.a()) {
/* 1176 */         return enuminteractionresult;
/*      */       }
/*      */     } 
/*      */     
/* 1180 */     if (itemstack.getItem() instanceof ItemMonsterEgg) {
/* 1181 */       if (this.world instanceof WorldServer) {
/* 1182 */         ItemMonsterEgg itemmonsteregg = (ItemMonsterEgg)itemstack.getItem();
/* 1183 */         Optional<EntityInsentient> optional = itemmonsteregg.a(entityhuman, this, (EntityTypes)getEntityType(), (WorldServer)this.world, getPositionVector(), itemstack);
/*      */         
/* 1185 */         optional.ifPresent(entityinsentient -> a(entityhuman, entityinsentient));
/*      */ 
/*      */         
/* 1188 */         return optional.isPresent() ? EnumInteractionResult.SUCCESS : EnumInteractionResult.PASS;
/*      */       } 
/* 1190 */       return EnumInteractionResult.CONSUME;
/*      */     } 
/*      */     
/* 1193 */     return EnumInteractionResult.PASS;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(EntityHuman entityhuman, EntityInsentient entityinsentient) {}
/*      */ 
/*      */   
/*      */   protected EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 1201 */     return EnumInteractionResult.PASS;
/*      */   }
/*      */   
/*      */   public boolean ev() {
/* 1205 */     return a(getChunkCoordinates());
/*      */   }
/*      */   
/*      */   public boolean a(BlockPosition blockposition) {
/* 1209 */     return (this.bA == -1.0F) ? true : ((this.bz.j(blockposition) < (this.bA * this.bA)));
/*      */   }
/*      */   
/*      */   public void a(BlockPosition blockposition, int i) {
/* 1213 */     this.bz = blockposition;
/* 1214 */     this.bA = i;
/*      */   }
/*      */   
/*      */   public BlockPosition ew() {
/* 1218 */     return this.bz;
/*      */   }
/*      */   
/*      */   public float ex() {
/* 1222 */     return this.bA;
/*      */   }
/*      */   
/*      */   public boolean ez() {
/* 1226 */     return (this.bA != -1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public <T extends EntityInsentient> T a(EntityTypes<T> entitytypes, boolean flag) {
/* 1232 */     return a(entitytypes, flag, EntityTransformEvent.TransformReason.UNKNOWN, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public <T extends EntityInsentient> T a(EntityTypes<T> entitytypes, boolean flag, EntityTransformEvent.TransformReason transformReason, CreatureSpawnEvent.SpawnReason spawnReason) {
/* 1238 */     if (this.dead) {
/* 1239 */       return null;
/*      */     }
/* 1241 */     EntityInsentient entityInsentient = (EntityInsentient)entitytypes.a(this.world);
/*      */     
/* 1243 */     entityInsentient.u(this);
/* 1244 */     entityInsentient.setBaby(isBaby());
/* 1245 */     entityInsentient.setNoAI(isNoAI());
/* 1246 */     if (hasCustomName()) {
/* 1247 */       entityInsentient.setCustomName(getCustomName());
/* 1248 */       entityInsentient.setCustomNameVisible(getCustomNameVisible());
/*      */     } 
/*      */     
/* 1251 */     if (isPersistent()) {
/* 1252 */       entityInsentient.setPersistent();
/*      */     }
/*      */     
/* 1255 */     entityInsentient.setInvulnerable(isInvulnerable());
/* 1256 */     if (flag) {
/* 1257 */       entityInsentient.setCanPickupLoot(canPickupLoot());
/* 1258 */       EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/* 1259 */       int i = aenumitemslot.length;
/*      */       
/* 1261 */       for (int j = 0; j < i; j++) {
/* 1262 */         EnumItemSlot enumitemslot = aenumitemslot[j];
/* 1263 */         ItemStack itemstack = getEquipment(enumitemslot);
/*      */         
/* 1265 */         if (!itemstack.isEmpty()) {
/* 1266 */           entityInsentient.setSlot(enumitemslot, itemstack.cloneItemStack());
/* 1267 */           entityInsentient.a(enumitemslot, e(enumitemslot));
/* 1268 */           itemstack.setCount(0);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1274 */     if (CraftEventFactory.callEntityTransformEvent(this, entityInsentient, transformReason).isCancelled()) {
/* 1275 */       return null;
/*      */     }
/* 1277 */     this.world.addEntity(entityInsentient, spawnReason);
/*      */     
/* 1279 */     if (isPassenger()) {
/* 1280 */       Entity entity = getVehicle();
/*      */       
/* 1282 */       stopRiding();
/* 1283 */       entityInsentient.a(entity, true);
/*      */     } 
/*      */     
/* 1286 */     die();
/* 1287 */     return (T)entityInsentient;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void eA() {
/* 1292 */     if (this.by != null) {
/* 1293 */       eI();
/*      */     }
/*      */     
/* 1296 */     if (this.leashHolder != null && (
/* 1297 */       !isAlive() || !this.leashHolder.isAlive())) {
/* 1298 */       this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), !isAlive() ? EntityUnleashEvent.UnleashReason.PLAYER_UNLEASH : EntityUnleashEvent.UnleashReason.HOLDER_GONE));
/* 1299 */       unleash(true, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void unleash(boolean flag, boolean flag1) {
/* 1306 */     if (this.leashHolder != null) {
/* 1307 */       this.attachedToPlayer = false;
/* 1308 */       if (!(this.leashHolder instanceof EntityHuman)) {
/* 1309 */         this.leashHolder.attachedToPlayer = false;
/*      */       }
/*      */       
/* 1312 */       this.leashHolder = null;
/* 1313 */       this.by = null;
/* 1314 */       if (!this.world.isClientSide && flag1) {
/* 1315 */         this.forceDrops = true;
/* 1316 */         a(Items.LEAD);
/* 1317 */         this.forceDrops = false;
/*      */       } 
/*      */       
/* 1320 */       if (!this.world.isClientSide && flag && this.world instanceof WorldServer) {
/* 1321 */         ((WorldServer)this.world).getChunkProvider().broadcast(this, new PacketPlayOutAttachEntity(this, (Entity)null));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(EntityHuman entityhuman) {
/* 1328 */     return (!isLeashed() && !(this instanceof IMonster));
/*      */   }
/*      */   
/*      */   public boolean isLeashed() {
/* 1332 */     return (this.leashHolder != null);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Entity getLeashHolder() {
/* 1337 */     if (this.leashHolder == null && this.bx != 0 && this.world.isClientSide) {
/* 1338 */       this.leashHolder = this.world.getEntity(this.bx);
/*      */     }
/*      */     
/* 1341 */     return this.leashHolder;
/*      */   }
/*      */   
/*      */   public void setLeashHolder(Entity entity, boolean flag) {
/* 1345 */     this.leashHolder = entity;
/* 1346 */     this.by = null;
/* 1347 */     this.attachedToPlayer = true;
/* 1348 */     if (!(this.leashHolder instanceof EntityHuman)) {
/* 1349 */       this.leashHolder.attachedToPlayer = true;
/*      */     }
/*      */     
/* 1352 */     if (!this.world.isClientSide && flag && this.world instanceof WorldServer) {
/* 1353 */       ((WorldServer)this.world).getChunkProvider().broadcast(this, new PacketPlayOutAttachEntity(this, this.leashHolder));
/*      */     }
/*      */     
/* 1356 */     if (isPassenger()) {
/* 1357 */       stopRiding();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean a(Entity entity, boolean flag) {
/* 1364 */     boolean flag1 = super.a(entity, flag);
/*      */     
/* 1366 */     if (flag1 && isLeashed()) {
/* 1367 */       this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.UNKNOWN));
/* 1368 */       unleash(true, true);
/*      */     } 
/*      */     
/* 1371 */     return flag1;
/*      */   }
/*      */   
/*      */   private void eI() {
/* 1375 */     if (this.by != null && this.world instanceof WorldServer) {
/* 1376 */       if (this.by.b("UUID")) {
/* 1377 */         UUID uuid = this.by.a("UUID");
/* 1378 */         Entity entity = ((WorldServer)this.world).getEntity(uuid);
/*      */         
/* 1380 */         if (entity != null) {
/* 1381 */           setLeashHolder(entity, true);
/*      */           return;
/*      */         } 
/* 1384 */       } else if (this.by.hasKeyOfType("X", 99) && this.by.hasKeyOfType("Y", 99) && this.by.hasKeyOfType("Z", 99)) {
/* 1385 */         BlockPosition blockposition = new BlockPosition(this.by.getInt("X"), this.by.getInt("Y"), this.by.getInt("Z"));
/*      */         
/* 1387 */         setLeashHolder(EntityLeash.a(this.world, blockposition), true);
/*      */         
/*      */         return;
/*      */       } 
/* 1391 */       if (this.ticksLived > 100) {
/* 1392 */         a(Items.LEAD);
/* 1393 */         this.by = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean a_(int i, ItemStack itemstack) {
/*      */     EnumItemSlot enumitemslot;
/* 1403 */     if (i == 98) {
/* 1404 */       enumitemslot = EnumItemSlot.MAINHAND;
/* 1405 */     } else if (i == 99) {
/* 1406 */       enumitemslot = EnumItemSlot.OFFHAND;
/* 1407 */     } else if (i == 100 + EnumItemSlot.HEAD.b()) {
/* 1408 */       enumitemslot = EnumItemSlot.HEAD;
/* 1409 */     } else if (i == 100 + EnumItemSlot.CHEST.b()) {
/* 1410 */       enumitemslot = EnumItemSlot.CHEST;
/* 1411 */     } else if (i == 100 + EnumItemSlot.LEGS.b()) {
/* 1412 */       enumitemslot = EnumItemSlot.LEGS;
/*      */     } else {
/* 1414 */       if (i != 100 + EnumItemSlot.FEET.b()) {
/* 1415 */         return false;
/*      */       }
/*      */       
/* 1418 */       enumitemslot = EnumItemSlot.FEET;
/*      */     } 
/*      */     
/* 1421 */     if (!itemstack.isEmpty() && !c(enumitemslot, itemstack) && enumitemslot != EnumItemSlot.HEAD) {
/* 1422 */       return false;
/*      */     }
/* 1424 */     setSlot(enumitemslot, itemstack);
/* 1425 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cr() {
/* 1431 */     return (er() && super.cr());
/*      */   }
/*      */   
/*      */   public static boolean c(EnumItemSlot enumitemslot, ItemStack itemstack) {
/* 1435 */     EnumItemSlot enumitemslot1 = j(itemstack);
/*      */     
/* 1437 */     return (enumitemslot1 == enumitemslot || (enumitemslot1 == EnumItemSlot.MAINHAND && enumitemslot == EnumItemSlot.OFFHAND) || (enumitemslot1 == EnumItemSlot.OFFHAND && enumitemslot == EnumItemSlot.MAINHAND));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean doAITick() {
/* 1442 */     return (super.doAITick() && !isNoAI());
/*      */   }
/*      */   
/*      */   public void setNoAI(boolean flag) {
/* 1446 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*      */     
/* 1448 */     this.datawatcher.set(b, Byte.valueOf(flag ? (byte)(b0 | 0x1) : (byte)(b0 & 0xFFFFFFFE)));
/*      */   }
/*      */   
/*      */   public void setLeftHanded(boolean flag) {
/* 1452 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*      */     
/* 1454 */     this.datawatcher.set(b, Byte.valueOf(flag ? (byte)(b0 | 0x2) : (byte)(b0 & 0xFFFFFFFD)));
/*      */   }
/*      */   
/*      */   public void setAggressive(boolean flag) {
/* 1458 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*      */     
/* 1460 */     this.datawatcher.set(b, Byte.valueOf(flag ? (byte)(b0 | 0x4) : (byte)(b0 & 0xFFFFFFFB)));
/*      */   }
/*      */   
/*      */   public boolean isNoAI() {
/* 1464 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x1) != 0);
/*      */   }
/*      */   
/*      */   public boolean isLeftHanded() {
/* 1468 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x2) != 0);
/*      */   }
/*      */   
/*      */   public boolean isAggressive() {
/* 1472 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x4) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBaby(boolean flag) {}
/*      */   
/*      */   public EnumMainHand getMainHand() {
/* 1479 */     return isLeftHanded() ? EnumMainHand.LEFT : EnumMainHand.RIGHT;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean c(EntityLiving entityliving) {
/* 1484 */     return (entityliving.getEntityType() == EntityTypes.PLAYER && ((EntityHuman)entityliving).abilities.isInvulnerable) ? false : super.c(entityliving);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean attackEntity(Entity entity) {
/* 1489 */     float f = (float)b(GenericAttributes.ATTACK_DAMAGE);
/* 1490 */     float f1 = (float)b(GenericAttributes.ATTACK_KNOCKBACK);
/*      */     
/* 1492 */     if (entity instanceof EntityLiving) {
/* 1493 */       f += EnchantmentManager.a(getItemInMainHand(), ((EntityLiving)entity).getMonsterType());
/* 1494 */       f1 += EnchantmentManager.b(this);
/*      */     } 
/*      */     
/* 1497 */     int i = EnchantmentManager.getFireAspectEnchantmentLevel(this);
/*      */     
/* 1499 */     if (i > 0) {
/*      */       
/* 1501 */       EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), i * 4);
/* 1502 */       Bukkit.getPluginManager().callEvent((Event)combustEvent);
/*      */       
/* 1504 */       if (!combustEvent.isCancelled()) {
/* 1505 */         entity.setOnFire(combustEvent.getDuration(), false);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1510 */     boolean flag = entity.damageEntity(DamageSource.mobAttack(this), f);
/*      */     
/* 1512 */     if (flag) {
/* 1513 */       if (f1 > 0.0F && entity instanceof EntityLiving) {
/* 1514 */         ((EntityLiving)entity).doKnockback(f1 * 0.5F, MathHelper.sin(this.yaw * 0.017453292F), -MathHelper.cos(this.yaw * 0.017453292F), this);
/* 1515 */         setMot(getMot().d(0.6D, 1.0D, 0.6D));
/*      */       } 
/*      */       
/* 1518 */       if (entity instanceof EntityHuman) {
/* 1519 */         EntityHuman entityhuman = (EntityHuman)entity;
/*      */         
/* 1521 */         a(entityhuman, getItemInMainHand(), entityhuman.isHandRaised() ? entityhuman.getActiveItem() : ItemStack.b);
/*      */       } 
/*      */       
/* 1524 */       a(this, entity);
/* 1525 */       z(entity);
/*      */     } 
/*      */     
/* 1528 */     return flag;
/*      */   }
/*      */   
/*      */   private void a(EntityHuman entityhuman, ItemStack itemstack, ItemStack itemstack1) {
/* 1532 */     if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD) {
/* 1533 */       float f = 0.25F + EnchantmentManager.getDigSpeedEnchantmentLevel(this) * 0.05F;
/*      */       
/* 1535 */       if (this.random.nextFloat() < f) {
/* 1536 */         entityhuman.getCooldownTracker().setCooldown(Items.SHIELD, 100);
/* 1537 */         this.world.broadcastEntityEffect(entityhuman, (byte)30);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isInDaylight() {
/* 1543 */     return eG();
/*      */   } protected boolean eG() {
/* 1545 */     if (this.world.isDay() && !this.world.isClientSide) {
/* 1546 */       float f = aQ();
/* 1547 */       BlockPosition blockposition = (getVehicle() instanceof EntityBoat) ? (new BlockPosition(locX(), Math.round(locY()), locZ())).up() : new BlockPosition(locX(), Math.round(locY()), locZ());
/*      */       
/* 1549 */       if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.e(blockposition)) {
/* 1550 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1554 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void c(Tag<FluidType> tag) {
/* 1559 */     if (getNavigation().r()) {
/* 1560 */       super.c(tag);
/*      */     } else {
/* 1562 */       setMot(getMot().add(0.0D, 0.3D, 0.0D));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void bM() {
/* 1569 */     super.bM();
/* 1570 */     this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.UNKNOWN));
/* 1571 */     unleash(true, false);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityInsentient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */