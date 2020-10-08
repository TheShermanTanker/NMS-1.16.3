package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;

public interface Repeater extends Directional, Powerable {
  int getDelay();
  
  void setDelay(int paramInt);
  
  int getMinimumDelay();
  
  int getMaximumDelay();
  
  boolean isLocked();
  
  void setLocked(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Repeater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */