/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.Iterator;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.HorseJumpEvent;
/*     */ 
/*     */ public abstract class EntityHorseAbstract extends EntityAnimal implements IInventoryListener, IJumpable, ISaddleable {
/*     */   static {
/*  13 */     bw = (entityliving -> 
/*  14 */       (entityliving instanceof EntityHorseAbstract && ((EntityHorseAbstract)entityliving).hasReproduced()));
/*     */   }
/*  16 */   private static final Predicate<EntityLiving> bw; private static final PathfinderTargetCondition bx = (new PathfinderTargetCondition()).a(16.0D).a().b().c().a(bw);
/*  17 */   private static final RecipeItemStack by = RecipeItemStack.a(new IMaterial[] { Items.WHEAT, Items.SUGAR, Blocks.HAY_BLOCK.getItem(), Items.APPLE, Items.GOLDEN_CARROT, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE });
/*  18 */   private static final DataWatcherObject<Byte> bz = DataWatcher.a((Class)EntityHorseAbstract.class, DataWatcherRegistry.a);
/*  19 */   private static final DataWatcherObject<Optional<UUID>> bA = DataWatcher.a((Class)EntityHorseAbstract.class, DataWatcherRegistry.o);
/*     */   private int bB;
/*     */   private int bC;
/*     */   private int bD;
/*     */   public int bo;
/*     */   public int bp;
/*     */   protected boolean bq;
/*     */   public InventorySubcontainer inventoryChest;
/*     */   protected int bs;
/*     */   protected float jumpPower;
/*     */   private boolean canSlide;
/*     */   private float bF;
/*     */   private float bG;
/*     */   private float bH;
/*     */   private float bI;
/*     */   private float bJ;
/*     */   private float bK;
/*     */   protected boolean bu = true;
/*     */   protected int bv;
/*  38 */   public int maxDomestication = 100;
/*     */   
/*     */   protected EntityHorseAbstract(EntityTypes<? extends EntityHorseAbstract> entitytypes, World world) {
/*  41 */     super((EntityTypes)entitytypes, world);
/*  42 */     this.G = 1.0F;
/*  43 */     loadChest();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  48 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.2D));
/*  49 */     this.goalSelector.a(1, new PathfinderGoalTame(this, 1.2D));
/*  50 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D, (Class)EntityHorseAbstract.class));
/*  51 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.0D));
/*  52 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 0.7D));
/*  53 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  54 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  55 */     eV();
/*     */   }
/*     */   
/*     */   protected void eV() {
/*  59 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  64 */     super.initDatawatcher();
/*  65 */     this.datawatcher.register(bz, Byte.valueOf((byte)0));
/*  66 */     this.datawatcher.register(bA, Optional.empty());
/*     */   }
/*     */   
/*     */   protected boolean t(int i) {
/*  70 */     return ((((Byte)this.datawatcher.<Byte>get(bz)).byteValue() & i) != 0);
/*     */   }
/*     */   
/*     */   protected void d(int i, boolean flag) {
/*  74 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(bz)).byteValue();
/*     */     
/*  76 */     if (flag) {
/*  77 */       this.datawatcher.set(bz, Byte.valueOf((byte)(b0 | i)));
/*     */     } else {
/*  79 */       this.datawatcher.set(bz, Byte.valueOf((byte)(b0 & (i ^ 0xFFFFFFFF))));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/*  85 */     return t(2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/*  90 */     return ((Optional<UUID>)this.datawatcher.<Optional<UUID>>get(bA)).orElse(null);
/*     */   }
/*     */   
/*     */   public void setOwnerUUID(@Nullable UUID uuid) {
/*  94 */     this.datawatcher.set(bA, Optional.ofNullable(uuid));
/*     */   }
/*     */   
/*     */   public boolean eY() {
/*  98 */     return this.bq;
/*     */   }
/*     */   
/*     */   public void setTamed(boolean flag) {
/* 102 */     d(2, flag);
/*     */   }
/*     */   
/*     */   public void v(boolean flag) {
/* 106 */     this.bq = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void x(float f) {
/* 111 */     if (f > 6.0F && eZ()) {
/* 112 */       x(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eZ() {
/* 118 */     return t(16);
/*     */   }
/*     */   
/*     */   public boolean fa() {
/* 122 */     return t(32);
/*     */   }
/*     */   
/*     */   public boolean hasReproduced() {
/* 126 */     return t(8);
/*     */   }
/*     */   
/*     */   public void w(boolean flag) {
/* 130 */     d(8, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSaddle() {
/* 135 */     return (isAlive() && !isBaby() && isTamed());
/*     */   }
/*     */ 
/*     */   
/*     */   public void saddle(@Nullable SoundCategory soundcategory) {
/* 140 */     this.inventoryChest.setItem(0, new ItemStack(Items.SADDLE));
/* 141 */     if (soundcategory != null) {
/* 142 */       this.world.playSound((EntityHuman)null, this, SoundEffects.ENTITY_HORSE_SADDLE, soundcategory, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSaddle() {
/* 149 */     return t(4);
/*     */   }
/*     */   
/*     */   public int getTemper() {
/* 153 */     return this.bs;
/*     */   }
/*     */   
/*     */   public void setTemper(int i) {
/* 157 */     this.bs = i;
/*     */   }
/*     */   
/*     */   public int v(int i) {
/* 161 */     int j = MathHelper.clamp(getTemper() + i, 0, getMaxDomestication());
/*     */     
/* 163 */     setTemper(j);
/* 164 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 169 */     return !isVehicle();
/*     */   }
/*     */   
/*     */   private void eL() {
/* 173 */     eO();
/* 174 */     if (!isSilent()) {
/* 175 */       SoundEffect soundeffect = fg();
/*     */       
/* 177 */       if (soundeffect != null) {
/* 178 */         this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), soundeffect, getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/* 186 */     if (f > 1.0F) {
/* 187 */       playSound(SoundEffects.ENTITY_HORSE_LAND, 0.4F, 1.0F);
/*     */     }
/*     */     
/* 190 */     int i = e(f, f1);
/*     */     
/* 192 */     if (i <= 0) {
/* 193 */       return false;
/*     */     }
/* 195 */     damageEntity(DamageSource.FALL, i);
/* 196 */     if (isVehicle()) {
/* 197 */       Iterator<Entity> iterator = getAllPassengers().iterator();
/*     */       
/* 199 */       while (iterator.hasNext()) {
/* 200 */         Entity entity = iterator.next();
/*     */         
/* 202 */         entity.damageEntity(DamageSource.FALL, i);
/*     */       } 
/*     */     } 
/*     */     
/* 206 */     playBlockStepSound();
/* 207 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int e(float f, float f1) {
/* 213 */     return MathHelper.f((f * 0.5F - 3.0F) * f1);
/*     */   }
/*     */   
/*     */   protected int getChestSlots() {
/* 217 */     return 2;
/*     */   }
/*     */   
/*     */   public void loadChest() {
/* 221 */     InventorySubcontainer inventorysubcontainer = this.inventoryChest;
/*     */     
/* 223 */     this.inventoryChest = new InventorySubcontainer(getChestSlots(), (InventoryHolder)getBukkitEntity());
/* 224 */     if (inventorysubcontainer != null) {
/* 225 */       inventorysubcontainer.b(this);
/* 226 */       int i = Math.min(inventorysubcontainer.getSize(), this.inventoryChest.getSize());
/*     */       
/* 228 */       for (int j = 0; j < i; j++) {
/* 229 */         ItemStack itemstack = inventorysubcontainer.getItem(j);
/*     */         
/* 231 */         if (!itemstack.isEmpty()) {
/* 232 */           this.inventoryChest.setItem(j, itemstack.cloneItemStack());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 237 */     this.inventoryChest.a(this);
/* 238 */     fe();
/*     */   }
/*     */   
/*     */   protected void fe() {
/* 242 */     if (!this.world.isClientSide) {
/* 243 */       d(4, !this.inventoryChest.getItem(0).isEmpty());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 249 */     boolean flag = hasSaddle();
/*     */     
/* 251 */     fe();
/* 252 */     if (this.ticksLived > 20 && !flag && hasSaddle()) {
/* 253 */       playSound(SoundEffects.ENTITY_HORSE_SADDLE, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double getJumpStrength() {
/* 259 */     return b(GenericAttributes.JUMP_STRENGTH);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect fg() {
/* 264 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundDeath() {
/* 270 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 276 */     if (this.random.nextInt(3) == 0) {
/* 277 */       eU();
/*     */     }
/*     */     
/* 280 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAmbient() {
/* 286 */     if (this.random.nextInt(10) == 0 && !isFrozen()) {
/* 287 */       eU();
/*     */     }
/*     */     
/* 290 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAngry() {
/* 295 */     eU();
/* 296 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 301 */     if (!iblockdata.getMaterial().isLiquid()) {
/* 302 */       IBlockData iblockdata1 = this.world.getType(blockposition.up());
/* 303 */       SoundEffectType soundeffecttype = iblockdata.getStepSound();
/*     */       
/* 305 */       if (iblockdata1.a(Blocks.SNOW)) {
/* 306 */         soundeffecttype = iblockdata1.getStepSound();
/*     */       }
/*     */       
/* 309 */       if (isVehicle() && this.bu) {
/* 310 */         this.bv++;
/* 311 */         if (this.bv > 5 && this.bv % 3 == 0) {
/* 312 */           a(soundeffecttype);
/* 313 */         } else if (this.bv <= 5) {
/* 314 */           playSound(SoundEffects.ENTITY_HORSE_STEP_WOOD, soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*     */         } 
/* 316 */       } else if (soundeffecttype == SoundEffectType.a) {
/* 317 */         playSound(SoundEffects.ENTITY_HORSE_STEP_WOOD, soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*     */       } else {
/* 319 */         playSound(SoundEffects.ENTITY_HORSE_STEP, soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(SoundEffectType soundeffecttype) {
/* 326 */     playSound(SoundEffects.ENTITY_HORSE_GALLOP, soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder fi() {
/* 330 */     return EntityInsentient.p().a(GenericAttributes.JUMP_STRENGTH).a(GenericAttributes.MAX_HEALTH, 53.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.22499999403953552D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnGroup() {
/* 335 */     return 6;
/*     */   }
/*     */   
/*     */   public int getMaxDomestication() {
/* 339 */     return this.maxDomestication;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 344 */     return 0.8F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int D() {
/* 349 */     return 400;
/*     */   }
/*     */   
/*     */   public void f(EntityHuman entityhuman) {
/* 353 */     if (!this.world.isClientSide && (!isVehicle() || w(entityhuman)) && isTamed()) {
/* 354 */       entityhuman.openHorseInventory(this, this.inventoryChest);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, ItemStack itemstack) {
/* 360 */     boolean flag = c(entityhuman, itemstack);
/*     */     
/* 362 */     if (!entityhuman.abilities.canInstantlyBuild) {
/* 363 */       itemstack.subtract(1);
/*     */     }
/*     */     
/* 366 */     return this.world.isClientSide ? EnumInteractionResult.CONSUME : (flag ? EnumInteractionResult.SUCCESS : EnumInteractionResult.PASS);
/*     */   }
/*     */   
/*     */   protected boolean c(EntityHuman entityhuman, ItemStack itemstack) {
/* 370 */     boolean flag = false;
/* 371 */     float f = 0.0F;
/* 372 */     short short0 = 0;
/* 373 */     byte b0 = 0;
/* 374 */     Item item = itemstack.getItem();
/*     */     
/* 376 */     if (item == Items.WHEAT) {
/* 377 */       f = 2.0F;
/* 378 */       short0 = 20;
/* 379 */       b0 = 3;
/* 380 */     } else if (item == Items.SUGAR) {
/* 381 */       f = 1.0F;
/* 382 */       short0 = 30;
/* 383 */       b0 = 3;
/* 384 */     } else if (item == Blocks.HAY_BLOCK.getItem()) {
/* 385 */       f = 20.0F;
/* 386 */       short0 = 180;
/* 387 */     } else if (item == Items.APPLE) {
/* 388 */       f = 3.0F;
/* 389 */       short0 = 60;
/* 390 */       b0 = 3;
/* 391 */     } else if (item == Items.GOLDEN_CARROT) {
/* 392 */       f = 4.0F;
/* 393 */       short0 = 60;
/* 394 */       b0 = 5;
/* 395 */       if (!this.world.isClientSide && isTamed() && getAge() == 0 && !isInLove()) {
/* 396 */         flag = true;
/* 397 */         g(entityhuman);
/*     */       } 
/* 399 */     } else if (item == Items.GOLDEN_APPLE || item == Items.ENCHANTED_GOLDEN_APPLE) {
/* 400 */       f = 10.0F;
/* 401 */       short0 = 240;
/* 402 */       b0 = 10;
/* 403 */       if (!this.world.isClientSide && isTamed() && getAge() == 0 && !isInLove()) {
/* 404 */         flag = true;
/* 405 */         g(entityhuman);
/*     */       } 
/*     */     } 
/*     */     
/* 409 */     if (getHealth() < getMaxHealth() && f > 0.0F) {
/* 410 */       heal(f, EntityRegainHealthEvent.RegainReason.EATING);
/* 411 */       flag = true;
/*     */     } 
/*     */     
/* 414 */     if (isBaby() && short0 > 0) {
/* 415 */       this.world.addParticle(Particles.HAPPY_VILLAGER, d(1.0D), cE() + 0.5D, g(1.0D), 0.0D, 0.0D, 0.0D);
/* 416 */       if (!this.world.isClientSide) {
/* 417 */         setAge(short0);
/*     */       }
/*     */       
/* 420 */       flag = true;
/*     */     } 
/*     */     
/* 423 */     if (b0 > 0 && (flag || !isTamed()) && getTemper() < getMaxDomestication()) {
/* 424 */       flag = true;
/* 425 */       if (!this.world.isClientSide) {
/* 426 */         v(b0);
/*     */       }
/*     */     } 
/*     */     
/* 430 */     if (flag) {
/* 431 */       eL();
/*     */     }
/*     */     
/* 434 */     return flag;
/*     */   }
/*     */   
/*     */   protected void h(EntityHuman entityhuman) {
/* 438 */     x(false);
/* 439 */     setStanding(false);
/* 440 */     if (!this.world.isClientSide) {
/* 441 */       entityhuman.yaw = this.yaw;
/* 442 */       entityhuman.pitch = this.pitch;
/* 443 */       entityhuman.startRiding(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFrozen() {
/* 450 */     return ((super.isFrozen() && isVehicle() && hasSaddle()) || eZ() || fa());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 455 */     return by.test(itemstack);
/*     */   }
/*     */   
/*     */   private void eM() {
/* 459 */     this.bo = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropInventory() {
/* 464 */     super.dropInventory();
/* 465 */     if (this.inventoryChest != null) {
/* 466 */       for (int i = 0; i < this.inventoryChest.getSize(); i++) {
/* 467 */         ItemStack itemstack = this.inventoryChest.getItem(i);
/*     */         
/* 469 */         if (!itemstack.isEmpty() && !EnchantmentManager.shouldNotDrop(itemstack)) {
/* 470 */           a(itemstack);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 479 */     if (this.random.nextInt(200) == 0) {
/* 480 */       eM();
/*     */     }
/*     */     
/* 483 */     super.movementTick();
/* 484 */     if (!this.world.isClientSide && isAlive()) {
/* 485 */       if (this.random.nextInt(900) == 0 && this.deathTicks == 0) {
/* 486 */         heal(1.0F, EntityRegainHealthEvent.RegainReason.REGEN);
/*     */       }
/*     */       
/* 489 */       if (fl()) {
/* 490 */         if (!eZ() && !isVehicle() && this.random.nextInt(300) == 0 && this.world.getType(getChunkCoordinates().down()).a(Blocks.GRASS_BLOCK)) {
/* 491 */           x(true);
/*     */         }
/*     */         
/* 494 */         if (eZ() && ++this.bB > 50) {
/* 495 */           this.bB = 0;
/* 496 */           x(false);
/*     */         } 
/*     */       } 
/*     */       
/* 500 */       fk();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void fk() {
/* 505 */     if (hasReproduced() && isBaby() && !eZ()) {
/* 506 */       EntityLiving entityliving = this.world.a((Class)EntityHorseAbstract.class, bx, this, locX(), locY(), locZ(), getBoundingBox().g(16.0D));
/*     */       
/* 508 */       if (entityliving != null && h(entityliving) > 4.0D) {
/* 509 */         this.navigation.a(entityliving, 0);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fl() {
/* 516 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 521 */     super.tick();
/* 522 */     if (this.bC > 0 && ++this.bC > 30) {
/* 523 */       this.bC = 0;
/* 524 */       d(64, false);
/*     */     } 
/*     */     
/* 527 */     if ((cr() || doAITick()) && this.bD > 0 && ++this.bD > 20) {
/* 528 */       this.bD = 0;
/* 529 */       setStanding(false);
/*     */     } 
/*     */     
/* 532 */     if (this.bo > 0 && ++this.bo > 8) {
/* 533 */       this.bo = 0;
/*     */     }
/*     */     
/* 536 */     if (this.bp > 0) {
/* 537 */       this.bp++;
/* 538 */       if (this.bp > 300) {
/* 539 */         this.bp = 0;
/*     */       }
/*     */     } 
/*     */     
/* 543 */     this.bG = this.bF;
/* 544 */     if (eZ()) {
/* 545 */       this.bF += (1.0F - this.bF) * 0.4F + 0.05F;
/* 546 */       if (this.bF > 1.0F) {
/* 547 */         this.bF = 1.0F;
/*     */       }
/*     */     } else {
/* 550 */       this.bF += (0.0F - this.bF) * 0.4F - 0.05F;
/* 551 */       if (this.bF < 0.0F) {
/* 552 */         this.bF = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 556 */     this.bI = this.bH;
/* 557 */     if (fa()) {
/* 558 */       this.bF = 0.0F;
/* 559 */       this.bG = this.bF;
/* 560 */       this.bH += (1.0F - this.bH) * 0.4F + 0.05F;
/* 561 */       if (this.bH > 1.0F) {
/* 562 */         this.bH = 1.0F;
/*     */       }
/*     */     } else {
/* 565 */       this.canSlide = false;
/* 566 */       this.bH += (0.8F * this.bH * this.bH * this.bH - this.bH) * 0.6F - 0.05F;
/* 567 */       if (this.bH < 0.0F) {
/* 568 */         this.bH = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 572 */     this.bK = this.bJ;
/* 573 */     if (t(64)) {
/* 574 */       this.bJ += (1.0F - this.bJ) * 0.7F + 0.05F;
/* 575 */       if (this.bJ > 1.0F) {
/* 576 */         this.bJ = 1.0F;
/*     */       }
/*     */     } else {
/* 579 */       this.bJ += (0.0F - this.bJ) * 0.7F - 0.05F;
/* 580 */       if (this.bJ < 0.0F) {
/* 581 */         this.bJ = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void eO() {
/* 588 */     if (!this.world.isClientSide) {
/* 589 */       this.bC = 1;
/* 590 */       d(64, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void x(boolean flag) {
/* 596 */     d(16, flag);
/*     */   }
/*     */   
/*     */   public void setStanding(boolean flag) {
/* 600 */     if (flag) {
/* 601 */       x(false);
/*     */     }
/*     */     
/* 604 */     d(32, flag);
/*     */   }
/*     */   
/*     */   private void eU() {
/* 608 */     if (cr() || doAITick()) {
/* 609 */       this.bD = 1;
/* 610 */       setStanding(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void fm() {
/* 616 */     if (!fa()) {
/* 617 */       eU();
/* 618 */       SoundEffect soundeffect = getSoundAngry();
/*     */       
/* 620 */       if (soundeffect != null) {
/* 621 */         playSound(soundeffect, getSoundVolume(), dG());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean i(EntityHuman entityhuman) {
/* 628 */     setOwnerUUID(entityhuman.getUniqueID());
/* 629 */     setTamed(true);
/* 630 */     if (entityhuman instanceof EntityPlayer) {
/* 631 */       CriterionTriggers.x.a((EntityPlayer)entityhuman, this);
/*     */     }
/*     */     
/* 634 */     this.world.broadcastEntityEffect(this, (byte)7);
/* 635 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(Vec3D vec3d) {
/* 640 */     if (isAlive()) {
/* 641 */       if (isVehicle() && er() && hasSaddle()) {
/* 642 */         EntityLiving entityliving = (EntityLiving)getRidingPassenger();
/*     */         
/* 644 */         this.yaw = entityliving.yaw;
/* 645 */         this.lastYaw = this.yaw;
/* 646 */         this.pitch = entityliving.pitch * 0.5F;
/* 647 */         setYawPitch(this.yaw, this.pitch);
/* 648 */         this.aA = this.yaw;
/* 649 */         this.aC = this.aA;
/* 650 */         float f = entityliving.aR * 0.5F;
/* 651 */         float f1 = entityliving.aT;
/*     */         
/* 653 */         if (f1 <= 0.0F) {
/* 654 */           f1 *= 0.25F;
/* 655 */           this.bv = 0;
/*     */         } 
/*     */         
/* 658 */         if (this.onGround && this.jumpPower == 0.0F && fa() && !this.canSlide) {
/* 659 */           f = 0.0F;
/* 660 */           f1 = 0.0F;
/*     */         } 
/*     */         
/* 663 */         if (this.jumpPower > 0.0F && !eY() && this.onGround) {
/* 664 */           double d1, d0 = getJumpStrength() * this.jumpPower * getBlockJumpFactor();
/*     */ 
/*     */           
/* 667 */           if (hasEffect(MobEffects.JUMP)) {
/* 668 */             d1 = d0 + ((getEffect(MobEffects.JUMP).getAmplifier() + 1) * 0.1F);
/*     */           } else {
/* 670 */             d1 = d0;
/*     */           } 
/*     */           
/* 673 */           Vec3D vec3d1 = getMot();
/*     */           
/* 675 */           setMot(vec3d1.x, d1, vec3d1.z);
/* 676 */           v(true);
/* 677 */           this.impulse = true;
/* 678 */           if (f1 > 0.0F) {
/* 679 */             float f2 = MathHelper.sin(this.yaw * 0.017453292F);
/* 680 */             float f3 = MathHelper.cos(this.yaw * 0.017453292F);
/*     */             
/* 682 */             setMot(getMot().add((-0.4F * f2 * this.jumpPower), 0.0D, (0.4F * f3 * this.jumpPower)));
/*     */           } 
/*     */           
/* 685 */           this.jumpPower = 0.0F;
/*     */         } 
/*     */         
/* 688 */         this.aE = dM() * 0.1F;
/* 689 */         if (cr()) {
/* 690 */           q((float)b(GenericAttributes.MOVEMENT_SPEED));
/* 691 */           super.g(new Vec3D(f, vec3d.y, f1));
/* 692 */         } else if (entityliving instanceof EntityHuman) {
/* 693 */           setMot(Vec3D.ORIGIN);
/*     */         } 
/*     */         
/* 696 */         if (this.onGround) {
/* 697 */           this.jumpPower = 0.0F;
/* 698 */           v(false);
/*     */         } 
/*     */         
/* 701 */         a(this, false);
/*     */       } else {
/* 703 */         this.aE = 0.02F;
/* 704 */         super.g(vec3d);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fn() {
/* 710 */     playSound(SoundEffects.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 715 */     super.saveData(nbttagcompound);
/* 716 */     nbttagcompound.setBoolean("EatingHaystack", eZ());
/* 717 */     nbttagcompound.setBoolean("Bred", hasReproduced());
/* 718 */     nbttagcompound.setInt("Temper", getTemper());
/* 719 */     nbttagcompound.setBoolean("Tame", isTamed());
/* 720 */     if (getOwnerUUID() != null) {
/* 721 */       nbttagcompound.a("Owner", getOwnerUUID());
/*     */     }
/* 723 */     nbttagcompound.setInt("Bukkit.MaxDomestication", this.maxDomestication);
/*     */     
/* 725 */     if (!this.inventoryChest.getItem(0).isEmpty()) {
/* 726 */       nbttagcompound.set("SaddleItem", this.inventoryChest.getItem(0).save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*     */     UUID uuid;
/* 733 */     super.loadData(nbttagcompound);
/* 734 */     x(nbttagcompound.getBoolean("EatingHaystack"));
/* 735 */     w(nbttagcompound.getBoolean("Bred"));
/* 736 */     setTemper(nbttagcompound.getInt("Temper"));
/* 737 */     setTamed(nbttagcompound.getBoolean("Tame"));
/*     */ 
/*     */     
/* 740 */     if (nbttagcompound.b("Owner")) {
/* 741 */       uuid = nbttagcompound.a("Owner");
/*     */     } else {
/* 743 */       String s = nbttagcompound.getString("Owner");
/*     */       
/* 745 */       uuid = NameReferencingFileConverter.a(getMinecraftServer(), s);
/*     */     } 
/*     */     
/* 748 */     if (uuid != null) {
/* 749 */       setOwnerUUID(uuid);
/*     */     }
/*     */     
/* 752 */     if (nbttagcompound.hasKey("Bukkit.MaxDomestication")) {
/* 753 */       this.maxDomestication = nbttagcompound.getInt("Bukkit.MaxDomestication");
/*     */     }
/*     */ 
/*     */     
/* 757 */     if (nbttagcompound.hasKeyOfType("SaddleItem", 10)) {
/* 758 */       ItemStack itemstack = ItemStack.a(nbttagcompound.getCompound("SaddleItem"));
/*     */       
/* 760 */       if (itemstack.getItem() == Items.SADDLE) {
/* 761 */         this.inventoryChest.setItem(0, itemstack);
/*     */       }
/*     */     } 
/*     */     
/* 765 */     fe();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 770 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean fo() {
/* 774 */     return (!isVehicle() && !isPassenger() && isTamed() && !isBaby() && getHealth() >= getMaxHealth() && isInLove());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 780 */     return null;
/*     */   }
/*     */   
/*     */   protected void a(EntityAgeable entityageable, EntityHorseAbstract entityhorseabstract) {
/* 784 */     double d0 = c(GenericAttributes.MAX_HEALTH) + entityageable.c(GenericAttributes.MAX_HEALTH) + fp();
/*     */     
/* 786 */     entityhorseabstract.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(d0 / 3.0D);
/* 787 */     double d1 = c(GenericAttributes.JUMP_STRENGTH) + entityageable.c(GenericAttributes.JUMP_STRENGTH) + fq();
/*     */     
/* 789 */     entityhorseabstract.getAttributeInstance(GenericAttributes.JUMP_STRENGTH).setValue(d1 / 3.0D);
/* 790 */     double d2 = c(GenericAttributes.MOVEMENT_SPEED) + entityageable.c(GenericAttributes.MOVEMENT_SPEED) + fr();
/*     */     
/* 792 */     entityhorseabstract.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(d2 / 3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean er() {
/* 797 */     return getRidingPassenger() instanceof EntityLiving;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean P_() {
/* 802 */     return hasSaddle();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(int i) {
/*     */     float power;
/* 809 */     if (i >= 90) {
/* 810 */       power = 1.0F;
/*     */     } else {
/* 812 */       power = 0.4F + 0.4F * i / 90.0F;
/*     */     } 
/* 814 */     HorseJumpEvent event = CraftEventFactory.callHorseJumpEvent(this, power);
/* 815 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/*     */     
/* 819 */     this.canSlide = true;
/* 820 */     eU();
/* 821 */     fn();
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {}
/*     */ 
/*     */   
/*     */   public void k(Entity entity) {
/* 829 */     super.k(entity);
/* 830 */     if (entity instanceof EntityInsentient) {
/* 831 */       EntityInsentient entityinsentient = (EntityInsentient)entity;
/*     */       
/* 833 */       this.aA = entityinsentient.aA;
/*     */     } 
/*     */     
/* 836 */     if (this.bI > 0.0F) {
/* 837 */       float f = MathHelper.sin(this.aA * 0.017453292F);
/* 838 */       float f1 = MathHelper.cos(this.aA * 0.017453292F);
/* 839 */       float f2 = 0.7F * this.bI;
/* 840 */       float f3 = 0.15F * this.bI;
/*     */       
/* 842 */       entity.setPosition(locX() + (f2 * f), locY() + bb() + entity.ba() + f3, locZ() - (f2 * f1));
/* 843 */       if (entity instanceof EntityLiving) {
/* 844 */         ((EntityLiving)entity).aA = this.aA;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float fp() {
/* 851 */     return 15.0F + this.random.nextInt(8) + this.random.nextInt(9);
/*     */   }
/*     */   
/*     */   protected double fq() {
/* 855 */     return 0.4000000059604645D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D;
/*     */   }
/*     */   
/*     */   protected double fr() {
/* 859 */     return (0.44999998807907104D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClimbing() {
/* 864 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 869 */     return entitysize.height * 0.95F;
/*     */   }
/*     */   
/*     */   public boolean fs() {
/* 873 */     return false;
/*     */   }
/*     */   
/*     */   public boolean ft() {
/* 877 */     return !getEquipment(EnumItemSlot.CHEST).isEmpty();
/*     */   }
/*     */   
/*     */   public boolean l(ItemStack itemstack) {
/* 881 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a_(int i, ItemStack itemstack) {
/* 886 */     int j = i - 400;
/*     */     
/* 888 */     if (j >= 0 && j < 2 && j < this.inventoryChest.getSize()) {
/* 889 */       if (j == 0 && itemstack.getItem() != Items.SADDLE)
/* 890 */         return false; 
/* 891 */       if (j == 1 && (!fs() || !l(itemstack))) {
/* 892 */         return false;
/*     */       }
/* 894 */       this.inventoryChest.setItem(j, itemstack);
/* 895 */       fe();
/* 896 */       return true;
/*     */     } 
/*     */     
/* 899 */     int k = i - 500 + 2;
/*     */     
/* 901 */     if (k >= 2 && k < this.inventoryChest.getSize()) {
/* 902 */       this.inventoryChest.setItem(k, itemstack);
/* 903 */       return true;
/*     */     } 
/* 905 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getRidingPassenger() {
/* 913 */     return getPassengers().isEmpty() ? null : getPassengers().get(0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Vec3D a(Vec3D vec3d, EntityLiving entityliving) {
/* 918 */     double d0 = locX() + vec3d.x;
/* 919 */     double d1 = (getBoundingBox()).minY;
/* 920 */     double d2 = locZ() + vec3d.z;
/* 921 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 922 */     UnmodifiableIterator unmodifiableiterator = entityliving.ei().iterator();
/*     */     
/* 924 */     label20: while (unmodifiableiterator.hasNext()) {
/* 925 */       EntityPose entitypose = (EntityPose)unmodifiableiterator.next();
/*     */       
/* 927 */       blockposition_mutableblockposition.c(d0, d1, d2);
/* 928 */       double d3 = (getBoundingBox()).maxY + 0.75D;
/*     */       
/*     */       while (true) {
/* 931 */         double d4 = this.world.h(blockposition_mutableblockposition);
/*     */         
/* 933 */         if (blockposition_mutableblockposition.getY() + d4 > d3) {
/*     */           continue label20;
/*     */         }
/*     */         
/* 937 */         if (DismountUtil.a(d4)) {
/* 938 */           AxisAlignedBB axisalignedbb = entityliving.f(entitypose);
/* 939 */           Vec3D vec3d1 = new Vec3D(d0, blockposition_mutableblockposition.getY() + d4, d2);
/*     */           
/* 941 */           if (DismountUtil.a(this.world, entityliving, axisalignedbb.c(vec3d1))) {
/* 942 */             entityliving.setPose(entitypose);
/* 943 */             return vec3d1;
/*     */           } 
/*     */         } 
/*     */         
/* 947 */         blockposition_mutableblockposition.c(EnumDirection.UP);
/* 948 */         if (blockposition_mutableblockposition.getY() >= d3) {
/*     */           continue label20;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 954 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3D b(EntityLiving entityliving) {
/* 959 */     Vec3D vec3d = a(getWidth(), entityliving.getWidth(), this.yaw + ((entityliving.getMainHand() == EnumMainHand.RIGHT) ? 90.0F : -90.0F));
/* 960 */     Vec3D vec3d1 = a(vec3d, entityliving);
/*     */     
/* 962 */     if (vec3d1 != null) {
/* 963 */       return vec3d1;
/*     */     }
/* 965 */     Vec3D vec3d2 = a(getWidth(), entityliving.getWidth(), this.yaw + ((entityliving.getMainHand() == EnumMainHand.LEFT) ? 90.0F : -90.0F));
/* 966 */     Vec3D vec3d3 = a(vec3d2, entityliving);
/*     */     
/* 968 */     return (vec3d3 != null) ? vec3d3 : getPositionVector();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eK() {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 977 */     if (groupdataentity == null) {
/* 978 */       groupdataentity = new EntityAgeable.a(0.2F);
/*     */     }
/*     */     
/* 981 */     eK();
/* 982 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHorseAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */