package org.bukkit.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandBlock extends TileState {
  @NotNull
  String getCommand();
  
  void setCommand(@Nullable String paramString);
  
  @NotNull
  String getName();
  
  void setName(@Nullable String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\CommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */