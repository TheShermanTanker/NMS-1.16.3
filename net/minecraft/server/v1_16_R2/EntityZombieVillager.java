/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.entity.ZombieVillager;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.event.entity.EntityTransformEvent;
/*     */ 
/*     */ public class EntityZombieVillager
/*     */   extends EntityZombie
/*     */   implements VillagerDataHolder {
/*  17 */   public static final DataWatcherObject<Boolean> CONVERTING = DataWatcher.a((Class)EntityZombieVillager.class, DataWatcherRegistry.i);
/*  18 */   private static final DataWatcherObject<VillagerData> c = DataWatcher.a((Class)EntityZombieVillager.class, DataWatcherRegistry.q);
/*     */   public int conversionTime;
/*     */   public UUID conversionPlayer;
/*     */   private NBTBase bp;
/*     */   private NBTTagCompound bq;
/*     */   private int br;
/*  24 */   private int lastTick = MinecraftServer.currentTick;
/*     */   
/*     */   public EntityZombieVillager(EntityTypes<? extends EntityZombieVillager> entitytypes, World world) {
/*  27 */     super((EntityTypes)entitytypes, world);
/*  28 */     setVillagerData(getVillagerData().withProfession(IRegistry.VILLAGER_PROFESSION.a(this.random)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  33 */     super.initDatawatcher();
/*  34 */     this.datawatcher.register(CONVERTING, Boolean.valueOf(false));
/*  35 */     this.datawatcher.register(c, new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  40 */     super.saveData(nbttagcompound);
/*  41 */     DataResult<NBTBase> dataresult = VillagerData.a.encodeStart(DynamicOpsNBT.a, getVillagerData());
/*  42 */     Logger logger = LOGGER;
/*     */     
/*  44 */     logger.getClass();
/*  45 */     Objects.requireNonNull(logger); dataresult.resultOrPartial(logger::error).ifPresent(nbtbase -> nbttagcompound.set("VillagerData", nbtbase));
/*     */ 
/*     */     
/*  48 */     if (this.bq != null) {
/*  49 */       nbttagcompound.set("Offers", this.bq);
/*     */     }
/*     */     
/*  52 */     if (this.bp != null) {
/*  53 */       nbttagcompound.set("Gossips", this.bp);
/*     */     }
/*     */     
/*  56 */     nbttagcompound.setInt("ConversionTime", isConverting() ? this.conversionTime : -1);
/*  57 */     if (this.conversionPlayer != null) {
/*  58 */       nbttagcompound.a("ConversionPlayer", this.conversionPlayer);
/*     */     }
/*     */     
/*  61 */     nbttagcompound.setInt("Xp", this.br);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  66 */     super.loadData(nbttagcompound);
/*  67 */     if (nbttagcompound.hasKeyOfType("VillagerData", 10)) {
/*  68 */       DataResult<VillagerData> dataresult = VillagerData.a.parse(new Dynamic(DynamicOpsNBT.a, nbttagcompound.get("VillagerData")));
/*  69 */       Logger logger = LOGGER;
/*     */       
/*  71 */       logger.getClass();
/*  72 */       Objects.requireNonNull(logger); dataresult.resultOrPartial(logger::error).ifPresent(this::setVillagerData);
/*     */     } 
/*     */     
/*  75 */     if (nbttagcompound.hasKeyOfType("Offers", 10)) {
/*  76 */       this.bq = nbttagcompound.getCompound("Offers");
/*     */     }
/*     */     
/*  79 */     if (nbttagcompound.hasKeyOfType("Gossips", 10)) {
/*  80 */       this.bp = nbttagcompound.getList("Gossips", 10);
/*     */     }
/*     */     
/*  83 */     if (nbttagcompound.hasKeyOfType("ConversionTime", 99) && nbttagcompound.getInt("ConversionTime") > -1) {
/*  84 */       startConversion(nbttagcompound.b("ConversionPlayer") ? nbttagcompound.a("ConversionPlayer") : null, nbttagcompound.getInt("ConversionTime"));
/*     */     }
/*     */     
/*  87 */     if (nbttagcompound.hasKeyOfType("Xp", 3)) {
/*  88 */       this.br = nbttagcompound.getInt("Xp");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  95 */     if (!this.world.isClientSide && isAlive() && isConverting()) {
/*  96 */       int i = getConversionProgress();
/*     */       
/*  98 */       int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
/*  99 */       i *= elapsedTicks;
/*     */ 
/*     */       
/* 102 */       this.conversionTime -= i;
/* 103 */       if (this.conversionTime <= 0) {
/* 104 */         c((WorldServer)this.world);
/*     */       }
/*     */     } 
/*     */     
/* 108 */     super.tick();
/* 109 */     this.lastTick = MinecraftServer.currentTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 114 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 116 */     if (itemstack.getItem() == Items.GOLDEN_APPLE) {
/* 117 */       if (hasEffect(MobEffects.WEAKNESS)) {
/* 118 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 119 */           itemstack.subtract(1);
/*     */         }
/*     */         
/* 122 */         if (!this.world.isClientSide) {
/* 123 */           startConversion(entityhuman.getUniqueID(), this.random.nextInt(2401) + 3600);
/*     */         }
/*     */         
/* 126 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/* 128 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/*     */     
/* 131 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean eN() {
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double d0) {
/* 142 */     return (!isConverting() && this.br == 0);
/*     */   }
/*     */   
/*     */   public boolean isConverting() {
/* 146 */     return ((Boolean)getDataWatcher().<Boolean>get(CONVERTING)).booleanValue();
/*     */   }
/*     */   
/*     */   public void startConversion(@Nullable UUID uuid, int i) {
/* 150 */     this.conversionPlayer = uuid;
/* 151 */     this.conversionTime = i;
/* 152 */     getDataWatcher().set(CONVERTING, Boolean.valueOf(true));
/*     */     
/* 154 */     this.persistent = true;
/* 155 */     removeEffect(MobEffects.WEAKNESS, EntityPotionEffectEvent.Cause.CONVERSION);
/* 156 */     addEffect(new MobEffect(MobEffects.INCREASE_DAMAGE, i, Math.min(this.world.getDifficulty().a() - 1, 0)), EntityPotionEffectEvent.Cause.CONVERSION);
/*     */     
/* 158 */     this.world.broadcastEntityEffect(this, (byte)16);
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(WorldServer worldserver) {
/* 163 */     EntityVillager entityvillager = a(EntityTypes.VILLAGER, false, EntityTransformEvent.TransformReason.CURED, CreatureSpawnEvent.SpawnReason.CURED);
/* 164 */     if (entityvillager == null) {
/* 165 */       ((ZombieVillager)getBukkitEntity()).setConversionTime(-1);
/*     */       
/*     */       return;
/*     */     } 
/* 169 */     EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/* 170 */     int i = aenumitemslot.length;
/*     */     
/* 172 */     for (int j = 0; j < i; j++) {
/* 173 */       EnumItemSlot enumitemslot = aenumitemslot[j];
/* 174 */       ItemStack itemstack = getEquipment(enumitemslot);
/*     */       
/* 176 */       if (!itemstack.isEmpty()) {
/* 177 */         if (EnchantmentManager.d(itemstack)) {
/* 178 */           entityvillager.a_(enumitemslot.b() + 300, itemstack);
/*     */         } else {
/* 180 */           double d0 = e(enumitemslot);
/*     */           
/* 182 */           if (d0 > 1.0D) {
/* 183 */             a(itemstack);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 189 */     entityvillager.setVillagerData(getVillagerData());
/* 190 */     if (this.bp != null) {
/* 191 */       entityvillager.a(this.bp);
/*     */     }
/*     */     
/* 194 */     if (this.bq != null) {
/* 195 */       entityvillager.b(new MerchantRecipeList(this.bq));
/*     */     }
/*     */     
/* 198 */     entityvillager.setExperience(this.br);
/* 199 */     entityvillager.prepare(worldserver, worldserver.getDamageScaler(entityvillager.getChunkCoordinates()), EnumMobSpawn.CONVERSION, (GroupDataEntity)null, (NBTTagCompound)null);
/* 200 */     if (this.conversionPlayer != null) {
/* 201 */       EntityHuman entityhuman = worldserver.b(this.conversionPlayer);
/*     */       
/* 203 */       if (entityhuman instanceof EntityPlayer) {
/* 204 */         CriterionTriggers.r.a((EntityPlayer)entityhuman, this, entityvillager);
/* 205 */         worldserver.a(ReputationEvent.a, entityhuman, entityvillager);
/*     */       } 
/*     */     } 
/*     */     
/* 209 */     entityvillager.addEffect(new MobEffect(MobEffects.CONFUSION, 200, 0), EntityPotionEffectEvent.Cause.CONVERSION);
/* 210 */     if (!isSilent()) {
/* 211 */       worldserver.a((EntityHuman)null, 1027, getChunkCoordinates(), 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int getConversionProgress() {
/* 217 */     int i = 1;
/*     */     
/* 219 */     if (this.random.nextFloat() < 0.01F) {
/* 220 */       int j = 0;
/* 221 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */       
/* 223 */       for (int k = (int)locX() - 4; k < (int)locX() + 4 && j < 14; k++) {
/* 224 */         for (int l = (int)locY() - 4; l < (int)locY() + 4 && j < 14; l++) {
/* 225 */           for (int i1 = (int)locZ() - 4; i1 < (int)locZ() + 4 && j < 14; i1++) {
/* 226 */             Block block = this.world.getType(blockposition_mutableblockposition.d(k, l, i1)).getBlock();
/*     */             
/* 228 */             if (block == Blocks.IRON_BARS || block instanceof BlockBed) {
/* 229 */               if (this.random.nextFloat() < 0.3F) {
/* 230 */                 i++;
/*     */               }
/*     */               
/* 233 */               j++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 240 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float dG() {
/* 245 */     return isBaby() ? ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 2.0F) : ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEffect getSoundAmbient() {
/* 250 */     return SoundEffects.ENTITY_ZOMBIE_VILLAGER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEffect getSoundHurt(DamageSource damagesource) {
/* 255 */     return SoundEffects.ENTITY_ZOMBIE_VILLAGER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEffect getSoundDeath() {
/* 260 */     return SoundEffects.ENTITY_ZOMBIE_VILLAGER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEffect getSoundStep() {
/* 265 */     return SoundEffects.ENTITY_ZOMBIE_VILLAGER_STEP;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack eM() {
/* 270 */     return ItemStack.b;
/*     */   }
/*     */   
/*     */   public void setOffers(NBTTagCompound nbttagcompound) {
/* 274 */     this.bq = nbttagcompound;
/*     */   }
/*     */   
/*     */   public void a(NBTBase nbtbase) {
/* 278 */     this.bp = nbtbase;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 284 */     setVillagerData(getVillagerData().withType(VillagerType.a(worldaccess.i(getChunkCoordinates()))));
/* 285 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   public void setVillagerData(VillagerData villagerdata) {
/* 289 */     VillagerData villagerdata1 = getVillagerData();
/*     */     
/* 291 */     if (villagerdata1.getProfession() != villagerdata.getProfession()) {
/* 292 */       this.bq = null;
/*     */     }
/*     */     
/* 295 */     this.datawatcher.set(c, villagerdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public VillagerData getVillagerData() {
/* 300 */     return this.datawatcher.<VillagerData>get(c);
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 304 */     this.br = i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */