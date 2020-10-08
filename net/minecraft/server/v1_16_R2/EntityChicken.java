/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class EntityChicken
/*     */   extends EntityAnimal {
/*   5 */   private static final RecipeItemStack bv = RecipeItemStack.a(new IMaterial[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS });
/*     */   public float bo;
/*     */   public float bp;
/*     */   public float bq;
/*     */   public float br;
/*  10 */   public float bs = 1.0F;
/*     */   public int eggLayTime;
/*     */   public boolean chickenJockey;
/*     */   
/*     */   public EntityChicken(EntityTypes<? extends EntityChicken> entitytypes, World world) {
/*  15 */     super((EntityTypes)entitytypes, world);
/*  16 */     this.eggLayTime = this.random.nextInt(6000) + 6000;
/*  17 */     a(PathType.WATER, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  22 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  23 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.4D));
/*  24 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
/*  25 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.0D, false, bv));
/*  26 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.1D));
/*  27 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  28 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  29 */     this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  34 */     return isBaby() ? (entitysize.height * 0.85F) : (entitysize.height * 0.92F);
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  38 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 4.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  44 */     if (isChickenJockey()) {
/*  45 */       this.persistent = !isTypeNotPersistent(0.0D);
/*     */     }
/*     */     
/*  48 */     super.movementTick();
/*  49 */     this.br = this.bo;
/*  50 */     this.bq = this.bp;
/*  51 */     this.bp = (float)(this.bp + (this.onGround ? -1 : 4) * 0.3D);
/*  52 */     this.bp = MathHelper.a(this.bp, 0.0F, 1.0F);
/*  53 */     if (!this.onGround && this.bs < 1.0F) {
/*  54 */       this.bs = 1.0F;
/*     */     }
/*     */     
/*  57 */     this.bs = (float)(this.bs * 0.9D);
/*  58 */     Vec3D vec3d = getMot();
/*     */     
/*  60 */     if (!this.onGround && vec3d.y < 0.0D) {
/*  61 */       setMot(vec3d.d(1.0D, 0.6D, 1.0D));
/*     */     }
/*     */     
/*  64 */     this.bo += this.bs * 2.0F;
/*  65 */     if (!this.world.isClientSide && isAlive() && !isBaby() && !isChickenJockey() && --this.eggLayTime <= 0) {
/*  66 */       playSound(SoundEffects.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*  67 */       this.forceDrops = true;
/*  68 */       a(Items.EGG);
/*  69 */       this.forceDrops = false;
/*  70 */       this.eggLayTime = this.random.nextInt(6000) + 6000;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  82 */     return SoundEffects.ENTITY_CHICKEN_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  87 */     return SoundEffects.ENTITY_CHICKEN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  92 */     return SoundEffects.ENTITY_CHICKEN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/*  97 */     playSound(SoundEffects.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityChicken createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 102 */     return EntityTypes.CHICKEN.a(worldserver);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 107 */     return bv.test(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getExpValue(EntityHuman entityhuman) {
/* 112 */     return isChickenJockey() ? 10 : super.getExpValue(entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 117 */     super.loadData(nbttagcompound);
/* 118 */     this.chickenJockey = nbttagcompound.getBoolean("IsChickenJockey");
/* 119 */     if (nbttagcompound.hasKey("EggLayTime")) {
/* 120 */       this.eggLayTime = nbttagcompound.getInt("EggLayTime");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 127 */     super.saveData(nbttagcompound);
/* 128 */     nbttagcompound.setBoolean("IsChickenJockey", this.chickenJockey);
/* 129 */     nbttagcompound.setInt("EggLayTime", this.eggLayTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/* 134 */     return isChickenJockey();
/*     */   }
/*     */ 
/*     */   
/*     */   public void k(Entity entity) {
/* 139 */     super.k(entity);
/* 140 */     float f = MathHelper.sin(this.aA * 0.017453292F);
/* 141 */     float f1 = MathHelper.cos(this.aA * 0.017453292F);
/* 142 */     float f2 = 0.1F;
/* 143 */     float f3 = 0.0F;
/*     */     
/* 145 */     entity.setPosition(locX() + (0.1F * f), e(0.5D) + entity.ba() + 0.0D, locZ() - (0.1F * f1));
/* 146 */     if (entity instanceof EntityLiving) {
/* 147 */       ((EntityLiving)entity).aA = this.aA;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChickenJockey() {
/* 153 */     return this.chickenJockey;
/*     */   }
/*     */   
/*     */   public void setChickenJockey(boolean flag) {
/* 157 */     this.chickenJockey = flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */