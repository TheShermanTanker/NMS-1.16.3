/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.TurtleLayEggEvent;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Turtle;
/*     */ 
/*     */ public class EntityTurtle extends EntityAnimal {
/*  12 */   private static final DataWatcherObject<BlockPosition> bp = DataWatcher.a((Class)EntityTurtle.class, DataWatcherRegistry.l);
/*  13 */   private static final DataWatcherObject<Boolean> bq = DataWatcher.a((Class)EntityTurtle.class, DataWatcherRegistry.i);
/*  14 */   private static final DataWatcherObject<Boolean> br = DataWatcher.a((Class)EntityTurtle.class, DataWatcherRegistry.i);
/*  15 */   private static final DataWatcherObject<BlockPosition> bs = DataWatcher.a((Class)EntityTurtle.class, DataWatcherRegistry.l);
/*  16 */   private static final DataWatcherObject<Boolean> bt = DataWatcher.a((Class)EntityTurtle.class, DataWatcherRegistry.i);
/*  17 */   private static final DataWatcherObject<Boolean> bu = DataWatcher.a((Class)EntityTurtle.class, DataWatcherRegistry.i); private int bv; public static final Predicate<EntityLiving> bo;
/*     */   static {
/*  19 */     bo = (entityliving -> 
/*  20 */       (entityliving.isBaby() && !entityliving.isInWater()));
/*     */   }
/*     */   
/*     */   public EntityTurtle(EntityTypes<? extends EntityTurtle> entitytypes, World world) {
/*  24 */     super((EntityTypes)entitytypes, world);
/*  25 */     a(PathType.WATER, 0.0F);
/*  26 */     this.moveController = new e(this);
/*  27 */     this.G = 1.0F;
/*     */   }
/*     */   
/*     */   public void setHomePos(BlockPosition blockposition) {
/*  31 */     this.datawatcher.set(bp, blockposition.immutableCopy());
/*     */   }
/*     */   
/*     */   public BlockPosition getHomePos() {
/*  35 */     return this.datawatcher.<BlockPosition>get(bp);
/*     */   }
/*     */   
/*     */   private void setTravelPos(BlockPosition blockposition) {
/*  39 */     this.datawatcher.set(bs, blockposition);
/*     */   }
/*     */   
/*     */   private BlockPosition getTravelPos() {
/*  43 */     return this.datawatcher.<BlockPosition>get(bs);
/*     */   }
/*     */   
/*     */   public boolean hasEgg() {
/*  47 */     return ((Boolean)this.datawatcher.<Boolean>get(bq)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setHasEgg(boolean flag) {
/*  51 */     this.datawatcher.set(bq, Boolean.valueOf(flag));
/*     */   }
/*     */   public final boolean isDigging() {
/*  54 */     return eL();
/*     */   } public boolean eL() {
/*  56 */     return ((Boolean)this.datawatcher.<Boolean>get(br)).booleanValue();
/*     */   }
/*     */   public final void setDigging(boolean digging) {
/*  59 */     u(digging);
/*     */   } private void u(boolean flag) {
/*  61 */     this.bv = flag ? 1 : 0;
/*  62 */     this.datawatcher.set(br, Boolean.valueOf(flag));
/*     */   }
/*     */   public final boolean isGoingHome() {
/*  65 */     return eU();
/*     */   } private boolean eU() {
/*  67 */     return ((Boolean)this.datawatcher.<Boolean>get(bt)).booleanValue();
/*     */   }
/*     */   public final void setGoingHome(boolean goingHome) {
/*  70 */     v(goingHome);
/*     */   } private void v(boolean flag) {
/*  72 */     this.datawatcher.set(bt, Boolean.valueOf(flag));
/*     */   }
/*     */   public final boolean isTravelling() {
/*  75 */     return eV();
/*     */   } private boolean eV() {
/*  77 */     return ((Boolean)this.datawatcher.<Boolean>get(bu)).booleanValue();
/*     */   }
/*     */   public final void setTravelling(boolean travelling) {
/*  80 */     w(travelling);
/*     */   } private void w(boolean flag) {
/*  82 */     this.datawatcher.set(bu, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  87 */     super.initDatawatcher();
/*  88 */     this.datawatcher.register(bp, BlockPosition.ZERO);
/*  89 */     this.datawatcher.register(bq, Boolean.valueOf(false));
/*  90 */     this.datawatcher.register(bs, BlockPosition.ZERO);
/*  91 */     this.datawatcher.register(bt, Boolean.valueOf(false));
/*  92 */     this.datawatcher.register(bu, Boolean.valueOf(false));
/*  93 */     this.datawatcher.register(br, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  98 */     super.saveData(nbttagcompound);
/*  99 */     nbttagcompound.setInt("HomePosX", getHomePos().getX());
/* 100 */     nbttagcompound.setInt("HomePosY", getHomePos().getY());
/* 101 */     nbttagcompound.setInt("HomePosZ", getHomePos().getZ());
/* 102 */     nbttagcompound.setBoolean("HasEgg", hasEgg());
/* 103 */     nbttagcompound.setInt("TravelPosX", getTravelPos().getX());
/* 104 */     nbttagcompound.setInt("TravelPosY", getTravelPos().getY());
/* 105 */     nbttagcompound.setInt("TravelPosZ", getTravelPos().getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 110 */     int i = nbttagcompound.getInt("HomePosX");
/* 111 */     int j = nbttagcompound.getInt("HomePosY");
/* 112 */     int k = nbttagcompound.getInt("HomePosZ");
/*     */     
/* 114 */     setHomePos(new BlockPosition(i, j, k));
/* 115 */     super.loadData(nbttagcompound);
/* 116 */     setHasEgg(nbttagcompound.getBoolean("HasEgg"));
/* 117 */     int l = nbttagcompound.getInt("TravelPosX");
/* 118 */     int i1 = nbttagcompound.getInt("TravelPosY");
/* 119 */     int j1 = nbttagcompound.getInt("TravelPosZ");
/*     */     
/* 121 */     setTravelPos(new BlockPosition(l, i1, j1));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 127 */     setHomePos(getChunkCoordinates());
/* 128 */     setTravelPos(BlockPosition.ZERO);
/* 129 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<EntityTurtle> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 133 */     return (blockposition.getY() < generatoraccess.getSeaLevel() + 4 && BlockTurtleEgg.a(generatoraccess, blockposition) && generatoraccess.getLightLevel(blockposition, 0) > 8);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/* 138 */     this.goalSelector.a(0, new f(this, 1.2D));
/* 139 */     this.goalSelector.a(1, new a(this, 1.0D));
/* 140 */     this.goalSelector.a(1, new d(this, 1.0D));
/* 141 */     this.goalSelector.a(2, new i(this, 1.1D, Blocks.SEAGRASS.getItem()));
/* 142 */     this.goalSelector.a(3, new c(this, 1.0D));
/* 143 */     this.goalSelector.a(4, new b(this, 1.0D));
/* 144 */     this.goalSelector.a(7, new j(this, 1.0D));
/* 145 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/* 146 */     this.goalSelector.a(9, new h(this, 1.0D, 100));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eM() {
/* 150 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 30.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bU() {
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cL() {
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 165 */     return EnumMonsterType.WATER_MOB;
/*     */   }
/*     */ 
/*     */   
/*     */   public int D() {
/* 170 */     return 200;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAmbient() {
/* 176 */     return (!isInWater() && this.onGround && !isBaby()) ? SoundEffects.ENTITY_TURTLE_AMBIENT_LAND : super.getSoundAmbient();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void d(float f) {
/* 181 */     super.d(f * 1.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSwim() {
/* 186 */     return SoundEffects.ENTITY_TURTLE_SWIM;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 192 */     return isBaby() ? SoundEffects.ENTITY_TURTLE_HURT_BABY : SoundEffects.ENTITY_TURTLE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundDeath() {
/* 198 */     return isBaby() ? SoundEffects.ENTITY_TURTLE_DEATH_BABY : SoundEffects.ENTITY_TURTLE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 203 */     SoundEffect soundeffect = isBaby() ? SoundEffects.ENTITY_TURTLE_SHAMBLE_BABY : SoundEffects.ENTITY_TURTLE_SHAMBLE;
/*     */     
/* 205 */     playSound(soundeffect, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eP() {
/* 210 */     return (super.eP() && !hasEgg());
/*     */   }
/*     */ 
/*     */   
/*     */   protected float as() {
/* 215 */     return this.B + 0.15F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float cR() {
/* 220 */     return isBaby() ? 0.3F : 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World world) {
/* 225 */     return new g(this, world);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 231 */     return EntityTypes.TURTLE.a(worldserver);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 236 */     return (itemstack.getItem() == Blocks.SEAGRASS.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition blockposition, IWorldReader iworldreader) {
/* 241 */     return (!eU() && iworldreader.getFluid(blockposition).a(TagsFluid.WATER)) ? 10.0F : (BlockTurtleEgg.a(iworldreader, blockposition) ? 10.0F : (iworldreader.y(blockposition) - 0.5F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 246 */     super.movementTick();
/* 247 */     if (isAlive() && eL() && this.bv >= 1 && this.bv % 5 == 0) {
/* 248 */       BlockPosition blockposition = getChunkCoordinates();
/*     */       
/* 250 */       if (BlockTurtleEgg.a(this.world, blockposition)) {
/* 251 */         this.world.triggerEffect(2001, blockposition, Block.getCombinedId(Blocks.SAND.getBlockData()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m() {
/* 259 */     super.m();
/* 260 */     if (!isBaby() && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
/* 261 */       this.forceDrops = true;
/* 262 */       a(Items.SCUTE, 1);
/* 263 */       this.forceDrops = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(Vec3D vec3d) {
/* 270 */     if (doAITick() && isInWater()) {
/* 271 */       a(0.1F, vec3d);
/* 272 */       move(EnumMoveType.SELF, getMot());
/* 273 */       setMot(getMot().a(0.9D));
/* 274 */       if (getGoalTarget() == null && (!eU() || !getHomePos().a(getPositionVector(), 20.0D))) {
/* 275 */         setMot(getMot().add(0.0D, -0.005D, 0.0D));
/*     */       }
/*     */     } else {
/* 278 */       super.g(vec3d);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 285 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLightningStrike(WorldServer worldserver, EntityLightning entitylightning) {
/* 290 */     CraftEventFactory.entityDamage = entitylightning;
/* 291 */     damageEntity(DamageSource.LIGHTNING, Float.MAX_VALUE);
/* 292 */     CraftEventFactory.entityDamage = null;
/*     */   }
/*     */   
/*     */   static class g
/*     */     extends NavigationGuardian {
/*     */     g(EntityTurtle entityturtle, World world) {
/* 298 */       super(entityturtle, world);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a() {
/* 303 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Pathfinder a(int i) {
/* 308 */       this.o = new PathfinderTurtle();
/* 309 */       return new Pathfinder(this.o, i);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(BlockPosition blockposition) {
/* 314 */       if (this.a instanceof EntityTurtle) {
/* 315 */         EntityTurtle entityturtle = (EntityTurtle)this.a;
/*     */         
/* 317 */         if (entityturtle.eV()) {
/* 318 */           return this.b.getType(blockposition).a(Blocks.WATER);
/*     */         }
/*     */       } 
/*     */       
/* 322 */       return !this.b.getType(blockposition.down()).isAir();
/*     */     }
/*     */   }
/*     */   
/*     */   static class e
/*     */     extends ControllerMove {
/*     */     private final EntityTurtle i;
/*     */     
/*     */     e(EntityTurtle entityturtle) {
/* 331 */       super(entityturtle);
/* 332 */       this.i = entityturtle;
/*     */     }
/*     */     
/*     */     private void g() {
/* 336 */       if (this.i.isInWater()) {
/* 337 */         this.i.setMot(this.i.getMot().add(0.0D, 0.005D, 0.0D));
/* 338 */         if (!this.i.getHomePos().a(this.i.getPositionVector(), 16.0D)) {
/* 339 */           this.i.q(Math.max(this.i.dM() / 2.0F, 0.08F));
/*     */         }
/*     */         
/* 342 */         if (this.i.isBaby()) {
/* 343 */           this.i.q(Math.max(this.i.dM() / 3.0F, 0.06F));
/*     */         }
/* 345 */       } else if (this.i.onGround) {
/* 346 */         this.i.q(Math.max(this.i.dM() / 2.0F, 0.06F));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void a() {
/* 353 */       g();
/* 354 */       if (this.h == ControllerMove.Operation.MOVE_TO && !this.i.getNavigation().m()) {
/* 355 */         double d0 = this.b - this.i.locX();
/* 356 */         double d1 = this.c - this.i.locY();
/* 357 */         double d2 = this.d - this.i.locZ();
/* 358 */         double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */         
/* 360 */         d1 /= d3;
/* 361 */         float f = (float)(MathHelper.d(d2, d0) * 57.2957763671875D) - 90.0F;
/*     */         
/* 363 */         this.i.yaw = a(this.i.yaw, f, 90.0F);
/* 364 */         this.i.aA = this.i.yaw;
/* 365 */         float f1 = (float)(this.e * this.i.b(GenericAttributes.MOVEMENT_SPEED));
/*     */         
/* 367 */         this.i.q(MathHelper.g(0.125F, this.i.dM(), f1));
/* 368 */         this.i.setMot(this.i.getMot().add(0.0D, this.i.dM() * d1 * 0.1D, 0.0D));
/*     */       } else {
/* 370 */         this.i.q(0.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends PathfinderGoalGotoTarget {
/*     */     private final EntityTurtle g;
/*     */     
/*     */     private c(EntityTurtle entityturtle, double d0) {
/* 380 */       super(entityturtle, entityturtle.isBaby() ? 2.0D : d0, 24);
/* 381 */       this.g = entityturtle;
/* 382 */       this.f = -1;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 387 */       return (!this.g.isInWater() && this.d <= 1200 && a(this.g.world, this.e));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 392 */       return (this.g.isBaby() && !this.g.isInWater()) ? super.a() : ((!this.g.eU() && !this.g.isInWater() && !this.g.hasEgg()) ? super.a() : false);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean k() {
/* 397 */       return (this.d % 160 == 0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/* 402 */       return iworldreader.getType(blockposition).a(Blocks.WATER);
/*     */     }
/*     */   }
/*     */   
/*     */   static class h
/*     */     extends PathfinderGoalRandomStroll {
/*     */     private final EntityTurtle h;
/*     */     
/*     */     private h(EntityTurtle entityturtle, double d0, int i) {
/* 411 */       super(entityturtle, d0, i);
/* 412 */       this.h = entityturtle;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 417 */       return (!this.a.isInWater() && !this.h.eU() && !this.h.hasEgg()) ? super.a() : false;
/*     */     }
/*     */   }
/*     */   
/*     */   static class d
/*     */     extends PathfinderGoalGotoTarget {
/*     */     private final EntityTurtle g;
/*     */     
/*     */     d(EntityTurtle entityturtle, double d0) {
/* 426 */       super(entityturtle, d0, 16);
/* 427 */       this.g = entityturtle;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 432 */       return (this.g.hasEgg() && this.g.getHomePos().a(this.g.getPositionVector(), 9.0D)) ? super.a() : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 437 */       return (super.b() && this.g.hasEgg() && this.g.getHomePos().a(this.g.getPositionVector(), 9.0D));
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 442 */       super.e();
/* 443 */       BlockPosition blockposition = this.g.getChunkCoordinates();
/*     */       
/* 445 */       if (!this.g.isInWater() && l()) {
/* 446 */         if (this.g.bv < 1) {
/* 447 */           this.g.setDigging((new TurtleStartDiggingEvent((Turtle)this.g.getBukkitEntity(), MCUtil.toLocation(this.g.world, getTargetPosition()))).callEvent());
/* 448 */         } else if (this.g.bv > 200) {
/* 449 */           World world = this.g.world;
/*     */ 
/*     */ 
/*     */           
/* 453 */           int eggCount = this.g.random.nextInt(4) + 1;
/* 454 */           TurtleLayEggEvent layEggEvent = new TurtleLayEggEvent((Turtle)this.g.getBukkitEntity(), MCUtil.toLocation(this.g.world, this.e.up()), eggCount);
/* 455 */           if (layEggEvent.callEvent() && !CraftEventFactory.callEntityChangeBlockEvent(this.g, this.e.up(), Blocks.TURTLE_EGG.getBlockData().set(BlockTurtleEgg.b, Integer.valueOf(layEggEvent.getEggCount()))).isCancelled()) {
/* 456 */             world.playSound((EntityHuman)null, blockposition, SoundEffects.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
/* 457 */             world.setTypeAndData(this.e.up(), Blocks.TURTLE_EGG.getBlockData().set(BlockTurtleEgg.b, Integer.valueOf(layEggEvent.getEggCount())), 3);
/*     */           } 
/*     */           
/* 460 */           this.g.setHasEgg(false);
/* 461 */           this.g.u(false);
/* 462 */           this.g.setLoveTicks(600);
/*     */         } 
/*     */         
/* 465 */         if (this.g.eL()) {
/* 466 */           this.g.bv++;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/* 474 */       return !iworldreader.isEmpty(blockposition.up()) ? false : BlockTurtleEgg.b(iworldreader, blockposition);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends PathfinderGoalBreed {
/*     */     private final EntityTurtle d;
/*     */     
/*     */     a(EntityTurtle entityturtle, double d0) {
/* 483 */       super(entityturtle, d0);
/* 484 */       this.d = entityturtle;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 489 */       return (super.a() && !this.d.hasEgg());
/*     */     }
/*     */ 
/*     */     
/*     */     protected void g() {
/* 494 */       EntityPlayer entityplayer = this.animal.getBreedCause();
/*     */       
/* 496 */       if (entityplayer == null && this.partner.getBreedCause() != null) {
/* 497 */         entityplayer = this.partner.getBreedCause();
/*     */       }
/*     */       
/* 500 */       if (entityplayer != null) {
/* 501 */         entityplayer.a(StatisticList.ANIMALS_BRED);
/* 502 */         CriterionTriggers.o.a(entityplayer, this.animal, this.partner, (EntityAgeable)null);
/*     */       } 
/*     */       
/* 505 */       this.d.setHasEgg(true);
/* 506 */       this.animal.resetLove();
/* 507 */       this.partner.resetLove();
/* 508 */       Random random = this.animal.getRandom();
/*     */       
/* 510 */       if (this.b.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
/* 511 */         this.b.addEntity(new EntityExperienceOrb(this.b, this.animal.locX(), this.animal.locY(), this.animal.locZ(), random.nextInt(7) + 1, ExperienceOrb.SpawnReason.BREED, entityplayer));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   static class i
/*     */     extends PathfinderGoal
/*     */   {
/* 519 */     private static final PathfinderTargetCondition a = (new PathfinderTargetCondition()).a(10.0D).b().a();
/*     */     private final EntityTurtle b;
/*     */     private final double c;
/*     */     private EntityHuman d;
/*     */     private int e;
/*     */     private final Set<Item> f;
/*     */     
/*     */     i(EntityTurtle entityturtle, double d0, Item item) {
/* 527 */       this.b = entityturtle;
/* 528 */       this.c = d0;
/* 529 */       this.f = Sets.newHashSet((Object[])new Item[] { item });
/* 530 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 535 */       if (this.e > 0) {
/* 536 */         this.e--;
/* 537 */         return false;
/*     */       } 
/* 539 */       this; this.d = this.b.world.a(a, this.b);
/* 540 */       return (this.d == null) ? false : ((a(this.d.getItemInMainHand()) || a(this.d.getItemInOffHand())));
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean a(ItemStack itemstack) {
/* 545 */       return this.f.contains(itemstack.getItem());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 550 */       return a();
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 555 */       this.d = null;
/* 556 */       this.b.getNavigation().o();
/* 557 */       this.e = 100;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 562 */       this.b.getControllerLook().a(this.d, (this.b.eo() + 20), this.b.O());
/* 563 */       if (this.b.h(this.d) < 6.25D) {
/* 564 */         this.b.getNavigation().o();
/*     */       } else {
/* 566 */         this.b.getNavigation().a(this.d, this.c);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityTurtle a;
/*     */     private final double b;
/*     */     private boolean c;
/*     */     private int d;
/*     */     
/*     */     b(EntityTurtle entityturtle, double d0) {
/* 580 */       this.a = entityturtle;
/* 581 */       this.b = d0;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 586 */       return this.a.isBaby() ? false : (((this.a.hasEgg() || (this.a.getRandom().nextInt(700) == 0 && !this.a.getHomePos().a(this.a.getPositionVector(), 64.0D))) && (new TurtleGoHomeEvent((Turtle)this.a.getBukkitEntity())).callEvent()));
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 591 */       this.a.v(true);
/* 592 */       this.c = false;
/* 593 */       this.d = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 598 */       this.a.v(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 603 */       return (!this.a.getHomePos().a(this.a.getPositionVector(), 7.0D) && !this.c && this.d <= 600);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 608 */       BlockPosition blockposition = this.a.getHomePos();
/* 609 */       boolean flag = blockposition.a(this.a.getPositionVector(), 16.0D);
/*     */       
/* 611 */       if (flag) {
/* 612 */         this.d++;
/*     */       }
/*     */       
/* 615 */       if (this.a.getNavigation().m()) {
/* 616 */         Vec3D vec3d = Vec3D.c(blockposition);
/* 617 */         Vec3D vec3d1 = RandomPositionGenerator.a(this.a, 16, 3, vec3d, 0.3141592741012573D);
/*     */         
/* 619 */         if (vec3d1 == null) {
/* 620 */           vec3d1 = RandomPositionGenerator.b(this.a, 8, 7, vec3d);
/*     */         }
/*     */         
/* 623 */         if (vec3d1 != null && !flag && !this.a.world.getType(new BlockPosition(vec3d1)).a(Blocks.WATER)) {
/* 624 */           vec3d1 = RandomPositionGenerator.b(this.a, 16, 5, vec3d);
/*     */         }
/*     */         
/* 627 */         if (vec3d1 == null) {
/* 628 */           this.c = true;
/*     */           
/*     */           return;
/*     */         } 
/* 632 */         this.a.getNavigation().a(vec3d1.x, vec3d1.y, vec3d1.z, this.b);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class j
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityTurtle a;
/*     */     private final double b;
/*     */     private boolean c;
/*     */     
/*     */     j(EntityTurtle entityturtle, double d0) {
/* 645 */       this.a = entityturtle;
/* 646 */       this.b = d0;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 651 */       return (!this.a.eU() && !this.a.hasEgg() && this.a.isInWater());
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 656 */       boolean flag = true;
/* 657 */       boolean flag1 = true;
/* 658 */       Random random = this.a.random;
/* 659 */       int i = random.nextInt(1025) - 512;
/* 660 */       int m = random.nextInt(9) - 4;
/* 661 */       int k = random.nextInt(1025) - 512;
/*     */       
/* 663 */       if (m + this.a.locY() > (this.a.world.getSeaLevel() - 1)) {
/* 664 */         m = 0;
/*     */       }
/*     */       
/* 667 */       BlockPosition blockposition = new BlockPosition(i + this.a.locX(), m + this.a.locY(), k + this.a.locZ());
/*     */       
/* 669 */       this.a.setTravelPos(blockposition);
/* 670 */       this.a.w(true);
/* 671 */       this.c = false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 676 */       if (this.a.getNavigation().m()) {
/* 677 */         Vec3D vec3d = Vec3D.c(this.a.getTravelPos());
/* 678 */         Vec3D vec3d1 = RandomPositionGenerator.a(this.a, 16, 3, vec3d, 0.3141592741012573D);
/*     */         
/* 680 */         if (vec3d1 == null) {
/* 681 */           vec3d1 = RandomPositionGenerator.b(this.a, 8, 7, vec3d);
/*     */         }
/*     */         
/* 684 */         if (vec3d1 != null) {
/* 685 */           int i = MathHelper.floor(vec3d1.x);
/* 686 */           int k = MathHelper.floor(vec3d1.z);
/* 687 */           boolean flag = true;
/*     */           
/* 689 */           if (!this.a.world.isAreaLoaded(i - 34, 0, k - 34, i + 34, 0, k + 34)) {
/* 690 */             vec3d1 = null;
/*     */           }
/*     */         } 
/*     */         
/* 694 */         if (vec3d1 == null) {
/* 695 */           this.c = true;
/*     */           
/*     */           return;
/*     */         } 
/* 699 */         this.a.getNavigation().a(vec3d1.x, vec3d1.y, vec3d1.z, this.b);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 706 */       return (!this.a.getNavigation().m() && !this.c && !this.a.eU() && !this.a.isInLove() && !this.a.hasEgg());
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 711 */       this.a.w(false);
/* 712 */       super.d();
/*     */     }
/*     */   }
/*     */   
/*     */   static class f
/*     */     extends PathfinderGoalPanic {
/*     */     f(EntityTurtle entityturtle, double d0) {
/* 719 */       super(entityturtle, d0);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 724 */       if (this.a.getLastDamager() == null && !this.a.isBurning()) {
/* 725 */         return false;
/*     */       }
/* 727 */       BlockPosition blockposition = a(this.a.world, this.a, 7, 4);
/*     */       
/* 729 */       if (blockposition != null) {
/* 730 */         this.c = blockposition.getX();
/* 731 */         this.d = blockposition.getY();
/* 732 */         this.e = blockposition.getZ();
/* 733 */         return true;
/*     */       } 
/* 735 */       return g();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */