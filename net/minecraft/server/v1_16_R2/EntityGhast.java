/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class EntityGhast
/*     */   extends EntityFlying implements IMonster {
/*   8 */   private static final DataWatcherObject<Boolean> b = DataWatcher.a((Class)EntityGhast.class, DataWatcherRegistry.i);
/*   9 */   private int c = 1;
/*     */   
/*     */   public EntityGhast(EntityTypes<? extends EntityGhast> entitytypes, World world) {
/*  12 */     super((EntityTypes)entitytypes, world);
/*  13 */     this.f = 5;
/*  14 */     this.moveController = new ControllerGhast(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  19 */     this.goalSelector.a(5, new PathfinderGoalGhastIdleMove(this));
/*  20 */     this.goalSelector.a(7, new PathfinderGoalGhastMoveTowardsTarget(this));
/*  21 */     this.goalSelector.a(7, new PathfinderGoalGhastAttackTarget(this));
/*  22 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, 10, true, false, entityliving -> (Math.abs(entityliving.locY() - locY()) <= 4.0D)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void t(boolean flag) {
/*  28 */     this.datawatcher.set(b, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public int getPower() {
/*  32 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean L() {
/*  37 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  42 */     if (isInvulnerable(damagesource))
/*  43 */       return false; 
/*  44 */     if (damagesource.j() instanceof EntityLargeFireball && damagesource.getEntity() instanceof EntityHuman) {
/*  45 */       super.damageEntity(damagesource, 1000.0F);
/*  46 */       return true;
/*     */     } 
/*  48 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  54 */     super.initDatawatcher();
/*  55 */     this.datawatcher.register(b, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eJ() {
/*  59 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 10.0D).a(GenericAttributes.FOLLOW_RANGE, 100.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/*  64 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  69 */     return SoundEffects.ENTITY_GHAST_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  74 */     return SoundEffects.ENTITY_GHAST_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  79 */     return SoundEffects.ENTITY_GHAST_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/*  84 */     return 5.0F;
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<EntityGhast> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  88 */     return (generatoraccess.getDifficulty() != EnumDifficulty.PEACEFUL && random.nextInt(20) == 0 && a((EntityTypes)entitytypes, generatoraccess, enummobspawn, blockposition, random));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnGroup() {
/*  93 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  98 */     super.saveData(nbttagcompound);
/*  99 */     nbttagcompound.setInt("ExplosionPower", this.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 104 */     super.loadData(nbttagcompound);
/* 105 */     if (nbttagcompound.hasKeyOfType("ExplosionPower", 99)) {
/* 106 */       this.c = nbttagcompound.getInt("ExplosionPower");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 113 */     return 2.6F;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalGhastAttackTarget
/*     */     extends PathfinderGoal {
/*     */     private final EntityGhast ghast;
/*     */     public int a;
/*     */     
/*     */     public PathfinderGoalGhastAttackTarget(EntityGhast entityghast) {
/* 122 */       this.ghast = entityghast;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 127 */       return (this.ghast.getGoalTarget() != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 132 */       this.a = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 137 */       this.ghast.t(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 142 */       EntityLiving entityliving = this.ghast.getGoalTarget();
/* 143 */       double d0 = 64.0D;
/*     */       
/* 145 */       if (entityliving.h(this.ghast) < 4096.0D && this.ghast.hasLineOfSight(entityliving)) {
/* 146 */         World world = this.ghast.world;
/*     */         
/* 148 */         this.a++;
/* 149 */         if (this.a == 10 && !this.ghast.isSilent()) {
/* 150 */           world.a((EntityHuman)null, 1015, this.ghast.getChunkCoordinates(), 0);
/*     */         }
/*     */         
/* 153 */         if (this.a == 20) {
/* 154 */           double d1 = 4.0D;
/* 155 */           Vec3D vec3d = this.ghast.f(1.0F);
/* 156 */           double d2 = entityliving.locX() - this.ghast.locX() + vec3d.x * 4.0D;
/* 157 */           double d3 = entityliving.e(0.5D) - 0.5D + this.ghast.e(0.5D);
/* 158 */           double d4 = entityliving.locZ() - this.ghast.locZ() + vec3d.z * 4.0D;
/*     */           
/* 160 */           if (!this.ghast.isSilent()) {
/* 161 */             world.a((EntityHuman)null, 1016, this.ghast.getChunkCoordinates(), 0);
/*     */           }
/*     */           
/* 164 */           EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this.ghast, d2, d3, d4);
/*     */ 
/*     */           
/* 167 */           entitylargefireball.bukkitYield = (entitylargefireball.yield = this.ghast.getPower());
/* 168 */           entitylargefireball.setPosition(this.ghast.locX() + vec3d.x * 4.0D, this.ghast.e(0.5D) + 0.5D, entitylargefireball.locZ() + vec3d.z * 4.0D);
/* 169 */           world.addEntity(entitylargefireball);
/* 170 */           this.a = -40;
/*     */         } 
/* 172 */       } else if (this.a > 0) {
/* 173 */         this.a--;
/*     */       } 
/*     */       
/* 176 */       this.ghast.t((this.a > 10));
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalGhastMoveTowardsTarget
/*     */     extends PathfinderGoal {
/*     */     private final EntityGhast a;
/*     */     
/*     */     public PathfinderGoalGhastMoveTowardsTarget(EntityGhast entityghast) {
/* 185 */       this.a = entityghast;
/* 186 */       a(EnumSet.of(PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 191 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 196 */       if (this.a.getGoalTarget() == null) {
/* 197 */         Vec3D vec3d = this.a.getMot();
/*     */         
/* 199 */         this.a.yaw = -((float)MathHelper.d(vec3d.x, vec3d.z)) * 57.295776F;
/* 200 */         this.a.aA = this.a.yaw;
/*     */       } else {
/* 202 */         EntityLiving entityliving = this.a.getGoalTarget();
/* 203 */         double d0 = 64.0D;
/*     */         
/* 205 */         if (entityliving.h(this.a) < 4096.0D) {
/* 206 */           double d1 = entityliving.locX() - this.a.locX();
/* 207 */           double d2 = entityliving.locZ() - this.a.locZ();
/*     */           
/* 209 */           this.a.yaw = -((float)MathHelper.d(d1, d2)) * 57.295776F;
/* 210 */           this.a.aA = this.a.yaw;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalGhastIdleMove
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityGhast a;
/*     */     
/*     */     public PathfinderGoalGhastIdleMove(EntityGhast entityghast) {
/* 222 */       this.a = entityghast;
/* 223 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 228 */       ControllerMove controllermove = this.a.getControllerMove();
/*     */       
/* 230 */       if (!controllermove.b()) {
/* 231 */         return true;
/*     */       }
/* 233 */       double d0 = controllermove.d() - this.a.locX();
/* 234 */       double d1 = controllermove.e() - this.a.locY();
/* 235 */       double d2 = controllermove.f() - this.a.locZ();
/* 236 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */       
/* 238 */       return (d3 < 1.0D || d3 > 3600.0D);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 244 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 249 */       Random random = this.a.getRandom();
/* 250 */       double d0 = this.a.locX() + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 251 */       double d1 = this.a.locY() + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 252 */       double d2 = this.a.locZ() + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/*     */       
/* 254 */       this.a.getControllerMove().a(d0, d1, d2, 1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ControllerGhast
/*     */     extends ControllerMove {
/*     */     private final EntityGhast i;
/*     */     private int j;
/*     */     
/*     */     public ControllerGhast(EntityGhast entityghast) {
/* 264 */       super(entityghast);
/* 265 */       this.i = entityghast;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 270 */       if (this.h == ControllerMove.Operation.MOVE_TO && 
/* 271 */         this.j-- <= 0) {
/* 272 */         this.j += this.i.getRandom().nextInt(5) + 2;
/* 273 */         Vec3D vec3d = new Vec3D(this.b - this.i.locX(), this.c - this.i.locY(), this.d - this.i.locZ());
/* 274 */         double d0 = vec3d.f();
/*     */         
/* 276 */         vec3d = vec3d.d();
/* 277 */         if (a(vec3d, MathHelper.f(d0))) {
/* 278 */           this.i.setMot(this.i.getMot().e(vec3d.a(0.1D)));
/*     */         } else {
/* 280 */           this.h = ControllerMove.Operation.WAIT;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean a(Vec3D vec3d, int i) {
/* 288 */       AxisAlignedBB axisalignedbb = this.i.getBoundingBox();
/*     */       
/* 290 */       for (int j = 1; j < i; j++) {
/* 291 */         axisalignedbb = axisalignedbb.c(vec3d);
/* 292 */         if (!this.i.world.getCubes(this.i, axisalignedbb)) {
/* 293 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 297 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityGhast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */