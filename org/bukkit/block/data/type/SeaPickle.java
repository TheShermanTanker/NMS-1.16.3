package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

public interface SeaPickle extends Waterlogged {
  int getPickles();
  
  void setPickles(int paramInt);
  
  int getMinimumPickles();
  
  int getMaximumPickles();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\SeaPickle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */