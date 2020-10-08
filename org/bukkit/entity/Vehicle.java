package org.bukkit.entity;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Vehicle extends Entity {
  @NotNull
  Vector getVelocity();
  
  void setVelocity(@NotNull Vector paramVector);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Vehicle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */