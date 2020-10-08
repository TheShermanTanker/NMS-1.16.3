package org.bukkit.block.data;

import java.util.Set;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface Directional extends BlockData {
  @NotNull
  BlockFace getFacing();
  
  void setFacing(@NotNull BlockFace paramBlockFace);
  
  @NotNull
  Set<BlockFace> getFaces();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\Directional.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */