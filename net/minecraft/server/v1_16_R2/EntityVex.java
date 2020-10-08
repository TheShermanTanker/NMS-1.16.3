/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityVex
/*     */   extends EntityMonster {
/*   9 */   protected static final DataWatcherObject<Byte> b = DataWatcher.a((Class)EntityVex.class, DataWatcherRegistry.a);
/*     */   private EntityInsentient c;
/*     */   @Nullable
/*     */   private BlockPosition d;
/*     */   private boolean bo;
/*     */   private int bp;
/*     */   
/*     */   public EntityVex(EntityTypes<? extends EntityVex> entitytypes, World world) {
/*  17 */     super((EntityTypes)entitytypes, world);
/*  18 */     this.moveController = new c(this);
/*  19 */     this.f = 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType enummovetype, Vec3D vec3d) {
/*  24 */     super.move(enummovetype, vec3d);
/*  25 */     checkBlockCollisions();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  30 */     this.noclip = true;
/*  31 */     super.tick();
/*  32 */     this.noclip = false;
/*  33 */     setNoGravity(true);
/*  34 */     if (this.bo && --this.bp <= 0) {
/*  35 */       this.bp = 20;
/*  36 */       damageEntity(DamageSource.STARVE, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  43 */     super.initPathfinder();
/*  44 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  45 */     this.goalSelector.a(4, new a());
/*  46 */     this.goalSelector.a(8, new d());
/*  47 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  48 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*  49 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[] { EntityRaider.class })).a(new Class[0]));
/*  50 */     this.targetSelector.a(2, new b(this));
/*  51 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  55 */     return EntityMonster.eR().a(GenericAttributes.MAX_HEALTH, 14.0D).a(GenericAttributes.ATTACK_DAMAGE, 4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  60 */     super.initDatawatcher();
/*  61 */     this.datawatcher.register(b, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  66 */     super.loadData(nbttagcompound);
/*  67 */     if (nbttagcompound.hasKey("BoundX")) {
/*  68 */       this.d = new BlockPosition(nbttagcompound.getInt("BoundX"), nbttagcompound.getInt("BoundY"), nbttagcompound.getInt("BoundZ"));
/*     */     }
/*     */     
/*  71 */     if (nbttagcompound.hasKey("LifeTicks")) {
/*  72 */       a(nbttagcompound.getInt("LifeTicks"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  79 */     super.saveData(nbttagcompound);
/*  80 */     if (this.d != null) {
/*  81 */       nbttagcompound.setInt("BoundX", this.d.getX());
/*  82 */       nbttagcompound.setInt("BoundY", this.d.getY());
/*  83 */       nbttagcompound.setInt("BoundZ", this.d.getZ());
/*     */     } 
/*     */     
/*  86 */     if (this.bo) {
/*  87 */       nbttagcompound.setInt("LifeTicks", this.bp);
/*     */     }
/*     */   }
/*     */   
/*     */   public EntityInsentient getOwner() {
/*  92 */     return eK();
/*     */   } public EntityInsentient eK() {
/*  94 */     return this.c;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition eL() {
/*  99 */     return this.d;
/*     */   }
/*     */   
/*     */   public void g(@Nullable BlockPosition blockposition) {
/* 103 */     this.d = blockposition;
/*     */   }
/*     */   
/*     */   private boolean b(int i) {
/* 107 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*     */     
/* 109 */     return ((b0 & i) != 0);
/*     */   }
/*     */   private void a(int i, boolean flag) {
/*     */     int j;
/* 113 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*     */ 
/*     */     
/* 116 */     if (flag) {
/* 117 */       j = b0 | i;
/*     */     } else {
/* 119 */       j = b0 & (i ^ 0xFFFFFFFF);
/*     */     } 
/*     */     
/* 122 */     this.datawatcher.set(b, Byte.valueOf((byte)(j & 0xFF)));
/*     */   }
/*     */   
/*     */   public boolean isCharging() {
/* 126 */     return b(1);
/*     */   }
/*     */   
/*     */   public void setCharging(boolean flag) {
/* 130 */     a(1, flag);
/*     */   }
/*     */   public void setOwner(EntityInsentient entityinsentient) {
/* 133 */     a(entityinsentient);
/*     */   } public void a(EntityInsentient entityinsentient) {
/* 135 */     this.c = entityinsentient;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 139 */     this.bo = true;
/* 140 */     this.bp = i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 145 */     return SoundEffects.ENTITY_VEX_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 150 */     return SoundEffects.ENTITY_VEX_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 155 */     return SoundEffects.ENTITY_VEX_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   public float aQ() {
/* 160 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 166 */     a(difficultydamagescaler);
/* 167 */     b(difficultydamagescaler);
/* 168 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/* 173 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
/* 174 */     a(EnumItemSlot.MAINHAND, 0.0F);
/*     */   }
/*     */   
/*     */   class b
/*     */     extends PathfinderGoalTarget {
/* 179 */     private final PathfinderTargetCondition b = (new PathfinderTargetCondition()).c().e();
/*     */     
/*     */     public b(EntityCreature entitycreature) {
/* 182 */       super(entitycreature, false);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 187 */       return (EntityVex.this.c != null && EntityVex.this.c.getGoalTarget() != null && a(EntityVex.this.c.getGoalTarget(), this.b));
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 192 */       EntityVex.this.setGoalTarget(EntityVex.this.c.getGoalTarget(), EntityTargetEvent.TargetReason.OWNER_ATTACKED_TARGET, true);
/* 193 */       super.c();
/*     */     }
/*     */   }
/*     */   
/*     */   class d
/*     */     extends PathfinderGoal {
/*     */     public d() {
/* 200 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 205 */       return (!EntityVex.this.getControllerMove().b() && EntityVex.this.random.nextInt(7) == 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 210 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 215 */       BlockPosition blockposition = EntityVex.this.eL();
/*     */       
/* 217 */       if (blockposition == null) {
/* 218 */         blockposition = EntityVex.this.getChunkCoordinates();
/*     */       }
/*     */       
/* 221 */       for (int i = 0; i < 3; i++) {
/* 222 */         BlockPosition blockposition1 = blockposition.b(EntityVex.this.random.nextInt(15) - 7, EntityVex.this.random.nextInt(11) - 5, EntityVex.this.random.nextInt(15) - 7);
/*     */         
/* 224 */         if (EntityVex.this.world.isEmpty(blockposition1)) {
/* 225 */           EntityVex.this.moveController.a(blockposition1.getX() + 0.5D, blockposition1.getY() + 0.5D, blockposition1.getZ() + 0.5D, 0.25D);
/* 226 */           if (EntityVex.this.getGoalTarget() == null) {
/* 227 */             EntityVex.this.getControllerLook().a(blockposition1.getX() + 0.5D, blockposition1.getY() + 0.5D, blockposition1.getZ() + 0.5D, 180.0F, 20.0F);
/*     */           }
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoal
/*     */   {
/*     */     public a() {
/* 239 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 244 */       return (EntityVex.this.getGoalTarget() != null && !EntityVex.this.getControllerMove().b() && EntityVex.this.random.nextInt(7) == 0) ? ((EntityVex.this.h(EntityVex.this.getGoalTarget()) > 4.0D)) : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 249 */       return (EntityVex.this.getControllerMove().b() && EntityVex.this.isCharging() && EntityVex.this.getGoalTarget() != null && EntityVex.this.getGoalTarget().isAlive());
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 254 */       EntityLiving entityliving = EntityVex.this.getGoalTarget();
/* 255 */       Vec3D vec3d = entityliving.j(1.0F);
/*     */       
/* 257 */       EntityVex.this.moveController.a(vec3d.x, vec3d.y, vec3d.z, 1.0D);
/* 258 */       EntityVex.this.setCharging(true);
/* 259 */       EntityVex.this.playSound(SoundEffects.ENTITY_VEX_CHARGE, 1.0F, 1.0F);
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 264 */       EntityVex.this.setCharging(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 269 */       EntityLiving entityliving = EntityVex.this.getGoalTarget();
/*     */       
/* 271 */       if (EntityVex.this.getBoundingBox().c(entityliving.getBoundingBox())) {
/* 272 */         EntityVex.this.attackEntity(entityliving);
/* 273 */         EntityVex.this.setCharging(false);
/*     */       } else {
/* 275 */         double d0 = EntityVex.this.h(entityliving);
/*     */         
/* 277 */         if (d0 < 9.0D) {
/* 278 */           Vec3D vec3d = entityliving.j(1.0F);
/*     */           
/* 280 */           EntityVex.this.moveController.a(vec3d.x, vec3d.y, vec3d.z, 1.0D);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class c
/*     */     extends ControllerMove
/*     */   {
/*     */     public c(EntityVex entityvex) {
/* 290 */       super(entityvex);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 295 */       if (this.h == ControllerMove.Operation.MOVE_TO) {
/* 296 */         Vec3D vec3d = new Vec3D(this.b - EntityVex.this.locX(), this.c - EntityVex.this.locY(), this.d - EntityVex.this.locZ());
/* 297 */         double d0 = vec3d.f();
/*     */         
/* 299 */         if (d0 < EntityVex.this.getBoundingBox().a()) {
/* 300 */           this.h = ControllerMove.Operation.WAIT;
/* 301 */           EntityVex.this.setMot(EntityVex.this.getMot().a(0.5D));
/*     */         } else {
/* 303 */           EntityVex.this.setMot(EntityVex.this.getMot().e(vec3d.a(this.e * 0.05D / d0)));
/* 304 */           if (EntityVex.this.getGoalTarget() == null) {
/* 305 */             Vec3D vec3d1 = EntityVex.this.getMot();
/*     */             
/* 307 */             EntityVex.this.yaw = -((float)MathHelper.d(vec3d1.x, vec3d1.z)) * 57.295776F;
/* 308 */             EntityVex.this.aA = EntityVex.this.yaw;
/*     */           } else {
/* 310 */             double d1 = EntityVex.this.getGoalTarget().locX() - EntityVex.this.locX();
/* 311 */             double d2 = EntityVex.this.getGoalTarget().locZ() - EntityVex.this.locZ();
/*     */             
/* 313 */             EntityVex.this.yaw = -((float)MathHelper.d(d1, d2)) * 57.295776F;
/* 314 */             EntityVex.this.aA = EntityVex.this.yaw;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityVex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */