/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityIronGolem extends EntityGolem implements IEntityAngerable {
/*  13 */   protected static final DataWatcherObject<Byte> b = DataWatcher.a((Class)EntityIronGolem.class, DataWatcherRegistry.a);
/*     */   private int c;
/*     */   private int d;
/*  16 */   private static final IntRange bo = TimeRange.a(20, 39);
/*     */   private int bp;
/*     */   private UUID bq;
/*     */   
/*     */   public EntityIronGolem(EntityTypes<? extends EntityIronGolem> entitytypes, World world) {
/*  21 */     super((EntityTypes)entitytypes, world);
/*  22 */     this.G = 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  27 */     this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, true));
/*  28 */     this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.9D, 32.0F));
/*  29 */     this.goalSelector.a(2, new PathfinderGoalStrollVillage(this, 0.6D, false));
/*  30 */     this.goalSelector.a(4, new PathfinderGoalStrollVillageGolem(this, 0.6D));
/*  31 */     this.goalSelector.a(5, new PathfinderGoalOfferFlower(this));
/*  32 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  33 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  34 */     this.targetSelector.a(1, new PathfinderGoalDefendVillage(this));
/*  35 */     this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
/*  36 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, 10, true, false, this::a_));
/*  37 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityInsentient.class, 5, false, false, entityliving -> 
/*  38 */           (entityliving instanceof IMonster && !(entityliving instanceof EntityCreeper))));
/*     */     
/*  40 */     this.targetSelector.a(4, new PathfinderGoalUniversalAngerReset<>(this, false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  45 */     super.initDatawatcher();
/*  46 */     this.datawatcher.register(b, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  50 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 100.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.25D).a(GenericAttributes.KNOCKBACK_RESISTANCE, 1.0D).a(GenericAttributes.ATTACK_DAMAGE, 15.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int l(int i) {
/*  55 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void C(Entity entity) {
/*  60 */     if (entity instanceof IMonster && !(entity instanceof EntityCreeper) && getRandom().nextInt(20) == 0) {
/*  61 */       setGoalTarget((EntityLiving)entity, EntityTargetEvent.TargetReason.COLLISION, true);
/*     */     }
/*     */     
/*  64 */     super.C(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  69 */     super.movementTick();
/*  70 */     if (this.c > 0) {
/*  71 */       this.c--;
/*     */     }
/*     */     
/*  74 */     if (this.d > 0) {
/*  75 */       this.d--;
/*     */     }
/*     */     
/*  78 */     if (c(getMot()) > 2.500000277905201E-7D && this.random.nextInt(5) == 0) {
/*  79 */       int i = MathHelper.floor(locX());
/*  80 */       int j = MathHelper.floor(locY() - 0.20000000298023224D);
/*  81 */       int k = MathHelper.floor(locZ());
/*  82 */       IBlockData iblockdata = this.world.getType(new BlockPosition(i, j, k));
/*     */       
/*  84 */       if (!iblockdata.isAir()) {
/*  85 */         this.world.addParticle(new ParticleParamBlock(Particles.BLOCK, iblockdata), locX() + (this.random.nextFloat() - 0.5D) * getWidth(), locY() + 0.1D, locZ() + (this.random.nextFloat() - 0.5D) * getWidth(), 4.0D * (this.random.nextFloat() - 0.5D), 0.5D, (this.random.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */     } 
/*     */     
/*  89 */     if (!this.world.isClientSide) {
/*  90 */       a((WorldServer)this.world, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityTypes<?> entitytypes) {
/*  97 */     return (isPlayerCreated() && entitytypes == EntityTypes.PLAYER) ? false : ((entitytypes == EntityTypes.CREEPER) ? false : super.a(entitytypes));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 102 */     super.saveData(nbttagcompound);
/* 103 */     nbttagcompound.setBoolean("PlayerCreated", isPlayerCreated());
/* 104 */     c(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 109 */     super.loadData(nbttagcompound);
/* 110 */     setPlayerCreated(nbttagcompound.getBoolean("PlayerCreated"));
/* 111 */     a((WorldServer)this.world, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void anger() {
/* 116 */     setAnger(bo.a(this.random));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnger(int i) {
/* 121 */     this.bp = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnger() {
/* 126 */     return this.bp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAngerTarget(@Nullable UUID uuid) {
/* 131 */     this.bq = uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getAngerTarget() {
/* 136 */     return this.bq;
/*     */   }
/*     */   
/*     */   private float eO() {
/* 140 */     return (float)b(GenericAttributes.ATTACK_DAMAGE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntity(Entity entity) {
/* 145 */     this.c = 10;
/* 146 */     this.world.broadcastEntityEffect(this, (byte)4);
/* 147 */     float f = eO();
/* 148 */     float f1 = ((int)f > 0) ? (f / 2.0F + this.random.nextInt((int)f)) : f;
/* 149 */     boolean flag = entity.damageEntity(DamageSource.mobAttack(this), f1);
/*     */     
/* 151 */     if (flag) {
/* 152 */       entity.setMot(entity.getMot().add(0.0D, 0.4000000059604645D, 0.0D));
/* 153 */       a(this, entity);
/*     */     } 
/*     */     
/* 156 */     playSound(SoundEffects.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
/* 157 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 162 */     CrackLevel entityirongolem_cracklevel = eK();
/* 163 */     boolean flag = super.damageEntity(damagesource, f);
/*     */     
/* 165 */     if (flag && eK() != entityirongolem_cracklevel) {
/* 166 */       playSound(SoundEffects.ENTITY_IRON_GOLEM_DAMAGE, 1.0F, 1.0F);
/*     */     }
/*     */     
/* 169 */     return flag;
/*     */   }
/*     */   
/*     */   public CrackLevel eK() {
/* 173 */     return CrackLevel.a(getHealth() / getMaxHealth());
/*     */   }
/*     */   
/*     */   public void t(boolean flag) {
/* 177 */     if (flag) {
/* 178 */       this.d = 400;
/* 179 */       this.world.broadcastEntityEffect(this, (byte)11);
/*     */     } else {
/* 181 */       this.d = 0;
/* 182 */       this.world.broadcastEntityEffect(this, (byte)34);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 189 */     return SoundEffects.ENTITY_IRON_GOLEM_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 194 */     return SoundEffects.ENTITY_IRON_GOLEM_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 199 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 200 */     Item item = itemstack.getItem();
/*     */     
/* 202 */     if (item != Items.IRON_INGOT) {
/* 203 */       return EnumInteractionResult.PASS;
/*     */     }
/* 205 */     float f = getHealth();
/*     */     
/* 207 */     heal(25.0F);
/* 208 */     if (getHealth() == f) {
/* 209 */       return EnumInteractionResult.PASS;
/*     */     }
/* 211 */     float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
/*     */     
/* 213 */     playSound(SoundEffects.ENTITY_IRON_GOLEM_REPAIR, 1.0F, f1);
/* 214 */     if (!entityhuman.abilities.canInstantlyBuild) {
/* 215 */       itemstack.subtract(1);
/*     */     }
/*     */     
/* 218 */     return EnumInteractionResult.a(this.world.isClientSide);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 225 */     playSound(SoundEffects.ENTITY_IRON_GOLEM_STEP, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean isPlayerCreated() {
/* 229 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void setPlayerCreated(boolean flag) {
/* 233 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*     */     
/* 235 */     if (flag) {
/* 236 */       this.datawatcher.set(b, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 238 */       this.datawatcher.set(b, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 245 */     super.die(damagesource);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader iworldreader) {
/* 250 */     BlockPosition blockposition = getChunkCoordinates();
/* 251 */     BlockPosition blockposition1 = blockposition.down();
/* 252 */     IBlockData iblockdata = iworldreader.getType(blockposition1);
/*     */     
/* 254 */     if (!iblockdata.a(iworldreader, blockposition1, this) && !this.world.paperConfig.ironGolemsCanSpawnInAir) {
/* 255 */       return false;
/*     */     }
/* 257 */     for (int i = 1; i < 3; i++) {
/* 258 */       BlockPosition blockposition2 = blockposition.up(i);
/* 259 */       IBlockData iblockdata1 = iworldreader.getType(blockposition2);
/*     */       
/* 261 */       if (!SpawnerCreature.a(iworldreader, blockposition2, iblockdata1, iblockdata1.getFluid(), EntityTypes.IRON_GOLEM)) {
/* 262 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 266 */     return (SpawnerCreature.a(iworldreader, blockposition, iworldreader.getType(blockposition), FluidTypes.EMPTY.h(), EntityTypes.IRON_GOLEM) && iworldreader.j(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public enum CrackLevel
/*     */   {
/* 272 */     NONE(1.0F), LOW(0.75F), MEDIUM(0.5F), HIGH(0.25F); private final float f;
/*     */     private static final List<CrackLevel> e;
/*     */     
/*     */     static {
/* 276 */       e = (List<CrackLevel>)Stream.<CrackLevel>of(values()).sorted(Comparator.comparingDouble(entityirongolem_cracklevel -> entityirongolem_cracklevel.f)).collect(ImmutableList.toImmutableList());
/*     */     }
/*     */     
/*     */     CrackLevel(float f) {
/* 280 */       this.f = f;
/*     */     }
/*     */     public static CrackLevel a(float f) {
/*     */       CrackLevel entityirongolem_cracklevel;
/* 284 */       Iterator<CrackLevel> iterator = e.iterator();
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 289 */         if (!iterator.hasNext()) {
/* 290 */           return NONE;
/*     */         }
/*     */         
/* 293 */         entityirongolem_cracklevel = iterator.next();
/* 294 */       } while (f >= entityirongolem_cracklevel.f);
/*     */       
/* 296 */       return entityirongolem_cracklevel;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityIronGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */