package org.bukkit.entity;

import org.bukkit.block.BlockFace;
import org.bukkit.material.Attachable;
import org.jetbrains.annotations.NotNull;

public interface Hanging extends Entity, Attachable {
  boolean setFacingDirection(@NotNull BlockFace paramBlockFace, boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Hanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */