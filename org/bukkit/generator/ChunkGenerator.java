/*     */ package org.bukkit.generator;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ChunkGenerator
/*     */ {
/*     */   @NotNull
/*     */   public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int z, @NotNull BiomeGrid biome) {
/* 117 */     throw new UnsupportedOperationException("Custom generator " + getClass().getName() + " is missing required method generateChunkData");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected final ChunkData createChunkData(@NotNull World world) {
/* 127 */     return Bukkit.getServer().createChunkData(world);
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
/*     */   public boolean canSpawn(@NotNull World world, int x, int z) {
/* 139 */     Block highest = world.getBlockAt(x, world.getHighestBlockYAt(x, z), z);
/*     */     
/* 141 */     switch (world.getEnvironment()) {
/*     */       case NETHER:
/* 143 */         return true;
/*     */       case THE_END:
/* 145 */         return (highest.getType() != Material.AIR && highest.getType() != Material.WATER && highest.getType() != Material.LAVA);
/*     */     } 
/*     */     
/* 148 */     return (highest.getType() == Material.SAND || highest.getType() == Material.GRAVEL);
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
/*     */   @NotNull
/*     */   public List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
/* 161 */     return new ArrayList<>();
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
/*     */   @Nullable
/*     */   public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
/* 176 */     return null;
/*     */   } public static interface ChunkData {
/*     */     int getMaxHeight(); void setBlock(int param1Int1, int param1Int2, int param1Int3, @NotNull Material param1Material); void setBlock(int param1Int1, int param1Int2, int param1Int3, @NotNull MaterialData param1MaterialData); void setBlock(int param1Int1, int param1Int2, int param1Int3, @NotNull BlockData param1BlockData); void setRegion(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, @NotNull Material param1Material); void setRegion(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, @NotNull MaterialData param1MaterialData); void setRegion(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, @NotNull BlockData param1BlockData); @NotNull
/*     */     Material getType(int param1Int1, int param1Int2, int param1Int3);
/*     */     @NotNull
/*     */     MaterialData getTypeAndData(int param1Int1, int param1Int2, int param1Int3);
/*     */     @NotNull
/*     */     BlockData getBlockData(int param1Int1, int param1Int2, int param1Int3);
/*     */     @Deprecated
/*     */     byte getData(int param1Int1, int param1Int2, int param1Int3); }
/*     */   public boolean isParallelCapable() {
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldGenerateCaves() {
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldGenerateDecorations() {
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldGenerateMobs() {
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldGenerateStructures() {
/* 227 */     return false;
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
/*     */   @NotNull
/*     */   public ChunkData createVanillaChunkData(@NotNull World world, int x, int z) {
/* 242 */     return Bukkit.getServer().createVanillaChunkData(world, x, z);
/*     */   }
/*     */   
/*     */   public static interface BiomeGrid {
/*     */     @Deprecated
/*     */     @NotNull
/*     */     Biome getBiome(int param1Int1, int param1Int2);
/*     */     
/*     */     @NotNull
/*     */     Biome getBiome(int param1Int1, int param1Int2, int param1Int3);
/*     */     
/*     */     @Deprecated
/*     */     void setBiome(int param1Int1, int param1Int2, @NotNull Biome param1Biome);
/*     */     
/*     */     void setBiome(int param1Int1, int param1Int2, int param1Int3, @NotNull Biome param1Biome);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\generator\ChunkGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */