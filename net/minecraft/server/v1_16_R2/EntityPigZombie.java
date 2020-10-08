/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ import org.bukkit.event.entity.PigZombieAngerEvent;
/*     */ 
/*     */ public class EntityPigZombie extends EntityZombie implements IEntityAngerable {
/*   9 */   private static final UUID b = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
/*  10 */   private static final AttributeModifier c = new AttributeModifier(b, "Attacking speed boost", 0.05D, AttributeModifier.Operation.ADDITION);
/*  11 */   private static final IntRange d = TimeRange.a(0, 1);
/*     */   private int bo;
/*  13 */   private static final IntRange bp = TimeRange.a(20, 39);
/*     */   private int bq;
/*     */   private UUID br;
/*  16 */   private static final IntRange bs = TimeRange.a(4, 6);
/*     */   private int bt;
/*     */   
/*     */   public EntityPigZombie(EntityTypes<? extends EntityPigZombie> entitytypes, World world) {
/*  20 */     super((EntityTypes)entitytypes, world);
/*  21 */     a(PathType.LAVA, 8.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAngerTarget(@Nullable UUID uuid) {
/*  26 */     this.br = uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public double ba() {
/*  31 */     return isBaby() ? -0.05D : -0.45D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m() {
/*  36 */     this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
/*  37 */     this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  38 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
/*  39 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, 10, true, false, this::a_));
/*  40 */     this.targetSelector.a(3, new PathfinderGoalUniversalAngerReset<>(this, true));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eW() {
/*  44 */     return EntityZombie.eS().a(GenericAttributes.SPAWN_REINFORCEMENTS, 0.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.23000000417232513D).a(GenericAttributes.ATTACK_DAMAGE, 5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean eN() {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/*  54 */     AttributeModifiable attributemodifiable = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*     */     
/*  56 */     if (isAngry()) {
/*  57 */       if (!isBaby() && !attributemodifiable.a(c)) {
/*  58 */         attributemodifiable.b(c);
/*     */       }
/*     */       
/*  61 */       eX();
/*  62 */     } else if (attributemodifiable.a(c)) {
/*  63 */       attributemodifiable.removeModifier(c);
/*     */     } 
/*     */     
/*  66 */     a((WorldServer)this.world, true);
/*  67 */     if (getGoalTarget() != null) {
/*  68 */       eY();
/*     */     }
/*     */     
/*  71 */     if (isAngry()) {
/*  72 */       this.lastDamageByPlayerTime = this.ticksLived;
/*     */     }
/*     */     
/*  75 */     super.mobTick();
/*     */   }
/*     */   
/*     */   private void eX() {
/*  79 */     if (this.bo > 0) {
/*  80 */       this.bo--;
/*  81 */       if (this.bo == 0) {
/*  82 */         fa();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void eY() {
/*  89 */     if (this.bt > 0) {
/*  90 */       this.bt--;
/*     */     } else {
/*  92 */       if (getEntitySenses().a(getGoalTarget())) {
/*  93 */         eZ();
/*     */       }
/*     */       
/*  96 */       this.bt = bs.a(this.random);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void eZ() {
/* 101 */     double d0 = b(GenericAttributes.FOLLOW_RANGE);
/* 102 */     AxisAlignedBB axisalignedbb = AxisAlignedBB.a(getPositionVector()).grow(d0, 10.0D, d0);
/*     */     
/* 104 */     this.world.<EntityPigZombie>b(EntityPigZombie.class, axisalignedbb).stream().filter(entitypigzombie -> (entitypigzombie != this))
/*     */       
/* 106 */       .filter(entitypigzombie -> (entitypigzombie.getGoalTarget() == null))
/*     */       
/* 108 */       .filter(entitypigzombie -> !entitypigzombie.r(getGoalTarget()))
/*     */       
/* 110 */       .forEach(entitypigzombie -> entitypigzombie.setGoalTarget(getGoalTarget(), EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void fa() {
/* 116 */     playSound(SoundEffects.ENTITY_ZOMBIFIED_PIGLIN_ANGRY, getSoundVolume() * 2.0F, dG() * 1.8F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setGoalTarget(@Nullable EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
/* 121 */     if (getGoalTarget() == null && entityliving != null) {
/* 122 */       this.bo = d.a(this.random);
/* 123 */       this.bt = bs.a(this.random);
/*     */     } 
/*     */     
/* 126 */     if (entityliving instanceof EntityHuman) {
/* 127 */       e((EntityHuman)entityliving);
/*     */     }
/*     */     
/* 130 */     return super.setGoalTarget(entityliving, reason, fireEvent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void anger() {
/* 136 */     Entity entity = ((WorldServer)this.world).getEntity(getAngerTarget());
/* 137 */     PigZombieAngerEvent event = new PigZombieAngerEvent((PigZombie)getBukkitEntity(), (entity == null) ? null : (Entity)entity.getBukkitEntity(), bp.a(this.random));
/* 138 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/* 139 */     if (event.isCancelled()) {
/* 140 */       setAngerTarget((UUID)null);
/*     */       return;
/*     */     } 
/* 143 */     setAnger(event.getNewAnger());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(EntityTypes<EntityPigZombie> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 148 */     return (generatoraccess.getDifficulty() != EnumDifficulty.PEACEFUL && generatoraccess.getType(blockposition.down()).getBlock() != Blocks.NETHER_WART_BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader iworldreader) {
/* 153 */     return (iworldreader.j(this) && !iworldreader.containsLiquid(getBoundingBox()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 158 */     super.saveData(nbttagcompound);
/* 159 */     c(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 164 */     super.loadData(nbttagcompound);
/* 165 */     a((WorldServer)this.world, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnger(int i) {
/* 170 */     this.bq = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnger() {
/* 175 */     return this.bq;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 180 */     return isInvulnerable(damagesource) ? false : super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 185 */     return isAngry() ? SoundEffects.ENTITY_ZOMBIFIED_PIGLIN_ANGRY : SoundEffects.ENTITY_ZOMBIFIED_PIGLIN_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 190 */     return SoundEffects.ENTITY_ZOMBIFIED_PIGLIN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 195 */     return SoundEffects.ENTITY_ZOMBIFIED_PIGLIN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/* 200 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack eM() {
/* 205 */     return ItemStack.b;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eV() {
/* 210 */     getAttributeInstance(GenericAttributes.SPAWN_REINFORCEMENTS).setValue(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getAngerTarget() {
/* 215 */     return this.br;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean f(EntityHuman entityhuman) {
/* 220 */     return a_(entityhuman);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPigZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */