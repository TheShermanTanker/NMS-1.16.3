/*     */ package org.spigotmc;
/*     */ 
/*     */ import co.aikar.timings.MinecraftTimings;
/*     */ import net.minecraft.server.v1_16_R2.Activity;
/*     */ import net.minecraft.server.v1_16_R2.AxisAlignedBB;
/*     */ import net.minecraft.server.v1_16_R2.BehaviorController;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.Chunk;
/*     */ import net.minecraft.server.v1_16_R2.ChunkProviderServer;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*     */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*     */ import net.minecraft.server.v1_16_R2.EntityBee;
/*     */ import net.minecraft.server.v1_16_R2.EntityCreeper;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EntityLlama;
/*     */ import net.minecraft.server.v1_16_R2.EntityPillager;
/*     */ import net.minecraft.server.v1_16_R2.EntitySheep;
/*     */ import net.minecraft.server.v1_16_R2.EntityVillager;
/*     */ import net.minecraft.server.v1_16_R2.MathHelper;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActivationRange
/*     */ {
/*     */   public enum ActivationType
/*     */   {
/*  59 */     WATER,
/*  60 */     FLYING_MONSTER,
/*  61 */     VILLAGER,
/*  62 */     MONSTER,
/*  63 */     ANIMAL,
/*  64 */     RAIDER,
/*  65 */     MISC;
/*     */     ActivationType() {
/*  67 */       this.boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/*     */     AxisAlignedBB boundingBox; }
/*  71 */   static Activity[] VILLAGER_PANIC_IMMUNITIES = new Activity[] { Activity.HIDE, Activity.PRE_RAID, Activity.RAID, Activity.PANIC };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int checkInactiveWakeup(Entity entity) {
/*  79 */     World world = entity.world;
/*  80 */     SpigotWorldConfig config = world.spigotConfig;
/*  81 */     long inactiveFor = MinecraftServer.currentTick - entity.activatedTick;
/*  82 */     if (entity.activationType == ActivationType.VILLAGER) {
/*  83 */       if (inactiveFor > config.wakeUpInactiveVillagersEvery && world.wakeupInactiveRemainingVillagers > 0) {
/*  84 */         world.wakeupInactiveRemainingVillagers--;
/*  85 */         return config.wakeUpInactiveVillagersFor;
/*     */       } 
/*  87 */     } else if (entity.activationType == ActivationType.ANIMAL) {
/*  88 */       if (inactiveFor > config.wakeUpInactiveAnimalsEvery && world.wakeupInactiveRemainingAnimals > 0) {
/*  89 */         world.wakeupInactiveRemainingAnimals--;
/*  90 */         return config.wakeUpInactiveAnimalsFor;
/*     */       } 
/*  92 */     } else if (entity.activationType == ActivationType.FLYING_MONSTER) {
/*  93 */       if (inactiveFor > config.wakeUpInactiveFlyingEvery && world.wakeupInactiveRemainingFlying > 0) {
/*  94 */         world.wakeupInactiveRemainingFlying--;
/*  95 */         return config.wakeUpInactiveFlyingFor;
/*     */       } 
/*  97 */     } else if ((entity.activationType == ActivationType.MONSTER || entity.activationType == ActivationType.RAIDER) && 
/*  98 */       inactiveFor > config.wakeUpInactiveMonstersEvery && world.wakeupInactiveRemainingMonsters > 0) {
/*  99 */       world.wakeupInactiveRemainingMonsters--;
/* 100 */       return config.wakeUpInactiveMonstersFor;
/*     */     } 
/*     */     
/* 103 */     return -1;
/*     */   }
/*     */ 
/*     */   
/* 107 */   static AxisAlignedBB maxBB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ActivationType initializeEntityActivationType(Entity entity) {
/* 118 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityWaterAnimal) return ActivationType.WATER; 
/* 119 */     if (entity instanceof EntityVillager) return ActivationType.VILLAGER; 
/* 120 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityFlying && entity instanceof net.minecraft.server.v1_16_R2.IMonster) return ActivationType.FLYING_MONSTER; 
/* 121 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityRaider)
/*     */     {
/* 123 */       return ActivationType.RAIDER; } 
/* 124 */     if (entity instanceof net.minecraft.server.v1_16_R2.IMonster)
/*     */     {
/* 126 */       return ActivationType.MONSTER; } 
/* 127 */     if (entity instanceof net.minecraft.server.v1_16_R2.EntityCreature || entity instanceof net.minecraft.server.v1_16_R2.EntityAmbient)
/*     */     {
/* 129 */       return ActivationType.ANIMAL;
/*     */     }
/*     */     
/* 132 */     return ActivationType.MISC;
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
/*     */   public static boolean initializeEntityActivationState(Entity entity, SpigotWorldConfig config) {
/* 145 */     if ((entity.activationType == ActivationType.MISC && config.miscActivationRange <= 0) || (entity.activationType == ActivationType.RAIDER && config.raiderActivationRange <= 0) || (entity.activationType == ActivationType.ANIMAL && config.animalActivationRange <= 0) || (entity.activationType == ActivationType.MONSTER && config.monsterActivationRange <= 0) || (entity.activationType == ActivationType.VILLAGER && config.villagerActivationRange <= 0) || (entity.activationType == ActivationType.WATER && config.waterActivationRange <= 0) || (entity.activationType == ActivationType.FLYING_MONSTER && config.flyingMonsterActivationRange <= 0) || entity instanceof net.minecraft.server.v1_16_R2.EntityEnderSignal || entity instanceof EntityHuman || entity instanceof net.minecraft.server.v1_16_R2.EntityProjectile || entity instanceof net.minecraft.server.v1_16_R2.EntityEnderDragon || entity instanceof net.minecraft.server.v1_16_R2.EntityComplexPart || entity instanceof net.minecraft.server.v1_16_R2.EntityWither || entity instanceof net.minecraft.server.v1_16_R2.EntityFireball || entity instanceof net.minecraft.server.v1_16_R2.EntityLightning || entity instanceof net.minecraft.server.v1_16_R2.EntityTNTPrimed || entity instanceof net.minecraft.server.v1_16_R2.EntityFallingBlock || entity instanceof net.minecraft.server.v1_16_R2.EntityEnderCrystal || entity instanceof net.minecraft.server.v1_16_R2.EntityFireworks || entity instanceof net.minecraft.server.v1_16_R2.EntityThrownTrident)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 166 */       return true;
/*     */     }
/*     */     
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void activateEntities(World world) {
/* 180 */     MinecraftTimings.entityActivationCheckTimer.startTiming();
/* 181 */     int miscActivationRange = world.spigotConfig.miscActivationRange;
/* 182 */     int raiderActivationRange = world.spigotConfig.raiderActivationRange;
/* 183 */     int animalActivationRange = world.spigotConfig.animalActivationRange;
/* 184 */     int monsterActivationRange = world.spigotConfig.monsterActivationRange;
/*     */     
/* 186 */     int waterActivationRange = world.spigotConfig.waterActivationRange;
/* 187 */     int flyingActivationRange = world.spigotConfig.flyingMonsterActivationRange;
/* 188 */     int villagerActivationRange = world.spigotConfig.villagerActivationRange;
/* 189 */     world.wakeupInactiveRemainingAnimals = Math.min(world.wakeupInactiveRemainingAnimals + 1, world.spigotConfig.wakeUpInactiveAnimals);
/* 190 */     world.wakeupInactiveRemainingVillagers = Math.min(world.wakeupInactiveRemainingVillagers + 1, world.spigotConfig.wakeUpInactiveVillagers);
/* 191 */     world.wakeupInactiveRemainingMonsters = Math.min(world.wakeupInactiveRemainingMonsters + 1, world.spigotConfig.wakeUpInactiveMonsters);
/* 192 */     world.wakeupInactiveRemainingFlying = Math.min(world.wakeupInactiveRemainingFlying + 1, world.spigotConfig.wakeUpInactiveFlying);
/* 193 */     ChunkProviderServer chunkProvider = (ChunkProviderServer)world.getChunkProvider();
/*     */ 
/*     */     
/* 196 */     int maxRange = Math.max(monsterActivationRange, animalActivationRange);
/* 197 */     maxRange = Math.max(maxRange, raiderActivationRange);
/* 198 */     maxRange = Math.max(maxRange, miscActivationRange);
/*     */     
/* 200 */     maxRange = Math.max(maxRange, flyingActivationRange);
/* 201 */     maxRange = Math.max(maxRange, waterActivationRange);
/* 202 */     maxRange = Math.max(maxRange, villagerActivationRange);
/*     */     
/* 204 */     maxRange = Math.min(((((WorldServer)world).getChunkProvider()).playerChunkMap.getEffectiveViewDistance() << 4) - 8, maxRange);
/*     */     
/* 206 */     for (EntityHuman player : world.getPlayers()) {
/*     */ 
/*     */       
/* 209 */       player.activatedTick = MinecraftServer.currentTick;
/* 210 */       maxBB = player.getBoundingBox().grow(maxRange, 256.0D, maxRange);
/* 211 */       ActivationType.MISC.boundingBox = player.getBoundingBox().grow(miscActivationRange, 256.0D, miscActivationRange);
/* 212 */       ActivationType.RAIDER.boundingBox = player.getBoundingBox().grow(raiderActivationRange, 256.0D, raiderActivationRange);
/* 213 */       ActivationType.ANIMAL.boundingBox = player.getBoundingBox().grow(animalActivationRange, 256.0D, animalActivationRange);
/* 214 */       ActivationType.MONSTER.boundingBox = player.getBoundingBox().grow(monsterActivationRange, 256.0D, monsterActivationRange);
/*     */       
/* 216 */       ActivationType.WATER.boundingBox = player.getBoundingBox().grow(waterActivationRange, 256.0D, waterActivationRange);
/* 217 */       ActivationType.FLYING_MONSTER.boundingBox = player.getBoundingBox().grow(flyingActivationRange, 256.0D, flyingActivationRange);
/* 218 */       ActivationType.VILLAGER.boundingBox = player.getBoundingBox().grow(villagerActivationRange, 256.0D, waterActivationRange);
/*     */ 
/*     */       
/* 221 */       int i = MathHelper.floor(maxBB.minX / 16.0D);
/* 222 */       int j = MathHelper.floor(maxBB.maxX / 16.0D);
/* 223 */       int k = MathHelper.floor(maxBB.minZ / 16.0D);
/* 224 */       int l = MathHelper.floor(maxBB.maxZ / 16.0D);
/*     */       
/* 226 */       for (int i1 = i; i1 <= j; i1++) {
/*     */         
/* 228 */         for (int j1 = k; j1 <= l; j1++) {
/*     */           
/* 230 */           Chunk chunk = chunkProvider.getChunkAtIfLoadedMainThreadNoCache(i1, j1);
/* 231 */           if (chunk != null)
/*     */           {
/* 233 */             activateChunkEntities(chunk);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 238 */     MinecraftTimings.entityActivationCheckTimer.stopTiming();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void activateChunkEntities(Chunk chunk) {
/* 249 */     Entity[] rawData = chunk.entities.getRawData();
/* 250 */     for (int i = 0; i < chunk.entities.size(); i++) {
/* 251 */       Entity entity = rawData[i];
/*     */ 
/*     */ 
/*     */       
/* 255 */       if (MinecraftServer.currentTick > entity.activatedTick && (
/* 256 */         entity.defaultActivationState || entity.activationType.boundingBox.c(entity.getBoundingBox()))) {
/* 257 */         entity.activatedTick = MinecraftServer.currentTick;
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
/*     */   public static int checkEntityImmunities(Entity entity) {
/* 274 */     SpigotWorldConfig config = entity.world.spigotConfig;
/* 275 */     int inactiveWakeUpImmunity = checkInactiveWakeup(entity);
/* 276 */     if (inactiveWakeUpImmunity > -1) {
/* 277 */       return inactiveWakeUpImmunity;
/*     */     }
/* 279 */     if (entity.fireTicks > 0) {
/* 280 */       return 2;
/*     */     }
/* 282 */     long inactiveFor = MinecraftServer.currentTick - entity.activatedTick;
/*     */ 
/*     */     
/* 285 */     if (entity.activationType != ActivationType.WATER && entity.inWater && entity.isPushedByWater())
/*     */     {
/* 287 */       return 100;
/*     */     }
/* 289 */     if (!(entity instanceof EntityArrow)) {
/*     */       
/* 291 */       if (!entity.isOnGround() && !(entity instanceof net.minecraft.server.v1_16_R2.EntityFlying))
/*     */       {
/* 293 */         return 10;
/*     */       }
/* 295 */     } else if (!((EntityArrow)entity).inGround) {
/*     */       
/* 297 */       return 1;
/*     */     } 
/*     */     
/* 300 */     if (entity instanceof EntityLiving) {
/*     */       
/* 302 */       EntityLiving living = (EntityLiving)entity;
/* 303 */       if (living.isClimbing() || living.jumping || living.hurtTicks > 0 || living.effects.size() > 0)
/*     */       {
/* 305 */         return 1;
/*     */       }
/* 307 */       if (entity instanceof EntityInsentient && ((EntityInsentient)entity).getGoalTarget() != null)
/*     */       {
/* 309 */         return 20;
/*     */       }
/*     */       
/* 312 */       if (entity instanceof EntityBee) {
/* 313 */         EntityBee bee = (EntityBee)entity;
/* 314 */         BlockPosition movingTarget = bee.getMovingTarget();
/* 315 */         if (bee.isAngry() || (bee
/* 316 */           .getHivePos() != null && bee.getHivePos().equals(movingTarget)) || (bee
/* 317 */           .getFlowerPos() != null && bee.getFlowerPos().equals(movingTarget)))
/*     */         {
/* 319 */           return 20;
/*     */         }
/*     */       } 
/* 322 */       if (entity instanceof EntityVillager) {
/* 323 */         BehaviorController<EntityVillager> behaviorController = ((EntityVillager)entity).getBehaviorController();
/*     */         
/* 325 */         if (config.villagersActiveForPanic) {
/* 326 */           for (Activity activity : VILLAGER_PANIC_IMMUNITIES) {
/* 327 */             if (behaviorController.c(activity)) {
/* 328 */               return 100;
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 333 */         if (config.villagersWorkImmunityAfter > 0 && inactiveFor >= config.villagersWorkImmunityAfter && 
/* 334 */           behaviorController.c(Activity.WORK)) {
/* 335 */           return config.villagersWorkImmunityFor;
/*     */         }
/*     */       } 
/*     */       
/* 339 */       if (entity instanceof EntityLlama && ((EntityLlama)entity).inCaravan())
/*     */       {
/* 341 */         return 1;
/*     */       }
/*     */       
/* 344 */       if (entity instanceof EntityAnimal) {
/*     */         
/* 346 */         EntityAnimal animal = (EntityAnimal)entity;
/* 347 */         if (animal.isBaby() || animal.isInLove())
/*     */         {
/* 349 */           return 5;
/*     */         }
/* 351 */         if (entity instanceof EntitySheep && ((EntitySheep)entity).isSheared())
/*     */         {
/* 353 */           return 1;
/*     */         }
/*     */       } 
/* 356 */       if (entity instanceof EntityCreeper && ((EntityCreeper)entity).isIgnited()) {
/* 357 */         return 20;
/*     */       }
/*     */       
/* 360 */       if (entity instanceof EntityInsentient && ((EntityInsentient)entity).targetSelector.hasTasks()) {
/* 361 */         return 0;
/*     */       }
/* 363 */       if (entity instanceof EntityPillager) {
/* 364 */         EntityPillager entityPillager = (EntityPillager)entity;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 369 */     return -1;
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
/*     */   public static boolean checkIfActive(Entity entity) {
/* 381 */     if (!entity.inChunk || entity instanceof net.minecraft.server.v1_16_R2.EntityFireworks) {
/* 382 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 386 */     if (entity.defaultActivationState || entity.ticksLived < 200 || !entity.isAlive() || entity.inPortal || entity.portalCooldown > 0) {
/* 387 */       return true;
/*     */     }
/*     */     
/* 390 */     if (entity instanceof EntityInsentient && ((EntityInsentient)entity).leashHolder instanceof EntityHuman) {
/* 391 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 395 */     boolean isActive = (entity.activatedTick >= MinecraftServer.currentTick);
/* 396 */     entity.isTemporarilyActive = false;
/*     */ 
/*     */     
/* 399 */     if (!isActive) {
/*     */       
/* 401 */       if ((MinecraftServer.currentTick - entity.activatedTick - 1L) % 20L == 0L)
/*     */       {
/*     */ 
/*     */         
/* 405 */         int immunity = checkEntityImmunities(entity);
/* 406 */         if (immunity >= 0) {
/* 407 */           entity.activatedTick = (MinecraftServer.currentTick + immunity);
/*     */         } else {
/* 409 */           entity.isTemporarilyActive = true;
/*     */         } 
/*     */         
/* 412 */         isActive = true;
/*     */       }
/*     */     
/*     */     }
/* 416 */     else if (entity.ticksLived % 4 == 0 && checkEntityImmunities(entity) < 0) {
/*     */       
/* 418 */       isActive = false;
/*     */     } 
/* 420 */     return isActive;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\ActivationRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */