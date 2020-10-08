/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Hanging;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.hanging.HangingBreakByEntityEvent;
/*     */ import org.bukkit.event.hanging.HangingBreakEvent;
/*     */ 
/*     */ public abstract class EntityHanging
/*     */   extends Entity {
/*     */   static {
/*  15 */     b = (entity -> entity instanceof EntityHanging);
/*     */   }
/*     */   protected static final Predicate<Entity> b;
/*  18 */   private int e = getId() % this.world.spigotConfig.hangingTickFrequency;
/*     */   public BlockPosition blockPosition;
/*     */   protected EnumDirection direction;
/*     */   
/*     */   protected EntityHanging(EntityTypes<? extends EntityHanging> entitytypes, World world) {
/*  23 */     super(entitytypes, world);
/*  24 */     this.direction = EnumDirection.SOUTH;
/*     */   }
/*     */   
/*     */   protected EntityHanging(EntityTypes<? extends EntityHanging> entitytypes, World world, BlockPosition blockposition) {
/*  28 */     this(entitytypes, world);
/*  29 */     this.blockPosition = blockposition;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {}
/*     */   
/*     */   public void setDirection(EnumDirection enumdirection) {
/*  36 */     Validate.notNull(enumdirection);
/*  37 */     Validate.isTrue(enumdirection.n().d());
/*  38 */     this.direction = enumdirection;
/*  39 */     this.yaw = (this.direction.get2DRotationValue() * 90);
/*  40 */     this.lastYaw = this.yaw;
/*  41 */     updateBoundingBox();
/*     */   }
/*     */   
/*     */   protected void updateBoundingBox() {
/*  45 */     if (this.direction != null)
/*     */     {
/*  47 */       a(calculateBoundingBox(this, this.blockPosition, this.direction, getHangingWidth(), getHangingHeight()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AxisAlignedBB calculateBoundingBox(@Nullable Entity entity, BlockPosition blockPosition, EnumDirection direction, int width, int height) {
/*  55 */     double d0 = blockPosition.getX() + 0.5D;
/*  56 */     double d1 = blockPosition.getY() + 0.5D;
/*  57 */     double d2 = blockPosition.getZ() + 0.5D;
/*  58 */     double d3 = 0.46875D;
/*  59 */     double d4 = a(width);
/*  60 */     double d5 = a(height);
/*     */     
/*  62 */     d0 -= direction.getAdjacentX() * 0.46875D;
/*  63 */     d2 -= direction.getAdjacentZ() * 0.46875D;
/*  64 */     d1 += d5;
/*  65 */     EnumDirection enumdirection = direction.h();
/*     */     
/*  67 */     d0 += d4 * enumdirection.getAdjacentX();
/*  68 */     d2 += d4 * enumdirection.getAdjacentZ();
/*  69 */     if (entity != null) {
/*  70 */       entity.setPositionRaw(d0, d1, d2);
/*     */     }
/*  72 */     double d6 = width;
/*  73 */     double d7 = height;
/*  74 */     double d8 = width;
/*     */     
/*  76 */     if (direction.n() == EnumDirection.EnumAxis.Z) {
/*  77 */       d8 = 1.0D;
/*     */     } else {
/*  79 */       d6 = 1.0D;
/*     */     } 
/*     */     
/*  82 */     d6 /= 32.0D;
/*  83 */     d7 /= 32.0D;
/*  84 */     d8 /= 32.0D;
/*  85 */     return new AxisAlignedBB(d0 - d6, d1 - d7, d2 - d8, d0 + d6, d1 + d7, d2 + d8);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static double a(int i) {
/*  91 */     return (i % 32 == 0) ? 0.5D : 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  96 */     if (!this.world.isClientSide) {
/*  97 */       if (locY() < -64.0D) {
/*  98 */         am();
/*     */       }
/*     */       
/* 101 */       if (this.e++ == this.world.spigotConfig.hangingTickFrequency) {
/* 102 */         this.e = 0;
/* 103 */         if (!this.dead && !survives()) {
/*     */           HangingBreakEvent.RemoveCause cause;
/* 105 */           Material material = this.world.getType(getChunkCoordinates()).getMaterial();
/*     */ 
/*     */           
/* 108 */           if (!material.equals(Material.AIR)) {
/*     */             
/* 110 */             cause = HangingBreakEvent.RemoveCause.OBSTRUCTION;
/*     */           } else {
/* 112 */             cause = HangingBreakEvent.RemoveCause.PHYSICS;
/*     */           } 
/*     */           
/* 115 */           HangingBreakEvent event = new HangingBreakEvent((Hanging)getBukkitEntity(), cause);
/* 116 */           this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */           
/* 118 */           if (this.dead || event.isCancelled()) {
/*     */             return;
/*     */           }
/*     */           
/* 122 */           die();
/* 123 */           a((Entity)null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean survives() {
/* 131 */     if (!this.world.getCubes(this)) {
/* 132 */       return false;
/*     */     }
/* 134 */     int i = Math.max(1, getHangingWidth() / 16);
/* 135 */     int j = Math.max(1, getHangingHeight() / 16);
/* 136 */     BlockPosition blockposition = this.blockPosition.shift(this.direction.opposite());
/* 137 */     EnumDirection enumdirection = this.direction.h();
/* 138 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/* 140 */     for (int k = 0; k < i; k++) {
/* 141 */       for (int l = 0; l < j; l++) {
/* 142 */         int i1 = (i - 1) / -2;
/* 143 */         int j1 = (j - 1) / -2;
/*     */         
/* 145 */         blockposition_mutableblockposition.g(blockposition).c(enumdirection, k + i1).c(EnumDirection.UP, l + j1);
/* 146 */         IBlockData iblockdata = this.world.getType(blockposition_mutableblockposition);
/*     */         
/* 148 */         if (!iblockdata.getMaterial().isBuildable() && !BlockDiodeAbstract.isDiode(iblockdata)) {
/* 149 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     return this.world.getEntities(this, getBoundingBox(), b).isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean t(Entity entity) {
/* 165 */     if (entity instanceof EntityHuman) {
/* 166 */       EntityHuman entityhuman = (EntityHuman)entity;
/*     */       
/* 168 */       return !this.world.a(entityhuman, this.blockPosition) ? true : damageEntity(DamageSource.playerAttack(entityhuman), 0.0F);
/*     */     } 
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumDirection getDirection() {
/* 176 */     return this.direction;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 181 */     if (isInvulnerable(damagesource)) {
/* 182 */       return false;
/*     */     }
/* 184 */     if (!this.dead && !this.world.isClientSide) {
/*     */       HangingBreakEvent event;
/* 186 */       Entity damager = (damagesource instanceof EntityDamageSourceIndirect) ? ((EntityDamageSourceIndirect)damagesource).getProximateDamageSource() : damagesource.getEntity();
/*     */       
/* 188 */       if (damager != null) {
/* 189 */         HangingBreakByEntityEvent hangingBreakByEntityEvent = new HangingBreakByEntityEvent((Hanging)getBukkitEntity(), (Entity)damager.getBukkitEntity(), damagesource.isExplosion() ? HangingBreakEvent.RemoveCause.EXPLOSION : HangingBreakEvent.RemoveCause.ENTITY);
/*     */       } else {
/* 191 */         event = new HangingBreakEvent((Hanging)getBukkitEntity(), damagesource.isExplosion() ? HangingBreakEvent.RemoveCause.EXPLOSION : HangingBreakEvent.RemoveCause.DEFAULT);
/*     */       } 
/*     */       
/* 194 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 196 */       if (this.dead || event.isCancelled()) {
/* 197 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 201 */       die();
/* 202 */       velocityChanged();
/* 203 */       a(damagesource.getEntity());
/*     */     } 
/*     */     
/* 206 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType enummovetype, Vec3D vec3d) {
/* 212 */     if (!this.world.isClientSide && !this.dead && vec3d.g() > 0.0D) {
/* 213 */       if (this.dead) {
/*     */         return;
/*     */       }
/*     */       
/* 217 */       HangingBreakEvent event = new HangingBreakEvent((Hanging)getBukkitEntity(), HangingBreakEvent.RemoveCause.PHYSICS);
/* 218 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 220 */       if (this.dead || event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 225 */       die();
/* 226 */       a((Entity)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void i(double d0, double d1, double d2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 242 */     BlockPosition blockposition = getBlockPosition();
/*     */     
/* 244 */     nbttagcompound.setInt("TileX", blockposition.getX());
/* 245 */     nbttagcompound.setInt("TileY", blockposition.getY());
/* 246 */     nbttagcompound.setInt("TileZ", blockposition.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 251 */     this.blockPosition = new BlockPosition(nbttagcompound.getInt("TileX"), nbttagcompound.getInt("TileY"), nbttagcompound.getInt("TileZ"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityItem a(ItemStack itemstack, float f) {
/* 264 */     EntityItem entityitem = new EntityItem(this.world, locX() + (this.direction.getAdjacentX() * 0.15F), locY() + f, locZ() + (this.direction.getAdjacentZ() * 0.15F), itemstack);
/*     */     
/* 266 */     entityitem.defaultPickupDelay();
/* 267 */     this.world.addEntity(entityitem);
/* 268 */     return entityitem;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean aU() {
/* 273 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPosition(double d0, double d1, double d2) {
/* 278 */     this.blockPosition = new BlockPosition(d0, d1, d2);
/* 279 */     updateBoundingBox();
/* 280 */     this.impulse = true;
/*     */   }
/*     */   
/*     */   public BlockPosition getBlockPosition() {
/* 284 */     return this.blockPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(EnumBlockRotation enumblockrotation) {
/* 289 */     if (this.direction.n() != EnumDirection.EnumAxis.Y) {
/* 290 */       switch (enumblockrotation) {
/*     */         case CLOCKWISE_180:
/* 292 */           this.direction = this.direction.opposite();
/*     */           break;
/*     */         case COUNTERCLOCKWISE_90:
/* 295 */           this.direction = this.direction.h();
/*     */           break;
/*     */         case CLOCKWISE_90:
/* 298 */           this.direction = this.direction.g();
/*     */           break;
/*     */       } 
/*     */     }
/* 302 */     float f = MathHelper.g(this.yaw);
/*     */     
/* 304 */     switch (enumblockrotation) {
/*     */       case CLOCKWISE_180:
/* 306 */         return f + 180.0F;
/*     */       case COUNTERCLOCKWISE_90:
/* 308 */         return f + 90.0F;
/*     */       case CLOCKWISE_90:
/* 310 */         return f + 270.0F;
/*     */     } 
/* 312 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float a(EnumBlockMirror enumblockmirror) {
/* 318 */     return a(enumblockmirror.a(this.direction));
/*     */   }
/*     */   
/*     */   public void onLightningStrike(WorldServer worldserver, EntityLightning entitylightning) {}
/*     */   
/*     */   public void updateSize() {}
/*     */   
/*     */   public abstract int getHangingWidth();
/*     */   
/*     */   public abstract int getHangingHeight();
/*     */   
/*     */   public abstract void a(@Nullable Entity paramEntity);
/*     */   
/*     */   public abstract void playPlaceSound();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */