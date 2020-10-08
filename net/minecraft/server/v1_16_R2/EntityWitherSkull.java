/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityWitherSkull extends EntityFireball {
/*   7 */   private static final DataWatcherObject<Boolean> e = DataWatcher.a((Class)EntityWitherSkull.class, DataWatcherRegistry.i);
/*     */   
/*     */   public EntityWitherSkull(EntityTypes<? extends EntityWitherSkull> entitytypes, World world) {
/*  10 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityWitherSkull(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/*  14 */     super((EntityTypes)EntityTypes.WITHER_SKULL, entityliving, d0, d1, d2, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float i() {
/*  19 */     return isCharged() ? 0.73F : super.i();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/*  24 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(Explosion explosion, IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, Fluid fluid, float f) {
/*  29 */     return (isCharged() && EntityWither.c(iblockdata)) ? Math.min(0.8F, f) : f;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/*  34 */     super.a(movingobjectpositionentity);
/*  35 */     if (!this.world.isClientSide) {
/*  36 */       boolean flag; Entity entity = movingobjectpositionentity.getEntity();
/*  37 */       Entity entity1 = getShooter();
/*     */ 
/*     */       
/*  40 */       if (entity1 instanceof EntityLiving) {
/*  41 */         EntityLiving entityliving = (EntityLiving)entity1;
/*     */         
/*  43 */         flag = entity.damageEntity(DamageSource.a(this, entityliving), 8.0F);
/*  44 */         if (flag) {
/*  45 */           if (entity.isAlive()) {
/*  46 */             a(entityliving, entity);
/*     */           } else {
/*  48 */             entityliving.heal(5.0F, EntityRegainHealthEvent.RegainReason.WITHER);
/*     */           } 
/*     */         }
/*     */       } else {
/*  52 */         flag = entity.damageEntity(DamageSource.MAGIC, 5.0F);
/*     */       } 
/*     */       
/*  55 */       if (flag && entity instanceof EntityLiving) {
/*  56 */         byte b0 = 0;
/*     */         
/*  58 */         if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
/*  59 */           b0 = 10;
/*  60 */         } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
/*  61 */           b0 = 40;
/*     */         } 
/*     */         
/*  64 */         if (b0 > 0) {
/*  65 */           ((EntityLiving)entity).addEffect(new MobEffect(MobEffects.WITHER, 20 * b0, 1), EntityPotionEffectEvent.Cause.ATTACK);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/*  74 */     super.a(movingobjectposition);
/*  75 */     if (!this.world.isClientSide) {
/*  76 */       Explosion.Effect explosion_effect = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? Explosion.Effect.DESTROY : Explosion.Effect.NONE;
/*     */ 
/*     */ 
/*     */       
/*  80 */       ExplosionPrimeEvent event = new ExplosionPrimeEvent((Entity)getBukkitEntity(), 1.0F, false);
/*  81 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/*  83 */       if (!event.isCancelled()) {
/*  84 */         this.world.createExplosion(this, locX(), locY(), locZ(), event.getRadius(), event.getFire(), explosion_effect);
/*     */       }
/*     */       
/*  87 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 104 */     this.datawatcher.register(e, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean isCharged() {
/* 108 */     return ((Boolean)this.datawatcher.<Boolean>get(e)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setCharged(boolean flag) {
/* 112 */     this.datawatcher.set(e, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean W_() {
/* 117 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityWitherSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */