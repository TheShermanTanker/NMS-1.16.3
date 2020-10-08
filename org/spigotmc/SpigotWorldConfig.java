/*     */ package org.spigotmc;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpigotWorldConfig
/*     */ {
/*     */   private final String worldName;
/*     */   private final YamlConfiguration config;
/*     */   private boolean verbose;
/*     */   public int cactusModifier;
/*     */   public int caneModifier;
/*     */   public int melonModifier;
/*     */   public int mushroomModifier;
/*     */   public int pumpkinModifier;
/*     */   public int saplingModifier;
/*     */   public int beetrootModifier;
/*     */   public int carrotModifier;
/*     */   public int potatoModifier;
/*     */   public int wheatModifier;
/*     */   public int wartModifier;
/*     */   public int vineModifier;
/*     */   public int cocoaModifier;
/*     */   public int bambooModifier;
/*     */   public int sweetBerryModifier;
/*     */   public int kelpModifier;
/*     */   public double itemMerge;
/*     */   public double expMerge;
/*     */   public int viewDistance;
/*     */   public byte mobSpawnRange;
/*     */   public int itemDespawnRate;
/*     */   public int animalActivationRange;
/*     */   public int monsterActivationRange;
/*     */   public int raiderActivationRange;
/*     */   public int miscActivationRange;
/*     */   public int flyingMonsterActivationRange;
/*     */   public int waterActivationRange;
/*     */   public int villagerActivationRange;
/*     */   public int wakeUpInactiveAnimals;
/*     */   public int wakeUpInactiveAnimalsEvery;
/*     */   public int wakeUpInactiveAnimalsFor;
/*     */   public int wakeUpInactiveMonsters;
/*     */   public int wakeUpInactiveMonstersEvery;
/*     */   public int wakeUpInactiveMonstersFor;
/*     */   public int wakeUpInactiveVillagers;
/*     */   public int wakeUpInactiveVillagersEvery;
/*     */   public int wakeUpInactiveVillagersFor;
/*     */   public int wakeUpInactiveFlying;
/*     */   public int wakeUpInactiveFlyingEvery;
/*     */   public int wakeUpInactiveFlyingFor;
/*     */   public int villagersWorkImmunityAfter;
/*     */   public int villagersWorkImmunityFor;
/*     */   public boolean villagersActiveForPanic;
/*     */   public boolean tickInactiveVillagers;
/*     */   public int playerTrackingRange;
/*     */   public int animalTrackingRange;
/*     */   public int monsterTrackingRange;
/*     */   public int miscTrackingRange;
/*     */   public int otherTrackingRange;
/*     */   public int hopperTransfer;
/*     */   public int hopperCheck;
/*     */   public int hopperAmount;
/*     */   public int arrowDespawnRate;
/*     */   public int tridentDespawnRate;
/*     */   public boolean zombieAggressiveTowardsVillager;
/*     */   public boolean nerfSpawnerMobs;
/*     */   public boolean enableZombiePigmenPortalSpawns;
/*     */   public int dragonDeathSoundRadius;
/*     */   public int witherSpawnSoundRadius;
/*     */   public int endPortalSoundRadius;
/*     */   public int villageSeed;
/*     */   public int desertSeed;
/*     */   public int iglooSeed;
/*     */   public int jungleSeed;
/*     */   public int swampSeed;
/*     */   public int monumentSeed;
/*     */   public int oceanSeed;
/*     */   public int outpostSeed;
/*     */   public int shipwreckSeed;
/*     */   public int slimeSeed;
/*     */   public int endCitySeed;
/*     */   public int bastionSeed;
/*     */   public int fortressSeed;
/*     */   public int mansionSeed;
/*     */   public int fossilSeed;
/*     */   public int portalSeed;
/*     */   public float jumpWalkExhaustion;
/*     */   public float jumpSprintExhaustion;
/*     */   public float combatExhaustion;
/*     */   public float regenExhaustion;
/*     */   public float swimMultiplier;
/*     */   public float sprintMultiplier;
/*     */   public float otherMultiplier;
/*     */   public int currentPrimedTnt;
/*     */   public int maxTntTicksPerTick;
/*     */   public int hangingTickFrequency;
/*     */   public int tileMaxTickTime;
/*     */   public int entityMaxTickTime;
/*     */   public double squidSpawnRangeMin;
/*     */   
/*     */   public void init() {
/*     */     this.verbose = getBoolean("verbose", true);
/*     */     log("-------- World Settings For [" + this.worldName + "] --------");
/*     */     SpigotConfig.readConfig(SpigotWorldConfig.class, this);
/*     */   }
/*     */   
/*     */   private void log(String s) {
/*     */     if (this.verbose)
/*     */       Bukkit.getLogger().info(s); 
/*     */   }
/*     */   
/*     */   private void set(String path, Object val) {
/*     */     this.config.set("world-settings.default." + path, val);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String path, boolean def) {
/*     */     this.config.addDefault("world-settings.default." + path, Boolean.valueOf(def));
/*     */     return this.config.getBoolean("world-settings." + this.worldName + "." + path, this.config.getBoolean("world-settings.default." + path));
/*     */   }
/*     */   
/*     */   public double getDouble(String path, double def) {
/*     */     this.config.addDefault("world-settings.default." + path, Double.valueOf(def));
/*     */     return this.config.getDouble("world-settings." + this.worldName + "." + path, this.config.getDouble("world-settings.default." + path));
/*     */   }
/*     */   
/*     */   public int getInt(String path) {
/*     */     return this.config.getInt("world-settings." + this.worldName + "." + path);
/*     */   }
/*     */   
/*     */   public int getInt(String path, int def) {
/*     */     this.config.addDefault("world-settings.default." + path, Integer.valueOf(def));
/*     */     return this.config.getInt("world-settings." + this.worldName + "." + path, this.config.getInt("world-settings.default." + path));
/*     */   }
/*     */   
/*     */   public SpigotWorldConfig(String worldName) {
/* 179 */     this.animalActivationRange = 32;
/* 180 */     this.monsterActivationRange = 32;
/* 181 */     this.raiderActivationRange = 48;
/* 182 */     this.miscActivationRange = 16;
/*     */     
/* 184 */     this.flyingMonsterActivationRange = 32;
/* 185 */     this.waterActivationRange = 16;
/* 186 */     this.villagerActivationRange = 32;
/* 187 */     this.wakeUpInactiveAnimals = 4;
/* 188 */     this.wakeUpInactiveAnimalsEvery = 1200;
/* 189 */     this.wakeUpInactiveAnimalsFor = 100;
/* 190 */     this.wakeUpInactiveMonsters = 8;
/* 191 */     this.wakeUpInactiveMonstersEvery = 400;
/* 192 */     this.wakeUpInactiveMonstersFor = 100;
/* 193 */     this.wakeUpInactiveVillagers = 4;
/* 194 */     this.wakeUpInactiveVillagersEvery = 600;
/* 195 */     this.wakeUpInactiveVillagersFor = 100;
/* 196 */     this.wakeUpInactiveFlying = 8;
/* 197 */     this.wakeUpInactiveFlyingEvery = 200;
/* 198 */     this.wakeUpInactiveFlyingFor = 100;
/* 199 */     this.villagersWorkImmunityAfter = 100;
/* 200 */     this.villagersWorkImmunityFor = 20;
/* 201 */     this.villagersActiveForPanic = true;
/*     */     
/* 203 */     this.tickInactiveVillagers = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     this.playerTrackingRange = 48;
/* 241 */     this.animalTrackingRange = 48;
/* 242 */     this.monsterTrackingRange = 48;
/* 243 */     this.miscTrackingRange = 32;
/* 244 */     this.otherTrackingRange = 64;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 383 */     this.currentPrimedTnt = 0; this.worldName = worldName; this.config = SpigotConfig.config; init();
/*     */   }
/*     */   public <T> List getList(String path, T def) { this.config.addDefault("world-settings.default." + path, def); return this.config.getList("world-settings." + this.worldName + "." + path, this.config.getList("world-settings.default." + path)); }
/* 386 */   public String getString(String path, String def) { this.config.addDefault("world-settings.default." + path, def); return this.config.getString("world-settings." + this.worldName + "." + path, this.config.getString("world-settings.default." + path)); } private Object get(String path, Object def) { this.config.addDefault("world-settings.default." + path, def); return this.config.get("world-settings." + this.worldName + "." + path, this.config.get("world-settings.default." + path)); } private int getAndValidateGrowth(String crop) { int modifier = getInt("growth." + crop.toLowerCase(Locale.ENGLISH) + "-modifier", 100); if (modifier == 0) { log("Cannot set " + crop + " growth to zero, defaulting to 100"); modifier = 100; }  log(crop + " Growth Modifier: " + modifier + "%"); return modifier; } private void growthModifiers() { this.cactusModifier = getAndValidateGrowth("Cactus"); this.caneModifier = getAndValidateGrowth("Cane"); this.melonModifier = getAndValidateGrowth("Melon"); this.mushroomModifier = getAndValidateGrowth("Mushroom"); this.pumpkinModifier = getAndValidateGrowth("Pumpkin"); this.saplingModifier = getAndValidateGrowth("Sapling"); this.beetrootModifier = getAndValidateGrowth("Beetroot"); this.carrotModifier = getAndValidateGrowth("Carrot"); this.potatoModifier = getAndValidateGrowth("Potato"); this.wheatModifier = getAndValidateGrowth("Wheat"); this.wartModifier = getAndValidateGrowth("NetherWart"); this.vineModifier = getAndValidateGrowth("Vine"); this.cocoaModifier = getAndValidateGrowth("Cocoa"); this.bambooModifier = getAndValidateGrowth("Bamboo"); this.sweetBerryModifier = getAndValidateGrowth("SweetBerry"); this.kelpModifier = getAndValidateGrowth("Kelp"); } private void itemMerge() { this.itemMerge = getDouble("merge-radius.item", 2.5D); log("Item Merge Radius: " + this.itemMerge); } private void expMerge() { this.expMerge = getDouble("merge-radius.exp", 3.0D); log("Experience Merge Radius: " + this.expMerge); } private void viewDistance() { if (SpigotConfig.version < 12) set("view-distance", null);  Object viewDistanceObject = get("view-distance", "default"); this.viewDistance = (viewDistanceObject instanceof Number) ? ((Number)viewDistanceObject).intValue() : -1; if (this.viewDistance <= 0) this.viewDistance = Bukkit.getViewDistance();  this.viewDistance = Math.max(Math.min(this.viewDistance, 32), 3); log("View Distance: " + this.viewDistance); } private void mobSpawnRange() { this.mobSpawnRange = (byte)getInt("mob-spawn-range", 8); log("Mob Spawn Range: " + this.mobSpawnRange); } private void itemDespawnRate() { this.itemDespawnRate = getInt("item-despawn-rate", 6000); log("Item Despawn Rate: " + this.itemDespawnRate); } private void activationRange() { boolean hasAnimalsConfig = (this.config.getInt("entity-activation-range.animals", this.animalActivationRange) != this.animalActivationRange); this.animalActivationRange = getInt("entity-activation-range.animals", this.animalActivationRange); this.monsterActivationRange = getInt("entity-activation-range.monsters", this.monsterActivationRange); this.raiderActivationRange = getInt("entity-activation-range.raiders", this.raiderActivationRange); this.miscActivationRange = getInt("entity-activation-range.misc", this.miscActivationRange); this.waterActivationRange = getInt("entity-activation-range.water", this.waterActivationRange); this.villagerActivationRange = getInt("entity-activation-range.villagers", hasAnimalsConfig ? this.animalActivationRange : this.villagerActivationRange); this.flyingMonsterActivationRange = getInt("entity-activation-range.flying-monsters", this.flyingMonsterActivationRange); this.wakeUpInactiveAnimals = getInt("entity-activation-range.wake-up-inactive.animals-max-per-tick", this.wakeUpInactiveAnimals); this.wakeUpInactiveAnimalsEvery = getInt("entity-activation-range.wake-up-inactive.animals-every", this.wakeUpInactiveAnimalsEvery); this.wakeUpInactiveAnimalsFor = getInt("entity-activation-range.wake-up-inactive.animals-for", this.wakeUpInactiveAnimalsFor); this.wakeUpInactiveMonsters = getInt("entity-activation-range.wake-up-inactive.monsters-max-per-tick", this.wakeUpInactiveMonsters); this.wakeUpInactiveMonstersEvery = getInt("entity-activation-range.wake-up-inactive.monsters-every", this.wakeUpInactiveMonstersEvery); this.wakeUpInactiveMonstersFor = getInt("entity-activation-range.wake-up-inactive.monsters-for", this.wakeUpInactiveMonstersFor); this.wakeUpInactiveVillagers = getInt("entity-activation-range.wake-up-inactive.villagers-max-per-tick", this.wakeUpInactiveVillagers); this.wakeUpInactiveVillagersEvery = getInt("entity-activation-range.wake-up-inactive.villagers-every", this.wakeUpInactiveVillagersEvery); this.wakeUpInactiveVillagersFor = getInt("entity-activation-range.wake-up-inactive.villagers-for", this.wakeUpInactiveVillagersFor); this.wakeUpInactiveFlying = getInt("entity-activation-range.wake-up-inactive.flying-monsters-max-per-tick", this.wakeUpInactiveFlying); this.wakeUpInactiveFlyingEvery = getInt("entity-activation-range.wake-up-inactive.flying-monsters-every", this.wakeUpInactiveFlyingEvery); this.wakeUpInactiveFlyingFor = getInt("entity-activation-range.wake-up-inactive.flying-monsters-for", this.wakeUpInactiveFlyingFor); this.villagersWorkImmunityAfter = getInt("entity-activation-range.villagers-work-immunity-after", this.villagersWorkImmunityAfter); this.villagersWorkImmunityFor = getInt("entity-activation-range.villagers-work-immunity-for", this.villagersWorkImmunityFor); this.villagersActiveForPanic = getBoolean("entity-activation-range.villagers-active-for-panic", this.villagersActiveForPanic); this.tickInactiveVillagers = getBoolean("entity-activation-range.tick-inactive-villagers", this.tickInactiveVillagers); log("Entity Activation Range: An " + this.animalActivationRange + " / Mo " + this.monsterActivationRange + " / Ra " + this.raiderActivationRange + " / Mi " + this.miscActivationRange + " / Tiv " + this.tickInactiveVillagers); } private void maxTntPerTick() { if (SpigotConfig.version < 7)
/*     */     {
/* 388 */       set("max-tnt-per-tick", Integer.valueOf(100));
/*     */     }
/* 390 */     this.maxTntTicksPerTick = getInt("max-tnt-per-tick", 100);
/* 391 */     log("Max TNT Explosions: " + this.maxTntTicksPerTick); }
/*     */   private void trackingRange() { this.playerTrackingRange = getInt("entity-tracking-range.players", this.playerTrackingRange); this.animalTrackingRange = getInt("entity-tracking-range.animals", this.animalTrackingRange); this.monsterTrackingRange = getInt("entity-tracking-range.monsters", this.monsterTrackingRange); this.miscTrackingRange = getInt("entity-tracking-range.misc", this.miscTrackingRange); this.otherTrackingRange = getInt("entity-tracking-range.other", this.otherTrackingRange); log("Entity Tracking Range: Pl " + this.playerTrackingRange + " / An " + this.animalTrackingRange + " / Mo " + this.monsterTrackingRange + " / Mi " + this.miscTrackingRange + " / Other " + this.otherTrackingRange); }
/*     */   private void hoppers() { this.hopperTransfer = getInt("ticks-per.hopper-transfer", 8); if (SpigotConfig.version < 11)
/*     */       set("ticks-per.hopper-check", Integer.valueOf(1));  this.hopperCheck = getInt("ticks-per.hopper-check", 1); this.hopperAmount = getInt("hopper-amount", 1); log("Hopper Transfer: " + this.hopperTransfer + " Hopper Check: " + this.hopperCheck + " Hopper Amount: " + this.hopperAmount); }
/*     */   private void arrowDespawnRate() { this.arrowDespawnRate = getInt("arrow-despawn-rate", 1200);
/*     */     this.tridentDespawnRate = getInt("trident-despawn-rate", this.arrowDespawnRate);
/* 397 */     log("Arrow Despawn Rate: " + this.arrowDespawnRate + " Trident Respawn Rate:" + this.tridentDespawnRate); } private void hangingTickFrequency() { this.hangingTickFrequency = getInt("hanging-tick-frequency", 100); } private void zombieAggressiveTowardsVillager() { this.zombieAggressiveTowardsVillager = getBoolean("zombie-aggressive-towards-villager", true); log("Zombie Aggressive Towards Villager: " + this.zombieAggressiveTowardsVillager); } private void nerfSpawnerMobs() { this.nerfSpawnerMobs = getBoolean("nerf-spawner-mobs", false); log("Nerfing mobs spawned from spawners: " + this.nerfSpawnerMobs); }
/*     */   private void enableZombiePigmenPortalSpawns() { this.enableZombiePigmenPortalSpawns = getBoolean("enable-zombie-pigmen-portal-spawns", true); log("Allow Zombie Pigmen to spawn from portal blocks: " + this.enableZombiePigmenPortalSpawns); }
/*     */   private void keepDragonDeathPerWorld() { this.dragonDeathSoundRadius = getInt("dragon-death-sound-radius", 0); }
/*     */   private void witherSpawnSoundRadius() { this.witherSpawnSoundRadius = getInt("wither-spawn-sound-radius", 0); }
/*     */   private void endPortalSoundRadius() { this.endPortalSoundRadius = getInt("end-portal-sound-radius", 0); }
/*     */   private void initWorldGenSeeds() { this.villageSeed = getInt("seed-village", 10387312); this.desertSeed = getInt("seed-desert", 14357617); this.iglooSeed = getInt("seed-igloo", 14357618); this.jungleSeed = getInt("seed-jungle", 14357619); this.swampSeed = getInt("seed-swamp", 14357620); this.monumentSeed = getInt("seed-monument", 10387313); this.shipwreckSeed = getInt("seed-shipwreck", 165745295); this.oceanSeed = getInt("seed-ocean", 14357621); this.outpostSeed = getInt("seed-outpost", 165745296); this.endCitySeed = getInt("seed-endcity", 10387313); this.slimeSeed = getInt("seed-slime", 987234911); this.bastionSeed = getInt("seed-bastion", 30084232); this.fortressSeed = getInt("seed-fortress", 30084232); this.mansionSeed = getInt("seed-mansion", 10387319); this.fossilSeed = getInt("seed-fossil", 14357921); this.portalSeed = getInt("seed-portal", 34222645); log("Custom Map Seeds:  Village: " + this.villageSeed + " Desert: " + this.desertSeed + " Igloo: " + this.iglooSeed + " Jungle: " + this.jungleSeed + " Swamp: " + this.swampSeed + " Monument: " + this.monumentSeed + " Ocean: " + this.oceanSeed + " Shipwreck: " + this.shipwreckSeed + " End City: " + this.endCitySeed + " Slime: " + this.slimeSeed + " Bastion: " + this.bastionSeed + " Fortress: " + this.fortressSeed + " Mansion: " + this.mansionSeed + " Fossil: " + this.fossilSeed + " Portal: " + this.portalSeed); }
/*     */   private void initHunger() { if (SpigotConfig.version < 10) { set("hunger.walk-exhaustion", null); set("hunger.sprint-exhaustion", null); set("hunger.combat-exhaustion", Double.valueOf(0.1D)); set("hunger.regen-exhaustion", Double.valueOf(6.0D)); }  this.jumpWalkExhaustion = (float)getDouble("hunger.jump-walk-exhaustion", 0.05D); this.jumpSprintExhaustion = (float)getDouble("hunger.jump-sprint-exhaustion", 0.2D); this.combatExhaustion = (float)getDouble("hunger.combat-exhaustion", 0.1D); this.regenExhaustion = (float)getDouble("hunger.regen-exhaustion", 6.0D); this.swimMultiplier = (float)getDouble("hunger.swim-multiplier", 0.01D); this.sprintMultiplier = (float)getDouble("hunger.sprint-multiplier", 0.1D); this.otherMultiplier = (float)getDouble("hunger.other-multiplier", 0.0D); }
/* 404 */   private void maxTickTimes() { this.tileMaxTickTime = getInt("max-tick-time.tile", 50);
/* 405 */     this.entityMaxTickTime = getInt("max-tick-time.entity", 50);
/* 406 */     log("Tile Max Tick Time: " + this.tileMaxTickTime + "ms Entity max Tick Time: " + this.entityMaxTickTime + "ms"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void squidSpawnRange() {
/* 412 */     this.squidSpawnRangeMin = getDouble("squid-spawn-range.min", 45.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\SpigotWorldConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */