package org.bukkit.entity;

import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public interface Animals extends Breedable {
  @Nullable
  UUID getBreedCause();
  
  void setBreedCause(@Nullable UUID paramUUID);
  
  boolean isLoveMode();
  
  int getLoveModeTicks();
  
  void setLoveModeTicks(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Animals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */