/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class EntitySquid
/*     */   extends EntityWaterAnimal {
/*     */   public float b;
/*     */   public float c;
/*     */   public float d;
/*     */   public float bo;
/*     */   public float bp;
/*     */   public float bq;
/*     */   public float br;
/*     */   public float bs;
/*     */   private float bt;
/*     */   private float bu;
/*     */   private float bv;
/*     */   private float bw;
/*     */   private float bx;
/*     */   private float by;
/*     */   
/*     */   public EntitySquid(EntityTypes<? extends EntitySquid> entitytypes, World world) {
/*  23 */     super((EntityTypes)entitytypes, world);
/*     */     
/*  25 */     this.bu = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  30 */     this.goalSelector.a(0, new PathfinderGoalSquid(this));
/*  31 */     this.goalSelector.a(1, new a());
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  35 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  40 */     return entitysize.height * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  45 */     return SoundEffects.ENTITY_SQUID_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  50 */     return SoundEffects.ENTITY_SQUID_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  55 */     return SoundEffects.ENTITY_SQUID_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/*  60 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  70 */     super.movementTick();
/*  71 */     this.c = this.b;
/*  72 */     this.bo = this.d;
/*  73 */     this.bq = this.bp;
/*  74 */     this.bs = this.br;
/*  75 */     this.bp += this.bu;
/*  76 */     if (this.bp > 6.283185307179586D) {
/*  77 */       if (this.world.isClientSide) {
/*  78 */         this.bp = 6.2831855F;
/*     */       } else {
/*  80 */         this.bp = (float)(this.bp - 6.283185307179586D);
/*  81 */         if (this.random.nextInt(10) == 0) {
/*  82 */           this.bu = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
/*     */         }
/*     */         
/*  85 */         this.world.broadcastEntityEffect(this, (byte)19);
/*     */       } 
/*     */     }
/*     */     
/*  89 */     if (aG()) {
/*  90 */       if (this.bp < 3.1415927F) {
/*  91 */         float f = this.bp / 3.1415927F;
/*     */         
/*  93 */         this.br = MathHelper.sin(f * f * 3.1415927F) * 3.1415927F * 0.25F;
/*  94 */         if (f > 0.75D) {
/*  95 */           this.bt = 1.0F;
/*  96 */           this.bv = 1.0F;
/*     */         } else {
/*  98 */           this.bv *= 0.8F;
/*     */         } 
/*     */       } else {
/* 101 */         this.br = 0.0F;
/* 102 */         this.bt *= 0.9F;
/* 103 */         this.bv *= 0.99F;
/*     */       } 
/*     */       
/* 106 */       if (!this.world.isClientSide) {
/* 107 */         setMot((this.bw * this.bt), (this.bx * this.bt), (this.by * this.bt));
/*     */       }
/*     */       
/* 110 */       Vec3D vec3d = getMot();
/* 111 */       float f1 = MathHelper.sqrt(c(vec3d));
/*     */       
/* 113 */       this.aA += (-((float)MathHelper.d(vec3d.x, vec3d.z)) * 57.295776F - this.aA) * 0.1F;
/* 114 */       this.yaw = this.aA;
/* 115 */       this.d = (float)(this.d + Math.PI * this.bv * 1.5D);
/* 116 */       this.b += (-((float)MathHelper.d(f1, vec3d.y)) * 57.295776F - this.b) * 0.1F;
/*     */     } else {
/* 118 */       this.br = MathHelper.e(MathHelper.sin(this.bp)) * 3.1415927F * 0.25F;
/* 119 */       if (!this.world.isClientSide) {
/* 120 */         double d0 = (getMot()).y;
/*     */         
/* 122 */         if (hasEffect(MobEffects.LEVITATION)) {
/* 123 */           d0 = 0.05D * (getEffect(MobEffects.LEVITATION).getAmplifier() + 1);
/* 124 */         } else if (!isNoGravity()) {
/* 125 */           d0 -= 0.08D;
/*     */         } 
/*     */         
/* 128 */         setMot(0.0D, d0 * 0.9800000190734863D, 0.0D);
/*     */       } 
/*     */       
/* 131 */       this.b = (float)(this.b + (-90.0F - this.b) * 0.02D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 138 */     if (super.damageEntity(damagesource, f) && getLastDamager() != null) {
/* 139 */       eL();
/* 140 */       return true;
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private Vec3D i(Vec3D vec3d) {
/* 147 */     Vec3D vec3d1 = vec3d.a(this.c * 0.017453292F);
/*     */     
/* 149 */     vec3d1 = vec3d1.b(-this.aB * 0.017453292F);
/* 150 */     return vec3d1;
/*     */   }
/*     */   
/*     */   private void eL() {
/* 154 */     playSound(SoundEffects.ENTITY_SQUID_SQUIRT, getSoundVolume(), dG());
/* 155 */     Vec3D vec3d = i(new Vec3D(0.0D, -1.0D, 0.0D)).add(locX(), locY(), locZ());
/*     */     
/* 157 */     for (int i = 0; i < 30; i++) {
/* 158 */       Vec3D vec3d1 = i(new Vec3D(this.random.nextFloat() * 0.6D - 0.3D, -1.0D, this.random.nextFloat() * 0.6D - 0.3D));
/* 159 */       Vec3D vec3d2 = vec3d1.a(0.3D + (this.random.nextFloat() * 2.0F));
/*     */       
/* 161 */       ((WorldServer)this.world).a(Particles.SQUID_INK, vec3d.x, vec3d.y + 0.5D, vec3d.z, 0, vec3d2.x, vec3d2.y, vec3d2.z, 0.10000000149011612D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(Vec3D vec3d) {
/* 168 */     move(EnumMoveType.SELF, getMot());
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<EntitySquid> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 172 */     double maxHeight = ((generatoraccess.getMinecraftWorld()).paperConfig.squidMaxSpawnHeight > 0.0D) ? (generatoraccess.getMinecraftWorld()).paperConfig.squidMaxSpawnHeight : generatoraccess.getSeaLevel();
/* 173 */     return (blockposition.getY() > (generatoraccess.getMinecraftWorld()).spigotConfig.squidSpawnRangeMin && blockposition.getY() < maxHeight);
/*     */   }
/*     */   
/*     */   public void a(float f, float f1, float f2) {
/* 177 */     this.bw = f;
/* 178 */     this.bx = f1;
/* 179 */     this.by = f2;
/*     */   }
/*     */   
/*     */   public boolean eK() {
/* 183 */     return (this.bw != 0.0F || this.bx != 0.0F || this.by != 0.0F);
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private int b;
/*     */     
/*     */     private a() {}
/*     */     
/*     */     public boolean a() {
/* 194 */       EntityLiving entityliving = EntitySquid.this.getLastDamager();
/*     */       
/* 196 */       return (EntitySquid.this.isInWater() && entityliving != null) ? ((EntitySquid.this.h(entityliving) < 100.0D)) : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 201 */       this.b = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 206 */       this.b++;
/* 207 */       EntityLiving entityliving = EntitySquid.this.getLastDamager();
/*     */       
/* 209 */       if (entityliving != null) {
/* 210 */         Vec3D vec3d = new Vec3D(EntitySquid.this.locX() - entityliving.locX(), EntitySquid.this.locY() - entityliving.locY(), EntitySquid.this.locZ() - entityliving.locZ());
/* 211 */         IBlockData iblockdata = EntitySquid.this.world.getType(new BlockPosition(EntitySquid.this.locX() + vec3d.x, EntitySquid.this.locY() + vec3d.y, EntitySquid.this.locZ() + vec3d.z));
/* 212 */         Fluid fluid = EntitySquid.this.world.getFluid(new BlockPosition(EntitySquid.this.locX() + vec3d.x, EntitySquid.this.locY() + vec3d.y, EntitySquid.this.locZ() + vec3d.z));
/*     */         
/* 214 */         if (fluid.a(TagsFluid.WATER) || iblockdata.isAir()) {
/* 215 */           double d0 = vec3d.f();
/*     */           
/* 217 */           if (d0 > 0.0D) {
/* 218 */             vec3d.d();
/* 219 */             float f = 3.0F;
/*     */             
/* 221 */             if (d0 > 5.0D) {
/* 222 */               f = (float)(f - (d0 - 5.0D) / 5.0D);
/*     */             }
/*     */             
/* 225 */             if (f > 0.0F) {
/* 226 */               vec3d = vec3d.a(f);
/*     */             }
/*     */           } 
/*     */           
/* 230 */           if (iblockdata.isAir()) {
/* 231 */             vec3d = vec3d.a(0.0D, vec3d.y, 0.0D);
/*     */           }
/*     */           
/* 234 */           EntitySquid.this.a((float)vec3d.x / 20.0F, (float)vec3d.y / 20.0F, (float)vec3d.z / 20.0F);
/*     */         } 
/*     */         
/* 237 */         if (this.b % 10 == 5) {
/* 238 */           EntitySquid.this.world.addParticle(Particles.BUBBLE, EntitySquid.this.locX(), EntitySquid.this.locY(), EntitySquid.this.locZ(), 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class PathfinderGoalSquid
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntitySquid b;
/*     */     
/*     */     public PathfinderGoalSquid(EntitySquid entitysquid) {
/* 250 */       this.b = entitysquid;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 255 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 260 */       int i = this.b.dc();
/*     */       
/* 262 */       if (i > 100) {
/* 263 */         this.b.a(0.0F, 0.0F, 0.0F);
/* 264 */       } else if (this.b.getRandom().nextInt(50) == 0 || !this.b.inWater || !this.b.eK()) {
/* 265 */         float f = this.b.getRandom().nextFloat() * 6.2831855F;
/* 266 */         float f1 = MathHelper.cos(f) * 0.2F;
/* 267 */         float f2 = -0.1F + this.b.getRandom().nextFloat() * 0.2F;
/* 268 */         float f3 = MathHelper.sin(f) * 0.2F;
/*     */         
/* 270 */         this.b.a(f1, f2, f3);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */