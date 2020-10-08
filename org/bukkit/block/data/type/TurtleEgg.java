package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

public interface TurtleEgg extends BlockData {
  int getEggs();
  
  void setEggs(int paramInt);
  
  int getMinimumEggs();
  
  int getMaximumEggs();
  
  int getHatch();
  
  void setHatch(int paramInt);
  
  int getMaximumHatch();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\TurtleEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */