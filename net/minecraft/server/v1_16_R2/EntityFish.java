/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ 
/*     */ public abstract class EntityFish extends EntityWaterAnimal {
/*   8 */   private static final DataWatcherObject<Boolean> FROM_BUCKET = DataWatcher.a((Class)EntityFish.class, DataWatcherRegistry.i);
/*     */   
/*     */   public EntityFish(EntityTypes<? extends EntityFish> entitytypes, World world) {
/*  11 */     super((EntityTypes)entitytypes, world);
/*  12 */     this.moveController = new a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  17 */     return entitysize.height * 0.65F;
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  21 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSpecialPersistence() {
/*  26 */     return (super.isSpecialPersistence() || isFromBucket());
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<? extends EntityFish> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  30 */     return (generatoraccess.getType(blockposition).a(Blocks.WATER) && generatoraccess.getType(blockposition.up()).a(Blocks.WATER));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/*  35 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnGroup() {
/*  40 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  45 */     super.initDatawatcher();
/*  46 */     this.datawatcher.register(FROM_BUCKET, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean isFromBucket() {
/*  50 */     return ((Boolean)this.datawatcher.<Boolean>get(FROM_BUCKET)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setFromBucket(boolean flag) {
/*  54 */     this.datawatcher.set(FROM_BUCKET, Boolean.valueOf(flag));
/*  55 */     this.persistent = isPersistent();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  60 */     super.saveData(nbttagcompound);
/*  61 */     nbttagcompound.setBoolean("FromBucket", isFromBucket());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  66 */     super.loadData(nbttagcompound);
/*  67 */     setFromBucket(nbttagcompound.getBoolean("FromBucket"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  72 */     super.initPathfinder();
/*  73 */     this.goalSelector.a(0, new PathfinderGoalPanic(this, 1.25D));
/*  74 */     PathfinderGoalSelector pathfindergoalselector = this.goalSelector;
/*  75 */     Predicate<Entity> predicate = IEntitySelector.g;
/*     */     
/*  77 */     predicate.getClass();
/*  78 */     Objects.requireNonNull(predicate); pathfindergoalselector.a(2, new PathfinderGoalAvoidTarget<>(this, EntityHuman.class, 8.0F, 1.6D, 1.4D, predicate::test));
/*  79 */     this.goalSelector.a(4, new b(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World world) {
/*  84 */     return new NavigationGuardian(this, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(Vec3D vec3d) {
/*  89 */     if (doAITick() && isInWater()) {
/*  90 */       a(0.01F, vec3d);
/*  91 */       move(EnumMoveType.SELF, getMot());
/*  92 */       setMot(getMot().a(0.9D));
/*  93 */       if (getGoalTarget() == null) {
/*  94 */         setMot(getMot().add(0.0D, -0.005D, 0.0D));
/*     */       }
/*     */     } else {
/*  97 */       super.g(vec3d);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 104 */     if (!isInWater() && this.onGround && this.v) {
/* 105 */       setMot(getMot().add(((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), 0.4000000059604645D, ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
/* 106 */       this.onGround = false;
/* 107 */       this.impulse = true;
/* 108 */       playSound(getSoundFlop(), getSoundVolume(), dG());
/*     */     } 
/*     */     
/* 111 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 116 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 118 */     if (itemstack.getItem() == Items.WATER_BUCKET && isAlive()) {
/* 119 */       playSound(SoundEffects.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);
/* 120 */       itemstack.subtract(1);
/* 121 */       ItemStack itemstack1 = eK();
/*     */       
/* 123 */       k(itemstack1);
/* 124 */       if (!this.world.isClientSide) {
/* 125 */         CriterionTriggers.j.a((EntityPlayer)entityhuman, itemstack1);
/*     */       }
/*     */       
/* 128 */       if (itemstack.isEmpty()) {
/* 129 */         entityhuman.a(enumhand, itemstack1);
/* 130 */       } else if (!entityhuman.inventory.pickup(itemstack1)) {
/* 131 */         entityhuman.drop(itemstack1, false);
/*     */       } 
/*     */       
/* 134 */       die();
/* 135 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/* 137 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void k(ItemStack itemstack) {
/* 142 */     if (hasCustomName()) {
/* 143 */       itemstack.a(getCustomName());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract ItemStack eK();
/*     */   
/*     */   protected boolean eL() {
/* 151 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract SoundEffect getSoundFlop();
/*     */   
/*     */   protected SoundEffect getSoundSwim() {
/* 158 */     return SoundEffects.ENTITY_FISH_SWIM;
/*     */   }
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {}
/*     */   
/*     */   static class a
/*     */     extends ControllerMove
/*     */   {
/*     */     private final EntityFish i;
/*     */     
/*     */     a(EntityFish entityfish) {
/* 169 */       super(entityfish);
/* 170 */       this.i = entityfish;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 175 */       if (this.i.a(TagsFluid.WATER)) {
/* 176 */         this.i.setMot(this.i.getMot().add(0.0D, 0.005D, 0.0D));
/*     */       }
/*     */       
/* 179 */       if (this.h == ControllerMove.Operation.MOVE_TO && !this.i.getNavigation().m()) {
/* 180 */         float f = (float)(this.e * this.i.b(GenericAttributes.MOVEMENT_SPEED));
/*     */         
/* 182 */         this.i.q(MathHelper.g(0.125F, this.i.dM(), f));
/* 183 */         double d0 = this.b - this.i.locX();
/* 184 */         double d1 = this.c - this.i.locY();
/* 185 */         double d2 = this.d - this.i.locZ();
/*     */         
/* 187 */         if (d1 != 0.0D) {
/* 188 */           double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */           
/* 190 */           this.i.setMot(this.i.getMot().add(0.0D, this.i.dM() * d1 / d3 * 0.1D, 0.0D));
/*     */         } 
/*     */         
/* 193 */         if (d0 != 0.0D || d2 != 0.0D) {
/* 194 */           float f1 = (float)(MathHelper.d(d2, d0) * 57.2957763671875D) - 90.0F;
/*     */           
/* 196 */           this.i.yaw = a(this.i.yaw, f1, 90.0F);
/* 197 */           this.i.aA = this.i.yaw;
/*     */         } 
/*     */       } else {
/*     */         
/* 201 */         this.i.q(0.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends PathfinderGoalRandomSwim {
/*     */     private final EntityFish h;
/*     */     
/*     */     public b(EntityFish entityfish) {
/* 211 */       super(entityfish, 1.0D, 40);
/* 212 */       this.h = entityfish;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 217 */       return (this.h.eL() && super.a());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */