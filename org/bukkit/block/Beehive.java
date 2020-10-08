package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.jetbrains.annotations.Nullable;

public interface Beehive extends EntityBlockStorage<Bee> {
  @Nullable
  Location getFlower();
  
  void setFlower(@Nullable Location paramLocation);
  
  boolean isSedated();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Beehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */