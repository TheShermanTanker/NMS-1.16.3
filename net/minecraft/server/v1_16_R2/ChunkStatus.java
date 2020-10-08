/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.IntList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ChunkStatus {
/*  17 */   private static final EnumSet<HeightMap.Type> n = EnumSet.of(HeightMap.Type.OCEAN_FLOOR_WG, HeightMap.Type.WORLD_SURFACE_WG);
/*  18 */   private static final EnumSet<HeightMap.Type> o = EnumSet.of(HeightMap.Type.OCEAN_FLOOR, HeightMap.Type.WORLD_SURFACE, HeightMap.Type.MOTION_BLOCKING, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES); private static final c p; static {
/*  19 */     p = ((chunkstatus, worldserver, definedstructuremanager, lightenginethreaded, function, ichunkaccess) -> {
/*     */         if (ichunkaccess instanceof ProtoChunk && !ichunkaccess.getChunkStatus().b(chunkstatus))
/*     */           ((ProtoChunk)ichunkaccess).a(chunkstatus); 
/*     */         return CompletableFuture.completedFuture(Either.left(ichunkaccess));
/*     */       });
/*     */   }
/*     */   
/*  26 */   public static final ChunkStatus EMPTY = a("empty", (ChunkStatus)null, -1, n, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> {
/*     */       
/*  28 */       }); public static final ChunkStatus STRUCTURE_STARTS; public static final ChunkStatus STRUCTURE_REFERENCES; public static final ChunkStatus BIOMES; public static final ChunkStatus NOISE; public static final ChunkStatus SURFACE; static { STRUCTURE_STARTS = a("structure_starts", EMPTY, 0, n, Type.PROTOCHUNK, (chunkstatus, worldserver, chunkgenerator, definedstructuremanager, lightenginethreaded, function, list, ichunkaccess) -> {
/*     */           if (!ichunkaccess.getChunkStatus().b(chunkstatus)) {
/*     */             if (worldserver.worldDataServer.getGeneratorSettings().shouldGenerateMapFeatures()) {
/*     */               chunkgenerator.createStructures(worldserver.r(), worldserver.getStructureManager(), ichunkaccess, definedstructuremanager, worldserver.getSeed());
/*     */             }
/*     */             
/*     */             if (ichunkaccess instanceof ProtoChunk) {
/*     */               ((ProtoChunk)ichunkaccess).a(chunkstatus);
/*     */             }
/*     */           } 
/*     */           
/*     */           return CompletableFuture.completedFuture(Either.left(ichunkaccess));
/*     */         });
/*  41 */     STRUCTURE_REFERENCES = a("structure_references", STRUCTURE_STARTS, 8, n, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> {
/*     */           RegionLimitedWorldAccess regionlimitedworldaccess = new RegionLimitedWorldAccess(worldserver, list);
/*     */           
/*     */           chunkgenerator.storeStructures(regionlimitedworldaccess, worldserver.getStructureManager().a(regionlimitedworldaccess), ichunkaccess);
/*     */         });
/*  46 */     BIOMES = a("biomes", STRUCTURE_REFERENCES, 0, n, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> chunkgenerator.createBiomes(worldserver.r().b(IRegistry.ay), ichunkaccess));
/*     */ 
/*     */     
/*  49 */     NOISE = a("noise", BIOMES, 8, n, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> {
/*     */           RegionLimitedWorldAccess regionlimitedworldaccess = new RegionLimitedWorldAccess(worldserver, list);
/*     */           
/*     */           chunkgenerator.buildNoise(regionlimitedworldaccess, worldserver.getStructureManager().a(regionlimitedworldaccess), ichunkaccess);
/*     */         });
/*  54 */     SURFACE = a("surface", NOISE, 0, n, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> chunkgenerator.buildBase(new RegionLimitedWorldAccess(worldserver, list), ichunkaccess));
/*     */ 
/*     */     
/*  57 */     CARVERS = a("carvers", SURFACE, 0, n, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> chunkgenerator.doCarving(worldserver.getSeed(), worldserver.d(), ichunkaccess, WorldGenStage.Features.AIR));
/*     */ 
/*     */     
/*  60 */     LIQUID_CARVERS = a("liquid_carvers", CARVERS, 0, o, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> chunkgenerator.doCarving(worldserver.getSeed(), worldserver.d(), ichunkaccess, WorldGenStage.Features.LIQUID));
/*     */ 
/*     */     
/*  63 */     FEATURES = a("features", LIQUID_CARVERS, 8, o, Type.PROTOCHUNK, (chunkstatus, worldserver, chunkgenerator, definedstructuremanager, lightenginethreaded, function, list, ichunkaccess) -> {
/*     */           ProtoChunk protochunk = (ProtoChunk)ichunkaccess;
/*     */           
/*     */           protochunk.a(lightenginethreaded);
/*     */           
/*     */           if (!ichunkaccess.getChunkStatus().b(chunkstatus)) {
/*     */             HeightMap.a(ichunkaccess, EnumSet.of(HeightMap.Type.MOTION_BLOCKING, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, HeightMap.Type.OCEAN_FLOOR, HeightMap.Type.WORLD_SURFACE));
/*     */             
/*     */             RegionLimitedWorldAccess regionlimitedworldaccess = new RegionLimitedWorldAccess(worldserver, list);
/*     */             chunkgenerator.addDecorations(regionlimitedworldaccess, worldserver.getStructureManager().a(regionlimitedworldaccess));
/*     */             protochunk.a(chunkstatus);
/*     */           } 
/*     */           return CompletableFuture.completedFuture(Either.left(ichunkaccess));
/*     */         });
/*  77 */     LIGHT = a("light", FEATURES, 1, o, Type.PROTOCHUNK, (chunkstatus, worldserver, chunkgenerator, definedstructuremanager, lightenginethreaded, function, list, ichunkaccess) -> a(chunkstatus, lightenginethreaded, ichunkaccess), (chunkstatus, worldserver, definedstructuremanager, lightenginethreaded, function, ichunkaccess) -> a(chunkstatus, lightenginethreaded, ichunkaccess));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     SPAWN = a("spawn", LIGHT, 0, o, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> chunkgenerator.addMobs(new RegionLimitedWorldAccess(worldserver, list))); }
/*     */   
/*     */   public static final ChunkStatus CARVERS; public static final ChunkStatus LIQUID_CARVERS; public static final ChunkStatus FEATURES; public static final ChunkStatus LIGHT; public static final ChunkStatus SPAWN;
/*  85 */   public static final ChunkStatus HEIGHTMAPS = a("heightmaps", SPAWN, 0, o, Type.PROTOCHUNK, (worldserver, chunkgenerator, list, ichunkaccess) -> {
/*     */       
/*  87 */       }); public static final ChunkStatus FULL; static { FULL = a("full", HEIGHTMAPS, 0, o, Type.LEVELCHUNK, (chunkstatus, worldserver, chunkgenerator, definedstructuremanager, lightenginethreaded, function, list, ichunkaccess) -> (CompletableFuture)function.apply(ichunkaccess), (chunkstatus, worldserver, definedstructuremanager, lightenginethreaded, function, ichunkaccess) -> (CompletableFuture)function.apply(ichunkaccess)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   private static final List<ChunkStatus> q = (List<ChunkStatus>)ImmutableList.of(FULL, FEATURES, LIQUID_CARVERS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS); private static final IntList r; private final String s; private final int t; private final ChunkStatus u; private final b v; private final c w; private final int x; private final Type y; private final EnumSet<HeightMap.Type> z; public final HeightMap.Type[] heightMaps; static {
/*  93 */     r = (IntList)SystemUtils.a(new IntArrayList(a().size()), intarraylist -> {
/*     */           int i = 0;
/*     */           for (int j = a().size() - 1; j >= 0; j--) {
/*     */             while (i + 1 < q.size() && j <= ((ChunkStatus)q.get(i + 1)).c()) {
/*     */               i++;
/*     */             }
/*     */             intarraylist.add(0, i);
/*     */           } 
/*     */         });
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
/*     */   private static CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> a(ChunkStatus chunkstatus, LightEngineThreaded lightenginethreaded, IChunkAccess ichunkaccess) {
/* 115 */     boolean flag = a(chunkstatus, ichunkaccess);
/*     */     
/* 117 */     if (!ichunkaccess.getChunkStatus().b(chunkstatus)) {
/* 118 */       ((ProtoChunk)ichunkaccess).a(chunkstatus);
/*     */     }
/*     */     
/* 121 */     return lightenginethreaded.a(ichunkaccess, flag).thenApply(Either::left);
/*     */   }
/*     */   
/*     */   private static ChunkStatus a(String s, @Nullable ChunkStatus chunkstatus, int i, EnumSet<HeightMap.Type> enumset, Type chunkstatus_type, d chunkstatus_d) {
/* 125 */     return a(s, chunkstatus, i, enumset, chunkstatus_type, chunkstatus_d);
/*     */   }
/*     */   
/*     */   private static ChunkStatus a(String s, @Nullable ChunkStatus chunkstatus, int i, EnumSet<HeightMap.Type> enumset, Type chunkstatus_type, b chunkstatus_b) {
/* 129 */     return a(s, chunkstatus, i, enumset, chunkstatus_type, chunkstatus_b, p);
/*     */   }
/*     */   
/*     */   private static ChunkStatus a(String s, @Nullable ChunkStatus chunkstatus, int i, EnumSet<HeightMap.Type> enumset, Type chunkstatus_type, b chunkstatus_b, c chunkstatus_c) {
/* 133 */     return IRegistry.<ChunkStatus>a(IRegistry.CHUNK_STATUS, s, new ChunkStatus(s, chunkstatus, i, enumset, chunkstatus_type, chunkstatus_b, chunkstatus_c));
/*     */   }
/*     */   
/*     */   public static List<ChunkStatus> a() {
/* 137 */     List<ChunkStatus> list = Lists.newArrayList();
/*     */     
/*     */     ChunkStatus chunkstatus;
/*     */     
/* 141 */     for (chunkstatus = FULL; chunkstatus.e() != chunkstatus; chunkstatus = chunkstatus.e()) {
/* 142 */       list.add(chunkstatus);
/*     */     }
/*     */     
/* 145 */     list.add(chunkstatus);
/* 146 */     Collections.reverse(list);
/* 147 */     return list;
/*     */   }
/*     */   
/*     */   private static boolean a(ChunkStatus chunkstatus, IChunkAccess ichunkaccess) {
/* 151 */     return (ichunkaccess.getChunkStatus().b(chunkstatus) && ichunkaccess.r());
/*     */   }
/*     */   
/*     */   public static ChunkStatus a(int i) {
/* 155 */     return (i >= q.size()) ? EMPTY : ((i < 0) ? FULL : q.get(i));
/*     */   }
/*     */   
/*     */   public static int b() {
/* 159 */     return q.size();
/*     */   }
/*     */   public static int getTicketLevelOffset(ChunkStatus status) {
/* 162 */     return a(status);
/*     */   } public static int a(ChunkStatus chunkstatus) {
/* 164 */     return r.getInt(chunkstatus.c());
/*     */   }
/*     */   
/*     */   ChunkStatus(String s, @Nullable ChunkStatus chunkstatus, int i, EnumSet<HeightMap.Type> enumset, Type chunkstatus_type, b chunkstatus_b, c chunkstatus_c) {
/* 168 */     this.s = s;
/* 169 */     this.u = (chunkstatus == null) ? this : chunkstatus;
/* 170 */     this.v = chunkstatus_b;
/* 171 */     this.w = chunkstatus_c;
/* 172 */     this.x = i;
/* 173 */     this.y = chunkstatus_type;
/* 174 */     this.z = enumset; this.heightMaps = (new ArrayList(this.z)).<HeightMap.Type>toArray(new HeightMap.Type[0]);
/* 175 */     this.t = (chunkstatus == null) ? 0 : (chunkstatus.c() + 1);
/*     */   }
/*     */   public final int getStatusIndex() {
/* 178 */     return c();
/*     */   } public int c() {
/* 180 */     return this.t;
/*     */   }
/*     */   
/*     */   public String d() {
/* 184 */     return this.s;
/*     */   }
/*     */   public final ChunkStatus getPreviousStatus() {
/* 187 */     return e();
/*     */   } public ChunkStatus e() {
/* 189 */     return this.u;
/*     */   }
/*     */   
/*     */   public CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> a(WorldServer worldserver, ChunkGenerator chunkgenerator, DefinedStructureManager definedstructuremanager, LightEngineThreaded lightenginethreaded, Function<IChunkAccess, CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>>> function, List<IChunkAccess> list) {
/* 193 */     return this.v.doWork(this, worldserver, chunkgenerator, definedstructuremanager, lightenginethreaded, function, list, list.get(list.size() / 2));
/*     */   }
/*     */   
/*     */   public CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> a(WorldServer worldserver, DefinedStructureManager definedstructuremanager, LightEngineThreaded lightenginethreaded, Function<IChunkAccess, CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>>> function, IChunkAccess ichunkaccess) {
/* 197 */     return this.w.doWork(this, worldserver, definedstructuremanager, lightenginethreaded, function, ichunkaccess);
/*     */   }
/*     */   public final int getNeighborRadius() {
/* 200 */     return f();
/*     */   } public int f() {
/* 202 */     return this.x;
/*     */   }
/*     */   
/*     */   public Type getType() {
/* 206 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ChunkStatus getStatus(String name) {
/*     */     try {
/* 213 */       MinecraftKey key = new MinecraftKey(name);
/* 214 */       return IRegistry.CHUNK_STATUS.getOptional(key).orElse(null);
/* 215 */     } catch (Exception ex) {
/* 216 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ChunkStatus a(String s) {
/* 221 */     return IRegistry.CHUNK_STATUS.get(MinecraftKey.a(s));
/*     */   }
/*     */   
/*     */   public EnumSet<HeightMap.Type> h() {
/* 225 */     return this.z;
/*     */   }
/*     */   public final boolean isAtLeastStatus(ChunkStatus chunkstatus) {
/* 228 */     return b(chunkstatus);
/*     */   } public boolean b(ChunkStatus chunkstatus) {
/* 230 */     return (c() >= chunkstatus.c());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 234 */     return IRegistry.CHUNK_STATUS.getKey(this).toString();
/*     */   }
/*     */   
/*     */   public enum Type
/*     */   {
/* 239 */     PROTOCHUNK, LEVELCHUNK; }
/*     */   
/*     */   static interface b {
/*     */     CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> doWork(ChunkStatus param1ChunkStatus, WorldServer param1WorldServer, ChunkGenerator param1ChunkGenerator, DefinedStructureManager param1DefinedStructureManager, LightEngineThreaded param1LightEngineThreaded, Function<IChunkAccess, CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>>> param1Function, List<IChunkAccess> param1List, IChunkAccess param1IChunkAccess);
/*     */   }
/*     */   
/*     */   static interface c { CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> doWork(ChunkStatus param1ChunkStatus, WorldServer param1WorldServer, DefinedStructureManager param1DefinedStructureManager, LightEngineThreaded param1LightEngineThreaded, Function<IChunkAccess, CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>>> param1Function, IChunkAccess param1IChunkAccess); }
/*     */   
/*     */   static interface d extends b { default CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>> doWork(ChunkStatus chunkstatus, WorldServer worldserver, ChunkGenerator chunkgenerator, DefinedStructureManager definedstructuremanager, LightEngineThreaded lightenginethreaded, Function<IChunkAccess, CompletableFuture<Either<IChunkAccess, PlayerChunk.Failure>>> function, List<IChunkAccess> list, IChunkAccess ichunkaccess) {
/* 248 */       if (!ichunkaccess.getChunkStatus().b(chunkstatus)) {
/* 249 */         doWork(worldserver, chunkgenerator, list, ichunkaccess);
/* 250 */         if (ichunkaccess instanceof ProtoChunk) {
/* 251 */           ((ProtoChunk)ichunkaccess).a(chunkstatus);
/*     */         }
/*     */       } 
/*     */       
/* 255 */       return CompletableFuture.completedFuture(Either.left(ichunkaccess));
/*     */     }
/*     */     
/*     */     void doWork(WorldServer param1WorldServer, ChunkGenerator param1ChunkGenerator, List<IChunkAccess> param1List, IChunkAccess param1IChunkAccess); }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */