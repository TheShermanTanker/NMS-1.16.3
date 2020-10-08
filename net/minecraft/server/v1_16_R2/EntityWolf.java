/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityWolf
/*     */   extends EntityTameableAnimal
/*     */   implements IEntityAngerable
/*     */ {
/*  14 */   private static final DataWatcherObject<Boolean> br = DataWatcher.a((Class)EntityWolf.class, DataWatcherRegistry.i);
/*  15 */   private static final DataWatcherObject<Integer> bs = DataWatcher.a((Class)EntityWolf.class, DataWatcherRegistry.b);
/*  16 */   private static final DataWatcherObject<Integer> bt = DataWatcher.a((Class)EntityWolf.class, DataWatcherRegistry.b); public static final Predicate<EntityLiving> bq; static {
/*  17 */     bq = (entityliving -> {
/*     */         EntityTypes<?> entitytypes = entityliving.getEntityType();
/*     */         
/*  20 */         return (entitytypes == EntityTypes.SHEEP || entitytypes == EntityTypes.RABBIT || entitytypes == EntityTypes.FOX);
/*     */       });
/*     */   }
/*     */   private float bu; private float bv;
/*     */   private boolean bw;
/*     */   private boolean bx;
/*     */   private float by;
/*     */   private float bz;
/*  28 */   private static final IntRange bA = TimeRange.a(20, 39);
/*     */   private UUID bB;
/*     */   
/*     */   public EntityWolf(EntityTypes<? extends EntityWolf> entitytypes, World world) {
/*  32 */     super((EntityTypes)entitytypes, world);
/*  33 */     setTamed(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  38 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  39 */     this.goalSelector.a(2, new PathfinderGoalSit(this));
/*  40 */     this.goalSelector.a(3, new a<>(this, EntityLlama.class, 24.0F, 1.5D, 1.5D));
/*  41 */     this.goalSelector.a(4, new PathfinderGoalLeapAtTarget(this, 0.4F));
/*  42 */     this.goalSelector.a(5, new PathfinderGoalMeleeAttack(this, 1.0D, true));
/*  43 */     this.goalSelector.a(6, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 2.0F, false));
/*  44 */     this.goalSelector.a(7, new PathfinderGoalBreed(this, 1.0D));
/*  45 */     this.goalSelector.a(8, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  46 */     this.goalSelector.a(9, new PathfinderGoalBeg(this, 8.0F));
/*  47 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  48 */     this.goalSelector.a(10, new PathfinderGoalRandomLookaround(this));
/*  49 */     this.targetSelector.a(1, new PathfinderGoalOwnerHurtByTarget(this));
/*  50 */     this.targetSelector.a(2, new PathfinderGoalOwnerHurtTarget(this));
/*  51 */     this.targetSelector.a(3, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
/*  52 */     this.targetSelector.a(4, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, 10, true, false, this::a_));
/*  53 */     this.targetSelector.a(5, new PathfinderGoalRandomTargetNonTamed<>(this, EntityAnimal.class, false, bq));
/*  54 */     this.targetSelector.a(6, new PathfinderGoalRandomTargetNonTamed<>(this, EntityTurtle.class, false, EntityTurtle.bo));
/*  55 */     this.targetSelector.a(7, new PathfinderGoalNearestAttackableTarget<>(this, EntitySkeletonAbstract.class, false));
/*  56 */     this.targetSelector.a(8, new PathfinderGoalUniversalAngerReset<>(this, true));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eU() {
/*  60 */     return EntityInsentient.p().a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D).a(GenericAttributes.MAX_HEALTH, 8.0D).a(GenericAttributes.ATTACK_DAMAGE, 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fire) {
/*  66 */     if (!super.setGoalTarget(entityliving, reason, fire)) {
/*  67 */       return false;
/*     */     }
/*  69 */     entityliving = getGoalTarget();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  83 */     super.initDatawatcher();
/*  84 */     this.datawatcher.register(br, Boolean.valueOf(false));
/*  85 */     this.datawatcher.register(bs, Integer.valueOf(EnumColor.RED.getColorIndex()));
/*  86 */     this.datawatcher.register(bt, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/*  91 */     playSound(SoundEffects.ENTITY_WOLF_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  96 */     super.saveData(nbttagcompound);
/*  97 */     nbttagcompound.setByte("CollarColor", (byte)getCollarColor().getColorIndex());
/*  98 */     c(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 103 */     super.loadData(nbttagcompound);
/* 104 */     if (nbttagcompound.hasKeyOfType("CollarColor", 99)) {
/* 105 */       setCollarColor(EnumColor.fromColorIndex(nbttagcompound.getInt("CollarColor")));
/*     */     }
/*     */     
/* 108 */     a((WorldServer)this.world, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 113 */     return isAngry() ? SoundEffects.ENTITY_WOLF_GROWL : ((this.random.nextInt(3) == 0) ? ((isTamed() && getHealth() < 10.0F) ? SoundEffects.ENTITY_WOLF_WHINE : SoundEffects.ENTITY_WOLF_PANT) : SoundEffects.ENTITY_WOLF_AMBIENT);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 118 */     return SoundEffects.ENTITY_WOLF_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 123 */     return SoundEffects.ENTITY_WOLF_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 128 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 133 */     super.movementTick();
/* 134 */     if (!this.world.isClientSide && this.bw && !this.bx && !eI() && this.onGround) {
/* 135 */       this.bx = true;
/* 136 */       this.by = 0.0F;
/* 137 */       this.bz = 0.0F;
/* 138 */       this.world.broadcastEntityEffect(this, (byte)8);
/*     */     } 
/*     */     
/* 141 */     if (!this.world.isClientSide) {
/* 142 */       a((WorldServer)this.world, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 149 */     super.tick();
/* 150 */     if (isAlive()) {
/* 151 */       this.bv = this.bu;
/* 152 */       if (eY()) {
/* 153 */         this.bu += (1.0F - this.bu) * 0.4F;
/*     */       } else {
/* 155 */         this.bu += (0.0F - this.bu) * 0.4F;
/*     */       } 
/*     */       
/* 158 */       if (aF()) {
/* 159 */         this.bw = true;
/* 160 */         if (this.bx && !this.world.isClientSide) {
/* 161 */           this.world.broadcastEntityEffect(this, (byte)56);
/* 162 */           eZ();
/*     */         } 
/* 164 */       } else if ((this.bw || this.bx) && this.bx) {
/* 165 */         if (this.by == 0.0F) {
/* 166 */           playSound(SoundEffects.ENTITY_WOLF_SHAKE, getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*     */         }
/*     */         
/* 169 */         this.bz = this.by;
/* 170 */         this.by += 0.05F;
/* 171 */         if (this.bz >= 2.0F) {
/* 172 */           this.bw = false;
/* 173 */           this.bx = false;
/* 174 */           this.bz = 0.0F;
/* 175 */           this.by = 0.0F;
/*     */         } 
/*     */         
/* 178 */         if (this.by > 0.4F) {
/* 179 */           float f = (float)locY();
/* 180 */           int i = (int)(MathHelper.sin((this.by - 0.4F) * 3.1415927F) * 7.0F);
/* 181 */           Vec3D vec3d = getMot();
/*     */           
/* 183 */           for (int j = 0; j < i; j++) {
/* 184 */             float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * getWidth() * 0.5F;
/* 185 */             float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * getWidth() * 0.5F;
/*     */             
/* 187 */             this.world.addParticle(Particles.SPLASH, locX() + f1, (f + 0.8F), locZ() + f2, vec3d.x, vec3d.y, vec3d.z);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void eZ() {
/* 196 */     this.bx = false;
/* 197 */     this.by = 0.0F;
/* 198 */     this.bz = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 203 */     this.bw = false;
/* 204 */     this.bx = false;
/* 205 */     this.bz = 0.0F;
/* 206 */     this.by = 0.0F;
/* 207 */     super.die(damagesource);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 212 */     return entitysize.height * 0.8F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int O() {
/* 217 */     return isSitting() ? 20 : super.O();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 222 */     if (isInvulnerable(damagesource)) {
/* 223 */       return false;
/*     */     }
/* 225 */     Entity entity = damagesource.getEntity();
/*     */ 
/*     */     
/* 228 */     if (entity != null && !(entity instanceof EntityHuman) && !(entity instanceof EntityArrow)) {
/* 229 */       f = (f + 1.0F) / 2.0F;
/*     */     }
/*     */     
/* 232 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 238 */     boolean flag = entity.damageEntity(DamageSource.mobAttack(this), (int)b(GenericAttributes.ATTACK_DAMAGE));
/*     */     
/* 240 */     if (flag) {
/* 241 */       a(this, entity);
/*     */     }
/*     */     
/* 244 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTamed(boolean flag) {
/* 249 */     super.setTamed(flag);
/* 250 */     if (flag) {
/* 251 */       getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(20.0D);
/* 252 */       setHealth(getMaxHealth());
/*     */     } else {
/* 254 */       getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(8.0D);
/*     */     } 
/*     */     
/* 257 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 262 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 263 */     Item item = itemstack.getItem();
/*     */     
/* 265 */     if (this.world.isClientSide) {
/* 266 */       boolean flag = (i(entityhuman) || isTamed() || (item == Items.BONE && !isTamed() && !isAngry()));
/*     */       
/* 268 */       return flag ? EnumInteractionResult.CONSUME : EnumInteractionResult.PASS;
/*     */     } 
/* 270 */     if (isTamed()) {
/* 271 */       if (k(itemstack) && getHealth() < getMaxHealth()) {
/* 272 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 273 */           itemstack.subtract(1);
/*     */         }
/*     */         
/* 276 */         heal(item.getFoodInfo().getNutrition(), EntityRegainHealthEvent.RegainReason.EATING);
/* 277 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/*     */       
/* 280 */       if (!(item instanceof ItemDye)) {
/* 281 */         EnumInteractionResult enuminteractionresult = super.b(entityhuman, enumhand);
/*     */         
/* 283 */         if ((!enuminteractionresult.a() || isBaby()) && i(entityhuman)) {
/* 284 */           setWillSit(!isWillSit());
/* 285 */           this.jumping = false;
/* 286 */           this.navigation.o();
/* 287 */           setGoalTarget((EntityLiving)null, EntityTargetEvent.TargetReason.FORGOT_TARGET, true);
/* 288 */           return EnumInteractionResult.SUCCESS;
/*     */         } 
/*     */         
/* 291 */         return enuminteractionresult;
/*     */       } 
/*     */       
/* 294 */       EnumColor enumcolor = ((ItemDye)item).d();
/*     */       
/* 296 */       if (enumcolor != getCollarColor()) {
/* 297 */         setCollarColor(enumcolor);
/* 298 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 299 */           itemstack.subtract(1);
/*     */         }
/*     */         
/* 302 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/* 304 */     } else if (item == Items.BONE && !isAngry()) {
/* 305 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 306 */         itemstack.subtract(1);
/*     */       }
/*     */ 
/*     */       
/* 310 */       if (this.random.nextInt(3) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
/* 311 */         tame(entityhuman);
/* 312 */         this.navigation.o();
/* 313 */         setGoalTarget((EntityLiving)null);
/* 314 */         setWillSit(true);
/* 315 */         this.world.broadcastEntityEffect(this, (byte)7);
/*     */       } else {
/* 317 */         this.world.broadcastEntityEffect(this, (byte)6);
/*     */       } 
/*     */       
/* 320 */       return EnumInteractionResult.SUCCESS;
/*     */     } 
/*     */     
/* 323 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 329 */     Item item = itemstack.getItem();
/*     */     
/* 331 */     return (item.isFood() && item.getFoodInfo().c());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnGroup() {
/* 336 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnger() {
/* 341 */     return ((Integer)this.datawatcher.<Integer>get(bt)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnger(int i) {
/* 346 */     this.datawatcher.set(bt, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public void anger() {
/* 351 */     setAnger(bA.a(this.random));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getAngerTarget() {
/* 357 */     return this.bB;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAngerTarget(@Nullable UUID uuid) {
/* 362 */     this.bB = uuid;
/*     */   }
/*     */   
/*     */   public EnumColor getCollarColor() {
/* 366 */     return EnumColor.fromColorIndex(((Integer)this.datawatcher.<Integer>get(bs)).intValue());
/*     */   }
/*     */   
/*     */   public void setCollarColor(EnumColor enumcolor) {
/* 370 */     this.datawatcher.set(bs, Integer.valueOf(enumcolor.getColorIndex()));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityWolf createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 375 */     EntityWolf entitywolf = EntityTypes.WOLF.a(worldserver);
/* 376 */     UUID uuid = getOwnerUUID();
/*     */     
/* 378 */     if (uuid != null) {
/* 379 */       entitywolf.setOwnerUUID(uuid);
/* 380 */       entitywolf.setTamed(true);
/*     */     } 
/*     */     
/* 383 */     return entitywolf;
/*     */   }
/*     */   
/*     */   public void x(boolean flag) {
/* 387 */     this.datawatcher.set(br, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 392 */     if (entityanimal == this)
/* 393 */       return false; 
/* 394 */     if (!isTamed())
/* 395 */       return false; 
/* 396 */     if (!(entityanimal instanceof EntityWolf)) {
/* 397 */       return false;
/*     */     }
/* 399 */     EntityWolf entitywolf = (EntityWolf)entityanimal;
/*     */     
/* 401 */     return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : ((isInLove() && entitywolf.isInLove())));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eY() {
/* 406 */     return ((Boolean)this.datawatcher.<Boolean>get(br)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityLiving entityliving, EntityLiving entityliving1) {
/* 411 */     if (!(entityliving instanceof EntityCreeper) && !(entityliving instanceof EntityGhast)) {
/* 412 */       if (entityliving instanceof EntityWolf) {
/* 413 */         EntityWolf entitywolf = (EntityWolf)entityliving;
/*     */         
/* 415 */         return (!entitywolf.isTamed() || entitywolf.getOwner() != entityliving1);
/*     */       } 
/* 417 */       return (entityliving instanceof EntityHuman && entityliving1 instanceof EntityHuman && !((EntityHuman)entityliving1).a((EntityHuman)entityliving)) ? false : ((entityliving instanceof EntityHorseAbstract && ((EntityHorseAbstract)entityliving).isTamed()) ? false : ((!(entityliving instanceof EntityTameableAnimal) || !((EntityTameableAnimal)entityliving).isTamed())));
/*     */     } 
/*     */     
/* 420 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 426 */     return (!isAngry() && super.a(entityhuman));
/*     */   }
/*     */   
/*     */   class a<T extends EntityLiving>
/*     */     extends PathfinderGoalAvoidTarget<T> {
/*     */     private final EntityWolf j;
/*     */     
/*     */     public a(EntityWolf entitywolf, Class<T> oclass, float f, double d0, double d1) {
/* 434 */       super(entitywolf, oclass, f, d0, d1);
/* 435 */       this.j = entitywolf;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 440 */       return (super.a() && this.b instanceof EntityLlama) ? ((!this.j.isTamed() && a((EntityLlama)this.b))) : false;
/*     */     }
/*     */     
/*     */     private boolean a(EntityLlama entityllama) {
/* 444 */       return (entityllama.getStrength() >= EntityWolf.this.random.nextInt(5));
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 449 */       EntityWolf.this.setGoalTarget((EntityLiving)null);
/* 450 */       super.c();
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 455 */       EntityWolf.this.setGoalTarget((EntityLiving)null);
/* 456 */       super.e();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */