/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPiglin
/*     */   extends EntityPiglinAbstract
/*     */   implements ICrossbow
/*     */ {
/*  65 */   private static final DataWatcherObject<Boolean> bp = DataWatcher.a((Class)EntityPiglin.class, DataWatcherRegistry.i);
/*  66 */   private static final DataWatcherObject<Boolean> bq = DataWatcher.a((Class)EntityPiglin.class, DataWatcherRegistry.i);
/*  67 */   private static final DataWatcherObject<Boolean> br = DataWatcher.a((Class)EntityPiglin.class, DataWatcherRegistry.i);
/*     */   
/*  69 */   private static final UUID bs = UUID.fromString("766bfa64-11f3-11ea-8d71-362b9e155667");
/*  70 */   private static final AttributeModifier bt = new AttributeModifier(bs, "Baby speed boost", 0.20000000298023224D, AttributeModifier.Operation.MULTIPLY_BASE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private final InventorySubcontainer bu = new InventorySubcontainer(8);
/*     */   
/*     */   public boolean cannotHunt = false;
/*  85 */   protected static final ImmutableList<SensorType<? extends Sensor<? super EntityPiglin>>> d = ImmutableList.of(SensorType.c, SensorType.d, SensorType.b, SensorType.f, SensorType.k);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   protected static final ImmutableList<MemoryModuleType<?>> bo = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.DOORS_TO_CLOSE, MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS, MemoryModuleType.NEARBY_ADULT_PIGLINS, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.WALK_TARGET, (Object[])new MemoryModuleType[] { MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.INTERACTION_TARGET, MemoryModuleType.PATH, MemoryModuleType.ANGRY_AT, MemoryModuleType.UNIVERSAL_ANGER, MemoryModuleType.AVOID_TARGET, MemoryModuleType.ADMIRING_ITEM, MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, MemoryModuleType.ADMIRING_DISABLED, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryModuleType.CELEBRATE_LOCATION, MemoryModuleType.DANCING, MemoryModuleType.HUNTED_RECENTLY, MemoryModuleType.NEAREST_VISIBLE_BABY_HOGLIN, MemoryModuleType.NEAREST_VISIBLE_NEMSIS, MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, MemoryModuleType.RIDE_TARGET, MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN, MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD, MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM, MemoryModuleType.ATE_RECENTLY, MemoryModuleType.NEAREST_REPELLENT });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityPiglin(EntityTypes<? extends EntityPiglinAbstract> var0, World var1) {
/* 135 */     super(var0, var1);
/* 136 */     this.f = 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/* 141 */     super.saveData(var0);
/*     */     
/* 143 */     if (isBaby()) {
/* 144 */       var0.setBoolean("IsBaby", true);
/*     */     }
/* 146 */     if (this.cannotHunt) {
/* 147 */       var0.setBoolean("CannotHunt", true);
/*     */     }
/* 149 */     var0.set("Inventory", this.bu.g());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/* 154 */     super.loadData(var0);
/*     */     
/* 156 */     setBaby(var0.getBoolean("IsBaby"));
/* 157 */     v(var0.getBoolean("CannotHunt"));
/* 158 */     this.bu.a(var0.getList("Inventory", 10));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropDeathLoot(DamageSource var0, int var1, boolean var2) {
/* 169 */     super.dropDeathLoot(var0, var1, var2);
/*     */     
/* 171 */     this.bu.f().forEach(this::a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack k(ItemStack var0) {
/* 178 */     return this.bu.a(var0);
/*     */   }
/*     */   
/*     */   protected boolean l(ItemStack var0) {
/* 182 */     return this.bu.b(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 187 */     super.initDatawatcher();
/* 188 */     this.datawatcher.register(bp, Boolean.valueOf(false));
/* 189 */     this.datawatcher.register(bq, Boolean.valueOf(false));
/* 190 */     this.datawatcher.register(br, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> var0) {
/* 195 */     super.a(var0);
/* 196 */     if (bp.equals(var0)) {
/* 197 */       updateSize();
/*     */     }
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eT() {
/* 202 */     return EntityMonster.eR()
/* 203 */       .a(GenericAttributes.MAX_HEALTH, 16.0D)
/* 204 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.3499999940395355D)
/* 205 */       .a(GenericAttributes.ATTACK_DAMAGE, 5.0D);
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<EntityPiglin> var0, GeneratorAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/* 209 */     return !var1.getType(var3.down()).a(Blocks.NETHER_WART_BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/* 215 */     if (var2 != EnumMobSpawn.STRUCTURE) {
/* 216 */       if (var0.getRandom().nextFloat() < 0.2F) {
/* 217 */         setBaby(true);
/* 218 */       } else if (eM()) {
/* 219 */         setSlot(EnumItemSlot.MAINHAND, eV());
/*     */       } 
/*     */     }
/* 222 */     PiglinAI.a(this);
/* 223 */     a(var1);
/* 224 */     b(var1);
/* 225 */     return super.prepare(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean L() {
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double var0) {
/* 235 */     return !isPersistent();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler var0) {
/* 240 */     if (eM()) {
/* 241 */       d(EnumItemSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
/* 242 */       d(EnumItemSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
/* 243 */       d(EnumItemSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
/* 244 */       d(EnumItemSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void d(EnumItemSlot var0, ItemStack var1) {
/* 249 */     if (this.world.random.nextFloat() < 0.1F) {
/* 250 */       setSlot(var0, var1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController.b<EntityPiglin> cJ() {
/* 256 */     return BehaviorController.a((Collection<? extends MemoryModuleType<?>>)bo, (Collection<? extends SensorType<? extends Sensor<? super EntityPiglin>>>)d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController<?> a(Dynamic<?> var0) {
/* 261 */     return PiglinAI.a(this, cJ().a(var0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BehaviorController<EntityPiglin> getBehaviorController() {
/* 267 */     return (BehaviorController)super.getBehaviorController();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman var0, EnumHand var1) {
/* 272 */     EnumInteractionResult var2 = super.b(var0, var1);
/* 273 */     if (var2.a()) {
/* 274 */       return var2;
/*     */     }
/* 276 */     if (this.world.isClientSide) {
/* 277 */       boolean var3 = (PiglinAI.b(this, var0.b(var1)) && eN() != EntityPiglinArmPose.ADMIRING_ITEM);
/* 278 */       return var3 ? EnumInteractionResult.SUCCESS : EnumInteractionResult.PASS;
/*     */     } 
/*     */     
/* 281 */     return PiglinAI.a(this, var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose var0, EntitySize var1) {
/* 286 */     return isBaby() ? 0.93F : 1.74F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/* 291 */     return getHeight() * 0.92D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaby(boolean var0) {
/* 296 */     getDataWatcher().set(bp, Boolean.valueOf(var0));
/*     */     
/* 298 */     if (!this.world.isClientSide) {
/* 299 */       AttributeModifiable var1 = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/* 300 */       var1.removeModifier(bt);
/* 301 */       if (var0) {
/* 302 */         var1.b(bt);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/* 309 */     return ((Boolean)getDataWatcher().<Boolean>get(bp)).booleanValue();
/*     */   }
/*     */   
/*     */   private void v(boolean var0) {
/* 313 */     this.cannotHunt = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean m() {
/* 318 */     return !this.cannotHunt;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 323 */     this.world.getMethodProfiler().enter("piglinBrain");
/* 324 */     getBehaviorController().a((WorldServer)this.world, this);
/* 325 */     this.world.getMethodProfiler().exit();
/*     */     
/* 327 */     PiglinAI.b(this);
/*     */     
/* 329 */     super.mobTick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getExpValue(EntityHuman var0) {
/* 334 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0) {
/* 339 */     PiglinAI.c(this);
/* 340 */     this.bu.f().forEach(this::a);
/* 341 */     super.c(var0);
/*     */   }
/*     */   
/*     */   private ItemStack eV() {
/* 345 */     if (this.random.nextFloat() < 0.5D) {
/* 346 */       return new ItemStack(Items.CROSSBOW);
/*     */     }
/* 348 */     return new ItemStack(Items.GOLDEN_SWORD);
/*     */   }
/*     */   
/*     */   private boolean eW() {
/* 352 */     return ((Boolean)this.datawatcher.<Boolean>get(bq)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(boolean var0) {
/* 357 */     this.datawatcher.set(bq, Boolean.valueOf(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void U_() {
/* 362 */     this.ticksFarFromPlayer = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityPiglinArmPose eN() {
/* 368 */     if (eU())
/* 369 */       return EntityPiglinArmPose.DANCING; 
/* 370 */     if (PiglinAI.a(getItemInOffHand().getItem()))
/* 371 */       return EntityPiglinArmPose.ADMIRING_ITEM; 
/* 372 */     if (isAggressive() && eO())
/* 373 */       return EntityPiglinArmPose.ATTACKING_WITH_MELEE_WEAPON; 
/* 374 */     if (eW())
/* 375 */       return EntityPiglinArmPose.CROSSBOW_CHARGE; 
/* 376 */     if (isAggressive() && a(Items.CROSSBOW)) {
/* 377 */       return EntityPiglinArmPose.CROSSBOW_HOLD;
/*     */     }
/* 379 */     return EntityPiglinArmPose.DEFAULT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eU() {
/* 384 */     return ((Boolean)this.datawatcher.<Boolean>get(br)).booleanValue();
/*     */   }
/*     */   
/*     */   public void u(boolean var0) {
/* 388 */     this.datawatcher.set(br, Boolean.valueOf(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource var0, float var1) {
/* 393 */     boolean var2 = super.damageEntity(var0, var1);
/* 394 */     if (this.world.isClientSide) {
/* 395 */       return false;
/*     */     }
/* 397 */     if (var2 && var0.getEntity() instanceof EntityLiving) {
/* 398 */       PiglinAI.a(this, (EntityLiving)var0.getEntity());
/*     */     }
/* 400 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving var0, float var1) {
/* 405 */     b(this, 1.6F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving var0, ItemStack var1, IProjectile var2, float var3) {
/* 410 */     a(this, var0, var2, var3, 1.6F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemProjectileWeapon var0) {
/* 415 */     return (var0 == Items.CROSSBOW);
/*     */   }
/*     */   
/*     */   protected void m(ItemStack var0) {
/* 419 */     b(EnumItemSlot.MAINHAND, var0);
/*     */   }
/*     */   
/*     */   protected void n(ItemStack var0) {
/* 423 */     if (var0.getItem() == PiglinAI.a) {
/*     */       
/* 425 */       setSlot(EnumItemSlot.OFFHAND, var0);
/* 426 */       d(EnumItemSlot.OFFHAND);
/*     */     } else {
/* 428 */       b(EnumItemSlot.OFFHAND, var0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean i(ItemStack var0) {
/* 434 */     return (this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) && canPickupLoot() && PiglinAI.a(this, var0));
/*     */   }
/*     */   
/*     */   protected boolean o(ItemStack var0) {
/* 438 */     EnumItemSlot var1 = EntityInsentient.j(var0);
/* 439 */     ItemStack var2 = getEquipment(var1);
/* 440 */     return a(var0, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(ItemStack var0, ItemStack var1) {
/* 445 */     if (EnchantmentManager.d(var1)) {
/* 446 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 451 */     boolean var2 = (PiglinAI.a(var0.getItem()) || var0.getItem() == Items.CROSSBOW);
/* 452 */     boolean var3 = (PiglinAI.a(var1.getItem()) || var1.getItem() == Items.CROSSBOW);
/*     */ 
/*     */ 
/*     */     
/* 456 */     if (var2 && !var3) {
/* 457 */       return true;
/*     */     }
/* 459 */     if (!var2 && var3) {
/* 460 */       return false;
/*     */     }
/* 462 */     if (eM() && var0.getItem() != Items.CROSSBOW && var1.getItem() == Items.CROSSBOW)
/*     */     {
/* 464 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 468 */     return super.a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(EntityItem var0) {
/* 473 */     a(var0);
/* 474 */     PiglinAI.a(this, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Entity var0, boolean var1) {
/* 479 */     if (isBaby() && var0.getEntityType() == EntityTypes.HOGLIN) {
/* 480 */       var0 = b(var0, 3);
/*     */     }
/* 482 */     return super.a(var0, var1);
/*     */   }
/*     */   
/*     */   private Entity b(Entity var0, int var1) {
/* 486 */     List<Entity> var2 = var0.getPassengers();
/* 487 */     if (var1 == 1 || var2.isEmpty()) {
/* 488 */       return var0;
/*     */     }
/* 490 */     return b(var2.get(0), var1 - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 496 */     if (this.world.isClientSide) {
/* 497 */       return null;
/*     */     }
/* 499 */     return PiglinAI.d(this).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 504 */     return SoundEffects.ENTITY_PIGLIN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 509 */     return SoundEffects.ENTITY_PIGLIN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition var0, IBlockData var1) {
/* 514 */     playSound(SoundEffects.ENTITY_PIGLIN_STEP, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void a(SoundEffect var0) {
/* 518 */     playSound(var0, getSoundVolume(), dG());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eP() {
/* 523 */     a(SoundEffects.ENTITY_PIGLIN_CONVERTED_TO_ZOMBIFIED);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPiglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */