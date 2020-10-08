/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityRabbit
/*     */   extends EntityAnimal {
/*  10 */   private static final DataWatcherObject<Integer> bo = DataWatcher.a((Class)EntityRabbit.class, DataWatcherRegistry.b);
/*  11 */   private static final MinecraftKey bp = new MinecraftKey("killer_bunny");
/*     */   private int bq;
/*     */   private int br;
/*     */   private boolean bs;
/*     */   
/*     */   static {
/*  17 */     isHostileTowards = (entityliving -> (entityliving instanceof EntityArmorStand) ? false : ((entityliving instanceof EntityRabbit) ? ((((EntityRabbit)entityliving).getRabbitType() != 99)) : (!(entityliving instanceof IMonster))));
/*     */   }
/*     */ 
/*     */   
/*     */   private int bt;
/*     */   
/*     */   private int bu;
/*     */   
/*     */   public static final Predicate<EntityLiving> isHostileTowards;
/*     */   
/*     */   public EntityRabbit(EntityTypes<? extends EntityRabbit> entitytypes, World world) {
/*  28 */     super((EntityTypes)entitytypes, world);
/*  29 */     this.bi = new ControllerJumpRabbit(this);
/*  30 */     this.moveController = new ControllerMoveRabbit(this);
/*  31 */     initializePathFinderGoals();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializePathFinderGoals() {
/*  36 */     i(0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initPathfinder() {
/*  42 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  43 */     this.goalSelector.a(1, new PathfinderGoalRabbitPanic(this, 2.2D));
/*  44 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 0.8D));
/*  45 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.0D, RecipeItemStack.a(new IMaterial[] { Items.CARROT, Items.GOLDEN_CARROT, Blocks.DANDELION }, ), false));
/*  46 */     this.goalSelector.a(4, new PathfinderGoalRabbitAvoidTarget<>(this, EntityHuman.class, 8.0F, 2.2D, 2.2D));
/*  47 */     this.goalSelector.a(4, new PathfinderGoalRabbitAvoidTarget<>(this, EntityWolf.class, 10.0F, 2.2D, 2.2D));
/*  48 */     this.goalSelector.a(4, new PathfinderGoalRabbitAvoidTarget<>(this, EntityMonster.class, 4.0F, 2.2D, 2.2D));
/*  49 */     this.goalSelector.a(5, new PathfinderGoalEatCarrots(this));
/*  50 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 0.6D));
/*  51 */     this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 10.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected float dI() {
/*  56 */     if (!this.positionChanged && (!this.moveController.b() || this.moveController.e() <= locY() + 0.5D)) {
/*  57 */       PathEntity pathentity = this.navigation.k();
/*     */       
/*  59 */       if (pathentity != null && !pathentity.c()) {
/*  60 */         Vec3D vec3d = pathentity.a(this);
/*     */         
/*  62 */         if (vec3d.y > locY() + 0.5D) {
/*  63 */           return 0.5F;
/*     */         }
/*     */       } 
/*     */       
/*  67 */       return (this.moveController.c() <= 0.6D) ? 0.2F : 0.3F;
/*     */     } 
/*  69 */     return 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void jump() {
/*  75 */     super.jump();
/*  76 */     double d0 = this.moveController.c();
/*     */     
/*  78 */     if (d0 > 0.0D) {
/*  79 */       double d1 = c(getMot());
/*     */       
/*  81 */       if (d1 < 0.01D) {
/*  82 */         a(0.1F, new Vec3D(0.0D, 0.0D, 1.0D));
/*     */       }
/*     */     } 
/*     */     
/*  86 */     if (!this.world.isClientSide) {
/*  87 */       this.world.broadcastEntityEffect(this, (byte)1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void i(double d0) {
/*  93 */     getNavigation().a(d0);
/*  94 */     this.moveController.a(this.moveController.d(), this.moveController.e(), this.moveController.f(), d0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJumping(boolean flag) {
/*  99 */     super.setJumping(flag);
/* 100 */     if (flag) {
/* 101 */       playSound(getSoundJump(), getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void eK() {
/* 107 */     setJumping(true);
/* 108 */     this.br = 10;
/* 109 */     this.bq = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 114 */     super.initDatawatcher();
/* 115 */     this.datawatcher.register(bo, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void mobTick() {
/* 120 */     if (this.bt > 0) {
/* 121 */       this.bt--;
/*     */     }
/*     */     
/* 124 */     if (this.bu > 0) {
/* 125 */       this.bu -= this.random.nextInt(3);
/* 126 */       if (this.bu < 0) {
/* 127 */         this.bu = 0;
/*     */       }
/*     */     } 
/*     */     
/* 131 */     if (this.onGround) {
/* 132 */       if (!this.bs) {
/* 133 */         setJumping(false);
/* 134 */         eW();
/*     */       } 
/*     */       
/* 137 */       if (getRabbitType() == 99 && this.bt == 0) {
/* 138 */         EntityLiving entityliving = getGoalTarget();
/*     */         
/* 140 */         if (entityliving != null && h(entityliving) < 16.0D) {
/* 141 */           b(entityliving.locX(), entityliving.locZ());
/* 142 */           this.moveController.a(entityliving.locX(), entityliving.locY(), entityliving.locZ(), this.moveController.c());
/* 143 */           eK();
/* 144 */           this.bs = true;
/*     */         } 
/*     */       } 
/*     */       
/* 148 */       ControllerJumpRabbit entityrabbit_controllerjumprabbit = (ControllerJumpRabbit)this.bi;
/*     */       
/* 150 */       if (!entityrabbit_controllerjumprabbit.c()) {
/* 151 */         if (this.moveController.b() && this.bt == 0) {
/* 152 */           PathEntity pathentity = this.navigation.k();
/* 153 */           Vec3D vec3d = new Vec3D(this.moveController.d(), this.moveController.e(), this.moveController.f());
/*     */           
/* 155 */           if (pathentity != null && !pathentity.c()) {
/* 156 */             vec3d = pathentity.a(this);
/*     */           }
/*     */           
/* 159 */           b(vec3d.x, vec3d.z);
/* 160 */           eK();
/*     */         } 
/* 162 */       } else if (!entityrabbit_controllerjumprabbit.d()) {
/* 163 */         eO();
/*     */       } 
/*     */     } 
/*     */     
/* 167 */     this.bs = this.onGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean aN() {
/* 172 */     return false;
/*     */   }
/*     */   
/*     */   private void b(double d0, double d1) {
/* 176 */     this.yaw = (float)(MathHelper.d(d1 - locZ(), d0 - locX()) * 57.2957763671875D) - 90.0F;
/*     */   }
/*     */   
/*     */   private void eO() {
/* 180 */     ((ControllerJumpRabbit)this.bi).a(true);
/*     */   }
/*     */   
/*     */   private void eU() {
/* 184 */     ((ControllerJumpRabbit)this.bi).a(false);
/*     */   }
/*     */   
/*     */   private void eV() {
/* 188 */     if (this.moveController.c() < 2.2D) {
/* 189 */       this.bt = 10;
/*     */     } else {
/* 191 */       this.bt = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void eW() {
/* 197 */     eV();
/* 198 */     eU();
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 203 */     super.movementTick();
/* 204 */     if (this.bq != this.br) {
/* 205 */       this.bq++;
/* 206 */     } else if (this.br != 0) {
/* 207 */       this.bq = 0;
/* 208 */       this.br = 0;
/* 209 */       setJumping(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeProvider.Builder eL() {
/* 215 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 3.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 220 */     super.saveData(nbttagcompound);
/* 221 */     nbttagcompound.setInt("RabbitType", getRabbitType());
/* 222 */     nbttagcompound.setInt("MoreCarrotTicks", this.bu);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 227 */     super.loadData(nbttagcompound);
/* 228 */     setRabbitType(nbttagcompound.getInt("RabbitType"));
/* 229 */     this.bu = nbttagcompound.getInt("MoreCarrotTicks");
/*     */   }
/*     */   
/*     */   protected SoundEffect getSoundJump() {
/* 233 */     return SoundEffects.ENTITY_RABBIT_JUMP;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 238 */     return SoundEffects.ENTITY_RABBIT_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 243 */     return SoundEffects.ENTITY_RABBIT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 248 */     return SoundEffects.ENTITY_RABBIT_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 253 */     if (getRabbitType() == 99) {
/* 254 */       playSound(SoundEffects.ENTITY_RABBIT_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/* 255 */       return entity.damageEntity(DamageSource.mobAttack(this), 8.0F);
/*     */     } 
/* 257 */     return entity.damageEntity(DamageSource.mobAttack(this), 3.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/* 263 */     return (getRabbitType() == 99) ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 268 */     return isInvulnerable(damagesource) ? false : super.damageEntity(damagesource, f);
/*     */   }
/*     */   
/*     */   private boolean b(Item item) {
/* 272 */     return (item == Items.CARROT || item == Items.GOLDEN_CARROT || item == Blocks.DANDELION.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityRabbit createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 277 */     EntityRabbit entityrabbit = EntityTypes.RABBIT.a(worldserver);
/* 278 */     int i = a(worldserver);
/*     */     
/* 280 */     if (this.random.nextInt(20) != 0) {
/* 281 */       if (entityageable instanceof EntityRabbit && this.random.nextBoolean()) {
/* 282 */         i = ((EntityRabbit)entityageable).getRabbitType();
/*     */       } else {
/* 284 */         i = getRabbitType();
/*     */       } 
/*     */     }
/*     */     
/* 288 */     entityrabbit.setRabbitType(i);
/* 289 */     return entityrabbit;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 294 */     return b(itemstack.getItem());
/*     */   }
/*     */   
/*     */   public int getRabbitType() {
/* 298 */     return ((Integer)this.datawatcher.<Integer>get(bo)).intValue();
/*     */   }
/*     */   
/*     */   public void setRabbitType(int i) {
/* 302 */     if (i == 99) {
/* 303 */       getAttributeInstance(GenericAttributes.ARMOR).setValue(8.0D);
/* 304 */       this.goalSelector.a(4, new PathfinderGoalKillerRabbitMeleeAttack(this));
/* 305 */       this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
/* 306 */       this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityLiving.class, 10, true, false, isHostileTowards));
/* 307 */       if (!hasCustomName()) {
/* 308 */         setCustomName(new ChatMessage(SystemUtils.a("entity", bp)));
/*     */       }
/*     */     } 
/*     */     
/* 312 */     this.datawatcher.set(bo, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 318 */     int i = a(worldaccess);
/*     */     
/* 320 */     if (groupdataentity instanceof GroupDataRabbit) {
/* 321 */       i = ((GroupDataRabbit)groupdataentity).a;
/*     */     } else {
/* 323 */       groupdataentity = new GroupDataRabbit(i);
/*     */     } 
/*     */     
/* 326 */     setRabbitType(i);
/* 327 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   private int a(GeneratorAccess generatoraccess) {
/* 331 */     if (this.random.nextInt(100) <= 25) return 99; 
/* 332 */     BiomeBase biomebase = generatoraccess.getBiome(getChunkCoordinates());
/* 333 */     int i = this.random.nextInt(100);
/*     */     
/* 335 */     return (biomebase.c() == BiomeBase.Precipitation.SNOW) ? ((i < 80) ? 1 : 3) : ((biomebase.t() == BiomeBase.Geography.DESERT) ? 4 : ((i < 50) ? 0 : ((i < 90) ? 5 : 2)));
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<EntityRabbit> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 339 */     IBlockData iblockdata = generatoraccess.getType(blockposition.down());
/*     */     
/* 341 */     return ((iblockdata.a(Blocks.GRASS_BLOCK) || iblockdata.a(Blocks.SNOW) || iblockdata.a(Blocks.SAND)) && generatoraccess.getLightLevel(blockposition, 0) > 8);
/*     */   }
/*     */   
/*     */   private boolean eX() {
/* 345 */     return (this.bu == 0);
/*     */   }
/*     */   
/*     */   static class PathfinderGoalKillerRabbitMeleeAttack
/*     */     extends PathfinderGoalMeleeAttack {
/*     */     public PathfinderGoalKillerRabbitMeleeAttack(EntityRabbit entityrabbit) {
/* 351 */       super(entityrabbit, 1.4D, true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected double a(EntityLiving entityliving) {
/* 356 */       return (4.0F + entityliving.getWidth());
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalRabbitPanic
/*     */     extends PathfinderGoalPanic {
/*     */     private final EntityRabbit g;
/*     */     
/*     */     public PathfinderGoalRabbitPanic(EntityRabbit entityrabbit, double d0) {
/* 365 */       super(entityrabbit, d0);
/* 366 */       this.g = entityrabbit;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 371 */       super.e();
/* 372 */       this.g.i(this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalEatCarrots
/*     */     extends PathfinderGoalGotoTarget {
/*     */     private final EntityRabbit entity;
/*     */     private boolean h;
/*     */     private boolean i;
/*     */     
/*     */     public PathfinderGoalEatCarrots(EntityRabbit entityrabbit) {
/* 383 */       super(entityrabbit, 0.699999988079071D, 16);
/* 384 */       this.entity = entityrabbit;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 389 */       if (this.c <= 0) {
/* 390 */         if (!this.entity.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
/* 391 */           return false;
/*     */         }
/*     */         
/* 394 */         this.i = false;
/* 395 */         this.h = this.entity.eX();
/* 396 */         this.h = true;
/*     */       } 
/*     */       
/* 399 */       return super.a();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 404 */       return (this.i && super.b());
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 409 */       super.e();
/* 410 */       this.entity.getControllerLook().a(this.e.getX() + 0.5D, (this.e.getY() + 1), this.e.getZ() + 0.5D, 10.0F, this.entity.O());
/* 411 */       if (l()) {
/* 412 */         World world = this.entity.world;
/* 413 */         BlockPosition blockposition = this.e.up();
/* 414 */         IBlockData iblockdata = world.getType(blockposition);
/* 415 */         Block block = iblockdata.getBlock();
/*     */         
/* 417 */         if (this.i && block instanceof BlockCarrots) {
/* 418 */           Integer integer = (Integer)iblockdata.get(BlockCarrots.AGE);
/*     */           
/* 420 */           if (integer.intValue() == 0) {
/*     */             
/* 422 */             if (CraftEventFactory.callEntityChangeBlockEvent(this.entity, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/*     */               return;
/*     */             }
/*     */             
/* 426 */             world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 2);
/* 427 */             world.a(blockposition, true, this.entity);
/*     */           } else {
/*     */             
/* 430 */             if (CraftEventFactory.callEntityChangeBlockEvent(this.entity, blockposition, iblockdata
/*     */ 
/*     */                 
/* 433 */                 .set(BlockCarrots.AGE, Integer.valueOf(integer.intValue() - 1)))
/* 434 */               .isCancelled()) {
/*     */               return;
/*     */             }
/*     */             
/* 438 */             world.setTypeAndData(blockposition, iblockdata.set(BlockCarrots.AGE, Integer.valueOf(integer.intValue() - 1)), 2);
/* 439 */             world.triggerEffect(2001, blockposition, Block.getCombinedId(iblockdata));
/*     */           } 
/*     */           
/* 442 */           this.entity.bu = 40;
/*     */         } 
/*     */         
/* 445 */         this.i = false;
/* 446 */         this.c = 10;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/* 453 */       Block block = iworldreader.getType(blockposition).getBlock();
/*     */       
/* 455 */       if (block == Blocks.FARMLAND && this.h && !this.i) {
/* 456 */         blockposition = blockposition.up();
/* 457 */         IBlockData iblockdata = iworldreader.getType(blockposition);
/*     */         
/* 459 */         block = iblockdata.getBlock();
/* 460 */         if (block instanceof BlockCarrots && ((BlockCarrots)block).isRipe(iblockdata)) {
/* 461 */           this.i = true;
/* 462 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 466 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalRabbitAvoidTarget<T extends EntityLiving>
/*     */     extends PathfinderGoalAvoidTarget<T> {
/*     */     private final EntityRabbit i;
/*     */     
/*     */     public PathfinderGoalRabbitAvoidTarget(EntityRabbit entityrabbit, Class<T> oclass, float f, double d0, double d1) {
/* 475 */       super(entityrabbit, oclass, f, d0, d1);
/* 476 */       this.i = entityrabbit;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 481 */       return (this.i.getRabbitType() != 99 && super.a());
/*     */     }
/*     */   }
/*     */   
/*     */   static class ControllerMoveRabbit
/*     */     extends ControllerMove {
/*     */     private final EntityRabbit i;
/*     */     private double j;
/*     */     
/*     */     public ControllerMoveRabbit(EntityRabbit entityrabbit) {
/* 491 */       super(entityrabbit);
/* 492 */       this.i = entityrabbit;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 497 */       if (this.i.onGround && !this.i.jumping && !((EntityRabbit.ControllerJumpRabbit)this.i.bi).c()) {
/* 498 */         this.i.i(0.0D);
/* 499 */       } else if (b()) {
/* 500 */         this.i.i(this.j);
/*     */       } 
/*     */       
/* 503 */       super.a();
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(double d0, double d1, double d2, double d3) {
/* 508 */       if (this.i.isInWater()) {
/* 509 */         d3 = 1.5D;
/*     */       }
/*     */       
/* 512 */       super.a(d0, d1, d2, d3);
/* 513 */       if (d3 > 0.0D) {
/* 514 */         this.j = d3;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class ControllerJumpRabbit
/*     */     extends ControllerJump
/*     */   {
/*     */     private final EntityRabbit c;
/*     */     private boolean d;
/*     */     
/*     */     public ControllerJumpRabbit(EntityRabbit entityrabbit) {
/* 526 */       super(entityrabbit);
/* 527 */       this.c = entityrabbit;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 531 */       return this.a;
/*     */     }
/*     */     
/*     */     public boolean d() {
/* 535 */       return this.d;
/*     */     }
/*     */     
/*     */     public void a(boolean flag) {
/* 539 */       this.d = flag;
/*     */     }
/*     */ 
/*     */     
/*     */     public void b() {
/* 544 */       if (this.a) {
/* 545 */         this.c.eK();
/* 546 */         this.a = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GroupDataRabbit
/*     */     extends EntityAgeable.a
/*     */   {
/*     */     public final int a;
/*     */     
/*     */     public GroupDataRabbit(int i) {
/* 557 */       super(1.0F);
/* 558 */       this.a = i;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */