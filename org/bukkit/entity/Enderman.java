package org.bukkit.entity;

import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Enderman extends Monster {
  boolean teleportRandomly();
  
  @NotNull
  MaterialData getCarriedMaterial();
  
  void setCarriedMaterial(@NotNull MaterialData paramMaterialData);
  
  @Nullable
  BlockData getCarriedBlock();
  
  void setCarriedBlock(@Nullable BlockData paramBlockData);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Enderman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */