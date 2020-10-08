package org.bukkit.entity;

import org.bukkit.TreeSpecies;
import org.jetbrains.annotations.NotNull;

public interface Boat extends Vehicle {
  @NotNull
  TreeSpecies getWoodType();
  
  void setWoodType(@NotNull TreeSpecies paramTreeSpecies);
  
  @Deprecated
  double getMaxSpeed();
  
  @Deprecated
  void setMaxSpeed(double paramDouble);
  
  @Deprecated
  double getOccupiedDeceleration();
  
  @Deprecated
  void setOccupiedDeceleration(double paramDouble);
  
  @Deprecated
  double getUnoccupiedDeceleration();
  
  @Deprecated
  void setUnoccupiedDeceleration(double paramDouble);
  
  @Deprecated
  boolean getWorkOnLand();
  
  @Deprecated
  void setWorkOnLand(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Boat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */