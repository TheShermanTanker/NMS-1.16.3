/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityParrot extends EntityPerchable implements EntityBird {
/*  15 */   private static final DataWatcherObject<Integer> bu = DataWatcher.a((Class)EntityParrot.class, DataWatcherRegistry.b);
/*  16 */   private static final Predicate<EntityInsentient> bv = new Predicate<EntityInsentient>() {
/*     */       public boolean test(@Nullable EntityInsentient entityinsentient) {
/*  18 */         return (entityinsentient != null && EntityParrot.by.containsKey(entityinsentient.getEntityType()));
/*     */       }
/*     */     };
/*  21 */   private static final Item bw = Items.COOKIE; private static final Map<EntityTypes<?>, SoundEffect> by;
/*  22 */   private static final Set<Item> bx = Sets.newHashSet((Object[])new Item[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS }); public float bq; static {
/*  23 */     by = SystemUtils.<Map<EntityTypes<?>, SoundEffect>>a(Maps.newHashMap(), hashmap -> {
/*     */           hashmap.put(EntityTypes.BLAZE, SoundEffects.ENTITY_PARROT_IMITATE_BLAZE);
/*     */           hashmap.put(EntityTypes.CAVE_SPIDER, SoundEffects.ENTITY_PARROT_IMITATE_SPIDER);
/*     */           hashmap.put(EntityTypes.CREEPER, SoundEffects.ENTITY_PARROT_IMITATE_CREEPER);
/*     */           hashmap.put(EntityTypes.DROWNED, SoundEffects.ENTITY_PARROT_IMITATE_DROWNED);
/*     */           hashmap.put(EntityTypes.ELDER_GUARDIAN, SoundEffects.ENTITY_PARROT_IMITATE_ELDER_GUARDIAN);
/*     */           hashmap.put(EntityTypes.ENDER_DRAGON, SoundEffects.ENTITY_PARROT_IMITATE_ENDER_DRAGON);
/*     */           hashmap.put(EntityTypes.ENDERMITE, SoundEffects.ENTITY_PARROT_IMITATE_ENDERMITE);
/*     */           hashmap.put(EntityTypes.EVOKER, SoundEffects.ENTITY_PARROT_IMITATE_EVOKER);
/*     */           hashmap.put(EntityTypes.GHAST, SoundEffects.ENTITY_PARROT_IMITATE_GHAST);
/*     */           hashmap.put(EntityTypes.GUARDIAN, SoundEffects.ENTITY_PARROT_IMITATE_GUARDIAN);
/*     */           hashmap.put(EntityTypes.HOGLIN, SoundEffects.ENTITY_PARROT_IMITATE_HOGLIN);
/*     */           hashmap.put(EntityTypes.HUSK, SoundEffects.ENTITY_PARROT_IMITATE_HUSK);
/*     */           hashmap.put(EntityTypes.ILLUSIONER, SoundEffects.ENTITY_PARROT_IMITATE_ILLUSIONER);
/*     */           hashmap.put(EntityTypes.MAGMA_CUBE, SoundEffects.ENTITY_PARROT_IMITATE_MAGMA_CUBE);
/*     */           hashmap.put(EntityTypes.PHANTOM, SoundEffects.ENTITY_PARROT_IMITATE_PHANTOM);
/*     */           hashmap.put(EntityTypes.PIGLIN, SoundEffects.ENTITY_PARROT_IMITATE_PIGLIN);
/*     */           hashmap.put(EntityTypes.PIGLIN_BRUTE, SoundEffects.ENTITY_PARROT_IMITATE_PIGLIN_BRUTE);
/*     */           hashmap.put(EntityTypes.PILLAGER, SoundEffects.ENTITY_PARROT_IMITATE_PILLAGER);
/*     */           hashmap.put(EntityTypes.RAVAGER, SoundEffects.ENTITY_PARROT_IMITATE_RAVAGER);
/*     */           hashmap.put(EntityTypes.SHULKER, SoundEffects.ENTITY_PARROT_IMITATE_SHULKER);
/*     */           hashmap.put(EntityTypes.SILVERFISH, SoundEffects.ENTITY_PARROT_IMITATE_SILVERFISH);
/*     */           hashmap.put(EntityTypes.SKELETON, SoundEffects.ENTITY_PARROT_IMITATE_SKELETON);
/*     */           hashmap.put(EntityTypes.SLIME, SoundEffects.ENTITY_PARROT_IMITATE_SLIME);
/*     */           hashmap.put(EntityTypes.SPIDER, SoundEffects.ENTITY_PARROT_IMITATE_SPIDER);
/*     */           hashmap.put(EntityTypes.STRAY, SoundEffects.ENTITY_PARROT_IMITATE_STRAY);
/*     */           hashmap.put(EntityTypes.VEX, SoundEffects.ENTITY_PARROT_IMITATE_VEX);
/*     */           hashmap.put(EntityTypes.VINDICATOR, SoundEffects.ENTITY_PARROT_IMITATE_VINDICATOR);
/*     */           hashmap.put(EntityTypes.WITCH, SoundEffects.ENTITY_PARROT_IMITATE_WITCH);
/*     */           hashmap.put(EntityTypes.WITHER, SoundEffects.ENTITY_PARROT_IMITATE_WITHER);
/*     */           hashmap.put(EntityTypes.WITHER_SKELETON, SoundEffects.ENTITY_PARROT_IMITATE_WITHER_SKELETON);
/*     */           hashmap.put(EntityTypes.ZOGLIN, SoundEffects.ENTITY_PARROT_IMITATE_ZOGLIN);
/*     */           hashmap.put(EntityTypes.ZOMBIE, SoundEffects.ENTITY_PARROT_IMITATE_ZOMBIE);
/*     */           hashmap.put(EntityTypes.ZOMBIE_VILLAGER, SoundEffects.ENTITY_PARROT_IMITATE_ZOMBIE_VILLAGER);
/*     */         });
/*     */   }
/*     */   public float br;
/*     */   public float bs;
/*     */   public float bt;
/*  62 */   private float bz = 1.0F;
/*     */   private boolean bA;
/*     */   private BlockPosition bB;
/*     */   
/*     */   public EntityParrot(EntityTypes<? extends EntityParrot> entitytypes, World world) {
/*  67 */     super((EntityTypes)entitytypes, world);
/*  68 */     this.moveController = new ControllerMoveFlying(this, 10, false);
/*  69 */     a(PathType.DANGER_FIRE, -1.0F);
/*  70 */     a(PathType.DAMAGE_FIRE, -1.0F);
/*  71 */     a(PathType.COCOA, -1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  77 */     setVariant(this.random.nextInt(5));
/*  78 */     if (groupdataentity == null) {
/*  79 */       groupdataentity = new EntityAgeable.a(false);
/*     */     }
/*     */     
/*  82 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  92 */     this.goalSelector.a(0, new PathfinderGoalPanic(this, 1.25D));
/*  93 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  94 */     this.goalSelector.a(1, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  95 */     this.goalSelector.a(2, new PathfinderGoalSit(this));
/*  96 */     this.goalSelector.a(2, new PathfinderGoalFollowOwner(this, 1.0D, 5.0F, 1.0F, true));
/*  97 */     this.goalSelector.a(2, new PathfinderGoalRandomFly(this, 1.0D));
/*  98 */     this.goalSelector.a(3, new PathfinderGoalPerch(this));
/*  99 */     this.goalSelector.a(3, new PathfinderGoalFollowEntity(this, 1.0D, 3.0F, 7.0F));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eU() {
/* 103 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 6.0D).a(GenericAttributes.FLYING_SPEED, 0.4000000059604645D).a(GenericAttributes.MOVEMENT_SPEED, 0.20000000298023224D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World world) {
/* 108 */     NavigationFlying navigationflying = new NavigationFlying(this, world);
/*     */     
/* 110 */     navigationflying.a(false);
/* 111 */     navigationflying.d(true);
/* 112 */     navigationflying.b(true);
/* 113 */     return navigationflying;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 118 */     return entitysize.height * 0.6F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 123 */     if (this.bB == null || !this.bB.a(getPositionVector(), 3.46D) || !this.world.getType(this.bB).a(Blocks.JUKEBOX)) {
/* 124 */       this.bA = false;
/* 125 */       this.bB = null;
/*     */     } 
/*     */     
/* 128 */     if (this.world.random.nextInt(400) == 0) {
/* 129 */       a(this.world, this);
/*     */     }
/*     */     
/* 132 */     super.movementTick();
/* 133 */     eZ();
/*     */   }
/*     */   
/*     */   private void eZ() {
/* 137 */     this.bt = this.bq;
/* 138 */     this.bs = this.br;
/* 139 */     this.br = (float)(this.br + ((!this.onGround && !isPassenger()) ? 4 : -1) * 0.3D);
/* 140 */     this.br = MathHelper.a(this.br, 0.0F, 1.0F);
/* 141 */     if (!this.onGround && this.bz < 1.0F) {
/* 142 */       this.bz = 1.0F;
/*     */     }
/*     */     
/* 145 */     this.bz = (float)(this.bz * 0.9D);
/* 146 */     Vec3D vec3d = getMot();
/*     */     
/* 148 */     if (!this.onGround && vec3d.y < 0.0D) {
/* 149 */       setMot(vec3d.d(1.0D, 0.6D, 1.0D));
/*     */     }
/*     */     
/* 152 */     this.bq += this.bz * 2.0F;
/*     */   }
/*     */   
/*     */   public static boolean a(World world, Entity entity) {
/* 156 */     if (entity.isAlive() && !entity.isSilent() && world.random.nextInt(2) == 0) {
/* 157 */       List<EntityInsentient> list = world.a(EntityInsentient.class, entity.getBoundingBox().g(20.0D), bv);
/*     */       
/* 159 */       if (!list.isEmpty()) {
/* 160 */         EntityInsentient entityinsentient = list.get(world.random.nextInt(list.size()));
/*     */         
/* 162 */         if (!entityinsentient.isSilent()) {
/* 163 */           SoundEffect soundeffect = b(entityinsentient.getEntityType());
/*     */           
/* 165 */           world.playSound((EntityHuman)null, entity.locX(), entity.locY(), entity.locZ(), soundeffect, entity.getSoundCategory(), 0.7F, a(world.random));
/* 166 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 170 */       return false;
/*     */     } 
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 178 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 180 */     if (!isTamed() && bx.contains(itemstack.getItem())) {
/* 181 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 182 */         itemstack.subtract(1);
/*     */       }
/*     */       
/* 185 */       if (!isSilent()) {
/* 186 */         this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_PARROT_EAT, getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */       }
/*     */       
/* 189 */       if (!this.world.isClientSide) {
/* 190 */         if (this.random.nextInt(10) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
/* 191 */           tame(entityhuman);
/* 192 */           this.world.broadcastEntityEffect(this, (byte)7);
/*     */         } else {
/* 194 */           this.world.broadcastEntityEffect(this, (byte)6);
/*     */         } 
/*     */       }
/*     */       
/* 198 */       return EnumInteractionResult.a(this.world.isClientSide);
/* 199 */     }  if (itemstack.getItem() == bw) {
/* 200 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 201 */         itemstack.subtract(1);
/*     */       }
/*     */       
/* 204 */       addEffect(new MobEffect(MobEffects.POISON, 900), EntityPotionEffectEvent.Cause.FOOD);
/* 205 */       if (entityhuman.isCreative() || !isInvulnerable()) {
/* 206 */         damageEntity(DamageSource.playerAttack(entityhuman), Float.MAX_VALUE);
/*     */       }
/*     */       
/* 209 */       return EnumInteractionResult.a(this.world.isClientSide);
/* 210 */     }  if (!fa() && isTamed() && i(entityhuman)) {
/* 211 */       if (!this.world.isClientSide) {
/* 212 */         setWillSit(!isWillSit());
/*     */       }
/*     */       
/* 215 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/* 217 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 223 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<EntityParrot> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 227 */     IBlockData iblockdata = generatoraccess.getType(blockposition.down());
/*     */     
/* 229 */     return ((iblockdata.a(TagsBlock.LEAVES) || iblockdata.a(Blocks.GRASS_BLOCK) || iblockdata.a(TagsBlock.LOGS) || iblockdata.a(Blocks.AIR)) && generatoraccess.getLightLevel(blockposition, 0) > 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/* 234 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {}
/*     */ 
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 248 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 253 */     return entity.damageEntity(DamageSource.mobAttack(this), 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SoundEffect getSoundAmbient() {
/* 259 */     return a(this.world, this.world.random);
/*     */   }
/*     */   
/*     */   public static SoundEffect a(World world, Random random) {
/* 263 */     if (world.getDifficulty() != EnumDifficulty.PEACEFUL && random.nextInt(1000) == 0) {
/* 264 */       List<EntityTypes<?>> list = Lists.newArrayList(by.keySet());
/*     */       
/* 266 */       return b(list.get(random.nextInt(list.size())));
/*     */     } 
/* 268 */     return SoundEffects.ENTITY_PARROT_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   private static SoundEffect b(EntityTypes<?> entitytypes) {
/* 273 */     return by.getOrDefault(entitytypes, SoundEffects.ENTITY_PARROT_AMBIENT);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 278 */     return SoundEffects.ENTITY_PARROT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 283 */     return SoundEffects.ENTITY_PARROT_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 288 */     playSound(SoundEffects.ENTITY_PARROT_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float e(float f) {
/* 293 */     playSound(SoundEffects.ENTITY_PARROT_FLY, 0.15F, 1.0F);
/* 294 */     return f + this.br / 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean ay() {
/* 299 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float dG() {
/* 304 */     return a(this.random);
/*     */   }
/*     */   
/*     */   public static float a(Random random) {
/* 308 */     return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/* 313 */     return SoundCategory.NEUTRAL;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 318 */     return super.isCollidable();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void C(Entity entity) {
/* 323 */     if (!(entity instanceof EntityHuman)) {
/* 324 */       super.C(entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 330 */     if (isInvulnerable(damagesource)) {
/* 331 */       return false;
/*     */     }
/*     */     
/* 334 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVariant() {
/* 339 */     return MathHelper.clamp(((Integer)this.datawatcher.<Integer>get(bu)).intValue(), 0, 4);
/*     */   }
/*     */   
/*     */   public void setVariant(int i) {
/* 343 */     this.datawatcher.set(bu, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 348 */     super.initDatawatcher();
/* 349 */     this.datawatcher.register(bu, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 354 */     super.saveData(nbttagcompound);
/* 355 */     nbttagcompound.setInt("Variant", getVariant());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 360 */     super.loadData(nbttagcompound);
/* 361 */     setVariant(nbttagcompound.getInt("Variant"));
/*     */   }
/*     */   
/*     */   public boolean fa() {
/* 365 */     return !this.onGround;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityParrot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */