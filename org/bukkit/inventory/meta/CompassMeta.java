package org.bukkit.inventory.meta;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public interface CompassMeta extends ItemMeta {
  boolean hasLodestone();
  
  @Nullable
  Location getLodestone();
  
  void setLodestone(@Nullable Location paramLocation);
  
  boolean isLodestoneTracked();
  
  void setLodestoneTracked(boolean paramBoolean);
  
  CompassMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\CompassMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */