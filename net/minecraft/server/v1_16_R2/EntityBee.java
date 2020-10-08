/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.Comparator;
/*      */ import java.util.EnumSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Optional;
/*      */ import java.util.UUID;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.stream.Collectors;
/*      */ import java.util.stream.Stream;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*      */ import org.bukkit.event.entity.EntityTargetEvent;
/*      */ 
/*      */ public class EntityBee extends EntityAnimal implements IEntityAngerable, EntityBird {
/*   17 */   private static final DataWatcherObject<Byte> bo = DataWatcher.a((Class)EntityBee.class, DataWatcherRegistry.a);
/*   18 */   private static final DataWatcherObject<Integer> bp = DataWatcher.a((Class)EntityBee.class, DataWatcherRegistry.b);
/*   19 */   private static final IntRange bq = TimeRange.a(20, 39);
/*      */   private UUID br;
/*      */   private float bs;
/*      */   private float bt;
/*      */   private int bu;
/*      */   private int ticksSincePollination;
/*      */   public int cannotEnterHiveTicks;
/*      */   private int numCropsGrownSincePollination;
/*   27 */   private int by = 0;
/*   28 */   private int bz = 0; @Nullable
/*   29 */   private BlockPosition flowerPos = null;
/*      */   @Nullable
/*   31 */   public BlockPosition hivePos = null;
/*      */   
/*      */   private k bC;
/*      */   private e bD;
/*      */   private f bE;
/*      */   private int bF;
/*      */   
/*      */   public EntityBee(EntityTypes<? extends EntityBee> entitytypes, World world) {
/*   39 */     super((EntityTypes)entitytypes, world);
/*      */     
/*   41 */     this.moveController = new ControllerMoveFlying(this, 20, true)
/*      */       {
/*      */         public void tick() {
/*   44 */           if (getEntity().locY() <= 0.0D) {
/*   45 */             getEntity().setNoGravity(false);
/*      */           }
/*   47 */           super.tick();
/*      */         }
/*      */       };
/*      */     
/*   51 */     this.lookController = new j(this);
/*   52 */     a(PathType.DANGER_FIRE, -1.0F);
/*   53 */     a(PathType.WATER, -1.0F);
/*   54 */     a(PathType.WATER_BORDER, 16.0F);
/*   55 */     a(PathType.COCOA, -1.0F);
/*   56 */     a(PathType.FENCE, -1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initDatawatcher() {
/*   61 */     super.initDatawatcher();
/*   62 */     this.datawatcher.register(bo, Byte.valueOf((byte)0));
/*   63 */     this.datawatcher.register(bp, Integer.valueOf(0));
/*      */   }
/*      */ 
/*      */   
/*      */   public float a(BlockPosition blockposition, IWorldReader iworldreader) {
/*   68 */     return iworldreader.getType(blockposition).isAir() ? 10.0F : 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initPathfinder() {
/*   73 */     this.goalSelector.a(0, new b(this, 1.399999976158142D, true));
/*   74 */     this.goalSelector.a(1, new d());
/*   75 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
/*   76 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.25D, RecipeItemStack.a(TagsItem.FLOWERS), false));
/*   77 */     this.bC = new k();
/*   78 */     this.goalSelector.a(4, this.bC);
/*   79 */     this.goalSelector.a(5, new PathfinderGoalFollowParent(this, 1.25D));
/*   80 */     this.goalSelector.a(5, new i());
/*   81 */     this.bD = new e();
/*   82 */     this.goalSelector.a(5, this.bD);
/*   83 */     this.bE = new f();
/*   84 */     this.goalSelector.a(6, this.bE);
/*   85 */     this.goalSelector.a(7, new g());
/*   86 */     this.goalSelector.a(8, new l());
/*   87 */     this.goalSelector.a(9, new PathfinderGoalFloat(this));
/*   88 */     this.targetSelector.a(1, (new h(this)).a(new Class[0]));
/*   89 */     this.targetSelector.a(2, new c(this));
/*   90 */     this.targetSelector.a(3, new PathfinderGoalUniversalAngerReset<>(this, true));
/*      */   }
/*      */ 
/*      */   
/*      */   public void saveData(NBTTagCompound nbttagcompound) {
/*   95 */     super.saveData(nbttagcompound);
/*   96 */     if (hasHivePos()) {
/*   97 */       nbttagcompound.set("HivePos", GameProfileSerializer.a(getHivePos()));
/*      */     }
/*      */     
/*  100 */     if (hasFlowerPos()) {
/*  101 */       nbttagcompound.set("FlowerPos", GameProfileSerializer.a(getFlowerPos()));
/*      */     }
/*      */     
/*  104 */     nbttagcompound.setBoolean("HasNectar", hasNectar());
/*  105 */     nbttagcompound.setBoolean("HasStung", hasStung());
/*  106 */     nbttagcompound.setInt("TicksSincePollination", this.ticksSincePollination);
/*  107 */     nbttagcompound.setInt("CannotEnterHiveTicks", this.cannotEnterHiveTicks);
/*  108 */     nbttagcompound.setInt("CropsGrownSincePollination", this.numCropsGrownSincePollination);
/*  109 */     c(nbttagcompound);
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadData(NBTTagCompound nbttagcompound) {
/*  114 */     this.hivePos = null;
/*  115 */     if (nbttagcompound.hasKey("HivePos")) {
/*  116 */       this.hivePos = GameProfileSerializer.b(nbttagcompound.getCompound("HivePos"));
/*      */     }
/*      */     
/*  119 */     this.flowerPos = null;
/*  120 */     if (nbttagcompound.hasKey("FlowerPos")) {
/*  121 */       this.flowerPos = GameProfileSerializer.b(nbttagcompound.getCompound("FlowerPos"));
/*      */     }
/*      */     
/*  124 */     super.loadData(nbttagcompound);
/*  125 */     setHasNectar(nbttagcompound.getBoolean("HasNectar"));
/*  126 */     setHasStung(nbttagcompound.getBoolean("HasStung"));
/*  127 */     this.ticksSincePollination = nbttagcompound.getInt("TicksSincePollination");
/*  128 */     this.cannotEnterHiveTicks = nbttagcompound.getInt("CannotEnterHiveTicks");
/*  129 */     this.numCropsGrownSincePollination = nbttagcompound.getInt("CropsGrownSincePollination");
/*  130 */     a((WorldServer)this.world, nbttagcompound);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean attackEntity(Entity entity) {
/*  135 */     boolean flag = entity.damageEntity(DamageSource.b(this), (int)b(GenericAttributes.ATTACK_DAMAGE));
/*      */     
/*  137 */     if (flag) {
/*  138 */       a(this, entity);
/*  139 */       if (entity instanceof EntityLiving) {
/*  140 */         ((EntityLiving)entity).q(((EntityLiving)entity).dy() + 1);
/*  141 */         byte b0 = 0;
/*      */         
/*  143 */         if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
/*  144 */           b0 = 10;
/*  145 */         } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
/*  146 */           b0 = 18;
/*      */         } 
/*      */         
/*  149 */         if (b0 > 0) {
/*  150 */           ((EntityLiving)entity).addEffect(new MobEffect(MobEffects.POISON, b0 * 20, 0), EntityPotionEffectEvent.Cause.ATTACK);
/*      */         }
/*      */       } 
/*      */       
/*  154 */       setHasStung(true);
/*  155 */       pacify();
/*  156 */       playSound(SoundEffects.ENTITY_BEE_STING, 1.0F, 1.0F);
/*      */     } 
/*      */     
/*  159 */     return flag;
/*      */   }
/*      */ 
/*      */   
/*      */   public void tick() {
/*  164 */     super.tick();
/*  165 */     if (hasNectar() && getNumCropsGrownSincePollination() < 10 && this.random.nextFloat() < 0.05F) {
/*  166 */       for (int i = 0; i < this.random.nextInt(2) + 1; i++) {
/*  167 */         a(this.world, locX() - 0.30000001192092896D, locX() + 0.30000001192092896D, locZ() - 0.30000001192092896D, locZ() + 0.30000001192092896D, e(0.5D), Particles.FALLING_NECTAR);
/*      */       }
/*      */     }
/*      */     
/*  171 */     fe();
/*      */   }
/*      */   
/*      */   private void a(World world, double d0, double d1, double d2, double d3, double d4, ParticleParam particleparam) {
/*  175 */     world.addParticle(particleparam, MathHelper.d(world.random.nextDouble(), d0, d1), d4, MathHelper.d(world.random.nextDouble(), d2, d3), 0.0D, 0.0D, 0.0D);
/*      */   }
/*      */   
/*      */   private void h(BlockPosition blockposition) {
/*  179 */     Vec3D vec3d = Vec3D.c(blockposition);
/*  180 */     byte b0 = 0;
/*  181 */     BlockPosition blockposition1 = getChunkCoordinates();
/*  182 */     int i = (int)vec3d.y - blockposition1.getY();
/*      */     
/*  184 */     if (i > 2) {
/*  185 */       b0 = 4;
/*  186 */     } else if (i < -2) {
/*  187 */       b0 = -4;
/*      */     } 
/*      */     
/*  190 */     int j = 6;
/*  191 */     int m = 8;
/*  192 */     int l = blockposition1.k(blockposition);
/*      */     
/*  194 */     if (l < 15) {
/*  195 */       j = l / 2;
/*  196 */       m = l / 2;
/*      */     } 
/*      */     
/*  199 */     Vec3D vec3d1 = RandomPositionGenerator.b(this, j, m, b0, vec3d, 0.3141592741012573D);
/*      */     
/*  201 */     if (vec3d1 != null) {
/*  202 */       this.navigation.a(0.5F);
/*  203 */       this.navigation.a(vec3d1.x, vec3d1.y, vec3d1.z, 1.0D);
/*      */     } 
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPosition getFlowerPos() {
/*  209 */     return this.flowerPos;
/*      */   }
/*      */   
/*      */   public boolean hasFlowerPos() {
/*  213 */     return (this.flowerPos != null);
/*      */   }
/*      */   
/*      */   public void setFlowerPos(BlockPosition blockposition) {
/*  217 */     this.flowerPos = blockposition;
/*      */   }
/*      */   
/*      */   private boolean canPollinate() {
/*  221 */     return (this.ticksSincePollination > 3600);
/*      */   }
/*      */   
/*      */   private boolean fd() {
/*  225 */     if (this.cannotEnterHiveTicks <= 0 && !this.bC.k() && !hasStung() && getGoalTarget() == null) {
/*  226 */       boolean flag = (canPollinate() || this.world.isRaining() || this.world.isNight() || hasNectar());
/*      */       
/*  228 */       return (flag && !ff());
/*      */     } 
/*  230 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCannotEnterHiveTicks(int i) {
/*  235 */     this.cannotEnterHiveTicks = i;
/*      */   }
/*      */   
/*      */   private void fe() {
/*  239 */     this.bt = this.bs;
/*  240 */     if (fk()) {
/*  241 */       this.bs = Math.min(1.0F, this.bs + 0.2F);
/*      */     } else {
/*  243 */       this.bs = Math.max(0.0F, this.bs - 0.24F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void mobTick() {
/*  250 */     boolean flag = hasStung();
/*      */     
/*  252 */     if (aG()) {
/*  253 */       this.bF++;
/*      */     } else {
/*  255 */       this.bF = 0;
/*      */     } 
/*      */     
/*  258 */     if (this.bF > 20) {
/*  259 */       damageEntity(DamageSource.DROWN, 1.0F);
/*      */     }
/*      */     
/*  262 */     if (flag) {
/*  263 */       this.bu++;
/*  264 */       if (this.bu % 5 == 0 && this.random.nextInt(MathHelper.clamp(1200 - this.bu, 1, 1200)) == 0) {
/*  265 */         damageEntity(DamageSource.GENERIC, getHealth());
/*      */       }
/*      */     } 
/*      */     
/*  269 */     if (!hasNectar()) {
/*  270 */       this.ticksSincePollination++;
/*      */     }
/*      */     
/*  273 */     if (!this.world.isClientSide) {
/*  274 */       a((WorldServer)this.world, false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void eO() {
/*  280 */     this.ticksSincePollination = 0;
/*      */   }
/*      */   
/*      */   private boolean ff() {
/*  284 */     if (this.hivePos == null) {
/*  285 */       return false;
/*      */     }
/*  287 */     if (!this.world.isLoadedAndInBounds(this.hivePos)) return false; 
/*  288 */     TileEntity tileentity = this.world.getTileEntity(this.hivePos);
/*      */     
/*  290 */     return (tileentity instanceof TileEntityBeehive && ((TileEntityBeehive)tileentity).d());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAnger() {
/*  296 */     return ((Integer)this.datawatcher.<Integer>get(bp)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAnger(int i) {
/*  301 */     this.datawatcher.set(bp, Integer.valueOf(i));
/*      */   }
/*      */ 
/*      */   
/*      */   public UUID getAngerTarget() {
/*  306 */     return this.br;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAngerTarget(@Nullable UUID uuid) {
/*  311 */     this.br = uuid;
/*      */   }
/*      */ 
/*      */   
/*      */   public void anger() {
/*  316 */     setAnger(bq.a(this.random));
/*      */   }
/*      */   
/*      */   private boolean i(BlockPosition blockposition) {
/*  320 */     if (!this.world.isLoadedAndInBounds(blockposition)) return false; 
/*  321 */     TileEntity tileentity = this.world.getTileEntity(blockposition);
/*      */     
/*  323 */     return (tileentity instanceof TileEntityBeehive) ? (!((TileEntityBeehive)tileentity).isFull()) : false;
/*      */   }
/*      */   
/*      */   public boolean hasHivePos() {
/*  327 */     return (this.hivePos != null);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public BlockPosition getHivePos() {
/*  332 */     return this.hivePos;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void M() {
/*  337 */     super.M();
/*  338 */     PacketDebug.a(this);
/*      */   }
/*      */   
/*      */   private int getNumCropsGrownSincePollination() {
/*  342 */     return this.numCropsGrownSincePollination;
/*      */   }
/*      */   
/*      */   private void fh() {
/*  346 */     this.numCropsGrownSincePollination = 0;
/*      */   }
/*      */   
/*      */   private void fi() {
/*  350 */     this.numCropsGrownSincePollination++;
/*      */   }
/*      */ 
/*      */   
/*      */   public void movementTick() {
/*  355 */     super.movementTick();
/*  356 */     if (!this.world.isClientSide) {
/*  357 */       if (this.cannotEnterHiveTicks > 0) {
/*  358 */         this.cannotEnterHiveTicks--;
/*      */       }
/*      */       
/*  361 */       if (this.by > 0) {
/*  362 */         this.by--;
/*      */       }
/*      */       
/*  365 */       if (this.bz > 0) {
/*  366 */         this.bz--;
/*      */       }
/*      */       
/*  369 */       boolean flag = (isAngry() && !hasStung() && getGoalTarget() != null && getGoalTarget().h(this) < 4.0D);
/*      */       
/*  371 */       v(flag);
/*  372 */       if (this.ticksLived % 20 == 0 && !fj()) {
/*  373 */         this.hivePos = null;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean fj() {
/*  380 */     if (!hasHivePos()) {
/*  381 */       return false;
/*      */     }
/*  383 */     if (this.world.getChunkIfLoadedImmediately(this.hivePos.getX() >> 4, this.hivePos.getZ() >> 4) == null) return true; 
/*  384 */     TileEntity tileentity = this.world.getTileEntity(this.hivePos);
/*      */     
/*  386 */     return (tileentity != null && tileentity.getTileType() == TileEntityTypes.BEEHIVE);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasNectar() {
/*  391 */     return u(8);
/*      */   }
/*      */   
/*      */   public void setHasNectar(boolean flag) {
/*  395 */     if (flag) {
/*  396 */       eO();
/*      */     }
/*      */     
/*  399 */     d(8, flag);
/*      */   }
/*      */   
/*      */   public boolean hasStung() {
/*  403 */     return u(4);
/*      */   }
/*      */   
/*      */   public void setHasStung(boolean flag) {
/*  407 */     d(4, flag);
/*      */   }
/*      */   
/*      */   private boolean fk() {
/*  411 */     return u(2);
/*      */   }
/*      */   
/*      */   private void v(boolean flag) {
/*  415 */     d(2, flag);
/*      */   }
/*      */   
/*      */   private boolean j(BlockPosition blockposition) {
/*  419 */     return !b(blockposition, 32);
/*      */   }
/*      */   
/*      */   private void d(int i, boolean flag) {
/*  423 */     if (flag) {
/*  424 */       this.datawatcher.set(bo, Byte.valueOf((byte)(((Byte)this.datawatcher.<Byte>get(bo)).byteValue() | i)));
/*      */     } else {
/*  426 */       this.datawatcher.set(bo, Byte.valueOf((byte)(((Byte)this.datawatcher.<Byte>get(bo)).byteValue() & (i ^ 0xFFFFFFFF))));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean u(int i) {
/*  432 */     return ((((Byte)this.datawatcher.<Byte>get(bo)).byteValue() & i) != 0);
/*      */   }
/*      */   
/*      */   public static AttributeProvider.Builder eZ() {
/*  436 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 10.0D).a(GenericAttributes.FLYING_SPEED, 0.6000000238418579D).a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D).a(GenericAttributes.ATTACK_DAMAGE, 2.0D).a(GenericAttributes.FOLLOW_RANGE, 48.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   protected NavigationAbstract b(World world) {
/*  441 */     NavigationFlying navigationflying = new NavigationFlying(this, world)
/*      */       {
/*      */         public boolean a(BlockPosition blockposition) {
/*  444 */           return !this.b.getType(blockposition.down()).isAir();
/*      */         }
/*      */ 
/*      */         
/*      */         public void c() {
/*  449 */           if (!EntityBee.this.bC.k()) {
/*  450 */             super.c();
/*      */           }
/*      */         }
/*      */       };
/*      */     
/*  455 */     navigationflying.a(false);
/*  456 */     navigationflying.d(false);
/*  457 */     navigationflying.b(true);
/*  458 */     return navigationflying;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean k(ItemStack itemstack) {
/*  463 */     return itemstack.getItem().a(TagsItem.FLOWERS);
/*      */   }
/*      */   
/*      */   private boolean k(BlockPosition blockposition) {
/*  467 */     return (this.world.p(blockposition) && this.world.getType(blockposition).getBlock().a(TagsBlock.FLOWERS));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {}
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundAmbient() {
/*  475 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  480 */     return SoundEffects.ENTITY_BEE_HURT;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEffect getSoundDeath() {
/*  485 */     return SoundEffects.ENTITY_BEE_DEATH;
/*      */   }
/*      */ 
/*      */   
/*      */   protected float getSoundVolume() {
/*  490 */     return 0.4F;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityBee createChild(WorldServer worldserver, EntityAgeable entityageable) {
/*  495 */     return EntityTypes.BEE.a(worldserver);
/*      */   }
/*      */ 
/*      */   
/*      */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  500 */     return isBaby() ? (entitysize.height * 0.5F) : (entitysize.height * 0.5F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean b(float f2, float f1) {
/*  505 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {}
/*      */ 
/*      */   
/*      */   protected boolean ay() {
/*  513 */     return true;
/*      */   }
/*      */   
/*      */   public void fb() {
/*  517 */     setHasNectar(false);
/*  518 */     fh();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean damageEntity(DamageSource damagesource, float f1) {
/*  523 */     if (isInvulnerable(damagesource)) {
/*  524 */       return false;
/*      */     }
/*  526 */     Entity entity = damagesource.getEntity();
/*      */ 
/*      */     
/*  529 */     boolean result = super.damageEntity(damagesource, f1);
/*      */     
/*  531 */     if (result && !this.world.isClientSide) {
/*  532 */       this.bC.l();
/*      */     }
/*      */     
/*  535 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumMonsterType getMonsterType() {
/*  542 */     return EnumMonsterType.ARTHROPOD;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void c(Tag<FluidType> tag) {
/*  547 */     setMot(getMot().add(0.0D, 0.01D, 0.0D));
/*      */   }
/*      */   
/*      */   private boolean b(BlockPosition blockposition, int i) {
/*  551 */     return blockposition.a(getChunkCoordinates(), i);
/*      */   }
/*      */ 
/*      */   
/*      */   class d
/*      */     extends a
/*      */   {
/*      */     private d() {}
/*      */ 
/*      */     
/*      */     public boolean g() {
/*  562 */       if (EntityBee.this.hasHivePos() && EntityBee.this.fd() && EntityBee.this.hivePos.a(EntityBee.this.getPositionVector(), 2.0D)) {
/*  563 */         if (!EntityBee.this.world.isLoadedAndInBounds(EntityBee.this.hivePos)) return false; 
/*  564 */         TileEntity tileentity = EntityBee.this.world.getTileEntity(EntityBee.this.hivePos);
/*      */         
/*  566 */         if (tileentity instanceof TileEntityBeehive) {
/*  567 */           TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/*      */           
/*  569 */           if (!tileentitybeehive.isFull()) {
/*  570 */             return true;
/*      */           }
/*      */           
/*  573 */           EntityBee.this.hivePos = null;
/*      */         } 
/*      */       } 
/*      */       
/*  577 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean h() {
/*  582 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  587 */       if (!EntityBee.this.world.isLoadedAndInBounds(EntityBee.this.hivePos))
/*  588 */         return;  TileEntity tileentity = EntityBee.this.world.getTileEntity(EntityBee.this.hivePos);
/*      */       
/*  590 */       if (tileentity instanceof TileEntityBeehive) {
/*  591 */         TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/*      */         
/*  593 */         tileentitybeehive.addBee(EntityBee.this, EntityBee.this.hasNectar());
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   class b
/*      */     extends PathfinderGoalMeleeAttack
/*      */   {
/*      */     b(EntityCreature entitycreature, double d0, boolean flag) {
/*  602 */       super(entitycreature, d0, flag);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  607 */       return (super.a() && EntityBee.this.isAngry() && !EntityBee.this.hasStung());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  612 */       return (super.b() && EntityBee.this.isAngry() && !EntityBee.this.hasStung());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   class g
/*      */     extends a
/*      */   {
/*      */     private g() {}
/*      */ 
/*      */     
/*      */     public boolean g() {
/*  624 */       return (EntityBee.this.getNumCropsGrownSincePollination() >= 10) ? false : ((EntityBee.this.random.nextFloat() < 0.3F) ? false : ((EntityBee.this.hasNectar() && EntityBee.this.fj())));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean h() {
/*  629 */       return g();
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/*  634 */       if (EntityBee.this.random.nextInt(30) == 0) {
/*  635 */         for (int i = 1; i <= 2; i++) {
/*  636 */           BlockPosition blockposition = EntityBee.this.getChunkCoordinates().down(i);
/*  637 */           IBlockData iblockdata = EntityBee.this.world.getType(blockposition);
/*  638 */           Block block = iblockdata.getBlock();
/*  639 */           boolean flag = false;
/*  640 */           BlockStateInteger blockstateinteger = null;
/*      */           
/*  642 */           if (block.a(TagsBlock.BEE_GROWABLES)) {
/*  643 */             if (block instanceof BlockCrops) {
/*  644 */               BlockCrops blockcrops = (BlockCrops)block;
/*      */               
/*  646 */               if (!blockcrops.isRipe(iblockdata)) {
/*  647 */                 flag = true;
/*  648 */                 blockstateinteger = blockcrops.c();
/*      */               
/*      */               }
/*      */             
/*      */             }
/*  653 */             else if (block instanceof BlockStem) {
/*  654 */               int j = ((Integer)iblockdata.get(BlockStem.AGE)).intValue();
/*  655 */               if (j < 7) {
/*  656 */                 flag = true;
/*  657 */                 blockstateinteger = BlockStem.AGE;
/*      */               } 
/*  659 */             } else if (block == Blocks.SWEET_BERRY_BUSH) {
/*  660 */               int j = ((Integer)iblockdata.get(BlockSweetBerryBush.a)).intValue();
/*  661 */               if (j < 3) {
/*  662 */                 flag = true;
/*  663 */                 blockstateinteger = BlockSweetBerryBush.a;
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*  668 */             if (flag && !CraftEventFactory.callEntityChangeBlockEvent(EntityBee.this, blockposition, iblockdata.set(blockstateinteger, Integer.valueOf(((Integer)iblockdata.get(blockstateinteger)).intValue() + 1))).isCancelled()) {
/*  669 */               EntityBee.this.world.triggerEffect(2005, blockposition, 0);
/*  670 */               EntityBee.this.world.setTypeUpdate(blockposition, iblockdata.set(blockstateinteger, Integer.valueOf(((Integer)iblockdata.get(blockstateinteger)).intValue() + 1)));
/*  671 */               EntityBee.this.fi();
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   class i
/*      */     extends a
/*      */   {
/*      */     private i() {}
/*      */ 
/*      */     
/*      */     public boolean g() {
/*  688 */       return (EntityBee.this.by == 0 && !EntityBee.this.hasHivePos() && EntityBee.this.fd());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean h() {
/*  693 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  698 */       EntityBee.this.by = 200;
/*  699 */       List<BlockPosition> list = j();
/*      */       
/*  701 */       if (!list.isEmpty()) {
/*  702 */         BlockPosition blockposition; Iterator<BlockPosition> iterator = list.iterator();
/*      */ 
/*      */ 
/*      */         
/*      */         do {
/*  707 */           if (!iterator.hasNext()) {
/*  708 */             EntityBee.this.bD.j();
/*  709 */             EntityBee.this.hivePos = list.get(0);
/*      */             
/*      */             return;
/*      */           } 
/*  713 */           blockposition = iterator.next();
/*  714 */         } while (EntityBee.this.bD.b(blockposition));
/*      */         
/*  716 */         EntityBee.this.hivePos = blockposition;
/*      */       } 
/*      */     }
/*      */     
/*      */     private List<BlockPosition> j() {
/*  721 */       BlockPosition blockposition = EntityBee.this.getChunkCoordinates();
/*  722 */       VillagePlace villageplace = ((WorldServer)EntityBee.this.world).y();
/*  723 */       Stream<VillagePlaceRecord> stream = villageplace.c(villageplacetype -> 
/*  724 */           (villageplacetype == VillagePlaceType.t || villageplacetype == VillagePlaceType.u), blockposition, 20, VillagePlace.Occupancy.ANY);
/*      */ 
/*      */       
/*  727 */       return (List<BlockPosition>)stream.map(VillagePlaceRecord::f).filter(blockposition1 -> EntityBee.this.i(blockposition1))
/*      */         
/*  729 */         .sorted(Comparator.comparingDouble(blockposition1 -> blockposition1.j(blockposition)))
/*      */         
/*  731 */         .collect(Collectors.toList());
/*      */     } }
/*      */   class k extends a { private final Predicate<IBlockData> c; private int d;
/*      */     private int e;
/*      */     
/*      */     k() {
/*  737 */       this.c = (iblockdata -> iblockdata.a(TagsBlock.TALL_FLOWERS) ? (iblockdata.a(Blocks.SUNFLOWER) ? ((iblockdata.get(BlockTallPlant.HALF) == BlockPropertyDoubleBlockHalf.UPPER)) : true) : iblockdata.a(TagsBlock.SMALL_FLOWERS));
/*      */ 
/*      */       
/*  740 */       this.d = 0;
/*  741 */       this.e = 0;
/*      */ 
/*      */       
/*  744 */       this.h = 0;
/*      */ 
/*      */ 
/*      */       
/*  748 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*      */     }
/*      */     private boolean f; private Vec3D g; private int h;
/*      */     
/*      */     public boolean g() {
/*  753 */       if (EntityBee.this.bz > 0)
/*  754 */         return false; 
/*  755 */       if (EntityBee.this.hasNectar())
/*  756 */         return false; 
/*  757 */       if (EntityBee.this.world.isRaining())
/*  758 */         return false; 
/*  759 */       if (EntityBee.this.random.nextFloat() < 0.7F) {
/*  760 */         return false;
/*      */       }
/*  762 */       Optional<BlockPosition> optional = o();
/*      */       
/*  764 */       if (optional.isPresent()) {
/*  765 */         EntityBee.this.flowerPos = optional.get();
/*  766 */         EntityBee.this.navigation.a(EntityBee.this.flowerPos.getX() + 0.5D, EntityBee.this.flowerPos.getY() + 0.5D, EntityBee.this.flowerPos.getZ() + 0.5D, 1.2000000476837158D);
/*  767 */         return true;
/*      */       } 
/*  769 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean h() {
/*  776 */       if (!this.f)
/*  777 */         return false; 
/*  778 */       if (!EntityBee.this.hasFlowerPos())
/*  779 */         return false; 
/*  780 */       if (EntityBee.this.world.isRaining())
/*  781 */         return false; 
/*  782 */       if (j())
/*  783 */         return (EntityBee.this.random.nextFloat() < 0.2F); 
/*  784 */       if (EntityBee.this.ticksLived % 20 == 0 && !EntityBee.this.k(EntityBee.this.flowerPos)) {
/*  785 */         EntityBee.this.flowerPos = null;
/*  786 */         return false;
/*      */       } 
/*  788 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean j() {
/*  793 */       return (this.d > 400);
/*      */     }
/*      */     
/*      */     private boolean k() {
/*  797 */       return this.f;
/*      */     }
/*      */     
/*      */     private void l() {
/*  801 */       this.f = false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  806 */       this.d = 0;
/*  807 */       this.h = 0;
/*  808 */       this.e = 0;
/*  809 */       this.f = true;
/*  810 */       EntityBee.this.eO();
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/*  815 */       if (j()) {
/*  816 */         EntityBee.this.setHasNectar(true);
/*      */       }
/*      */       
/*  819 */       this.f = false;
/*  820 */       EntityBee.this.navigation.o();
/*  821 */       EntityBee.this.bz = 200;
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/*  826 */       this.h++;
/*  827 */       if (this.h > 600) {
/*  828 */         EntityBee.this.flowerPos = null;
/*      */       } else {
/*  830 */         Vec3D vec3d = Vec3D.c(EntityBee.this.flowerPos).add(0.0D, 0.6000000238418579D, 0.0D);
/*      */         
/*  832 */         if (vec3d.f(EntityBee.this.getPositionVector()) > 1.0D) {
/*  833 */           this.g = vec3d;
/*  834 */           m();
/*      */         } else {
/*  836 */           if (this.g == null) {
/*  837 */             this.g = vec3d;
/*      */           }
/*      */           
/*  840 */           boolean flag = (EntityBee.this.getPositionVector().f(this.g) <= 0.1D);
/*  841 */           boolean flag1 = true;
/*      */           
/*  843 */           if (!flag && this.h > 600) {
/*  844 */             EntityBee.this.flowerPos = null;
/*      */           } else {
/*  846 */             if (flag) {
/*  847 */               boolean flag2 = (EntityBee.this.random.nextInt(25) == 0);
/*      */               
/*  849 */               if (flag2) {
/*  850 */                 this.g = new Vec3D(vec3d.getX() + n(), vec3d.getY(), vec3d.getZ() + n());
/*  851 */                 EntityBee.this.navigation.o();
/*      */               } else {
/*  853 */                 flag1 = false;
/*      */               } 
/*      */               
/*  856 */               EntityBee.this.getControllerLook().a(vec3d.getX(), vec3d.getY(), vec3d.getZ());
/*      */             } 
/*      */             
/*  859 */             if (flag1) {
/*  860 */               m();
/*      */             }
/*      */             
/*  863 */             this.d++;
/*  864 */             if (EntityBee.this.random.nextFloat() < 0.05F && this.d > this.e + 60) {
/*  865 */               this.e = this.d;
/*  866 */               EntityBee.this.playSound(SoundEffects.ENTITY_BEE_POLLINATE, 1.0F, 1.0F);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void m() {
/*  875 */       EntityBee.this.getControllerMove().a(this.g.getX(), this.g.getY(), this.g.getZ(), 0.3499999940395355D);
/*      */     }
/*      */     
/*      */     private float n() {
/*  879 */       return (EntityBee.this.random.nextFloat() * 2.0F - 1.0F) * 0.33333334F;
/*      */     }
/*      */     
/*      */     private Optional<BlockPosition> o() {
/*  883 */       return a(this.c, 5.0D);
/*      */     }
/*      */     
/*      */     private Optional<BlockPosition> a(Predicate<IBlockData> predicate, double d0) {
/*  887 */       BlockPosition blockposition = EntityBee.this.getChunkCoordinates();
/*  888 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*      */       int i;
/*  890 */       for (i = 0; i <= d0; i = (i > 0) ? -i : (1 - i)) {
/*  891 */         for (int j = 0; j < d0; j++) {
/*  892 */           int m; for (m = 0; m <= j; m = (m > 0) ? -m : (1 - m)) {
/*  893 */             int l; for (l = (m < j && m > -j) ? j : 0; l <= j; l = (l > 0) ? -l : (1 - l)) {
/*  894 */               blockposition_mutableblockposition.a(blockposition, m, i - 1, l);
/*  895 */               if (blockposition.a(blockposition_mutableblockposition, d0) && predicate.test(EntityBee.this.world.getType(blockposition_mutableblockposition))) {
/*  896 */                 return Optional.of(blockposition_mutableblockposition);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  903 */       return Optional.empty();
/*      */     } }
/*      */ 
/*      */   
/*      */   class j
/*      */     extends ControllerLook {
/*      */     j(EntityInsentient entityinsentient) {
/*  910 */       super(entityinsentient);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a() {
/*  915 */       if (!EntityBee.this.isAngry()) {
/*  916 */         super.a();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean b() {
/*  922 */       return !EntityBee.this.bC.k();
/*      */     }
/*      */   }
/*      */   
/*      */   public class f
/*      */     extends a
/*      */   {
/*      */     private int c;
/*      */     
/*      */     f() {
/*  932 */       this.c = EntityBee.this.world.random.nextInt(10);
/*  933 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean g() {
/*  938 */       return (EntityBee.this.flowerPos != null && !EntityBee.this.ez() && j() && EntityBee.this.k(EntityBee.this.flowerPos) && !EntityBee.this.b(EntityBee.this.flowerPos, 2));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean h() {
/*  943 */       return g();
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  948 */       this.c = 0;
/*  949 */       super.c();
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/*  954 */       this.c = 0;
/*  955 */       EntityBee.this.navigation.o();
/*  956 */       EntityBee.this.navigation.g();
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/*  961 */       if (EntityBee.this.flowerPos != null) {
/*  962 */         this.c++;
/*  963 */         if (this.c > 600) {
/*  964 */           EntityBee.this.flowerPos = null;
/*  965 */         } else if (!EntityBee.this.navigation.n()) {
/*  966 */           if (EntityBee.this.j(EntityBee.this.flowerPos)) {
/*  967 */             EntityBee.this.flowerPos = null;
/*      */           } else {
/*  969 */             EntityBee.this.h(EntityBee.this.flowerPos);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private boolean j() {
/*  976 */       return (EntityBee.this.ticksSincePollination > 2400);
/*      */     }
/*      */   }
/*      */   
/*      */   public class e
/*      */     extends a
/*      */   {
/*      */     private int c;
/*      */     private List<BlockPosition> d;
/*      */     @Nullable
/*      */     private PathEntity e;
/*      */     private int f;
/*      */     
/*      */     e() {
/*  990 */       this.c = EntityBee.this.world.random.nextInt(10);
/*  991 */       this.d = Lists.newArrayList();
/*  992 */       this.e = null;
/*  993 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean g() {
/*  998 */       return (EntityBee.this.hivePos != null && !EntityBee.this.ez() && EntityBee.this.fd() && !d(EntityBee.this.hivePos) && EntityBee.this.world.getType(EntityBee.this.hivePos).a(TagsBlock.BEEHIVES));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean h() {
/* 1003 */       return g();
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/* 1008 */       this.c = 0;
/* 1009 */       this.f = 0;
/* 1010 */       super.c();
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/* 1015 */       this.c = 0;
/* 1016 */       this.f = 0;
/* 1017 */       EntityBee.this.navigation.o();
/* 1018 */       EntityBee.this.navigation.g();
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/* 1023 */       if (EntityBee.this.hivePos != null) {
/* 1024 */         this.c++;
/* 1025 */         if (this.c > 600) {
/* 1026 */           k();
/* 1027 */         } else if (!EntityBee.this.navigation.n()) {
/* 1028 */           if (!EntityBee.this.b(EntityBee.this.hivePos, 16)) {
/* 1029 */             if (EntityBee.this.j(EntityBee.this.hivePos)) {
/* 1030 */               l();
/*      */             } else {
/* 1032 */               EntityBee.this.h(EntityBee.this.hivePos);
/*      */             } 
/*      */           } else {
/* 1035 */             boolean flag = a(EntityBee.this.hivePos);
/*      */             
/* 1037 */             if (!flag) {
/* 1038 */               k();
/* 1039 */             } else if (this.e != null && EntityBee.this.navigation.k().a(this.e)) {
/* 1040 */               this.f++;
/* 1041 */               if (this.f > 60) {
/* 1042 */                 l();
/* 1043 */                 this.f = 0;
/*      */               } 
/*      */             } else {
/* 1046 */               this.e = EntityBee.this.navigation.k();
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean a(BlockPosition blockposition) {
/* 1055 */       EntityBee.this.navigation.a(10.0F);
/* 1056 */       EntityBee.this.navigation.a(blockposition.getX(), blockposition.getY(), blockposition.getZ(), 1.0D);
/* 1057 */       return (EntityBee.this.navigation.k() != null && EntityBee.this.navigation.k().j());
/*      */     }
/*      */     
/*      */     private boolean b(BlockPosition blockposition) {
/* 1061 */       return this.d.contains(blockposition);
/*      */     }
/*      */     
/*      */     private void c(BlockPosition blockposition) {
/* 1065 */       this.d.add(blockposition);
/*      */       
/* 1067 */       while (this.d.size() > 3) {
/* 1068 */         this.d.remove(0);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private void j() {
/* 1074 */       this.d.clear();
/*      */     }
/*      */     
/*      */     private void k() {
/* 1078 */       if (EntityBee.this.hivePos != null) {
/* 1079 */         c(EntityBee.this.hivePos);
/*      */       }
/*      */       
/* 1082 */       l();
/*      */     }
/*      */     
/*      */     private void l() {
/* 1086 */       EntityBee.this.hivePos = null;
/* 1087 */       EntityBee.this.by = 200;
/*      */     }
/*      */     
/*      */     private boolean d(BlockPosition blockposition) {
/* 1091 */       if (EntityBee.this.b(blockposition, 2)) {
/* 1092 */         return true;
/*      */       }
/* 1094 */       PathEntity pathentity = EntityBee.this.navigation.k();
/*      */       
/* 1096 */       return (pathentity != null && pathentity.m().equals(blockposition) && pathentity.j() && pathentity.c());
/*      */     }
/*      */   }
/*      */   
/*      */   class l
/*      */     extends PathfinderGoal
/*      */   {
/*      */     l() {
/* 1104 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1109 */       return (EntityBee.this.navigation.m() && EntityBee.this.random.nextInt(10) == 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/* 1114 */       return EntityBee.this.navigation.n();
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/* 1119 */       Vec3D vec3d = g();
/*      */       
/* 1121 */       if (vec3d != null) {
/* 1122 */         EntityBee.this.navigation.a(EntityBee.this.navigation.a(new BlockPosition(vec3d), 1), 1.0D);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @Nullable
/*      */     private Vec3D g() {
/*      */       Vec3D vec3d;
/* 1131 */       if (EntityBee.this.fj() && !EntityBee.this.b(EntityBee.this.hivePos, 22)) {
/* 1132 */         Vec3D vec3d1 = Vec3D.a(EntityBee.this.hivePos);
/*      */         
/* 1134 */         vec3d = vec3d1.d(EntityBee.this.getPositionVector()).d();
/*      */       } else {
/* 1136 */         vec3d = EntityBee.this.f(0.0F);
/*      */       } 
/*      */       
/* 1139 */       boolean flag = true;
/* 1140 */       Vec3D vec3d2 = RandomPositionGenerator.a(EntityBee.this, 8, 7, vec3d, 1.5707964F, 2, 1);
/*      */       
/* 1142 */       return (vec3d2 != null) ? vec3d2 : RandomPositionGenerator.a(EntityBee.this, 8, 4, -2, vec3d, 1.5707963705062866D);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   abstract class a
/*      */     extends PathfinderGoal
/*      */   {
/*      */     private a() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1156 */       return (g() && !EntityBee.this.isAngry());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/* 1161 */       return (h() && !EntityBee.this.isAngry());
/*      */     }
/*      */     public abstract boolean g();
/*      */     
/*      */     public abstract boolean h(); }
/*      */   
/*      */   static class c extends PathfinderGoalNearestAttackableTarget<EntityHuman> { c(EntityBee entitybee) {
/* 1168 */       super(entitybee, EntityHuman.class, 10, true, false, entitybee::a_);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/* 1173 */       return (h() && super.a());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/* 1178 */       boolean flag = h();
/*      */       
/* 1180 */       if (flag && this.e.getGoalTarget() != null) {
/* 1181 */         return super.b();
/*      */       }
/* 1183 */       this.g = null;
/* 1184 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean h() {
/* 1189 */       EntityBee entitybee = (EntityBee)this.e;
/*      */       
/* 1191 */       return (entitybee.isAngry() && !entitybee.hasStung());
/*      */     } }
/*      */ 
/*      */   
/*      */   class h
/*      */     extends PathfinderGoalHurtByTarget {
/*      */     h(EntityBee entitybee) {
/* 1198 */       super(entitybee, new Class[0]);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/* 1203 */       return (EntityBee.this.isAngry() && super.b());
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(EntityInsentient entityinsentient, EntityLiving entityliving) {
/* 1208 */       if (entityinsentient instanceof EntityBee && this.e.hasLineOfSight(entityliving))
/* 1209 */         entityinsentient.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY, true); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityBee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */