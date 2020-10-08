/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.EndermanAttackPlayerEvent;
/*     */ import com.destroystokyo.paper.event.entity.EndermanEscapeEvent;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Enderman;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityEnderman extends EntityMonster implements IEntityAngerable {
/*  13 */   private static final UUID b = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
/*  14 */   private static final AttributeModifier c = new AttributeModifier(b, "Attacking speed boost", 0.15000000596046448D, AttributeModifier.Operation.ADDITION);
/*  15 */   private static final DataWatcherObject<Optional<IBlockData>> d = DataWatcher.a((Class)EntityEnderman.class, DataWatcherRegistry.h);
/*  16 */   private static final DataWatcherObject<Boolean> bo = DataWatcher.a((Class)EntityEnderman.class, DataWatcherRegistry.i);
/*  17 */   private static final DataWatcherObject<Boolean> bp = DataWatcher.a((Class)EntityEnderman.class, DataWatcherRegistry.i); private static final Predicate<EntityLiving> bq; static {
/*  18 */     bq = (entityliving -> 
/*  19 */       (entityliving instanceof EntityEndermite && ((EntityEndermite)entityliving).isPlayerSpawned()));
/*     */   }
/*  21 */   private int br = Integer.MIN_VALUE;
/*     */   private int bs;
/*  23 */   private static final IntRange bt = TimeRange.a(20, 39);
/*     */   private int bu;
/*     */   private UUID bv;
/*     */   
/*     */   public EntityEnderman(EntityTypes<? extends EntityEnderman> entitytypes, World world) {
/*  28 */     super((EntityTypes)entitytypes, world);
/*  29 */     this.G = 1.0F;
/*  30 */     a(PathType.WATER, -1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  35 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  36 */     this.goalSelector.a(1, new a(this));
/*  37 */     this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*  38 */     this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
/*  39 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  40 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  41 */     this.goalSelector.a(10, new PathfinderGoalEndermanPlaceBlock(this));
/*  42 */     this.goalSelector.a(11, new PathfinderGoalEndermanPickupBlock(this));
/*  43 */     this.targetSelector.a(1, new PathfinderGoalPlayerWhoLookedAtTarget(this, this::a_));
/*  44 */     this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
/*  45 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityEndermite.class, 10, true, false, bq));
/*  46 */     this.targetSelector.a(4, new PathfinderGoalUniversalAngerReset<>(this, false));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  50 */     return EntityMonster.eR().a(GenericAttributes.MAX_HEALTH, 40.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D).a(GenericAttributes.ATTACK_DAMAGE, 7.0D).a(GenericAttributes.FOLLOW_RANGE, 64.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGoalTarget(@Nullable EntityLiving entityliving) {
/*  56 */     setGoalTarget(entityliving, EntityTargetEvent.TargetReason.UNKNOWN, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean tryEscape(EndermanEscapeEvent.Reason reason) {
/*  61 */     return (new EndermanEscapeEvent((Enderman)getBukkitEntity(), reason)).callEvent();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
/*  67 */     if (!super.setGoalTarget(entityliving, reason, fireEvent)) {
/*  68 */       return false;
/*     */     }
/*  70 */     entityliving = getGoalTarget();
/*     */     
/*  72 */     AttributeModifiable attributemodifiable = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*     */     
/*  74 */     if (entityliving == null) {
/*  75 */       this.bs = 0;
/*  76 */       this.datawatcher.set(bo, Boolean.valueOf(false));
/*  77 */       this.datawatcher.set(bp, Boolean.valueOf(false));
/*  78 */       attributemodifiable.removeModifier(c);
/*     */     } else {
/*  80 */       this.bs = this.ticksLived;
/*  81 */       this.datawatcher.set(bo, Boolean.valueOf(true));
/*  82 */       if (!attributemodifiable.a(c)) {
/*  83 */         attributemodifiable.b(c);
/*     */       }
/*     */     } 
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  92 */     super.initDatawatcher();
/*  93 */     this.datawatcher.register(d, Optional.empty());
/*  94 */     this.datawatcher.register(bo, Boolean.valueOf(false));
/*  95 */     this.datawatcher.register(bp, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void anger() {
/* 100 */     setAnger(bt.a(this.random));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnger(int i) {
/* 105 */     this.bu = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnger() {
/* 110 */     return this.bu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAngerTarget(@Nullable UUID uuid) {
/* 115 */     this.bv = uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getAngerTarget() {
/* 120 */     return this.bv;
/*     */   }
/*     */   
/*     */   public void eK() {
/* 124 */     if (this.ticksLived >= this.br + 400) {
/* 125 */       this.br = this.ticksLived;
/* 126 */       if (!isSilent()) {
/* 127 */         this.world.a(locX(), getHeadY(), locZ(), SoundEffects.ENTITY_ENDERMAN_STARE, getSoundCategory(), 2.5F, 1.0F, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 135 */     if (bo.equals(datawatcherobject) && eO() && this.world.isClientSide) {
/* 136 */       eK();
/*     */     }
/*     */     
/* 139 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 144 */     super.saveData(nbttagcompound);
/* 145 */     IBlockData iblockdata = getCarried();
/*     */     
/* 147 */     if (iblockdata != null) {
/* 148 */       nbttagcompound.set("carriedBlockState", GameProfileSerializer.a(iblockdata));
/*     */     }
/*     */     
/* 151 */     c(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 156 */     super.loadData(nbttagcompound);
/* 157 */     IBlockData iblockdata = null;
/*     */     
/* 159 */     if (nbttagcompound.hasKeyOfType("carriedBlockState", 10)) {
/* 160 */       iblockdata = GameProfileSerializer.c(nbttagcompound.getCompound("carriedBlockState"));
/* 161 */       if (iblockdata.isAir()) {
/* 162 */         iblockdata = null;
/*     */       }
/*     */     } 
/*     */     
/* 166 */     setCarried(iblockdata);
/* 167 */     a((WorldServer)this.world, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean g(EntityHuman entityhuman) {
/* 172 */     boolean shouldAttack = g_real(entityhuman);
/* 173 */     EndermanAttackPlayerEvent event = new EndermanAttackPlayerEvent((Enderman)getBukkitEntity(), (Player)entityhuman.getBukkitEntity());
/* 174 */     event.setCancelled(!shouldAttack);
/* 175 */     return event.callEvent();
/*     */   }
/*     */   
/*     */   private boolean g_real(EntityHuman entityhuman) {
/* 179 */     ItemStack itemstack = entityhuman.inventory.armor.get(3);
/*     */     
/* 181 */     if (itemstack.getItem() == Blocks.CARVED_PUMPKIN.getItem()) {
/* 182 */       return false;
/*     */     }
/* 184 */     Vec3D vec3d = entityhuman.f(1.0F).d();
/* 185 */     Vec3D vec3d1 = new Vec3D(locX() - entityhuman.locX(), getHeadY() - entityhuman.getHeadY(), locZ() - entityhuman.locZ());
/* 186 */     double d0 = vec3d1.f();
/*     */     
/* 188 */     vec3d1 = vec3d1.d();
/* 189 */     double d1 = vec3d.b(vec3d1);
/*     */     
/* 191 */     return (d1 > 1.0D - 0.025D / d0) ? entityhuman.hasLineOfSight(this) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 197 */     return 2.55F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 202 */     if (this.world.isClientSide) {
/* 203 */       for (int i = 0; i < 2; i++) {
/* 204 */         this.world.addParticle(Particles.PORTAL, d(0.5D), cE() - 0.25D, g(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
/*     */       }
/*     */     }
/*     */     
/* 208 */     this.jumping = false;
/* 209 */     if (!this.world.isClientSide) {
/* 210 */       a((WorldServer)this.world, true);
/*     */     }
/*     */     
/* 213 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dN() {
/* 218 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 223 */     if (this.world.isDay() && this.ticksLived >= this.bs + 600) {
/* 224 */       float f = aQ();
/*     */       
/* 226 */       if (f > 0.5F && this.world.e(getChunkCoordinates()) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && tryEscape(EndermanEscapeEvent.Reason.RUNAWAY)) {
/* 227 */         setGoalTarget((EntityLiving)null);
/* 228 */         eL();
/*     */       } 
/*     */     } 
/*     */     
/* 232 */     super.mobTick();
/*     */   }
/*     */   public final boolean teleportRandomly() {
/* 235 */     return eL();
/*     */   } protected boolean eL() {
/* 237 */     if (!this.world.s_() && isAlive()) {
/* 238 */       double d0 = locX() + (this.random.nextDouble() - 0.5D) * 64.0D;
/* 239 */       double d1 = locY() + (this.random.nextInt(64) - 32);
/* 240 */       double d2 = locZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
/*     */       
/* 242 */       return p(d0, d1, d2);
/*     */     } 
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(Entity entity) {
/* 249 */     Vec3D vec3d = new Vec3D(locX() - entity.locX(), e(0.5D) - entity.getHeadY(), locZ() - entity.locZ());
/*     */     
/* 251 */     vec3d = vec3d.d();
/* 252 */     double d0 = 16.0D;
/* 253 */     double d1 = locX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
/* 254 */     double d2 = locY() + (this.random.nextInt(16) - 8) - vec3d.y * 16.0D;
/* 255 */     double d3 = locZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
/*     */     
/* 257 */     return p(d1, d2, d3);
/*     */   }
/*     */   
/*     */   private boolean p(double d0, double d1, double d2) {
/* 261 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(d0, d1, d2);
/*     */     
/* 263 */     while (blockposition_mutableblockposition.getY() > 0 && !this.world.getType(blockposition_mutableblockposition).getMaterial().isSolid()) {
/* 264 */       blockposition_mutableblockposition.c(EnumDirection.DOWN);
/*     */     }
/*     */     
/* 267 */     IBlockData iblockdata = this.world.getType(blockposition_mutableblockposition);
/* 268 */     boolean flag = iblockdata.getMaterial().isSolid();
/* 269 */     boolean flag1 = iblockdata.getFluid().a(TagsFluid.WATER);
/*     */     
/* 271 */     if (flag && !flag1) {
/* 272 */       boolean flag2 = a(d0, d1, d2, true);
/*     */       
/* 274 */       if (flag2 && !isSilent()) {
/* 275 */         this.world.playSound((EntityHuman)null, this.lastX, this.lastY, this.lastZ, SoundEffects.ENTITY_ENDERMAN_TELEPORT, getSoundCategory(), 1.0F, 1.0F);
/* 276 */         playSound(SoundEffects.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
/*     */       } 
/*     */       
/* 279 */       return flag2;
/*     */     } 
/* 281 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 287 */     return eN() ? SoundEffects.ENTITY_ENDERMAN_SCREAM : SoundEffects.ENTITY_ENDERMAN_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 292 */     return SoundEffects.ENTITY_ENDERMAN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 297 */     return SoundEffects.ENTITY_ENDERMAN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
/* 302 */     super.dropDeathLoot(damagesource, i, flag);
/* 303 */     IBlockData iblockdata = getCarried();
/*     */     
/* 305 */     if (iblockdata != null) {
/* 306 */       a(iblockdata.getBlock());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCarried(@Nullable IBlockData iblockdata) {
/* 312 */     this.datawatcher.set(d, Optional.ofNullable(iblockdata));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getCarried() {
/* 317 */     return ((Optional<IBlockData>)this.datawatcher.<Optional<IBlockData>>get(d)).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 322 */     if (isInvulnerable(damagesource))
/* 323 */       return false; 
/* 324 */     if (damagesource instanceof EntityDamageSourceIndirect) {
/* 325 */       if (tryEscape(EndermanEscapeEvent.Reason.INDIRECT)) {
/* 326 */         for (int i = 0; i < 64; i++) {
/* 327 */           if (eL()) {
/* 328 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 333 */       return false;
/*     */     } 
/* 335 */     boolean flag = super.damageEntity(damagesource, f);
/*     */     
/* 337 */     if (!this.world.s_() && !(damagesource.getEntity() instanceof EntityLiving) && this.random.nextInt(10) != 0 && tryEscape((damagesource == DamageSource.DROWN) ? EndermanEscapeEvent.Reason.DROWN : EndermanEscapeEvent.Reason.INDIRECT)) {
/* 338 */       eL();
/*     */     }
/*     */     
/* 341 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eN() {
/* 346 */     return ((Boolean)this.datawatcher.<Boolean>get(bo)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean eO() {
/* 350 */     return ((Boolean)this.datawatcher.<Boolean>get(bp)).booleanValue();
/*     */   }
/*     */   
/*     */   public void eP() {
/* 354 */     this.datawatcher.set(bp, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSpecialPersistence() {
/* 359 */     return (super.isSpecialPersistence() || getCarried() != null);
/*     */   }
/*     */   
/*     */   static class PathfinderGoalEndermanPickupBlock
/*     */     extends PathfinderGoal {
/*     */     private final EntityEnderman enderman;
/*     */     
/*     */     public PathfinderGoalEndermanPickupBlock(EntityEnderman entityenderman) {
/* 367 */       this.enderman = entityenderman;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 372 */       return (this.enderman.getCarried() != null) ? false : (!this.enderman.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? false : ((this.enderman.getRandom().nextInt(20) == 0)));
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 377 */       Random random = this.enderman.getRandom();
/* 378 */       World world = this.enderman.world;
/* 379 */       int i = MathHelper.floor(this.enderman.locX() - 2.0D + random.nextDouble() * 4.0D);
/* 380 */       int j = MathHelper.floor(this.enderman.locY() + random.nextDouble() * 3.0D);
/* 381 */       int k = MathHelper.floor(this.enderman.locZ() - 2.0D + random.nextDouble() * 4.0D);
/* 382 */       BlockPosition blockposition = new BlockPosition(i, j, k);
/* 383 */       IBlockData iblockdata = world.getTypeIfLoaded(blockposition);
/* 384 */       if (iblockdata == null)
/* 385 */         return;  Block block = iblockdata.getBlock();
/* 386 */       Vec3D vec3d = new Vec3D(MathHelper.floor(this.enderman.locX()) + 0.5D, j + 0.5D, MathHelper.floor(this.enderman.locZ()) + 0.5D);
/* 387 */       Vec3D vec3d1 = new Vec3D(i + 0.5D, j + 0.5D, k + 0.5D);
/* 388 */       MovingObjectPositionBlock movingobjectpositionblock = world.rayTrace(new RayTrace(vec3d, vec3d1, RayTrace.BlockCollisionOption.OUTLINE, RayTrace.FluidCollisionOption.NONE, this.enderman));
/* 389 */       boolean flag = movingobjectpositionblock.getBlockPosition().equals(blockposition);
/*     */       
/* 391 */       if (block.a(TagsBlock.ENDERMAN_HOLDABLE) && flag)
/*     */       {
/* 393 */         if (!CraftEventFactory.callEntityChangeBlockEvent(this.enderman, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/* 394 */           world.a(blockposition, false);
/* 395 */           this.enderman.setCarried(iblockdata.getBlock().getBlockData());
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class PathfinderGoalEndermanPlaceBlock
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityEnderman a;
/*     */     
/*     */     public PathfinderGoalEndermanPlaceBlock(EntityEnderman entityenderman) {
/* 408 */       this.a = entityenderman;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 413 */       return (this.a.getCarried() == null) ? false : (!this.a.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? false : ((this.a.getRandom().nextInt(2000) == 0)));
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 418 */       Random random = this.a.getRandom();
/* 419 */       World world = this.a.world;
/* 420 */       int i = MathHelper.floor(this.a.locX() - 1.0D + random.nextDouble() * 2.0D);
/* 421 */       int j = MathHelper.floor(this.a.locY() + random.nextDouble() * 2.0D);
/* 422 */       int k = MathHelper.floor(this.a.locZ() - 1.0D + random.nextDouble() * 2.0D);
/* 423 */       BlockPosition blockposition = new BlockPosition(i, j, k);
/* 424 */       IBlockData iblockdata = world.getTypeIfLoaded(blockposition);
/* 425 */       if (iblockdata == null)
/* 426 */         return;  BlockPosition blockposition1 = blockposition.down();
/* 427 */       IBlockData iblockdata1 = world.getType(blockposition1);
/* 428 */       IBlockData iblockdata2 = this.a.getCarried();
/*     */       
/* 430 */       if (iblockdata2 != null) {
/* 431 */         iblockdata2 = Block.b(iblockdata2, this.a.world, blockposition);
/* 432 */         if (a(world, blockposition, iblockdata2, iblockdata, iblockdata1, blockposition1))
/*     */         {
/* 434 */           if (!CraftEventFactory.callEntityChangeBlockEvent(this.a, blockposition, iblockdata2).isCancelled()) {
/* 435 */             world.setTypeAndData(blockposition, iblockdata2, 3);
/* 436 */             this.a.setCarried((IBlockData)null);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1, IBlockData iblockdata2, BlockPosition blockposition1) {
/* 445 */       return (iblockdata1.isAir() && !iblockdata2.isAir() && !iblockdata2.a(Blocks.BEDROCK) && iblockdata2.r(world, blockposition1) && iblockdata.canPlace(world, blockposition) && world.getEntities(this.a, AxisAlignedBB.a(Vec3D.b(blockposition))).isEmpty());
/*     */     }
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends PathfinderGoal {
/*     */     private final EntityEnderman a;
/*     */     private EntityLiving b;
/*     */     
/*     */     public a(EntityEnderman entityenderman) {
/* 455 */       this.a = entityenderman;
/* 456 */       a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 461 */       this.b = this.a.getGoalTarget();
/* 462 */       if (!(this.b instanceof EntityHuman)) {
/* 463 */         return false;
/*     */       }
/* 465 */       double d0 = this.b.h(this.a);
/*     */       
/* 467 */       return (d0 > 256.0D) ? false : this.a.g((EntityHuman)this.b);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void c() {
/* 473 */       this.a.getNavigation().o();
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 478 */       this.a.getControllerLook().a(this.b.locX(), this.b.getHeadY(), this.b.locZ());
/*     */     } }
/*     */   static class PathfinderGoalPlayerWhoLookedAtTarget extends PathfinderGoalNearestAttackableTarget<EntityHuman> { private final EntityEnderman i;
/*     */     private EntityHuman j;
/*     */     
/*     */     public final EntityEnderman getEnderman() {
/* 484 */       return this.i;
/*     */     }
/*     */     private int k;
/*     */     private int l;
/*     */     private final PathfinderTargetCondition m;
/* 489 */     private final PathfinderTargetCondition n = (new PathfinderTargetCondition()).c();
/*     */     
/*     */     public PathfinderGoalPlayerWhoLookedAtTarget(EntityEnderman entityenderman, @Nullable Predicate<EntityLiving> predicate) {
/* 492 */       super(entityenderman, EntityHuman.class, 10, false, false, predicate);
/* 493 */       this.i = entityenderman;
/* 494 */       this.m = (new PathfinderTargetCondition()).a(k()).a(entityliving -> entityenderman.g((EntityHuman)entityliving));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 501 */       this.j = this.i.world.a(this.m, this.i);
/* 502 */       return (this.j != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 507 */       this.k = 5;
/* 508 */       this.l = 0;
/* 509 */       this.i.eP();
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 514 */       this.j = null;
/* 515 */       super.d();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 520 */       if (this.j != null) {
/* 521 */         if (!this.i.g(this.j)) {
/* 522 */           return false;
/*     */         }
/* 524 */         this.i.a(this.j, 10.0F, 10.0F);
/* 525 */         return true;
/*     */       } 
/*     */       
/* 528 */       return (this.c != null && this.n.a(this.i, this.c)) ? true : super.b();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 534 */       if (this.i.getGoalTarget() == null) {
/* 535 */         a((EntityLiving)null);
/*     */       }
/*     */       
/* 538 */       if (this.j != null) {
/* 539 */         if (--this.k <= 0) {
/* 540 */           this.c = this.j;
/* 541 */           this.j = null;
/* 542 */           super.c();
/*     */         } 
/*     */       } else {
/* 545 */         if (this.c != null && !this.i.isPassenger()) {
/* 546 */           if (this.i.g((EntityHuman)this.c)) {
/* 547 */             if (this.c.h(this.i) < 16.0D && getEnderman().tryEscape(EndermanEscapeEvent.Reason.STARE)) {
/* 548 */               this.i.eL();
/*     */             }
/*     */             
/* 551 */             this.l = 0;
/* 552 */           } else if (this.c.h(this.i) > 256.0D && this.l++ >= 30 && this.i.a(this.c)) {
/* 553 */             this.l = 0;
/*     */           } 
/*     */         }
/*     */         
/* 557 */         super.e();
/*     */       } 
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEnderman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */