package org.bukkit.block;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public interface CreatureSpawner extends TileState {
  @NotNull
  EntityType getSpawnedType();
  
  void setSpawnedType(@NotNull EntityType paramEntityType);
  
  @Deprecated
  void setCreatureTypeByName(@NotNull String paramString);
  
  @Deprecated
  @NotNull
  String getCreatureTypeName();
  
  int getDelay();
  
  void setDelay(int paramInt);
  
  int getMinSpawnDelay();
  
  void setMinSpawnDelay(int paramInt);
  
  int getMaxSpawnDelay();
  
  void setMaxSpawnDelay(int paramInt);
  
  int getSpawnCount();
  
  void setSpawnCount(int paramInt);
  
  int getMaxNearbyEntities();
  
  void setMaxNearbyEntities(int paramInt);
  
  int getRequiredPlayerRange();
  
  void setRequiredPlayerRange(int paramInt);
  
  int getSpawnRange();
  
  void setSpawnRange(int paramInt);
  
  boolean isActivated();
  
  void resetTimer();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\CreatureSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */