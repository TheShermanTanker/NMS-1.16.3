package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

public interface Snow extends BlockData {
  int getLayers();
  
  void setLayers(int paramInt);
  
  int getMinimumLayers();
  
  int getMaximumLayers();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Snow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */