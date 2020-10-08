package org.bukkit.entity;

import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public interface Tameable extends Animals {
  boolean isTamed();
  
  void setTamed(boolean paramBoolean);
  
  @Nullable
  UUID getOwnerUniqueId();
  
  @Nullable
  AnimalTamer getOwner();
  
  void setOwner(@Nullable AnimalTamer paramAnimalTamer);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Tameable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */