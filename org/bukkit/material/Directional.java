package org.bukkit.material;

import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface Directional {
  void setFacingDirection(@NotNull BlockFace paramBlockFace);
  
  @NotNull
  BlockFace getFacing();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Directional.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */