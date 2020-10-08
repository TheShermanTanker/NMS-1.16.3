/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import java.util.Arrays;
/*      */ import java.util.EnumSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.UUID;
/*      */ import java.util.function.Predicate;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*      */ import org.bukkit.entity.ExperienceOrb;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.event.entity.EntityBreedEvent;
/*      */ import org.bukkit.event.entity.EntityDeathEvent;
/*      */ 
/*      */ public class EntityFox extends EntityAnimal {
/*   18 */   private static final DataWatcherObject<Integer> bo = DataWatcher.a((Class)EntityFox.class, DataWatcherRegistry.b);
/*   19 */   private static final DataWatcherObject<Byte> bp = DataWatcher.a((Class)EntityFox.class, DataWatcherRegistry.a);
/*   20 */   public static final DataWatcherObject<Optional<UUID>> FIRST_TRUSTED_PLAYER = DataWatcher.a((Class)EntityFox.class, DataWatcherRegistry.o);
/*   21 */   public static final DataWatcherObject<Optional<UUID>> SECOND_TRUSTED_PLAYER = DataWatcher.a((Class)EntityFox.class, DataWatcherRegistry.o); private static final Predicate<EntityItem> bs; private static final Predicate<Entity> bt; private static final Predicate<Entity> bu; static {
/*   22 */     bs = (entityitem -> 
/*   23 */       (!entityitem.p() && entityitem.isAlive()));
/*      */     
/*   25 */     bt = (entity -> {
/*      */         if (!(entity instanceof EntityLiving)) {
/*      */           return false;
/*      */         }
/*      */         
/*      */         EntityLiving entityliving = (EntityLiving)entity;
/*   31 */         return (entityliving.da() != null && entityliving.db() < entityliving.ticksLived + 600);
/*      */       });
/*      */     
/*   34 */     bu = (entity -> 
/*   35 */       (entity instanceof EntityChicken || entity instanceof EntityRabbit));
/*      */     
/*   37 */     bv = (entity -> 
/*   38 */       (!entity.bw() && IEntitySelector.e.test(entity)));
/*      */   }
/*      */   private static final Predicate<Entity> bv; private PathfinderGoal bw;
/*      */   private PathfinderGoal bx;
/*      */   private PathfinderGoal by;
/*      */   private float bz;
/*      */   private float bA;
/*      */   private float bB;
/*      */   private float bC;
/*      */   private int bD;
/*      */   
/*      */   public EntityFox(EntityTypes<? extends EntityFox> entitytypes, World world) {
/*   50 */     super((EntityTypes)entitytypes, world);
/*   51 */     this.lookController = new k();
/*   52 */     this.moveController = new m();
/*   53 */     a(PathType.DANGER_OTHER, 0.0F);
/*   54 */     a(PathType.DAMAGE_OTHER, 0.0F);
/*   55 */     setCanPickupLoot(true);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initDatawatcher() {
/*   60 */     super.initDatawatcher();
/*   61 */     this.datawatcher.register(FIRST_TRUSTED_PLAYER, Optional.empty());
/*   62 */     this.datawatcher.register(SECOND_TRUSTED_PLAYER, Optional.empty());
/*   63 */     this.datawatcher.register(bo, Integer.valueOf(0));
/*   64 */     this.datawatcher.register(bp, Byte.valueOf((byte)0));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initPathfinder() {
/*   69 */     this.bw = new PathfinderGoalNearestAttackableTarget<>(this, EntityAnimal.class, 10, false, false, entityliving -> 
/*   70 */         (entityliving instanceof EntityChicken || entityliving instanceof EntityRabbit));
/*      */     
/*   72 */     this.bx = new PathfinderGoalNearestAttackableTarget<>(this, EntityTurtle.class, 10, false, false, EntityTurtle.bo);
/*   73 */     this.by = new PathfinderGoalNearestAttackableTarget<>(this, EntityFish.class, 20, false, false, entityliving -> entityliving instanceof EntityFishSchool);
/*      */ 
/*      */     
/*   76 */     this.goalSelector.a(0, new g());
/*   77 */     this.goalSelector.a(1, new b());
/*   78 */     this.goalSelector.a(2, new n(2.2D));
/*   79 */     this.goalSelector.a(3, new e(1.0D));
/*   80 */     this.goalSelector.a(4, new PathfinderGoalAvoidTarget<>(this, EntityHuman.class, 16.0F, 1.6D, 1.4D, entityliving -> 
/*   81 */           (bv.test(entityliving) && !c(entityliving.getUniqueID()) && !fb())));
/*      */     
/*   83 */     this.goalSelector.a(4, new PathfinderGoalAvoidTarget<>(this, EntityWolf.class, 8.0F, 1.6D, 1.4D, entityliving -> 
/*   84 */           (!((EntityWolf)entityliving).isTamed() && !fb())));
/*      */     
/*   86 */     this.goalSelector.a(4, new PathfinderGoalAvoidTarget<>(this, EntityPolarBear.class, 8.0F, 1.6D, 1.4D, entityliving -> !fb()));
/*      */ 
/*      */     
/*   89 */     this.goalSelector.a(5, new u());
/*   90 */     this.goalSelector.a(6, new o());
/*   91 */     this.goalSelector.a(6, new s(1.25D));
/*   92 */     this.goalSelector.a(7, new l(1.2000000476837158D, true));
/*   93 */     this.goalSelector.a(7, new t());
/*   94 */     this.goalSelector.a(8, new h(this, 1.25D));
/*   95 */     this.goalSelector.a(9, new q(32, 200));
/*   96 */     this.goalSelector.a(10, new f(1.2000000476837158D, 12, 2));
/*   97 */     this.goalSelector.a(10, new PathfinderGoalLeapAtTarget(this, 0.4F));
/*   98 */     this.goalSelector.a(11, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*   99 */     this.goalSelector.a(11, new p());
/*  100 */     this.goalSelector.a(12, new j(this, EntityHuman.class, 24.0F));
/*  101 */     this.goalSelector.a(13, new r());
/*  102 */     this.targetSelector.a(3, new a(EntityLiving.class, false, false, entityliving -> 
/*  103 */           (bt.test(entityliving) && !c(entityliving.getUniqueID()))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public SoundEffect d(ItemStack itemstack) {
/*  109 */     return SoundEffects.ENTITY_FOX_EAT;
/*      */   }
/*      */ 
/*      */   
/*      */   public void movementTick() {
/*  114 */     if (!this.world.isClientSide && isAlive() && doAITick()) {
/*  115 */       this.bD++;
/*  116 */       ItemStack itemstack = getEquipment(EnumItemSlot.MAINHAND);
/*      */       
/*  118 */       if (l(itemstack)) {
/*  119 */         if (this.bD > 600) {
/*  120 */           ItemStack itemstack1 = itemstack.a(this.world, this);
/*      */           
/*  122 */           if (!itemstack1.isEmpty()) {
/*  123 */             setSlot(EnumItemSlot.MAINHAND, itemstack1);
/*      */           }
/*      */           
/*  126 */           this.bD = 0;
/*  127 */         } else if (this.bD > 560 && this.random.nextFloat() < 0.1F) {
/*  128 */           playSound(d(itemstack), 1.0F, 1.0F);
/*  129 */           this.world.broadcastEntityEffect(this, (byte)45);
/*      */         } 
/*      */       }
/*      */       
/*  133 */       EntityLiving entityliving = getGoalTarget();
/*      */       
/*  135 */       if (entityliving == null || !entityliving.isAlive()) {
/*  136 */         setCrouching(false);
/*  137 */         w(false);
/*      */       } 
/*      */     } 
/*      */     
/*  141 */     if (isSleeping() || isFrozen()) {
/*  142 */       this.jumping = false;
/*  143 */       this.aR = 0.0F;
/*  144 */       this.aT = 0.0F;
/*      */     } 
/*      */     
/*  147 */     super.movementTick();
/*  148 */     if (fb() && this.random.nextFloat() < 0.05F) {
/*  149 */       playSound(SoundEffects.ENTITY_FOX_AGGRO, 1.0F, 1.0F);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isFrozen() {
/*  156 */     return dk();
/*      */   }
/*      */   
/*      */   private boolean l(ItemStack itemstack) {
/*  160 */     return (itemstack.getItem().isFood() && getGoalTarget() == null && this.onGround && !isSleeping());
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/*  165 */     if (this.random.nextFloat() < 0.2F) {
/*  166 */       ItemStack itemstack; float f = this.random.nextFloat();
/*      */ 
/*      */       
/*  169 */       if (f < 0.05F) {
/*  170 */         itemstack = new ItemStack(Items.EMERALD);
/*  171 */       } else if (f < 0.2F) {
/*  172 */         itemstack = new ItemStack(Items.EGG);
/*  173 */       } else if (f < 0.4F) {
/*  174 */         itemstack = this.random.nextBoolean() ? new ItemStack(Items.RABBIT_FOOT) : new ItemStack(Items.RABBIT_HIDE);
/*  175 */       } else if (f < 0.6F) {
/*  176 */         itemstack = new ItemStack(Items.WHEAT);
/*  177 */       } else if (f < 0.8F) {
/*  178 */         itemstack = new ItemStack(Items.LEATHER);
/*      */       } else {
/*  180 */         itemstack = new ItemStack(Items.FEATHER);
/*      */       } 
/*      */       
/*  183 */       setSlot(EnumItemSlot.MAINHAND, itemstack);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static AttributeProvider.Builder eK() {
/*  189 */     return EntityInsentient.p().a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D).a(GenericAttributes.MAX_HEALTH, 10.0D).a(GenericAttributes.FOLLOW_RANGE, 32.0D).a(GenericAttributes.ATTACK_DAMAGE, 2.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityFox createChild(WorldServer worldserver, EntityAgeable entityageable) {
/*  194 */     EntityFox entityfox = EntityTypes.FOX.a(worldserver);
/*      */     
/*  196 */     entityfox.setFoxType(this.random.nextBoolean() ? getFoxType() : ((EntityFox)entityageable).getFoxType());
/*  197 */     return entityfox;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  203 */     Optional<ResourceKey<BiomeBase>> optional = worldaccess.i(getChunkCoordinates());
/*  204 */     Type entityfox_type = Type.a(optional);
/*  205 */     boolean flag = false;
/*      */     
/*  207 */     if (groupdataentity instanceof i) {
/*  208 */       entityfox_type = ((i)groupdataentity).a;
/*  209 */       if (((i)groupdataentity).a() >= 2) {
/*  210 */         flag = true;
/*      */       }
/*      */     } else {
/*  213 */       groupdataentity = new i(entityfox_type);
/*      */     } 
/*      */     
/*  216 */     setFoxType(entityfox_type);
/*  217 */     if (flag) {
/*  218 */       setAgeRaw(-24000);
/*      */     }
/*      */     
/*  221 */     if (worldaccess instanceof WorldServer) {
/*  222 */       initializePathFinderGoals();
/*      */     }
/*      */     
/*  225 */     a(difficultydamagescaler);
/*  226 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*      */   }
/*      */   
/*      */   private void initializePathFinderGoals() {
/*  230 */     if (getFoxType() == Type.RED) {
/*  231 */       this.targetSelector.a(4, this.bw);
/*  232 */       this.targetSelector.a(4, this.bx);
/*  233 */       this.targetSelector.a(6, this.by);
/*      */     } else {
/*  235 */       this.targetSelector.a(4, this.by);
/*  236 */       this.targetSelector.a(6, this.bw);
/*  237 */       this.targetSelector.a(6, this.bx);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void a(EntityHuman entityhuman, ItemStack itemstack) {
/*  244 */     if (k(itemstack)) {
/*  245 */       playSound(d(itemstack), 1.0F, 1.0F);
/*      */     }
/*      */     
/*  248 */     super.a(entityhuman, itemstack);
/*      */   }
/*      */ 
/*      */   
/*      */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  253 */     return isBaby() ? (entitysize.height * 0.85F) : 0.4F;
/*      */   }
/*      */   
/*      */   public Type getFoxType() {
/*  257 */     return Type.a(((Integer)this.datawatcher.<Integer>get(bo)).intValue());
/*      */   }
/*      */   
/*      */   public void setFoxType(Type entityfox_type) {
/*  261 */     this.datawatcher.set(bo, Integer.valueOf(entityfox_type.b()));
/*      */   }
/*      */   
/*      */   private List<UUID> fa() {
/*  265 */     List<UUID> list = Lists.newArrayList();
/*      */     
/*  267 */     list.add(((Optional<UUID>)this.datawatcher.<Optional<UUID>>get(FIRST_TRUSTED_PLAYER)).orElse(null));
/*  268 */     list.add(((Optional<UUID>)this.datawatcher.<Optional<UUID>>get(SECOND_TRUSTED_PLAYER)).orElse(null));
/*  269 */     return list;
/*      */   }
/*      */   
/*      */   private void b(@Nullable UUID uuid) {
/*  273 */     if (((Optional)this.datawatcher.<Optional>get((DataWatcherObject)FIRST_TRUSTED_PLAYER)).isPresent()) {
/*  274 */       this.datawatcher.set(SECOND_TRUSTED_PLAYER, Optional.ofNullable(uuid));
/*      */     } else {
/*  276 */       this.datawatcher.set(FIRST_TRUSTED_PLAYER, Optional.ofNullable(uuid));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveData(NBTTagCompound nbttagcompound) {
/*  283 */     super.saveData(nbttagcompound);
/*  284 */     List<UUID> list = fa();
/*  285 */     NBTTagList nbttaglist = new NBTTagList();
/*  286 */     Iterator<UUID> iterator = list.iterator();
/*      */     
/*  288 */     while (iterator.hasNext()) {
/*  289 */       UUID uuid = iterator.next();
/*      */       
/*  291 */       if (uuid != null) {
/*  292 */         nbttaglist.add(GameProfileSerializer.a(uuid));
/*      */       }
/*      */     } 
/*      */     
/*  296 */     nbttagcompound.set("Trusted", nbttaglist);
/*  297 */     nbttagcompound.setBoolean("Sleeping", isSleeping());
/*  298 */     nbttagcompound.setString("Type", getFoxType().a());
/*  299 */     nbttagcompound.setBoolean("Sitting", isSitting());
/*  300 */     nbttagcompound.setBoolean("Crouching", isCrouching());
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadData(NBTTagCompound nbttagcompound) {
/*  305 */     super.loadData(nbttagcompound);
/*  306 */     NBTTagList nbttaglist = nbttagcompound.getList("Trusted", 11);
/*      */     
/*  308 */     for (int i = 0; i < nbttaglist.size(); i++) {
/*  309 */       b(GameProfileSerializer.a(nbttaglist.get(i)));
/*      */     }
/*      */     
/*  312 */     setSleeping(nbttagcompound.getBoolean("Sleeping"));
/*  313 */     setFoxType(Type.a(nbttagcompound.getString("Type")));
/*  314 */     setSitting(nbttagcompound.getBoolean("Sitting"));
/*  315 */     setCrouching(nbttagcompound.getBoolean("Crouching"));
/*  316 */     if (this.world instanceof WorldServer) {
/*  317 */       initializePathFinderGoals();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSitting() {
/*  323 */     return t(1);
/*      */   }
/*      */   
/*      */   public void setSitting(boolean flag) {
/*  327 */     d(1, flag);
/*      */   }
/*      */   
/*      */   public boolean eN() {
/*  331 */     return t(64);
/*      */   }
/*      */   
/*      */   private void x(boolean flag) {
/*  335 */     d(64, flag);
/*      */   }
/*      */   
/*      */   private boolean fb() {
/*  339 */     return t(128);
/*      */   }
/*      */   
/*      */   private void y(boolean flag) {
/*  343 */     d(128, flag);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSleeping() {
/*  348 */     return t(32);
/*      */   }
/*      */   
/*      */   public void setSleeping(boolean flag) {
/*  352 */     d(32, flag);
/*      */   }
/*      */   
/*      */   private void d(int i, boolean flag) {
/*  356 */     if (flag) {
/*  357 */       this.datawatcher.set(bp, Byte.valueOf((byte)(((Byte)this.datawatcher.<Byte>get(bp)).byteValue() | i)));
/*      */     } else {
/*  359 */       this.datawatcher.set(bp, Byte.valueOf((byte)(((Byte)this.datawatcher.<Byte>get(bp)).byteValue() & (i ^ 0xFFFFFFFF))));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean t(int i) {
/*  365 */     return ((((Byte)this.datawatcher.<Byte>get(bp)).byteValue() & i) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean e(ItemStack itemstack) {
/*  370 */     EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/*      */     
/*  372 */     return !getEquipment(enumitemslot).isEmpty() ? false : ((enumitemslot == EnumItemSlot.MAINHAND && super.e(itemstack)));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canPickup(ItemStack itemstack) {
/*  377 */     Item item = itemstack.getItem();
/*  378 */     ItemStack itemstack1 = getEquipment(EnumItemSlot.MAINHAND);
/*      */     
/*  380 */     return (itemstack1.isEmpty() || (this.bD > 0 && item.isFood() && !itemstack1.getItem().isFood()));
/*      */   }
/*      */   
/*      */   private void m(ItemStack itemstack) {
/*  384 */     if (!itemstack.isEmpty() && !this.world.isClientSide) {
/*  385 */       EntityItem entityitem = new EntityItem(this.world, locX() + (getLookDirection()).x, locY() + 1.0D, locZ() + (getLookDirection()).z, itemstack);
/*      */       
/*  387 */       entityitem.setPickupDelay(40);
/*  388 */       entityitem.setThrower(getUniqueID());
/*  389 */       playSound(SoundEffects.ENTITY_FOX_SPIT, 1.0F, 1.0F);
/*  390 */       this.world.addEntity(entityitem);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void n(ItemStack itemstack) {
/*  395 */     EntityItem entityitem = new EntityItem(this.world, locX(), locY(), locZ(), itemstack);
/*      */     
/*  397 */     this.world.addEntity(entityitem);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(EntityItem entityitem) {
/*  402 */     ItemStack itemstack = entityitem.getItemStack();
/*      */     
/*  404 */     if (!CraftEventFactory.callEntityPickupItemEvent(this, entityitem, itemstack.getCount() - 1, !canPickup(itemstack)).isCancelled()) {
/*  405 */       itemstack = entityitem.getItemStack();
/*  406 */       int i = itemstack.getCount();
/*      */       
/*  408 */       if (i > 1) {
/*  409 */         n(itemstack.cloneAndSubtract(i - 1));
/*      */       }
/*      */       
/*  412 */       m(getEquipment(EnumItemSlot.MAINHAND));
/*  413 */       a(entityitem);
/*  414 */       setSlot(EnumItemSlot.MAINHAND, itemstack.cloneAndSubtract(1));
/*  415 */       this.dropChanceHand[EnumItemSlot.MAINHAND.b()] = 2.0F;
/*  416 */       receive(entityitem, itemstack.getCount());
/*  417 */       entityitem.die();
/*  418 */       this.bD = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {
/*  425 */     super.tick();
/*  426 */     if (doAITick()) {
/*  427 */       boolean flag = isInWater();
/*      */       
/*  429 */       if (flag || getGoalTarget() != null || this.world.V()) {
/*  430 */         fc();
/*      */       }
/*      */       
/*  433 */       if (flag || isSleeping()) {
/*  434 */         setSitting(false);
/*      */       }
/*      */       
/*  437 */       if (eN() && this.world.random.nextFloat() < 0.2F) {
/*  438 */         BlockPosition blockposition = getChunkCoordinates();
/*  439 */         IBlockData iblockdata = this.world.getType(blockposition);
/*      */         
/*  441 */         this.world.triggerEffect(2001, blockposition, Block.getCombinedId(iblockdata));
/*      */       } 
/*      */     } 
/*      */     
/*  445 */     this.bA = this.bz;
/*  446 */     if (eW()) {
/*  447 */       this.bz += (1.0F - this.bz) * 0.4F;
/*      */     } else {
/*  449 */       this.bz += (0.0F - this.bz) * 0.4F;
/*      */     } 
/*      */     
/*  452 */     this.bC = this.bB;
/*  453 */     if (isCrouching()) {
/*  454 */       this.bB += 0.2F;
/*  455 */       if (this.bB > 3.0F) {
/*  456 */         this.bB = 3.0F;
/*      */       }
/*      */     } else {
/*  459 */       this.bB = 0.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean k(ItemStack itemstack) {
/*  466 */     return (itemstack.getItem() == Items.SWEET_BERRIES);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(EntityHuman entityhuman, EntityInsentient entityinsentient) {
/*  471 */     ((EntityFox)entityinsentient).b(entityhuman.getUniqueID());
/*      */   }
/*      */   
/*      */   public boolean eO() {
/*  475 */     return t(16);
/*      */   }
/*      */   
/*      */   public void u(boolean flag) {
/*  479 */     d(16, flag);
/*      */   }
/*      */   
/*      */   public boolean eV() {
/*  483 */     return (this.bB == 3.0F);
/*      */   }
/*      */   
/*      */   public void setCrouching(boolean flag) {
/*  487 */     d(4, flag);
/*      */   }
/*      */   
/*      */   public boolean isCrouching() {
/*  491 */     return t(4);
/*      */   }
/*      */   
/*      */   public void w(boolean flag) {
/*  495 */     d(8, flag);
/*      */   }
/*      */   
/*      */   public boolean eW() {
/*  499 */     return t(8);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGoalTarget(@Nullable EntityLiving entityliving) {
/*  504 */     if (fb() && entityliving == null) {
/*  505 */       y(false);
/*      */     }
/*      */     
/*  508 */     super.setGoalTarget(entityliving);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int e(float f, float f1) {
/*  513 */     return MathHelper.f((f - 5.0F) * f1);
/*      */   }
/*      */   
/*      */   private void fc() {
/*  517 */     setSleeping(false);
/*      */   }
/*      */   
/*      */   private void fd() {
/*  521 */     w(false);
/*  522 */     setCrouching(false);
/*  523 */     setSitting(false);
/*  524 */     setSleeping(false);
/*  525 */     y(false);
/*  526 */     x(false);
/*      */   }
/*      */   
/*      */   private boolean fe() {
/*  530 */     return (!isSleeping() && !isSitting() && !eN());
/*      */   }
/*      */ 
/*      */   
/*      */   public void F() {
/*  535 */     SoundEffect soundeffect = getSoundAmbient();
/*      */     
/*  537 */     if (soundeffect == SoundEffects.ENTITY_FOX_SCREECH) {
/*  538 */       playSound(soundeffect, 2.0F, dG());
/*      */     } else {
/*  540 */       super.F();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect getSoundAmbient() {
/*  548 */     if (isSleeping()) {
/*  549 */       return SoundEffects.ENTITY_FOX_SLEEP;
/*      */     }
/*  551 */     if (!this.world.isDay() && this.random.nextFloat() < 0.1F) {
/*  552 */       List<EntityHuman> list = (List)this.world.a((Class)EntityHuman.class, getBoundingBox().grow(16.0D, 16.0D, 16.0D), IEntitySelector.g);
/*      */       
/*  554 */       if (list.isEmpty()) {
/*  555 */         return SoundEffects.ENTITY_FOX_SCREECH;
/*      */       }
/*      */     } 
/*      */     
/*  559 */     return SoundEffects.ENTITY_FOX_AMBIENT;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  566 */     return SoundEffects.ENTITY_FOX_HURT;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect getSoundDeath() {
/*  572 */     return SoundEffects.ENTITY_FOX_DEATH;
/*      */   }
/*      */   
/*      */   private boolean c(UUID uuid) {
/*  576 */     return fa().contains(uuid);
/*      */   }
/*      */ 
/*      */   
/*      */   protected EntityDeathEvent d(DamageSource damagesource) {
/*  581 */     ItemStack itemstack = getEquipment(EnumItemSlot.MAINHAND).cloneItemStack();
/*      */ 
/*      */     
/*  584 */     EntityDeathEvent deathEvent = super.d(damagesource);
/*      */ 
/*      */ 
/*      */     
/*  588 */     if (deathEvent == null || deathEvent.isCancelled()) {
/*  589 */       return deathEvent;
/*      */     }
/*      */ 
/*      */     
/*  593 */     if (!itemstack.isEmpty()) {
/*  594 */       a(itemstack);
/*  595 */       setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/*      */     } 
/*      */     
/*  598 */     return deathEvent;
/*      */   }
/*      */   
/*      */   public static boolean a(EntityFox entityfox, EntityLiving entityliving) {
/*  602 */     double d0 = entityliving.locZ() - entityfox.locZ();
/*  603 */     double d1 = entityliving.locX() - entityfox.locX();
/*  604 */     double d2 = d0 / d1;
/*  605 */     boolean flag = true;
/*      */     
/*  607 */     for (int i = 0; i < 6; i++) {
/*  608 */       double d3 = (d2 == 0.0D) ? 0.0D : (d0 * (i / 6.0F));
/*  609 */       double d4 = (d2 == 0.0D) ? (d1 * (i / 6.0F)) : (d3 / d2);
/*      */       
/*  611 */       for (int j = 1; j < 4; j++) {
/*  612 */         if (!entityfox.world.getType(new BlockPosition(entityfox.locX() + d4, entityfox.locY() + j, entityfox.locZ() + d3)).getMaterial().isReplaceable()) {
/*  613 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  618 */     return true;
/*      */   }
/*      */   
/*      */   class j
/*      */     extends PathfinderGoalLookAtPlayer {
/*      */     public j(EntityInsentient entityinsentient, Class<? extends EntityLiving> oclass, float f) {
/*  624 */       super(entityinsentient, oclass, f);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  629 */       return (super.a() && !EntityFox.this.eN() && !EntityFox.this.eW());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  634 */       return (super.b() && !EntityFox.this.eN() && !EntityFox.this.eW());
/*      */     }
/*      */   }
/*      */   
/*      */   class h
/*      */     extends PathfinderGoalFollowParent {
/*      */     private final EntityFox b;
/*      */     
/*      */     public h(EntityFox entityfox, double d0) {
/*  643 */       super(entityfox, d0);
/*  644 */       this.b = entityfox;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  649 */       return (!this.b.fb() && super.a());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  654 */       return (!this.b.fb() && super.b());
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  659 */       this.b.fd();
/*  660 */       super.c();
/*      */     }
/*      */   }
/*      */   
/*      */   public class k
/*      */     extends ControllerLook {
/*      */     public k() {
/*  667 */       super(EntityFox.this);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a() {
/*  672 */       if (!EntityFox.this.isSleeping()) {
/*  673 */         super.a();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean b() {
/*  680 */       if (!EntityFox.this.eO() && !EntityFox.this.isCrouching()) if (((!EntityFox.this.eW() ? 1 : 0) & (!EntityFox.this.eN() ? 1 : 0)) != 0);  return false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class o
/*      */     extends PathfinderGoalWaterJumpAbstract
/*      */   {
/*      */     public boolean a() {
/*  690 */       if (!EntityFox.this.eV()) {
/*  691 */         return false;
/*      */       }
/*  693 */       EntityLiving entityliving = EntityFox.this.getGoalTarget();
/*      */       
/*  695 */       if (entityliving != null && entityliving.isAlive()) {
/*  696 */         if (entityliving.getAdjustedDirection() != entityliving.getDirection()) {
/*  697 */           return false;
/*      */         }
/*  699 */         boolean flag = EntityFox.a(EntityFox.this, entityliving);
/*      */         
/*  701 */         if (!flag) {
/*  702 */           EntityFox.this.getNavigation().a(entityliving, 0);
/*  703 */           EntityFox.this.setCrouching(false);
/*  704 */           EntityFox.this.w(false);
/*      */         } 
/*      */         
/*  707 */         return flag;
/*      */       } 
/*      */       
/*  710 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  717 */       EntityLiving entityliving = EntityFox.this.getGoalTarget();
/*      */       
/*  719 */       if (entityliving != null && entityliving.isAlive()) {
/*  720 */         double d0 = (EntityFox.this.getMot()).y;
/*      */         
/*  722 */         return ((d0 * d0 >= 0.05000000074505806D || Math.abs(EntityFox.this.pitch) >= 15.0F || !EntityFox.this.onGround) && !EntityFox.this.eN());
/*      */       } 
/*  724 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean C_() {
/*  730 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  735 */       EntityFox.this.setJumping(true);
/*  736 */       EntityFox.this.u(true);
/*  737 */       EntityFox.this.w(false);
/*  738 */       EntityLiving entityliving = EntityFox.this.getGoalTarget();
/*      */       
/*  740 */       EntityFox.this.getControllerLook().a(entityliving, 60.0F, 30.0F);
/*  741 */       Vec3D vec3d = (new Vec3D(entityliving.locX() - EntityFox.this.locX(), entityliving.locY() - EntityFox.this.locY(), entityliving.locZ() - EntityFox.this.locZ())).d();
/*      */       
/*  743 */       EntityFox.this.setMot(EntityFox.this.getMot().add(vec3d.x * 0.8D, 0.9D, vec3d.z * 0.8D));
/*  744 */       EntityFox.this.getNavigation().o();
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/*  749 */       EntityFox.this.setCrouching(false);
/*  750 */       EntityFox.this.bB = 0.0F;
/*  751 */       EntityFox.this.bC = 0.0F;
/*  752 */       EntityFox.this.w(false);
/*  753 */       EntityFox.this.u(false);
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/*  758 */       EntityLiving entityliving = EntityFox.this.getGoalTarget();
/*      */       
/*  760 */       if (entityliving != null) {
/*  761 */         EntityFox.this.getControllerLook().a(entityliving, 60.0F, 30.0F);
/*      */       }
/*      */       
/*  764 */       if (!EntityFox.this.eN()) {
/*  765 */         Vec3D vec3d = EntityFox.this.getMot();
/*      */         
/*  767 */         if (vec3d.y * vec3d.y < 0.029999999329447746D && EntityFox.this.pitch != 0.0F) {
/*  768 */           EntityFox.this.pitch = MathHelper.j(EntityFox.this.pitch, 0.0F, 0.2F);
/*      */         } else {
/*  770 */           double d0 = Math.sqrt(Entity.c(vec3d));
/*  771 */           double d1 = Math.signum(-vec3d.y) * Math.acos(d0 / vec3d.f()) * 57.2957763671875D;
/*      */           
/*  773 */           EntityFox.this.pitch = (float)d1;
/*      */         } 
/*      */       } 
/*      */       
/*  777 */       if (entityliving != null && EntityFox.this.g(entityliving) <= 2.0F) {
/*  778 */         EntityFox.this.attackEntity(entityliving);
/*  779 */       } else if (EntityFox.this.pitch > 0.0F && EntityFox.this.onGround && (float)(EntityFox.this.getMot()).y != 0.0F && EntityFox.this.world.getType(EntityFox.this.getChunkCoordinates()).a(Blocks.SNOW)) {
/*  780 */         EntityFox.this.pitch = 60.0F;
/*  781 */         EntityFox.this.setGoalTarget((EntityLiving)null);
/*  782 */         EntityFox.this.x(true);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   class g
/*      */     extends PathfinderGoalFloat
/*      */   {
/*      */     public g() {
/*  791 */       super(EntityFox.this);
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  796 */       super.c();
/*  797 */       EntityFox.this.fd();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  802 */       return ((EntityFox.this.isInWater() && EntityFox.this.b(TagsFluid.WATER) > 0.25D) || EntityFox.this.aP());
/*      */     }
/*      */   }
/*      */   
/*      */   class q
/*      */     extends PathfinderGoalNearestVillage {
/*      */     public q(int i, int j) {
/*  809 */       super(EntityFox.this, j);
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  814 */       EntityFox.this.fd();
/*  815 */       super.c();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  820 */       return (super.a() && g());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  825 */       return (super.b() && g());
/*      */     }
/*      */     
/*      */     private boolean g() {
/*  829 */       return (!EntityFox.this.isSleeping() && !EntityFox.this.isSitting() && !EntityFox.this.fb() && EntityFox.this.getGoalTarget() == null);
/*      */     }
/*      */   }
/*      */   
/*      */   class n
/*      */     extends PathfinderGoalPanic {
/*      */     public n(double d0) {
/*  836 */       super(EntityFox.this, d0);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  841 */       return (!EntityFox.this.fb() && super.a());
/*      */     }
/*      */   }
/*      */   
/*      */   class b
/*      */     extends PathfinderGoal {
/*      */     int a;
/*      */     
/*      */     public b() {
/*  850 */       a(EnumSet.of(PathfinderGoal.Type.LOOK, PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  855 */       return EntityFox.this.eN();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  860 */       return (a() && this.a > 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  865 */       this.a = 40;
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/*  870 */       EntityFox.this.x(false);
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/*  875 */       this.a--;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class i
/*      */     extends EntityAgeable.a {
/*      */     public final EntityFox.Type a;
/*      */     
/*      */     public i(EntityFox.Type entityfox_type) {
/*  884 */       super(false);
/*  885 */       this.a = entityfox_type;
/*      */     }
/*      */   }
/*      */   
/*      */   public class f
/*      */     extends PathfinderGoalGotoTarget {
/*      */     protected int g;
/*      */     
/*      */     public f(double d0, int i, int j) {
/*  894 */       super(EntityFox.this, d0, i, j);
/*      */     }
/*      */ 
/*      */     
/*      */     public double h() {
/*  899 */       return 2.0D;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean k() {
/*  904 */       return (this.d % 100 == 0);
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/*  909 */       IBlockData iblockdata = iworldreader.getType(blockposition);
/*      */       
/*  911 */       return (iblockdata.a(Blocks.SWEET_BERRY_BUSH) && ((Integer)iblockdata.get(BlockSweetBerryBush.a)).intValue() >= 2);
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/*  916 */       if (l()) {
/*  917 */         if (this.g >= 40) {
/*  918 */           n();
/*      */         } else {
/*  920 */           this.g++;
/*      */         } 
/*  922 */       } else if (!l() && EntityFox.this.random.nextFloat() < 0.05F) {
/*  923 */         EntityFox.this.playSound(SoundEffects.ENTITY_FOX_SNIFF, 1.0F, 1.0F);
/*      */       } 
/*      */       
/*  926 */       super.e();
/*      */     }
/*      */     
/*      */     protected void n() {
/*  930 */       if (EntityFox.this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
/*  931 */         IBlockData iblockdata = EntityFox.this.world.getType(this.e);
/*      */         
/*  933 */         if (iblockdata.a(Blocks.SWEET_BERRY_BUSH)) {
/*  934 */           int i = ((Integer)iblockdata.get(BlockSweetBerryBush.a)).intValue();
/*      */           
/*  936 */           iblockdata.set(BlockSweetBerryBush.a, Integer.valueOf(1));
/*      */           
/*  938 */           if (CraftEventFactory.callEntityChangeBlockEvent(EntityFox.this, this.e, iblockdata.set(BlockSweetBerryBush.a, Integer.valueOf(1))).isCancelled()) {
/*      */             return;
/*      */           }
/*      */           
/*  942 */           int j = 1 + EntityFox.this.world.random.nextInt(2) + ((i == 3) ? 1 : 0);
/*  943 */           ItemStack itemstack = EntityFox.this.getEquipment(EnumItemSlot.MAINHAND);
/*      */           
/*  945 */           if (itemstack.isEmpty()) {
/*  946 */             EntityFox.this.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
/*  947 */             j--;
/*      */           } 
/*      */           
/*  950 */           if (j > 0) {
/*  951 */             Block.a(EntityFox.this.world, this.e, new ItemStack(Items.SWEET_BERRIES, j));
/*      */           }
/*      */           
/*  954 */           EntityFox.this.playSound(SoundEffects.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1.0F, 1.0F);
/*  955 */           EntityFox.this.world.setTypeAndData(this.e, iblockdata.set(BlockSweetBerryBush.a, Integer.valueOf(1)), 2);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  962 */       return (!EntityFox.this.isSleeping() && super.a());
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  967 */       this.g = 0;
/*  968 */       EntityFox.this.setSitting(false);
/*  969 */       super.c();
/*      */     }
/*      */   }
/*      */   
/*      */   class r
/*      */     extends d
/*      */   {
/*      */     private double c;
/*      */     private double d;
/*      */     private int e;
/*      */     private int f;
/*      */     
/*      */     public r() {
/*  982 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  987 */       return (EntityFox.this.getLastDamager() == null && EntityFox.this.getRandom().nextFloat() < 0.02F && !EntityFox.this.isSleeping() && EntityFox.this.getGoalTarget() == null && EntityFox.this.getNavigation().m() && !h() && !EntityFox.this.eO() && !EntityFox.this.isCrouching());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  992 */       return (this.f > 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  997 */       j();
/*  998 */       this.f = 2 + EntityFox.this.getRandom().nextInt(3);
/*  999 */       EntityFox.this.setSitting(true);
/* 1000 */       EntityFox.this.getNavigation().o();
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/* 1005 */       EntityFox.this.setSitting(false);
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/* 1010 */       this.e--;
/* 1011 */       if (this.e <= 0) {
/* 1012 */         this.f--;
/* 1013 */         j();
/*      */       } 
/*      */       
/* 1016 */       EntityFox.this.getControllerLook().a(EntityFox.this.locX() + this.c, EntityFox.this.getHeadY(), EntityFox.this.locZ() + this.d, EntityFox.this.eo(), EntityFox.this.O());
/*      */     }
/*      */     
/*      */     private void j() {
/* 1020 */       double d0 = 6.283185307179586D * EntityFox.this.getRandom().nextDouble();
/*      */       
/* 1022 */       this.c = Math.cos(d0);
/* 1023 */       this.d = Math.sin(d0);
/* 1024 */       this.e = 80 + EntityFox.this.getRandom().nextInt(20);
/*      */     }
/*      */   }
/*      */   
/*      */   class t
/*      */     extends d
/*      */   {
/*      */     private int c;
/*      */     
/*      */     public t() {
/* 1034 */       this.c = EntityFox.this.random.nextInt(140);
/* 1035 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK, PathfinderGoal.Type.JUMP));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1040 */       return (EntityFox.this.aR == 0.0F && EntityFox.this.aS == 0.0F && EntityFox.this.aT == 0.0F) ? ((j() || EntityFox.this.isSleeping())) : false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/* 1045 */       return j();
/*      */     }
/*      */     
/*      */     private boolean j() {
/* 1049 */       if (this.c > 0) {
/* 1050 */         this.c--;
/* 1051 */         return false;
/*      */       } 
/* 1053 */       return (EntityFox.this.world.isDay() && g() && !h());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void d() {
/* 1059 */       this.c = EntityFox.this.random.nextInt(140);
/* 1060 */       EntityFox.this.fd();
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/* 1065 */       EntityFox.this.setSitting(false);
/* 1066 */       EntityFox.this.setCrouching(false);
/* 1067 */       EntityFox.this.w(false);
/* 1068 */       EntityFox.this.setJumping(false);
/* 1069 */       EntityFox.this.setSleeping(true);
/* 1070 */       EntityFox.this.getNavigation().o();
/* 1071 */       EntityFox.this.getControllerMove().a(EntityFox.this.locX(), EntityFox.this.locY(), EntityFox.this.locZ(), 0.0D);
/*      */     }
/*      */   }
/*      */   
/*      */   abstract class d
/*      */     extends PathfinderGoal {
/*      */     private final PathfinderTargetCondition b;
/*      */     
/*      */     private d() {
/* 1080 */       Objects.requireNonNull(EntityFox.this); this.b = (new PathfinderTargetCondition()).a(12.0D).c().a(new EntityFox.c());
/*      */     }
/*      */     
/*      */     protected boolean g() {
/* 1084 */       BlockPosition blockposition = new BlockPosition(EntityFox.this.locX(), (EntityFox.this.getBoundingBox()).maxY, EntityFox.this.locZ());
/*      */       
/* 1086 */       return (!EntityFox.this.world.e(blockposition) && EntityFox.this.f(blockposition) >= 0.0F);
/*      */     }
/*      */     
/*      */     protected boolean h() {
/* 1090 */       return !EntityFox.this.world.<EntityLiving>a(EntityLiving.class, this.b, EntityFox.this, EntityFox.this.getBoundingBox().grow(12.0D, 6.0D, 12.0D)).isEmpty();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class c
/*      */     implements Predicate<EntityLiving>
/*      */   {
/*      */     public boolean test(EntityLiving entityliving) {
/* 1099 */       return (entityliving instanceof EntityFox) ? false : ((!(entityliving instanceof EntityChicken) && !(entityliving instanceof EntityRabbit) && !(entityliving instanceof EntityMonster)) ? ((entityliving instanceof EntityTameableAnimal) ? (!((EntityTameableAnimal)entityliving).isTamed()) : ((entityliving instanceof EntityHuman && (entityliving.isSpectator() || ((EntityHuman)entityliving).isCreative())) ? false : (EntityFox.this.c(entityliving.getUniqueID()) ? false : ((!entityliving.isSleeping() && !entityliving.bw()))))) : true);
/*      */     }
/*      */   }
/*      */   
/*      */   class s
/*      */     extends PathfinderGoalFleeSun {
/* 1105 */     private int c = 100;
/*      */     
/*      */     public s(double d0) {
/* 1108 */       super(EntityFox.this, d0);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1113 */       if (!EntityFox.this.isSleeping() && this.a.getGoalTarget() == null) {
/* 1114 */         if (EntityFox.this.world.V())
/* 1115 */           return true; 
/* 1116 */         if (this.c > 0) {
/* 1117 */           this.c--;
/* 1118 */           return false;
/*      */         } 
/* 1120 */         this.c = 100;
/* 1121 */         BlockPosition blockposition = this.a.getChunkCoordinates();
/*      */         
/* 1123 */         return (EntityFox.this.world.isDay() && EntityFox.this.world.e(blockposition) && !((WorldServer)EntityFox.this.world).a_(blockposition) && g());
/*      */       } 
/*      */       
/* 1126 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void c() {
/* 1132 */       EntityFox.this.fd();
/* 1133 */       super.c();
/*      */     }
/*      */   }
/*      */   
/*      */   class a
/*      */     extends PathfinderGoalNearestAttackableTarget<EntityLiving> {
/*      */     @Nullable
/*      */     private EntityLiving j;
/*      */     private EntityLiving k;
/*      */     private int l;
/*      */     
/*      */     public a(Class<EntityLiving> oclass, boolean flag, boolean flag1, Predicate<EntityLiving> predicate) {
/* 1145 */       super(EntityFox.this, oclass, 10, flag, flag1, predicate);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1150 */       if (this.b > 0 && this.e.getRandom().nextInt(this.b) != 0) {
/* 1151 */         return false;
/*      */       }
/* 1153 */       Iterator<UUID> iterator = EntityFox.this.fa().iterator();
/*      */       
/* 1155 */       while (iterator.hasNext()) {
/* 1156 */         UUID uuid = iterator.next();
/*      */         
/* 1158 */         if (uuid != null && EntityFox.this.world instanceof WorldServer) {
/* 1159 */           Entity entity = ((WorldServer)EntityFox.this.world).getEntity(uuid);
/*      */           
/* 1161 */           if (entity instanceof EntityLiving) {
/* 1162 */             EntityLiving entityliving = (EntityLiving)entity;
/*      */             
/* 1164 */             this.k = entityliving;
/* 1165 */             this.j = entityliving.getLastDamager();
/* 1166 */             int i = entityliving.cZ();
/*      */             
/* 1168 */             return (i != this.l && a(this.j, this.d));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1173 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void c() {
/* 1179 */       a(this.j);
/* 1180 */       this.c = this.j;
/* 1181 */       if (this.k != null) {
/* 1182 */         this.l = this.k.cZ();
/*      */       }
/*      */       
/* 1185 */       EntityFox.this.playSound(SoundEffects.ENTITY_FOX_AGGRO, 1.0F, 1.0F);
/* 1186 */       EntityFox.this.y(true);
/* 1187 */       EntityFox.this.fc();
/* 1188 */       super.c();
/*      */     }
/*      */   }
/*      */   
/*      */   class e
/*      */     extends PathfinderGoalBreed {
/*      */     public e(double d0) {
/* 1195 */       super(EntityFox.this, d0);
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/* 1200 */       ((EntityFox)this.animal).fd();
/* 1201 */       ((EntityFox)this.partner).fd();
/* 1202 */       super.c();
/*      */     }
/*      */ 
/*      */     
/*      */     protected void g() {
/* 1207 */       WorldServer worldserver = (WorldServer)this.b;
/* 1208 */       EntityFox entityfox = (EntityFox)this.animal.createChild(worldserver, this.partner);
/*      */       
/* 1210 */       if (entityfox != null) {
/* 1211 */         EntityPlayer entityplayer = this.animal.getBreedCause();
/* 1212 */         EntityPlayer entityplayer1 = this.partner.getBreedCause();
/* 1213 */         EntityPlayer entityplayer2 = entityplayer;
/*      */         
/* 1215 */         if (entityplayer != null) {
/* 1216 */           entityfox.b(entityplayer.getUniqueID());
/*      */         } else {
/* 1218 */           entityplayer2 = entityplayer1;
/*      */         } 
/*      */         
/* 1221 */         if (entityplayer1 != null && entityplayer != entityplayer1) {
/* 1222 */           entityfox.b(entityplayer1.getUniqueID());
/*      */         }
/*      */         
/* 1225 */         int experience = this.animal.getRandom().nextInt(7) + 1;
/* 1226 */         EntityBreedEvent entityBreedEvent = CraftEventFactory.callEntityBreedEvent(entityfox, this.animal, this.partner, entityplayer, this.animal.breedItem, experience);
/* 1227 */         if (entityBreedEvent.isCancelled()) {
/*      */           return;
/*      */         }
/* 1230 */         experience = entityBreedEvent.getExperience();
/*      */ 
/*      */         
/* 1233 */         if (entityplayer2 != null) {
/* 1234 */           entityplayer2.a(StatisticList.ANIMALS_BRED);
/* 1235 */           CriterionTriggers.o.a(entityplayer2, this.animal, this.partner, entityfox);
/*      */         } 
/*      */         
/* 1238 */         this.animal.setAgeRaw(6000);
/* 1239 */         this.partner.setAgeRaw(6000);
/* 1240 */         this.animal.resetLove();
/* 1241 */         this.partner.resetLove();
/* 1242 */         entityfox.setAgeRaw(-24000);
/* 1243 */         entityfox.setPositionRotation(this.animal.locX(), this.animal.locY(), this.animal.locZ(), 0.0F, 0.0F);
/* 1244 */         worldserver.addAllEntities(entityfox, CreatureSpawnEvent.SpawnReason.BREEDING);
/* 1245 */         this.b.broadcastEntityEffect(this.animal, (byte)18);
/* 1246 */         if (this.b.getGameRules().getBoolean(GameRules.DO_MOB_LOOT))
/*      */         {
/* 1248 */           if (experience > 0) {
/* 1249 */             this.b.addEntity(new EntityExperienceOrb(this.b, this.animal.locX(), this.animal.locY(), this.animal.locZ(), experience, ExperienceOrb.SpawnReason.BREED, entityplayer, entityfox));
/*      */           }
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   class l
/*      */     extends PathfinderGoalMeleeAttack
/*      */   {
/*      */     public l(double d0, boolean flag) {
/* 1261 */       super(EntityFox.this, d0, flag);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(EntityLiving entityliving, double d0) {
/* 1266 */       double d1 = a(entityliving);
/*      */       
/* 1268 */       if (d0 <= d1 && h()) {
/* 1269 */         g();
/* 1270 */         this.a.attackEntity(entityliving);
/* 1271 */         EntityFox.this.playSound(SoundEffects.ENTITY_FOX_BITE, 1.0F, 1.0F);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void c() {
/* 1278 */       EntityFox.this.w(false);
/* 1279 */       super.c();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1284 */       return (!EntityFox.this.isSitting() && !EntityFox.this.isSleeping() && !EntityFox.this.isCrouching() && !EntityFox.this.eN() && super.a());
/*      */     }
/*      */   }
/*      */   
/*      */   class u
/*      */     extends PathfinderGoal {
/*      */     public u() {
/* 1291 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1296 */       if (EntityFox.this.isSleeping()) {
/* 1297 */         return false;
/*      */       }
/* 1299 */       EntityLiving entityliving = EntityFox.this.getGoalTarget();
/*      */       
/* 1301 */       return (entityliving != null && entityliving.isAlive() && EntityFox.bu.test(entityliving) && EntityFox.this.h(entityliving) > 36.0D && !EntityFox.this.isCrouching() && !EntityFox.this.eW() && !EntityFox.this.jumping);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void c() {
/* 1307 */       EntityFox.this.setSitting(false);
/* 1308 */       EntityFox.this.x(false);
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/* 1313 */       EntityLiving entityliving = EntityFox.this.getGoalTarget();
/*      */       
/* 1315 */       if (entityliving != null && EntityFox.a(EntityFox.this, entityliving)) {
/* 1316 */         EntityFox.this.w(true);
/* 1317 */         EntityFox.this.setCrouching(true);
/* 1318 */         EntityFox.this.getNavigation().o();
/* 1319 */         EntityFox.this.getControllerLook().a(entityliving, EntityFox.this.eo(), EntityFox.this.O());
/*      */       } else {
/* 1321 */         EntityFox.this.w(false);
/* 1322 */         EntityFox.this.setCrouching(false);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void e() {
/* 1329 */       EntityLiving entityliving = EntityFox.this.getGoalTarget();
/*      */       
/* 1331 */       EntityFox.this.getControllerLook().a(entityliving, EntityFox.this.eo(), EntityFox.this.O());
/* 1332 */       if (EntityFox.this.h(entityliving) <= 36.0D) {
/* 1333 */         EntityFox.this.w(true);
/* 1334 */         EntityFox.this.setCrouching(true);
/* 1335 */         EntityFox.this.getNavigation().o();
/*      */       } else {
/* 1337 */         EntityFox.this.getNavigation().a(entityliving, 1.5D);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   class m
/*      */     extends ControllerMove
/*      */   {
/*      */     public m() {
/* 1346 */       super(EntityFox.this);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a() {
/* 1351 */       if (EntityFox.this.fe()) {
/* 1352 */         super.a();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   class p
/*      */     extends PathfinderGoal
/*      */   {
/*      */     public p() {
/* 1361 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1366 */       if (!EntityFox.this.getEquipment(EnumItemSlot.MAINHAND).isEmpty())
/* 1367 */         return false; 
/* 1368 */       if (EntityFox.this.getGoalTarget() == null && EntityFox.this.getLastDamager() == null) {
/* 1369 */         if (!EntityFox.this.fe())
/* 1370 */           return false; 
/* 1371 */         if (EntityFox.this.getRandom().nextInt(10) != 0) {
/* 1372 */           return false;
/*      */         }
/* 1374 */         List<EntityItem> list = EntityFox.this.world.a(EntityItem.class, EntityFox.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityFox.bs);
/*      */         
/* 1376 */         return (!list.isEmpty() && EntityFox.this.getEquipment(EnumItemSlot.MAINHAND).isEmpty());
/*      */       } 
/*      */       
/* 1379 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void e() {
/* 1385 */       List<EntityItem> list = EntityFox.this.world.a(EntityItem.class, EntityFox.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityFox.bs);
/* 1386 */       ItemStack itemstack = EntityFox.this.getEquipment(EnumItemSlot.MAINHAND);
/*      */       
/* 1388 */       if (itemstack.isEmpty() && !list.isEmpty()) {
/* 1389 */         EntityFox.this.getNavigation().a(list.get(0), 1.2000000476837158D);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void c() {
/* 1396 */       List<EntityItem> list = EntityFox.this.world.a(EntityItem.class, EntityFox.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityFox.bs);
/*      */       
/* 1398 */       if (!list.isEmpty()) {
/* 1399 */         EntityFox.this.getNavigation().a(list.get(0), 1.2000000476837158D);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public enum Type
/*      */   {
/* 1407 */     RED(0, "red", new ResourceKey[] { Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.TAIGA_MOUNTAINS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.GIANT_SPRUCE_TAIGA_HILLS }), SNOW(1, "snow", new ResourceKey[] { Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS }); private final List<ResourceKey<BiomeBase>> g; private final String f;
/*      */     static {
/* 1409 */       c = (Type[])Arrays.<Type>stream(values()).sorted(Comparator.comparingInt(Type::b)).toArray(i -> new Type[i]);
/*      */ 
/*      */       
/* 1412 */       d = (Map<String, Type>)Arrays.<Type>stream(values()).collect(Collectors.toMap(Type::a, entityfox_type -> entityfox_type));
/*      */     }
/*      */     
/*      */     private final int e;
/*      */     private static final Map<String, Type> d;
/*      */     private static final Type[] c;
/*      */     
/*      */     Type(int i, String s, ResourceKey... aresourcekey) {
/* 1420 */       this.e = i;
/* 1421 */       this.f = s;
/* 1422 */       this.g = Arrays.asList((ResourceKey<BiomeBase>[])aresourcekey);
/*      */     }
/*      */     
/*      */     public String a() {
/* 1426 */       return this.f;
/*      */     }
/*      */     
/*      */     public int b() {
/* 1430 */       return this.e;
/*      */     }
/*      */     
/*      */     public static Type a(String s) {
/* 1434 */       return d.getOrDefault(s, RED);
/*      */     }
/*      */     
/*      */     public static Type a(int i) {
/* 1438 */       if (i < 0 || i > c.length) {
/* 1439 */         i = 0;
/*      */       }
/*      */       
/* 1442 */       return c[i];
/*      */     }
/*      */     
/*      */     public static Type a(Optional<ResourceKey<BiomeBase>> optional) {
/* 1446 */       return (optional.isPresent() && SNOW.g.contains(optional.get())) ? SNOW : RED;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */