package org.bukkit.inventory.meta;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface SpawnEggMeta extends ItemMeta {
  @Deprecated
  @Contract("-> fail")
  EntityType getSpawnedType();
  
  @Deprecated
  @Contract("_ -> fail")
  void setSpawnedType(EntityType paramEntityType);
  
  @NotNull
  SpawnEggMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\SpawnEggMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */