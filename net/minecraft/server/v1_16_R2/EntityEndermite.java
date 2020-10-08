/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ 
/*     */ public class EntityEndermite
/*     */   extends EntityMonster
/*     */ {
/*     */   private int b;
/*     */   private boolean c;
/*     */   
/*     */   public EntityEndermite(EntityTypes<? extends EntityEndermite> var0, World var1) {
/*  38 */     super((EntityTypes)var0, var1);
/*  39 */     this.f = 3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  44 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  45 */     this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*  46 */     this.goalSelector.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  47 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  48 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */     
/*  50 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
/*  51 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose var0, EntitySize var1) {
/*  56 */     return 0.13F;
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  60 */     return EntityMonster.eR()
/*  61 */       .a(GenericAttributes.MAX_HEALTH, 8.0D)
/*  62 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.25D)
/*  63 */       .a(GenericAttributes.ATTACK_DAMAGE, 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  73 */     return SoundEffects.ENTITY_ENDERMITE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/*  78 */     return SoundEffects.ENTITY_ENDERMITE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  83 */     return SoundEffects.ENTITY_ENDERMITE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition var0, IBlockData var1) {
/*  88 */     playSound(SoundEffects.ENTITY_ENDERMITE_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/*  93 */     super.loadData(var0);
/*  94 */     this.b = var0.getInt("Lifetime");
/*  95 */     this.c = var0.getBoolean("PlayerSpawned");
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/* 100 */     super.saveData(var0);
/* 101 */     var0.setInt("Lifetime", this.b);
/* 102 */     var0.setBoolean("PlayerSpawned", this.c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 108 */     this.aA = this.yaw;
/*     */     
/* 110 */     super.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void n(float var0) {
/* 115 */     this.yaw = var0;
/* 116 */     super.n(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public double ba() {
/* 121 */     return 0.1D;
/*     */   }
/*     */   
/*     */   public boolean isPlayerSpawned() {
/* 125 */     return this.c;
/*     */   }
/*     */   
/*     */   public void setPlayerSpawned(boolean var0) {
/* 129 */     this.c = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 134 */     super.movementTick();
/*     */     
/* 136 */     if (this.world.isClientSide) {
/* 137 */       for (int var0 = 0; var0 < 2; var0++) {
/* 138 */         this.world.addParticle(Particles.PORTAL, d(0.5D), cE(), g(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
/*     */       }
/*     */     } else {
/* 141 */       if (!isPersistent()) {
/* 142 */         this.b++;
/*     */       }
/*     */       
/* 145 */       if (this.b >= 2400) {
/* 146 */         die();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<EntityEndermite> var0, GeneratorAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/* 152 */     if (c((EntityTypes)var0, var1, var2, var3, var4)) {
/* 153 */       EntityHuman var5 = var1.a(var3.getX() + 0.5D, var3.getY() + 0.5D, var3.getZ() + 0.5D, 5.0D, true);
/* 154 */       return (var5 == null);
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 161 */     return EnumMonsterType.ARTHROPOD;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEndermite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */