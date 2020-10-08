/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
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
/*     */ public class EntityHoglin
/*     */   extends EntityAnimal
/*     */   implements IMonster, IOglin
/*     */ {
/*  57 */   private static final DataWatcherObject<Boolean> bq = DataWatcher.a((Class)EntityHoglin.class, DataWatcherRegistry.i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int br;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public int conversionTicks = 0;
/*     */   
/*     */   public boolean cannotBeHunted = false;
/*  72 */   protected static final ImmutableList<? extends SensorType<? extends Sensor<? super EntityHoglin>>> bo = ImmutableList.of(SensorType.c, SensorType.d, SensorType.n, SensorType.m);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   protected static final ImmutableList<? extends MemoryModuleType<?>> bp = ImmutableList.of(MemoryModuleType.BREED_TARGET, MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLIN, (Object[])new MemoryModuleType[] { MemoryModuleType.AVOID_TARGET, MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, MemoryModuleType.NEAREST_VISIBLE_ADULT_HOGLINS, MemoryModuleType.NEAREST_VISIBLE_ADULY, MemoryModuleType.NEAREST_REPELLENT, MemoryModuleType.PACIFIED });
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
/*     */   public EntityHoglin(EntityTypes<? extends EntityHoglin> var0, World var1) {
/* 101 */     super((EntityTypes)var0, var1);
/* 102 */     this.f = 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman var0) {
/* 107 */     return !isLeashed();
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/* 111 */     return EntityMonster.eR()
/* 112 */       .a(GenericAttributes.MAX_HEALTH, 40.0D)
/* 113 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D)
/* 114 */       .a(GenericAttributes.KNOCKBACK_RESISTANCE, 0.6000000238418579D)
/* 115 */       .a(GenericAttributes.ATTACK_KNOCKBACK, 1.0D)
/* 116 */       .a(GenericAttributes.ATTACK_DAMAGE, 6.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity var0) {
/* 121 */     if (!(var0 instanceof EntityLiving)) {
/* 122 */       return false;
/*     */     }
/* 124 */     this.br = 10;
/* 125 */     this.world.broadcastEntityEffect(this, (byte)4);
/*     */     
/* 127 */     playSound(SoundEffects.ENTITY_HOGLIN_ATTACK, 1.0F, dG());
/* 128 */     HoglinAI.a(this, (EntityLiving)var0);
/* 129 */     return IOglin.a(this, (EntityLiving)var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void e(EntityLiving var0) {
/* 134 */     if (eL()) {
/* 135 */       IOglin.b(this, var0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource var0, float var1) {
/* 141 */     boolean var2 = super.damageEntity(var0, var1);
/* 142 */     if (this.world.isClientSide) {
/* 143 */       return false;
/*     */     }
/* 145 */     if (var2 && var0.getEntity() instanceof EntityLiving) {
/* 146 */       HoglinAI.b(this, (EntityLiving)var0.getEntity());
/*     */     }
/* 148 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController.b<EntityHoglin> cJ() {
/* 153 */     return BehaviorController.a((Collection<? extends MemoryModuleType<?>>)bp, (Collection<? extends SensorType<? extends Sensor<? super EntityHoglin>>>)bo);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController<?> a(Dynamic<?> var0) {
/* 158 */     return HoglinAI.a(cJ().a(var0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BehaviorController<EntityHoglin> getBehaviorController() {
/* 164 */     return (BehaviorController)super.getBehaviorController();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 169 */     this.world.getMethodProfiler().enter("hoglinBrain");
/* 170 */     getBehaviorController().a((WorldServer)this.world, this);
/* 171 */     this.world.getMethodProfiler().exit();
/*     */     
/* 173 */     HoglinAI.a(this);
/*     */     
/* 175 */     if (isConverting()) {
/* 176 */       this.conversionTicks++;
/* 177 */       if (this.conversionTicks > 300) {
/* 178 */         a(SoundEffects.ENTITY_HOGLIN_CONVERTED_TO_ZOMBIFIED);
/* 179 */         c((WorldServer)this.world);
/*     */       } 
/*     */     } else {
/* 182 */       this.conversionTicks = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 189 */     if (this.br > 0) {
/* 190 */       this.br--;
/*     */     }
/* 192 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m() {
/* 197 */     if (isBaby()) {
/* 198 */       this.f = 3;
/* 199 */       getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(0.5D);
/*     */     } else {
/* 201 */       this.f = 5;
/* 202 */       getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(6.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<EntityHoglin> var0, GeneratorAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/* 207 */     return !var1.getType(var3.down()).a(Blocks.NETHER_WART_BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/* 213 */     if (var0.getRandom().nextFloat() < 0.2F) {
/* 214 */       setBaby(true);
/*     */     }
/*     */     
/* 217 */     return super.prepare(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double var0) {
/* 222 */     return !isPersistent();
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition var0, IWorldReader var1) {
/* 227 */     if (HoglinAI.a(this, var0)) {
/* 228 */       return -1.0F;
/*     */     }
/* 230 */     if (var1.getType(var0.down()).a(Blocks.CRIMSON_NYLIUM))
/*     */     {
/* 232 */       return 10.0F;
/*     */     }
/* 234 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/* 239 */     return getHeight() - (isBaby() ? 0.2D : 0.15D);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman var0, EnumHand var1) {
/* 244 */     EnumInteractionResult var2 = super.b(var0, var1);
/* 245 */     if (var2.a()) {
/* 246 */       setPersistent();
/*     */     }
/* 248 */     return var2;
/*     */   }
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
/*     */   protected boolean isDropExperience() {
/* 270 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getExpValue(EntityHuman var0) {
/* 275 */     return this.f;
/*     */   }
/*     */   
/*     */   private void c(WorldServer var0) {
/* 279 */     EntityZoglin var1 = a(EntityTypes.ZOGLIN, true);
/* 280 */     if (var1 != null) {
/* 281 */       var1.addEffect(new MobEffect(MobEffects.CONFUSION, 200, 0));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack var0) {
/* 287 */     return (var0.getItem() == Items.bw);
/*     */   }
/*     */   
/*     */   public boolean eL() {
/* 291 */     return !isBaby();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 296 */     super.initDatawatcher();
/* 297 */     this.datawatcher.register(bq, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/* 302 */     super.saveData(var0);
/* 303 */     if (isImmuneToZombification()) {
/* 304 */       var0.setBoolean("IsImmuneToZombification", true);
/*     */     }
/* 306 */     var0.setInt("TimeInOverworld", this.conversionTicks);
/* 307 */     if (this.cannotBeHunted) {
/* 308 */       var0.setBoolean("CannotBeHunted", true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/* 314 */     super.loadData(var0);
/* 315 */     setImmuneToZombification(var0.getBoolean("IsImmuneToZombification"));
/* 316 */     this.conversionTicks = var0.getInt("TimeInOverworld");
/* 317 */     u(var0.getBoolean("CannotBeHunted"));
/*     */   }
/*     */   
/*     */   public void setImmuneToZombification(boolean var0) {
/* 321 */     getDataWatcher().set(bq, Boolean.valueOf(var0));
/*     */   }
/*     */   
/*     */   public boolean isImmuneToZombification() {
/* 325 */     return ((Boolean)getDataWatcher().<Boolean>get(bq)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isConverting() {
/* 329 */     return (!this.world.getDimensionManager().isPiglinSafe() && !isImmuneToZombification() && !isNoAI());
/*     */   }
/*     */   
/*     */   private void u(boolean var0) {
/* 333 */     this.cannotBeHunted = var0;
/*     */   }
/*     */   
/*     */   public boolean eO() {
/* 337 */     return (eL() && !this.cannotBeHunted);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(WorldServer var0, EntityAgeable var1) {
/* 343 */     EntityHoglin var2 = EntityTypes.HOGLIN.a(var0);
/* 344 */     if (var2 != null) {
/* 345 */       var2.setPersistent();
/*     */     }
/* 347 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eP() {
/* 356 */     return (!HoglinAI.c(this) && super.eP());
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/* 361 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 366 */     if (this.world.isClientSide) {
/* 367 */       return null;
/*     */     }
/* 369 */     return HoglinAI.b(this).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 374 */     return SoundEffects.ENTITY_HOGLIN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 379 */     return SoundEffects.ENTITY_HOGLIN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSwim() {
/* 384 */     return SoundEffects.ENTITY_HOSTILE_SWIM;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSplash() {
/* 389 */     return SoundEffects.ENTITY_HOSTILE_SPLASH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition var0, IBlockData var1) {
/* 394 */     playSound(SoundEffects.ENTITY_HOGLIN_STEP, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void a(SoundEffect var0) {
/* 398 */     playSound(var0, getSoundVolume(), dG());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/* 403 */     super.M();
/* 404 */     PacketDebug.a(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHoglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */