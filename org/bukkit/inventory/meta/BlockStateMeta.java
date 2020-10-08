package org.bukkit.inventory.meta;

import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

public interface BlockStateMeta extends ItemMeta {
  boolean hasBlockState();
  
  @NotNull
  BlockState getBlockState();
  
  void setBlockState(@NotNull BlockState paramBlockState);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\BlockStateMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */