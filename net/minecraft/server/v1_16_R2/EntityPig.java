/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class EntityPig
/*     */   extends EntityAnimal
/*     */   implements ISteerable, ISaddleable
/*     */ {
/*  12 */   private static final DataWatcherObject<Boolean> bo = DataWatcher.a((Class)EntityPig.class, DataWatcherRegistry.i);
/*  13 */   private static final DataWatcherObject<Integer> bp = DataWatcher.a((Class)EntityPig.class, DataWatcherRegistry.b);
/*  14 */   private static final RecipeItemStack bq = RecipeItemStack.a(new IMaterial[] { Items.CARROT, Items.POTATO, Items.BEETROOT });
/*     */   public final SaddleStorage saddleStorage;
/*     */   
/*     */   public EntityPig(EntityTypes<? extends EntityPig> entitytypes, World world) {
/*  18 */     super((EntityTypes)entitytypes, world);
/*  19 */     this.saddleStorage = new SaddleStorage(this.datawatcher, bp, bo);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  24 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  25 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.25D));
/*  26 */     this.goalSelector.a(3, new PathfinderGoalBreed(this, 1.0D));
/*  27 */     this.goalSelector.a(4, new PathfinderGoalTempt(this, 1.2D, RecipeItemStack.a(new IMaterial[] { Items.CARROT_ON_A_STICK }, ), false));
/*  28 */     this.goalSelector.a(4, new PathfinderGoalTempt(this, 1.2D, false, bq));
/*  29 */     this.goalSelector.a(5, new PathfinderGoalFollowParent(this, 1.1D));
/*  30 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  31 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  32 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  36 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 10.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getRidingPassenger() {
/*  42 */     return getPassengers().isEmpty() ? null : getPassengers().get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean er() {
/*  47 */     Entity entity = getRidingPassenger();
/*     */     
/*  49 */     if (!(entity instanceof EntityHuman)) {
/*  50 */       return false;
/*     */     }
/*  52 */     EntityHuman entityhuman = (EntityHuman)entity;
/*     */     
/*  54 */     return (entityhuman.getItemInMainHand().getItem() == Items.CARROT_ON_A_STICK || entityhuman.getItemInOffHand().getItem() == Items.CARROT_ON_A_STICK);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/*  60 */     if (bp.equals(datawatcherobject) && this.world.isClientSide) {
/*  61 */       this.saddleStorage.a();
/*     */     }
/*     */     
/*  64 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  69 */     super.initDatawatcher();
/*  70 */     this.datawatcher.register(bo, Boolean.valueOf(false));
/*  71 */     this.datawatcher.register(bp, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  76 */     super.saveData(nbttagcompound);
/*  77 */     this.saddleStorage.a(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  82 */     super.loadData(nbttagcompound);
/*  83 */     this.saddleStorage.b(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  88 */     return SoundEffects.ENTITY_PIG_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  93 */     return SoundEffects.ENTITY_PIG_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  98 */     return SoundEffects.ENTITY_PIG_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 103 */     playSound(SoundEffects.ENTITY_PIG_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 108 */     boolean flag = k(entityhuman.b(enumhand));
/*     */     
/* 110 */     if (!flag && hasSaddle() && !isVehicle() && !entityhuman.ep()) {
/* 111 */       if (!this.world.isClientSide) {
/* 112 */         entityhuman.startRiding(this);
/*     */       }
/*     */       
/* 115 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/* 117 */     EnumInteractionResult enuminteractionresult = super.b(entityhuman, enumhand);
/*     */     
/* 119 */     if (!enuminteractionresult.a()) {
/* 120 */       ItemStack itemstack = entityhuman.b(enumhand);
/*     */       
/* 122 */       return (itemstack.getItem() == Items.SADDLE) ? itemstack.a(entityhuman, this, enumhand) : EnumInteractionResult.PASS;
/*     */     } 
/* 124 */     return enuminteractionresult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSaddle() {
/* 131 */     return (isAlive() && !isBaby());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropInventory() {
/* 136 */     super.dropInventory();
/* 137 */     if (hasSaddle()) {
/* 138 */       a(Items.SADDLE);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSaddle() {
/* 145 */     return this.saddleStorage.hasSaddle();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saddle(@Nullable SoundCategory soundcategory) {
/* 150 */     this.saddleStorage.setSaddle(true);
/* 151 */     if (soundcategory != null) {
/* 152 */       this.world.playSound((EntityHuman)null, this, SoundEffects.ENTITY_PIG_SADDLE, soundcategory, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3D b(EntityLiving entityliving) {
/* 159 */     EnumDirection enumdirection = getAdjustedDirection();
/*     */     
/* 161 */     if (enumdirection.n() == EnumDirection.EnumAxis.Y) {
/* 162 */       return super.b(entityliving);
/*     */     }
/* 164 */     int[][] aint = DismountUtil.a(enumdirection);
/* 165 */     BlockPosition blockposition = getChunkCoordinates();
/* 166 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 167 */     UnmodifiableIterator unmodifiableiterator = entityliving.ei().iterator();
/*     */     
/* 169 */     while (unmodifiableiterator.hasNext()) {
/* 170 */       EntityPose entitypose = (EntityPose)unmodifiableiterator.next();
/* 171 */       AxisAlignedBB axisalignedbb = entityliving.f(entitypose);
/* 172 */       int[][] aint1 = aint;
/* 173 */       int i = aint.length;
/*     */       
/* 175 */       for (int j = 0; j < i; j++) {
/* 176 */         int[] aint2 = aint1[j];
/*     */         
/* 178 */         blockposition_mutableblockposition.d(blockposition.getX() + aint2[0], blockposition.getY(), blockposition.getZ() + aint2[1]);
/* 179 */         double d0 = this.world.h(blockposition_mutableblockposition);
/*     */         
/* 181 */         if (DismountUtil.a(d0)) {
/* 182 */           Vec3D vec3d = Vec3D.a(blockposition_mutableblockposition, d0);
/*     */           
/* 184 */           if (DismountUtil.a(this.world, entityliving, axisalignedbb.c(vec3d))) {
/* 185 */             entityliving.setPose(entitypose);
/* 186 */             return vec3d;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 192 */     return super.b(entityliving);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLightningStrike(WorldServer worldserver, EntityLightning entitylightning) {
/* 198 */     if (worldserver.getDifficulty() != EnumDifficulty.PEACEFUL) {
/* 199 */       EntityPigZombie entitypigzombie = EntityTypes.ZOMBIFIED_PIGLIN.a(worldserver);
/*     */       
/* 201 */       entitypigzombie.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
/* 202 */       entitypigzombie.setPositionRotation(locX(), locY(), locZ(), this.yaw, this.pitch);
/* 203 */       entitypigzombie.setNoAI(isNoAI());
/* 204 */       entitypigzombie.setBaby(isBaby());
/* 205 */       if (hasCustomName()) {
/* 206 */         entitypigzombie.setCustomName(getCustomName());
/* 207 */         entitypigzombie.setCustomNameVisible(getCustomNameVisible());
/*     */       } 
/*     */       
/* 210 */       entitypigzombie.setPersistent();
/*     */       
/* 212 */       if (CraftEventFactory.callEntityZapEvent(this, entitylightning, entitypigzombie).isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 217 */       if (CraftEventFactory.callPigZapEvent(this, entitylightning, entitypigzombie).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/* 221 */       worldserver.addEntity(entitypigzombie, CreatureSpawnEvent.SpawnReason.LIGHTNING);
/*     */       
/* 223 */       die();
/*     */     } else {
/* 225 */       super.onLightningStrike(worldserver, entitylightning);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(Vec3D vec3d) {
/* 232 */     a(this, this.saddleStorage, vec3d);
/*     */   }
/*     */ 
/*     */   
/*     */   public float N_() {
/* 237 */     return (float)b(GenericAttributes.MOVEMENT_SPEED) * 0.225F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a_(Vec3D vec3d) {
/* 242 */     super.g(vec3d);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean O_() {
/* 247 */     return this.saddleStorage.a(getRandom());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPig createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 252 */     return EntityTypes.PIG.a(worldserver);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 257 */     return bq.test(itemstack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */