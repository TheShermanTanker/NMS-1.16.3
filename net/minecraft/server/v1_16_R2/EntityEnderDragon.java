/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.block.TNTPrimeEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ 
/*     */ public class EntityEnderDragon extends EntityInsentient implements IMonster {
/*  19 */   private static final Logger LOGGER = LogManager.getLogger();
/*  20 */   public static final DataWatcherObject<Integer> PHASE = DataWatcher.a((Class)EntityEnderDragon.class, DataWatcherRegistry.b);
/*  21 */   private static final PathfinderTargetCondition bw = (new PathfinderTargetCondition()).a(64.0D);
/*  22 */   public final double[][] c = new double[64][3];
/*  23 */   public int d = -1;
/*     */   public final EntityComplexPart[] children;
/*  25 */   public final EntityComplexPart bo = new EntityComplexPart(this, "head", 1.0F, 1.0F);
/*  26 */   private final EntityComplexPart by = new EntityComplexPart(this, "neck", 3.0F, 3.0F);
/*  27 */   private final EntityComplexPart bz = new EntityComplexPart(this, "body", 5.0F, 3.0F);
/*  28 */   private final EntityComplexPart bA = new EntityComplexPart(this, "tail", 2.0F, 2.0F);
/*  29 */   private final EntityComplexPart bB = new EntityComplexPart(this, "tail", 2.0F, 2.0F);
/*  30 */   private final EntityComplexPart bC = new EntityComplexPart(this, "tail", 2.0F, 2.0F);
/*  31 */   private final EntityComplexPart bD = new EntityComplexPart(this, "wing", 4.0F, 2.0F);
/*  32 */   private final EntityComplexPart bE = new EntityComplexPart(this, "wing", 4.0F, 2.0F);
/*     */   public float bp;
/*     */   public float bq;
/*     */   public boolean br;
/*     */   public int deathAnimationTicks;
/*     */   public float bt;
/*     */   @Nullable
/*     */   public EntityEnderCrystal currentEnderCrystal;
/*     */   @Nullable
/*     */   private final EnderDragonBattle bF;
/*     */   private final DragonControllerManager bG;
/*  43 */   private int bH = 100;
/*     */   private int bI;
/*  45 */   private final PathPoint[] bJ = new PathPoint[24];
/*  46 */   private final int[] bK = new int[24];
/*  47 */   private final Path bL = new Path();
/*  48 */   private Explosion explosionSource = new Explosion(null, this, null, null, Double.NaN, Double.NaN, Double.NaN, Float.NaN, true, Explosion.Effect.DESTROY);
/*     */   
/*     */   public EntityEnderDragon(EntityTypes<? extends EntityEnderDragon> entitytypes, World world) {
/*  51 */     super((EntityTypes)EntityTypes.ENDER_DRAGON, world);
/*  52 */     this.children = new EntityComplexPart[] { this.bo, this.by, this.bz, this.bA, this.bB, this.bC, this.bD, this.bE };
/*  53 */     setHealth(getMaxHealth());
/*  54 */     this.noclip = true;
/*  55 */     this.Y = true;
/*  56 */     if (world instanceof WorldServer) {
/*  57 */       this.bF = ((WorldServer)world).getDragonBattle();
/*     */     } else {
/*  59 */       this.bF = null;
/*     */     } 
/*     */     
/*  62 */     this.bG = new DragonControllerManager(this);
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  66 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 200.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  71 */     super.initDatawatcher();
/*  72 */     getDataWatcher().register(PHASE, Integer.valueOf(DragonControllerPhase.HOVER.b()));
/*     */   }
/*     */   
/*     */   public double[] a(int i, float f) {
/*  76 */     if (dk()) {
/*  77 */       f = 0.0F;
/*     */     }
/*     */     
/*  80 */     f = 1.0F - f;
/*  81 */     int j = this.d - i & 0x3F;
/*  82 */     int k = this.d - i - 1 & 0x3F;
/*  83 */     double[] adouble = new double[3];
/*  84 */     double d0 = this.c[j][0];
/*  85 */     double d1 = MathHelper.g(this.c[k][0] - d0);
/*     */     
/*  87 */     adouble[0] = d0 + d1 * f;
/*  88 */     d0 = this.c[j][1];
/*  89 */     d1 = this.c[k][1] - d0;
/*  90 */     adouble[1] = d0 + d1 * f;
/*  91 */     adouble[2] = MathHelper.d(f, this.c[j][2], this.c[k][2]);
/*  92 */     return adouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 100 */     if (this.world.isClientSide) {
/* 101 */       setHealth(getHealth());
/* 102 */       if (!isSilent()) {
/* 103 */         float f = MathHelper.cos(this.bq * 6.2831855F);
/* 104 */         float f1 = MathHelper.cos(this.bp * 6.2831855F);
/* 105 */         if (f1 <= -0.3F && f >= -0.3F) {
/* 106 */           this.world.a(locX(), locY(), locZ(), SoundEffects.ENTITY_ENDER_DRAGON_FLAP, getSoundCategory(), 5.0F, 0.8F + this.random.nextFloat() * 0.3F, false);
/*     */         }
/*     */         
/* 109 */         if (!this.bG.a().a() && --this.bH < 0) {
/* 110 */           this.world.a(locX(), locY(), locZ(), SoundEffects.ENTITY_ENDER_DRAGON_GROWL, getSoundCategory(), 2.5F, 0.8F + this.random.nextFloat() * 0.3F, false);
/* 111 */           this.bH = 200 + this.random.nextInt(200);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 116 */     this.bp = this.bq;
/* 117 */     if (dk()) {
/* 118 */       float f = (this.random.nextFloat() - 0.5F) * 8.0F;
/* 119 */       float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
/* 120 */       float f2 = (this.random.nextFloat() - 0.5F) * 8.0F;
/*     */       
/* 122 */       this.world.addParticle(Particles.EXPLOSION, locX() + f, locY() + 2.0D + f1, locZ() + f2, 0.0D, 0.0D, 0.0D);
/*     */     } else {
/* 124 */       eN();
/* 125 */       Vec3D vec3d = getMot();
/*     */       
/* 127 */       float f1 = 0.2F / (MathHelper.sqrt(c(vec3d)) * 10.0F + 1.0F);
/* 128 */       f1 *= (float)Math.pow(2.0D, vec3d.y);
/* 129 */       if (this.bG.a().a()) {
/* 130 */         this.bq += 0.1F;
/* 131 */       } else if (this.br) {
/* 132 */         this.bq += f1 * 0.5F;
/*     */       } else {
/* 134 */         this.bq += f1;
/*     */       } 
/*     */       
/* 137 */       this.yaw = MathHelper.g(this.yaw);
/* 138 */       if (isNoAI()) {
/* 139 */         this.bq = 0.5F;
/*     */       } else {
/* 141 */         if (this.d < 0) {
/* 142 */           for (int i = 0; i < this.c.length; i++) {
/* 143 */             this.c[i][0] = this.yaw;
/* 144 */             this.c[i][1] = locY();
/*     */           } 
/*     */         }
/*     */         
/* 148 */         if (++this.d == this.c.length) {
/* 149 */           this.d = 0;
/*     */         }
/*     */         
/* 152 */         this.c[this.d][0] = this.yaw;
/* 153 */         this.c[this.d][1] = locY();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 160 */         if (this.world.isClientSide) {
/* 161 */           if (this.aU > 0) {
/* 162 */             double d3 = locX() + (this.aV - locX()) / this.aU;
/*     */             
/* 164 */             double d0 = locY() + (this.aW - locY()) / this.aU;
/* 165 */             double d1 = locZ() + (this.aX - locZ()) / this.aU;
/* 166 */             double d2 = MathHelper.g(this.aY - this.yaw);
/* 167 */             this.yaw = (float)(this.yaw + d2 / this.aU);
/* 168 */             this.pitch = (float)(this.pitch + (this.aZ - this.pitch) / this.aU);
/* 169 */             this.aU--;
/* 170 */             setPosition(d3, d0, d1);
/* 171 */             setYawPitch(this.yaw, this.pitch);
/*     */           } 
/*     */           
/* 174 */           this.bG.a().b();
/*     */         } else {
/* 176 */           IDragonController idragoncontroller = this.bG.a();
/*     */           
/* 178 */           idragoncontroller.c();
/* 179 */           if (this.bG.a() != idragoncontroller) {
/* 180 */             idragoncontroller = this.bG.a();
/* 181 */             idragoncontroller.c();
/*     */           } 
/*     */           
/* 184 */           Vec3D vec3d1 = idragoncontroller.g();
/*     */           
/* 186 */           if (vec3d1 != null && idragoncontroller.getControllerPhase() != DragonControllerPhase.HOVER) {
/* 187 */             double d0 = vec3d1.x - locX();
/* 188 */             double d1 = vec3d1.y - locY();
/* 189 */             double d2 = vec3d1.z - locZ();
/* 190 */             double d4 = d0 * d0 + d1 * d1 + d2 * d2;
/* 191 */             float f5 = idragoncontroller.f();
/* 192 */             double d5 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*     */             
/* 194 */             if (d5 > 0.0D) {
/* 195 */               d1 = MathHelper.a(d1 / d5, -f5, f5);
/*     */             }
/*     */             
/* 198 */             setMot(getMot().add(0.0D, d1 * 0.01D, 0.0D));
/* 199 */             this.yaw = MathHelper.g(this.yaw);
/* 200 */             double d6 = MathHelper.a(MathHelper.g(180.0D - MathHelper.d(d0, d2) * 57.2957763671875D - this.yaw), -50.0D, 50.0D);
/* 201 */             Vec3D vec3d2 = vec3d1.a(locX(), locY(), locZ()).d();
/* 202 */             Vec3D vec3d3 = (new Vec3D(MathHelper.sin(this.yaw * 0.017453292F), (getMot()).y, -MathHelper.cos(this.yaw * 0.017453292F))).d();
/*     */             
/* 204 */             float f3 = Math.max(((float)vec3d3.b(vec3d2) + 0.5F) / 1.5F, 0.0F);
/* 205 */             this.bt *= 0.8F;
/* 206 */             this.bt = (float)(this.bt + d6 * idragoncontroller.h());
/* 207 */             this.yaw += this.bt * 0.1F;
/* 208 */             float f4 = (float)(2.0D / (d4 + 1.0D));
/* 209 */             float f6 = 0.06F;
/*     */             
/* 211 */             a(0.06F * (f3 * f4 + 1.0F - f4), new Vec3D(0.0D, 0.0D, -1.0D));
/* 212 */             if (this.br) {
/* 213 */               move(EnumMoveType.SELF, getMot().a(0.800000011920929D));
/*     */             } else {
/* 215 */               move(EnumMoveType.SELF, getMot());
/*     */             } 
/*     */             
/* 218 */             Vec3D vec3d4 = getMot().d();
/* 219 */             double d7 = 0.8D + 0.15D * (vec3d4.b(vec3d3) + 1.0D) / 2.0D;
/*     */             
/* 221 */             setMot(getMot().d(d7, 0.9100000262260437D, d7));
/*     */           } 
/*     */         } 
/*     */         
/* 225 */         this.aA = this.yaw;
/* 226 */         Vec3D[] avec3d = new Vec3D[this.children.length];
/*     */         
/* 228 */         for (int j = 0; j < this.children.length; j++) {
/* 229 */           avec3d[j] = new Vec3D(this.children[j].locX(), this.children[j].locY(), this.children[j].locZ());
/*     */         }
/*     */         
/* 232 */         float f7 = (float)(a(5, 1.0F)[1] - a(10, 1.0F)[1]) * 10.0F * 0.017453292F;
/* 233 */         float f8 = MathHelper.cos(f7);
/* 234 */         float f9 = MathHelper.sin(f7);
/* 235 */         float f10 = this.yaw * 0.017453292F;
/* 236 */         float f11 = MathHelper.sin(f10);
/* 237 */         float f12 = MathHelper.cos(f10);
/*     */         
/* 239 */         a(this.bz, (f11 * 0.5F), 0.0D, (-f12 * 0.5F));
/* 240 */         a(this.bD, (f12 * 4.5F), 2.0D, (f11 * 4.5F));
/* 241 */         a(this.bE, (f12 * -4.5F), 2.0D, (f11 * -4.5F));
/* 242 */         if (!this.world.isClientSide && this.hurtTicks == 0) {
/* 243 */           a(this.world.getEntities(this, this.bD.getBoundingBox().grow(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D), IEntitySelector.e));
/* 244 */           a(this.world.getEntities(this, this.bE.getBoundingBox().grow(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D), IEntitySelector.e));
/* 245 */           b(this.world.getEntities(this, this.bo.getBoundingBox().g(1.0D), IEntitySelector.e));
/* 246 */           b(this.world.getEntities(this, this.by.getBoundingBox().g(1.0D), IEntitySelector.e));
/*     */         } 
/*     */         
/* 249 */         float f13 = MathHelper.sin(this.yaw * 0.017453292F - this.bt * 0.01F);
/* 250 */         float f14 = MathHelper.cos(this.yaw * 0.017453292F - this.bt * 0.01F);
/* 251 */         float f15 = eM();
/*     */         
/* 253 */         a(this.bo, (f13 * 6.5F * f8), (f15 + f9 * 6.5F), (-f14 * 6.5F * f8));
/* 254 */         a(this.by, (f13 * 5.5F * f8), (f15 + f9 * 5.5F), (-f14 * 5.5F * f8));
/* 255 */         double[] adouble = a(5, 1.0F);
/*     */         
/*     */         int k;
/*     */         
/* 259 */         for (k = 0; k < 3; k++) {
/* 260 */           EntityComplexPart entitycomplexpart = null;
/*     */           
/* 262 */           if (k == 0) {
/* 263 */             entitycomplexpart = this.bA;
/*     */           }
/*     */           
/* 266 */           if (k == 1) {
/* 267 */             entitycomplexpart = this.bB;
/*     */           }
/*     */           
/* 270 */           if (k == 2) {
/* 271 */             entitycomplexpart = this.bC;
/*     */           }
/*     */           
/* 274 */           double[] adouble1 = a(12 + k * 2, 1.0F);
/* 275 */           float f16 = this.yaw * 0.017453292F + i(adouble1[0] - adouble[0]) * 0.017453292F;
/* 276 */           float f17 = MathHelper.sin(f16);
/* 277 */           float f18 = MathHelper.cos(f16);
/*     */           
/* 279 */           float f3 = 1.5F;
/* 280 */           float f4 = (k + 1) * 2.0F;
/* 281 */           a(entitycomplexpart, (-(f11 * 1.5F + f17 * f4) * f8), adouble1[1] - adouble[1] - ((f4 + 1.5F) * f9) + 1.5D, ((f12 * 1.5F + f18 * f4) * f8));
/*     */         } 
/*     */         
/* 284 */         if (!this.world.isClientSide) {
/* 285 */           this.br = b(this.bo.getBoundingBox()) | b(this.by.getBoundingBox()) | b(this.bz.getBoundingBox());
/* 286 */           if (this.bF != null) {
/* 287 */             this.bF.b(this);
/*     */           }
/*     */         } 
/*     */         
/* 291 */         for (k = 0; k < this.children.length; k++) {
/* 292 */           (this.children[k]).lastX = (avec3d[k]).x;
/* 293 */           (this.children[k]).lastY = (avec3d[k]).y;
/* 294 */           (this.children[k]).lastZ = (avec3d[k]).z;
/* 295 */           (this.children[k]).D = (avec3d[k]).x;
/* 296 */           (this.children[k]).E = (avec3d[k]).y;
/* 297 */           (this.children[k]).F = (avec3d[k]).z;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(EntityComplexPart entitycomplexpart, double d0, double d1, double d2) {
/* 305 */     entitycomplexpart.setPosition(locX() + d0, locY() + d1, locZ() + d2);
/*     */   }
/*     */   
/*     */   private float eM() {
/* 309 */     if (this.bG.a().a()) {
/* 310 */       return -1.0F;
/*     */     }
/* 312 */     double[] adouble = a(5, 1.0F);
/* 313 */     double[] adouble1 = a(0, 1.0F);
/*     */     
/* 315 */     return (float)(adouble[1] - adouble1[1]);
/*     */   }
/*     */ 
/*     */   
/*     */   private void eN() {
/* 320 */     if (this.currentEnderCrystal != null) {
/* 321 */       if (this.currentEnderCrystal.dead) {
/* 322 */         this.currentEnderCrystal = null;
/* 323 */       } else if (this.ticksLived % 10 == 0 && getHealth() < getMaxHealth()) {
/*     */         
/* 325 */         EntityRegainHealthEvent event = new EntityRegainHealthEvent((Entity)getBukkitEntity(), 1.0D, EntityRegainHealthEvent.RegainReason.ENDER_CRYSTAL);
/* 326 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 328 */         if (!event.isCancelled()) {
/* 329 */           setHealth((float)(getHealth() + event.getAmount()));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 335 */     if (this.random.nextInt(10) == 0) {
/* 336 */       List<EntityEnderCrystal> list = this.world.a(EntityEnderCrystal.class, getBoundingBox().g(32.0D));
/* 337 */       EntityEnderCrystal entityendercrystal = null;
/* 338 */       double d0 = Double.MAX_VALUE;
/* 339 */       Iterator<EntityEnderCrystal> iterator = list.iterator();
/*     */       
/* 341 */       while (iterator.hasNext()) {
/* 342 */         EntityEnderCrystal entityendercrystal1 = iterator.next();
/* 343 */         double d1 = entityendercrystal1.h(this);
/*     */         
/* 345 */         if (d1 < d0) {
/* 346 */           d0 = d1;
/* 347 */           entityendercrystal = entityendercrystal1;
/*     */         } 
/*     */       } 
/*     */       
/* 351 */       this.currentEnderCrystal = entityendercrystal;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(List<Entity> list) {
/* 357 */     double d0 = ((this.bz.getBoundingBox()).minX + (this.bz.getBoundingBox()).maxX) / 2.0D;
/* 358 */     double d1 = ((this.bz.getBoundingBox()).minZ + (this.bz.getBoundingBox()).maxZ) / 2.0D;
/* 359 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/* 361 */     while (iterator.hasNext()) {
/* 362 */       Entity entity = iterator.next();
/*     */       
/* 364 */       if (entity instanceof EntityLiving) {
/* 365 */         double d2 = entity.locX() - d0;
/* 366 */         double d3 = entity.locZ() - d1;
/* 367 */         double d4 = d2 * d2 + d3 * d3;
/*     */         
/* 369 */         entity.i(d2 / d4 * 4.0D, 0.20000000298023224D, d3 / d4 * 4.0D);
/* 370 */         if (!this.bG.a().a() && ((EntityLiving)entity).cZ() < entity.ticksLived - 2) {
/* 371 */           entity.damageEntity(DamageSource.mobAttack(this), 5.0F);
/* 372 */           a(this, entity);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(List<Entity> list) {
/* 380 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/* 382 */     while (iterator.hasNext()) {
/* 383 */       Entity entity = iterator.next();
/*     */       
/* 385 */       if (entity instanceof EntityLiving) {
/* 386 */         entity.damageEntity(DamageSource.mobAttack(this), 10.0F);
/* 387 */         a(this, entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float i(double d0) {
/* 394 */     return (float)MathHelper.g(d0);
/*     */   }
/*     */   
/*     */   private boolean b(AxisAlignedBB axisalignedbb) {
/* 398 */     int i = MathHelper.floor(axisalignedbb.minX);
/* 399 */     int j = MathHelper.floor(axisalignedbb.minY);
/* 400 */     int k = MathHelper.floor(axisalignedbb.minZ);
/* 401 */     int l = MathHelper.floor(axisalignedbb.maxX);
/* 402 */     int i1 = MathHelper.floor(axisalignedbb.maxY);
/* 403 */     int j1 = MathHelper.floor(axisalignedbb.maxZ);
/* 404 */     boolean flag = false;
/* 405 */     boolean flag1 = false;
/*     */     
/* 407 */     List<Block> destroyedBlocks = new ArrayList<>();
/*     */ 
/*     */     
/* 410 */     for (int k1 = i; k1 <= l; k1++) {
/* 411 */       for (int l1 = j; l1 <= i1; l1++) {
/* 412 */         for (int i2 = k; i2 <= j1; i2++) {
/* 413 */           BlockPosition blockposition = new BlockPosition(k1, l1, i2);
/* 414 */           IBlockData iblockdata = this.world.getType(blockposition);
/* 415 */           Block block = iblockdata.getBlock();
/*     */           
/* 417 */           if (!iblockdata.isAir() && iblockdata.getMaterial() != Material.FIRE) {
/* 418 */             if (this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) && !TagsBlock.DRAGON_IMMUNE.isTagged(block)) {
/*     */ 
/*     */               
/* 421 */               flag1 = true;
/* 422 */               destroyedBlocks.add(CraftBlock.at(this.world, blockposition));
/*     */             } else {
/*     */               
/* 425 */               flag = true;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 434 */     if (!flag1) {
/* 435 */       return flag;
/*     */     }
/*     */     
/* 438 */     CraftEntity craftEntity = getBukkitEntity();
/* 439 */     EntityExplodeEvent event = new EntityExplodeEvent((Entity)craftEntity, craftEntity.getLocation(), destroyedBlocks, 0.0F);
/* 440 */     craftEntity.getServer().getPluginManager().callEvent((Event)event);
/* 441 */     if (event.isCancelled())
/*     */     {
/*     */       
/* 444 */       return flag; } 
/* 445 */     if (event.getYield() == 0.0F) {
/*     */       
/* 447 */       for (Block block : event.blockList()) {
/* 448 */         this.world.a(new BlockPosition(block.getX(), block.getY(), block.getZ()), false);
/*     */       }
/*     */     } else {
/* 451 */       for (Block block : event.blockList()) {
/* 452 */         Material blockId = block.getType();
/* 453 */         if (blockId.isAir()) {
/*     */           continue;
/*     */         }
/*     */         
/* 457 */         CraftBlock craftBlock = (CraftBlock)block;
/* 458 */         BlockPosition blockposition = craftBlock.getPosition();
/*     */         
/* 460 */         Block nmsBlock = craftBlock.getNMS().getBlock();
/* 461 */         if (nmsBlock.a(this.explosionSource)) {
/* 462 */           TileEntity tileentity = nmsBlock.isTileEntity() ? this.world.getTileEntity(blockposition) : null;
/* 463 */           LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder((WorldServer)this.world)).a(this.world.random).<Vec3D>set(LootContextParameters.ORIGIN, Vec3D.a(blockposition)).<ItemStack>set(LootContextParameters.TOOL, ItemStack.b).<Float>set(LootContextParameters.EXPLOSION_RADIUS, Float.valueOf(1.0F / event.getYield())).setOptional(LootContextParameters.BLOCK_ENTITY, tileentity);
/*     */           
/* 465 */           craftBlock.getNMS().a(loottableinfo_builder).forEach(itemstack -> Block.a(this.world, blockposition, itemstack));
/*     */ 
/*     */           
/* 468 */           craftBlock.getNMS().dropNaturally((WorldServer)this.world, blockposition, ItemStack.b);
/*     */         } 
/*     */         
/* 471 */         Block tntBlock = this.world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 472 */         if (!(new TNTPrimeEvent(tntBlock, TNTPrimeEvent.PrimeReason.EXPLOSION, (Entity)this.explosionSource.getSource().getBukkitEntity())).callEvent()) {
/*     */           continue;
/*     */         }
/* 475 */         nmsBlock.wasExploded(this.world, blockposition, this.explosionSource);
/*     */         
/* 477 */         this.world.a(blockposition, false);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 482 */     if (flag1) {
/* 483 */       BlockPosition blockposition1 = new BlockPosition(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(i1 - j + 1), k + this.random.nextInt(j1 - k + 1));
/*     */       
/* 485 */       this.world.triggerEffect(2008, blockposition1, 0);
/*     */     } 
/*     */     
/* 488 */     return flag;
/*     */   }
/*     */   
/*     */   public boolean a(EntityComplexPart entitycomplexpart, DamageSource damagesource, float f) {
/* 492 */     if (this.bG.a().getControllerPhase() == DragonControllerPhase.DYING) {
/* 493 */       return false;
/*     */     }
/* 495 */     f = this.bG.a().a(damagesource, f);
/* 496 */     if (entitycomplexpart != this.bo) {
/* 497 */       f = f / 4.0F + Math.min(f, 1.0F);
/*     */     }
/*     */     
/* 500 */     if (f < 0.01F) {
/* 501 */       return false;
/*     */     }
/* 503 */     if (damagesource.getEntity() instanceof EntityHuman || damagesource.isExplosion()) {
/* 504 */       float f1 = getHealth();
/*     */       
/* 506 */       dealDamage(damagesource, f);
/* 507 */       if (dk() && !this.bG.a().a()) {
/* 508 */         setHealth(1.0F);
/* 509 */         this.bG.setControllerPhase(DragonControllerPhase.DYING);
/*     */       } 
/*     */       
/* 512 */       if (this.bG.a().a()) {
/* 513 */         this.bI = (int)(this.bI + f1 - getHealth());
/* 514 */         if (this.bI > 0.25F * getMaxHealth()) {
/* 515 */           this.bI = 0;
/* 516 */           this.bG.setControllerPhase(DragonControllerPhase.TAKEOFF);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 521 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 528 */     if (damagesource instanceof EntityDamageSource && ((EntityDamageSource)damagesource).y()) {
/* 529 */       a(this.bz, damagesource, f);
/*     */     }
/*     */     
/* 532 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean dealDamage(DamageSource damagesource, float f) {
/* 536 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void killEntity() {
/* 541 */     die();
/* 542 */     if (this.bF != null) {
/* 543 */       this.bF.b(this);
/* 544 */       this.bF.a(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void cT() {
/* 551 */     if (this.bF != null) {
/* 552 */       this.bF.b(this);
/*     */     }
/*     */     
/* 555 */     this.deathAnimationTicks++;
/* 556 */     if (this.deathAnimationTicks >= 180 && this.deathAnimationTicks <= 200) {
/* 557 */       float f = (this.random.nextFloat() - 0.5F) * 8.0F;
/* 558 */       float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
/* 559 */       float f2 = (this.random.nextFloat() - 0.5F) * 8.0F;
/*     */       
/* 561 */       this.world.addParticle(Particles.EXPLOSION_EMITTER, locX() + f, locY() + 2.0D + f1, locZ() + f2, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/* 564 */     boolean flag = this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
/* 565 */     short short0 = 500;
/*     */     
/* 567 */     if (this.bF != null && !this.bF.isPreviouslyKilled()) {
/* 568 */       short0 = 12000;
/*     */     }
/*     */     
/* 571 */     if (!this.world.isClientSide) {
/* 572 */       if (this.deathAnimationTicks > 150 && this.deathAnimationTicks % 5 == 0 && flag) {
/* 573 */         a(MathHelper.d(short0 * 0.08F));
/*     */       }
/*     */       
/* 576 */       if (this.deathAnimationTicks == 1 && !isSilent()) {
/*     */ 
/*     */         
/* 579 */         int viewDistance = ((WorldServer)this.world).getServer().getViewDistance() * 16;
/* 580 */         for (EntityPlayer player : ((WorldServer)this.world).getPlayers()) {
/*     */ 
/*     */           
/* 583 */           double deltaX = locX() - player.locX();
/* 584 */           double deltaZ = locZ() - player.locZ();
/* 585 */           double distanceSquared = deltaX * deltaX + deltaZ * deltaZ;
/* 586 */           if (this.world.spigotConfig.dragonDeathSoundRadius > 0 && distanceSquared > (this.world.spigotConfig.dragonDeathSoundRadius * this.world.spigotConfig.dragonDeathSoundRadius))
/* 587 */             continue;  if (distanceSquared > (viewDistance * viewDistance)) {
/* 588 */             double deltaLength = Math.sqrt(distanceSquared);
/* 589 */             double relativeX = player.locX() + deltaX / deltaLength * viewDistance;
/* 590 */             double relativeZ = player.locZ() + deltaZ / deltaLength * viewDistance;
/* 591 */             player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1028, new BlockPosition((int)relativeX, (int)locY(), (int)relativeZ), 0, true)); continue;
/*     */           } 
/* 593 */           player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1028, new BlockPosition((int)locX(), (int)locY(), (int)locZ()), 0, true));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 600 */     move(EnumMoveType.SELF, new Vec3D(0.0D, 0.10000000149011612D, 0.0D));
/* 601 */     this.yaw += 20.0F;
/* 602 */     this.aA = this.yaw;
/* 603 */     if (this.deathAnimationTicks == 200 && !this.world.isClientSide) {
/* 604 */       if (flag) {
/* 605 */         a(MathHelper.d(short0 * 0.2F));
/*     */       }
/*     */       
/* 608 */       if (this.bF != null) {
/* 609 */         this.bF.a(this);
/*     */       }
/*     */       
/* 612 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(int i) {
/* 618 */     while (i > 0) {
/* 619 */       int j = EntityExperienceOrb.getOrbValue(i);
/*     */       
/* 621 */       i -= j;
/* 622 */       this.world.addEntity(new EntityExperienceOrb(this.world, locX(), locY(), locZ(), j, ExperienceOrb.SpawnReason.ENTITY_DEATH, this.killer, this));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int eI() {
/* 628 */     if (this.bJ[0] == null) {
/* 629 */       for (int i = 0; i < 24; i++) {
/* 630 */         int k, l, j = 5;
/*     */ 
/*     */ 
/*     */         
/* 634 */         if (i < 12) {
/* 635 */           k = MathHelper.d(60.0F * MathHelper.cos(2.0F * (-3.1415927F + 0.2617994F * i)));
/* 636 */           l = MathHelper.d(60.0F * MathHelper.sin(2.0F * (-3.1415927F + 0.2617994F * i)));
/*     */ 
/*     */         
/*     */         }
/* 640 */         else if (i < 20) {
/* 641 */           int i1 = i - 12;
/* 642 */           k = MathHelper.d(40.0F * MathHelper.cos(2.0F * (-3.1415927F + 0.3926991F * i1)));
/* 643 */           l = MathHelper.d(40.0F * MathHelper.sin(2.0F * (-3.1415927F + 0.3926991F * i1)));
/* 644 */           j += 10;
/*     */         } else {
/* 646 */           int i1 = i - 20;
/* 647 */           k = MathHelper.d(20.0F * MathHelper.cos(2.0F * (-3.1415927F + 0.7853982F * i1)));
/* 648 */           l = MathHelper.d(20.0F * MathHelper.sin(2.0F * (-3.1415927F + 0.7853982F * i1)));
/*     */         } 
/*     */ 
/*     */         
/* 652 */         int j1 = Math.max(this.world.getSeaLevel() + 10, this.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPosition(k, 0, l)).getY() + j);
/*     */         
/* 654 */         this.bJ[i] = new PathPoint(k, j1, l);
/*     */       } 
/*     */       
/* 657 */       this.bK[0] = 6146;
/* 658 */       this.bK[1] = 8197;
/* 659 */       this.bK[2] = 8202;
/* 660 */       this.bK[3] = 16404;
/* 661 */       this.bK[4] = 32808;
/* 662 */       this.bK[5] = 32848;
/* 663 */       this.bK[6] = 65696;
/* 664 */       this.bK[7] = 131392;
/* 665 */       this.bK[8] = 131712;
/* 666 */       this.bK[9] = 263424;
/* 667 */       this.bK[10] = 526848;
/* 668 */       this.bK[11] = 525313;
/* 669 */       this.bK[12] = 1581057;
/* 670 */       this.bK[13] = 3166214;
/* 671 */       this.bK[14] = 2138120;
/* 672 */       this.bK[15] = 6373424;
/* 673 */       this.bK[16] = 4358208;
/* 674 */       this.bK[17] = 12910976;
/* 675 */       this.bK[18] = 9044480;
/* 676 */       this.bK[19] = 9706496;
/* 677 */       this.bK[20] = 15216640;
/* 678 */       this.bK[21] = 13688832;
/* 679 */       this.bK[22] = 11763712;
/* 680 */       this.bK[23] = 8257536;
/*     */     } 
/*     */     
/* 683 */     return p(locX(), locY(), locZ());
/*     */   }
/*     */   
/*     */   public int p(double d0, double d1, double d2) {
/* 687 */     float f = 10000.0F;
/* 688 */     int i = 0;
/* 689 */     PathPoint pathpoint = new PathPoint(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2));
/* 690 */     byte b0 = 0;
/*     */     
/* 692 */     if (this.bF == null || this.bF.c() == 0) {
/* 693 */       b0 = 12;
/*     */     }
/*     */     
/* 696 */     for (int j = b0; j < 24; j++) {
/* 697 */       if (this.bJ[j] != null) {
/* 698 */         float f1 = this.bJ[j].b(pathpoint);
/*     */         
/* 700 */         if (f1 < f) {
/* 701 */           f = f1;
/* 702 */           i = j;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 707 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PathEntity a(int i, int j, @Nullable PathPoint pathpoint) {
/* 714 */     for (int k = 0; k < 24; k++) {
/* 715 */       PathPoint pathPoint = this.bJ[k];
/* 716 */       pathPoint.i = false;
/* 717 */       pathPoint.g = 0.0F;
/* 718 */       pathPoint.e = 0.0F;
/* 719 */       pathPoint.f = 0.0F;
/* 720 */       pathPoint.h = null;
/* 721 */       pathPoint.d = -1;
/*     */     } 
/*     */     
/* 724 */     PathPoint pathpoint2 = this.bJ[i];
/*     */     
/* 726 */     PathPoint pathpoint1 = this.bJ[j];
/* 727 */     pathpoint2.e = 0.0F;
/* 728 */     pathpoint2.f = pathpoint2.a(pathpoint1);
/* 729 */     pathpoint2.g = pathpoint2.f;
/* 730 */     this.bL.a();
/* 731 */     this.bL.a(pathpoint2);
/* 732 */     PathPoint pathpoint3 = pathpoint2;
/* 733 */     byte b0 = 0;
/*     */     
/* 735 */     if (this.bF == null || this.bF.c() == 0) {
/* 736 */       b0 = 12;
/*     */     }
/*     */ 
/*     */     
/* 740 */     while (!this.bL.e()) {
/* 741 */       PathPoint pathpoint4 = this.bL.c();
/*     */       
/* 743 */       if (pathpoint4.equals(pathpoint1)) {
/* 744 */         if (pathpoint != null) {
/* 745 */           pathpoint.h = pathpoint1;
/* 746 */           pathpoint1 = pathpoint;
/*     */         } 
/*     */         
/* 749 */         return a(pathpoint2, pathpoint1);
/*     */       } 
/*     */       
/* 752 */       if (pathpoint4.a(pathpoint1) < pathpoint3.a(pathpoint1)) {
/* 753 */         pathpoint3 = pathpoint4;
/*     */       }
/*     */       
/* 756 */       pathpoint4.i = true;
/* 757 */       int l = 0;
/* 758 */       int i1 = 0;
/*     */ 
/*     */       
/* 761 */       while (i1 < 24) {
/* 762 */         if (this.bJ[i1] != pathpoint4) {
/* 763 */           i1++;
/*     */           
/*     */           continue;
/*     */         } 
/* 767 */         l = i1;
/*     */       } 
/*     */       
/* 770 */       i1 = b0;
/*     */ 
/*     */       
/* 773 */       while (i1 < 24) {
/*     */ 
/*     */ 
/*     */         
/* 777 */         if ((this.bK[l] & 1 << i1) > 0) {
/* 778 */           PathPoint pathpoint5 = this.bJ[i1];
/*     */           
/* 780 */           if (!pathpoint5.i) {
/* 781 */             float f = pathpoint4.e + pathpoint4.a(pathpoint5);
/*     */             
/* 783 */             if (!pathpoint5.c() || f < pathpoint5.e) {
/* 784 */               pathpoint5.h = pathpoint4;
/* 785 */               pathpoint5.e = f;
/* 786 */               pathpoint5.f = pathpoint5.a(pathpoint1);
/* 787 */               if (pathpoint5.c()) {
/* 788 */                 this.bL.a(pathpoint5, pathpoint5.e + pathpoint5.f);
/*     */               } else {
/* 790 */                 pathpoint5.g = pathpoint5.e + pathpoint5.f;
/* 791 */                 this.bL.a(pathpoint5);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 797 */         i1++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 802 */     if (pathpoint3 == pathpoint2) {
/* 803 */       return null;
/*     */     }
/* 805 */     LOGGER.debug("Failed to find path from {} to {}", Integer.valueOf(i), Integer.valueOf(j));
/* 806 */     if (pathpoint != null) {
/* 807 */       pathpoint.h = pathpoint3;
/* 808 */       pathpoint3 = pathpoint;
/*     */     } 
/*     */     
/* 811 */     return a(pathpoint2, pathpoint3);
/*     */   }
/*     */ 
/*     */   
/*     */   private PathEntity a(PathPoint pathpoint, PathPoint pathpoint1) {
/* 816 */     List<PathPoint> list = Lists.newArrayList();
/* 817 */     PathPoint pathpoint2 = pathpoint1;
/*     */     
/* 819 */     list.add(0, pathpoint1);
/*     */     
/* 821 */     while (pathpoint2.h != null) {
/* 822 */       pathpoint2 = pathpoint2.h;
/* 823 */       list.add(0, pathpoint2);
/*     */     } 
/*     */     
/* 826 */     return new PathEntity(list, new BlockPosition(pathpoint1.a, pathpoint1.b, pathpoint1.c), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 831 */     super.saveData(nbttagcompound);
/* 832 */     nbttagcompound.setInt("DragonPhase", this.bG.a().getControllerPhase().b());
/* 833 */     nbttagcompound.setInt("Paper.DeathTick", this.deathAnimationTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 838 */     super.loadData(nbttagcompound);
/* 839 */     if (nbttagcompound.hasKey("DragonPhase")) {
/* 840 */       this.bG.setControllerPhase(DragonControllerPhase.getById(nbttagcompound.getInt("DragonPhase")));
/*     */     }
/* 842 */     this.deathAnimationTicks = nbttagcompound.getInt("Paper.DeathTick");
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkDespawn() {}
/*     */ 
/*     */   
/*     */   public EntityComplexPart[] eJ() {
/* 850 */     return this.children;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 855 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/* 860 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 865 */     return SoundEffects.ENTITY_ENDER_DRAGON_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 870 */     return SoundEffects.ENTITY_ENDER_DRAGON_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 875 */     return 5.0F;
/*     */   }
/*     */   public Vec3D x(float f) {
/*     */     Vec3D vec3d;
/* 879 */     IDragonController idragoncontroller = this.bG.a();
/* 880 */     DragonControllerPhase<? extends IDragonController> dragoncontrollerphase = idragoncontroller.getControllerPhase();
/*     */ 
/*     */ 
/*     */     
/* 884 */     if (dragoncontrollerphase != DragonControllerPhase.LANDING && dragoncontrollerphase != DragonControllerPhase.TAKEOFF) {
/* 885 */       if (idragoncontroller.a()) {
/* 886 */         float f2 = this.pitch;
/*     */         
/* 888 */         float f1 = 1.5F;
/* 889 */         this.pitch = -45.0F;
/* 890 */         vec3d = f(f);
/* 891 */         this.pitch = f2;
/*     */       } else {
/* 893 */         vec3d = f(f);
/*     */       } 
/*     */     } else {
/* 896 */       BlockPosition blockposition = this.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, WorldGenEndTrophy.a);
/*     */       
/* 898 */       float f1 = Math.max(MathHelper.sqrt(blockposition.a(getPositionVector(), true)) / 4.0F, 1.0F);
/* 899 */       float f3 = 6.0F / f1;
/* 900 */       float f4 = this.pitch;
/* 901 */       float f5 = 1.5F;
/*     */       
/* 903 */       this.pitch = -f3 * 1.5F * 5.0F;
/* 904 */       vec3d = f(f);
/* 905 */       this.pitch = f4;
/*     */     } 
/*     */     
/* 908 */     return vec3d;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityEnderCrystal entityendercrystal, BlockPosition blockposition, DamageSource damagesource) {
/*     */     EntityHuman entityhuman;
/* 914 */     if (damagesource.getEntity() instanceof EntityHuman) {
/* 915 */       entityhuman = (EntityHuman)damagesource.getEntity();
/*     */     } else {
/* 917 */       entityhuman = this.world.a(bw, blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */     } 
/*     */     
/* 920 */     if (entityendercrystal == this.currentEnderCrystal) {
/* 921 */       a(this.bo, DamageSource.d(entityhuman), 10.0F);
/*     */     }
/*     */     
/* 924 */     this.bG.a().a(entityendercrystal, blockposition, damagesource, entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 929 */     if (PHASE.equals(datawatcherobject) && this.world.isClientSide) {
/* 930 */       this.bG.setControllerPhase(DragonControllerPhase.getById(((Integer)getDataWatcher().<Integer>get(PHASE)).intValue()));
/*     */     }
/*     */     
/* 933 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public DragonControllerManager getDragonControllerManager() {
/* 937 */     return this.bG;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EnderDragonBattle getEnderDragonBattle() {
/* 942 */     return this.bF;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addEffect(MobEffect mobeffect) {
/* 947 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean n(Entity entity) {
/* 952 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPortal() {
/* 957 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEnderDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */