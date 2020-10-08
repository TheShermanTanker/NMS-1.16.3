package org.bukkit.block;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public interface EndGateway extends TileState {
  @Nullable
  Location getExitLocation();
  
  void setExitLocation(@Nullable Location paramLocation);
  
  boolean isExactTeleport();
  
  void setExactTeleport(boolean paramBoolean);
  
  long getAge();
  
  void setAge(long paramLong);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\EndGateway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */