/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
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
/*     */ public class EntityGuardian
/*     */   extends EntityMonster
/*     */ {
/*  52 */   private static final DataWatcherObject<Boolean> b = DataWatcher.a((Class)EntityGuardian.class, DataWatcherRegistry.i);
/*  53 */   private static final DataWatcherObject<Integer> d = DataWatcher.a((Class)EntityGuardian.class, DataWatcherRegistry.b);
/*     */   
/*     */   private float bo;
/*     */   private float bp;
/*     */   private float bq;
/*     */   private float br;
/*     */   private float bs;
/*     */   private EntityLiving bt;
/*     */   private int bu;
/*     */   private boolean bv;
/*     */   public PathfinderGoalRandomStroll goalRandomStroll;
/*     */   
/*     */   public EntityGuardian(EntityTypes<? extends EntityGuardian> var0, World var1) {
/*  66 */     super((EntityTypes)var0, var1);
/*     */     
/*  68 */     this.f = 10;
/*     */     
/*  70 */     a(PathType.WATER, 0.0F);
/*  71 */     this.moveController = new ControllerMoveGuardian(this);
/*     */     
/*  73 */     this.bo = this.random.nextFloat();
/*  74 */     this.bp = this.bo;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  79 */     PathfinderGoalMoveTowardsRestriction var0 = new PathfinderGoalMoveTowardsRestriction(this, 1.0D);
/*  80 */     this.goalRandomStroll = new PathfinderGoalRandomStroll(this, 1.0D, 80);
/*     */     
/*  82 */     this.goalSelector.a(4, new PathfinderGoalGuardianAttack(this));
/*  83 */     this.goalSelector.a(5, var0);
/*  84 */     this.goalSelector.a(7, this.goalRandomStroll);
/*  85 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*     */     
/*  87 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityGuardian.class, 12.0F, 0.01F));
/*  88 */     this.goalSelector.a(9, new PathfinderGoalRandomLookaround(this));
/*     */ 
/*     */     
/*  91 */     this.goalRandomStroll.a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*  92 */     var0.a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     
/*  94 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityLiving.class, 10, true, false, new EntitySelectorGuardianTargetHumanSquid(this)));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eM() {
/*  98 */     return EntityMonster.eR()
/*  99 */       .a(GenericAttributes.ATTACK_DAMAGE, 6.0D)
/* 100 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.5D)
/* 101 */       .a(GenericAttributes.FOLLOW_RANGE, 16.0D)
/* 102 */       .a(GenericAttributes.MAX_HEALTH, 30.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World var0) {
/* 107 */     return new NavigationGuardian(this, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 112 */     super.initDatawatcher();
/*     */     
/* 114 */     this.datawatcher.register(b, Boolean.valueOf(false));
/* 115 */     this.datawatcher.register(d, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cL() {
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 125 */     return EnumMonsterType.WATER_MOB;
/*     */   }
/*     */   
/*     */   public boolean eN() {
/* 129 */     return ((Boolean)this.datawatcher.<Boolean>get(b)).booleanValue();
/*     */   }
/*     */   
/*     */   private void t(boolean var0) {
/* 133 */     this.datawatcher.set(b, Boolean.valueOf(var0));
/*     */   }
/*     */   
/*     */   public int eK() {
/* 137 */     return 80;
/*     */   }
/*     */   
/*     */   private void a(int var0) {
/* 141 */     this.datawatcher.set(d, Integer.valueOf(var0));
/*     */   }
/*     */   
/*     */   public boolean eO() {
/* 145 */     return (((Integer)this.datawatcher.<Integer>get(d)).intValue() != 0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving eP() {
/* 150 */     if (!eO()) {
/* 151 */       return null;
/*     */     }
/* 153 */     if (this.world.isClientSide) {
/* 154 */       if (this.bt != null) {
/* 155 */         return this.bt;
/*     */       }
/* 157 */       Entity var0 = this.world.getEntity(((Integer)this.datawatcher.<Integer>get(d)).intValue());
/* 158 */       if (var0 instanceof EntityLiving) {
/* 159 */         this.bt = (EntityLiving)var0;
/* 160 */         return this.bt;
/*     */       } 
/* 162 */       return null;
/*     */     } 
/* 164 */     return getGoalTarget();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> var0) {
/* 169 */     super.a(var0);
/*     */     
/* 171 */     if (d.equals(var0)) {
/* 172 */       this.bu = 0;
/* 173 */       this.bt = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int D() {
/* 179 */     return 160;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 184 */     return aG() ? SoundEffects.ENTITY_GUARDIAN_AMBIENT : SoundEffects.ENTITY_GUARDIAN_AMBIENT_LAND;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 189 */     return aG() ? SoundEffects.ENTITY_GUARDIAN_HURT : SoundEffects.ENTITY_GUARDIAN_HURT_LAND;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 194 */     return aG() ? SoundEffects.ENTITY_GUARDIAN_DEATH : SoundEffects.ENTITY_GUARDIAN_DEATH_LAND;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose var0, EntitySize var1) {
/* 204 */     return var1.height * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition var0, IWorldReader var1) {
/* 209 */     if (var1.getFluid(var0).a(TagsFluid.WATER)) {
/* 210 */       return 10.0F + var1.y(var0) - 0.5F;
/*     */     }
/* 212 */     return super.a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 217 */     if (isAlive()) {
/* 218 */       if (this.world.isClientSide) {
/*     */         
/* 220 */         this.bp = this.bo;
/* 221 */         if (!isInWater()) {
/* 222 */           this.bq = 2.0F;
/* 223 */           Vec3D var0 = getMot();
/* 224 */           if (var0.y > 0.0D && this.bv && !isSilent()) {
/* 225 */             this.world.a(locX(), locY(), locZ(), getSoundFlop(), getSoundCategory(), 1.0F, 1.0F, false);
/*     */           }
/* 227 */           this.bv = (var0.y < 0.0D && this.world.a(getChunkCoordinates().down(), this));
/* 228 */         } else if (eN()) {
/* 229 */           if (this.bq < 0.5F) {
/* 230 */             this.bq = 4.0F;
/*     */           } else {
/* 232 */             this.bq += (0.5F - this.bq) * 0.1F;
/*     */           } 
/*     */         } else {
/* 235 */           this.bq += (0.125F - this.bq) * 0.2F;
/*     */         } 
/* 237 */         this.bo += this.bq;
/*     */ 
/*     */         
/* 240 */         this.bs = this.br;
/* 241 */         if (!aG()) {
/* 242 */           this.br = this.random.nextFloat();
/* 243 */         } else if (eN()) {
/* 244 */           this.br += (0.0F - this.br) * 0.25F;
/*     */         } else {
/* 246 */           this.br += (1.0F - this.br) * 0.06F;
/*     */         } 
/*     */         
/* 249 */         if (eN() && isInWater()) {
/* 250 */           Vec3D var0 = f(0.0F);
/* 251 */           for (int var1 = 0; var1 < 2; var1++) {
/* 252 */             this.world.addParticle(Particles.BUBBLE, d(0.5D) - var0.x * 1.5D, cE() - var0.y * 1.5D, g(0.5D) - var0.z * 1.5D, 0.0D, 0.0D, 0.0D);
/*     */           }
/*     */         } 
/*     */         
/* 256 */         if (eO()) {
/* 257 */           if (this.bu < eK()) {
/* 258 */             this.bu++;
/*     */           }
/* 260 */           EntityLiving var0 = eP();
/* 261 */           if (var0 != null) {
/* 262 */             getControllerLook().a(var0, 90.0F, 90.0F);
/* 263 */             getControllerLook().a();
/*     */             
/* 265 */             double var1 = A(0.0F);
/* 266 */             double var3 = var0.locX() - locX();
/* 267 */             double var5 = var0.e(0.5D) - getHeadY();
/* 268 */             double var7 = var0.locZ() - locZ();
/* 269 */             double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
/* 270 */             var3 /= var9;
/* 271 */             var5 /= var9;
/* 272 */             var7 /= var9;
/* 273 */             double var11 = this.random.nextDouble();
/* 274 */             while (var11 < var9) {
/* 275 */               var11 += 1.8D - var1 + this.random.nextDouble() * (1.7D - var1);
/* 276 */               this.world.addParticle(Particles.BUBBLE, locX() + var3 * var11, getHeadY() + var5 * var11, locZ() + var7 * var11, 0.0D, 0.0D, 0.0D);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 282 */       if (aG()) {
/* 283 */         setAirTicks(300);
/*     */       }
/* 285 */       else if (this.onGround) {
/* 286 */         setMot(getMot().add(((this.random
/* 287 */               .nextFloat() * 2.0F - 1.0F) * 0.4F), 0.5D, ((this.random
/*     */               
/* 289 */               .nextFloat() * 2.0F - 1.0F) * 0.4F)));
/*     */         
/* 291 */         this.yaw = this.random.nextFloat() * 360.0F;
/* 292 */         this.onGround = false;
/* 293 */         this.impulse = true;
/*     */       } 
/*     */ 
/*     */       
/* 297 */       if (eO()) {
/* 298 */         this.yaw = this.aC;
/*     */       }
/*     */     } 
/*     */     
/* 302 */     super.movementTick();
/*     */   }
/*     */   
/*     */   protected SoundEffect getSoundFlop() {
/* 306 */     return SoundEffects.ENTITY_GUARDIAN_FLOP;
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
/*     */   public float A(float var0) {
/* 318 */     return (this.bu + var0) / eK();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader var0) {
/* 323 */     return var0.j(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(EntityTypes<? extends EntityGuardian> var0, GeneratorAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/* 328 */     return ((var4.nextInt(20) == 0 || !var1.x(var3)) && var1
/* 329 */       .getDifficulty() != EnumDifficulty.PEACEFUL && (var2 == EnumMobSpawn.SPAWNER || var1
/* 330 */       .getFluid(var3).a(TagsFluid.WATER)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource var0, float var1) {
/* 335 */     if (!eN() && !var0.isMagic() && var0.j() instanceof EntityLiving) {
/* 336 */       EntityLiving var2 = (EntityLiving)var0.j();
/*     */ 
/*     */       
/* 339 */       if (!var0.isExplosion()) {
/* 340 */         var2.damageEntity(DamageSource.a(this), 2.0F);
/*     */       }
/*     */     } 
/*     */     
/* 344 */     if (this.goalRandomStroll != null) {
/* 345 */       this.goalRandomStroll.h();
/*     */     }
/*     */     
/* 348 */     return super.damageEntity(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int O() {
/* 353 */     return 180;
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(Vec3D var0) {
/* 358 */     if (doAITick() && isInWater()) {
/* 359 */       a(0.1F, var0);
/* 360 */       move(EnumMoveType.SELF, getMot());
/*     */       
/* 362 */       setMot(getMot().a(0.9D));
/*     */       
/* 364 */       if (!eN() && getGoalTarget() == null) {
/* 365 */         setMot(getMot().add(0.0D, -0.005D, 0.0D));
/*     */       }
/*     */     } else {
/* 368 */       super.g(var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   static class EntitySelectorGuardianTargetHumanSquid implements Predicate<EntityLiving> {
/*     */     private final EntityGuardian a;
/*     */     
/*     */     public EntitySelectorGuardianTargetHumanSquid(EntityGuardian var0) {
/* 376 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(@Nullable EntityLiving var0) {
/* 381 */       return ((var0 instanceof EntityHuman || var0 instanceof EntitySquid) && var0.h(this.a) > 9.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalGuardianAttack extends PathfinderGoal {
/*     */     private final EntityGuardian a;
/*     */     private int b;
/*     */     private final boolean c;
/*     */     
/*     */     public PathfinderGoalGuardianAttack(EntityGuardian var0) {
/* 391 */       this.a = var0;
/*     */ 
/*     */       
/* 394 */       this.c = var0 instanceof EntityGuardianElder;
/*     */       
/* 396 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 401 */       EntityLiving var0 = this.a.getGoalTarget();
/* 402 */       return (var0 != null && var0.isAlive());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 407 */       return (super.b() && (this.c || this.a.h(this.a.getGoalTarget()) > 9.0D));
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 412 */       this.b = -10;
/* 413 */       this.a.getNavigation().o();
/* 414 */       this.a.getControllerLook().a(this.a.getGoalTarget(), 90.0F, 90.0F);
/*     */ 
/*     */       
/* 417 */       this.a.impulse = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 422 */       EntityGuardian.a(this.a, 0);
/* 423 */       this.a.setGoalTarget((EntityLiving)null);
/*     */       
/* 425 */       this.a.goalRandomStroll.h();
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 430 */       EntityLiving var0 = this.a.getGoalTarget();
/*     */       
/* 432 */       this.a.getNavigation().o();
/* 433 */       this.a.getControllerLook().a(var0, 90.0F, 90.0F);
/*     */       
/* 435 */       if (!this.a.hasLineOfSight(var0)) {
/* 436 */         this.a.setGoalTarget((EntityLiving)null);
/*     */         
/*     */         return;
/*     */       } 
/* 440 */       this.b++;
/* 441 */       if (this.b == 0) {
/*     */         
/* 443 */         EntityGuardian.a(this.a, this.a.getGoalTarget().getId());
/* 444 */         if (!this.a.isSilent()) {
/* 445 */           this.a.world.broadcastEntityEffect(this.a, (byte)21);
/*     */         }
/* 447 */       } else if (this.b >= this.a.eK()) {
/* 448 */         float var1 = 1.0F;
/* 449 */         if (this.a.world.getDifficulty() == EnumDifficulty.HARD) {
/* 450 */           var1 += 2.0F;
/*     */         }
/* 452 */         if (this.c) {
/* 453 */           var1 += 2.0F;
/*     */         }
/* 455 */         var0.damageEntity(DamageSource.c(this.a, this.a), var1);
/* 456 */         var0.damageEntity(DamageSource.mobAttack(this.a), (float)this.a.b(GenericAttributes.ATTACK_DAMAGE));
/* 457 */         this.a.setGoalTarget((EntityLiving)null);
/*     */       } 
/*     */       
/* 460 */       super.e();
/*     */     }
/*     */   }
/*     */   
/*     */   static class ControllerMoveGuardian extends ControllerMove {
/*     */     private final EntityGuardian i;
/*     */     
/*     */     public ControllerMoveGuardian(EntityGuardian var0) {
/* 468 */       super(var0);
/* 469 */       this.i = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 474 */       if (this.h != ControllerMove.Operation.MOVE_TO || this.i.getNavigation().m()) {
/*     */         
/* 476 */         this.i.q(0.0F);
/* 477 */         EntityGuardian.a(this.i, false);
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 485 */       Vec3D var0 = new Vec3D(this.b - this.i.locX(), this.c - this.i.locY(), this.d - this.i.locZ());
/*     */       
/* 487 */       double var1 = var0.f();
/*     */       
/* 489 */       double var3 = var0.x / var1;
/* 490 */       double var5 = var0.y / var1;
/* 491 */       double var7 = var0.z / var1;
/*     */       
/* 493 */       float var9 = (float)(MathHelper.d(var0.z, var0.x) * 57.2957763671875D) - 90.0F;
/*     */       
/* 495 */       this.i.yaw = a(this.i.yaw, var9, 90.0F);
/* 496 */       this.i.aA = this.i.yaw;
/*     */       
/* 498 */       float var10 = (float)(this.e * this.i.b(GenericAttributes.MOVEMENT_SPEED));
/* 499 */       float var11 = MathHelper.g(0.125F, this.i.dM(), var10);
/* 500 */       this.i.q(var11);
/* 501 */       double var12 = Math.sin((this.i.ticksLived + this.i.getId()) * 0.5D) * 0.05D;
/* 502 */       double var14 = Math.cos((this.i.yaw * 0.017453292F));
/* 503 */       double var16 = Math.sin((this.i.yaw * 0.017453292F));
/* 504 */       double var18 = Math.sin((this.i.ticksLived + this.i.getId()) * 0.75D) * 0.05D;
/*     */       
/* 506 */       this.i.setMot(this.i.getMot().add(var12 * var14, var18 * (var16 + var14) * 0.25D + var11 * var5 * 0.1D, var12 * var16));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 512 */       ControllerLook var20 = this.i.getControllerLook();
/* 513 */       double var21 = this.i.locX() + var3 * 2.0D;
/* 514 */       double var23 = this.i.getHeadY() + var5 / var1;
/* 515 */       double var25 = this.i.locZ() + var7 * 2.0D;
/* 516 */       double var27 = var20.d();
/* 517 */       double var29 = var20.e();
/* 518 */       double var31 = var20.f();
/* 519 */       if (!var20.c()) {
/* 520 */         var27 = var21;
/* 521 */         var29 = var23;
/* 522 */         var31 = var25;
/*     */       } 
/* 524 */       this.i.getControllerLook().a(MathHelper.d(0.125D, var27, var21), MathHelper.d(0.125D, var29, var23), MathHelper.d(0.125D, var31, var25), 10.0F, 40.0F);
/* 525 */       EntityGuardian.a(this.i, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */