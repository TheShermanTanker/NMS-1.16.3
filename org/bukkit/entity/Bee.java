package org.bukkit.entity;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public interface Bee extends Animals {
  @Nullable
  Location getHive();
  
  void setHive(@Nullable Location paramLocation);
  
  @Nullable
  Location getFlower();
  
  void setFlower(@Nullable Location paramLocation);
  
  boolean hasNectar();
  
  void setHasNectar(boolean paramBoolean);
  
  boolean hasStung();
  
  void setHasStung(boolean paramBoolean);
  
  int getAnger();
  
  void setAnger(int paramInt);
  
  int getCannotEnterHiveTicks();
  
  void setCannotEnterHiveTicks(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Bee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */