package org.bukkit.block;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface EntityBlockStorage<T extends org.bukkit.entity.Entity> extends TileState {
  boolean isFull();
  
  int getEntityCount();
  
  int getMaxEntities();
  
  void setMaxEntities(int paramInt);
  
  @NotNull
  List<T> releaseEntities();
  
  void addEntity(@NotNull T paramT);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\EntityBlockStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */