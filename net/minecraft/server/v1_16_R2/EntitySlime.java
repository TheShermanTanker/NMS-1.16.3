/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.event.entity.SlimeChangeDirectionEvent;
/*     */ import com.destroystokyo.paper.event.entity.SlimeSwimEvent;
/*     */ import com.destroystokyo.paper.event.entity.SlimeTargetLivingEntityEvent;
/*     */ import com.destroystokyo.paper.event.entity.SlimeWanderEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Slime;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityTransformEvent;
/*     */ import org.bukkit.event.entity.SlimeSplitEvent;
/*     */ 
/*     */ public class EntitySlime
/*     */   extends EntityInsentient
/*     */   implements IMonster
/*     */ {
/*  26 */   private static final DataWatcherObject<Integer> bo = DataWatcher.a((Class)EntitySlime.class, DataWatcherRegistry.b);
/*     */   public float b;
/*     */   public float c;
/*     */   public float d;
/*     */   private boolean bp;
/*     */   private boolean canWander;
/*     */   protected void initPathfinder() { this.goalSelector.a(1, new PathfinderGoalSlimeRandomJump(this)); this.goalSelector.a(2, new PathfinderGoalSlimeNearestPlayer(this)); this.goalSelector.a(3, new PathfinderGoalSlimeRandomDirection(this)); this.goalSelector.a(5, new PathfinderGoalSlimeIdle(this)); this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, 10, true, false, entityliving -> (Math.abs(entityliving.locY() - locY()) <= 4.0D))); this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true)); }
/*  33 */   protected void initDatawatcher() { super.initDatawatcher(); this.datawatcher.register(bo, Integer.valueOf(1)); } public void setSize(int i, boolean flag) { this.datawatcher.set(bo, Integer.valueOf(i)); ae(); updateSize(); getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue((i * i)); getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue((0.2F + 0.1F * i)); getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(i); if (flag) setHealth(getMaxHealth());  this.f = i; } public int getSize() { return ((Integer)this.datawatcher.<Integer>get(bo)).intValue(); } public void saveData(NBTTagCompound nbttagcompound) { super.saveData(nbttagcompound); nbttagcompound.setBoolean("Paper.canWander", this.canWander); nbttagcompound.setInt("Size", getSize() - 1); nbttagcompound.setBoolean("wasOnGround", this.bp); } public void loadData(NBTTagCompound nbttagcompound) { int i = nbttagcompound.getInt("Size"); if (i < 0) i = 0;  setSize(i + 1, false); super.loadData(nbttagcompound); if (nbttagcompound.hasKey("Paper.canWander")) this.canWander = nbttagcompound.getBoolean("Paper.canWander");  this.bp = nbttagcompound.getBoolean("wasOnGround"); } public boolean eQ() { return (getSize() <= 1); } protected ParticleParam eI() { return Particles.ITEM_SLIME; } protected boolean L() { return (getSize() > 0); } public void tick() { this.c += (this.b - this.c) * 0.5F; this.d = this.c; super.tick(); if (this.onGround && !this.bp) { int i = getSize(); for (int j = 0; j < i * 8; j++) { float f = this.random.nextFloat() * 6.2831855F; float f1 = this.random.nextFloat() * 0.5F + 0.5F; float f2 = MathHelper.sin(f) * i * 0.5F * f1; float f3 = MathHelper.cos(f) * i * 0.5F * f1; this.world.addParticle(eI(), locX() + f2, locY(), locZ() + f3, 0.0D, 0.0D, 0.0D); }  playSound(getSoundSquish(), getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F); this.b = -0.5F; } else if (!this.onGround && this.bp) { this.b = 1.0F; }  this.bp = this.onGround; eK(); } public EntitySlime(EntityTypes<? extends EntitySlime> entitytypes, World world) { super((EntityTypes)entitytypes, world);
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
/* 558 */     this.canWander = true; this.moveController = new ControllerMoveSlime(this); }
/*     */   protected void eK() { this.b *= 0.6F; }
/* 560 */   protected int eJ() { return this.random.nextInt(20) + 10; } public void updateSize() { double d0 = locX(); double d1 = locY(); double d2 = locZ(); super.updateSize(); setPosition(d0, d1, d2); } public void a(DataWatcherObject<?> datawatcherobject) { if (bo.equals(datawatcherobject)) { updateSize(); this.yaw = this.aC; this.aA = this.aC; if (isInWater() && this.random.nextInt(20) == 0) aL();  }  super.a(datawatcherobject); } public EntityTypes<? extends EntitySlime> getEntityType() { return (EntityTypes)super.getEntityType(); } public void die() { int i = getSize(); if (!this.world.isClientSide && i > 1 && dk()) { IChatBaseComponent ichatbasecomponent = getCustomName(); boolean flag = isNoAI(); float f = i / 4.0F; int j = i / 2; int k = 2 + this.random.nextInt(3); SlimeSplitEvent event = new SlimeSplitEvent((Slime)getBukkitEntity(), k); this.world.getServer().getPluginManager().callEvent((Event)event); if (!event.isCancelled() && event.getCount() > 0) { k = event.getCount(); } else { super.die(); return; }  List<EntityLiving> slimes = new ArrayList<>(j); for (int l = 0; l < k; l++) { float f1 = ((l % 2) - 0.5F) * f; float f2 = ((l / 2) - 0.5F) * f; EntitySlime entityslime = getEntityType().a(this.world); if (isPersistent()) entityslime.setPersistent();  entityslime.setCustomName(ichatbasecomponent); entityslime.setNoAI(flag); entityslime.setInvulnerable(isInvulnerable()); entityslime.setSize(j, true); entityslime.setPositionRotation(locX() + f1, locY() + 0.5D, locZ() + f2, this.random.nextFloat() * 360.0F, 0.0F); slimes.add(entityslime); }  if (CraftEventFactory.callEntityTransformEvent(this, slimes, EntityTransformEvent.TransformReason.SPLIT).isCancelled()) return;  for (EntityLiving living : slimes) this.world.addEntity(living, CreatureSpawnEvent.SpawnReason.SLIME_SPLIT);  }  super.die(); } public void collide(Entity entity) { super.collide(entity); if (entity instanceof EntityIronGolem && eL()) i((EntityLiving)entity);  } public void pickup(EntityHuman entityhuman) { if (eL()) i(entityhuman);  } protected void i(EntityLiving entityliving) { if (isAlive()) { int i = getSize(); if (h(entityliving) < 0.6D * i * 0.6D * i && hasLineOfSight(entityliving) && entityliving.damageEntity(DamageSource.mobAttack(this), eM())) { playSound(SoundEffects.ENTITY_SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F); a(this, entityliving); }  }  } protected float b(EntityPose entitypose, EntitySize entitysize) { return 0.625F * entitysize.height; } public boolean canWander() { return this.canWander; } protected boolean eL() { return (!eQ() && doAITick()); } protected float eM() { return (float)b(GenericAttributes.ATTACK_DAMAGE); } protected SoundEffect getSoundHurt(DamageSource damagesource) { return eQ() ? SoundEffects.ENTITY_SLIME_HURT_SMALL : SoundEffects.ENTITY_SLIME_HURT; } protected SoundEffect getSoundDeath() { return eQ() ? SoundEffects.ENTITY_SLIME_DEATH_SMALL : SoundEffects.ENTITY_SLIME_DEATH; } protected SoundEffect getSoundSquish() { return eQ() ? SoundEffects.ENTITY_SLIME_SQUISH_SMALL : SoundEffects.ENTITY_SLIME_SQUISH; } protected MinecraftKey getDefaultLootTable() { return (getSize() == 1) ? getEntityType().i() : LootTables.a; } public static boolean c(EntityTypes<EntitySlime> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) { if (generatoraccess.getDifficulty() != EnumDifficulty.PEACEFUL) { if (Objects.equals(generatoraccess.i(blockposition), Optional.of(Biomes.SWAMP)) && blockposition.getY() > 50 && blockposition.getY() < 70 && random.nextFloat() < 0.5F && random.nextFloat() < generatoraccess.ae() && generatoraccess.getLightLevel(blockposition) <= random.nextInt(8)) return a((EntityTypes)entitytypes, generatoraccess, enummobspawn, blockposition, random);  if (!(generatoraccess instanceof GeneratorAccessSeed)) return false;  ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(blockposition); boolean flag = ((generatoraccess.getMinecraftWorld()).paperConfig.allChunksAreSlimeChunks || SeededRandom.a(chunkcoordintpair.x, chunkcoordintpair.z, ((GeneratorAccessSeed)generatoraccess).getSeed(), (generatoraccess.getMinecraftWorld()).spigotConfig.slimeSeed).nextInt(10) == 0); if (random.nextInt(10) == 0 && flag && blockposition.getY() < 40) return a((EntityTypes)entitytypes, generatoraccess, enummobspawn, blockposition, random);  }  return false; }
/*     */   protected float getSoundVolume() { return 0.4F * getSize(); }
/*     */   public int O() { return 0; }
/*     */   protected boolean eR() { return (getSize() > 0); }
/* 564 */   public void setWander(boolean canWander) { this.canWander = canWander; }
/*     */ 
/*     */   
/*     */   protected void jump() {
/*     */     Vec3D vec3d = getMot();
/*     */     setMot(vec3d.x, dI(), vec3d.z);
/*     */     this.impulse = true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*     */     int i = this.random.nextInt(3);
/*     */     if (i < 2 && this.random.nextFloat() < 0.5F * difficultydamagescaler.d())
/*     */       i++; 
/*     */     int j = 1 << i;
/*     */     setSize(j, true);
/*     */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   private float m() {
/*     */     float f = eQ() ? 1.4F : 0.8F;
/*     */     return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
/*     */   }
/*     */   
/*     */   protected SoundEffect getSoundJump() {
/*     */     return eQ() ? SoundEffects.ENTITY_SLIME_JUMP_SMALL : SoundEffects.ENTITY_SLIME_JUMP;
/*     */   }
/*     */   
/*     */   public EntitySize a(EntityPose entitypose) {
/*     */     return super.a(entitypose).a(0.255F * getSize());
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSlimeIdle extends PathfinderGoal {
/*     */     private final EntitySlime a;
/*     */     
/*     */     public PathfinderGoalSlimeIdle(EntitySlime entityslime) {
/*     */       this.a = entityslime;
/*     */       a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
/*     */     }
/*     */     
/*     */     public boolean a() {
/*     */       return (!this.a.isPassenger() && this.a.canWander && (new SlimeWanderEvent((Slime)this.a.getBukkitEntity())).callEvent());
/*     */     }
/*     */     
/*     */     public void e() {
/*     */       ((EntitySlime.ControllerMoveSlime)this.a.getControllerMove()).a(1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSlimeRandomJump extends PathfinderGoal {
/*     */     private final EntitySlime a;
/*     */     
/*     */     public PathfinderGoalSlimeRandomJump(EntitySlime entityslime) {
/*     */       this.a = entityslime;
/*     */       a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
/*     */       entityslime.getNavigation().d(true);
/*     */     }
/*     */     
/*     */     public boolean a() {
/*     */       return ((this.a.isInWater() || this.a.aP()) && this.a.getControllerMove() instanceof EntitySlime.ControllerMoveSlime && this.a.canWander && (new SlimeSwimEvent((Slime)this.a.getBukkitEntity())).callEvent());
/*     */     }
/*     */     
/*     */     public void e() {
/*     */       if (this.a.getRandom().nextFloat() < 0.8F)
/*     */         this.a.getControllerJump().jump(); 
/*     */       ((EntitySlime.ControllerMoveSlime)this.a.getControllerMove()).a(1.2D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSlimeRandomDirection extends PathfinderGoal {
/*     */     private final EntitySlime a;
/*     */     private float b;
/*     */     private int c;
/*     */     
/*     */     public PathfinderGoalSlimeRandomDirection(EntitySlime entityslime) {
/*     */       this.a = entityslime;
/*     */       a(EnumSet.of(PathfinderGoal.Type.LOOK));
/*     */     }
/*     */     
/*     */     public boolean a() {
/*     */       return (this.a.getGoalTarget() == null && (this.a.onGround || this.a.isInWater() || this.a.aP() || this.a.hasEffect(MobEffects.LEVITATION)) && this.a.getControllerMove() instanceof EntitySlime.ControllerMoveSlime && this.a.canWander);
/*     */     }
/*     */     
/*     */     public void e() {
/*     */       if (--this.c <= 0) {
/*     */         this.c = 40 + this.a.getRandom().nextInt(60);
/*     */         SlimeChangeDirectionEvent event = new SlimeChangeDirectionEvent((Slime)this.a.getBukkitEntity(), this.a.getRandom().nextInt(360));
/*     */         if (!this.a.canWander || !event.callEvent())
/*     */           return; 
/*     */         this.b = event.getNewYaw();
/*     */       } 
/*     */       ((EntitySlime.ControllerMoveSlime)this.a.getControllerMove()).a(this.b, false);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSlimeNearestPlayer extends PathfinderGoal {
/*     */     private final EntitySlime a;
/*     */     private int b;
/*     */     
/*     */     public PathfinderGoalSlimeNearestPlayer(EntitySlime entityslime) {
/*     */       this.a = entityslime;
/*     */       a(EnumSet.of(PathfinderGoal.Type.LOOK));
/*     */     }
/*     */     
/*     */     public boolean a() {
/*     */       EntityLiving entityliving = this.a.getGoalTarget();
/*     */       if (entityliving == null || !entityliving.isAlive())
/*     */         return false; 
/*     */       if (entityliving instanceof EntityHuman && ((EntityHuman)entityliving).abilities.isInvulnerable)
/*     */         return false; 
/*     */       return (this.a.getControllerMove() instanceof EntitySlime.ControllerMoveSlime && this.a.canWander && (new SlimeTargetLivingEntityEvent((Slime)this.a.getBukkitEntity(), (LivingEntity)entityliving.getBukkitEntity())).callEvent());
/*     */     }
/*     */     
/*     */     public void c() {
/*     */       this.b = 300;
/*     */       super.c();
/*     */     }
/*     */     
/*     */     public boolean b() {
/*     */       EntityLiving entityliving = this.a.getGoalTarget();
/*     */       if (entityliving == null || !entityliving.isAlive())
/*     */         return false; 
/*     */       if (entityliving instanceof EntityHuman && ((EntityHuman)entityliving).abilities.isInvulnerable)
/*     */         return false; 
/*     */       return (--this.b > 0 && this.a.canWander && (new SlimeTargetLivingEntityEvent((Slime)this.a.getBukkitEntity(), (LivingEntity)entityliving.getBukkitEntity())).callEvent());
/*     */     }
/*     */     
/*     */     public void e() {
/*     */       this.a.a(this.a.getGoalTarget(), 10.0F, 10.0F);
/*     */       ((EntitySlime.ControllerMoveSlime)this.a.getControllerMove()).a(this.a.yaw, this.a.eL());
/*     */     }
/*     */     
/*     */     public void d() {
/*     */       this.b = 0;
/*     */       this.a.setGoalTarget((EntityLiving)null);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ControllerMoveSlime extends ControllerMove {
/*     */     private float i;
/*     */     private int j;
/*     */     private final EntitySlime k;
/*     */     private boolean l;
/*     */     
/*     */     public ControllerMoveSlime(EntitySlime entityslime) {
/*     */       super(entityslime);
/*     */       this.k = entityslime;
/*     */       this.i = 180.0F * entityslime.yaw / 3.1415927F;
/*     */     }
/*     */     
/*     */     public void a(float f, boolean flag) {
/*     */       this.i = f;
/*     */       this.l = flag;
/*     */     }
/*     */     
/*     */     public void a(double d0) {
/*     */       this.e = d0;
/*     */       this.h = ControllerMove.Operation.MOVE_TO;
/*     */     }
/*     */     
/*     */     public void a() {
/*     */       this.a.yaw = a(this.a.yaw, this.i, 90.0F);
/*     */       this.a.aC = this.a.yaw;
/*     */       this.a.aA = this.a.yaw;
/*     */       if (this.h != ControllerMove.Operation.MOVE_TO) {
/*     */         this.a.t(0.0F);
/*     */       } else {
/*     */         this.h = ControllerMove.Operation.WAIT;
/*     */         if (this.a.isOnGround()) {
/*     */           this.a.q((float)(this.e * this.a.b(GenericAttributes.MOVEMENT_SPEED)));
/*     */           if (this.j-- <= 0) {
/*     */             this.j = this.k.eJ();
/*     */             if (this.l)
/*     */               this.j /= 3; 
/*     */             this.k.getControllerJump().jump();
/*     */             if (this.k.eR())
/*     */               this.k.playSound(this.k.getSoundJump(), this.k.getSoundVolume(), this.k.m()); 
/*     */           } else {
/*     */             this.k.aR = 0.0F;
/*     */             this.k.aT = 0.0F;
/*     */             this.a.q(0.0F);
/*     */           } 
/*     */         } else {
/*     */           this.a.q((float)(this.e * this.a.b(GenericAttributes.MOVEMENT_SPEED)));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */