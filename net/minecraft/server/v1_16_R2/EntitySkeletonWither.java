/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ 
/*     */ public class EntitySkeletonWither extends EntitySkeletonAbstract {
/*     */   public EntitySkeletonWither(EntityTypes<? extends EntitySkeletonWither> entitytypes, World world) {
/*   8 */     super((EntityTypes)entitytypes, world);
/*   9 */     a(PathType.LAVA, 8.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  14 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityPiglinAbstract.class, true));
/*  15 */     super.initPathfinder();
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  20 */     return SoundEffects.ENTITY_WITHER_SKELETON_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  25 */     return SoundEffects.ENTITY_WITHER_SKELETON_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  30 */     return SoundEffects.ENTITY_WITHER_SKELETON_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   SoundEffect eK() {
/*  35 */     return SoundEffects.ENTITY_WITHER_SKELETON_STEP;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
/*  40 */     super.dropDeathLoot(damagesource, i, flag);
/*  41 */     Entity entity = damagesource.getEntity();
/*     */     
/*  43 */     if (entity instanceof EntityCreeper) {
/*  44 */       EntityCreeper entitycreeper = (EntityCreeper)entity;
/*     */       
/*  46 */       if (entitycreeper.canCauseHeadDrop()) {
/*  47 */         entitycreeper.setCausedHeadDrop();
/*  48 */         a(Items.WITHER_SKELETON_SKULL);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/*  56 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(DifficultyDamageScaler difficultydamagescaler) {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  65 */     GroupDataEntity groupdataentity1 = super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */     
/*  67 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(4.0D);
/*  68 */     eL();
/*  69 */     return groupdataentity1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  74 */     return 2.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/*  79 */     if (!super.attackEntity(entity)) {
/*  80 */       return false;
/*     */     }
/*  82 */     if (entity instanceof EntityLiving) {
/*  83 */       ((EntityLiving)entity).addEffect(new MobEffect(MobEffects.WITHER, 200), EntityPotionEffectEvent.Cause.ATTACK);
/*     */     }
/*     */     
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EntityArrow b(ItemStack itemstack, float f) {
/*  92 */     EntityArrow entityarrow = super.b(itemstack, f);
/*     */     
/*  94 */     entityarrow.setOnFire(100);
/*  95 */     return entityarrow;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean d(MobEffect mobeffect) {
/* 100 */     return (mobeffect.getMobEffect() == MobEffects.WITHER) ? false : super.d(mobeffect);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySkeletonWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */