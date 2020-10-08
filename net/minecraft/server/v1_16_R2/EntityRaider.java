/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public abstract class EntityRaider extends EntityMonsterPatrolling {
/*  14 */   protected static final DataWatcherObject<Boolean> c = DataWatcher.a((Class)EntityRaider.class, DataWatcherRegistry.i); static {
/*  15 */     b = (entityitem -> 
/*  16 */       (!entityitem.p() && entityitem.isAlive() && ItemStack.matches(entityitem.getItemStack(), Raid.s())));
/*     */   }
/*     */   private static final Predicate<EntityItem> b; @Nullable
/*     */   protected Raid d;
/*     */   private int bo;
/*     */   private boolean canJoinRaid;
/*     */   private int bq;
/*     */   
/*     */   protected EntityRaider(EntityTypes<? extends EntityRaider> entitytypes, World world) {
/*  25 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  30 */     super.initPathfinder();
/*  31 */     this.goalSelector.a(1, new b<>(this));
/*  32 */     this.goalSelector.a(3, new PathfinderGoalRaid<>(this));
/*  33 */     this.goalSelector.a(4, new d(this, 1.0499999523162842D, 1));
/*  34 */     this.goalSelector.a(5, new c(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  39 */     super.initDatawatcher();
/*  40 */     this.datawatcher.register(c, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCanJoinRaid() {
/*  46 */     return this.canJoinRaid;
/*     */   }
/*     */   
/*     */   public void setCanJoinRaid(boolean flag) {
/*  50 */     this.canJoinRaid = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  55 */     if (this.world instanceof WorldServer && isAlive()) {
/*  56 */       Raid raid = fa();
/*     */       
/*  58 */       if (isCanJoinRaid()) {
/*  59 */         if (raid == null) {
/*  60 */           if (this.world.getTime() % 20L == 0L) {
/*  61 */             Raid raid1 = ((WorldServer)this.world).b_(getChunkCoordinates());
/*     */             
/*  63 */             if (raid1 != null && PersistentRaid.a(this, raid1)) {
/*  64 */               raid1.a(raid1.getGroupsSpawned(), this, (BlockPosition)null, true);
/*     */             }
/*     */           } 
/*     */         } else {
/*  68 */           EntityLiving entityliving = getGoalTarget();
/*     */           
/*  70 */           if (entityliving != null && (entityliving.getEntityType() == EntityTypes.PLAYER || entityliving.getEntityType() == EntityTypes.IRON_GOLEM)) {
/*  71 */             this.ticksFarFromPlayer = 0;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  77 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eQ() {
/*  82 */     this.ticksFarFromPlayer += 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/*  87 */     if (this.world instanceof WorldServer) {
/*  88 */       Entity entity = damagesource.getEntity();
/*  89 */       Raid raid = fa();
/*     */       
/*  91 */       if (raid != null) {
/*  92 */         if (isPatrolLeader()) {
/*  93 */           raid.c(fc());
/*     */         }
/*     */         
/*  96 */         if (entity != null && entity.getEntityType() == EntityTypes.PLAYER) {
/*  97 */           raid.a(entity);
/*     */         }
/*     */         
/* 100 */         raid.a(this, false);
/*     */       } 
/*     */       
/* 103 */       if (isPatrolLeader() && raid == null && ((WorldServer)this.world).b_(getChunkCoordinates()) == null) {
/* 104 */         ItemStack itemstack = getEquipment(EnumItemSlot.HEAD);
/* 105 */         EntityHuman entityhuman = null;
/*     */         
/* 107 */         if (entity instanceof EntityHuman) {
/* 108 */           entityhuman = (EntityHuman)entity;
/* 109 */         } else if (entity instanceof EntityWolf) {
/* 110 */           EntityWolf entitywolf = (EntityWolf)entity;
/* 111 */           EntityLiving entityliving = entitywolf.getOwner();
/*     */           
/* 113 */           if (entitywolf.isTamed() && entityliving instanceof EntityHuman) {
/* 114 */             entityhuman = (EntityHuman)entityliving;
/*     */           }
/*     */         } 
/*     */         
/* 118 */         if (!itemstack.isEmpty() && ItemStack.matches(itemstack, Raid.s()) && entityhuman != null) {
/* 119 */           MobEffect mobeffect = entityhuman.getEffect(MobEffects.BAD_OMEN);
/* 120 */           byte b0 = 1;
/*     */ 
/*     */           
/* 123 */           if (mobeffect != null) {
/* 124 */             i = b0 + mobeffect.getAmplifier();
/* 125 */             entityhuman.c(MobEffects.BAD_OMEN);
/*     */           } else {
/* 127 */             i = b0 - 1;
/*     */           } 
/*     */           
/* 130 */           int i = MathHelper.clamp(i, 0, 4);
/* 131 */           MobEffect mobeffect1 = new MobEffect(MobEffects.BAD_OMEN, 120000, i, false, false, true);
/*     */           
/* 133 */           if (!this.world.getGameRules().getBoolean(GameRules.DISABLE_RAIDS)) {
/* 134 */             entityhuman.addEffect(mobeffect1, EntityPotionEffectEvent.Cause.PATROL_CAPTAIN);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     super.die(damagesource);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eT() {
/* 145 */     return !fb();
/*     */   }
/*     */   
/*     */   public void a(@Nullable Raid raid) {
/* 149 */     this.d = raid;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Raid fa() {
/* 154 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean fb() {
/* 158 */     return (fa() != null && fa().v());
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 162 */     this.bo = i;
/*     */   }
/*     */   
/*     */   public int fc() {
/* 166 */     return this.bo;
/*     */   }
/*     */   
/*     */   public void x(boolean flag) {
/* 170 */     this.datawatcher.set(c, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 175 */     super.saveData(nbttagcompound);
/* 176 */     nbttagcompound.setInt("Wave", this.bo);
/* 177 */     nbttagcompound.setBoolean("CanJoinRaid", this.canJoinRaid);
/* 178 */     if (this.d != null) {
/* 179 */       nbttagcompound.setInt("RaidId", this.d.getId());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 186 */     super.loadData(nbttagcompound);
/* 187 */     this.bo = nbttagcompound.getInt("Wave");
/* 188 */     this.canJoinRaid = nbttagcompound.getBoolean("CanJoinRaid");
/* 189 */     if (nbttagcompound.hasKeyOfType("RaidId", 3)) {
/* 190 */       if (this.world instanceof WorldServer) {
/* 191 */         this.d = ((WorldServer)this.world).getPersistentRaid().a(nbttagcompound.getInt("RaidId"));
/*     */       }
/*     */       
/* 194 */       if (this.d != null) {
/* 195 */         this.d.a(this.bo, this, false);
/* 196 */         if (isPatrolLeader()) {
/* 197 */           this.d.a(this.bo, this);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(EntityItem entityitem) {
/* 206 */     ItemStack itemstack = entityitem.getItemStack();
/* 207 */     boolean flag = (fb() && fa().b(fc()) != null);
/*     */     
/* 209 */     if (fb() && !flag && ItemStack.matches(itemstack, Raid.s())) {
/* 210 */       EnumItemSlot enumitemslot = EnumItemSlot.HEAD;
/* 211 */       ItemStack itemstack1 = getEquipment(enumitemslot);
/* 212 */       double d0 = e(enumitemslot);
/*     */       
/* 214 */       if (!itemstack1.isEmpty() && Math.max(this.random.nextFloat() - 0.1F, 0.0F) < d0) {
/* 215 */         a(itemstack1);
/*     */       }
/*     */       
/* 218 */       a(entityitem);
/* 219 */       setSlot(enumitemslot, itemstack);
/* 220 */       receive(entityitem, itemstack.getCount());
/* 221 */       entityitem.die();
/* 222 */       fa().a(fc(), this);
/* 223 */       setPatrolLeader(true);
/*     */     } else {
/* 225 */       super.b(entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/* 232 */     return (fa() == null) ? super.isTypeNotPersistent(d0) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSpecialPersistence() {
/* 237 */     return (super.isSpecialPersistence() || fa() != null);
/*     */   }
/*     */   
/*     */   public int fe() {
/* 241 */     return this.bq;
/*     */   }
/*     */   
/*     */   public void b(int i) {
/* 245 */     this.bq = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 250 */     if (fb()) {
/* 251 */       fa().updateProgress();
/*     */     }
/*     */     
/* 254 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 260 */     setCanJoinRaid((getEntityType() != EntityTypes.WITCH || enummobspawn != EnumMobSpawn.NATURAL));
/* 261 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   public abstract void a(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract SoundEffect eL();
/*     */   
/*     */   static class d extends PathfinderGoal { private final EntityRaider a;
/*     */     private final double b;
/*     */     private BlockPosition c;
/* 271 */     private final List<BlockPosition> d = Lists.newArrayList();
/*     */     private final int e;
/*     */     private boolean f;
/*     */     
/*     */     public d(EntityRaider entityraider, double d0, int i) {
/* 276 */       this.a = entityraider;
/* 277 */       this.b = d0;
/* 278 */       this.e = i;
/* 279 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 284 */       j();
/* 285 */       return (g() && h() && this.a.getGoalTarget() == null);
/*     */     }
/*     */     
/*     */     private boolean g() {
/* 289 */       return (this.a.fb() && !this.a.fa().a());
/*     */     }
/*     */     
/*     */     private boolean h() {
/* 293 */       WorldServer worldserver = (WorldServer)this.a.world;
/* 294 */       BlockPosition blockposition = this.a.getChunkCoordinates();
/* 295 */       Optional<BlockPosition> optional = worldserver.y().a(villageplacetype -> (villageplacetype == VillagePlaceType.r), this::a, VillagePlace.Occupancy.ANY, blockposition, 48, this.a.random);
/*     */ 
/*     */ 
/*     */       
/* 299 */       if (!optional.isPresent()) {
/* 300 */         return false;
/*     */       }
/* 302 */       this.c = ((BlockPosition)optional.get()).immutableCopy();
/* 303 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 309 */       return this.a.getNavigation().m() ? false : ((this.a.getGoalTarget() == null && !this.c.a(this.a.getPositionVector(), (this.a.getWidth() + this.e)) && !this.f));
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 314 */       if (this.c.a(this.a.getPositionVector(), this.e)) {
/* 315 */         this.d.add(this.c);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void c() {
/* 322 */       super.c();
/* 323 */       this.a.n(0);
/* 324 */       this.a.getNavigation().a(this.c.getX(), this.c.getY(), this.c.getZ(), this.b);
/* 325 */       this.f = false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 330 */       if (this.a.getNavigation().m()) {
/* 331 */         Vec3D vec3d = Vec3D.c(this.c);
/* 332 */         Vec3D vec3d1 = RandomPositionGenerator.a(this.a, 16, 7, vec3d, 0.3141592741012573D);
/*     */         
/* 334 */         if (vec3d1 == null) {
/* 335 */           vec3d1 = RandomPositionGenerator.b(this.a, 8, 7, vec3d);
/*     */         }
/*     */         
/* 338 */         if (vec3d1 == null) {
/* 339 */           this.f = true;
/*     */           
/*     */           return;
/*     */         } 
/* 343 */         this.a.getNavigation().a(vec3d1.x, vec3d1.y, vec3d1.z, this.b);
/*     */       } 
/*     */     }
/*     */     
/*     */     private boolean a(BlockPosition blockposition) {
/*     */       BlockPosition blockposition1;
/* 349 */       Iterator<BlockPosition> iterator = this.d.iterator();
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 354 */         if (!iterator.hasNext()) {
/* 355 */           return true;
/*     */         }
/*     */         
/* 358 */         blockposition1 = iterator.next();
/* 359 */       } while (!Objects.equals(blockposition, blockposition1));
/*     */       
/* 361 */       return false;
/*     */     }
/*     */     
/*     */     private void j() {
/* 365 */       if (this.d.size() > 2) {
/* 366 */         this.d.remove(0);
/*     */       }
/*     */     } }
/*     */ 
/*     */   
/*     */   public class a
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityRaider c;
/*     */     private final float d;
/* 376 */     public final PathfinderTargetCondition a = (new PathfinderTargetCondition()).a(8.0D).d().a().b().c().e();
/*     */     
/*     */     public a(EntityIllagerAbstract entityillagerabstract, float f) {
/* 379 */       this.c = entityillagerabstract;
/* 380 */       this.d = f * f;
/* 381 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 386 */       EntityLiving entityliving = this.c.getLastDamager();
/*     */       
/* 388 */       return (this.c.fa() == null && this.c.isPatrolling() && this.c.getGoalTarget() != null && !this.c.isAggressive() && (entityliving == null || entityliving.getEntityType() != EntityTypes.PLAYER));
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 393 */       super.c();
/* 394 */       this.c.getNavigation().o();
/* 395 */       List<EntityRaider> list = this.c.world.a(EntityRaider.class, this.a, this.c, this.c.getBoundingBox().grow(8.0D, 8.0D, 8.0D));
/* 396 */       Iterator<EntityRaider> iterator = list.iterator();
/*     */       
/* 398 */       while (iterator.hasNext()) {
/* 399 */         EntityRaider entityraider = iterator.next();
/*     */         
/* 401 */         entityraider.setGoalTarget(this.c.getGoalTarget(), EntityTargetEvent.TargetReason.FOLLOW_LEADER, true);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void d() {
/* 408 */       super.d();
/* 409 */       EntityLiving entityliving = this.c.getGoalTarget();
/*     */       
/* 411 */       if (entityliving != null) {
/* 412 */         List<EntityRaider> list = this.c.world.a(EntityRaider.class, this.a, this.c, this.c.getBoundingBox().grow(8.0D, 8.0D, 8.0D));
/* 413 */         Iterator<EntityRaider> iterator = list.iterator();
/*     */         
/* 415 */         while (iterator.hasNext()) {
/* 416 */           EntityRaider entityraider = iterator.next();
/*     */           
/* 418 */           entityraider.setGoalTarget(this.c.getGoalTarget(), EntityTargetEvent.TargetReason.FOLLOW_LEADER, true);
/* 419 */           entityraider.setAggressive(true);
/*     */         } 
/*     */         
/* 422 */         this.c.setAggressive(true);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 429 */       EntityLiving entityliving = this.c.getGoalTarget();
/*     */       
/* 431 */       if (entityliving != null) {
/* 432 */         if (this.c.h(entityliving) > this.d) {
/* 433 */           this.c.getControllerLook().a(entityliving, 30.0F, 30.0F);
/* 434 */           if (this.c.random.nextInt(50) == 0) {
/* 435 */             this.c.F();
/*     */           }
/*     */         } else {
/* 438 */           this.c.setAggressive(true);
/*     */         } 
/*     */         
/* 441 */         super.e();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class c
/*     */     extends PathfinderGoal {
/*     */     private final EntityRaider b;
/*     */     
/*     */     c(EntityRaider entityraider) {
/* 451 */       this.b = entityraider;
/* 452 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 457 */       Raid raid = this.b.fa();
/*     */       
/* 459 */       return (this.b.isAlive() && this.b.getGoalTarget() == null && raid != null && raid.isLoss());
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 464 */       this.b.x(true);
/* 465 */       super.c();
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 470 */       this.b.x(false);
/* 471 */       super.d();
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 476 */       if (!this.b.isSilent() && this.b.random.nextInt(100) == 0) {
/* 477 */         EntityRaider.this.playSound(EntityRaider.this.eL(), EntityRaider.this.getSoundVolume(), EntityRaider.this.dG());
/*     */       }
/*     */       
/* 480 */       if (!this.b.isPassenger() && this.b.random.nextInt(50) == 0) {
/* 481 */         this.b.getControllerJump().jump();
/*     */       }
/*     */       
/* 484 */       super.e();
/*     */     } }
/*     */   
/*     */   public class b<T extends EntityRaider> extends PathfinderGoal { private final T b;
/*     */     
/*     */     private T getRaider() {
/* 490 */       return this.b;
/*     */     }
/*     */     public b(T entityraider) {
/* 493 */       this.b = entityraider;
/* 494 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 499 */       if (!((EntityRaider)getRaider()).world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) || !getRaider().canPickupLoot()) return false; 
/* 500 */       Raid raid = this.b.fa();
/*     */       
/* 502 */       if (this.b.fb() && !this.b.fa().a() && this.b.eN() && !ItemStack.matches(this.b.getEquipment(EnumItemSlot.HEAD), Raid.s())) {
/* 503 */         EntityRaider entityraider = raid.b(this.b.fc());
/*     */         
/* 505 */         if (entityraider == null || !entityraider.isAlive()) {
/* 506 */           List<EntityItem> list = ((EntityRaider)this.b).world.a(EntityItem.class, this.b.getBoundingBox().grow(16.0D, 8.0D, 16.0D), EntityRaider.b);
/*     */           
/* 508 */           if (!list.isEmpty()) {
/* 509 */             return this.b.getNavigation().a(list.get(0), 1.149999976158142D);
/*     */           }
/*     */         } 
/*     */         
/* 513 */         return false;
/*     */       } 
/* 515 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 521 */       if (this.b.getNavigation().h().a(this.b.getPositionVector(), 1.414D)) {
/* 522 */         List<EntityItem> list = ((EntityRaider)this.b).world.a(EntityItem.class, this.b.getBoundingBox().grow(4.0D, 4.0D, 4.0D), EntityRaider.b);
/*     */         
/* 524 */         if (!list.isEmpty())
/* 525 */           this.b.b(list.get(0)); 
/*     */       } 
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityRaider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */