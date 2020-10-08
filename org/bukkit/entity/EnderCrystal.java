package org.bukkit.entity;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public interface EnderCrystal extends Entity {
  boolean isShowingBottom();
  
  void setShowingBottom(boolean paramBoolean);
  
  @Nullable
  Location getBeamTarget();
  
  void setBeamTarget(@Nullable Location paramLocation);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\EnderCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */