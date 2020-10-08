/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Comparator;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityPhantom extends EntityFlying implements IMonster {
/*  11 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityPhantom.class, DataWatcherRegistry.b); private Vec3D c;
/*     */   private BlockPosition d;
/*     */   private AttackPhase bo;
/*     */   UUID spawningEntity;
/*     */   
/*     */   public EntityPhantom(EntityTypes<? extends EntityPhantom> entitytypes, World world) {
/*  17 */     super((EntityTypes)entitytypes, world);
/*  18 */     this.c = Vec3D.ORIGIN;
/*  19 */     this.d = BlockPosition.ZERO;
/*  20 */     this.bo = AttackPhase.CIRCLE;
/*  21 */     this.f = 5;
/*  22 */     this.moveController = new g(this);
/*  23 */     this.lookController = new f(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityAIBodyControl r() {
/*  28 */     return new d(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  33 */     this.goalSelector.a(1, new c());
/*  34 */     this.goalSelector.a(2, new i());
/*  35 */     this.goalSelector.a(3, new e());
/*  36 */     this.targetSelector.a(1, new b());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  41 */     super.initDatawatcher();
/*  42 */     this.datawatcher.register(b, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void setSize(int i) {
/*  46 */     this.datawatcher.set(b, Integer.valueOf(MathHelper.clamp(i, 0, 64)));
/*     */   }
/*     */   
/*     */   private void eJ() {
/*  50 */     updateSize();
/*  51 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue((6 + getSize()));
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  55 */     return ((Integer)this.datawatcher.<Integer>get(b)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  60 */     return entitysize.height * 0.35F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/*  65 */     if (b.equals(datawatcherobject)) {
/*  66 */       eJ();
/*     */     }
/*     */     
/*  69 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean L() {
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  79 */     super.tick();
/*  80 */     if (this.world.isClientSide) {
/*  81 */       float f = MathHelper.cos((getId() * 3 + this.ticksLived) * 0.13F + 3.1415927F);
/*  82 */       float f1 = MathHelper.cos((getId() * 3 + this.ticksLived + 1) * 0.13F + 3.1415927F);
/*     */       
/*  84 */       if (f > 0.0F && f1 <= 0.0F) {
/*  85 */         this.world.a(locX(), locY(), locZ(), SoundEffects.ENTITY_PHANTOM_FLAP, getSoundCategory(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
/*     */       }
/*     */       
/*  88 */       int i = getSize();
/*  89 */       float f2 = MathHelper.cos(this.yaw * 0.017453292F) * (1.3F + 0.21F * i);
/*  90 */       float f3 = MathHelper.sin(this.yaw * 0.017453292F) * (1.3F + 0.21F * i);
/*  91 */       float f4 = (0.3F + f * 0.45F) * (i * 0.2F + 1.0F);
/*     */       
/*  93 */       this.world.addParticle(Particles.MYCELIUM, locX() + f2, locY() + f4, locZ() + f3, 0.0D, 0.0D, 0.0D);
/*  94 */       this.world.addParticle(Particles.MYCELIUM, locX() - f2, locY() + f4, locZ() - f3, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 101 */     if (isAlive() && eG()) {
/* 102 */       setOnFire(8);
/*     */     }
/*     */     
/* 105 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 110 */     super.mobTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 115 */     this.d = getChunkCoordinates().up(5);
/* 116 */     setSize(0);
/* 117 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 122 */     super.loadData(nbttagcompound);
/* 123 */     if (nbttagcompound.hasKey("AX")) {
/* 124 */       this.d = new BlockPosition(nbttagcompound.getInt("AX"), nbttagcompound.getInt("AY"), nbttagcompound.getInt("AZ"));
/*     */     }
/*     */     
/* 127 */     setSize(nbttagcompound.getInt("Size"));
/*     */     
/* 129 */     if (nbttagcompound.hasUUID("Paper.SpawningEntity")) {
/* 130 */       this.spawningEntity = nbttagcompound.getUUID("Paper.SpawningEntity");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 137 */     super.saveData(nbttagcompound);
/* 138 */     nbttagcompound.setInt("AX", this.d.getX());
/* 139 */     nbttagcompound.setInt("AY", this.d.getY());
/* 140 */     nbttagcompound.setInt("AZ", this.d.getZ());
/* 141 */     nbttagcompound.setInt("Size", getSize());
/*     */     
/* 143 */     if (this.spawningEntity != null) {
/* 144 */       nbttagcompound.setUUID("Paper.SpawningEntity", this.spawningEntity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/* 151 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 156 */     return SoundEffects.ENTITY_PHANTOM_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 161 */     return SoundEffects.ENTITY_PHANTOM_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 166 */     return SoundEffects.ENTITY_PHANTOM_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 171 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 176 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityTypes<?> entitytypes) {
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize a(EntityPose entitypose) {
/* 186 */     int i = getSize();
/* 187 */     EntitySize entitysize = super.a(entitypose);
/* 188 */     float f = (entitysize.width + 0.2F * i) / entitysize.width;
/*     */     
/* 190 */     return entitysize.a(f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UUID getSpawningEntity() {
/* 197 */     return this.spawningEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class b
/*     */     extends PathfinderGoal
/*     */   {
/* 207 */     private final PathfinderTargetCondition b = (new PathfinderTargetCondition()).a(64.0D);
/* 208 */     private int c = 20;
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 213 */       if (this.c > 0) {
/* 214 */         this.c--;
/* 215 */         return false;
/*     */       } 
/* 217 */       this.c = 60;
/* 218 */       List<EntityHuman> list = EntityPhantom.this.world.a(this.b, EntityPhantom.this, EntityPhantom.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
/*     */       
/* 220 */       if (!list.isEmpty()) {
/* 221 */         list.sort(Comparator.<EntityHuman, Comparable>comparing(Entity::locY).reversed());
/* 222 */         Iterator<EntityHuman> iterator = list.iterator();
/*     */         
/* 224 */         while (iterator.hasNext()) {
/* 225 */           EntityHuman entityhuman = iterator.next();
/*     */           
/* 227 */           if (EntityPhantom.this.a(entityhuman, PathfinderTargetCondition.a)) {
/* 228 */             if (!EntityPhantom.this.world.paperConfig.phantomOnlyAttackInsomniacs || IEntitySelector.isInsomniac.test(entityhuman))
/* 229 */               EntityPhantom.this.setGoalTarget(entityhuman, EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true); 
/* 230 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 235 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 241 */       EntityLiving entityliving = EntityPhantom.this.getGoalTarget();
/*     */       
/* 243 */       return (entityliving != null) ? EntityPhantom.this.a(entityliving, PathfinderTargetCondition.a) : false;
/*     */     }
/*     */     
/*     */     private b() {}
/*     */   }
/*     */   
/*     */   class c extends PathfinderGoal {
/*     */     private int b;
/*     */     
/*     */     private c() {}
/*     */     
/*     */     public boolean a() {
/* 255 */       EntityLiving entityliving = EntityPhantom.this.getGoalTarget();
/*     */       
/* 257 */       return (entityliving != null) ? EntityPhantom.this.a(EntityPhantom.this.getGoalTarget(), PathfinderTargetCondition.a) : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 262 */       this.b = 10;
/* 263 */       EntityPhantom.this.bo = EntityPhantom.AttackPhase.CIRCLE;
/* 264 */       g();
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 269 */       EntityPhantom.this.d = EntityPhantom.this.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, EntityPhantom.this.d).up(10 + EntityPhantom.this.random.nextInt(20));
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 274 */       if (EntityPhantom.this.bo == EntityPhantom.AttackPhase.CIRCLE) {
/* 275 */         this.b--;
/* 276 */         if (this.b <= 0) {
/* 277 */           EntityPhantom.this.bo = EntityPhantom.AttackPhase.SWOOP;
/* 278 */           g();
/* 279 */           this.b = (8 + EntityPhantom.this.random.nextInt(4)) * 20;
/* 280 */           EntityPhantom.this.playSound(SoundEffects.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + EntityPhantom.this.random.nextFloat() * 0.1F);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void g() {
/* 287 */       EntityPhantom.this.d = EntityPhantom.this.getGoalTarget().getChunkCoordinates().up(20 + EntityPhantom.this.random.nextInt(20));
/* 288 */       if (EntityPhantom.this.d.getY() < EntityPhantom.this.world.getSeaLevel()) {
/* 289 */         EntityPhantom.this.d = new BlockPosition(EntityPhantom.this.d.getX(), EntityPhantom.this.world.getSeaLevel() + 1, EntityPhantom.this.d.getZ());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class i
/*     */     extends h
/*     */   {
/*     */     private i() {}
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 303 */       return (EntityPhantom.this.getGoalTarget() != null && EntityPhantom.this.bo == EntityPhantom.AttackPhase.SWOOP);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 308 */       EntityLiving entityliving = EntityPhantom.this.getGoalTarget();
/*     */       
/* 310 */       if (entityliving == null)
/* 311 */         return false; 
/* 312 */       if (!entityliving.isAlive())
/* 313 */         return false; 
/* 314 */       if (entityliving instanceof EntityHuman && (((EntityHuman)entityliving).isSpectator() || ((EntityHuman)entityliving).isCreative()))
/* 315 */         return false; 
/* 316 */       if (!a()) {
/* 317 */         return false;
/*     */       }
/* 319 */       if (EntityPhantom.this.ticksLived % 20 == 0) {
/* 320 */         List<EntityCat> list = (List)EntityPhantom.this.world.a((Class)EntityCat.class, EntityPhantom.this.getBoundingBox().g(16.0D), IEntitySelector.a);
/*     */         
/* 322 */         if (!list.isEmpty()) {
/* 323 */           Iterator<EntityCat> iterator = list.iterator();
/*     */           
/* 325 */           while (iterator.hasNext()) {
/* 326 */             EntityCat entitycat = iterator.next();
/*     */             
/* 328 */             entitycat.eZ();
/*     */           } 
/*     */           
/* 331 */           return false;
/*     */         } 
/*     */       } 
/*     */       
/* 335 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void c() {}
/*     */ 
/*     */     
/*     */     public void d() {
/* 344 */       EntityPhantom.this.setGoalTarget((EntityLiving)null);
/* 345 */       EntityPhantom.this.bo = EntityPhantom.AttackPhase.CIRCLE;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 350 */       EntityLiving entityliving = EntityPhantom.this.getGoalTarget();
/*     */       
/* 352 */       EntityPhantom.this.c = new Vec3D(entityliving.locX(), entityliving.e(0.5D), entityliving.locZ());
/* 353 */       if (EntityPhantom.this.getBoundingBox().g(0.20000000298023224D).c(entityliving.getBoundingBox())) {
/* 354 */         EntityPhantom.this.attackEntity(entityliving);
/* 355 */         EntityPhantom.this.bo = EntityPhantom.AttackPhase.CIRCLE;
/* 356 */         if (!EntityPhantom.this.isSilent()) {
/* 357 */           EntityPhantom.this.world.triggerEffect(1039, EntityPhantom.this.getChunkCoordinates(), 0);
/*     */         }
/* 359 */       } else if (EntityPhantom.this.positionChanged || EntityPhantom.this.hurtTicks > 0) {
/* 360 */         EntityPhantom.this.bo = EntityPhantom.AttackPhase.CIRCLE;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class e
/*     */     extends h
/*     */   {
/*     */     private float c;
/*     */     
/*     */     private float d;
/*     */     
/*     */     private float e;
/*     */     private float f;
/*     */     
/*     */     private e() {}
/*     */     
/*     */     public boolean a() {
/* 379 */       return (EntityPhantom.this.getGoalTarget() == null || EntityPhantom.this.bo == EntityPhantom.AttackPhase.CIRCLE);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 384 */       this.d = 5.0F + EntityPhantom.this.random.nextFloat() * 10.0F;
/* 385 */       this.e = -4.0F + EntityPhantom.this.random.nextFloat() * 9.0F;
/* 386 */       this.f = EntityPhantom.this.random.nextBoolean() ? 1.0F : -1.0F;
/* 387 */       h();
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 392 */       if (EntityPhantom.this.random.nextInt(350) == 0) {
/* 393 */         this.e = -4.0F + EntityPhantom.this.random.nextFloat() * 9.0F;
/*     */       }
/*     */       
/* 396 */       if (EntityPhantom.this.random.nextInt(250) == 0) {
/* 397 */         this.d++;
/* 398 */         if (this.d > 15.0F) {
/* 399 */           this.d = 5.0F;
/* 400 */           this.f = -this.f;
/*     */         } 
/*     */       } 
/*     */       
/* 404 */       if (EntityPhantom.this.random.nextInt(450) == 0) {
/* 405 */         this.c = EntityPhantom.this.random.nextFloat() * 2.0F * 3.1415927F;
/* 406 */         h();
/*     */       } 
/*     */       
/* 409 */       if (g()) {
/* 410 */         h();
/*     */       }
/*     */       
/* 413 */       if (EntityPhantom.this.c.y < EntityPhantom.this.locY() && !EntityPhantom.this.world.isEmpty(EntityPhantom.this.getChunkCoordinates().down(1))) {
/* 414 */         this.e = Math.max(1.0F, this.e);
/* 415 */         h();
/*     */       } 
/*     */       
/* 418 */       if (EntityPhantom.this.c.y > EntityPhantom.this.locY() && !EntityPhantom.this.world.isEmpty(EntityPhantom.this.getChunkCoordinates().up(1))) {
/* 419 */         this.e = Math.min(-1.0F, this.e);
/* 420 */         h();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void h() {
/* 426 */       if (BlockPosition.ZERO.equals(EntityPhantom.this.d)) {
/* 427 */         EntityPhantom.this.d = EntityPhantom.this.getChunkCoordinates();
/*     */       }
/*     */       
/* 430 */       this.c += this.f * 15.0F * 0.017453292F;
/* 431 */       EntityPhantom.this.c = Vec3D.b(EntityPhantom.this.d).add((this.d * MathHelper.cos(this.c)), (-4.0F + this.e), (this.d * MathHelper.sin(this.c)));
/*     */     }
/*     */   }
/*     */   
/*     */   abstract class h
/*     */     extends PathfinderGoal {
/*     */     public h() {
/* 438 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */     
/*     */     protected boolean g() {
/* 442 */       return (EntityPhantom.this.c.c(EntityPhantom.this.locX(), EntityPhantom.this.locY(), EntityPhantom.this.locZ()) < 4.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   class f
/*     */     extends ControllerLook {
/*     */     public f(EntityInsentient entityinsentient) {
/* 449 */       super(entityinsentient);
/*     */     }
/*     */     
/*     */     public void a() {}
/*     */   }
/*     */   
/*     */   class d
/*     */     extends EntityAIBodyControl
/*     */   {
/*     */     public d(EntityInsentient entityinsentient) {
/* 459 */       super(entityinsentient);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 464 */       EntityPhantom.this.aC = EntityPhantom.this.aA;
/* 465 */       EntityPhantom.this.aA = EntityPhantom.this.yaw;
/*     */     }
/*     */   }
/*     */   
/*     */   class g
/*     */     extends ControllerMove {
/* 471 */     private float j = 0.1F;
/*     */     
/*     */     public g(EntityInsentient entityinsentient) {
/* 474 */       super(entityinsentient);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 479 */       if (EntityPhantom.this.positionChanged) {
/* 480 */         EntityPhantom.this.yaw += 180.0F;
/* 481 */         this.j = 0.1F;
/*     */       } 
/*     */       
/* 484 */       float f = (float)(EntityPhantom.this.c.x - EntityPhantom.this.locX());
/* 485 */       float f1 = (float)(EntityPhantom.this.c.y - EntityPhantom.this.locY());
/* 486 */       float f2 = (float)(EntityPhantom.this.c.z - EntityPhantom.this.locZ());
/* 487 */       double d0 = MathHelper.c(f * f + f2 * f2);
/* 488 */       double d1 = 1.0D - MathHelper.e(f1 * 0.7F) / d0;
/*     */       
/* 490 */       f = (float)(f * d1);
/* 491 */       f2 = (float)(f2 * d1);
/* 492 */       d0 = MathHelper.c(f * f + f2 * f2);
/* 493 */       double d2 = MathHelper.c(f * f + f2 * f2 + f1 * f1);
/* 494 */       float f3 = EntityPhantom.this.yaw;
/* 495 */       float f4 = (float)MathHelper.d(f2, f);
/* 496 */       float f5 = MathHelper.g(EntityPhantom.this.yaw + 90.0F);
/* 497 */       float f6 = MathHelper.g(f4 * 57.295776F);
/*     */       
/* 499 */       EntityPhantom.this.yaw = MathHelper.d(f5, f6, 4.0F) - 90.0F;
/* 500 */       EntityPhantom.this.aA = EntityPhantom.this.yaw;
/* 501 */       if (MathHelper.d(f3, EntityPhantom.this.yaw) < 3.0F) {
/* 502 */         this.j = MathHelper.c(this.j, 1.8F, 0.005F * 1.8F / this.j);
/*     */       } else {
/* 504 */         this.j = MathHelper.c(this.j, 0.2F, 0.025F);
/*     */       } 
/*     */       
/* 507 */       float f7 = (float)-(MathHelper.d(-f1, d0) * 57.2957763671875D);
/*     */       
/* 509 */       EntityPhantom.this.pitch = f7;
/* 510 */       float f8 = EntityPhantom.this.yaw + 90.0F;
/* 511 */       double d3 = (this.j * MathHelper.cos(f8 * 0.017453292F)) * Math.abs(f / d2);
/* 512 */       double d4 = (this.j * MathHelper.sin(f8 * 0.017453292F)) * Math.abs(f2 / d2);
/* 513 */       double d5 = (this.j * MathHelper.sin(f7 * 0.017453292F)) * Math.abs(f1 / d2);
/* 514 */       Vec3D vec3d = EntityPhantom.this.getMot();
/*     */       
/* 516 */       EntityPhantom.this.setMot(vec3d.e((new Vec3D(d3, d5, d4)).d(vec3d).a(0.2D)));
/*     */     }
/*     */   }
/*     */   
/*     */   enum AttackPhase
/*     */   {
/* 522 */     CIRCLE, SWOOP;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPhantom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */