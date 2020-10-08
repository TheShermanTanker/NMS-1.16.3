/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.BitSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import org.spigotmc.SpigotWorldConfig;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ChunkGenerator
/*     */ {
/*     */   public ChunkGenerator(WorldChunkManager worldchunkmanager, StructureSettings structuresettings) {
/*  24 */     this(worldchunkmanager, worldchunkmanager, structuresettings, 0L);
/*     */   }
/*     */   
/*     */   public ChunkGenerator(WorldChunkManager worldchunkmanager, WorldChunkManager worldchunkmanager1, StructureSettings structuresettings, long i) {
/*  28 */     this.f = Lists.newArrayList();
/*  29 */     this.b = worldchunkmanager;
/*  30 */     this.c = worldchunkmanager1;
/*  31 */     this.structureSettings = structuresettings;
/*  32 */     this.e = i;
/*     */   }
/*     */   
/*     */   private void g() {
/*  36 */     if (this.f.isEmpty()) {
/*  37 */       StructureSettingsStronghold structuresettingsstronghold = this.structureSettings.b();
/*     */       
/*  39 */       if (structuresettingsstronghold != null && structuresettingsstronghold.c() != 0) {
/*  40 */         List<BiomeBase> list = Lists.newArrayList();
/*  41 */         Iterator<BiomeBase> iterator = this.b.b().iterator();
/*     */         
/*  43 */         while (iterator.hasNext()) {
/*  44 */           BiomeBase biomebase = iterator.next();
/*     */           
/*  46 */           if (biomebase.e().a(StructureGenerator.STRONGHOLD)) {
/*  47 */             list.add(biomebase);
/*     */           }
/*     */         } 
/*     */         
/*  51 */         int i = structuresettingsstronghold.a();
/*  52 */         int j = structuresettingsstronghold.c();
/*  53 */         int k = structuresettingsstronghold.b();
/*  54 */         Random random = new Random();
/*     */         
/*  56 */         random.setSeed(this.e);
/*  57 */         double d0 = random.nextDouble() * Math.PI * 2.0D;
/*  58 */         int l = 0;
/*  59 */         int i1 = 0;
/*     */         
/*  61 */         for (int j1 = 0; j1 < j; j1++) {
/*  62 */           double d1 = (4 * i + i * i1 * 6) + (random.nextDouble() - 0.5D) * i * 2.5D;
/*  63 */           int k1 = (int)Math.round(Math.cos(d0) * d1);
/*  64 */           int l1 = (int)Math.round(Math.sin(d0) * d1);
/*  65 */           Objects.requireNonNull(list); BlockPosition blockposition = this.b.a((k1 << 4) + 8, 0, (l1 << 4) + 8, 112, list::contains, random);
/*     */           
/*  67 */           if (blockposition != null) {
/*  68 */             k1 = blockposition.getX() >> 4;
/*  69 */             l1 = blockposition.getZ() >> 4;
/*     */           } 
/*     */           
/*  72 */           this.f.add(new ChunkCoordIntPair(k1, l1));
/*  73 */           d0 += 6.283185307179586D / k;
/*  74 */           l++;
/*  75 */           if (l == k) {
/*  76 */             i1++;
/*  77 */             l = 0;
/*  78 */             k += 2 * k / (i1 + 1);
/*  79 */             k = Math.min(k, j - j1);
/*  80 */             d0 += random.nextDouble() * Math.PI * 2.0D;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createBiomes(IRegistry<BiomeBase> iregistry, IChunkAccess ichunkaccess) {
/*  91 */     ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos();
/*     */     
/*  93 */     ((ProtoChunk)ichunkaccess).a(new BiomeStorage(iregistry, chunkcoordintpair, this.c));
/*     */   }
/*     */   
/*     */   public void doCarving(long i, BiomeManager biomemanager, IChunkAccess ichunkaccess, WorldGenStage.Features worldgenstage_features) {
/*  97 */     BiomeManager biomemanager1 = biomemanager.a(this.b);
/*  98 */     SeededRandom seededrandom = new SeededRandom();
/*  99 */     boolean flag = true;
/* 100 */     ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos();
/* 101 */     int j = chunkcoordintpair.x;
/* 102 */     int k = chunkcoordintpair.z;
/* 103 */     BiomeSettingsGeneration biomesettingsgeneration = this.b.getBiome(chunkcoordintpair.x << 2, 0, chunkcoordintpair.z << 2).e();
/* 104 */     BitSet bitset = ((ProtoChunk)ichunkaccess).b(worldgenstage_features);
/*     */     
/* 106 */     for (int l = j - 8; l <= j + 8; l++) {
/* 107 */       for (int i1 = k - 8; i1 <= k + 8; i1++) {
/* 108 */         List<Supplier<WorldGenCarverWrapper<?>>> list = biomesettingsgeneration.a(worldgenstage_features);
/* 109 */         ListIterator<Supplier<WorldGenCarverWrapper<?>>> listiterator = list.listIterator();
/*     */         
/* 111 */         while (listiterator.hasNext()) {
/* 112 */           int j1 = listiterator.nextIndex();
/* 113 */           WorldGenCarverWrapper<?> worldgencarverwrapper = ((Supplier<WorldGenCarverWrapper>)listiterator.next()).get();
/*     */           
/* 115 */           seededrandom.c(i + j1, l, i1);
/* 116 */           if (worldgencarverwrapper.a(seededrandom, l, i1)) {
/* 117 */             Objects.requireNonNull(biomemanager1); worldgencarverwrapper.a(ichunkaccess, biomemanager1::a, seededrandom, getSeaLevel(), l, i1, j, k, bitset);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition findNearestMapFeature(WorldServer worldserver, StructureGenerator<?> structuregenerator, BlockPosition blockposition, int i, boolean flag) {
/* 127 */     if (!this.b.a(structuregenerator))
/* 128 */       return null; 
/* 129 */     if (structuregenerator == StructureGenerator.STRONGHOLD) {
/* 130 */       g();
/* 131 */       BlockPosition blockposition1 = null;
/* 132 */       double d0 = Double.MAX_VALUE;
/* 133 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 134 */       Iterator<ChunkCoordIntPair> iterator = this.f.iterator();
/*     */       
/* 136 */       while (iterator.hasNext()) {
/* 137 */         ChunkCoordIntPair chunkcoordintpair = iterator.next();
/*     */         
/* 139 */         blockposition_mutableblockposition.d((chunkcoordintpair.x << 4) + 8, 32, (chunkcoordintpair.z << 4) + 8);
/* 140 */         double d1 = blockposition_mutableblockposition.j(blockposition);
/*     */         
/* 142 */         if (blockposition1 == null) {
/* 143 */           blockposition1 = new BlockPosition(blockposition_mutableblockposition);
/* 144 */           d0 = d1; continue;
/* 145 */         }  if (d1 < d0) {
/* 146 */           blockposition1 = new BlockPosition(blockposition_mutableblockposition);
/* 147 */           d0 = d1;
/*     */         } 
/*     */       } 
/*     */       
/* 151 */       return blockposition1;
/*     */     } 
/* 153 */     updateStructureSettings(worldserver, this.structureSettings);
/* 154 */     StructureSettingsFeature structuresettingsfeature = this.structureSettings.a(structuregenerator);
/*     */     
/* 156 */     return (structuresettingsfeature == null) ? null : structuregenerator.getNearestGeneratedFeature(worldserver, worldserver.getStructureManager(), blockposition, i, flag, worldserver.getSeed(), structuresettingsfeature);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDecorations(RegionLimitedWorldAccess regionlimitedworldaccess, StructureManager structuremanager) {
/* 161 */     int i = regionlimitedworldaccess.a();
/* 162 */     int j = regionlimitedworldaccess.b();
/* 163 */     int k = i * 16;
/* 164 */     int l = j * 16;
/* 165 */     BlockPosition blockposition = new BlockPosition(k, 0, l);
/* 166 */     BiomeBase biomebase = this.b.getBiome((i << 2) + 2, 2, (j << 2) + 2);
/* 167 */     SeededRandom seededrandom = new SeededRandom();
/* 168 */     long i1 = seededrandom.a(regionlimitedworldaccess.getSeed(), k, l);
/*     */     
/*     */     try {
/* 171 */       biomebase.a(structuremanager, this, regionlimitedworldaccess, i1, seededrandom, blockposition);
/* 172 */     } catch (Exception exception) {
/* 173 */       CrashReport crashreport = CrashReport.a(exception, "Biome decoration");
/*     */       
/* 175 */       crashreport.a("Generation").a("CenterX", Integer.valueOf(i)).a("CenterZ", Integer.valueOf(j)).a("Seed", Long.valueOf(i1)).a("Biome", biomebase);
/* 176 */       throw new ReportedException(crashreport);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMobs(RegionLimitedWorldAccess regionlimitedworldaccess) {}
/*     */ 
/*     */   
/*     */   public StructureSettings getSettings() {
/* 185 */     return this.structureSettings;
/*     */   }
/*     */   
/*     */   public int getSpawnHeight() {
/* 189 */     return 64;
/*     */   }
/*     */   
/*     */   public WorldChunkManager getWorldChunkManager() {
/* 193 */     return this.c;
/*     */   }
/*     */   
/*     */   public int getGenerationDepth() {
/* 197 */     return 256;
/*     */   }
/*     */   
/*     */   public List<BiomeSettingsMobs.c> getMobsFor(BiomeBase biomebase, StructureManager structuremanager, EnumCreatureType enumcreaturetype, BlockPosition blockposition) {
/* 201 */     return biomebase.b().a(enumcreaturetype);
/*     */   }
/*     */   
/*     */   public void createStructures(IRegistryCustom iregistrycustom, StructureManager structuremanager, IChunkAccess ichunkaccess, DefinedStructureManager definedstructuremanager, long i) {
/* 205 */     ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos();
/* 206 */     BiomeBase biomebase = this.b.getBiome((chunkcoordintpair.x << 2) + 2, 0, (chunkcoordintpair.z << 2) + 2);
/*     */     
/* 208 */     a(StructureFeatures.k, iregistrycustom, structuremanager, ichunkaccess, definedstructuremanager, i, chunkcoordintpair, biomebase);
/* 209 */     Iterator<Supplier<StructureFeature<?, ?>>> iterator = biomebase.e().a().iterator();
/*     */     
/* 211 */     while (iterator.hasNext()) {
/* 212 */       Supplier<StructureFeature<?, ?>> supplier = iterator.next();
/*     */ 
/*     */       
/* 215 */       StructureFeature<?, ?> structurefeature = supplier.get();
/* 216 */       if (StructureFeature.c == StructureGenerator.STRONGHOLD) {
/* 217 */         synchronized (structurefeature) {
/* 218 */           a(structurefeature, iregistrycustom, structuremanager, ichunkaccess, definedstructuremanager, i, chunkcoordintpair, biomebase);
/*     */         }  continue;
/*     */       } 
/* 221 */       a(structurefeature, iregistrycustom, structuremanager, ichunkaccess, definedstructuremanager, i, chunkcoordintpair, biomebase);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(StructureFeature<?, ?> structurefeature, IRegistryCustom iregistrycustom, StructureManager structuremanager, IChunkAccess ichunkaccess, DefinedStructureManager definedstructuremanager, long i, ChunkCoordIntPair chunkcoordintpair, BiomeBase biomebase) {
/* 229 */     StructureStart<?> structurestart = structuremanager.a(SectionPosition.a(ichunkaccess.getPos(), 0), (StructureGenerator<?>)structurefeature.d, ichunkaccess);
/* 230 */     int j = (structurestart != null) ? structurestart.j() : 0;
/* 231 */     updateStructureSettings(structuremanager.getWorld(), this.structureSettings);
/* 232 */     StructureSettingsFeature structuresettingsfeature = this.structureSettings.a((StructureGenerator<?>)structurefeature.d);
/*     */     
/* 234 */     if (structuresettingsfeature != null) {
/* 235 */       StructureStart<?> structurestart1 = structurefeature.a(iregistrycustom, this, this.b, definedstructuremanager, i, chunkcoordintpair, biomebase, j, structuresettingsfeature);
/*     */       
/* 237 */       structuremanager.a(SectionPosition.a(ichunkaccess.getPos(), 0), (StructureGenerator<?>)structurefeature.d, structurestart1, ichunkaccess);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateStructureSettings(World world, StructureSettings settings) {
/* 245 */     if (this.injected) {
/*     */       return;
/*     */     }
/* 248 */     synchronized (settings) {
/* 249 */       if (this.injected) {
/*     */         return;
/*     */       }
/* 252 */       Map<StructureGenerator<?>, StructureSettingsFeature> original = settings.a();
/* 253 */       Map<StructureGenerator<?>, StructureSettingsFeature> updated = new HashMap<>();
/* 254 */       SpigotWorldConfig conf = world.spigotConfig;
/*     */       
/* 256 */       for (Map.Entry<StructureGenerator<?>, StructureSettingsFeature> entry : original.entrySet()) {
/* 257 */         String name = IRegistry.STRUCTURE_FEATURE.getKey(entry.getKey()).getKey();
/* 258 */         StructureSettingsFeature feature = entry.getValue();
/* 259 */         int seed = feature.c();
/*     */         
/* 261 */         switch (name) {
/*     */           case "bastion_remnant":
/* 263 */             seed = conf.bastionSeed;
/*     */             break;
/*     */           case "desert_pyramid":
/* 266 */             seed = conf.desertSeed;
/*     */             break;
/*     */           case "endcity":
/* 269 */             seed = conf.endCitySeed;
/*     */             break;
/*     */           case "fortress":
/* 272 */             seed = conf.fortressSeed;
/*     */             break;
/*     */           case "igloo":
/* 275 */             seed = conf.iglooSeed;
/*     */             break;
/*     */           case "jungle_pyramid":
/* 278 */             seed = conf.jungleSeed;
/*     */             break;
/*     */           case "mansion":
/* 281 */             seed = conf.mansionSeed;
/*     */             break;
/*     */           case "monument":
/* 284 */             seed = conf.monumentSeed;
/*     */             break;
/*     */           case "nether_fossil":
/* 287 */             seed = conf.fossilSeed;
/*     */             break;
/*     */           case "ocean_ruin":
/* 290 */             seed = conf.oceanSeed;
/*     */             break;
/*     */           case "pillager_outpost":
/* 293 */             seed = conf.outpostSeed;
/*     */             break;
/*     */           case "ruined_portal":
/* 296 */             seed = conf.portalSeed;
/*     */             break;
/*     */           case "shipwreck":
/* 299 */             seed = conf.shipwreckSeed;
/*     */             break;
/*     */           case "swamp_hut":
/* 302 */             seed = conf.swampSeed;
/*     */             break;
/*     */           case "village":
/* 305 */             seed = conf.villageSeed;
/*     */             break;
/*     */         } 
/*     */         
/* 309 */         updated.put(entry.getKey(), new StructureSettingsFeature(feature.a(), feature.b(), seed));
/*     */       } 
/*     */       
/* 312 */       original.clear();
/* 313 */       original.putAll(updated);
/* 314 */       this.injected = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void storeStructures(GeneratorAccessSeed generatoraccessseed, StructureManager structuremanager, IChunkAccess ichunkaccess) {
/* 320 */     boolean flag = true;
/* 321 */     int i = (ichunkaccess.getPos()).x;
/* 322 */     int j = (ichunkaccess.getPos()).z;
/* 323 */     int k = i << 4;
/* 324 */     int l = j << 4;
/* 325 */     SectionPosition sectionposition = SectionPosition.a(ichunkaccess.getPos(), 0);
/*     */     
/* 327 */     for (int i1 = i - 8; i1 <= i + 8; i1++) {
/* 328 */       for (int j1 = j - 8; j1 <= j + 8; j1++) {
/* 329 */         long k1 = ChunkCoordIntPair.pair(i1, j1);
/* 330 */         Iterator<StructureStart> iterator = generatoraccessseed.getChunkAt(i1, j1).h().values().iterator();
/*     */         
/* 332 */         while (iterator.hasNext()) {
/* 333 */           StructureStart<?> structurestart = iterator.next();
/*     */           
/*     */           try {
/* 336 */             if (structurestart != StructureStart.a && structurestart.c().a(k, l, k + 15, l + 15)) {
/* 337 */               structuremanager.a(sectionposition, structurestart.l(), k1, ichunkaccess);
/* 338 */               PacketDebug.a(generatoraccessseed, structurestart);
/*     */             } 
/* 340 */           } catch (Exception exception) {
/* 341 */             CrashReport crashreport = CrashReport.a(exception, "Generating structure reference");
/* 342 */             CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Structure");
/*     */             
/* 344 */             crashreportsystemdetails.a("Id", () -> IRegistry.STRUCTURE_FEATURE.getKey(structurestart.l()).toString());
/*     */ 
/*     */             
/* 347 */             crashreportsystemdetails.a("Name", () -> structurestart.l().i());
/*     */ 
/*     */             
/* 350 */             crashreportsystemdetails.a("Class", () -> structurestart.l().getClass().getCanonicalName());
/*     */ 
/*     */             
/* 353 */             throw new ReportedException(crashreport);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeaLevel() {
/* 364 */     return 63;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b(int i, int j, HeightMap.Type heightmap_type) {
/* 372 */     return getBaseHeight(i, j, heightmap_type);
/*     */   }
/*     */   
/*     */   public int c(int i, int j, HeightMap.Type heightmap_type) {
/* 376 */     return getBaseHeight(i, j, heightmap_type) - 1;
/*     */   }
/*     */   
/*     */   public boolean a(ChunkCoordIntPair chunkcoordintpair) {
/* 380 */     g();
/* 381 */     return this.f.contains(chunkcoordintpair);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 386 */     IRegistry.a(IRegistry.CHUNK_GENERATOR, "noise", ChunkGeneratorAbstract.d);
/* 387 */     IRegistry.a(IRegistry.CHUNK_GENERATOR, "flat", ChunkProviderFlat.d);
/* 388 */     IRegistry.a(IRegistry.CHUNK_GENERATOR, "debug", ChunkProviderDebug.d);
/*     */   }
/* 390 */   public static final Codec<ChunkGenerator> a = IRegistry.CHUNK_GENERATOR.dispatchStable(ChunkGenerator::a, Function.identity());
/*     */   protected final WorldChunkManager b;
/*     */   protected final WorldChunkManager c;
/*     */   private final StructureSettings structureSettings;
/*     */   private final long e;
/*     */   private final List<ChunkCoordIntPair> f;
/*     */   private volatile boolean injected;
/*     */   
/*     */   protected abstract Codec<? extends ChunkGenerator> a();
/*     */   
/*     */   public abstract void buildBase(RegionLimitedWorldAccess paramRegionLimitedWorldAccess, IChunkAccess paramIChunkAccess);
/*     */   
/*     */   public abstract void buildNoise(GeneratorAccess paramGeneratorAccess, StructureManager paramStructureManager, IChunkAccess paramIChunkAccess);
/*     */   
/*     */   public abstract int getBaseHeight(int paramInt1, int paramInt2, HeightMap.Type paramType);
/*     */   
/*     */   public abstract IBlockAccess a(int paramInt1, int paramInt2);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */