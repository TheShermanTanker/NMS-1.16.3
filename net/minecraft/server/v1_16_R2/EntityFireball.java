/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public abstract class EntityFireball extends IProjectile {
/*     */   public double dirX;
/*     */   public double dirY;
/*     */   public double dirZ;
/*  10 */   public float bukkitYield = 1.0F;
/*     */   public boolean isIncendiary = true;
/*     */   
/*     */   protected EntityFireball(EntityTypes<? extends EntityFireball> entitytypes, World world) {
/*  14 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityFireball(EntityTypes<? extends EntityFireball> entitytypes, double d0, double d1, double d2, double d3, double d4, double d5, World world) {
/*  18 */     this(entitytypes, world);
/*  19 */     setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
/*  20 */     ae();
/*     */     
/*  22 */     setDirection(d3, d4, d5);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDirection(double d3, double d4, double d5) {
/*  27 */     double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*     */     
/*  29 */     if (d6 != 0.0D) {
/*  30 */       this.dirX = d3 / d6 * 0.1D;
/*  31 */       this.dirY = d4 / d6 * 0.1D;
/*  32 */       this.dirZ = d5 / d6 * 0.1D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFireball(EntityTypes<? extends EntityFireball> entitytypes, EntityLiving entityliving, double d0, double d1, double d2, World world) {
/*  38 */     this(entitytypes, entityliving.locX(), entityliving.locY(), entityliving.locZ(), d0, d1, d2, world);
/*  39 */     setShooter(entityliving);
/*  40 */     setYawPitch(entityliving.yaw, entityliving.pitch);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {}
/*     */ 
/*     */   
/*     */   public void tick() {
/*  48 */     Entity entity = getShooter();
/*     */     
/*  50 */     if (!this.world.isClientSide && ((entity != null && entity.dead) || !this.world.isLoaded(getChunkCoordinates()))) {
/*  51 */       die();
/*     */     } else {
/*  53 */       super.tick();
/*  54 */       if (W_()) {
/*  55 */         setOnFire(1);
/*     */       }
/*     */       
/*  58 */       MovingObjectPosition movingobjectposition = ProjectileHelper.a(this, this::a);
/*     */ 
/*     */       
/*  61 */       if (movingobjectposition instanceof MovingObjectPositionEntity) {
/*  62 */         ProjectileCollideEvent event = CraftEventFactory.callProjectileCollideEvent(this, (MovingObjectPositionEntity)movingobjectposition);
/*  63 */         if (event.isCancelled()) {
/*  64 */           movingobjectposition = null;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  69 */       if (movingobjectposition != null && movingobjectposition.getType() != MovingObjectPosition.EnumMovingObjectType.MISS) {
/*  70 */         a(movingobjectposition);
/*     */ 
/*     */         
/*  73 */         if (this.dead) {
/*  74 */           CraftEventFactory.callProjectileHitEvent(this, movingobjectposition);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  79 */       checkBlockCollisions();
/*  80 */       Vec3D vec3d = getMot();
/*  81 */       double d0 = locX() + vec3d.x;
/*  82 */       double d1 = locY() + vec3d.y;
/*  83 */       double d2 = locZ() + vec3d.z;
/*     */       
/*  85 */       ProjectileHelper.a(this, 0.2F);
/*  86 */       float f = i();
/*     */       
/*  88 */       if (isInWater()) {
/*  89 */         for (int i = 0; i < 4; i++) {
/*  90 */           float f1 = 0.25F;
/*     */           
/*  92 */           this.world.addParticle(Particles.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D, d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
/*     */         } 
/*     */         
/*  95 */         f = 0.8F;
/*     */       } 
/*     */       
/*  98 */       setMot(vec3d.add(this.dirX, this.dirY, this.dirZ).a(f));
/*  99 */       this.world.addParticle(h(), d0, d1 + 0.5D, d2, 0.0D, 0.0D, 0.0D);
/* 100 */       setPosition(d0, d1, d2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(Entity entity) {
/* 106 */     return (super.a(entity) && !entity.noclip);
/*     */   }
/*     */   
/*     */   protected boolean W_() {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   protected ParticleParam h() {
/* 114 */     return Particles.SMOKE;
/*     */   }
/*     */   
/*     */   protected float i() {
/* 118 */     return 0.95F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 123 */     super.saveData(nbttagcompound);
/* 124 */     nbttagcompound.set("power", a(new double[] { this.dirX, this.dirY, this.dirZ }));
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 129 */     super.loadData(nbttagcompound);
/* 130 */     if (nbttagcompound.hasKeyOfType("power", 9)) {
/* 131 */       NBTTagList nbttaglist = nbttagcompound.getList("power", 6);
/*     */       
/* 133 */       if (nbttaglist.size() == 3) {
/* 134 */         this.dirX = nbttaglist.h(0);
/* 135 */         this.dirY = nbttaglist.h(1);
/* 136 */         this.dirZ = nbttaglist.h(2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float bf() {
/* 149 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 154 */     if (isInvulnerable(damagesource)) {
/* 155 */       return false;
/*     */     }
/* 157 */     velocityChanged();
/* 158 */     Entity entity = damagesource.getEntity();
/*     */     
/* 160 */     if (entity != null) {
/*     */       
/* 162 */       if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f)) {
/* 163 */         return false;
/*     */       }
/*     */       
/* 166 */       Vec3D vec3d = entity.getLookDirection();
/*     */       
/* 168 */       setMot(vec3d);
/* 169 */       this.dirX = vec3d.x * 0.1D;
/* 170 */       this.dirY = vec3d.y * 0.1D;
/* 171 */       this.dirZ = vec3d.z * 0.1D;
/* 172 */       setShooter(entity);
/* 173 */       return true;
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float aQ() {
/* 182 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 187 */     Entity entity = getShooter();
/* 188 */     int i = (entity == null) ? 0 : entity.getId();
/*     */     
/* 190 */     return new PacketPlayOutSpawnEntity(getId(), getUniqueID(), locX(), locY(), locZ(), this.pitch, this.yaw, getEntityType(), i, new Vec3D(this.dirX, this.dirY, this.dirZ));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */