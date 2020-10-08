package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

public interface Leaves extends BlockData {
  boolean isPersistent();
  
  void setPersistent(boolean paramBoolean);
  
  int getDistance();
  
  void setDistance(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Leaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */