/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import com.destroystokyo.paper.antixray.ChunkPacketBlockControllerAntiXray;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.spigotmc.SpigotWorldConfig;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PaperWorldConfig
/*     */ {
/*     */   private final String worldName;
/*     */   private final SpigotWorldConfig spigotConfig;
/*     */   private final YamlConfiguration config;
/*     */   private boolean verbose;
/*     */   public int cactusMaxHeight;
/*     */   public int reedMaxHeight;
/*     */   public double babyZombieMovementModifier;
/*     */   public int fishingMinTicks;
/*     */   public int fishingMaxTicks;
/*     */   public boolean nerfedMobsShouldJump;
/*     */   public int softDespawnDistance;
/*     */   public int hardDespawnDistance;
/*     */   public boolean keepSpawnInMemory;
/*     */   public int fallingBlockHeightNerf;
/*     */   public int entityTNTHeightNerf;
/*     */   public int netherVoidTopDamageHeight;
/*     */   public boolean disableEndCredits;
/*     */   public boolean optimizeExplosions;
/*     */   public boolean disableExplosionKnockback;
/*     */   public boolean disableThunder;
/*     */   public boolean disableIceAndSnow;
/*     */   public int mobSpawnerTickRate;
/*     */   public int containerUpdateTickRate;
/*     */   public boolean disableChestCatDetection;
/*     */   public boolean disablePlayerCrits;
/*     */   public boolean allChunksAreSlimeChunks;
/*     */   public int portalSearchRadius;
/*     */   public int portalCreateRadius;
/*     */   public boolean disableTeleportationSuffocationCheck;
/*     */   public boolean nonPlayerEntitiesOnScoreboards;
/*     */   public int nonPlayerArrowDespawnRate;
/*     */   public int creativeArrowDespawnRate;
/*     */   public double skeleHorseSpawnChance;
/*     */   public int fixedInhabitedTime;
/*     */   public int grassUpdateRate;
/*     */   public boolean useVanillaScoreboardColoring;
/*     */   public boolean frostedIceEnabled;
/*     */   public int frostedIceDelayMin;
/*     */   public int frostedIceDelayMax;
/*     */   public boolean autoReplenishLootables;
/*     */   public boolean restrictPlayerReloot;
/*     */   public boolean changeLootTableSeedOnFill;
/*     */   public int maxLootableRefills;
/*     */   public int lootableRegenMin;
/*     */   public int lootableRegenMax;
/*     */   public boolean preventTntFromMovingInWater;
/*     */   public boolean removeCorruptTEs;
/*     */   public boolean filterNBTFromSpawnEgg;
/*     */   public boolean enableTreasureMaps;
/*     */   public boolean treasureMapsAlreadyDiscovered;
/*     */   public int maxCollisionsPerEntity;
/*     */   public boolean parrotsHangOnBetter;
/*     */   public boolean disableCreeperLingeringEffect;
/*     */   public int expMergeMaxValue;
/*     */   public double squidMaxSpawnHeight;
/*     */   public boolean disableSprintInterruptionOnAttack;
/*     */   public boolean disableEnderpearlExploit;
/*     */   public int shieldBlockingDelay;
/*     */   public boolean scanForLegacyEnderDragon;
/*     */   public boolean ironGolemsCanSpawnInAir;
/*     */   public boolean armorStandEntityLookups;
/*     */   public boolean armorStandTick;
/*     */   public int waterOverLavaFlowSpeed;
/*     */   public boolean preventMovingIntoUnloadedChunks;
/*     */   public DuplicateUUIDMode duplicateUUIDMode;
/*     */   public int duplicateUUIDDeleteRange;
/*     */   public short keepLoadedRange;
/*     */   public int autoSavePeriod;
/*     */   public int maxAutoSaveChunksPerTick;
/*     */   public boolean countAllMobsForSpawning;
/*     */   public boolean antiXray;
/*     */   public ChunkPacketBlockControllerAntiXray.EngineMode engineMode;
/*     */   public int maxChunkSectionIndex;
/*     */   public int updateRadius;
/*     */   public List<String> hiddenBlocks;
/*     */   public List<String> replacementBlocks;
/*     */   public boolean disableRelativeProjectileVelocity;
/*     */   public boolean altItemDespawnRateEnabled;
/*     */   public Map<Material, Integer> altItemDespawnRateMap;
/*     */   public boolean perPlayerMobSpawns;
/*     */   public boolean generateFlatBedrock;
/*     */   public boolean disablePillagerPatrols;
/*     */   public double patrolSpawnChance;
/*     */   public boolean patrolPerPlayerDelay;
/*     */   public int patrolDelay;
/*     */   public boolean patrolPerPlayerStart;
/*     */   public int patrolStartDay;
/*     */   public boolean entitiesTargetWithFollowRange;
/*     */   public boolean cooldownHopperWhenFull;
/*     */   public boolean disableHopperMoveEvents;
/*     */   public boolean nerfNetherPortalPigmen;
/*     */   public double zombieVillagerInfectionChance;
/*     */   public int lightQueueSize;
/*     */   public boolean phantomIgnoreCreative;
/*     */   public boolean phantomOnlyAttackInsomniacs;
/*     */   public int noTickViewDistance;
/*     */   public long delayChunkUnloadsBy;
/*     */   public double sqrMaxThunderDistance;
/*     */   public double sqrMaxLightningImpactSoundDistance;
/*     */   public double maxLightningFlashDistance;
/*     */   public boolean zombiesTargetTurtleEggs;
/*     */   public boolean useEigencraftRedstone;
/*     */   
/*     */   public PaperWorldConfig(String worldName, SpigotWorldConfig spigotConfig) {
/* 219 */     this.nonPlayerEntitiesOnScoreboards = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     this.nonPlayerArrowDespawnRate = -1;
/* 225 */     this.creativeArrowDespawnRate = -1;
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
/* 257 */     this.grassUpdateRate = 1;
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
/* 268 */     this.frostedIceEnabled = true;
/* 269 */     this.frostedIceDelayMin = 20;
/* 270 */     this.frostedIceDelayMax = 40;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 310 */     this.removeCorruptTEs = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 315 */     this.filterNBTFromSpawnEgg = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 323 */     this.enableTreasureMaps = true;
/* 324 */     this.treasureMapsAlreadyDiscovered = false;
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
/* 367 */     this.disableEnderpearlExploit = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 373 */     this.shieldBlockingDelay = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 378 */     this.scanForLegacyEnderDragon = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 383 */     this.ironGolemsCanSpawnInAir = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     this.armorStandEntityLookups = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 393 */     this.armorStandTick = true;
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
/* 405 */     this.preventMovingIntoUnloadedChunks = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 413 */     this.duplicateUUIDMode = DuplicateUUIDMode.SAFE_REGEN;
/* 414 */     this.duplicateUUIDDeleteRange = 32;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 455 */     this.autoSavePeriod = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 465 */     this.maxAutoSaveChunksPerTick = 24;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 470 */     this.countAllMobsForSpawning = false;
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
/* 560 */     this.perPlayerMobSpawns = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 570 */     this.disablePillagerPatrols = false;
/* 571 */     this.patrolSpawnChance = 0.2D;
/* 572 */     this.patrolPerPlayerDelay = false;
/* 573 */     this.patrolDelay = 12000;
/* 574 */     this.patrolPerPlayerStart = false;
/* 575 */     this.patrolStartDay = 5;
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
/* 586 */     this.entitiesTargetWithFollowRange = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 591 */     this.cooldownHopperWhenFull = true;
/* 592 */     this.disableHopperMoveEvents = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 600 */     this.nerfNetherPortalPigmen = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 605 */     this.zombieVillagerInfectionChance = -1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 610 */     this.lightQueueSize = 20;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 615 */     this.phantomIgnoreCreative = true;
/* 616 */     this.phantomOnlyAttackInsomniacs = true;
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
/* 658 */     this.zombiesTargetTurtleEggs = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 663 */     this.useEigencraftRedstone = false; this.worldName = worldName; this.spigotConfig = spigotConfig;
/*     */     this.config = PaperConfig.config;
/* 665 */     init(); } private void useEigencraftRedstone() { this.useEigencraftRedstone = getBoolean("use-faster-eigencraft-redstone", false);
/* 666 */     if (this.useEigencraftRedstone) {
/* 667 */       PaperConfig.log("Using Eigencraft redstone algorithm by theosib.");
/*     */     } else {
/* 669 */       PaperConfig.log("Using vanilla redstone algorithm.");
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void init() {
/*     */     PaperConfig.log("-------- World Settings For [" + this.worldName + "] --------");
/*     */     PaperConfig.readConfig(PaperWorldConfig.class, this);
/*     */   }
/*     */   
/*     */   private void set(String path, Object val) {
/*     */     this.config.set("world-settings.default." + path, val);
/*     */     if (this.config.get("world-settings." + this.worldName + "." + path) != null)
/*     */       this.config.set("world-settings." + this.worldName + "." + path, val); 
/*     */   }
/*     */   
/*     */   private boolean getBoolean(String path, boolean def) {
/*     */     this.config.addDefault("world-settings.default." + path, Boolean.valueOf(def));
/*     */     return this.config.getBoolean("world-settings." + this.worldName + "." + path, this.config.getBoolean("world-settings.default." + path));
/*     */   }
/*     */   
/*     */   private double getDouble(String path, double def) {
/*     */     this.config.addDefault("world-settings.default." + path, Double.valueOf(def));
/*     */     return this.config.getDouble("world-settings." + this.worldName + "." + path, this.config.getDouble("world-settings.default." + path));
/*     */   }
/*     */   
/*     */   private int getInt(String path, int def) {
/*     */     this.config.addDefault("world-settings.default." + path, Integer.valueOf(def));
/*     */     return this.config.getInt("world-settings." + this.worldName + "." + path, this.config.getInt("world-settings.default." + path));
/*     */   }
/*     */   
/*     */   private float getFloat(String path, float def) {
/*     */     return (float)getDouble(path, def);
/*     */   }
/*     */   
/*     */   private <T> List<T> getList(String path, List<T> def) {
/*     */     this.config.addDefault("world-settings.default." + path, def);
/*     */     return this.config.getList("world-settings." + this.worldName + "." + path, this.config.getList("world-settings.default." + path));
/*     */   }
/*     */   
/*     */   private String getString(String path, String def) {
/*     */     this.config.addDefault("world-settings.default." + path, def);
/*     */     return this.config.getString("world-settings." + this.worldName + "." + path, this.config.getString("world-settings.default." + path));
/*     */   }
/*     */   
/*     */   private void blockGrowthHeight() {
/*     */     this.cactusMaxHeight = getInt("max-growth-height.cactus", 3);
/*     */     this.reedMaxHeight = getInt("max-growth-height.reeds", 3);
/*     */     PaperConfig.log("Max height for cactus growth " + this.cactusMaxHeight + ". Max height for reed growth " + this.reedMaxHeight);
/*     */   }
/*     */   
/*     */   private void babyZombieMovementModifier() {
/*     */     this.babyZombieMovementModifier = getDouble("baby-zombie-movement-modifier", 0.5D);
/*     */     if (PaperConfig.version < 20) {
/*     */       this.babyZombieMovementModifier = getDouble("baby-zombie-movement-speed", 0.5D);
/*     */       set("baby-zombie-movement-modifier", Double.valueOf(this.babyZombieMovementModifier));
/*     */     } 
/*     */     PaperConfig.log("Baby zombies will move at the speed of " + this.babyZombieMovementModifier);
/*     */   }
/*     */   
/*     */   private void fishingTickRange() {
/*     */     this.fishingMinTicks = getInt("fishing-time-range.MinimumTicks", 100);
/*     */     this.fishingMaxTicks = getInt("fishing-time-range.MaximumTicks", 600);
/*     */     PaperConfig.log("Fishing time ranges are between " + this.fishingMinTicks + " and " + this.fishingMaxTicks + " ticks");
/*     */   }
/*     */   
/*     */   private void nerfedMobsShouldJump() {
/*     */     this.nerfedMobsShouldJump = getBoolean("spawner-nerfed-mobs-should-jump", false);
/*     */   }
/*     */   
/*     */   private void despawnDistances() {
/*     */     this.softDespawnDistance = getInt("despawn-ranges.soft", 32);
/*     */     this.hardDespawnDistance = getInt("despawn-ranges.hard", 128);
/*     */     if (this.softDespawnDistance > this.hardDespawnDistance)
/*     */       this.softDespawnDistance = this.hardDespawnDistance; 
/*     */     PaperConfig.log("Living Entity Despawn Ranges:  Soft: " + this.softDespawnDistance + " Hard: " + this.hardDespawnDistance);
/*     */     this.softDespawnDistance *= this.softDespawnDistance;
/*     */     this.hardDespawnDistance *= this.hardDespawnDistance;
/*     */   }
/*     */   
/*     */   private void keepSpawnInMemory() {
/*     */     this.keepSpawnInMemory = getBoolean("keep-spawn-loaded", true);
/*     */     PaperConfig.log("Keep spawn chunk loaded: " + this.keepSpawnInMemory);
/*     */   }
/*     */   
/*     */   private void heightNerfs() {
/*     */     this.fallingBlockHeightNerf = getInt("falling-block-height-nerf", 0);
/*     */     this.entityTNTHeightNerf = getInt("tnt-entity-height-nerf", 0);
/*     */     if (this.fallingBlockHeightNerf != 0)
/*     */       PaperConfig.log("Falling Block Height Limit set to Y: " + this.fallingBlockHeightNerf); 
/*     */     if (this.entityTNTHeightNerf != 0)
/*     */       PaperConfig.log("TNT Entity Height Limit set to Y: " + this.entityTNTHeightNerf); 
/*     */   }
/*     */   
/*     */   public boolean doNetherTopVoidDamage() {
/*     */     return (this.netherVoidTopDamageHeight > 0);
/*     */   }
/*     */   
/*     */   private void netherVoidTopDamageHeight() {
/*     */     this.netherVoidTopDamageHeight = getInt("nether-ceiling-void-damage-height", 0);
/*     */     PaperConfig.log("Top of the nether void damage height: " + this.netherVoidTopDamageHeight);
/*     */     if (PaperConfig.version < 18) {
/*     */       boolean legacy = getBoolean("nether-ceiling-void-damage", false);
/*     */       if (legacy) {
/*     */         this.netherVoidTopDamageHeight = 128;
/*     */         set("nether-ceiling-void-damage-height", Integer.valueOf(this.netherVoidTopDamageHeight));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void disableEndCredits() {
/*     */     this.disableEndCredits = getBoolean("game-mechanics.disable-end-credits", false);
/*     */     PaperConfig.log("End credits disabled: " + this.disableEndCredits);
/*     */   }
/*     */   
/*     */   private void optimizeExplosions() {
/*     */     this.optimizeExplosions = getBoolean("optimize-explosions", false);
/*     */     PaperConfig.log("Optimize explosions: " + this.optimizeExplosions);
/*     */   }
/*     */   
/*     */   private void disableExplosionKnockback() {
/*     */     this.disableExplosionKnockback = getBoolean("disable-explosion-knockback", false);
/*     */   }
/*     */   
/*     */   private void disableThunder() {
/*     */     this.disableThunder = getBoolean("disable-thunder", false);
/*     */   }
/*     */   
/*     */   private void disableIceAndSnow() {
/*     */     this.disableIceAndSnow = getBoolean("disable-ice-and-snow", false);
/*     */   }
/*     */   
/*     */   private void mobSpawnerTickRate() {
/*     */     this.mobSpawnerTickRate = getInt("mob-spawner-tick-rate", 1);
/*     */   }
/*     */   
/*     */   private void containerUpdateTickRate() {
/*     */     this.containerUpdateTickRate = getInt("container-update-tick-rate", 1);
/*     */   }
/*     */   
/*     */   private void disableChestCatDetection() {
/*     */     this.disableChestCatDetection = getBoolean("game-mechanics.disable-chest-cat-detection", false);
/*     */   }
/*     */   
/*     */   private void disablePlayerCrits() {
/*     */     this.disablePlayerCrits = getBoolean("game-mechanics.disable-player-crits", false);
/*     */   }
/*     */   
/*     */   private void allChunksAreSlimeChunks() {
/*     */     this.allChunksAreSlimeChunks = getBoolean("all-chunks-are-slime-chunks", false);
/*     */   }
/*     */   
/*     */   private void portalSearchRadius() {
/*     */     this.portalSearchRadius = getInt("portal-search-radius", 128);
/*     */     this.portalCreateRadius = getInt("portal-create-radius", 16);
/*     */   }
/*     */   
/*     */   private void disableTeleportationSuffocationCheck() {
/*     */     this.disableTeleportationSuffocationCheck = getBoolean("disable-teleportation-suffocation-check", false);
/*     */   }
/*     */   
/*     */   private void nonPlayerEntitiesOnScoreboards() {
/*     */     this.nonPlayerEntitiesOnScoreboards = getBoolean("allow-non-player-entities-on-scoreboards", false);
/*     */   }
/*     */   
/*     */   private void nonPlayerArrowDespawnRate() {
/*     */     this.nonPlayerArrowDespawnRate = getInt("non-player-arrow-despawn-rate", -1);
/*     */     if (this.nonPlayerArrowDespawnRate == -1)
/*     */       this.nonPlayerArrowDespawnRate = this.spigotConfig.arrowDespawnRate; 
/*     */     this.creativeArrowDespawnRate = getInt("creative-arrow-despawn-rate", -1);
/*     */     if (this.creativeArrowDespawnRate == -1)
/*     */       this.creativeArrowDespawnRate = this.spigotConfig.arrowDespawnRate; 
/*     */     PaperConfig.log("Non Player Arrow Despawn Rate: " + this.nonPlayerArrowDespawnRate);
/*     */     PaperConfig.log("Creative Arrow Despawn Rate: " + this.creativeArrowDespawnRate);
/*     */   }
/*     */   
/*     */   private void skeleHorseSpawnChance() {
/*     */     this.skeleHorseSpawnChance = getDouble("skeleton-horse-thunder-spawn-chance", 0.01D);
/*     */     if (this.skeleHorseSpawnChance < 0.0D)
/*     */       this.skeleHorseSpawnChance = 0.01D; 
/*     */   }
/*     */   
/*     */   private void fixedInhabitedTime() {
/*     */     if (PaperConfig.version < 16) {
/*     */       if (!this.config.getBoolean("world-settings.default.use-chunk-inhabited-timer", true))
/*     */         this.config.set("world-settings.default.fixed-chunk-inhabited-time", Integer.valueOf(0)); 
/*     */       if (!this.config.getBoolean("world-settings." + this.worldName + ".use-chunk-inhabited-timer", true))
/*     */         this.config.set("world-settings." + this.worldName + ".fixed-chunk-inhabited-time", Integer.valueOf(0)); 
/*     */       set("use-chunk-inhabited-timer", null);
/*     */     } 
/*     */     this.fixedInhabitedTime = getInt("fixed-chunk-inhabited-time", -1);
/*     */   }
/*     */   
/*     */   private void grassUpdateRate() {
/*     */     this.grassUpdateRate = Math.max(0, getInt("grass-spread-tick-rate", this.grassUpdateRate));
/*     */     PaperConfig.log("Grass Spread Tick Rate: " + this.grassUpdateRate);
/*     */   }
/*     */   
/*     */   private void useVanillaScoreboardColoring() {
/*     */     this.useVanillaScoreboardColoring = getBoolean("use-vanilla-world-scoreboard-name-coloring", false);
/*     */   }
/*     */   
/*     */   private void frostedIce() {
/*     */     this.frostedIceEnabled = getBoolean("frosted-ice.enabled", this.frostedIceEnabled);
/*     */     this.frostedIceDelayMin = getInt("frosted-ice.delay.min", this.frostedIceDelayMin);
/*     */     this.frostedIceDelayMax = getInt("frosted-ice.delay.max", this.frostedIceDelayMax);
/*     */     PaperConfig.log("Frosted Ice: " + (this.frostedIceEnabled ? "enabled" : "disabled") + " / delay: min=" + this.frostedIceDelayMin + ", max=" + this.frostedIceDelayMax);
/*     */   }
/*     */   
/*     */   private void enhancedLootables() {
/*     */     this.autoReplenishLootables = getBoolean("lootables.auto-replenish", false);
/*     */     this.restrictPlayerReloot = getBoolean("lootables.restrict-player-reloot", true);
/*     */     this.changeLootTableSeedOnFill = getBoolean("lootables.reset-seed-on-fill", true);
/*     */     this.maxLootableRefills = getInt("lootables.max-refills", -1);
/*     */     this.lootableRegenMin = PaperConfig.getSeconds(getString("lootables.refresh-min", "12h"));
/*     */     this.lootableRegenMax = PaperConfig.getSeconds(getString("lootables.refresh-max", "2d"));
/*     */     if (this.autoReplenishLootables)
/*     */       PaperConfig.log("Lootables: Replenishing every " + PaperConfig.timeSummary(this.lootableRegenMin) + " to " + PaperConfig.timeSummary(this.lootableRegenMax) + (this.restrictPlayerReloot ? " (restricting reloot)" : "")); 
/*     */   }
/*     */   
/*     */   private void preventTntFromMovingInWater() {
/*     */     if (PaperConfig.version < 13) {
/*     */       boolean oldVal = getBoolean("enable-old-tnt-cannon-behaviors", false);
/*     */       set("prevent-tnt-from-moving-in-water", Boolean.valueOf(oldVal));
/*     */     } 
/*     */     this.preventTntFromMovingInWater = getBoolean("prevent-tnt-from-moving-in-water", false);
/*     */     PaperConfig.log("Prevent TNT from moving in water: " + this.preventTntFromMovingInWater);
/*     */   }
/*     */   
/*     */   private void removeCorruptTEs() {
/*     */     this.removeCorruptTEs = getBoolean("remove-corrupt-tile-entities", false);
/*     */   }
/*     */   
/*     */   private void fitlerNBTFromSpawnEgg() {
/*     */     this.filterNBTFromSpawnEgg = getBoolean("filter-nbt-data-from-spawn-eggs-and-related", true);
/*     */     if (!this.filterNBTFromSpawnEgg)
/*     */       Bukkit.getLogger().warning("Spawn Egg and Armor Stand NBT filtering disabled, this is a potential security risk"); 
/*     */   }
/*     */   
/*     */   private void treasureMapsAlreadyDiscovered() {
/*     */     this.enableTreasureMaps = getBoolean("enable-treasure-maps", true);
/*     */     this.treasureMapsAlreadyDiscovered = getBoolean("treasure-maps-return-already-discovered", false);
/*     */     if (this.treasureMapsAlreadyDiscovered)
/*     */       PaperConfig.log("Treasure Maps will return already discovered locations"); 
/*     */   }
/*     */   
/*     */   private void maxEntityCollision() {
/*     */     this.maxCollisionsPerEntity = getInt("max-entity-collisions", this.spigotConfig.getInt("max-entity-collisions", 8));
/*     */     PaperConfig.log("Max Entity Collisions: " + this.maxCollisionsPerEntity);
/*     */   }
/*     */   
/*     */   private void parrotsHangOnBetter() {
/*     */     this.parrotsHangOnBetter = getBoolean("parrots-are-unaffected-by-player-movement", false);
/*     */     PaperConfig.log("Parrots are unaffected by player movement: " + this.parrotsHangOnBetter);
/*     */   }
/*     */   
/*     */   private void setDisableCreeperLingeringEffect() {
/*     */     this.disableCreeperLingeringEffect = getBoolean("disable-creeper-lingering-effect", false);
/*     */     PaperConfig.log("Creeper lingering effect: " + this.disableCreeperLingeringEffect);
/*     */   }
/*     */   
/*     */   private void expMergeMaxValue() {
/*     */     this.expMergeMaxValue = getInt("experience-merge-max-value", -1);
/*     */     PaperConfig.log("Experience Merge Max Value: " + this.expMergeMaxValue);
/*     */   }
/*     */   
/*     */   private void squidMaxSpawnHeight() {
/*     */     this.squidMaxSpawnHeight = getDouble("squid-spawn-height.maximum", 0.0D);
/*     */   }
/*     */   
/*     */   private void disableSprintInterruptionOnAttack() {
/*     */     this.disableSprintInterruptionOnAttack = getBoolean("game-mechanics.disable-sprint-interruption-on-attack", false);
/*     */   }
/*     */   
/*     */   private void disableEnderpearlExploit() {
/*     */     this.disableEnderpearlExploit = getBoolean("game-mechanics.disable-unloaded-chunk-enderpearl-exploit", this.disableEnderpearlExploit);
/*     */     PaperConfig.log("Disable Unloaded Chunk Enderpearl Exploit: " + (this.disableEnderpearlExploit ? "enabled" : "disabled"));
/*     */   }
/*     */   
/*     */   private void shieldBlockingDelay() {
/*     */     this.shieldBlockingDelay = getInt("game-mechanics.shield-blocking-delay", 5);
/*     */   }
/*     */   
/*     */   private void scanForLegacyEnderDragon() {
/*     */     this.scanForLegacyEnderDragon = getBoolean("game-mechanics.scan-for-legacy-ender-dragon", true);
/*     */   }
/*     */   
/*     */   private void ironGolemsCanSpawnInAir() {
/*     */     this.ironGolemsCanSpawnInAir = getBoolean("iron-golems-can-spawn-in-air", this.ironGolemsCanSpawnInAir);
/*     */   }
/*     */   
/*     */   private void armorStandEntityLookups() {
/*     */     this.armorStandEntityLookups = getBoolean("armor-stands-do-collision-entity-lookups", true);
/*     */   }
/*     */   
/*     */   private void armorStandTick() {
/*     */     this.armorStandTick = getBoolean("armor-stands-tick", this.armorStandTick);
/*     */     PaperConfig.log("ArmorStand ticking is " + (this.armorStandTick ? "enabled" : "disabled") + " by default");
/*     */   }
/*     */   
/*     */   private void waterOverLavaFlowSpeed() {
/*     */     this.waterOverLavaFlowSpeed = getInt("water-over-lava-flow-speed", 5);
/*     */     PaperConfig.log("Water over lava flow speed: " + this.waterOverLavaFlowSpeed);
/*     */   }
/*     */   
/*     */   private void preventMovingIntoUnloadedChunks() {
/*     */     this.preventMovingIntoUnloadedChunks = getBoolean("prevent-moving-into-unloaded-chunks", false);
/*     */   }
/*     */   
/*     */   public enum DuplicateUUIDMode {
/*     */     SAFE_REGEN, DELETE, NOTHING, WARN;
/*     */   }
/*     */   
/*     */   private void repairDuplicateUUID() {
/*     */     String desiredMode = getString("duplicate-uuid-resolver", "saferegen").toLowerCase().trim();
/*     */     this.duplicateUUIDDeleteRange = getInt("duplicate-uuid-saferegen-delete-range", this.duplicateUUIDDeleteRange);
/*     */     switch (desiredMode.toLowerCase()) {
/*     */       case "regen":
/*     */       case "regenerate":
/*     */       case "saferegen":
/*     */       case "saferegenerate":
/*     */         this.duplicateUUIDMode = DuplicateUUIDMode.SAFE_REGEN;
/*     */         PaperConfig.log("Duplicate UUID Resolve: Regenerate New UUID if distant (Delete likely duplicates within " + this.duplicateUUIDDeleteRange + " blocks)");
/*     */         return;
/*     */       case "remove":
/*     */       case "delete":
/*     */         this.duplicateUUIDMode = DuplicateUUIDMode.DELETE;
/*     */         PaperConfig.log("Duplicate UUID Resolve: Delete Entity");
/*     */         return;
/*     */       case "silent":
/*     */       case "nothing":
/*     */         this.duplicateUUIDMode = DuplicateUUIDMode.NOTHING;
/*     */         PaperConfig.logError("Duplicate UUID Resolve: Do Nothing (no logs) - Warning, may lose indication of bad things happening");
/*     */         return;
/*     */       case "log":
/*     */       case "warn":
/*     */         this.duplicateUUIDMode = DuplicateUUIDMode.WARN;
/*     */         PaperConfig.log("Duplicate UUID Resolve: Warn (do nothing but log it happened, may be spammy)");
/*     */         return;
/*     */     } 
/*     */     this.duplicateUUIDMode = DuplicateUUIDMode.WARN;
/*     */     PaperConfig.logError("Warning: Invalid duplicate-uuid-resolver config " + desiredMode + " - must be one of: regen, delete, nothing, warn");
/*     */     PaperConfig.log("Duplicate UUID Resolve: Warn (do nothing but log it happened, may be spammy)");
/*     */   }
/*     */   
/*     */   private void keepLoadedRange() {
/*     */     this.keepLoadedRange = (short)(getInt("keep-spawn-loaded-range", Math.min(this.spigotConfig.viewDistance, 10)) * 16);
/*     */     PaperConfig.log("Keep Spawn Loaded Range: " + (this.keepLoadedRange / 16));
/*     */   }
/*     */   
/*     */   private void autoSavePeriod() {
/*     */     this.autoSavePeriod = getInt("auto-save-interval", -1);
/*     */     if (this.autoSavePeriod > 0) {
/*     */       PaperConfig.log("Auto Save Interval: " + this.autoSavePeriod + " (" + (this.autoSavePeriod / 20) + "s)");
/*     */     } else if (this.autoSavePeriod < 0) {
/*     */       this.autoSavePeriod = (MinecraftServer.getServer()).autosavePeriod;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void maxAutoSaveChunksPerTick() {
/*     */     this.maxAutoSaveChunksPerTick = getInt("max-auto-save-chunks-per-tick", 24);
/*     */   }
/*     */   
/*     */   private void countAllMobsForSpawning() {
/*     */     this.countAllMobsForSpawning = getBoolean("count-all-mobs-for-spawning", false);
/*     */     if (this.countAllMobsForSpawning) {
/*     */       PaperConfig.log("Counting all mobs for spawning. Mob farms may reduce natural spawns elsewhere in world.");
/*     */     } else {
/*     */       PaperConfig.log("Using improved mob spawn limits (Only Natural Spawns impact spawn limits for more natural spawns)");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void antiXray() {
/*     */     this.antiXray = getBoolean("anti-xray.enabled", false);
/*     */     this.engineMode = ChunkPacketBlockControllerAntiXray.EngineMode.getById(getInt("anti-xray.engine-mode", ChunkPacketBlockControllerAntiXray.EngineMode.HIDE.getId()));
/*     */     this.engineMode = (this.engineMode == null) ? ChunkPacketBlockControllerAntiXray.EngineMode.HIDE : this.engineMode;
/*     */     this.maxChunkSectionIndex = getInt("anti-xray.max-chunk-section-index", 3);
/*     */     this.maxChunkSectionIndex = (this.maxChunkSectionIndex > 15) ? 15 : this.maxChunkSectionIndex;
/*     */     this.updateRadius = getInt("anti-xray.update-radius", 2);
/*     */     this.hiddenBlocks = getList("anti-xray.hidden-blocks", Arrays.asList(new String[] { 
/*     */             "gold_ore", "iron_ore", "coal_ore", "lapis_ore", "mossy_cobblestone", "obsidian", "chest", "diamond_ore", "redstone_ore", "clay", 
/*     */             "emerald_ore", "ender_chest" }));
/*     */     this.replacementBlocks = getList("anti-xray.replacement-blocks", Arrays.asList(new String[] { "stone", "oak_planks" }));
/*     */     if (PaperConfig.version < 19) {
/*     */       this.hiddenBlocks.remove("lit_redstone_ore");
/*     */       int index = this.replacementBlocks.indexOf("planks");
/*     */       if (index != -1)
/*     */         this.replacementBlocks.set(index, "oak_planks"); 
/*     */       set("anti-xray.hidden-blocks", this.hiddenBlocks);
/*     */       set("anti-xray.replacement-blocks", this.replacementBlocks);
/*     */     } 
/*     */     PaperConfig.log("Anti-Xray: " + (this.antiXray ? "enabled" : "disabled") + " / Engine Mode: " + this.engineMode.getDescription() + " / Up to " + ((this.maxChunkSectionIndex + 1) * 16) + " blocks / Update Radius: " + this.updateRadius);
/*     */   }
/*     */   
/*     */   private void disableRelativeProjectileVelocity() {
/*     */     this.disableRelativeProjectileVelocity = getBoolean("game-mechanics.disable-relative-projectile-velocity", false);
/*     */   }
/*     */   
/*     */   private void altItemDespawnRate() {
/*     */     String path = "alt-item-despawn-rate";
/*     */     this.altItemDespawnRateEnabled = getBoolean(path + ".enabled", false);
/*     */     Map<Material, Integer> altItemDespawnRateMapDefault = new EnumMap<>(Material.class);
/*     */     altItemDespawnRateMapDefault.put(Material.COBBLESTONE, Integer.valueOf(300));
/*     */     for (Material key : altItemDespawnRateMapDefault.keySet())
/*     */       this.config.addDefault("world-settings.default." + path + ".items." + key, altItemDespawnRateMapDefault.get(key)); 
/*     */     Map<String, Integer> rawMap = new HashMap<>();
/*     */     try {
/*     */       ConfigurationSection mapSection = this.config.getConfigurationSection("world-settings." + this.worldName + "." + path + ".items");
/*     */       if (mapSection == null)
/*     */         mapSection = this.config.getConfigurationSection("world-settings.default." + path + ".items"); 
/*     */       for (String key : mapSection.getKeys(false)) {
/*     */         int val = mapSection.getInt(key);
/*     */         rawMap.put(key, Integer.valueOf(val));
/*     */       } 
/*     */     } catch (Exception e) {
/*     */       PaperConfig.logError("alt-item-despawn-rate was malformatted");
/*     */       this.altItemDespawnRateEnabled = false;
/*     */     } 
/*     */     this.altItemDespawnRateMap = new EnumMap<>(Material.class);
/*     */     if (!this.altItemDespawnRateEnabled)
/*     */       return; 
/*     */     for (String key : rawMap.keySet()) {
/*     */       try {
/*     */         this.altItemDespawnRateMap.put(Material.valueOf(key), rawMap.get(key));
/*     */       } catch (Exception e) {
/*     */         PaperConfig.logError("Could not add item " + key + " to altItemDespawnRateMap: " + e.getMessage());
/*     */       } 
/*     */     } 
/*     */     if (this.altItemDespawnRateEnabled)
/*     */       for (Material key : this.altItemDespawnRateMap.keySet())
/*     */         PaperConfig.log("Alternative item despawn rate of " + key + ": " + this.altItemDespawnRateMap.get(key));  
/*     */   }
/*     */   
/*     */   private void perPlayerMobSpawns() {
/*     */     this.perPlayerMobSpawns = getBoolean("per-player-mob-spawns", false);
/*     */   }
/*     */   
/*     */   private void generatorSettings() {
/*     */     this.generateFlatBedrock = getBoolean("generator-settings.flat-bedrock", false);
/*     */   }
/*     */   
/*     */   private void pillagerSettings() {
/*     */     this.disablePillagerPatrols = getBoolean("game-mechanics.disable-pillager-patrols", this.disablePillagerPatrols);
/*     */     this.patrolSpawnChance = getDouble("game-mechanics.pillager-patrols.spawn-chance", this.patrolSpawnChance);
/*     */     this.patrolPerPlayerDelay = getBoolean("game-mechanics.pillager-patrols.spawn-delay.per-player", this.patrolPerPlayerDelay);
/*     */     this.patrolDelay = getInt("game-mechanics.pillager-patrols.spawn-delay.ticks", this.patrolDelay);
/*     */     this.patrolPerPlayerStart = getBoolean("game-mechanics.pillager-patrols.start.per-player", this.patrolPerPlayerStart);
/*     */     this.patrolStartDay = getInt("game-mechanics.pillager-patrols.start.day", this.patrolStartDay);
/*     */   }
/*     */   
/*     */   private void entitiesTargetWithFollowRange() {
/*     */     this.entitiesTargetWithFollowRange = getBoolean("entities-target-with-follow-range", this.entitiesTargetWithFollowRange);
/*     */   }
/*     */   
/*     */   private void hopperOptimizations() {
/*     */     this.cooldownHopperWhenFull = getBoolean("hopper.cooldown-when-full", this.cooldownHopperWhenFull);
/*     */     PaperConfig.log("Cooldown Hoppers when Full: " + (this.cooldownHopperWhenFull ? "enabled" : "disabled"));
/*     */     this.disableHopperMoveEvents = getBoolean("hopper.disable-move-event", this.disableHopperMoveEvents);
/*     */     PaperConfig.log("Hopper Move Item Events: " + (this.disableHopperMoveEvents ? "disabled" : "enabled"));
/*     */   }
/*     */   
/*     */   private void nerfNetherPortalPigmen() {
/*     */     this.nerfNetherPortalPigmen = getBoolean("game-mechanics.nerf-pigmen-from-nether-portals", this.nerfNetherPortalPigmen);
/*     */   }
/*     */   
/*     */   private void zombieVillagerInfectionChance() {
/*     */     this.zombieVillagerInfectionChance = getDouble("zombie-villager-infection-chance", this.zombieVillagerInfectionChance);
/*     */   }
/*     */   
/*     */   private void lightQueueSize() {
/*     */     this.lightQueueSize = getInt("light-queue-size", this.lightQueueSize);
/*     */   }
/*     */   
/*     */   private void phantomSettings() {
/*     */     this.phantomIgnoreCreative = getBoolean("phantoms-do-not-spawn-on-creative-players", this.phantomIgnoreCreative);
/*     */     this.phantomOnlyAttackInsomniacs = getBoolean("phantoms-only-attack-insomniacs", this.phantomOnlyAttackInsomniacs);
/*     */   }
/*     */   
/*     */   private void viewDistance() {
/*     */     this.noTickViewDistance = getInt("viewdistances.no-tick-view-distance", -1);
/*     */   }
/*     */   
/*     */   private void delayChunkUnloadsBy() {
/*     */     this.delayChunkUnloadsBy = PaperConfig.getSeconds(getString("delay-chunk-unloads-by", "10s"));
/*     */     if (this.delayChunkUnloadsBy > 0L) {
/*     */       PaperConfig.log("Delaying chunk unloads by " + this.delayChunkUnloadsBy + " seconds");
/*     */       this.delayChunkUnloadsBy *= 20L;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void lightningStrikeDistanceLimit() {
/*     */     this.sqrMaxThunderDistance = getInt("lightning-strike-distance-limit.sound", -1);
/*     */     if (this.sqrMaxThunderDistance > 0.0D)
/*     */       this.sqrMaxThunderDistance *= this.sqrMaxThunderDistance; 
/*     */     this.sqrMaxLightningImpactSoundDistance = getInt("lightning-strike-distance-limit.impact-sound", -1);
/*     */     if (this.sqrMaxLightningImpactSoundDistance < 0.0D) {
/*     */       this.sqrMaxLightningImpactSoundDistance = 1024.0D;
/*     */     } else {
/*     */       this.sqrMaxLightningImpactSoundDistance *= this.sqrMaxLightningImpactSoundDistance;
/*     */     } 
/*     */     this.maxLightningFlashDistance = getInt("lightning-strike-distance-limit.flash", -1);
/*     */     if (this.maxLightningFlashDistance < 0.0D)
/*     */       this.maxLightningFlashDistance = 512.0D; 
/*     */   }
/*     */   
/*     */   private void zombiesTargetTurtleEggs() {
/*     */     this.zombiesTargetTurtleEggs = getBoolean("zombies-target-turtle-eggs", this.zombiesTargetTurtleEggs);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\PaperWorldConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */