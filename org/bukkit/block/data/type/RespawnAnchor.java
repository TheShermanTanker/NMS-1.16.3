package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

public interface RespawnAnchor extends BlockData {
  int getCharges();
  
  void setCharges(int paramInt);
  
  int getMaximumCharges();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\RespawnAnchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */