/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityBlaze
/*     */   extends EntityMonster
/*     */ {
/*  33 */   private float b = 0.5F;
/*     */   
/*     */   private int c;
/*  36 */   private static final DataWatcherObject<Byte> d = DataWatcher.a((Class)EntityBlaze.class, DataWatcherRegistry.a);
/*     */   
/*     */   public EntityBlaze(EntityTypes<? extends EntityBlaze> var0, World var1) {
/*  39 */     super((EntityTypes)var0, var1);
/*     */     
/*  41 */     a(PathType.WATER, -1.0F);
/*  42 */     a(PathType.LAVA, 8.0F);
/*  43 */     a(PathType.DANGER_FIRE, 0.0F);
/*  44 */     a(PathType.DAMAGE_FIRE, 0.0F);
/*  45 */     this.f = 10;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  50 */     this.goalSelector.a(4, new PathfinderGoalBlazeFireball(this));
/*  51 */     this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
/*  52 */     this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
/*  53 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  54 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */     
/*  56 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
/*  57 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  61 */     return EntityMonster.eR()
/*  62 */       .a(GenericAttributes.ATTACK_DAMAGE, 6.0D)
/*  63 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.23000000417232513D)
/*  64 */       .a(GenericAttributes.FOLLOW_RANGE, 48.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  69 */     super.initDatawatcher();
/*     */     
/*  71 */     this.datawatcher.register(d, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  76 */     return SoundEffects.ENTITY_BLAZE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/*  81 */     return SoundEffects.ENTITY_BLAZE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  86 */     return SoundEffects.ENTITY_BLAZE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   public float aQ() {
/*  91 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  97 */     if (!this.onGround && (getMot()).y < 0.0D) {
/*  98 */       setMot(getMot().d(1.0D, 0.6D, 1.0D));
/*     */     }
/*     */     
/* 101 */     if (this.world.isClientSide) {
/* 102 */       if (this.random.nextInt(24) == 0 && !isSilent()) {
/* 103 */         this.world.a(locX() + 0.5D, locY() + 0.5D, locZ() + 0.5D, SoundEffects.ENTITY_BLAZE_BURN, getSoundCategory(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
/*     */       }
/* 105 */       for (int var0 = 0; var0 < 2; var0++) {
/* 106 */         this.world.addParticle(Particles.LARGE_SMOKE, d(0.5D), cE(), g(0.5D), 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     } 
/*     */     
/* 110 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dN() {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 120 */     this.c--;
/* 121 */     if (this.c <= 0) {
/* 122 */       this.c = 100;
/* 123 */       this.b = 0.5F + (float)this.random.nextGaussian() * 3.0F;
/*     */     } 
/*     */     
/* 126 */     EntityLiving var0 = getGoalTarget();
/* 127 */     if (var0 != null && var0.getHeadY() > getHeadY() + this.b && c(var0)) {
/* 128 */       Vec3D var1 = getMot();
/* 129 */       setMot(getMot().add(0.0D, (0.30000001192092896D - var1.y) * 0.30000001192092896D, 0.0D));
/* 130 */       this.impulse = true;
/*     */     } 
/*     */     
/* 133 */     super.mobTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float var0, float var1) {
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 143 */     return eK();
/*     */   }
/*     */   
/*     */   private boolean eK() {
/* 147 */     return ((((Byte)this.datawatcher.<Byte>get(d)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   private void t(boolean var0) {
/* 151 */     byte var1 = ((Byte)this.datawatcher.<Byte>get(d)).byteValue();
/* 152 */     if (var0) {
/* 153 */       var1 = (byte)(var1 | 0x1);
/*     */     } else {
/* 155 */       var1 = (byte)(var1 & 0xFFFFFFFE);
/*     */     } 
/* 157 */     this.datawatcher.set(d, Byte.valueOf(var1));
/*     */   }
/*     */   
/*     */   static class PathfinderGoalBlazeFireball extends PathfinderGoal {
/*     */     private final EntityBlaze a;
/*     */     private int b;
/*     */     private int c;
/*     */     private int d;
/*     */     
/*     */     public PathfinderGoalBlazeFireball(EntityBlaze var0) {
/* 167 */       this.a = var0;
/*     */       
/* 169 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 174 */       EntityLiving var0 = this.a.getGoalTarget();
/* 175 */       return (var0 != null && var0.isAlive() && this.a.c(var0));
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 180 */       this.b = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 185 */       EntityBlaze.a(this.a, false);
/* 186 */       this.d = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 191 */       this.c--;
/*     */       
/* 193 */       EntityLiving var0 = this.a.getGoalTarget();
/*     */       
/* 195 */       if (var0 == null) {
/*     */         return;
/*     */       }
/*     */       
/* 199 */       boolean var1 = this.a.getEntitySenses().a(var0);
/*     */       
/* 201 */       if (var1) {
/* 202 */         this.d = 0;
/*     */       } else {
/* 204 */         this.d++;
/*     */       } 
/*     */       
/* 207 */       double var2 = this.a.h(var0);
/*     */       
/* 209 */       if (var2 < 4.0D) {
/* 210 */         if (!var1) {
/*     */           return;
/*     */         }
/*     */         
/* 214 */         if (this.c <= 0) {
/* 215 */           this.c = 20;
/* 216 */           this.a.attackEntity(var0);
/*     */         } 
/* 218 */         this.a.getControllerMove().a(var0.locX(), var0.locY(), var0.locZ(), 1.0D);
/* 219 */       } else if (var2 < g() * g() && var1) {
/* 220 */         double var4 = var0.locX() - this.a.locX();
/* 221 */         double var6 = var0.e(0.5D) - this.a.e(0.5D);
/* 222 */         double var8 = var0.locZ() - this.a.locZ();
/*     */         
/* 224 */         if (this.c <= 0) {
/* 225 */           this.b++;
/* 226 */           if (this.b == 1) {
/* 227 */             this.c = 60;
/* 228 */             EntityBlaze.a(this.a, true);
/* 229 */           } else if (this.b <= 4) {
/* 230 */             this.c = 6;
/*     */           } else {
/* 232 */             this.c = 100;
/* 233 */             this.b = 0;
/* 234 */             EntityBlaze.a(this.a, false);
/*     */           } 
/*     */           
/* 237 */           if (this.b > 1) {
/* 238 */             float var10 = MathHelper.c(MathHelper.sqrt(var2)) * 0.5F;
/*     */             
/* 240 */             if (!this.a.isSilent()) {
/* 241 */               this.a.world.a((EntityHuman)null, 1018, this.a.getChunkCoordinates(), 0);
/*     */             }
/* 243 */             for (int var11 = 0; var11 < 1; var11++) {
/* 244 */               EntitySmallFireball var12 = new EntitySmallFireball(this.a.world, this.a, var4 + this.a.getRandom().nextGaussian() * var10, var6, var8 + this.a.getRandom().nextGaussian() * var10);
/* 245 */               var12.setPosition(var12.locX(), this.a.e(0.5D) + 0.5D, var12.locZ());
/* 246 */               this.a.world.addEntity(var12);
/*     */             } 
/*     */           } 
/*     */         } 
/* 250 */         this.a.getControllerLook().a(var0, 10.0F, 10.0F);
/*     */       }
/* 252 */       else if (this.d < 5) {
/* 253 */         this.a.getControllerMove().a(var0.locX(), var0.locY(), var0.locZ(), 1.0D);
/*     */       } 
/*     */ 
/*     */       
/* 257 */       super.e();
/*     */     }
/*     */     
/*     */     private double g() {
/* 261 */       return this.a.b(GenericAttributes.FOLLOW_RANGE);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityBlaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */