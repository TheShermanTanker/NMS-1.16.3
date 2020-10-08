package org.bukkit.block.data;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockData extends Cloneable {
  @NotNull
  Material getMaterial();
  
  @NotNull
  String getAsString();
  
  @NotNull
  String getAsString(boolean paramBoolean);
  
  @NotNull
  BlockData merge(@NotNull BlockData paramBlockData);
  
  boolean matches(@Nullable BlockData paramBlockData);
  
  @NotNull
  BlockData clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\BlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */