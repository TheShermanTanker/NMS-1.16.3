/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.datafixers.util.Either;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.OptionalInt;
/*      */ import java.util.UUID;
/*      */ import java.util.function.Predicate;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.OfflinePlayer;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.Item;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*      */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*      */ import org.bukkit.event.player.PlayerBedLeaveEvent;
/*      */ import org.bukkit.event.player.PlayerDropItemEvent;
/*      */ import org.bukkit.event.player.PlayerVelocityEvent;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.scoreboard.Team;
/*      */ import org.bukkit.util.Vector;
/*      */ 
/*      */ public abstract class EntityHuman extends EntityLiving {
/*   34 */   public static final EntitySize bh = EntitySize.b(0.6F, 1.8F);
/*      */   
/*   36 */   private static final Map<EntityPose, EntitySize> b = (Map<EntityPose, EntitySize>)ImmutableMap.builder().put(EntityPose.STANDING, bh).put(EntityPose.SLEEPING, ah).put(EntityPose.FALL_FLYING, EntitySize.b(0.6F, 0.6F)).put(EntityPose.SWIMMING, EntitySize.b(0.6F, 0.6F)).put(EntityPose.SPIN_ATTACK, EntitySize.b(0.6F, 0.6F)).put(EntityPose.CROUCHING, EntitySize.b(0.6F, 1.5F)).put(EntityPose.DYING, EntitySize.c(0.2F, 0.2F)).build();
/*   37 */   private static final DataWatcherObject<Float> c = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.c);
/*   38 */   private static final DataWatcherObject<Integer> d = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.b);
/*   39 */   protected static final DataWatcherObject<Byte> bi = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.a); public static DataWatcherObject<Byte> getSkinPartsWatcher() { return bi; }
/*   40 */    protected static final DataWatcherObject<Byte> bj = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.a);
/*   41 */   protected static final DataWatcherObject<NBTTagCompound> bk = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.p);
/*   42 */   protected static final DataWatcherObject<NBTTagCompound> bl = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.p);
/*      */   private long e;
/*   44 */   public final PlayerInventory inventory = new PlayerInventory(this);
/*   45 */   protected InventoryEnderChest enderChest = new InventoryEnderChest(this);
/*      */   public final ContainerPlayer defaultContainer;
/*      */   public Container activeContainer;
/*   48 */   protected FoodMetaData foodData = new FoodMetaData(this);
/*      */   protected int br;
/*      */   public float bs;
/*      */   public float bt;
/*      */   public int bu;
/*      */   public double bv;
/*      */   public double bw;
/*      */   public double bx;
/*      */   public double by;
/*      */   public double bz;
/*      */   public double bA;
/*      */   public int sleepTicks;
/*      */   protected boolean bB;
/*   61 */   public final PlayerAbilities abilities = new PlayerAbilities();
/*      */   public int expLevel;
/*      */   public int expTotal;
/*      */   public float exp;
/*      */   protected int bG;
/*   66 */   protected final float bH = 0.02F; private int g; private GameProfile bJ; private ItemStack bL; private final ItemCooldown bM; @Nullable
/*      */   public EntityFishingHook hookedFish; public final void setProfile(GameProfile profile) {
/*   68 */     this.bJ = profile;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean affectsSpawning = true;
/*      */ 
/*      */   
/*      */   public boolean fauxSleeping;
/*      */ 
/*      */   
/*   79 */   public int oldLevel = -1;
/*      */ 
/*      */   
/*      */   public CraftHumanEntity getBukkitEntity() {
/*   83 */     return (CraftHumanEntity)super.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityHuman(World world, BlockPosition blockposition, float f, GameProfile gameprofile) {
/*   88 */     super((EntityTypes)EntityTypes.PLAYER, world);
/*   89 */     this.bL = ItemStack.b;
/*   90 */     this.bM = i();
/*   91 */     a_(a(gameprofile));
/*   92 */     this.bJ = gameprofile;
/*   93 */     this.defaultContainer = new ContainerPlayer(this.inventory, !world.isClientSide, this);
/*   94 */     this.activeContainer = this.defaultContainer;
/*   95 */     setPositionRotation(blockposition.getX() + 0.5D, (blockposition.getY() + 1), blockposition.getZ() + 0.5D, f, 0.0F);
/*   96 */     this.aN = 180.0F;
/*      */   }
/*      */   
/*      */   public boolean a(World world, BlockPosition blockposition, EnumGamemode enumgamemode) {
/*  100 */     if (!enumgamemode.d())
/*  101 */       return false; 
/*  102 */     if (enumgamemode == EnumGamemode.SPECTATOR)
/*  103 */       return true; 
/*  104 */     if (eJ()) {
/*  105 */       return false;
/*      */     }
/*  107 */     ItemStack itemstack = getItemInMainHand();
/*      */     
/*  109 */     return (itemstack.isEmpty() || !itemstack.a(world.p(), new ShapeDetectorBlock(world, blockposition, false)));
/*      */   }
/*      */ 
/*      */   
/*      */   public static AttributeProvider.Builder eo() {
/*  114 */     return EntityLiving.cK().a(GenericAttributes.ATTACK_DAMAGE, 1.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.10000000149011612D).a(GenericAttributes.ATTACK_SPEED).a(GenericAttributes.LUCK);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initDatawatcher() {
/*  119 */     super.initDatawatcher();
/*  120 */     this.datawatcher.register(c, Float.valueOf(0.0F));
/*  121 */     this.datawatcher.register(d, Integer.valueOf(0));
/*  122 */     this.datawatcher.register(bi, Byte.valueOf((byte)0));
/*  123 */     this.datawatcher.register(bj, Byte.valueOf((byte)1));
/*  124 */     this.datawatcher.register(bk, new NBTTagCompound());
/*  125 */     this.datawatcher.register(bl, new NBTTagCompound());
/*      */   }
/*      */ 
/*      */   
/*      */   public void tick() {
/*  130 */     this.noclip = isSpectator();
/*  131 */     if (isSpectator()) {
/*  132 */       this.onGround = false;
/*      */     }
/*      */     
/*  135 */     if (this.bu > 0) {
/*  136 */       this.bu--;
/*      */     }
/*      */     
/*  139 */     if (isSleeping()) {
/*  140 */       this.sleepTicks++;
/*  141 */       if (this.sleepTicks > 100) {
/*  142 */         this.sleepTicks = 100;
/*      */       }
/*      */       
/*  145 */       if (!this.world.isClientSide && this.world.isDay()) {
/*  146 */         wakeup(false, true);
/*      */       }
/*  148 */     } else if (this.sleepTicks > 0) {
/*  149 */       this.sleepTicks++;
/*  150 */       if (this.sleepTicks >= 110) {
/*  151 */         this.sleepTicks = 0;
/*      */       }
/*      */     } 
/*      */     
/*  155 */     es();
/*  156 */     super.tick();
/*  157 */     if (!this.world.isClientSide && this.activeContainer != null && !this.activeContainer.canUse(this)) {
/*  158 */       closeInventory(InventoryCloseEvent.Reason.CANT_USE);
/*  159 */       this.activeContainer = this.defaultContainer;
/*      */     } 
/*      */     
/*  162 */     p();
/*  163 */     if (!this.world.isClientSide) {
/*  164 */       this.foodData.a(this);
/*  165 */       a(StatisticList.PLAY_ONE_MINUTE);
/*  166 */       if (isAlive()) {
/*  167 */         a(StatisticList.TIME_SINCE_DEATH);
/*      */       }
/*      */       
/*  170 */       if (bw()) {
/*  171 */         a(StatisticList.SNEAK_TIME);
/*      */       }
/*      */       
/*  174 */       if (!isSleeping()) {
/*  175 */         a(StatisticList.TIME_SINCE_REST);
/*      */       }
/*      */     } 
/*      */     
/*  179 */     int i = 29999999;
/*  180 */     double d0 = MathHelper.a(locX(), -2.9999999E7D, 2.9999999E7D);
/*  181 */     double d1 = MathHelper.a(locZ(), -2.9999999E7D, 2.9999999E7D);
/*      */     
/*  183 */     if (d0 != locX() || d1 != locZ()) {
/*  184 */       setPosition(d0, locY(), d1);
/*      */     }
/*      */     
/*  187 */     this.at++;
/*  188 */     ItemStack itemstack = getItemInMainHand();
/*      */     
/*  190 */     if (!ItemStack.matches(this.bL, itemstack)) {
/*  191 */       if (!ItemStack.d(this.bL, itemstack)) {
/*  192 */         resetAttackCooldown();
/*      */       }
/*      */       
/*  195 */       this.bL = itemstack.cloneItemStack();
/*      */     } 
/*      */     
/*  198 */     o();
/*  199 */     this.bM.a();
/*  200 */     et();
/*      */   }
/*      */   
/*      */   public boolean ep() {
/*  204 */     return isSneaking();
/*      */   }
/*      */   
/*      */   protected boolean eq() {
/*  208 */     return isSneaking();
/*      */   }
/*      */   
/*      */   protected boolean er() {
/*  212 */     return isSneaking();
/*      */   }
/*      */   
/*      */   protected boolean es() {
/*  216 */     this.bB = a(TagsFluid.WATER);
/*  217 */     return this.bB;
/*      */   }
/*      */   
/*      */   private void o() {
/*  221 */     ItemStack itemstack = getEquipment(EnumItemSlot.HEAD);
/*      */     
/*  223 */     if (itemstack.getItem() == Items.TURTLE_HELMET && !a(TagsFluid.WATER)) {
/*  224 */       addEffect(new MobEffect(MobEffects.WATER_BREATHING, 200, 0, false, false, true), EntityPotionEffectEvent.Cause.TURTLE_HELMET);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected ItemCooldown i() {
/*  230 */     return new ItemCooldown();
/*      */   }
/*      */   
/*      */   private void p() {
/*  234 */     this.bv = this.by;
/*  235 */     this.bw = this.bz;
/*  236 */     this.bx = this.bA;
/*  237 */     double d0 = locX() - this.by;
/*  238 */     double d1 = locY() - this.bz;
/*  239 */     double d2 = locZ() - this.bA;
/*  240 */     double d3 = 10.0D;
/*      */     
/*  242 */     if (d0 > 10.0D) {
/*  243 */       this.by = locX();
/*  244 */       this.bv = this.by;
/*      */     } 
/*      */     
/*  247 */     if (d2 > 10.0D) {
/*  248 */       this.bA = locZ();
/*  249 */       this.bx = this.bA;
/*      */     } 
/*      */     
/*  252 */     if (d1 > 10.0D) {
/*  253 */       this.bz = locY();
/*  254 */       this.bw = this.bz;
/*      */     } 
/*      */     
/*  257 */     if (d0 < -10.0D) {
/*  258 */       this.by = locX();
/*  259 */       this.bv = this.by;
/*      */     } 
/*      */     
/*  262 */     if (d2 < -10.0D) {
/*  263 */       this.bA = locZ();
/*  264 */       this.bx = this.bA;
/*      */     } 
/*      */     
/*  267 */     if (d1 < -10.0D) {
/*  268 */       this.bz = locY();
/*  269 */       this.bw = this.bz;
/*      */     } 
/*      */     
/*  272 */     this.by += d0 * 0.25D;
/*  273 */     this.bA += d2 * 0.25D;
/*  274 */     this.bz += d1 * 0.25D;
/*      */   }
/*      */   
/*      */   protected void et() {
/*  278 */     if (c(EntityPose.SWIMMING)) {
/*      */       EntityPose entitypose, entitypose1;
/*      */       
/*  281 */       if (isGliding()) {
/*  282 */         entitypose = EntityPose.FALL_FLYING;
/*  283 */       } else if (isSleeping()) {
/*  284 */         entitypose = EntityPose.SLEEPING;
/*  285 */       } else if (isSwimming()) {
/*  286 */         entitypose = EntityPose.SWIMMING;
/*  287 */       } else if (isRiptiding()) {
/*  288 */         entitypose = EntityPose.SPIN_ATTACK;
/*  289 */       } else if (isSneaking() && !this.abilities.isFlying) {
/*  290 */         entitypose = EntityPose.CROUCHING;
/*      */       } else {
/*  292 */         entitypose = EntityPose.STANDING;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  297 */       if (!isSpectator() && !isPassenger() && !c(entitypose)) {
/*  298 */         if (c(EntityPose.CROUCHING)) {
/*  299 */           entitypose1 = EntityPose.CROUCHING;
/*      */         } else {
/*  301 */           entitypose1 = EntityPose.SWIMMING;
/*      */         } 
/*      */       } else {
/*  304 */         entitypose1 = entitypose;
/*      */       } 
/*      */       
/*  307 */       setPose(entitypose1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int ai() {
/*  313 */     return this.abilities.isInvulnerable ? 1 : 80;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundSwim() {
/*  318 */     return SoundEffects.ENTITY_PLAYER_SWIM;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundSplash() {
/*  323 */     return SoundEffects.ENTITY_PLAYER_SPLASH;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundSplashHighSpeed() {
/*  328 */     return SoundEffects.ENTITY_PLAYER_SPLASH_HIGH_SPEED;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDefaultPortalCooldown() {
/*  333 */     return 10;
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(SoundEffect soundeffect, float f, float f1) {
/*  338 */     this.world.playSound(this, locX(), locY(), locZ(), soundeffect, getSoundCategory(), f, f1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1) {}
/*      */   
/*      */   public SoundCategory getSoundCategory() {
/*  345 */     return SoundCategory.PLAYERS;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxFireTicks() {
/*  350 */     return 20;
/*      */   }
/*      */ 
/*      */   
/*      */   public void closeInventory(InventoryCloseEvent.Reason reason) {
/*  355 */     closeInventory();
/*  356 */     this.activeContainer = this.defaultContainer;
/*      */   }
/*      */ 
/*      */   
/*      */   public void closeInventory() {
/*  361 */     this.activeContainer = this.defaultContainer;
/*      */   }
/*      */ 
/*      */   
/*      */   public void passengerTick() {
/*  366 */     if (eq() && isPassenger()) {
/*  367 */       stopRiding();
/*  368 */       setSneaking(false);
/*      */     } else {
/*  370 */       double d0 = locX();
/*  371 */       double d1 = locY();
/*  372 */       double d2 = locZ();
/*      */       
/*  374 */       super.passengerTick();
/*  375 */       this.bs = this.bt;
/*  376 */       this.bt = 0.0F;
/*  377 */       q(locX() - d0, locY() - d1, locZ() - d2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void doTick() {
/*  383 */     super.doTick();
/*  384 */     dz();
/*  385 */     this.aC = this.yaw;
/*      */   }
/*      */   
/*      */   public void movementTick() {
/*      */     float f;
/*  390 */     if (this.br > 0) {
/*  391 */       this.br--;
/*      */     }
/*      */     
/*  394 */     if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL && this.world.getGameRules().getBoolean(GameRules.NATURAL_REGENERATION)) {
/*  395 */       if (getHealth() < getMaxHealth() && this.ticksLived % 20 == 0)
/*      */       {
/*  397 */         heal(1.0F, EntityRegainHealthEvent.RegainReason.REGEN);
/*      */       }
/*      */       
/*  400 */       if (this.foodData.c() && this.ticksLived % 10 == 0) {
/*  401 */         this.foodData.a(this.foodData.getFoodLevel() + 1);
/*      */       }
/*      */     } 
/*      */     
/*  405 */     this.inventory.j();
/*  406 */     this.bs = this.bt;
/*  407 */     super.movementTick();
/*  408 */     this.aE = 0.02F;
/*  409 */     if (isSprinting()) {
/*  410 */       this.aE = (float)(this.aE + 0.005999999865889549D);
/*      */     }
/*      */     
/*  413 */     q((float)b(GenericAttributes.MOVEMENT_SPEED));
/*      */ 
/*      */     
/*  416 */     if (this.onGround && !dk() && !isSwimming()) {
/*  417 */       f = Math.min(0.1F, MathHelper.sqrt(c(getMot())));
/*      */     } else {
/*  419 */       f = 0.0F;
/*      */     } 
/*      */     
/*  422 */     this.bt += (f - this.bt) * 0.4F;
/*  423 */     if (getHealth() > 0.0F && !isSpectator()) {
/*      */       AxisAlignedBB axisalignedbb;
/*      */       
/*  426 */       if (isPassenger() && !(getVehicle()).dead) {
/*  427 */         axisalignedbb = getBoundingBox().b(getVehicle().getBoundingBox()).grow(1.0D, 0.0D, 1.0D);
/*      */       } else {
/*  429 */         axisalignedbb = getBoundingBox().grow(1.0D, 0.5D, 1.0D);
/*      */       } 
/*      */       
/*  432 */       List<Entity> list = this.world.getEntities(this, axisalignedbb);
/*      */       
/*  434 */       for (int i = 0; i < list.size(); i++) {
/*  435 */         Entity entity = list.get(i);
/*      */         
/*  437 */         if (!entity.dead) {
/*  438 */           c(entity);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  443 */     j(getShoulderEntityLeft());
/*  444 */     j(getShoulderEntityRight());
/*  445 */     if (((!this.world.isClientSide && (this.fallDistance > 0.5F || isInWater())) || this.abilities.isFlying || isSleeping()) && 
/*  446 */       !this.world.paperConfig.parrotsHangOnBetter) releaseShoulderEntities();
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   private void j(@Nullable NBTTagCompound nbttagcompound) {
/*  452 */     if (nbttagcompound != null && (!nbttagcompound.hasKey("Silent") || !nbttagcompound.getBoolean("Silent")) && this.world.random.nextInt(200) == 0) {
/*  453 */       String s = nbttagcompound.getString("id");
/*      */       
/*  455 */       EntityTypes.a(s).filter(entitytypes -> (entitytypes == EntityTypes.PARROT))
/*      */         
/*  457 */         .ifPresent(entitytypes -> {
/*      */             if (!EntityParrot.a(this.world, this)) {
/*      */               this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), EntityParrot.a(this.world, this.world.random), getSoundCategory(), 1.0F, EntityParrot.a(this.world.random));
/*      */             }
/*      */           });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void c(Entity entity) {
/*  468 */     entity.pickup(this);
/*      */   }
/*      */   
/*      */   public int getScore() {
/*  472 */     return ((Integer)this.datawatcher.<Integer>get(d)).intValue();
/*      */   }
/*      */   
/*      */   public void setScore(int i) {
/*  476 */     this.datawatcher.set(d, Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   public void addScore(int i) {
/*  480 */     int j = getScore();
/*      */     
/*  482 */     this.datawatcher.set(d, Integer.valueOf(j + i));
/*      */   }
/*      */ 
/*      */   
/*      */   public void die(DamageSource damagesource) {
/*  487 */     super.die(damagesource);
/*  488 */     ae();
/*  489 */     if (!isSpectator()) {
/*  490 */       d(damagesource);
/*      */     }
/*      */     
/*  493 */     if (damagesource != null) {
/*  494 */       setMot((-MathHelper.cos((this.ap + this.yaw) * 0.017453292F) * 0.1F), 0.10000000149011612D, (-MathHelper.sin((this.ap + this.yaw) * 0.017453292F) * 0.1F));
/*      */     } else {
/*  496 */       setMot(0.0D, 0.1D, 0.0D);
/*      */     } 
/*      */     
/*  499 */     a(StatisticList.DEATHS);
/*  500 */     a(StatisticList.CUSTOM.b(StatisticList.TIME_SINCE_DEATH));
/*  501 */     a(StatisticList.CUSTOM.b(StatisticList.TIME_SINCE_REST));
/*  502 */     extinguish();
/*  503 */     setFlag(0, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void dropInventory() {
/*  508 */     super.dropInventory();
/*  509 */     if (!this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
/*  510 */       removeCursedItems();
/*  511 */       this.inventory.dropContents();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void removeCursedItems() {
/*  517 */     for (int i = 0; i < this.inventory.getSize(); i++) {
/*  518 */       ItemStack itemstack = this.inventory.getItem(i);
/*      */       
/*  520 */       if (!itemstack.isEmpty() && EnchantmentManager.shouldNotDrop(itemstack)) {
/*  521 */         this.inventory.splitWithoutUpdate(i);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  529 */     return (damagesource == DamageSource.BURN) ? SoundEffects.ENTITY_PLAYER_HURT_ON_FIRE : ((damagesource == DamageSource.DROWN) ? SoundEffects.ENTITY_PLAYER_HURT_DROWN : ((damagesource == DamageSource.SWEET_BERRY_BUSH) ? SoundEffects.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH : SoundEffects.ENTITY_PLAYER_HURT));
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundDeath() {
/*  534 */     return SoundEffects.ENTITY_PLAYER_DEATH;
/*      */   }
/*      */   
/*      */   public boolean dropItem(boolean flag) {
/*  538 */     return (a(this.inventory.splitStack(this.inventory.itemInHandIndex, (flag && !this.inventory.getItemInHand().isEmpty()) ? this.inventory.getItemInHand().getCount() : 1), false, true) != null);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityItem drop(ItemStack itemstack, boolean flag) {
/*  543 */     return a(itemstack, false, flag);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityItem a(ItemStack itemstack, boolean flag, boolean flag1) {
/*  548 */     if (itemstack.isEmpty()) {
/*  549 */       return null;
/*      */     }
/*  551 */     if (this.world.isClientSide) {
/*  552 */       swingHand(EnumHand.MAIN_HAND);
/*      */     }
/*      */     
/*  555 */     double d0 = getHeadY() - 0.30000001192092896D;
/*  556 */     EntityItem entityitem = new EntityItem(this.world, locX(), d0, locZ(), itemstack);
/*      */     
/*  558 */     entityitem.setPickupDelay(40);
/*  559 */     if (flag1) {
/*  560 */       entityitem.setThrower(getUniqueID());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  566 */     if (flag) {
/*  567 */       float f = this.random.nextFloat() * 0.5F;
/*  568 */       float f1 = this.random.nextFloat() * 6.2831855F;
/*  569 */       entityitem.setMot((-MathHelper.sin(f1) * f), 0.20000000298023224D, (MathHelper.cos(f1) * f));
/*      */     } else {
/*  571 */       float f = 0.3F;
/*  572 */       float f1 = MathHelper.sin(this.pitch * 0.017453292F);
/*  573 */       float f2 = MathHelper.cos(this.pitch * 0.017453292F);
/*  574 */       float f3 = MathHelper.sin(this.yaw * 0.017453292F);
/*  575 */       float f4 = MathHelper.cos(this.yaw * 0.017453292F);
/*  576 */       float f5 = this.random.nextFloat() * 6.2831855F;
/*  577 */       float f6 = 0.02F * this.random.nextFloat();
/*      */       
/*  579 */       entityitem.setMot((-f3 * f2 * 0.3F) + Math.cos(f5) * f6, (-f1 * 0.3F + 0.1F + (this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (f4 * f2 * 0.3F) + Math.sin(f5) * f6);
/*      */     } 
/*      */ 
/*      */     
/*  583 */     Player player = (Player)getBukkitEntity();
/*  584 */     Item drop = (Item)entityitem.getBukkitEntity();
/*      */     
/*  586 */     PlayerDropItemEvent event = new PlayerDropItemEvent(player, drop);
/*  587 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/*  589 */     if (event.isCancelled()) {
/*  590 */       ItemStack cur = player.getInventory().getItemInHand();
/*  591 */       if (flag1 && (cur == null || cur.getAmount() == 0)) {
/*      */         
/*  593 */         player.getInventory().setItemInHand(drop.getItemStack());
/*  594 */       } else if (flag1 && cur.isSimilar(drop.getItemStack()) && cur.getAmount() < cur.getMaxStackSize() && drop.getItemStack().getAmount() == 1) {
/*      */         
/*  596 */         cur.setAmount(cur.getAmount() + 1);
/*  597 */         player.getInventory().setItemInHand(cur);
/*      */       } else {
/*      */         
/*  600 */         player.getInventory().addItem(new ItemStack[] { drop.getItemStack() });
/*      */       } 
/*  602 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  606 */     if (itemstack.getItem() == Items.FILLED_MAP) {
/*  607 */       WorldMap worldmap = ItemWorldMap.getSavedMap(itemstack, this.world);
/*  608 */       worldmap.updateSeenPlayers(this, itemstack);
/*      */     } 
/*      */ 
/*      */     
/*  612 */     return entityitem;
/*      */   }
/*      */ 
/*      */   
/*      */   public float c(IBlockData iblockdata) {
/*  617 */     float f = this.inventory.a(iblockdata);
/*      */     
/*  619 */     if (f > 1.0F) {
/*  620 */       int i = EnchantmentManager.getDigSpeedEnchantmentLevel(this);
/*  621 */       ItemStack itemstack = getItemInMainHand();
/*      */       
/*  623 */       if (i > 0 && !itemstack.isEmpty()) {
/*  624 */         f += (i * i + 1);
/*      */       }
/*      */     } 
/*      */     
/*  628 */     if (MobEffectUtil.a(this)) {
/*  629 */       f *= 1.0F + (MobEffectUtil.b(this) + 1) * 0.2F;
/*      */     }
/*      */     
/*  632 */     if (hasEffect(MobEffects.SLOWER_DIG)) {
/*      */       float f1;
/*      */       
/*  635 */       switch (getEffect(MobEffects.SLOWER_DIG).getAmplifier()) {
/*      */         case 0:
/*  637 */           f1 = 0.3F;
/*      */           break;
/*      */         case 1:
/*  640 */           f1 = 0.09F;
/*      */           break;
/*      */         case 2:
/*  643 */           f1 = 0.0027F;
/*      */           break;
/*      */         
/*      */         default:
/*  647 */           f1 = 8.1E-4F;
/*      */           break;
/*      */       } 
/*  650 */       f *= f1;
/*      */     } 
/*      */     
/*  653 */     if (a(TagsFluid.WATER) && !EnchantmentManager.h(this)) {
/*  654 */       f /= 5.0F;
/*      */     }
/*      */     
/*  657 */     if (!this.onGround) {
/*  658 */       f /= 5.0F;
/*      */     }
/*      */     
/*  661 */     return f;
/*      */   }
/*      */   
/*      */   public boolean hasBlock(IBlockData iblockdata) {
/*  665 */     return (!iblockdata.isRequiresSpecialTool() || this.inventory.getItemInHand().canDestroySpecialBlock(iblockdata));
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadData(NBTTagCompound nbttagcompound) {
/*  670 */     super.loadData(nbttagcompound);
/*  671 */     a_(a(this.bJ));
/*  672 */     NBTTagList nbttaglist = nbttagcompound.getList("Inventory", 10);
/*      */     
/*  674 */     this.inventory.b(nbttaglist);
/*  675 */     this.inventory.itemInHandIndex = nbttagcompound.getInt("SelectedItemSlot");
/*  676 */     this.sleepTicks = nbttagcompound.getShort("SleepTimer");
/*  677 */     this.exp = nbttagcompound.getFloat("XpP");
/*  678 */     this.expLevel = nbttagcompound.getInt("XpLevel");
/*  679 */     this.expTotal = nbttagcompound.getInt("XpTotal");
/*  680 */     this.bG = nbttagcompound.getInt("XpSeed");
/*  681 */     if (this.bG == 0) {
/*  682 */       this.bG = this.random.nextInt();
/*      */     }
/*      */     
/*  685 */     setScore(nbttagcompound.getInt("Score"));
/*  686 */     this.foodData.a(nbttagcompound);
/*  687 */     this.abilities.b(nbttagcompound);
/*  688 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.abilities.b());
/*  689 */     if (nbttagcompound.hasKeyOfType("EnderItems", 9)) {
/*  690 */       this.enderChest.a(nbttagcompound.getList("EnderItems", 10));
/*      */     }
/*      */     
/*  693 */     if (nbttagcompound.hasKeyOfType("ShoulderEntityLeft", 10)) {
/*  694 */       setShoulderEntityLeft(nbttagcompound.getCompound("ShoulderEntityLeft"));
/*      */     }
/*      */     
/*  697 */     if (nbttagcompound.hasKeyOfType("ShoulderEntityRight", 10)) {
/*  698 */       setShoulderEntityRight(nbttagcompound.getCompound("ShoulderEntityRight"));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveData(NBTTagCompound nbttagcompound) {
/*  705 */     super.saveData(nbttagcompound);
/*  706 */     nbttagcompound.setInt("DataVersion", SharedConstants.getGameVersion().getWorldVersion());
/*  707 */     nbttagcompound.set("Inventory", this.inventory.a(new NBTTagList()));
/*  708 */     nbttagcompound.setInt("SelectedItemSlot", this.inventory.itemInHandIndex);
/*  709 */     nbttagcompound.setShort("SleepTimer", (short)this.sleepTicks);
/*  710 */     nbttagcompound.setFloat("XpP", this.exp);
/*  711 */     nbttagcompound.setInt("XpLevel", this.expLevel);
/*  712 */     nbttagcompound.setInt("XpTotal", this.expTotal);
/*  713 */     nbttagcompound.setInt("XpSeed", this.bG);
/*  714 */     nbttagcompound.setInt("Score", getScore());
/*  715 */     this.foodData.b(nbttagcompound);
/*  716 */     this.abilities.a(nbttagcompound);
/*  717 */     nbttagcompound.set("EnderItems", this.enderChest.g());
/*  718 */     if (!getShoulderEntityLeft().isEmpty()) {
/*  719 */       nbttagcompound.set("ShoulderEntityLeft", getShoulderEntityLeft());
/*      */     }
/*      */     
/*  722 */     if (!getShoulderEntityRight().isEmpty()) {
/*  723 */       nbttagcompound.set("ShoulderEntityRight", getShoulderEntityRight());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInvulnerable(DamageSource damagesource) {
/*  730 */     return super.isInvulnerable(damagesource) ? true : ((damagesource == DamageSource.DROWN) ? (!this.world.getGameRules().getBoolean(GameRules.DROWNING_DAMAGE)) : ((damagesource == DamageSource.FALL) ? (!this.world.getGameRules().getBoolean(GameRules.FALL_DAMAGE)) : (damagesource.isFire() ? (!this.world.getGameRules().getBoolean(GameRules.FIRE_DAMAGE)) : false)));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  735 */     if (isInvulnerable(damagesource))
/*  736 */       return false; 
/*  737 */     if (this.abilities.isInvulnerable && !damagesource.ignoresInvulnerability()) {
/*  738 */       this.forceExplosionKnockback = true;
/*  739 */       return false;
/*      */     } 
/*  741 */     this.ticksFarFromPlayer = 0;
/*  742 */     if (dk()) {
/*  743 */       return false;
/*      */     }
/*      */     
/*  746 */     if (damagesource.s()) {
/*  747 */       if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
/*  748 */         return false;
/*      */       }
/*      */       
/*  751 */       if (this.world.getDifficulty() == EnumDifficulty.EASY) {
/*  752 */         f = Math.min(f / 2.0F + 1.0F, f);
/*      */       }
/*      */       
/*  755 */       if (this.world.getDifficulty() == EnumDifficulty.HARD) {
/*  756 */         f = f * 3.0F / 2.0F;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  761 */     boolean damaged = super.damageEntity(damagesource, f);
/*  762 */     if (damaged) {
/*  763 */       releaseShoulderEntities();
/*      */     }
/*  765 */     return damaged;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void shieldBlock(EntityLiving entityliving) {
/*  773 */     super.shieldBlock(entityliving);
/*  774 */     if (entityliving.getItemInMainHand().getItem() instanceof ItemAxe) {
/*  775 */       p(true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean a(EntityHuman entityhuman) {
/*      */     Team team;
/*  784 */     if (entityhuman instanceof EntityPlayer) {
/*  785 */       EntityPlayer thatPlayer = (EntityPlayer)entityhuman;
/*  786 */       team = thatPlayer.getBukkitEntity().getScoreboard().getPlayerTeam((OfflinePlayer)thatPlayer.getBukkitEntity());
/*  787 */       if (team == null || team.allowFriendlyFire()) {
/*  788 */         return true;
/*      */       }
/*      */     } else {
/*      */       
/*  792 */       OfflinePlayer thisPlayer = entityhuman.world.getServer().getOfflinePlayer(entityhuman.getName());
/*  793 */       team = entityhuman.world.getServer().getScoreboardManager().getMainScoreboard().getPlayerTeam(thisPlayer);
/*  794 */       if (team == null || team.allowFriendlyFire()) {
/*  795 */         return true;
/*      */       }
/*      */     } 
/*      */     
/*  799 */     if (this instanceof EntityPlayer) {
/*  800 */       return !team.hasPlayer((OfflinePlayer)((EntityPlayer)this).getBukkitEntity());
/*      */     }
/*  802 */     return !team.hasPlayer(this.world.getServer().getOfflinePlayer(getName()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void damageArmor(DamageSource damagesource, float f) {
/*  808 */     this.inventory.a(damagesource, f);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void damageShield(float f) {
/*  813 */     if (this.activeItem.getItem() == Items.SHIELD) {
/*  814 */       if (!this.world.isClientSide) {
/*  815 */         b(StatisticList.ITEM_USED.b(this.activeItem.getItem()));
/*      */       }
/*      */       
/*  818 */       if (f >= 3.0F) {
/*  819 */         int i = 1 + MathHelper.d(f);
/*  820 */         EnumHand enumhand = getRaisedHand();
/*      */         
/*  822 */         this.activeItem.damage(i, this, entityhuman -> entityhuman.broadcastItemBreak(enumhand));
/*      */ 
/*      */         
/*  825 */         if (this.activeItem.isEmpty()) {
/*  826 */           if (enumhand == EnumHand.MAIN_HAND) {
/*  827 */             setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/*      */           } else {
/*  829 */             setSlot(EnumItemSlot.OFFHAND, ItemStack.b);
/*      */           } 
/*      */           
/*  832 */           this.activeItem = ItemStack.b;
/*  833 */           playSound(SoundEffects.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.random.nextFloat() * 0.4F);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean damageEntity0(DamageSource damagesource, float f) {
/*  844 */     return super.damageEntity0(damagesource, f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean cO() {
/*  877 */     return (!this.abilities.isFlying && super.cO());
/*      */   }
/*      */   
/*      */   public void openSign(TileEntitySign tileentitysign) {}
/*      */   
/*      */   public void a(CommandBlockListenerAbstract commandblocklistenerabstract) {}
/*      */   
/*      */   public void a(TileEntityCommand tileentitycommand) {}
/*      */   
/*      */   public void a(TileEntityStructure tileentitystructure) {}
/*      */   
/*      */   public void a(TileEntityJigsaw tileentityjigsaw) {}
/*      */   
/*      */   public void openHorseInventory(EntityHorseAbstract entityhorseabstract, IInventory iinventory) {}
/*      */   
/*      */   public OptionalInt openContainer(@Nullable ITileInventory itileinventory) {
/*  893 */     return OptionalInt.empty();
/*      */   }
/*      */   
/*      */   public void openTrade(int i, MerchantRecipeList merchantrecipelist, int j, int k, boolean flag, boolean flag1) {}
/*      */   
/*      */   public void openBook(ItemStack itemstack, EnumHand enumhand) {}
/*      */   
/*      */   public EnumInteractionResult a(Entity entity, EnumHand enumhand) {
/*  901 */     if (isSpectator()) {
/*  902 */       if (entity instanceof ITileInventory) {
/*  903 */         openContainer((ITileInventory)entity);
/*      */       }
/*      */       
/*  906 */       return EnumInteractionResult.PASS;
/*      */     } 
/*  908 */     ItemStack itemstack = b(enumhand);
/*  909 */     ItemStack itemstack1 = itemstack.cloneItemStack();
/*  910 */     EnumInteractionResult enuminteractionresult = entity.a(this, enumhand);
/*      */     
/*  912 */     if (enuminteractionresult.a()) {
/*  913 */       if (this.abilities.canInstantlyBuild && itemstack == b(enumhand) && itemstack.getCount() < itemstack1.getCount()) {
/*  914 */         itemstack.setCount(itemstack1.getCount());
/*      */       }
/*      */       
/*  917 */       return enuminteractionresult;
/*      */     } 
/*  919 */     if (!itemstack.isEmpty() && entity instanceof EntityLiving) {
/*  920 */       if (this.abilities.canInstantlyBuild) {
/*  921 */         itemstack = itemstack1;
/*      */       }
/*      */       
/*  924 */       EnumInteractionResult enuminteractionresult1 = itemstack.a(this, (EntityLiving)entity, enumhand);
/*      */       
/*  926 */       if (enuminteractionresult1.a()) {
/*  927 */         if (itemstack.isEmpty() && !this.abilities.canInstantlyBuild) {
/*  928 */           a(enumhand, ItemStack.b);
/*      */         }
/*      */         
/*  931 */         return enuminteractionresult1;
/*      */       } 
/*      */     } 
/*      */     
/*  935 */     return EnumInteractionResult.PASS;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double ba() {
/*  942 */     return -0.35D;
/*      */   }
/*      */   
/*      */   public void be() {
/*  946 */     stopRiding(false);
/*      */   }
/*      */   public void stopRiding(boolean suppressCancellation) {
/*  949 */     super.stopRiding(suppressCancellation);
/*  950 */     this.j = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isFrozen() {
/*  955 */     return (super.isFrozen() || isSleeping() || this.dead || !this.valid);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean cS() {
/*  960 */     return !this.abilities.isFlying;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Vec3D a(Vec3D vec3d, EnumMoveType enummovetype) {
/*  965 */     if (!this.abilities.isFlying && (enummovetype == EnumMoveType.SELF || enummovetype == EnumMoveType.PLAYER) && er() && q()) {
/*  966 */       double d0 = vec3d.x;
/*  967 */       double d1 = vec3d.z;
/*  968 */       double d2 = 0.05D;
/*      */       
/*  970 */       while (d0 != 0.0D && this.world.getCubes(this, getBoundingBox().d(d0, -this.G, 0.0D))) {
/*  971 */         if (d0 < 0.05D && d0 >= -0.05D) {
/*  972 */           d0 = 0.0D; continue;
/*  973 */         }  if (d0 > 0.0D) {
/*  974 */           d0 -= 0.05D; continue;
/*      */         } 
/*  976 */         d0 += 0.05D;
/*      */       } 
/*      */ 
/*      */       
/*  980 */       while (d1 != 0.0D && this.world.getCubes(this, getBoundingBox().d(0.0D, -this.G, d1))) {
/*  981 */         if (d1 < 0.05D && d1 >= -0.05D) {
/*  982 */           d1 = 0.0D; continue;
/*  983 */         }  if (d1 > 0.0D) {
/*  984 */           d1 -= 0.05D; continue;
/*      */         } 
/*  986 */         d1 += 0.05D;
/*      */       } 
/*      */ 
/*      */       
/*  990 */       while (d0 != 0.0D && d1 != 0.0D && this.world.getCubes(this, getBoundingBox().d(d0, -this.G, d1))) {
/*  991 */         if (d0 < 0.05D && d0 >= -0.05D) {
/*  992 */           d0 = 0.0D;
/*  993 */         } else if (d0 > 0.0D) {
/*  994 */           d0 -= 0.05D;
/*      */         } else {
/*  996 */           d0 += 0.05D;
/*      */         } 
/*      */         
/*  999 */         if (d1 < 0.05D && d1 >= -0.05D) {
/* 1000 */           d1 = 0.0D; continue;
/* 1001 */         }  if (d1 > 0.0D) {
/* 1002 */           d1 -= 0.05D; continue;
/*      */         } 
/* 1004 */         d1 += 0.05D;
/*      */       } 
/*      */ 
/*      */       
/* 1008 */       vec3d = new Vec3D(d0, vec3d.y, d1);
/*      */     } 
/*      */     
/* 1011 */     return vec3d;
/*      */   }
/*      */   
/*      */   private boolean q() {
/* 1015 */     return (this.onGround || (this.fallDistance < this.G && !this.world.getCubes(this, getBoundingBox().d(0.0D, (this.fallDistance - this.G), 0.0D))));
/*      */   }
/*      */   
/*      */   public void attack(Entity entity) {
/* 1019 */     if (entity.bK() && 
/* 1020 */       !entity.t(this)) {
/* 1021 */       float f1, f = (float)b(GenericAttributes.ATTACK_DAMAGE);
/*      */ 
/*      */       
/* 1024 */       if (entity instanceof EntityLiving) {
/* 1025 */         f1 = EnchantmentManager.a(getItemInMainHand(), ((EntityLiving)entity).getMonsterType());
/*      */       } else {
/* 1027 */         f1 = EnchantmentManager.a(getItemInMainHand(), EnumMonsterType.UNDEFINED);
/*      */       } 
/*      */       
/* 1030 */       float f2 = getAttackCooldown(0.5F);
/*      */       
/* 1032 */       f *= 0.2F + f2 * f2 * 0.8F;
/* 1033 */       f1 *= f2;
/*      */       
/* 1035 */       if (f > 0.0F || f1 > 0.0F) {
/* 1036 */         boolean flag = (f2 > 0.9F);
/* 1037 */         boolean flag1 = false;
/* 1038 */         byte b0 = 0;
/* 1039 */         int i = b0 + EnchantmentManager.b(this);
/*      */         
/* 1041 */         if (isSprinting() && flag) {
/* 1042 */           sendSoundEffect(this, locX(), locY(), locZ(), SoundEffects.ENTITY_PLAYER_ATTACK_KNOCKBACK, getSoundCategory(), 1.0F, 1.0F);
/* 1043 */           i++;
/* 1044 */           flag1 = true;
/*      */         } 
/*      */         
/* 1047 */         boolean flag2 = (flag && this.fallDistance > 0.0F && !this.onGround && !isClimbing() && !isInWater() && !hasEffect(MobEffects.BLINDNESS) && !isPassenger() && entity instanceof EntityLiving);
/*      */         
/* 1049 */         flag2 = (flag2 && !this.world.paperConfig.disablePlayerCrits);
/* 1050 */         flag2 = (flag2 && !isSprinting());
/* 1051 */         if (flag2) {
/* 1052 */           f *= 1.5F;
/*      */         }
/*      */         
/* 1055 */         f += f1;
/* 1056 */         boolean flag3 = false;
/* 1057 */         double d0 = (this.A - this.z);
/*      */         
/* 1059 */         if (flag && !flag2 && !flag1 && this.onGround && d0 < dM()) {
/* 1060 */           ItemStack itemstack = b(EnumHand.MAIN_HAND);
/*      */           
/* 1062 */           if (itemstack.getItem() instanceof ItemSword) {
/* 1063 */             flag3 = true;
/*      */           }
/*      */         } 
/*      */         
/* 1067 */         float f3 = 0.0F;
/* 1068 */         boolean flag4 = false;
/* 1069 */         int j = EnchantmentManager.getFireAspectEnchantmentLevel(this);
/*      */         
/* 1071 */         if (entity instanceof EntityLiving) {
/* 1072 */           f3 = ((EntityLiving)entity).getHealth();
/* 1073 */           if (j > 0 && !entity.isBurning()) {
/*      */             
/* 1075 */             EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), 1);
/* 1076 */             Bukkit.getPluginManager().callEvent((Event)combustEvent);
/*      */             
/* 1078 */             if (!combustEvent.isCancelled()) {
/* 1079 */               flag4 = true;
/* 1080 */               entity.setOnFire(combustEvent.getDuration(), false);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1086 */         Vec3D vec3d = entity.getMot();
/* 1087 */         boolean flag5 = entity.damageEntity(DamageSource.playerAttack(this), f);
/*      */         
/* 1089 */         if (flag5) {
/* 1090 */           if (i > 0) {
/* 1091 */             if (entity instanceof EntityLiving) {
/* 1092 */               ((EntityLiving)entity).doKnockback(i * 0.5F, MathHelper.sin(this.yaw * 0.017453292F), -MathHelper.cos(this.yaw * 0.017453292F), this);
/*      */             } else {
/* 1094 */               entity.i((-MathHelper.sin(this.yaw * 0.017453292F) * i * 0.5F), 0.1D, (MathHelper.cos(this.yaw * 0.017453292F) * i * 0.5F));
/*      */             } 
/*      */             
/* 1097 */             setMot(getMot().d(0.6D, 1.0D, 0.6D));
/*      */             
/* 1099 */             if (!this.world.paperConfig.disableSprintInterruptionOnAttack) {
/* 1100 */               setSprinting(false);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 1105 */           if (flag3) {
/* 1106 */             float f4 = 1.0F + EnchantmentManager.a(this) * f;
/* 1107 */             List<EntityLiving> list = this.world.a(EntityLiving.class, entity.getBoundingBox().grow(1.0D, 0.25D, 1.0D));
/* 1108 */             Iterator<EntityLiving> iterator = list.iterator();
/*      */             
/* 1110 */             while (iterator.hasNext()) {
/* 1111 */               EntityLiving entityliving = iterator.next();
/*      */               
/* 1113 */               if (entityliving != this && entityliving != entity && !r(entityliving) && (!(entityliving instanceof EntityArmorStand) || !((EntityArmorStand)entityliving).isMarker()) && h(entityliving) < 9.0D)
/*      */               {
/* 1115 */                 if (entityliving.damageEntity(DamageSource.playerAttack(this).sweep(), f4)) {
/* 1116 */                   entityliving.doKnockback(0.4F, MathHelper.sin(this.yaw * 0.017453292F), -MathHelper.cos(this.yaw * 0.017453292F), this);
/*      */                 }
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/* 1122 */             sendSoundEffect(this, locX(), locY(), locZ(), SoundEffects.ENTITY_PLAYER_ATTACK_SWEEP, getSoundCategory(), 1.0F, 1.0F);
/* 1123 */             ew();
/*      */           } 
/*      */           
/* 1126 */           if (entity instanceof EntityPlayer && entity.velocityChanged) {
/*      */             
/* 1128 */             boolean cancelled = false;
/* 1129 */             Player player = (Player)entity.getBukkitEntity();
/* 1130 */             Vector velocity = CraftVector.toBukkit(vec3d);
/*      */             
/* 1132 */             PlayerVelocityEvent event = new PlayerVelocityEvent(player, velocity.clone());
/* 1133 */             this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */             
/* 1135 */             if (event.isCancelled()) {
/* 1136 */               cancelled = true;
/* 1137 */             } else if (!velocity.equals(event.getVelocity())) {
/* 1138 */               player.setVelocity(event.getVelocity());
/*      */             } 
/*      */             
/* 1141 */             if (!cancelled) {
/* 1142 */               ((EntityPlayer)entity).playerConnection.sendPacket(new PacketPlayOutEntityVelocity(entity));
/* 1143 */               entity.velocityChanged = false;
/* 1144 */               entity.setMot(vec3d);
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 1149 */           if (flag2) {
/* 1150 */             sendSoundEffect(this, locX(), locY(), locZ(), SoundEffects.ENTITY_PLAYER_ATTACK_CRIT, getSoundCategory(), 1.0F, 1.0F);
/* 1151 */             a(entity);
/*      */           } 
/*      */           
/* 1154 */           if (!flag2 && !flag3) {
/* 1155 */             if (flag) {
/* 1156 */               sendSoundEffect(this, locX(), locY(), locZ(), SoundEffects.ENTITY_PLAYER_ATTACK_STRONG, getSoundCategory(), 1.0F, 1.0F);
/*      */             } else {
/* 1158 */               sendSoundEffect(this, locX(), locY(), locZ(), SoundEffects.ENTITY_PLAYER_ATTACK_WEAK, getSoundCategory(), 1.0F, 1.0F);
/*      */             } 
/*      */           }
/*      */           
/* 1162 */           if (f1 > 0.0F) {
/* 1163 */             b(entity);
/*      */           }
/*      */           
/* 1166 */           z(entity);
/* 1167 */           if (entity instanceof EntityLiving) {
/* 1168 */             EnchantmentManager.a((EntityLiving)entity, this);
/*      */           }
/*      */           
/* 1171 */           EnchantmentManager.b(this, entity);
/* 1172 */           ItemStack itemstack1 = getItemInMainHand();
/* 1173 */           Object object = entity;
/*      */           
/* 1175 */           if (entity instanceof EntityComplexPart) {
/* 1176 */             object = ((EntityComplexPart)entity).owner;
/*      */           }
/*      */           
/* 1179 */           if (!this.world.isClientSide && !itemstack1.isEmpty() && object instanceof EntityLiving) {
/* 1180 */             itemstack1.a((EntityLiving)object, this);
/* 1181 */             if (itemstack1.isEmpty()) {
/* 1182 */               a(EnumHand.MAIN_HAND, ItemStack.b);
/*      */             }
/*      */           } 
/*      */           
/* 1186 */           if (entity instanceof EntityLiving) {
/* 1187 */             float f5 = f3 - ((EntityLiving)entity).getHealth();
/*      */             
/* 1189 */             a(StatisticList.DAMAGE_DEALT, Math.round(f5 * 10.0F));
/* 1190 */             if (j > 0) {
/*      */               
/* 1192 */               EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), j * 4);
/* 1193 */               Bukkit.getPluginManager().callEvent((Event)combustEvent);
/*      */               
/* 1195 */               if (!combustEvent.isCancelled()) {
/* 1196 */                 entity.setOnFire(combustEvent.getDuration(), false);
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/* 1201 */             if (this.world instanceof WorldServer && f5 > 2.0F) {
/* 1202 */               int k = (int)(f5 * 0.5D);
/*      */               
/* 1204 */               ((WorldServer)this.world).a(Particles.DAMAGE_INDICATOR, entity.locX(), entity.e(0.5D), entity.locZ(), k, 0.1D, 0.0D, 0.1D, 0.2D);
/*      */             } 
/*      */           } 
/*      */           
/* 1208 */           applyExhaustion(this.world.spigotConfig.combatExhaustion);
/*      */         } else {
/* 1210 */           sendSoundEffect(this, locX(), locY(), locZ(), SoundEffects.ENTITY_PLAYER_ATTACK_NODAMAGE, getSoundCategory(), 1.0F, 1.0F);
/* 1211 */           if (flag4) {
/* 1212 */             entity.extinguish();
/*      */           }
/*      */           
/* 1215 */           if (this instanceof EntityPlayer) {
/* 1216 */             ((EntityPlayer)this).getBukkitEntity().updateInventory();
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void g(EntityLiving entityliving) {
/* 1228 */     attack(entityliving);
/*      */   }
/*      */   
/*      */   public void p(boolean flag) {
/* 1232 */     float f = 0.25F + EnchantmentManager.getDigSpeedEnchantmentLevel(this) * 0.05F;
/*      */     
/* 1234 */     if (flag) {
/* 1235 */       f += 0.75F;
/*      */     }
/*      */     
/* 1238 */     if (this.random.nextFloat() < f) {
/* 1239 */       getCooldownTracker().setCooldown(Items.SHIELD, 100);
/* 1240 */       clearActiveItem();
/* 1241 */       this.world.broadcastEntityEffect(this, (byte)30);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(Entity entity) {}
/*      */   
/*      */   public void b(Entity entity) {}
/*      */   
/*      */   public void ew() {
/* 1251 */     double d0 = -MathHelper.sin(this.yaw * 0.017453292F);
/* 1252 */     double d1 = MathHelper.cos(this.yaw * 0.017453292F);
/*      */     
/* 1254 */     if (this.world instanceof WorldServer) {
/* 1255 */       ((WorldServer)this.world).a(Particles.SWEEP_ATTACK, locX() + d0, e(0.5D), locZ() + d1, 0, d0, 0.0D, d1, 0.0D);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void die() {
/* 1262 */     super.die();
/* 1263 */     this.defaultContainer.b(this);
/* 1264 */     if (this.activeContainer != null) {
/* 1265 */       this.activeContainer.b(this);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ey() {
/* 1271 */     return false;
/*      */   }
/*      */   
/*      */   public GameProfile getProfile() {
/* 1275 */     return this.bJ;
/*      */   }
/*      */ 
/*      */   
/*      */   public Either<EnumBedResult, Unit> sleep(BlockPosition blockposition) {
/* 1280 */     return sleep(blockposition, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public Either<EnumBedResult, Unit> sleep(BlockPosition blockposition, boolean force) {
/* 1285 */     entitySleep(blockposition);
/* 1286 */     this.sleepTicks = 0;
/* 1287 */     return Either.right(Unit.INSTANCE);
/*      */   }
/*      */   
/*      */   public void wakeup(boolean flag, boolean flag1) {
/* 1291 */     BlockPosition bedPosition = getBedPosition().orElse(null);
/* 1292 */     super.entityWakeup();
/* 1293 */     if (this.world instanceof WorldServer && flag1) {
/* 1294 */       ((WorldServer)this.world).everyoneSleeping();
/*      */     }
/*      */ 
/*      */     
/* 1298 */     if (getBukkitEntity() instanceof Player) {
/* 1299 */       Block bed; Player player = (Player)getBukkitEntity();
/*      */ 
/*      */       
/* 1302 */       if (bedPosition != null) {
/* 1303 */         bed = this.world.getWorld().getBlockAt(bedPosition.getX(), bedPosition.getY(), bedPosition.getZ());
/*      */       } else {
/* 1305 */         bed = this.world.getWorld().getBlockAt(player.getLocation());
/*      */       } 
/*      */       
/* 1308 */       PlayerBedLeaveEvent event = new PlayerBedLeaveEvent(player, bed, true);
/* 1309 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */     } 
/*      */ 
/*      */     
/* 1313 */     this.sleepTicks = flag ? 0 : 100;
/*      */   }
/*      */ 
/*      */   
/*      */   public void entityWakeup() {
/* 1318 */     wakeup(true, true);
/*      */   }
/*      */   
/*      */   public static Optional<Vec3D> getBed(WorldServer worldserver, BlockPosition blockposition, float f, boolean flag, boolean flag1) {
/* 1322 */     IBlockData iblockdata = worldserver.getType(blockposition);
/* 1323 */     Block block = iblockdata.getBlock();
/*      */     
/* 1325 */     if (block instanceof BlockRespawnAnchor && ((Integer)iblockdata.get(BlockRespawnAnchor.a)).intValue() > 0 && BlockRespawnAnchor.a(worldserver)) {
/* 1326 */       Optional<Vec3D> optional = BlockRespawnAnchor.a(EntityTypes.PLAYER, worldserver, blockposition);
/*      */       
/* 1328 */       if (!flag1 && optional.isPresent()) {
/* 1329 */         worldserver.setTypeAndData(blockposition, iblockdata.set(BlockRespawnAnchor.a, Integer.valueOf(((Integer)iblockdata.get(BlockRespawnAnchor.a)).intValue() - 1)), 3);
/*      */       }
/*      */       
/* 1332 */       return optional;
/* 1333 */     }  if (block instanceof BlockBed && BlockBed.a(worldserver))
/* 1334 */       return BlockBed.a(EntityTypes.PLAYER, worldserver, blockposition, f); 
/* 1335 */     if (!flag) {
/* 1336 */       return Optional.empty();
/*      */     }
/* 1338 */     boolean flag2 = block.ai_();
/* 1339 */     boolean flag3 = worldserver.getType(blockposition.up()).getBlock().ai_();
/*      */     
/* 1341 */     return (flag2 && flag3) ? Optional.<Vec3D>of(new Vec3D(blockposition.getX() + 0.5D, blockposition.getY() + 0.1D, blockposition.getZ() + 0.5D)) : Optional.<Vec3D>empty();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDeeplySleeping() {
/* 1346 */     return (isSleeping() && this.sleepTicks >= 100);
/*      */   }
/*      */   
/*      */   public int eB() {
/* 1350 */     return this.sleepTicks;
/*      */   }
/*      */   
/*      */   public void a(IChatBaseComponent ichatbasecomponent, boolean flag) {}
/*      */   
/*      */   public void a(MinecraftKey minecraftkey) {
/* 1356 */     b(StatisticList.CUSTOM.b(minecraftkey));
/*      */   }
/*      */   
/*      */   public void a(MinecraftKey minecraftkey, int i) {
/* 1360 */     a(StatisticList.CUSTOM.b(minecraftkey), i);
/*      */   }
/*      */   
/*      */   public void b(Statistic<?> statistic) {
/* 1364 */     a(statistic, 1);
/*      */   }
/*      */   
/*      */   public void a(Statistic<?> statistic, int i) {}
/*      */   
/*      */   public void a(Statistic<?> statistic) {}
/*      */   
/*      */   public int discoverRecipes(Collection<IRecipe<?>> collection) {
/* 1372 */     return 0;
/*      */   }
/*      */   
/*      */   public void a(MinecraftKey[] aminecraftkey) {}
/*      */   
/*      */   public int undiscoverRecipes(Collection<IRecipe<?>> collection) {
/* 1378 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void jump() {
/* 1383 */     super.jump();
/* 1384 */     a(StatisticList.JUMP);
/* 1385 */     if (isSprinting()) {
/* 1386 */       applyExhaustion(this.world.spigotConfig.jumpSprintExhaustion);
/*      */     } else {
/* 1388 */       applyExhaustion(this.world.spigotConfig.jumpWalkExhaustion);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void g(Vec3D vec3d) {
/* 1395 */     double d0 = locX();
/* 1396 */     double d1 = locY();
/* 1397 */     double d2 = locZ();
/*      */ 
/*      */     
/* 1400 */     if (isSwimming() && !isPassenger()) {
/* 1401 */       double d3 = (getLookDirection()).y;
/* 1402 */       double d4 = (d3 < -0.2D) ? 0.085D : 0.06D;
/*      */       
/* 1404 */       if (d3 <= 0.0D || this.jumping || !this.world.getType(new BlockPosition(locX(), locY() + 1.0D - 0.1D, locZ())).getFluid().isEmpty()) {
/* 1405 */         Vec3D vec3d1 = getMot();
/*      */         
/* 1407 */         setMot(vec3d1.add(0.0D, (d3 - vec3d1.y) * d4, 0.0D));
/*      */       } 
/*      */     } 
/*      */     
/* 1411 */     if (this.abilities.isFlying && !isPassenger()) {
/* 1412 */       double d3 = (getMot()).y;
/* 1413 */       float f = this.aE;
/*      */       
/* 1415 */       this.aE = this.abilities.a() * (isSprinting() ? 2 : true);
/* 1416 */       super.g(vec3d);
/* 1417 */       Vec3D vec3d2 = getMot();
/*      */       
/* 1419 */       setMot(vec3d2.x, d3 * 0.6D, vec3d2.z);
/* 1420 */       this.aE = f;
/* 1421 */       this.fallDistance = 0.0F;
/*      */       
/* 1423 */       if (getFlag(7) && !CraftEventFactory.callToggleGlideEvent(this, false).isCancelled()) {
/* 1424 */         setFlag(7, false);
/*      */       }
/*      */     } else {
/*      */       
/* 1428 */       super.g(vec3d);
/*      */     } 
/*      */     
/* 1431 */     checkMovement(locX() - d0, locY() - d1, locZ() - d2);
/*      */   }
/*      */ 
/*      */   
/*      */   public void aI() {
/* 1436 */     if (this.abilities.isFlying) {
/* 1437 */       setSwimming(false);
/*      */     } else {
/* 1439 */       super.aI();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean f(BlockPosition blockposition) {
/* 1445 */     return !this.world.getType(blockposition).o(this.world, blockposition);
/*      */   }
/*      */ 
/*      */   
/*      */   public float dM() {
/* 1450 */     return (float)b(GenericAttributes.MOVEMENT_SPEED);
/*      */   }
/*      */   
/*      */   public void checkMovement(double d0, double d1, double d2) {
/* 1454 */     if (!isPassenger())
/*      */     {
/*      */       
/* 1457 */       if (isSwimming()) {
/* 1458 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/* 1459 */         if (i > 0) {
/* 1460 */           a(StatisticList.SWIM_ONE_CM, i);
/* 1461 */           applyExhaustion(0.01F * i * 0.01F);
/*      */         } 
/* 1463 */       } else if (a(TagsFluid.WATER)) {
/* 1464 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/* 1465 */         if (i > 0) {
/* 1466 */           a(StatisticList.WALK_UNDER_WATER_ONE_CM, i);
/* 1467 */           applyExhaustion(this.world.spigotConfig.swimMultiplier * i * 0.01F);
/*      */         } 
/* 1469 */       } else if (isInWater()) {
/* 1470 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1471 */         if (i > 0) {
/* 1472 */           a(StatisticList.WALK_ON_WATER_ONE_CM, i);
/* 1473 */           applyExhaustion(this.world.spigotConfig.swimMultiplier * i * 0.01F);
/*      */         } 
/* 1475 */       } else if (isClimbing()) {
/* 1476 */         if (d1 > 0.0D) {
/* 1477 */           a(StatisticList.CLIMB_ONE_CM, (int)Math.round(d1 * 100.0D));
/*      */         }
/* 1479 */       } else if (this.onGround) {
/* 1480 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1481 */         if (i > 0) {
/* 1482 */           if (isSprinting()) {
/* 1483 */             a(StatisticList.SPRINT_ONE_CM, i);
/* 1484 */             applyExhaustion(this.world.spigotConfig.sprintMultiplier * i * 0.01F);
/* 1485 */           } else if (by()) {
/* 1486 */             a(StatisticList.CROUCH_ONE_CM, i);
/* 1487 */             applyExhaustion(this.world.spigotConfig.otherMultiplier * i * 0.01F);
/*      */           } else {
/* 1489 */             a(StatisticList.WALK_ONE_CM, i);
/* 1490 */             applyExhaustion(this.world.spigotConfig.otherMultiplier * i * 0.01F);
/*      */           } 
/*      */         }
/* 1493 */       } else if (isGliding()) {
/* 1494 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/* 1495 */         a(StatisticList.AVIATE_ONE_CM, i);
/*      */       } else {
/* 1497 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1498 */         if (i > 25) {
/* 1499 */           a(StatisticList.FLY_ONE_CM, i);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void q(double d0, double d1, double d2) {
/* 1507 */     if (isPassenger()) {
/* 1508 */       int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/*      */       
/* 1510 */       if (i > 0) {
/* 1511 */         Entity entity = getVehicle();
/*      */         
/* 1513 */         if (entity instanceof EntityMinecartAbstract) {
/* 1514 */           a(StatisticList.MINECART_ONE_CM, i);
/* 1515 */         } else if (entity instanceof EntityBoat) {
/* 1516 */           a(StatisticList.BOAT_ONE_CM, i);
/* 1517 */         } else if (entity instanceof EntityPig) {
/* 1518 */           a(StatisticList.PIG_ONE_CM, i);
/* 1519 */         } else if (entity instanceof EntityHorseAbstract) {
/* 1520 */           a(StatisticList.HORSE_ONE_CM, i);
/* 1521 */         } else if (entity instanceof EntityStrider) {
/* 1522 */           a(StatisticList.STRIDER_ONE_CM, i);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean b(float f, float f1) {
/* 1531 */     if (this.abilities.canFly) {
/* 1532 */       return false;
/*      */     }
/* 1534 */     if (f >= 2.0F) {
/* 1535 */       a(StatisticList.FALL_ONE_CM, (int)Math.round(f * 100.0D));
/*      */     }
/*      */     
/* 1538 */     return super.b(f, f1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eC() {
/* 1543 */     if (!this.onGround && !isGliding() && !isInWater() && !hasEffect(MobEffects.LEVITATION)) {
/* 1544 */       ItemStack itemstack = getEquipment(EnumItemSlot.CHEST);
/*      */       
/* 1546 */       if (itemstack.getItem() == Items.ELYTRA && ItemElytra.d(itemstack)) {
/* 1547 */         startGliding();
/* 1548 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 1552 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void startGliding() {
/* 1557 */     if (!CraftEventFactory.callToggleGlideEvent(this, true).isCancelled()) {
/* 1558 */       setFlag(7, true);
/*      */     } else {
/*      */       
/* 1561 */       setFlag(7, true);
/* 1562 */       setFlag(7, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void stopGliding() {
/* 1569 */     if (!CraftEventFactory.callToggleGlideEvent(this, false).isCancelled()) {
/* 1570 */       setFlag(7, true);
/* 1571 */       setFlag(7, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void aL() {
/* 1578 */     if (!isSpectator()) {
/* 1579 */       super.aL();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundFall(int i) {
/* 1586 */     return (i > 4) ? SoundEffects.ENTITY_PLAYER_BIG_FALL : SoundEffects.ENTITY_PLAYER_SMALL_FALL;
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(WorldServer worldserver, EntityLiving entityliving) {
/* 1591 */     b(StatisticList.ENTITY_KILLED.b(entityliving.getEntityType()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(IBlockData iblockdata, Vec3D vec3d) {
/* 1596 */     if (!this.abilities.isFlying) {
/* 1597 */       super.a(iblockdata, vec3d);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void giveExp(int i) {
/* 1603 */     addScore(i);
/* 1604 */     this.exp += i / getExpToLevel();
/* 1605 */     this.expTotal = MathHelper.clamp(this.expTotal + i, 0, 2147483647);
/*      */     
/* 1607 */     while (this.exp < 0.0F) {
/* 1608 */       float f = this.exp * getExpToLevel();
/*      */       
/* 1610 */       if (this.expLevel > 0) {
/* 1611 */         levelDown(-1);
/* 1612 */         this.exp = 1.0F + f / getExpToLevel(); continue;
/*      */       } 
/* 1614 */       levelDown(-1);
/* 1615 */       this.exp = 0.0F;
/*      */     } 
/*      */ 
/*      */     
/* 1619 */     while (this.exp >= 1.0F) {
/* 1620 */       this.exp = (this.exp - 1.0F) * getExpToLevel();
/* 1621 */       levelDown(1);
/* 1622 */       this.exp /= getExpToLevel();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int eF() {
/* 1628 */     return this.bG;
/*      */   }
/*      */   
/*      */   public void enchantDone(ItemStack itemstack, int i) {
/* 1632 */     this.expLevel -= i;
/* 1633 */     if (this.expLevel < 0) {
/* 1634 */       this.expLevel = 0;
/* 1635 */       this.exp = 0.0F;
/* 1636 */       this.expTotal = 0;
/*      */     } 
/*      */     
/* 1639 */     this.bG = this.random.nextInt();
/*      */   }
/*      */   
/*      */   public void levelDown(int i) {
/* 1643 */     this.expLevel += i;
/* 1644 */     if (this.expLevel < 0) {
/* 1645 */       this.expLevel = 0;
/* 1646 */       this.exp = 0.0F;
/* 1647 */       this.expTotal = 0;
/*      */     } 
/*      */     
/* 1650 */     if (i > 0 && this.expLevel % 5 == 0 && this.g < this.ticksLived - 100.0F) {
/* 1651 */       float f = (this.expLevel > 30) ? 1.0F : (this.expLevel / 30.0F);
/*      */       
/* 1653 */       this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_PLAYER_LEVELUP, getSoundCategory(), f * 0.75F, 1.0F);
/* 1654 */       this.g = this.ticksLived;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getExpToLevel() {
/* 1660 */     return (this.expLevel >= 30) ? (112 + (this.expLevel - 30) * 9) : ((this.expLevel >= 15) ? (37 + (this.expLevel - 15) * 5) : (7 + this.expLevel * 2));
/*      */   }
/*      */   
/*      */   private static void sendSoundEffect(EntityHuman fromEntity, double x, double y, double z, SoundEffect soundEffect, SoundCategory soundCategory, float volume, float pitch) {
/* 1664 */     fromEntity.world.playSound(fromEntity, x, y, z, soundEffect, soundCategory, volume, pitch);
/* 1665 */     if (fromEntity instanceof EntityPlayer) {
/* 1666 */       ((EntityPlayer)fromEntity).playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(soundEffect, soundCategory, x, y, z, volume, pitch));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void applyExhaustion(float f) {
/* 1672 */     if (!this.abilities.isInvulnerable && 
/* 1673 */       !this.world.isClientSide) {
/* 1674 */       this.foodData.a(f);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public FoodMetaData getFoodData() {
/* 1681 */     return this.foodData;
/*      */   }
/*      */   
/*      */   public boolean q(boolean flag) {
/* 1685 */     return (this.abilities.isInvulnerable || flag || this.foodData.c());
/*      */   }
/*      */   
/*      */   public boolean eI() {
/* 1689 */     return (getHealth() > 0.0F && getHealth() < getMaxHealth());
/*      */   }
/*      */   
/*      */   public boolean eJ() {
/* 1693 */     return this.abilities.mayBuild;
/*      */   }
/*      */   
/*      */   public boolean a(BlockPosition blockposition, EnumDirection enumdirection, ItemStack itemstack) {
/* 1697 */     if (this.abilities.mayBuild) {
/* 1698 */       return true;
/*      */     }
/* 1700 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
/* 1701 */     ShapeDetectorBlock shapedetectorblock = new ShapeDetectorBlock(this.world, blockposition1, false);
/*      */     
/* 1703 */     return itemstack.b(this.world.p(), shapedetectorblock);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getExpValue(EntityHuman entityhuman) {
/* 1709 */     if (!this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY) && !isSpectator()) {
/* 1710 */       int i = this.expLevel * 7;
/*      */       
/* 1712 */       return (i > 100) ? 100 : i;
/*      */     } 
/* 1714 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean alwaysGivesExp() {
/* 1720 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean playStepSound() {
/* 1725 */     return (!this.abilities.isFlying && (!this.onGround || !bw()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateAbilities() {}
/*      */   
/*      */   public void a(EnumGamemode enumgamemode) {}
/*      */   
/*      */   public IChatBaseComponent getDisplayName() {
/* 1734 */     return new ChatComponentText(this.bJ.getName());
/*      */   }
/*      */   
/*      */   public InventoryEnderChest getEnderChest() {
/* 1738 */     return this.enderChest;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getEquipment(EnumItemSlot enumitemslot) {
/* 1743 */     return (enumitemslot == EnumItemSlot.MAINHAND) ? this.inventory.getItemInHand() : ((enumitemslot == EnumItemSlot.OFFHAND) ? this.inventory.extraSlots.get(0) : ((enumitemslot.a() == EnumItemSlot.Function.ARMOR) ? this.inventory.armor.get(enumitemslot.b()) : ItemStack.b));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSlot(EnumItemSlot enumitemslot, ItemStack itemstack) {
/* 1748 */     if (enumitemslot == EnumItemSlot.MAINHAND) {
/* 1749 */       b(itemstack);
/* 1750 */       this.inventory.items.set(this.inventory.itemInHandIndex, itemstack);
/* 1751 */     } else if (enumitemslot == EnumItemSlot.OFFHAND) {
/* 1752 */       b(itemstack);
/* 1753 */       this.inventory.extraSlots.set(0, itemstack);
/* 1754 */     } else if (enumitemslot.a() == EnumItemSlot.Function.ARMOR) {
/* 1755 */       b(itemstack);
/* 1756 */       this.inventory.armor.set(enumitemslot.b(), itemstack);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean g(ItemStack itemstack) {
/* 1762 */     b(itemstack);
/* 1763 */     return this.inventory.pickup(itemstack);
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterable<ItemStack> bm() {
/* 1768 */     return Lists.newArrayList((Object[])new ItemStack[] { getItemInMainHand(), getItemInOffHand() });
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterable<ItemStack> getArmorItems() {
/* 1773 */     return this.inventory.armor;
/*      */   }
/*      */   
/*      */   public boolean g(NBTTagCompound nbttagcompound) {
/* 1777 */     if (!isPassenger() && this.onGround && !isInWater()) {
/* 1778 */       if (getShoulderEntityLeft().isEmpty()) {
/* 1779 */         setShoulderEntityLeft(nbttagcompound);
/* 1780 */         this.e = this.world.getTime();
/* 1781 */         return true;
/* 1782 */       }  if (getShoulderEntityRight().isEmpty()) {
/* 1783 */         setShoulderEntityRight(nbttagcompound);
/* 1784 */         this.e = this.world.getTime();
/* 1785 */         return true;
/*      */       } 
/* 1787 */       return false;
/*      */     } 
/*      */     
/* 1790 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void releaseShoulderEntities() {
/* 1795 */     if (this.e + 20L < this.world.getTime()) {
/*      */       
/* 1797 */       if (spawnEntityFromShoulder(getShoulderEntityLeft())) {
/* 1798 */         setShoulderEntityLeft(new NBTTagCompound());
/*      */       }
/* 1800 */       if (spawnEntityFromShoulder(getShoulderEntityRight())) {
/* 1801 */         setShoulderEntityRight(new NBTTagCompound());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity releaseLeftShoulderEntity() {
/* 1810 */     Entity entity = spawnEntityFromShoulder0(getShoulderEntityLeft());
/* 1811 */     if (entity != null) {
/* 1812 */       setShoulderEntityLeft(new NBTTagCompound());
/*      */     }
/* 1814 */     return entity;
/*      */   }
/*      */   
/*      */   public Entity releaseRightShoulderEntity() {
/* 1818 */     Entity entity = spawnEntityFromShoulder0(getShoulderEntityRight());
/* 1819 */     if (entity != null) {
/* 1820 */       setShoulderEntityRight(new NBTTagCompound());
/*      */     }
/* 1822 */     return entity;
/*      */   }
/*      */   
/*      */   private boolean spawnEntityFromShoulder(NBTTagCompound nbttagcompound) {
/* 1826 */     return (spawnEntityFromShoulder0(nbttagcompound) != null);
/*      */   }
/*      */ 
/*      */   
/*      */   private Entity spawnEntityFromShoulder0(@Nullable NBTTagCompound nbttagcompound) {
/* 1831 */     if (!this.world.isClientSide && nbttagcompound != null && !nbttagcompound.isEmpty()) {
/* 1832 */       return EntityTypes.a(nbttagcompound, this.world).<Entity>map(entity -> {
/*      */             if (entity instanceof EntityTameableAnimal) {
/*      */               ((EntityTameableAnimal)entity).setOwnerUUID(this.uniqueID);
/*      */             }
/*      */             
/*      */             entity.setPosition(locX(), locY() + 0.699999988079071D, locZ());
/*      */             boolean addedToWorld = ((WorldServer)this.world).addEntitySerialized(entity, CreatureSpawnEvent.SpawnReason.SHOULDER_ENTITY);
/*      */             return addedToWorld ? entity : null;
/* 1840 */           }).orElse(null);
/*      */     }
/*      */     
/* 1843 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSwimming() {
/* 1852 */     return (!this.abilities.isFlying && !isSpectator() && super.isSwimming());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean bU() {
/* 1859 */     return !this.abilities.isFlying;
/*      */   }
/*      */   
/*      */   public Scoreboard getScoreboard() {
/* 1863 */     return this.world.getScoreboard();
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatBaseComponent getScoreboardDisplayName() {
/* 1868 */     IChatMutableComponent ichatmutablecomponent = ScoreboardTeam.a(getScoreboardTeam(), getDisplayName());
/*      */     
/* 1870 */     return a(ichatmutablecomponent);
/*      */   }
/*      */   
/*      */   private IChatMutableComponent a(IChatMutableComponent ichatmutablecomponent) {
/* 1874 */     String s = getProfile().getName();
/*      */     
/* 1876 */     return ichatmutablecomponent.format(chatmodifier -> chatmodifier.setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, "/tell " + s + " ")).setChatHoverable(ca()).setInsertion(s));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/* 1883 */     return getProfile().getName();
/*      */   }
/*      */ 
/*      */   
/*      */   public float b(EntityPose entitypose, EntitySize entitysize) {
/* 1888 */     switch (entitypose) {
/*      */       case SWIMMING:
/*      */       case FALL_FLYING:
/*      */       case SPIN_ATTACK:
/* 1892 */         return 0.4F;
/*      */       case CROUCHING:
/* 1894 */         return 1.27F;
/*      */     } 
/* 1896 */     return 1.62F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAbsorptionHearts(float f) {
/* 1902 */     if (f < 0.0F) {
/* 1903 */       f = 0.0F;
/*      */     }
/*      */     
/* 1906 */     getDataWatcher().set(c, Float.valueOf(f));
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAbsorptionHearts() {
/* 1911 */     return ((Float)getDataWatcher().<Float>get(c)).floatValue();
/*      */   }
/*      */   
/*      */   public static UUID a(GameProfile gameprofile) {
/* 1915 */     UUID uuid = gameprofile.getId();
/*      */     
/* 1917 */     if (uuid == null) {
/* 1918 */       uuid = getOfflineUUID(gameprofile.getName());
/*      */     }
/*      */     
/* 1921 */     return uuid;
/*      */   }
/*      */   
/*      */   public static UUID getOfflineUUID(String s) {
/* 1925 */     return UUID.nameUUIDFromBytes(("OfflinePlayer:" + s).getBytes(StandardCharsets.UTF_8));
/*      */   }
/*      */   
/*      */   public boolean a_(int i, ItemStack itemstack) {
/*      */     EnumItemSlot enumitemslot;
/* 1930 */     if (i >= 0 && i < this.inventory.items.size()) {
/* 1931 */       this.inventory.setItem(i, itemstack);
/* 1932 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1936 */     if (i == 100 + EnumItemSlot.HEAD.b()) {
/* 1937 */       enumitemslot = EnumItemSlot.HEAD;
/* 1938 */     } else if (i == 100 + EnumItemSlot.CHEST.b()) {
/* 1939 */       enumitemslot = EnumItemSlot.CHEST;
/* 1940 */     } else if (i == 100 + EnumItemSlot.LEGS.b()) {
/* 1941 */       enumitemslot = EnumItemSlot.LEGS;
/* 1942 */     } else if (i == 100 + EnumItemSlot.FEET.b()) {
/* 1943 */       enumitemslot = EnumItemSlot.FEET;
/*      */     } else {
/* 1945 */       enumitemslot = null;
/*      */     } 
/*      */     
/* 1948 */     if (i == 98) {
/* 1949 */       setSlot(EnumItemSlot.MAINHAND, itemstack);
/* 1950 */       return true;
/* 1951 */     }  if (i == 99) {
/* 1952 */       setSlot(EnumItemSlot.OFFHAND, itemstack);
/* 1953 */       return true;
/* 1954 */     }  if (enumitemslot == null) {
/* 1955 */       int j = i - 200;
/*      */       
/* 1957 */       if (j >= 0 && j < this.enderChest.getSize()) {
/* 1958 */         this.enderChest.setItem(j, itemstack);
/* 1959 */         return true;
/*      */       } 
/* 1961 */       return false;
/*      */     } 
/*      */     
/* 1964 */     if (!itemstack.isEmpty()) {
/* 1965 */       if (!(itemstack.getItem() instanceof ItemArmor) && !(itemstack.getItem() instanceof ItemElytra)) {
/* 1966 */         if (enumitemslot != EnumItemSlot.HEAD) {
/* 1967 */           return false;
/*      */         }
/* 1969 */       } else if (EntityInsentient.j(itemstack) != enumitemslot) {
/* 1970 */         return false;
/*      */       } 
/*      */     }
/*      */     
/* 1974 */     this.inventory.setItem(enumitemslot.b() + this.inventory.items.size(), itemstack);
/* 1975 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFireTicks(int i) {
/* 1982 */     super.setFireTicks(this.abilities.isInvulnerable ? Math.min(i, 1) : i);
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumMainHand getMainHand() {
/* 1987 */     return (((Byte)this.datawatcher.<Byte>get(bj)).byteValue() == 0) ? EnumMainHand.LEFT : EnumMainHand.RIGHT;
/*      */   }
/*      */   
/*      */   public void a(EnumMainHand enummainhand) {
/* 1991 */     this.datawatcher.set(bj, Byte.valueOf((byte)((enummainhand == EnumMainHand.LEFT) ? 0 : 1)));
/*      */   }
/*      */   
/*      */   public NBTTagCompound getShoulderEntityLeft() {
/* 1995 */     return this.datawatcher.<NBTTagCompound>get(bk);
/*      */   }
/*      */   
/*      */   public void setShoulderEntityLeft(NBTTagCompound nbttagcompound) {
/* 1999 */     this.datawatcher.set(bk, nbttagcompound);
/*      */   }
/*      */   
/*      */   public NBTTagCompound getShoulderEntityRight() {
/* 2003 */     return this.datawatcher.<NBTTagCompound>get(bl);
/*      */   }
/*      */   
/*      */   public void setShoulderEntityRight(NBTTagCompound nbttagcompound) {
/* 2007 */     this.datawatcher.set(bl, nbttagcompound);
/*      */   }
/*      */   public float getCooldownPeriod() {
/* 2010 */     return eQ();
/*      */   } public float eQ() {
/* 2012 */     return (float)(1.0D / b(GenericAttributes.ATTACK_SPEED) * 20.0D);
/*      */   }
/*      */   
/*      */   public float getAttackCooldown(float f) {
/* 2016 */     return MathHelper.a((this.at + f) / eQ(), 0.0F, 1.0F);
/*      */   }
/*      */   
/*      */   public void resetAttackCooldown() {
/* 2020 */     this.at = 0;
/*      */   }
/*      */   
/*      */   public ItemCooldown getCooldownTracker() {
/* 2024 */     return this.bM;
/*      */   }
/*      */ 
/*      */   
/*      */   protected float getBlockSpeedFactor() {
/* 2029 */     return (!this.abilities.isFlying && !isGliding()) ? super.getBlockSpeedFactor() : 1.0F;
/*      */   }
/*      */   
/*      */   public float eT() {
/* 2033 */     return (float)b(GenericAttributes.LUCK);
/*      */   }
/*      */   
/*      */   public boolean isCreativeAndOp() {
/* 2037 */     return (this.abilities.canInstantlyBuild && y() >= 2);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean e(ItemStack itemstack) {
/* 2042 */     EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/*      */     
/* 2044 */     return getEquipment(enumitemslot).isEmpty();
/*      */   }
/*      */ 
/*      */   
/*      */   public EntitySize a(EntityPose entitypose) {
/* 2049 */     return b.getOrDefault(entitypose, bh);
/*      */   }
/*      */ 
/*      */   
/*      */   public ImmutableList<EntityPose> ei() {
/* 2054 */     return ImmutableList.of(EntityPose.STANDING, EntityPose.CROUCHING, EntityPose.SWIMMING);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean tryReadyArrow(ItemStack bow, ItemStack itemstack) {
/* 2059 */     return (!(this instanceof EntityPlayer) || (new PlayerReadyArrowEvent((Player)((EntityPlayer)this)
/*      */         
/* 2061 */         .getBukkitEntity(), 
/* 2062 */         (ItemStack)CraftItemStack.asCraftMirror(bow), 
/* 2063 */         (ItemStack)CraftItemStack.asCraftMirror(itemstack)))
/* 2064 */       .callEvent());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack f(ItemStack itemstack) {
/* 2070 */     if (!(itemstack.getItem() instanceof ItemProjectileWeapon)) {
/* 2071 */       return ItemStack.b;
/*      */     }
/* 2073 */     Predicate<ItemStack> predicate = ((ItemProjectileWeapon)itemstack.getItem()).e();
/* 2074 */     ItemStack itemstack1 = ItemProjectileWeapon.a(this, predicate);
/*      */     
/* 2076 */     if (!itemstack1.isEmpty()) {
/* 2077 */       return itemstack1;
/*      */     }
/* 2079 */     predicate = ((ItemProjectileWeapon)itemstack.getItem()).b();
/*      */     
/* 2081 */     for (int i = 0; i < this.inventory.getSize(); i++) {
/* 2082 */       ItemStack itemstack2 = this.inventory.getItem(i);
/*      */       
/* 2084 */       if (predicate.test(itemstack2) && tryReadyArrow(itemstack, itemstack2)) {
/* 2085 */         return itemstack2;
/*      */       }
/*      */     } 
/*      */     
/* 2089 */     return this.abilities.canInstantlyBuild ? new ItemStack(Items.ARROW) : ItemStack.b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack a(World world, ItemStack itemstack) {
/* 2096 */     getFoodData().a(itemstack.getItem(), itemstack);
/* 2097 */     b(StatisticList.ITEM_USED.b(itemstack.getItem()));
/* 2098 */     world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
/* 2099 */     if (this instanceof EntityPlayer) {
/* 2100 */       CriterionTriggers.z.a((EntityPlayer)this, itemstack);
/*      */     }
/*      */     
/* 2103 */     return super.a(world, itemstack);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean b(IBlockData iblockdata) {
/* 2108 */     return (this.abilities.isFlying || super.b(iblockdata));
/*      */   }
/*      */   public abstract boolean isSpectator();
/*      */   public abstract boolean isCreative();
/*      */   
/* 2113 */   public enum EnumBedResult { NOT_POSSIBLE_HERE, NOT_POSSIBLE_NOW((String)new ChatMessage("block.minecraft.bed.no_sleep")), TOO_FAR_AWAY((String)new ChatMessage("block.minecraft.bed.too_far_away")), OBSTRUCTED((String)new ChatMessage("block.minecraft.bed.obstructed")), OTHER_PROBLEM, NOT_SAFE((String)new ChatMessage("block.minecraft.bed.not_safe"));
/*      */     
/*      */     @Nullable
/*      */     private final IChatBaseComponent g;
/*      */     
/*      */     EnumBedResult() {
/* 2119 */       this.g = null;
/*      */     }
/*      */     
/*      */     EnumBedResult(IChatBaseComponent ichatbasecomponent) {
/* 2123 */       this.g = ichatbasecomponent;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     public IChatBaseComponent a() {
/* 2128 */       return this.g;
/*      */     } }
/*      */ 
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHuman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */