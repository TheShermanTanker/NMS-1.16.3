/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
/*     */ import com.destroystokyo.paper.exception.ServerInternalException;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMaps;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ 
/*     */ public final class SpawnerCreature
/*     */ {
/*  23 */   private static final Logger LOGGER = LogManager.getLogger();
/*  24 */   private static final int b = (int)Math.pow(17.0D, 2.0D); private static final EnumCreatureType[] c;
/*     */   
/*     */   static {
/*  27 */     c = (EnumCreatureType[])Stream.<EnumCreatureType>of(EnumCreatureType.values()).filter(enumcreaturetype -> (enumcreaturetype != EnumCreatureType.MISC)).toArray(i -> new EnumCreatureType[i]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static d a(int i, Iterable<Entity> iterable, b spawnercreature_b) {
/*  33 */     return countMobs(i, iterable, spawnercreature_b, false);
/*     */   }
/*     */   
/*     */   public static d countMobs(int i, Iterable<Entity> iterable, b spawnercreature_b, boolean countMobs) {
/*  37 */     SpawnerCreatureProbabilities spawnercreatureprobabilities = new SpawnerCreatureProbabilities();
/*  38 */     Object2IntOpenHashMap<EnumCreatureType> object2intopenhashmap = new Object2IntOpenHashMap();
/*  39 */     Iterator<Entity> iterator = iterable.iterator();
/*     */     
/*  41 */     while (iterator.hasNext()) {
/*  42 */       Entity entity = iterator.next();
/*     */       
/*  44 */       if (entity instanceof EntityInsentient) {
/*  45 */         EntityInsentient entityinsentient = (EntityInsentient)entity;
/*     */ 
/*     */         
/*  48 */         if (entityinsentient.isTypeNotPersistent(0.0D) && entityinsentient.isPersistent()) {
/*     */           continue;
/*     */         }
/*     */       } 
/*     */       
/*  53 */       EnumCreatureType enumcreaturetype = entity.getEntityType().e();
/*     */       
/*  55 */       if (enumcreaturetype != EnumCreatureType.MISC) {
/*     */         
/*  57 */         if (!entity.world.paperConfig.countAllMobsForSpawning && entity.spawnReason != CreatureSpawnEvent.SpawnReason.NATURAL && entity.spawnReason != CreatureSpawnEvent.SpawnReason.CHUNK_GEN) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*  63 */         BlockPosition blockposition = entity.getChunkCoordinates();
/*  64 */         long j = ChunkCoordIntPair.pair(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*     */         
/*  66 */         spawnercreature_b.query(j, chunk -> {
/*     */               BiomeSettingsMobs.b biomesettingsmobs_b = b(blockposition, chunk).b().a(entity.getEntityType());
/*     */ 
/*     */               
/*     */               if (biomesettingsmobs_b != null) {
/*     */                 spawnercreatureprobabilities.a(entity.getChunkCoordinates(), biomesettingsmobs_b.b());
/*     */               }
/*     */               
/*     */               object2intopenhashmap.addTo(enumcreaturetype, 1);
/*     */               
/*     */               if (countMobs) {
/*     */                 (chunk.world.getChunkProvider()).playerChunkMap.updatePlayerMobTypeMap(entity);
/*     */               }
/*     */             });
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     return new d(i, object2intopenhashmap, spawnercreatureprobabilities);
/*     */   }
/*     */   
/*     */   private static BiomeBase b(BlockPosition blockposition, IChunkAccess ichunkaccess) {
/*  87 */     return GenLayerZoomerBiome.INSTANCE.a(0L, blockposition.getX(), blockposition.getY(), blockposition.getZ(), ichunkaccess.getBiomeIndex());
/*     */   }
/*     */   
/*     */   public static void a(WorldServer worldserver, Chunk chunk, d spawnercreature_d, boolean flag, boolean flag1, boolean flag2) {
/*  91 */     worldserver.getMethodProfiler().enter("spawner");
/*  92 */     worldserver.timings.mobSpawn.startTiming();
/*  93 */     EnumCreatureType[] aenumcreaturetype = c;
/*  94 */     int i = aenumcreaturetype.length;
/*     */ 
/*     */     
/*  97 */     WorldData worlddata = worldserver.getWorldData();
/*  98 */     boolean spawnAnimalThisTick = (worldserver.ticksPerAnimalSpawns != 0L && worlddata.getTime() % worldserver.ticksPerAnimalSpawns == 0L);
/*  99 */     boolean spawnMonsterThisTick = (worldserver.ticksPerMonsterSpawns != 0L && worlddata.getTime() % worldserver.ticksPerMonsterSpawns == 0L);
/* 100 */     boolean spawnWaterThisTick = (worldserver.ticksPerWaterSpawns != 0L && worlddata.getTime() % worldserver.ticksPerWaterSpawns == 0L);
/* 101 */     boolean spawnAmbientThisTick = (worldserver.ticksPerAmbientSpawns != 0L && worlddata.getTime() % worldserver.ticksPerAmbientSpawns == 0L);
/* 102 */     boolean spawnWaterAmbientThisTick = (worldserver.ticksPerWaterAmbientSpawns != 0L && worlddata.getTime() % worldserver.ticksPerWaterAmbientSpawns == 0L);
/*     */ 
/*     */     
/* 105 */     for (int j = 0; j < i; j++) {
/* 106 */       EnumCreatureType enumcreaturetype = aenumcreaturetype[j];
/*     */       
/* 108 */       boolean spawnThisTick = true;
/* 109 */       int limit = enumcreaturetype.c();
/* 110 */       switch (enumcreaturetype) {
/*     */         case IN_WATER:
/* 112 */           spawnThisTick = spawnMonsterThisTick;
/* 113 */           limit = worldserver.getWorld().getMonsterSpawnLimit();
/*     */           break;
/*     */         case IN_LAVA:
/* 116 */           spawnThisTick = spawnAnimalThisTick;
/* 117 */           limit = worldserver.getWorld().getAnimalSpawnLimit();
/*     */           break;
/*     */         case ON_GROUND:
/* 120 */           spawnThisTick = spawnWaterThisTick;
/* 121 */           limit = worldserver.getWorld().getWaterAnimalSpawnLimit();
/*     */           break;
/*     */         case null:
/* 124 */           spawnThisTick = spawnAmbientThisTick;
/* 125 */           limit = worldserver.getWorld().getAmbientSpawnLimit();
/*     */           break;
/*     */         case null:
/* 128 */           spawnThisTick = spawnWaterAmbientThisTick;
/* 129 */           limit = worldserver.getWorld().getWaterAmbientSpawnLimit();
/*     */           break;
/*     */       } 
/*     */       
/* 133 */       if (spawnThisTick && limit != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 138 */         int currEntityCount = spawnercreature_d.getEntityCountsByType().getInt(enumcreaturetype);
/* 139 */         int k1 = limit * spawnercreature_d.getSpawnerChunks() / b;
/* 140 */         int difference = k1 - currEntityCount;
/*     */         
/* 142 */         if (worldserver.paperConfig.perPlayerMobSpawns) {
/* 143 */           int minDiff = Integer.MAX_VALUE;
/* 144 */           for (EntityPlayer entityplayer : (worldserver.getChunkProvider()).playerChunkMap.playerMobDistanceMap.getPlayersInRange(chunk.getPos())) {
/* 145 */             minDiff = Math.min(limit - (worldserver.getChunkProvider()).playerChunkMap.getMobCountNear(entityplayer, enumcreaturetype), minDiff);
/*     */           }
/* 147 */           difference = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 152 */         if ((flag || !enumcreaturetype.d()) && (flag1 || enumcreaturetype.d()) && (flag2 || !enumcreaturetype.e()) && difference > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 159 */           Objects.requireNonNull((worldserver.getChunkProvider()).playerChunkMap); int spawnCount = spawnMobs(enumcreaturetype, worldserver, chunk, (entitytypes, blockposition, ichunkaccess) -> spawnercreature_d.a(entitytypes, blockposition, ichunkaccess), (entityinsentient, ichunkaccess) -> spawnercreature_d.a(entityinsentient, ichunkaccess), difference, worldserver.paperConfig.perPlayerMobSpawns ? (worldserver.getChunkProvider()).playerChunkMap::updatePlayerMobTypeMap : null);
/* 160 */           spawnercreature_d.getEntityCountsByType().mergeInt(enumcreaturetype, spawnCount, (keyInMap, valueInMap) -> Integer.valueOf(spawnCount + valueInMap.intValue()));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 167 */     worldserver.timings.mobSpawn.stopTiming();
/* 168 */     worldserver.getMethodProfiler().exit();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(EnumCreatureType enumcreaturetype, WorldServer worldserver, Chunk chunk, c spawnercreature_c, a spawnercreature_a) {
/* 173 */     spawnMobs(enumcreaturetype, worldserver, chunk, spawnercreature_c, spawnercreature_a, 2147483647, null);
/*     */   }
/*     */   
/*     */   public static int spawnMobs(EnumCreatureType enumcreaturetype, WorldServer worldserver, Chunk chunk, c spawnercreature_c, a spawnercreature_a, int maxSpawns, Consumer<Entity> trackEntity) {
/* 177 */     BlockPosition blockposition = getRandomPosition(worldserver, chunk);
/*     */     
/* 179 */     if (blockposition.getY() >= 1) {
/* 180 */       return spawnMobsInternal(enumcreaturetype, worldserver, chunk, blockposition, spawnercreature_c, spawnercreature_a, maxSpawns, trackEntity);
/*     */     }
/* 182 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(EnumCreatureType enumcreaturetype, WorldServer worldserver, IChunkAccess ichunkaccess, BlockPosition blockposition, c spawnercreature_c, a spawnercreature_a) {
/* 187 */     spawnMobsInternal(enumcreaturetype, worldserver, ichunkaccess, blockposition, spawnercreature_c, spawnercreature_a, 2147483647, null);
/*     */   }
/*     */   
/*     */   public static int spawnMobsInternal(EnumCreatureType enumcreaturetype, WorldServer worldserver, IChunkAccess ichunkaccess, BlockPosition blockposition, c spawnercreature_c, a spawnercreature_a, int maxSpawns, Consumer<Entity> trackEntity) {
/* 191 */     StructureManager structuremanager = worldserver.getStructureManager();
/* 192 */     ChunkGenerator chunkgenerator = worldserver.getChunkProvider().getChunkGenerator();
/* 193 */     int i = blockposition.getY();
/* 194 */     IBlockData iblockdata = worldserver.getTypeIfLoadedAndInBounds(blockposition);
/* 195 */     int j = 0;
/*     */     
/* 197 */     if (iblockdata != null && !iblockdata.isOccluding(ichunkaccess, blockposition)) {
/* 198 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */       
/* 200 */       int k = 0;
/*     */       
/* 202 */       while (k < 3) {
/* 203 */         int l = blockposition.getX();
/* 204 */         int i1 = blockposition.getZ();
/* 205 */         boolean flag = true;
/* 206 */         BiomeSettingsMobs.c biomesettingsmobs_c = null;
/* 207 */         GroupDataEntity groupdataentity = null;
/* 208 */         int j1 = MathHelper.f(worldserver.random.nextFloat() * 4.0F);
/* 209 */         int k1 = 0;
/* 210 */         int l1 = 0;
/*     */ 
/*     */         
/* 213 */         while (l1 < j1) {
/*     */ 
/*     */           
/* 216 */           l += worldserver.random.nextInt(6) - worldserver.random.nextInt(6);
/* 217 */           i1 += worldserver.random.nextInt(6) - worldserver.random.nextInt(6);
/* 218 */           blockposition_mutableblockposition.d(l, i, i1);
/* 219 */           double d0 = l + 0.5D;
/* 220 */           double d1 = i1 + 0.5D;
/* 221 */           EntityHuman entityhuman = worldserver.a(d0, i, d1, -1.0D, false);
/*     */           
/* 223 */           if (entityhuman != null) {
/* 224 */             double d2 = entityhuman.h(d0, i, d1);
/*     */             
/* 226 */             if (a(worldserver, ichunkaccess, blockposition_mutableblockposition, d2) && worldserver.isLoadedAndInBounds(blockposition_mutableblockposition)) {
/* 227 */               if (biomesettingsmobs_c == null) {
/* 228 */                 biomesettingsmobs_c = a(worldserver, structuremanager, chunkgenerator, enumcreaturetype, worldserver.random, blockposition_mutableblockposition);
/* 229 */                 if (biomesettingsmobs_c == null) {
/*     */                   break;
/*     */                 }
/*     */                 
/* 233 */                 j1 = biomesettingsmobs_c.d + worldserver.random.nextInt(1 + biomesettingsmobs_c.e - biomesettingsmobs_c.d);
/*     */               } 
/*     */ 
/*     */               
/* 237 */               Boolean doSpawning = a(worldserver, enumcreaturetype, structuremanager, chunkgenerator, biomesettingsmobs_c, blockposition_mutableblockposition, d2);
/* 238 */               if (doSpawning == null) {
/* 239 */                 return j;
/*     */               }
/* 241 */               if (doSpawning.booleanValue() && spawnercreature_c.test(biomesettingsmobs_c.c, blockposition_mutableblockposition, ichunkaccess)) {
/*     */                 
/* 243 */                 EntityInsentient entityinsentient = a(worldserver, biomesettingsmobs_c.c);
/*     */ 
/*     */                 
/* 246 */                 if (entityinsentient == null) {
/* 247 */                   return j;
/*     */                 }
/*     */                 
/* 250 */                 entityinsentient.setPositionRotation(d0, i, d1, worldserver.random.nextFloat() * 360.0F, 0.0F);
/* 251 */                 if (a(worldserver, entityinsentient, d2)) {
/* 252 */                   groupdataentity = entityinsentient.prepare(worldserver, worldserver.getDamageScaler(entityinsentient.getChunkCoordinates()), EnumMobSpawn.NATURAL, groupdataentity, (NBTTagCompound)null);
/*     */                   
/* 254 */                   if (worldserver.addAllEntities(entityinsentient, CreatureSpawnEvent.SpawnReason.NATURAL)) {
/* 255 */                     j++;
/* 256 */                     k1++;
/* 257 */                     spawnercreature_a.run(entityinsentient, ichunkaccess);
/*     */                     
/* 259 */                     if (trackEntity != null) {
/* 260 */                       trackEntity.accept(entityinsentient);
/*     */                     }
/*     */                   } 
/*     */ 
/*     */                   
/* 265 */                   if (j >= entityinsentient.getMaxSpawnGroup() || j >= maxSpawns) {
/* 266 */                     return j;
/*     */                   }
/*     */                   
/* 269 */                   if (entityinsentient.c(k1)) {
/*     */                     break;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 277 */           l1++;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 282 */         k++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 288 */     return j;
/*     */   }
/*     */   
/*     */   private static boolean a(WorldServer worldserver, IChunkAccess ichunkaccess, BlockPosition.MutableBlockPosition blockposition_mutableblockposition, double d0) {
/* 292 */     if (d0 <= 576.0D)
/* 293 */       return false; 
/* 294 */     if (worldserver.getSpawn().a(new Vec3D(blockposition_mutableblockposition.getX() + 0.5D, blockposition_mutableblockposition.getY(), blockposition_mutableblockposition.getZ() + 0.5D), 24.0D)) {
/* 295 */       return false;
/*     */     }
/* 297 */     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(blockposition_mutableblockposition);
/*     */     
/* 299 */     return (Objects.equals(chunkcoordintpair, ichunkaccess.getPos()) || worldserver.getChunkProvider().a(chunkcoordintpair));
/*     */   }
/*     */ 
/*     */   
/*     */   private static Boolean a(WorldServer worldserver, EnumCreatureType enumcreaturetype, StructureManager structuremanager, ChunkGenerator chunkgenerator, BiomeSettingsMobs.c biomesettingsmobs_c, BlockPosition.MutableBlockPosition blockposition_mutableblockposition, double d0) {
/* 304 */     EntityTypes<?> entitytypes = biomesettingsmobs_c.c;
/*     */ 
/*     */     
/* 307 */     EntityType type = EntityType.fromName(EntityTypes.getName(entitytypes).getKey());
/* 308 */     if (type != null) {
/*     */       
/* 310 */       PreCreatureSpawnEvent event = new PreCreatureSpawnEvent(MCUtil.toLocation(worldserver, blockposition_mutableblockposition), type, CreatureSpawnEvent.SpawnReason.NATURAL);
/*     */ 
/*     */       
/* 313 */       if (!event.callEvent()) {
/* 314 */         if (event.shouldAbortSpawn()) {
/* 315 */           return null;
/*     */         }
/* 317 */         return Boolean.valueOf(false);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 322 */     if (entitytypes.e() == EnumCreatureType.MISC)
/* 323 */       return Boolean.valueOf(false); 
/* 324 */     if (!entitytypes.d() && d0 > (entitytypes.e().f() * entitytypes.e().f()))
/* 325 */       return Boolean.valueOf(false); 
/* 326 */     if (entitytypes.b() && a(worldserver, structuremanager, chunkgenerator, enumcreaturetype, biomesettingsmobs_c, blockposition_mutableblockposition)) {
/* 327 */       EntityPositionTypes.Surface entitypositiontypes_surface = EntityPositionTypes.a(entitytypes);
/*     */       
/* 329 */       return Boolean.valueOf(!a(entitypositiontypes_surface, worldserver, blockposition_mutableblockposition, entitytypes) ? false : (!EntityPositionTypes.a(entitytypes, worldserver, EnumMobSpawn.NATURAL, blockposition_mutableblockposition, worldserver.random) ? false : worldserver.b(entitytypes.a(blockposition_mutableblockposition.getX() + 0.5D, blockposition_mutableblockposition.getY(), blockposition_mutableblockposition.getZ() + 0.5D))));
/*     */     } 
/* 331 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static EntityInsentient a(WorldServer worldserver, EntityTypes<?> entitytypes) {
/*     */     try {
/* 338 */       Entity entity = (Entity)entitytypes.a(worldserver);
/*     */       
/* 340 */       if (!(entity instanceof EntityInsentient)) {
/* 341 */         throw new IllegalStateException("Trying to spawn a non-mob: " + IRegistry.ENTITY_TYPE.getKey(entitytypes));
/*     */       }
/* 343 */       EntityInsentient entityinsentient = (EntityInsentient)entity;
/*     */       
/* 345 */       return entityinsentient;
/*     */     }
/* 347 */     catch (Exception exception) {
/* 348 */       LOGGER.warn("Failed to create mob", exception);
/* 349 */       ServerInternalException.reportInternalException(exception);
/* 350 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean a(WorldServer worldserver, EntityInsentient entityinsentient, double d0) {
/* 355 */     return (d0 > (entityinsentient.getEntityType().e().f() * entityinsentient.getEntityType().e().f()) && entityinsentient.isTypeNotPersistent(d0)) ? false : ((entityinsentient.a(worldserver, EnumMobSpawn.NATURAL) && entityinsentient.a(worldserver)));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static BiomeSettingsMobs.c a(WorldServer worldserver, StructureManager structuremanager, ChunkGenerator chunkgenerator, EnumCreatureType enumcreaturetype, Random random, BlockPosition blockposition) {
/* 360 */     BiomeBase biomebase = worldserver.getBiome(blockposition);
/*     */     
/* 362 */     if (enumcreaturetype == EnumCreatureType.WATER_AMBIENT && biomebase.t() == BiomeBase.Geography.RIVER && random.nextFloat() < 0.98F) {
/* 363 */       return null;
/*     */     }
/* 365 */     List<BiomeSettingsMobs.c> list = a(worldserver, structuremanager, chunkgenerator, enumcreaturetype, blockposition, biomebase);
/*     */     
/* 367 */     return list.isEmpty() ? null : WeightedRandom.<BiomeSettingsMobs.c>a(random, list);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(WorldServer worldserver, StructureManager structuremanager, ChunkGenerator chunkgenerator, EnumCreatureType enumcreaturetype, BiomeSettingsMobs.c biomesettingsmobs_c, BlockPosition blockposition) {
/* 372 */     return a(worldserver, structuremanager, chunkgenerator, enumcreaturetype, blockposition, (BiomeBase)null).contains(biomesettingsmobs_c);
/*     */   }
/*     */   
/*     */   private static List<BiomeSettingsMobs.c> a(WorldServer worldserver, StructureManager structuremanager, ChunkGenerator chunkgenerator, EnumCreatureType enumcreaturetype, BlockPosition blockposition, @Nullable BiomeBase biomebase) {
/* 376 */     return (enumcreaturetype == EnumCreatureType.MONSTER && worldserver.getType(blockposition.down()).getBlock() == Blocks.NETHER_BRICKS && structuremanager.a(blockposition, false, StructureGenerator.FORTRESS).e()) ? StructureGenerator.FORTRESS.c() : chunkgenerator.getMobsFor((biomebase != null) ? biomebase : worldserver.getBiome(blockposition), structuremanager, enumcreaturetype, blockposition);
/*     */   }
/*     */   
/*     */   private static BlockPosition getRandomPosition(World world, Chunk chunk) {
/* 380 */     ChunkCoordIntPair chunkcoordintpair = chunk.getPos();
/* 381 */     int i = chunkcoordintpair.d() + world.random.nextInt(16);
/* 382 */     int j = chunkcoordintpair.e() + world.random.nextInt(16);
/* 383 */     int k = chunk.getHighestBlock(HeightMap.Type.WORLD_SURFACE, i, j) + 1;
/* 384 */     int l = world.random.nextInt(k + 1);
/*     */     
/* 386 */     return new BlockPosition(i, l, j);
/*     */   }
/*     */   
/*     */   public static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, Fluid fluid, EntityTypes<?> entitytypes) {
/* 390 */     return iblockdata.r(iblockaccess, blockposition) ? false : (iblockdata.isPowerSource() ? false : (!fluid.isEmpty() ? false : (iblockdata.a(TagsBlock.PREVENT_MOB_SPAWNING_INSIDE) ? false : (!entitytypes.a(iblockdata)))));
/*     */   } @FunctionalInterface
/*     */   public static interface a {
/*     */     void run(EntityInsentient param1EntityInsentient, IChunkAccess param1IChunkAccess); } public static boolean a(EntityPositionTypes.Surface entitypositiontypes_surface, IWorldReader iworldreader, BlockPosition blockposition, @Nullable EntityTypes<?> entitytypes) {
/* 394 */     if (entitypositiontypes_surface == EntityPositionTypes.Surface.NO_RESTRICTIONS)
/* 395 */       return true; 
/* 396 */     if (entitytypes != null && iworldreader.getWorldBorder().a(blockposition)) {
/* 397 */       IBlockData iblockdata = iworldreader.getType(blockposition);
/* 398 */       Fluid fluid = iworldreader.getFluid(blockposition);
/* 399 */       BlockPosition blockposition1 = blockposition.up();
/* 400 */       BlockPosition blockposition2 = blockposition.down();
/*     */       
/* 402 */       switch (entitypositiontypes_surface) {
/*     */         case IN_WATER:
/* 404 */           return (fluid.a(TagsFluid.WATER) && iworldreader.getFluid(blockposition2).a(TagsFluid.WATER) && !iworldreader.getType(blockposition1).isOccluding(iworldreader, blockposition1));
/*     */         case IN_LAVA:
/* 406 */           return fluid.a(TagsFluid.LAVA);
/*     */       } 
/*     */       
/* 409 */       IBlockData iblockdata1 = iworldreader.getType(blockposition2);
/*     */       
/* 411 */       return !iblockdata1.a(iworldreader, blockposition2, entitytypes) ? false : ((a(iworldreader, blockposition, iblockdata, fluid, entitytypes) && a(iworldreader, blockposition1, iworldreader.getType(blockposition1), iworldreader.getFluid(blockposition1), entitytypes)));
/*     */     } 
/*     */     
/* 414 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(WorldAccess worldaccess, BiomeBase biomebase, int i, int j, Random random) {
/* 419 */     BiomeSettingsMobs biomesettingsmobs = biomebase.b();
/* 420 */     List<BiomeSettingsMobs.c> list = biomesettingsmobs.a(EnumCreatureType.CREATURE);
/*     */     
/* 422 */     if (!list.isEmpty()) {
/* 423 */       int k = i << 4;
/* 424 */       int l = j << 4;
/*     */       
/* 426 */       while (random.nextFloat() < biomesettingsmobs.a()) {
/* 427 */         BiomeSettingsMobs.c biomesettingsmobs_c = WeightedRandom.<BiomeSettingsMobs.c>a(random, list);
/* 428 */         int i1 = biomesettingsmobs_c.d + random.nextInt(1 + biomesettingsmobs_c.e - biomesettingsmobs_c.d);
/* 429 */         GroupDataEntity groupdataentity = null;
/* 430 */         int j1 = k + random.nextInt(16);
/* 431 */         int k1 = l + random.nextInt(16);
/* 432 */         int l1 = j1;
/* 433 */         int i2 = k1;
/*     */         
/* 435 */         for (int j2 = 0; j2 < i1; j2++) {
/* 436 */           boolean flag = false;
/*     */           
/* 438 */           for (int k2 = 0; !flag && k2 < 4; k2++) {
/* 439 */             BlockPosition blockposition = a(worldaccess, biomesettingsmobs_c.c, j1, k1);
/*     */             
/* 441 */             if (biomesettingsmobs_c.c.b() && a(EntityPositionTypes.a(biomesettingsmobs_c.c), worldaccess, blockposition, biomesettingsmobs_c.c)) {
/* 442 */               Entity entity; float f = biomesettingsmobs_c.c.j();
/* 443 */               double d0 = MathHelper.a(j1, k + f, k + 16.0D - f);
/* 444 */               double d1 = MathHelper.a(k1, l + f, l + 16.0D - f);
/*     */               
/* 446 */               if (!worldaccess.b(biomesettingsmobs_c.c.a(d0, blockposition.getY(), d1)) || !EntityPositionTypes.a(biomesettingsmobs_c.c, worldaccess, EnumMobSpawn.CHUNK_GENERATION, new BlockPosition(d0, blockposition.getY(), d1), worldaccess.getRandom())) {
/*     */                 continue;
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               try {
/* 453 */                 entity = (Entity)biomesettingsmobs_c.c.a(worldaccess.getMinecraftWorld());
/* 454 */               } catch (Exception exception) {
/* 455 */                 LOGGER.warn("Failed to create mob", exception);
/* 456 */                 ServerInternalException.reportInternalException(exception);
/*     */               } 
/*     */ 
/*     */               
/* 460 */               entity.setPositionRotation(d0, blockposition.getY(), d1, random.nextFloat() * 360.0F, 0.0F);
/* 461 */               if (entity instanceof EntityInsentient) {
/* 462 */                 EntityInsentient entityinsentient = (EntityInsentient)entity;
/*     */                 
/* 464 */                 if (entityinsentient.a(worldaccess, EnumMobSpawn.CHUNK_GENERATION) && entityinsentient.a(worldaccess)) {
/* 465 */                   groupdataentity = entityinsentient.prepare(worldaccess, worldaccess.getDamageScaler(entityinsentient.getChunkCoordinates()), EnumMobSpawn.CHUNK_GENERATION, groupdataentity, (NBTTagCompound)null);
/* 466 */                   worldaccess.addAllEntities(entityinsentient, CreatureSpawnEvent.SpawnReason.CHUNK_GEN);
/* 467 */                   flag = true;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             
/* 472 */             j1 += random.nextInt(5) - random.nextInt(5);
/*     */             
/* 474 */             for (k1 += random.nextInt(5) - random.nextInt(5); j1 < k || j1 >= k + 16 || k1 < l || k1 >= l + 16; k1 = i2 + random.nextInt(5) - random.nextInt(5))
/* 475 */               j1 = l1 + random.nextInt(5) - random.nextInt(5); 
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } @FunctionalInterface
/*     */   public static interface c {
/*     */     boolean test(EntityTypes<?> param1EntityTypes, BlockPosition param1BlockPosition, IChunkAccess param1IChunkAccess); }
/*     */   private static BlockPosition a(IWorldReader iworldreader, EntityTypes<?> entitytypes, int i, int j) {
/* 485 */     int k = iworldreader.a(EntityPositionTypes.b(entitytypes), i, j);
/* 486 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(i, k, j);
/*     */     
/* 488 */     if (iworldreader.getDimensionManager().hasCeiling()) {
/*     */       do {
/* 490 */         blockposition_mutableblockposition.c(EnumDirection.DOWN);
/* 491 */       } while (!iworldreader.getType(blockposition_mutableblockposition).isAir());
/*     */       
/*     */       do {
/* 494 */         blockposition_mutableblockposition.c(EnumDirection.DOWN);
/* 495 */       } while (iworldreader.getType(blockposition_mutableblockposition).isAir() && blockposition_mutableblockposition.getY() > 0);
/*     */     } 
/*     */     
/* 498 */     if (EntityPositionTypes.a(entitytypes) == EntityPositionTypes.Surface.ON_GROUND) {
/* 499 */       BlockPosition blockposition = blockposition_mutableblockposition.down();
/*     */       
/* 501 */       if (iworldreader.getType(blockposition).a(iworldreader, blockposition, PathMode.LAND)) {
/* 502 */         return blockposition;
/*     */       }
/*     */     } 
/*     */     
/* 506 */     return blockposition_mutableblockposition.immutableCopy();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class d
/*     */   {
/*     */     private final int a;
/*     */     
/*     */     private final Object2IntOpenHashMap<EnumCreatureType> b;
/*     */     
/*     */     private final SpawnerCreatureProbabilities c;
/*     */     
/*     */     private final Object2IntMap<EnumCreatureType> d;
/*     */     
/*     */     @Nullable
/*     */     private BlockPosition e;
/*     */     
/*     */     @Nullable
/*     */     private EntityTypes<?> f;
/*     */     private double g;
/*     */     
/*     */     final int getSpawnerChunks()
/*     */     {
/* 529 */       return this.a; } final Object2IntMap<EnumCreatureType> getEntityCountsByType() {
/* 530 */       return (Object2IntMap<EnumCreatureType>)this.b;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private d(int i, Object2IntOpenHashMap<EnumCreatureType> object2intopenhashmap, SpawnerCreatureProbabilities spawnercreatureprobabilities) {
/* 540 */       this.a = i;
/* 541 */       this.b = object2intopenhashmap;
/* 542 */       this.c = spawnercreatureprobabilities;
/* 543 */       this.d = Object2IntMaps.unmodifiable((Object2IntMap)object2intopenhashmap);
/*     */     }
/*     */     
/*     */     private boolean a(EntityTypes<?> entitytypes, BlockPosition blockposition, IChunkAccess ichunkaccess) {
/* 547 */       this.e = blockposition;
/* 548 */       this.f = entitytypes;
/* 549 */       BiomeSettingsMobs.b biomesettingsmobs_b = SpawnerCreature.b(blockposition, ichunkaccess).b().a(entitytypes);
/*     */       
/* 551 */       if (biomesettingsmobs_b == null) {
/* 552 */         this.g = 0.0D;
/* 553 */         return true;
/*     */       } 
/* 555 */       double d0 = biomesettingsmobs_b.b();
/*     */       
/* 557 */       this.g = d0;
/* 558 */       double d1 = this.c.b(blockposition, d0);
/*     */       
/* 560 */       return (d1 <= biomesettingsmobs_b.a());
/*     */     }
/*     */     
/*     */     private void a(EntityInsentient entityinsentient, IChunkAccess ichunkaccess) {
/*     */       double d0;
/* 565 */       EntityTypes<?> entitytypes = entityinsentient.getEntityType();
/* 566 */       BlockPosition blockposition = entityinsentient.getChunkCoordinates();
/*     */ 
/*     */       
/* 569 */       if (blockposition.equals(this.e) && entitytypes == this.f) {
/* 570 */         d0 = this.g;
/*     */       } else {
/* 572 */         BiomeSettingsMobs.b biomesettingsmobs_b = SpawnerCreature.b(blockposition, ichunkaccess).b().a(entitytypes);
/*     */         
/* 574 */         if (biomesettingsmobs_b != null) {
/* 575 */           d0 = biomesettingsmobs_b.b();
/*     */         } else {
/* 577 */           d0 = 0.0D;
/*     */         } 
/*     */       } 
/*     */       
/* 581 */       this.c.a(blockposition, d0);
/* 582 */       this.b.addTo(entitytypes.e(), 1);
/*     */     }
/*     */     
/*     */     public Object2IntMap<EnumCreatureType> b() {
/* 586 */       return this.d;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean a(EnumCreatureType enumcreaturetype, int limit) {
/* 591 */       int i = limit * this.a / SpawnerCreature.b;
/*     */ 
/*     */       
/* 594 */       return (this.b.getInt(enumcreaturetype) < i);
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface b {
/*     */     void query(long param1Long, Consumer<Chunk> param1Consumer);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SpawnerCreature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */