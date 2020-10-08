/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Collection;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPiglinBrute
/*     */   extends EntityPiglinAbstract
/*     */ {
/*  41 */   protected static final ImmutableList<SensorType<? extends Sensor<? super EntityPiglinBrute>>> d = ImmutableList.of(SensorType.c, SensorType.d, SensorType.b, SensorType.f, SensorType.l);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   protected static final ImmutableList<MemoryModuleType<?>> bo = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.DOORS_TO_CLOSE, MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS, MemoryModuleType.NEARBY_ADULT_PIGLINS, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, (Object[])new MemoryModuleType[] { MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.INTERACTION_TARGET, MemoryModuleType.PATH, MemoryModuleType.ANGRY_AT, MemoryModuleType.NEAREST_VISIBLE_NEMSIS, MemoryModuleType.HOME });
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
/*     */   public EntityPiglinBrute(EntityTypes<? extends EntityPiglinBrute> var0, World var1) {
/*  72 */     super((EntityTypes)var0, var1);
/*  73 */     this.f = 20;
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eS() {
/*  77 */     return EntityMonster.eR()
/*  78 */       .a(GenericAttributes.MAX_HEALTH, 50.0D)
/*  79 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.3499999940395355D)
/*  80 */       .a(GenericAttributes.ATTACK_DAMAGE, 7.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/*  86 */     PiglinBruteAI.a(this);
/*  87 */     a(var1);
/*  88 */     return super.prepare(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler var0) {
/*  93 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController.b<EntityPiglinBrute> cJ() {
/*  98 */     return BehaviorController.a((Collection<? extends MemoryModuleType<?>>)bo, (Collection<? extends SensorType<? extends Sensor<? super EntityPiglinBrute>>>)d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BehaviorController<?> a(Dynamic<?> var0) {
/* 103 */     return PiglinBruteAI.a(this, cJ().a(var0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BehaviorController<EntityPiglinBrute> getBehaviorController() {
/* 109 */     return (BehaviorController)super.getBehaviorController();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m() {
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean i(ItemStack var0) {
/* 119 */     if (var0.getItem() == Items.GOLDEN_AXE) {
/* 120 */       return super.i(var0);
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/* 127 */     this.world.getMethodProfiler().enter("piglinBruteBrain");
/* 128 */     getBehaviorController().a((WorldServer)this.world, this);
/* 129 */     this.world.getMethodProfiler().exit();
/*     */     
/* 131 */     PiglinBruteAI.b(this);
/* 132 */     PiglinBruteAI.c(this);
/*     */     
/* 134 */     super.mobTick();
/*     */   }
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
/*     */   public boolean damageEntity(DamageSource var0, float var1) {
/* 148 */     boolean var2 = super.damageEntity(var0, var1);
/* 149 */     if (this.world.isClientSide) {
/* 150 */       return false;
/*     */     }
/* 152 */     if (var2 && var0.getEntity() instanceof EntityLiving) {
/* 153 */       PiglinBruteAI.a(this, (EntityLiving)var0.getEntity());
/*     */     }
/* 155 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 160 */     return SoundEffects.ENTITY_PIGLIN_BRUTE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 165 */     return SoundEffects.ENTITY_PIGLIN_BRUTE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 170 */     return SoundEffects.ENTITY_PIGLIN_BRUTE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition var0, IBlockData var1) {
/* 175 */     playSound(SoundEffects.ENTITY_PIGLIN_BRUTE_STEP, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void eT() {
/* 179 */     playSound(SoundEffects.ENTITY_PIGLIN_BRUTE_ANGRY, 1.0F, dG());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eP() {
/* 184 */     playSound(SoundEffects.ENTITY_PIGLIN_BRUTE_CONVERTED_TO_ZOMBIFIED, 1.0F, dG());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPiglinBrute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */