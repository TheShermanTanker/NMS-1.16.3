/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ public abstract class IProjectile
/*     */   extends Entity
/*     */ {
/*     */   private UUID shooter;
/*     */   private int c;
/*     */   private boolean d;
/*     */   
/*     */   IProjectile(EntityTypes<? extends IProjectile> entitytypes, World world) {
/*  18 */     super(entitytypes, world);
/*     */   }
/*     */   
/*     */   public void setShooter(@Nullable Entity entity) {
/*  22 */     if (entity != null) {
/*  23 */       this.shooter = entity.getUniqueID();
/*  24 */       this.c = entity.getId();
/*     */     } 
/*  26 */     this.projectileSource = (entity != null && entity.getBukkitEntity() instanceof ProjectileSource) ? (ProjectileSource)entity.getBukkitEntity() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getShooter() {
/*  33 */     Entity entity = (this.shooter != null && this.world instanceof WorldServer) ? ((WorldServer)this.world).getEntity(this.shooter) : ((this.c != 0) ? this.world.getEntity(this.c) : null);
/*  34 */     if (entity == null) {
/*  35 */       for (WorldServer world : this.world.getMinecraftServer().getWorlds()) {
/*  36 */         entity = world.getEntity(this.shooter);
/*  37 */         if (entity != null) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     }
/*  42 */     return entity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/*  48 */     if (this.shooter != null) {
/*  49 */       nbttagcompound.a("Owner", this.shooter);
/*     */     }
/*     */     
/*  52 */     if (this.d) {
/*  53 */       nbttagcompound.setBoolean("LeftOwner", true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/*  60 */     if (nbttagcompound.b("Owner")) {
/*  61 */       this.shooter = nbttagcompound.a("Owner");
/*  62 */       if (this instanceof EntityEnderPearl && this.world != null && this.world.paperConfig.disableEnderpearlExploit) this.shooter = null;
/*     */     
/*     */     } 
/*  65 */     this.d = nbttagcompound.getBoolean("LeftOwner");
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  70 */     if (!this.d) {
/*  71 */       this.d = h();
/*     */     }
/*     */     
/*  74 */     super.tick();
/*     */   }
/*     */   
/*     */   private boolean h() {
/*  78 */     Entity entity = getShooter();
/*     */     
/*  80 */     if (entity != null) {
/*     */ 
/*     */       
/*  83 */       Iterator<Entity> iterator = this.world.getEntities(this, getBoundingBox().b(getMot()).g(1.0D), entity1 -> (!entity1.isSpectator() && entity1.isInteractable())).iterator();
/*     */       
/*  85 */       while (iterator.hasNext()) {
/*  86 */         Entity entity1 = iterator.next();
/*     */         
/*  88 */         if (entity1.getRootVehicle() == entity.getRootVehicle()) {
/*  89 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     return true;
/*     */   }
/*     */   
/*     */   public void shoot(double d0, double d1, double d2, float f, float f1) {
/*  98 */     Vec3D vec3d = (new Vec3D(d0, d1, d2)).d().add(this.random.nextGaussian() * 0.007499999832361937D * f1, this.random.nextGaussian() * 0.007499999832361937D * f1, this.random.nextGaussian() * 0.007499999832361937D * f1).a(f);
/*     */     
/* 100 */     setMot(vec3d);
/* 101 */     float f2 = MathHelper.sqrt(c(vec3d));
/*     */     
/* 103 */     this.yaw = (float)(MathHelper.d(vec3d.x, vec3d.z) * 57.2957763671875D);
/* 104 */     this.pitch = (float)(MathHelper.d(vec3d.y, f2) * 57.2957763671875D);
/* 105 */     this.lastYaw = this.yaw;
/* 106 */     this.lastPitch = this.pitch;
/*     */   }
/*     */   
/*     */   public void a(Entity entity, float f, float f1, float f2, float f3, float f4) {
/* 110 */     float f5 = -MathHelper.sin(f1 * 0.017453292F) * MathHelper.cos(f * 0.017453292F);
/* 111 */     float f6 = -MathHelper.sin((f + f2) * 0.017453292F);
/* 112 */     float f7 = MathHelper.cos(f1 * 0.017453292F) * MathHelper.cos(f * 0.017453292F);
/*     */     
/* 114 */     shoot(f5, f6, f7, f3, f4);
/* 115 */     Vec3D vec3d = entity.getMot();
/*     */     
/* 117 */     if (!entity.world.paperConfig.disableRelativeProjectileVelocity) setMot(getMot().add(vec3d.x, entity.isOnGround() ? 0.0D : vec3d.y, vec3d.z)); 
/*     */   }
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/* 121 */     CraftEventFactory.callProjectileHitEvent(this, movingobjectposition);
/* 122 */     MovingObjectPosition.EnumMovingObjectType movingobjectposition_enummovingobjecttype = movingobjectposition.getType();
/*     */     
/* 124 */     if (movingobjectposition_enummovingobjecttype == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
/* 125 */       a((MovingObjectPositionEntity)movingobjectposition);
/* 126 */     } else if (movingobjectposition_enummovingobjecttype == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 127 */       a((MovingObjectPositionBlock)movingobjectposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {}
/*     */   
/*     */   protected void a(MovingObjectPositionBlock movingobjectpositionblock) {
/* 135 */     IBlockData iblockdata = this.world.getType(movingobjectpositionblock.getBlockPosition());
/*     */     
/* 137 */     iblockdata.a(this.world, iblockdata, movingobjectpositionblock, this);
/*     */   }
/*     */   
/*     */   protected boolean a(Entity entity) {
/* 141 */     if (!entity.isSpectator() && entity.isAlive() && entity.isInteractable()) {
/* 142 */       Entity entity1 = getShooter();
/*     */       
/* 144 */       if (entity1 instanceof EntityPlayer && entity instanceof EntityPlayer) {
/* 145 */         Player collided = (Player)entity.getBukkitEntity();
/* 146 */         Player shooter = (Player)entity1.getBukkitEntity();
/* 147 */         if (!shooter.canSee(collided)) return false; 
/*     */       } 
/* 149 */       return (entity1 == null || this.d || !entity1.isSameVehicle(entity));
/*     */     } 
/*     */     
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void x() {
/* 157 */     Vec3D vec3d = getMot();
/* 158 */     float f = MathHelper.sqrt(c(vec3d));
/*     */     
/* 160 */     this.pitch = e(this.lastPitch, (float)(MathHelper.d(vec3d.y, f) * 57.2957763671875D));
/* 161 */     this.yaw = e(this.lastYaw, (float)(MathHelper.d(vec3d.x, vec3d.z) * 57.2957763671875D));
/*     */   }
/*     */   
/*     */   protected static float e(float f, float f1) {
/* 165 */     while (f1 - f < -180.0F) {
/* 166 */       f -= 360.0F;
/*     */     }
/*     */     
/* 169 */     while (f1 - f >= 180.0F) {
/* 170 */       f += 360.0F;
/*     */     }
/*     */     
/* 173 */     return MathHelper.g(0.2F, f, f1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */