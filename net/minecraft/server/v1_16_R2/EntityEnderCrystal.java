/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityEnderCrystal
/*     */   extends Entity
/*     */ {
/*  13 */   private static final DataWatcherObject<Optional<BlockPosition>> c = DataWatcher.a((Class)EntityEnderCrystal.class, DataWatcherRegistry.m);
/*  14 */   private static final DataWatcherObject<Boolean> d = DataWatcher.a((Class)EntityEnderCrystal.class, DataWatcherRegistry.i);
/*     */   public int b;
/*     */   
/*     */   public EntityEnderCrystal(EntityTypes<? extends EntityEnderCrystal> entitytypes, World world) {
/*  18 */     super(entitytypes, world);
/*  19 */     this.i = true;
/*  20 */     this.b = this.random.nextInt(100000);
/*     */   }
/*     */   
/*     */   public EntityEnderCrystal(World world, double d0, double d1, double d2) {
/*  24 */     this(EntityTypes.END_CRYSTAL, world);
/*  25 */     setPosition(d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  30 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  35 */     getDataWatcher().register(c, Optional.empty());
/*  36 */     getDataWatcher().register(d, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  41 */     this.b++;
/*  42 */     if (this.world instanceof WorldServer) {
/*  43 */       BlockPosition blockposition = getChunkCoordinates();
/*     */       
/*  45 */       if (((WorldServer)this.world).getDragonBattle() != null && this.world.getType(blockposition).isAir())
/*     */       {
/*  47 */         if (!CraftEventFactory.callBlockIgniteEvent(this.world, blockposition, this).isCancelled()) {
/*  48 */           this.world.setTypeUpdate(blockposition, BlockFireAbstract.a(this.world, blockposition));
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/*  58 */     if (getBeamTarget() != null) {
/*  59 */       nbttagcompound.set("BeamTarget", GameProfileSerializer.a(getBeamTarget()));
/*     */     }
/*     */     
/*  62 */     nbttagcompound.setBoolean("ShowBottom", isShowingBottom());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/*  67 */     if (nbttagcompound.hasKeyOfType("BeamTarget", 10)) {
/*  68 */       setBeamTarget(GameProfileSerializer.b(nbttagcompound.getCompound("BeamTarget")));
/*     */     }
/*     */     
/*  71 */     if (nbttagcompound.hasKeyOfType("ShowBottom", 1)) {
/*  72 */       setShowingBottom(nbttagcompound.getBoolean("ShowBottom"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  84 */     if (isInvulnerable(damagesource))
/*  85 */       return false; 
/*  86 */     if (damagesource.getEntity() instanceof EntityEnderDragon) {
/*  87 */       return false;
/*     */     }
/*  89 */     if (!this.dead && !this.world.isClientSide) {
/*     */       
/*  91 */       if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f, false)) {
/*  92 */         return false;
/*     */       }
/*     */       
/*  95 */       die();
/*  96 */       if (!damagesource.isExplosion()) {
/*     */         
/*  98 */         ExplosionPrimeEvent event = new ExplosionPrimeEvent((Entity)getBukkitEntity(), 6.0F, false);
/*  99 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/* 100 */         if (event.isCancelled()) {
/* 101 */           this.dead = false;
/* 102 */           return false;
/*     */         } 
/* 104 */         this.world.createExplosion(this, locX(), locY(), locZ(), event.getRadius(), event.getFire(), Explosion.Effect.DESTROY);
/*     */       } 
/*     */ 
/*     */       
/* 108 */       a(damagesource);
/*     */     } 
/*     */     
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void killEntity() {
/* 117 */     a(DamageSource.GENERIC);
/* 118 */     super.killEntity();
/*     */   }
/*     */   
/*     */   private void a(DamageSource damagesource) {
/* 122 */     if (this.world instanceof WorldServer) {
/* 123 */       EnderDragonBattle enderdragonbattle = ((WorldServer)this.world).getDragonBattle();
/*     */       
/* 125 */       if (enderdragonbattle != null) {
/* 126 */         enderdragonbattle.a(this, damagesource);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBeamTarget(@Nullable BlockPosition blockposition) {
/* 133 */     getDataWatcher().set(c, Optional.ofNullable(blockposition));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition getBeamTarget() {
/* 138 */     return ((Optional<BlockPosition>)getDataWatcher().<Optional<BlockPosition>>get(c)).orElse(null);
/*     */   }
/*     */   
/*     */   public void setShowingBottom(boolean flag) {
/* 142 */     getDataWatcher().set(d, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean isShowingBottom() {
/* 146 */     return ((Boolean)getDataWatcher().<Boolean>get(d)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 151 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEnderCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */