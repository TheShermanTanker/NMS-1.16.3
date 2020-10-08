/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.CreeperIgniteEvent;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.CreeperPowerEvent;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityCreeper extends EntityMonster {
/*  13 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityCreeper.class, DataWatcherRegistry.b);
/*  14 */   private static final DataWatcherObject<Boolean> POWERED = DataWatcher.a((Class)EntityCreeper.class, DataWatcherRegistry.i); private static final DataWatcherObject<Boolean> d;
/*  15 */   private static final DataWatcherObject<Boolean> isIgnitedDW = d = DataWatcher.a((Class)EntityCreeper.class, DataWatcherRegistry.i);
/*     */   private int bo;
/*     */   public int fuseTicks;
/*  18 */   public int maxFuseTicks = 30;
/*  19 */   public int explosionRadius = 3;
/*     */   private int bs;
/*     */   
/*     */   public EntityCreeper(EntityTypes<? extends EntityCreeper> entitytypes, World world) {
/*  23 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  28 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  29 */     this.goalSelector.a(2, new PathfinderGoalSwell(this));
/*  30 */     this.goalSelector.a(3, new PathfinderGoalAvoidTarget<>(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
/*  31 */     this.goalSelector.a(3, new PathfinderGoalAvoidTarget<>(this, EntityCat.class, 6.0F, 1.0D, 1.2D));
/*  32 */     this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*  33 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 0.8D));
/*  34 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  35 */     this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
/*  36 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  37 */     this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  41 */     return EntityMonster.eR().a(GenericAttributes.MOVEMENT_SPEED, 0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int bO() {
/*  46 */     return (getGoalTarget() == null) ? 3 : (3 + (int)(getHealth() - 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/*  51 */     boolean flag = super.b(f, f1);
/*     */     
/*  53 */     this.fuseTicks = (int)(this.fuseTicks + f * 1.5F);
/*  54 */     if (this.fuseTicks > this.maxFuseTicks - 5) {
/*  55 */       this.fuseTicks = this.maxFuseTicks - 5;
/*     */     }
/*     */     
/*  58 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  63 */     super.initDatawatcher();
/*  64 */     this.datawatcher.register(b, Integer.valueOf(-1));
/*  65 */     this.datawatcher.register(POWERED, Boolean.valueOf(false));
/*  66 */     this.datawatcher.register(d, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  71 */     super.saveData(nbttagcompound);
/*  72 */     if (((Boolean)this.datawatcher.<Boolean>get(POWERED)).booleanValue()) {
/*  73 */       nbttagcompound.setBoolean("powered", true);
/*     */     }
/*     */     
/*  76 */     nbttagcompound.setShort("Fuse", (short)this.maxFuseTicks);
/*  77 */     nbttagcompound.setByte("ExplosionRadius", (byte)this.explosionRadius);
/*  78 */     nbttagcompound.setBoolean("ignited", isIgnited());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  83 */     super.loadData(nbttagcompound);
/*  84 */     this.datawatcher.set(POWERED, Boolean.valueOf(nbttagcompound.getBoolean("powered")));
/*  85 */     if (nbttagcompound.hasKeyOfType("Fuse", 99)) {
/*  86 */       this.maxFuseTicks = nbttagcompound.getShort("Fuse");
/*     */     }
/*     */     
/*  89 */     if (nbttagcompound.hasKeyOfType("ExplosionRadius", 99)) {
/*  90 */       this.explosionRadius = nbttagcompound.getByte("ExplosionRadius");
/*     */     }
/*     */     
/*  93 */     if (nbttagcompound.getBoolean("ignited")) {
/*  94 */       ignite();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 101 */     if (isAlive()) {
/* 102 */       this.bo = this.fuseTicks;
/* 103 */       if (isIgnited()) {
/* 104 */         a(1);
/*     */       }
/*     */       
/* 107 */       int i = eK();
/*     */       
/* 109 */       if (i > 0 && this.fuseTicks == 0) {
/* 110 */         playSound(SoundEffects.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
/*     */       }
/*     */       
/* 113 */       this.fuseTicks += i;
/* 114 */       if (this.fuseTicks < 0) {
/* 115 */         this.fuseTicks = 0;
/*     */       }
/*     */       
/* 118 */       if (this.fuseTicks >= this.maxFuseTicks) {
/* 119 */         this.fuseTicks = this.maxFuseTicks;
/* 120 */         explode();
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     super.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 129 */     return SoundEffects.ENTITY_CREEPER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 134 */     return SoundEffects.ENTITY_CREEPER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
/* 139 */     super.dropDeathLoot(damagesource, i, flag);
/* 140 */     Entity entity = damagesource.getEntity();
/*     */     
/* 142 */     if (entity != this && entity instanceof EntityCreeper) {
/* 143 */       EntityCreeper entitycreeper = (EntityCreeper)entity;
/*     */       
/* 145 */       if (entitycreeper.canCauseHeadDrop()) {
/* 146 */         entitycreeper.setCausedHeadDrop();
/* 147 */         a(Items.CREEPER_HEAD);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 155 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isPowered() {
/* 159 */     return ((Boolean)this.datawatcher.<Boolean>get(POWERED)).booleanValue();
/*     */   }
/*     */   
/*     */   public int eK() {
/* 163 */     return ((Integer)this.datawatcher.<Integer>get(b)).intValue();
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 167 */     this.datawatcher.set(b, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLightningStrike(WorldServer worldserver, EntityLightning entitylightning) {
/* 172 */     super.onLightningStrike(worldserver, entitylightning);
/*     */     
/* 174 */     if (CraftEventFactory.callCreeperPowerEvent(this, entitylightning, CreeperPowerEvent.PowerCause.LIGHTNING).isCancelled()) {
/*     */       return;
/*     */     }
/*     */     
/* 178 */     setPowered(true);
/*     */   }
/*     */   
/*     */   public void setPowered(boolean powered) {
/* 182 */     this.datawatcher.set(POWERED, Boolean.valueOf(powered));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 188 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 190 */     if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
/* 191 */       this.world.playSound(entityhuman, locX(), locY(), locZ(), SoundEffects.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
/* 192 */       if (!this.world.isClientSide) {
/* 193 */         ignite();
/* 194 */         itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(enumhand));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 199 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/* 201 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void explode() {
/* 206 */     if (!this.world.isClientSide) {
/* 207 */       Explosion.Effect explosion_effect = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? Explosion.Effect.DESTROY : Explosion.Effect.NONE;
/* 208 */       float f = isPowered() ? 2.0F : 1.0F;
/*     */ 
/*     */       
/* 211 */       ExplosionPrimeEvent event = new ExplosionPrimeEvent((Entity)getBukkitEntity(), this.explosionRadius * f, false);
/* 212 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/* 213 */       if (!event.isCancelled()) {
/* 214 */         this.killed = true;
/* 215 */         this.world.createExplosion(this, locX(), locY(), locZ(), event.getRadius(), event.getFire(), explosion_effect);
/* 216 */         die();
/* 217 */         createEffectCloud();
/*     */       } else {
/* 219 */         this.fuseTicks = 0;
/* 220 */         this.datawatcher.set(isIgnitedDW, Boolean.valueOf(false));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void createEffectCloud() {
/* 228 */     Collection<MobEffect> collection = getEffects();
/*     */     
/* 230 */     if (!collection.isEmpty() && !this.world.paperConfig.disableCreeperLingeringEffect) {
/* 231 */       EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, locX(), locY(), locZ());
/*     */       
/* 233 */       entityareaeffectcloud.setSource(this);
/* 234 */       entityareaeffectcloud.setRadius(2.5F);
/* 235 */       entityareaeffectcloud.setRadiusOnUse(-0.5F);
/* 236 */       entityareaeffectcloud.setWaitTime(10);
/* 237 */       entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
/* 238 */       entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());
/* 239 */       Iterator<MobEffect> iterator = collection.iterator();
/*     */       
/* 241 */       while (iterator.hasNext()) {
/* 242 */         MobEffect mobeffect = iterator.next();
/*     */         
/* 244 */         entityareaeffectcloud.addEffect(new MobEffect(mobeffect));
/*     */       } 
/*     */       
/* 247 */       this.world.addEntity(entityareaeffectcloud, CreatureSpawnEvent.SpawnReason.EXPLOSION);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIgnited() {
/* 253 */     return ((Boolean)this.datawatcher.<Boolean>get(d)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ignite() {
/* 258 */     setIgnited(true);
/*     */   }
/*     */   
/*     */   public void setIgnited(boolean ignited) {
/* 262 */     if (isIgnited() != ignited) {
/* 263 */       CreeperIgniteEvent event = new CreeperIgniteEvent((Creeper)getBukkitEntity(), ignited);
/* 264 */       if (event.callEvent()) {
/* 265 */         this.datawatcher.set(d, Boolean.valueOf(event.isIgnited()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCauseHeadDrop() {
/* 272 */     return (isPowered() && this.bs < 1);
/*     */   }
/*     */   
/*     */   public void setCausedHeadDrop() {
/* 276 */     this.bs++;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */