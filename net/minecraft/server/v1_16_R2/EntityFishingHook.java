/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.entity.FishHook;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerFishEvent;
/*     */ 
/*     */ public class EntityFishingHook
/*     */   extends IProjectile {
/*     */   private final Random b;
/*     */   private boolean c;
/*     */   private int d;
/*  20 */   private static final DataWatcherObject<Integer> e = DataWatcher.a((Class)EntityFishingHook.class, DataWatcherRegistry.b);
/*  21 */   private static final DataWatcherObject<Boolean> f = DataWatcher.a((Class)EntityFishingHook.class, DataWatcherRegistry.i);
/*     */   private int g;
/*     */   private int ag;
/*     */   private int ah;
/*     */   private int ai;
/*     */   private float aj;
/*     */   private boolean ak;
/*     */   private Entity hooked;
/*     */   private HookState am;
/*     */   private final int an;
/*     */   private final int ao;
/*     */   
/*     */   private EntityFishingHook(World world, EntityHuman entityhuman, int i, int j) {
/*  34 */     super((EntityTypes)EntityTypes.FISHING_BOBBER, world);
/*  35 */     this.b = new Random();
/*  36 */     this.ak = true;
/*  37 */     this.am = HookState.FLYING;
/*  38 */     this.Y = true;
/*  39 */     setShooter(entityhuman);
/*  40 */     entityhuman.hookedFish = this;
/*  41 */     this.an = Math.max(0, i);
/*  42 */     this.ao = Math.max(0, j);
/*     */   }
/*     */   
/*     */   public EntityFishingHook(EntityHuman entityhuman, World world, int i, int j) {
/*  46 */     this(world, entityhuman, i, j);
/*  47 */     float f = entityhuman.pitch;
/*  48 */     float f1 = entityhuman.yaw;
/*  49 */     float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
/*  50 */     float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
/*  51 */     float f4 = -MathHelper.cos(-f * 0.017453292F);
/*  52 */     float f5 = MathHelper.sin(-f * 0.017453292F);
/*  53 */     double d0 = entityhuman.locX() - f3 * 0.3D;
/*  54 */     double d1 = entityhuman.getHeadY();
/*  55 */     double d2 = entityhuman.locZ() - f2 * 0.3D;
/*     */     
/*  57 */     setPositionRotation(d0, d1, d2, f1, f);
/*  58 */     Vec3D vec3d = new Vec3D(-f3, MathHelper.a(-(f5 / f4), -5.0F, 5.0F), -f2);
/*  59 */     double d3 = vec3d.f();
/*     */     
/*  61 */     vec3d = vec3d.d(0.6D / d3 + 0.5D + this.random.nextGaussian() * 0.0045D, 0.6D / d3 + 0.5D + this.random.nextGaussian() * 0.0045D, 0.6D / d3 + 0.5D + this.random.nextGaussian() * 0.0045D);
/*  62 */     setMot(vec3d);
/*  63 */     this.yaw = (float)(MathHelper.d(vec3d.x, vec3d.z) * 57.2957763671875D);
/*  64 */     this.pitch = (float)(MathHelper.d(vec3d.y, MathHelper.sqrt(c(vec3d))) * 57.2957763671875D);
/*  65 */     this.lastYaw = this.yaw;
/*  66 */     this.lastPitch = this.pitch;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  71 */     getDataWatcher().register(e, Integer.valueOf(0));
/*  72 */     getDataWatcher().register(f, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/*  77 */     if (e.equals(datawatcherobject)) {
/*  78 */       int i = ((Integer)getDataWatcher().<Integer>get(e)).intValue();
/*     */       
/*  80 */       this.hooked = (i > 0) ? this.world.getEntity(i - 1) : null;
/*     */     } 
/*     */     
/*  83 */     if (f.equals(datawatcherobject)) {
/*  84 */       this.c = ((Boolean)getDataWatcher().<Boolean>get(f)).booleanValue();
/*  85 */       if (this.c) {
/*  86 */         setMot((getMot()).x, (-0.4F * MathHelper.a(this.b, 0.6F, 1.0F)), (getMot()).z);
/*     */       }
/*     */     } 
/*     */     
/*  90 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  95 */     this.b.setSeed(getUniqueID().getLeastSignificantBits() ^ this.world.getTime());
/*  96 */     super.tick();
/*  97 */     EntityHuman entityhuman = getOwner();
/*     */     
/*  99 */     if (entityhuman == null) {
/* 100 */       die();
/* 101 */     } else if (this.world.isClientSide || !a(entityhuman)) {
/* 102 */       if (this.onGround) {
/* 103 */         this.g++;
/* 104 */         if (this.g >= 1200) {
/* 105 */           die();
/*     */           return;
/*     */         } 
/*     */       } else {
/* 109 */         this.g = 0;
/*     */       } 
/*     */       
/* 112 */       float f = 0.0F;
/* 113 */       BlockPosition blockposition = getChunkCoordinates();
/* 114 */       Fluid fluid = this.world.getFluid(blockposition);
/*     */       
/* 116 */       if (fluid.a(TagsFluid.WATER)) {
/* 117 */         f = fluid.getHeight(this.world, blockposition);
/*     */       }
/*     */       
/* 120 */       boolean flag = (f > 0.0F);
/*     */       
/* 122 */       if (this.am == HookState.FLYING) {
/* 123 */         if (this.hooked != null) {
/* 124 */           setMot(Vec3D.ORIGIN);
/* 125 */           this.am = HookState.HOOKED_IN_ENTITY;
/*     */           
/*     */           return;
/*     */         } 
/* 129 */         if (flag) {
/* 130 */           setMot(getMot().d(0.3D, 0.2D, 0.3D));
/* 131 */           this.am = HookState.BOBBING;
/*     */           
/*     */           return;
/*     */         } 
/* 135 */         m();
/*     */       } else {
/* 137 */         if (this.am == HookState.HOOKED_IN_ENTITY) {
/* 138 */           if (this.hooked != null) {
/* 139 */             if (this.hooked.dead) {
/* 140 */               this.hooked = null;
/* 141 */               this.am = HookState.FLYING;
/*     */             } else {
/* 143 */               setPosition(this.hooked.locX(), this.hooked.e(0.8D), this.hooked.locZ());
/*     */             } 
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 150 */         if (this.am == HookState.BOBBING) {
/* 151 */           Vec3D vec3d = getMot();
/* 152 */           double d0 = locY() + vec3d.y - blockposition.getY() - f;
/*     */           
/* 154 */           if (Math.abs(d0) < 0.01D) {
/* 155 */             d0 += Math.signum(d0) * 0.1D;
/*     */           }
/*     */           
/* 158 */           setMot(vec3d.x * 0.9D, vec3d.y - d0 * this.random.nextFloat() * 0.2D, vec3d.z * 0.9D);
/* 159 */           if (this.ag <= 0 && this.ai <= 0) {
/* 160 */             this.ak = true;
/*     */           } else {
/* 162 */             this.ak = (this.ak && this.d < 10 && b(blockposition));
/*     */           } 
/*     */           
/* 165 */           if (flag) {
/* 166 */             this.d = Math.max(0, this.d - 1);
/* 167 */             if (this.c) {
/* 168 */               setMot(getMot().add(0.0D, -0.1D * this.b.nextFloat() * this.b.nextFloat(), 0.0D));
/*     */             }
/*     */             
/* 171 */             if (!this.world.isClientSide) {
/* 172 */               a(blockposition);
/*     */             }
/*     */           } else {
/* 175 */             this.d = Math.min(10, this.d + 1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 180 */       if (!fluid.a(TagsFluid.WATER)) {
/* 181 */         setMot(getMot().add(0.0D, -0.03D, 0.0D));
/*     */       }
/*     */       
/* 184 */       move(EnumMoveType.SELF, getMot());
/* 185 */       x();
/* 186 */       if (this.am == HookState.FLYING && (this.onGround || this.positionChanged)) {
/* 187 */         setMot(Vec3D.ORIGIN);
/*     */       }
/*     */       
/* 190 */       double d1 = 0.92D;
/*     */       
/* 192 */       setMot(getMot().a(0.92D));
/* 193 */       ae();
/*     */       
/* 195 */       if (this.inPortal) {
/* 196 */         die();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(EntityHuman entityhuman) {
/* 203 */     ItemStack itemstack = entityhuman.getItemInMainHand();
/* 204 */     ItemStack itemstack1 = entityhuman.getItemInOffHand();
/* 205 */     boolean flag = (itemstack.getItem() == Items.FISHING_ROD);
/* 206 */     boolean flag1 = (itemstack1.getItem() == Items.FISHING_ROD);
/*     */     
/* 208 */     if (!entityhuman.dead && entityhuman.isAlive() && (flag || flag1) && h(entityhuman) <= 1024.0D) {
/* 209 */       return false;
/*     */     }
/* 211 */     die();
/* 212 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void m() {
/* 217 */     MovingObjectPosition movingobjectposition = ProjectileHelper.a(this, this::a);
/*     */     
/* 219 */     a(movingobjectposition);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(Entity entity) {
/* 224 */     return (super.a(entity) || (entity.isAlive() && entity instanceof EntityItem));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/* 229 */     super.a(movingobjectpositionentity);
/* 230 */     if (!this.world.isClientSide) {
/* 231 */       this.hooked = movingobjectpositionentity.getEntity();
/* 232 */       n();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionBlock movingobjectpositionblock) {
/* 239 */     super.a(movingobjectpositionblock);
/* 240 */     setMot(getMot().d().a(movingobjectpositionblock.a(this)));
/*     */   }
/*     */   
/*     */   private void n() {
/* 244 */     getDataWatcher().set(e, Integer.valueOf(this.hooked.getId() + 1));
/*     */   }
/*     */   
/*     */   private void a(BlockPosition blockposition) {
/* 248 */     WorldServer worldserver = (WorldServer)this.world;
/* 249 */     int i = 1;
/* 250 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/* 252 */     if (this.random.nextFloat() < 0.25F && this.world.isRainingAt(blockposition1)) {
/* 253 */       i++;
/*     */     }
/*     */     
/* 256 */     if (this.random.nextFloat() < 0.5F && !this.world.e(blockposition1)) {
/* 257 */       i--;
/*     */     }
/*     */     
/* 260 */     if (this.ag > 0) {
/* 261 */       this.ag--;
/* 262 */       if (this.ag <= 0) {
/* 263 */         this.ah = 0;
/* 264 */         this.ai = 0;
/* 265 */         getDataWatcher().set(EntityFishingHook.f, Boolean.valueOf(false));
/*     */         
/* 267 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)getOwner().getBukkitEntity(), null, (FishHook)getBukkitEntity(), PlayerFishEvent.State.FAILED_ATTEMPT);
/* 268 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 280 */     else if (this.ai > 0) {
/* 281 */       this.ai -= i;
/* 282 */       if (this.ai > 0) {
/* 283 */         this.aj = (float)(this.aj + this.random.nextGaussian() * 4.0D);
/* 284 */         float f = this.aj * 0.017453292F;
/* 285 */         float f1 = MathHelper.sin(f);
/* 286 */         float f2 = MathHelper.cos(f);
/* 287 */         double d0 = locX() + (f1 * this.ai * 0.1F);
/* 288 */         double d1 = (MathHelper.floor(locY()) + 1.0F);
/* 289 */         double d2 = locZ() + (f2 * this.ai * 0.1F);
/* 290 */         IBlockData iblockdata = worldserver.getType(new BlockPosition(d0, d1 - 1.0D, d2));
/* 291 */         if (iblockdata.a(Blocks.WATER)) {
/* 292 */           if (this.random.nextFloat() < 0.15F) {
/* 293 */             worldserver.a(Particles.BUBBLE, d0, d1 - 0.10000000149011612D, d2, 1, f1, 0.1D, f2, 0.0D);
/*     */           }
/*     */           
/* 296 */           float f3 = f1 * 0.04F;
/* 297 */           float f4 = f2 * 0.04F;
/*     */           
/* 299 */           worldserver.a(Particles.FISHING, d0, d1, d2, 0, f4, 0.01D, -f3, 1.0D);
/* 300 */           worldserver.a(Particles.FISHING, d0, d1, d2, 0, -f4, 0.01D, f3, 1.0D);
/*     */         } 
/*     */       } else {
/*     */         
/* 304 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)getOwner().getBukkitEntity(), null, (FishHook)getBukkitEntity(), PlayerFishEvent.State.BITE);
/* 305 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/* 306 */         if (playerFishEvent.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 310 */         playSound(SoundEffects.ENTITY_FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
/* 311 */         double d3 = locY() + 0.5D;
/*     */         
/* 313 */         worldserver.a(Particles.BUBBLE, locX(), d3, locZ(), (int)(1.0F + getWidth() * 20.0F), getWidth(), 0.0D, getWidth(), 0.20000000298023224D);
/* 314 */         worldserver.a(Particles.FISHING, locX(), d3, locZ(), (int)(1.0F + getWidth() * 20.0F), getWidth(), 0.0D, getWidth(), 0.20000000298023224D);
/* 315 */         this.ag = MathHelper.nextInt(this.random, 20, 40);
/* 316 */         getDataWatcher().set(EntityFishingHook.f, Boolean.valueOf(true));
/*     */       } 
/* 318 */     } else if (this.ah > 0) {
/* 319 */       this.ah -= i;
/* 320 */       float f = 0.15F;
/* 321 */       if (this.ah < 20) {
/* 322 */         f = (float)(f + (20 - this.ah) * 0.05D);
/* 323 */       } else if (this.ah < 40) {
/* 324 */         f = (float)(f + (40 - this.ah) * 0.02D);
/* 325 */       } else if (this.ah < 60) {
/* 326 */         f = (float)(f + (60 - this.ah) * 0.01D);
/*     */       } 
/*     */       
/* 329 */       if (this.random.nextFloat() < f) {
/* 330 */         float f1 = MathHelper.a(this.random, 0.0F, 360.0F) * 0.017453292F;
/* 331 */         float f2 = MathHelper.a(this.random, 25.0F, 60.0F);
/* 332 */         double d0 = locX() + (MathHelper.sin(f1) * f2 * 0.1F);
/* 333 */         double d1 = (MathHelper.floor(locY()) + 1.0F);
/* 334 */         double d2 = locZ() + (MathHelper.cos(f1) * f2 * 0.1F);
/* 335 */         IBlockData iblockdata = worldserver.getType(new BlockPosition(d0, d1 - 1.0D, d2));
/* 336 */         if (iblockdata.a(Blocks.WATER)) {
/* 337 */           worldserver.a(Particles.SPLASH, d0, d1, d2, 2 + this.random.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
/*     */         }
/*     */       } 
/*     */       
/* 341 */       if (this.ah <= 0) {
/* 342 */         this.aj = MathHelper.a(this.random, 0.0F, 360.0F);
/* 343 */         this.ai = MathHelper.nextInt(this.random, 20, 80);
/*     */       } 
/*     */     } else {
/* 346 */       this.ah = MathHelper.nextInt(this.random, this.world.paperConfig.fishingMinTicks, this.world.paperConfig.fishingMaxTicks);
/* 347 */       this.ah -= this.ao * 20 * 5;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean b(BlockPosition blockposition) {
/* 354 */     WaterPosition entityfishinghook_waterposition = WaterPosition.INVALID;
/*     */     
/* 356 */     for (int i = -1; i <= 2; i++) {
/* 357 */       WaterPosition entityfishinghook_waterposition1 = a(blockposition.b(-2, i, -2), blockposition.b(2, i, 2));
/*     */       
/* 359 */       switch (entityfishinghook_waterposition1) {
/*     */         case INVALID:
/* 361 */           return false;
/*     */         case ABOVE_WATER:
/* 363 */           if (entityfishinghook_waterposition == WaterPosition.INVALID) {
/* 364 */             return false;
/*     */           }
/*     */           break;
/*     */         case INSIDE_WATER:
/* 368 */           if (entityfishinghook_waterposition == WaterPosition.ABOVE_WATER) {
/* 369 */             return false;
/*     */           }
/*     */           break;
/*     */       } 
/* 373 */       entityfishinghook_waterposition = entityfishinghook_waterposition1;
/*     */     } 
/*     */     
/* 376 */     return true;
/*     */   }
/*     */   
/*     */   private WaterPosition a(BlockPosition blockposition, BlockPosition blockposition1) {
/* 380 */     return BlockPosition.b(blockposition, blockposition1).<WaterPosition>map(this::c).reduce((entityfishinghook_waterposition, entityfishinghook_waterposition1) -> (entityfishinghook_waterposition == entityfishinghook_waterposition1) ? entityfishinghook_waterposition : WaterPosition.INVALID)
/*     */       
/* 382 */       .orElse(WaterPosition.INVALID);
/*     */   }
/*     */   
/*     */   private WaterPosition c(BlockPosition blockposition) {
/* 386 */     IBlockData iblockdata = this.world.getType(blockposition);
/*     */     
/* 388 */     if (!iblockdata.isAir() && !iblockdata.a(Blocks.LILY_PAD)) {
/* 389 */       Fluid fluid = iblockdata.getFluid();
/*     */       
/* 391 */       return (fluid.a(TagsFluid.WATER) && fluid.isSource() && iblockdata.getCollisionShape(this.world, blockposition).isEmpty()) ? WaterPosition.INSIDE_WATER : WaterPosition.INVALID;
/*     */     } 
/* 393 */     return WaterPosition.ABOVE_WATER;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean g() {
/* 398 */     return this.ak;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {}
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */   public int b(ItemStack itemstack) {
/* 408 */     EntityHuman entityhuman = getOwner();
/*     */     
/* 410 */     if (!this.world.isClientSide && entityhuman != null) {
/* 411 */       int i = 0;
/*     */       
/* 413 */       if (this.hooked != null) {
/*     */         
/* 415 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)entityhuman.getBukkitEntity(), (Entity)this.hooked.getBukkitEntity(), (FishHook)getBukkitEntity(), PlayerFishEvent.State.CAUGHT_ENTITY);
/* 416 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*     */         
/* 418 */         if (playerFishEvent.isCancelled()) {
/* 419 */           return 0;
/*     */         }
/*     */         
/* 422 */         reel();
/* 423 */         CriterionTriggers.D.a((EntityPlayer)entityhuman, itemstack, this, Collections.emptyList());
/* 424 */         this.world.broadcastEntityEffect(this, (byte)31);
/* 425 */         i = (this.hooked instanceof EntityItem) ? 3 : 5;
/* 426 */       } else if (this.ag > 0) {
/* 427 */         LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder((WorldServer)this.world)).<Vec3D>set(LootContextParameters.ORIGIN, getPositionVector()).<ItemStack>set(LootContextParameters.TOOL, itemstack).<Entity>set(LootContextParameters.THIS_ENTITY, this).a(this.random).a(this.an + entityhuman.eT());
/* 428 */         LootTable loottable = this.world.getMinecraftServer().getLootTableRegistry().getLootTable(LootTables.ag);
/* 429 */         List<ItemStack> list = loottable.populateLoot(loottableinfo_builder.build(LootContextParameterSets.FISHING));
/*     */         
/* 431 */         CriterionTriggers.D.a((EntityPlayer)entityhuman, itemstack, this, list);
/* 432 */         Iterator<ItemStack> iterator = list.iterator();
/*     */         
/* 434 */         while (iterator.hasNext()) {
/* 435 */           ItemStack itemstack1 = iterator.next();
/* 436 */           EntityItem entityitem = new EntityItem(this.world, locX(), locY(), locZ(), itemstack1);
/*     */           
/* 438 */           PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)entityhuman.getBukkitEntity(), (Entity)entityitem.getBukkitEntity(), (FishHook)getBukkitEntity(), PlayerFishEvent.State.CAUGHT_FISH);
/* 439 */           playerFishEvent.setExpToDrop(this.random.nextInt(6) + 1);
/* 440 */           this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*     */           
/* 442 */           if (playerFishEvent.isCancelled()) {
/* 443 */             return 0;
/*     */           }
/*     */           
/* 446 */           double d0 = entityhuman.locX() - locX();
/* 447 */           double d1 = entityhuman.locY() - locY();
/* 448 */           double d2 = entityhuman.locZ() - locZ();
/* 449 */           double d3 = 0.1D;
/*     */           
/* 451 */           entityitem.setMot(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
/* 452 */           this.world.addEntity(entityitem);
/*     */           
/* 454 */           if (playerFishEvent.getExpToDrop() > 0) {
/* 455 */             entityhuman.world.addEntity(new EntityExperienceOrb(entityhuman.world, entityhuman.locX(), entityhuman.locY() + 0.5D, entityhuman.locZ() + 0.5D, playerFishEvent.getExpToDrop(), ExperienceOrb.SpawnReason.FISHING, getOwner(), this));
/*     */           }
/*     */           
/* 458 */           if (itemstack1.getItem().a(TagsItem.FISHES)) {
/* 459 */             entityhuman.a(StatisticList.FISH_CAUGHT, 1);
/*     */           }
/*     */         } 
/*     */         
/* 463 */         i = 1;
/*     */       } 
/*     */       
/* 466 */       if (this.onGround) {
/*     */         
/* 468 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)entityhuman.getBukkitEntity(), null, (FishHook)getBukkitEntity(), PlayerFishEvent.State.IN_GROUND);
/* 469 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*     */         
/* 471 */         if (playerFishEvent.isCancelled()) {
/* 472 */           return 0;
/*     */         }
/*     */         
/* 475 */         i = 2;
/*     */       } 
/*     */       
/* 478 */       if (i == 0) {
/* 479 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)entityhuman.getBukkitEntity(), null, (FishHook)getBukkitEntity(), PlayerFishEvent.State.REEL_IN);
/* 480 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/* 481 */         if (playerFishEvent.isCancelled()) {
/* 482 */           return 0;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 487 */       die();
/* 488 */       return i;
/*     */     } 
/* 490 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void reel() {
/* 495 */     Entity entity = getShooter();
/*     */     
/* 497 */     if (entity != null) {
/* 498 */       Vec3D vec3d = (new Vec3D(entity.locX() - locX(), entity.locY() - locY(), entity.locZ() - locZ())).a(0.1D);
/*     */       
/* 500 */       this.hooked.setMot(this.hooked.getMot().e(vec3d));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/* 506 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 511 */     super.die();
/* 512 */     EntityHuman entityhuman = getOwner();
/*     */     
/* 514 */     if (entityhuman != null) {
/* 515 */       entityhuman.hookedFish = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityHuman getOwner() {
/* 522 */     Entity entity = getShooter();
/*     */     
/* 524 */     return (entity instanceof EntityHuman) ? (EntityHuman)entity : null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity k() {
/* 529 */     return this.hooked;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPortal() {
/* 534 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 539 */     Entity entity = getShooter();
/*     */     
/* 541 */     return new PacketPlayOutSpawnEntity(this, (entity == null) ? getId() : entity.getId());
/*     */   }
/*     */   
/*     */   enum WaterPosition
/*     */   {
/* 546 */     ABOVE_WATER, INSIDE_WATER, INVALID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   enum HookState
/*     */   {
/* 553 */     FLYING, HOOKED_IN_ENTITY, BOBBING;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFishingHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */