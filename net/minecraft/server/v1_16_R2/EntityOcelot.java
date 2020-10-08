/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityOcelot
/*     */   extends EntityAnimal {
/*   9 */   private static final RecipeItemStack bo = RecipeItemStack.a(new IMaterial[] { Items.COD, Items.SALMON });
/*  10 */   private static final DataWatcherObject<Boolean> bp = DataWatcher.a((Class)EntityOcelot.class, DataWatcherRegistry.i);
/*     */   private a<EntityHuman> bq;
/*     */   private b br;
/*     */   
/*     */   public EntityOcelot(EntityTypes<? extends EntityOcelot> entitytypes, World world) {
/*  15 */     super((EntityTypes)entitytypes, world);
/*  16 */     eL();
/*     */   }
/*     */   
/*     */   private boolean isTrusting() {
/*  20 */     return ((Boolean)this.datawatcher.<Boolean>get(bp)).booleanValue();
/*     */   }
/*     */   
/*     */   private void setTrusting(boolean flag) {
/*  24 */     this.datawatcher.set(bp, Boolean.valueOf(flag));
/*  25 */     eL();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  30 */     super.saveData(nbttagcompound);
/*  31 */     nbttagcompound.setBoolean("Trusting", isTrusting());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  36 */     super.loadData(nbttagcompound);
/*  37 */     setTrusting(nbttagcompound.getBoolean("Trusting"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  42 */     super.initDatawatcher();
/*  43 */     this.datawatcher.register(bp, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  48 */     this.br = new b(this, 0.6D, bo, true);
/*  49 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  50 */     this.goalSelector.a(3, this.br);
/*  51 */     this.goalSelector.a(7, new PathfinderGoalLeapAtTarget(this, 0.3F));
/*  52 */     this.goalSelector.a(8, new PathfinderGoalOcelotAttack(this));
/*  53 */     this.goalSelector.a(9, new PathfinderGoalBreed(this, 0.8D));
/*  54 */     this.goalSelector.a(10, new PathfinderGoalRandomStrollLand(this, 0.8D, 1.0000001E-5F));
/*  55 */     this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 10.0F));
/*  56 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityChicken.class, false));
/*  57 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityTurtle.class, 10, false, false, EntityTurtle.bo));
/*     */   }
/*     */ 
/*     */   
/*     */   public void mobTick() {
/*  62 */     if (getControllerMove().b()) {
/*  63 */       double d0 = getControllerMove().c();
/*     */       
/*  65 */       if (d0 == 0.6D) {
/*  66 */         setPose(EntityPose.CROUCHING);
/*  67 */         setSprinting(false);
/*  68 */       } else if (d0 == 1.33D) {
/*  69 */         setPose(EntityPose.STANDING);
/*  70 */         setSprinting(true);
/*     */       } else {
/*  72 */         setPose(EntityPose.STANDING);
/*  73 */         setSprinting(false);
/*     */       } 
/*     */     } else {
/*  76 */       setPose(EntityPose.STANDING);
/*  77 */       setSprinting(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/*  84 */     return (!isTrusting() && !hasCustomName() && !isLeashed());
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  88 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 10.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D).a(GenericAttributes.ATTACK_DAMAGE, 3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAmbient() {
/*  99 */     return SoundEffects.ENTITY_OCELOT_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public int D() {
/* 104 */     return 900;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 109 */     return SoundEffects.ENTITY_OCELOT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 114 */     return SoundEffects.ENTITY_OCELOT_DEATH;
/*     */   }
/*     */   
/*     */   private float eN() {
/* 118 */     return (float)b(GenericAttributes.ATTACK_DAMAGE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 123 */     return entity.damageEntity(DamageSource.mobAttack(this), eN());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 128 */     return isInvulnerable(damagesource) ? false : super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 133 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 135 */     if ((this.br == null || this.br.h()) && !isTrusting() && k(itemstack) && entityhuman.h(this) < 9.0D) {
/* 136 */       a(entityhuman, itemstack);
/* 137 */       if (!this.world.isClientSide)
/*     */       {
/* 139 */         if (this.random.nextInt(3) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
/* 140 */           setTrusting(true);
/* 141 */           u(true);
/* 142 */           this.world.broadcastEntityEffect(this, (byte)41);
/*     */         } else {
/* 144 */           u(false);
/* 145 */           this.world.broadcastEntityEffect(this, (byte)40);
/*     */         } 
/*     */       }
/*     */       
/* 149 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/* 151 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */   
/*     */   private void u(boolean flag) {
/* 156 */     ParticleType particletype = Particles.HEART;
/*     */     
/* 158 */     if (!flag) {
/* 159 */       particletype = Particles.SMOKE;
/*     */     }
/*     */     
/* 162 */     for (int i = 0; i < 7; i++) {
/* 163 */       double d0 = this.random.nextGaussian() * 0.02D;
/* 164 */       double d1 = this.random.nextGaussian() * 0.02D;
/* 165 */       double d2 = this.random.nextGaussian() * 0.02D;
/*     */       
/* 167 */       this.world.addParticle(particletype, d(1.0D), cE() + 0.5D, g(1.0D), d0, d1, d2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eL() {
/* 173 */     if (this.bq == null) {
/* 174 */       this.bq = new a<>(this, EntityHuman.class, 16.0F, 0.8D, 1.33D);
/*     */     }
/*     */     
/* 177 */     this.goalSelector.a(this.bq);
/* 178 */     if (!isTrusting()) {
/* 179 */       this.goalSelector.a(4, this.bq);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityOcelot createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 186 */     return EntityTypes.OCELOT.a(worldserver);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 191 */     return bo.test(itemstack);
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<EntityOcelot> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 195 */     return (random.nextInt(3) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader iworldreader) {
/* 200 */     if (iworldreader.j(this) && !iworldreader.containsLiquid(getBoundingBox())) {
/* 201 */       BlockPosition blockposition = getChunkCoordinates();
/*     */       
/* 203 */       if (blockposition.getY() < iworldreader.getSeaLevel()) {
/* 204 */         return false;
/*     */       }
/*     */       
/* 207 */       IBlockData iblockdata = iworldreader.getType(blockposition.down());
/*     */       
/* 209 */       if (iblockdata.a(Blocks.GRASS_BLOCK) || iblockdata.a(TagsBlock.LEAVES)) {
/* 210 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 214 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 220 */     if (groupdataentity == null) {
/* 221 */       groupdataentity = new EntityAgeable.a(1.0F);
/*     */     }
/*     */     
/* 224 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends PathfinderGoalTempt {
/*     */     private final EntityOcelot c;
/*     */     
/*     */     public b(EntityOcelot entityocelot, double d0, RecipeItemStack recipeitemstack, boolean flag) {
/* 232 */       super(entityocelot, d0, recipeitemstack, flag);
/* 233 */       this.c = entityocelot;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean g() {
/* 238 */       return (super.g() && !this.c.isTrusting());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class a<T extends EntityLiving>
/*     */     extends PathfinderGoalAvoidTarget<T>
/*     */   {
/*     */     private final EntityOcelot i;
/*     */     
/*     */     public a(EntityOcelot entityocelot, Class<T> oclass, float f, double d0, double d1) {
/* 249 */       super(entityocelot, oclass, f, d0, d1, IEntitySelector.e::test);
/* 250 */       this.i = entityocelot;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 255 */       return (!this.i.isTrusting() && super.a());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 260 */       return (!this.i.isTrusting() && super.b());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityOcelot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */