/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityCat extends EntityTameableAnimal {
/*  13 */   private static final RecipeItemStack br = RecipeItemStack.a(new IMaterial[] { Items.COD, Items.SALMON });
/*  14 */   private static final DataWatcherObject<Integer> bs = DataWatcher.a((Class)EntityCat.class, DataWatcherRegistry.b);
/*  15 */   private static final DataWatcherObject<Boolean> bt = DataWatcher.a((Class)EntityCat.class, DataWatcherRegistry.i);
/*  16 */   private static final DataWatcherObject<Boolean> bu = DataWatcher.a((Class)EntityCat.class, DataWatcherRegistry.i); public static final Map<Integer, MinecraftKey> bq;
/*  17 */   private static final DataWatcherObject<Integer> bv = DataWatcher.a((Class)EntityCat.class, DataWatcherRegistry.b); private a<EntityHuman> bw; static {
/*  18 */     bq = SystemUtils.<Map<Integer, MinecraftKey>>a(Maps.newHashMap(), hashmap -> {
/*     */           hashmap.put(Integer.valueOf(0), new MinecraftKey("textures/entity/cat/tabby.png"));
/*     */           hashmap.put(Integer.valueOf(1), new MinecraftKey("textures/entity/cat/black.png"));
/*     */           hashmap.put(Integer.valueOf(2), new MinecraftKey("textures/entity/cat/red.png"));
/*     */           hashmap.put(Integer.valueOf(3), new MinecraftKey("textures/entity/cat/siamese.png"));
/*     */           hashmap.put(Integer.valueOf(4), new MinecraftKey("textures/entity/cat/british_shorthair.png"));
/*     */           hashmap.put(Integer.valueOf(5), new MinecraftKey("textures/entity/cat/calico.png"));
/*     */           hashmap.put(Integer.valueOf(6), new MinecraftKey("textures/entity/cat/persian.png"));
/*     */           hashmap.put(Integer.valueOf(7), new MinecraftKey("textures/entity/cat/ragdoll.png"));
/*     */           hashmap.put(Integer.valueOf(8), new MinecraftKey("textures/entity/cat/white.png"));
/*     */           hashmap.put(Integer.valueOf(9), new MinecraftKey("textures/entity/cat/jellie.png"));
/*     */           hashmap.put(Integer.valueOf(10), new MinecraftKey("textures/entity/cat/all_black.png"));
/*     */         });
/*     */   }
/*     */   private PathfinderGoalTempt bx;
/*     */   private float by;
/*     */   private float bz;
/*     */   private float bA;
/*     */   private float bB;
/*     */   private float bC;
/*     */   private float bD;
/*     */   
/*     */   public EntityCat(EntityTypes<? extends EntityCat> entitytypes, World world) {
/*  41 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public MinecraftKey eU() {
/*  45 */     return bq.getOrDefault(Integer.valueOf(getCatType()), bq.get(Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  50 */     this.bx = new PathfinderGoalTemptChance(this, 0.6D, br, true);
/*  51 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  52 */     this.goalSelector.a(1, new PathfinderGoalSit(this));
/*  53 */     this.goalSelector.a(2, new b(this));
/*  54 */     this.goalSelector.a(3, this.bx);
/*  55 */     this.goalSelector.a(5, new PathfinderGoalCatSitOnBed(this, 1.1D, 8));
/*  56 */     this.goalSelector.a(6, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 5.0F, false));
/*  57 */     this.goalSelector.a(7, new PathfinderGoalJumpOnBlock(this, 0.8D));
/*  58 */     this.goalSelector.a(8, new PathfinderGoalLeapAtTarget(this, 0.3F));
/*  59 */     this.goalSelector.a(9, new PathfinderGoalOcelotAttack(this));
/*  60 */     this.goalSelector.a(10, new PathfinderGoalBreed(this, 0.8D));
/*  61 */     this.goalSelector.a(11, new PathfinderGoalRandomStrollLand(this, 0.8D, 1.0000001E-5F));
/*  62 */     this.goalSelector.a(12, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 10.0F));
/*  63 */     this.targetSelector.a(1, new PathfinderGoalRandomTargetNonTamed<>(this, EntityRabbit.class, false, (Predicate<EntityLiving>)null));
/*  64 */     this.targetSelector.a(1, new PathfinderGoalRandomTargetNonTamed<>(this, EntityTurtle.class, false, EntityTurtle.bo));
/*     */   }
/*     */   
/*     */   public int getCatType() {
/*  68 */     return ((Integer)this.datawatcher.<Integer>get(bs)).intValue();
/*     */   }
/*     */   
/*     */   public void setCatType(int i) {
/*  72 */     if (i < 0 || i >= 11) {
/*  73 */       i = this.random.nextInt(10);
/*     */     }
/*     */     
/*  76 */     this.datawatcher.set(bs, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public void x(boolean flag) {
/*  80 */     this.datawatcher.set(bt, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean eW() {
/*  84 */     return ((Boolean)this.datawatcher.<Boolean>get(bt)).booleanValue();
/*     */   }
/*     */   
/*     */   public void y(boolean flag) {
/*  88 */     this.datawatcher.set(bu, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean eX() {
/*  92 */     return ((Boolean)this.datawatcher.<Boolean>get(bu)).booleanValue();
/*     */   }
/*     */   
/*     */   public EnumColor getCollarColor() {
/*  96 */     return EnumColor.fromColorIndex(((Integer)this.datawatcher.<Integer>get(bv)).intValue());
/*     */   }
/*     */   
/*     */   public void setCollarColor(EnumColor enumcolor) {
/* 100 */     this.datawatcher.set(bv, Integer.valueOf(enumcolor.getColorIndex()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 105 */     super.initDatawatcher();
/* 106 */     this.datawatcher.register(bs, Integer.valueOf(1));
/* 107 */     this.datawatcher.register(bt, Boolean.valueOf(false));
/* 108 */     this.datawatcher.register(bu, Boolean.valueOf(false));
/* 109 */     this.datawatcher.register(bv, Integer.valueOf(EnumColor.RED.getColorIndex()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 114 */     super.saveData(nbttagcompound);
/* 115 */     nbttagcompound.setInt("CatType", getCatType());
/* 116 */     nbttagcompound.setByte("CollarColor", (byte)getCollarColor().getColorIndex());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 121 */     super.loadData(nbttagcompound);
/* 122 */     setCatType(nbttagcompound.getInt("CatType"));
/* 123 */     if (nbttagcompound.hasKeyOfType("CollarColor", 99)) {
/* 124 */       setCollarColor(EnumColor.fromColorIndex(nbttagcompound.getInt("CollarColor")));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mobTick() {
/* 131 */     if (getControllerMove().b()) {
/* 132 */       double d0 = getControllerMove().c();
/*     */       
/* 134 */       if (d0 == 0.6D) {
/* 135 */         setPose(EntityPose.CROUCHING);
/* 136 */         setSprinting(false);
/* 137 */       } else if (d0 == 1.33D) {
/* 138 */         setPose(EntityPose.STANDING);
/* 139 */         setSprinting(true);
/*     */       } else {
/* 141 */         setPose(EntityPose.STANDING);
/* 142 */         setSprinting(false);
/*     */       } 
/*     */     } else {
/* 145 */       setPose(EntityPose.STANDING);
/* 146 */       setSprinting(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAmbient() {
/* 154 */     return isTamed() ? (isInLove() ? SoundEffects.ENTITY_CAT_PURR : ((this.random.nextInt(4) == 0) ? SoundEffects.ENTITY_CAT_PURREOW : SoundEffects.ENTITY_CAT_AMBIENT)) : SoundEffects.ENTITY_CAT_STRAY_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public int D() {
/* 159 */     return 120;
/*     */   }
/*     */   
/*     */   public void eZ() {
/* 163 */     playSound(SoundEffects.ENTITY_CAT_HISS, getSoundVolume(), dG());
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 168 */     return SoundEffects.ENTITY_CAT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 173 */     return SoundEffects.ENTITY_CAT_DEATH;
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder fa() {
/* 177 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 10.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D).a(GenericAttributes.ATTACK_DAMAGE, 3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(EntityHuman entityhuman, ItemStack itemstack) {
/* 187 */     if (k(itemstack)) {
/* 188 */       playSound(SoundEffects.ENTITY_CAT_EAT, 1.0F, 1.0F);
/*     */     }
/*     */     
/* 191 */     super.a(entityhuman, itemstack);
/*     */   }
/*     */   
/*     */   private float fb() {
/* 195 */     return (float)b(GenericAttributes.ATTACK_DAMAGE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 200 */     return entity.damageEntity(DamageSource.mobAttack(this), fb());
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 205 */     super.tick();
/* 206 */     if (this.bx != null && this.bx.h() && !isTamed() && this.ticksLived % 100 == 0) {
/* 207 */       playSound(SoundEffects.ENTITY_CAT_BEG_FOR_FOOD, 1.0F, 1.0F);
/*     */     }
/*     */     
/* 210 */     fc();
/*     */   }
/*     */   
/*     */   private void fc() {
/* 214 */     if ((eW() || eX()) && this.ticksLived % 5 == 0) {
/* 215 */       playSound(SoundEffects.ENTITY_CAT_PURR, 0.6F + 0.4F * (this.random.nextFloat() - this.random.nextFloat()), 1.0F);
/*     */     }
/*     */     
/* 218 */     fd();
/* 219 */     fe();
/*     */   }
/*     */   
/*     */   private void fd() {
/* 223 */     this.bz = this.by;
/* 224 */     this.bB = this.bA;
/* 225 */     if (eW()) {
/* 226 */       this.by = Math.min(1.0F, this.by + 0.15F);
/* 227 */       this.bA = Math.min(1.0F, this.bA + 0.08F);
/*     */     } else {
/* 229 */       this.by = Math.max(0.0F, this.by - 0.22F);
/* 230 */       this.bA = Math.max(0.0F, this.bA - 0.13F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void fe() {
/* 236 */     this.bD = this.bC;
/* 237 */     if (eX()) {
/* 238 */       this.bC = Math.min(1.0F, this.bC + 0.1F);
/*     */     } else {
/* 240 */       this.bC = Math.max(0.0F, this.bC - 0.13F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityCat createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 247 */     EntityCat entitycat = EntityTypes.CAT.a(worldserver);
/*     */     
/* 249 */     if (entityageable instanceof EntityCat) {
/* 250 */       if (this.random.nextBoolean()) {
/* 251 */         entitycat.setCatType(getCatType());
/*     */       } else {
/* 253 */         entitycat.setCatType(((EntityCat)entityageable).getCatType());
/*     */       } 
/*     */       
/* 256 */       if (isTamed()) {
/* 257 */         entitycat.setOwnerUUID(getOwnerUUID());
/* 258 */         entitycat.setTamed(true);
/* 259 */         if (this.random.nextBoolean()) {
/* 260 */           entitycat.setCollarColor(getCollarColor());
/*     */         } else {
/* 262 */           entitycat.setCollarColor(((EntityCat)entityageable).getCollarColor());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 267 */     return entitycat;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 272 */     if (!isTamed())
/* 273 */       return false; 
/* 274 */     if (!(entityanimal instanceof EntityCat)) {
/* 275 */       return false;
/*     */     }
/* 277 */     EntityCat entitycat = (EntityCat)entityanimal;
/*     */     
/* 279 */     return (entitycat.isTamed() && super.mate(entityanimal));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 286 */     groupdataentity = super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/* 287 */     if (worldaccess.ae() > 0.9F) {
/* 288 */       setCatType(this.random.nextInt(11));
/*     */     } else {
/* 290 */       setCatType(this.random.nextInt(10));
/*     */     } 
/*     */     
/* 293 */     WorldServer worldserver = worldaccess.getMinecraftWorld();
/*     */     
/* 295 */     if (worldserver instanceof WorldServer && worldserver.getStructureManager().getStructureStarts(getChunkCoordinates(), true, StructureGenerator.SWAMP_HUT, worldaccess).e()) {
/* 296 */       setCatType(10);
/* 297 */       setPersistent();
/*     */     } 
/*     */     
/* 300 */     return groupdataentity;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 305 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 306 */     Item item = itemstack.getItem();
/*     */     
/* 308 */     if (this.world.isClientSide) {
/* 309 */       return (isTamed() && i(entityhuman)) ? EnumInteractionResult.SUCCESS : ((k(itemstack) && (getHealth() < getMaxHealth() || !isTamed())) ? EnumInteractionResult.SUCCESS : EnumInteractionResult.PASS);
/*     */     }
/*     */ 
/*     */     
/* 313 */     if (isTamed()) {
/* 314 */       if (i(entityhuman)) {
/* 315 */         if (!(item instanceof ItemDye)) {
/* 316 */           if (item.isFood() && k(itemstack) && getHealth() < getMaxHealth()) {
/* 317 */             a(entityhuman, itemstack);
/* 318 */             heal(item.getFoodInfo().getNutrition());
/* 319 */             return EnumInteractionResult.CONSUME;
/*     */           } 
/*     */           
/* 322 */           EnumInteractionResult enumInteractionResult = super.b(entityhuman, enumhand);
/* 323 */           if (!enumInteractionResult.a() || isBaby()) {
/* 324 */             setWillSit(!isWillSit());
/*     */           }
/*     */           
/* 327 */           return enumInteractionResult;
/*     */         } 
/*     */         
/* 330 */         EnumColor enumcolor = ((ItemDye)item).d();
/*     */         
/* 332 */         if (enumcolor != getCollarColor()) {
/* 333 */           setCollarColor(enumcolor);
/* 334 */           if (!entityhuman.abilities.canInstantlyBuild) {
/* 335 */             itemstack.subtract(1);
/*     */           }
/*     */           
/* 338 */           setPersistent();
/* 339 */           return EnumInteractionResult.CONSUME;
/*     */         } 
/*     */       } 
/* 342 */     } else if (k(itemstack)) {
/* 343 */       a(entityhuman, itemstack);
/* 344 */       if (this.random.nextInt(3) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
/* 345 */         tame(entityhuman);
/* 346 */         setWillSit(true);
/* 347 */         this.world.broadcastEntityEffect(this, (byte)7);
/*     */       } else {
/* 349 */         this.world.broadcastEntityEffect(this, (byte)6);
/*     */       } 
/*     */       
/* 352 */       setPersistent();
/* 353 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/*     */     
/* 356 */     EnumInteractionResult enuminteractionresult = super.b(entityhuman, enumhand);
/* 357 */     if (enuminteractionresult.a()) {
/* 358 */       setPersistent();
/*     */     }
/*     */     
/* 361 */     return enuminteractionresult;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 367 */     return br.test(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 372 */     return entitysize.height * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/* 377 */     return (!isTamed() && this.ticksLived > 2400);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eL() {
/* 382 */     if (this.bw == null) {
/* 383 */       this.bw = new a<>(this, EntityHuman.class, 16.0F, 0.8D, 1.33D);
/*     */     }
/*     */     
/* 386 */     this.goalSelector.a(this.bw);
/* 387 */     if (!isTamed()) {
/* 388 */       this.goalSelector.a(4, this.bw);
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityCat a;
/*     */     private EntityHuman b;
/*     */     private BlockPosition c;
/*     */     private int d;
/*     */     
/*     */     public b(EntityCat entitycat) {
/* 401 */       this.a = entitycat;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 406 */       if (!this.a.isTamed())
/* 407 */         return false; 
/* 408 */       if (this.a.isWillSit()) {
/* 409 */         return false;
/*     */       }
/* 411 */       EntityLiving entityliving = this.a.getOwner();
/*     */       
/* 413 */       if (entityliving instanceof EntityHuman) {
/* 414 */         this.b = (EntityHuman)entityliving;
/* 415 */         if (!entityliving.isSleeping()) {
/* 416 */           return false;
/*     */         }
/*     */         
/* 419 */         if (this.a.h(this.b) > 100.0D) {
/* 420 */           return false;
/*     */         }
/*     */         
/* 423 */         BlockPosition blockposition = this.b.getChunkCoordinates();
/* 424 */         IBlockData iblockdata = this.a.world.getType(blockposition);
/*     */         
/* 426 */         if (iblockdata.getBlock().a(TagsBlock.BEDS)) {
/* 427 */           this
/*     */             
/* 429 */             .c = iblockdata.d(BlockBed.FACING).map(enumdirection -> blockposition.shift(enumdirection.opposite())).orElseGet(() -> new BlockPosition(blockposition));
/*     */ 
/*     */           
/* 432 */           return !g();
/*     */         } 
/*     */       } 
/*     */       
/* 436 */       return false;
/*     */     }
/*     */     
/*     */     private boolean g() {
/*     */       EntityCat entitycat;
/* 441 */       List<EntityCat> list = this.a.world.a(EntityCat.class, (new AxisAlignedBB(this.c)).g(2.0D));
/* 442 */       Iterator<EntityCat> iterator = list.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 448 */         if (!iterator.hasNext()) {
/* 449 */           return false;
/*     */         }
/*     */         
/* 452 */         entitycat = iterator.next();
/* 453 */       } while (entitycat == this.a || (
/* 454 */         !entitycat.eW() && !entitycat.eX()));
/*     */       
/* 456 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 461 */       return (this.a.isTamed() && !this.a.isWillSit() && this.b != null && this.b.isSleeping() && this.c != null && !g());
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 466 */       if (this.c != null) {
/* 467 */         this.a.setSitting(false);
/* 468 */         this.a.getNavigation().a(this.c.getX(), this.c.getY(), this.c.getZ(), 1.100000023841858D);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void d() {
/* 475 */       this.a.x(false);
/* 476 */       float f = this.a.world.f(1.0F);
/*     */       
/* 478 */       if (this.b.eB() >= 100 && f > 0.77D && f < 0.8D && this.a.world.getRandom().nextFloat() < 0.7D) {
/* 479 */         h();
/*     */       }
/*     */       
/* 482 */       this.d = 0;
/* 483 */       this.a.y(false);
/* 484 */       this.a.getNavigation().o();
/*     */     }
/*     */     
/*     */     private void h() {
/* 488 */       Random random = this.a.getRandom();
/* 489 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */       
/* 491 */       blockposition_mutableblockposition.g(this.a.getChunkCoordinates());
/* 492 */       this.a.a((blockposition_mutableblockposition.getX() + random.nextInt(11) - 5), (blockposition_mutableblockposition.getY() + random.nextInt(5) - 2), (blockposition_mutableblockposition.getZ() + random.nextInt(11) - 5), false);
/* 493 */       blockposition_mutableblockposition.g(this.a.getChunkCoordinates());
/* 494 */       LootTable loottable = this.a.world.getMinecraftServer().getLootTableRegistry().getLootTable(LootTables.ak);
/* 495 */       LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder((WorldServer)this.a.world)).<Vec3D>set(LootContextParameters.ORIGIN, this.a.getPositionVector()).<Entity>set(LootContextParameters.THIS_ENTITY, this.a).a(random);
/* 496 */       List<ItemStack> list = loottable.populateLoot(loottableinfo_builder.build(LootContextParameterSets.GIFT));
/* 497 */       Iterator<ItemStack> iterator = list.iterator();
/*     */       
/* 499 */       while (iterator.hasNext()) {
/* 500 */         ItemStack itemstack = iterator.next();
/*     */         
/* 502 */         this.a.world.addEntity(new EntityItem(this.a.world, blockposition_mutableblockposition.getX() - MathHelper.sin(this.a.aA * 0.017453292F), blockposition_mutableblockposition.getY(), blockposition_mutableblockposition.getZ() + MathHelper.cos(this.a.aA * 0.017453292F), itemstack));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 509 */       if (this.b != null && this.c != null) {
/* 510 */         this.a.setSitting(false);
/* 511 */         this.a.getNavigation().a(this.c.getX(), this.c.getY(), this.c.getZ(), 1.100000023841858D);
/* 512 */         if (this.a.h(this.b) < 2.5D) {
/* 513 */           this.d++;
/* 514 */           if (this.d > 16) {
/* 515 */             this.a.x(true);
/* 516 */             this.a.y(false);
/*     */           } else {
/* 518 */             this.a.a(this.b, 45.0F, 45.0F);
/* 519 */             this.a.y(true);
/*     */           } 
/*     */         } else {
/* 522 */           this.a.x(false);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalTemptChance
/*     */     extends PathfinderGoalTempt
/*     */   {
/*     */     @Nullable
/*     */     private EntityLiving chosenTarget;
/*     */     private final EntityCat d;
/*     */     
/*     */     public PathfinderGoalTemptChance(EntityCat entitycat, double d0, RecipeItemStack recipeitemstack, boolean flag) {
/* 536 */       super(entitycat, d0, recipeitemstack, flag);
/* 537 */       this.d = entitycat;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 542 */       super.e();
/* 543 */       if (this.chosenTarget == null && this.a.getRandom().nextInt(600) == 0) {
/* 544 */         this.chosenTarget = this.target;
/* 545 */       } else if (this.a.getRandom().nextInt(500) == 0) {
/* 546 */         this.chosenTarget = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean g() {
/* 553 */       return (this.chosenTarget != null && this.chosenTarget.equals(this.target)) ? false : super.g();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 558 */       return (super.a() && !this.d.isTamed());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class a<T extends EntityLiving>
/*     */     extends PathfinderGoalAvoidTarget<T>
/*     */   {
/*     */     private final EntityCat i;
/*     */     
/*     */     public a(EntityCat entitycat, Class<T> oclass, float f, double d0, double d1) {
/* 569 */       super(entitycat, oclass, f, d0, d1, IEntitySelector.e::test);
/* 570 */       this.i = entitycat;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 575 */       return (!this.i.isTamed() && super.a());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 580 */       return (!this.i.isTamed() && super.b());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityCat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */