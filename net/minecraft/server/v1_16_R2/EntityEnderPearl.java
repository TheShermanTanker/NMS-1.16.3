/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent;
/*     */ 
/*     */ public class EntityEnderPearl extends EntityProjectileThrowable {
/*     */   public EntityEnderPearl(EntityTypes<? extends EntityEnderPearl> entitytypes, World world) {
/*  14 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityEnderPearl(World world, EntityLiving entityliving) {
/*  18 */     super((EntityTypes)EntityTypes.ENDER_PEARL, entityliving, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDefaultItem() {
/*  23 */     return Items.ENDER_PEARL;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/*  28 */     super.a(movingobjectpositionentity);
/*  29 */     movingobjectpositionentity.getEntity().damageEntity(DamageSource.projectile(this, getShooter()), 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/*  34 */     super.a(movingobjectposition);
/*  35 */     Entity entity = getShooter();
/*     */     
/*  37 */     for (int i = 0; i < 32; i++) {
/*  38 */       this.world.addParticle(Particles.PORTAL, locX(), locY() + this.random.nextDouble() * 2.0D, locZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
/*     */     }
/*     */     
/*  41 */     if (!this.world.isClientSide && !this.dead) {
/*  42 */       if (entity instanceof EntityPlayer) {
/*  43 */         EntityPlayer entityplayer = (EntityPlayer)entity;
/*     */         
/*  45 */         if (entityplayer.playerConnection.a().isConnected() && entityplayer.world == this.world && !entityplayer.isSleeping()) {
/*     */           
/*  47 */           CraftPlayer player = entityplayer.getBukkitEntity();
/*  48 */           Location location = getBukkitEntity().getLocation();
/*  49 */           location.setPitch(player.getLocation().getPitch());
/*  50 */           location.setYaw(player.getLocation().getYaw());
/*     */           
/*  52 */           PlayerTeleportEvent teleEvent = new PlayerTeleportEvent((Player)player, player.getLocation(), location, PlayerTeleportEvent.TeleportCause.ENDER_PEARL);
/*  53 */           Bukkit.getPluginManager().callEvent((Event)teleEvent);
/*     */           
/*  55 */           if (!teleEvent.isCancelled() && !entityplayer.playerConnection.isDisconnected()) {
/*  56 */             if (this.random.nextFloat() < 0.05F && this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
/*  57 */               EntityEndermite entityendermite = EntityTypes.ENDERMITE.a(this.world);
/*     */               
/*  59 */               entityendermite.setPlayerSpawned(true);
/*  60 */               entityendermite.setPositionRotation(entity.locX(), entity.locY(), entity.locZ(), entity.yaw, entity.pitch);
/*  61 */               this.world.addEntity(entityendermite, CreatureSpawnEvent.SpawnReason.ENDER_PEARL);
/*     */             } 
/*     */             
/*  64 */             if (entity.isPassenger()) {
/*  65 */               entity.stopRiding();
/*     */             }
/*     */             
/*  68 */             entityplayer.playerConnection.teleport(teleEvent.getTo());
/*  69 */             entity.fallDistance = 0.0F;
/*  70 */             CraftEventFactory.entityDamage = this;
/*  71 */             entity.damageEntity(DamageSource.FALL, 5.0F);
/*  72 */             CraftEventFactory.entityDamage = null;
/*     */           }
/*     */         
/*     */         } 
/*  76 */       } else if (entity != null) {
/*  77 */         entity.enderTeleportTo(locX(), locY(), locZ());
/*  78 */         entity.fallDistance = 0.0F;
/*     */       } 
/*     */       
/*  81 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  88 */     Entity entity = getShooter();
/*     */     
/*  90 */     if (entity instanceof EntityHuman && !entity.isAlive()) {
/*  91 */       die();
/*     */     } else {
/*  93 */       super.tick();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity b(WorldServer worldserver) {
/* 101 */     Entity entity = getShooter();
/*     */     
/* 103 */     if (entity != null && worldserver != null && entity.world.getDimensionKey() != worldserver.getDimensionKey()) {
/* 104 */       setShooter((Entity)null);
/*     */     }
/*     */     
/* 107 */     return super.b(worldserver);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEnderPearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */