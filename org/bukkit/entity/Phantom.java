package org.bukkit.entity;

import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public interface Phantom extends Flying {
  int getSize();
  
  void setSize(int paramInt);
  
  @Nullable
  UUID getSpawningEntity();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Phantom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */