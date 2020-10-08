/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
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
/*     */ 
/*     */ 
/*     */ public class EntityPolarBear
/*     */   extends EntityAnimal
/*     */   implements IEntityAngerable
/*     */ {
/*  58 */   private static final DataWatcherObject<Boolean> bo = DataWatcher.a((Class)EntityPolarBear.class, DataWatcherRegistry.i);
/*     */   
/*     */   private float bp;
/*     */   
/*     */   private float bq;
/*     */   
/*     */   private int br;
/*  65 */   private static final IntRange bs = TimeRange.a(20, 39);
/*     */   private int bt;
/*     */   private UUID bu;
/*     */   
/*     */   public EntityPolarBear(EntityTypes<? extends EntityPolarBear> var0, World var1) {
/*  70 */     super((EntityTypes)var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(WorldServer var0, EntityAgeable var1) {
/*  75 */     return EntityTypes.POLAR_BEAR.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack var0) {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  85 */     super.initPathfinder();
/*     */     
/*  87 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  88 */     this.goalSelector.a(1, new c(this));
/*  89 */     this.goalSelector.a(1, new d(this));
/*  90 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.25D));
/*  91 */     this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 1.0D));
/*  92 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  93 */     this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*     */     
/*  95 */     this.targetSelector.a(1, new b(this));
/*  96 */     this.targetSelector.a(2, new a(this));
/*  97 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, 10, true, false, this::a_));
/*  98 */     this.targetSelector.a(4, new PathfinderGoalNearestAttackableTarget<>(this, EntityFox.class, 10, true, true, null));
/*  99 */     this.targetSelector.a(5, new PathfinderGoalUniversalAngerReset<>(this, false));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/* 103 */     return EntityInsentient.p()
/* 104 */       .a(GenericAttributes.MAX_HEALTH, 30.0D)
/* 105 */       .a(GenericAttributes.FOLLOW_RANGE, 20.0D)
/* 106 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.25D)
/* 107 */       .a(GenericAttributes.ATTACK_DAMAGE, 6.0D);
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<EntityPolarBear> var0, GeneratorAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/* 111 */     Optional<ResourceKey<BiomeBase>> var5 = var1.i(var3);
/*     */     
/* 113 */     if (Objects.equals(var5, Optional.of(Biomes.FROZEN_OCEAN)) || Objects.equals(var5, Optional.of(Biomes.DEEP_FROZEN_OCEAN))) {
/* 114 */       return (var1.getLightLevel(var3, 0) > 8 && var1.getType(var3.down()).a(Blocks.ICE));
/*     */     }
/*     */     
/* 117 */     return b((EntityTypes)var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/* 122 */     super.loadData(var0);
/* 123 */     a((WorldServer)this.world, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/* 128 */     super.saveData(var0);
/* 129 */     c(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void anger() {
/* 134 */     setAnger(bs.a(this.random));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnger(int var0) {
/* 139 */     this.bt = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnger() {
/* 144 */     return this.bt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAngerTarget(@Nullable UUID var0) {
/* 149 */     this.bu = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getAngerTarget() {
/* 154 */     return this.bu;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 159 */     if (isBaby()) {
/* 160 */       return SoundEffects.ENTITY_POLAR_BEAR_AMBIENT_BABY;
/*     */     }
/* 162 */     return SoundEffects.ENTITY_POLAR_BEAR_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 167 */     return SoundEffects.ENTITY_POLAR_BEAR_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 172 */     return SoundEffects.ENTITY_POLAR_BEAR_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition var0, IBlockData var1) {
/* 177 */     playSound(SoundEffects.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void eL() {
/* 181 */     if (this.br <= 0) {
/* 182 */       playSound(SoundEffects.ENTITY_POLAR_BEAR_WARNING, 1.0F, dG());
/*     */       
/* 184 */       this.br = 40;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 190 */     super.initDatawatcher();
/*     */     
/* 192 */     this.datawatcher.register(bo, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 197 */     super.tick();
/*     */     
/* 199 */     if (this.world.isClientSide) {
/* 200 */       if (this.bq != this.bp) {
/* 201 */         updateSize();
/*     */       }
/* 203 */       this.bp = this.bq;
/* 204 */       if (eM()) {
/* 205 */         this.bq = MathHelper.a(this.bq + 1.0F, 0.0F, 6.0F);
/*     */       } else {
/* 207 */         this.bq = MathHelper.a(this.bq - 1.0F, 0.0F, 6.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 211 */     if (this.br > 0) {
/* 212 */       this.br--;
/*     */     }
/*     */     
/* 215 */     if (!this.world.isClientSide) {
/* 216 */       a((WorldServer)this.world, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize a(EntityPose var0) {
/* 222 */     if (this.bq > 0.0F) {
/*     */       
/* 224 */       float var1 = this.bq / 6.0F;
/* 225 */       float var2 = 1.0F + var1;
/* 226 */       return super.a(var0).a(1.0F, var2);
/*     */     } 
/* 228 */     return super.a(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity var0) {
/* 235 */     boolean var1 = var0.damageEntity(DamageSource.mobAttack(this), (int)b(GenericAttributes.ATTACK_DAMAGE));
/* 236 */     if (var1) {
/* 237 */       a(this, var0);
/*     */     }
/* 239 */     return var1;
/*     */   }
/*     */   
/*     */   public boolean eM() {
/* 243 */     return ((Boolean)this.datawatcher.<Boolean>get(bo)).booleanValue();
/*     */   }
/*     */   
/*     */   public void t(boolean var0) {
/* 247 */     this.datawatcher.set(bo, Boolean.valueOf(var0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float dL() {
/* 256 */     return 0.98F;
/*     */   }
/*     */ 
/*     */   
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/* 261 */     if (var3 == null) {
/* 262 */       var3 = new EntityAgeable.a(1.0F);
/*     */     }
/*     */     
/* 265 */     return super.prepare(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class b
/*     */     extends PathfinderGoalHurtByTarget
/*     */   {
/*     */     public b(EntityPolarBear this$0) {
/* 274 */       super(this$0, new Class[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 279 */       super.c();
/* 280 */       if (this.a.isBaby()) {
/* 281 */         g();
/* 282 */         d();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(EntityInsentient var0, EntityLiving var1) {
/* 288 */       if (var0 instanceof EntityPolarBear && 
/* 289 */         !var0.isBaby()) {
/* 290 */         super.a(var0, var1);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class a
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityHuman>
/*     */   {
/*     */     public a(EntityPolarBear this$0) {
/* 302 */       super(this$0, EntityHuman.class, 20, true, true, (Predicate<EntityLiving>)null);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 307 */       if (this.i.isBaby()) {
/* 308 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 312 */       if (super.a()) {
/* 313 */         List<EntityPolarBear> var0 = this.i.world.a(EntityPolarBear.class, this.i.getBoundingBox().grow(8.0D, 4.0D, 8.0D));
/* 314 */         for (EntityPolarBear var2 : var0) {
/* 315 */           if (var2.isBaby()) {
/* 316 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 321 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected double k() {
/* 326 */       return super.k() * 0.5D;
/*     */     }
/*     */   }
/*     */   
/*     */   class c extends PathfinderGoalMeleeAttack {
/*     */     public c(EntityPolarBear this$0) {
/* 332 */       super(this$0, 1.25D, true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(EntityLiving var0, double var1) {
/* 337 */       double var3 = a(var0);
/* 338 */       if (var1 <= var3 && h()) {
/* 339 */         g();
/* 340 */         this.a.attackEntity(var0);
/* 341 */         this.b.t(false);
/* 342 */       } else if (var1 <= var3 * 2.0D) {
/* 343 */         if (h()) {
/* 344 */           this.b.t(false);
/* 345 */           g();
/*     */         } 
/* 347 */         if (j() <= 10) {
/* 348 */           this.b.t(true);
/* 349 */           this.b.eL();
/*     */         } 
/*     */       } else {
/*     */         
/* 353 */         g();
/* 354 */         this.b.t(false);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 360 */       this.b.t(false);
/* 361 */       super.d();
/*     */     }
/*     */ 
/*     */     
/*     */     protected double a(EntityLiving var0) {
/* 366 */       return (4.0F + var0.getWidth());
/*     */     }
/*     */   }
/*     */   
/*     */   class d extends PathfinderGoalPanic {
/*     */     public d(EntityPolarBear this$0) {
/* 372 */       super(this$0, 2.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 377 */       if (!this.g.isBaby() && !this.g.isBurning()) {
/* 378 */         return false;
/*     */       }
/* 380 */       return super.a();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPolarBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */