/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityLlama
/*     */   extends EntityHorseChestedAbstract implements IRangedEntity {
/*   8 */   private static final RecipeItemStack bw = RecipeItemStack.a(new IMaterial[] { Items.WHEAT, Blocks.HAY_BLOCK.getItem() });
/*   9 */   private static final DataWatcherObject<Integer> bx = DataWatcher.a((Class)EntityLlama.class, DataWatcherRegistry.b);
/*  10 */   private static final DataWatcherObject<Integer> by = DataWatcher.a((Class)EntityLlama.class, DataWatcherRegistry.b);
/*  11 */   private static final DataWatcherObject<Integer> bz = DataWatcher.a((Class)EntityLlama.class, DataWatcherRegistry.b);
/*     */   private boolean bA;
/*     */   @Nullable
/*     */   private EntityLlama bB;
/*     */   @Nullable
/*     */   private EntityLlama bC;
/*     */   
/*     */   public EntityLlama(EntityTypes<? extends EntityLlama> entitytypes, World world) {
/*  19 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public void setStrength(int i) {
/*  23 */     this.datawatcher.set(bx, Integer.valueOf(Math.max(1, Math.min(5, i))));
/*     */   }
/*     */   
/*     */   private void fE() {
/*  27 */     int i = (this.random.nextFloat() < 0.04F) ? 5 : 3;
/*     */     
/*  29 */     setStrength(1 + this.random.nextInt(i));
/*     */   }
/*     */   
/*     */   public int getStrength() {
/*  33 */     return ((Integer)this.datawatcher.<Integer>get(bx)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  38 */     super.saveData(nbttagcompound);
/*  39 */     nbttagcompound.setInt("Variant", getVariant());
/*  40 */     nbttagcompound.setInt("Strength", getStrength());
/*  41 */     if (!this.inventoryChest.getItem(1).isEmpty()) {
/*  42 */       nbttagcompound.set("DecorItem", this.inventoryChest.getItem(1).save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  49 */     setStrength(nbttagcompound.getInt("Strength"));
/*  50 */     super.loadData(nbttagcompound);
/*  51 */     setVariant(nbttagcompound.getInt("Variant"));
/*  52 */     if (nbttagcompound.hasKeyOfType("DecorItem", 10)) {
/*  53 */       this.inventoryChest.setItem(1, ItemStack.a(nbttagcompound.getCompound("DecorItem")));
/*     */     }
/*     */     
/*  56 */     fe();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  61 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  62 */     this.goalSelector.a(1, new PathfinderGoalTame(this, 1.2D));
/*  63 */     this.goalSelector.a(2, new PathfinderGoalLlamaFollow(this, 2.0999999046325684D));
/*  64 */     this.goalSelector.a(3, new PathfinderGoalArrowAttack(this, 1.25D, 40, 20.0F));
/*  65 */     this.goalSelector.a(3, new PathfinderGoalPanic(this, 1.2D));
/*  66 */     this.goalSelector.a(4, new PathfinderGoalBreed(this, 1.0D));
/*  67 */     this.goalSelector.a(5, new PathfinderGoalFollowParent(this, 1.0D));
/*  68 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 0.7D));
/*  69 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  70 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  71 */     this.targetSelector.a(1, new c(this));
/*  72 */     this.targetSelector.a(2, new a(this));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder fw() {
/*  76 */     return eL().a(GenericAttributes.FOLLOW_RANGE, 40.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  81 */     super.initDatawatcher();
/*  82 */     this.datawatcher.register(bx, Integer.valueOf(0));
/*  83 */     this.datawatcher.register(by, Integer.valueOf(-1));
/*  84 */     this.datawatcher.register(bz, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public int getVariant() {
/*  88 */     return MathHelper.clamp(((Integer)this.datawatcher.<Integer>get(bz)).intValue(), 0, 3);
/*     */   }
/*     */   
/*     */   public void setVariant(int i) {
/*  92 */     this.datawatcher.set(bz, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getChestSlots() {
/*  97 */     return isCarryingChest() ? (2 + 3 * eU()) : super.getChestSlots();
/*     */   }
/*     */ 
/*     */   
/*     */   public void k(Entity entity) {
/* 102 */     if (w(entity)) {
/* 103 */       float f = MathHelper.cos(this.aA * 0.017453292F);
/* 104 */       float f1 = MathHelper.sin(this.aA * 0.017453292F);
/* 105 */       float f2 = 0.3F;
/*     */       
/* 107 */       entity.setPosition(locX() + (0.3F * f1), locY() + bb() + entity.ba(), locZ() - (0.3F * f));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/* 113 */     return getHeight() * 0.67D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean er() {
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 123 */     return bw.test(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean c(EntityHuman entityhuman, ItemStack itemstack) {
/* 128 */     byte b0 = 0;
/* 129 */     byte b1 = 0;
/* 130 */     float f = 0.0F;
/* 131 */     boolean flag = false;
/* 132 */     Item item = itemstack.getItem();
/*     */     
/* 134 */     if (item == Items.WHEAT) {
/* 135 */       b0 = 10;
/* 136 */       b1 = 3;
/* 137 */       f = 2.0F;
/* 138 */     } else if (item == Blocks.HAY_BLOCK.getItem()) {
/* 139 */       b0 = 90;
/* 140 */       b1 = 6;
/* 141 */       f = 10.0F;
/* 142 */       if (isTamed() && getAge() == 0 && eP()) {
/* 143 */         flag = true;
/* 144 */         g(entityhuman);
/*     */       } 
/*     */     } 
/*     */     
/* 148 */     if (getHealth() < getMaxHealth() && f > 0.0F) {
/* 149 */       heal(f);
/* 150 */       flag = true;
/*     */     } 
/*     */     
/* 153 */     if (isBaby() && b0 > 0) {
/* 154 */       this.world.addParticle(Particles.HAPPY_VILLAGER, d(1.0D), cE() + 0.5D, g(1.0D), 0.0D, 0.0D, 0.0D);
/* 155 */       if (!this.world.isClientSide) {
/* 156 */         setAge(b0);
/*     */       }
/*     */       
/* 159 */       flag = true;
/*     */     } 
/*     */     
/* 162 */     if (b1 > 0 && (flag || !isTamed()) && getTemper() < getMaxDomestication()) {
/* 163 */       flag = true;
/* 164 */       if (!this.world.isClientSide) {
/* 165 */         v(b1);
/*     */       }
/*     */     } 
/*     */     
/* 169 */     if (flag && !isSilent()) {
/* 170 */       SoundEffect soundeffect = fg();
/*     */       
/* 172 */       if (soundeffect != null) {
/* 173 */         this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), fg(), getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */       }
/*     */     } 
/*     */     
/* 177 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isFrozen() {
/* 182 */     return (dk() || eZ());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*     */     int i;
/* 188 */     fE();
/*     */ 
/*     */     
/* 191 */     if (groupdataentity instanceof b) {
/* 192 */       i = ((b)groupdataentity).a;
/*     */     } else {
/* 194 */       i = this.random.nextInt(4);
/* 195 */       groupdataentity = new b(i);
/*     */     } 
/*     */     
/* 198 */     setVariant(i);
/* 199 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAngry() {
/* 204 */     return SoundEffects.ENTITY_LLAMA_ANGRY;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 209 */     return SoundEffects.ENTITY_LLAMA_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 214 */     return SoundEffects.ENTITY_LLAMA_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 219 */     return SoundEffects.ENTITY_LLAMA_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect fg() {
/* 225 */     return SoundEffects.ENTITY_LLAMA_EAT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 230 */     playSound(SoundEffects.ENTITY_LLAMA_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eO() {
/* 235 */     playSound(SoundEffects.ENTITY_LLAMA_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fm() {
/* 240 */     SoundEffect soundeffect = getSoundAngry();
/*     */     
/* 242 */     if (soundeffect != null) {
/* 243 */       playSound(soundeffect, getSoundVolume(), dG());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int eU() {
/* 250 */     return getStrength();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fs() {
/* 255 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ft() {
/* 260 */     return !this.inventoryChest.getItem(1).isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean l(ItemStack itemstack) {
/* 265 */     Item item = itemstack.getItem();
/*     */     
/* 267 */     return TagsItem.CARPETS.isTagged(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSaddle() {
/* 272 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 277 */     EnumColor enumcolor = fy();
/*     */     
/* 279 */     super.a(iinventory);
/* 280 */     EnumColor enumcolor1 = fy();
/*     */     
/* 282 */     if (this.ticksLived > 20 && enumcolor1 != null && enumcolor1 != enumcolor) {
/* 283 */       playSound(SoundEffects.ENTITY_LLAMA_SWAG, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fe() {
/* 290 */     if (!this.world.isClientSide) {
/* 291 */       super.fe();
/* 292 */       a(m(this.inventoryChest.getItem(1)));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(@Nullable EnumColor enumcolor) {
/* 297 */     this.datawatcher.set(by, Integer.valueOf((enumcolor == null) ? -1 : enumcolor.getColorIndex()));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static EnumColor m(ItemStack itemstack) {
/* 302 */     Block block = Block.asBlock(itemstack.getItem());
/*     */     
/* 304 */     return (block instanceof BlockCarpet) ? ((BlockCarpet)block).c() : null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EnumColor fy() {
/* 309 */     int i = ((Integer)this.datawatcher.<Integer>get(by)).intValue();
/*     */     
/* 311 */     return (i == -1) ? null : EnumColor.fromColorIndex(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxDomestication() {
/* 316 */     return 30;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 321 */     return (entityanimal != this && entityanimal instanceof EntityLlama && fo() && ((EntityLlama)entityanimal).fo());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLlama createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 326 */     EntityLlama entityllama = fz();
/*     */     
/* 328 */     a(entityageable, entityllama);
/* 329 */     EntityLlama entityllama1 = (EntityLlama)entityageable;
/* 330 */     int i = this.random.nextInt(Math.max(getStrength(), entityllama1.getStrength())) + 1;
/*     */     
/* 332 */     if (this.random.nextFloat() < 0.03F) {
/* 333 */       i++;
/*     */     }
/*     */     
/* 336 */     entityllama.setStrength(i);
/* 337 */     entityllama.setVariant(this.random.nextBoolean() ? getVariant() : entityllama1.getVariant());
/* 338 */     return entityllama;
/*     */   }
/*     */   
/*     */   protected EntityLlama fz() {
/* 342 */     return EntityTypes.LLAMA.a(this.world);
/*     */   }
/*     */   
/*     */   private void i(EntityLiving entityliving) {
/* 346 */     EntityLlamaSpit entityllamaspit = new EntityLlamaSpit(this.world, this);
/* 347 */     double d0 = entityliving.locX() - locX();
/* 348 */     double d1 = entityliving.e(0.3333333333333333D) - entityllamaspit.locY();
/* 349 */     double d2 = entityliving.locZ() - locZ();
/* 350 */     float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
/*     */     
/* 352 */     entityllamaspit.shoot(d0, d1 + f, d2, 1.5F, 10.0F);
/* 353 */     if (!isSilent()) {
/* 354 */       this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_LLAMA_SPIT, getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */     }
/*     */     
/* 357 */     this.world.addEntity(entityllamaspit);
/* 358 */     this.bA = true;
/*     */   }
/*     */   
/*     */   private void A(boolean flag) {
/* 362 */     this.bA = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/* 367 */     int i = e(f, f1);
/*     */     
/* 369 */     if (i <= 0) {
/* 370 */       return false;
/*     */     }
/* 372 */     if (f >= 6.0F) {
/* 373 */       damageEntity(DamageSource.FALL, i);
/* 374 */       if (isVehicle()) {
/* 375 */         Iterator<Entity> iterator = getAllPassengers().iterator();
/*     */         
/* 377 */         while (iterator.hasNext()) {
/* 378 */           Entity entity = iterator.next();
/*     */           
/* 380 */           entity.damageEntity(DamageSource.FALL, i);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 385 */     playBlockStepSound();
/* 386 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fA() {
/* 391 */     if (this.bB != null) {
/* 392 */       this.bB.bC = null;
/*     */     }
/*     */     
/* 395 */     this.bB = null;
/*     */   }
/*     */   
/*     */   public void a(EntityLlama entityllama) {
/* 399 */     this.bB = entityllama;
/* 400 */     this.bB.bC = this;
/*     */   }
/*     */   
/*     */   public boolean fB() {
/* 404 */     return (this.bC != null);
/*     */   }
/*     */   public final boolean inCaravan() {
/* 407 */     return fC();
/*     */   } public boolean fC() {
/* 409 */     return (this.bB != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLlama fD() {
/* 414 */     return this.bB;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double eJ() {
/* 419 */     return 2.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fk() {
/* 424 */     if (!fC() && isBaby()) {
/* 425 */       super.fk();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean fl() {
/* 432 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 437 */     i(entityliving);
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityWolf> {
/*     */     public a(EntityLlama entityllama) {
/* 443 */       super(entityllama, EntityWolf.class, 16, false, true, entityliving -> !((EntityWolf)entityliving).isTamed());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected double k() {
/* 450 */       return super.k() * 0.25D;
/*     */     }
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends PathfinderGoalHurtByTarget {
/*     */     public c(EntityLlama entityllama) {
/* 457 */       super(entityllama, new Class[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 462 */       if (this.e instanceof EntityLlama) {
/* 463 */         EntityLlama entityllama = (EntityLlama)this.e;
/*     */         
/* 465 */         if (entityllama.bA) {
/* 466 */           entityllama.A(false);
/* 467 */           return false;
/*     */         } 
/*     */       } 
/*     */       
/* 471 */       return super.b();
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends EntityAgeable.a {
/*     */     public final int a;
/*     */     
/*     */     private b(int i) {
/* 480 */       super(true);
/* 481 */       this.a = i;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityLlama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */