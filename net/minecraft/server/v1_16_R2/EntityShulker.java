/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityTeleportEvent;
/*     */ 
/*     */ public class EntityShulker extends EntityGolem implements IMonster {
/*  16 */   private static final UUID bp = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
/*  17 */   private static final AttributeModifier bq = new AttributeModifier(bp, "Covered armor bonus", 20.0D, AttributeModifier.Operation.ADDITION);
/*  18 */   protected static final DataWatcherObject<EnumDirection> b = DataWatcher.a((Class)EntityShulker.class, DataWatcherRegistry.n);
/*  19 */   protected static final DataWatcherObject<Optional<BlockPosition>> c = DataWatcher.a((Class)EntityShulker.class, DataWatcherRegistry.m);
/*  20 */   protected static final DataWatcherObject<Byte> d = DataWatcher.a((Class)EntityShulker.class, DataWatcherRegistry.a);
/*  21 */   public static final DataWatcherObject<Byte> COLOR = DataWatcher.a((Class)EntityShulker.class, DataWatcherRegistry.a);
/*     */   private float br;
/*     */   private float bs;
/*  24 */   private BlockPosition bt = null;
/*     */   private int bu;
/*     */   
/*     */   public EntityShulker(EntityTypes<? extends EntityShulker> entitytypes, World world) {
/*  28 */     super((EntityTypes)entitytypes, world);
/*  29 */     this.f = 5;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  34 */     this.goalSelector.a(1, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  35 */     this.goalSelector.a(4, new a());
/*  36 */     this.goalSelector.a(7, new e());
/*  37 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  38 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
/*  39 */     this.targetSelector.a(2, new d(this));
/*  40 */     this.targetSelector.a(3, new c(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/*  50 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  55 */     return SoundEffects.ENTITY_SHULKER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void F() {
/*  60 */     if (!eT()) {
/*  61 */       super.F();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  68 */     return SoundEffects.ENTITY_SHULKER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  73 */     return eT() ? SoundEffects.ENTITY_SHULKER_HURT_CLOSED : SoundEffects.ENTITY_SHULKER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  78 */     super.initDatawatcher();
/*  79 */     this.datawatcher.register(b, EnumDirection.DOWN);
/*  80 */     this.datawatcher.register(c, Optional.empty());
/*  81 */     this.datawatcher.register(d, Byte.valueOf((byte)0));
/*  82 */     this.datawatcher.register(COLOR, Byte.valueOf((byte)16));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  86 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 30.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityAIBodyControl r() {
/*  91 */     return new b(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  96 */     super.loadData(nbttagcompound);
/*  97 */     this.datawatcher.set(b, EnumDirection.fromType1(nbttagcompound.getByte("AttachFace")));
/*  98 */     this.datawatcher.set(d, Byte.valueOf(nbttagcompound.getByte("Peek")));
/*  99 */     this.datawatcher.set(COLOR, Byte.valueOf(nbttagcompound.getByte("Color")));
/* 100 */     if (nbttagcompound.hasKey("APX")) {
/* 101 */       int i = nbttagcompound.getInt("APX");
/* 102 */       int j = nbttagcompound.getInt("APY");
/* 103 */       int k = nbttagcompound.getInt("APZ");
/*     */       
/* 105 */       this.datawatcher.set(c, Optional.of(new BlockPosition(i, j, k)));
/*     */     } else {
/* 107 */       this.datawatcher.set(c, Optional.empty());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 114 */     super.saveData(nbttagcompound);
/* 115 */     nbttagcompound.setByte("AttachFace", (byte)((EnumDirection)this.datawatcher.<EnumDirection>get(b)).c());
/* 116 */     nbttagcompound.setByte("Peek", ((Byte)this.datawatcher.<Byte>get(d)).byteValue());
/* 117 */     nbttagcompound.setByte("Color", ((Byte)this.datawatcher.<Byte>get(COLOR)).byteValue());
/* 118 */     BlockPosition blockposition = eM();
/*     */     
/* 120 */     if (blockposition != null) {
/* 121 */       nbttagcompound.setInt("APX", blockposition.getX());
/* 122 */       nbttagcompound.setInt("APY", blockposition.getY());
/* 123 */       nbttagcompound.setInt("APZ", blockposition.getZ());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 130 */     super.tick();
/* 131 */     BlockPosition blockposition = ((Optional<BlockPosition>)this.datawatcher.<Optional<BlockPosition>>get(c)).orElse(null);
/*     */     
/* 133 */     if (blockposition == null && !this.world.isClientSide) {
/* 134 */       blockposition = getChunkCoordinates();
/* 135 */       this.datawatcher.set(c, Optional.of(blockposition));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 140 */     if (isPassenger()) {
/* 141 */       blockposition = null;
/* 142 */       float f1 = (getVehicle()).yaw;
/* 143 */       this.yaw = f1;
/* 144 */       this.aA = f1;
/* 145 */       this.aB = f1;
/* 146 */       this.bu = 0;
/* 147 */     } else if (!this.world.isClientSide) {
/* 148 */       IBlockData iblockdata = this.world.getType(blockposition);
/*     */ 
/*     */       
/* 151 */       if (!iblockdata.isAir()) {
/* 152 */         if (iblockdata.a(Blocks.MOVING_PISTON)) {
/* 153 */           EnumDirection enumDirection = (EnumDirection)iblockdata.get(BlockPiston.FACING);
/* 154 */           if (this.world.isEmpty(blockposition.shift(enumDirection))) {
/* 155 */             blockposition = blockposition.shift(enumDirection);
/* 156 */             this.datawatcher.set(c, Optional.of(blockposition));
/*     */           } else {
/* 158 */             eK();
/*     */           } 
/* 160 */         } else if (iblockdata.a(Blocks.PISTON_HEAD)) {
/* 161 */           EnumDirection enumDirection = (EnumDirection)iblockdata.get(BlockPistonExtension.FACING);
/* 162 */           if (this.world.isEmpty(blockposition.shift(enumDirection))) {
/* 163 */             blockposition = blockposition.shift(enumDirection);
/* 164 */             this.datawatcher.set(c, Optional.of(blockposition));
/*     */           } else {
/* 166 */             eK();
/*     */           } 
/*     */         } else {
/* 169 */           eK();
/*     */         } 
/*     */       }
/*     */       
/* 173 */       EnumDirection enumdirection = eL();
/* 174 */       if (!a(blockposition, enumdirection)) {
/* 175 */         EnumDirection enumdirection1 = g(blockposition);
/*     */         
/* 177 */         if (enumdirection1 != null) {
/* 178 */           this.datawatcher.set(b, enumdirection1);
/*     */         } else {
/* 180 */           eK();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     float f = eN() * 0.01F;
/* 186 */     this.br = this.bs;
/* 187 */     if (this.bs > f) {
/* 188 */       this.bs = MathHelper.a(this.bs - 0.05F, f, 1.0F);
/* 189 */     } else if (this.bs < f) {
/* 190 */       this.bs = MathHelper.a(this.bs + 0.05F, 0.0F, f);
/*     */     } 
/*     */     
/* 193 */     if (blockposition != null) {
/* 194 */       if (this.world.isClientSide) {
/* 195 */         if (this.bu > 0 && this.bt != null) {
/* 196 */           this.bu--;
/*     */         } else {
/* 198 */           this.bt = blockposition;
/*     */         } 
/*     */       }
/*     */       
/* 202 */       g(blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D);
/* 203 */       double d0 = 0.5D - MathHelper.sin((0.5F + this.bs) * 3.1415927F) * 0.5D;
/* 204 */       double d1 = 0.5D - MathHelper.sin((0.5F + this.br) * 3.1415927F) * 0.5D;
/* 205 */       EnumDirection enumdirection2 = eL().opposite();
/*     */       
/* 207 */       a((new AxisAlignedBB(locX() - 0.5D, locY(), locZ() - 0.5D, locX() + 0.5D, locY() + 1.0D, locZ() + 0.5D)).b(enumdirection2.getAdjacentX() * d0, enumdirection2.getAdjacentY() * d0, enumdirection2.getAdjacentZ() * d0));
/* 208 */       double d2 = d0 - d1;
/*     */       
/* 210 */       if (d2 > 0.0D) {
/* 211 */         List<Entity> list = this.world.getEntities(this, getBoundingBox());
/*     */         
/* 213 */         if (!list.isEmpty()) {
/* 214 */           Iterator<Entity> iterator = list.iterator();
/*     */           
/* 216 */           while (iterator.hasNext()) {
/* 217 */             Entity entity = iterator.next();
/*     */             
/* 219 */             if (!(entity instanceof EntityShulker) && !entity.noclip) {
/* 220 */               entity.move(EnumMoveType.SHULKER, new Vec3D(d2 * enumdirection2.getAdjacentX(), d2 * enumdirection2.getAdjacentY(), d2 * enumdirection2.getAdjacentZ()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType enummovetype, Vec3D vec3d) {
/* 231 */     if (enummovetype == EnumMoveType.SHULKER_BOX) {
/* 232 */       eK();
/*     */     } else {
/* 234 */       super.move(enummovetype, vec3d);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(double d0, double d1, double d2) {
/* 241 */     super.setPosition(d0, d1, d2);
/* 242 */     if (this.datawatcher != null && this.ticksLived != 0) {
/* 243 */       Optional<BlockPosition> optional = this.datawatcher.<Optional<BlockPosition>>get(c);
/* 244 */       Optional<BlockPosition> optional1 = Optional.of(new BlockPosition(d0, d1, d2));
/*     */       
/* 246 */       if (!optional1.equals(optional)) {
/* 247 */         this.datawatcher.set(c, optional1);
/* 248 */         this.datawatcher.set(d, Byte.valueOf((byte)0));
/* 249 */         this.impulse = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected EnumDirection g(BlockPosition blockposition) {
/* 257 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 258 */     int i = aenumdirection.length;
/*     */     
/* 260 */     for (int j = 0; j < i; j++) {
/* 261 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/* 263 */       if (a(blockposition, enumdirection)) {
/* 264 */         return enumdirection;
/*     */       }
/*     */     } 
/*     */     
/* 268 */     return null;
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition blockposition, EnumDirection enumdirection) {
/* 272 */     return (this.world.a(blockposition.shift(enumdirection), this, enumdirection.opposite()) && this.world.getCubes(this, ShulkerUtil.a(blockposition, enumdirection.opposite())));
/*     */   }
/*     */   
/*     */   protected boolean eK() {
/* 276 */     if (!isNoAI() && isAlive()) {
/* 277 */       BlockPosition blockposition = getChunkCoordinates();
/*     */       
/* 279 */       for (int i = 0; i < 5; i++) {
/* 280 */         BlockPosition blockposition1 = blockposition.b(8 - this.random.nextInt(17), 8 - this.random.nextInt(17), 8 - this.random.nextInt(17));
/*     */         
/* 282 */         if (blockposition1.getY() > 0 && this.world.isEmpty(blockposition1) && this.world.getWorldBorder().a(blockposition1) && this.world.getCubes(this, new AxisAlignedBB(blockposition1))) {
/* 283 */           EnumDirection enumdirection = g(blockposition1);
/*     */           
/* 285 */           if (enumdirection != null) {
/*     */             
/* 287 */             EntityTeleportEvent teleport = new EntityTeleportEvent((Entity)getBukkitEntity(), getBukkitEntity().getLocation(), new Location((World)this.world.getWorld(), blockposition1.getX(), blockposition1.getY(), blockposition1.getZ()));
/* 288 */             this.world.getServer().getPluginManager().callEvent((Event)teleport);
/* 289 */             if (!teleport.isCancelled()) {
/* 290 */               Location to = teleport.getTo();
/* 291 */               blockposition1 = new BlockPosition(to.getX(), to.getY(), to.getZ());
/*     */             } else {
/* 293 */               return false;
/*     */             } 
/*     */             
/* 296 */             this.datawatcher.set(b, enumdirection);
/* 297 */             playSound(SoundEffects.ENTITY_SHULKER_TELEPORT, 1.0F, 1.0F);
/* 298 */             this.datawatcher.set(c, Optional.of(blockposition1));
/* 299 */             this.datawatcher.set(d, Byte.valueOf((byte)0));
/* 300 */             setGoalTarget((EntityLiving)null);
/* 301 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 306 */       return false;
/*     */     } 
/* 308 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 314 */     super.movementTick();
/* 315 */     setMot(Vec3D.ORIGIN);
/* 316 */     if (!isNoAI()) {
/* 317 */       this.aB = 0.0F;
/* 318 */       this.aA = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 325 */     if (c.equals(datawatcherobject) && this.world.isClientSide && !isPassenger()) {
/* 326 */       BlockPosition blockposition = eM();
/*     */       
/* 328 */       if (blockposition != null) {
/* 329 */         if (this.bt == null) {
/* 330 */           this.bt = blockposition;
/*     */         } else {
/* 332 */           this.bu = 6;
/*     */         } 
/*     */         
/* 335 */         g(blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D);
/* 336 */         if (this.valid) ((WorldServer)this.world).chunkCheck(this);
/*     */       
/*     */       } 
/*     */     } 
/* 340 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 345 */     if (eT()) {
/* 346 */       Entity entity = damagesource.j();
/*     */       
/* 348 */       if (entity instanceof EntityArrow) {
/* 349 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 353 */     if (super.damageEntity(damagesource, f)) {
/* 354 */       if (getHealth() < getMaxHealth() * 0.5D && this.random.nextInt(4) == 0) {
/* 355 */         eK();
/*     */       }
/*     */       
/* 358 */       return true;
/*     */     } 
/* 360 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean eT() {
/* 365 */     return (eN() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean aY() {
/* 370 */     return isAlive();
/*     */   }
/*     */   
/*     */   public EnumDirection eL() {
/* 374 */     return this.datawatcher.<EnumDirection>get(b);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition eM() {
/* 379 */     return ((Optional<BlockPosition>)this.datawatcher.<Optional<BlockPosition>>get(c)).orElse(null);
/*     */   }
/*     */   
/*     */   public void h(@Nullable BlockPosition blockposition) {
/* 383 */     this.datawatcher.set(c, Optional.ofNullable(blockposition));
/*     */   }
/*     */   
/*     */   public int eN() {
/* 387 */     return ((Byte)this.datawatcher.<Byte>get(d)).byteValue();
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 391 */     if (!this.world.isClientSide) {
/* 392 */       getAttributeInstance(GenericAttributes.ARMOR).removeModifier(bq);
/* 393 */       if (i == 0) {
/* 394 */         getAttributeInstance(GenericAttributes.ARMOR).addModifier(bq);
/* 395 */         playSound(SoundEffects.ENTITY_SHULKER_CLOSE, 1.0F, 1.0F);
/*     */       } else {
/* 397 */         playSound(SoundEffects.ENTITY_SHULKER_OPEN, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 401 */     this.datawatcher.set(d, Byte.valueOf((byte)i));
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 406 */     return 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int O() {
/* 411 */     return 180;
/*     */   }
/*     */ 
/*     */   
/*     */   public int eo() {
/* 416 */     return 180;
/*     */   }
/*     */ 
/*     */   
/*     */   public void collide(Entity entity) {}
/*     */ 
/*     */   
/*     */   public float bf() {
/* 424 */     return 0.0F;
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityLiving> {
/*     */     public c(EntityShulker entityshulker) {
/* 430 */       super(entityshulker, EntityLiving.class, 10, true, false, entityliving -> entityliving instanceof IMonster);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 437 */       return (this.e.getScoreboardTeam() == null) ? false : super.a();
/*     */     }
/*     */ 
/*     */     
/*     */     protected AxisAlignedBB a(double d0) {
/* 442 */       EnumDirection enumdirection = ((EntityShulker)this.e).eL();
/*     */       
/* 444 */       return (enumdirection.n() == EnumDirection.EnumAxis.X) ? this.e.getBoundingBox().grow(4.0D, d0, d0) : ((enumdirection.n() == EnumDirection.EnumAxis.Z) ? this.e.getBoundingBox().grow(d0, d0, 4.0D) : this.e.getBoundingBox().grow(d0, 4.0D, d0));
/*     */     }
/*     */   }
/*     */   
/*     */   class d
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityHuman> {
/*     */     public d(EntityShulker entityshulker) {
/* 451 */       super(entityshulker, EntityHuman.class, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 456 */       return (EntityShulker.this.world.getDifficulty() == EnumDifficulty.PEACEFUL) ? false : super.a();
/*     */     }
/*     */ 
/*     */     
/*     */     protected AxisAlignedBB a(double d0) {
/* 461 */       EnumDirection enumdirection = ((EntityShulker)this.e).eL();
/*     */       
/* 463 */       return (enumdirection.n() == EnumDirection.EnumAxis.X) ? this.e.getBoundingBox().grow(4.0D, d0, d0) : ((enumdirection.n() == EnumDirection.EnumAxis.Z) ? this.e.getBoundingBox().grow(d0, d0, 4.0D) : this.e.getBoundingBox().grow(d0, 4.0D, d0));
/*     */     }
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoal {
/*     */     private int b;
/*     */     
/*     */     public a() {
/* 472 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 477 */       EntityLiving entityliving = EntityShulker.this.getGoalTarget();
/*     */       
/* 479 */       return (entityliving != null && entityliving.isAlive()) ? ((EntityShulker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL)) : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 484 */       this.b = 20;
/* 485 */       EntityShulker.this.a(100);
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 490 */       EntityShulker.this.a(0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 495 */       if (EntityShulker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
/* 496 */         this.b--;
/* 497 */         EntityLiving entityliving = EntityShulker.this.getGoalTarget();
/*     */         
/* 499 */         EntityShulker.this.getControllerLook().a(entityliving, 180.0F, 180.0F);
/* 500 */         double d0 = EntityShulker.this.h(entityliving);
/*     */         
/* 502 */         if (d0 < 400.0D) {
/* 503 */           if (this.b <= 0) {
/* 504 */             this.b = 20 + EntityShulker.this.random.nextInt(10) * 20 / 2;
/* 505 */             EntityShulker.this.world.addEntity(new EntityShulkerBullet(EntityShulker.this.world, EntityShulker.this, entityliving, EntityShulker.this.eL().n()));
/* 506 */             EntityShulker.this.playSound(SoundEffects.ENTITY_SHULKER_SHOOT, 2.0F, (EntityShulker.this.random.nextFloat() - EntityShulker.this.random.nextFloat()) * 0.2F + 1.0F);
/*     */           } 
/*     */         } else {
/* 509 */           EntityShulker.this.setGoalTarget((EntityLiving)null);
/*     */         } 
/*     */         
/* 512 */         super.e();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class e
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private int b;
/*     */     
/*     */     private e() {}
/*     */     
/*     */     public boolean a() {
/* 525 */       return (EntityShulker.this.getGoalTarget() == null && EntityShulker.this.random.nextInt(40) == 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 530 */       return (EntityShulker.this.getGoalTarget() == null && this.b > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 535 */       this.b = 20 * (1 + EntityShulker.this.random.nextInt(3));
/* 536 */       EntityShulker.this.a(30);
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 541 */       if (EntityShulker.this.getGoalTarget() == null) {
/* 542 */         EntityShulker.this.a(0);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 549 */       this.b--;
/*     */     }
/*     */   }
/*     */   
/*     */   class b
/*     */     extends EntityAIBodyControl {
/*     */     public b(EntityInsentient entityinsentient) {
/* 556 */       super(entityinsentient);
/*     */     }
/*     */     
/*     */     public void a() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityShulker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */