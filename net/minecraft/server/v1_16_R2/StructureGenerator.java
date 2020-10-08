/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public abstract class StructureGenerator<C extends WorldGenFeatureConfiguration>
/*     */ {
/*  18 */   public static final BiMap<String, StructureGenerator<?>> a = (BiMap<String, StructureGenerator<?>>)HashBiMap.create();
/*  19 */   private static final Map<StructureGenerator<?>, WorldGenStage.Decoration> u = Maps.newHashMap();
/*  20 */   private static final Logger LOGGER = LogManager.getLogger();
/*  21 */   public static final StructureGenerator<WorldGenFeatureVillageConfiguration> PILLAGER_OUTPOST = a("Pillager_Outpost", new WorldGenFeaturePillagerOutpost(WorldGenFeatureVillageConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  22 */   public static final StructureGenerator<WorldGenMineshaftConfiguration> MINESHAFT = a("Mineshaft", new WorldGenMineshaft(WorldGenMineshaftConfiguration.a), WorldGenStage.Decoration.UNDERGROUND_STRUCTURES);
/*  23 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> MANSION = a("Mansion", new WorldGenWoodlandMansion(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  24 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> JUNGLE_PYRAMID = a("Jungle_Pyramid", new WorldGenFeatureJunglePyramid(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  25 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> DESERT_PYRAMID = a("Desert_Pyramid", new WorldGenFeatureDesertPyramid(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  26 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> IGLOO = a("Igloo", new WorldGenFeatureIgloo(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  27 */   public static final StructureGenerator<WorldGenFeatureRuinedPortalConfiguration> RUINED_PORTAL = a("Ruined_Portal", new WorldGenFeatureRuinedPortal(WorldGenFeatureRuinedPortalConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  28 */   public static final StructureGenerator<WorldGenFeatureShipwreckConfiguration> SHIPWRECK = a("Shipwreck", new WorldGenFeatureShipwreck(WorldGenFeatureShipwreckConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  29 */   public static final WorldGenFeatureSwampHut SWAMP_HUT = a("Swamp_Hut", new WorldGenFeatureSwampHut(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  30 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> STRONGHOLD = a("Stronghold", new WorldGenStronghold(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.STRONGHOLDS);
/*  31 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> MONUMENT = a("Monument", new WorldGenMonument(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  32 */   public static final StructureGenerator<WorldGenFeatureOceanRuinConfiguration> OCEAN_RUIN = a("Ocean_Ruin", new WorldGenFeatureOceanRuin(WorldGenFeatureOceanRuinConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  33 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> FORTRESS = a("Fortress", new WorldGenNether(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.UNDERGROUND_DECORATION);
/*  34 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> ENDCITY = a("EndCity", new WorldGenEndCity(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  35 */   public static final StructureGenerator<WorldGenFeatureConfigurationChance> BURIED_TREASURE = a("Buried_Treasure", new WorldGenBuriedTreasure(WorldGenFeatureConfigurationChance.b), WorldGenStage.Decoration.UNDERGROUND_STRUCTURES);
/*  36 */   public static final StructureGenerator<WorldGenFeatureVillageConfiguration> VILLAGE = a("Village", new WorldGenVillage(WorldGenFeatureVillageConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  37 */   public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> NETHER_FOSSIL = a("Nether_Fossil", new WorldGenFeatureNetherFossil(WorldGenFeatureEmptyConfiguration.a), WorldGenStage.Decoration.UNDERGROUND_DECORATION);
/*  38 */   public static final StructureGenerator<WorldGenFeatureVillageConfiguration> BASTION_REMNANT = a("Bastion_Remnant", new WorldGenFeatureBastionRemnant(WorldGenFeatureVillageConfiguration.a), WorldGenStage.Decoration.SURFACE_STRUCTURES);
/*  39 */   public static final List<StructureGenerator<?>> t = (List<StructureGenerator<?>>)ImmutableList.of(PILLAGER_OUTPOST, VILLAGE, NETHER_FOSSIL);
/*  40 */   private static final MinecraftKey w = new MinecraftKey("jigsaw");
/*  41 */   private static final Map<MinecraftKey, MinecraftKey> x = (Map<MinecraftKey, MinecraftKey>)ImmutableMap.builder().put(new MinecraftKey("nvi"), w).put(new MinecraftKey("pcp"), w).put(new MinecraftKey("bastionremnant"), w).put(new MinecraftKey("runtime"), w).build();
/*     */   private final Codec<StructureFeature<C, StructureGenerator<C>>> y;
/*     */   
/*     */   private static <F extends StructureGenerator<?>> F a(String s, F f0, WorldGenStage.Decoration worldgenstage_decoration) {
/*  45 */     a.put(s.toLowerCase(Locale.ROOT), f0);
/*  46 */     u.put((StructureGenerator<?>)f0, worldgenstage_decoration);
/*  47 */     return (F)IRegistry.<StructureGenerator>a((IRegistry)IRegistry.STRUCTURE_FEATURE, s.toLowerCase(Locale.ROOT), (StructureGenerator)f0);
/*     */   }
/*     */   
/*     */   public StructureGenerator(Codec<C> codec) {
/*  51 */     this
/*     */ 
/*     */ 
/*     */       
/*  55 */       .y = codec.fieldOf("config").xmap(worldgenfeatureconfiguration -> new StructureFeature<>(this, worldgenfeatureconfiguration), structurefeature -> structurefeature.e).codec();
/*     */   }
/*     */   
/*     */   public WorldGenStage.Decoration f() {
/*  59 */     return u.get(this);
/*     */   }
/*     */   
/*     */   public static void g() {}
/*     */   
/*     */   @Nullable
/*     */   public static StructureStart<?> a(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound, long i) {
/*  66 */     String s = nbttagcompound.getString("id");
/*     */     
/*  68 */     if ("INVALID".equals(s)) {
/*  69 */       return StructureStart.a;
/*     */     }
/*  71 */     StructureGenerator<?> structuregenerator = IRegistry.STRUCTURE_FEATURE.get(new MinecraftKey(s.toLowerCase(Locale.ROOT)));
/*     */     
/*  73 */     if (structuregenerator == null) {
/*  74 */       LOGGER.error("Unknown feature id: {}", s);
/*  75 */       return null;
/*     */     } 
/*  77 */     int j = nbttagcompound.getInt("ChunkX");
/*  78 */     int k = nbttagcompound.getInt("ChunkZ");
/*  79 */     int l = nbttagcompound.getInt("references");
/*  80 */     StructureBoundingBox structureboundingbox = nbttagcompound.hasKey("BB") ? new StructureBoundingBox(nbttagcompound.getIntArray("BB")) : StructureBoundingBox.a();
/*  81 */     NBTTagList nbttaglist = nbttagcompound.getList("Children", 10);
/*     */     
/*     */     try {
/*  84 */       StructureStart<?> structurestart = structuregenerator.a(j, k, structureboundingbox, l, i);
/*     */       
/*  86 */       for (int i1 = 0; i1 < nbttaglist.size(); i1++) {
/*  87 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(i1);
/*  88 */         String s1 = nbttagcompound1.getString("id").toLowerCase(Locale.ROOT);
/*  89 */         MinecraftKey minecraftkey = new MinecraftKey(s1);
/*  90 */         MinecraftKey minecraftkey1 = x.getOrDefault(minecraftkey, minecraftkey);
/*  91 */         WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype = IRegistry.STRUCTURE_PIECE.get(minecraftkey1);
/*     */         
/*  93 */         if (worldgenfeaturestructurepiecetype == null) {
/*  94 */           LOGGER.error("Unknown structure piece id: {}", minecraftkey1);
/*     */         } else {
/*     */           try {
/*  97 */             StructurePiece structurepiece = worldgenfeaturestructurepiecetype.load(definedstructuremanager, nbttagcompound1);
/*     */             
/*  99 */             structurestart.d().add(structurepiece);
/* 100 */           } catch (Exception exception) {
/* 101 */             LOGGER.error("Exception loading structure piece with id {}", minecraftkey1, exception);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 106 */       return structurestart;
/* 107 */     } catch (Exception exception1) {
/* 108 */       LOGGER.error("Failed Start with id {}", s, exception1);
/* 109 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Codec<StructureFeature<C, StructureGenerator<C>>> h() {
/* 116 */     return this.y;
/*     */   }
/*     */   
/*     */   public StructureFeature<C, ? extends StructureGenerator<C>> a(C c0) {
/* 120 */     return new StructureFeature<>(this, c0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition getNearestGeneratedFeature(IWorldReader iworldreader, StructureManager structuremanager, BlockPosition blockposition, int i, boolean flag, long j, StructureSettingsFeature structuresettingsfeature) {
/* 125 */     int k = structuresettingsfeature.a();
/* 126 */     int l = blockposition.getX() >> 4;
/* 127 */     int i1 = blockposition.getZ() >> 4;
/* 128 */     int j1 = 0;
/* 129 */     SeededRandom seededrandom = new SeededRandom();
/*     */     
/* 131 */     while (j1 <= i) {
/* 132 */       int k1 = -j1;
/*     */ 
/*     */       
/* 135 */       while (k1 <= j1) {
/* 136 */         boolean flag1 = (k1 == -j1 || k1 == j1);
/*     */         
/* 138 */         for (int l1 = -j1; l1 <= j1; l1++) {
/* 139 */           boolean flag2 = (l1 == -j1 || l1 == j1);
/*     */           
/* 141 */           if (flag1 || flag2) {
/* 142 */             int i2 = l + k * k1;
/* 143 */             int j2 = i1 + k * l1;
/* 144 */             ChunkCoordIntPair chunkcoordintpair = a(structuresettingsfeature, j, seededrandom, i2, j2);
/* 145 */             if (iworldreader.getWorldBorder().isChunkInBounds(chunkcoordintpair.x, chunkcoordintpair.z)) {
/* 146 */               IChunkAccess ichunkaccess = iworldreader.getChunkAt(chunkcoordintpair.x, chunkcoordintpair.z, ChunkStatus.STRUCTURE_STARTS);
/* 147 */               StructureStart<?> structurestart = structuremanager.a(SectionPosition.a(ichunkaccess.getPos(), 0), this, ichunkaccess);
/*     */               
/* 149 */               if (structurestart != null && structurestart.e()) {
/* 150 */                 if (flag && structurestart.h()) {
/* 151 */                   structurestart.i();
/* 152 */                   return structurestart.a();
/*     */                 } 
/*     */                 
/* 155 */                 if (!flag) {
/* 156 */                   return structurestart.a();
/*     */                 }
/*     */               } 
/*     */               
/* 160 */               if (j1 == 0) {
/*     */                 break;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/* 166 */         if (j1 != 0) {
/* 167 */           k1++;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 172 */       j1++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 177 */     return null;
/*     */   }
/*     */   
/*     */   protected boolean b() {
/* 181 */     return true;
/*     */   }
/*     */   
/*     */   public final ChunkCoordIntPair a(StructureSettingsFeature structuresettingsfeature, long i, SeededRandom seededrandom, int j, int k) {
/* 185 */     int l1, i2, l = structuresettingsfeature.a();
/* 186 */     int i1 = structuresettingsfeature.b();
/* 187 */     int j1 = Math.floorDiv(j, l);
/* 188 */     int k1 = Math.floorDiv(k, l);
/*     */     
/* 190 */     seededrandom.a(i, j1, k1, structuresettingsfeature.c());
/*     */ 
/*     */ 
/*     */     
/* 194 */     if (b()) {
/* 195 */       l1 = seededrandom.nextInt(l - i1);
/* 196 */       i2 = seededrandom.nextInt(l - i1);
/*     */     } else {
/* 198 */       l1 = (seededrandom.nextInt(l - i1) + seededrandom.nextInt(l - i1)) / 2;
/* 199 */       i2 = (seededrandom.nextInt(l - i1) + seededrandom.nextInt(l - i1)) / 2;
/*     */     } 
/*     */     
/* 202 */     return new ChunkCoordIntPair(j1 * l + l1, k1 * l + i2);
/*     */   }
/*     */   
/*     */   protected boolean a(ChunkGenerator chunkgenerator, WorldChunkManager worldchunkmanager, long i, SeededRandom seededrandom, int j, int k, BiomeBase biomebase, ChunkCoordIntPair chunkcoordintpair, C c0) {
/* 206 */     return true;
/*     */   }
/*     */   
/*     */   private StructureStart<C> a(int i, int j, StructureBoundingBox structureboundingbox, int k, long l) {
/* 210 */     return a().create(this, i, j, structureboundingbox, k, l);
/*     */   }
/*     */   
/*     */   public StructureStart<?> a(IRegistryCustom iregistrycustom, ChunkGenerator chunkgenerator, WorldChunkManager worldchunkmanager, DefinedStructureManager definedstructuremanager, long i, ChunkCoordIntPair chunkcoordintpair, BiomeBase biomebase, int j, SeededRandom seededrandom, StructureSettingsFeature structuresettingsfeature, C c0) {
/* 214 */     ChunkCoordIntPair chunkcoordintpair1 = a(structuresettingsfeature, i, seededrandom, chunkcoordintpair.x, chunkcoordintpair.z);
/*     */     
/* 216 */     if (chunkcoordintpair.x == chunkcoordintpair1.x && chunkcoordintpair.z == chunkcoordintpair1.z && a(chunkgenerator, worldchunkmanager, i, seededrandom, chunkcoordintpair.x, chunkcoordintpair.z, biomebase, chunkcoordintpair1, c0)) {
/* 217 */       StructureStart<C> structurestart = a(chunkcoordintpair.x, chunkcoordintpair.z, StructureBoundingBox.a(), j, i);
/*     */       
/* 219 */       structurestart.a(iregistrycustom, chunkgenerator, definedstructuremanager, chunkcoordintpair.x, chunkcoordintpair.z, biomebase, c0);
/* 220 */       if (structurestart.e()) {
/* 221 */         return structurestart;
/*     */       }
/*     */     } 
/*     */     
/* 225 */     return StructureStart.a;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String i() {
/* 231 */     return (String)a.inverse().get(this);
/*     */   }
/*     */   
/*     */   public List<BiomeSettingsMobs.c> c() {
/* 235 */     return (List<BiomeSettingsMobs.c>)ImmutableList.of();
/*     */   }
/*     */   
/*     */   public List<BiomeSettingsMobs.c> j() {
/* 239 */     return (List<BiomeSettingsMobs.c>)ImmutableList.of();
/*     */   }
/*     */   
/*     */   public abstract a<C> a();
/*     */   
/*     */   public static interface a<C extends WorldGenFeatureConfiguration> {
/*     */     StructureStart<C> create(StructureGenerator<C> param1StructureGenerator, int param1Int1, int param1Int2, StructureBoundingBox param1StructureBoundingBox, int param1Int3, long param1Long);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */