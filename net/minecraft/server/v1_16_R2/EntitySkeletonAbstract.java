/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoField;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ 
/*     */ public abstract class EntitySkeletonAbstract extends EntityMonster implements IRangedEntity {
/*   9 */   private final PathfinderGoalBowShoot<EntitySkeletonAbstract> b = new PathfinderGoalBowShoot<>(this, 1.0D, 20, 15.0F);
/*  10 */   private final PathfinderGoalMeleeAttack c = new PathfinderGoalMeleeAttack(this, 1.2D, false)
/*     */     {
/*     */       public void d() {
/*  13 */         super.d();
/*  14 */         EntitySkeletonAbstract.this.setAggressive(false);
/*     */       }
/*     */ 
/*     */       
/*     */       public void c() {
/*  19 */         super.c();
/*  20 */         EntitySkeletonAbstract.this.setAggressive(true);
/*     */       }
/*     */     };
/*     */   
/*     */   protected EntitySkeletonAbstract(EntityTypes<? extends EntitySkeletonAbstract> entitytypes, World world) {
/*  25 */     super((EntityTypes)entitytypes, world);
/*  26 */     eL();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  31 */     this.goalSelector.a(2, new PathfinderGoalRestrictSun(this));
/*  32 */     this.goalSelector.a(3, new PathfinderGoalFleeSun(this, 1.0D));
/*  33 */     this.goalSelector.a(3, new PathfinderGoalAvoidTarget<>(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
/*  34 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  35 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  36 */     this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
/*  37 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, new Class[0]));
/*  38 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  39 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*  40 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityTurtle.class, 10, true, false, EntityTurtle.bo));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  44 */     return EntityMonster.eR().a(GenericAttributes.MOVEMENT_SPEED, 0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/*  49 */     playSound(eK(), 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   abstract SoundEffect eK();
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/*  56 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  61 */     boolean flag = eG();
/*     */     
/*  63 */     if (flag) {
/*  64 */       ItemStack itemstack = getEquipment(EnumItemSlot.HEAD);
/*     */       
/*  66 */       if (!itemstack.isEmpty()) {
/*  67 */         if (itemstack.e()) {
/*  68 */           itemstack.setDamage(itemstack.getDamage() + this.random.nextInt(2));
/*  69 */           if (itemstack.getDamage() >= itemstack.h()) {
/*  70 */             broadcastItemBreak(EnumItemSlot.HEAD);
/*  71 */             setSlot(EnumItemSlot.HEAD, ItemStack.b);
/*     */           } 
/*     */         } 
/*     */         
/*  75 */         flag = false;
/*     */       } 
/*     */       
/*  78 */       if (flag) {
/*  79 */         setOnFire(8);
/*     */       }
/*     */     } 
/*     */     
/*  83 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void passengerTick() {
/*  88 */     super.passengerTick();
/*  89 */     if (getVehicle() instanceof EntityCreature) {
/*  90 */       EntityCreature entitycreature = (EntityCreature)getVehicle();
/*     */       
/*  92 */       this.aA = entitycreature.aA;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/*  99 */     super.a(difficultydamagescaler);
/* 100 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.BOW));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 106 */     groupdataentity = super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/* 107 */     a(difficultydamagescaler);
/* 108 */     b(difficultydamagescaler);
/* 109 */     eL();
/* 110 */     setCanPickupLoot((this.random.nextFloat() < 0.55F * difficultydamagescaler.d()));
/* 111 */     if (getEquipment(EnumItemSlot.HEAD).isEmpty()) {
/* 112 */       LocalDate localdate = LocalDate.now();
/* 113 */       int i = localdate.get(ChronoField.DAY_OF_MONTH);
/* 114 */       int j = localdate.get(ChronoField.MONTH_OF_YEAR);
/*     */       
/* 116 */       if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
/* 117 */         setSlot(EnumItemSlot.HEAD, new ItemStack((this.random.nextFloat() < 0.1F) ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
/* 118 */         this.dropChanceArmor[EnumItemSlot.HEAD.b()] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     return groupdataentity;
/*     */   }
/*     */   
/*     */   public void eL() {
/* 126 */     if (this.world != null && !this.world.isClientSide) {
/* 127 */       this.goalSelector.a(this.c);
/* 128 */       this.goalSelector.a(this.b);
/* 129 */       ItemStack itemstack = b(ProjectileHelper.a(this, Items.BOW));
/*     */       
/* 131 */       if (itemstack.getItem() == Items.BOW) {
/* 132 */         byte b0 = 20;
/*     */         
/* 134 */         if (this.world.getDifficulty() != EnumDifficulty.HARD) {
/* 135 */           b0 = 40;
/*     */         }
/*     */         
/* 138 */         this.b.a(b0);
/* 139 */         this.goalSelector.a(4, this.b);
/*     */       } else {
/* 141 */         this.goalSelector.a(4, this.c);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 149 */     ItemStack itemstack = f(b(ProjectileHelper.a(this, Items.BOW)));
/* 150 */     EntityArrow entityarrow = b(itemstack, f);
/* 151 */     double d0 = entityliving.locX() - locX();
/* 152 */     double d1 = entityliving.e(0.3333333333333333D) - entityarrow.locY();
/* 153 */     double d2 = entityliving.locZ() - locZ();
/* 154 */     double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*     */     
/* 156 */     entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - this.world.getDifficulty().a() * 4));
/*     */     
/* 158 */     EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(this, getItemInMainHand(), entityarrow.getOriginalItemStack(), entityarrow, EnumHand.MAIN_HAND, 0.8F, true);
/* 159 */     if (event.isCancelled()) {
/* 160 */       event.getProjectile().remove();
/*     */       
/*     */       return;
/*     */     } 
/* 164 */     if (event.getProjectile() == entityarrow.getBukkitEntity()) {
/* 165 */       this.world.addEntity(entityarrow);
/*     */     }
/*     */     
/* 168 */     playSound(SoundEffects.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityArrow b(ItemStack itemstack, float f) {
/* 173 */     return ProjectileHelper.a(this, itemstack, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemProjectileWeapon itemprojectileweapon) {
/* 178 */     return (itemprojectileweapon == Items.BOW);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 183 */     super.loadData(nbttagcompound);
/* 184 */     eL();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSlot(EnumItemSlot enumitemslot, ItemStack itemstack) {
/* 189 */     super.setSlot(enumitemslot, itemstack);
/* 190 */     if (!this.world.isClientSide) {
/* 191 */       eL();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 198 */     return 1.74F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double ba() {
/* 203 */     return -0.6D;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySkeletonAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */