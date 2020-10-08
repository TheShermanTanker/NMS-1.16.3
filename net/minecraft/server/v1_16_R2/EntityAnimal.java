/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityBreedEvent;
/*     */ import org.bukkit.event.entity.EntityEnterLoveModeEvent;
/*     */ 
/*     */ public abstract class EntityAnimal extends EntityAgeable {
/*     */   public int loveTicks;
/*     */   public UUID breedCause;
/*     */   public ItemStack breedItem;
/*     */   
/*     */   protected EntityAnimal(EntityTypes<? extends EntityAnimal> entitytypes, World world) {
/*  18 */     super((EntityTypes)entitytypes, world);
/*  19 */     a(PathType.DANGER_FIRE, 16.0F);
/*  20 */     a(PathType.DAMAGE_FIRE, -1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/*  25 */     if (getAge() != 0) {
/*  26 */       this.loveTicks = 0;
/*     */     }
/*     */     
/*  29 */     super.mobTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  34 */     super.movementTick();
/*  35 */     if (getAge() != 0) {
/*  36 */       this.loveTicks = 0;
/*     */     }
/*     */     
/*  39 */     if (this.loveTicks > 0) {
/*  40 */       this.loveTicks--;
/*  41 */       if (this.loveTicks % 10 == 0) {
/*  42 */         double d0 = this.random.nextGaussian() * 0.02D;
/*  43 */         double d1 = this.random.nextGaussian() * 0.02D;
/*  44 */         double d2 = this.random.nextGaussian() * 0.02D;
/*     */         
/*  46 */         this.world.addParticle(Particles.HEART, d(1.0D), cE() + 0.5D, g(1.0D), d0, d1, d2);
/*     */       } 
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float a(BlockPosition blockposition, IWorldReader iworldreader) {
/*  68 */     return iworldreader.getType(blockposition.down()).a(Blocks.GRASS_BLOCK) ? 10.0F : (iworldreader.y(blockposition) - 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  73 */     super.saveData(nbttagcompound);
/*  74 */     nbttagcompound.setInt("InLove", this.loveTicks);
/*  75 */     if (this.breedCause != null) {
/*  76 */       nbttagcompound.a("LoveCause", this.breedCause);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double ba() {
/*  83 */     return 0.14D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  88 */     super.loadData(nbttagcompound);
/*  89 */     this.loveTicks = nbttagcompound.getInt("InLove");
/*  90 */     this.breedCause = nbttagcompound.b("LoveCause") ? nbttagcompound.a("LoveCause") : null;
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<? extends EntityAnimal> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  94 */     return (generatoraccess.getType(blockposition.down()).a(Blocks.GRASS_BLOCK) && generatoraccess.getLightLevel(blockposition, 0) > 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public int D() {
/*  99 */     return 120;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getExpValue(EntityHuman entityhuman) {
/* 109 */     return 1 + this.world.random.nextInt(3);
/*     */   }
/*     */   
/*     */   public boolean k(ItemStack itemstack) {
/* 113 */     return (itemstack.getItem() == Items.WHEAT);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 118 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 120 */     if (k(itemstack)) {
/* 121 */       int i = getAge();
/*     */       
/* 123 */       if (!this.world.isClientSide && i == 0 && eP()) {
/* 124 */         a(entityhuman, itemstack);
/* 125 */         g(entityhuman);
/* 126 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/*     */       
/* 129 */       if (isBaby()) {
/* 130 */         a(entityhuman, itemstack);
/* 131 */         setAge((int)((-i / 20) * 0.1F), true);
/* 132 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */       
/* 135 */       if (this.world.isClientSide) {
/* 136 */         return EnumInteractionResult.CONSUME;
/*     */       }
/*     */     } 
/*     */     
/* 140 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */   
/*     */   protected void a(EntityHuman entityhuman, ItemStack itemstack) {
/* 144 */     if (!entityhuman.abilities.canInstantlyBuild) {
/* 145 */       itemstack.subtract(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eP() {
/* 151 */     return (this.loveTicks <= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(@Nullable EntityHuman entityhuman) {
/* 156 */     EntityEnterLoveModeEvent entityEnterLoveModeEvent = CraftEventFactory.callEntityEnterLoveModeEvent(entityhuman, this, 600);
/* 157 */     if (entityEnterLoveModeEvent.isCancelled()) {
/*     */       return;
/*     */     }
/* 160 */     this.loveTicks = entityEnterLoveModeEvent.getTicksInLove();
/*     */     
/* 162 */     if (entityhuman != null) {
/* 163 */       this.breedCause = entityhuman.getUniqueID();
/*     */     }
/* 165 */     this.breedItem = entityhuman.inventory.getItemInHand();
/*     */     
/* 167 */     this.world.broadcastEntityEffect(this, (byte)18);
/*     */   }
/*     */   
/*     */   public void setLoveTicks(int i) {
/* 171 */     this.loveTicks = i;
/*     */   }
/*     */   
/*     */   public int eQ() {
/* 175 */     return this.loveTicks;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityPlayer getBreedCause() {
/* 180 */     if (this.breedCause == null) {
/* 181 */       return null;
/*     */     }
/* 183 */     EntityHuman entityhuman = this.world.b(this.breedCause);
/*     */     
/* 185 */     return (entityhuman instanceof EntityPlayer) ? (EntityPlayer)entityhuman : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInLove() {
/* 190 */     return (this.loveTicks > 0);
/*     */   }
/*     */   
/*     */   public void resetLove() {
/* 194 */     this.loveTicks = 0;
/*     */   }
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 198 */     return (entityanimal == this) ? false : ((entityanimal.getClass() != getClass()) ? false : ((isInLove() && entityanimal.isInLove())));
/*     */   }
/*     */   
/*     */   public void a(WorldServer worldserver, EntityAnimal entityanimal) {
/* 202 */     EntityAgeable entityageable = createChild(worldserver, entityanimal);
/*     */     
/* 204 */     if (entityageable != null) {
/*     */       
/* 206 */       if (entityageable instanceof EntityTameableAnimal && ((EntityTameableAnimal)entityageable).isTamed()) {
/* 207 */         entityageable.persistent = true;
/*     */       }
/*     */       
/* 210 */       EntityPlayer entityplayer = getBreedCause();
/*     */       
/* 212 */       if (entityplayer == null && entityanimal.getBreedCause() != null) {
/* 213 */         entityplayer = entityanimal.getBreedCause();
/*     */       }
/*     */       
/* 216 */       int experience = getRandom().nextInt(7) + 1;
/* 217 */       EntityBreedEvent entityBreedEvent = CraftEventFactory.callEntityBreedEvent(entityageable, this, entityanimal, entityplayer, this.breedItem, experience);
/* 218 */       if (entityBreedEvent.isCancelled()) {
/*     */         return;
/*     */       }
/* 221 */       experience = entityBreedEvent.getExperience();
/*     */ 
/*     */       
/* 224 */       if (entityplayer != null) {
/* 225 */         entityplayer.a(StatisticList.ANIMALS_BRED);
/* 226 */         CriterionTriggers.o.a(entityplayer, this, entityanimal, entityageable);
/*     */       } 
/*     */       
/* 229 */       setAgeRaw(6000);
/* 230 */       entityanimal.setAgeRaw(6000);
/* 231 */       resetLove();
/* 232 */       entityanimal.resetLove();
/* 233 */       entityageable.setBaby(true);
/* 234 */       entityageable.setPositionRotation(locX(), locY(), locZ(), 0.0F, 0.0F);
/* 235 */       worldserver.addAllEntities(entityageable, CreatureSpawnEvent.SpawnReason.BREEDING);
/* 236 */       worldserver.broadcastEntityEffect(this, (byte)18);
/* 237 */       if (worldserver.getGameRules().getBoolean(GameRules.DO_MOB_LOOT))
/*     */       {
/* 239 */         if (experience > 0)
/* 240 */           worldserver.addEntity(new EntityExperienceOrb(worldserver, locX(), locY(), locZ(), experience, ExperienceOrb.SpawnReason.BREED, entityplayer, entityageable)); 
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */