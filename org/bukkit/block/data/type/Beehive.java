package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

public interface Beehive extends Directional {
  int getHoneyLevel();
  
  void setHoneyLevel(int paramInt);
  
  int getMaximumHoneyLevel();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Beehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */