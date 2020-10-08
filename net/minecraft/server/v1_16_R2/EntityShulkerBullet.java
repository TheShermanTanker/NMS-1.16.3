/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ 
/*     */ public class EntityShulkerBullet
/*     */   extends IProjectile
/*     */ {
/*     */   private Entity target;
/*     */   @Nullable
/*     */   private EnumDirection dir;
/*     */   private int d;
/*     */   
/*     */   public EntityShulkerBullet(EntityTypes<? extends EntityShulkerBullet> entitytypes, World world) {
/*  21 */     super((EntityTypes)entitytypes, world);
/*  22 */     this.noclip = true;
/*     */   } private double e; private double f; private double g; @Nullable
/*     */   private UUID ag;
/*     */   public EntityShulkerBullet(World world, EntityLiving entityliving, Entity entity, EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  26 */     this(EntityTypes.SHULKER_BULLET, world);
/*  27 */     setShooter(entityliving);
/*  28 */     BlockPosition blockposition = entityliving.getChunkCoordinates();
/*  29 */     double d0 = blockposition.getX() + 0.5D;
/*  30 */     double d1 = blockposition.getY() + 0.5D;
/*  31 */     double d2 = blockposition.getZ() + 0.5D;
/*     */     
/*  33 */     setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
/*  34 */     this.target = entity;
/*  35 */     this.dir = EnumDirection.UP;
/*  36 */     a(enumdirection_enumaxis);
/*  37 */     this.projectileSource = (ProjectileSource)entityliving.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getTarget() {
/*  42 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(Entity e) {
/*  46 */     this.target = e;
/*  47 */     this.dir = EnumDirection.UP;
/*  48 */     a(EnumDirection.EnumAxis.X);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/*  54 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/*  59 */     super.saveData(nbttagcompound);
/*  60 */     if (this.target != null) {
/*  61 */       nbttagcompound.a("Target", this.target.getUniqueID());
/*     */     }
/*     */     
/*  64 */     if (this.dir != null) {
/*  65 */       nbttagcompound.setInt("Dir", this.dir.c());
/*     */     }
/*     */     
/*  68 */     nbttagcompound.setInt("Steps", this.d);
/*  69 */     nbttagcompound.setDouble("TXD", this.e);
/*  70 */     nbttagcompound.setDouble("TYD", this.f);
/*  71 */     nbttagcompound.setDouble("TZD", this.g);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/*  76 */     super.loadData(nbttagcompound);
/*  77 */     this.d = nbttagcompound.getInt("Steps");
/*  78 */     this.e = nbttagcompound.getDouble("TXD");
/*  79 */     this.f = nbttagcompound.getDouble("TYD");
/*  80 */     this.g = nbttagcompound.getDouble("TZD");
/*  81 */     if (nbttagcompound.hasKeyOfType("Dir", 99)) {
/*  82 */       this.dir = EnumDirection.fromType1(nbttagcompound.getInt("Dir"));
/*     */     }
/*     */     
/*  85 */     if (nbttagcompound.b("Target")) {
/*  86 */       this.ag = nbttagcompound.a("Target");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {}
/*     */ 
/*     */   
/*     */   private void a(@Nullable EnumDirection enumdirection) {
/*  95 */     this.dir = enumdirection;
/*     */   }
/*     */   private void a(@Nullable EnumDirection.EnumAxis enumdirection_enumaxis) {
/*     */     BlockPosition blockposition;
/*  99 */     double d0 = 0.5D;
/*     */ 
/*     */     
/* 102 */     if (this.target == null) {
/* 103 */       blockposition = getChunkCoordinates().down();
/*     */     } else {
/* 105 */       d0 = this.target.getHeight() * 0.5D;
/* 106 */       blockposition = new BlockPosition(this.target.locX(), this.target.locY() + d0, this.target.locZ());
/*     */     } 
/*     */     
/* 109 */     double d1 = blockposition.getX() + 0.5D;
/* 110 */     double d2 = blockposition.getY() + d0;
/* 111 */     double d3 = blockposition.getZ() + 0.5D;
/* 112 */     EnumDirection enumdirection = null;
/*     */     
/* 114 */     if (!blockposition.a(getPositionVector(), 2.0D)) {
/* 115 */       BlockPosition blockposition1 = getChunkCoordinates();
/* 116 */       List<EnumDirection> list = Lists.newArrayList();
/*     */       
/* 118 */       if (enumdirection_enumaxis != EnumDirection.EnumAxis.X) {
/* 119 */         if (blockposition1.getX() < blockposition.getX() && this.world.isEmpty(blockposition1.east())) {
/* 120 */           list.add(EnumDirection.EAST);
/* 121 */         } else if (blockposition1.getX() > blockposition.getX() && this.world.isEmpty(blockposition1.west())) {
/* 122 */           list.add(EnumDirection.WEST);
/*     */         } 
/*     */       }
/*     */       
/* 126 */       if (enumdirection_enumaxis != EnumDirection.EnumAxis.Y) {
/* 127 */         if (blockposition1.getY() < blockposition.getY() && this.world.isEmpty(blockposition1.up())) {
/* 128 */           list.add(EnumDirection.UP);
/* 129 */         } else if (blockposition1.getY() > blockposition.getY() && this.world.isEmpty(blockposition1.down())) {
/* 130 */           list.add(EnumDirection.DOWN);
/*     */         } 
/*     */       }
/*     */       
/* 134 */       if (enumdirection_enumaxis != EnumDirection.EnumAxis.Z) {
/* 135 */         if (blockposition1.getZ() < blockposition.getZ() && this.world.isEmpty(blockposition1.south())) {
/* 136 */           list.add(EnumDirection.SOUTH);
/* 137 */         } else if (blockposition1.getZ() > blockposition.getZ() && this.world.isEmpty(blockposition1.north())) {
/* 138 */           list.add(EnumDirection.NORTH);
/*     */         } 
/*     */       }
/*     */       
/* 142 */       enumdirection = EnumDirection.a(this.random);
/* 143 */       if (list.isEmpty()) {
/* 144 */         for (int i = 5; !this.world.isEmpty(blockposition1.shift(enumdirection)) && i > 0; i--) {
/* 145 */           enumdirection = EnumDirection.a(this.random);
/*     */         }
/*     */       } else {
/* 148 */         enumdirection = list.get(this.random.nextInt(list.size()));
/*     */       } 
/*     */       
/* 151 */       d1 = locX() + enumdirection.getAdjacentX();
/* 152 */       d2 = locY() + enumdirection.getAdjacentY();
/* 153 */       d3 = locZ() + enumdirection.getAdjacentZ();
/*     */     } 
/*     */     
/* 156 */     a(enumdirection);
/* 157 */     double d4 = d1 - locX();
/* 158 */     double d5 = d2 - locY();
/* 159 */     double d6 = d3 - locZ();
/* 160 */     double d7 = MathHelper.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
/*     */     
/* 162 */     if (d7 == 0.0D) {
/* 163 */       this.e = 0.0D;
/* 164 */       this.f = 0.0D;
/* 165 */       this.g = 0.0D;
/*     */     } else {
/* 167 */       this.e = d4 / d7 * 0.15D;
/* 168 */       this.f = d5 / d7 * 0.15D;
/* 169 */       this.g = d6 / d7 * 0.15D;
/*     */     } 
/*     */     
/* 172 */     this.impulse = true;
/* 173 */     this.d = 10 + this.random.nextInt(5) * 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkDespawn() {
/* 178 */     if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
/* 179 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 186 */     super.tick();
/*     */ 
/*     */     
/* 189 */     if (!this.world.isClientSide) {
/* 190 */       if (this.target == null && this.ag != null) {
/* 191 */         this.target = ((WorldServer)this.world).getEntity(this.ag);
/* 192 */         if (this.target == null) {
/* 193 */           this.ag = null;
/*     */         }
/*     */       } 
/*     */       
/* 197 */       if (this.target != null && this.target.isAlive() && (!(this.target instanceof EntityHuman) || !((EntityHuman)this.target).isSpectator())) {
/* 198 */         this.e = MathHelper.a(this.e * 1.025D, -1.0D, 1.0D);
/* 199 */         this.f = MathHelper.a(this.f * 1.025D, -1.0D, 1.0D);
/* 200 */         this.g = MathHelper.a(this.g * 1.025D, -1.0D, 1.0D);
/* 201 */         Vec3D vec3D = getMot();
/* 202 */         setMot(vec3D.add((this.e - vec3D.x) * 0.2D, (this.f - vec3D.y) * 0.2D, (this.g - vec3D.z) * 0.2D));
/* 203 */       } else if (!isNoGravity()) {
/* 204 */         setMot(getMot().add(0.0D, -0.04D, 0.0D));
/*     */       } 
/*     */       
/* 207 */       MovingObjectPosition movingobjectposition = ProjectileHelper.a(this, this::a);
/*     */       
/* 209 */       if (movingobjectposition.getType() != MovingObjectPosition.EnumMovingObjectType.MISS) {
/* 210 */         a(movingobjectposition);
/*     */       }
/*     */     } 
/*     */     
/* 214 */     checkBlockCollisions();
/* 215 */     Vec3D vec3d = getMot();
/* 216 */     setPosition(locX() + vec3d.x, locY() + vec3d.y, locZ() + vec3d.z);
/* 217 */     ProjectileHelper.a(this, 0.5F);
/* 218 */     if (this.world.isClientSide) {
/* 219 */       this.world.addParticle(Particles.END_ROD, locX() - vec3d.x, locY() - vec3d.y + 0.15D, locZ() - vec3d.z, 0.0D, 0.0D, 0.0D);
/* 220 */     } else if (this.target != null && !this.target.dead) {
/* 221 */       if (this.d > 0) {
/* 222 */         this.d--;
/* 223 */         if (this.d == 0) {
/* 224 */           a((this.dir == null) ? null : this.dir.n());
/*     */         }
/*     */       } 
/*     */       
/* 228 */       if (this.dir != null) {
/* 229 */         BlockPosition blockposition = getChunkCoordinates();
/* 230 */         EnumDirection.EnumAxis enumdirection_enumaxis = this.dir.n();
/*     */         
/* 232 */         if (this.world.a(blockposition.shift(this.dir), this)) {
/* 233 */           a(enumdirection_enumaxis);
/*     */         } else {
/* 235 */           BlockPosition blockposition1 = this.target.getChunkCoordinates();
/*     */           
/* 237 */           if ((enumdirection_enumaxis == EnumDirection.EnumAxis.X && blockposition.getX() == blockposition1.getX()) || (enumdirection_enumaxis == EnumDirection.EnumAxis.Z && blockposition.getZ() == blockposition1.getZ()) || (enumdirection_enumaxis == EnumDirection.EnumAxis.Y && blockposition.getY() == blockposition1.getY())) {
/* 238 */             a(enumdirection_enumaxis);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(Entity entity) {
/* 248 */     return (super.a(entity) && !entity.noclip);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float aQ() {
/* 258 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/* 263 */     super.a(movingobjectpositionentity);
/* 264 */     Entity entity = movingobjectpositionentity.getEntity();
/* 265 */     Entity entity1 = getShooter();
/* 266 */     EntityLiving entityliving = (entity1 instanceof EntityLiving) ? (EntityLiving)entity1 : null;
/* 267 */     boolean flag = entity.damageEntity(DamageSource.a(this, entityliving).c(), 4.0F);
/*     */     
/* 269 */     if (flag) {
/* 270 */       a(entityliving, entity);
/* 271 */       if (entity instanceof EntityLiving) {
/* 272 */         ((EntityLiving)entity).addEffect(new MobEffect(MobEffects.LEVITATION, 200), EntityPotionEffectEvent.Cause.ATTACK);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionBlock movingobjectpositionblock) {
/* 280 */     super.a(movingobjectpositionblock);
/* 281 */     ((WorldServer)this.world).a(Particles.EXPLOSION, locX(), locY(), locZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
/* 282 */     playSound(SoundEffects.ENTITY_SHULKER_BULLET_HIT, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/* 287 */     super.a(movingobjectposition);
/* 288 */     die();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 293 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 299 */     if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f, false)) {
/* 300 */       return false;
/*     */     }
/*     */     
/* 303 */     if (!this.world.isClientSide) {
/* 304 */       playSound(SoundEffects.ENTITY_SHULKER_BULLET_HURT, 1.0F, 1.0F);
/* 305 */       ((WorldServer)this.world).a(Particles.CRIT, locX(), locY(), locZ(), 15, 0.2D, 0.2D, 0.2D, 0.0D);
/* 306 */       die();
/*     */     } 
/*     */     
/* 309 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 314 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityShulkerBullet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */