/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.AbstractVillager;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.event.entity.VillagerReplenishTradeEvent;
/*     */ import org.bukkit.inventory.MerchantRecipe;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class EntityVillager
/*     */   extends EntityVillagerAbstract
/*     */   implements ReputationHandler, VillagerDataHolder {
/*  34 */   private static final DataWatcherObject<VillagerData> br = DataWatcher.a((Class)EntityVillager.class, DataWatcherRegistry.q);
/*  35 */   public static final Map<Item, Integer> bp = (Map<Item, Integer>)ImmutableMap.of(Items.BREAD, Integer.valueOf(4), Items.POTATO, Integer.valueOf(1), Items.CARROT, Integer.valueOf(1), Items.BEETROOT, Integer.valueOf(1)); private int bt; private boolean bu; @Nullable
/*  36 */   private EntityHuman bv; private byte bx; private final Reputation by; private long bz; private static final Set<Item> bs = (Set<Item>)ImmutableSet.of(Items.BREAD, Items.POTATO, Items.CARROT, Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT, (Object[])new Item[] { Items.BEETROOT_SEEDS });
/*     */   
/*     */   private long bA;
/*     */   
/*     */   private int bB;
/*     */   private long bC;
/*     */   private int bD;
/*     */   private long bE;
/*     */   private boolean bF;
/*     */   
/*     */   public int getRestocksToday() {
/*  47 */     return this.bD; } public void setRestocksToday(int restocksToday) { this.bD = restocksToday; }
/*     */ 
/*     */   
/*  50 */   private static final ImmutableList<MemoryModuleType<?>> bG = ImmutableList.of(MemoryModuleType.HOME, MemoryModuleType.JOB_SITE, MemoryModuleType.POTENTIAL_JOB_SITE, MemoryModuleType.MEETING_POINT, MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.VISIBLE_VILLAGER_BABIES, MemoryModuleType.NEAREST_PLAYERS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleType.WALK_TARGET, (Object[])new MemoryModuleType[] { MemoryModuleType.LOOK_TARGET, MemoryModuleType.INTERACTION_TARGET, MemoryModuleType.BREED_TARGET, MemoryModuleType.PATH, MemoryModuleType.DOORS_TO_CLOSE, MemoryModuleType.NEAREST_BED, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.NEAREST_HOSTILE, MemoryModuleType.SECONDARY_JOB_SITE, MemoryModuleType.HIDING_PLACE, MemoryModuleType.HEARD_BELL_TIME, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.LAST_SLEPT, MemoryModuleType.LAST_WOKEN, MemoryModuleType.LAST_WORKED_AT_POI, MemoryModuleType.GOLEM_DETECTED_RECENTLY });
/*  51 */   private static final ImmutableList<SensorType<? extends Sensor<? super EntityVillager>>> bH = ImmutableList.of(SensorType.c, SensorType.d, SensorType.b, SensorType.e, SensorType.f, SensorType.g, SensorType.h, SensorType.i, SensorType.j); public static final Map<MemoryModuleType<GlobalPos>, BiPredicate<EntityVillager, VillagePlaceType>> bq; static {
/*  52 */     bq = (Map<MemoryModuleType<GlobalPos>, BiPredicate<EntityVillager, VillagePlaceType>>)ImmutableMap.of(MemoryModuleType.HOME, (entityvillager, villageplacetype) -> (villageplacetype == VillagePlaceType.r), MemoryModuleType.JOB_SITE, (entityvillager, villageplacetype) -> (entityvillager.getVillagerData().getProfession().b() == villageplacetype), MemoryModuleType.POTENTIAL_JOB_SITE, (entityvillager, villageplacetype) -> VillagePlaceType.a.test(villageplacetype), MemoryModuleType.MEETING_POINT, (entityvillager, villageplacetype) -> (villageplacetype == VillagePlaceType.s));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityVillager(EntityTypes<? extends EntityVillager> entitytypes, World world) {
/*  63 */     this(entitytypes, world, VillagerType.PLAINS);
/*     */   }
/*     */   
/*     */   public EntityVillager(EntityTypes<? extends EntityVillager> entitytypes, World world, VillagerType villagertype) {
/*  67 */     super((EntityTypes)entitytypes, world);
/*  68 */     this.by = new Reputation();
/*  69 */     ((Navigation)getNavigation()).a(true);
/*  70 */     getNavigation().d(true);
/*  71 */     setCanPickupLoot(true);
/*  72 */     setVillagerData(getVillagerData().withType(villagertype).withProfession(VillagerProfession.NONE));
/*     */   }
/*     */ 
/*     */   
/*     */   public BehaviorController<EntityVillager> getBehaviorController() {
/*  77 */     return (BehaviorController)super.getBehaviorController();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController.b<EntityVillager> cJ() {
/*  82 */     return BehaviorController.a((Collection<? extends MemoryModuleType<?>>)bG, (Collection<? extends SensorType<? extends Sensor<? super EntityVillager>>>)bH);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController<?> a(Dynamic<?> dynamic) {
/*  87 */     BehaviorController<EntityVillager> behaviorcontroller = cJ().a(dynamic);
/*     */     
/*  89 */     a(behaviorcontroller);
/*  90 */     return behaviorcontroller;
/*     */   }
/*     */   
/*     */   public void c(WorldServer worldserver) {
/*  94 */     BehaviorController<EntityVillager> behaviorcontroller = getBehaviorController();
/*     */     
/*  96 */     behaviorcontroller.b(worldserver, this);
/*  97 */     this.bg = behaviorcontroller.h();
/*  98 */     a(getBehaviorController());
/*     */   }
/*     */   
/*     */   private void a(BehaviorController<EntityVillager> behaviorcontroller) {
/* 102 */     VillagerProfession villagerprofession = getVillagerData().getProfession();
/*     */     
/* 104 */     if (isBaby()) {
/* 105 */       behaviorcontroller.setSchedule(Schedule.VILLAGER_BABY);
/* 106 */       behaviorcontroller.a(Activity.PLAY, Behaviors.a(0.5F));
/*     */     } else {
/* 108 */       behaviorcontroller.setSchedule(Schedule.VILLAGER_DEFAULT);
/* 109 */       behaviorcontroller.a(Activity.WORK, Behaviors.b(villagerprofession, 0.5F), (Set<Pair<MemoryModuleType<?>, MemoryStatus>>)ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_PRESENT)));
/*     */     } 
/*     */     
/* 112 */     behaviorcontroller.a(Activity.CORE, Behaviors.a(villagerprofession, 0.5F));
/* 113 */     behaviorcontroller.a(Activity.MEET, Behaviors.d(villagerprofession, 0.5F), (Set<Pair<MemoryModuleType<?>, MemoryStatus>>)ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT)));
/* 114 */     behaviorcontroller.a(Activity.REST, Behaviors.c(villagerprofession, 0.5F));
/* 115 */     behaviorcontroller.a(Activity.IDLE, Behaviors.e(villagerprofession, 0.5F));
/* 116 */     behaviorcontroller.a(Activity.PANIC, Behaviors.f(villagerprofession, 0.5F));
/* 117 */     behaviorcontroller.a(Activity.PRE_RAID, Behaviors.g(villagerprofession, 0.5F));
/* 118 */     behaviorcontroller.a(Activity.RAID, Behaviors.h(villagerprofession, 0.5F));
/* 119 */     behaviorcontroller.a(Activity.HIDE, Behaviors.i(villagerprofession, 0.5F));
/* 120 */     behaviorcontroller.a((Set<Activity>)ImmutableSet.of(Activity.CORE));
/* 121 */     behaviorcontroller.b(Activity.IDLE);
/* 122 */     behaviorcontroller.a(Activity.IDLE);
/* 123 */     behaviorcontroller.a(this.world.getDayTime(), this.world.getTime());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m() {
/* 128 */     super.m();
/* 129 */     if (this.world instanceof WorldServer) {
/* 130 */       c((WorldServer)this.world);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeProvider.Builder eY() {
/* 136 */     return EntityInsentient.p().a(GenericAttributes.MOVEMENT_SPEED, 0.5D).a(GenericAttributes.FOLLOW_RANGE, 48.0D);
/*     */   }
/*     */   
/*     */   public boolean eZ() {
/* 140 */     return this.bF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inactiveTick() {
/* 148 */     if (getUnhappy() > 0) {
/* 149 */       setUnhappy(getUnhappy() - 1);
/*     */     }
/* 151 */     if (doAITick()) {
/* 152 */       if (this.world.spigotConfig.tickInactiveVillagers) {
/* 153 */         mobTick();
/*     */       } else {
/* 155 */         mobTick(true);
/*     */       } 
/*     */     }
/* 158 */     doReputationTick();
/*     */ 
/*     */     
/* 161 */     super.inactiveTick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 166 */     mobTick(false);
/*     */   } protected void mobTick(boolean inactive) {
/* 168 */     this.world.getMethodProfiler().enter("villagerBrain");
/* 169 */     if (!inactive) getBehaviorController().a((WorldServer)this.world, this); 
/* 170 */     this.world.getMethodProfiler().exit();
/* 171 */     if (this.bF) {
/* 172 */       this.bF = false;
/*     */     }
/*     */     
/* 175 */     if (!eN() && this.bt > 0) {
/* 176 */       this.bt--;
/* 177 */       if (this.bt <= 0) {
/* 178 */         if (this.bu) {
/* 179 */           populateTrades();
/* 180 */           this.bu = false;
/*     */         } 
/*     */         
/* 183 */         addEffect(new MobEffect(MobEffects.REGENERATION, 200, 0), EntityPotionEffectEvent.Cause.VILLAGER_TRADE);
/*     */       } 
/*     */     } 
/*     */     
/* 187 */     if (this.bv != null && this.world instanceof WorldServer) {
/* 188 */       ((WorldServer)this.world).a(ReputationEvent.e, this.bv, this);
/* 189 */       this.world.broadcastEntityEffect(this, (byte)14);
/* 190 */       this.bv = null;
/*     */     } 
/*     */     
/* 193 */     if (!inactive && !isNoAI() && this.random.nextInt(100) == 0) {
/* 194 */       Raid raid = ((WorldServer)this.world).b_(getChunkCoordinates());
/*     */       
/* 196 */       if (raid != null && raid.v() && !raid.a()) {
/* 197 */         this.world.broadcastEntityEffect(this, (byte)42);
/*     */       }
/*     */     } 
/*     */     
/* 201 */     if (getVillagerData().getProfession() == VillagerProfession.NONE && eN()) {
/* 202 */       eT();
/*     */     }
/* 204 */     if (inactive)
/*     */       return; 
/* 206 */     super.mobTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 211 */     super.tick();
/* 212 */     if (eK() > 0) {
/* 213 */       s(eK() - 1);
/*     */     }
/*     */     
/* 216 */     fw();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 221 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 223 */     if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && isAlive() && !eN() && !isSleeping()) {
/* 224 */       if (isBaby()) {
/* 225 */         fk();
/* 226 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/* 228 */       boolean flag = getOffers().isEmpty();
/*     */       
/* 230 */       if (enumhand == EnumHand.MAIN_HAND) {
/* 231 */         if (flag && !this.world.isClientSide) {
/* 232 */           fk();
/*     */         }
/*     */         
/* 235 */         entityhuman.a(StatisticList.TALKED_TO_VILLAGER);
/*     */       } 
/*     */       
/* 238 */       if (flag) {
/* 239 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       }
/* 241 */       if (!this.world.isClientSide && !this.trades.isEmpty()) {
/* 242 */         h(entityhuman);
/*     */       }
/*     */       
/* 245 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/*     */ 
/*     */     
/* 249 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */   
/*     */   private void fk() {
/* 254 */     s(40);
/* 255 */     if (!this.world.s_()) {
/* 256 */       playSound(SoundEffects.ENTITY_VILLAGER_NO, getSoundVolume(), dG());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void h(EntityHuman entityhuman) {
/* 262 */     i(entityhuman);
/* 263 */     setTradingPlayer(entityhuman);
/* 264 */     openTrade(entityhuman, getScoreboardDisplayName(), getVillagerData().getLevel());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTradingPlayer(@Nullable EntityHuman entityhuman) {
/* 269 */     boolean flag = (getTrader() != null && entityhuman == null);
/*     */     
/* 271 */     super.setTradingPlayer(entityhuman);
/* 272 */     if (flag) {
/* 273 */       eT();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void eT() {
/* 280 */     super.eT();
/* 281 */     fl();
/*     */   }
/*     */   
/*     */   private void fl() {
/* 285 */     Iterator<MerchantRecipe> iterator = getOffers().iterator();
/*     */     
/* 287 */     while (iterator.hasNext()) {
/* 288 */       MerchantRecipe merchantrecipe = iterator.next();
/*     */       
/* 290 */       merchantrecipe.setSpecialPrice();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean fa() {
/* 297 */     return true;
/*     */   }
/*     */   
/*     */   public void fb() {
/* 301 */     fp();
/* 302 */     Iterator<MerchantRecipe> iterator = getOffers().iterator();
/*     */     
/* 304 */     while (iterator.hasNext()) {
/* 305 */       MerchantRecipe merchantrecipe = iterator.next();
/*     */       
/* 307 */       merchantrecipe.resetUses();
/*     */     } 
/*     */     
/* 310 */     this.bC = this.world.getTime();
/* 311 */     this.bD++;
/*     */   }
/*     */   private boolean fm() {
/*     */     MerchantRecipe merchantrecipe;
/* 315 */     Iterator<MerchantRecipe> iterator = getOffers().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 320 */       if (!iterator.hasNext()) {
/* 321 */         return false;
/*     */       }
/*     */       
/* 324 */       merchantrecipe = iterator.next();
/* 325 */     } while (!merchantrecipe.r());
/*     */     
/* 327 */     return true;
/*     */   }
/*     */   
/*     */   private boolean fn() {
/* 331 */     return (this.bD == 0 || (this.bD < 2 && this.world.getTime() > this.bC + 2400L));
/*     */   }
/*     */   public boolean fc() {
/*     */     int m;
/* 335 */     long i = this.bC + 12000L;
/* 336 */     long j = this.world.getTime();
/* 337 */     boolean flag = (j > i);
/* 338 */     long k = this.world.getDayTime();
/*     */     
/* 340 */     if (this.bE > 0L) {
/* 341 */       long l = this.bE / 24000L;
/* 342 */       long i1 = k / 24000L;
/*     */       
/* 344 */       m = flag | ((i1 > l) ? 1 : 0);
/*     */     } 
/*     */     
/* 347 */     this.bE = k;
/* 348 */     if (m != 0) {
/* 349 */       this.bC = j;
/* 350 */       fx();
/*     */     } 
/*     */     
/* 353 */     return (fn() && fm());
/*     */   }
/*     */   
/*     */   private void fo() {
/* 357 */     int i = 2 - this.bD;
/*     */     
/* 359 */     if (i > 0) {
/* 360 */       Iterator<MerchantRecipe> iterator = getOffers().iterator();
/*     */       
/* 362 */       while (iterator.hasNext()) {
/* 363 */         MerchantRecipe merchantrecipe = iterator.next();
/*     */         
/* 365 */         merchantrecipe.resetUses();
/*     */       } 
/*     */     } 
/*     */     
/* 369 */     for (int j = 0; j < i; j++) {
/* 370 */       fp();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void fp() {
/* 376 */     Iterator<MerchantRecipe> iterator = getOffers().iterator();
/*     */     
/* 378 */     while (iterator.hasNext()) {
/* 379 */       MerchantRecipe merchantrecipe = iterator.next();
/*     */       
/* 381 */       merchantrecipe.e();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void i(EntityHuman entityhuman) {
/* 387 */     int i = g(entityhuman);
/*     */     
/* 389 */     if (i != 0) {
/* 390 */       Iterator<MerchantRecipe> iterator = getOffers().iterator();
/*     */       
/* 392 */       while (iterator.hasNext()) {
/* 393 */         MerchantRecipe merchantrecipe = iterator.next();
/*     */ 
/*     */         
/* 396 */         int bonus = -MathHelper.d(i * merchantrecipe.getPriceMultiplier());
/* 397 */         VillagerReplenishTradeEvent event = new VillagerReplenishTradeEvent((AbstractVillager)getBukkitEntity(), (MerchantRecipe)merchantrecipe.asBukkit(), bonus);
/* 398 */         Bukkit.getPluginManager().callEvent((Event)event);
/* 399 */         if (!event.isCancelled()) {
/* 400 */           merchantrecipe.increaseSpecialPrice(event.getBonus());
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 406 */     if (entityhuman.hasEffect(MobEffects.HERO_OF_THE_VILLAGE)) {
/* 407 */       MobEffect mobeffect = entityhuman.getEffect(MobEffects.HERO_OF_THE_VILLAGE);
/* 408 */       int j = mobeffect.getAmplifier();
/* 409 */       Iterator<MerchantRecipe> iterator1 = getOffers().iterator();
/*     */       
/* 411 */       while (iterator1.hasNext()) {
/* 412 */         MerchantRecipe merchantrecipe1 = iterator1.next();
/* 413 */         double d0 = 0.3D + 0.0625D * j;
/* 414 */         int k = (int)Math.floor(d0 * merchantrecipe1.a().getCount());
/*     */         
/* 416 */         merchantrecipe1.increaseSpecialPrice(-Math.max(k, 1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 424 */     super.initDatawatcher();
/* 425 */     this.datawatcher.register(br, new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 430 */     super.saveData(nbttagcompound);
/* 431 */     DataResult<NBTBase> dataresult = VillagerData.a.encodeStart(DynamicOpsNBT.a, getVillagerData());
/* 432 */     Logger logger = LOGGER;
/*     */     
/* 434 */     logger.getClass();
/* 435 */     Objects.requireNonNull(logger); dataresult.resultOrPartial(logger::error).ifPresent(nbtbase -> nbttagcompound.set("VillagerData", nbtbase));
/*     */ 
/*     */     
/* 438 */     nbttagcompound.setByte("FoodLevel", this.bx);
/* 439 */     nbttagcompound.set("Gossips", (NBTBase)this.by.<T>a(DynamicOpsNBT.a).getValue());
/* 440 */     nbttagcompound.setInt("Xp", this.bB);
/* 441 */     nbttagcompound.setLong("LastRestock", this.bC);
/* 442 */     nbttagcompound.setLong("LastGossipDecay", this.bA);
/* 443 */     nbttagcompound.setInt("RestocksToday", this.bD);
/* 444 */     if (this.bF) {
/* 445 */       nbttagcompound.setBoolean("AssignProfessionWhenSpawned", true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 452 */     super.loadData(nbttagcompound);
/* 453 */     if (nbttagcompound.hasKeyOfType("VillagerData", 10)) {
/* 454 */       DataResult<VillagerData> dataresult = VillagerData.a.parse(new Dynamic(DynamicOpsNBT.a, nbttagcompound.get("VillagerData")));
/* 455 */       Logger logger = LOGGER;
/*     */       
/* 457 */       logger.getClass();
/* 458 */       Objects.requireNonNull(logger); dataresult.resultOrPartial(logger::error).ifPresent(this::setVillagerData);
/*     */     } 
/*     */     
/* 461 */     if (nbttagcompound.hasKeyOfType("Offers", 10)) {
/* 462 */       this.trades = new MerchantRecipeList(nbttagcompound.getCompound("Offers"));
/*     */     }
/*     */     
/* 465 */     if (nbttagcompound.hasKeyOfType("FoodLevel", 1)) {
/* 466 */       this.bx = nbttagcompound.getByte("FoodLevel");
/*     */     }
/*     */     
/* 469 */     NBTTagList nbttaglist = nbttagcompound.getList("Gossips", 10);
/*     */     
/* 471 */     this.by.a(new Dynamic(DynamicOpsNBT.a, nbttaglist));
/* 472 */     if (nbttagcompound.hasKeyOfType("Xp", 3)) {
/* 473 */       this.bB = nbttagcompound.getInt("Xp");
/*     */     }
/*     */     
/* 476 */     this.bC = nbttagcompound.getLong("LastRestock");
/* 477 */     this.bA = nbttagcompound.getLong("LastGossipDecay");
/* 478 */     setCanPickupLoot(true);
/* 479 */     if (this.world instanceof WorldServer) {
/* 480 */       c((WorldServer)this.world);
/*     */     }
/*     */     
/* 483 */     this.bD = nbttagcompound.getInt("RestocksToday");
/* 484 */     if (nbttagcompound.hasKey("AssignProfessionWhenSpawned")) {
/* 485 */       this.bF = nbttagcompound.getBoolean("AssignProfessionWhenSpawned");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/* 492 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAmbient() {
/* 498 */     return isSleeping() ? null : (eN() ? SoundEffects.ENTITY_VILLAGER_TRADE : SoundEffects.ENTITY_VILLAGER_AMBIENT);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 503 */     return SoundEffects.ENTITY_VILLAGER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 508 */     return SoundEffects.ENTITY_VILLAGER_DEATH;
/*     */   }
/*     */   
/*     */   public void fd() {
/* 512 */     SoundEffect soundeffect = getVillagerData().getProfession().e();
/*     */     
/* 514 */     if (soundeffect != null) {
/* 515 */       playSound(soundeffect, getSoundVolume(), dG());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillagerData(VillagerData villagerdata) {
/* 521 */     VillagerData villagerdata1 = getVillagerData();
/*     */     
/* 523 */     if (villagerdata1.getProfession() != villagerdata.getProfession()) {
/* 524 */       this.trades = null;
/*     */     }
/*     */     
/* 527 */     this.datawatcher.set(br, villagerdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public VillagerData getVillagerData() {
/* 532 */     return this.datawatcher.<VillagerData>get(br);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(MerchantRecipe merchantrecipe) {
/* 537 */     int i = 3 + this.random.nextInt(4);
/*     */     
/* 539 */     this.bB += merchantrecipe.getXp();
/* 540 */     this.bv = getTrader();
/* 541 */     if (ft()) {
/* 542 */       this.bt = 40;
/* 543 */       this.bu = true;
/* 544 */       i += 5;
/*     */     } 
/*     */     
/* 547 */     if (merchantrecipe.isRewardExp()) {
/* 548 */       this.world.addEntity(new EntityExperienceOrb(this.world, locX(), locY() + 0.5D, locZ(), i, ExperienceOrb.SpawnReason.VILLAGER_TRADE, getTrader(), this));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastDamager(@Nullable EntityLiving entityliving) {
/* 555 */     if (entityliving != null && this.world instanceof WorldServer) {
/* 556 */       ((WorldServer)this.world).a(ReputationEvent.c, entityliving, this);
/* 557 */       if (isAlive() && entityliving instanceof EntityHuman) {
/* 558 */         this.world.broadcastEntityEffect(this, (byte)13);
/*     */       }
/*     */     } 
/*     */     
/* 562 */     super.setLastDamager(entityliving);
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 567 */     if (SpigotConfig.logVillagerDeaths) LOGGER.info("Villager {} died, message: '{}'", this, damagesource.getLocalizedDeathMessage(this).getString()); 
/* 568 */     Entity entity = damagesource.getEntity();
/*     */     
/* 570 */     if (entity != null) {
/* 571 */       a(entity);
/*     */     }
/*     */     
/* 574 */     fq();
/* 575 */     super.die(damagesource);
/*     */   }
/*     */   
/*     */   private void fq() {
/* 579 */     a(MemoryModuleType.HOME);
/* 580 */     a(MemoryModuleType.JOB_SITE);
/* 581 */     a(MemoryModuleType.POTENTIAL_JOB_SITE);
/* 582 */     a(MemoryModuleType.MEETING_POINT);
/*     */   }
/*     */   
/*     */   private void a(Entity entity) {
/* 586 */     if (this.world instanceof WorldServer) {
/* 587 */       Optional<List<EntityLiving>> optional = this.bg.getMemory(MemoryModuleType.VISIBLE_MOBS);
/*     */       
/* 589 */       if (optional.isPresent()) {
/* 590 */         WorldServer worldserver = (WorldServer)this.world;
/*     */         
/* 592 */         ((List)optional.get()).stream().filter(entityliving -> entityliving instanceof ReputationHandler)
/*     */           
/* 594 */           .forEach(entityliving -> worldserver.a(ReputationEvent.d, entity, (ReputationHandler)entityliving));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(MemoryModuleType<GlobalPos> memorymoduletype) {
/* 602 */     if (this.world instanceof WorldServer) {
/* 603 */       MinecraftServer minecraftserver = ((WorldServer)this.world).getMinecraftServer();
/*     */       
/* 605 */       this.bg.<GlobalPos>getMemory(memorymoduletype).ifPresent(globalpos -> {
/*     */             WorldServer worldserver = minecraftserver.getWorldServer(globalpos.getDimensionManager());
/*     */             if (worldserver != null) {
/*     */               VillagePlace villageplace = worldserver.y();
/*     */               Optional<VillagePlaceType> optional = villageplace.c(globalpos.getBlockPosition());
/*     */               BiPredicate<EntityVillager, VillagePlaceType> bipredicate = bq.get(memorymoduletype);
/*     */               if (optional.isPresent() && bipredicate.test(this, optional.get())) {
/*     */                 villageplace.b(globalpos.getBlockPosition());
/*     */                 PacketDebug.c(worldserver, globalpos.getBlockPosition());
/*     */               } 
/*     */             } 
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBreed() {
/* 625 */     return (this.bx + fv() >= 12 && getAge() == 0);
/*     */   }
/*     */   
/*     */   private boolean fr() {
/* 629 */     return (this.bx < 12);
/*     */   }
/*     */   
/*     */   private void fs() {
/* 633 */     if (fr() && fv() != 0) {
/* 634 */       for (int i = 0; i < getInventory().getSize(); i++) {
/* 635 */         ItemStack itemstack = getInventory().getItem(i);
/*     */         
/* 637 */         if (!itemstack.isEmpty()) {
/* 638 */           Integer integer = bp.get(itemstack.getItem());
/*     */           
/* 640 */           if (integer != null) {
/* 641 */             int j = itemstack.getCount();
/*     */             
/* 643 */             for (int k = j; k > 0; k--) {
/* 644 */               this.bx = (byte)(this.bx + integer.intValue());
/* 645 */               getInventory().splitStack(i, 1);
/* 646 */               if (!fr()) {
/*     */                 return;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int g(EntityHuman entityhuman) {
/* 658 */     return this.by.a(entityhuman.getUniqueID(), reputationtype -> true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void v(int i) {
/* 664 */     this.bx = (byte)(this.bx - i);
/*     */   }
/*     */   
/*     */   public void ff() {
/* 668 */     fs();
/* 669 */     v(12);
/*     */   }
/*     */   
/*     */   public void b(MerchantRecipeList merchantrecipelist) {
/* 673 */     this.trades = merchantrecipelist;
/*     */   }
/*     */   
/*     */   private boolean ft() {
/* 677 */     int i = getVillagerData().getLevel();
/*     */     
/* 679 */     return (VillagerData.d(i) && this.bB >= VillagerData.c(i));
/*     */   }
/*     */   
/*     */   public void populateTrades() {
/* 683 */     setVillagerData(getVillagerData().withLevel(getVillagerData().getLevel() + 1));
/* 684 */     eW();
/*     */   }
/*     */ 
/*     */   
/*     */   protected IChatBaseComponent bI() {
/* 689 */     return new ChatMessage(getEntityType().f() + '.' + IRegistry.VILLAGER_PROFESSION.getKey(getVillagerData().getProfession()).getKey());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 695 */     if (enummobspawn == EnumMobSpawn.BREEDING) {
/* 696 */       setVillagerData(getVillagerData().withProfession(VillagerProfession.NONE));
/*     */     }
/*     */     
/* 699 */     if (enummobspawn == EnumMobSpawn.COMMAND || enummobspawn == EnumMobSpawn.SPAWN_EGG || enummobspawn == EnumMobSpawn.SPAWNER || enummobspawn == EnumMobSpawn.DISPENSER) {
/* 700 */       setVillagerData(getVillagerData().withType(VillagerType.a(worldaccess.i(getChunkCoordinates()))));
/*     */     }
/*     */     
/* 703 */     if (enummobspawn == EnumMobSpawn.STRUCTURE) {
/* 704 */       this.bF = true;
/*     */     }
/*     */     
/* 707 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   public EntityVillager createChild(WorldServer worldserver, EntityAgeable entityageable) {
/*     */     VillagerType villagertype;
/* 712 */     double d0 = this.random.nextDouble();
/*     */ 
/*     */     
/* 715 */     if (d0 < 0.5D) {
/* 716 */       villagertype = VillagerType.a(worldserver.i(getChunkCoordinates()));
/* 717 */     } else if (d0 < 0.75D) {
/* 718 */       villagertype = getVillagerData().getType();
/*     */     } else {
/* 720 */       villagertype = ((EntityVillager)entityageable).getVillagerData().getType();
/*     */     } 
/*     */     
/* 723 */     EntityVillager entityvillager = new EntityVillager(EntityTypes.VILLAGER, worldserver, villagertype);
/*     */     
/* 725 */     entityvillager.prepare(worldserver, worldserver.getDamageScaler(entityvillager.getChunkCoordinates()), EnumMobSpawn.BREEDING, (GroupDataEntity)null, (NBTTagCompound)null);
/* 726 */     return entityvillager;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(EntityItem entityitem) {
/* 731 */     ItemStack itemstack = entityitem.getItemStack();
/*     */     
/* 733 */     if (i(itemstack)) {
/* 734 */       InventorySubcontainer inventorysubcontainer = getInventory();
/* 735 */       boolean flag = inventorysubcontainer.b(itemstack);
/*     */       
/* 737 */       if (!flag) {
/*     */         return;
/*     */       }
/*     */       
/* 741 */       a(entityitem);
/* 742 */       receive(entityitem, itemstack.getCount());
/* 743 */       ItemStack itemstack1 = inventorysubcontainer.a(itemstack);
/*     */       
/* 745 */       if (itemstack1.isEmpty()) {
/* 746 */         entityitem.die();
/*     */       } else {
/* 748 */         itemstack.setCount(itemstack1.getCount());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean i(ItemStack itemstack) {
/* 756 */     Item item = itemstack.getItem();
/*     */     
/* 758 */     return ((bs.contains(item) || getVillagerData().getProfession().c().contains(item)) && getInventory().b(itemstack));
/*     */   }
/*     */   
/*     */   public boolean fg() {
/* 762 */     return (fv() >= 24);
/*     */   }
/*     */   
/*     */   public boolean fh() {
/* 766 */     return (fv() < 12);
/*     */   }
/*     */   
/*     */   private int fv() {
/* 770 */     InventorySubcontainer inventorysubcontainer = getInventory();
/*     */     
/* 772 */     return bp.entrySet().stream().mapToInt(entry -> inventorysubcontainer.a((Item)entry.getKey()) * ((Integer)entry.getValue()).intValue())
/*     */       
/* 774 */       .sum();
/*     */   }
/*     */   
/*     */   public boolean canPlant() {
/* 778 */     return getInventory().a((Set<Item>)ImmutableSet.of(Items.WHEAT_SEEDS, Items.POTATO, Items.CARROT, Items.BEETROOT_SEEDS));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eW() {
/* 783 */     VillagerData villagerdata = getVillagerData();
/* 784 */     Int2ObjectMap<VillagerTrades.IMerchantRecipeOption[]> int2objectmap = VillagerTrades.a.get(villagerdata.getProfession());
/*     */     
/* 786 */     if (int2objectmap != null && !int2objectmap.isEmpty()) {
/* 787 */       VillagerTrades.IMerchantRecipeOption[] avillagertrades_imerchantrecipeoption = (VillagerTrades.IMerchantRecipeOption[])int2objectmap.get(villagerdata.getLevel());
/*     */       
/* 789 */       if (avillagertrades_imerchantrecipeoption != null) {
/* 790 */         MerchantRecipeList merchantrecipelist = getOffers();
/*     */         
/* 792 */         a(merchantrecipelist, avillagertrades_imerchantrecipeoption, 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
/* 798 */     if ((i < this.bz || i >= this.bz + 1200L) && (i < entityvillager.bz || i >= entityvillager.bz + 1200L)) {
/* 799 */       this.by.a(entityvillager.by, this.random, 10);
/* 800 */       this.bz = i;
/* 801 */       entityvillager.bz = i;
/* 802 */       a(worldserver, i, 5);
/*     */     } 
/*     */   }
/*     */   private void doReputationTick() {
/* 806 */     fw();
/*     */   } private void fw() {
/* 808 */     long i = this.world.getTime();
/*     */     
/* 810 */     if (this.bA == 0L) {
/* 811 */       this.bA = i;
/* 812 */     } else if (i >= this.bA + 24000L) {
/* 813 */       this.by.b();
/* 814 */       this.bA = i;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(WorldServer worldserver, long i, int j) {
/* 819 */     if (a(i)) {
/* 820 */       AxisAlignedBB axisalignedbb = getBoundingBox().grow(10.0D, 10.0D, 10.0D);
/* 821 */       List<EntityVillager> list = worldserver.a(EntityVillager.class, axisalignedbb);
/*     */ 
/*     */       
/* 824 */       List<EntityVillager> list1 = (List<EntityVillager>)list.stream().filter(entityvillager -> entityvillager.a(i)).limit(5L).collect(Collectors.toList());
/*     */       
/* 826 */       if (list1.size() >= j) {
/* 827 */         EntityIronGolem entityirongolem = d(worldserver);
/*     */         
/* 829 */         if (entityirongolem != null) {
/* 830 */           list.forEach(SensorGolemLastSeen::b);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean a(long i) {
/* 837 */     return !b(this.world.getTime()) ? false : (!this.bg.hasMemory(MemoryModuleType.GOLEM_DETECTED_RECENTLY));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EntityIronGolem d(WorldServer worldserver) {
/* 842 */     BlockPosition blockposition = getChunkCoordinates();
/*     */     
/* 844 */     for (int i = 0; i < 10; i++) {
/* 845 */       double d0 = (worldserver.random.nextInt(16) - 8);
/* 846 */       double d1 = (worldserver.random.nextInt(16) - 8);
/* 847 */       BlockPosition blockposition1 = a(blockposition, d0, d1);
/*     */       
/* 849 */       if (blockposition1 != null) {
/* 850 */         EntityIronGolem entityirongolem = EntityTypes.IRON_GOLEM.createCreature(worldserver, (NBTTagCompound)null, (IChatBaseComponent)null, (EntityHuman)null, blockposition1, EnumMobSpawn.MOB_SUMMONED, false, false);
/*     */         
/* 852 */         if (entityirongolem != null) {
/* 853 */           if (entityirongolem.a(worldserver, EnumMobSpawn.MOB_SUMMONED) && entityirongolem.a(worldserver)) {
/* 854 */             worldserver.addAllEntities(entityirongolem, CreatureSpawnEvent.SpawnReason.VILLAGE_DEFENSE);
/* 855 */             return entityirongolem;
/*     */           } 
/*     */           
/* 858 */           entityirongolem.die();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 863 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private BlockPosition a(BlockPosition blockposition, double d0, double d1) {
/* 868 */     boolean flag = true;
/* 869 */     BlockPosition blockposition1 = blockposition.a(d0, 6.0D, d1);
/* 870 */     IBlockData iblockdata = this.world.getType(blockposition1);
/*     */     
/* 872 */     for (int i = 6; i >= -6; i--) {
/* 873 */       BlockPosition blockposition2 = blockposition1;
/* 874 */       IBlockData iblockdata1 = iblockdata;
/*     */       
/* 876 */       blockposition1 = blockposition1.down();
/* 877 */       iblockdata = this.world.getType(blockposition1);
/* 878 */       if ((iblockdata1.isAir() || iblockdata1.getMaterial().isLiquid()) && iblockdata.getMaterial().f()) {
/* 879 */         return blockposition2;
/*     */       }
/*     */     } 
/*     */     
/* 883 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ReputationEvent reputationevent, Entity entity) {
/* 888 */     if (reputationevent == ReputationEvent.a) {
/* 889 */       this.by.a(entity.getUniqueID(), ReputationType.MAJOR_POSITIVE, 20);
/* 890 */       this.by.a(entity.getUniqueID(), ReputationType.MINOR_POSITIVE, 25);
/* 891 */     } else if (reputationevent == ReputationEvent.e) {
/* 892 */       this.by.a(entity.getUniqueID(), ReputationType.TRADING, 2);
/* 893 */     } else if (reputationevent == ReputationEvent.c) {
/* 894 */       this.by.a(entity.getUniqueID(), ReputationType.MINOR_NEGATIVE, 25);
/* 895 */     } else if (reputationevent == ReputationEvent.d) {
/* 896 */       this.by.a(entity.getUniqueID(), ReputationType.MAJOR_NEGATIVE, 25);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExperience() {
/* 903 */     return this.bB;
/*     */   }
/*     */   
/*     */   public void setExperience(int i) {
/* 907 */     this.bB = i;
/*     */   }
/*     */   
/*     */   private void fx() {
/* 911 */     fo();
/* 912 */     this.bD = 0;
/*     */   }
/*     */   public Reputation getReputation() {
/* 915 */     return fj();
/*     */   } public Reputation fj() {
/* 917 */     return this.by;
/*     */   }
/*     */   
/*     */   public void a(NBTBase nbtbase) {
/* 921 */     this.by.a(new Dynamic(DynamicOpsNBT.a, nbtbase));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/* 926 */     super.M();
/* 927 */     PacketDebug.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void entitySleep(BlockPosition blockposition) {
/* 932 */     super.entitySleep(blockposition);
/* 933 */     this.bg.setMemory(MemoryModuleType.LAST_SLEPT, Long.valueOf(this.world.getTime()));
/* 934 */     this.bg.removeMemory(MemoryModuleType.WALK_TARGET);
/* 935 */     this.bg.removeMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void entityWakeup() {
/* 940 */     super.entityWakeup();
/* 941 */     this.bg.setMemory(MemoryModuleType.LAST_WOKEN, Long.valueOf(this.world.getTime()));
/*     */   }
/*     */   
/*     */   private boolean b(long i) {
/* 945 */     Optional<Long> optional = this.bg.getMemory(MemoryModuleType.LAST_SLEPT);
/*     */     
/* 947 */     return optional.isPresent() ? ((i - ((Long)optional.get()).longValue() < 24000L)) : false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */