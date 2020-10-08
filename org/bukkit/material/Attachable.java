package org.bukkit.material;

import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface Attachable extends Directional {
  @NotNull
  BlockFace getAttachedFace();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Attachable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */