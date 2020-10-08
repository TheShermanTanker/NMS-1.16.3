/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
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
/*     */ public class EntityZoglin
/*     */   extends EntityMonster
/*     */   implements IMonster, IOglin
/*     */ {
/*  59 */   private static final DataWatcherObject<Boolean> d = DataWatcher.a((Class)EntityZoglin.class, DataWatcherRegistry.i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int bo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   protected static final ImmutableList<? extends SensorType<? extends Sensor<? super EntityZoglin>>> b = ImmutableList.of(SensorType.c, SensorType.d);
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected static final ImmutableList<? extends MemoryModuleType<?>> c = ImmutableList.of(MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN);
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
/*     */   public EntityZoglin(EntityTypes<? extends EntityZoglin> var0, World var1) {
/*  94 */     super((EntityTypes)var0, var1);
/*  95 */     this.f = 5;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController.b<EntityZoglin> cJ() {
/* 100 */     return BehaviorController.a((Collection<? extends MemoryModuleType<?>>)c, (Collection<? extends SensorType<? extends Sensor<? super EntityZoglin>>>)b);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController<?> a(Dynamic<?> var0) {
/* 105 */     BehaviorController<EntityZoglin> var1 = cJ().a(var0);
/* 106 */     a(var1);
/* 107 */     b(var1);
/* 108 */     c(var1);
/*     */     
/* 110 */     var1.a((Set<Activity>)ImmutableSet.of(Activity.CORE));
/* 111 */     var1.b(Activity.IDLE);
/* 112 */     var1.e();
/* 113 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(BehaviorController<EntityZoglin> var0) {
/* 118 */     var0.a(Activity.CORE, 0, ImmutableList.of(new BehaviorLook(45, 90), new BehavorMove()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void b(BehaviorController<EntityZoglin> var0) {
/* 125 */     var0.a(Activity.IDLE, 10, ImmutableList.of(new BehaviorAttackTargetSet<>(EntityZoglin::eO), new BehaviorRunSometimes<>(new BehaviorLookTarget(8.0F), 
/*     */             
/* 127 */             IntRange.a(30, 60)), new BehaviorGateSingle<>(
/* 128 */             (List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 129 */               Pair.of(new BehaviorStrollRandomUnconstrained(0.4F), Integer.valueOf(2)), 
/* 130 */               Pair.of(new BehaviorLookWalk(0.4F, 3), Integer.valueOf(2)), 
/* 131 */               Pair.of(new BehaviorNop(30, 60), Integer.valueOf(1))))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void c(BehaviorController<EntityZoglin> var0) {
/* 137 */     var0.a(Activity.FLIGHT, 10, ImmutableList.of(new BehaviorWalkAwayOutOfRange(1.0F), new BehaviorRunIf<>(EntityZoglin::eK, new BehaviorAttack(40)), new BehaviorRunIf<>(EntityZoglin::isBaby, new BehaviorAttack(15)), new BehaviorAttackTargetForget<>()), MemoryModuleType.ATTACK_TARGET);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Optional<? extends EntityLiving> eO() {
/* 146 */     return ((List<? extends EntityLiving>)getBehaviorController().getMemory(MemoryModuleType.VISIBLE_MOBS).orElse(ImmutableList.of())).stream().filter(EntityZoglin::i).findFirst();
/*     */   }
/*     */   
/*     */   private static boolean i(EntityLiving var0) {
/* 150 */     EntityTypes<?> var1 = var0.getEntityType();
/* 151 */     return (var1 != EntityTypes.ZOGLIN && var1 != EntityTypes.CREEPER && IEntitySelector.f.test(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 156 */     super.initDatawatcher();
/* 157 */     this.datawatcher.register(d, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> var0) {
/* 162 */     super.a(var0);
/* 163 */     if (d.equals(var0)) {
/* 164 */       updateSize();
/*     */     }
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/* 169 */     return EntityMonster.eR()
/* 170 */       .a(GenericAttributes.MAX_HEALTH, 40.0D)
/* 171 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D)
/* 172 */       .a(GenericAttributes.KNOCKBACK_RESISTANCE, 0.6000000238418579D)
/* 173 */       .a(GenericAttributes.ATTACK_KNOCKBACK, 1.0D)
/* 174 */       .a(GenericAttributes.ATTACK_DAMAGE, 6.0D);
/*     */   }
/*     */   
/*     */   public boolean eK() {
/* 178 */     return !isBaby();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity var0) {
/* 183 */     if (!(var0 instanceof EntityLiving)) {
/* 184 */       return false;
/*     */     }
/* 186 */     this.bo = 10;
/* 187 */     this.world.broadcastEntityEffect(this, (byte)4);
/*     */     
/* 189 */     playSound(SoundEffects.ENTITY_ZOGLIN_ATTACK, 1.0F, dG());
/* 190 */     return IOglin.a(this, (EntityLiving)var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman var0) {
/* 195 */     return !isLeashed();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void e(EntityLiving var0) {
/* 200 */     if (!isBaby()) {
/* 201 */       IOglin.b(this, var0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/* 207 */     return getHeight() - (isBaby() ? 0.2D : 0.15D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource var0, float var1) {
/* 212 */     boolean var2 = super.damageEntity(var0, var1);
/* 213 */     if (this.world.isClientSide) {
/* 214 */       return false;
/*     */     }
/* 216 */     if (!var2 || !(var0.getEntity() instanceof EntityLiving)) {
/* 217 */       return var2;
/*     */     }
/* 219 */     EntityLiving var3 = (EntityLiving)var0.getEntity();
/* 220 */     if (IEntitySelector.f.test(var3) && !BehaviorUtil.a(this, var3, 4.0D)) {
/* 221 */       j(var3);
/*     */     }
/* 223 */     return var2;
/*     */   }
/*     */   
/*     */   private void j(EntityLiving var0) {
/* 227 */     this.bg.removeMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/* 228 */     this.bg.a(MemoryModuleType.ATTACK_TARGET, var0, 200L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BehaviorController<EntityZoglin> getBehaviorController() {
/* 234 */     return (BehaviorController)super.getBehaviorController();
/*     */   }
/*     */   
/*     */   protected void eL() {
/* 238 */     Activity var0 = this.bg.f().orElse(null);
/*     */ 
/*     */     
/* 241 */     this.bg.a((List<Activity>)ImmutableList.of(Activity.FLIGHT, Activity.IDLE));
/*     */     
/* 243 */     Activity var1 = this.bg.f().orElse(null);
/* 244 */     if (var1 == Activity.FLIGHT && var0 != Activity.FLIGHT)
/*     */     {
/* 246 */       eN();
/*     */     }
/*     */ 
/*     */     
/* 250 */     setAggressive(this.bg.hasMemory(MemoryModuleType.ATTACK_TARGET));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 256 */     this.world.getMethodProfiler().enter("zoglinBrain");
/* 257 */     getBehaviorController().a((WorldServer)this.world, this);
/* 258 */     this.world.getMethodProfiler().exit();
/*     */     
/* 260 */     eL();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaby(boolean var0) {
/* 265 */     getDataWatcher().set(d, Boolean.valueOf(var0));
/* 266 */     if (!this.world.isClientSide && var0) {
/* 267 */       getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(0.5D);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/* 273 */     return ((Boolean)getDataWatcher().<Boolean>get(d)).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 279 */     if (this.bo > 0) {
/* 280 */       this.bo--;
/*     */     }
/* 282 */     super.movementTick();
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
/*     */   protected SoundEffect getSoundAmbient() {
/* 304 */     if (this.world.isClientSide) {
/* 305 */       return null;
/*     */     }
/* 307 */     if (this.bg.hasMemory(MemoryModuleType.ATTACK_TARGET)) {
/* 308 */       return SoundEffects.ENTITY_ZOGLIN_ANGRY;
/*     */     }
/* 310 */     return SoundEffects.ENTITY_ZOGLIN_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 315 */     return SoundEffects.ENTITY_ZOGLIN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 320 */     return SoundEffects.ENTITY_ZOGLIN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition var0, IBlockData var1) {
/* 325 */     playSound(SoundEffects.ENTITY_ZOGLIN_STEP, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void eN() {
/* 329 */     playSound(SoundEffects.ENTITY_ZOGLIN_ANGRY, 1.0F, dG());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/* 334 */     super.M();
/* 335 */     PacketDebug.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 340 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/* 345 */     super.saveData(var0);
/*     */     
/* 347 */     if (isBaby()) {
/* 348 */       var0.setBoolean("IsBaby", true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/* 354 */     super.loadData(var0);
/*     */     
/* 356 */     if (var0.getBoolean("IsBaby"))
/* 357 */       setBaby(true); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityZoglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */