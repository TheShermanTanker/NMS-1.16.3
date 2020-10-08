package org.bukkit.block.data;

import java.util.Set;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface MultipleFacing extends BlockData {
  boolean hasFace(@NotNull BlockFace paramBlockFace);
  
  void setFace(@NotNull BlockFace paramBlockFace, boolean paramBoolean);
  
  @NotNull
  Set<BlockFace> getFaces();
  
  @NotNull
  Set<BlockFace> getAllowedFaces();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\MultipleFacing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */