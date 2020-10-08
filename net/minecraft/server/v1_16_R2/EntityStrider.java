/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
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
/*     */ public class EntityStrider
/*     */   extends EntityAnimal
/*     */   implements ISteerable, ISaddleable
/*     */ {
/*  77 */   private static final RecipeItemStack bo = RecipeItemStack.a(new IMaterial[] { Items.bx });
/*  78 */   private static final RecipeItemStack bp = RecipeItemStack.a(new IMaterial[] { Items.bx, Items.WARPED_FUNGUS_ON_A_STICK });
/*     */   
/*  80 */   private static final DataWatcherObject<Integer> bq = DataWatcher.a((Class)EntityStrider.class, DataWatcherRegistry.b);
/*  81 */   private static final DataWatcherObject<Boolean> br = DataWatcher.a((Class)EntityStrider.class, DataWatcherRegistry.i);
/*  82 */   private static final DataWatcherObject<Boolean> bs = DataWatcher.a((Class)EntityStrider.class, DataWatcherRegistry.i);
/*     */   
/*     */   public final SaddleStorage saddleStorage;
/*     */   
/*     */   private PathfinderGoalTempt bu;
/*     */   private PathfinderGoalPanic bv;
/*     */   
/*     */   public EntityStrider(EntityTypes<? extends EntityStrider> var0, World var1) {
/*  90 */     super((EntityTypes)var0, var1);
/*  91 */     this.saddleStorage = new SaddleStorage(this.datawatcher, bq, bs);
/*     */     
/*  93 */     this.i = true;
/*     */     
/*  95 */     a(PathType.WATER, -1.0F);
/*  96 */     a(PathType.LAVA, 0.0F);
/*  97 */     a(PathType.DANGER_FIRE, 0.0F);
/*  98 */     a(PathType.DAMAGE_FIRE, 0.0F);
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<EntityStrider> var0, GeneratorAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/* 102 */     BlockPosition.MutableBlockPosition var5 = var3.i();
/*     */     do {
/* 104 */       var5.c(EnumDirection.UP);
/* 105 */     } while (var1.getFluid(var5).a(TagsFluid.LAVA));
/*     */     
/* 107 */     return var1.getType(var5).isAir();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> var0) {
/* 112 */     if (bq.equals(var0) && this.world.isClientSide) {
/* 113 */       this.saddleStorage.a();
/*     */     }
/* 115 */     super.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 120 */     super.initDatawatcher();
/* 121 */     this.datawatcher.register(bq, Integer.valueOf(0));
/* 122 */     this.datawatcher.register(br, Boolean.valueOf(false));
/* 123 */     this.datawatcher.register(bs, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/* 128 */     super.saveData(var0);
/* 129 */     this.saddleStorage.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/* 134 */     super.loadData(var0);
/* 135 */     this.saddleStorage.b(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSaddle() {
/* 140 */     return this.saddleStorage.hasSaddle();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSaddle() {
/* 145 */     return (isAlive() && !isBaby());
/*     */   }
/*     */ 
/*     */   
/*     */   public void saddle(@Nullable SoundCategory var0) {
/* 150 */     this.saddleStorage.setSaddle(true);
/* 151 */     if (var0 != null) {
/* 152 */       this.world.playSound((EntityHuman)null, this, SoundEffects.ENTITY_STRIDER_SADDLE, var0, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/* 158 */     this.bv = new PathfinderGoalPanic(this, 1.65D);
/* 159 */     this.goalSelector.a(1, this.bv);
/* 160 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
/* 161 */     this.bu = new PathfinderGoalTempt(this, 1.4D, false, bp);
/* 162 */     this.goalSelector.a(3, this.bu);
/* 163 */     this.goalSelector.a(4, new a(this, 1.5D));
/* 164 */     this.goalSelector.a(5, new PathfinderGoalFollowParent(this, 1.1D));
/* 165 */     this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D, 60));
/* 166 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/* 167 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/* 168 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityStrider.class, 8.0F));
/*     */   }
/*     */   
/*     */   public void setShivering(boolean var0) {
/* 172 */     this.datawatcher.set(br, Boolean.valueOf(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShivering() {
/* 177 */     if (getVehicle() instanceof EntityStrider) {
/* 178 */       return ((EntityStrider)getVehicle()).isShivering();
/*     */     }
/*     */     
/* 181 */     return ((Boolean)this.datawatcher.<Boolean>get(br)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(FluidType var0) {
/* 186 */     return var0.a(TagsFluid.LAVA);
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/* 191 */     float var0 = Math.min(0.25F, this.av);
/* 192 */     float var1 = this.aw;
/*     */     
/* 194 */     return getHeight() - 0.19D + (0.12F * MathHelper.cos(var1 * 1.5F) * 2.0F * var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean er() {
/* 199 */     Entity var0 = getRidingPassenger();
/* 200 */     if (!(var0 instanceof EntityHuman)) {
/* 201 */       return false;
/*     */     }
/*     */     
/* 204 */     EntityHuman var1 = (EntityHuman)var0;
/*     */     
/* 206 */     return (var1.getItemInMainHand().getItem() == Items.WARPED_FUNGUS_ON_A_STICK || var1.getItemInOffHand().getItem() == Items.WARPED_FUNGUS_ON_A_STICK);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader var0) {
/* 211 */     return var0.j(this);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getRidingPassenger() {
/* 217 */     if (getPassengers().isEmpty()) {
/* 218 */       return null;
/*     */     }
/* 220 */     return getPassengers().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3D b(EntityLiving var0) {
/* 230 */     Vec3D[] var1 = { a(getWidth(), var0.getWidth(), var0.yaw), a(getWidth(), var0.getWidth(), var0.yaw - 22.5F), a(getWidth(), var0.getWidth(), var0.yaw + 22.5F), a(getWidth(), var0.getWidth(), var0.yaw - 45.0F), a(getWidth(), var0.getWidth(), var0.yaw + 45.0F) };
/*     */ 
/*     */     
/* 233 */     Set<BlockPosition> var2 = Sets.newLinkedHashSet();
/* 234 */     double var3 = (getBoundingBox()).maxY;
/* 235 */     double var5 = (getBoundingBox()).minY - 0.5D;
/*     */     
/* 237 */     BlockPosition.MutableBlockPosition var7 = new BlockPosition.MutableBlockPosition();
/* 238 */     for (Vec3D var11 : var1) {
/* 239 */       var7.c(locX() + var11.x, var3, locZ() + var11.z);
/*     */       
/*     */       double var12;
/* 242 */       for (var12 = var3; var12 > var5; var12--) {
/* 243 */         var2.add(var7.immutableCopy());
/* 244 */         var7.c(EnumDirection.DOWN);
/*     */       } 
/*     */     } 
/*     */     
/* 248 */     for (BlockPosition var9 : var2) {
/* 249 */       if (this.world.getFluid(var9).a(TagsFluid.LAVA)) {
/*     */         continue;
/*     */       }
/*     */       
/* 253 */       double var10 = this.world.h(var9);
/* 254 */       if (DismountUtil.a(var10)) {
/* 255 */         Vec3D var12 = Vec3D.a(var9, var10);
/*     */         
/* 257 */         for (UnmodifiableIterator<EntityPose> unmodifiableIterator = var0.ei().iterator(); unmodifiableIterator.hasNext(); ) { EntityPose var14 = unmodifiableIterator.next();
/* 258 */           AxisAlignedBB var15 = var0.f(var14);
/*     */           
/* 260 */           if (DismountUtil.a(this.world, var0, var15.c(var12))) {
/* 261 */             var0.setPose(var14);
/* 262 */             return var12;
/*     */           }  }
/*     */       
/*     */       } 
/*     */     } 
/*     */     
/* 268 */     return new Vec3D(locX(), (getBoundingBox()).maxY, locZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(Vec3D var0) {
/* 273 */     q(eL());
/* 274 */     a(this, this.saddleStorage, var0);
/*     */   }
/*     */   
/*     */   public float eL() {
/* 278 */     return (float)b(GenericAttributes.MOVEMENT_SPEED) * (isShivering() ? 0.66F : 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public float N_() {
/* 283 */     return (float)b(GenericAttributes.MOVEMENT_SPEED) * (isShivering() ? 0.23F : 0.55F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a_(Vec3D var0) {
/* 288 */     super.g(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float as() {
/* 293 */     return this.B + 0.6F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition var0, IBlockData var1) {
/* 298 */     playSound(aP() ? SoundEffects.ENTITY_STRIDER_STEP_LAVA : SoundEffects.ENTITY_STRIDER_STEP, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean O_() {
/* 303 */     return this.saddleStorage.a(getRandom());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(double var0, boolean var2, IBlockData var3, BlockPosition var4) {
/* 308 */     checkBlockCollisions();
/*     */     
/* 310 */     if (aP()) {
/* 311 */       this.fallDistance = 0.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 315 */     super.a(var0, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 320 */     if (eO() && this.random.nextInt(140) == 0) {
/* 321 */       playSound(SoundEffects.ENTITY_STRIDER_HAPPY, 1.0F, dG());
/* 322 */     } else if (eN() && this.random.nextInt(60) == 0) {
/* 323 */       playSound(SoundEffects.ENTITY_STRIDER_RETREAT, 1.0F, dG());
/*     */     } 
/*     */     
/* 326 */     IBlockData var0 = this.world.getType(getChunkCoordinates());
/* 327 */     IBlockData var1 = aM();
/*     */     
/* 329 */     boolean var2 = (var0.a(TagsBlock.STRIDER_WARM_BLOCKS) || var1.a(TagsBlock.STRIDER_WARM_BLOCKS) || b(TagsFluid.LAVA) > 0.0D);
/*     */ 
/*     */     
/* 332 */     setShivering(!var2);
/*     */     
/* 334 */     super.tick();
/*     */     
/* 336 */     eU();
/* 337 */     checkBlockCollisions();
/*     */   }
/*     */   
/*     */   private boolean eN() {
/* 341 */     return (this.bv != null && this.bv.h());
/*     */   }
/*     */   
/*     */   private boolean eO() {
/* 345 */     return (this.bu != null && this.bu.h());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean q() {
/* 350 */     return true;
/*     */   }
/*     */   
/*     */   private void eU() {
/* 354 */     if (aP()) {
/* 355 */       VoxelShapeCollision var0 = VoxelShapeCollision.a(this);
/* 356 */       if (!var0.a(BlockFluids.c, getChunkCoordinates(), true) || this.world.getFluid(getChunkCoordinates().up()).a(TagsFluid.LAVA)) {
/* 357 */         setMot(getMot().a(0.5D).add(0.0D, 0.05D, 0.0D));
/*     */       } else {
/* 359 */         this.onGround = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eM() {
/* 365 */     return EntityInsentient.p()
/* 366 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.17499999701976776D)
/* 367 */       .a(GenericAttributes.FOLLOW_RANGE, 16.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 372 */     if (eN() || eO()) {
/* 373 */       return null;
/*     */     }
/* 375 */     return SoundEffects.ENTITY_STRIDER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 380 */     return SoundEffects.ENTITY_STRIDER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 385 */     return SoundEffects.ENTITY_STRIDER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean q(Entity var0) {
/* 390 */     return (getPassengers().isEmpty() && !a(TagsFluid.LAVA));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dN() {
/* 395 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 400 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World var0) {
/* 405 */     return new b(this, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition var0, IWorldReader var1) {
/* 410 */     if (var1.getType(var0).getFluid().a(TagsFluid.LAVA)) {
/* 411 */       return 10.0F;
/*     */     }
/*     */ 
/*     */     
/* 415 */     return aP() ? Float.NEGATIVE_INFINITY : 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityStrider createChild(WorldServer var0, EntityAgeable var1) {
/* 420 */     return EntityTypes.STRIDER.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack var0) {
/* 425 */     return bo.test(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropInventory() {
/* 430 */     super.dropInventory();
/* 431 */     if (hasSaddle()) {
/* 432 */       a(Items.SADDLE);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman var0, EnumHand var1) {
/* 438 */     boolean var2 = k(var0.b(var1));
/*     */     
/* 440 */     if (!var2 && hasSaddle() && !isVehicle() && !var0.ep()) {
/* 441 */       if (!this.world.isClientSide) {
/* 442 */         var0.startRiding(this);
/*     */       }
/* 444 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/*     */     
/* 447 */     EnumInteractionResult var3 = super.b(var0, var1);
/* 448 */     if (!var3.a()) {
/* 449 */       ItemStack var4 = var0.b(var1);
/* 450 */       if (var4.getItem() == Items.SADDLE) {
/* 451 */         return var4.a(var0, this, var1);
/*     */       }
/* 453 */       return EnumInteractionResult.PASS;
/* 454 */     }  if (var2 && !isSilent()) {
/* 455 */       this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_STRIDER_EAT, getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */     }
/*     */     
/* 458 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/* 470 */     if (isBaby()) {
/* 471 */       return super.prepare(var0, var1, var2, var3, var4);
/*     */     }
/*     */     
/* 474 */     if (this.random.nextInt(30) == 0) {
/* 475 */       EntityInsentient var5 = EntityTypes.ZOMBIFIED_PIGLIN.a(var0.getMinecraftWorld());
/* 476 */       var3 = a(var0, var1, var5, new EntityZombie.GroupDataZombie(EntityZombie.a(this.random), false));
/*     */       
/* 478 */       var5.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.WARPED_FUNGUS_ON_A_STICK));
/* 479 */       saddle((SoundCategory)null);
/* 480 */     } else if (this.random.nextInt(10) == 0) {
/* 481 */       EntityAgeable var5 = EntityTypes.STRIDER.a(var0.getMinecraftWorld());
/* 482 */       var5.setAgeRaw(-24000);
/*     */       
/* 484 */       var3 = a(var0, var1, var5, (GroupDataEntity)null);
/*     */     } else {
/* 486 */       var3 = new EntityAgeable.a(0.5F);
/*     */     } 
/*     */     
/* 489 */     return super.prepare(var0, var1, var2, var3, var4);
/*     */   }
/*     */   
/*     */   private GroupDataEntity a(WorldAccess var0, DifficultyDamageScaler var1, EntityInsentient var2, @Nullable GroupDataEntity var3) {
/* 493 */     var2.setPositionRotation(locX(), locY(), locZ(), this.yaw, 0.0F);
/* 494 */     var2.prepare(var0, var1, EnumMobSpawn.JOCKEY, var3, (NBTTagCompound)null);
/* 495 */     var2.a(this, true);
/*     */     
/* 497 */     return new EntityAgeable.a(0.0F);
/*     */   }
/*     */   
/*     */   static class b extends Navigation {
/*     */     b(EntityStrider var0, World var1) {
/* 502 */       super(var0, var1);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Pathfinder a(int var0) {
/* 508 */       this.o = new PathfinderNormal();
/* 509 */       return new Pathfinder(this.o, var0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a(PathType var0) {
/* 514 */       if (var0 == PathType.LAVA || var0 == PathType.DAMAGE_FIRE || var0 == PathType.DANGER_FIRE) {
/* 515 */         return true;
/*     */       }
/*     */       
/* 518 */       return super.a(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(BlockPosition var0) {
/* 523 */       return (this.b.getType(var0).a(Blocks.LAVA) || super.a(var0));
/*     */     }
/*     */   }
/*     */   
/*     */   static class a extends PathfinderGoalGotoTarget {
/*     */     private final EntityStrider g;
/*     */     
/*     */     private a(EntityStrider var0, double var1) {
/* 531 */       super(var0, var1, 8, 2);
/* 532 */       this.g = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPosition j() {
/* 537 */       return this.e;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 542 */       return (!this.g.aP() && a(this.g.world, this.e));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 547 */       return (!this.g.aP() && super.a());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean k() {
/* 552 */       return (this.d % 20 == 0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a(IWorldReader var0, BlockPosition var1) {
/* 557 */       return (var0.getType(var1).a(Blocks.LAVA) && var0.getType(var1.up()).a(var0, var1, PathMode.LAND));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityStrider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */