/*     */ package org.bukkit.craftbukkit.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.server.v1_16_R2.BiomeStorage;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.DataPaletteBlock;
/*     */ import net.minecraft.server.v1_16_R2.HeightMap;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import org.bukkit.ChunkSnapshot;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ 
/*     */ 
/*     */ public class CraftChunkSnapshot
/*     */   implements ChunkSnapshot
/*     */ {
/*     */   private final int x;
/*     */   private final int z;
/*     */   private final String worldname;
/*     */   private final DataPaletteBlock<IBlockData>[] blockids;
/*     */   private final byte[][] skylight;
/*     */   private final byte[][] emitlight;
/*     */   private final boolean[] empty;
/*     */   private final HeightMap hmap;
/*     */   private final long captureFulltime;
/*     */   private final BiomeStorage biome;
/*     */   
/*     */   CraftChunkSnapshot(int x, int z, String wname, long wtime, DataPaletteBlock<IBlockData>[] sectionBlockIDs, byte[][] sectionSkyLights, byte[][] sectionEmitLights, boolean[] sectionEmpty, HeightMap hmap, BiomeStorage biome) {
/*  37 */     this.x = x;
/*  38 */     this.z = z;
/*  39 */     this.worldname = wname;
/*  40 */     this.captureFulltime = wtime;
/*  41 */     this.blockids = sectionBlockIDs;
/*  42 */     this.skylight = sectionSkyLights;
/*  43 */     this.emitlight = sectionEmitLights;
/*  44 */     this.empty = sectionEmpty;
/*  45 */     this.hmap = hmap;
/*  46 */     this.biome = biome;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/*  51 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getZ() {
/*  56 */     return this.z;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWorldName() {
/*  61 */     return this.worldname;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(BlockData block) {
/*  66 */     Preconditions.checkArgument((block != null), "Block cannot be null");
/*     */     
/*  68 */     Predicate predicate = Predicates.equalTo(((CraftBlockData)block).getState());
/*  69 */     for (DataPaletteBlock<IBlockData> palette : this.blockids) {
/*  70 */       if (palette.contains((Predicate)predicate)) {
/*  71 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getBlockType(int x, int y, int z) {
/*  80 */     CraftChunk.validateChunkCoordinates(x, y, z);
/*     */     
/*  82 */     return ((IBlockData)this.blockids[y >> 4].a(x, y & 0xF, z)).getBukkitMaterial();
/*     */   }
/*     */ 
/*     */   
/*     */   public final BlockData getBlockData(int x, int y, int z) {
/*  87 */     CraftChunk.validateChunkCoordinates(x, y, z);
/*     */     
/*  89 */     return (BlockData)CraftBlockData.fromData((IBlockData)this.blockids[y >> 4].a(x, y & 0xF, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getData(int x, int y, int z) {
/*  94 */     CraftChunk.validateChunkCoordinates(x, y, z);
/*     */     
/*  96 */     return CraftMagicNumbers.toLegacyData((IBlockData)this.blockids[y >> 4].a(x, y & 0xF, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getBlockSkyLight(int x, int y, int z) {
/* 101 */     CraftChunk.validateChunkCoordinates(x, y, z);
/*     */     
/* 103 */     int off = (y & 0xF) << 7 | z << 3 | x >> 1;
/* 104 */     return this.skylight[y >> 4][off] >> (x & 0x1) << 2 & 0xF;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getBlockEmittedLight(int x, int y, int z) {
/* 109 */     CraftChunk.validateChunkCoordinates(x, y, z);
/*     */     
/* 111 */     int off = (y & 0xF) << 7 | z << 3 | x >> 1;
/* 112 */     return this.emitlight[y >> 4][off] >> (x & 0x1) << 2 & 0xF;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getHighestBlockYAt(int x, int z) {
/* 117 */     Preconditions.checkState((this.hmap != null), "ChunkSnapshot created without height map. Please call getSnapshot with includeMaxblocky=true");
/* 118 */     CraftChunk.validateChunkCoordinates(x, 0, z);
/*     */     
/* 120 */     return this.hmap.a(x, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public final Biome getBiome(int x, int z) {
/* 125 */     return getBiome(x, 0, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public final Biome getBiome(int x, int y, int z) {
/* 130 */     Preconditions.checkState((this.biome != null), "ChunkSnapshot created without biome. Please call getSnapshot with includeBiome=true");
/* 131 */     CraftChunk.validateChunkCoordinates(x, y, z);
/*     */     
/* 133 */     return CraftBlock.biomeBaseToBiome((IRegistry)this.biome.g, this.biome.getBiome(x >> 2, y >> 2, z >> 2));
/*     */   }
/*     */ 
/*     */   
/*     */   public final double getRawBiomeTemperature(int x, int z) {
/* 138 */     return getRawBiomeTemperature(x, 0, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public final double getRawBiomeTemperature(int x, int y, int z) {
/* 143 */     Preconditions.checkState((this.biome != null), "ChunkSnapshot created without biome. Please call getSnapshot with includeBiome=true");
/* 144 */     CraftChunk.validateChunkCoordinates(x, y, z);
/*     */     
/* 146 */     return this.biome.getBiome(x >> 2, y >> 2, z >> 2).getAdjustedTemperature(new BlockPosition(this.x << 4 | x, y, this.z << 4 | z));
/*     */   }
/*     */ 
/*     */   
/*     */   public final long getCaptureFullTime() {
/* 151 */     return this.captureFulltime;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isSectionEmpty(int sy) {
/* 156 */     return this.empty[sy];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftChunkSnapshot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */