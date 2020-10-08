/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityWither
/*     */   extends EntityMonster
/*     */   implements IRangedEntity {
/*  18 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityWither.class, DataWatcherRegistry.b);
/*  19 */   private static final DataWatcherObject<Integer> c = DataWatcher.a((Class)EntityWither.class, DataWatcherRegistry.b);
/*  20 */   private static final DataWatcherObject<Integer> d = DataWatcher.a((Class)EntityWither.class, DataWatcherRegistry.b);
/*  21 */   private static final List<DataWatcherObject<Integer>> bo = (List<DataWatcherObject<Integer>>)ImmutableList.of(b, c, d);
/*  22 */   private static final DataWatcherObject<Integer> bp = DataWatcher.a((Class)EntityWither.class, DataWatcherRegistry.b);
/*  23 */   private final float[] bq = new float[2];
/*  24 */   private final float[] br = new float[2];
/*  25 */   private final float[] bs = new float[2];
/*  26 */   private final float[] bt = new float[2];
/*  27 */   private final int[] bu = new int[2]; private int bw; public final BossBattleServer bossBattle;
/*  28 */   private final int[] bv = new int[2]; private static final Predicate<EntityLiving> by;
/*     */   
/*     */   static {
/*  31 */     by = (entityliving -> 
/*  32 */       (entityliving.getMonsterType() != EnumMonsterType.UNDEAD && entityliving.eh()));
/*     */   }
/*  34 */   private static final PathfinderTargetCondition bz = (new PathfinderTargetCondition()).a(20.0D).a(by);
/*     */   
/*     */   public EntityWither(EntityTypes<? extends EntityWither> entitytypes, World world) {
/*  37 */     super((EntityTypes)entitytypes, world);
/*  38 */     this.bossBattle = (BossBattleServer)(new BossBattleServer(getScoreboardDisplayName(), BossBattle.BarColor.PURPLE, BossBattle.BarStyle.PROGRESS)).setDarkenSky(true);
/*  39 */     setHealth(getMaxHealth());
/*  40 */     getNavigation().d(true);
/*  41 */     this.f = 50;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  46 */     this.goalSelector.a(0, new a());
/*  47 */     this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 40, 20.0F));
/*  48 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  49 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  50 */     this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*  51 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, new Class[0]));
/*  52 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityInsentient.class, 0, false, false, by));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  57 */     super.initDatawatcher();
/*  58 */     this.datawatcher.register(b, Integer.valueOf(0));
/*  59 */     this.datawatcher.register(c, Integer.valueOf(0));
/*  60 */     this.datawatcher.register(d, Integer.valueOf(0));
/*  61 */     this.datawatcher.register(bp, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  66 */     super.saveData(nbttagcompound);
/*  67 */     nbttagcompound.setInt("Invul", getInvul());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  72 */     super.loadData(nbttagcompound);
/*  73 */     setInvul(nbttagcompound.getInt("Invul"));
/*  74 */     if (hasCustomName()) {
/*  75 */       this.bossBattle.a(getScoreboardDisplayName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomName(@Nullable IChatBaseComponent ichatbasecomponent) {
/*  82 */     super.setCustomName(ichatbasecomponent);
/*  83 */     this.bossBattle.a(getScoreboardDisplayName());
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  88 */     return SoundEffects.ENTITY_WITHER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  93 */     return SoundEffects.ENTITY_WITHER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  98 */     return SoundEffects.ENTITY_WITHER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 103 */     Vec3D vec3d = getMot().d(1.0D, 0.6D, 1.0D);
/*     */     
/* 105 */     if (!this.world.isClientSide && getHeadTarget(0) > 0) {
/* 106 */       Entity entity = this.world.getEntity(getHeadTarget(0));
/*     */       
/* 108 */       if (entity != null) {
/* 109 */         double d0 = vec3d.y;
/*     */         
/* 111 */         if (locY() < entity.locY() || (!S_() && locY() < entity.locY() + 5.0D)) {
/* 112 */           d0 = Math.max(0.0D, d0);
/* 113 */           d0 += 0.3D - d0 * 0.6000000238418579D;
/*     */         } 
/*     */         
/* 116 */         vec3d = new Vec3D(vec3d.x, d0, vec3d.z);
/* 117 */         Vec3D vec3d1 = new Vec3D(entity.locX() - locX(), 0.0D, entity.locZ() - locZ());
/*     */         
/* 119 */         if (c(vec3d1) > 9.0D) {
/* 120 */           Vec3D vec3d2 = vec3d1.d();
/*     */           
/* 122 */           vec3d = vec3d.add(vec3d2.x * 0.3D - vec3d.x * 0.6D, 0.0D, vec3d2.z * 0.3D - vec3d.z * 0.6D);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     setMot(vec3d);
/* 128 */     if (c(vec3d) > 0.05D) {
/* 129 */       this.yaw = (float)MathHelper.d(vec3d.z, vec3d.x) * 57.295776F - 90.0F;
/*     */     }
/*     */     
/* 132 */     super.movementTick();
/*     */     
/*     */     int i;
/*     */     
/* 136 */     for (i = 0; i < 2; i++) {
/* 137 */       this.bt[i] = this.br[i];
/* 138 */       this.bs[i] = this.bq[i];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 143 */     for (i = 0; i < 2; i++) {
/* 144 */       int k = getHeadTarget(i + 1);
/* 145 */       Entity entity1 = null;
/*     */       
/* 147 */       if (k > 0) {
/* 148 */         entity1 = this.world.getEntity(k);
/*     */       }
/*     */       
/* 151 */       if (entity1 != null) {
/* 152 */         double d1 = u(i + 1);
/* 153 */         double d2 = v(i + 1);
/* 154 */         double d3 = w(i + 1);
/* 155 */         double d4 = entity1.locX() - d1;
/* 156 */         double d5 = entity1.getHeadY() - d2;
/* 157 */         double d6 = entity1.locZ() - d3;
/* 158 */         double d7 = MathHelper.sqrt(d4 * d4 + d6 * d6);
/* 159 */         float f = (float)(MathHelper.d(d6, d4) * 57.2957763671875D) - 90.0F;
/* 160 */         float f1 = (float)-(MathHelper.d(d5, d7) * 57.2957763671875D);
/*     */         
/* 162 */         this.bq[i] = a(this.bq[i], f1, 40.0F);
/* 163 */         this.br[i] = a(this.br[i], f, 10.0F);
/*     */       } else {
/* 165 */         this.br[i] = a(this.br[i], this.aA, 10.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     boolean flag = S_();
/*     */     int j;
/* 171 */     for (j = 0; j < 3; j++) {
/* 172 */       double d8 = u(j);
/* 173 */       double d9 = v(j);
/* 174 */       double d10 = w(j);
/*     */       
/* 176 */       this.world.addParticle(Particles.SMOKE, d8 + this.random.nextGaussian() * 0.30000001192092896D, d9 + this.random.nextGaussian() * 0.30000001192092896D, d10 + this.random.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);
/* 177 */       if (flag && this.world.random.nextInt(4) == 0) {
/* 178 */         this.world.addParticle(Particles.ENTITY_EFFECT, d8 + this.random.nextGaussian() * 0.30000001192092896D, d9 + this.random.nextGaussian() * 0.30000001192092896D, d10 + this.random.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
/*     */       }
/*     */     } 
/*     */     
/* 182 */     if (getInvul() > 0) {
/* 183 */       for (j = 0; j < 3; j++) {
/* 184 */         this.world.addParticle(Particles.ENTITY_EFFECT, locX() + this.random.nextGaussian(), locY() + (this.random.nextFloat() * 3.3F), locZ() + this.random.nextGaussian(), 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 194 */     if (getInvul() > 0) {
/* 195 */       int i = getInvul() - 1;
/* 196 */       if (i <= 0) {
/* 197 */         Explosion.Effect explosion_effect = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? Explosion.Effect.DESTROY : Explosion.Effect.NONE;
/*     */ 
/*     */         
/* 200 */         ExplosionPrimeEvent event = new ExplosionPrimeEvent((Entity)getBukkitEntity(), 7.0F, false);
/* 201 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 203 */         if (!event.isCancelled()) {
/* 204 */           this.world.createExplosion(this, locX(), getHeadY(), locZ(), event.getRadius(), event.getFire(), explosion_effect);
/*     */         }
/*     */ 
/*     */         
/* 208 */         if (!isSilent()) {
/*     */ 
/*     */           
/* 211 */           int viewDistance = ((WorldServer)this.world).getServer().getViewDistance() * 16;
/* 212 */           for (EntityPlayer player : this.world.getPlayers()) {
/*     */             
/* 214 */             double deltaX = locX() - player.locX();
/* 215 */             double deltaZ = locZ() - player.locZ();
/* 216 */             double distanceSquared = deltaX * deltaX + deltaZ * deltaZ;
/* 217 */             if (this.world.spigotConfig.witherSpawnSoundRadius > 0 && distanceSquared > (this.world.spigotConfig.witherSpawnSoundRadius * this.world.spigotConfig.witherSpawnSoundRadius))
/* 218 */               continue;  if (distanceSquared > (viewDistance * viewDistance)) {
/* 219 */               double deltaLength = Math.sqrt(distanceSquared);
/* 220 */               double relativeX = player.locX() + deltaX / deltaLength * viewDistance;
/* 221 */               double relativeZ = player.locZ() + deltaZ / deltaLength * viewDistance;
/* 222 */               player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1023, new BlockPosition((int)relativeX, (int)locY(), (int)relativeZ), 0, true)); continue;
/*     */             } 
/* 224 */             player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1023, getChunkCoordinates(), 0, true));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 231 */       setInvul(i);
/* 232 */       if (this.ticksLived % 10 == 0) {
/* 233 */         heal(10.0F, EntityRegainHealthEvent.RegainReason.WITHER_SPAWN);
/*     */       }
/*     */     } else {
/*     */       
/* 237 */       super.mobTick();
/*     */       
/*     */       int i;
/*     */       
/* 241 */       for (i = 1; i < 3; i++) {
/* 242 */         if (this.ticksLived >= this.bu[i - 1]) {
/* 243 */           this.bu[i - 1] = this.ticksLived + 10 + this.random.nextInt(10);
/* 244 */           if (this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) {
/* 245 */             int k = i - 1;
/* 246 */             int l = this.bv[i - 1];
/*     */             
/* 248 */             this.bv[k] = this.bv[i - 1] + 1;
/* 249 */             if (l > 15) {
/* 250 */               float f = 10.0F;
/* 251 */               float f1 = 5.0F;
/* 252 */               double d0 = MathHelper.a(this.random, locX() - 10.0D, locX() + 10.0D);
/* 253 */               double d1 = MathHelper.a(this.random, locY() - 5.0D, locY() + 5.0D);
/* 254 */               double d2 = MathHelper.a(this.random, locZ() - 10.0D, locZ() + 10.0D);
/*     */               
/* 256 */               a(i + 1, d0, d1, d2, true);
/* 257 */               this.bv[i - 1] = 0;
/*     */             } 
/*     */           } 
/*     */           
/* 261 */           int j = getHeadTarget(i);
/* 262 */           if (j > 0) {
/* 263 */             Entity entity = this.world.getEntity(j);
/*     */             
/* 265 */             if (entity != null && entity.isAlive() && h(entity) <= 900.0D && hasLineOfSight(entity)) {
/* 266 */               if (entity instanceof EntityHuman && ((EntityHuman)entity).abilities.isInvulnerable) {
/* 267 */                 setHeadTarget(i, 0);
/*     */               } else {
/* 269 */                 a(i + 1, (EntityLiving)entity);
/* 270 */                 this.bu[i - 1] = this.ticksLived + 40 + this.random.nextInt(20);
/* 271 */                 this.bv[i - 1] = 0;
/*     */               } 
/*     */             } else {
/* 274 */               setHeadTarget(i, 0);
/*     */             } 
/*     */           } else {
/* 277 */             List<EntityLiving> list = this.world.a(EntityLiving.class, bz, this, getBoundingBox().grow(20.0D, 8.0D, 20.0D));
/*     */             
/* 279 */             for (int i1 = 0; i1 < 10 && !list.isEmpty(); i1++) {
/* 280 */               EntityLiving entityliving = list.get(this.random.nextInt(list.size()));
/*     */               
/* 282 */               if (entityliving != this && entityliving.isAlive() && hasLineOfSight(entityliving)) {
/* 283 */                 if (entityliving instanceof EntityHuman) {
/* 284 */                   if (!((EntityHuman)entityliving).abilities.isInvulnerable)
/* 285 */                   { if (!CraftEventFactory.callEntityTargetLivingEvent(this, entityliving, EntityTargetEvent.TargetReason.CLOSEST_PLAYER).isCancelled())
/* 286 */                     { setHeadTarget(i, entityliving.getId()); break; }  }
/*     */                   else { break; }
/*     */                 
/* 289 */                 } else if (!CraftEventFactory.callEntityTargetLivingEvent(this, entityliving, EntityTargetEvent.TargetReason.CLOSEST_ENTITY).isCancelled()) {
/* 290 */                   setHeadTarget(i, entityliving.getId());
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } else {
/* 295 */                 list.remove(entityliving);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 301 */       if (getGoalTarget() != null) {
/* 302 */         setHeadTarget(0, getGoalTarget().getId());
/*     */       } else {
/* 304 */         setHeadTarget(0, 0);
/*     */       } 
/*     */       
/* 307 */       if (this.bw > 0) {
/* 308 */         this.bw--;
/* 309 */         if (this.bw == 0 && this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
/* 310 */           i = MathHelper.floor(locY());
/* 311 */           int j = MathHelper.floor(locX());
/* 312 */           int j1 = MathHelper.floor(locZ());
/* 313 */           boolean flag = false;
/*     */           
/* 315 */           for (int k1 = -1; k1 <= 1; k1++) {
/* 316 */             for (int l1 = -1; l1 <= 1; l1++) {
/* 317 */               for (int i2 = 0; i2 <= 3; i2++) {
/* 318 */                 int j2 = j + k1;
/* 319 */                 int k2 = i + i2;
/* 320 */                 int l2 = j1 + l1;
/* 321 */                 BlockPosition blockposition = new BlockPosition(j2, k2, l2);
/* 322 */                 IBlockData iblockdata = this.world.getType(blockposition);
/*     */                 
/* 324 */                 if (c(iblockdata))
/*     */                 {
/* 326 */                   if (!CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, Blocks.AIR.getBlockData()).isCancelled())
/*     */                   {
/*     */ 
/*     */                     
/* 330 */                     flag = (this.world.a(blockposition, true, this) || flag);
/*     */                   }
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/* 336 */           if (flag) {
/* 337 */             this.world.a((EntityHuman)null, 1022, getChunkCoordinates(), 0);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 342 */       if (this.ticksLived % 20 == 0) {
/* 343 */         heal(1.0F, EntityRegainHealthEvent.RegainReason.REGEN);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 348 */     this.bossBattle.setProgress(getHealth() / getMaxHealth());
/*     */   }
/*     */   
/*     */   public static boolean c(IBlockData iblockdata) {
/* 352 */     return (!iblockdata.isAir() && !TagsBlock.WITHER_IMMUNE.isTagged(iblockdata.getBlock()));
/*     */   }
/*     */   
/*     */   public void beginSpawnSequence() {
/* 356 */     setInvul(220);
/* 357 */     setHealth(getMaxHealth() / 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, Vec3D vec3d) {}
/*     */ 
/*     */   
/*     */   public void b(EntityPlayer entityplayer) {
/* 365 */     super.b(entityplayer);
/* 366 */     this.bossBattle.addPlayer(entityplayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(EntityPlayer entityplayer) {
/* 371 */     super.c(entityplayer);
/* 372 */     this.bossBattle.removePlayer(entityplayer);
/*     */   }
/*     */   
/*     */   private double u(int i) {
/* 376 */     if (i <= 0) {
/* 377 */       return locX();
/*     */     }
/* 379 */     float f = (this.aA + (180 * (i - 1))) * 0.017453292F;
/* 380 */     float f1 = MathHelper.cos(f);
/*     */     
/* 382 */     return locX() + f1 * 1.3D;
/*     */   }
/*     */ 
/*     */   
/*     */   private double v(int i) {
/* 387 */     return (i <= 0) ? (locY() + 3.0D) : (locY() + 2.2D);
/*     */   }
/*     */   
/*     */   private double w(int i) {
/* 391 */     if (i <= 0) {
/* 392 */       return locZ();
/*     */     }
/* 394 */     float f = (this.aA + (180 * (i - 1))) * 0.017453292F;
/* 395 */     float f1 = MathHelper.sin(f);
/*     */     
/* 397 */     return locZ() + f1 * 1.3D;
/*     */   }
/*     */ 
/*     */   
/*     */   private float a(float f, float f1, float f2) {
/* 402 */     float f3 = MathHelper.g(f1 - f);
/*     */     
/* 404 */     if (f3 > f2) {
/* 405 */       f3 = f2;
/*     */     }
/*     */     
/* 408 */     if (f3 < -f2) {
/* 409 */       f3 = -f2;
/*     */     }
/*     */     
/* 412 */     return f + f3;
/*     */   }
/*     */   
/*     */   private void a(int i, EntityLiving entityliving) {
/* 416 */     a(i, entityliving.locX(), entityliving.locY() + entityliving.getHeadHeight() * 0.5D, entityliving.locZ(), (i == 0 && this.random.nextFloat() < 0.001F));
/*     */   }
/*     */   
/*     */   private void a(int i, double d0, double d1, double d2, boolean flag) {
/* 420 */     if (!isSilent()) {
/* 421 */       this.world.a((EntityHuman)null, 1024, getChunkCoordinates(), 0);
/*     */     }
/*     */     
/* 424 */     double d3 = u(i);
/* 425 */     double d4 = v(i);
/* 426 */     double d5 = w(i);
/* 427 */     double d6 = d0 - d3;
/* 428 */     double d7 = d1 - d4;
/* 429 */     double d8 = d2 - d5;
/* 430 */     EntityWitherSkull entitywitherskull = new EntityWitherSkull(this.world, this, d6, d7, d8);
/*     */     
/* 432 */     entitywitherskull.setShooter(this);
/* 433 */     if (flag) {
/* 434 */       entitywitherskull.setCharged(true);
/*     */     }
/*     */     
/* 437 */     entitywitherskull.setPositionRaw(d3, d4, d5);
/* 438 */     this.world.addEntity(entitywitherskull);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 443 */     a(0, entityliving);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 448 */     if (isInvulnerable(damagesource))
/* 449 */       return false; 
/* 450 */     if (damagesource != DamageSource.DROWN && !(damagesource.getEntity() instanceof EntityWither)) {
/* 451 */       if (getInvul() > 0 && damagesource != DamageSource.OUT_OF_WORLD) {
/* 452 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 456 */       if (S_()) {
/* 457 */         Entity entity1 = damagesource.j();
/* 458 */         if (entity1 instanceof EntityArrow) {
/* 459 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 463 */       Entity entity = damagesource.getEntity();
/* 464 */       if (entity != null && !(entity instanceof EntityHuman) && entity instanceof EntityLiving && ((EntityLiving)entity).getMonsterType() == getMonsterType()) {
/* 465 */         return false;
/*     */       }
/* 467 */       if (this.bw <= 0) {
/* 468 */         this.bw = 20;
/*     */       }
/*     */       
/* 471 */       for (int i = 0; i < this.bv.length; i++) {
/* 472 */         this.bv[i] = this.bv[i] + 3;
/*     */       }
/*     */       
/* 475 */       return super.damageEntity(damagesource, f);
/*     */     } 
/*     */ 
/*     */     
/* 479 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
/* 485 */     super.dropDeathLoot(damagesource, i, flag);
/* 486 */     EntityItem entityitem = a(Items.NETHER_STAR);
/*     */     
/* 488 */     if (entityitem != null) {
/* 489 */       entityitem.r();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkDespawn() {
/* 496 */     if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL && L()) {
/* 497 */       die();
/*     */     } else {
/* 499 */       this.ticksFarFromPlayer = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/* 505 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffect mobeffect) {
/* 510 */     return false;
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/* 514 */     return EntityMonster.eR().a(GenericAttributes.MAX_HEALTH, 300.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.6000000238418579D).a(GenericAttributes.FOLLOW_RANGE, 40.0D).a(GenericAttributes.ARMOR, 4.0D);
/*     */   }
/*     */   
/*     */   public int getInvul() {
/* 518 */     return ((Integer)this.datawatcher.<Integer>get(bp)).intValue();
/*     */   }
/*     */   
/*     */   public void setInvul(int i) {
/* 522 */     this.datawatcher.set(bp, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int getHeadTarget(int i) {
/* 526 */     return ((Integer)this.datawatcher.<Integer>get(bo.get(i))).intValue();
/*     */   }
/*     */   
/*     */   public void setHeadTarget(int i, int j) {
/* 530 */     this.datawatcher.set(bo.get(i), Integer.valueOf(j));
/*     */   }
/*     */   
/*     */   public boolean S_() {
/* 534 */     return (getHealth() <= getMaxHealth() / 2.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 539 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean n(Entity entity) {
/* 544 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPortal() {
/* 549 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean d(MobEffect mobeffect) {
/* 554 */     return (mobeffect.getMobEffect() == MobEffects.WITHER) ? false : super.d(mobeffect);
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoal {
/*     */     public a() {
/* 560 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.JUMP, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 565 */       return (EntityWither.this.getInvul() > 0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */