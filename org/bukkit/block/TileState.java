package org.bukkit.block;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;

public interface TileState extends BlockState, PersistentDataHolder {
  @NotNull
  PersistentDataContainer getPersistentDataContainer();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\TileState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */