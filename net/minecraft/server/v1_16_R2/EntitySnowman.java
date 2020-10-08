/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntitySnowman
/*     */   extends EntityGolem
/*     */   implements IShearable, IRangedEntity
/*     */ {
/*  10 */   private static final DataWatcherObject<Byte> b = DataWatcher.a((Class)EntitySnowman.class, DataWatcherRegistry.a);
/*     */   
/*     */   public EntitySnowman(EntityTypes<? extends EntitySnowman> entitytypes, World world) {
/*  13 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  18 */     this.goalSelector.a(1, new PathfinderGoalArrowAttack(this, 1.25D, 20, 10.0F));
/*  19 */     this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D, 1.0000001E-5F));
/*  20 */     this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  21 */     this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
/*  22 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityInsentient.class, 10, true, false, entityliving -> entityliving instanceof IMonster));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  28 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 4.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.20000000298023224D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  33 */     super.initDatawatcher();
/*  34 */     this.datawatcher.register(b, Byte.valueOf((byte)16));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  39 */     super.saveData(nbttagcompound);
/*  40 */     nbttagcompound.setBoolean("Pumpkin", hasPumpkin());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  45 */     super.loadData(nbttagcompound);
/*  46 */     if (nbttagcompound.hasKey("Pumpkin")) {
/*  47 */       setHasPumpkin(nbttagcompound.getBoolean("Pumpkin"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean dN() {
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  59 */     super.movementTick();
/*  60 */     if (!this.world.isClientSide) {
/*  61 */       int i = MathHelper.floor(locX());
/*  62 */       int j = MathHelper.floor(locY());
/*  63 */       int k = MathHelper.floor(locZ());
/*     */       
/*  65 */       if (this.world.getBiome(new BlockPosition(i, 0, k)).getAdjustedTemperature(new BlockPosition(i, j, k)) > 1.0F) {
/*  66 */         damageEntity(CraftEventFactory.MELTING, 1.0F);
/*     */       }
/*     */       
/*  69 */       if (!this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
/*     */         return;
/*     */       }
/*     */       
/*  73 */       IBlockData iblockdata = Blocks.SNOW.getBlockData();
/*     */       
/*  75 */       for (int l = 0; l < 4; l++) {
/*  76 */         i = MathHelper.floor(locX() + ((l % 2 * 2 - 1) * 0.25F));
/*  77 */         j = MathHelper.floor(locY());
/*  78 */         k = MathHelper.floor(locZ() + ((l / 2 % 2 * 2 - 1) * 0.25F));
/*  79 */         BlockPosition blockposition = new BlockPosition(i, j, k);
/*     */         
/*  81 */         if (this.world.getType(blockposition).isAir() && this.world.getBiome(blockposition).getAdjustedTemperature(blockposition) < 0.8F && iblockdata.canPlace(this.world, blockposition)) {
/*  82 */           CraftEventFactory.handleBlockFormEvent(this.world, blockposition, iblockdata, this);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/*  91 */     EntitySnowball entitysnowball = new EntitySnowball(this.world, this);
/*  92 */     double d0 = entityliving.getHeadY() - 1.100000023841858D;
/*  93 */     double d1 = entityliving.locX() - locX();
/*  94 */     double d2 = d0 - entitysnowball.locY();
/*  95 */     double d3 = entityliving.locZ() - locZ();
/*  96 */     float f1 = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
/*     */     
/*  98 */     entitysnowball.shoot(d1, d2 + f1, d3, 1.6F, 12.0F);
/*  99 */     playSound(SoundEffects.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (getRandom().nextFloat() * 0.4F + 0.8F));
/* 100 */     this.world.addEntity(entitysnowball);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 105 */     return 1.7F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 110 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 112 */     if (itemstack.getItem() == Items.SHEARS && canShear()) {
/*     */       
/* 114 */       if (!CraftEventFactory.handlePlayerShearEntityEvent(entityhuman, this, itemstack, enumhand)) {
/* 115 */         return EnumInteractionResult.PASS;
/*     */       }
/*     */       
/* 118 */       shear(SoundCategory.PLAYERS);
/* 119 */       if (!this.world.isClientSide) {
/* 120 */         itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(enumhand));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 125 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/* 127 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shear(SoundCategory soundcategory) {
/* 133 */     this.world.playSound((EntityHuman)null, this, SoundEffects.ENTITY_SNOW_GOLEM_SHEAR, soundcategory, 1.0F, 1.0F);
/* 134 */     if (!this.world.s_()) {
/* 135 */       setHasPumpkin(false);
/* 136 */       a(new ItemStack(Items.dj), 1.7F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canShear() {
/* 143 */     return (isAlive() && hasPumpkin());
/*     */   }
/*     */   
/*     */   public boolean hasPumpkin() {
/* 147 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x10) != 0);
/*     */   }
/*     */   
/*     */   public void setHasPumpkin(boolean flag) {
/* 151 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*     */     
/* 153 */     if (flag) {
/* 154 */       this.datawatcher.set(b, Byte.valueOf((byte)(b0 | 0x10)));
/*     */     } else {
/* 156 */       this.datawatcher.set(b, Byte.valueOf((byte)(b0 & 0xFFFFFFEF)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundAmbient() {
/* 164 */     return SoundEffects.ENTITY_SNOW_GOLEM_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 170 */     return SoundEffects.ENTITY_SNOW_GOLEM_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundDeath() {
/* 176 */     return SoundEffects.ENTITY_SNOW_GOLEM_DEATH;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySnowman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */