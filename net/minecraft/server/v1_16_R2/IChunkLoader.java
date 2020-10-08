/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.io.PaperFileIOThread;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IChunkLoader
/*     */   implements AutoCloseable
/*     */ {
/*     */   protected final DataFixer b;
/*     */   @Nullable
/*     */   private volatile PersistentStructureLegacy c;
/*  20 */   private final Object persistentDataLock = new Object();
/*     */   protected final RegionFileCache regionFileCache;
/*     */   
/*     */   public IChunkLoader(File file, DataFixer datafixer, boolean flag) {
/*  24 */     this.regionFileCache = new RegionFileCache(file, flag, true);
/*  25 */     this.b = datafixer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean check(ChunkProviderServer cps, int x, int z) throws IOException {
/*  31 */     ChunkCoordIntPair pos = new ChunkCoordIntPair(x, z);
/*  32 */     if (cps != null)
/*     */     {
/*  34 */       if (cps.getChunkAtIfCachedImmediately(x, z) != null) {
/*  35 */         return true;
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     NBTTagCompound nbt = (cps == null) ? read(pos) : (
/*  43 */       PaperFileIOThread.Holder.INSTANCE.loadChunkData((WorldServer)cps.getWorld(), x, z, 1, false, true)).chunkData;
/*     */     
/*  45 */     if (nbt != null) {
/*  46 */       NBTTagCompound level = nbt.getCompound("Level");
/*  47 */       if (level.getBoolean("TerrainPopulated")) {
/*  48 */         return true;
/*     */       }
/*     */       
/*  51 */       ChunkStatus status = ChunkStatus.a(level.getString("Status"));
/*  52 */       if (status != null && status.b(ChunkStatus.FEATURES)) {
/*  53 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound getChunkData(ResourceKey<DimensionManager> resourcekey, Supplier<WorldPersistentData> supplier, NBTTagCompound nbttagcompound, ChunkCoordIntPair pos, @Nullable GeneratorAccess generatoraccess) throws IOException {
/*  62 */     int i = a(nbttagcompound);
/*  63 */     boolean flag = true;
/*     */ 
/*     */     
/*  66 */     if (i < 1466) {
/*  67 */       NBTTagCompound level = nbttagcompound.getCompound("Level");
/*  68 */       if (level.getBoolean("TerrainPopulated") && !level.getBoolean("LightPopulated")) {
/*  69 */         ChunkProviderServer cps = (generatoraccess == null) ? null : ((WorldServer)generatoraccess).getChunkProvider();
/*  70 */         if (check(cps, pos.x - 1, pos.z) && check(cps, pos.x - 1, pos.z - 1) && check(cps, pos.x, pos.z - 1)) {
/*  71 */           level.setBoolean("LightPopulated", true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  77 */     if (i < 1493) {
/*  78 */       nbttagcompound = GameProfileSerializer.a(this.b, DataFixTypes.CHUNK, nbttagcompound, i, 1493);
/*  79 */       if (nbttagcompound.getCompound("Level").getBoolean("hasLegacyStructureData")) {
/*  80 */         synchronized (this.persistentDataLock) {
/*  81 */           if (this.c == null) {
/*  82 */             this.c = PersistentStructureLegacy.a(resourcekey, supplier.get());
/*     */           }
/*     */           
/*  85 */           nbttagcompound = this.c.a(nbttagcompound);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  90 */     nbttagcompound = GameProfileSerializer.a(this.b, DataFixTypes.CHUNK, nbttagcompound, Math.max(1493, i));
/*  91 */     if (i < SharedConstants.getGameVersion().getWorldVersion()) {
/*  92 */       nbttagcompound.setInt("DataVersion", SharedConstants.getGameVersion().getWorldVersion());
/*     */     }
/*     */     
/*  95 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public static int a(NBTTagCompound nbttagcompound) {
/*  99 */     return nbttagcompound.hasKeyOfType("DataVersion", 99) ? nbttagcompound.getInt("DataVersion") : -1;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound read(ChunkCoordIntPair chunkcoordintpair) throws IOException {
/* 104 */     return this.regionFileCache.read(chunkcoordintpair);
/*     */   }
/*     */   public void a(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) throws IOException {
/* 107 */     write(chunkcoordintpair, nbttagcompound);
/*     */   }
/*     */   public void write(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) throws IOException {
/* 110 */     if (!chunkcoordintpair.equals(ChunkRegionLoader.getChunkCoordinate(nbttagcompound))) {
/* 111 */       String world = (this instanceof PlayerChunkMap) ? ((PlayerChunkMap)this).world.getWorld().getName() : null;
/* 112 */       throw new IllegalArgumentException("Chunk coordinate and serialized data do not have matching coordinates, trying to serialize coordinate " + chunkcoordintpair.toString() + " but compound says coordinate is " + 
/* 113 */           ChunkRegionLoader.getChunkCoordinate(nbttagcompound).toString() + ((world == null) ? " for an unknown world" : (" for world: " + world)));
/*     */     } 
/*     */     
/* 116 */     this.regionFileCache.write(chunkcoordintpair, nbttagcompound);
/* 117 */     if (this.c != null) {
/* 118 */       synchronized (this.persistentDataLock) {
/* 119 */         this.c.a(chunkcoordintpair.pair());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 125 */     this.regionFileCache.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IChunkLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */