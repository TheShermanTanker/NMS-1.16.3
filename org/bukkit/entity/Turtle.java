package org.bukkit.entity;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface Turtle extends Animals {
  @NotNull
  Location getHome();
  
  void setHome(@NotNull Location paramLocation);
  
  boolean isGoingHome();
  
  boolean isDigging();
  
  boolean hasEgg();
  
  void setHasEgg(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Turtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */