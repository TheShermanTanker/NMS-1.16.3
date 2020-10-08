/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftMerchantRecipe;
/*     */ import org.bukkit.entity.AbstractVillager;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.VillagerAcquireTradeEvent;
/*     */ import org.bukkit.inventory.MerchantRecipe;
/*     */ 
/*     */ public class EntityVillagerTrader extends EntityVillagerAbstract {
/*     */   @Nullable
/*     */   private BlockPosition bp;
/*     */   private int bq;
/*     */   
/*     */   public EntityVillagerTrader(EntityTypes<? extends EntityVillagerTrader> entitytypes, World world) {
/*  19 */     super((EntityTypes)entitytypes, world);
/*  20 */     this.attachedToPlayer = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  25 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  26 */     this.goalSelector.a(0, new PathfinderGoalUseItem<>(this, PotionUtil.a(new ItemStack(Items.POTION), Potions.INVISIBILITY), SoundEffects.ENTITY_WANDERING_TRADER_DISAPPEARED, entityvillagertrader -> 
/*  27 */           (this.world.isNight() && !entityvillagertrader.isInvisible())));
/*     */     
/*  29 */     this.goalSelector.a(0, new PathfinderGoalUseItem<>(this, new ItemStack(Items.MILK_BUCKET), SoundEffects.ENTITY_WANDERING_TRADER_REAPPEARED, entityvillagertrader -> 
/*  30 */           (this.world.isDay() && entityvillagertrader.isInvisible())));
/*     */     
/*  32 */     this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
/*  33 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityZombie.class, 8.0F, 0.5D, 0.5D));
/*  34 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityEvoker.class, 12.0F, 0.5D, 0.5D));
/*  35 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityVindicator.class, 8.0F, 0.5D, 0.5D));
/*  36 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityVex.class, 8.0F, 0.5D, 0.5D));
/*  37 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityPillager.class, 15.0F, 0.5D, 0.5D));
/*  38 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityIllagerIllusioner.class, 12.0F, 0.5D, 0.5D));
/*  39 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityZoglin.class, 10.0F, 0.5D, 0.5D));
/*  40 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.5D));
/*  41 */     this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
/*  42 */     this.goalSelector.a(2, new a(this, 2.0D, 0.35D));
/*  43 */     this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 0.35D));
/*  44 */     this.goalSelector.a(8, new PathfinderGoalRandomStrollLand(this, 0.35D));
/*  45 */     this.goalSelector.a(9, new PathfinderGoalInteract(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  46 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(WorldServer worldserver, EntityAgeable entityageable) {
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRegularVillager() {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/*  62 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  64 */     if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && isAlive() && !eN() && !isBaby()) {
/*  65 */       if (enumhand == EnumHand.MAIN_HAND) {
/*  66 */         entityhuman.a(StatisticList.TALKED_TO_VILLAGER);
/*     */       }
/*     */       
/*  69 */       if (getOffers().isEmpty()) {
/*  70 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       }
/*  72 */       if (!this.world.isClientSide) {
/*  73 */         setTradingPlayer(entityhuman);
/*  74 */         openTrade(entityhuman, getScoreboardDisplayName(), 1);
/*     */       } 
/*     */       
/*  77 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/*     */     
/*  80 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void eW() {
/*  86 */     VillagerTrades.IMerchantRecipeOption[] avillagertrades_imerchantrecipeoption = (VillagerTrades.IMerchantRecipeOption[])VillagerTrades.b.get(1);
/*  87 */     VillagerTrades.IMerchantRecipeOption[] avillagertrades_imerchantrecipeoption1 = (VillagerTrades.IMerchantRecipeOption[])VillagerTrades.b.get(2);
/*     */     
/*  89 */     if (avillagertrades_imerchantrecipeoption != null && avillagertrades_imerchantrecipeoption1 != null) {
/*  90 */       MerchantRecipeList merchantrecipelist = getOffers();
/*     */       
/*  92 */       a(merchantrecipelist, avillagertrades_imerchantrecipeoption, 5);
/*  93 */       int i = this.random.nextInt(avillagertrades_imerchantrecipeoption1.length);
/*  94 */       VillagerTrades.IMerchantRecipeOption villagertrades_imerchantrecipeoption = avillagertrades_imerchantrecipeoption1[i];
/*  95 */       MerchantRecipe merchantrecipe = villagertrades_imerchantrecipeoption.a(this, this.random);
/*     */       
/*  97 */       if (merchantrecipe != null) {
/*     */         
/*  99 */         VillagerAcquireTradeEvent event = new VillagerAcquireTradeEvent((AbstractVillager)getBukkitEntity(), (MerchantRecipe)merchantrecipe.asBukkit());
/*     */         
/* 101 */         if (this.valid) {
/* 102 */           Bukkit.getPluginManager().callEvent((Event)event);
/*     */         }
/* 104 */         if (!event.isCancelled()) {
/* 105 */           merchantrecipelist.add(CraftMerchantRecipe.fromBukkit(event.getRecipe()).toMinecraft());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 115 */     super.saveData(nbttagcompound);
/* 116 */     nbttagcompound.setInt("DespawnDelay", this.bq);
/* 117 */     if (this.bp != null) {
/* 118 */       nbttagcompound.set("WanderTarget", GameProfileSerializer.a(this.bp));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 125 */     super.loadData(nbttagcompound);
/* 126 */     if (nbttagcompound.hasKeyOfType("DespawnDelay", 99)) {
/* 127 */       this.bq = nbttagcompound.getInt("DespawnDelay");
/*     */     }
/*     */     
/* 130 */     if (nbttagcompound.hasKey("WanderTarget")) {
/* 131 */       this.bp = GameProfileSerializer.b(nbttagcompound.getCompound("WanderTarget"));
/*     */     }
/*     */     
/* 134 */     setAgeRaw(Math.max(0, getAge()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(MerchantRecipe merchantrecipe) {
/* 144 */     if (merchantrecipe.isRewardExp()) {
/* 145 */       int i = 3 + this.random.nextInt(4);
/*     */       
/* 147 */       this.world.addEntity(new EntityExperienceOrb(this.world, locX(), locY() + 0.5D, locZ(), i, ExperienceOrb.SpawnReason.VILLAGER_TRADE, getTrader(), this));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 154 */     return eN() ? SoundEffects.ENTITY_WANDERING_TRADER_TRADE : SoundEffects.ENTITY_WANDERING_TRADER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 159 */     return SoundEffects.ENTITY_WANDERING_TRADER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 164 */     return SoundEffects.ENTITY_WANDERING_TRADER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect c(ItemStack itemstack) {
/* 169 */     Item item = itemstack.getItem();
/*     */     
/* 171 */     return (item == Items.MILK_BUCKET) ? SoundEffects.ENTITY_WANDERING_TRADER_DRINK_MILK : SoundEffects.ENTITY_WANDERING_TRADER_DRINK_POTION;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect t(boolean flag) {
/* 176 */     return flag ? SoundEffects.ENTITY_WANDERING_TRADER_YES : SoundEffects.ENTITY_WANDERING_TRADER_NO;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEffect getTradeSound() {
/* 181 */     return SoundEffects.ENTITY_WANDERING_TRADER_YES;
/*     */   }
/*     */   
/*     */   public void u(int i) {
/* 185 */     this.bq = i;
/*     */   }
/*     */   
/*     */   public int eX() {
/* 189 */     return this.bq;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 194 */     super.movementTick();
/* 195 */     if (!this.world.isClientSide) {
/* 196 */       eY();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void eY() {
/* 202 */     if (this.bq > 0 && !eN() && --this.bq == 0) {
/* 203 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(@Nullable BlockPosition blockposition) {
/* 209 */     this.bp = blockposition;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private BlockPosition eZ() {
/* 214 */     return this.bp;
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoal {
/*     */     final EntityVillagerTrader a;
/*     */     final double b;
/*     */     final double c;
/*     */     
/*     */     a(EntityVillagerTrader entityvillagertrader, double d0, double d1) {
/* 224 */       this.a = entityvillagertrader;
/* 225 */       this.b = d0;
/* 226 */       this.c = d1;
/* 227 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 232 */       this.a.g((BlockPosition)null);
/* 233 */       EntityVillagerTrader.this.navigation.o();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 238 */       BlockPosition blockposition = this.a.eZ();
/*     */       
/* 240 */       return (blockposition != null && a(blockposition, this.b));
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 245 */       BlockPosition blockposition = this.a.eZ();
/*     */       
/* 247 */       if (blockposition != null && EntityVillagerTrader.this.navigation.m()) {
/* 248 */         if (a(blockposition, 10.0D)) {
/* 249 */           Vec3D vec3d = (new Vec3D(blockposition.getX() - this.a.locX(), blockposition.getY() - this.a.locY(), blockposition.getZ() - this.a.locZ())).d();
/* 250 */           Vec3D vec3d1 = vec3d.a(10.0D).add(this.a.locX(), this.a.locY(), this.a.locZ());
/*     */           
/* 252 */           EntityVillagerTrader.this.navigation.a(vec3d1.x, vec3d1.y, vec3d1.z, this.c);
/*     */         } else {
/* 254 */           EntityVillagerTrader.this.navigation.a(blockposition.getX(), blockposition.getY(), blockposition.getZ(), this.c);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean a(BlockPosition blockposition, double d0) {
/* 261 */       return !blockposition.a(this.a.getPositionVector(), d0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityVillagerTrader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */