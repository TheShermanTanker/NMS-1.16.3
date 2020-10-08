package org.bukkit.inventory.meta;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface BlockDataMeta extends ItemMeta {
  boolean hasBlockData();
  
  @NotNull
  BlockData getBlockData(@NotNull Material paramMaterial);
  
  void setBlockData(@NotNull BlockData paramBlockData);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\BlockDataMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */