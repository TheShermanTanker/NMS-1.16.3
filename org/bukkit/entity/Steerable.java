package org.bukkit.entity;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public interface Steerable extends Animals {
  boolean hasSaddle();
  
  void setSaddle(boolean paramBoolean);
  
  int getBoostTicks();
  
  void setBoostTicks(int paramInt);
  
  int getCurrentBoostTicks();
  
  void setCurrentBoostTicks(int paramInt);
  
  @NotNull
  Material getSteerMaterial();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Steerable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */