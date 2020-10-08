/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityFireworks extends IProjectile {
/*  11 */   public static final DataWatcherObject<ItemStack> FIREWORK_ITEM = DataWatcher.a((Class)EntityFireworks.class, DataWatcherRegistry.g);
/*  12 */   private static final DataWatcherObject<OptionalInt> c = DataWatcher.a((Class)EntityFireworks.class, DataWatcherRegistry.r);
/*  13 */   public static final DataWatcherObject<Boolean> SHOT_AT_ANGLE = DataWatcher.a((Class)EntityFireworks.class, DataWatcherRegistry.i);
/*     */   private int ticksFlown;
/*     */   public int expectedLifespan;
/*     */   public EntityLiving ridingEntity;
/*     */   public UUID spawningEntity;
/*     */   
/*     */   public EntityFireworks(EntityTypes<? extends EntityFireworks> entitytypes, World world) {
/*  20 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityFireworks(World world, double d0, double d1, double d2, ItemStack itemstack) {
/*  24 */     super((EntityTypes)EntityTypes.FIREWORK_ROCKET, world);
/*  25 */     this.ticksFlown = 0;
/*  26 */     setPosition(d0, d1, d2);
/*  27 */     int i = 1;
/*     */     
/*  29 */     if (!itemstack.isEmpty() && itemstack.hasTag()) {
/*  30 */       this.datawatcher.set(FIREWORK_ITEM, itemstack.cloneItemStack());
/*  31 */       i += itemstack.a("Fireworks").getByte("Flight");
/*     */     } 
/*     */     
/*  34 */     setMot(this.random.nextGaussian() * 0.001D, 0.05D, this.random.nextGaussian() * 0.001D);
/*  35 */     this.expectedLifespan = 10 * i + this.random.nextInt(6) + this.random.nextInt(7);
/*     */   }
/*     */   
/*     */   public EntityFireworks(World world, @Nullable Entity entity, double d0, double d1, double d2, ItemStack itemstack) {
/*  39 */     this(world, d0, d1, d2, itemstack);
/*  40 */     setShooter(entity);
/*     */   }
/*     */   
/*     */   public EntityFireworks(World world, ItemStack itemstack, EntityLiving entityliving) {
/*  44 */     this(world, entityliving, entityliving.locX(), entityliving.locY(), entityliving.locZ(), itemstack);
/*  45 */     this.datawatcher.set(c, OptionalInt.of(entityliving.getId()));
/*  46 */     this.ridingEntity = entityliving;
/*     */   }
/*     */   
/*     */   public EntityFireworks(World world, ItemStack itemstack, double d0, double d1, double d2, boolean flag) {
/*  50 */     this(world, d0, d1, d2, itemstack);
/*  51 */     this.datawatcher.set(SHOT_AT_ANGLE, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public EntityFireworks(World world, ItemStack itemstack, Entity entity, double d0, double d1, double d2, boolean flag) {
/*  55 */     this(world, itemstack, d0, d1, d2, flag);
/*  56 */     setShooter(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inactiveTick() {
/*  62 */     this.ticksFlown++;
/*     */     
/*  64 */     if (!this.world.isClientSide && this.ticksFlown > this.expectedLifespan)
/*     */     {
/*  66 */       if (!CraftEventFactory.callFireworkExplodeEvent(this).isCancelled()) {
/*  67 */         explode();
/*     */       }
/*     */     }
/*     */     
/*  71 */     super.inactiveTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  77 */     this.datawatcher.register(FIREWORK_ITEM, ItemStack.b);
/*  78 */     this.datawatcher.register(c, OptionalInt.empty());
/*  79 */     this.datawatcher.register(SHOT_AT_ANGLE, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  84 */     super.tick();
/*     */ 
/*     */     
/*  87 */     if (n()) {
/*  88 */       if (this.ridingEntity == null) {
/*  89 */         ((OptionalInt)this.datawatcher.<OptionalInt>get(c)).ifPresent(i -> {
/*     */               Entity entity = this.world.getEntity(i);
/*     */ 
/*     */               
/*     */               if (entity instanceof EntityLiving) {
/*     */                 this.ridingEntity = (EntityLiving)entity;
/*     */               }
/*     */             });
/*     */       }
/*     */       
/*  99 */       if (this.ridingEntity != null) {
/* 100 */         if (this.ridingEntity.isGliding()) {
/* 101 */           Vec3D vec3d = this.ridingEntity.getLookDirection();
/* 102 */           double d0 = 1.5D;
/* 103 */           double d1 = 0.1D;
/* 104 */           Vec3D vec3d1 = this.ridingEntity.getMot();
/*     */           
/* 106 */           this.ridingEntity.setMot(vec3d1.add(vec3d.x * 0.1D + (vec3d.x * 1.5D - vec3d1.x) * 0.5D, vec3d.y * 0.1D + (vec3d.y * 1.5D - vec3d1.y) * 0.5D, vec3d.z * 0.1D + (vec3d.z * 1.5D - vec3d1.z) * 0.5D));
/*     */         } 
/*     */         
/* 109 */         setPosition(this.ridingEntity.locX(), this.ridingEntity.locY(), this.ridingEntity.locZ());
/* 110 */         setMot(this.ridingEntity.getMot());
/*     */       } 
/*     */     } else {
/* 113 */       if (!isShotAtAngle()) {
/* 114 */         setMot(getMot().d(1.15D, 1.0D, 1.15D).add(0.0D, 0.04D, 0.0D));
/*     */       }
/*     */       
/* 117 */       Vec3D vec3d = getMot();
/* 118 */       move(EnumMoveType.SELF, vec3d);
/* 119 */       setMot(vec3d);
/*     */     } 
/*     */     
/* 122 */     MovingObjectPosition movingobjectposition = ProjectileHelper.a(this, this::a);
/*     */     
/* 124 */     if (!this.noclip) {
/* 125 */       a(movingobjectposition);
/* 126 */       this.impulse = true;
/*     */     } 
/*     */     
/* 129 */     x();
/* 130 */     if (this.ticksFlown == 0 && !isSilent()) {
/* 131 */       this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_FIREWORK_ROCKET_LAUNCH, SoundCategory.AMBIENT, 3.0F, 1.0F);
/*     */     }
/*     */     
/* 134 */     this.ticksFlown++;
/* 135 */     if (this.world.isClientSide && this.ticksFlown % 2 < 2) {
/* 136 */       this.world.addParticle(Particles.FIREWORK, locX(), locY() - 0.3D, locZ(), this.random.nextGaussian() * 0.05D, -(getMot()).y * 0.5D, this.random.nextGaussian() * 0.05D);
/*     */     }
/*     */     
/* 139 */     if (!this.world.isClientSide && this.ticksFlown > this.expectedLifespan)
/*     */     {
/* 141 */       if (!CraftEventFactory.callFireworkExplodeEvent(this).isCancelled()) {
/* 142 */         explode();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void explode() {
/* 150 */     this.world.broadcastEntityEffect(this, (byte)17);
/* 151 */     m();
/* 152 */     die();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/* 157 */     super.a(movingobjectpositionentity);
/* 158 */     if (!this.world.isClientSide)
/*     */     {
/* 160 */       if (!CraftEventFactory.callFireworkExplodeEvent(this).isCancelled()) {
/* 161 */         explode();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionBlock movingobjectpositionblock) {
/* 169 */     BlockPosition blockposition = new BlockPosition(movingobjectpositionblock.getBlockPosition());
/*     */     
/* 171 */     this.world.getType(blockposition).a(this.world, blockposition, this);
/* 172 */     if (!this.world.s_() && hasExplosions())
/*     */     {
/* 174 */       if (!CraftEventFactory.callFireworkExplodeEvent(this).isCancelled()) {
/* 175 */         explode();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 180 */     super.a(movingobjectpositionblock);
/*     */   }
/*     */   
/*     */   private boolean hasExplosions() {
/* 184 */     ItemStack itemstack = this.datawatcher.<ItemStack>get(FIREWORK_ITEM);
/* 185 */     NBTTagCompound nbttagcompound = itemstack.isEmpty() ? null : itemstack.b("Fireworks");
/* 186 */     NBTTagList nbttaglist = (nbttagcompound != null) ? nbttagcompound.getList("Explosions", 10) : null;
/*     */     
/* 188 */     return (nbttaglist != null && !nbttaglist.isEmpty());
/*     */   }
/*     */   
/*     */   private void m() {
/* 192 */     float f = 0.0F;
/* 193 */     ItemStack itemstack = this.datawatcher.<ItemStack>get(FIREWORK_ITEM);
/* 194 */     NBTTagCompound nbttagcompound = itemstack.isEmpty() ? null : itemstack.b("Fireworks");
/* 195 */     NBTTagList nbttaglist = (nbttagcompound != null) ? nbttagcompound.getList("Explosions", 10) : null;
/*     */     
/* 197 */     if (nbttaglist != null && !nbttaglist.isEmpty()) {
/* 198 */       f = 5.0F + (nbttaglist.size() * 2);
/*     */     }
/*     */     
/* 201 */     if (f > 0.0F) {
/* 202 */       if (this.ridingEntity != null) {
/* 203 */         CraftEventFactory.entityDamage = this;
/* 204 */         this.ridingEntity.damageEntity(DamageSource.a(this, getShooter()), 5.0F + (nbttaglist.size() * 2));
/* 205 */         CraftEventFactory.entityDamage = null;
/*     */       } 
/*     */       
/* 208 */       double d0 = 5.0D;
/* 209 */       Vec3D vec3d = getPositionVector();
/* 210 */       List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox().g(5.0D));
/* 211 */       Iterator<EntityLiving> iterator = list.iterator();
/*     */       
/* 213 */       while (iterator.hasNext()) {
/* 214 */         EntityLiving entityliving = iterator.next();
/*     */         
/* 216 */         if (entityliving != this.ridingEntity && h(entityliving) <= 25.0D) {
/* 217 */           boolean flag = false;
/*     */           
/* 219 */           for (int i = 0; i < 2; i++) {
/* 220 */             Vec3D vec3d1 = new Vec3D(entityliving.locX(), entityliving.e(0.5D * i), entityliving.locZ());
/* 221 */             MovingObjectPositionBlock movingobjectpositionblock = this.world.rayTrace(new RayTrace(vec3d, vec3d1, RayTrace.BlockCollisionOption.COLLIDER, RayTrace.FluidCollisionOption.NONE, this));
/*     */             
/* 223 */             if (movingobjectpositionblock.getType() == MovingObjectPosition.EnumMovingObjectType.MISS) {
/* 224 */               flag = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 229 */           if (flag) {
/* 230 */             float f1 = f * (float)Math.sqrt((5.0D - g(entityliving)) / 5.0D);
/*     */             
/* 232 */             CraftEventFactory.entityDamage = this;
/* 233 */             entityliving.damageEntity(DamageSource.a(this, getShooter()), f1);
/* 234 */             CraftEventFactory.entityDamage = null;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean n() {
/* 243 */     return ((OptionalInt)this.datawatcher.<OptionalInt>get(c)).isPresent();
/*     */   }
/*     */   
/*     */   public boolean isShotAtAngle() {
/* 247 */     return ((Boolean)this.datawatcher.<Boolean>get(SHOT_AT_ANGLE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 252 */     super.saveData(nbttagcompound);
/* 253 */     nbttagcompound.setInt("Life", this.ticksFlown);
/* 254 */     nbttagcompound.setInt("LifeTime", this.expectedLifespan);
/* 255 */     ItemStack itemstack = this.datawatcher.<ItemStack>get(FIREWORK_ITEM);
/*     */     
/* 257 */     if (!itemstack.isEmpty()) {
/* 258 */       nbttagcompound.set("FireworksItem", itemstack.save(new NBTTagCompound()));
/*     */     }
/*     */     
/* 261 */     nbttagcompound.setBoolean("ShotAtAngle", ((Boolean)this.datawatcher.<Boolean>get(SHOT_AT_ANGLE)).booleanValue());
/*     */     
/* 263 */     if (this.spawningEntity != null) {
/* 264 */       nbttagcompound.setUUID("SpawningEntity", this.spawningEntity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 271 */     super.loadData(nbttagcompound);
/* 272 */     this.ticksFlown = nbttagcompound.getInt("Life");
/* 273 */     this.expectedLifespan = nbttagcompound.getInt("LifeTime");
/* 274 */     ItemStack itemstack = ItemStack.a(nbttagcompound.getCompound("FireworksItem"));
/*     */     
/* 276 */     if (!itemstack.isEmpty()) {
/* 277 */       this.datawatcher.set(FIREWORK_ITEM, itemstack);
/*     */     }
/*     */     
/* 280 */     if (nbttagcompound.hasKey("ShotAtAngle")) {
/* 281 */       this.datawatcher.set(SHOT_AT_ANGLE, Boolean.valueOf(nbttagcompound.getBoolean("ShotAtAngle")));
/*     */     }
/*     */     
/* 284 */     if (nbttagcompound.hasUUID("SpawningEntity")) {
/* 285 */       this.spawningEntity = nbttagcompound.getUUID("SpawningEntity");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bK() {
/* 292 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 297 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFireworks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */