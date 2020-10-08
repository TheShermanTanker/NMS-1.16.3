/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityDrowned
/*     */   extends EntityZombie implements IRangedEntity {
/*     */   private boolean d;
/*     */   protected final NavigationGuardian navigationWater;
/*     */   protected final Navigation navigationLand;
/*     */   
/*     */   public EntityDrowned(EntityTypes<? extends EntityDrowned> entitytypes, World world) {
/*  16 */     super((EntityTypes)entitytypes, world);
/*  17 */     this.G = 1.0F;
/*  18 */     this.moveController = new d(this);
/*  19 */     a(PathType.WATER, 0.0F);
/*  20 */     this.navigationWater = new NavigationGuardian(this, world);
/*  21 */     this.navigationLand = new Navigation(this, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m() {
/*  26 */     this.goalSelector.a(1, new c(this, 1.0D));
/*  27 */     this.goalSelector.a(2, new f(this, 1.0D, 40, 10.0F));
/*  28 */     this.goalSelector.a(2, new a(this, 1.0D, false));
/*  29 */     this.goalSelector.a(5, new b(this, 1.0D));
/*  30 */     this.goalSelector.a(6, new e(this, 1.0D, this.world.getSeaLevel()));
/*  31 */     this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
/*  32 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[] { EntityDrowned.class })).a(new Class[] { EntityPigZombie.class }));
/*  33 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, 10, true, false, this::i));
/*  34 */     if (this.world.spigotConfig.zombieAggressiveTowardsVillager) this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillagerAbstract.class, false)); 
/*  35 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*  36 */     this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget<>(this, EntityTurtle.class, 10, true, false, EntityTurtle.bo));
/*     */   }
/*     */ 
/*     */   
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  41 */     groupdataentity = super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*  42 */     if (getEquipment(EnumItemSlot.OFFHAND).isEmpty() && this.random.nextFloat() < 0.03F) {
/*  43 */       setSlot(EnumItemSlot.OFFHAND, new ItemStack(Items.NAUTILUS_SHELL));
/*  44 */       this.dropChanceHand[EnumItemSlot.OFFHAND.b()] = 2.0F;
/*     */     } 
/*     */     
/*  47 */     return groupdataentity;
/*     */   }
/*     */   
/*     */   public static boolean a(EntityTypes<EntityDrowned> entitytypes, WorldAccess worldaccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  51 */     Optional<ResourceKey<BiomeBase>> optional = worldaccess.i(blockposition);
/*  52 */     boolean flag = (worldaccess.getDifficulty() != EnumDifficulty.PEACEFUL && a(worldaccess, blockposition, random) && (enummobspawn == EnumMobSpawn.SPAWNER || worldaccess.getFluid(blockposition).a(TagsFluid.WATER)));
/*     */     
/*  54 */     return (!Objects.equals(optional, Optional.of(Biomes.RIVER)) && !Objects.equals(optional, Optional.of(Biomes.FROZEN_RIVER))) ? ((random.nextInt(40) == 0 && a(worldaccess, blockposition) && flag)) : ((random.nextInt(15) == 0 && flag));
/*     */   }
/*     */   
/*     */   private static boolean a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/*  58 */     return (blockposition.getY() < generatoraccess.getSeaLevel() - 5);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean eK() {
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  68 */     return isInWater() ? SoundEffects.ENTITY_DROWNED_AMBIENT_WATER : SoundEffects.ENTITY_DROWNED_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  73 */     return isInWater() ? SoundEffects.ENTITY_DROWNED_HURT_WATER : SoundEffects.ENTITY_DROWNED_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  78 */     return isInWater() ? SoundEffects.ENTITY_DROWNED_DEATH_WATER : SoundEffects.ENTITY_DROWNED_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundStep() {
/*  83 */     return SoundEffects.ENTITY_DROWNED_STEP;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSwim() {
/*  88 */     return SoundEffects.ENTITY_DROWNED_SWIM;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack eM() {
/*  93 */     return ItemStack.b;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/*  98 */     if (this.random.nextFloat() > 0.9D) {
/*  99 */       int i = this.random.nextInt(16);
/*     */       
/* 101 */       if (i < 10) {
/* 102 */         setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.TRIDENT));
/*     */       } else {
/* 104 */         setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.FISHING_ROD));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 112 */     return (itemstack1.getItem() == Items.NAUTILUS_SHELL) ? false : ((itemstack1.getItem() == Items.TRIDENT) ? ((itemstack.getItem() == Items.TRIDENT) ? ((itemstack.getDamage() < itemstack1.getDamage())) : false) : ((itemstack.getItem() == Items.TRIDENT) ? true : super.a(itemstack, itemstack1)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean eN() {
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader iworldreader) {
/* 122 */     return iworldreader.j(this);
/*     */   }
/*     */   
/*     */   public boolean i(@Nullable EntityLiving entityliving) {
/* 126 */     return (entityliving != null) ? ((!this.world.isDay() || entityliving.isInWater())) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bU() {
/* 131 */     return !isSwimming();
/*     */   }
/*     */   
/*     */   private boolean eW() {
/* 135 */     if (this.d) {
/* 136 */       return true;
/*     */     }
/* 138 */     EntityLiving entityliving = getGoalTarget();
/*     */     
/* 140 */     return (entityliving != null && entityliving.isInWater());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(Vec3D vec3d) {
/* 146 */     if (doAITick() && isInWater() && eW()) {
/* 147 */       a(0.01F, vec3d);
/* 148 */       move(EnumMoveType.SELF, getMot());
/* 149 */       setMot(getMot().a(0.9D));
/*     */     } else {
/* 151 */       super.g(vec3d);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aI() {
/* 158 */     if (!this.world.isClientSide) {
/* 159 */       if (doAITick() && isInWater() && eW()) {
/* 160 */         this.navigation = this.navigationWater;
/* 161 */         setSwimming(true);
/*     */       } else {
/* 163 */         this.navigation = this.navigationLand;
/* 164 */         setSwimming(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean eO() {
/* 171 */     PathEntity pathentity = getNavigation().k();
/*     */     
/* 173 */     if (pathentity != null) {
/* 174 */       BlockPosition blockposition = pathentity.m();
/*     */       
/* 176 */       if (blockposition != null) {
/* 177 */         double d0 = h(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */         
/* 179 */         if (d0 < 4.0D) {
/* 180 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 190 */     EntityThrownTrident entitythrowntrident = new EntityThrownTrident(this.world, this, new ItemStack(Items.TRIDENT));
/* 191 */     double d0 = entityliving.locX() - locX();
/* 192 */     double d1 = entityliving.e(0.3333333333333333D) - entitythrowntrident.locY();
/* 193 */     double d2 = entityliving.locZ() - locZ();
/* 194 */     double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*     */     
/* 196 */     entitythrowntrident.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - this.world.getDifficulty().a() * 4));
/* 197 */     playSound(SoundEffects.ENTITY_DROWNED_SHOOT, 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
/* 198 */     this.world.addEntity(entitythrowntrident);
/*     */   }
/*     */   
/*     */   public void t(boolean flag) {
/* 202 */     this.d = flag;
/*     */   }
/*     */   
/*     */   static class d
/*     */     extends ControllerMove {
/*     */     private final EntityDrowned i;
/*     */     
/*     */     public d(EntityDrowned entitydrowned) {
/* 210 */       super(entitydrowned);
/* 211 */       this.i = entitydrowned;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 216 */       EntityLiving entityliving = this.i.getGoalTarget();
/*     */       
/* 218 */       if (this.i.eW() && this.i.isInWater()) {
/* 219 */         if ((entityliving != null && entityliving.locY() > this.i.locY()) || this.i.d) {
/* 220 */           this.i.setMot(this.i.getMot().add(0.0D, 0.002D, 0.0D));
/*     */         }
/*     */         
/* 223 */         if (this.h != ControllerMove.Operation.MOVE_TO || this.i.getNavigation().m()) {
/* 224 */           this.i.q(0.0F);
/*     */           
/*     */           return;
/*     */         } 
/* 228 */         double d0 = this.b - this.i.locX();
/* 229 */         double d1 = this.c - this.i.locY();
/* 230 */         double d2 = this.d - this.i.locZ();
/* 231 */         double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */         
/* 233 */         d1 /= d3;
/* 234 */         float f = (float)(MathHelper.d(d2, d0) * 57.2957763671875D) - 90.0F;
/*     */         
/* 236 */         this.i.yaw = a(this.i.yaw, f, 90.0F);
/* 237 */         this.i.aA = this.i.yaw;
/* 238 */         float f1 = (float)(this.e * this.i.b(GenericAttributes.MOVEMENT_SPEED));
/* 239 */         float f2 = MathHelper.g(0.125F, this.i.dM(), f1);
/*     */         
/* 241 */         this.i.q(f2);
/* 242 */         this.i.setMot(this.i.getMot().add(f2 * d0 * 0.005D, f2 * d1 * 0.1D, f2 * d2 * 0.005D));
/*     */       } else {
/* 244 */         if (!this.i.onGround) {
/* 245 */           this.i.setMot(this.i.getMot().add(0.0D, -0.008D, 0.0D));
/*     */         }
/*     */         
/* 248 */         super.a();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends PathfinderGoalZombieAttack
/*     */   {
/*     */     private final EntityDrowned b;
/*     */     
/*     */     public a(EntityDrowned entitydrowned, double d0, boolean flag) {
/* 259 */       super(entitydrowned, d0, flag);
/* 260 */       this.b = entitydrowned;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 265 */       return (super.a() && this.b.i(this.b.getGoalTarget()));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 270 */       return (super.b() && this.b.i(this.b.getGoalTarget()));
/*     */     }
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends PathfinderGoal {
/*     */     private final EntityCreature a;
/*     */     private double b;
/*     */     private double c;
/*     */     private double d;
/*     */     private final double e;
/*     */     private final World f;
/*     */     
/*     */     public c(EntityCreature entitycreature, double d0) {
/* 284 */       this.a = entitycreature;
/* 285 */       this.e = d0;
/* 286 */       this.f = entitycreature.world;
/* 287 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 292 */       if (!this.f.isDay())
/* 293 */         return false; 
/* 294 */       if (this.a.isInWater()) {
/* 295 */         return false;
/*     */       }
/* 297 */       Vec3D vec3d = g();
/*     */       
/* 299 */       if (vec3d == null) {
/* 300 */         return false;
/*     */       }
/* 302 */       this.b = vec3d.x;
/* 303 */       this.c = vec3d.y;
/* 304 */       this.d = vec3d.z;
/* 305 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 312 */       return !this.a.getNavigation().m();
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 317 */       this.a.getNavigation().a(this.b, this.c, this.d, this.e);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private Vec3D g() {
/* 322 */       Random random = this.a.getRandom();
/* 323 */       BlockPosition blockposition = this.a.getChunkCoordinates();
/*     */       
/* 325 */       for (int i = 0; i < 10; i++) {
/* 326 */         BlockPosition blockposition1 = blockposition.b(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
/*     */         
/* 328 */         if (this.f.getType(blockposition1).a(Blocks.WATER)) {
/* 329 */           return Vec3D.c(blockposition1);
/*     */         }
/*     */       } 
/*     */       
/* 333 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends PathfinderGoalGotoTarget {
/*     */     private final EntityDrowned g;
/*     */     
/*     */     public b(EntityDrowned entitydrowned, double d0) {
/* 342 */       super(entitydrowned, d0, 8, 2);
/* 343 */       this.g = entitydrowned;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 348 */       return (super.a() && !this.g.world.isDay() && this.g.isInWater() && this.g.locY() >= (this.g.world.getSeaLevel() - 3));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 353 */       return super.b();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/* 358 */       BlockPosition blockposition1 = blockposition.up();
/*     */       
/* 360 */       return (iworldreader.isEmpty(blockposition1) && iworldreader.isEmpty(blockposition1.up())) ? iworldreader.getType(blockposition).a(iworldreader, blockposition, this.g) : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 365 */       this.g.t(false);
/* 366 */       this.g.navigation = this.g.navigationLand;
/* 367 */       super.c();
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 372 */       super.d();
/*     */     }
/*     */   }
/*     */   
/*     */   static class e
/*     */     extends PathfinderGoal {
/*     */     private final EntityDrowned a;
/*     */     private final double b;
/*     */     private final int c;
/*     */     private boolean d;
/*     */     
/*     */     public e(EntityDrowned entitydrowned, double d0, int i) {
/* 384 */       this.a = entitydrowned;
/* 385 */       this.b = d0;
/* 386 */       this.c = i;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 391 */       return (!this.a.world.isDay() && this.a.isInWater() && this.a.locY() < (this.c - 2));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 396 */       return (a() && !this.d);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 401 */       if (this.a.locY() < (this.c - 1) && (this.a.getNavigation().m() || this.a.eO())) {
/* 402 */         Vec3D vec3d = RandomPositionGenerator.b(this.a, 4, 8, new Vec3D(this.a.locX(), (this.c - 1), this.a.locZ()));
/*     */         
/* 404 */         if (vec3d == null) {
/* 405 */           this.d = true;
/*     */           
/*     */           return;
/*     */         } 
/* 409 */         this.a.getNavigation().a(vec3d.x, vec3d.y, vec3d.z, this.b);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void c() {
/* 416 */       this.a.t(true);
/* 417 */       this.d = false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 422 */       this.a.t(false);
/*     */     }
/*     */   }
/*     */   
/*     */   static class f
/*     */     extends PathfinderGoalArrowAttack {
/*     */     private final EntityDrowned a;
/*     */     
/*     */     public f(IRangedEntity irangedentity, double d0, int i, float f1) {
/* 431 */       super(irangedentity, d0, i, f1);
/* 432 */       this.a = (EntityDrowned)irangedentity;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 437 */       return (super.a() && this.a.getItemInMainHand().getItem() == Items.TRIDENT);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 442 */       super.c();
/* 443 */       this.a.setAggressive(true);
/* 444 */       this.a.c(EnumHand.MAIN_HAND);
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 449 */       super.d();
/* 450 */       this.a.clearActiveItem();
/* 451 */       this.a.setAggressive(false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityDrowned.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */