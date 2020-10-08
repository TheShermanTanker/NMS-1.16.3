/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ 
/*     */ public class EntitySpider extends EntityMonster {
/*   8 */   private static final DataWatcherObject<Byte> b = DataWatcher.a((Class)EntitySpider.class, DataWatcherRegistry.a);
/*     */   
/*     */   public EntitySpider(EntityTypes<? extends EntitySpider> entitytypes, World world) {
/*  11 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  16 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  17 */     this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
/*  18 */     this.goalSelector.a(4, new PathfinderGoalSpiderMeleeAttack(this));
/*  19 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 0.8D));
/*  20 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  21 */     this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
/*  22 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, new Class[0]));
/*  23 */     this.targetSelector.a(2, new PathfinderGoalSpiderNearestAttackableTarget<>(this, EntityHuman.class));
/*  24 */     this.targetSelector.a(3, new PathfinderGoalSpiderNearestAttackableTarget<>(this, EntityIronGolem.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/*  29 */     return (getHeight() * 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World world) {
/*  34 */     return new NavigationSpider(this, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  39 */     super.initDatawatcher();
/*  40 */     this.datawatcher.register(b, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  45 */     super.tick();
/*  46 */     if (!this.world.isClientSide) {
/*  47 */       t(this.positionChanged);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  53 */     return EntityMonster.eR().a(GenericAttributes.MAX_HEALTH, 16.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  58 */     return SoundEffects.ENTITY_SPIDER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  63 */     return SoundEffects.ENTITY_SPIDER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  68 */     return SoundEffects.ENTITY_SPIDER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/*  73 */     playSound(SoundEffects.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClimbing() {
/*  78 */     return eL();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, Vec3D vec3d) {
/*  83 */     if (!iblockdata.a(Blocks.COBWEB)) {
/*  84 */       super.a(iblockdata, vec3d);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/*  91 */     return EnumMonsterType.ARTHROPOD;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean d(MobEffect mobeffect) {
/*  96 */     return (mobeffect.getMobEffect() == MobEffects.POISON) ? false : super.d(mobeffect);
/*     */   }
/*     */   
/*     */   public boolean eL() {
/* 100 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void t(boolean flag) {
/* 104 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*     */     
/* 106 */     if (flag) {
/* 107 */       b0 = (byte)(b0 | 0x1);
/*     */     } else {
/* 109 */       b0 = (byte)(b0 & 0xFFFFFFFE);
/*     */     } 
/*     */     
/* 112 */     this.datawatcher.set(b, Byte.valueOf(b0));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 118 */     Object object = super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */     
/* 120 */     if (worldaccess.getRandom().nextInt(100) == 0) {
/* 121 */       EntitySkeleton entityskeleton = EntityTypes.SKELETON.a(this.world);
/*     */       
/* 123 */       entityskeleton.setPositionRotation(locX(), locY(), locZ(), this.yaw, 0.0F);
/* 124 */       entityskeleton.prepare(worldaccess, difficultydamagescaler, enummobspawn, (GroupDataEntity)null, (NBTTagCompound)null);
/* 125 */       entityskeleton.startRiding(this);
/*     */     } 
/*     */     
/* 128 */     if (object == null) {
/* 129 */       object = new GroupDataSpider();
/* 130 */       if (worldaccess.getDifficulty() == EnumDifficulty.HARD && worldaccess.getRandom().nextFloat() < 0.1F * difficultydamagescaler.d()) {
/* 131 */         ((GroupDataSpider)object).a(worldaccess.getRandom());
/*     */       }
/*     */     } 
/*     */     
/* 135 */     if (object instanceof GroupDataSpider) {
/* 136 */       MobEffectList mobeffectlist = ((GroupDataSpider)object).a;
/*     */       
/* 138 */       if (mobeffectlist != null) {
/* 139 */         addEffect(new MobEffect(mobeffectlist, 2147483647), EntityPotionEffectEvent.Cause.SPIDER_SPAWN);
/*     */       }
/*     */     } 
/*     */     
/* 143 */     return (GroupDataEntity)object;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 148 */     return 0.65F;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSpiderNearestAttackableTarget<T extends EntityLiving>
/*     */     extends PathfinderGoalNearestAttackableTarget<T> {
/*     */     public PathfinderGoalSpiderNearestAttackableTarget(EntitySpider entityspider, Class<T> oclass) {
/* 154 */       super(entityspider, oclass, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 159 */       float f = this.e.aQ();
/*     */       
/* 161 */       return (f >= 0.5F) ? false : super.a();
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSpiderMeleeAttack
/*     */     extends PathfinderGoalMeleeAttack {
/*     */     public PathfinderGoalSpiderMeleeAttack(EntitySpider entityspider) {
/* 168 */       super(entityspider, 1.0D, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 173 */       return (super.a() && !this.a.isVehicle());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 178 */       float f = this.a.aQ();
/*     */       
/* 180 */       if (f >= 0.5F && this.a.getRandom().nextInt(100) == 0) {
/* 181 */         this.a.setGoalTarget((EntityLiving)null);
/* 182 */         return false;
/*     */       } 
/* 184 */       return super.b();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected double a(EntityLiving entityliving) {
/* 190 */       return (4.0F + entityliving.getWidth());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class GroupDataSpider
/*     */     implements GroupDataEntity
/*     */   {
/*     */     public MobEffectList a;
/*     */     
/*     */     public void a(Random random) {
/* 201 */       int i = random.nextInt(5);
/*     */       
/* 203 */       if (i <= 1) {
/* 204 */         this.a = MobEffects.FASTER_MOVEMENT;
/* 205 */       } else if (i <= 2) {
/* 206 */         this.a = MobEffects.INCREASE_DAMAGE;
/* 207 */       } else if (i <= 3) {
/* 208 */         this.a = MobEffects.REGENERATION;
/* 209 */       } else if (i <= 4) {
/* 210 */         this.a = MobEffects.INVISIBILITY;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */