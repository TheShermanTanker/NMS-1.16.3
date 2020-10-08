/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ public class MobSpawnerPatrol implements MobSpawner {
/*     */   private int a;
/*     */   
/*   7 */   private int getSpawnDelay() { return this.a; } private void setSpawnDelay(int spawnDelay) { this.a = spawnDelay; }
/*     */ 
/*     */   
/*     */   public int a(WorldServer worldserver, boolean flag, boolean flag1) {
/*     */     int patrolSpawnDelay;
/*     */     long days;
/*  13 */     if (!worldserver.getGameRules().getBoolean(GameRules.DO_PATROL_SPAWNING)) {
/*  14 */       return 0;
/*     */     }
/*  16 */     Random random = worldserver.random;
/*     */ 
/*     */ 
/*     */     
/*  20 */     int j = worldserver.getPlayers().size();
/*  21 */     if (j < 1) {
/*  22 */       return 0;
/*     */     }
/*     */     
/*  25 */     EntityPlayer entityhuman = worldserver.getPlayers().get(random.nextInt(j));
/*  26 */     if (entityhuman.isSpectator()) {
/*  27 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  31 */     if (worldserver.paperConfig.patrolPerPlayerDelay) {
/*     */       
/*  33 */       patrolSpawnDelay = --entityhuman.patrolSpawnDelay;
/*     */     } else {
/*  35 */       setSpawnDelay(getSpawnDelay() - 1);
/*  36 */       patrolSpawnDelay = getSpawnDelay();
/*     */     } 
/*     */     
/*  39 */     if (patrolSpawnDelay > 0) {
/*  40 */       return 0;
/*     */     }
/*     */     
/*  43 */     if (worldserver.paperConfig.patrolPerPlayerStart) {
/*  44 */       days = entityhuman.getStatisticManager().getStatisticValue(StatisticList.CUSTOM.get(StatisticList.PLAY_ONE_MINUTE)) / 24000L;
/*     */     } else {
/*  46 */       days = worldserver.getDayTime() / 24000L;
/*     */     } 
/*  48 */     if (worldserver.paperConfig.patrolPerPlayerDelay) {
/*  49 */       entityhuman.patrolSpawnDelay += worldserver.paperConfig.patrolDelay + random.nextInt(1200);
/*     */     } else {
/*  51 */       setSpawnDelay(getSpawnDelay() + worldserver.paperConfig.patrolDelay + random.nextInt(1200));
/*     */     } 
/*     */     
/*  54 */     if (days >= worldserver.paperConfig.patrolStartDay && worldserver.isDay()) {
/*  55 */       if (random.nextDouble() >= worldserver.paperConfig.patrolSpawnChance)
/*     */       {
/*  57 */         return 0;
/*     */       }
/*     */       
/*  60 */       if (j < 1) {
/*  61 */         return 0;
/*     */       }
/*     */       
/*  64 */       if (entityhuman.isSpectator())
/*  65 */         return 0; 
/*  66 */       if (worldserver.a(entityhuman.getChunkCoordinates(), 2)) {
/*  67 */         return 0;
/*     */       }
/*  69 */       int k = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
/*  70 */       int l = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
/*  71 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = entityhuman.getChunkCoordinates().i().e(k, 0, l);
/*     */       
/*  73 */       if (!worldserver.isAreaLoaded(blockposition_mutableblockposition.getX() - 10, blockposition_mutableblockposition.getY() - 10, blockposition_mutableblockposition.getZ() - 10, blockposition_mutableblockposition.getX() + 10, blockposition_mutableblockposition.getY() + 10, blockposition_mutableblockposition.getZ() + 10)) {
/*  74 */         return 0;
/*     */       }
/*  76 */       BiomeBase biomebase = worldserver.getBiome(blockposition_mutableblockposition);
/*  77 */       BiomeBase.Geography biomebase_geography = biomebase.t();
/*     */       
/*  79 */       if (biomebase_geography == BiomeBase.Geography.MUSHROOM) {
/*  80 */         return 0;
/*     */       }
/*  82 */       int i1 = 0;
/*  83 */       int j1 = (int)Math.ceil(worldserver.getDamageScaler(blockposition_mutableblockposition).b()) + 1;
/*     */       
/*  85 */       for (int k1 = 0; k1 < j1; k1++) {
/*  86 */         i1++;
/*  87 */         blockposition_mutableblockposition.p(worldserver.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, blockposition_mutableblockposition).getY());
/*  88 */         if (k1 == 0) {
/*  89 */           if (!a(worldserver, blockposition_mutableblockposition, random, true)) {
/*     */             break;
/*     */           }
/*     */         } else {
/*  93 */           a(worldserver, blockposition_mutableblockposition, random, false);
/*     */         } 
/*     */         
/*  96 */         blockposition_mutableblockposition.o(blockposition_mutableblockposition.getX() + random.nextInt(5) - random.nextInt(5));
/*  97 */         blockposition_mutableblockposition.q(blockposition_mutableblockposition.getZ() + random.nextInt(5) - random.nextInt(5));
/*     */       } 
/*     */       
/* 100 */       return i1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(WorldServer worldserver, BlockPosition blockposition, Random random, boolean flag) {
/* 114 */     IBlockData iblockdata = worldserver.getType(blockposition);
/*     */     
/* 116 */     if (!SpawnerCreature.a(worldserver, blockposition, iblockdata, iblockdata.getFluid(), EntityTypes.PILLAGER))
/* 117 */       return false; 
/* 118 */     if (!EntityMonsterPatrolling.b((EntityTypes)EntityTypes.PILLAGER, worldserver, EnumMobSpawn.PATROL, blockposition, random)) {
/* 119 */       return false;
/*     */     }
/* 121 */     EntityMonsterPatrolling entitymonsterpatrolling = EntityTypes.PILLAGER.a(worldserver);
/*     */     
/* 123 */     if (entitymonsterpatrolling != null) {
/* 124 */       if (flag) {
/* 125 */         entitymonsterpatrolling.setPatrolLeader(true);
/* 126 */         entitymonsterpatrolling.eU();
/*     */       } 
/*     */       
/* 129 */       entitymonsterpatrolling.setPosition(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 130 */       entitymonsterpatrolling.prepare(worldserver, worldserver.getDamageScaler(blockposition), EnumMobSpawn.PATROL, (GroupDataEntity)null, (NBTTagCompound)null);
/* 131 */       worldserver.addAllEntities(entitymonsterpatrolling, CreatureSpawnEvent.SpawnReason.PATROL);
/* 132 */       return true;
/*     */     } 
/* 134 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobSpawnerPatrol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */