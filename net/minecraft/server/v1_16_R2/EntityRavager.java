/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityRavager extends EntityRaider {
/*     */   private static final Predicate<Entity> b;
/*     */   
/*     */   static {
/*  10 */     b = (entity -> 
/*  11 */       (entity.isAlive() && !(entity instanceof EntityRavager)));
/*     */   }
/*     */   private int bo;
/*     */   private int bp;
/*     */   private int bq;
/*     */   
/*     */   public EntityRavager(EntityTypes<? extends EntityRavager> entitytypes, World world) {
/*  18 */     super((EntityTypes)entitytypes, world);
/*  19 */     this.G = 1.0F;
/*  20 */     this.f = 20;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  25 */     super.initPathfinder();
/*  26 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  27 */     this.goalSelector.a(4, new a());
/*  28 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 0.4D));
/*  29 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  30 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*  31 */     this.targetSelector.a(2, (new PathfinderGoalHurtByTarget(this, new Class[] { EntityRaider.class })).a(new Class[0]));
/*  32 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  33 */     this.targetSelector.a(4, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillagerAbstract.class, true));
/*  34 */     this.targetSelector.a(4, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void H() {
/*  39 */     boolean flag = (!(getRidingPassenger() instanceof EntityInsentient) || getRidingPassenger().getEntityType().a(TagsEntity.RADIERS));
/*  40 */     boolean flag1 = !(getVehicle() instanceof EntityBoat);
/*     */     
/*  42 */     this.goalSelector.a(PathfinderGoal.Type.MOVE, flag);
/*  43 */     this.goalSelector.a(PathfinderGoal.Type.JUMP, (flag && flag1));
/*  44 */     this.goalSelector.a(PathfinderGoal.Type.LOOK, flag);
/*  45 */     this.goalSelector.a(PathfinderGoal.Type.TARGET, flag);
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  49 */     return EntityMonster.eR().a(GenericAttributes.MAX_HEALTH, 100.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.3D).a(GenericAttributes.KNOCKBACK_RESISTANCE, 0.75D).a(GenericAttributes.ATTACK_DAMAGE, 12.0D).a(GenericAttributes.ATTACK_KNOCKBACK, 1.5D).a(GenericAttributes.FOLLOW_RANGE, 32.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  54 */     super.saveData(nbttagcompound);
/*  55 */     nbttagcompound.setInt("AttackTick", this.bo);
/*  56 */     nbttagcompound.setInt("StunTick", this.bp);
/*  57 */     nbttagcompound.setInt("RoarTick", this.bq);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  62 */     super.loadData(nbttagcompound);
/*  63 */     this.bo = nbttagcompound.getInt("AttackTick");
/*  64 */     this.bp = nbttagcompound.getInt("StunTick");
/*  65 */     this.bq = nbttagcompound.getInt("RoarTick");
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEffect eL() {
/*  70 */     return SoundEffects.ENTITY_RAVAGER_CELEBRATE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World world) {
/*  75 */     return new b(this, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public int eo() {
/*  80 */     return 45;
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/*  85 */     return 2.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean er() {
/*  90 */     return (!isNoAI() && getRidingPassenger() instanceof EntityLiving);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getRidingPassenger() {
/*  96 */     return getPassengers().isEmpty() ? null : getPassengers().get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 101 */     super.movementTick();
/* 102 */     if (isAlive()) {
/* 103 */       if (isFrozen()) {
/* 104 */         getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.0D);
/*     */       } else {
/* 106 */         double d0 = (getGoalTarget() != null) ? 0.35D : 0.3D;
/* 107 */         double d1 = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getBaseValue();
/*     */         
/* 109 */         getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(MathHelper.d(0.1D, d1, d0));
/*     */       } 
/*     */       
/* 112 */       if (this.positionChanged && this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
/* 113 */         boolean flag = false;
/* 114 */         AxisAlignedBB axisalignedbb = getBoundingBox().g(0.2D);
/* 115 */         Iterator<BlockPosition> iterator = BlockPosition.b(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ)).iterator();
/*     */         
/* 117 */         while (iterator.hasNext()) {
/* 118 */           BlockPosition blockposition = iterator.next();
/* 119 */           IBlockData iblockdata = this.world.getType(blockposition);
/* 120 */           Block block = iblockdata.getBlock();
/*     */           
/* 122 */           if (block instanceof BlockLeaves && !CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/* 123 */             flag = (this.world.a(blockposition, true, this) || flag);
/*     */           }
/*     */         } 
/*     */         
/* 127 */         if (!flag && this.onGround) {
/* 128 */           jump();
/*     */         }
/*     */       } 
/*     */       
/* 132 */       if (this.bq > 0) {
/* 133 */         this.bq--;
/* 134 */         if (this.bq == 10) {
/* 135 */           eY();
/*     */         }
/*     */       } 
/*     */       
/* 139 */       if (this.bo > 0) {
/* 140 */         this.bo--;
/*     */       }
/*     */       
/* 143 */       if (this.bp > 0) {
/* 144 */         this.bp--;
/* 145 */         eX();
/* 146 */         if (this.bp == 0) {
/* 147 */           playSound(SoundEffects.ENTITY_RAVAGER_ROAR, 1.0F, 1.0F);
/* 148 */           this.bq = 20;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void eX() {
/* 156 */     if (this.random.nextInt(6) == 0) {
/* 157 */       double d0 = locX() - getWidth() * Math.sin((this.aA * 0.017453292F)) + this.random.nextDouble() * 0.6D - 0.3D;
/* 158 */       double d1 = locY() + getHeight() - 0.3D;
/* 159 */       double d2 = locZ() + getWidth() * Math.cos((this.aA * 0.017453292F)) + this.random.nextDouble() * 0.6D - 0.3D;
/*     */       
/* 161 */       this.world.addParticle(Particles.ENTITY_EFFECT, d0, d1, d2, 0.4980392156862745D, 0.5137254901960784D, 0.5725490196078431D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFrozen() {
/* 168 */     return (super.isFrozen() || this.bo > 0 || this.bp > 0 || this.bq > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasLineOfSight(Entity entity) {
/* 173 */     return (this.bp <= 0 && this.bq <= 0) ? super.hasLineOfSight(entity) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void e(EntityLiving entityliving) {
/* 178 */     if (this.bq == 0) {
/* 179 */       if (this.random.nextDouble() < 0.5D) {
/* 180 */         this.bp = 40;
/* 181 */         playSound(SoundEffects.ENTITY_RAVAGER_STUNNED, 1.0F, 1.0F);
/* 182 */         this.world.broadcastEntityEffect(this, (byte)39);
/* 183 */         entityliving.collide(this);
/*     */       } else {
/* 185 */         a(entityliving);
/*     */       } 
/*     */       
/* 188 */       entityliving.velocityChanged = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void eY() {
/* 194 */     if (isAlive()) {
/* 195 */       List<Entity> list = this.world.a((Class)EntityLiving.class, getBoundingBox().g(4.0D), b);
/*     */ 
/*     */ 
/*     */       
/* 199 */       for (Iterator<Entity> iterator = list.iterator(); iterator.hasNext(); a(entity)) {
/* 200 */         Entity entity = iterator.next();
/* 201 */         if (!(entity instanceof EntityIllagerAbstract)) {
/* 202 */           entity.damageEntity(DamageSource.mobAttack(this), 6.0F);
/*     */         }
/*     */       } 
/*     */       
/* 206 */       Vec3D vec3d = getBoundingBox().f();
/*     */       
/* 208 */       for (int i = 0; i < 40; i++) {
/* 209 */         double d0 = this.random.nextGaussian() * 0.2D;
/* 210 */         double d1 = this.random.nextGaussian() * 0.2D;
/* 211 */         double d2 = this.random.nextGaussian() * 0.2D;
/*     */         
/* 213 */         this.world.addParticle(Particles.POOF, vec3d.x, vec3d.y, vec3d.z, d0, d1, d2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Entity entity) {
/* 220 */     double d0 = entity.locX() - locX();
/* 221 */     double d1 = entity.locZ() - locZ();
/* 222 */     double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
/*     */     
/* 224 */     entity.i(d0 / d2 * 4.0D, 0.2D, d1 / d2 * 4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 229 */     this.bo = 10;
/* 230 */     this.world.broadcastEntityEffect(this, (byte)4);
/* 231 */     playSound(SoundEffects.ENTITY_RAVAGER_ATTACK, 1.0F, 1.0F);
/* 232 */     return super.attackEntity(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAmbient() {
/* 238 */     return SoundEffects.ENTITY_RAVAGER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 243 */     return SoundEffects.ENTITY_RAVAGER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 248 */     return SoundEffects.ENTITY_RAVAGER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 253 */     playSound(SoundEffects.ENTITY_RAVAGER_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader iworldreader) {
/* 258 */     return !iworldreader.containsLiquid(getBoundingBox());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, boolean flag) {}
/*     */ 
/*     */   
/*     */   public boolean eN() {
/* 266 */     return false;
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends PathfinderNormal
/*     */   {
/*     */     private c() {}
/*     */     
/*     */     protected PathType a(IBlockAccess iblockaccess, boolean flag, boolean flag1, BlockPosition blockposition, PathType pathtype) {
/* 275 */       return (pathtype == PathType.LEAVES) ? PathType.OPEN : super.a(iblockaccess, flag, flag1, blockposition, pathtype);
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends Navigation {
/*     */     public b(EntityInsentient entityinsentient, World world) {
/* 282 */       super(entityinsentient, world);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Pathfinder a(int i) {
/* 287 */       this.o = new EntityRavager.c();
/* 288 */       return new Pathfinder(this.o, i);
/*     */     }
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoalMeleeAttack {
/*     */     public a() {
/* 295 */       super(EntityRavager.this, 1.0D, true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected double a(EntityLiving entityliving) {
/* 300 */       float f = EntityRavager.this.getWidth() - 0.1F;
/*     */       
/* 302 */       return (f * 2.0F * f * 2.0F + entityliving.getWidth());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityRavager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */