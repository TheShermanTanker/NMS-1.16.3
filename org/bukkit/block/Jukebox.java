package org.bukkit.block;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Jukebox extends TileState {
  @NotNull
  Material getPlaying();
  
  void setPlaying(@Nullable Material paramMaterial);
  
  @NotNull
  ItemStack getRecord();
  
  void setRecord(@Nullable ItemStack paramItemStack);
  
  boolean isPlaying();
  
  void stopPlaying();
  
  boolean eject();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Jukebox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */