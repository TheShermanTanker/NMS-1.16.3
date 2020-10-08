/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ 
/*     */ public class EntityIllagerIllusioner
/*     */   extends EntityIllagerWizard implements IRangedEntity {
/*     */   public EntityIllagerIllusioner(EntityTypes<? extends EntityIllagerIllusioner> entitytypes, World world) {
/*  11 */     super((EntityTypes)entitytypes, world);
/*  12 */     this.f = 5;
/*  13 */     this.bp = new Vec3D[2][4];
/*     */     
/*  15 */     for (int i = 0; i < 4; i++) {
/*  16 */       this.bp[0][i] = Vec3D.ORIGIN;
/*  17 */       this.bp[1][i] = Vec3D.ORIGIN;
/*     */     } 
/*     */   }
/*     */   private int bo;
/*     */   private final Vec3D[][] bp;
/*     */   
/*     */   protected void initPathfinder() {
/*  24 */     super.initPathfinder();
/*  25 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  26 */     this.goalSelector.a(1, new EntityIllagerWizard.b(this));
/*  27 */     this.goalSelector.a(4, new b());
/*  28 */     this.goalSelector.a(5, new a());
/*  29 */     this.goalSelector.a(6, new PathfinderGoalBowShoot<>(this, 0.5D, 20, 15.0F));
/*  30 */     this.goalSelector.a(8, new PathfinderGoalRandomStroll(this, 0.6D));
/*  31 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  32 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*  33 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[] { EntityRaider.class })).a(new Class[0]));
/*  34 */     this.targetSelector.a(2, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityHuman.class, true)).a(300));
/*  35 */     this.targetSelector.a(3, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityVillagerAbstract.class, false)).a(300));
/*  36 */     this.targetSelector.a(3, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityIronGolem.class, false)).a(300));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  40 */     return EntityMonster.eR().a(GenericAttributes.MOVEMENT_SPEED, 0.5D).a(GenericAttributes.FOLLOW_RANGE, 18.0D).a(GenericAttributes.MAX_HEALTH, 32.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  45 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.BOW));
/*  46 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  51 */     super.initDatawatcher();
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  56 */     super.movementTick();
/*  57 */     if (this.world.isClientSide && isInvisible()) {
/*  58 */       this.bo--;
/*  59 */       if (this.bo < 0) {
/*  60 */         this.bo = 0;
/*     */       }
/*     */       
/*  63 */       if (this.hurtTicks != 1 && this.ticksLived % 1200 != 0) {
/*  64 */         if (this.hurtTicks == this.hurtDuration - 1) {
/*  65 */           this.bo = 3;
/*     */           
/*  67 */           for (int i = 0; i < 4; i++) {
/*  68 */             this.bp[0][i] = this.bp[1][i];
/*  69 */             this.bp[1][i] = new Vec3D(0.0D, 0.0D, 0.0D);
/*     */           } 
/*     */         } 
/*     */       } else {
/*  73 */         this.bo = 3;
/*  74 */         float f = -6.0F;
/*  75 */         boolean flag = true;
/*     */         
/*     */         int j;
/*     */         
/*  79 */         for (j = 0; j < 4; j++) {
/*  80 */           this.bp[0][j] = this.bp[1][j];
/*  81 */           this.bp[1][j] = new Vec3D((-6.0F + this.random.nextInt(13)) * 0.5D, Math.max(0, this.random.nextInt(6) - 4), (-6.0F + this.random.nextInt(13)) * 0.5D);
/*     */         } 
/*     */         
/*  84 */         for (j = 0; j < 16; j++) {
/*  85 */           this.world.addParticle(Particles.CLOUD, d(0.5D), cE(), f(0.5D), 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */         
/*  88 */         this.world.a(locX(), locY(), locZ(), SoundEffects.ENTITY_ILLUSIONER_MIRROR_MOVE, getSoundCategory(), 1.0F, 1.0F, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEffect eL() {
/*  96 */     return SoundEffects.ENTITY_ILLUSIONER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean r(Entity entity) {
/* 101 */     return super.r(entity) ? true : ((entity instanceof EntityLiving && ((EntityLiving)entity).getMonsterType() == EnumMonsterType.ILLAGER) ? ((getScoreboardTeam() == null && entity.getScoreboardTeam() == null)) : false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 106 */     return SoundEffects.ENTITY_ILLUSIONER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 111 */     return SoundEffects.ENTITY_ILLUSIONER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 116 */     return SoundEffects.ENTITY_ILLUSIONER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundCastSpell() {
/* 121 */     return SoundEffects.ENTITY_ILLUSIONER_CAST_SPELL;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, boolean flag) {}
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 129 */     ItemStack itemstack = f(b(ProjectileHelper.a(this, Items.BOW)));
/* 130 */     EntityArrow entityarrow = ProjectileHelper.a(this, itemstack, f);
/* 131 */     double d0 = entityliving.locX() - locX();
/* 132 */     double d1 = entityliving.e(0.3333333333333333D) - entityarrow.locY();
/* 133 */     double d2 = entityliving.locZ() - locZ();
/* 134 */     double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*     */     
/* 136 */     entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - this.world.getDifficulty().a() * 4));
/*     */     
/* 138 */     EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(this, getItemInMainHand(), entityarrow.getOriginalItemStack(), entityarrow, entityliving.getRaisedHand(), 0.8F, true);
/* 139 */     if (event.isCancelled()) {
/* 140 */       event.getProjectile().remove();
/*     */       
/*     */       return;
/*     */     } 
/* 144 */     if (event.getProjectile() == entityarrow.getBukkitEntity()) {
/* 145 */       this.world.addEntity(entityarrow);
/*     */     }
/* 147 */     playSound(SoundEffects.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
/*     */   }
/*     */ 
/*     */   
/*     */   class a
/*     */     extends EntityIllagerWizard.PathfinderGoalCastSpell
/*     */   {
/*     */     private int e;
/*     */ 
/*     */     
/*     */     private a() {}
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 161 */       return !super.a() ? false : ((EntityIllagerIllusioner.this.getGoalTarget() == null) ? false : ((EntityIllagerIllusioner.this.getGoalTarget().getId() == this.e) ? false : EntityIllagerIllusioner.this.world.getDamageScaler(EntityIllagerIllusioner.this.getChunkCoordinates()).a(EnumDifficulty.NORMAL.ordinal())));
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 166 */       super.c();
/* 167 */       this.e = EntityIllagerIllusioner.this.getGoalTarget().getId();
/*     */     }
/*     */ 
/*     */     
/*     */     protected int g() {
/* 172 */       return 20;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int h() {
/* 177 */       return 180;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void j() {
/* 182 */       EntityIllagerIllusioner.this.getGoalTarget().addEffect(new MobEffect(MobEffects.BLINDNESS, 400), EntityPotionEffectEvent.Cause.ATTACK);
/*     */     }
/*     */ 
/*     */     
/*     */     protected SoundEffect k() {
/* 187 */       return SoundEffects.ENTITY_ILLUSIONER_PREPARE_BLINDNESS;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell getCastSpell() {
/* 192 */       return EntityIllagerWizard.Spell.BLINDNESS;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class b
/*     */     extends EntityIllagerWizard.PathfinderGoalCastSpell
/*     */   {
/*     */     private b() {}
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 204 */       return !super.a() ? false : (!EntityIllagerIllusioner.this.hasEffect(MobEffects.INVISIBILITY));
/*     */     }
/*     */ 
/*     */     
/*     */     protected int g() {
/* 209 */       return 20;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int h() {
/* 214 */       return 340;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void j() {
/* 219 */       EntityIllagerIllusioner.this.addEffect(new MobEffect(MobEffects.INVISIBILITY, 1200), EntityPotionEffectEvent.Cause.ILLUSION);
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     protected SoundEffect k() {
/* 225 */       return SoundEffects.ENTITY_ILLUSIONER_PREPARE_MIRROR;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell getCastSpell() {
/* 230 */       return EntityIllagerWizard.Spell.DISAPPEAR;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityIllagerIllusioner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */