/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Explosive;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityTNTPrimed extends Entity {
/*   8 */   private static final DataWatcherObject<Integer> FUSE_TICKS = DataWatcher.a((Class)EntityTNTPrimed.class, DataWatcherRegistry.b);
/*     */   @Nullable
/*     */   private EntityLiving source;
/*     */   private int fuseTicks;
/*  12 */   public float yield = 4.0F;
/*     */   public boolean isIncendiary = false;
/*     */   
/*     */   public EntityTNTPrimed(EntityTypes<? extends EntityTNTPrimed> entitytypes, World world) {
/*  16 */     super(entitytypes, world);
/*  17 */     this.fuseTicks = 80;
/*  18 */     this.i = true;
/*     */   }
/*     */   
/*     */   public EntityTNTPrimed(World world, double d0, double d1, double d2, @Nullable EntityLiving entityliving) {
/*  22 */     this(EntityTypes.TNT, world);
/*  23 */     setPosition(d0, d1, d2);
/*  24 */     double d3 = world.random.nextDouble() * 6.2831854820251465D;
/*     */     
/*  26 */     setMot(-Math.sin(d3) * 0.02D, 0.20000000298023224D, -Math.cos(d3) * 0.02D);
/*  27 */     setFuseTicks(80);
/*  28 */     this.lastX = d0;
/*  29 */     this.lastY = d1;
/*  30 */     this.lastZ = d2;
/*  31 */     this.source = entityliving;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  36 */     this.datawatcher.register(FUSE_TICKS, Integer.valueOf(80));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/*  46 */     return !this.dead;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  51 */     if (this.world.spigotConfig.currentPrimedTnt++ > this.world.spigotConfig.maxTntTicksPerTick)
/*  52 */       return;  if (!isNoGravity()) {
/*  53 */       setMot(getMot().add(0.0D, -0.04D, 0.0D));
/*     */     }
/*     */     
/*  56 */     move(EnumMoveType.SELF, getMot());
/*     */     
/*  58 */     if (this.world.paperConfig.entityTNTHeightNerf != 0 && locY() > this.world.paperConfig.entityTNTHeightNerf) {
/*  59 */       die();
/*     */       
/*     */       return;
/*     */     } 
/*  63 */     setMot(getMot().a(0.98D));
/*  64 */     if (this.onGround) {
/*  65 */       setMot(getMot().d(0.7D, -0.5D, 0.7D));
/*     */     }
/*     */     
/*  68 */     this.fuseTicks--;
/*  69 */     if (this.fuseTicks <= 0) {
/*     */ 
/*     */       
/*  72 */       if (!this.world.isClientSide) {
/*  73 */         explode();
/*     */       }
/*  75 */       die();
/*     */     } else {
/*     */       
/*  78 */       aJ();
/*  79 */       if (this.world.isClientSide) {
/*  80 */         this.world.addParticle(Particles.SMOKE, locX(), locY() + 0.5D, locZ(), 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     } 
/*     */     
/*  84 */     if (!this.dead && this.inWater && this.world.paperConfig.preventTntFromMovingInWater) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  90 */       PlayerChunkMap.EntityTracker ete = (PlayerChunkMap.EntityTracker)(((WorldServer)this.world).getChunkProvider()).playerChunkMap.trackedEntities.get(getId());
/*  91 */       if (ete != null) {
/*  92 */         PacketPlayOutEntityVelocity velocityPacket = new PacketPlayOutEntityVelocity(this);
/*  93 */         PacketPlayOutEntityTeleport positionPacket = new PacketPlayOutEntityTeleport(this);
/*     */         
/*  95 */         ete.trackedPlayers.stream()
/*  96 */           .filter(viewer -> ((viewer.locX() - locX()) * (viewer.locY() - locY()) * (viewer.locZ() - locZ()) < 256.0D))
/*  97 */           .forEach(viewer -> {
/*     */               viewer.playerConnection.sendPacket(velocityPacket);
/*     */               viewer.playerConnection.sendPacket(positionPacket);
/*     */             });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void explode() {
/* 110 */     ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)getBukkitEntity());
/* 111 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */     
/* 113 */     if (!event.isCancelled()) {
/* 114 */       this.world.createExplosion(this, locX(), e(0.0625D), locZ(), event.getRadius(), event.getFire(), Explosion.Effect.BREAK);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/* 121 */     nbttagcompound.setShort("Fuse", (short)getFuseTicks());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/* 126 */     setFuseTicks(nbttagcompound.getShort("Fuse"));
/*     */     
/* 128 */     if (nbttagcompound.hasKey("SourceLoc_x")) {
/* 129 */       int srcX = nbttagcompound.getInt("SourceLoc_x");
/* 130 */       int srcY = nbttagcompound.getInt("SourceLoc_y");
/* 131 */       int srcZ = nbttagcompound.getInt("SourceLoc_z");
/* 132 */       this.origin = new Location((World)this.world.getWorld(), srcX, srcY, srcZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getSource() {
/* 139 */     return this.source;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getHeadHeight(EntityPose entitypose, EntitySize entitysize) {
/* 144 */     return 0.15F;
/*     */   }
/*     */   
/*     */   public void setFuseTicks(int i) {
/* 148 */     this.datawatcher.set(FUSE_TICKS, Integer.valueOf(i));
/* 149 */     this.fuseTicks = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 154 */     if (FUSE_TICKS.equals(datawatcherobject)) {
/* 155 */       this.fuseTicks = h();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int h() {
/* 161 */     return ((Integer)this.datawatcher.<Integer>get(FUSE_TICKS)).intValue();
/*     */   }
/*     */   
/*     */   public int getFuseTicks() {
/* 165 */     return this.fuseTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 170 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean pushedByWater() {
/* 176 */     return (!this.world.paperConfig.preventTntFromMovingInWater && super.pushedByWater());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityTNTPrimed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */