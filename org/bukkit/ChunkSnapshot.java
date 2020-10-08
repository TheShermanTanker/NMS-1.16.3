package org.bukkit;

import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface ChunkSnapshot {
  int getX();
  
  int getZ();
  
  @NotNull
  String getWorldName();
  
  @NotNull
  Material getBlockType(int paramInt1, int paramInt2, int paramInt3);
  
  @NotNull
  BlockData getBlockData(int paramInt1, int paramInt2, int paramInt3);
  
  @Deprecated
  int getData(int paramInt1, int paramInt2, int paramInt3);
  
  int getBlockSkyLight(int paramInt1, int paramInt2, int paramInt3);
  
  int getBlockEmittedLight(int paramInt1, int paramInt2, int paramInt3);
  
  int getHighestBlockYAt(int paramInt1, int paramInt2);
  
  @Deprecated
  @NotNull
  Biome getBiome(int paramInt1, int paramInt2);
  
  @NotNull
  Biome getBiome(int paramInt1, int paramInt2, int paramInt3);
  
  @Deprecated
  double getRawBiomeTemperature(int paramInt1, int paramInt2);
  
  double getRawBiomeTemperature(int paramInt1, int paramInt2, int paramInt3);
  
  long getCaptureFullTime();
  
  boolean isSectionEmpty(int paramInt);
  
  boolean contains(@NotNull BlockData paramBlockData);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\ChunkSnapshot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */