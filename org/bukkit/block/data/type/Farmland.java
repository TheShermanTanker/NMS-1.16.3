package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

public interface Farmland extends BlockData {
  int getMoisture();
  
  void setMoisture(int paramInt);
  
  int getMaximumMoisture();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Farmland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */