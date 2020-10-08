package org.bukkit.block;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.Metadatable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockState extends Metadatable {
  @NotNull
  Block getBlock();
  
  @NotNull
  MaterialData getData();
  
  @NotNull
  BlockData getBlockData();
  
  @NotNull
  Material getType();
  
  byte getLightLevel();
  
  @NotNull
  World getWorld();
  
  int getX();
  
  int getY();
  
  int getZ();
  
  @NotNull
  Location getLocation();
  
  @Contract("null -> null; !null -> !null")
  @Nullable
  Location getLocation(@Nullable Location paramLocation);
  
  @NotNull
  Chunk getChunk();
  
  void setData(@NotNull MaterialData paramMaterialData);
  
  void setBlockData(@NotNull BlockData paramBlockData);
  
  void setType(@NotNull Material paramMaterial);
  
  boolean update();
  
  boolean update(boolean paramBoolean);
  
  boolean update(boolean paramBoolean1, boolean paramBoolean2);
  
  @Deprecated
  byte getRawData();
  
  @Deprecated
  void setRawData(byte paramByte);
  
  boolean isPlaced();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\BlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */