package org.bukkit.block.data;

import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface Rotatable extends BlockData {
  @NotNull
  BlockFace getRotation();
  
  void setRotation(@NotNull BlockFace paramBlockFace);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\Rotatable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */