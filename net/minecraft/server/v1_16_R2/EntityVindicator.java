/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Map;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityVindicator extends EntityIllagerAbstract {
/*     */   private static final Predicate<EnumDifficulty> b;
/*     */   private boolean bo;
/*     */   
/*     */   static {
/*  11 */     b = (enumdifficulty -> 
/*  12 */       (enumdifficulty == EnumDifficulty.NORMAL || enumdifficulty == EnumDifficulty.HARD));
/*     */   }
/*  14 */   public boolean isJohnny() { return this.bo; } public void setJohnny(boolean johnny) { this.bo = johnny; }
/*     */   
/*     */   public EntityVindicator(EntityTypes<? extends EntityVindicator> entitytypes, World world) {
/*  17 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  22 */     super.initPathfinder();
/*  23 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  24 */     this.goalSelector.a(1, new a(this));
/*  25 */     this.goalSelector.a(2, new EntityIllagerAbstract.b(this, this));
/*  26 */     this.goalSelector.a(3, new EntityRaider.a(this, this, 10.0F));
/*  27 */     this.goalSelector.a(4, new c(this));
/*  28 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[] { EntityRaider.class })).a(new Class[0]));
/*  29 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  30 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillagerAbstract.class, true));
/*  31 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*  32 */     this.targetSelector.a(4, new b(this));
/*  33 */     this.goalSelector.a(8, new PathfinderGoalRandomStroll(this, 0.6D));
/*  34 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  35 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/*  40 */     if (!isNoAI() && PathfinderGoalUtil.a(this)) {
/*  41 */       boolean flag = ((WorldServer)this.world).c_(getChunkCoordinates());
/*     */       
/*  43 */       ((Navigation)getNavigation()).a(flag);
/*     */     } 
/*     */     
/*  46 */     super.mobTick();
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  50 */     return EntityMonster.eR().a(GenericAttributes.MOVEMENT_SPEED, 0.3499999940395355D).a(GenericAttributes.FOLLOW_RANGE, 12.0D).a(GenericAttributes.MAX_HEALTH, 24.0D).a(GenericAttributes.ATTACK_DAMAGE, 5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  55 */     super.saveData(nbttagcompound);
/*  56 */     if (this.bo) {
/*  57 */       nbttagcompound.setBoolean("Johnny", true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  64 */     super.loadData(nbttagcompound);
/*  65 */     if (nbttagcompound.hasKeyOfType("Johnny", 99)) {
/*  66 */       this.bo = nbttagcompound.getBoolean("Johnny");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEffect eL() {
/*  73 */     return SoundEffects.ENTITY_VINDICATOR_CELEBRATE;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  79 */     GroupDataEntity groupdataentity1 = super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */     
/*  81 */     ((Navigation)getNavigation()).a(true);
/*  82 */     a(difficultydamagescaler);
/*  83 */     b(difficultydamagescaler);
/*  84 */     return groupdataentity1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/*  89 */     if (fa() == null) {
/*  90 */       setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean r(Entity entity) {
/*  97 */     return super.r(entity) ? true : ((entity instanceof EntityLiving && ((EntityLiving)entity).getMonsterType() == EnumMonsterType.ILLAGER) ? ((getScoreboardTeam() == null && entity.getScoreboardTeam() == null)) : false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomName(@Nullable IChatBaseComponent ichatbasecomponent) {
/* 102 */     super.setCustomName(ichatbasecomponent);
/* 103 */     if (!this.bo && ichatbasecomponent != null && ichatbasecomponent.getString().equals("Johnny")) {
/* 104 */       this.bo = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 111 */     return SoundEffects.ENTITY_VINDICATOR_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 116 */     return SoundEffects.ENTITY_VINDICATOR_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 121 */     return SoundEffects.ENTITY_VINDICATOR_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, boolean flag) {
/* 126 */     ItemStack itemstack = new ItemStack(Items.IRON_AXE);
/* 127 */     Raid raid = fa();
/* 128 */     byte b0 = 1;
/*     */     
/* 130 */     if (i > raid.a(EnumDifficulty.NORMAL)) {
/* 131 */       b0 = 2;
/*     */     }
/*     */     
/* 134 */     boolean flag1 = (this.random.nextFloat() <= raid.w());
/*     */     
/* 136 */     if (flag1) {
/* 137 */       Map<Enchantment, Integer> map = Maps.newHashMap();
/*     */       
/* 139 */       map.put(Enchantments.DAMAGE_ALL, Integer.valueOf(b0));
/* 140 */       EnchantmentManager.a(map, itemstack);
/*     */     } 
/*     */     
/* 143 */     setSlot(EnumItemSlot.MAINHAND, itemstack);
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityLiving> {
/*     */     public b(EntityVindicator entityvindicator) {
/* 149 */       super(entityvindicator, EntityLiving.class, 0, true, true, EntityLiving::eh);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 154 */       return (((EntityVindicator)this.e).bo && super.a());
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 159 */       super.c();
/* 160 */       this.e.n(0);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends PathfinderGoalBreakDoor {
/*     */     public a(EntityInsentient entityinsentient) {
/* 167 */       super(entityinsentient, 6, EntityVindicator.b);
/* 168 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 173 */       EntityVindicator entityvindicator = (EntityVindicator)this.entity;
/*     */       
/* 175 */       return (entityvindicator.fb() && super.b());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 180 */       EntityVindicator entityvindicator = (EntityVindicator)this.entity;
/*     */       
/* 182 */       return (entityvindicator.fb() && entityvindicator.random.nextInt(10) == 0 && super.a());
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 187 */       super.c();
/* 188 */       this.entity.n(0);
/*     */     }
/*     */   }
/*     */   
/*     */   class c
/*     */     extends PathfinderGoalMeleeAttack {
/*     */     public c(EntityVindicator entityvindicator) {
/* 195 */       super(entityvindicator, 1.0D, false);
/*     */     }
/*     */ 
/*     */     
/*     */     protected double a(EntityLiving entityliving) {
/* 200 */       if (this.a.getVehicle() instanceof EntityRavager) {
/* 201 */         float f = this.a.getVehicle().getWidth() - 0.1F;
/*     */         
/* 203 */         return (f * 2.0F * f * 2.0F + entityliving.getWidth());
/*     */       } 
/* 205 */       return super.a(entityliving);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityVindicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */